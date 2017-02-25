<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.List,com.wondersgroup.technocracy.bo.Addexpert"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>专家查询</title>
<link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<script>

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
								</table> <s:form method="post" action="query.action" name="qForm">
									<table width="99%" border="0" cellpadding="0" cellspacing="0" style="margin-top:5px;  padding-right:16px;">
										<tr>
											<td height="8"><input type="hidden" name="currpage" value="1" /></td>
										</tr>
										<tr>
											<td nowrap="nowrap" width="10%" align="right">姓名:</td>
											<td width="15%" align="left"><input type="text" id="name" name="name"></td>
											<td nowrap="nowrap" width="10%" align="right">所属单位:</td>
											<td width="15%" align="left"><input type="text" id="org" name="org"></td>
											<td nowrap="nowrap" width="10%" align="right">所属委员会名称:</td>
											<td width="15%" align="left"><input type="text" id="committee" name="committee"></td>
											<td nowrap="nowrap" width="10%" align="right">专家类别:</td>
											<td width="15%" align="left"><select id="expertstyle" name="expertstyle">
													<option value="">请选择</option>
													<option value="1">考评专家</option>
													<option value="2">命题专家</option>
													<option value="3">监考老师</option>
													<option value="4">督导专家</option>
											</select></td>
											<td>&nbsp;</td>
										</tr>
									</table>
								</s:form>
								<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
									<tr>
										<td align="center"><input name="button" type="button" class="submit_2" onClick="doQuery()" value="查 询" /></td>
									</tr>
									<tr height="8px"></tr>
								</table></td>
						</tr>
					</table>
					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
						<tr>
							<td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" class="header7"></td>
													<td class="header8">查询结果</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb">
									<tr class="title_font">
										<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">操作 </span></td>
										<td width="7%" align="center" bgcolor="#C7E2F8"><span class="out">专家姓名 </span></td>
										<td width="7%" align="center" bgcolor="#C7E2F8"><span class="out">证件类型</span></td>
										<td width="7%" align="center" bgcolor="#C7E2F8"><span class="out">证件号码</span></td>
										<%--<td width="10%" align="center" bgcolor="#C7E2F8"><span
											class="out">出生日期</span>
										</td>--%>
										<td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">所属单位</span></td>
										<td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">擅长专业</span></td>
										<td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">联系电话</span></td>
										<%--<td width="7%" align="center" bgcolor="#C7E2F8"><span
											class="out">专家类别</span>
										</td>--%>
										<td width="7%" align="center" bgcolor="#C7E2F8"><span class="out">职称</span></td>
										<%--<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">职称</span></td>--%>
									</tr>
									<s:iterator value="expertinfo" id="user" status="state">
										<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
											<td align='center' class='num_font'><input type='button' value="查看详情" onclick="detailedInfo(<s:property value="#user.hzz001"/>)"></td>
											<td align='center' class='num_font'><s:property value="#user.name" /></td>
											<td align='center' class='num_font'><s:property value="#user.idstyle" /></td>
											<td align='center' class='num_font'><s:property value="#user.idnumber" /></td>
											<%--<td align='center' class='num_font'>
												<fmt:formatDate value="${user.date}" type="date" timeStyle="default"/>
											</td>--%>
											<td align='center' class='num_font'><s:property value="#user.org" /></td>
											<td align='center' class='num_font'><s:property value="#user.major" /></td>
											<td align='center' class='num_font'><s:property value="#user.phone" /></td>

											<%--<td align='center' class='num_font'><s:property
													value="#user.expertstyle" /></td>--%>
											<td align='center' class='num_font'><s:property value="#user.jtitle" /></td>
											<%--<td align='center' class='num_font'><s:property value="#user.jtitle"/></td>--%>
										</tr>
									</s:iterator>
								</table>
								<form action="query.action" name="chaForm" style="margin-top: 6px;">
									<input type="hidden" name="name"> <input type="hidden" name="org"> <input type="hidden" name="committee"> <input type="hidden" name="expertstyle">
									<elile:navigateBar navigateform="navigateform" actionName="query.action" formName="chaForm" />

								</form>
								<form action="detailquery.action" name="detail">
									<input id="eid" name="eid" type="hidden" />
								</form></td>
						</tr>
					</table>
				</div></td>
		</tr>
	</table>
</body>
</html>
