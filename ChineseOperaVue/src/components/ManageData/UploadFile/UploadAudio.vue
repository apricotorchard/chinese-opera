<template>
  <div class="container">
    <div class="upload-area">
      <el-upload
        class="upload"
        drag
        action="http://localhost:8080/file/upload"
        multiple
        method="post"
        ref="upload"
        :auto-upload="false"
        :before-upload="beforeUpload"
        :file-list="fileList"
        :data="uploadData"
        :on-success="handleUploadSuccess"
        @change="handleFileChange"
        :show-file-list="false"
      >
        <el-icon class="el-icon--upload"><upload-filled /></el-icon>
        <div class="el-upload__text">
          <em>拖拽 或 点击 上传文件</em>
        </div>
      </el-upload>
      <div class="custom-file-list">
        <el-scrollbar style="max-height: 150px;">
          <ul>
            <li v-for="(file, index) in fileList" :key="index">
              <el-icon><upload-filled /></el-icon>
              {{ file.name }}
            </li>
          </ul>
        </el-scrollbar>
      </div>
      <div>
            单个文件最大为500MB,总文件最大为1GB
      </div>
    </div>
      
    <div class="base-info">
      <el-form :model = audioList >

      </el-form>
        <span>名称</span>
        <!-- <span>类型</span> -->
        <el-dropdown @command="handleCommand" label-width="auto" style="max-width: 600px">
          <span class="el-dropdown-link" >
            * 类型<el-icon class="el-icon--right"><arrow-down /></el-icon>
          </span>
          <template #dropdown >
            <el-scrollbar style="height:200px">
                <el-dropdown-item v-for="(tag,index) in operaTags" :key="index" :command="tag">{{ tag }}</el-dropdown-item>
            </el-scrollbar>
          </template>
        </el-dropdown>
        <el-button type="success" @click="submitUpload">上传</el-button>
    </div>
    
  </div>
  
</template>
  
<script setup>
    import { ref } from 'vue';
    import { UploadFilled } from '@element-plus/icons-vue'
    import { addAudio } from '@/api/audio.js'
    const upload = ref(null);
    const fileList = ref([]);
    const uploadData = ref({});
    const operaTags = ['京剧', '黄梅戏', '秦腔', '曲剧', '晋剧', '评剧', '豫剧', '吕剧', '昆曲', '越剧', '潮剧', '川剧', '琼剧', '茂腔', '蒲剧', '越调', '赣剧', '湘剧'];
    const selectedTag = ref('')

    const handleCommand = (command) => {
      selectedTag.value = command
      console.log("选中的戏曲类型为:", selectedTag.value)
    }

    const beforeUpload = (file) => {
      const isAudio = file.type.startsWith('audio/');
      // 1.先检查是不是音频格式
      if (!isAudio) {
          alert('只能上传音频文件!');
          return false;
      } 
      return true;
    };

    const handleFileChange = (file, fileListNew) => {
      console.log(fileListNew)
      const allAreAudio = fileListNew.every(f => f.raw.type.startsWith('audio/'));
      if (!allAreAudio) {
        alert('所有文件都必须是音频文件!');
        fileList.value = fileListNew.filter(f => f.raw.type.startsWith('audio/')); // 只保留音频文件
      } else {
        fileList.value = fileListNew; // 更新 fileList
      }
    }


    const submitUpload = () =>{
      // 在这个里面检查需要的字段。
      if (fileList.value.length === 0) {
        alert('请选择需要上传的文件!');
      } else if (upload.value) {
        upload.value.submit();
      }
    }

    // 上传成功后的处理
    const handleUploadSuccess = (response, file, fileList) => {
        console.log(response);
        const AudioInfo = {
          name: file.name,
          // description: '视频描述',
          audioUrl: file.response.data,
          tag:'京剧'
          // 可以根据需求添加更多字段
        };
        // 上传视频的相关信息到数据库
        saveAudioInfo(AudioInfo);
    };
    // 保存视频信息到数据库
    const saveAudioInfo =  (AudioInfo) => {
     
      addAudio(AudioInfo).then(res=>{
        console.log("插入成功");
      })
    };
    
</script>

<style scoped lang="scss">
.container{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100vw;
}
.upload-area{
  background-color: aqua;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 800px;
  height: 40vh;
  .upload{
    width: 600px;
    // height: 500px;
  }
  .custom-file-list {
      width: 600px;
      margin-top: 10px;
      padding: 10px;
      border: 1px solid #dcdfe6;
      border-radius: 5px;
      background-color: #f5f7fa;
      max-height: 100px;
      overflow: auto;
    }

    .custom-file-list ul {
      list-style: none;
      padding: 0;
      margin: 0;
    }

    .custom-file-list li {
      display: flex;
      align-items: center;
      padding: 5px 0;
    }

    .custom-file-list li + li {
      border-top: 1px solid #ebeef5;
    }

    .custom-file-list el-icon {
      margin-right: 10px;
      color: #409EFF;
    }

    .upload-note {
      margin-top: 10px;
      color: #909399;
      font-size: 12px;
    }
}

.base-info{
  height: 50vh;
}
</style>
  