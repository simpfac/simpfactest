<script setup>
import { useRouter } from 'vue-router';
import axios from 'axios';
import { ref } from 'vue';
import { useTokenStore } from '@/stores/token';
const tokenStore = useTokenStore();
const loginmessage = ref({
    username: "",
    userpassword: ""
})
const registermessage = ref({
  username: '',
  userpassword: '',
  sex:'',
  hobby:'',
  confirmPassword: ''
})
const hobbyOptions = ref([
  { value: '小说', label: '小说' },
  { value: '教材', label: '教材' },
  { value: '网文', label: '网文' },
  { value: '科技', label: '科技' },
  { value: '游戏', label: '游戏' },
  { value: '漫画', label: '漫画' }
])
const router = useRouter();
const isregister = ref(false);
function handleLogin(){
    axios.post("/api/users/login", loginmessage.value)
    .then(res => {
        if(res.data.code === 101){
            tokenStore.setToken(loginmessage.value.username);
            alert(res.data.msg);
            router.push('/');
        }else{
            alert(res.data.msg);
            return;
        }
    }).catch(err => {
        console.log(err);
        return;
    });
}

function handleregister(){
    if(registermessage.value.userpassword !== registermessage.value.confirmPassword){
        alert("两次输入的密码不一致，请重新输入");
        return;
    }
    axios.post("/api/users/register", registermessage.value)
    .then(res => {
        if(res.data.code === 101){
            alert(res.data.msg);
            // 注册成功后清空表单
            registermessage.value = {
              username: '',
              userpassword: '',
              sex: '',
              hobby: '',
              confirmPassword: ''
            }
            isregister.value = false;
            return;
        }else{
            alert(res.data.msg);
            return;
        }
    }).catch(err => {
        console.log(err);
        return;
    });
}
function goadminlogin(){
    router.push('/adminlogin');
}
</script>

<template>
  <div class="login-container">
    <div class="login-box">
      <!-- 顶部标题（动态显示） -->
      <div class="login-header">
        <h2>图书借阅系统</h2>
        <p>{{ isregister ? '注册新账号' : '欢迎回来，请登录您的账号' }}</p>
      </div>

      <!-- 登录表单（v-if控制显示） -->
      <el-form 
        v-if="!isregister"
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

        <el-button 
          type="primary" 
          class="login-btn"
          @click="handleLogin"
        >
          登录
        </el-button>
        <br />
        <br />
        <el-button 
          type="primary" 
          class="login-btn"
          @click="goadminlogin"
        >
          管理员登录
        </el-button>
      </el-form>

      <!-- 注册表单（v-if控制显示） -->
      <el-form
        v-else
        class="login-form"
        label-position="top"
      >
        <el-form-item label="用户名">
          <el-input
            v-model="registermessage.username"
            placeholder="请输入用户名"
            prefix-icon="User"
          />
        </el-form-item>

        <el-form-item label="密码">
          <el-input
            v-model="registermessage.userpassword"
            type="password"
            placeholder="请输入密码"
            prefix-icon="Lock"
            show-password
          />
        </el-form-item>

        <el-form-item label="确认密码">
          <el-input
            v-model="registermessage.confirmPassword"
            type="password"
            placeholder="请再次输入密码"
            prefix-icon="Lock"
            show-password
          />
        </el-form-item>
        
        <!-- 添加性别选择 -->
        <el-form-item label="性别">
          <el-radio-group v-model="registermessage.sex">
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>

        <!-- 添加爱好多选 -->
        <el-form-item label="爱好">
          <el-select 
            v-model="registermessage.hobby" 
            placeholder="请选择爱好"
            style="width: 100%"
          >
          <el-option
            v-for="hobby in hobbyOptions"
            :key="hobby.value"
            :label="hobby.label"
            :value="hobby.value"
          />
          </el-select>
        </el-form-item>

        <el-button 
          type="primary" 
          class="login-btn"
          @click="handleregister"
        >
          注册
        </el-button>
      </el-form>

      <!-- 切换提示（动态显示文字） -->
      <div class="register-tip">
        {{ isregister ? '已有账号？' : '还没有账号？' }}
        <el-link 
          type="primary" 
          @click="isregister = !isregister"
        >
          {{ isregister ? '立即登录' : '立即注册' }}
        </el-link>
      </div>
    </div>
  </div>
</template>
  
  <style scoped>
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
  </style>