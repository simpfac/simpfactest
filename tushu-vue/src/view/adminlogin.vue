<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import axios from 'axios';
const router = useRouter();
const loginmessage = ref({
    username: "",
    userpassword: ""
})
function handleLogin(){
    axios.post("/api/users/adminlogin", loginmessage.value)
    .then(res => {
        if(res.data.code === 101){
            alert(res.data.msg);
            router.push('/bookmannger');
        }else{
            alert(res.data.msg);
            return;
        }
    }).catch(err => {
        console.log(err);
        return;
    });
}
function returntonormal(){
    router.push('/login');
}
</script>

<template>
    <div class="login-container">
      <div class="login-box">
        <!-- 顶部标题（动态显示） -->
        <div class="login-header">
          <h2>图书借阅系统</h2>
          <p>请登录管理员账号</p>
        </div>
  
        <!-- 登录表单 -->
        <el-form 
          class="login-form" 
          label-position="top"
        >
          <el-form-item label="用户名">
            <el-input
              v-model="loginmessage.username" 
              placeholder="请输入用户名"
              prefix-icon="User"
            />
          </el-form-item>
  
          <el-form-item label="密码">
            <el-input
              v-model="loginmessage.userpassword"
              type="password"
              placeholder="请输入密码"
              prefix-icon="Lock"
              show-password
            />
          </el-form-item>
  
          <div class="button-group">
          <el-button 
            type="primary" 
            class="login-btn"
            @click="handleLogin"
          >
            登录
          </el-button>
          <br />
          <el-button
            class="normal-login-btn"
            @click="returntonormal"
          >
            返回普通用户登录
          </el-button>
          </div>
        </el-form> 
      </div>
    </div>
</template>

<style>
.login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background-color: #f5f7fa;
    background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
  }
  
  .login-box {
    width: 400px;
    padding: 40px;
    background: white;
    border-radius: 8px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
  }
  
  .login-header {
    text-align: center;
    margin-bottom: 30px;
  }
  
  .login-header h2 {
    margin: 0;
    color: #333;
    font-size: 24px;
  }
  
  .login-header p {
    margin: 10px 0 0;
    color: #666;
    font-size: 14px;
  }
  
  .login-form {
    margin-top: 20px;
  }
  
  .remember-forgot {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
  }
  
  .login-btn {
    width: 100%;
    height: 42px;
    font-size: 16px;
  }
  
  .social-login {
    margin: 30px 0;
    text-align: center;
  }
  
  .divider {
    position: relative;
    color: #999;
    font-size: 12px;
  }
  
  .divider::before,
  .divider::after {
    content: "";
    position: absolute;
    top: 50%;
    width: 30%;
    height: 1px;
    background: #eee;
  }
  
  .divider::before {
    left: 0;
  }
  
  .divider::after {
    right: 0;
  }
  
  .social-icons {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin-top: 20px;
  }
  
  .register-tip {
    text-align: center;
    font-size: 14px;
    color: #666;
  }

  .button-group {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.normal-login-btn {
  width: 100%;
  margin-top: 10px;
}
</style>