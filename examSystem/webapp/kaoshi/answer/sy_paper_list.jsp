<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib uri="elile.tld" prefix="elile"%>

<html>
<head>
<title>答卷审阅</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=request.getContextPath()%>/newcss/style.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet"
	type="text/css" />
<script language="JavaScript" type="text/JavaScript"
	src="<%=request.getContextPath()%>/js/dateMy97/WdatePicker.js"></script>
<script language="JavaScript" type="text/JavaScript">
	function modify(stid) {

		document.aForm.action = "epaperquestion.action?sjid=" + stid;
		document.aForm.myaction.value = "modifyload";
		document.aForm.submit();
	}
	function commit() {
		var row = 0;
		var answerpaperid = "";
		var values = document.getElementsByName("deleteid");
		for ( var i = 0; i < values.length; i++) {
			if (values[i].checked == true) {
				answerpaperid += values[i].value + ",";
				row++;
			}
		}
		if (row > 0) {
			answerpaperid = answerpaperid
					.substring(0, answerpaperid.length - 1);
			var url = "commitQuestion.action?answerpaperid=" + answerpaperid;
			// window.location.href="defenQuestion.action?answerpaperid=&DjSyzt=2";
			window.location.href = url;
			return false;
		} else {
			alert("您还没有选择");
			return false;
		}
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
	<table width="99%" border="0" align="right" cellpadding="0"
		cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left"
					cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">答卷审阅</td>
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
								</table>

								<table width="99%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<s:form action="tosypaperAction" name="aForm" method="POST">
										<s:hidden name="djSyzt"></s:hidden>
										<tr>
											<td height="8" colspan="7"></td>
										</tr>
										<tr>
											<td width="13%" height="28" align="right">试卷名称：</td>
											<td width="18%" align="left"><s:textfield name="sjMc"></s:textfield></td>
											<td width="13%" align="right">答卷时间：</td>
											<td width="18%"><input type="text"
												value='<s:property value="kksj"/>' class="Wdate" id="kksj"
												name="kksj" onclick="WdatePicker()" /></td>
										</tr>
										

										<table width="98%" border="0" align="center" cellpadding="0"
											cellspacing="0" style="margin-top:8px; padding-right:16px;">
											<tr>
												<td align="center"><input type="submit"
													class="submit_2" value="查 询" /></td>
											</tr>
											<tr>
												<td height="8" colspan="7"></td>
											</tr>
										</table>
									</s:form>
								</table> <s:hidden name="djSyzt" /> <s:hidden name="answerpaperid" />
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
																<td class="header8">试题查询列表 <c:if test="${flag!=1}">
																		<a href="#" onClick="commit()"><font color=red>批量提交</font></a>&nbsp;</c:if>
																</td>

															</tr>
														</table></td>
												</tr>
											</table>
											<table width="100%" border="0" align="center" cellpadding="0"
												cellspacing="1" class="table_list" id="tb">
												<tr class="title_font">
													<c:if test="${flag!=1}">
														<td align="center" bgcolor="#C7E2F8"><span
															class="out"><input id="checkAll" name="all1"
																type="checkbox" onClick="selectall()"></span></td>
													</c:if>
													<td align="center" bgcolor="#C7E2F8"><span class="out">序号
													</span></td>
													<td align="center" bgcolor="#C7E2F8"><span class="out">试卷名称</span></td>
													<td align="center" bgcolor="#C7E2F8"><span class="out">答卷人员</span></td>
													<td align="center" bgcolor="#C7E2F8"><span class="out">答卷时间</span></td>
													<c:if test="${flag!=1}">
														<td align="center" bgcolor="#C7E2F8"><span
															class="out">结束时间</span></td>
														<td align="center" bgcolor="#C7E2F8"><span
															class="out">状态</span></td>
													</c:if>
													<td align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
												</tr>
												<s:iterator value="answerpapers" id="answerpaper"
													status="status">

													<c:if test="${answerpaper.epapers.paperType!=4}">
														<tr onMouseOver="this.className='td_over'"
															onMouseOut="this.className=''" id='r1'>
															<td align='center' class='num_font'><input
																type="checkbox" id="checkbox" name="deleteid"
																value="<s:property value='#answerpaper.djId'/>">

															</td>
															<td align='center' class='num_font'><s:property
																	value="#status.index+1" /></td>
															<td align='center' class='num_font'><s:property
																	value="#answerpaper.epapers.sjMc" /></td>
															<td align='center' class='num_font'><s:property
																	value="#answerpaper.djRymc" /></td>
															<td align='center' class='num_font'><s:date
																	name="#answerpaper.djKssj" format="yyyy-MM-dd HH:mm" /></td>
															<c:if test="${answerpaper.epapers.paperType!=4}">
																<td align='center' class='num_font'><c:if
																		test="${answerpaper.djKssj!=null}">
																		<s:date name="#answerpaper.djJssj"
																			format="yyyy-MM-dd HH:mm" />
																	</c:if> <c:if test="${answerpaper.djKssj==null}">&nbsp;</c:if></td>
																<td align='center' class='num_font'><s:if
																		test="#answerpaper.djSyzt==0">
																		<font color="green">待审阅</font>
																	</s:if> <s:elseif test="#answerpaper.djSyzt==1">
																		<font color="red">待提交</font>
																	</s:elseif> <s:else>
																		<font color="orange">审阅完成</font>
																	</s:else></td>
															</c:if>
															<td align="center"><s:if
																	test="#answerpaper.djSyzt!=2">
																	<s:url id="tosy" action="toViewQuestion.action">
																		<s:param name="answerpaperid"
																			value="#answerpaper.djId" />
																	</s:url>
																	<s:a href="%{#tosy}">审阅</s:a>
																</s:if> <s:url id="commit" action="commitQuestion.action">
																	<s:param name="answerpaperid" value="#answerpaper.djId" />
																</s:url> <s:a href="%{#commit}">提交</s:a></td>
														</tr>
													</c:if>
													<c:if test="${answerpaper.epapers.paperType==4}">
														<c:if test="${answerpaper.epapers.toUserId==userid}"></c:if>
														<c:if test="${answerpaper.epapers.toUserId!=userid}">
															<tr onMouseOver="this.className='td_over'"
																onMouseOut="this.className=''" id='r1'>
																<td align='center' class='num_font'><s:property
																		value="#status.index+1" /></td>
																<td align='center' class='num_font'><s:property
																		value="#answerpaper.epapers.sjMc" /></td>
																<td align='center' class='num_font'><s:property
																		value="#answerpaper.djRymc" /></td>
																<td align='center' class='num_font'><s:date
																		name="#answerpaper.djKssj" format="yyyy-MM-dd HH:mm" /></td>
																<td align="center"><s:if
																		test="#answerpaper.djSyzt!=2">
																		<s:url id="tosy" action="toViewQuestion.action">
																			<s:param name="answerpaperid"
																				value="#answerpaper.djId" />
																		</s:url>
																		<s:a href="%{#tosy}">拨测</s:a>
																	</s:if> <s:url id="commit" action="commitQuestion.action">
																		<s:param name="answerpaperid"
																			value="#answerpaper.djId" />
																	</s:url> <s:a href="%{#commit}">提交</s:a></td>
															</tr>
														</c:if>
													</c:if>
												</s:iterator>
											</table> <!-- fenye --> 
											<c:if test="${answerpapers!=null }">
												<form name="tosypaperActionform" method="post" action="tosypaperAction" style="margin-top: 6px;">
													<s:hidden name="djSyzt"></s:hidden>
													<s:hidden name="sjMc" />
													<s:hidden name="kksj"></s:hidden>
													<s:hidden name="answerpaperid"></s:hidden>
													<elile:navigateBar navigateform="navigateform"
														actionName="tosypaperAction.action"
														formName="tosypaperActionform" />
												</form>
											</c:if>
									</tr>
								</table></td>
						</tr>
					</table>
				</div></td>
		</tr>
	</table>

	<script language="javascript" type="text/JavaScript">
		function selectall() {
			var check = document.getElementById("checkAll");
			var values = document.getElementsByName("deleteid");
			for ( var i = 0; i < values.length; i++)
				values[i].checked = check.checked;
		}
	</script>
</body>
</html>

