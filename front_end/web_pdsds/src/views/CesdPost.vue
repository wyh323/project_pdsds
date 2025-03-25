<script setup>
import { ref } from 'vue'
import { User, Lock } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'

const postData = ref({
    ces_d_1: 1,
    ces_d_2: 1,
    ces_d_3: 1,
    ces_d_4: 1,
    ces_d_5: 1,
    ces_d_6: 1,
    ces_d_7: 1,
    ces_d_8: 1,
    ces_d_9: 1,
    ces_d_10: 1,
    ces_d_11: 1,
    ces_d_12: 1,
    ces_d_13: 1,
    ces_d_14: 1,
    ces_d_15: 1,
    ces_d_16: 1,
    ces_d_17: 1,
    ces_d_18: 1,
    ces_d_19: 1,
    ces_d_20: 1
});
const options = [
  {
    value: 0,
    label: '少于一天',
  },
  {
    value: 1,
    label: '一至两天',
  },
  {
    value: 2,
    label: '三四天',
  },
  {
    value: 3,
    label: '五至七天',
  },
]


import {patientCesdPost}from '@/api/scale.js'
import {useTokenStore} from '@/stores/token.js'

const postsds = async () => {
    const result = await patientCesdPost(postData.value);

    ElMessage.success(result.message ? result.message : '提交成功')
  };

</script>

<template>

<el-card class="card">
    <template #header>
        <div class="header">
            <span>CES－D 抑郁自评量表</span>
        </div>
    </template>
    <div class="choose">
        <p>1.我因一些小事而烦恼；</p>
        <el-select  v-model="postData.ces_d_1"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>2.我不想吃东西，我胃口不好；</p>
        <el-select  v-model="postData.ces_d_2"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>3.即使家人和朋友帮助我，我仍然无法摆脱心中苦闷；</p>
        <el-select  v-model="postData.ces_d_3"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>4.我觉得不如多数人好；</p>
        <el-select  v-model="postData.ces_d_4"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>5.我在做事时无法集中注意力；</p>
        <el-select  v-model="postData.ces_d_5"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>6.我感到情绪低落； </p>
        <el-select  v-model="postData.ces_d_6"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>7.我感到做任何事都很费力；</p>
        <el-select  v-model="postData.ces_d_7"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>8.我感到前途没有希望；</p>
        <el-select  v-model="postData.ces_d_8"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>9.我觉得我的生活是失败的；</p>
        <el-select  v-model="postData.ces_d_9"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>10.我感到害怕；</p>
        <el-select  v-model="postData.ces_d_10"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>11.我的睡眠不好；</p>
        <el-select  v-model="postData.ces_d_11"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>12.我感到不高兴；</p>
        <el-select  v-model="postData.ces_d_12"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>13.我比平时说话要少；</p>
        <el-select  v-model="postData.ces_d_13"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>14.我感到孤单；</p>
        <el-select  v-model="postData.ces_d_14"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>15.我觉得人们对我不太友好；</p>
        <el-select  v-model="postData.ces_d_15"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>16.我觉得生活没有意思；</p>
        <el-select  v-model="postData.ces_d_16"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>17.我曾哭泣；</p>
        <el-select  v-model="postData.ces_d_17"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>18.我感到忧愁；</p>
        <el-select  v-model="postData.ces_d_18"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>19.我感到人们不喜欢我；</p>
        <el-select  v-model="postData.ces_d_19"  placeholder="Select"  style="width: 240px">
            <el-option   v-for="item in options"  :key="item.value"  :label="item.label" :value="item.value"/>
        </el-select>
    </div>
    <div class="choose">
        <p>20.我觉得我无法继续我的生活。</p>
        <el-select  v-model="postData.ces_d_20"  placeholder="Select"  style="width: 240px">
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