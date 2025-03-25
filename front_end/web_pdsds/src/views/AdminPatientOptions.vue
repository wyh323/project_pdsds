<template>  
  <div>  
    <el-row type="flex" justify="end" align="middle" style="margin-bottom: 10px;">  
      <el-col :span="8">  
        <el-input  
          v-model="search"  
          size="mini"  
          placeholder="输入关键字搜索"  
        />  
      </el-col>  
      <el-button type="warning" style="margin-left: 5px;" @click="searchData">搜索</el-button>  
    </el-row>  
  
    <el-table :data="currentPageData" style="width: 100%; font-size: 12px;" border>  
      <el-table-column label="患者id" prop="id" width="130"></el-table-column>  
      <el-table-column label="患者用户名" prop="username" width="170"></el-table-column>  
      <el-table-column label="患者姓名" prop="nickname" width="150"></el-table-column>  
      <el-table-column label="患者邮箱" prop="email" width="200"></el-table-column>  
      <el-table-column label="医生id" prop="doctor_id" width="170"></el-table-column>  
      <el-table-column label="医生姓名" prop="doctor_nickname" width="170"></el-table-column>  
      <el-table-column label="创建时间" prop="createTime" width="170"></el-table-column>  
      <el-table-column label="更新时间" prop="updateTime" width="170"></el-table-column>  
    </el-table>  
  
    <div style="margin-top: 10px;">  
      <el-pagination  
        background  
        layout="prev, pager, next"  
        :total="total"  
        :page-size="pageSize"  
        :current-page="currentPage"  
        @current-change="handleCurrentChange">  
      </el-pagination>  
    </div>  
  </div>  
</template>  
  
<script setup>  
import { ref, onMounted, computed } from 'vue';  
import { getAllPatientInfo } from '@/api/admin'; 

const tableData = ref([]);  
const filteredData = ref([]);  
const search = ref('');  
const total = ref(0);  
const currentPage = ref(1);  
const pageSize = ref(10);  
  
const currentPageData = computed(() => {  
  const start = (currentPage.value - 1) * pageSize.value;  
  return filteredData.value.slice(start, start + pageSize.value);  
});  
  
async function getTableData() {  
  try {  
    const res = await getAllPatientInfo();  
    tableData.value = res.tableData;   
    total.value = res.total;   
    filteredData.value = tableData.value;  
  } catch (error) {  
    console.error('获取患者信息时出错:', error);  
  }  
}  
  
const searchData = () => {  
  if (search.value) {  
    const searchLower = search.value.toLowerCase();  
    filteredData.value = tableData.value.filter(data =>  
      data.nickname.toLowerCase().includes(searchLower) ||  
      data.username.toLowerCase().includes(searchLower) ||  
      data.id.toString().includes(searchLower) ||  
      data.doctor_id.toString().includes(searchLower) ||    
      data.doctor_nickname.toLowerCase().includes(searchLower)   
    );  
  } else {  
    filteredData.value = tableData.value;   
  }  
      
  currentPage.value = 1;  
  search.value = '';  
};  
  
const handleCurrentChange = (newPage) => {  
  currentPage.value = newPage;   
};  
  
onMounted(() => {  
  getTableData();   
});  
</script>  
  
<style>  
.upload-demo i {  
  font-size: 28px;  
}  
</style>