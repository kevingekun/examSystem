<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="gb2312"%>
    <%@page import="com.wondersgroup.kaoshi.dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Insert title here</title>
</head>
<body>

<% 

System.out.println("1====================11");

EPapersDAOImpl dao=new EPapersDAOImpl();
dao.delete();
%>
</body>
</html>