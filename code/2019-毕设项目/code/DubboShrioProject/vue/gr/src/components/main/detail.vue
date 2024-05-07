<template>
  <el-row>
    <el-col :span="18"><!--详情页-->
      <el-row>
        <el-col :span="20"
                style="text-align: left; ">
          <h2>{{sf.title}}</h2>
        </el-col>
        <el-col :span="20" style="text-align: left;padding-top: 20px">
          <el-col :span="4">
            <el-button size="mini" type="info">{{sf.type}}</el-button>
          </el-col>
          <el-col :span="4">
            <el-button size="mini" @click="doCollection()" type="success" v-if="!doCollectionFlag">关注</el-button>
            <el-button size="mini" @click="deCollection()"  type="warning" v-else>取消关注</el-button>
          </el-col>
          <el-col :span="8">
              <el-date-picker style="width: 130px"
                              v-model="sf.date"
                              type="date" readonly align="right"
                              size="mini"
             >
              </el-date-picker>
          </el-col>
          <el-col :span="6" style="padding-top: 5px">
            <el-rate
              @change="this.doRate"
              allow-half
              :disabled="this.allowDoRateFlag || !this.isLogin"
              v-model="sf.score"
              :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
            </el-rate>
          </el-col>

        </el-col>
        <el-col :span="24" style="padding-top: 20px">
          <div id="main" style=" border-top: #E4E7ED 1px solid" v-html="sf.text">

          </div>
        </el-col>


      </el-row>

    </el-col>
    <el-col :span="18" style="margin-top: 400px;border-top: #E4E7ED 1px solid">

      <el-row>
        <h4 style="text-align: left"> 下载链接</h4>
        <p style="font-size: 16px;text-align: left"> {{sf.name}}  </p>
      </el-row>
    </el-col>



    <el-col :span="18" style=" border-top: #E4E7ED 1px solid"><!--评论-->
      <el-row style="text-align: left">
        <h3 style="color: #606266">{{items.length}}条留言</h3>
      </el-row>

      <el-row>
        <el-col :span="24">

          <el-row v-for="(item,index) in items" :key="index" :span="24"
                  style="padding-top: 10px">
            <el-col :span="1" :offset="0">
              <el-button size="mini"  >{{item.fromUid}}:</el-button>
            </el-col>
            <el-col :span="4" :offset="18">
              <el-dropdown trigger="click">
                                <span class="el-dropdown-link">
                                        <el-date-picker style="border: none;width: 130px"
                                                        v-model="item.time"
                                                        type="date" readonly align="right"
                                                        size="mini" format="yyyy-MM-dd" clear-icon=""
                                        >
                                        </el-date-picker>
                                 </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>
                    <el-button type="info"
                               size="mini"
                               icon="el-icon-message"
                               @click="replyMessage(item.fromUid)"
                    >留言
                    </el-button>
                  </el-dropdown-item>
                  <el-dropdown-item>
                    <el-button v-show="MineOrNot(item.fromUid)"
                               type="danger"
                               size="mini"
                               icon="el-icon-delete"
                               @click="delComment(item.id,index)"
                    >删除
                    </el-button>
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-col>
            <el-col :span="23" :offset="1"
                    style=" font-size: 18px;text-align: left;padding-top: 10px">
              &ensp;&ensp;{{item.content}}

            </el-col>


          </el-row>


        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24" style="text-align: left;margin-top: 50px;border-top: 1px solid #E4E7ED">
          <h3 style="color: #606266">写留言</h3>
        </el-col>
        <el-col :span="20">
          <el-card shadow="hover">
            <el-col>
              <el-input
                type="textarea"
                :autosize="{ minRows: 4, maxRows: 8}"
                v-model="message0"
                placeholder="请输入内容"
              >
              </el-input>
            </el-col>
            <el-col style="text-align: right;margin-bottom: 20px">
              <el-button type="primary" @click="this.addComment">留言</el-button>
            </el-col>

          </el-card>

        </el-col>
      </el-row>

    </el-col>

    <el-col :span="24" style="padding-top: 300px">


    </el-col>

  </el-row>

</template>

