<template>
    <div class="container">
      <div class="upload-area">
        <el-upload
            ref="uploadRef"
            class="upload"
            multiple
            drag
            :limit="1"
            action="http://localhost:8080/file/upload"
            :on-exceed="handleVideoExceed"
            :on-success="handleVideoSuccess"
            :auto-upload="false"
            @change="handleFileChange"
        >
          <el-icon class="el-icon--upload"><upload-filled /></el-icon>
          <div class="el-upload__text">
            <em>拖拽 或 点击 上传视频文件</em>
          </div>
        </el-upload>
        <div>
          文件最大为1GB,新文件将会覆盖旧文件
        </div>
      </div>
  
      <!-- 单个文件信息表单 -->
      <div class="file-info-form">
        <el-form :model="fileInfo" label-width="120px" status-icon>
          <el-form-item label="文件名称">
            <el-input v-model="fileInfo.name" placeholder="请输入文件名称"></el-input>
          </el-form-item>
  
          <el-form-item label="视频封面">
            <el-upload
                ref="uploadPic"
                class="upload-cover"
                action="http://localhost:8080/file/upload"
                :limit="1"
                :on-exceed="handlePicExceed"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :on-success="handleCoverSuccess"
                :auto-upload="false"
                :file-list="picFileList"
                @change="handlePicChange"
                list-type="picture"
            >
              <el-button size="small" type="primary">上传封面</el-button>
            </el-upload>
          </el-form-item>
  
          <el-form-item label="视频描述">
            <el-input v-model="fileInfo.description" placeholder="请输入视频描述"></el-input>
          </el-form-item>
  
          <el-form-item label="视频种类">
            <el-dropdown @command="handleTypeChange" label-width="auto" style="max-width: 600px">
              <span class="el-dropdown-link">
                {{ fileInfo.tag || '请选择视频种类' }}
                <el-icon class="el-icon--right">
                  <arrow-down />
                </el-icon>
              </span>
              <template #dropdown>
                <el-scrollbar style="height:200px">
                  <el-dropdown-item v-for="(tag, index) in operaTags" :key="index" :command="tag">{{ tag }}</el-dropdown-item>
                </el-scrollbar>
              </template>
            </el-dropdown>
          </el-form-item>
  
          <el-form-item>
            <el-button type="primary" @click="uploadVideoFile">上传视频</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </template>

<script setup lang="ts">
    import { ref } from 'vue';
    import { UploadFilled } from '@element-plus/icons-vue';
    import { addVideo } from '@/api/video.js'; // 修改为视频信息的批量添加接口
    import { genFileId } from 'element-plus'
    import type { UploadProps, UploadRawFile } from 'element-plus'
    const fileInfo = ref({
        name: '',
        description: '',
        playUrl:'',
        coverUrl: '',
        tag: '京剧', // 默认视频种类
    });
    const videoInfo =  {
        name: '',
        description: '',
        playUrl:'',
        coverUrl: '',
        tag: '京剧', 
    }
    const uploadRef = ref();
    const uploadPic = ref();
    const operaTags = ['京剧', '黄梅戏', '秦腔', '曲剧', '晋剧', '评剧', '豫剧', '吕剧', '昆曲', '越剧', '潮剧', '川剧', '琼剧', '茂腔', '蒲剧', '越调', '赣剧', '湘剧'];
    const picFileList = [];

    const handleVideoExceed = (files) =>{
        uploadRef.value.clearFiles()
        uploadRef.value.handleStart(files[0])
        
        alert("只能上传一个文件，新文件将要覆盖旧文件");
    };
    
    const handlePicExceed: UploadProps['onExceed'] = (files) => {
        uploadPic.value!.clearFiles()
        const file = files[0] as UploadRawFile
        file.uid = genFileId()
        uploadPic.value!.handleStart(file)
    }
    const handleTypeChange = (newType) => {
        fileInfo.value.tag = newType;
    };

    const handleCoverSuccess = (response) => {
        videoInfo.coverUrl = response.data; // 保存封面 URL
        console.log("1111231312321");
        console.log("封面上传成功:", response.data);
    };
    const handleVideoSuccess = (response) => {
        videoInfo.playUrl = response.data;
        console.log("1111231312321");
        console.log("视频上传成功:", response.data);
        saveVideoInfo();
    }
    const handleRemove: UploadProps['onRemove'] = (uploadFile, uploadFiles) => {
        console.log(uploadFile, uploadFiles)
    }

    const handlePreview: UploadProps['onPreview'] = (file) => {
        console.log(file)
    }
    const handleFileChange = (file) => {
        if (file.raw.type.startsWith('video/')) {
            fileInfo.value.name = file.name; // 设置文件名
        } else {
            alert('只能上传视频文件!');
            uploadRef.value.clearFiles()
        }
    };
    const handlePicChange = (file) =>{
        console.log("现在上传的是",file);
        if (!file.raw.type.startsWith('image/')) {
            alert('文件必须是图片格式!')
            uploadPic.value!.clearFiles()
        } else if (file.size / 1024 / 1024 > 2) {
            alert('文件大小不能超过 2MB!')
            uploadPic.value!.clearFiles()
        }else{
            picFileList.push(file)
        }
    }
    
    const uploadVideoFile = async () => {
        
        console.log(fileInfo);
        if(picFileList.length === 0){
            console.log(uploadPic.value);
            alert("请选择需要上传的视频封面!")
            return;
        }
        if (!fileInfo.value.name) {
            alert("请选择需要上传的视频文件!");
            return;
        }
        
        
        try {
            await uploadPic.value.submit(); // 上传封面
            await uploadRef.value.submit(); // 上传视频
            
        } catch (error) {
            console.error("文件上传失败:", error);
        }
    };

    const saveVideoInfo = () => {
        videoInfo.name = fileInfo.value.name;
        videoInfo.description = fileInfo.value.description;
        videoInfo.tag = fileInfo.value.tag;
        try {
            addVideo(videoInfo);
            alert("视频信息保存成功");
        } catch (error) {
            console.error("视频信息保存失败", error);
        }
    };
</script>
<style scoped lang="scss">
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.upload-area {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 800px;
  height: 30vh;
}

.upload {
  width: 800px;
}

.file-info-form {
  margin-top: 20px;
  width: 800px;
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.el-form-item {
  margin-bottom: 15px;
}

.el-button {
  width: 100%;
}
</style>

  