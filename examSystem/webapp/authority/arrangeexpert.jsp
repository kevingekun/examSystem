<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.List,com.wondersgroup.technocracy.bo.Addexpert"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>安排专家</title>
<link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<script>

function tj(s){
	//alert(s);
	$.ajax({
		type:'post',
		url:'findcid.action?cid='+s,
		success:function(result){
			var data = eval(result);
			$("#cid").val(data[1]);
			$("#zjid").val(data[0]);
			// alert(data[0]);
		},
		error:function(){
			alert("error");
		}
	});
}
function queding(){
	var id = $("#zjid").val();
	if(id==""){
		alert("请选择一条记录！");
	}else
	document.arrangeForm.submit();
}
	//document.getElementById("HZZ900").value=s;
function detailedInfo(v){
	document.getElementById("eid").value=v;
	//document.detail.action="detailquery.action?eid="+v;
	document.detail.submit();
}

function doQuery() {
	document.qForm.submit();
}
</script>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">专家信息查询</td>
						<td class="header3" width="24"><img src="<%=request.getContextPath()%>/newimages/content_right_bj.gif " width="24" height="22"></td>
					</tr>
				</table></td>
			<td width="53%" align="left"></td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
				<div id="content1" class="borader">
					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">查询条件</td>
												</tr>
											</table>
										</td>
									</tr>
								</table> 
								<s:form method="post" action="query1.action" name="qForm">
									<input type="hidden" name="kcid" value="<c:out value="${teamId}"/>">
									<input type="hidden" name="ksid" value="<c:out value="${examId}"/>">
									<input type="hidden" name="currpage" value="1"/>
									<table width="80%" border="0" align="left" cellpadding="0" cellspacing="10" style="margin-top:8px; margin-left:100px; padding-right:16px;">
										<tr>
											<td nowrap="nowrap" width='10%' align="right">姓名:</td>
											<td width='15%' align="left"><input type="text" id="name" name="name"></td>
											<td nowrap="nowrap" width='10%' align="right">所属单位:</td>
											<td width='15%' align="left"><input type="text" id="org" name="org"></td>
											<td nowrap="nowrap" width='10%' align="right">所属委员会名称:</td>
											<td width='15%' align="left"><input type="text" id="committee" name="committee"></td>

											<td nowrap="nowrap" width="10%" align="right">专家类别:</td>
											<td width="15%">
												<select id="expertstyle" name="expertstyle">
													<option value="">请选择</option>
													<option value="1">考评专家</option>
													<option value="2">命题专家</option>
													<option value="3">监考老师</option>
													<option value="4">督导专家</option>
												</select>
											</td>
											<%--<td align="right"><input type="submit" class="submit_2"  value="查询" /></td>--%>
										</tr>
									</table>
								</s:form>
								<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
									<tr>
										<td align="center"><input name="button" type="button" class="submit_2" onClick="doQuery()" value="查 询" /></td>
									</tr>
									<tr height="8px"></tr>
								</table>
							</td>
						</tr>
					</table>
					
					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;">
						<tr>
							<td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" class="header7"></td>
													<td class="header8" width="80px">查询结果</td>
													<td align="left" class="header8">
														<input type="button" class="submit_2" value="确定" bgcolor="#C7E2F8" onclick="queding()" />
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_list" id="tb">
									<tr class="title_font">
										<td width="4%" align="center" bgcolor="#C7E2F8"><span class="out">选择 </span></td>
										<td width="4%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
										<td width="6%" align="center" bgcolor="#C7E2F8"><span class="out">专家姓名 </span></td>
										<td width="6%" align="center" bgcolor="#C7E2F8"><span class="out">证件类型</span></td>
										<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">证件号码</span></td>
										<%--<td width="10%" align="center" bgcolor="#C7E2F8"><span
											class="out">出生日期</span></td>--%>
										<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">所属单位</span></td>
										<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">擅长专业</span></td>
										<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">联系电话</span></td>
										<%--<td width="7%" align="center" bgcolor="#C7E2F8"><span
											class="out">专家类别</span></td>--%>
										<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">职称</span></td>
										<%--<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">职称</span></td>--%>
									</tr>
									<s:iterator value="expertinfo" id="user" status="state">
										<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
											<td align='center' class='num_font'><input type='radio' name="select" onclick="tj(<s:property value="#user.hzz001"/>)"></td>
											<td align='center' class='num_font'><s:property value="#state.index+1" /></td>
											<td align='center' class='num_font'><s:property value="#user.name" /></td>
											<td align='center' class='num_font'><s:property value="#user.idstyle" /></td>
											<td align='center' class='num_font'><s:property value="#user.idnumber" /></td>
											<%--<td align='center' class='num_font'>
												<fmt:formatDate value="${user.date}" type="date" timeStyle="default"/></td>--%>
											<td align='center' class='num_font'><s:property value="#user.org" /></td>
											<td align='center' class='num_font'><s:property value="#user.major" /></td>
											<td align='center' class='num_font'><s:property value="#user.phone" /></td>

											<%--<td align='center' class='num_font'><s:property
													value="#user.expertstyle" /></td>
													--%>
											<td align='center' class='num_font'><s:property value="#user.jtitle" /></td>
											<%--<td align='center' class='num_font'><s:property value="#user.jtitle"/></td>--%>
										</tr>
									</s:iterator>
								</table>
								<c:if test="${expertinfo!=null}">
										<form action="query1.action" name="QueryForm" style="margin-top:6px;">
											<input type="hidden" name="kcid" value="<c:out value="${teamId}"/>">
											<input type="hidden" name="ksid" value="<c:out value="${examId}"/>">
											<input type="hidden" name="name">
											<input type="hidden" name="org">
											<input type="hidden" name="committee">
											<input type="hidden" name="expertstyle">
											<elile:navigateBar navigateform="navigateform" actionName="query1.action" formName="QueryForm" />
										</form>
									</c:if>
								<form method="post" action="arangequery.action" name="arrangeForm" >
									<input id="Kcid" name="Kcid" type="hidden" value="<c:out value="${kcid}"/>" /> 
									<input id="Ksid" name="Ksid" type="hidden" value="<c:out value="${ksid}"/>" /> 
									<input id="cid" name="cid" type="hidden" /> 
									<input id="zjid" name="zjid" type="hidden" /> 
									<input id="zw" name="zw" type="hidden" value="<c:out value="${nduty}"/>" /> 
									<input id="bz" name="bz" type="hidden" value="<c:out value="${nremark}"/>" />
								</form>
						</tr>
					</table>
			</div>
		</td>
		</tr>
	</table>
</body>
</html>
