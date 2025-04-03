import {createRouter,createWebHistory} from "vue-router";
import { useTokenStore } from '@/stores/token';
//导入组件
import tushulist from '@/view/tushulist.vue'
import index from "@/view/index.vue";
import login from "@/view/login.vue";
import myborrow from "@/view/myborrow.vue";
import adminlogin from "@/view/adminlogin.vue";
import bookmannger from "@/view/bookmannger.vue";
import mymessage from "@/view/mymessage.vue";

//定义路由关系
const routes = [
    {path:'/tushu',component:tushulist},
    {path:'/',component:index,},
    {path:'/login',component:login},
    {path:'/myborrow',component:myborrow,meta: { requiresAuth: true }},
    {path:'/adminlogin',component:adminlogin},
    {path:'/bookmannger',component:bookmannger},
    {path:'/mymessage',component:mymessage,meta: { requiresAuth: true }},
    
]

//创建路由器
const router = createRouter({
    history:createWebHistory(),
    routes:routes
})

// 添加全局前置守卫
router.beforeEach((to, from, next) => {
    const tokenStore = useTokenStore()
    
    // 检查目标路由是否需要认证
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // 检查用户是否已登录
        if (!tokenStore.token) {
            // 未登录，重定向到登录页面，并记录目标路径
            next({
                path: '/login',
                query: { redirect: to.fullPath }
            })
        } else {
            // 已登录，继续导航
            next()
        }
    } else {
        // 不需要认证的路由，直接放行
        next()
    }
})



//导出路由器
export default router