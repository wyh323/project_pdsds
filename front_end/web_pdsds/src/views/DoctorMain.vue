<script setup>
import {useRouter} from 'vue-router'

import {ElMessage,ElMessageBox} from 'element-plus'
const router = useRouter();

const navigateTo = (path) => {
  router.push(path)
}
</script>

<template>
<el-container style="height: 100vh;">
      <!-- 侧边栏 -->
      <el-aside width="200px" style="background-color: #003366;">
        <div style="padding: 35px; color: white; font-size: 20px; margin-bottom: 20px;">
          心理健康疾病辅助诊疗系统
        </div>
        <el-menu default-active="1" class="el-menu-vertical-demo" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="1" @click="navigateTo('查看本人信息')">查看本人信息</el-menu-item>
          <el-menu-item index="2" @click="navigateTo('修改个人密码')">修改个人密码</el-menu-item>
          <el-menu-item index="3" @click="navigateTo('修改账号密码')">修改账号密码</el-menu-item>
          <el-menu-item index="/doctormain/viewpatientinfo" @click="navigateTo('/doctormain/viewpatientinfo')">查看患者信息</el-menu-item>
          <el-menu-item index="5" @click="navigateTo('修改患者信息')">修改患者信息</el-menu-item>
        </el-menu>
      </el-aside>
  
      <!-- 主体内容 -->
      <el-container>
        <!-- 头部 -->
        <el-header style="background-color: rgb(173, 173, 173); text-align: right; padding-right: 20px; line-height: 60px;">
          <span style="color: black; margin-left: 20px; cursor: pointer;" @click="showLoginInfo">显示医生登录信息</span>
        </el-header>
        
        <!-- 内容区域 -->
        <el-main>
        <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
</template>

<style lang="scss" scoped>
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