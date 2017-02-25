/**
 * <p>Title: ������̳</p>
 * <p>Description: ������̳����ʱ������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ���㹤����</p>
 * @author ����
 * @version 2.0
 */
package com.wondersgroup.falcon.Util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import org.apache.log4j.Logger;

public final class TimerUtil {

    private static Logger log = Logger.getLogger(TimerUtil.class);

    private static TimerUtil instance = null;

    private Timer timer = null;

    private TimerUtil() {
        log.debug("TimerUtil ��ʵ������");
        timer = new Timer();
    }

    public static synchronized TimerUtil getInstance() {
        if (instance == null) {
            instance = new TimerUtil();
        }
        return instance;
    }

    public void cancel() {
        timer.cancel();
    }

    public void schedule(TimerTask task, Date firstTime, long period) {
        timer.schedule(task, firstTime, period);
    }

    public void schedule(TimerTask task, Date time) {
        timer.schedule(task, time);
    }

    public void schedule(TimerTask task, long delay) {
        timer.schedule(task, delay);
    }

    public void schedule(TimerTask task, long delay, long period) {
        timer.schedule(task, delay, period);
    }

    public void scheduleAtFixedRate(TimerTask task, Date firstTime, long period) {
        timer.schedule(task, firstTime, period);
    }

    public void scheduleAtFixedRate(TimerTask task, long delay, long period) {
        timer.schedule(task, delay, period);
    }
}
