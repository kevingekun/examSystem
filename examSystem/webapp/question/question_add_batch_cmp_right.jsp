<%@ page import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@ page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
<%@page import="com.wondersgroup.kaoshi.bo.EPapersSet"%>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
ProfessionBean professionBean = new ProfessionBean();
String sjid = request.getParameter("sjid");
String sjmc = "";
if(sjid!=null&&!"".equals(sjid)){
	EPapersSet e = professionBean.getPapersBySjid(sjid);
	sjmc = e.getSj_mc();
}
%>
<%
//List list = (List)request.getAttribute("list");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>批量导入试题</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript">
	function fileChange_cmp(){
		var sjid = $("#sjid").val();
		if(sjid=="null"){
			alert("请选择试卷");
			$("#file").val("");
		}else{
			document.fileupload.submit();
		}
	}
	function showT(){
		$("#tb2").css("display","none");
		$("#tb1").css("display","block");
	}
	function showF(){
		$("#tb1").css("display","none");
		$("#tb2").css("display","block");
	}
	function batchSubmit(){
			$("#bcsmt").attr("disabled","disabled");
		var listCount = $("#list1_id").val();
		if(listCount=='1'){
			var batchNumber = $("#batchNumber").val();
			$.ajax({
				type: 'post',
				url: 'batchAddAction_batchSubmit.action?batchNumber='+batchNumber+'&flag=batchSet',
				success:function(result){
					alert(result);
					window.location.href="question/question_add_batch_cmp_right.jsp";
					window.parent['LeftTree2'].location.reload();
				},
				error:function(){
					alert("导入失败！");
				}
			});
		}else{
			alert("没有可导入的试题！");
		}
	}
	function templateDownload(){
		window.location.href="templateDownload.action?flag=cmp";
		//window.location.href="<%=request.getContextPath() %>/templates/questionTemplate_cmp.xlsx";
	}
	function checkPaper(){
		var sjid = $("#sjid").val();
		if(sjid=='null'){
			$("#file").attr("onchange","");
			alert("请先选择试卷！");
		}
	}
	</script>
</head>

