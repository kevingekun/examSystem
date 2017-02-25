<%@page contentType="text/html;charset=gbk" %>
<%@page import="com.wondersgroup.falcon.model.exam.ExamPaper" %>
<jsp:useBean id="examservice" class="com.wondersgroup.falcon.beans.exam.ExamPaperServiceImple" />
<% 
	String examid = request.getParameter("examid");	
	ExamPaper exampaper = examservice.findExamPaperById(examid);
	examservice.delete(exampaper);
	
	
%>