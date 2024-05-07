<template>

  <el-row style="">
    <!--循环输出动态-->
    <el-row type="flex" class="row-bg" justify="left"
            v-for="(item,index) in items" :key="index" style="padding-top: 20px">
      <el-card style="width: 100%;text-align: left" shadow="hover"
                :style="{backgroundColor: MineOrNot(item.userId)  ? 'rgba(103,194,58,.1)' : '#fdfffd'}" >
        <el-col :span="24" :offset="0" :style="{ paddingBottom:'100px'}">
          <!--动态头部-->
          <el-row>
            <el-col :span="4" style="padding-top: 10px">
              <el-tag class="el-icon-location"> {{item.userId}} </el-tag>
            </el-col>
            <el-col :span="6" :offset="14" style="padding-top: 10px">
              <el-tag type="info" size="primary">{{new Date(item.datetime).Format("yyyy-MM-dd")}}</el-tag>
              <el-tag v-show="MineOrNot(item.userId)" type="success" size="primary">Mine</el-tag>
              <el-button v-show="MineOrNot(item.userId)" icon="el-icon-delete" size="small" type="danger"
                         @click="deleteShare(item.id,index)"></el-button>
            </el-col>

          </el-row>
          <!--分割线-->
          <el-row style="padding-top: 10px;padding-bottom: 10px">
              <hr color="lightgrey"  >
          </el-row>
          <!--动态正文-->
          <el-card shadow="never" >
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
              <el-col :span="1">
                <!--给按钮绑定点击事件-->
                <el-button v-bind:id="'btn_'+item.id" v-if="!comment_flag[index]"
                           size="mini" icon="el-icon-news" type="primary"
                           @click="show_comment(item.id,index)"> 评论
                </el-button>
                <el-button
                  size="mini" icon="el-icon-news" type="primary"
                  v-else @click="hide_comment(item.id,index)">收起
                </el-button>

              </el-col>
              <el-col :span="2" :offset="2">
                <el-button
                  size="mini"
                  icon="el-icon-location"
                  @click="likeIt(item.id,item.likenum,index)"
                >点赞:{{item.likenum}}
                </el-button>
              </el-col>
            </el-row>
            <el-row><!--默认隐藏的评论内容-->
              <el-col :span="24">
                <div v-show="comment_flag[index]" v-bind:id="'div_'+item.id"
                     style="padding-top: 30px; ">
                  <el-row v-for="(comment,index1) in comments[index]" :key="index1" :span="24"
                          style="padding-top: 10px"><!--评论 遍历数组的数组-->
                    <el-row >
                      <el-col :span="1" :offset="0">
                        <el-tag class="el-icon-view" size="mini" type="info">&ensp;{{comment.fromUid}}:</el-tag>
                      </el-col>
                      <el-col :span="4" :offset="18"  >
                        <el-dropdown trigger="click">
                                <span class="el-dropdown-link">

                                  {{new Date(comment.time).Format("MM-dd  hh:mm")}}
                                  <i class="el-icon-more"> </i>
                                 </span>
                          <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item  >
                              <el-button type="info"
                                         size="mini"
                                         icon="el-icon-message"
                                         @click="replyComment(comment.fromUid,index)">留言
                              </el-button>
                            </el-dropdown-item>
                            <el-dropdown-item  >
                              <el-button v-if="MineOrNot(comment.fromUid)"
                                          type="danger"
                                         size="mini"
                                         icon="el-icon-delete"
                                         @click="delComment(comment.id,index1,index)">删除
                              </el-button>
                            </el-dropdown-item>
                          </el-dropdown-menu>
                        </el-dropdown>
                      </el-col>


                    </el-row>
                    <el-row  style="padding-top: 10px;padding-left: 0px">
                      <el-col :span="24" :offset="0"
                              style="border-bottom: 1px solid lightgrey;border-radius: unset;font-size: 14px">
                          &ensp;&ensp;{{comment.content}}

                      </el-col>

                    </el-row>

                  </el-row>

                  <el-row style="padding-top:40px" >
                    <el-col :span="14" :offset="1">
                      <el-input size="small"
                                v-model="comment_edit_content[index]" placeholder="说点什么吧"></el-input>
                    </el-col>
                    <el-col :span="2" :offset="1">
                      <el-button
                        size="small"
                        icon="el-icon-delete"
                        @click="reset_Comment(index)"
                      ><!--传递所属动态ID / comments数组对应的index值 -->
                      </el-button>
                    </el-col>
                    <el-col :span="2">
                      <el-button
                        size="small"
                        icon="el-icon-upload2"
                        @click="addComment(item.id,index)"
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


    <el-row type="flex" class="row-bg" justify="left" style="padding-top: 10px">
      <el-col :span="24" class="toolbar pageBar">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="offset"
          :page-sizes="[5,10,20]"
          :page-size="limit"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalCount"
        >
        </el-pagination>
      </el-col>

    </el-row>

  </el-row>


</template>

