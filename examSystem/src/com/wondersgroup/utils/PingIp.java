/** 
* 
* author:mxk 
*/ 
package com.wondersgroup.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 *
 */
public class PingIp {
	public static boolean ping(String ipAddress, int pingTimes, int timeOut) {  
        BufferedReader in = null;  
        Runtime r = Runtime.getRuntime();  // 将要执行的ping命令,此命令是windows格式的命令  
        String pingCommand = "ping " + ipAddress + " -n " + pingTimes    + " -w " + timeOut;  
        try {   // 执行命令并获取输出  
            System.out.println(pingCommand);   
            Process p = r.exec(pingCommand);   
            if (p == null) {    
                return false;   
            }
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));   // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数  
            int connectedCount = 0;   
            String line = null;   
            while ((line = in.readLine()) != null) {    
                connectedCount += getCheckResult(line);   
            }   // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真  
            return connectedCount == pingTimes;  
        } catch (Exception ex) {   
            ex.printStackTrace();   // 出现异常则返回假  
            return false;  
        } finally {   
            try {    
                in.close();   
            } catch (IOException e) {    
                e.printStackTrace();   
            }  
        }
    }
	private static int getCheckResult(String line) {  // System.out.println("控制台输出的结果为:"+line);  
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)",    Pattern.CASE_INSENSITIVE);  
        Matcher matcher = pattern.matcher(line);  
        while (matcher.find()) {
            return 1;
        }
        return 0; 
    }
}
