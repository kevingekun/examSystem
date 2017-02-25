<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<html>
<head>
<title>试题信息列表</title>
<link href="<%=request.getContextPath()%>/newcss/style.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet"
	type="text/css" />
<script language="JavaScript" type="text/JavaScript"
	src="<%=request.getContextPath()%>/js/dateMy97/WdatePicker.js"></script>

<style>
<!--
.hh {
	width: 200px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap
}
-->
</style>
<script type="text/javascript">
//导出excel
function excel(paperid){
	window.location.href="wrongPercentExcelAction_xf.action?paperid="+paperid;
}
</script>
</head>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0"
		cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left"
					cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<%-- <td class="header2">所属试卷：<s:property value="epapers.sjMc" /></td> --%>
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
						cellspacing="0" style="margin-top:12px;">
						<tr>
							<td class="borader3"><table width="100%" border="0"
									cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left"
												cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">结果</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="1" class="table_list" id="tb">
									<tr class="title_font">
										<td align="center" bgcolor="#C7E2F8"><span class="out">试题题目</span></td>
										<td align="center" bgcolor="#C7E2F8"><span class="out">试题类型</span></td>
										<td align="center" bgcolor="#C7E2F8"><span class="out">试题错误率</span></td>
									</tr>
									<s:iterator value="wpList" id="wpl" status="index">
										<tr onMouseOver="this.className='td_over'"
											onMouseOut="this.className=''" id='r1'>
											<td align='center' class='num_font'>
												<a href="#" class="hh" style="display:block;width:840px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<s:property value="#wpl.st_tg" />">
												<s:property value="#wpl.st_tg" /></a></td>
											<td align='center' class='num_font'>
												<%-- <s:property value="#wpl.st_lx" /> --%>
												<s:if test="#wpl.st_lx==2">单选题</s:if>
												<s:if test="#wpl.st_lx==3">判断题</s:if>
												<s:if test="#wpl.st_lx==8">多选题</s:if>
											</td>
											<td align='center' class='num_font'><s:property
													value="#wpl.wrong_persent" />%</td>
										</tr>
									</s:iterator>
								</table>
								<table width="98%" border="0" align="center" cellpadding="0"
									cellspacing="0" style="margin-top:8px; padding-right:16px;">
									<tr>
										<td align="center"><button class="submit_2" onclick="excel(<s:property value="paperId"/>);">导出excel</button></td>
									</tr>
								</table></td>
						</tr>
					</table>
	</table>
</body>
<script type="text/javascript">
	
</script>
</html>

