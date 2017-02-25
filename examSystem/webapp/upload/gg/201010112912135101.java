package tmq.action;

import com.opensymphony.xwork2.*;
import org.apache.struts2.*;
import java.io.*;
import java.util.Map;
public class MyDownload extends ActionSupport
{
  private String inputPath;

  public void setInputPath(String inputPath)
  {
    try{
    inputPath=new String(inputPath.getBytes("utf-8"));
    }catch(Exception e){}
    this.inputPath=inputPath;
    System.out.println(this.inputPath);
  }

  public InputStream getTargetFile() throws Exception
  {
    return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
  }

  public String execute() throws Exception
  {
     ActionContext actionContext = ActionContext.getContext(); 
     Map session=actionContext.getSession();
     String username=(String)session.get("user");
     if(username==null)
     {
       addActionError("登录后方可允许下载文件！");
       return this.LOGIN;
     }
     return this.SUCCESS;
  }
}