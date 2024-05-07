<template>
  <el-row>

    <el-card style="width: 100%;text-align: left" shadow="hover"
             :style="{backgroundColor: MineOrNot(item.userId)  ? 'rgba(103,194,58,.1)' : '#fdfffd'}">
      <el-col :span="24" :offset="0" :style="{ paddingBottom:'100px'}">
        <!--动态头部-->
        <el-row>
          <el-col :span="4" style="padding-top: 10px">
            <el-tag class="el-icon-location"> {{item.userId}}</el-tag>
          </el-col>
          <el-col :span="6" :offset="14" style="padding-top: 10px">
            <el-tag type="info" size="primary"> {{item.datetime}}</el-tag>
            <el-tag v-show="MineOrNot(item.userId)" type="success" size="primary">Mine</el-tag>
          </el-col>
        </el-row>
        <!--分割线-->
        <el-row style="padding-top: 10px;padding-bottom: 10px">
          <hr color="lightgrey">
        </el-row>
        <!--动态正文-->
        <el-card shadow="never">
          <!---->
          <el-row style="height: 80px">
            <el-col :span="24" style="font-weight: bold;font-size: 18px">
              <div v-html="item.text"></div>
            </el-col>

          </el-row>

        </el-card>
        <!--动态尾部-->
        <el-row>
          <el-row style="padding-top: 15px">
            <el-col :span="2" :offset="0">
              <el-button
                size="mini"
                icon="el-icon-location"
                @click="likeIt(item.id,item.likenum)"
              >点赞:{{item.likenum}}
              </el-button>
            </el-col>
          </el-row>
          <el-row><!--默认隐藏的评论内容-->
            <el-col :span="24">
              <div style="padding-top: 30px; ">
                <el-row v-for="(comment,index) in comments" :key="index" :span="24"
                        style="padding-top: 10px"><!--评论 遍历数组的数组-->
                  <el-row>
                    <el-col :span="1" :offset="0">
                      <el-tag class="el-icon-view" size="mini" type="info">&ensp;{{comment.fromUid}}:</el-tag>
                    </el-col>
                    <el-col :span="4" :offset="18">
                      <el-dropdown trigger="click">
                                <span class="el-dropdown-link">
                                  <el-date-picker style="width: 130px"
                                                  v-model="comment.time"
                                                  type="date" readonly align="right"
                                                  size="mini"
                                  >
                                   </el-date-picker>
                                 </span>
                        <el-dropdown-menu slot="dropdown">
                          <el-dropdown-item>
                            <el-button type="info"
                                       size="mini"
                                       icon="el-icon-message"
                                       @click="replyComment(comment.fromUid,index)">留言
                            </el-button>
                          </el-dropdown-item>
                          <el-dropdown-item>
                            <el-button v-show="MineOrNot(comment.fromUid)"
                                       type="danger"
                                       size="mini"
                                       icon="el-icon-delete"
                                       @click="delComment(comment.id,index)">删除
                            </el-button>
                          </el-dropdown-item>
                        </el-dropdown-menu>
                      </el-dropdown>
                    </el-col>


                  </el-row>
                  <el-row style="padding-top: 10px;padding-left: 0px">
                    <el-col :span="24" :offset="0"
                            style="border-bottom: 1px solid lightgrey;border-radius: unset;font-size: 14px">
                      &ensp;&ensp;{{comment.content}}

                    </el-col>

                  </el-row>

                </el-row>

                <el-row style="padding-top:40px">
                  <el-col :span="14" :offset="1">
                    <el-input size="small"
                              v-model="edit" placeholder="说点什么吧"></el-input>
                  </el-col>
                  <el-col :span="2" :offset="1">
                    <el-button
                      size="small"
                      icon="el-icon-delete"
                      @click="reset_Comment()"
                    ><!--传递所属动态ID / comments数组对应的index值 -->
                    </el-button>
                  </el-col>
                  <el-col :span="2">
                    <el-button
                      size="small"
                      icon="el-icon-upload2"
                      @click="addComment(item.id)"
                    ><!--传递所属动态ID / comments数组对应的index值 -->
                    </el-button>
                  </el-col>

                </el-row>
              </div>
            </el-col>
          </el-row>

        </el-row>


      </el-col>

    </el-card>
  </el-row>

</template>

<script>
  export default {
    name: "userDetail",
    data() {
      return {
        aid: this.$store.state.aid,
        item: '',
        comments: '',
        edit: '',
      }
    },
    methods: {
      init() {
        var params1 = new URLSearchParams();
        params1.append('aid', this.aid);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/share/getShareById',
        }).then(result => {
          this.item = result.data.data;
        }).catch(error => {
          this.$message.error("网络异常");
        })


      },
      addComment(id) {
        if (this.edit == '') {
          this.$message.warning("请输入相关内容");
        } else {
          var obj = {};
          obj.type = '2';
          obj.aid = id;
          obj.time = new Date();
          obj.content = this.edit;
          obj.fromUid = this.$store.state.user.nickname;
          obj.flag = '0'

          this.axios({
            method: 'post',
            data: obj,
            url: 'api/comment/addComment',
          }).then(result => {
            obj.id = result.data.data;
            this.comments.push(obj);
          }).catch(error => {
            this.$message.error("网络异常");
          })
        }

      },
      MineOrNot(val) {
        if (val === this.$store.state.user.nickname) {
          return true;
        } else {
          return false;
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
          this.comments.splice(index, 1);/*删除数组中指定位置的元素*/

        }).catch(error => {
          this.$message.error("网络异常");
        })


      },
      replyComment(fromUid) {
        this.edit = "@" + fromUid + "    ";
      },
      reset_Comment() {
        this.edit = '';
      },
      likeIt(itemId, likenum) {
        var params1 = new URLSearchParams();
        params1.append('itemId', itemId);
        params1.append('likenum', likenum);
        params1.append('nickname', this.$store.state.user.nickname);

        this.axios({
          method: 'post',
          data: params1,
          url: 'api/share/likeIt',
        }).then(result => {
          if (result.data.code == '1') {
            this.$message.success("成功");
            this.item.likenum = likenum + 1;
          } else {
            this.$message.info("你已经点赞过了");
          }
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },
      getMessage() {
        var params1 = new URLSearchParams();
        params1.append('id', this.aid);
        params1.append('type', 2);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/comment/getCommentByTypeAndId',
        }).then(result => {
          this.comments = result.data.data;//通过index将获取到的评论信息 设置到comments数组的指定位置
        }).catch(error => {
        })
      },

    },
    created() {
      this.init();
      this.getMessage();
    }
  }
</script>

<style scoped>

</style>
