package cc.job.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.common.annotation.CronTag;

@CronTag("testTask")
public class TestTask {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public void test(String params) {
        log.info("我是带参数的test方法，正在被执行，参数为：{}" , params);
    }

    public void test1() {
        log.info("我是不带参数的test1方法，正在被执行");
    }

}
