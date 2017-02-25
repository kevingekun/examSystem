/**
 * <p>Title: 飞鱼论坛</p>
 * <p>Description: 飞鱼论坛－－日期属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 飞鱼工作室</p>
 * @author 飞鱼
 * @version 2.0
 */

package com.wondersgroup.falcon.Util;

import java.util.ResourceBundle;
import org.apache.log4j.*;

final class DateOptions {
	
	private static Logger log = Logger.getLogger(DateOptions.class);

    private static final String OPTION_FILE_NAME = "fiyucore_util_DateOptions";

    //default values
    int serverHourOffset = 0;

    DateOptions() {
        try {
            ResourceBundle res = ResourceBundle.getBundle(OPTION_FILE_NAME);
            String strOffset = res.getString("SERVER_HOUR_OFFSET").trim();
            serverHourOffset = Integer.parseInt(strOffset);
        } catch (Exception e) {
            String message = "fiyucore.util.ParamOptions: 不能读取文件: '" + OPTION_FILE_NAME + ".properties'. 请确认该文件确实存在！";
            log.error(message, e);
        }
    }//constructor
}
