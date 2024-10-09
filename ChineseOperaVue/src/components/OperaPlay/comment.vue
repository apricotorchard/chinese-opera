<template>
    <!-- 用户输入完之后，无法回复用户 -->
  <div class="comment-container">
    <div class="my-reply">
        <el-avatar class="header-img" :src="myHeader"></el-avatar>
        <div class="reply-info">
            <div tabindex="0" contenteditable="true" id="replyInput" placeholder="输入评论..." class="reply-input" @focus="showReplyBtn" @input="onDivInput($event)"></div>
        </div>
        <div class="reply-btn-box" v-show="btnShow">
          <el-button @click="sendComment" type="primary">发表评论</el-button>
        </div>
    </div>
    <!-- 评论区 -->
    <div v-for="(comment, index) in comments" :key="index" class="comment-block">
        <div class="author-gambits">
            <div class="author-info">
                <el-avatar class="header-img" :src="comment.headImg" style="width:40px;height:40px"></el-avatar>
                <span class="author-name">{{ comment.name }}</span>
                <span class="author-time">{{ comment.time }}</span>
            </div>
            <div class="comment-content">{{ comment.comment }}</div>
  
            <!-- 回复列表 -->
            <div v-for="(reply, replyIndex) in comment.reply" :key="replyIndex" class="reply-block" @click=getReplyIndex(replyIndex)>
                    <div class="author-info">
                        <el-avatar class="header-img" :src="reply.fromHeadImg" style="width:40px;height:40px"></el-avatar>
                        <span class="author-name">{{ reply.from }}</span>
                        <span class="author-time">{{ reply.time }}</span>
                    </div>
                    <div class="reply-content">
                        回复 {{ reply.to }}: {{ reply.comment }}
                    </div>
            </div>
        </div>
         <!-- 回复输入框 -->
        <div v-if=inputShow(index) class="my-reply reply-comment">
            <el-avatar class="header-img" :src="myHeader"></el-avatar>
            <div class="reply-info">
                <div class="replying-to">回复 {{replyname}}:</div>
                <div tabindex="0" contenteditable="true" placeholder="输入回复..." class="reply-input reply-comment-input" @input="onDivInput($event)"></div>
            </div>
            <div class="reply-btn-box">
                <el-button @click="sendCommentReply(index)" type="primary">回复评论</el-button>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
export default {
    name: "CommentSection",
    data() {
      return {
        btnShow: false,
        myHeader: "https://ae01.alicdn.com/kf/Hf6c0b4a7428b4edf866a9fbab75568e6U.jpg",
        replyComment: "",
        ReplyIndex:-1,
        replyname:'',
        comments: [
          {
            name: "Lana Del Rey",
            headImg: "https://ae01.alicdn.com/kf/Hf6c0b4a7428b4edf866a9fbab75568e6U.jpg",
            comment: "我发布一张新专辑Norman Fucking Rockwell,大家快来听啊",
            time: "2019年9月16日 18:43",
            reply: [
              {
                from: "Taylor Swift",
                fromHeadImg: "https://ae01.alicdn.com/kf/Hf6c0b4a7428b4edf866a9fbab75568e6U.jpg",
                to: "Lana Del Rey",
                comment: "我很喜欢你的新专辑！！",
                time: "2019年9月16日 18:43",
              },
              {
                from: "Ariana Grande",
                fromHeadImg: "https://ae01.alicdn.com/kf/Hf6c0b4a7428b4edf866a9fbab75568e6U.jpg",
                to: "Lana Del Rey",
                comment: "别忘记宣传我们的合作单曲啊",
                time: "2019年9月16日 18:43",
              },
            ],
          },
          {
            name: "Lana Del Rey",
            headImg: "https://ae01.alicdn.com/kf/Hf6c0b4a7428b4edf866a9fbab75568e6U.jpg",
            comment: "这是我第二次发评论",
            time: "2019年9月16日 18:43",
            reply: [
              {
                from: "Taylor Swift",
                fromHeadImg: "https://ae01.alicdn.com/kf/Hf6c0b4a7428b4edf866a9fbab75568e6U.jpg",
                to: "Lana Del Rey",
                comment: "我很喜欢你的新专辑！！",
                time: "2019年9月16日 18:43",
              },
              {
                from: "Ariana Grande",
                fromHeadImg: "https://ae01.alicdn.com/kf/Hf6c0b4a7428b4edf866a9fbab75568e6U.jpg",
                to: "Lana Del Rey",
                comment: "别忘记宣传我们的合作单曲啊",
                time: "2019年9月16日 18:43",
              },
            ],
          },
        ],
      };
    },
    methods:{
        showReplyBtn() {
            this.btnShow = true;
        },
        hideReplyBtn() {
            this.btnShow = false;
        },
        onDivInput(e) {
            this.replyComment = e.target.innerHTML;
        },
        sendComment() { //发送的评论
            if (!this.replyComment.trim()) {
                alert("评论不能为空");
                return;
            }
            const newComment = {  //这个应该要写入到数据库当中去
                name: "Lana Del Rey",
                headImg: this.myHeader,
                comment: this.replyComment,
                time: new Date().toLocaleString(),
                reply: [],
            };
            this.comments.push(newComment);
            this.replyComment = "";
            document.getElementById("replyInput").innerHTML = "";//清空
      },
      getReplyIndex(replyIndex){
            console.log(replyIndex)
            this.ReplyIndex = replyIndex;
      },
      inputShow(index){
        if(this.ReplyIndex===-1){
            return false;
        }
        this.replyname = this.comments[index].reply[this.ReplyIndex].from;
        return this.replyname;
      },
      sendCommentReply(index) { //评论回复
        if (!this.replyComment.trim()) {
          alert("回复不能为空");
          return;
        }
        const newReply = {
          from: "Lana Del Rey",
          fromHeadImg: this.myHeader,
          to: this.comments[index].name,
          comment: this.replyComment,
          time: new Date().toLocaleString(),
        };
        this.comments[index].reply.push(newReply);
        this.replyComment = "";
      },
    }
}
</script>

<style lang="scss" scoped>
.my-reply {
  display: flex;
  align-items: flex-start;
  margin-bottom: 20px;
  .header-img {
    width: 30px; /* 头像宽度 */
    height: 30px; /* 头像高度 */
    border-radius: 50%;
    margin-right: 10px;
    object-fit: cover;
  }
  .reply-info {
    flex-grow: 1;
    .reply-input {
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
      min-height: 40px;
      width: 100%;
      &:empty:before {
        content: attr(placeholder);
        color: #ccc;
      }
    }
  }
  .reply-btn-box {
    margin: 5px 5px 0 5px;
  }
}

.comment-block {
  margin-bottom: 30px;
  padding-bottom: 15px;
  border-bottom: 1px solid #eaeaea;
  .author-gambits{
    margin-bottom: 20px;
    .author-info {
        margin-bottom: 10px;
        .header-img {
            border-radius: 50%;
            margin-right: 10px;
        }
        .author-name {
            font-weight: bold;
            margin-right: 10px;
        }
        .author-time {
            color: #999;
            font-size: 12px;
        }
    }
    .comment-content {
        margin-left: 50px;
        margin-bottom: 10px;
        font-size: 14px;
        line-height: 1.6;
    }
  }
}
 /* 回复部分样式 */
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
    //   flex-direction: column;

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
//   回复的内容的输入框
.reply-comment {
    display: flex;
    align-items: flex-start;
    margin-top: 10px;
    .header-img {
      width: 40px; /* 头像宽度 */
      height: 40px; /* 头像高度 */
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
        width: 100%;
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