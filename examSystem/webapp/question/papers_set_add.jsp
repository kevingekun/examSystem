<%@page language="java" contentType="text/html;charset=gbk"%>
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
<title>组卷</title>
<link rel="stylesheet" type="text/css" href="newcss/style.css" />
<link rel="stylesheet" type="text/css" href="inc/all.css" />
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript">
	function showPaper(){
		$("#questionadd").hide();
		$("#questionadd_written").hide();
		$("#paperadd").show();
	}
	function showQuestionOfCpt(){
		$("#paperadd").hide();
		$("#questionadd_written").hide();
		$("#questionadd").show();
	}
	function showQuestionOfWrt(){
		$("#paperadd").hide();
		$("#questionadd").hide();
		$("#questionadd_written").show();
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
							<a style="color: white;" href="javascript:void(0)" onclick="showPaper()">试卷信息录入</a>
						</td>
						<td class="header3" width="24">
							<img src="newimages/content_right_bj.gif " width="24" height="22">
						</td>
						<td class="header2">
							<a style="color: white;" href="javascript:void(0)" onclick="showQuestionOfCpt()">机考试题录入</a>
						</td>
						<td class="header3" width="24">
							<img src="newimages/content_right_bj.gif " width="24" height="22">
						</td>
						<td class="header2">
							<a style="color: white;" href="javascript:void(0)" onclick="showQuestionOfWrt()">笔答试题录入</a>
						</td>
						<td class="header3" width="24">
							<img src="newimages/content_right_bj.gif " width="24" height="22">
						</td>
					</tr>
				</table></td>
			<td width="53%" align="left"></td>
		</tr>
		<tr id="paperadd">
			<td colspan="2" valign="top">
				<div id="content1" class="borader">
					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td class="borader3">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">试卷录入</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="2"></td>
									</tr>
								</table>
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;">
									<tr>
										<td align="center" valign="middle" class="num_font">
											<table width="98%" height="33" border="0" align="center" cellpadding="0" cellspacing="2" bgcolor="#D2E8FF" style="border-width:1px; border-style:solid; border-color:#8DD6F4; ">
												<tr>
													<td bgcolor="#FFFFFF" width="25%">
														<div align="center">
															<iframe name="LeftTree1" src="question/papers_set_add_left.jsp" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" width="100%" height="600px"></iframe>
														</div>
													</td>
													<td bgcolor="#FFFFFF" width="75%"><iframe name="MainWin1" src="question/papers_set_add_right.jsp" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" width="100%" height="600px"> </iframe></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>

		<tr id="questionadd" style="display: none;">
			<td colspan="2" valign="top">
				<div id="content1" class="borader">
					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td class="borader3">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">机考试题录入</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="2"></td>
									</tr>
								</table>
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;">
									<tr>
										<td align="center" valign="middle" class="num_font">
											<table width="98%" height="33" border="0" align="center" cellpadding="0" cellspacing="2" bgcolor="#D2E8FF" style="border-width:1px; border-style:solid; border-color:#8DD6F4; ">
												<tr>
													<td bgcolor="#FFFFFF" width="25%">
														<div align="center">
															<iframe name="LeftTree2" src="question/questions_set_add_left.jsp" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" width="100%" height="600px"></iframe>
														</div>
													</td>
													<td bgcolor="#FFFFFF" width="75%"><iframe name="MainWin2" src="question/questions_set_add_right.jsp" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" width="100%" height="600px"> </iframe></td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr id="questionadd_written" style="display: none;">
			<td colspan="2" valign="top">
				<div id="content1" class="borader">
					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td class="borader3">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">笔答试题录入</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="2"></td>
									</tr>
								</table>
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;">
									<tr>
										<td align="center" valign="middle" class="num_font">
											<table width="98%" height="33" border="0" align="center" cellpadding="0" cellspacing="2" bgcolor="#D2E8FF" style="border-width:1px; border-style:solid; border-color:#8DD6F4; ">
												<tr>
													<td bgcolor="#FFFFFF" width="25%">
														<div align="center">
															<iframe name="LeftTree3" src="question/questions_set_add_written_left.jsp" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" width="100%" height="600px"></iframe>
														</div>
													</td>
													<td bgcolor="#FFFFFF" width="75%"><iframe name="MainWin3" src="question/questions_set_add_written_right.jsp" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" width="100%" height="600px"> </iframe></td>
												</tr>
											</table>
										</td>
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
