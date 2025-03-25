<script setup>
import { ElMessage } from 'element-plus'
import { User, Lock } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'
import { patientDetail } from '@/api/scale.js'
import { usepatientNameStore } from '@/stores/patientname.js'
import { computed, ref } from 'vue'

const router = useRouter();
const patientName = usepatientNameStore();
const responseData = ref([
    {
      "id": 0,
      "username": "",
      "nickname": "",
      "email": "",
      "pic": "",
      "doctor_id": "",
      "doctor_nickname": "",
      "createTime": "",
      "updateTime": ""
    },
    {
      "id": 0,
      "patient_id": 0,
      "patient_nickname": "",
      "createTime": "",
      "updateTime": "",
      "grade": 0,
      "result": "",
      "sds_1": 0,
      "sds_2": 0,
      "sds_3": 0,
      "sds_4": 0,
      "sds_5": 0,
      "sds_6": 0,
      "sds_7": 0,
      "sds_8": 0,
      "sds_9": 0,
      "sds_10": 0,
      "sds_11": 0,
      "sds_12": 0,
      "sds_13": 0,
      "sds_14": 0,
      "sds_15": 0,
      "sds_16": 0,
      "sds_17": 0,
      "sds_18": 0,
      "sds_19": 0,
      "sds_20": 0
    },
    {
      "id": 0,
      "patient_id": 0,
      "patient_nickname": "",
      "createTime": "",
      "updateTime": "",
      "grade": 0,
      "result": "",
      "ces_d_1": 0,
      "ces_d_2": 0,
      "ces_d_3": 0,
      "ces_d_4": 0,
      "ces_d_5": 0,
      "ces_d_6": 0,
      "ces_d_7": 0,
      "ces_d_8": 0,
      "ces_d_9": 0,
      "ces_d_10": 0,
      "ces_d_11": 0,
      "ces_d_12": 0,
      "ces_d_13": 0,
      "ces_d_14": 0,
      "ces_d_15": 0,
      "ces_d_16": 0,
      "ces_d_17": 0,
      "ces_d_18": 0,
      "ces_d_19": 0,
      "ces_d_20": 0
    },
    {
      "id": 0,
      "patient_id": 0,
      "patient_nickname": "",
      "createTime": "",
      "updateTime": "",
      "grade": 0,
      "result": "",
      "madrs_1": 0,
      "madrs_2": 0,
      "madrs_3": 0,
      "madrs_4": 0,
      "madrs_5": 0,
      "madrs_6": 0,
      "madrs_7": 0,
      "madrs_8": 0,
      "madrs_9": 0,
      "madrs_10": 0
    }
])
const getinfo = async () => {
    const result = await patientDetail(patientName.name);
    ElMessage.success(result.message ? result.message : '刷新成功')
    responseData.value =result.data;
};

const patientSDSData = computed(() => {
  const item = responseData.value.find(item => item !== null && 'sds_1' in item);
  return item || {};
});
 
const cesdData = computed(() => {
  const item = responseData.value.find(item => item !== null && 'ces_d_1' in item);
  return item || {};
});
 
const madrsData = computed(() => {
  const item = responseData.value.find(item => item !== null && 'madrs_1' in item);
  return item || {};
});
 
const patientData = computed(() => {
  const item = responseData.value.find(item => item !== null && 'username' in item);
  return item || {};
});
</script>