<script>

  export default {
    name: "detail",
    data() {
      return {
        sf: JSON.parse(this.$store.state.software),
        allowDoRateFlag: false,

        doCollectionFlag:false,

        items: '',
        perms: this.$store.state.user.userRoles,
        message0: '',
        isLogin: this.$store.state.isLogin,
        nickname:this.$store.state.user.nickname,

        comment_reviews: {
          content: '',
          toUid: '',
          type: '1',
          aid: '',
          fromUid: '',
          time: '',
          flag:0,
        },
      }

    },
    methods: {
      allowDoRate: function () {
        var params = new URLSearchParams();
        params.append('nickname', this.$store.state.user.nickname);
        params.append('id', this.sf.id);
        this.axios({
          method: 'post',
          data: params,
          url: 'api/sf/allowDoRate',
        }).then(result => {
          if (result.data.code == '-1') {
            this.allowDoRateFlag = true
          }
        }).catch(error => {
          this.$message.error("网络异常");
        })

      },
      checkDoCollection(){
        var params = new URLSearchParams();
        params.append('nickname', this.$store.state.user.nickname);
        params.append('sid', this.sf.id);
        this.axios({
          method: 'post',
          data: params,
          url: 'api/sf/checkDoCollection',
        }).then(result => {
          console.log(result.data.data)
          if (result.data.data == '1') {
            this.doCollectionFlag = true
          }
        }).catch(error => {
          this.$message.error("网络异常");
        })

      },
      doCollection(){
        if(this.nickname == ''){
          this.$message.error("请登录后操作");
          return ;
        }
        var params = new URLSearchParams();
        params.append('nickname', this.$store.state.user.nickname);
        params.append('sid', this.sf.id);
        this.axios({
          method: 'post',
          data: params,
          url: 'api/sf/doCollection',
        }).then(result => {
          this.$message.success("OK");
          this.doCollectionFlag = true;
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },
      deCollection(){
        if(this.nickname == ''){
          this.$message.error("请登录后操作");
          return ;
        }
        var params = new URLSearchParams();
        params.append('nickname', this.$store.state.user.nickname);
        params.append('sid', this.sf.id);
        this.axios({
          method: 'post',
          data: params,
          url: 'api/sf/deCollection',
        }).then(result => {
          this.$message.success("OK");
          this.doCollectionFlag = false;
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },

      MineOrNot(val) {
        if (val == this.nickname) {
          return true;
        } else {
          return false;
        }

      },
      doRate: function () {

        var params = new URLSearchParams();
        params.append('score', this.sf.score);
        params.append('id', this.sf.id);
        if (this.isLogin == false) {
          params.append('nickname', new Date());
        } else {
          params.append('nickname', this.$store.state.user.nickname);
        }

        this.axios({
          method: 'post',
          data: params,
          url: 'api/sf/doRate',
        }).then(result => {
          if (result.data.data.code == -1) {
            this.$message.error("您已经评过分了");

          } else {
            this.allowDoRateFlag = true;
            this.$message.success("成功评分");

          }
        }).catch(error => {
          this.$message.error("网络异常");
        })


      },

      getMessage() {
        var params1 = new URLSearchParams();
        params1.append('id', this.sf.id);
        params1.append('type', 1);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/comment/getCommentByTypeAndId',
        }).then(result => {
          this.items = result.data.data;
        }).catch(error => {
        })
      },

      replyMessage(val) {
        this.message0 = '@' + val + '  ' + this.message0;
      },

      addComment: function () {
        if(!this.isLogin){
          this.$message.warning("请登录后操作");
        }else{
          if (this.message0 == '') {
            this.$message.warning("请输入相关内容");
          }

          else {

            this.comment_reviews.content = this.message0;
            this.comment_reviews.fromUid = this.$store.state.user.nickname;
            this.comment_reviews.aid = this.sf.id;
            this.comment_reviews.type = 1;
            this.comment_reviews.time = new Date();
            this.axios({
              method: 'post',
              data: this.comment_reviews,
              url: 'api/comment/addComment',
            }).then(result => {

              this.comment_reviews.id = result.data.data;/*获取插入后 数据库生成的主键*/

              this.items.push(this.comment_reviews);
              this.comment_reviews = {};
              this.message0 = '';
            }).catch(error => {
              this.$message.error("网络异常");
            })
          }
        }


      },
      delComment(id, index) {
        var params1 = new URLSearchParams();
        params1.append('id', id);
        this.axios({
          method: 'post',
          data: id,
          url: 'api/comment/delComment',
        }).then(result => {

          this.$message.success("删除成功");
          this.items.splice(index, 1);/*删除数组中指定位置的元素*/
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },

    },
    created() {
      this.allowDoRate();
      this.checkDoCollection();
      this.getMessage();

    },



  }
</script>

<style scoped>
  .bg-purple {
    background: #d3dce6;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }

  .bg-purple {
    background: #d3dce6;
  }

  .bg-purple-light {
    background: #e5e9f2;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }

  .hot {
    height: 400px;
  }

  .detail {
    height: 800px;
    padding-top: 80px;
  }

  .title {
    background-color: #409EFF;
  }

  .tag {
    background-color: grey;
  }

  .el-col {
    /*padding-top: 20px;*/
  }

  /*.main {*/
  /*  background-color: aquamarine;*/
  /*  height: 200px;*/
  /*  padding-top: 20px;*/
  /*}*/

  #main >>> img {
    max-width: 100%;

  }


</style>
