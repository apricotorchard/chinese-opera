<template>
    <!-- 评论区 -->
    <div v-for="(comment, index) in localComments" :key="index" class="comment-block">
        <div class="author-gambits" :class="{ 'hasfather':father}">
            <div class="author-info">
                <el-avatar class="header-img" :src="comment.user.avatar"></el-avatar>
                <span class="author-name" >{{ comment.user.nickName }}</span>
                <span class="author-name" v-if="father"> 回复给：{{father.user.nickName}}</span>
                <span class="author-time">{{ comment.createdAt }}</span>
                <span class="reply-people" @click=getUserInfo(comment,index)>回复</span>
            </div>
            <div class="comment-content">{{ comment.content}}</div>
        </div>
        <div v-if="comment.children && comment.children.length" class="reply-list">
          <comment-list :comments="comment.children" :father="comment" :fatherIndex="index" @CommentFromSon="setCommentFromSon"/>
        </div>

        <!-- 回复输入框 -->
        <div class="reply-comment" v-if="!father&&inputIndex(index)">
            <el-avatar class="header-img" :src="userStore.avatar"></el-avatar>
            <span class="replying-to">回复:{{reply.replyname}}</span>
            <div class="reply-info">
                <div contenteditable="true" placeholder="输入回复..." :ref="'replyInput' + index" class="reply-input reply-comment-input" @input="onDivInput($event)"></div>
            </div>
            <div class="reply-btn-box">
                <el-button @click="sendCommentReply(index)" type="primary">发布</el-button>
            </div>
        </div>
    </div>
</template>

<script>
import {addComment,getCommentsByOperaId} from '@/api/opera.js';
import useUserStore from '@/stores/userStore';
export default {
    emits: ['CommentFromSon'],
    props:{
        comments:{
            type:Array
        },
        father:{
            type:Object,
        },
        fatherIndex:{
            type:Number
        }
    },
    computed:{
      userStore(){
        return useUserStore();
      }
    },
    data(){
        return{
            isShowInput:false,  //用于判断是否展示输入框
            ischoicedcommentId:'', //用于判断是否连续两次点击相同的comment
            reply:{
                replyindex:-1,
                replyname:'',
                replyComment:''
            },
            localComments: [...this.comments]
        }
    },
    watch: {
        comments(newComments) {
            this.localComments = [...newComments]; // 当 props 的 comments 变化时更新 localComments
        },
    },
    methods:{
        
        inputIndex(index){
            return this.reply.replyindex == index&&this.isShowInput;
        },
        onDivInput(e) {
            this.reply.replyComment = e.target.innerHTML;
        },
        getUserInfo(comment,index) {
            if(this.father){
                this.$emit('CommentFromSon',comment,this.fatherIndex);
            }
            else{
                this.reply.replyname = comment.user.nickName;
                this.reply.replyindex = index;
                // 为了实现两次点击相同的commentid,评论输入框消失的。
                if(!this.ischoicedcommentId){
                    this.ischoicedcommentId=comment.id;
                    this.isShowInput = this.isShowInput==true ? false:true;
                }
                else if(this.ischoicedcommentId == comment.id){
                    this.isShowInput = this.isShowInput==true ? false:true;
                }
                else if(this.ischoicedcommentId&&this.ischoicedcommentId!=comment.id){
                    this.ischoicedcommentId=comment.id;

                }
            }
        },
        setCommentFromSon(comment,fatherIndex){
            if(this.father){
                this.$emit('CommentFromSon',comment,this.fatherIndex);
            }
            else{
                this.reply.replyname = comment.user.nickName;
                this.reply.replyindex = fatherIndex;
                if(!this.ischoicedcommentId){
                    this.ischoicedcommentId=comment.id;
                    this.isShowInput = this.isShowInput==true ? false:true;
                }
                else if(this.ischoicedcommentId == comment.id){
                    this.isShowInput = this.isShowInput==true ? false:true;
                }
                else if(this.ischoicedcommentId&&this.ischoicedcommentId!=comment.id){
                    this.ischoicedcommentId=comment.id;
                }
               
            }
        },
        sendCommentReply(index) {
            if (!this.reply.replyComment.trim()) {
                alert("回复不能为空");
                return;
            }
            // 执行插入评论数据的操作
           
            const insertComment = {
                userId:this.userStore.userId,
                operaId:this.comments[0].operaId,
                parentId:this.ischoicedcommentId,
                content:this.reply.replyComment,
            }
            
            addComment(insertComment).then(res=>{
                getCommentsByOperaId(this.comments[0].operaId).then(response => {

                    this.localComments = response.data.data; 
                });
                
                console.log("发布成功");
                // 清空输入框操作。
                const inputContent = this.$refs['replyInput' + index]?.[0]; // .[0] 取第一个匹配的元素
                if (inputContent) {
                    inputContent.innerHTML = "";
                }
                
            })
            
        },
    }
}
</script>

