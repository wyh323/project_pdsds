<script setup>
import { ref } from 'vue'
import { User, Lock } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import { patientUpdateService } from '@/api/user.js'
import {useTokenStore} from '@/stores/token.js'


const postupdate = async () => {
    const result = await patientUpdateService(postData.value);

    ElMessage.success(result.message ? result.message : '提交成功')
  };

const postData = ref({
     nickname: "",
      email: "",
});
</script>

<template>
<a>信息修改</a>
<p>昵称</p>
<el-input v-model="postData.nickname" style="width: 240px" placeholder="Please input" clearable  />
<p>邮箱</p>
<el-input v-model="postData.email" style="width: 240px" placeholder="Please input" clearable  />
<el-button type="primary" @click="postupdate">Primary</el-button>
</template>

<style lang="scss" scoped>

</style>