/**
 * <p>Title: ∑…”„¬€Ã≥</p>
 * <p>Description: ∑…”„¬€Ã≥£≠£≠∑¢ÀÕ” º˛≈‰÷√</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ∑…”„π§◊˜ “</p>
 * @author ∑…”„
 * @version 2.0
 */

package com.wondersgroup.falcon.Util;

import java.util.ResourceBundle;
import java.util.MissingResourceException;
import org.apache.log4j.Logger;

final class MailOptions {

    private static Logger log = Logger.getLogger(MailOptions.class);

    private static final String OPTION_FILE_NAME = "fiyucore_util_MailOptions";

    //default values
    String mailServer       = "mail.host";
    String defaultMailFrom  = "user@host";
    String username         = "";
    String password         = "";
    int    port             = 25;

    MailOptions() {
        try {
            ResourceBundle res = ResourceBundle.getBundle(OPTION_FILE_NAME);

            mailServer      = res.getString("MAIL_SERVER").trim();
            defaultMailFrom = res.getString("DEFAULT_MAIL_FROM").trim();
            username        = res.getString("USERNAME").trim();
            password        = res.getString("PASSWORD").trim();
            try {
                String temp = res.getString("PORT").trim();
                port = Integer.parseInt(temp);
            } catch (MissingResourceException ex) {
                // do nothing, just use default
            } catch (NumberFormatException ex) {
                // do nothing, just use default
            }
        } catch (Exception e) {
            String message = "fiyucore.util.MailOptions: Can't read the properties file: '" + OPTION_FILE_NAME + ".properties'. Make sure the file is in your CLASSPATH";
            log.error(message, e);
        }
    }//constructor
}
