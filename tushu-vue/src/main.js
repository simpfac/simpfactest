//import './assets/main.css'

import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import App from './App.vue'
import axios from 'axios'

//导入路由器
import router from '@/router/index.js'
const app = createApp(App)
axios.defaults.withCredentials = true
// 创建并配置 Pinia
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)
app.use(pinia)       // 先注册 Pinia  // 先给 pinia 添加插件
app.use(ElementPlus)         // 先使用 .use() 
app.use(router)             // 然后才能使用 .use()
app.mount('#app')
