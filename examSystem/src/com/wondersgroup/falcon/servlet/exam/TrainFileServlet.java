package com.wondersgroup.falcon.servlet.exam;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;


import com.wondersgroup.falcon.beans.auth.AuthBeans;
import com.wondersgroup.falcon.beans.common.CommFunc;
import com.wondersgroup.falcon.beans.exam.TrainFileService;
import com.wondersgroup.falcon.model.archives.Users;
import com.wondersgroup.falcon.model.auth.User;
import com.wondersgroup.falcon.model.exam.TrainFile;
import com.wondersgroup.falcon.model.exam.TrainFileFujian;


public class TrainFileServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 553516431452863160L;

	/**
	 * 
	 */

	
	public void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
			doPost(arg0,arg1);
	}

	
	public void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {

	}


	public void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		String actionType = arg0.getParameter("actionType");
		if(actionType==null||actionType.equals("")){
			try {
				addSFeedBack(arg0,arg1);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}else if(actionType.equals("addVoice")){
			addVoice(arg0,arg1);
		}
	}
	
	public void addSFeedBack(HttpServletRequest arg0, HttpServletResponse arg1) throws FileUploadException, IOException, ServletException{
		TrainFileService tfs = new TrainFileService();
		AuthBeans ab = new AuthBeans();
		PrintWriter out = arg1.getWriter();
		try{
			TrainFile tf = new TrainFile();
			Set fujians = new HashSet();
			tf.setSendtime(new Date());
			
			DiskFileUpload dfu = new DiskFileUpload();
			dfu.setSizeMax(4194304);
			dfu.setSizeThreshold(100000);
			List fileItems = dfu.parseRequest(arg0);
			
			String realpath = getServletContext().getRealPath("\\feedbackupload");
			String projectpath = arg0.getRequestURL().toString().substring(0,arg0.getRequestURL().toString().lastIndexOf("/")+1);
			String username = "";
			String nowtime = CommFunc.getNowStr();
			
			for(int i=0;i<fileItems.size();i++){
				FileItem fi = (FileItem) fileItems.get(i);
				if(fi.getFieldName().equals("sender")){
					username = fi.getString();
					Users sender = ab.findByUsername(username);
					tf.setSender(sender);
				}else if(fi.getFieldName().equals("type")){
					String type = fi.getString();
					tf.setType(Integer.parseInt(type));
				}else if(!fi.isFormField()){
					TrainFileFujian tff = new TrainFileFujian();

					tff.setTrainfile(tf);
					
					
					String filename =fi.getName();
					String realname = filename.substring(filename.lastIndexOf("\\")+1,filename.length());
					String subfilename = realname.substring(realname.lastIndexOf("."),realname.length());
					
					String code = username + nowtime + i + subfilename;
					tff.setCode(code);
					tff.setAddress(projectpath+"feedbackupload/"+code);
					tff.setName(realname);
					
					fujians.add(tff);
					File file = new File(realpath+"\\"+code);
					try {
						fi.write(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					//liangkd 2009
				}else if(fi.getFieldName().indexOf("title")>0){
					String title = fi.getString();
					tf.setTitle(title);
				}else if(fi.getFieldName().indexOf("content")>0){
					String content = fi.getString();
					tf.setContentstring(content);
				}
			}	
			tf.setFujian(fujians);
			
			tfs.saveTrainFile(tf);  
						
		}catch(Exception ex){
			ex.printStackTrace();
			out.print("<script>");
			out.print("alert('操作失败，请重新输入！');");
			out.print("window.location='"+arg0.getContextPath()+"/train/train_file_input.jsp';");
			out.print("</script>");			
		}	
		out.print("<script>");
		out.print("alert('操作成功！');");
		out.print("window.location='"+arg0.getContextPath()+"/train/train_file_input.jsp';");
		out.print("</script>");
		
		//arg0.getRequestDispatcher("smessage_input.jsp").forward(arg0, arg1);
	}
	
	public void addVoice(HttpServletRequest arg0, HttpServletResponse arg1) throws IOException, ServletException{
		TrainFileService tfs = new TrainFileService();
		AuthBeans ab = new AuthBeans();
		PrintWriter out = arg1.getWriter();
		try{
			TrainFile tf = new TrainFile();
			Set fujians = new HashSet();
			
			tf.setSendtime(new Date());
			String username = arg0.getParameter("sender");
			Users sender = ab.findByUsername(username);
			tf.setSender(sender);
			String type = arg0.getParameter("type");
			tf.setType(Integer.parseInt(type));
			String voice = arg0.getParameter("voice");
			String title = arg0.getParameter("title");
			String contentstring = arg0.getParameter("content");
			tf.setTitle(title);
			tf.setContentstring(contentstring);
			
			TrainFileFujian tff = new TrainFileFujian();
			tff.setTrainfile(tf);
			tff.setCode(voice);
			tff.setAddress("");
			tff.setName("");
			fujians.add(tff);
			
			tf.setFujian(fujians);
			tfs.saveTrainFile(tf);  
						
		}catch(Exception ex){
			ex.printStackTrace();
			out.print("<script>");
			out.print("alert('操作失败，请重新输入！');");
			out.print("window.location='"+arg0.getContextPath()+"/train/train_file_input.jsp';");
			out.print("</script>");			
		}	
		out.print("<script>");
		out.print("alert('操作成功！');");
		out.print("window.location='"+arg0.getContextPath()+"/train/train_file_input.jsp';");
		out.print("</script>");
	}	
	
	
	
	public void destroy() {
		super.destroy();
	}

	
	public void init() throws ServletException {
		super.init();
	}
	
}
