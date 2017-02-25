<%@page import="java.util.List,com.wondersgroup.popedom.bo.ExamStaff"%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>公告</title>
<link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css" />
</head>
<%--<%
List<ExamStaff> list=(List<ExamStaff>)request.getAttribute("infolist");
%>
--%>
<script>
function addmgg(){
	
	document.maintainadd.submit();
}

function modifygg(v){
	//alert(v);
	document.getElementById("sj_id").value=v;
	 
	document.mdForm.submit();
	
	
	
}
</script>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">公告维护</td>
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
													<td class="header8">查询条件</td>
												</tr>
											</table></td>
									</tr>
								</table> <s:form method="post" action="ggmaintain.action">
									<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
										<tr>
											<td width="13%" height="28" align="center">试卷名称：</td>
											<td width="18%" align="center"><s:textfield name="sj_mc" id="sj_mc" size="25" /></td>
											<td width="3%"><input type="hidden" name="currpage" value="1"/></td>
											<td align="left"><input name="button_submit" type="submit" class="submit_2" value="查询" /></td>
										</tr>
									</table>

								</s:form></td>
						</tr>
					</table>

					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
						<tr>
							<td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" class="header7"></td>
													<td class="header8" width='50px'>查询结果</td>
													<td class="header8"><input name="button_add" type="button" class="submit_2" value="新增" onclick="addmgg()" /></td>
												</tr>
											</table></td>
									</tr>
								</table>
								<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" class="table_list" id="tb">
									<tr class="title_font">
										<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">选择</span></td>
										<td width="30%" align="center" bgcolor="#C7E2F8"><span class="out">试卷名称</span></td>
										<td width="30%" align="center" bgcolor="#C7E2F8"><span class="out">考生须知</span></td>

									</tr>
									<s:iterator value="mggs" id="gonggao" status="state">
										<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
											<td align='center' class='num_font'><input type='button' value="修改" onclick="modifygg(<s:property value="#gonggao.sj_id"/>)"></td>

											<td align='center' class='num_font'><s:property value="#gonggao.identifynumber" /></td>

											<td align='center' class='num_font'><s:property value="#gonggao.hzz092" /></td>


										</tr>
									</s:iterator>

								</table> 
								<s:form action="ggmaintain.action" name="maForm" method="post">
									<s:hidden name="sj_mc"></s:hidden>
									<elile:navigateBar navigateform="navigateform" actionName="ggmaintain.action" formName="maForm" />
								</s:form> 
								<s:form action="maintainadd.action" name="maintainadd" method="post">

								</s:form> 
								<s:form action="modifies.action" name="mdForm" method="post">

									<td align='center' class='num_font'><input id="sj_id" name="sj_Id" value="" type="hidden" /></td>
								</s:form></td>
						</tr>

					</table>

				</div></td>
		</tr>
	</table>
</body>
</html>
