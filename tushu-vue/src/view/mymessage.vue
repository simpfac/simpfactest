<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRouter } from 'vue-router'
const router = useRouter()
// 用户数据
const userInfo = ref([])

// 加载状态
const loading = ref(true)

// 获取用户信息
const fetchUserInfo = async () => {
  try {
    loading.value = true
    const response = await axios.get('/api/users/mymessage')
    userInfo.value = response.data.data
  } catch (error) {
    ElMessage.error('获取用户信息失败')
    console.error(error)
  } finally {
    loading.value = false
  }
}

// 初始化加载数据
onMounted(() => {
  fetchUserInfo()
})

function returnindex(){
  router.push('/');
}
</script>

<template>
  <div class="user-profile-container">
    <!-- 加载状态 -->
    <div v-if="loading" class="loading">
      <el-icon class="is-loading">
        <Loading />
      </el-icon>
      加载中...
    </div>

    <!-- 用户信息展示 -->
    <div v-else class="user-info">
      <div>
        <button v-on:click="returnindex">返回首页</button>
      </div>
      <h2>用户信息</h2>
      
      <div class="info-item">
        <span class="label">用户名：</span>
        <span class="value">{{ userInfo.username }}</span>
      </div>
      
      <div class="info-item">
        <span class="label">爱好：</span>
        <span class="value">{{ userInfo.hobby || '暂无' }}</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.user-profile-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
}

.loading {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  color: var(--el-color-primary);
}

.user-info {
  background: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.user-info h2 {
  margin-top: 0;
  margin-bottom: 20px;
  color: var(--el-text-color-primary);
}

.info-item {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
  padding: 10px;
  border-bottom: 1px solid var(--el-border-color-light);
}

.label {
  font-weight: bold;
  min-width: 80px;
}

.value {
  flex: 1;
  color: var(--el-text-color-regular);
}

.edit-btn {
  margin-left: 15px;
}
</style>