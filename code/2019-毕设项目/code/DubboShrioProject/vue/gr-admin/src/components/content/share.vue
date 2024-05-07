<template>
  <el-row>
    <el-table
      :data="this.tableData"
      stripe
      style="width: 100%;padding-top: 80px">
      <el-table-column prop="datetime" align="center" sortable label="发布时间">
      </el-table-column>
      <el-table-column prop="text" align="center" sortable label="内容">
      </el-table-column>


      <el-table-column prop="userId" align="center" sortable label="发布者">
      </el-table-column>
      <el-table-column prop="likenum" align="center" sortable label="点赞数">
      </el-table-column>
      <el-table-column prop="likeId" align="center" sortable label="点赞人">
      </el-table-column>

      <el-table-column label="操作" width="160px">
        <template slot-scope="scope">
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
</template>

<script>
  export default {
    name: "share",
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
        this.axios({
          method: 'post',
          data: params,
          url: 'api/share/getSharePage',
        }).then(result => {
          if (result.data.code == 4403) {
          } else {
            this.tableData = result.data.rows;
            this.totalCount = result.data.total;
          }
        }).catch(error => {
          this.$message.error(error.data);
        })
      },
      del(index,row){
        this.$confirm('此操作将删除该动态, 是否继续?', '提示', {
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
            url: 'api/share/deleteShareById',
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
      }


    },
    created() {
      this.tableInit();
    }

  }
</script>

<style scoped>

</style>
