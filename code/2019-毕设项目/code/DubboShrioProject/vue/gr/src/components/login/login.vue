<template>
    <div id="content">
      <h1 style="color: #409EFF">Welcome</h1>
      <el-form ref="form" :model="form" :rules="rules">
        <el-row type="flex" justify="center">
          <el-col :span="5">
            <el-form-item prop="nickname">
              <el-input v-model.trim="form.nickname" size="large">
                <template slot="prepend">账号</template>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" justify="center">
          <el-col :span="5">
            <el-form-item prop="pswd">
              <el-input v-model.trim="form.pswd" size="large" type="password"  >
                <template slot="prepend">密码</template>
              </el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row type="flex" justify="center">
          <el-col :span="5">
            <el-form-item>
              <el-button type="primary" @click="submitForm('form')"  @keyup.enter.native="submitForm">登录</el-button>
              <el-button @click="ValueReset">重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <Register></Register>

    </div>



</template>

<script>
  import Register from "./register";
  export default {
    name: "login",
    components: {Register},
    data() {
      return {
        form: {
          nickname: 'test',
          pswd: '123456',
        },
        rules: {
          nickname: [
            {
              required: true,
              message: '账户不能为空',
              trigger: 'blur'
            },
          ],
          pswd: [
            {
              required: true,
              message: '密码不能为空',
              trigger: 'blur'
            },
          ]
        }
      }
    },
    methods: {
      /*提交进行判断的函数 */
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.axios({
              method: 'post',
              url: 'api/base/login',
              data: this.form
            }).then(result => {
              if(result.data.code == 1){
                this.$store.dispatch('isLogin');
                this.$store.commit('user',result.data.data);
                this.getUnreadMessage(result.data.data.nickname);
                this.$router.push('/share');
              }
              else{
                this.$message.error(result.data.msg);
              }
            }).catch(error => {
              this.$message.error("网络异常");
            })
          } else {
            return false;
          }
        })
      },
      ValueReset: function () {
        this.form.nickname = '';
        this.form.pswd = '';
      },
      getUnreadMessage(val) {
        var params1 = new URLSearchParams();
        params1.append('nickname', val);
        this.axios({
          method: 'post',
          data: params1,
          url: 'api/comment/getUnreadMessageCount',
        }).then(result => {
          this.$store.commit("num",result.data.data)
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },
    },
  }
</script>

<style scoped>
  #content {
    text-align: center;
    padding-top: 10%;
    min-width: 400px;
  }
</style>
