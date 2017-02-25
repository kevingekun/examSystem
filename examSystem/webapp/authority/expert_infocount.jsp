<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>专家统计分析</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript" src="js/echarts/echarts-all.js"></script>
<script type="text/javascript" src="js/echarts/chart.js"></script>

</head>

<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:20px; margin-left:10px; ">
		<tr>
			<td align="left">
				<table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">专家统计分析</td>
						<td class="header3" width="24"><img src="<%=request.getContextPath()%>/newimages/content_right_bj.gif" width="24" height="22"></td>
					</tr>
				</table>
			</td>
			<td align="left"></td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
				<div id="content1" class="borader" style="text-align:center">
					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td class="borader3">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">
														<a id="btn_age" class="infocount" href="javascript:void(0)" onclick="ageClick()">年龄统计</a>
														<!-- <a id="btn_academic" href="javascript:void(0)">学历统计</a>
														<a id="btn_category" href="javascript:void(0)">类别统计</a> -->
														<a id="btn_usenum" class="infocount" href="javascript:void(0)" onclick="usenumClick()">安排频次</a>
														<a id="btn_usenum" class="infocount" href="javascript:void(0)" >专业</a>
														<a id="btn_usenum" class="infocount" href="javascript:void(0)" >职称</a>
														<a id="btn_usenum" class="infocount" href="javascript:void(0)" >性别</a>
														<a id="btn_usenum" class="infocount" href="javascript:void(0)" >学历</a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<div style="width: 800px; height: 350px;margin-top: 6px;  MARGIN-RIGHT: auto; MARGIN-LEFT: auto;">
									<div id="ageCount" style="width: 640px; height: 350px;"></div>
									<div id="info" class="info">
										<div class="ageinfo">
											最大年龄：<a id="maxage" class="age">1</a>&nbsp;岁
										</div>
										<div class="ageinfo">
											最小年龄：<a id="minage" class="age">1</a>&nbsp;岁
										</div>
										<div class="ageinfo">
											平均年龄：<a id="avgage" class="age">1</a>&nbsp;岁
										</div>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
