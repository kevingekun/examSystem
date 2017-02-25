<%@page import="com.wondersgroup.kaoshi.bo.EPapersSet"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@ page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
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
var gzid,gzdj;
var ret = false;//工种名称是否正确
var state = 0;//鉴定要素是否已经存在，0不存在，1存在
function checkGz(){
	var jobname = document.getElementById("gzmc").value;
	if(jobname==''){
		alert("请输入工种名称");
		ret = false;
		return ret;
	}
	$("#jobId").val(jobname);
	jobname = encodeURI(encodeURI(jobname));
	$.ajax({
		type:'post',
		async:false,
		url:'findProfessionByName.action?jobname='+jobname,
		success:function(result){
			if(result=='0'){
				alert("工种不存在");
				ret =  false;
			}else{
				//document.getElementById("id_job").value=result;
				gzid = result;
				ret =  true;
			}
		},
		error:function(){
			alert("error");
			ret =  false;
		}
	});
	return ret;
}
function checkDj(){
	if(ret==true){
		var jobname = document.getElementById("gzmc").value;
		var dj = document.getElementById("gzdj").value;
		jobname = encodeURI(encodeURI(jobname));
		dj = encodeURI(encodeURI(dj));
		if(dj=='0'){
			alert("请选择等级");
		}else{
			$.ajax({
				type:'post',
				async:false,
				url:'findTkByjobname.action?jobname='+jobname,
				success:function(result){
					if(result=='1'){
						state = 1;//鉴定要素存在
						$("#file_jdsy").attr("disabled",true);
						$("#file_jdsy").val("");
						alert("此工种鉴定要素已经存在！");
					}else{
						state = 0;//鉴定要素不存在
						$("#dj").val(dj);
						$("#file_jdsy").attr("disabled",false);
					}
				},
				error:function(){
					alert("程序出错！");
					state = 0;
				}
			});
		}
	}else{
		alert("您输入的工种名称有误！");
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
		var listCount = $("#list1_id").val();
		if(listCount=='1'){
			var batchNumber = $("#batchNumber").val();
			$.ajax({
				type: 'post',
				url: 'batchAddAction_batchSubmit.action?batchNumber='+batchNumber,
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
			alert("没有可导入的鉴定要素！");
		}
	}
	function templateDownload(){
		window.location.href="templateDownload.action?flag=jdysTemplate";
		//window.location.href="<%=request.getContextPath()%>/templates/questionTemplate_cmp.xlsx";
	}
	function fileChange(){
		var dj = $("#dj").val();
		if(dj==""){
			alert("请选择工种等级");
			$("#file").val("");
		}else{
			document.fileupload.submit();
		}
	}
</script>
</head>

<body class="nrbj">
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;">
		<tr>
			<td colspan="2" valign="top">
				<!-- <div id="content1" class="borader"> -->
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td height="6" colspan="4"></td>
					</tr>
					<tr>
						<input id="id_job" type="hidden" />
						<td width="13%" height="28" align="right">工种名称：</td>
						<td width="18%" align="left"><s:textfield id="gzmc" name="gz" onblur="checkGz()"></s:textfield></td>
						<td width="13%" align="right">等级：</td>
						<td width="18%"><select id="gzdj" name="grade" style="width: 100px;" onchange="checkDj()">
								<option value="0">请选择</option>
								<option value="一级">一级</option>
								<option value="二级">二级</option>
								<option value="三级">三级</option>
								<option value="四级">四级</option>
								<option value="五级">五级</option>
								<option value="专项">专项</option>
						</select></td>
						<td>
							<form name="fileupload" action="jdysBatchAdd.action" method="post" enctype="multipart/form-data">
								<input id="jobId" name="id_job" type="hidden" />
								<input id="dj" name="rankname" type="hidden" />
								<input id="file_jdsy" type="file" name="jdysFile" disabled="disabled" onchange="fileChange()"/>
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
					<form action="<%=request.getContextPath()%>/QuestionServlet?myaction=unpasslist" name="aForm" method="POST">
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
															<a href="javascript:void(0)" class="infocount" onclick="showT()" style="color: green">正确要素</a>
															<a href="javascript:void(0)" class="infocount" onclick="showF()" style="color: red">错误要素</a>
															<div style="float:right">
																<input id="batchNumber" value="<c:out value="${batchNumber}"/>" type="hidden">
																<input id="sjid" name="sjid" value="<c:out value="${sjid}"/>" type="hidden">
																<input type="button" onclick="batchSubmit()" value="提交" style="margin-right:20px;width:50px;background-color: green" />
															</div>
															<div style="float:right">
																<input type="button" onclick="templateDownload()" value="模板下载" style="margin-right:20px;width:85px;background-color: #79C7CD" />
															</div></td>
													</tr>
												</table></td>
										</tr>
									</table>
									<table id="tb1" width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list">
										<tr class="title_font">
											<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号</span></td>
											<td width="25%" align="center" bgcolor="#C7E2F8"><span class="out">行为领域</span></td>
											<td width="25%" align="center" bgcolor="#C7E2F8"><span class="out">鉴定范围</span></td>
											<td width="25%" align="center" bgcolor="#C7E2F8"><span class="out">鉴定点</span></td>
											<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">比例</span></td>
											<c:forEach var="aBean" items="${list1}" varStatus="status">
												<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
													<td align='center' class='num_font'><c:out value="${status.index+1}" /> <input type="hidden" id="list1_id" value="<c:if test="${list1!= null}">1</c:if><c:if test="${list1== null}">0</c:if>"></td>
													<td class='num_font alignleft'><a><c:out value="${aBean.stTg}" /></a></td>
													<td align='center' class='num_font'><c:out value="${aBean.equestiontype.name}" /></td>
													<td align='center' class='num_font'><c:out value="${aBean.stXx}" /></td>
													<td align='center' class='num_font'><c:out value="${aBean.stDa}" /></td>
												</tr>
											</c:forEach>
									</table>
									<table id="tb2" style="display: none;" width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list">
										<tr class="title_font">
											<td width="8%" align="center" bgcolor="#C7E2F8"><span class="out">序号</span></td>
											<td width="25%" align="center" bgcolor="#C7E2F8"><span class="out">行为领域</span></td>
											<td width="25%" align="center" bgcolor="#C7E2F8"><span class="out">鉴定范围</span></td>
											<td width="25%" align="center" bgcolor="#C7E2F8"><span class="out">鉴定点</span></td>
											<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">比例</span></td>
										</tr>
										<c:forEach var="aBean" items="${list2}" varStatus="status">
											<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
												<td align='center' class='num_font'><c:out value="${status.index+1}" /></td>
												<td class='num_font alignleft'><a><c:out value="${aBean.stTg}" /></a></td>
												<td align='center' class='num_font'><c:out value="${aBean.equestiontype.name}" /></td>
												<td align='center' class='num_font'><c:out value="${aBean.stXx}" /></td>
												<td align='center' class='num_font'><c:out value="${aBean.stDa}" /></td>
											</tr>
										</c:forEach>
									</table>
								</td>
							</tr>
						</table>
					</form>
			</td>
		</tr>
	</table>
</body>
</html>
