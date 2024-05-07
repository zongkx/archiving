<template>
  <div id="app">
    <div class="content" :style="contentStyleObj">
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
          height: ''
        },
        transitionName: 'slide-right',// 默认动态路由变化为slide-right
      }

    },

    methods: {
      getHeight() {
        this.contentStyleObj.height = window.innerHeight - 150 + 'px';
      }
    },

    created() {
      window.addEventListener('resize', this.getHeight);
      this.getHeight()
    },

    destroyed() {
      window.removeEventListener('resize', this.getHeight)
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
    min-width: 1000px;
  }

  .content {
    margin: 0 auto;
    width: 80%;
    min-width: 800px;
  }


</style>
