<template>
  <el-row>

    <el-col :span="16" :offset="4" v-for="(item,index) in items" :key="index" style="padding-top: 10px">
        <el-card>
          <el-col :span="12">
            <el-button size="mini">{{item.fromUid}}</el-button>
            <el-date-picker style="border: none;width: 130px"
                            v-model="item.time"
                            type="date" readonly align="right"
                            size="mini" format="yyyy-MM-dd" clear-icon=""
            >
            </el-date-picker>
          </el-col>
          <el-col :span="12" :offset="5" style="text-align: left">
            <h4>
              <el-badge is-dot class="item" v-if="unRead(index)">
                <a href="javascript:void(0)" @click="toDetail(item)" style="color:darkcyan;">{{item.content.substring(5,100)}}</a>
              </el-badge>
              <a v-else href="javascript:void(0)" @click="toDetail(item)" style="color:darkcyan;">{{item.content.substring(5,100)}}</a>
            </h4>

          </el-col>

        </el-card>

    </el-col>
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
</template>

<script>
  export default {
    name: "message",
    data() {
      return {
        nickname: this.$store.state.user.nickname,

        offset: 1,//默认在第一页

        limit: 10,//默认每页显示5条数据

        totalCount: 1,//默认总条数为1条

        items: '',

        unReadNum: this.$store.state.unReadNum,
      }

    },
    methods: {
      //操作每页显示几条
      handleSizeChange(val) {
        this.limit = val;//获取page-sizes里的每页显示几条数据的值，赋给我们自定义的每页显示数量的变量pageNum
        this.init();//展示页面信息
      },
      // 操作显示第几页
      handleCurrentChange(val) {
        this.offset = val;
        this.init();//确定当前页面后刷新页面
      },
      init() {
        var params1 = new URLSearchParams();
        params1.append('offset', this.offset);
        params1.append('limit', this.limit);
        params1.append('nickname', this.nickname);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/comment/getMessageByNickname',
        }).then(result => {
          this.items = result.data.data.rows;
          this.totalCount = result.data.data.total
        }).catch(error => {
          this.$message.error("网络异常");
        })


      },
      toDetail(item) {

        if(item.type == '1'){
          var params1 = new URLSearchParams();
          params1.append('aid', item.aid);
          params1.append('id', item.id);

          this.axios({
            method: 'post',
            data: params1,
            url: 'api/sf/getSoftwareById',
          }).then(result => {
            this.$store.dispatch('software', JSON.stringify(result.data.data));
            if( this.$store.state.unReadNum > 0){
              this.$store.commit('num',this.unReadNum-1);
            }
            this.$router.push('/detail');
          }).catch(error => {
            this.$message.error("网络异常");
          })

        }else if(item.type == '2'){
            if( this.$store.state.unReadNum > 0){
              this.$store.commit('num',this.unReadNum-1);
            }
            this.$store.commit('aid',item.aid);
            this.$router.push('/userDetail');

        }else{
          if( this.$store.state.unReadNum > 0){
            this.$store.commit('num',this.unReadNum-1);
          }
        }
        this.readed(item.id)
      },
      unRead(index) {
        if (index < this.unReadNum) {
          return true;
        } else {
          return false;
        }
      },
      readed(id){
        var params1 = new URLSearchParams();
        params1.append('id', id);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/comment/readed',
        }).then(result => {

        }).catch(error => {
        })

      },


    },
    created() {
      this.init();
    }
  }
</script>

<style scoped>
  a {
    text-decoration: none;
    color: grey;

  }

  a:hover {
    text-decoration: none;
    color: black;

  }
</style>
