<template>

  <div style=" ">
    <el-row>
      <el-col :span="5">
        <h2>分类:{{type}}</h2>
      </el-col>
    </el-row>
    <el-row v-for="(item,index) in items" :key="index">
      <el-col :span="6" :offset="0">
        <div class="grid-content  software" v-html="item.photo">
<!--          <img ref="imgHeight" :src="item.photo" class="bannerImg"/>-->
        </div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content  software">
          <el-row :span="6">
            <el-row>
              <el-col :span="23" :offset="1"
                      style="text-align:left;padding-top: 5px;font-size: 18px;font-weight:bold;">
                <a v-text="item.title" @click="toDetail(item)" href="javascript:void(0)"></a>
              </el-col>
            </el-row>
            <el-row style="padding-top: 10px">
              <el-col :span="6" :offset="0">
                <el-tag  type="info" size="mini">{{new Date(item.date).Format("yyyy-MM-dd")}}</el-tag>
              </el-col>
              <el-col :span="3" :offset="0">
                <el-tag type="info" size="mini" v-text="item.type"></el-tag>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="20" :offset="1" style="text-align:left;">
                <p v-html="item.text.substring(0,40)"></p>
              </el-col>
            </el-row>

          </el-row>
        </div>
      </el-col>
    </el-row>
    <el-row type="flex" class="row-bg" justify="left">
      <el-col :span="24" class="toolbar pageBar">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="offset"
          :page-sizes="[10,20]"
          :page-size="limit"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalCount"
        >
        </el-pagination>
      </el-col>

    </el-row>
  </div>

</template>

<script>
  export default {
    name: "list",
    data() {
      return {
        items: '',

        type: this.$store.state.type,

        offset: 1,//默认在第一页

        limit: 10,//默认每页显示5条数据

        totalCount: 1,//默认总条数为1条
      }
    },
    methods: {
      toDetail: function (item) {
        this.$store.dispatch('software', JSON.stringify(item));
        this.$router.push('/detail');

      },
      //操作每页显示几条
      handleSizeChange(val) {
        this.limit = val;//获取page-sizes里的每页显示几条数据的值，赋给我们自定义的每页显示数量的变量pageNum
        this.getlist();//展示页面信息
      },
      // 操作显示第几页
      handleCurrentChange(val) {
        this.offset = val;
        this.getlist();//确定当前页面后刷新页面
      },
      getlist: function () {
        var params1 = new URLSearchParams();
        params1.append('offset', this.offset);
        params1.append('limit', this.limit);
        params1.append('type', this.type);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/sf/getSoftwarePageByType',
        }).then(result => {
          this.items = result.data.rows;
          this.totalCount = result.data.total;
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },


    },
    mounted() {
      this.getlist();
    },
  }
</script>

<style scoped>


  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }



  .software {
    margin-top: 30px;
    height: 160px;
  }

  .bannerImg {
    width: 100%;
    height: inherit;
  }
  a{
    text-decoration: none;
    color: grey;

  }
  a:hover{
    text-decoration: none;
    color: black;

  }
  #div1 {
    height: 400px;
  }
</style>
