<template>
  <div id="share">

    <el-row type="flex" justify="left" style="border-bottom: 1px gainsboro dotted" >
      <el-col :span="3">
        <div class="grid-content"><img src="../assets/1.jpg" id="logoImg"></div>
      </el-col>
      <el-col :span="1" :offset="20" style="padding-top: 20px">
        <el-button  @click="logout" index="" v-if="isLogin" ><i class="el-icon-star-on"></i>退出</el-button>
        <el-button index="/login" v-else ><i class="el-icon-star-on"></i>登录</el-button>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="4"  >
        <HeaderNav></HeaderNav>
      </el-col>
      <el-col :span="16" :offset="2">
        <div class="content">
          <router-view v-if="isRouterAlive"></router-view>
        </div>
      </el-col>

    </el-row>


  </div>


</template>

<script>
  import HeaderNav from "./nav";
  import FooterNav from "./footer";

  export default {
    name: "share",
    components: {HeaderNav, FooterNav},
    provide() {
      return {
        reload: this.reload,
      }

    },
    data() {
      return {
        isRouterAlive: true,
        isLogin:this.$store.state.isLogin,

      }

    },

    methods: {
      logout: function () {
        this.axios({
          method: 'post',
          data: '',
          url: 'api/logout',
        }).then(result => {
          this.$store.dispatch('notLogin');
          this.$store.commit('user', '');
          this.$router.replace("/");
        }).catch(error => {
          this.$message.error("网络异常,退出失败");
        })


      },
      reload: function () {
        this.isRouterAlive = false
        this.$nextTick(function () {
          this.isRouterAlive = true
        })
      },

    },created() {
      if (!this.$store.state.isLogin) {
        this.$message.info("您还没有登录,请登录后重试");
        this.$router.push("/");
      }

    }


  }
</script>

<style scoped>
  #logoImg {
    max-height: 60px;
    max-width: 80px;
  }

</style>
