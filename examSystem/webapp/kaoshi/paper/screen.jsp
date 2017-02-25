<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String paperid=request.getParameter("paperId");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body onload="window.open('http://35.1.132.250:7001/falnewexam/examAction.action?paperId=<%=paperid %>','example01','width=1280,height=720,top=0,left=0,scrollbars=no,resizable=no,location=no,status=no,toolbar=no,menubar=no,fullscreen=1','');">
</body>
</html>
