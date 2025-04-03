import { defineStore } from "pinia";
import {ref} from 'vue'
/*

*/
export const useTokenStore = defineStore('token',()=>{
    // 定义一个ref变量
    const token = ref('')
    
    //定义一个修改token的方法
    const setToken = (newToken)=>{
        token.value = newToken
    }
    //定义一个删除token的方法
    const removeToken = ()=>{
        token.value = ''
    }
    return{
        token,
        setToken,
        removeToken
    }
},{
    persist:true
});