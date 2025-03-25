<script setup>
import { ref } from 'vue'
import { User, Lock } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import {useRouter} from 'vue-router'
import {useTokenStore} from '@/stores/token.js'
const tokenStore = useTokenStore();
const router = useRouter()
const isRegister = ref(false)
const selectedType = ref('')
const selectedType2 = ref('')
const options = [
  {
    value:'doctor',
    label:'医生'
  },
  {
    value:'patient',
    label:'病人'
  }]
const options2 = [
  {
    value:'doctor',
    label:'医生'
  },
  {
    value:'patient',
    label:'病人'
  },
  {
    value:'adm',
    label:'管理'
  }]
const registerData = ref({
    username: '',
    password: ''
})
const checkRePassword = (rule, value, callback) => {
    const passwordStrengthRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{5,16}$/;
    if (!passwordStrengthRegex.test(value)) {
        callback(new Error('密码必须包含大写字母、小写字母、数字，并且长度在5到16位之间'));
        } else {
        callback()
    }
}


const clearRegisterData = ()=>{
    registerData.value={
        username:'',
        password:''
    }
}
const rules = {
    username: [
        { required: true, message: '请输入用户名', trigger: 'blur' },
        { min: 5, max: 16, message: '长度为5~16位非空字符', trigger: 'blur' }
    ],
    password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { validator: checkRePassword, trigger: 'blur' }
    ],
}
import { userLoginPatient, userRegisterPatient, userLoginDoctor, userRegisterDoctor, userLoginAdm} from '@/api/user.js'
const register = async () => {
    let result;
    if (selectedType.value === 'doctor') {
      result = await userRegisterDoctor(registerData.value);
      
    } else if (selectedType.value === 'patient') {
      result = await userRegisterPatient(registerData.value);
    } else {
      ElMessage.error('请选择有效的身份类型进行注册');
      return;
    }
    ElMessage.success(result.message ? result.message : '注册成功')
  };

const login = async () => {
    let result;
    if (selectedType2.value === 'doctor') {
      result = await userLoginDoctor(registerData.value);
      
    } else if (selectedType2.value === 'patient') {
      result = await userLoginPatient(registerData.value);
    } else if (selectedType2.value === 'adm') {
      result = await userLoginAdm(registerData.value);
      
    }else {
      ElMessage.error('请选择有效的身份类型进行注册');
      return;
    }
    ElMessage.success(result.data.message ||'登录成功')
    tokenStore.setToken(result.data)
    router.push('/')
    if (selectedType2.value === 'doctor') {
      router.push('/doctormain');
    } else if (selectedType2.value === 'patient') {
      router.push('/patientmain');
    } else if (selectedType2.value === 'adm') {
      router.push('/admmain');
      
    }
  };
</script>

<template>
  <el-row class="main-page">
    <el-col :span="7" class="left"></el-col>
    <el-col :span="10" class="form">
        <!-- 注册表单 -->
        <el-form ref="form" size="large" autocomplete="off" v-if="isRegister" :model="registerData" :rules="rules">
            <el-form-item>
                <h1>注册</h1>
            </el-form-item>
            <el-form-item prop="username">
                <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码"
                    v-model="registerData.password"></el-input>             
            </el-form-item>
            <!-- 注册按钮 -->
            <el-form-item>
                <el-button class="button" type="primary" auto-insert-space @click="register">
                    注册
                </el-button>
            </el-form-item>
            <!-- 身份选择按钮 -->
            <el-form-item>
                <el-select v-model="selectedType" placeholder="Selet" size="large" style="width: 240px">
                      <el-option v-for="item in options":key="item.value":label="item.label":value="item.value"/>
                </el-select>
            </el-form-item>
            <el-form-item class="flex">
               <el-link type="info" :underline="false" @click="isRegister = false;clearRegisterData()">
                    ← 返回
                </el-link>
            </el-form-item>
        </el-form>
        <el-form ref="form" size="large" autocomplete="off" v-else :model="registerData" :rules="rules">
            <el-form-item>
                <h1>登录</h1>
            </el-form-item>
            <el-form-item prop="username">
                <el-input :prefix-icon="User" placeholder="请输入用户名" v-model="registerData.username"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input name="password" :prefix-icon="Lock" type="password" placeholder="请输入密码" v-model="registerData.password"></el-input>
            </el-form-item>
            <el-form-item class="flex">
                <div class="flex">
                   <el-checkbox>记住我</el-checkbox>
                    <el-link type="primary" :underline="false">忘记密码？</el-link>
                </div>
            </el-form-item>
            <!-- 登录按钮 -->
            <el-form-item>
                <el-button class="button" type="primary" auto-insert-space @click="login">登录</el-button>
            </el-form-item>
            <el-form-item>
                <el-select v-model="selectedType2" placeholder="Selet" size="large" style="width: 240px">
                      <el-option v-for="item in options2":key="item.value":label="item.label":value="item.value"/>
                </el-select>
            </el-form-item>
            <el-form-item class="flex">
                <el-link type="info" :underline="false" @click="isRegister = true;clearRegisterData()">
                    注册 →
                </el-link>
            </el-form-item>
        </el-form>
    </el-col>
    <el-col :span="7" class="right"></el-col>
  </el-row>

</template>

<style lang="scss" scoped>
.main-page {
    height: 100vh;
    background-color:#00ccff;
    .left{
        background: url('@/assets/login_bg1.jpg') no-repeat center / cover;
        border-radius: 0 20px 20px 0;
    }
    .form{
        display: flex;
        flex-direction: column;
        justify-content: center;
        user-select: none;
        .button {
        width: 100%;
        }

        
    }
    .right{
        background: url('@/assets/login_bg2.jpg') no-repeat center / cover;
        border-radius: 0 20px 20px 0;
    }
}
</style>
