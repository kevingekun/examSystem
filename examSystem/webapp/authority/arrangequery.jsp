<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.List,com.wondersgroup.technocracy.bo.Addexpert"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>专家安排</title>
<link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<script>
 
function arrangeexpert(v,s){
	//alert(v);
	//alert(s);
	document.getElementById("eid").value=v;
	document.getElementById("teamid").value=s;
	document.redict.submit();
}
function doreset() {
	document.Form.reset();
}
/* function autoarrange(){
	var sjmc = $("#identifynumber").val();
	sjmc = sjmc.replace(/\s/g,"");
	var u = 'autoarrange.action?sj_mc='+sjmc;
	var url = encodeURI(encodeURI(u));
	if(sjmc!=null){
		$.ajax({
			type: 'post',
			url: url,
			success:function(result){
				var data = eval(result);
				if(data[0][0]==0){
					alert(sjmc+" 安排专家成功！");
					$("#id_team").val(data[1][0][0]);
					$("#id_exam").val(data[1][0][1]);
					document.autocheck.submit();
				}else{
					alert("自动安排专家失败！"+data[0][1]);
				}
			},
			error:function(){
				alert("程序出错！");
			}
		});
	}
} */
function autoarrange(){
	$("#expert1 tr:gt(0)").remove();//清空table
	$("#expert2 tr:gt(0)").remove();//清空table
	var examid = $("#examid").val();
	var url = 'autoarrange.action?examId='+examid;
	if(examid!=''){
		$.ajax({
			type: 'post',
			url: url,
			success:function(result){
				var data = eval(result);
				var l1 = data[0]['list1'].length;
				var l2 = data[0]['list2'].length;
				for(var i=0;i<l1;i++){
					$("#expert1").append("<tr align='center'>"
							+"<td>"+data[0]['list1'][i][1]+"</td>"
							+"<td>"+data[0]['list1'][i][7]+"</td>"
							+"<td>"+data[0]['list1'][i][9]+"</td>"
							+"</tr>");
				}
				for(var i=0;i<l1;i++){
					$("#expert2").append("<tr align='center'>"
							+"<td>"+data[0]['list2'][i][1]+"</td>"
							+"<td>"+data[0]['list2'][i][7]+"</td>"
							+"<td>"+data[0]['list2'][i][9]+"</td>"
							+"</tr>");
				}
				/* alert(data[0]['list1'].length)
				alert(data[0]['list1'][0][0]) */
			},
			error:function(){
				alert("程序出错！");
			}
		});
	}else{
		alert("请选择试卷！");
	}
}
function redioClick(v){
	$("#examid").val(v);
}
function sbt(){
	var v = $("input[name='radio_expert']:checked").val();
	var examid = $("#examid").val();
	if(examid==''){
		alert("请选择试卷，并点击'自动安排'");
		return ;
	}
	if(v==''||v==null){
		alert("请选择自动安排的专家信息");
		return ;
	}
	var url = 'arrangeExpertAutoSubmit.action?examId='+examid+'&experts='+v;
	$.ajax({
		type: 'post',
		url: url,
		success:function(data){
			if(data=='null'){
				alert("安排失败！所选安排专家为空！");
			}else if(data=='success'){
				alert("安排成功");
			}
		},
		error:function(){
			alert("程序出错！");
		}
	});
}
</script>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="50%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">专家安排</td>
						<td class="header3" width="24"><img src="<%=request.getContextPath()%>/newimages/content_right_bj.gif " width="24" height="22"></td>
					</tr>
				</table></td>
			<td width="50%" align="left"></td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
				<div id="content1" class="borader">
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
								</table> <s:form method="post" action="arangeexpert.action" name="Form">
									<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
										<tr height="23">
											<td nowrap="nowrap" align="center">试卷名称:</td>
											<td align="left"><input type="text" id="identifynumber" name="sj_mc"></td>
											<td align="center"><input type="submit" class="submit_2" id="search" value="查询" /></td>
											<td align="center">
												<input type="button" class="submit_2" value="自动安排" onclick="autoarrange()" />
												<input type="hidden" id="examid" />
											</td>
										</tr>
										<tr>
											<td height="10"><input type="hidden" name="currpage" value="1" /></td>
										</tr>
									</table>
								</s:form></td>
						</tr>
					</table>
					<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
						<tr>
							<td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
												<tr>
													<td align="left" class="header7"></td>
													<td class="header8">鉴定批次信息</td>
												</tr>
											</table></td>
									</tr>
								</table>
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb">
									<tr class="title_font">
										<td width="6%" align="center" bgcolor="#C7E2F8"><span class="out">操作 </span></td>
										<td width="11%" align="center" bgcolor="#C7E2F8"><span class="out">试卷名称</span></td>
										<td width="7%" align="center" bgcolor="#C7E2F8"><span class="out">考场名称</span></td>
										<td width="7%" align="center" bgcolor="#C7E2F8"><span class="out">联系人</span></td>
										<td width="7%" align="center" bgcolor="#C7E2F8"><span class="out">联系电话</span></td>
										<td width="14%" align="center" bgcolor="#C7E2F8"><span class="out">地址</span></td>
										<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">考试人数</span></td>
									</tr>
									<s:iterator value="identifyInfo" id="user" status="state">
										<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
											<td align='center' class='num_font'> <input type="radio" name="redio_e" onclick="redioClick(<s:property value="#user.examid"/>)" /> <input type='button' value="安排专家" onclick="arrangeexpert(<s:property value="#user.teamid"/>,<s:property value="#user.examid"/>)"></td>
											<td align='center' class='num_font'><s:property value="#user.jdpch " /></td>
											<td align='center' class='num_font'><s:property value="#user.kcmc" /></td>

											<td align='center' class='num_font'><s:property value="#user.lxr" /></td>
											<td align='center' class='num_font'><s:property value="#user.lxdh" /></td>
											<td align='center' class='num_font'><s:property value="#user.dz" /></td>
											<td align='center' class='num_font'><s:property value="#user.ksrs" /></td>
										</tr>
									</s:iterator>
								</table> <c:if test="${identifyInfo!=null}">
									<form action="arangeexpert.action" name="chaForm" method="post" style="margin-top: 6px;">
										<elile:navigateBar navigateform="navigateform" actionName="arangeexpert.action" formName="chaForm" />
									</form>
								</c:if> <s:form action="redict.action" name="redict">
									<input id="eid" name="eid" type="hidden" />
									<input id="teamid" name="teamid" type="hidden" />
									<input name="currpage" value="1" type="hidden" />
								</s:form> <s:form action="autocheckexpert.action" name="autocheck">
									<input id="id_exam" name="examid" type="hidden" />
									<input id="id_team" name="teamId" type="hidden" />
								</s:form></td>
						</tr>
					</table>
				</div>
				</td>
		</tr>
		<tr>
			<td>
				<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
					<tr>
						<td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
											<tr>
												<td align="left" class="header7"></td>
												<td class="header8">专家信息
													<div style="float:right">
														<input type="radio" id="r1" name="radio_expert" value="list1" />选择
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="expert1">
								<tr class="title_font">
									<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">专家姓名</span></td>
									<td width="40%" align="center" bgcolor="#C7E2F8"><span class="out">所属单位</span></td>
									<td width="40%" align="center" bgcolor="#C7E2F8"><span class="out">联系电话</span></td>
								</tr>
							</table> 
						</td>
					</tr>
				</table>
			</td>
			<td>
				<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
					<tr>
						<td class="borader3">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
											<tr>
												<td align="left" class="header7"></td>
												<td class="header8">专家信息
													<div style="float:right">
														<input type="radio" id="r2" name="radio_expert" value="list2" />选择
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="expert2">
								<tr class="title_font">
									<td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">专家姓名</span></td>
									<td width="40%" align="center" bgcolor="#C7E2F8"><span class="out">所属单位</span></td>
									<td width="40%" align="center" bgcolor="#C7E2F8"><span class="out">联系电话</span></td>
								</tr>
							</table> 
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" height="35px">
				<input type="button" class="submit_2" value="确定" onclick="sbt()" />
			</td>
		</tr>
	</table>
</body>
</html>
