<template>

  <div>
    <el-col :span="24">
      <el-menu style=" height:900px"
        default-active="activeIndex"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        background-color="#545c64"
        @select="handleSelect"
        text-color="#fff"
        active-text-color="#ffd04b">

        <el-menu-item index="/main" class="nav"><i class="el-icon-view"></i>管理员首页</el-menu-item>
        <el-submenu index="1" class="nav" v-show="this.flag[0] || this.flag[1] || this.flag[2]">
          <template slot="title" >
            <i class="el-icon-location"></i>
            <span>信息管理</span>
          </template>
          <el-menu-item-group >
            <el-menu-item index="/user" v-show="this.flag[0]"><i class="el-icon-menu"></i> 用户管理</el-menu-item>
            <el-menu-item index="/role" v-show="this.flag[1]"><i class="el-icon-mobile-phone"></i>角色管理</el-menu-item>
            <el-menu-item index="/perm" v-show="this.flag[2]"><i class="el-icon-mobile-phone"></i>权限管理</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2" class="nav">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>内容管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/software"v-show="this.flag[3]"><i class="el-icon-menu"></i> 文章管理</el-menu-item>
            <el-menu-item index="/share" v-show="this.flag[4]"><i class="el-icon-mobile-phone"></i>动态管理</el-menu-item>
            <el-menu-item index="/comment"v-show="this.flag[5]" ><i class="el-icon-mobile-phone"></i>评论管理</el-menu-item>
          </el-menu-item-group>
        </el-submenu>


      </el-menu>
    </el-col>
  </div>
</template>

<script>
  export default {
    name: "HeaderNav",
    inject: ['reload'],
    data() {
      return {
        activeIndex: '/main',
        isLogin: this.$store.state.isLogin,
        perms: this.$store.state.user.rolePerms,
        flag:[],

      };
    },
    methods: {
      handleOpen(key, keyPath) {
      },
      handleClose(key, keyPath) {
      },
      handleSelect(key, keyPath) {
        if (key == 'login') {
          this.$router.push("/");
        }
        this.$router.push(key);
        this.reload();
      },
      checkMenu(){
        var params1 = new URLSearchParams();
        var str = '/user,/role,/perm,/software,/share,/comment';
        params1.append('perm', str);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/menu/check',
        }).then(result => {
          for(var i = 0 ;i<result.data.data.length;i++){
            this.flag.push(result.data.data[i])
          }
        }).catch(error => {
          this.$message.error("网络异常");
        })


      },
    },
    mounted() {
    },
    created() {
      if (!this.$store.state.isLogin) {
        this.$message.info("您还没有登录,请登录后重试");
        this.$router.push("/");
      }
       this.checkMenu();
    }


  }
</script>

<style scoped>
  .nav {
    text-align: left
  }


</style>
