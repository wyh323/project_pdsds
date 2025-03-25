import axios from 'axios';
import { ElMessage } from 'element-plus';
 
const baseURL = '/api';
const instance = axios.create({ baseURL });

import {useTokenStore} from '@/stores/token.js'

instance.interceptors.request.use(
    (config)=>{
        const tokenStore = useTokenStore();
        //判断有没有token
        if(tokenStore.token){
            config.headers.Authorization = tokenStore.token
        }
        return config;
    },
    (err)=>{
        Promise.reject(err)
    }
)
 
instance.interceptors.response.use(
    response => {
        const { code, message, data } = response.data || {};
 
        if (code === 0) {
            return response.data; 
        }
        if (code === 1){
        ElMessage.error(message || '服务异常');
        return Promise.reject(new Error(message || '服务异常')); }
    },
    error => {
        ElMessage.error('请求发生错误，请稍后再试');
        return Promise.reject(error); 
    }
);
 
export default instance;