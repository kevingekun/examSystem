/**
 * 
 */
package com.wondersgroup.falcon.Util;

/**
 * <p>Description: 加密处理</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * @author Keevn Wang
 * @version 2.0
 */

import java.net.URLEncoder;
import java.security.MessageDigest;
import org.apache.log4j.Logger;

public class Encoder {

    private static Logger log = Logger.getLogger(Encoder.class);

    private static MessageDigest digest = null;
    private static boolean isInited = false;

    private Encoder() {
    }

    public static synchronized String getMD5_Base64(String input) {

        if (isInited == false) {
            isInited = true;
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (Exception ex) {
                log.error("不能得到信息摘要，可能时应用程序没有正确运行!", ex);
            }
        }
        if (digest == null) return input;

        try {
            digest.update(input.getBytes("GBK"));
        } catch (java.io.UnsupportedEncodingException ex) {
            log.error("声明：这个永远不要发生！");
        }
        byte[] rawData = digest.digest();
        byte[] encoded = Base64.encode(rawData);
        String retValue = new String(encoded);
        return retValue;
    }

    public static String encodeURL(String input) {
        return URLEncoder.encode(input);
    }

    public static String filterUrl(String url) {
        String lowerUrl = url.toLowerCase();
        if ( (lowerUrl.indexOf("javascript:") >= 0) ||
             lowerUrl.indexOf("file:") >= 0) {
            return "";
        }

        String protocol = "http://";
        String name = null;
        if (url.startsWith("http://")) {
            protocol = "http://";
            name = url.substring(protocol.length());
        } else if (url.startsWith("https://")) {
            protocol = "https://";
            name = url.substring(protocol.length());
        } else if (url.startsWith("ftp://")) {
            protocol = "ftp://";
            name = url.substring(protocol.length());
        } else if (url.startsWith("mailto:")) {
            protocol = "mailto:";
            name = url.substring(protocol.length());
        } else {
            name = url;
        }
        String ret;
        if (protocol.equals("mailto:")) {
            try {
                MailUtil.checkGoodEmail(name);
                ret = protocol + name;
            } catch (Exception ex) {
                ret = "";
            }
        } else {
            ret = protocol + encodePath(name);
        }
        return ret;
    }


    private static String encodePath(String path) {
        return path;
        /*
        String ret = "";
        int indexFirstSlash = path.indexOf('/');
        if ( indexFirstSlash != -1 ) {
            String hostport = path.substring(0, indexFirstSlash);
            int indexFirstColon = hostport.indexOf(':');
            if (indexFirstColon != -1) {
                String host = hostport.substring(0, indexFirstColon);
                String port = hostport.substring(indexFirstColon + 1);
                hostport = Encoder.encodeURL(host) + ":" + Encoder.encodeURL(port);
            } else {
                hostport = Encoder.encodeURL(hostport);
            }
            String filename = path.substring(indexFirstSlash + 1);
            filename = Encoder.encodeURL(filename);
            ret = hostport + "/" + filename;
        } else {
            ret = Encoder.encodeURL(path);
        }
        return ret;
        */
    }

    /*
    public static void main(String[] args) {
        //test data should be
        //a1            iou3zTQ6oq2Zt9diAwhXog==
        //Hello World   sQqNsWTgdUEFt6mb5y4/5Q==

        String testString = "a1";
        String encrypted = getMD5_Base64(testString);
        System.out.println("encrypted = " + encrypted);
        System.out.println("length = " + encrypted.length());
    }
    */
}
