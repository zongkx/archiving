<template>


  <el-row >
    <el-col style="padding-top: 40px">
      <el-row>
        <el-col :span="2">
          <el-button icon="el-icon-caret-left" @click="back"></el-button>
        </el-col>
        <el-col :span="8">
          <el-input size="large" v-model="title" type="text" >
            <template slot="prepend">标题</template>
          </el-input>
        </el-col>
        <el-col :span="4" :offset="1">
          <el-select   v-model="selectInfo" placeholder="请选择">
            <el-option
              v-for="item in sf_types"
              :key="item.type"
              :label="item.name"
              :value="item.type">
            </el-option>
          </el-select>
        </el-col>
        <el-col :span="6" :offset="3">
          <el-button type="danger" icon="el-icon-delete"  @click="clearContent"></el-button>
          <el-button type="success" icon="el-icon-check" v-if="!this.flag" @click="modifyContent">保存</el-button>
          <el-button type="success" icon="el-icon-check" v-else  @click="addContent">添加</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="16" :offset="2">
          <el-input size="large" v-model="name" type="text" placeholder="可选" >
            <template slot="prepend">下载链接</template>
          </el-input>
        </el-col>

      </el-row>
    </el-col>


    <el-col style="padding-top: 10px">
      <el-row>
        <el-collapse v-model="activeName">
          <el-collapse-item title="正文" name="1" >
            <div style="text-align: left;width: 90%;margin: 0 auto">
              <quill-editor
                v-model="content"
                ref="myQuillEditor"
                :options="editorOption"
                @blur="onEditorBlur($event)"
                @focus="onEditorFocus($event)"
                @change="onEditorChange($event)">
              </quill-editor>
            </div>
          </el-collapse-item>
        </el-collapse>
        <el-col :span="24" style="padding-top: 20px">

        </el-col>
      </el-row>

    </el-col>
  </el-row>

</template>

<script>
  import {quillEditor} from "vue-quill-editor"; //调用编辑器
  import 'quill/dist/quill.core.css';
  import 'quill/dist/quill.snow.css';
  import 'quill/dist/quill.bubble.css';

  export default {
    name: "admin",
    comments: {
      quillEditor
    },
    data() {
      return {
        editorOption: {
          placeholder: '请输入文本...',
          modules: {
            toolbar: [
              ['bold', 'italic', 'underline'],        // 切换按钮
              ['blockquote', 'code-block'],
              [{'header': 1}, {'header': 2}],               // 自定义按钮值
              [{'list': 'ordered'}, {'list': 'bullet'}],
              [{ 'script': 'sub'}, { 'script': 'super' }],      // 上标/下标
              [{'indent': '-1'}, {'indent': '+1'}],          // 缩进
              [{ 'direction': 'rtl' }],                         // 文本方向
              [{'size': ['small', false, 'large', 'huge']}],  // 自定义下拉
              [{'header': [1, 2, 3, 4, 5, 6, false]}],
              [{'color': []}, {'background': []}],          // 具有主题默认值的下拉列表
              [{'font': []}],
              [{'align': []}],
              ['clean'],
              ['image'],
            ]
          }
        },
        activeName: '1',
        selectInfo:'',
        content: '',//JSON.parse(this.$store.state.software).text,
        sf_types: '',
        title:'',//JSON.parse(this.$store.state.software).title,
        flag:true,
      }
    },
    methods: {
      handleClick(tab, event) {
      },
      onEditorReady(editor) { // 准备编辑器
      },
      onEditorBlur() {
      }, // 失去焦点事件
      onEditorFocus() {
      }, // 获得焦点事件
      onEditorChange() {
      }, // 内容改变事件
      clearContent() {
        this.content = '';
        this.title =  '';
      },
      modifyContent(){
        if(this.content == ''){
          this.$message.error("标题与正文不能为空");
        }else{
          if(  this.title == ''){
            this.$message.error("标题与正文不能为空");
          }else{
            var software ={
              id:JSON.parse(this.$store.state.software).id,
              title:'',
              text:'',
              type:'',
              userId:this.$store.state.user.id,
              photo:'',
              name:'',
            }
            software.text = this.content;
            software.type = this.selectInfo;
            software.title = this.title;
            software.name = this.name;

            this.axios({
              method: 'post',
              data: software,
              url: 'api/software/modifySoftware',
            }).then(result => {
              this.$message.success("成功");
              this.$store.commit('software','');
              this.$router.go(-1)
            }).catch(error => {
              this.$message.error("网络异常");
            })
          }
        }



      },


      addContent() {
        if(this.content == ''){
          this.$message.error("标题与正文不能为空");
        }else{
          if(  this.title == ''){
            this.$message.error("标题与正文不能为空");
          }else{
            var software ={
              title:'',
              text:'',
              type:'',
              userId:this.$store.state.user.id,
              photo:'',
              name:'',
            }
            software.text = this.content;
            software.type = this.selectInfo;
            software.title = this.title;
            software.name = this.name;

            this.axios({
              method: 'post',
              data: software,
              url: 'api/software/addSoftware',
            }).then(result => {
              this.$message.success("成功");
              this.$store.commit('software','');
              this.$router.go(-1)
            }).catch(error => {
              this.$message.error("网络异常");
            })
          }
        }




      },
      getSfType: function () {
        this.axios({
          method: 'post',
          data: '',
          url: 'api/software/getSoftwareAllType',
        }).then(result => {
          this.sf_types = result.data.data;
        }).catch(error => {
          this.$message.error("网络异常");
        })
      },
      back:function () {
        this.$store.commit('software','');
        this.$router.go(-1)
      },

      init(){
        if( this.$store.state.software !=''){
          this.selectInfo = JSON.parse(this.$store.state.software).type;
          this.content=JSON.parse(this.$store.state.software).text;
          this.title=JSON.parse(this.$store.state.software).title;
          this.name=JSON.parse(this.$store.state.software).name;

          this.flag = false;
        }
      },
    },

    computed: {
      editor() {
        return this.$refs.myQuillEditor.quill;
      },
    },
    created() {
      this.init();
      this.getSfType();

    },
  }
