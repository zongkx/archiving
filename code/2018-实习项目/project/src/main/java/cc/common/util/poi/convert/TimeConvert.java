package cc.common.util.poi.convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.common.util.DateUtil;

public class TimeConvert implements ExportConvert {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String handler(Object val) {
        try {
            if (val == null)
                return "";
            else {
                return DateUtil.formatCSTTime(val.toString(), "yyyy-MM-dd HH:mm:ss");
            }
        } catch (Exception e) {
            log.error("时间转换异常", e);
            return "";
        }
    }

}
