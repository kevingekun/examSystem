<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
<title>答卷查询</title>
<link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath()%>/js/dateMy97/WdatePicker.js"></script>
<script language="JavaScript" type="text/JavaScript">
	function modify(stid) {

		document.aForm.action = "epaperquestion.action?sjid=" + stid;
		document.aForm.myaction.value = "modifyload";
		document.aForm.submit();
	}
	function del() {
		var row = 0;
		var t = window.document.getElementsByName('deleteid');
		for ( var i = 0; i < t.length; i++) {
			if (t[i].type == 'checkbox' && t[i].checked == true) {
				row = row + 1;
			}
		}
		if (row < 1) {
			alert("请选择要删除的记录！");
			return false;
		}
		var tt = confirm("确定要删除吗？"); //确认是否删除
		if (tt) {
			document.aForm.action = "QuestionServlet";
			document.aForm.myaction.value = "del";
			document.aForm.submit();
		} else {
			return false;
		}

	}

	function doQuery() {

		document.aForm.submit();
	}

	function jumpViewPaper(sjid) {
		window.location.href = "previewPaperAction.action?paperid=" + sjid;
	}
	//复制试卷
	function copyPaper(sjid) {
		window.location.href = "copyPaperAction.action?paperid=" + sjid;
	}
	//更改试卷状态
	function changeState(sjid, paperState) {
		window.location.href = "changeState.action?paperid=" + sjid
				+ "&paperState=" + paperState;
	}
</script>
</head>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">答卷查询</td>
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
								</table>
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<s:form action="answerPaperQuerryAction" name="aForm" method="POST">
										<s:hidden name="djSyzt" />
										<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1">
											<tr>
												<td height="8" colspan="7"></td>
											</tr>
											<tr>
												<td width="13%" height="28" align="right">试卷名称：</td>
												<td width="18%" align="left"><s:textfield name="sjMc" /></td>
												<td width="20%" align="right">答卷时间：</td>
												<td width="30%"><input type="text" value='<s:date name="djsj" format="yyyy-MM-dd"/>' class="Wdate" id="djsj" name="djsj" onclick="WdatePicker()" /> 至 <input type="text" value='<s:date name="djsjend" format="yyyy-MM-dd"/>' class="Wdate" id="djsjend" name="djsjend"
													onclick="WdatePicker()" />
											</tr>
											<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
												<tr>
													<td align="center"><input type="submit" class="submit_2" value="查 询" /></td>
												</tr>
												<tr>
													<td height="8" colspan="7"></td>
												</tr>
											</table>
										</table>
									</s:form>


									<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
										<tr>
											<td class="borader3">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
																<tr>
																	<td align="left" valign="middle" class="header7"></td>
																	<td class="header8">查询结果</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
												<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb">
													<tr class="title_font">
														<td width="6%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
														<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">试卷名称</span></td>
														<td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">答卷人员</span></td>
														<td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">考试类型</span></td>
														<td width="14%" align="center" bgcolor="#C7E2F8"><span class="out">答卷时间</span></td>
														<td width="14%" align="center" bgcolor="#C7E2F8"><span class="out">结束时间</span></td>
														<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">状态</span></td>
														<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">得分</span></td>
													</tr>
													<s:iterator value="answerpapers" id="answerpaper" status="status">
														<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
															<td align='center' class='num_font'><s:property value="#status.index+1" /></td>
															<td align='center' class='num_font'><s:url id="myPaperQuestionActionaction" action="answerPaperQuestionAction.action">
																	<s:param name="myPaperId" value="#answerpaper.djId" />
																</s:url> <s:a href="%{#myPaperQuestionActionaction}">
																	<s:property value="#answerpaper.epapers.sjMc" />
																</s:a></td>
															<td align='center' class='num_font'><s:property value="#answerpaper.djRymc" /></td>
															<td align='center' class='num_font'><c:choose>
																	<c:when test="${answerpaper.epapers.sjKslx == '1'}">鉴定类考试</c:when>
																	<c:when test="${answerpaper.epapers.sjKslx == '5'}">其他类考试</c:when>
																	<c:otherwise></c:otherwise>
																</c:choose></td>
															<td align='center' class='num_font'><s:date name="#answerpaper.djKssj" format="yyyy-MM-dd HH:mm" /></td>
															<td align='center' class='num_font'><s:date name="#answerpaper.djJssj" format="yyyy-MM-dd HH:mm" /></td>
															<td align='center' class='num_font'>
																<!-- 暂停使用，有主观题时可以启用 -->
																<%-- <s:if test="#answerpaper.djSyzt==0">
																	<font color="green">待审阅</font>
																</s:if> <s:elseif test="#answerpaper.djSyzt==1">
																	<font color="red">待二阅</font>
																</s:elseif> <s:else>
																	<font color="orange">审阅完成</font>
																</s:else> --%>
																<font color="orange">正常</font>
															</td>
															<td align="center"><s:property value="#answerpaper.djZf" /></td>
														</tr>
													</s:iterator>
												</table>
												<c:if test="${answerpapers!=null }">
													<form name="answerActionform" method="post" action="answerPaperQuerryAction" style="margin-top: 6px;">
														<s:hidden name="djSyzt" />
														<s:hidden name="sjMc" />
														<s:hidden name="djsj"></s:hidden>
														<elile:navigateBar navigateform="navigateform" actionName="answerPaperQuerryAction.action" formName="answerActionform" />
													</form>
												</c:if>
											</td>
										</tr>

									</table>
									
								</table>
							</td>
						</tr>
					</table>
				</div></td>
		</tr>
	</table>
</body>
</html>

