<template>
  <div>
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input
          v-model="searchId"
          class="search-value"
          size="mini"
          placeholder="输入ID查询">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-input
          v-model="searchUserName"
          class="search-value"
          size="mini"
          placeholder="输入用户名查询">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-input
          v-model="searchRealName"
          class="search-value"
          size="mini"
          placeholder="输入中文名查询">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          type="text"
          @click="onSearch()"
          class="el-icon-search">查询
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          class="el-icon-refresh"
          type="text"
          @click="refreshData">清空
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          class="el-icon-circle-plus-outline"
          type="text"
          @click="dialogVisible = true">添加
        </el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="tableData"
      highlight-current-row
      border
      style="width: 100%">
      <el-table-column
        label="id">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="用户名">
        <template slot-scope="scope">
          <span>{{ scope.row.userName }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="中文名">
        <template slot-scope="scope">
          <span>{{ scope.row.realName }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="跳转路径">
        <template slot-scope="scope">
          <span>{{ scope.row.path }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="等级">
        <template slot-scope="scope">
          <span>{{ scope.row.level }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="状态">
        <template slot-scope="scope">
          <span>{{ scope.row.status }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="创建时间">
        <template slot-scope="scope">
          <span>{{ dateFormat(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        fixed="right"
        width="200">
        <template slot-scope="scope">
          <el-button
            size="mini"
            icon="el-icon-edit"
            @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            icon="el-icon-delete"
            @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="70px" class="demo-ruleForm" size="medium">
      <el-dialog
        title="编辑"
        :append-to-body='true'
        :visible.sync="dialogVisible"
        width="80%"
        :before-close="handleClose">
        <el-input type="hidden" v-model="ruleForm.id"/>
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="ruleForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="中文名" prop="realName">
          <el-input v-model="ruleForm.realName"></el-input>
        </el-form-item>
        <el-form-item label="跳转路径" prop="path">
          <el-input v-model="ruleForm.path"></el-input>
        </el-form-item>
        <el-form-item label="等级" prop="level">
          <el-input v-model="ruleForm.level"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-input v-model="ruleForm.status"></el-input>
        </el-form-item>

        <span slot="footer" class="dialog-footer">
            <el-button @click="cancel()" size="medium">取 消</el-button>
            <el-button @click="addUser()" type="primary" size="medium">确 定</el-button>
          </span>
      </el-dialog>
    </el-form>

    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="70px" class="demo-ruleForm" size="medium">
      <el-dialog
        title="编辑"
        :append-to-body='true'
        :visible.sync="dialogUpdate"
        width="80%"
        :before-close="handleClose">
        <el-input type="hidden" v-model="ruleForm.id"/>
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="ruleForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="中文名" prop="realName">
          <el-input v-model="ruleForm.realName"></el-input>
        </el-form-item>
        <el-form-item label="跳转路径" prop="path">
          <el-input v-model="ruleForm.path"></el-input>
        </el-form-item>
        <el-form-item label="等级" prop="level">
          <el-input v-model="ruleForm.level"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-input v-model="ruleForm.status"></el-input>
        </el-form-item>

        <span slot="footer" class="dialog-footer">
            <el-button @click="cancel()" size="medium">取 消</el-button>
            <el-button @click="updateUser()" type="primary" size="medium">确 定</el-button>
          </span>
      </el-dialog>
    </el-form>
    <br>
    <div class="pages">
      <el-pagination
        background
        :disabled="disablePage"
        :current-page.sync="currentPage"
        small
        layout="prev, pager, next"
        :page-size="pageSize"
        :total="total"
        @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        id: '',
        userName: '',
        realName: '',
        path: '',
        level: '',
        status: ''
      },
      rules: {
        userName: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 2, max: 7, message: '长度在 2 到 7 个字符', trigger: 'blur'}
        ],
      },
      tableData: [],
      searchId: '',
      searchUserName: '',
      searchRealName: '',
      dialogVisible: false,
      dialogUpdate: false,
      pageSize: 2,
      currentPage: 1,
      total: 0,
      disablePage: false
    }
  },
  methods: {
    dateFormat(date) {
      if (date) {
        let dt = new Date(date);//实例化时间对象
        let y = dt.getFullYear();
        let m = dt.getMonth() + 1;
        let d = dt.getDate();
        //笨方法  return y + '-' + m + '-' + d
        //官方方法  注意引号，是tab键上面的符号（ES6的语法）
        return `${y} - ${m} - ${d}`
      } else {
        return ''
      }
    },
    handleEdit(index, row) {
      this.dialogUpdate = true;
      this.ruleForm = Object.assign({}, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
      this.$confirm('删除操作, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.axios({
          method: 'delete',
          url: '/' + row.id
        }).then(response => {
          this.currentPage = 1;
          let searchUrl = this.getSearchUrl()
          this.axios.get(searchUrl).then(response => {
            this.tableData = response.data.data.list;
            this.total = response.data.data.total;
          }).catch(error => {
            console.log(error);
          });
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          console.log(response);
        }).catch(error => {
          console.log(error);
        });

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    handleCurrentChange() {
      console.log(`当前页: ${this.currentPage}`);
      let searchUrl = this.getSearchUrl()
      this.axios({
        method: 'get',
        url: searchUrl
      }).then(response => {
        this.tableData = response.data.data.list;
        this.total = response.data.data.total;
      }).catch(error => {
        console.log(error);
      });
    },
    getSearchUrl() {
      let searchUrl = '/search?page=' + this.currentPage
      if (this.searchId || this.searchId != 0) {
        searchUrl = searchUrl + '&id=' + this.searchId
      }
      if (this.searchUserName || this.searchUserName != '') {
        searchUrl = searchUrl + '&userName=' + this.searchUserName
      }
      if (this.searchRealName || this.searchRealName != '') {
        searchUrl = searchUrl + '&realName=' + this.searchRealName
      }
      return searchUrl
    },
    cancel() {
      this.dialogUpdate = false;
      this.dialogVisible = false;
      this.emptyUserData();
    },
    emptyUserData() {
      this.ruleForm = {
        userName: '',
        status: ''
      }
    },
    addUser() {
      let postData = {
        userName: this.ruleForm.userName,
        realName: this.ruleForm.realName,
        path: this.ruleForm.path,
        level: this.ruleForm.level,
        status: this.ruleForm.status
      };
      this.axios({
        headers: {
          'Content-Type': 'application/json'
        },
        method: 'post',
        url: '/',
        data: JSON.stringify(postData)
      }).then(response => {
        let searchUrl = this.getSearchUrl()
        this.axios.get(searchUrl).then(response => {
          this.tableData = response.data.data.list;
          this.total = response.data.data.total;
          this.currentPage = 1;
          this.$message({
            type: 'success',
            message: '已添加!'
          });
        }).catch(error => {
          console.log(error);
        });
        this.dialogVisible = false
        console.log(response);
      }).catch(error => {
        console.log(error);
      });
    },
    updateUser() {
      let postData = {
        userName: this.ruleForm.userName,
        realName: this.ruleForm.realName,
        path: this.ruleForm.path,
        level: this.ruleForm.level,
        status: this.ruleForm.status
      };
      this.axios({
        headers: {
          'Content-Type': 'application/json'
        },
        method: 'put',
        url: '/' + this.ruleForm.id,
        data: JSON.stringify(postData)
      }).then(response => {
        this.handleCurrentChange();
        this.cancel();
        this.$message({
          type: 'success',
          message: '更新成功!'
        });
        console.log(response);
      }).catch(error => {
        this.$message({
          type: 'success',
          message: '更新失败!'
        });
        console.log(error);
      });
    },
    onSearch() {
      let searchUrl = this.getSearchUrl()
      this.axios({
        method: 'get',
        url: searchUrl
      }).then(response => {
        this.tableData = response.data.data.list;
        this.total = response.data.data.total;
      }).catch(error => {
        console.log(error);
      });
    },
    refreshData() {
      location.reload();
    }
  },
  created() {
    let searchUrl = this.getSearchUrl()
    this.axios.get(searchUrl).then(response => {
      this.tableData = response.data.data.list;
      this.total = response.data.data.total;
    }).catch(error => {
      console.log(error);
    });

  },
}
</script>
<style scoped>
.search-value {
  width: 200px;
}

.pages {
  margin: 0px;
  padding: 0px;
  text-align: right;
}
</style>
