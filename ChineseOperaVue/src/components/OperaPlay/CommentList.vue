<template>
    <!-- 评论区 -->
    <div v-for="(comment, index) in comments" :key="index" class="comment-block">
        <div class="author-gambits" :class="{ 'hasfather':father}">
            <div class="author-info">
                <el-avatar class="header-img" :src="comment.user.avatar"></el-avatar>
                <span class="author-name" >{{ comment.user.nickName }}</span>
                <span class="author-name" v-if="father"> 回复给：{{father.user.nickName}}</span>
                <span class="author-time">{{ comment.createdAt }}</span>
                <span class="reply-people" @click=getPeoPleInfo(comment)>回复</span>
            </div>
            <div class="comment-content">{{ comment.content}}</div>
        </div>
        <div v-if="comment.children && comment.children.length" class="reply-list">
          <comment-list :comments="comment.children" :father="comment" @CommentFromSon="setCommentFromSon"/>
        </div>

        <!-- 回复输入框 -->
        <div class="my-reply reply-comment" v-if="!father">
            <el-avatar class="header-img" :src="avatar"></el-avatar>
            <span class="replying-to">回复:{{reply.replyname}}</span>
            <div class="reply-info">
                
                <div tabindex="0" contenteditable="true" placeholder="输入回复..." class="reply-input reply-comment-input" @input="onDivInput($event)"></div>
            </div>
            <div class="reply-btn-box">
                <el-button @click="sendCommentReply(index)" type="primary">回复评论</el-button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props:{
        comments:{
            type:Array
        },
        father:{
            type:Object,
        }
    },
    data(){
        return{
            userId: '',
            nickName: '发起者1',
            avatar: 'https://ae01.alicdn.com/kf/Hf6c0b4a7428b4edf866a9fbab75568e6U.jpg',
            reply:{
                replyindex:'',
                replyname:'假的',
                replyComment:''
            }
        }
    },
    methods:{
        onDivInput(e) {
            this.reply.replyComment = e.target.innerHTML;
        },
        getPeoPleInfo(comment) {
            this.reply.replyname = comment.user.nickName;
            this.reply.replyindex = index;
            this.$emit('CommentFromSon',comment);
        },
        sendCommentReply(index) {
            if (!this.replyComment.trim()) {
                alert("回复不能为空");
                return;
            }
            const newReply = {
                from: "Lana Del Rey",
                fromHeadImg: this.myHeader,
                to: this.replyname,
                comment: this.replyComment,
                time: new Date().toLocaleString()
            };
            this.comments[index].reply.push(newReply);
            this.replyComment = "";
            const inputContent = document.querySelectorAll(".reply-comment-input")[index];
            if (inputContent) {
                inputContent.innerHTML = "";
            }
        },
        setCommentFromSon(comment){
            this.reply.replyname = comment.user.nickName;
            this.$emit('CommentFromSon',comment);
        }
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
      width: 40px; /* 头像宽度 */
      height: 40px; /* 头像高度 */
      border-radius: 50%;
      margin-right: 10px;
    }
    .author-info {
      flex-grow: 1;
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
    align-items: flex-start;
    margin-top: 10px;
    margin-left: 30px;
    .header-img {
      width: 30px; /* 头像宽度 */
      height: 30px; /* 头像高度 */
      border-radius: 50%;
      margin-right: 10px;
    }
    .reply-info {
        display: flex;
        flex-grow: 1;
        .reply-input {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            min-height: 40px;
            width: 50%;
            &:empty:before {
            content: attr(placeholder);
            color: #ccc;
            }
        }
    }
    .reply-btn-box {
      margin-top: 5px;
      margin-left: 10px;
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