<body class="nrbj">
	<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:6px; ">
		<tr>
			<td colspan="2" valign="top">
				<!-- <div id="content1" class="borader"> -->
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td class="borader3">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" valign="middle" class="header7"></td>
													<td class="header8">
													<%
													if(!"".equals(sjid)&&sjid!=null){
														out.print(sjmc+"_机考");
													}else{
														out.print("请选择试卷");
													}
													%>
													</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr height="8"></tr>
									<tr>
										<td align="center">
											<form name="fileupload" action="batchAddAction_displayExcel_cmp.action" method="post" enctype="multipart/form-data">
												<input id="sjid" name="sjid" type="hidden" value="<%=sjid%>"/>
												<input type="file" id="file" name="file_excel" onchange="fileChange_cmp()" onclick="checkPaper()" style="width: 280px" />
											</form>
										</td>
									</tr>
									<tr height="8"></tr>
								</table>
							</td>
						</tr>
					</table>
				<!-- </div> -->
			</td>
		</tr>
		<tr>
		<td colspan="2" valign="top">
				<div id="content1" class="borader">
			<form action="<%=request.getContextPath() %>/QuestionServlet?myaction=unpasslist" name="aForm" method="POST">
				<input type=hidden name="actionType" value="query">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
					<tr>
						<td class="borader3">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
											<tr>
												<td align="left" valign="middle" class="header7"></td>
												<td class="header8">
													<a href="javascript:void(0)" class="infocount" onclick="showT()" style="color: green">正确试题</a> 
													<a href="javascript:void(0)" class="infocount" onclick="showF()" style="color: red">错误试题</a>
													<div style="float:right">
														<input id="batchNumber" value="<c:out value="${batchNumber}"/>" type="hidden"> 
														<input id="sjid" name="sjid" value="<c:out value="${sjid}"/>" type="hidden"> 
														<input id="bcsmt" type="button" onclick="batchSubmit()" value="提交" style="margin-right:20px;width:50px;background-color: green" />
													</div>
													<div style="float:right">
														<input type="button" onclick="templateDownload()" value="试题模板下载" style="margin-right:20px;width:85px;background-color: #79C7CD" />
													</div></td>
											</tr>
										</table></td>
								</tr>
							</table>
							<table id="tb1" width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list">
								<tr class="title_font">
									<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号</span></td>
									<td width="30%" align="center" bgcolor="#C7E2F8"><span class="out">题目</span></td>
									<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">试题类型</span></td>
									<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">选项</span></td>
									<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">答案</span></td>
									<%-- <td width="60" align="center" bgcolor="#C7E2F8"><span class="out">重要程度</span></td>
									<td width="50" align="center" bgcolor="#C7E2F8"><span class="out">难易度</span></td> --%>
									<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">试题出处</span></td>
									 <td width="7%"  align="center" bgcolor="#C7E2F8"><span class="out">专家</span></td>
									 <td width="7%"  align="center" bgcolor="#C7E2F8"><span class="out">分值</span></td>
									<%-- <td width="100" align="center" bgcolor="#C7E2F8"><span class="out">工种</span></td>
									<td width="40" align="center" bgcolor="#C7E2F8"><span class="out">等级</span></td> --%>
								<c:forEach var="aBean" items="${list1}" varStatus="status">
									<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
										<td align='center' class='num_font'><c:out value="${status.index+1}" /> <input type="hidden" id="list1_id" value="<c:if test="${list1!= null}">1</c:if><c:if test="${list1== null}">0</c:if>"></td>
										<td class='num_font alignleft'>
											<a style="display:block;width:250;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.stTg}"/>"><c:out value="${aBean.stTg}"/></a>
										</td>
										<td align='center' class='num_font'><c:out value="${aBean.equestiontype.name}" /></td>
										<td align='center' class='num_font'>
											<a style="display:block;width:150;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.stXx}"/>"><c:out value="${aBean.stXx}"/></a>
										</td>
										<td align='center' class='num_font'><c:out value="${aBean.stDa}" /></td>
										<%-- <td align='center' class='num_font'><c:out value="${aBean.bxType}" /></td>
										<td align='center' class='num_font'><c:out value="${aBean.eimportance.name}" /></td> --%>
										<td align='center' class='num_font'>
											<a style="display:block;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.stCc}"/>">
												<c:out value="${aBean.stCc}" />
											</a>
										</td>
										<td align='center' class='num_font'><c:out value="${aBean.stSyryId}"/></td>
										<td align='center' class='num_font'><c:out value="${aBean.stFz}"/></td>
										<%-- <td align='center' class='num_font'><c:out value="${jobname}" /></td>
										<td align='center' class='num_font'><c:out value="${rankname}" /></td> --%>
									</tr>
								</c:forEach>
							</table>
							<table id="tb2" style="display: none;" width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list">
								<tr class="title_font">
									<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号</span></td>
									<td width="30%" align="center" bgcolor="#C7E2F8"><span class="out">题目</span></td>
									<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">试题类型</span></td>
									<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">选项</span></td>
									<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">答案</span></td>
									<%-- <td width="60" align="center" bgcolor="#C7E2F8"><span class="out">重要程度</span></td>
									<td width="50" align="center" bgcolor="#C7E2F8"><span class="out">难易度</span></td> --%>
									<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">试题出处</span></td>
									 <td width="7%"  align="center" bgcolor="#C7E2F8"><span class="out">专家</span></td>
									 <td width="7%"  align="center" bgcolor="#C7E2F8"><span class="out">分值</span></td>
									<%-- <td width="100" align="center" bgcolor="#C7E2F8"><span class="out">工种</span></td>
									<td width="40" align="center" bgcolor="#C7E2F8"><span class="out">等级</span></td> --%>
								</tr>
								<c:forEach var="aBean" items="${list2}" varStatus="status">
									<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
										<td align='center' class='num_font'><c:out value="${status.index+1}" /></td>
										<td class='num_font alignleft'>
											<a style="display:block;width:250;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.stTg}"/>"><c:out value="${aBean.stTg}"/></a>
										</td>
										<td align='center' class='num_font'><c:out value="${aBean.equestiontype.name}" /></td>
										<td align='center' class='num_font'>
											<a style="display:block;width:150;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.stXx}"/>"><c:out value="${aBean.stXx}"/></a>
										</td>
										<td align='center' class='num_font'><c:out value="${aBean.stDa}" /></td>
										<%-- <td align='center' class='num_font'><c:out value="${aBean.bxType}" /></td>
										<td align='center' class='num_font'><c:out value="${aBean.eimportance.name}" /></td> --%>
										<td align='center' class='num_font'>
											<a style="display:block;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.stCc}"/>">
												<c:out value="${aBean.stCc}" />
											</a>
										</td>
										<td align='center' class='num_font'><c:out value="${aBean.stSyryId}"/></td>
										<td align='center' class='num_font'><c:out value="${aBean.stFz}"/></td>
										<%-- <td align='center' class='num_font'><c:out value="${jobname}" /></td>
										<td align='center' class='num_font'><c:out value="${rankname}" /></td> --%>
									</tr>
								</c:forEach>
							</table> <!-- 分页       --> <%-- <c:if test="${list!= null}">
				<elile:navigateBar navigateform="navigateform" actionName="QuestionServlet?myaction=unpasslist" formName="aForm"/>
			  </c:if> --%>
						</td>
					</tr>
				</table>
			</form>
			</div>
			</td>
		</tr>
	</table>
</body>
</html>
