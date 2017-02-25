<%@ page contentType = "text/html;charset=utf-8" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core' %>
<%@ page import="net.sf.acegisecurity.ui.AbstractProcessingFilter" %>
<%@ page import="net.sf.acegisecurity.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="net.sf.acegisecurity.AuthenticationException" %>

<%
	session.setMaxInactiveInterval(-1);
	//out.print(session.getAttribute("liufei"));
	//out.print("OK");
	String login_error = request.getParameter("login_error");
	//out.print("OK1");	
	//out.print(session.getAttribute(AbstractProcessingFilter.ACEGI_SECURITY_TARGET_URL_KEY));
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-

transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title></title>
<link href="images/stulogin/css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
document.onkeydown = function(e){ 
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) {
    	document.forms['Lgin'].submit();
     }
}
window.onload=function(){ 
	document.getElementById('j_username').focus();
}
</script>
</head>

<body class="login">
<%
String name = request.getParameter("name");
String password = request.getParameter("password");
if (name != null) {
	out.println("<script>");
	out.println("window.setTimeout('sub()',1000)");
	out.println("function sub(){");
	out.println("Lgin.j_username.value = '" + name + "';");
	out.println("Lgin.j_password.value = '" + password + "';");
	if(name.equals("xxzx"))
	{
	session.setAttribute(AbstractProcessingFilter.ACEGI_SECURITY_TARGET_URL_KEY,"http://localhost:8080/infoall/frame.htm");
	}
	else
	{
	session.setAttribute(AbstractProcessingFilter.ACEGI_SECURITY_TARGET_URL_KEY,"/falcon/index_copy.jsp");
	}
	out.println("Lgin.submit();");
	out.println("}");
	out.println("</script>");
}
request.getSession().setAttribute("state", "stu");
%>
<form action="<c:url value='j_acegi_security_check'/>"  name="Lgin" method="post">
<div class="login_box">
   <div class="login_logo"><img src="images/stulogin/images/login_logo.png"/></div>
   <div class="login_show">
    <ul>
      <li>
        <div class="login_left">身份证号</div><input type="text" class="login_one" name="j_username" id="j_username">
      </li>
      <li>
       <div class="login_left">准考证号</div> <input type="text" class="login_two" name="j_password">
      </li>
     
      <li style="padding-top:15px;">
      	<a href="#" class="login_dl" onclick = "document.forms['Lgin'].submit();">
      		<span class="color_white">登  陆</span>
      	</a>
      </li>
      <li><c:if test="${not empty param.login_error }"><font color="red" size="2"> 登录失败请重新输入</font></c:if></li>
    </ul>
  </div>
</div>
</form>
<!-- <div class="login_foot">主办单位：青岛市人力资源和社会保障局</div> -->
</body>
<%-- <form action="<c:url value='j_acegi_security_check'/>"  name="Lgin" method="post">
<div class="login_box">
   <div class="login_logo"><img src="images/login_logo.png"/></div>
   <div class="login_show">
    <ul>
      <li>
        <input type="text" class="login_one" onKeyDown="if(event.keyCode==13)event.keyCode=9;" name="j_username"  id="j_username_id" value="" />
      </li>
      <li>
        <input type="password"  class="login_two" onKeyDown="if(event.keyCode==13)event.keyCode=9;" name="j_password" id="j_password_id" value="">
      </li>
     
      <li style="padding-top:15px;"><a href="#" class="login_dl" onclick = "document.forms['Lgin'].submit();"><span class="color_white">登录</span></a>
      <a href="#" class="login_cz" onclick = "document.forms['Lgin'].reset();"><span class="color_white">重置</span></a></li>
     <li><c:if test="${not empty param.login_error }"><font color="red" size="2"> 登录失败请重新输入</font></c:if></li>
    </ul>
  </div>
</div>
</form>
<div class="login_foot">主办单位：青岛市人力资源和社会保障局职业鉴定中心</div>
</body> --%>
</html>