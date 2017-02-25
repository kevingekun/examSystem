<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
ProfessionBean professionBean = new ProfessionBean();//工种
List<Tjobsubject> professions = professionBean.getDistinctProfessions();
request.setAttribute("professions", professions);

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>专业关联工种</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
function selectall(){
	var check=document.getElementById("checkAll");
    var values = document.getElementsByName("deleteid");  
      for (var i = 0; i < values.length; i++)
       	values[i].checked = check.checked;
}
function sbt()
{
    var row =0;
    var checkedid="";
	var values = document.getElementsByName("checkedid");
	var zymc = document.getElementById("zymc").value;
	zymc2 = zymc.replace(/\s/g,"");
	if(zymc==zymc2&&zymc2!=""){
		for (var i = 0; i < values.length; i++){
	       	if(values[i].checked == true){
	       		checkedid+=values[i].value+",";
	       	   row++;
	       	}
	    }
	    if(row>0)
	    {
	    	checkedid=checkedid.substring(0,checkedid.length-1);
			document.aForm.action="relateMajorAndProfession.action?checkid="+checkedid;
			document.aForm.submit();
	    }else{
	    	alert("您还没有选择工种！");
	    }
	}else{
		alert("专业名称不能为空或含有空格！")
	}
	
}
</script>
<script type="text/javascript">
<%
	String info = (String)request.getAttribute("info");
	if(info!=null){ %>
		alert('<%=info%>');
	<%}%>
</script>
</head>

<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">专业关联工种</td>
						<td class="header3" width="24"><img src="<%=request.getContextPath()%>/newimages/content_right_bj.gif " width="24" height="22"></td>
					</tr>
				</table></td>
			<td width="53%" align="left"></td>
		</tr>
		<tr>
			<td colspan="2" valign="top"><div id="content1" class="borader">
					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">专业</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<form id="aForm" name="aForm" method="post">
									<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
										<tr>
											<td height="8"></td>
										</tr>
										<tr>
											<td height="8">&nbsp;</td>
											<td width="13%" height="28" align="right">专业名称：</td>
											<td width="18%" align="left"><input id="zymc" name="zymc" /> </td>
											<td align="left">
												<input type="button" class="submit_2" onClick="javascript:sbt();" value="关 联"/>
											</td>
											<td height="8">&nbsp;</td>
										</tr>
										<tr>
											<td height="8"></td>
										</tr>
									</table>
								</form>
							</td>
						</tr>
					</table>
					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
						<tr>
							<td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">工种 &nbsp;&nbsp;<!-- <a href="javascript:void();" onClick="sbt();" ><font color=red>关联</font></a> -->&nbsp;&nbsp;&nbsp;</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb">
									<tr class="title_font">
										<td width="3%" align="center" bgcolor="#C7E2F8"><input id="checkAll" name="all1" type="checkbox" onClick="selectall()"></td>
										<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
										<td width="50%" align="center" bgcolor="#C7E2F8"><span class="out">工种名称</span></td>
										<td width="40%" align="center" bgcolor="#C7E2F8"><span class="out">工种编码</span></td>
									</tr>
									<c:forEach var="aBean" items="${professions}" varStatus="status">
										<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
											<td align='center' class='num_font'><input type="checkbox" id="checkbox" name="checkedid" value="<c:out value='${aBean.id_job}'/>" /></td>
											<td align='center' class='num_font'><c:out value="${status.index+1}" /></td>
											<td align='center' class='num_font'><c:out value="${aBean.jobname}" /></td>
											<td align='center' class='num_font'><c:out value="${aBean.id_job}" /></td>
										</tr>
									</c:forEach>
								</table> 
								<%-- <c:if test="${professions!= null }">
									<s:form action="answerpaperpmquery.action" name="answerpaperpmqueryForm" method="post">
										<elile:navigateBar navigateform="navigateform" actionName="answerpaperpmquery.action" formName="answerpaperpmqueryForm" />
									</s:form>
								</c:if> --%>
							</td>
						</tr>
					</table>

				</div></td>
		</tr>
	</table>

</body>
</html>
