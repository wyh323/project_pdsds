<script setup>
import { ref } from 'vue'
import { User, Lock } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import { myPatientInfo } from '@/api/scale.js'
import { usepatientNameStore } from '@/stores/patientname.js'

const router = useRouter();
const patientName = usepatientNameStore();
const responseData = ref([
    {
            "id": 1,
            "username": "patient01",
            "nickname": "csndm",
            "email": "114514@qq.com",
            "pic": "http",
            "doctor_id": "1",
            "doctor_nickname": "1234",
            "createTime": "2024-12-08T16:38:14",
            "updateTime": "2024-12-08T16:38:14"
        },
        {
            "id": 2,
            "username": "patient02",
            "nickname": "clndb",
            "email": "1919810@qq.com",
            "pic": "http",
            "doctor_id": "1",
            "doctor_nickname": "4321",
            "createTime": "2024-12-08T16:38:16",
            "updateTime": "2024-12-08T16:38:16"
        }
]);

const getinfo = async () => {
    const result = await myPatientInfo();
    ElMessage.success(result.message ? result.message : '刷新成功')
    responseData.value =result.data;
};

const toDetail = (row) => {
    patientName.setName(row.username);
    router.push('/doctormain/viewpatientdetail')
}


</script>

<template>
    <a>查看病人信息</a>
    <el-button type="primary" @click="getinfo">刷新</el-button>
    <el-table :data="responseData" style="width: 100%">
        <el-table-column fixed prop="id" label="病人id" width="150" />
        <el-table-column prop="username" label="用户名" width="120" />
        <el-table-column prop="nickname" label="昵称" width="120" />
        <el-table-column prop="doctor_id" label="医生id" width="120" />
        <el-table-column prop="doctor_nickname" label="医生昵称" width="120" />
        <el-table-column prop="createTime" label="创立时间" width="120" />
        <el-table-column fixed="right" label="操作" min-width="120">
            <template #default="scope">
                <el-button link type="primary" size="small" @click="toDetail(scope.row)">详细信息</el-button>
                <el-button link type="primary" size="small" >修改</el-button>
            </template>
        </el-table-column>
    </el-table>
</template>
<style lang="scss" scoped>

</style>