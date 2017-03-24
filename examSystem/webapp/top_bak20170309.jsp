<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.wondersgroup.kaoshi.bo.EKaoshi"%>
<%@page import="com.wondersgroup.popedom.dao.EUserMenusDao"%>
<%@ page import="com.wondersgroup.popedom.bo.EUser,com.wondersgroup.falcon.acegi.UserDetailsImpl,com.wondersgroup.falcon.acegi.AcegiUtil"%>
<%@page import="com.wondersgroup.falcon.paper.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>top</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="css/style.css" rel="stylesheet" type="text/css" />

<%
EUser user=((UserDetailsImpl)AcegiUtil.getUserDetails()).getUser();
//得到考试参数
String Flag=(String)session.getAttribute("Flag");
if(Flag==null){
	Flag="0";
}
//判断是否在考试
long zt=0;
EKaoshi ek=new EKaoshi();
EUserMenusDao dao=new EUserMenusDao();
String isst=dao.findst();

if(ek!=null){
	ek=dao.findZt();
	zt=ek.getZt();
} 

 %>
<script language="javascript">
function go_to(url){
	  parent.main.menu.location.href=url;
}
function logout(){
	parent.logout();
}
function startkaoshi(h,m,s){
	document.getElementById("timeh").innerHTML=h+":";
	document.getElementById("timem").innerHTML=m+":";		
	document.getElementById("times").innerHTML=s;	
}
</script>
</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="90"  style="background-image:url(imagesnew/banner_1.png) ;text-indent: 20px;font-size:60px;">
				<font color="blue">青岛市职业技能鉴定综合管理系统</font>
			</td>
			<td width="436px" height="90"  style="background-image:url(imagesnew/banner_2.png) ;background-repeat:no-repeat;">&nbsp;</td>
		</tr>
	</table>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="10" height="33" background="images/nav_background.jpg"></td>
			<td width="60%" align="left" background="images/nav_background.jpg" id="td_w">
				<table height="30" border="0" align="left" cellpadding="0" cellspacing="0">
					<%if(!Flag.equals("1")&&(zt!=1)){%>
					<tr>
						<td align="left">
							<c:forEach var="aBean" items="${eusermenus}" varStatus="status">
								<table width="100" border="0" align="left" cellpadding="0" cellspacing="0">
									<tr>
										<td width="*" align="center" class="nav">
											<a href="javascript:go_to('<c:out value="${aBean.menueurl}"/>?parentid=<c:out value="${aBean.id}"/>')"><c:out value="${aBean.menuname}" /></a>
										</td>
										<td width="2">
											<c:if test="${status.last != true}"> | </c:if>
										</td>
									</tr>
								</table>
								<c:if test="${status.first}">
									<script language="javascript">
							      		go_to('<c:out value="${aBean.menueurl}"/>?parentid=<c:out value="${aBean.id}"/>');
									</script>
								</c:if>
							</c:forEach>
						</td>
					</tr>
					<%} else if(!Flag.equals("1")){%>
					<tr>
						<td align="left">
							<table width="100" border="0" align="left" cellpadding="0" cellspacing="0">
								<tr>
									<td width="*" align="center" class="nav"><a href="javascript:go_to('leftMenuViewAction.action?parentid=1')">在线考试</a></td>
								</tr>
							</table> 
							<script language="javascript">
					      		go_to('leftMenuViewAction.action?parentid=1');
							</script>
						</td>
					</tr>
					<%} %>
				</table>
			</td>
			<td align="right" width="35%" background="images/nav_background.jpg" style="font-size: 14px;font-weight:500;">
				姓名：<font color="#0000ff"><%=user.getRealname() %></font>&nbsp; 
				<% if("1".equals(isst)){ %> 
					准考证号:<font color="#0000ff"><%=user.getPassword() %></font>&nbsp;
					考试剩余时间:<font id="remaintime">
								<font id="timeh" color="#0000ff">未开始考试</font>
								<font id="timem" color="#0000ff"></font>
								<font id="times" color="#0000ff"></font>
							</font>
				<%} %>
			</td>
			<td align="riht" width="6%" background="images/nav_background.jpg" style="font-size: 14px">
				&nbsp;&nbsp;&nbsp;
				<c:if test="${userStar=='1' }">
					<a href="#" onclick="logout();">退出</a>
				</c:if>
			</td>
		</tr>
	</table>
</body>
</html>
<script>
function runstar2(a,time,flag){
	if (1 == flag){runx=setInterval("run_a("+a+")",10)}
	else{runx=setInterval("run2_a("+a+")",10)}

}
function runover2(){
clearInterval(runx)
}
function run_a(a){
scrollx=frm_frdlist.document.body.scrollLeft
scrolly=frm_frdlist.document.body.scrollTop
scrollx=scrollx+a
frm_frdlist.window.scroll(scrollx,scrolly)
}
function run2_a(a){
scrollx=frm_navlist.document.body.scrollLeft
scrolly=frm_navlist.document.body.scrollTop
scrollx=scrollx+a
frm_navlist.window.scroll(scrollx,scrolly)
}
function x_down_a(theobject){
	object=theobject
	
	while(object.filters.alpha.opacity>60){
		object.filters.alpha.opacity+=-60
	}
}
function x_up_a(theobject){
	object=theobject
	while(object.filters.alpha.opacity<60){
		object.filters.alpha.opacity+=60
	}
}
function wback(){
	if(frm_frdlist.history.length==0){window.history.back()}
	else{frm_frdlist.history.back()}
}
 
    


</script>