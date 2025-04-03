import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
  //解决cros跨域问题
  server: {
    host: '0.0.0.0', // 允许所有网络访问
    port: 5173,      // 可选：指定端口
    proxy: {
      '/api': {  // 代理前缀
        target: 'http://localhost:8081', // 后端地址
        changeOrigin: true, // 修改请求头 Origin
        rewrite: (path) => path.replace(/^\/api/, ''), // 去掉 /api 前缀
        configure: (proxy, _options) => {
          proxy.on('proxyReq', (proxyReq) => {
            proxyReq.setHeader('Origin', 'http://localhost:8081'); // 修正 Origin
          });
          proxy.on('proxyRes', (proxyRes) => {
            const sc = proxyRes.headers['set-cookie'];
            if (sc) {
              // 修改 Cookie 的 Domain/Path 以适应前端
              proxyRes.headers['set-cookie'] = sc.map(cookie => 
                cookie.replace(/Domain=.*?;/, 'Domain=localhost;')
              );
            }
          });
        },
      },
    },
  },
})
