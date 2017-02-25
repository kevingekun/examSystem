<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.List,com.wondersgroup.technocracy.bo.Addexpert"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>专家替换</title>
<link href="<%=request.getContextPath()%>/newcss/style.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet"
	type="text/css" />
</head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<script>
 
function arrangeexpert(kcid,sjid){
	document.getElementById("teamid").value=kcid;
	document.getElementById("examid").value=sjid;
	document.redict.submit();
}
function doreset() {
	 
	document.Form.reset();
	
}
</script>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0"
		cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left"
					cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">专家替换</td>
						<td class="header3" width="24"><img
							src="<%=request.getContextPath()%>/newimages/content_right_bj.gif "
							width="24" height="22"></td>
					</tr>
				</table></td>
			<td width="53%" align="left"></td>
		</tr>
		<tr>
			<td colspan="2" valign="top"><div id="content1" class="borader">
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td class="borader3"><table width="100%" border="0"
									cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left"
												cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">查询条件</td>
												</tr>
											</table></td>
									</tr>
								</table> <s:form method="post" action="modifyexpert.action" name="Form">

									<table width="60%" border="0" align="center" cellpadding="0"
										cellspacing="0" style="margin-top:8px; padding-right:16px;">
										<tr height="23">
											<td nowrap="nowrap" align="center">鉴定批次号:</td>
											<td align="left"><input type="text" id="identifynumber"
												name="sj_mc"></td>

											<td align="center"><input type="submit"
												class="submit_2" id="search" value="查询" /></td>
											<td colspan="2"></td>
										</tr>
										<tr height="8px"><input type="hidden" name="currpage" value="1"/></tr>
									</table>
								</s:form></td>
						</tr>
					</table>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0" style="margin-top:12px;">
						<tr>
							<td class="borader3"><table width="100%" border="0"
									cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left"
												cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" class="header7"></td>
													<td class="header8">鉴定批次信息</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="1" class="table_list" id="tb" style="text-overflow:ellipsis;white-space:nowrap;">
									<tr class="title_font">

										<td width="6%" align="center" bgcolor="#C7E2F8"><span
											class="out">操作 </span></td>
										<td width="11%" align="center" bgcolor="#C7E2F8"><span
											class="out">试卷名称</span></td>
										<td width="7%" align="center" bgcolor="#C7E2F8"><span
											class="out">考场名称</span></td>
										<td width="7%" align="center" bgcolor="#C7E2F8"><span
											class="out">联系人</span></td>
										<td width="7%" align="center" bgcolor="#C7E2F8"><span
											class="out">联系电话</span></td>
										<td width="14%" align="center" bgcolor="#C7E2F8"><span
											class="out">地址</span></td>
										<td width="10%" align="center" bgcolor="#C7E2F8"><span
											class="out">考试人数</span></td>
									</tr>
									<s:iterator value="identifyInfo" id="user" status="state">
										<tr onMouseOver="this.className='td_over'"
											onMouseOut="this.className=''" id='r1'>
											<td align='center' class='num_font'><input type='button'
												value="专家维护"
												onclick="arrangeexpert(<s:property value="#user.teamid"/>,<s:property value="#user.examid"/>)"></td>
											<td align='center' class='num_font'><s:property
													value="#user.jdpch " /></td>
											<td align='center' class='num_font'><s:property
													value="#user.kcmc" /></td>

											<td align='center' class='num_font'><s:property
													value="#user.lxr" /></td>
											<td align='center' class='num_font'><s:property
													value="#user.lxdh" /></td>
											<td align='center' class='num_font'><s:property
													value="#user.dz" /></td>
											<td align='center' class='num_font'><s:property
													value="#user.ksrs" /></td>
										</tr>
									</s:iterator>
								</table> 
								<c:if test="${identifyInfo!=null}">
									<form action="modifyexpert.action" name="chaForm" method="post" style="margin-top: 6px;">
										<elile:navigateBar navigateform="navigateform"
											actionName="modifyexpert.action" formName="chaForm" />
									</form>
								</c:if>
								<s:form action="checkexpert.action" name="redict" >
									<input id="teamid" name="teamId" type="hidden" />
									<input id="examid" name="examid" type="hidden" />
								</s:form>
							</td>
						</tr>
					</table>
				</div></td>
		</tr>
	</table>
</body>
</html>
