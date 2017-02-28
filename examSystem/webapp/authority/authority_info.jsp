<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1"><!-- 为了确保适当的绘制和触屏缩放，需要在 <head> 之中添加 viewport 元数据标签。 -->
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	<title>人员分组</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<%-- <link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css" /> --%>

</head>
<script src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/JavaScript">
	function add(){
   		window.location.href="<%=request.getContextPath()%>/authority/authority_add.jsp";
	}
	function mod() {
		var row = 0;
		var t = window.document.getElementsByName('dealid');
		for ( var i = 0; i < t.length; i++) {
			if (t[i].type == 'checkbox' && t[i].checked == true) {
				row = row + 1;
			}
		}
		if (row < 1) {
			alert("请选择要修改的记录！");
			return false;
		}
		if (row > 1) {
			alert("请选择一个记录进行修改！");
			return false;
		}
		document.aForm.action = "authority_mod.jsp";
		document.aForm.myaction.value = "mod";
		document.aForm.submit();
	}
	function del() {
		var row = 0;
		var t = window.document.getElementsByName('deleEauthories');
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
			document.aForm.submit();
		} else {
			return false;
		}

	}
</script>
<body>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">角色管理</h3>
		</div>
		<div class="panel-body">
		    <p>本功能可以新增角色，可以为每个角色分配菜单</p>
		</div>
			<s:form action="deleteEauthority" name="aForm" method="POST">
			<table class="table table-striped table-bordered table-hover table-condensed" id="tb" style="margin-top: -12px;" >
				<tr class="info">
					<td align="center"><span class="out">选择 </span></td>
					<td align="center"><span class="out">序号</span></td>
					<td align="center"><span class="out">角色名称</span></td>
					<td align="center"><span class="out">说明</span></td>
					<td align="center"><span class="out">操作</span></td>
				</tr>
				<s:iterator value="eauthoritys" id="eauthority" status="state">
					<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
						<td class="text-center" style="width: 8%"><input type='checkbox' name='deleEauthories' value='<s:property value="#eauthority.id"/>'></td>
						<td class="text-center"><s:property value="#state.index+1" /></td>
						<td class="text-center"><s:property value="#eauthority.name" /></td>
						<td class="text-center"><s:property value="#eauthority.description" /></td>
						<td class="text-center">
							<a class="btn btn-primary btn-xs active" href='toupdateEauthority.action?authorityid=<s:property value="#eauthority.id"/>'>
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> 修改
							</a> 
							<a class="btn btn-info btn-xs active" href='userMeanuTreeView.action?authorityid=<s:property value="#eauthority.id"/>'>
								<span class="glyphicon glyphicon-cog" aria-hidden="true"></span> 菜单授权
							</a>
						</td>
					</tr>
				</s:iterator>
			</table>
			</s:form>
			<s:form action="eauthority.action" name="aForm1" method="post">
				<elile:navigateBar navigateform="navigateform" actionName="eauthority.action" formName="aForm1" />
			</s:form>
			<div class="panel-footer" style="margin-top: -20px;">
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-3" style="text-align: center;">
						<button type="button" class="btn btn-success" name="button_editfile" onclick="add()">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增
						</button>
					</div>
	 				<div class="col-md-3" style="text-align: center;">
						<button type="button" class="btn btn-danger" name="button_editfile2" onclick="del()">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 删除
						</button>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
		</div>
</div>
</body>
</html>
