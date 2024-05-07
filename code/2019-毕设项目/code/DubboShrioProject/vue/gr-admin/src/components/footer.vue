<template>
  <div>
    <div class="container" style="padding: 0">
      <div class="footer-widgets top-footer-widgets widgets-num-3">
        <div class="f-widget f-widget-1">
          <div id="text-8" class="widget widget_text"><h3 class="widget-title">关于</h3>
            <div class="textwidget"><p><a href="http://www.appinn.com/about/">关于我们</a><br> <a
              href="http://www.appinn.com/contact/">联系我们</a><br> <a href="http://www.appinn.com/join-us/">加入我们</a><br>
              <a href="https://meta.appinn.com/">继续讨论</a></p></div>
          </div>
        </div>
        <div class="f-widget f-widget-2">
          <div id="text-9" class="widget widget_text"><h3 class="widget-title">分享</h3>
            <div class="textwidget"><p><a href="https://meta.appinn.com/c/faxian">发布</a></p>
              <p>提交软件、应用、服务，也可以是数码产品</p>
              <p><a href="https://meta.appinn.com/c/wen-ti-qiu-zhu">提问</a></p>
              <p>任何问题都可以</p></div>
          </div>
        </div>

      </div>
    </div>

  </div>
</template>

<script>
  export default {
    name: "FooterNav"
  }
</script>

<style scoped>
  footer > .footer-carousel-wrap, footer > .copyrights {
    background: #fff
  }

  .footer-carousel-wrap {
    padding: 50px 0
  }

  footer {
    overflow: hidden;
    width: 100%;
    position: relative;
    clear: both;
  }

  footer .container {
    padding: 0
  }

  .footer-widgets {
    overflow: hidden;
    padding: 50px 0 0;
    width: 100%;
    margin: 0;
  }

  .f-widget {
    width: 29.5%;
    float: left;
    position: relative;
    margin-bottom: 0;
    margin-right: 5.75%;
  }

  .widgets-num-4 .f-widget {
    width: 22%;
    margin-right: 4%;
  }

  .footer-widgets .f-widget:last-of-type {
    margin-right: 0px !important
  }

  .copyrights {
    float: left;
    width: 100%;
    clear: both;
    padding: 40px 0;
  }

  .copyrights a {
    color: inherit;
    border-bottom: 1px dotted;
  }

  #copyright-note {
    width: 1170px;
    margin: 0 auto;
    clear: both;
    max-width: 100%;
  }

  #copyright-note > span {
    float: left
  }

  #copyright-note .right {
    margin-right: 10px
  }

  .postsby {
    margin-bottom: 25px
  }
</style>
