package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("select * from comments where opera_id = #{operaId}")
    List<Comment> selectByOperaId(long operaId);

    @Insert("insert into comments(user_id,opera_id,content,parent_id) values(#{userId},#{operaId},#{content},#{parentId}) ")
    boolean insertComment(Comment comment);
}
