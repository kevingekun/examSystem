<%@ page contentType="text/html;charset=gb2312"%>
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>无标题文档</title>
		<link href="../inc/all.css" rel="stylesheet" type="text/css">
	</head>
<%@ page import="java.util.*"%>
<%@ page import="com.wondersgroup.falcon.persistence.HibernateUtil"%>
<%@ page import="com.wondersgroup.falcon.beans.citizeninfo.*"%>
<%@ page import="com.wondersgroup.falcon.model.shortmessage.*" %>
<%@ page import="com.wondersgroup.falcon.dao.chouyang.*"%>
<%@ page import="java.util.List,java.util.Date,java.text.SimpleDateFormat,java.util.Set,java.util.HashSet" %>
	<body>
		<%
			

			String callid = request.getParameter("callid");
			
			
		ChouyangDAO chouyangService = new ChouyangDAO(); 
			
		 chouyangService.deletebad(callid);
			out.println("<script>");
			out.println("alert('提交成功');");
			out.println("window.location = 'baddianxingQuery.jsp'");
			out.println("</script>");	
		%>
	</body>
</html>
