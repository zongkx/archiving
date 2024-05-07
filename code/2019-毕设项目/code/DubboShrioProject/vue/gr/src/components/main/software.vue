<template>

  <div id="main" style="min-width: 800px;padding-top: 30px; ">
    <el-row type="flex" class="row-bg">
      <el-col :span="18"  ><!--图片轮播-->
        <div class="grid-content">
          <el-carousel :interval="5000" arrow="always">
            <el-carousel-item v-for="item in imgList1" :key="item.id">
              <el-row>
                <el-col :span="24"  >
<!--                  <img :src="item.idView" class="bannerImg"/>-->
                  <div v-html="item.photo" style="height: 400px;" @click="toDetail(item)"></div>
                </el-col>
              </el-row>
            </el-carousel-item>
          </el-carousel>
        </div>
      </el-col>
      <el-col :span="5" :offset="1" ><!--热门内容-->
        <div class="grid-content"  style="background-color: #e5e9f2" id="div1">
          <h4 style="padding-top: 10px">为你推荐</h4>
          <el-row  v-for="(item,index) in items1" :key="index" style="text-align: left;padding-top: 7px">
              <el-col :span="1"> <el-tag>{{index+1}}</el-tag></el-col>
              <el-col :offset="4" :span="19">
                <a v-text="item.title" @click="toDetail(item)" href="javascript:void(0)"></a>
              </el-col>
          </el-row>

        </div>
      </el-col>
    </el-row>

    <el-row type="flex" class="row-bg" justify="left" v-for="(item,index) in items" :key="index"  >
      <el-col :span="6" :offset="0" style="height: 200px">
        <div class="grid-content bg-purple software "  >
<!--          <img ref="imgHeight" :src="item.photo" class="bannerImg"/>-->
          <div class="bannerImg" v-html="item.photo" style="display: flex;align-items: center;justify-content: center"></div>
        </div>
      </el-col>
      <el-col :span="10">
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
              <el-col :span="23" :offset="1" style="text-align:left;">
                <p v-html="item.text.substring(0,item.text.indexOf('。'))"></p>
              </el-col>
            </el-row>

          </el-row>
        </div>
      </el-col>


    </el-row>


    <el-row type="flex" class="row-bg" justify="left" style="padding-top: 50px">
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
    <el-row>
      <div class="foot">
      </div>

    </el-row>
  </div>

</template>

<script>
  import FooterNav from "../footer";

  export default {
    name: "software",
    components: {FooterNav},
    data() {
      return {
        // 图片需要引入, 否则无法显示
        imgList: [
          {id: 0, name: 'xx', idView: '../../../static/photo/2.png'},
          {id: 1, name: '详情', idView: '../../../static/photo/2.png'},
          {id: 2, name: '推荐', idView: '../../../static/photo/2.png'}
        ],
        imgList1:'',

        items: '',

        offset: 1,//默认在第一页

        limit: 10,//默认每页显示5条数据

        totalCount: 1,//默认总条数为1条

        objs:'',

        items1:'',
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

        this.axios({
          method: 'post',
          data: params1,
          url: 'api/sf/getSoftwarePage',
        }).then(result => {
          this.items = result.data.rows;
          this.totalCount = result.data.total;
        }).catch(error => {
          this.$message.error(error.data);
        })

      },
      getImgList:function () {
        this.axios({
          method: 'post',
          data: '',
          url: 'api/sf/getSoftwareByScore',
        }).then(result => {
          this.imgList1 = result.data.data;
        }).catch(error => {
          this.$message.error("网络异常");
        })


      },
      getCF() {
        var params1 = new URLSearchParams();
        params1.append('nickname', this.$store.state.user.nickname);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/sf/getCF',
        }).then(result => {
          this.items1 = result.data.data;
          if (result.data.msg != '0') {
            this.$store.commit("cf", result.data.msg);
          }else{
            this.$store.commit("cf",'');
          }

        }).catch(error => {
          this.$message.error("网络异常");
        })
      },



    },
    mounted() {


    },
    created() {
      this.getImgList();
      this.getlist();
      this.getCF();
    }


  }

</script>

<style scoped>

  a{
    text-decoration: none;
    color: grey;

  }
  a:hover{
    text-decoration: none;
    color: black;

  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .software {
    margin-top: 20px;
    height: 160px;
  }

  .bannerImg {
    width: 100%;
    height: inherit;
  }

  #div1 {
    height: 350px;
  }



</style>
