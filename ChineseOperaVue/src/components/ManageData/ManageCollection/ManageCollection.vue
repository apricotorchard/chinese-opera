<!-- <template>
    <el-button type="success">新增合集</el-button>
    <el-table :data="collectionList" style="width: 100%">
        <el-table-column type="selection" width="55" />
        <el-table-column property="name" label="名称" width="150" show-overflow-tooltip/>
        <el-table-column property="singer" label="演唱者" width="150" show-overflow-tooltip/>
        <el-table-column property="des" label="描述" width="200" show-overflow-tooltip/>
        <el-table-column property="tag" label="Tag" width="100"></el-table-column>

        <el-table-column property="collectName" label="合集" width="200"/>

        <el-table-column  label="操作">
        <template #default="scope">
            <el-button link type="primary" size="small" @click="handleDeleteClick">
            删除
            </el-button>
            <el-button link type="primary" size="small" @click="handleEditClick(scope.row)">
            编辑
            </el-button>
        </template>
            
        </el-table-column>
    </el-table>

    <div class="pagination-fixed-bottom">  
        
        <el-pagination
        v-model:current-page="pageNum"
        v-model:page-size="pageSize"
        layout="prev, pager, next, jumper"
        :total="total"
        @current-change="handleCurrentChange"
        />
            
    </div>
</template>

<script>
export default {
    data(){
        return{
            pageNum:1,
            pageSize:10,
            collectionList:[],
        }
    },
    created(){
        this.loadCollectionInfo();
    },
    method:{
        loadCollectionInfo(){

        },
        handleEditClick(collection){

        },
        handleDeleteClick(){

        },
        handleCurrentChange(){

        }
    }
    
}
</script>

<style>

</style> -->