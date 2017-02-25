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
import com.wondersgroup.falcon.beans.exam.ExamQuestionService;
import com.wondersgroup.falcon.beans.exam.TrainExampleService;
import com.wondersgroup.falcon.model.archives.Users;
import com.wondersgroup.falcon.model.auth.User;
import com.wondersgroup.falcon.model.exam.ExamRealQuestions;
import com.wondersgroup.falcon.model.exam.TrainExample;


public class TrainExampleServlet extends HttpServlet{
	private ExamQuestionService examquestionservice = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 553516431452863160L;

	/**
	 * 
	 */
	
	public void init() throws ServletException {
		try {
			examquestionservice = (ExamQuestionService)Class.forName("com.wondersgroup.falcon.beans.exam.ExamQuestionServiceImple").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
			doPost(arg0,arg1);
	}


	public void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {

	}


	public void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		String actionType = arg0.getParameter("actionType");
		if(actionType==null||actionType.equals("")){
			
		}else if(actionType.equals("add")){
			addExample(arg0,arg1);
		}
	}

	
	public void addExample(HttpServletRequest arg0, HttpServletResponse arg1) throws IOException, ServletException{
		TrainExampleService tfs = new TrainExampleService();
		AuthBeans ab = new AuthBeans();
		PrintWriter out = arg1.getWriter();
		try{
			TrainExample tf = new TrainExample();
			
			tf.setSendtime(new Date());
			String username = arg0.getParameter("sender");
			Users sender = ab.findByUsername(username);
			tf.setSender(sender);
			String realquesid = arg0.getParameter("realquesid");
			ExamRealQuestions erq = examquestionservice.findRealQuesById(realquesid);
			tf.setRealquestion(erq);
			String title = arg0.getParameter("title");
			String contentstring = arg0.getParameter("content");
			tf.setTitle(title);
			tf.setContentstring(contentstring);

			tfs.saveTrainExample(tf);  
						
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


	
}