</script>

<style>
  .quill-editor {
    line-height: normal !important;
    height: 700px;
  }

  .ql-snow .ql-tooltip[data-mode=link]::before {
    content: "请输入链接地址:";
  }

  .ql-snow .ql-tooltip.ql-editing a.ql-action::after {
    border-right: 0px;
    content: '保存';
    padding-right: 0px;
  }



  .ql-snow .ql-picker.ql-size .ql-picker-label::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item::before {
    content: '14px';
  }

  .ql-snow .ql-picker.ql-size .ql-picker-label[data-value=small]::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item[data-value=small]::before {
    content: '10px';
  }

  .ql-snow .ql-picker.ql-size .ql-picker-label[data-value=large]::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item[data-value=large]::before {
    content: '18px';
  }

  .ql-snow .ql-picker.ql-size .ql-picker-label[data-value=huge]::before,
  .ql-snow .ql-picker.ql-size .ql-picker-item[data-value=huge]::before {
    content: '32px';
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item::before {
    content: '文本';
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="1"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="1"]::before {
    content: '标题1';
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="2"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="2"]::before {
    content: '标题2';
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="3"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="3"]::before {
    content: '标题3';
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="4"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="4"]::before {
    content: '标题4';
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="5"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="5"]::before {
    content: '标题5';
  }

  .ql-snow .ql-picker.ql-header .ql-picker-label[data-value="6"]::before,
  .ql-snow .ql-picker.ql-header .ql-picker-item[data-value="6"]::before {
    content: '标题6';
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item::before {
    content: '标准字体';
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value=serif]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value=serif]::before {
    content: '衬线字体';
  }

  .ql-snow .ql-picker.ql-font .ql-picker-label[data-value=monospace]::before,
  .ql-snow .ql-picker.ql-font .ql-picker-item[data-value=monospace]::before {
    content: '等宽字体';
  }

  .ql-align-center {
    text-align: center;
  }

  .ql-align-right {
    text-align: right;
  }

  .ql-align-left {
    text-align: left;
  }
</style>

