<%@ page language="java" contentType = "text/html;charset=gbk" %>
<%@ page import="org.hibernate.Query,org.hibernate.Session,com.wondersgroup.falcon.persistence.HibernateUtil" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<% 
String callid = request.getParameter("callid");
if(callid!=null&&!callid.equals("0000")){
	session.setAttribute("callid",callid);
}else if(callid!=null&&callid.equals("0000")){
	session.setAttribute("hm",null);
	session.removeAttribute("callid");
}
%>
<title>≈‡—µøº ‘œµÕ≥</title>

 <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<script type="text/JavaScript">
//<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
//-->
</script>

<script>
function send_sms()
{
   var mytop=screen.availHeight-246;
   var myleft=0;
   window.open("sms.htm","send_sms","height=200,width=350,status=0,toolbar=no,menubar=no,location=no,scrollbars=yes,top="+mytop+",left="+myleft+",resizable=yes");
}
function logout(){
	//document.getElementById("lgout").submit();
	//$("#lgout").submit();
	//window.location.href="logout.action";
	window.location.href="j_acegi_logout";
}
</script>
<form action="<c:url value='/j_acegi_logout'/>"  name="logout" id="logout" method="POST"></form>
<!-- <form action="logout.action" name="lgout" id="lgout" method="post"></form> -->
<input type=hidden name="gdrecallno" id="gdrecallno" value="">
<input type=hidden name="gdcaller" id="gdcaller" value="">
<frameset rows="150,*" frameborder="NO" border="0" framespacing="0">
  <frame src="<%=request.getContextPath() %>/topMenuViewAction.action" id="topFrame" name="topFrame" scrolling="NO" noresize >
  <frame  name="main"  id="main"  target="main"  src="main.html"  scrolling="auto" width="100%">      
</frameset> 




	