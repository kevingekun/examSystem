<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.wondersgroup.kaoshi.bo.Tjobsubject,java.util.List,com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
ProfessionBean professionBean = new ProfessionBean();
String s = request.getParameter("id_job");
String id = request.getParameter("gzid");
List<Object> list_dj = professionBean.getDjById_job(s);//通过工种id获取对应等级
Tjobsubject t = new Tjobsubject();
if(request.getParameter("gzid")!=null){
	t = professionBean.findTjobsubjectBygzid(Integer.parseInt(id));
}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>试题新增</title>
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript" src="js/jquery/autoresize.min.js"></script>
<link rel="stylesheet" type="text/css" href="newcss/style.css" />
<link rel="stylesheet" type="text/css" href="inc/all.css" />
<link rel="StyleSheet" type="text/css" href="authority/js/dtree.css" />
<script type="text/javascript" src="js/util/numeral.js"></script>
<script language="javascript">	
$(function(){  
 //文本框只能输入数字，并屏蔽输入法和粘贴  
  $("#sjzf").numeral();
});   
function checkSubmit(){
	var sjmc = $("#sjmc").val();
	sjmc = sjmc.replace(/\s/g,"");
	$("#sjmc").val(sjmc);
	if(sjmc=='') {
		alert("试卷名称不能为空！");
		return ;
	}	
	if(document.all.sjdj.value=="0"){
		alert("请选择等级");
		return ;
	}
	if($("#sjzf").val()==''){
		alert("请输入试卷总分！");
		return ;
	}
	$.ajax({
		type:'post',
		url:'paperAdd.action',
		dataType:'text',
		data:$('#paperAdd').serialize(),
		success:function(data){
			if(data=='success'){
				alert("添加成功！");
				window.parent.location.reload();
			}else if(data=='repeat'){
				alert("添加失败,试卷名称重复！");
			}else{
				alert("添加失败,请联系管理员！");
			}
		}
	});
}
function djChange(v){
	var tkid = v.options[v.selectedIndex].title;//获取对应等级的题库id
	$("#tkid").val(tkid);
	var dj = v.options[v.selectedIndex].text;//获取对应等级的题库id
	if(v.selectedIndex!=0){
		var sjmc = '<%=t.getJobname()%>'+dj;
		$("#sjmc").val(sjmc);
	}else{
		$("#sjmc").val('<%=t.getJobname()%>');
	}
}
</script>

<%
	String messge = (String) request.getAttribute("messge");
		if (messge != null) {
%>
<script>
	alert('<%=messge%>');
	</script>
<%
	}
%>
</head>
<body class="nrbj">
		<table width="99%" border="0" align="right" cellpadding="0"
			cellspacing="0" style="margin-top: 10px; margin-left: 8px;">
			<tr>
				<td width="45%" align="left">
					<table border="0" align="left" cellpadding="0" cellspacing="0">
						<tr>
							<td align="left" valign="middle" class="header1"></td>
							<td class="header2">
							<%
								if(request.getParameter("gzid")!=null){
									out.print(t.getJobname());
								}else{
									out.print("请选择工种");
								}
							%>
							</td>
							<td class="header3"></td>
						</tr>
					</table>
				</td>
				<td width="53%" align="left"></td>
			</tr>
			<tr>
				<td colspan="2" valign="top">
					<div id="content1" class="borader">
						<form name="addform" id="paperAdd">
							<input type=hidden name="actionType" value="add"/>
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
				               <tr class="row_height">
				               		<td width="8%" align="right">等级：</td>
									<td width="15%">
										<input id="tkid" name="tkid" type="hidden" />
										<select name="sjdj" style="width:80px;" onchange="djChange(this)">
											<option value="0">请选择</option>
											<%
											for (int i = 0; i < list_dj.size(); i++) {
													List<Object> l = (List<Object>)list_dj.get(i);
													out.println("<option value=" + l.get(0) + " title=" + l.get(2) + "");
													out.println(">" + l.get(1) + "");
													out.println("</option>");
												}
											%>
										</select>
										<font color="red">*</font>
									</td>
									<td width="10%" height="26" align="right">试卷名称：</td>
									<td width="20%"><input id="sjmc" name="sjmc" value="<%=t.getJobname()%>"/> <font color="red">*</font>
									
									<td width="10%" height="26" align="right">试卷总分：</td>
									<td width="10%"><input id="sjzf" name="sjzf" style="width:60px;"/> <font color="red">*</font>
									<td width="10%" height="26" align="right">试卷类型：</td>
									<td width="15%">
										<select name="sjType">
											<option value="1">机考</option>
											<option value="2">笔答</option>
										</select>
									</td>
								</tr>
							</table>
							<table width="100%" height="30" border="0" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="50%" height="40" align="center" valign="bottom">
										<input name="add" type=button class="submit_2" onClick="jvavscript:checkSubmit();" value="确  定" /> &nbsp; 
									</td>
									<td width="50%" height="40" align="center" valign="bottom">
										<input name="res" type="reset" class="submit_2" value="重 置" />
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
