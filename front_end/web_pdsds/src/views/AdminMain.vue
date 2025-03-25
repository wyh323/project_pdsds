<template>  
  <el-container style="height: 100vh;">  
    <el-aside width="200px" style="background-color: #003366;">  
      <div style="padding: 35px; color: white; font-size: 20px; margin-bottom: 20px;">  
        心理健康疾病辅助诊疗系统  
      </div>  
      <el-menu :default-active="$route.path" router class="el-menu-vertical-demo" text-color="#fff" active-text-color="#ffd04b">   
        <el-menu-item index="/adminmain/admindoctoroptions" @click="navigateTo('/adminmain/admindoctoroptions')">  
          管理医生信息  
        </el-menu-item>  
        <el-menu-item index="/adminmain/adminpatientoptions" @click="navigateTo('/adminmain/adminpatientoptions')">  
          查看病人信息  
        </el-menu-item>  
      </el-menu>  
    </el-aside>  

    <el-container>  
      <el-header style="background-color: rgb(173, 173, 173); text-align: right; padding-right: 20px; line-height: 60px;">  
        <span style="color: black; margin-left: 20px; cursor: pointer;" @click="showLoginInfo">显示管理员登录信息</span>  
      </el-header>  
      
      <el-main>  
        <router-view></router-view> 
      </el-main>  
    </el-container>  
  </el-container>  
</template>  

<script setup>  
import { useRouter } from 'vue-router';  

const router = useRouter();  

const navigateTo = (path) => {  
  router.push(path);   
};  

const showLoginInfo = () => {  
  alert('显示登录信息');  
};  
</script>  

<style>  
.el-header {  
  background-color: #8585857a;  
  color: rgb(173, 173, 173);  
  text-align: right;  
  line-height: 60px;  
}  

.el-aside {  
  color: white;  
}  

.el-menu-vertical-demo:not(.el-menu--collapse) {  
  width: 200px;  
}  

.el-menu-item {  
  background-color: #003366;   
  color: white;   
}  

.el-menu-item.is-active {  
  background-color: #ffd04b;   
  color: #003366;   
}  

 
</style>