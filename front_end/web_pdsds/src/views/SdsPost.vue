<script setup>
import { ref } from 'vue'
import { User, Lock } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'

const postData = ref({
    sds_1: 1,
    sds_2: 1,
    sds_3: 1,
    sds_4: 1,
    sds_5: 1,
    sds_6: 1,
    sds_7: 1,
    sds_8: 1,
    sds_9: 1,
    sds_10: 1,
    sds_11: 4,
    sds_12: 2,
    sds_13: 1,
    sds_14: 1,
    sds_15: 1,
    sds_16: 1,
    sds_17: 1,
    sds_18: 1,
    sds_19: 1,
    sds_20: 1
});
const options1 = [
  {
    value: 1,
    label: '很少',
  },
  {
    value: 2,
    label: '有时',
  },
  {
    value: 3,
    label: '大部分时间',
  },
  {
    value: 4,
    label: '总是',
  },
]
const options2 = [
  {
    value: 4,
    label: '很少',
  },
  {
    value: 3,
    label: '有时',
  },
  {
    value: 2,
    label: '大部分时间',
  },
  {
    value: 1,
    label: '总是',
  },
]

import {patientSdsPost}from '@/api/scale.js'
import {useTokenStore} from '@/stores/token.js'

const postsds = async () => {
    const result = await patientSdsPost(postData.value);

    ElMessage.success(result.message ? result.message : '提交成功')
  };

</script>

<template>

<el-card class="card">
    <template #header>
        <div class="header">
            <span>抑郁症自评量表SDS</span>
        </div>
    </template>
    <div class="choose">
        <p>1.我觉得闷闷不乐，情绪消沉</p>
        <el-select  v-model="postData.sds_1"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options1"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>2.我觉得一天之中早晨最好。</p>
        <el-select  v-model="postData.sds_2"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options2"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>3.我一阵阵哭出来或觉得想哭。</p>
        <el-select  v-model="postData.sds_3"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options1"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>4.我晚上睡眠不好。</p>
        <el-select  v-model="postData.sds_4"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options1"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>5.我吃得跟平常一样多</p>
        <el-select  v-model="postData.sds_5"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options2"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>6.我与异性密切接触时和一样一样感到愉快 </p>
        <el-select  v-model="postData.sds_6"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options2"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>7.我觉察我的体重在下降</p>
        <el-select  v-model="postData.sds_7"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options1"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>8.我有便秘的苦恼</p>
        <el-select  v-model="postData.sds_8"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options1"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>9.我心跳比平时快</p>
        <el-select  v-model="postData.sds_9"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options1"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>10.我无缘无故的感到疲乏</p>
        <el-select  v-model="postData.sds_10"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options1"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>11.我的头脑跟平常一样清楚</p>
        <el-select  v-model="postData.sds_11"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options2"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>12.我觉得经常做的事情并没有困难</p>
        <el-select  v-model="postData.sds_12"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options2"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>13.我觉得不安而平静不下来</p>
        <el-select  v-model="postData.sds_13"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options1"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>14.我对将来抱有希望</p>
        <el-select  v-model="postData.sds_14"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options2"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>15.我比平常容易生气冲动</p>
        <el-select  v-model="postData.sds_15"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options1"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>16.我觉得做出决定是容易的</p>
        <el-select  v-model="postData.sds_16"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options2"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>17.我觉得自己是个有用对人，有人需要我</p>
        <el-select  v-model="postData.sds_17"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options2"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>18.我的生活过得很有意思  </p>
        <el-select  v-model="postData.sds_18"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options2"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>19.我认为我死了别人会生活得好些</p>
        <el-select  v-model="postData.sds_19"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options1"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>20.平常感兴趣的事，我仍然照样感兴趣</p>
        <el-select  v-model="postData.sds_20"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options2"  :key="item.value"  :label="item.label" :value="item.value"/>
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