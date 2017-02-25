/**
 * <p>Title: ������̳</p>
 * <p>Description: ������̳������̳������������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ���㹤����</p>
 * @author ����
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
            String message = "fiyucore.util.ParamOptions: ���ܶ�ȡ�ļ�: '" + OPTION_FILE_NAME + ".properties'. ��ȷ�ϸ��ļ��Ƿ���ڣ�";
            log.error(message, e);
        }
    }//constructor
}
