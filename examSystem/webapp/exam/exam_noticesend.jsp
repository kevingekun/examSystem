<%@ page contentType="text/html;charset=gbk"%>
<jsp:useBean id="examservice" class="com.wondersgroup.falcon.beans.exam.ExamPaperServiceImple" />
<jsp:useBean id="noticeservice" class="com.wondersgroup.falcon.beans.exam.ExamNoticeServiceImple" />
<%@page import="java.util.List,java.util.Date"%>
<%@ page import="com.wondersgroup.falcon.model.exam.ExamPaper"%>
<%@ page import="com.wondersgroup.falcon.model.exam.ExamAnnoucement"%>
<%@ page import="com.wondersgroup.falcon.beans.common.CommFunc"%> 
<%@page import="com.wondersgroup.falcon.acegi.AcegiUtil"%>
<%@page import="com.wondersgroup.falcon.model.archives.Users" %>
<%@page import="com.wondersgroup.falcon.persistence.HibernateUtil" %>
<jsp:useBean id="authbean" class="com.wondersgroup.falcon.beans.auth.AuthBeans"/>
<%
	String exampaperid = request.getParameter("exampaperid");
	String title = request.getParameter("title");
	String effectdate = request.getParameter("effectdate");
	String noteffectdate = request.getParameter("noteffectdate");
	String content = request.getParameter("content");
	
	String username = AcegiUtil.getUserDetails().getUsername(); 
	Users users = authbean.findByUsername(username);
	
	System.out.println("exampaperid==================>>>"+exampaperid);
	ExamPaper exampaper = examservice.findExamPaperById(exampaperid); 
	
	ExamAnnoucement ea= new ExamAnnoucement();
	ea.setTitle(title);
	ea.setEffectstarttime(CommFunc.strToDate(effectdate));
	ea.setEffectendtime(CommFunc.strToDate(noteffectdate));
	ea.setContent(content);
	ea.setSendtime(new Date());
	ea.setSender(users);
	ea.setExampaper(exampaper);
	try{
		examservice.save(ea);
	}catch(Exception ex){
		ex.printStackTrace();
		out.println("<script>");
		out.println("alert('操作失败，请重新输入！')");
		out.println("window.location='exam_noticeadd.jsp'");
		out.println("</script>");
	}finally{
		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
	}
	out.println("<script>");
	out.println("alert('操作成功！')");
	out.println("window.location='exam_noticeadd.jsp'");
	out.println("</script>");

%>