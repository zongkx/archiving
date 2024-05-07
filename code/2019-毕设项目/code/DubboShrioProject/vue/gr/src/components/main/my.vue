<template>
  <div id="div1">
    <el-row>
      <el-col :span="24">
        <el-collapse v-model="activeNames">
          <el-collapse-item title="新增动态" name="1">
            <el-row type="flex" justify="center">
              <el-col :span="14">
                <el-input
                  type="textarea"
                  :rows="6"
                  placeholder="分享你此刻的想法"
                  v-model="edit_text"
                  aria-required="true"
                  require>
                </el-input>
              </el-col>
            </el-row>
            <el-row type="flex" justify="right">
              <el-col :span="12" :offset="11">
                <el-button icon="el-icon-delete" @click="reset"></el-button>
                <el-button icon="el-icon-circle-check" @click="add"></el-button>
              </el-col>
            </el-row>

          </el-collapse-item>

          <el-collapse-item title="我的动态" name="2">
            <!--循环输出动态-->
            <el-row type="flex" style="padding-top: 20px"
                    class="row-bg" justify="left" v-for="(item,index) in items" :key="index">
              <el-card style="width: 100%;border-color: lightgrey" shadow="hover">
                <el-col :span="24" :offset="0" :style="{ paddingBottom:'100px'}">
                  <!--动态头部-->
                  <el-row>
                    <el-col :span="3">
                      <el-tag size="primary">{{new Date(item.datetime).Format("yyyy-MM-dd")}}</el-tag>
                    </el-col>
                    <el-col :span="2" :offset="18">
                      <el-button icon="el-icon-delete" size="mini" type="danger"
                                 @click="deleteShare(item.id,index)"></el-button>
                    </el-col>
                  </el-row>
                  <!--分割线-->
                  <el-row style="padding-top: 10px">
                    <hr color="lightgrey">
                  </el-row>
                  <!--动态正文-->
                  <el-card shadow="never" style="border: none;text-align: left">
                    <el-row style="height: 80px">
                      <el-col :span="24" style="font-weight: bold;font-size: 18px">
                        <div v-html="item.text"></div>
                      </el-col>

                    </el-row>

                  </el-card>
                  <!--动态尾部-->
                  <el-row>
                    <el-row>
                      <el-col :span="1">
                        <!--给按钮绑定点击事件-->
                        <el-button v-bind:id="'btn_'+item.id" v-if="!comment_flag[index]"
                                   size="mini" icon="el-icon-news" type="primary"
                                   @click="show_comment(item.id,index)"> 评论
                        </el-button>
                        <el-button
                          size="mini" icon="el-icon-news"
                          v-else @click="hide_comment(item.id,index)">收起
                        </el-button>

                      </el-col>
                      <el-col :span="2" :offset="3">
                        <el-button
                          size="mini"
                          icon="el-icon-location"
                          @click="likeIt(item.id,item.likenum,index)"
                        >点赞:{{item.likenum}}
                        </el-button>
                      </el-col>
                    </el-row>
                    <el-row>
                      <el-col :span="24">
                        <div v-show="comment_flag[index]" v-bind:id="'div_'+item.id"
                             style="padding-top: 20px; ">
                          <el-row v-for="(comment,index1) in comments[index]" :key="index1" :span="24"
                          style="padding-top: 10px"><!--评论 遍历数组的数组-->
                            <el-row>
                              <el-col :span="1" :offset="0">
                                <el-tag class="el-icon-view" size="mini" type="success">
                                  &ensp;{{comment.fromUid}}: &ensp;
                                </el-tag>
                              </el-col>
                              <el-col :span="4" :offset="18">
                                <el-dropdown trigger="click">
                                  <span class="el-dropdown-link">
                                     {{new Date(comment.time).Format("MM-dd  hh:mm")}}
                                    <i class="el-icon-more"> </i>
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
                                      <el-button type="danger"
                                                 size="mini"
                                                 icon="el-icon-delete"
                                                 @click="delComment(comment.id,index1,index)">删除
                                      </el-button>
                                    </el-dropdown-item>
                                  </el-dropdown-menu>
                                </el-dropdown>
                              </el-col>


                            </el-row>

                            <el-row style="padding-top: 10px; text-align: left">
                              <el-col :span="24" :offset="0"
                                      style="border-bottom: 1px solid lightgrey;border-radius: unset;font-size: 14px">
                                &ensp;&ensp;&ensp;&ensp;{{comment.content}}

                              </el-col>

                            </el-row>


                          </el-row>

                          <el-row style="padding-top: 40px">
                            <el-col :span="14" :offset="1">
                              <el-input size="small"
                                        v-model="comment_edit_content[index]" placeholder="说点什么吧"></el-input>
                            </el-col>
                            <el-col :span="2">
                              <el-button
                                size="small"
                                icon="el-icon-delete"
                                @click="reset_Comment(index)"
                              ><!--传递所属动态ID / comments数组对应的index值 -->
                              </el-button>
                            </el-col>
                            <el-col :span="1">
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

          </el-collapse-item>



        </el-collapse>
      </el-col>


    </el-row>

  </div>
</template>

<script>

  export default {
    name: "my",
    data() {
      return {
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
        },
        activeNames: ['1'],
        activeNames2: ['1'],
        nickname: this.$store.state.user.nickname,
        edit_text: '',
        share: {
          title: '',
          datatime: '',
          userId: this.$store.state.user.nickname,
          text: '',
          likenum: 0,
          softwareId: '',
          pic: '',
        },
        from_nickname: '',
        to_nickname: '',
        items: '',

        offset: 1,//默认在第一页

        limit: 5,//默认每页显示5条数据

        totalCount: 1,//默认总条数为1条,

        myInfos:'',
      }

    },
    methods: {
      add: function () {
        var shareObj = {
          title: '',
          datetime: this.getNowTime(),
          userId: this.$store.state.user.nickname,
          text: this.edit_text,
          likenum: 0,
          softwareId: '',
          pic: '',
        };
        this.axios({
          method: 'post',
          data: shareObj,
          url: 'api/share/addShare',
        }).then(result => {
          this.items.unshift(result.data.data);
          this.edit_text = '';
          this.totalCount++;
          this.$message.success("添加成功");
        }).catch(error => {
          this.$message.error("网络异常");
        })
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
          // this.comments[index].splice(0);
          this.totalCount--;
          this.$message.success("删除成功");
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },
      reset: function () {
        this.edit_text = '';
      },
      getItems: function () {
        var params1 = new URLSearchParams();
        params1.append('offset', this.offset);
        params1.append('limit', this.limit);
        params1.append('userId', this.share.userId);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/share/getSharePageByUserId',
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
      getNowTime() {
        return new Date();
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
        console.log(id);
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
        console.log(fromUid + index);
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
        params1.append('nickname', this.nickname);
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
      getMyInfo:function () {
        var params1 = new URLSearchParams();
        params1.append('userId', this.nickname);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/share/getMyInfoByCommentAid',
        }).then(result => {


          //  this.$message.success("成功");
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },


    },
    created() {
      this.getItems();
      this.getMyInfo();
    },
    mounted() {
    }

  }
</script>

<style scoped>
  * {
    border-radius: 6px;
  }
</style>
