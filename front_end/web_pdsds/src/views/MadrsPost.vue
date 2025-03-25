<script setup>
import { ref } from 'vue'
import { User, Lock } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'

const postData = ref({
    madrs_1: 1,
    madrs_2: 1,
    madrs_3: 1,
    madrs_4: 1,
    madrs_5: 1,
    madrs_6: 1,
    madrs_7: 1,
    madrs_8: 1,
    madrs_9: 1,
    madrs_10: 1
});
const options = [
  {
    value: 0,
    label: '分数0',
  },
  {
    value: 1,
    label: '分数1',
  },
  {
    value: 2,
    label: '分数2',
  },
  {
    value: 3,
    label: '分数3',
  },
  {
    value: 4,
    label: '分数4',
  },
  {
    value: 5,
    label: '分数5',
  },
  {
    value: 6,
    label: '分数6',
  },
]

import {patientMadrsPost}from '@/api/scale.js'
import {useTokenStore} from '@/stores/token.js'

const postsds = async () => {
    const result = await patientMadrsPost(postData.value);

    ElMessage.success(result.message ? result.message : '提交成功')
  };

</script>

<template>

<el-card class="card">
    <template #header>
        <div class="header">
            <span>MADRS蒙哥马利抑郁评定量表</span>
        </div>
    </template>
    <div class="choose">
        <p>1.观察到的抑郁（结合您最近一周内的情绪（包括今天）作出符合自己情况的选择）</p>
        <p>选项:无,分数:0 |选项:介于上下两种选项之间；,分数:1 |选项:看起来是悲伤的，但能使之高兴一些；,分数:2 |选项:介于上下两种选项之间,分数:3 |选项:突出的悲伤忧郁，但其情绪仍可受外界环境影响；,分数:4 |选项:介于上下两种选项之间,分数:5 |选项:整天抑郁，极度严重。,分数:6 |
