<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>题库维护</title>
<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript">
function showTkUpdate(){
	$("#tkCategoryChange").hide();
	$("#subjectAdd").hide();
	$("#tkUpdate").show();
}
function showTkCategoryChange(){
	$("#tkUpdate").hide();
	$("#subjectAdd").hide();
	$("#tkCategoryChange").show();
}
function showTkBatchAdd(){
	$("#tkUpdate").hide();
	$("#subjectAdd").hide();
	$("#tkBatchAdd").show();
}
function showSubject(){
	$("#tkUpdate").hide();
	$("#tkCategoryChange").hide();
	$("#subjectAdd").show();
}

</script>
</head>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">
							<a style="color: white;" href="javascript:void(0)" onclick="showTkUpdate()">题库维护</a>
						</td>
						<td class="header3" width="24">
							<img src="newimages/content_right_bj.gif " width="24" height="22">
						</td>
						<td class="header2">
							<a style="color: white;" href="javascript:void(0)" onclick="showTkCategoryChange()">题库类别维护</a>
						</td>
						<td class="header3" width="24">
							<img src="newimages/content_right_bj.gif " width="24" height="22">
						</td>
						<td class="header2">
							<a style="color: white;" href="javascript:void(0)" onclick="showSubject()">工种维护</a>
						</td>
						<td class="header3" width="24">
							<img src="newimages/content_right_bj.gif " width="24" height="22">
						</td>
						<!-- <td class="header2">
							<a style="color: white;" href="javascript:void(0)" onclick="showTkBatchAdd()">鉴定要素导入</a>
						</td>
						<td class="header3" width="24">
							<img src="newimages/content_right_bj.gif " width="24" height="22">
						</td> -->
					</tr>
				</table></td>
			<td width="53%" align="left"></td>
		</tr>
		<tr id="tkUpdate">
			<td colspan="2" valign="top">
				<div id="content1" class="borader">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td class="borader3">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">题库管理</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td height="8" colspan="4"></td>
									</tr>
									<tr>
										<iframe name="Topjdys" src="question/testLibrary_top.jsp" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" width="100%" height="50px"></iframe>
									</tr>
									<tr>
										<iframe id="Bottomjdys" name="Bottomjdys"  marginwidth="0" marginheight="0" src="question/testLibrary_bottom.jsp" frameborder="0" scrolling="auto" width="1122" height="310px"></iframe>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr id="tkCategoryChange" style="display: none;">
			<td colspan="2" valign="top">
				<div id="content1" class="borader">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td class="borader3">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">题库类别新增</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td height="6" colspan="4"></td>
									</tr>
									<tr>
										<iframe name="categoryChange" src="question/testLibrary_categoryChange.jsp" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" width="100%" height="100px"></iframe>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr id="subjectAdd" style="display: none;">
			<td colspan="2" valign="top">
				<div id="content1" class="borader">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td class="borader3">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">工种新增</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td height="6" colspan="4"></td>
									</tr>
									<tr>
										<iframe name="categoryChange" src="question/subject_add.jsp" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" width="100%" height="100px"></iframe>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr id="tkBatchAdd" style="display: none;">
			<td colspan="2" valign="top">
				<div id="content1" class="borader">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td class="borader3">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">批量导入</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td height="6" colspan="4"></td>
									</tr>
									<tr>
										<iframe name="batch_add" src="question/testLibrary_batch_add.jsp" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" width="100%" height="330px"></iframe>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
