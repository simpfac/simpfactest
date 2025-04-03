<script setup>
import axios from 'axios';
import { onMounted,ref } from 'vue';
import { useRouter } from 'vue-router';
import { ElDialog, ElMessage,ElMessageBox } from 'element-plus';
    const books = ref([]);
    const router = useRouter();
    const search = ref({
        bookname: "",
        bookauthor: "",
        type: ""
    })
    const dialogVisible = ref(false); // 控制对话框显示
    const newBook = ref({ // 新增图书表单数据
        bookname: '',
        bookauthor: '',
        number: 0,
        description: '',
        type: ''
    });
    const typeOptions = ref([
        { value: '小说', label: '小说' },
        { value: '教材', label: '教材' },
        { value: '网文', label: '网文' },
        { value: '科技', label: '科技' },
        { value: '游戏', label: '游戏' },
        { value: '漫画', label: '漫画' }
    ])
    const editDialogVisible = ref(false)
    const editBook = ref({
        bookid: '',
        bookname: '',
        bookauthor: '',
        number: 0,
        description: '',
        type: ''
    })
    onMounted(() => {
      getallbooks();
    });
    function getallbooks(){
        axios.get("/api/books/getall")
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
        axios.post("/api/books/getbyall", query)
        .then(res => {
            books.value = res.data.data;
        }).catch(err => {
            console.log(err);
        });
    }
    function addBook(){
        dialogVisible.value = true; // 打开对话框
    }

    function submitNewBook(){
        if (!newBook.value.bookname || !newBook.value.bookauthor || !newBook.value.type || newBook.value.number==0 || !newBook.value.description) {
            console.log(newBook.value);
            ElMessage.warning('请填写所有信息');
            return;
        }
        axios.post("/api/books/save", newBook.value)
        .then(res => {
            ElMessage.success('添加成功');
            dialogVisible.value = false;
            getallbooks(); // 刷新列表
            resetNewBookForm(); // 重置表单
        }).catch(err => {
            ElMessage.error('添加失败');
            console.error(err);
        });
    }

    function submitEditBook() {
        if (!editBook.value.bookname || !editBook.value.bookauthor || !editBook.value.type || editBook.value.number==0 || !editBook.value.description) {
            ElMessage.warning('请填写所有信息')
            return
        }
  
        axios.post("/api/books/update", editBook.value)
            .then(res => {
            ElMessage.success('修改成功')
            editDialogVisible.value = false
            getallbooks() // 刷新列表
            }).catch(err => {
            ElMessage.error('修改失败')
            console.error(err)
        })
    }
    //重置表单
    function resetNewBookForm() {
        newBook.value = {
        bookname: '',
        bookauthor: '',
        number: 0,
        description: '',
        type: ''
        };
    }

    function updateBook(book){
        editBook.value = {
        bookid: book.bookid,
        bookname: book.bookname,
        bookauthor: book.bookauthor,
        number: book.number,
        description: book.description,
        type: book.type
        }
        editDialogVisible.value = true
    }

    function deleteBook(book) {
        ElMessageBox.confirm(
        `确定要删除《${book.bookname}》吗？`,
        '删除确认',
        {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true // 居中显示
        }
        ).then(() => {
        // 用户点击确定后执行删除
        axios.post("/api/books/delete",book)
            .then(res => {
            ElMessage.success('删除成功')
            getallbooks() // 刷新列表
        }).catch(err => {
            ElMessage.error('删除失败')
            console.error(err)
        })
        }).catch(() => {
        // 用户点击取消
        ElMessage.info('已取消删除')
        })
    }
    
</script>

<template>
    <div>
      <button v-on:click="returnindex">返回首页</button>
      <button v-on:click="addBook">新增图书</button>
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
            <button @click="updateBook(book)">修改</button>
            <span> | </span>
            <button @click="deleteBook(book)">删除</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  <!-- 新增图书对话框 -->
  <el-dialog v-model="dialogVisible" title="新增图书" width="50%">
    <el-form :model="newBook" label-width="80px">
      <el-form-item label="书名" required>
        <el-input v-model="newBook.bookname" placeholder="请输入书名" />
      </el-form-item>
      <el-form-item label="作者" required>
        <el-input v-model="newBook.bookauthor" placeholder="请输入作者" />
      </el-form-item>
      <el-form-item label="库存" required>
        <el-input-number v-model="newBook.number" :min="0" />
      </el-form-item>
      <el-form-item label="描述" required>
        <el-input 
          v-model="newBook.description" 
          type="textarea" 
          placeholder="请输入描述" 
          :rows="3"
        />
      </el-form-item>
       <!-- 添加的类型选择器 -->
      <el-form-item label="类型" required>
        <el-select 
          v-model="newBook.type"
          placeholder="请选择图书类型"
          style="width: 100%"
          
        >
          <el-option
            v-for="item in typeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
        <div v-if="newBook.type" style="margin-top: 8px;">
          当前选择：{{ newBook.type }}
        </div>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitNewBook">确定</el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 修改图书对话框 -->
  <el-dialog v-model="editDialogVisible" title="修改图书信息" width="50%">
    <el-form :model="editBook" label-width="80px">
      <el-form-item label="书名" required>
        <el-input v-model="editBook.bookname" placeholder="请输入书名" />
      </el-form-item>
      <el-form-item label="作者" required>
        <el-input v-model="editBook.bookauthor" placeholder="请输入作者" />
      </el-form-item>
      <el-form-item label="库存" required>
        <el-input-number v-model="editBook.number" :min="0" />
      </el-form-item>
      <el-form-item label="描述" required>
        <el-input 
          v-model="editBook.description" 
          type="textarea" 
          placeholder="请输入描述" 
          :rows="3"
        />
      </el-form-item>
      <el-form-item label="类型" required>
        <el-select 
          v-model="editBook.type"
          placeholder="请选择图书类型"
          style="width: 100%"
        >
          <el-option
            v-for="item in typeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
        <div v-if="editBook.type" style="margin-top: 8px;">
          当前选择：{{ editBook.type }}
        </div>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitEditBook">保存修改</el-button>
      </span>
    </template>
  </el-dialog>
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
