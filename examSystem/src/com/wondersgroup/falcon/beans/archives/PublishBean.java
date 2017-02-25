/**
 * 
 */
package com.wondersgroup.falcon.beans.archives;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.falcon.Util.PropertiesUtil;
import com.wondersgroup.falcon.Util.StringUtils;
import com.wondersgroup.falcon.Util.file.FileException;
import com.wondersgroup.falcon.Util.file.FileUtils;
import com.wondersgroup.falcon.model.archives.Node;
import com.wondersgroup.falcon.model.archives.PolicyNode;
import com.wondersgroup.falcon.persistence.JHKOperation;




/**
 * <p>Title:[类标题] </p>
 * <p>Description: [类功能描述]</p> 
 *
 * Created by [Kevin Liang] [2009-9-23]
 * Midified by [修改人] [修改时间]
 *
 */
public class PublishBean {
	private static Log log = LogFactory.getLog(PublishBean.class);
	private HttpServletRequest request ;
	private HttpServletResponse response ;
	private ServletContext application ;

	public PublishBean(){

	}

	public PublishBean(HttpServletRequest request,
			HttpServletResponse response, 
			ServletContext application){
		this.request = request;
		this.response = response;
		this.application = application;
	}

	/**
	 * 
	 * <p>Description:[将撤销、删除的资料信息静态页面文件删除] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-24]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param docid
	 * @param cname
	 */
	public void deleteFiles(long []docid,String cname,Byte usertype) throws Exception{

		PropertiesUtil pu = new PropertiesUtil();
		//文件物理路径
		String pulishpath = pu.getProperties("path.fabu") + cname+"/" + usertype +"/";
		
		for(int i=0;i<docid.length;i++){

			//文件名与ID相同
			String fileName = new String(docid[i]+".html");
			String filepath = StringUtils.replaceStr(pulishpath + fileName,"/", File.separator);
			try{
				
				FileUtils.deleteFile(filepath);
				
			}catch(Exception ex){

				throw new FileException(40,"文件删除异常错误",ex);
			}

		}
	}

	
	/**
	 * 
	 * <p>Description:[发布资料信息，生成静态页面文件] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-24]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param docid
	 * @param cname
	 * @throws Exception
	 */
	public void publishDocumentPage(long []docid,String cname,Byte usertype) throws Exception{

		PropertiesUtil pu = new PropertiesUtil();
		//模板相对路径
		String templateurl =pu.getProperties("template.url."+cname);
		String pulishpath = pu.getProperties("path.fabu") + cname+"/" + usertype +"/";
		

		for(int i=0;i<docid.length;i++){

			String fileName = new String(docid[i]+".html");
			String param = "?docid=" + docid[i] + "&cname=" + cname ;

			String filepath = StringUtils.replaceStr(pulishpath + fileName,"/", File.separator);

			this.createPage(request, response, templateurl+param, filepath);

		}

	}
	
	public void publishDocumentPage(long []docid,String templatename , String cname,Byte usertype) throws Exception{

		PropertiesUtil pu = new PropertiesUtil();
		//模板相对路径
		String templateurl =pu.getProperties("template.url."+templatename);
		String pulishpath = pu.getProperties("path.fabu") + cname+"/" + usertype +"/";
		

		for(int i=0;i<docid.length;i++){

			String fileName = new String(docid[i]+".html");
			String param = "?docid=" + docid[i] + "&cname=" + cname ;

			String filepath = StringUtils.replaceStr(pulishpath + fileName,"/", File.separator);

			this.createPage(request, response, templateurl+param, filepath);

		}

	}
	public void publishDocumentPage(long []docid,String cname,String template , String pulishpath) throws Exception{

		PropertiesUtil pu = new PropertiesUtil();
		//模板相对路径
		
		String templateurl = template ;	
//		System.out.println(cname);
//		System.out.println(templateurl);
//		System.out.println(pulishpath);
		
		
		for(int i=0;i<docid.length;i++){

			String fileName = new String(docid[i]+".html");
			String param = "?docid=" + docid[i] + "&cname=" + cname ;

			String filepath = StringUtils.replaceStr(pulishpath + fileName,"/", File.separator);

			this.createPage(request, response, templateurl+param, filepath);

		}

	}
	
	public  static void createPage(HttpServletRequest request, HttpServletResponse response, String templateurl, String tofilepath)
	throws FileException
	{

		RequestDispatcher rd = request.getRequestDispatcher(templateurl);

		final ByteArrayOutputStream os = new ByteArrayOutputStream();

		final ServletOutputStream stream = new ServletOutputStream()
		{
			public void write(byte[] data, int offset, int length)
			{
				os.write(data, offset, length);
			}

			public void write(int b) throws IOException
			{
				os.write(b);
			}
		};

		final PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
		HttpServletResponse rep = new HttpServletResponseWrapper(response)
		{
			public ServletOutputStream getOutputStream()
			{
				return stream;
			}

			public PrintWriter getWriter()
			{
				return pw;
			}
		};


		try{

			rd.include(request, rep);
			pw.flush();  

			String dir = FileUtils.extractFilePath(tofilepath);
			String fileext = FileUtils.extractFileExt(tofilepath);

			//System.out.print("dir=" + dir);
			//System.out.println(" fileext=" + fileext);

			if(!fileext.equals(""))
				FileUtils.makeDir(dir, true);

			/* FileOutputStream fos = new FileOutputStream(tofilepath); 
        os.writeTo(fos);
        fos.close();
			 */

			String html = os.toString();
			//删除头部的空行
			int beginP = html.indexOf("<");
			if(beginP>=0){
				html = html.substring(beginP);
			}

			String publog = "<!-- published at " + StringUtils.getCurDateTime() 
			+ " -->\n";
			html = publog + html;
			FileUtils.writeFile(tofilepath, html);

		}catch(IOException e){
			throw new FileException(1000,"生成页面时候发生IO错误",e);
		}catch(ServletException e){
			throw new FileException(1000,"生成页面时候发生Servlet异常错误",e);
		}catch(Exception e){
			throw new FileException(1000,"生成页面时候发生IO错误",e);
		}
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setApplication(ServletContext application) {
		this.application = application;
	}

}
