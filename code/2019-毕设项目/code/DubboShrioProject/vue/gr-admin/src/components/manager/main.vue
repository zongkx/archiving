<template>
  <div id="div">
    <el-row :span="24" :gutter="2">
      <el-col :span="8" class="userCol">
        <el-row type="flex" justify="left">
          <el-col>
            <el-form :model="userForm" :rules="rules" ref="userForm">
              <el-form-item label="账户："  prop='nickname'>
                <el-input v-model="userForm.nickname" size="medium" disabled  class="input"></el-input>
              </el-form-item>
              <el-form-item label="邮箱："   prop='email'>
                <el-input v-model="userForm.email" size="medium" :disabled="edit_flag" class="input"></el-input>
              </el-form-item>
              <el-form-item label="创建日期："   prop='ctrateTime'>
                <el-input v-model="userForm.createTime" disabled suffix-icon="el-icon-date" size="medium"
                          class="input"></el-input>
              </el-form-item>
              <el-form-item label="密码："  prop='pswd'>
                <el-input v-model="userForm.pswd" type="password" :disabled="edit_flag" size="medium"
                          class="input"></el-input>
              </el-form-item>
              <el-form-item label="确认密码："  prop='pswd1' v-show="pswdConfirm">
                <el-input  v-model="userForm.pswd1"  type="password" size="medium"   class="input"></el-input>
              </el-form-item>
            </el-form>
          </el-col>


        </el-row>
        <el-row :span="24" :gutter="4">
          <el-col :span="22">
            <el-button v-if="edit_flag" type="primary" icon="el-icon-edit" round @click="edit">修改</el-button>
            <el-button v-else type="primary" icon="el-icon-edit" round @click="_edit">取消修改</el-button>
            <el-button type="success" icon="el-icon-check" round @click="changeUser('userForm')" v-show="pswdConfirm">确认</el-button>
          </el-col>

        </el-row>
      </el-col>


    </el-row>


  </div>
</template>

<script>
  export default {
    name: "user",
    data() {
      var validateEmail = (rule,value,callback) =>{
        const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
        if (!value) {
          return callback(new Error('邮箱不能为空'))
        }
        setTimeout(() => {
          if (mailReg.test(value)) {
            callback()
          } else {
            callback(new Error('请输入正确的邮箱格式'))
          }
        }, 100)

      };


      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.userForm.pswd !== '') {
            this.$refs.userForm.validateField('pswd1');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.userForm.pswd) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        pswdConfirm: false,
        edit_flag: true,// 是否可修改
        pswd0: '',//修改和取消修改的临时密码值
        userForm:{
          nickname:this.$store.state.user.nickname,
          pswd:this.$store.state.user.pswd,
          createTime:this.$store.state.user.createTime,
          email:this.$store.state.user.email,
          pswd1:'',
        },
        rules: {
          pswd: [
            {validator: validatePass, trigger: 'blur'}
          ],
          pswd1: [
            {validator: validatePass2, trigger: 'blur'}
          ],
          email:[
            {validator:validateEmail,trigger:'blur'}
          ],

        }

      };


    },
    methods: {

      changeUser(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.axios({
              method: 'post',
              url: 'api/changeUser',
              data: this.userForm
            }).then(result => {
              if (result.data.code == 1) {
                this.$message.success(result.data.msg);
                this.$store.commit('user',result.data.data);
                this.pswdConfirm = false;
                this.edit_flag = true;

              } else {
                this.$message.error(result.data.data);
              }
            }).catch(error => {
              this.$message.error("网络异常");
            })
          } else {
            //验证失败执行
            console.log('error submit!!');
            return false;
          }
        })




      },
      edit: function () {
        this.edit_flag = false;
        this.pswd0 = this.userForm.pswd;
        this.userForm.pswd = '';
        this.userForm.pswd1 = '';
        this.pswdConfirm = true;
      },
      _edit: function () {
        this.edit_flag = true;
        this.pswdConfirm = false;
        this.userForm.pswd = this.pswd0;
      },


    },
  }
</script>

<style scoped>


  .input {
    float: left;
    padding: 4px;
  }

  .li {
    float: left;
    padding: 8px;
  }

  .ul {
    height: 200px;
    width: 400px;
    list-style: none;
  }
  .userCol{
    padding-top: 80px;
  }


</style>
