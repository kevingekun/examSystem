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
            return "�Զ����쳣";

        case 2: // '\002'
            return "����ת������";

        case 10: // '\n'
            return "��������";

        case 20: // '\024'
            return "��ָ�����";

        case 30: // '\036'
            return "��ѧ�����쳣";

        case 40: // '('
            return "���ݿ�����쳣";

        case 41: // ')'
            return "��ȡ���ݿ�����ʧ��";

        case 50: // '2'
            return "�ļ������쳣";

        case 51: // '3'
            return "���ļ�ʧ��";

        case 52: // '4'
            return "�ر��ļ�ʧ��";

        case 53: // '5'
            return "���ļ�ʧ��";

        case 54: // '6'
            return "д�ļ�ʧ��";

        case 55: // '7'
            return "�ļ�δ�ҵ�";

        case 110: // 'n'
            return "��ЧURL";

        case 111: // 'o'
            return "������������ʧ��";
            
        case 99: //
        	return "�û�δ��¼���¼��ʱ";
        }
        
       
        	
        return "δ֪����";
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
