<template>

  <div>
    <el-row type="flex" justify="left" class="header1">
      <el-col :span="3">
        <div class="grid-content"><img src="../assets/1.jpg" id="logoImg"></div>
      </el-col>
      <el-col :span="21" :offset="10" class="header1col">
        <el-autocomplete
          v-model="queryStr"
          @select="handleSelect_search"
          :fetch-suggestions="querySearchAsync"
          placeholder="请输入内容"
          style="padding-top: 5px"
          clearable
          autosize
        >
          <el-button slot="append" icon="el-icon-search" @click="querySearch"></el-button>
        </el-autocomplete>
      </el-col>
    </el-row>


    <el-row class="header2">
      <el-col :span="24">
        <el-menu
          :default-active="activeIndex"
          class="el-menu"
          mode="horizontal"
          background-color="white"
          text-color="black"
          active-text-color="#409EFF"
          @select="handleSelect"
          max-height="30px"
        >
          <el-menu-item index="/software"><i class="el-icon-view"></i> <strong>首页</strong></el-menu-item>
          <el-menu-item index="/list1"><i class="el-icon-menu"></i> <strong>Windows</strong></el-menu-item>
          <el-menu-item index="/list2"><i class="el-icon-mobile-phone"></i><strong>Android</strong></el-menu-item>
          <el-menu-item index="/list3"><i class="el-icon-mobile-phone"></i><strong>IOS</strong></el-menu-item>
          <el-menu-item index="/square"><i class="el-icon-circle-plus-outline"></i><strong>Center</strong>
          </el-menu-item>
          <el-submenu index=" " v-if="isLogin">
            <template slot="title"><i class="el-icon-setting"></i><strong>More</strong></template>
            <el-menu-item index="/my">我的动态</el-menu-item>
            <el-menu-item index="/userCollection">我的收藏</el-menu-item>

            <el-menu-item index="/message">
              <el-badge :value="this.unReadNum" class="item">
                我的消息
              </el-badge>
            </el-menu-item>
            <el-menu-item index="/user">个人信息</el-menu-item>
             <el-submenu index="test">
                          <template slot="title">作者选项</template>
                          <el-menu-item index="/author"
                                        v-if="this.perms.find(function(e) {if(e.url == '/oneSoftware'){return true;}else{return false;}})">发布软件</el-menu-item>
                          <el-menu-item v-else index="/apply">申请成为作者</el-menu-item>
             </el-submenu>
            <el-menu-item v-if="isLogin" @click="logout" index=""> 退出登录</el-menu-item>
          </el-submenu>
          <el-menu-item index="/login" v-else><i class="el-icon-star-on"></i>登录</el-menu-item>
        </el-menu>
      </el-col>
    </el-row>


  </div>
</template>

<script>
  export default {
    name: "HeaderNav",
    inject: ['reload'],

    data() {
      return {
        activeIndex: '/software',
        activeIndex2: '1',
        objs: [],
        queryStr: '',
        state4: '',
        timeout: null,
        isLogin: this.$store.state.isLogin,

        perms: this.$store.state.user.userPerms,

        nickname: this.$store.state.user.nickname,

        name : '',
      };
    },

    computed: {
      unReadNum() {
        return this.$store.state.unReadNum;
      }
    },

    methods: {
      handleSelect(key, keyPath) {
        if (key == '/list1') {
          this.$store.commit("type", 'windows');
          this.$router.push('/list')
        } else if (key == '/list2') {
          this.$store.commit("type", 'android');
          this.$router.push('/list')

        } else if (key == '/list3') {
          this.$store.commit("type", 'ios');
          this.$router.push('/list')
        } else if (key == '/login') {
          this.$router.push('/');
        } else if(key == '/apply'){
          this.apply();
        }else{
          this.$router.push(key);
        }
        this.reload();
      },
      apply() {
        var params1 = new URLSearchParams();
        params1.append('id', this.$store.state.user.id);
        this.$confirm('确认申请为作者吗', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.axios({
            method: 'post',
            data: params1,
            url: 'api/user/apply',
          }).then(result => {
            this.$message({
              type: 'success',
              message: '申请成功!'
            });
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

      loadAll() {//获取推荐内容
      },
      handleSelect_search(item) {
      },
      querySearch() {
        if (this.$store.state.qry == '') {
          this.$store.commit('qry', this.queryStr);
          this.$router.push('/search')
        } else {
          this.$store.commit('qry', this.queryStr);
          this.$router.push('/search')
        }
      },


      querySearchAsync(queryString, cb) {
        var restaurants = this.objs;
        var results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants;
        clearTimeout(this.timeout);
        this.timeout = setTimeout(() => {
          cb(results);
        }, 1000 * Math.random());
      },

      createStateFilter(queryString) {
        return (state) => {
          return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      logout: function () {
        this.$store.dispatch('notLogin');
        this.$store.commit('user', '');
        this.$store.commit('cf', '');
        this.$router.push('/share');
        this.$router.go(0)
      },
      toLogin: function () {
        this.$router.push('/');
      },
      getRec() {
        var params1 = new URLSearchParams();
        this.axios({
          method: 'post',
          data: '',
          url: 'api/sf/getRec5',
        }).then(result => {
          for (var i = 0; i < result.data.data.length; i++) {
            this.objs.push({
              value: result.data.data[i].title,
              num: i
            })
          }
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },

    },
    mounted() {
        this.getRec();
    }


  }
</script>

<style scoped>
  .header2 {
    margin: 0 auto;
    width: 80%;
    height: 80px;
    min-width: 800px;
  }

  .header1 {
    margin: 0 auto;
    width: 80%;
    height: 80px;
  }

  .header1col {
    padding-top: 20px;
  }


  .grid-content {
    border-radius: 4px;
    min-height: 40px;
  }

  .el-menu-item {
    min-width: 60px;
  }

  strong {
    font-size: 16px;
    font-family: Arial, Verdana, Sans-serif;
  }

  #logoImg {
    max-height: 60px;
    max-width: 80px;
    padding-top: 10px;
  }
</style>
