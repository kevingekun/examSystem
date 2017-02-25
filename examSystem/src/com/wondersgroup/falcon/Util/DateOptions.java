/**
 * <p>Title: ������̳</p>
 * <p>Description: ������̳������������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ���㹤����</p>
 * @author ����
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
            String message = "fiyucore.util.ParamOptions: ���ܶ�ȡ�ļ�: '" + OPTION_FILE_NAME + ".properties'. ��ȷ�ϸ��ļ�ȷʵ���ڣ�";
            log.error(message, e);
        }
    }//constructor
}
