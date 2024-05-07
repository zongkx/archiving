<template>
  <el-row>
    <el-row style="padding-top: 50px">
      <el-col :span="2">
        <el-button icon="el-icon-plus" @click="add"></el-button>
      </el-col>
    </el-row>

    <el-row>
      <el-table
        :data="this.roleTableData"
        stripe
        style="width: 100%">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-col :span="24">
              <h2>权限:</h2>
            </el-col>
            <el-col :span="14" :offset="4">
              <el-table :data="props.row.rolePerms" size="mini" max-height="500px">
                <el-table-column prop="url" label="URL" align="center"></el-table-column>
                <el-table-column prop="name" label="name" align="center"></el-table-column>
              </el-table>
            </el-col>
          </template>
        </el-table-column>
        <el-table-column sortable prop="name" label="角色" width="200" align="center">
        </el-table-column>
        <el-table-column prop="type" align="center" sortable label="角色名称">
        </el-table-column>
        <el-table-column label="操作" width="160px">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-row>
    <!-- 弹出框   -->
    <el-row>
      <el-dialog title="编辑" :visible.sync="dialogFormVisible" width="50%" @close='closeDialog'>
        <el-form :model="form" label-position="right" label-width="80px">
          <el-form-item label="用户名:">
            <el-col :span="16" :offset="3">
              <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="角色名称:">
            <el-col :span="16" :offset="3">
              <el-input v-model="form.type" autocomplete="off"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="权限:" style="padding-top: 20px">
            <el-col :span="24">
              <template>
                <div style="text-align: center">
                  <el-transfer
                    style="text-align: left; display: inline-block" :titles="['所有权限', '已有权限']"
                    :button-texts="['移除', '添加']"
                    v-model="value" :data="data"></el-transfer>
                </div>
              </template>
            </el-col>
          </el-form-item>
        </el-form>


        <el-button @click="save(form)">保存</el-button>
      </el-dialog>


      <!-- 添加弹出框   -->
      <el-row>
        <el-dialog title="添加" :visible.sync="dialogFormVisible1" width="50%" @close='closeDialog'>
          <el-form :model="form1" label-position="right" label-width="80px">
            <el-form-item label="用户名:">
              <el-col :span="16" :offset="3">
                <el-input v-model="form1.name" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="角色名称:">
              <el-col :span="16" :offset="3">
                <el-input v-model="form1.type" autocomplete="off"></el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="权限:" style="padding-top: 20px">
              <el-col :span="24">
                <template>
                  <div style="text-align: center">
                    <el-transfer
                      style="text-align: left; display: inline-block" :titles="['所有权限', '已有权限']"
                      :button-texts="['移除', '添加']"
                      v-model="value" :data="data"></el-transfer>
                  </div>
                </template>
              </el-col>
            </el-form-item>
          </el-form>
          <el-button @click="addSave(form1)">保存</el-button>
        </el-dialog>

      </el-row>
    </el-row>
  </el-row>
</template>

<script>
  export default {
    name: "role",
    data() {
      return {
        roleTableData: [],
        dialogFormVisible: false,
        dialogFormVisible1: false,

        form: {
          name: '',
          type: '',
          id: '',
        },
        form1: {
          name: '',
          type: '',
          id: '',
        },
        data: [],

        value: [],

        allPerms: [],

        oldPerms: '',

        loadFlag:true,
      }
    },
    methods: {
      roleTableInit: function () {
        this.axios({
          method: 'post',
          data: '',
          url: 'api/role/roleList',
        }).then(result => {
          if (result.data.code == 4403) {
            this.$message.error(result.data.msg);
          } else {
            this.roleTableData = result.data.data;
          }
        }).catch(error => {
          this.$message.error("网络异常");
        })

      },
      handleEdit(index, row) {
        this.dialogFormVisible = true;
        this.form.name = row.name;
        this.form.type = row.type;
        this.form.id = row.id;

        var q = row.rolePerms;
        q.forEach(({id}) => {
          var k = {
            id,
          }
          this.value.unshift(k.id);
        })
      },

      handleDelete(index, row) {
        var params = new URLSearchParams();
        params.append('rid', row.id);


        this.$confirm("确认删除?", '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.axios({
            method: 'post',
            data: params,
            url: 'api/role/delRole',
          }).then(result => {
            if (result.data.code == -1) {
              this.$message.info(result.data.oper);
            } else {
              this.$message.success('OK');
              this.dialogFormVisible = false;
              this.roleTableInit();
            }
          }).catch(error => {
            this.$message.error("网络异常");
          })
        }).catch(error => {
          this.$message.error("网络异常");
        })

      },

      closeDialog() {
        this.value = [];
        this.form.name = '';
        this.form.type = '';
        this.form1.name = '';
        this.form1.type = '';
      },

      getAllPerms() {
        var params1 = new URLSearchParams();
        params1.append('offset', 0);
        params1.append('limit', 100);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/perm/permPageList',
        }).then(result => {
          var q = result.data.rows;
          q.forEach(({id, name, url}) => {
            var k = {
              id,
              name,
              url
            }
            this.data.push({
              key: k.id,
              label: name,
              disabled: false,
            })
          })
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },


      save(val) {
        var params1 = new URLSearchParams();
        params1.append('id', this.form.id);
        params1.append('name', this.form.name);
        params1.append('type', this.form.type);
        params1.append('pids', this.value);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/role/modifyRole',
        }).then(result => {
          this.dialogFormVisible = false;
          this.value = [];
          this.$message.success("保存成功");
          this.roleTableInit();
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },

      add() {
        this.dialogFormVisible1 = true;

      },
      addSave() {
        var params1 = new URLSearchParams();
        if (this.form1.name == '') {
          this.$message.info("name 不能为空");
          return;
        }
        ;
        if (this.form1.type == '') {
          this.$message.info("名称不能为空");
          return;
        }
        ;
        if (this.form1.name.split(" ").join("").length == 0) {
          this.$message.info("Name不能全部为空格");
          return;
        }
        ;
        if (this.form1.type.split(" ").join("").length == 0) {
          this.$message.info("名称不能全部为空格");
          return;
        }
        ;

        params1.append('name', this.form1.name);
        params1.append('type', this.form1.type);
        params1.append('pids', this.value);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/role/addRole',
        }).then(result => {
          this.dialogFormVisible1 = false;
          this.$message.success("保存成功");
          this.roleTableInit();
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },
    },

    created() {
      if (!this.$store.state.isLogin) {
        this.$message.info("您还没有登录,请登录后重试");
        this.$router.push("/");
      } else {
        this.roleTableInit();
        this.getAllPerms();

      }
    }
  }
</script>

<style scoped>

</style>
