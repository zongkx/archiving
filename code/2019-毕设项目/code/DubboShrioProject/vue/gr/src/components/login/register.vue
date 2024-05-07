<template>

  <div>

    <div>
      <el-button type="text" @click="toRegister" style="color: blue">还没有账号？去注册</el-button>
      <el-dialog title="注册" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form" :rules="rules" ref="RegisterForm">
          <el-form-item prop="nickname">
            <el-tooltip class="item" effect="dark" content="唯一登录名" placement="bottom-start">
              <el-input v-model.trim="form.nickname" size="large">
                <template slot="prepend">登录账号</template>
              </el-input>
            </el-tooltip>

          </el-form-item>
          <el-form-item prop="pswd">
            <el-tooltip class="item" effect="dark" content="登录密码" placement="bottom-start">
              <el-input v-model.trim="form.pswd" size="large" type="password">
                <template slot="prepend">登录密码</template>
              </el-input>
            </el-tooltip>
          </el-form-item>
          <el-form-item prop="confirmPswd">
            <el-tooltip class="item" effect="dark" content="请确认密码" placement="bottom-start">
              <el-input v-model.trim="form.confirmPswd" size="large" type="password">
                <template slot="prepend">确认密码</template>
              </el-input>
            </el-tooltip>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="register('RegisterForm')">确 定</el-button>
        </div>
      </el-dialog>
    </div>


  </div>


</template>

<script>
  export default {
    name: "register",
    data() {
      const validateAccount = (rule, value, callback) => {

        if (value === '') {
          callback(new Error('请输入账户'));
        } else {
          callback();
        }
      };

      const validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.form.confirmPswd !== '') {
            this.$refs.RegisterForm.validateField('confirmPswd');
          }
          callback();
        }
      };
      const validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.form.pswd) {
          callback(new Error('两次输入密码不一致'));
        } else {
          callback();
        }
      };
      return {
        dialogFormVisible: false,
        form: {
          nickname: '',
          pswd: '',
          confirmPswd: ''
        },
        rules: {
          pswd: [
            {validator: validatePass, trigger: 'blur'}
          ],
          confirmPswd: [
            {validator: validatePass2, trigger: 'blur'}
          ],
          nickname: [
            {validator: validateAccount, trigger: 'blur'}
          ]
        }
      }

    },
    methods: {
      register(formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            this.axios({
              method: 'post',
              url: 'api/base/registUser',
              data: this.form
            }).then(result => {
              if (result.data.code == 1) {
                this.$message.success("成功");
                this.dialogFormVisible = false;

              } else {
                this.$message.error(result.data.msg);
              }

            }).catch(error => {
              this.$message.error("网络异常");
            })
          } else {
            return false
          }
        })
      },
      toRegister: function () {
        this.form.nickname = ''
        this.form.pswd = ''
        this.form.confirmPswd = ''
        this.dialogFormVisible = true
      }
    }


  }
</script>

<style scoped>

</style>
