<script setup>
import { useRouter } from 'vue-router';
import axios from 'axios';
import { onMounted,ref } from 'vue';
    const books = ref([]);
    const router = useRouter();
    const search = ref({
        bookname: "",
        bookauthor: "",
        type: ""
    })
    onMounted(() => {
      getallbooks();
    });
    function getallbooks(){
        axios.get("/api/userbooks/getall")
        .then(res => {
            books.value = res.data.data;
        }).catch(err => {
            console.log(err);
        });
    }
    function returnindex(){
        router.push('/');
    }
    function searchBook() {
        //去除空的条件
        const query = {};
        Object.entries(search.value).forEach(([key, value]) => {
        if (value.trim() !== "") {
            query[key] = value.trim();
        }
        });
        //传入筛选空值后的新列表
        axios.post("/api/userbooks/getbyall", query)
        .then(res => {
            books.value = res.data.data;
        }).catch(err => {
            console.log(err);
        });
    }
    function returnBook(book){
        const returnmessage = {
            bookname: book.bookname,
            bookauthor: book.bookauthor,
            type: book.type,
            returnnumber: 1
        }
        axios.post("/api/userbooks/return", returnmessage)
        .then(res => {
            if(res.data.code === 101){
                alert(res.data.msg);
                getallbooks();
                return;
            }else{
                alert(res.data.msg);
                getallbooks();
                return;
            }
        }).catch(err => {
            console.log(err);
            return;
        });
    }

</script>


<template>
<div>
      <button v-on:click="returnindex">返回首页</button>
    </div>
    <div>
        图书名称<input type="text" v-model="search.bookname"/>
        图书作者<input type="text" v-model="search.bookauthor"/>
        图书类型<input type="text" v-model="search.type"/>
        <button v-on:click="searchBook">搜索</button>
    </div>
    <div>
    <table>
      <thead>
        <tr>
          <th>书名</th>
          <th>作者</th>
          <th>库存</th>
          <th>描述</th>
          <th>类型</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="book in books" :key="book.bookname">
          <td>{{ book.bookname }}</td>
          <td>{{ book.bookauthor }}</td>
          <td>{{ book.number }}</td>
          <td>{{ book.description }}</td>
          <td>{{ book.type }}</td>
          <td>
            <button v-on:click="returnBook(book)">还书</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
/* 基础全局样式 */
body {
  font-family: Arial, sans-serif;
  margin: 20px;
  background: #f5f5f5;
  color: #333;
}

/* 搜索区域 */
div > div {
  margin: 20px 0;
  display: flex;
  gap: 15px;
}

input[type="text"] {
  flex: 1;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  transition: border-color 0.3s;
}

input[type="text"]:focus {
  border-color: #007bff;
  outline: none;
}

button {
  padding: 10px 20px;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s;
}

button:hover {
  opacity: 0.9;
}

/* 表格样式 */
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  padding: 12px;
  border-bottom: 1px solid #ddd;
  text-align: left;
}

th {
  background-color: #f8f9fa;
}

tr:hover {
  background: #f1f1f1;
}

/* 响应式 */
@media (max-width: 768px) {
  div > div {
    flex-direction: column;
  }
  
  input[type="text"] {
    margin-bottom: 10px;
  }
}
</style>