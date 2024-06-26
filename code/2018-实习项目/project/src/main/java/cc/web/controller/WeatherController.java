package cc.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.common.annotation.Log;
import cc.common.controller.BaseController;
import cc.common.domain.ResponseBo;
import cc.common.util.FebsConstant;
import cc.common.util.HttpUtils;

@Controller
public class WeatherController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Log("获取天气信息")
    @RequestMapping("weather")
    @RequiresPermissions("weather:list")
    public String weather() {
        return "web/weather/weather";
    }

    @RequestMapping("weather/query")
    @ResponseBody
    public ResponseBo queryWeather(String areaId) {
        try {
            String data = HttpUtils.sendPost(FebsConstant.MEIZU_WEATHER_URL, "cityIds=" + areaId);
            return ResponseBo.ok(data);
        } catch (Exception e) {
            log.error("查询天气失败", e);
            return ResponseBo.error("查询天气失败，请联系网站管理员！");
        }
    }
}
