/**
 * <p>Title: 飞鱼论坛</p>
 * <p>Description: 飞鱼论坛－－论坛基本属性配置</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 飞鱼工作室</p>
 * @author 飞鱼
 * @version 2.0
 */

package com.wondersgroup.falcon.Util;

import java.util.ResourceBundle;
import org.apache.log4j.Logger;

final class ParamOptions {

    private static Logger log = Logger.getLogger(ParamOptions.class);

    private static final String OPTION_FILE_NAME = "fiyucore_util_ParamOptions";

    //default values
    String contextPath = "";
    String serverPath = "";

    ParamOptions() {
        try {
            ResourceBundle res = ResourceBundle.getBundle(OPTION_FILE_NAME);
            contextPath = res.getString("CONTEXT_PATH").trim();
            serverPath = res.getString("SERVER_PATH").trim();
            if (serverPath.endsWith("/")) {
                serverPath = serverPath.substring(0, serverPath.length()-1);
            }
        } catch (Exception e) {
            String message = "fiyucore.util.ParamOptions: 不能读取文件: '" + OPTION_FILE_NAME + ".properties'. 请确认该文件是否存在！";
            log.error(message, e);
        }
    }//constructor
}