<template>
    <a>病人详细信息</a>
    <el-button type="primary" @click="getinfo">刷新</el-button>
    <el-descriptions title="个人信息" direction="vertical" :column="5" border>
        <el-descriptions-item label="id" >{{ patientData.id }}</el-descriptions-item>
        <el-descriptions-item label="用户名" >{{ patientData.username }}</el-descriptions-item>
        <el-descriptions-item label="昵称" >{{ patientData.nickname }}</el-descriptions-item>
        <el-descriptions-item label="邮箱" :span="2">{{ patientData.email }}</el-descriptions-item>
        <el-descriptions-item label="医生id" >{{ patientData.doctor_id }}</el-descriptions-item>
        <el-descriptions-item label="医生昵称" >{{ patientData.doctor_nickname }}</el-descriptions-item>
        <el-descriptions-item label="创建时间" :span="2">{{ patientData.createTime }}</el-descriptions-item>
        <el-descriptions-item label="更新时间" :span="2">{{ patientData.updateTime }}</el-descriptions-item>
    </el-descriptions>
    <el-descriptions title="抑郁症自评量表SDS" direction="vertical" :column="5" :data="[patientSDSData.value]" border>
        <el-descriptions-item label="问题1" >{{ patientSDSData.sds_1 }}</el-descriptions-item>
        <el-descriptions-item label="问题2" >{{ patientSDSData.sds_2 }}</el-descriptions-item>
        <el-descriptions-item label="问题3" >{{ patientSDSData.sds_3 }}</el-descriptions-item>
        <el-descriptions-item label="问题4" >{{ patientSDSData.sds_4 }}</el-descriptions-item>
        <el-descriptions-item label="问题5" >{{ patientSDSData.sds_5 }}</el-descriptions-item>
        <el-descriptions-item label="问题6" >{{ patientSDSData.sds_6 }}</el-descriptions-item>
        <el-descriptions-item label="问题7" >{{ patientSDSData.sds_7 }}</el-descriptions-item>
        <el-descriptions-item label="问题8" >{{ patientSDSData.sds_8 }}</el-descriptions-item>
        <el-descriptions-item label="问题9" >{{ patientSDSData.sds_9 }}</el-descriptions-item>
        <el-descriptions-item label="问题10" >{{ patientSDSData.sds_10 }}</el-descriptions-item>
        <el-descriptions-item label="问题11" >{{ patientSDSData.sds_11 }}</el-descriptions-item>
        <el-descriptions-item label="问题12" >{{ patientSDSData.sds_12 }}</el-descriptions-item>
        <el-descriptions-item label="问题13" >{{ patientSDSData.sds_13 }}</el-descriptions-item>
        <el-descriptions-item label="问题14" >{{ patientSDSData.sds_14 }}</el-descriptions-item>
        <el-descriptions-item label="问题15" >{{ patientSDSData.sds_15 }}</el-descriptions-item>
        <el-descriptions-item label="问题16" >{{ patientSDSData.sds_16 }}</el-descriptions-item>
        <el-descriptions-item label="问题17" >{{ patientSDSData.sds_17 }}</el-descriptions-item>
        <el-descriptions-item label="问题18" >{{ patientSDSData.sds_18 }}</el-descriptions-item>
        <el-descriptions-item label="问题19" >{{ patientSDSData.sds_19 }}</el-descriptions-item>
        <el-descriptions-item label="问题20" >{{ patientSDSData.sds_20 }}</el-descriptions-item>
        <el-descriptions-item label="总分" >{{ patientSDSData.grade }}</el-descriptions-item>
        <el-descriptions-item label="结论" :span="4" >{{ patientSDSData.result }}</el-descriptions-item>
        <el-descriptions-item label="创建时间" :span="2" >{{ patientSDSData.createTime }}</el-descriptions-item>
        <el-descriptions-item label="更新时间" :span="2" >{{ patientSDSData.updateTime }}</el-descriptions-item>
    </el-descriptions>
    <el-descriptions title="CES－D 抑郁自评量表" direction="vertical" :column="5" :data="[cesdData.value]" border>
        <el-descriptions-item label="问题1" >{{ cesdData.ces_d_1 }}</el-descriptions-item>
        <el-descriptions-item label="问题2" >{{ cesdData.ces_d_2 }}</el-descriptions-item>
        <el-descriptions-item label="问题3" >{{ cesdData.ces_d }}</el-descriptions-item>
        <el-descriptions-item label="问题4" >{{ cesdData.ces_d_4 }}</el-descriptions-item>
        <el-descriptions-item label="问题5" >{{ cesdData.ces_d_5 }}</el-descriptions-item>
        <el-descriptions-item label="问题6" >{{ cesdData.ces_d_6 }}</el-descriptions-item>
        <el-descriptions-item label="问题7" >{{ cesdData.ces_d_7 }}</el-descriptions-item>
        <el-descriptions-item label="问题8" >{{ cesdData.ces_d_8 }}</el-descriptions-item>
        <el-descriptions-item label="问题9" >{{ cesdData.ces_d_9 }}</el-descriptions-item>
        <el-descriptions-item label="问题10" >{{ cesdData.ces_d_10 }}</el-descriptions-item>
        <el-descriptions-item label="问题11" >{{ cesdData.ces_d_11 }}</el-descriptions-item>
        <el-descriptions-item label="问题12" >{{ cesdData.ces_d_12 }}</el-descriptions-item>
        <el-descriptions-item label="问题13" >{{ cesdData.ces_d_13 }}</el-descriptions-item>
        <el-descriptions-item label="问题14" >{{ cesdData.ces_d_14 }}</el-descriptions-item>
        <el-descriptions-item label="问题15" >{{ cesdData.ces_d_15 }}</el-descriptions-item>
        <el-descriptions-item label="问题16" >{{ cesdData.ces_d_16 }}</el-descriptions-item>
        <el-descriptions-item label="问题17" >{{ cesdData.ces_d_17 }}</el-descriptions-item>
        <el-descriptions-item label="问题18" >{{ cesdData.ces_d_18 }}</el-descriptions-item>
        <el-descriptions-item label="问题19" >{{ cesdData.ces_d_19 }}</el-descriptions-item>
        <el-descriptions-item label="问题20" >{{ cesdData.ces_d_20 }}</el-descriptions-item>
        <el-descriptions-item label="总分" >{{cesdData.grade }}</el-descriptions-item>
        <el-descriptions-item label="结论" :span="4" >{{ cesdData.result }}</el-descriptions-item>
        <el-descriptions-item label="创建时间" :span="2" >{{ cesdData.createTime }}</el-descriptions-item>
        <el-descriptions-item label="更新时间" :span="2" >{{ cesdData.updateTime }}</el-descriptions-item>
    </el-descriptions>
    <el-descriptions title="MADRS蒙哥马利抑郁评定量表" direction="vertical" :column="5" :data="[madrsData.value]" border>
        <el-descriptions-item label="问题1" >{{ madrsData.madrs_1 }}</el-descriptions-item>
        <el-descriptions-item label="问题2" >{{ madrsData.madrs_2 }}</el-descriptions-item>
        <el-descriptions-item label="问题3" >{{ madrsData.madrs_3 }}</el-descriptions-item>
        <el-descriptions-item label="问题4" >{{ madrsData.madrs_4 }}</el-descriptions-item>
        <el-descriptions-item label="问题5" >{{ madrsData.madrs_5 }}</el-descriptions-item>
        <el-descriptions-item label="问题6" >{{ madrsData.madrs_6 }}</el-descriptions-item>
        <el-descriptions-item label="问题7" >{{ madrsData.madrs_7 }}</el-descriptions-item>
        <el-descriptions-item label="问题8" >{{ madrsData.madrs_8 }}</el-descriptions-item>
        <el-descriptions-item label="问题9" >{{ madrsData.madrs_9 }}</el-descriptions-item>
        <el-descriptions-item label="问题10" >{{ madrsData.madrs_10 }}</el-descriptions-item>
        <el-descriptions-item label="总分" >{{ madrsData.grade }}</el-descriptions-item>
        <el-descriptions-item label="结论" :span="4" >{{ madrsData.result }}</el-descriptions-item>
        <el-descriptions-item label="创建时间" :span="2" >{{ madrsData.createTime }}</el-descriptions-item>
        <el-descriptions-item label="更新时间" :span="2" >{{ madrsData.updateTime }}</el-descriptions-item>

    </el-descriptions>
</template>    

<style lang="scss" scoped>

</style>