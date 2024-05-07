<template>
  <el-row>
    <el-row style="padding-top: 50px">

      <el-col :span="2">
        <el-button icon="el-icon-plus" @click="add"></el-button>
      </el-col>
      <el-col :span="1">
        <el-button icon="el-icon-delete" type="danger" @click="del"></el-button>
      </el-col>
      <el-col :span="5" :offset="16">
        <el-input v-model="qryStr" clearable>
          <el-button slot="append" icon="el-icon-search" @click="tableInit"></el-button>
        </el-input>

      </el-col>

    </el-row>


    <el-row>
      <el-table
        :data="this.tableData"
        ref="multipleTable"
        @selection-change="handleSelectionChange"
        stripe
        style="width: 100%;height: 700px;">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column prop="id" label="ID" align="left" width="100">
        </el-table-column>
        <el-table-column sortable prop="url" label="URL" width="300" align="center">
        </el-table-column>
        <el-table-column prop="name" align="center" sortable label="名称">
        </el-table-column>

        <el-table-column label="操作" width="160px">
          <template slot-scope="scope">
            <el-button
              size="mini"
              class="el-icon-edit"
              @click="handleEdit(scope.$index, scope.row)">编辑
            </el-button>
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
          <el-form-item label="ID">
            <el-input v-model="form.id" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="URL">
            <el-input v-model="form.url" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="名称">
            <el-input v-model="form.name" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <el-button @click="save(form)">保存</el-button>
      </el-dialog>
    </el-row>
    <!-- 弹出框   -->
    <el-row>
      <el-dialog title="添加" :visible.sync="dialogFormVisible1" width="30%">
        <el-form :model="this.addForm">

          <el-form-item label="URL">
            <el-input v-model="addForm.url"></el-input>
          </el-form-item>
          <el-form-item label="名称">
            <el-input v-model="addForm.name"></el-input>
          </el-form-item>
        </el-form>
        <el-button @click="save(addForm)">保存</el-button>
      </el-dialog>
    </el-row>

  </el-row>
</template>

<script>
  export default {
    name: "perm",
    data() {
      return {

        offset: 1,//默认在第一页
        limit: 10,//默认每页显示5条数据
        totalCount: 1,//默认总条数为1条

        tableData: [],
        dialogFormVisible: false,
        dialogFormVisible1: false,

        form: {
          url: '',
          name: '',
          id: '',
        },
        addForm: {
          url: '',
          name: '',
        },
        qryStr: '',

        selections: '',

      }
    },
    methods: {
      //操作每页显示几条
      handleSizeChange(val) {
        this.limit = val;//获取page-sizes里的每页显示几条数据的值，赋给我们自定义的每页显示数量的变量pageNum
        this.tableInit();//展示页面信息
      },
      // 操作显示第几页
      handleCurrentChange(val) {
        this.offset = val;
        this.tableInit();//确定当前页面后刷新页面
      },
      tableInit: function () {
        var params1 = new URLSearchParams();
        params1.append('offset', this.offset);
        params1.append('limit', this.limit);
        params1.append('qry', this.qryStr);

        this.axios({
          method: 'post',
          data: params1,
          url: 'api/perm/permPageList',
        }).then(result => {
          if (result.data.code == 4403) {
            this.$message.error(result.data.msg);
          } else {
            this.tableData = result.data.rows;
            this.totalCount = result.data.total
          }
        }).catch(error => {
          this.$message.error("网络异常");
        })

      },
      add() {
        this.dialogFormVisible1 = true;
      },
      handleEdit(index, row) {
        this.dialogFormVisible = true;
        this.form.id = row.id;
        this.form.name = row.name;
        this.form.url = row.url;

      },

      save(val) {

        var url = '';
        var obj = {};
        if (val.url == '') {
          this.$message.info("URL 不能为空");
          return;
        }
        if (val.name == '') {
          this.$message.info("名称不能为空");
          return;
        }
        if (val.name.split(" ").join("").length == 0) {
          this.$message.info("Name不能全部为空格");
          return;
        }
        ;
        if (val.url.split(" ").join("").length == 0) {
          this.$message.info("URL不能全部为空格");
          return;
        }
        ;

        obj.url = val.url;
        obj.name = val.name;
        if (val.id != null) {
          obj.id = val.id;
          url = 'api/perm/modifyPerm';
        } else {
          url = 'api/perm/addPerm';
        }
        this.axios({
          method: 'post',
          data: obj,
          url: url,
        }).then(result => {

          if (val.id != null) {
            this.dialogFormVisible = false;
            this.$message.success("修改成功");

          } else {
            this.dialogFormVisible1 = false;
            this.addForm.name = '';
            this.addForm.url = '';
            this.$message.success("添加成功");

          }


          this.tableInit();
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },
      del() {
        if (this.selections.length == 0) {
          this.$message.info("请选择需要删除的列");
        } else {
          var ids = '';
          this.selections.forEach(s => {
            ids = ids + s.id + ','
          });
          ids = ids.substring(0, ids.lastIndexOf(','));
          var params1 = new URLSearchParams();
          params1.append('ids', ids);
          this.axios({
            method: 'post',
            data: params1,
            url: 'api/perm/delPerm',
          }).then(result => {
            var name = '';
            result.data.data.forEach(s => {
              name = name + '  |  ' + s;
            });
            var msg = '';
            if (name != '') {
              msg = '你的操作即将影响下列[角色]:    ' + name;
            } else {
              msg = '确认删除';
            }
            this.$confirm(msg, '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              this.axios({
                method: 'post',
                data: params1,
                url: 'api/perm/delPermConfirm',
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

          }).catch(error => {
            this.$message.error("网络异常");
          })


        }
      },
      handleSelectionChange(val) {
        this.selections = val;
      }

    },

    created() {
        this.tableInit();
    }
  }
</script>

<style scoped>

</style>
