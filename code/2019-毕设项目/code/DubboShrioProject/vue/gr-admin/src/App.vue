<template>
  <div id="app" :style="contentStyleObj">
    <div style="margin: 0 auto;width: 100% " >
        <router-view/>
    </div>
  </div>

</template>

<script>

  export default {
    name: 'App',
    data() {
      return {
        contentStyleObj: {
          height: '',
          width:'',
        },
      }
    },

    methods: {
      getHeightAndWidth() {
        this.contentStyleObj.height = window.innerHeight - 150 + 'px';
        this.contentStyleObj.width = window.innerWidth - 40 + 'px';
      },

    },

    created() {
      window.addEventListener('resize', this.getHeightAndWidth);
      this.getHeightAndWidth()
    },

    destroyed() {
      window.removeEventListener('resize', this.getHeightAndWidth)
    },

  }
</script>

<style>
  #app {
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    height: 100%;
  }
  /* 清除内外边距 */
  body, h1, h2, h3, h4, h5, h6, hr, p, blockquote, /* structural elements 结构元素 */
  dl, dt, dd, ul, ol, li, /* list elements 列表元素 */
  pre, /* text formatting elements 文本格式元素 */
  fieldset, lengend, button, input, textarea, /* form elements 表单元素 */
  th, td { /* table elements 表格元素 */
    margin: 0;
    padding: 0;
  }

  /* 设置默认字体 */
  body,
  button, input, select, textarea { /* for ie */
    font: 12px/1 Tahoma, Helvetica, Arial, "微软雅黑", sans-serif;
  }

  h1 {
    font-size: 18px; /* 18px / 12px = 1.5 */
  }

  h2 {
    font-size: 16px;
  }

  h3 {
    font-size: 14px;
  }

  h4, h5, h6 {
    font-size: 100%;
  }

  address, cite, dfn, em, var {
    font-style: normal;
  }

  /* 将斜体扶正 */
  code, kbd, pre, samp, tt {
    font-family: "Courier New", Courier, monospace;
  }

  /* 统一等宽字体 */
  small {
    font-size: 12px;
  }

  /* 小于 12px 的中文很难阅读，让 small 正常化 */

  /* 重置列表元素 */
  ul, ol, li {
    list-style: none;
    text-decoration: none;
  }

  /* 重置文本格式元素 */
  a {
    text-decoration: none;
  }

  a:hover { text-decoration: underline; }

  abbr[title], acronym[title] { /* 注：1.ie6 不支持 abbr; 2.这里用了属性选择符，ie6 下无效果 */
    border-bottom: 1px dotted;
    cursor: help;
  }

  q:before, q:after {
    content: '';
  }

  /* 重置表单元素 */
  legend {
    color: #000;
  }

  /* for ie6 */
  fieldset, img {
    border: none;
  }

  /* img 搭车：让链接里的 img 无边框 */
  /* 注：optgroup 无法扶正 */
  button, input, select, textarea {
    font-size: 100%; /* 使得表单元素在 ie 下能继承字体大小 */
  }

  /* 重置表格元素 */
  table {
    border-collapse: collapse;
    border-spacing: 0;
  }

  /* 重置 hr */
  hr {
    border: none;
    height: 1px;
  }


</style>