<script>
  export default {
    name: "square",
    data() {
      return {

        nickname: this.$store.state.user.nickname,

        items: '',/*存放动态的数组*/

        offset: 1,//默认在第一页

        limit: 5,//默认每页显示5条数据

        totalCount: 1,//默认总条数为1条

        backgroundColor: '',

        comment_flag: [false, false, false, false, false],/*默认设置所有的评论内容不显示,通过for循环的index值作为下标获取该值*/
        comments: ['', '', '', '', ''],
        comment_edit_content: ['', '', '', '', ''],
        comment_reviews: {
          content: '',
          toUid: '',
          type: '2',
          aid: '',
          fromUid: '',
          time: '',
          flag:0,
        },
        share: {
          title: '',
          datatime: '',
          userId: this.$store.state.user.nickname,
          text: '',
          likenum: 0,
          softwareId: '',
          pic: '',

        },

      }
    },

    methods: {
      getItems: function () {
        var params1 = new URLSearchParams();
        params1.append('offset', this.offset);
        params1.append('limit', this.limit);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/share/getSharePage',
        }).then(result => {
          this.items = result.data.rows;
          this.totalCount = result.data.total;
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },
      //操作每页显示几条
      handleSizeChange(val) {
        this.limit = val;//获取page-sizes里的每页显示几条数据的值，赋给我们自定义的每页显示数量的变量pageNum
        this.getItems();//展示页面信息
      },
      // 操作显示第几页
      handleCurrentChange(val) {
        this.offset = val;
        this.getItems();//确定当前页面后刷新页面
        this.comment_flag = [false, false, false, false, false];
        this.comments = ['', '', '', '', ''];
      },
      MineOrNot(val) {
        if (val === this.nickname) {
          return true;
        } else {
          return false;
        }

      },
      deleteShare(val, index) {
        var params1 = new URLSearchParams();
        params1.append('id', val);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/share/deleteShareById',
        }).then(result => {
          this.items.splice(index, 1);
           // this.comments[index] ={};
          this.totalCount--;
          this.$message.success("删除成功");
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },
      getMessage(val, index) {
        var params1 = new URLSearchParams();
        params1.append('id', val);
        params1.append('type', 2);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/comment/getCommentByTypeAndId',
        }).then(result => {
          this.$set(this.comments, index, result.data.data);//通过index将获取到的评论信息 设置到comments数组的指定位置
        }).catch(error => {
        })
      },
      getNowTime() {
        return new Date();
      },
      show_comment(val, index) {
        this.getMessage(val, index);//根据id获取评论,并将 index传递过去
        this.$set(this.comment_flag, index, true);
      },
      hide_comment(val, index) {
        this.$set(this.comment_flag, index, false);
      },
      addComment(id, index) {
        if (this.comment_edit_content[index] == '') {
          this.$message.warning("请输入相关内容");
        } else {
          /*往 comment 对象 内添加数据*/
          this.comment_reviews.aid = id;
          this.comment_reviews.type = '2';
          this.comment_reviews.content = this.comment_edit_content[index];
          this.comment_reviews.time = this.getNowTime();
          this.comment_reviews.fromUid = this.$store.state.user.nickname;

          /* 往  数组中 添加当前对象*/
          this.comments[index].push(this.comment_reviews);

          this.axios({
            method: 'post',
            data: this.comment_reviews,
            url: 'api/comment/addComment',
          }).then(result => {
            console.log(result.data.data);
            this.comment_reviews.id = result.data.data;/*获取插入后 数据库生成的主键*/
            this.$set(this.comment_edit_content, index, "");

            this.comment_reviews = {};
          }).catch(error => {
            this.$message.error("网络异常");
          })
        }

      },
      delComment(id, index1, index) {
        this.comments[index].splice(index1, 1);/*删除数组中指定位置的元素*/
        var params1 = new URLSearchParams();
        params1.append('id', id);
        this.axios({
          method: 'post',
          data: id,
          url: 'api/comment/delComment',
        }).then(result => {
          this.$message.success("删除成功");
        }).catch(error => {
          this.$message.error("网络异常");
        })


      },
      replyComment(fromUid, index) {
        this.$set(this.comment_edit_content, index, "@" + fromUid + "    ");
        this.focusState = true
      },
      reset_Comment(index) {
        this.$set(this.comment_edit_content, index, "");

      },
      likeIt(itemId,likenum,index){
        var params1 = new URLSearchParams();
        params1.append('itemId', itemId);
        params1.append('likenum', likenum);
        if(this.nickname == null){
          params1.append('nickname', '游客');
        }else{
          params1.append('nickname',this.nickname);
        }

        this.axios({
          method: 'post',
          data: params1,
          url: 'api/share/likeIt',
        }).then(result => {
          if(result.data.code == '1'){
            this.$message.success("成功");
            this.items[index].likenum =likenum+1;
          }else{
            this.$message.info("你已经点赞过了");
          }
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },

    },
    created() {
      this.getItems();
    },

  }


</script>

<style scoped>
  *{
    border-radius: 6px;
  }
</style>
