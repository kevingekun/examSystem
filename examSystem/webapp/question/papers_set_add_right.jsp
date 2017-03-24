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
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>试题新增</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="bootstrap/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script language="javascript">	
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
<body>
<div class="container-fluid">
	<div class="panel panel-info">
		<div class="panel-heading">
			<%
			if(request.getParameter("gzid")!=null){
				out.print("<span class=\"label label-success\">"+t.getJobname()+"</span>");
			}else{
				out.print("<span class=\"label label-warning\">请选择工种</span>");
			}
			%>
		</div>
		<div class="panel-body">
			<form name="addform" id="paperAdd">
				<input type=hidden name="actionType" value="add"/>
				<div class="form-inline row">
					<div class="form-group col-md-3">
						<label for="sjdj">等级</label>
		    			<select class="form-control input-sm" name="sjdj" id="sjdj" style="width:80px;" onchange="djChange(this)">
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
		    			<input id="tkid" name="tkid" type="hidden" />
					</div>
					<div class="form-group col-md-3">
						<label for="sjmc">试卷名称</label>
						<input class="form-control input-sm" type="text" id="sjmc" name="sjmc" placeholder="试卷名称" value="<%=t.getJobname()%>"/> <font color="red">*</font>
					</div>
					<div class="form-group col-md-3">
						<label for="sjzf">试卷总分</label>
						<input class="form-control input-sm" type="number" id="sjzf" name="sjzf" style="width:60px;" placeholder="总分"/> <font color="red">*</font>
					</div>
					<div class="form-group col-md-3">
						<label for="sjType">试卷类型</label>
						<select class="form-control input-sm" name="sjType" id="sjType">
							<option value="1">机考</option>
							<option value="2">笔答</option>
						</select>
					</div>
				</div>
				<div class="form-inline row" style="margin-top:10px">
					<div class="form-group col-md-3" style="margin-left:300px" >
						<button class="btn btn-success btn-sm" type="button" onclick="checkSubmit()">确定</button>
					</div>
					<div class="form-group col-md-3">
						<button class="btn btn-warning btn-sm" type="reset">重置</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

</body>
</html>
