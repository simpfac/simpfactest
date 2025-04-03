<script setup>
    import { useRouter } from 'vue-router';
    import axios from 'axios';
    import { onMounted,ref } from 'vue';
    import { useTokenStore } from '@/stores/token';
    const tokenStore = useTokenStore();
    const router = useRouter();
    const isLoggedIn = ref(false);
    // 初始化检查登录状态
    onMounted(() => {
      isLoggedIn.value = tokenStore.token !== ''
    });
    function gosearchtushu(){
        router.push('/tushu');
    }
    function returntologin(){
        isLoggedIn.value = false;
        axios.post("/api/users/loginout")
        .then(res => {
            if(res.data.code === 131){
                tokenStore.removeToken();
                router.push('/login');
            }else{
                alert(res.data.msg);
                return;
            }
        }).catch(err => {
            console.log(err);
            return;
        });
    }
    if(tokenStore.token !== ""){
        isLoggedIn.value = true;
    }
    function gomyborrow(){
        router.push('/myborrow');
    }
    function goindex(){
        router.push('/');
    }
    function gomymessage(){
        router.push('/mymessage');
    }
</script>

<template>
    <div class="library-home">
      <!-- 顶部导航栏 -->
      <header class="header">
        <div class="logo">图书借阅系统</div>
        <div class="user-info">
          <!-- 已登录状态 -->
    <template v-if="isLoggedIn">
      <span class="welcome-text">欢迎您, {{ tokenStore.token }}</span>
      <el-dropdown>
        <span class="user-avatar">
          <el-avatar :size="40" :src="userAvatar" />
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item divided @click="returntologin">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </template>

    <!-- 未登录状态 -->
    <template v-else>
      <el-button-group>
        <el-button @click="returntologin">登录</el-button>
        <el-button @click="goToRegister">注册</el-button>
      </el-button-group>
    </template>
        </div>
      </header>
  
      <!-- 主内容区 -->
      <div class="main-container">
        <!-- 左侧功能菜单 -->
        <aside class="sidebar">
          <el-menu default-active="1" class="menu">
            <el-menu-item index="1" @click="goindex">
              <el-icon><Reading /></el-icon>
              <span>图书查询</span>
              <br/>
              <br/>
              <br/>
            </el-menu-item>
            <el-menu-item index="4" @click="gomymessage">
              <el-icon><User /></el-icon>
              <span>个人信息</span>
              <br/>
              <br/>
              <br/>
            </el-menu-item>
          </el-menu>
        </aside>
  
        <!-- 右侧内容区 -->
        <main class="content">
          <div class="banner">
            <h1>欢迎使用图书借阅系统</h1>
            <p>便捷的图书查询与借阅管理平台</p>
          </div>
  
          <div class="quick-actions">
            <el-row :gutter="20">
              <el-col :span="6">
                <el-card class="action-card" shadow="hover" @click="gosearchtushu">
                  <el-icon :size="40"><Search /></el-icon>
                  <h3>图书检索</h3>
                  <p>快速查找馆藏图书</p>
                </el-card>
              </el-col>         
              <el-col :span="6">
                <el-card class="action-card" shadow="hover" @click="gomyborrow">
                  <el-icon :size="40"><Collection /></el-icon>
                  <h3>我的借阅</h3>
                  <p>查看当前借阅情况</p>
                </el-card>
              </el-col>
            </el-row>
          </div>
        </main>
      </div>
    </div>
  </template>
  
  <style scoped>
  .library-home {
    height: 100vh;
    display: flex;
    flex-direction: column;
  }
  
  .header {
    height: 60px;
    background-color: #2c3e50;
    color: white;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 20px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  }
  
  .logo {
    font-size: 20px;
    font-weight: bold;
  }
  
  .user-info {
    display: flex;
    align-items: center;
    gap: 15px;
  }
  
  .welcome-text {
    font-size: 14px;
  }
  
  .user-avatar {
    cursor: pointer;
  }
  
  .main-container {
    display: flex;
    flex: 1;
    overflow: hidden;
  }
  
  .sidebar {
    width: 200px;
    background-color: #fff;
    border-right: 1px solid #e6e6e6;
  }
  
  .menu {
    border-right: none;
  }
  
  .content {
    flex: 1;
    padding: 20px;
    overflow-y: auto;
    background-color: #f5f7fa;
  }
  
  .banner {
    background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
    color: white;
    padding: 30px;
    border-radius: 8px;
    margin-bottom: 20px;
  }
  
  .banner h1 {
    margin: 0;
    font-size: 28px;
  }
  
  .banner p {
    margin: 10px 0 0;
    opacity: 0.9;
  }
  
  .quick-actions {
    margin-top: 30px;
  }
  
  .action-card {
    text-align: center;
    padding: 20px 10px;
    cursor: pointer;
    transition: transform 0.3s;
  }
  
  .action-card:hover {
    transform: translateY(-5px);
  }
  
  .action-card h3 {
    margin: 15px 0 10px;
    font-size: 16px;
  }
  
  .action-card p {
    margin: 0;
    color: #666;
    font-size: 14px;
  }
  </style>