</p>
        <el-select  v-model="postData.madrs_1"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>2.抑郁主诉（结合您最近一周内的情绪（包括今天）作出符合自己情况的选择） </p>
        <p>选项:在日常心境中偶有抑郁；,分数:0 | 选项:介于上下两种选项之间；,分数:1 | 选项:有抑郁或情绪低沉，但可使之愉快些；,分数:2 | 选项:介于上下两种选项之间；,分数:3 | 选项:沉湎于抑郁沮丧心境,但环境仍可对心境有些影响；,分数:4 | 选项:介于上下两种选项之间；,分数:5 | 选项:持久不断的深度抑郁沮丧。,分数:6 |</p>
        <el-select  v-model="postData.madrs_2"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>3.内心紧张（结合您最近一周内的情绪（包括今天）作出符合自己情况的选择）</p>
        <p>选项:平静，偶有瞬间的紧张；,分数:0 | 选项:介于上下两种选项之间；,分数:1 | 选项:偶有紧张不安及难以言明的不舒服感；,分数:2 | 选项:介于上下两种选项之间；,分数:3 | 选项:持久的内心紧张，或间歇呈现的恐惧状态，要花费 相当努力方能克制,分数:4 | 选项:介于上下两种选项之间；,分数:5 | 选项:持续的恐惧和苦恼，极度惊恐。,分数:6 |</p>
        <el-select  v-model="postData.madrs_3"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>4.睡眠减少（结合您最近一周内的情绪（包括今天）作出符合自己情况的选择）</p>
        <p>选项:睡眠如常；,分数:0 | 选项:介于上下两种选项之间；,分数:1 | 选项:轻度入睡困难，或睡眠较浅，或时睡时醒；,分数:2 | 选项:介于上下两种选项之间；,分数:3 | 选项:睡眠减少或睡眠中断2小时以上；,分数:4 | 选项:介于上下两种选项之间；,分数:5 | 选项:每天睡眠总时间不超过2－3小时。,分数:6 |</p>
        <el-select  v-model="postData.madrs_4"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>5.食欲减退（结合您最近一周内的情绪（包括今天）作出符合自己情况的选择）</p>
        <p>选项:食欲正常或增进；,分数:0 | 选项:介于上下两种选项之间；,分数:1 | 选项:轻度食欲减退；,分数:2 | 选项:介于上下两种选项之间；,分数:3 | 选项:没有食欲，食而无味；,分数:4 | 选项:介于上下两种选项之间；,分数:5 | 选项:不愿进食，需他人帮助。,分数:6 |</p>
        <el-select  v-model="postData.madrs_5"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>6.注意集中困难（结合您最近一周内的情绪（包括今天）作出符合自己情况的选择） </p>
        <p>选项:无；,分数:0 | 选项:介于上下两种选项之间；,分数:1 | 选项:偶有思想集中困难；,分数:2 | 选项:介于上下两种选项之间；,分数:3 | 选项:思想难以集中，以致干扰阅读或交谈；,分数:4 | 选项:介于上下两种选项之间；,分数:5 | 选项:完全不能集中思想，无法阅读。,分数:6 |</p>
        <el-select  v-model="postData.madrs_6"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>7.懒散（结合您最近一周内的情绪（包括今天）作出符合自己情况的选择）</p>
        <p>选项:活动发动并无困难，动作不慢；,分数:0 | 选项:介于上下两种选项之间；,分数:1 | 选项:有始动困难；,分数:2 | 选项:介于上下两种选项之间；,分数:3 | 选项:即使简单的日常活动也难以发动，需花很大努力；,分数:4 | 选项:介于上下两种选项之间；,分数:5 | 选项:完全呈懒散状态，无人帮助什么也干不了。,分数:6 |</p>
        <el-select  v-model="postData.madrs_7"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>8.感受不能（结合您最近一周内的情绪（包括今天）作出符合自己情况的选择）</p>
        <p>选项:对周围的人和物的兴趣正常；,分数:0 | 选项:介于上下两种选项之间；,分数:1 | 选项:对日常趣事的享受减退；,分数:2 | 选项:介于上下两种选项之间；,分数:3 | 选项:对周围不感兴趣，对朋友和熟人缺乏感情；,分数:4 | 选项:介于上下两种选项之间；,分数:5 | 选项:呈情感麻木状态，不能体验愤怒、悲痛和愉快，对亲友全无感情。,分数:6 |</p>
        <el-select  v-model="postData.madrs_8"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>9.悲观思想（结合您最近一周内的情绪（包括今天）作出符合自己情况的选择）</p>
        <p>选项:无；,分数:0 | 选项:介于上下两种选项之间；,分数:1 | 选项:时有时无的失败，自责和自卑感；,分数:2 | 选项:介于上下两种选项之间；,分数:3 | 选项:持久的自责或肯定的但尚近情理的自罪，对前途悲观；,分数:4 | 选项:介于上下两种选项之间；,分数:5 | 选项:自我毁灭、自我悔恨或感罪恶深重的妄想，荒谬绝伦、难以动摇的自我谴责 。,分数:6 |</p>
        <el-select  v-model="postData.madrs_9"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>10.我无缘无故的感到疲乏</p>
        <p>选项:无；,分数:0 | 选项:介于上下两种选项之间；,分数:1 | 选项:对生活厌倦，偶有瞬间即逝的自杀念头；,分数:2 | 选项:介于上下两种选项之间；,分数:3 | 选项:感到不如死了的好，常有自杀念头，认为自杀是一 种可能的自我解决的方法，但尚无切实的自杀计划；,分数:4 | 选项:介于上下两种选项之间；,分数:5 | 选项:已拟适合时机的自杀计划，并积极准备,分数:6 |</p>
        <el-select  v-model="postData.madrs_10"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <template #footer>
        <div class="footer">
        <el-button type="primary" @click="postsds">提交</el-button>
      </div>
    </template>
  </el-card>
</template>

<style lang="scss" scoped>
.card{
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
    .footer {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }
}
</style>