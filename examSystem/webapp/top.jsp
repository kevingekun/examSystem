<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.wondersgroup.kaoshi.bo.EKaoshi"%>
<%@page import="com.wondersgroup.popedom.dao.EUserMenusDao"%>
<%@ page import="com.wondersgroup.popedom.bo.EUser,com.wondersgroup.falcon.acegi.UserDetailsImpl,com.wondersgroup.falcon.acegi.AcegiUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>top</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">

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
<style type="text/css">
.td_top{
	width:100%;
	word-break:keep-all;             /* 不换行 */
	white-space:nowrap;            /* 不换行 */
	/* overflow:hidden;                  /* 内容超出宽度时隐藏超出部分的内容 */
	/*text-overflow:ellipsis;            /* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/ 
}

</style>
</head>
<body>
<div class="container-fluid">
	<div class="row">
		<table width="100%" style="table-layout:fixed;">
			<tr>
				<td class="td_top" height="90" style="background-image:url(imagesnew/banner_1.png) ;text-indent: 20px;font-size:60px;font-family:arial">
					<font color="blue">青岛市职业技能鉴定综合管理系统</font>
				</td>
				<td width="436px" height="90" style="background-image:url(imagesnew/banner_2.png) ;background-repeat:no-repeat;">&nbsp;</td>
			</tr>
		</table>
	</div>
	<div class="row" style="background-image:url(images/nav_background.jpg);">
		<div class="col-md-7">
			<div class="btn-group">
			   <%if(!Flag.equals("1")&&(zt!=1)){%>
					<c:forEach var="aBean" items="${eusermenus}" varStatus="status">
						<button class="btn btn-link" type="button" onclick="go_to('<c:out value="${aBean.menueurl}"/>?parentid=<c:out value="${aBean.id}"/>')">
							<font color="#004488"><c:out value="${aBean.menuname}" /></font>
						</button>
						<c:if test="${status.first}">
							<script language="javascript">
					      		go_to('<c:out value="${aBean.menueurl}"/>?parentid=<c:out value="${aBean.id}"/>');
							</script>
						</c:if>
					</c:forEach>
				<%} else if(!Flag.equals("1")){%>
					<button class="btn btn-link" type="button" onclick="go_to('leftMenuViewAction.action?parentid=1')">
						<font color="#004488">在线考试</font>
					</button>
					<script language="javascript">
			      		go_to('leftMenuViewAction.action?parentid=1');
					</script>
				<%} %>
			</div>
		</div>
		<div class="col-md-5" style="font-size: 16px;">
			<div style="float:right;padding-top:4px;">
				<c:if test="${userStar=='1' }">
					&nbsp;&nbsp;&nbsp;<button class="btn btn-warning btn-xs" onclick="logout();">退出</button>
				</c:if>
			</div>
			<div style="padding-top:5px;float:right">
				姓名：<font color="#0000ff"><%=user.getRealname() %></font>&nbsp; 
				<% if("1".equals(isst)){ %> 
					准考证号:<font color="#0000ff"><%=user.getPassword() %></font>&nbsp;
					考试剩余时间:<font id="remaintime">
								<font id="timeh" color="#0000ff">未开始考试</font>
								<font id="timem" color="#0000ff"></font>
								<font id="times" color="#0000ff"></font>
							</font>
				<%} %>
			</div>
		</div>
	</div>
</div>
</body>
</html>
