package com.wondersgroup.falcon.Util.file;

import java.io.PrintStream;
import java.io.PrintWriter;

public class FileException extends Exception
{

    protected int errNo;
    protected Throwable rootCause;
    public static final int ERR_UNKNOWN = 0;
    public static final int ERR_MYEXCEPTION = 1;
    public static final int ERR_DATACONVERT = 2;
    public static final int ERR_PARAM_INVALID = 10;
    public static final int ERR_OBJ_NULL = 20;
    public static final int ERR_NUMOP_FAIL = 30;
    public static final int ERR_DBOP_FAIL = 40;
    public static final int ERR_CONNECTION_GETFAIL = 41;
    public static final int ERR_FILEOP_FAIL = 50;
    public static final int ERR_FILEOP_OPEN = 51;
    public static final int ERR_FILEOP_CLOSE = 52;
    public static final int ERR_FILEOP_READ = 53;
    public static final int ERR_FILEOP_WRITE = 54;
    public static final int ERR_FILE_NOTFOUND = 55;
    public static final int ERR_URL_MALFORMED = 110;
    public static final int ERR_NET_OPENSTREAM = 111;
    public static final int ERR_USER_NOTLOGIN = 99;

    public FileException(int _errNo)
    {
        errNo = 0;
        rootCause = null;
        errNo = _errNo;
    }

    public FileException(int _errNo, String _sMsg)
    {
        super(_sMsg);
        errNo = 0;
        rootCause = null;
        errNo = _errNo;
    }

    public FileException(int _errNo, String _sMsg, Throwable _rootCause)
    {
        super(_sMsg);
        errNo = 0;
        rootCause = null;
        errNo = _errNo;
        rootCause = _rootCause;
    }

    public int getErrNo()
    {
        return errNo;
    }

    public Throwable getRootCause()
    {
        return rootCause;
    }

    public String getErrNoMsg()
    {
        switch(errNo)
        {
        case 1: // '\001'
            return "自定义异常";

        case 2: // '\002'
            return "数据转换错误";

        case 10: // '\n'
            return "参数错误";

        case 20: // '\024'
            return "空指针错误";

        case 30: // '\036'
            return "数学运算异常";

        case 40: // '('
            return "数据库操作异常";

        case 41: // ')'
            return "获取数据库连接失败";

        case 50: // '2'
            return "文件操作异常";

        case 51: // '3'
            return "打开文件失败";

        case 52: // '4'
            return "关闭文件失败";

        case 53: // '5'
            return "读文件失败";

        case 54: // '6'
            return "写文件失败";

        case 55: // '7'
            return "文件未找到";

        case 110: // 'n'
            return "无效URL";

        case 111: // 'o'
            return "打开网络数据流失败";
            
        case 99: //
        	return "用户未登录或登录超时";
        }
        
       
        	
        return "未知错误";
    }

    public String getMyMessage()
    {
        return super.getMessage();
    }

    public String getMessage()
    {
        if(rootCause == null)
            return String.valueOf(String.valueOf((new StringBuffer("[")).append(errNo).append("]").append(super.getMessage())));
        else
            return String.valueOf(String.valueOf((new StringBuffer("[")).append(errNo).append("]").append(super.getMessage()).append(";\r\n <-- ").append(rootCause.toString())));
    }

    public String getLocalizedMessage()
    {
        return getMessage();
    }

    public void printStackTrace(PrintStream _ps)
    {
        if(rootCause == null)
            super.printStackTrace(_ps);
        else
            synchronized(_ps)
            {
                _ps.println(this);
                rootCause.printStackTrace(_ps);
            }
    }

    public void printStackTrace(PrintWriter _pw)
    {
        if(rootCause == null)
            super.printStackTrace(_pw);
        else
            synchronized(_pw)
            {
                _pw.println(this);
                rootCause.printStackTrace(_pw);
            }
    }

   
}