<style lang="scss" scoped>
.comment-block {
  .author-gambits{
    margin-bottom: 20px;
    .author-info {
        margin-bottom: 10px;
        .header-img {
            border-radius: 50%;
            margin-right: 10px;
            width:30px;
            height:30px;
        }
        .author-name {
            font-weight: bold;
        }
        .author-time,.reply-people{
            color: #999;
            font-size: 12px;
            margin-left: 10px;
        }
        .reply-people:hover{
          color: #7FCBF8;
        }
       
    }
    .comment-content {
        margin-left: 50px;
        margin-bottom: 10px;
        font-size: 14px;
        line-height: 1.6;
    }
  }
  .hasfather{
    margin-left: 50px;
  }
}
 .reply-block {
    margin-left: 50px;
    padding: 10px;
    border-radius: 5px;
    background: #f9f9f9;
    margin-bottom: 10px;
    .header-img {
      width: 30px; /* 头像宽度 */
      height: 30px; /* 头像高度 */
      border-radius: 50%;
      margin-right: 10px;
    }
    .author-info {
      display: flex;
      .author-name {
        font-weight: bold;
        margin-right: 10px;
      }
      .author-time {
        color: #999;
        font-size: 12px;
      }
    }
    .reply-content {
      margin-top: 5px;
      font-size: 14px;
    }
  }
.reply-comment {
    display: flex;
    // align-items: flex-start;
    justify-content: center;
    align-items: center;
    margin-top: 10px;
    margin-left: 30px;
    .header-img {
      width: 30px;
      height: 30px; 
      border-radius: 50%;
      margin-right: 10px;
    }
    .reply-info {
        margin-left: 20px;
        display: flex;
        flex-grow: 1;
        .reply-input {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            min-height: 40px;
            width: 90%;
            &:empty:before {
            content: attr(placeholder);
            color: #ccc;
            }
        }
    }
    .reply-btn-box {
      margin-top: 5px;
      margin-right: 100px;
    }
}

</style>

<!-- 回复列表 -->
<!-- <div v-for="(reply, replyIndex) in comment.reply" :key="replyIndex" class="reply-block" >
        <div class="author-info">
            <el-avatar class="header-img" :src="reply.fromHeadImg" style="width:40px;height:40px"></el-avatar>
            <span class="author-name">{{ reply.from }}</span>
            <span class="author-time">{{ reply.time }}</span>
            <span class="reply-people" @click=getPeoPleInfo(index,reply.from)>回复</span>
        </div>
        <div class="reply-content">
            回复 {{ reply.to }}: {{ reply.comment }}
        </div>
</div> -->


<!-- //如何做到实时的将要添加的数据显示到前端界面上？
// const insertCommentVue = {
//     userId:this.userId,
//     operaId:this.comments[0].operaId,
//     parentId:this.ischoicedcommentId,
//     content:this.reply.replyComment,
//     createdAt: new Date().toISOString(),
//     children: [] // 新回复默认为无子评论
// }
//1.查找当前要回复的评论，并将新评论插入到其children数据当中
// const targetComment = findCommentById(this.comments,this.ischoicedcommentId);
// console.log(targetComment);
//2.如果children 不存在，初始化为空数组再添加。
// if (targetComment) {
//     if (!targetComment.children) {targetComment.children = [];}
//     targetComment.children.push(insertCommentVue);
// } -->