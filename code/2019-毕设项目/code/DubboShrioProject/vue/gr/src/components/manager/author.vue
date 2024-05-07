<template>

  <el-row>
    <el-row style="padding-top: 50px">
      <el-col :span="2">
        <el-button icon="el-icon-plus" @click="add"></el-button>
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
        stripe
        style="width: 100%">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-col :span="24">
              <el-card style="text-align: center" v-html="props.row.text">
              </el-card>
            </el-col>
          </template>
        </el-table-column>
        <el-table-column prop="title" align="center" sortable label="标题">
        </el-table-column>
        <el-table-column prop="type" align="center" sortable label="类别">
        </el-table-column>
        <el-table-column prop="date" align="center" sortable label="发布日期" width="180">
        </el-table-column>
        <el-table-column prop="userId" align="center" sortable label="发布者">
        </el-table-column>
        <el-table-column prop="score" align="center" sortable label="评分">
        </el-table-column>
        <el-table-column prop="scoreLog" align="center" sortable label="记录">
        </el-table-column>


        <el-table-column label="操作" width="160px">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="edit(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="del(scope.$index, scope.row)">删除
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
  </el-row>


</template>

<script>
  export default {
    name: "software",
    data() {
      return {
        offset: 1,//默认在第一页
        limit: 10,//默认每页显示5条数据
        totalCount: 1,//默认总条数为1条
        tableData: [],
        dialogFormVisible: false,

        qryStr: '',
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


      tableInit() {
        var params = new URLSearchParams();
        params.append('offset', this.offset);
        params.append('limit', this.limit);
        params.append('qryStr', this.qryStr);
        params.append('aid', this.$store.state.user.id);
        this.axios({
          method: 'post',
          data: params,
          url: 'api/sf/getSoftwarePageByAid',
        }).then(result => {
          this.tableData = result.data.rows;
          this.totalCount = result.data.total;
        }).catch(error => {
          this.$message.error(error.data);
        })
      },

      qry() {

      },


      del(index, row) {
        this.$confirm('此操作将删除该软件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          var params1 = new URLSearchParams();
          params1.append('id', row.id);
          this.axios({
            method: 'post',
            data: params1,
            url: 'api/sf/delSoftwareById',
          }).then(result => {
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
      },

      add() {
        this.$router.push("/admin")
      },
      edit(index, row) {
        this.$store.dispatch('software', JSON.stringify(row));
        this.$router.push("/admin")
      },

    },
    created() {
      this.tableInit();
    }
  }
</script>

<style scoped>

</style>
