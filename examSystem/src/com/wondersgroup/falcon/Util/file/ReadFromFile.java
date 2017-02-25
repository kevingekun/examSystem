package com.wondersgroup.falcon.Util.file;

import java.io.BufferedReader;   
import java.io.File;   
import java.io.FileInputStream;   
import java.io.FileReader;   
import java.io.IOException;   
import java.io.InputStream;   
import java.io.RandomAccessFile;   
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

import oracle.sql.CLOB;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.archives.Node;
import com.wondersgroup.falcon.model.archives.PolicyAttr;
import com.wondersgroup.falcon.model.archives.PolicyNode;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
  
class ReadFromFile {   
	
	public static List findAll()throws InfrastructureException {
		return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
		List result = null;


			String hql = "from PolicyAttr t order by id";
			Query query = s.createQuery(hql);
			result =query.list();
		return result;
			}
		});
	}
	
	public static PolicyNode findById(final String id)throws InfrastructureException {
		return (PolicyNode)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

			String hql = "from PolicyNode t where t.attribute.id = "+id+"";

			Query query = s.createQuery(hql);
			List list = query.list();
			PolicyNode pn = new PolicyNode();
			if(list!=null&&list.size()>0){
				pn = (PolicyNode)list.get(0);
			}
			
			return pn;
			}
		});
	}
	

    public static void readFileByBytes(String fileName){   
        File file = new File(fileName);   
        InputStream in = null;   
        try {   
            System.out.println("���ֽ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���ֽڣ�");   
            // һ�ζ�һ���ֽ�   
            in = new FileInputStream(file);   
            int tempbyte;   
            while((tempbyte=in.read()) != -1){   
                System.out.write(tempbyte);   
            }   
            in.close();   
        } catch (IOException e) {   
            e.printStackTrace();   
            return;   
        }   
        try {   
            System.out.println("���ֽ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�����ֽڣ�");   
            // һ�ζ�����ֽ�   
            byte[] tempbytes = new byte[100];   
            int byteread = 0;   
            in = new FileInputStream(fileName);   
            ReadFromFile.showAvailableBytes(in);   
            // �������ֽڵ��ֽ������У�bytereadΪһ�ζ�����ֽ���   
            while ((byteread = in.read(tempbytes)) != -1){   
                System.out.write(tempbytes, 0, byteread);   
            }   
        } catch (Exception e1) {   
            e1.printStackTrace();   
        } finally {   
            if (in != null){   
                try {   
                    in.close();   
                } catch (IOException e1) {   
                }   
            }   
        }   
    }   
       
    /**  
     * ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ�  
     *   
     * @param fileName  
     *            �ļ���  
     */  
    public static String readFileByLines(String fileName){   
        File file = new File(fileName);   
        BufferedReader reader = null;  
        final StringBuffer sb = new StringBuffer();
        String s="";
        try {   
            System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");   
            reader = new BufferedReader(new FileReader(file));   
            String tempString = null;   
            int line = 1;   
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����   
            while ((tempString = reader.readLine()) != null){   
                // ��ʾ�к�   
            	
            		sb.append(tempString);
                //System.out.println("line " + line + ": " + tempString);   
                line++; 
                
            } 
            if(sb.indexOf("</body>")>-1)
            {            	
            	System.out.print("1111111111111"+sb.indexOf("</body>"));
            	
            	s=sb.substring(sb.indexOf("<body>")+6, sb.indexOf("</body>"));
            	s=s.replace("../../file", "upload");
            }
            System.out.println(s);  
            reader.close();
        } catch (IOException e) {   
        e.printStackTrace();   
        } finally {   
            if (reader != null){   
                try {   
                reader.close();   
                } catch (IOException e1) {   
                }   
            }   
        }
		return s;   
    }   
    /**  
     * ����ȡ�ļ�����  
     *   
     * @param fileName  
     *            �ļ���  
     */  
    public static void readFileByRandomAccess(String fileName){   
        RandomAccessFile randomFile = null;   
        try {   
            System.out.println("����ȡһ���ļ����ݣ�");   
            // ��һ���������ļ�������ֻ����ʽ   
            randomFile = new RandomAccessFile(fileName, "r");   
            // �ļ����ȣ��ֽ���   
            long fileLength = randomFile.length();   
            // ���ļ�����ʼλ��   
            int beginIndex = (fileLength > 4) ? 4 : 0;   
            // �����ļ��Ŀ�ʼλ���Ƶ�beginIndexλ�á�   
            randomFile.seek(beginIndex);   
            byte[] bytes = new byte[10];   
            int byteread = 0;   
            // һ�ζ�10���ֽڣ�����ļ����ݲ���10���ֽڣ����ʣ�µ��ֽڡ�   
            // ��һ�ζ�ȡ���ֽ����byteread   
            while ((byteread = randomFile.read(bytes)) != -1){   
                System.out.write(bytes, 0, byteread);   
            }   
        } catch (IOException e){   
            e.printStackTrace();   
        } finally {   
            if (randomFile != null){   
                try {   
                randomFile.close();   
                } catch (IOException e1) {   
                }   
            }   
        }   
    }   
       
    /**  
     * ��ʾ�������л�ʣ���ֽ���  
     *   
     * @param in  
     */  
    private static void showAvailableBytes(InputStream in){   
        try {   
        System.out.println("��ǰ�ֽ��������е��ֽ���Ϊ:" + in.available());   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
    } 
    


    
    
  
    public static void main(String[] args) throws SQLException {
    	//Session s2 = HibernateUtil.getSession();
    	String fileName = "D:/libstore/libstore.war/policy/1886.html"; 
    	
    	ReadFromFile.readFileByLines(fileName);
 
          
        //ReadFromFile.readFileByBytes(fileName);   
          
        //ReadFromFile.readFileByRandomAccess(fileName);   
    }   
}   

  
  


