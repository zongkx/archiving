<template>
  <el-row>
    <el-row style="padding-top: 50px">

     <!-- <el-col :span="2">
        <el-button icon="el-icon-plus" @click="applyAll()"></el-button>
      </el-col>
-->
    </el-row>

    <el-row  >
      <el-table
        :data="this.userTableData"  @selection-change="handleSelectionChange"
        ref="multipleTable"
        stripe
        style="width: 100%"
        >

        <el-table-column
          prop="id"
          label="ID"
          align="left"
          width="40"
        >
        </el-table-column>
        <el-table-column
          sortable
          prop="nickname"
          label="用户"
          width="80"
        >
        </el-table-column>
        <el-table-column
          prop="email" align="center" sortable
          label="邮箱">
        </el-table-column>
        <el-table-column
          label="创建时间" align="center" sortable
        >
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">
            {{ new Date(scope.row.createTime ).Format("yyyy-MM-dd")}}
          </span>
          </template>
        </el-table-column>
        <el-table-column
          label="上次登录时间" sortable
          align="center">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">
            {{ new Date(scope.row.lastLoginTime ).Format("MM-dd hh:mm")}}
          </span>
          </template>
        </el-table-column>
        <el-table-column
          prop="status"
          label="状态"
          width="60"
          :formatter="ifendcase">
        </el-table-column>
        <el-table-column
          prop="apply"
          label="状态"
          width="60"
          :formatter="ifapply">
        </el-table-column>
        <el-table-column
          label="角色"
          width="100">
          <template slot-scope="scope">
          <span style="margin-left: 10px">
              <div v-for="(item,index) in scope.row.userRoles" :key="index">
                {{item.type}}
              </div>
            </span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="160px">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row, scope.row.userRoles)">编辑
            </el-button>
            <el-dropdown size="mini">
              <el-button type="mini">
                更多<i class="el-icon-arrow-down el-icon--right"></i>
              </el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click="handleDelete(scope.$index, scope.row)">删除</el-dropdown-item>
              </el-dropdown-menu>

            </el-dropdown>

          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="offset"
        :page-sizes="[5,10,20]"
        :page-size="limit"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalCount"
      ></el-pagination>
    </el-row>
    <!-- 弹出框   -->
    <el-row>
      <el-dialog title="编辑" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form">
          <el-form-item label="用户名">
            <el-input v-model="form.nickname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="是否可用">

            <el-tooltip :content="'禁用标识: 0 禁用 1允许'" placement="top">
              <el-switch
                v-model="status"
                active-color="#13ce66"
                inactive-color="#ff4949"
                active-value="1"
                inactive-value="0">
              </el-switch>
            </el-tooltip>
          </el-form-item>
          <el-form-item label="角色">
            <el-select v-model="role" placeholder="请选择">
              <el-option
                v-for="item in this.allRoles"
                :key="item.id"
                :label="item.type"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <el-button @click="saveUser(form)">保存</el-button>
      </el-dialog>
    </el-row>
  </el-row>
</template>

<script>
  export default {
    name: "manager",
    data() {
      return {
        userTableData: [],
        offset: 1,//默认在第一页
        limit: 5,//默认每页显示5条数据
        totalCount: 1,//默认总条数为1条
        dialogFormVisible: false,
        form: '',

        selections: '',
        roles: [],
        role: '',
        allRoles: '',

        status: '1',//弹窗中的默认值
      }
    },

    methods: {
      //操作每页显示几条
      handleSizeChange(val) {
        this.limit = val;//获取page-sizes里的每页显示几条数据的值，赋给我们自定义的每页显示数量的变量pageNum
        this.userTableInit();//展示页面信息
      },
      // 操作显示第几页
      handleCurrentChange(val) {
        this.offset = val;
        this.userTableInit();//确定当前页面后刷新页面
      },
      handleSelectionChange(val) {
        this.selections = val;
      },

      handleEdit(index, row, val) {
        this.dialogFormVisible = true;
        this.form = row;
        this.roles = val;
        // this.role = this.roles[0].type;
        this.getAllRoles();
      },

      handleDelete(index, row) {
        var params1 = new URLSearchParams();
        params1.append('idcard', row.id);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/user/delUsers',
        }).then(result => {
          this.$message.success("OK");
          this.userTableInit();
        }).catch(error => {
          this.$message.error("网络异常");
        })

      },

      userTableInit: function () {
        var params1 = new URLSearchParams();
        params1.append('offset', this.offset);
        params1.append('limit', this.limit);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/user/userList',
        }).then(result => {
          if(result.data.code == 4403){
            this.$message.error(result.data.msg);
          }else{
            this.userTableData = result.data.rows;
            this.totalCount = result.data.total;
          }



        }).catch(error => {
          this.$message.error("网络异常");
        })

      },
      getAllRoles() {
        this.axios({
          method: 'post',
          data: '',
          url: 'api/role/roleList',
        }).then(result => {
          this.allRoles = result.data.data;
        }).catch(error => {
          this.$message.error("getallroles网络异常");
        })
      },
      saveUser(val) {
        // var user = {
        //   id: val.id,
        //   status: this.status,
        //   userRoles: {'id':this.role},
        //   nickname: val.nickname,
        //   createTime: val.createTIme,
        //
        // }
        var params1 = new URLSearchParams();
        params1.append('id', val.id);
        params1.append('status', this.status);
        params1.append('roleId', this.role);
        params1.append('nickname',val.nickname);

        this.axios({
          method: 'post',
          data: params1,
          url: 'api/user/modifyUser',
        }).then(result => {
          val.status = this.status;
          this.status = '1';
          this.dialogFormVisible = false;
          this.userTableInit();
          this.$message.success("修改成功");
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },

      ifendcase(val){
        if(val.status == 1){
          return "可用"
        }else{
          return "禁用"
        }
      },
      ifapply(val){
        if(val.apply == 1){
          return "需要审批"
        }else if(val.apply ==  0){
          return "-"
        }else if(val.apply == 2){
          return  "已审批"
        }
      },
      applyAll(){
        if (this.selections.length == 0) {
          this.$message.info("请选择需要通过的列");
        } else {
          var ids = '';
          this.selections.forEach(s => {
            ids = ids + s.id + ','
          });
          ids = ids.substring(0, ids.lastIndexOf(','));
          var params1 = new URLSearchParams();
          params1.append('idcard', ids);
         this.$confirm(msg, '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.axios({
                method: 'post',
                data: params1,
                url: 'api/user/apply',
              }).then(result => {
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                this.tableInit();
              }).catch(error => {
                this.$message.error("网络异常");
              })

            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消删除'
              });
            });

        }

      }
    },

    created() {
      if (!this.$store.state.isLogin) {
        this.$message.info("您还没有登录,请登录后重试");
        this.$router.push("/");
      } else {
        this.userTableInit();
      }

    }


  }
</script>

<style scoped>

</style>
