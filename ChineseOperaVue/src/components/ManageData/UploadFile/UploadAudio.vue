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
          <em>拖拽 或 点击 上传音频文件</em>
        </div>
      </el-upload>
      <div>
            单个文件最大为500MB,总文件最大为1GB
      </div>
      
      
    </div>
    <div class="custom-file-list">
          <el-table :data="fileList" style="width:100%" @selection-change="handleSelectionChange" v-loading="loading">
            <el-table-column type="selection" width="55" />

            <el-table-column label="名称" width="250">
              <template #default="scope">
                <el-input v-model="scope.row.name"
                placeholder="请输入文件名称"
                @blur="handleNameChange(scope.row)"
                size="small"
                />
              </template>
              
            </el-table-column>
            <el-table-column label="类型" width="200">
              <template #default="scope">
                <el-dropdown @command="handleTypeChange(scope.row, $event)" label-width="auto" style="max-width: 600px">
                  <span class="el-dropdown-link" >
                    {{scope.row.tag}}
                    <el-icon class="el-icon--right">
                      <arrow-down />
                    </el-icon>
                  </span>
                  <template #dropdown >
                    <el-scrollbar style="height:200px">
                        <el-dropdown-item v-for="(tag,index) in operaTags" :key="index" :command="tag">{{ tag }}</el-dropdown-item>
                    </el-scrollbar>
                  </template>
                </el-dropdown>
              </template>
             
            </el-table-column>
            <el-table-column label="操作">
              <template #default="scope">
                <el-button
                  size="small"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)"
                >
                  删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          
      </div>
      <div style="margin-top: 16px;width:800px">
        <el-button style="width:100%" type="primary" @click="uploadSelectedFiles">上传选中的文件</el-button>
      </div>
  </div>
  
</template>
  
<script setup>
    import { ref } from 'vue';
    import { UploadFilled } from '@element-plus/icons-vue'
    import { uploadFile } from "@/api/audio.js";
    import { addAudioBatch} from '@/api/audio.js'
    const loading = ref(false);
    const upload = ref(null);
    const fileList = ref([]);
    const uploadData = ref({});
    const operaTags = ['京剧', '黄梅戏', '秦腔', '晋剧', '评剧', '豫剧', '越剧', '川剧', '花鼓戏'];
    const selectedFiles = ref([]);
    
    const handleNameChange = (row)=> {
      console.log('文件名称已修改:', row.name);
    };
    
    const handleTypeChange = (row, newType) =>{
      row.tag = newType;
    };

    const handleSelectionChange = (selected)=> {
      selectedFiles.value = selected;
    };

    const handleDelete = (index) => {
      fileList.value.splice(index, 1);
    };

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
      const taggedFile = {
        ...file,
        tag:'京剧'
      }
      // 检查文件是否是音频文件
      if (taggedFile.raw.type.startsWith('audio/')) {
        // 如果是音频文件，加入到 fileList
        fileList.value = [...fileList.value, taggedFile];
      } else {
        // 如果不是音频文件，弹出提示
        alert('文件必须是音频文件!');
      }
    }

    // 上传选中的文件
    async function uploadSelectedFiles(){
      if (selectedFiles.value.length === 0) {
        alert("请选择需要上传的文件!");
        return;
      }
      console.log("开始上传文件:", selectedFiles.value);
      loading.value = true;
      // 这里可以调用 API 进行文件上传
      // 同步上传改为并发上传，🐂
      const uploadPromises = selectedFiles.value.map(file => {
        const formData = new FormData();
        formData.append("file", file.raw);
        return uploadFile(formData)
          .then(response => {
            file.url = response.data; // 更新文件的 URL
            console.log("文件上传成功:", response.data);
            
            
          })
          .catch(error => {
            console.error("文件上传失败:", error);
            alert("文件上传失败");
          });
      });
      await Promise.all(uploadPromises);
      saveAudioInfo(selectedFiles);
    };
    // 保存视频信息到数据库
    const saveAudioInfo =  async (selectedFiles) => {
      const audioInfos = selectedFiles.value.map(audio=>{
        return {
          name:audio.name,
          audioUrl:audio.url.data,
          tag:audio.tag
        }
      })
      console.log(audioInfos);
      try{
        await addAudioBatch(audioInfos);
        alert("文件上传成功");
        loading.value = false;
        fileList.value = []
      }catch(error){
        console.error("批量插入失败", error);
      }
    };
    
</script>

<style scoped lang="scss">
.container{
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}
.upload-area{
  // background-color: aqua;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 800px;
  height: 30vh;
  .upload{
    width: 800px;
  }
}
.custom-file-list {
      width: 800px;
      // margin-top: 10px;
      padding: 10px;
      border: 1px solid #dcdfe6;
      border-radius: 5px;
      background-color: #f5f7fa;
}

.base-info{
  height: 50vh;
}
</style>
  