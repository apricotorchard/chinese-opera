package com.example.springboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.constant.HttpStatus;
import com.example.springboot.domain.Comment;
import com.example.springboot.utils.RedisCache;
import com.example.springboot.utils.ResponseResult;
import com.example.springboot.domain.User;
import com.example.springboot.mapper.CommentMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.example.springboot.common.constant.Constants.OPERA_COMMENT;
import static com.example.springboot.common.constant.Constants.OPERA_COMMENT_TTL;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisCache redisCache;
    //1.添加一个线程安全的队列
    private final ConcurrentLinkedDeque<Comment> commentQueue = new ConcurrentLinkedDeque<>();
    @Override
    public ResponseResult getCommentsByOperaId(long operaId) {
        String cacheKey = OPERA_COMMENT + ":" + operaId;
        //1.先检查缓存当中是否有
        List<Comment> rootComments = redisCache.getCacheList(cacheKey);
        //2.如果没有
        if(rootComments==null||rootComments.isEmpty()){
            List<Comment> commentList = commentMapper.selectByOperaId(operaId);
            for(Comment comment:commentList){
                User user = userMapper.selectById(comment.getUserId());
                comment.setUser(user);
            }
            //将评论按照parentId进行分组
            Map<Long,List<Comment>> groupedComments = commentList.stream().collect(Collectors.groupingBy(comment-> comment.getParentId() == null ? -1L : comment.getParentId()));

            rootComments = buildCommentsTree(groupedComments,-1L);

            redisCache.setCacheList(cacheKey,rootComments);
            redisCache.expire(cacheKey,OPERA_COMMENT_TTL, TimeUnit.MINUTES);
        }
        return new ResponseResult<>(HttpStatus.SUCCESS,"获取戏曲评论成功",rootComments);
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        boolean total = commentMapper.insertComment(comment);
        //成功插入到数据库当中，以及更新缓存

        if(total){
            //2.将评论添加到队列当中
            //commentQueue.add(comment);
            //异步
            CompletableFuture.runAsync(()->{
                String cacheKey = OPERA_COMMENT + ":" + comment.getOperaId();
                redisCache.deleteObject(cacheKey);
            });

            return new ResponseResult<>(HttpStatus.SUCCESS, "插入数据成功");
        }
        return new ResponseResult<>(HttpStatus.ERROR, "插入数据失败");
    }


    private List<Comment> buildCommentsTree(Map<Long, List<Comment>> groupedComments, Long parentId) {
        List<Comment> commentList = groupedComments.get(parentId);
        if(commentList == null){
            return new ArrayList<>();
        }
        for(Comment comment: commentList){
            List<Comment> children = buildCommentsTree(groupedComments, comment.getId());
            comment.setChildren(children);
        }
        return commentList;
    }

//    @Scheduled(fixedRate = 5000) // 每5秒执行一次
//    public void batchUpdateCache() {
//        if (!commentQueue.isEmpty()) {
//            Map<Long, List<Comment>> commentsByOperaId = commentQueue.stream()
//                    .collect(Collectors.groupingBy(Comment::getOperaId));
//            commentQueue.clear(); // 清空队列
//
//            commentsByOperaId.forEach((operaId, comments) -> {
//                String cacheKey = OPERA_COMMENT + ":" + operaId;
//                // 获取现有缓存数据
//                List<Comment> cachedComments = redisCache.getCacheList(cacheKey);
//                if (cachedComments == null) {
//                    cachedComments = new ArrayList<>();
//                }
//                // 添加新的评论到缓存
//                cachedComments.addAll(comments);
//                redisCache.setCacheList(cacheKey, cachedComments);
//                redisCache.expire(cacheKey, OPERA_COMMENT_TTL, TimeUnit.MINUTES);
//            });
//        }
//    }
}
