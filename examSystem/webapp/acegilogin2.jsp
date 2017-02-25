<%@ page contentType = "text/html;charset=gb2312" %>
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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
<link href="inc/global.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>
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
%>
<form action="<c:url value='j_acegi_security_check'/>"  name="Lgin" method="post">
<div class="wrap">
<div class="loginbox">
	<table width="826" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td width="60" height="28"><b>用户名：</b></td>
	    <td width="202"><input tabindex="1" type="text" onKeyDown="if(event.keyCode==13)event.keyCode=9;" name="j_username"  id="j_username_id" value="" /></td>
	    <td width="56"><b>密　码：</b></td>
	    <td width="204"><div class="log_div"><input tabindex="2" type="password" onKeyDown="if(event.keyCode==13)event.keyCode=9;" name="j_password" id="j_password_id" value=""/><img src="images/min_jp.gif" /></span></div></td>
	    <td><a href="#" class="login_btn" onclick = "document.forms['Lgin'].submit();"></a></td><td><c:if test="${not empty param.login_error}"><font color="red" size="2"> 登录失败请重新输入</font></c:if></td>
	  </tr>
	</table>
</div>
</div> <!--wrap.end-->
 </form>
<script type="text/javascript">

document.Lgin.j_username.focus();
//-->
</script>
</div><!--loginbox.end-->
<div class="footer">
</body>
</html>

