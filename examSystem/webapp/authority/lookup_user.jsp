<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib uri="elile.tld" prefix="elile"%>
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
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>用户列表</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">考试安排</h3>
		</div>
		<div class="panel-body">
			<form method="post" action="userview.action" name="lookup" class="form-inline">
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label for="username">用户名</label>
							<input type="text" class="form-control" name="username" id="username" value='<s:property value="username"/>'/>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="realname">人员姓名</label>
							<input type="text" class="form-control" name="realname" id="realname" value='<s:property value="realname"/>'/>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="userstar">用户类别</label>
							<select class="form-control" name="userstar" id="userstar">
								<option value="" >请选择</option>
								<option value="0" <s:if test="userstar==0">selected="selected"</s:if>>业务用户</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row" style="margin-top:5px;">
					<div class="col-md-1 col-md-offset-3">
						<button class="btn btn-primary btn-sm" type="submit">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span> 查询
						</button>
					</div>
					<div class="col-md-1 col-md-offset-3">
						<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target=".bs-example-modal-sm">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 人员新增
						</button>
					</div>
				</div>
			</form>
		</div>
		<table class="table table-striped table-bordered table-hover table-condensed" style="text-align:center;">
			<tr class="info">
				<td width="5%"><span class="out">用户名 </span></td>
				<td width="12%"><span class="out">姓名</span></td>
				<td width="9%"><span class="out">帐户状态</span></td>
				<td width="9%"><span class="out">操作</span></td>
			</tr>
			<s:iterator value="users" id="user" status="state">
				<tr>
					<td><s:property value="#user.username" /></td>
					<td><s:property value="#user.realname" /></td>
					<td><s:if test="#user.enabled==1">
							正常
						</s:if> <s:elseif test="#user.enabled==2">
							锁定
						</s:elseif> <s:elseif test="#user.enabled==3">
							不可用
						</s:elseif>
					</td>
					<td align="center">
						<a class="btn btn-info btn-xs active" href="viewAuthUser.action?userid=<s:property value='#user.id'/>">
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span> 角色分配
						</a>
					</td>
				</tr>
			</s:iterator>
		</table>
		<form action="userview.action" name="userViewForm">
			<s:hidden name="username"></s:hidden>
			<s:hidden name="realname"></s:hidden>
			<s:hidden name="enabled"></s:hidden>
			<s:hidden name="userstar"></s:hidden>
			<elile:navigateBar navigateform="navigateform" actionName="userview.action" formName="userViewForm" />
		</form>
	</div>
</div>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">人员新增</h4>
		</div>
		<div class="modal-body">
			<form id="form1" name="addgg" method="post" action="addexaminee.action">
	      		<div class="row">
	      			<div class="col-md-4 text-right">
	      				<label for="">身份证号</label>
	      			</div>
	      			<div class="col-md-8">
	      				<input class="form-control" type=text id="username" name="euser.username" onblur="isidrepeat()"/>
	      			</div>
	      		</div>
	      		<div class="row" style="margin-top:5px;">
	      			<div class="col-md-4 text-right">
	      				<label for="">姓名</label>
	      			</div>
	      			<div class="col-md-8">
	      				<input class="form-control" type=text id="realname" name="euser.realname"/>
	      			</div>
	      		</div>
	      		<div class="row" style="margin-top:5px;">
	      			<div class="col-md-4 text-right">
	      				<label for="">密码</label>
	      			</div>
	      			<div class="col-md-8">
	      				<input class="form-control" type="password" id="inputpassword" name="inputpassword"/>
	      			</div>
	      		</div>
	      		<div class="row" style="margin-top:5px;">
	      			<div class="col-md-4 text-right">
	      				<label for="">确认密码</label>
	      			</div>
	      			<div class="col-md-8">
	      				<input class="form-control" type="password" id="confirmpassword" name="euser.password"/>
	      			</div>
	      		</div>
	      		<div class="row" style="margin-top:5px;">
	      			<div class="col-md-4 text-right">
	      				<label for="">角色</label>
	      			</div>
	      			<div class="col-md-8">
	      				<select id="role" name="role" class="form-control">
							<c:forEach var="m" items="${addlist}" varStatus="status">
								<option value="<c:out value='${m[0]}'/>">
									<c:out value='${m[1]}' />
								</option>
							</c:forEach>
						</select>
	      			</div>
	      		</div>
	      		<div class="row" style="margin-top:5px;">
	      			<div class="col-md-1 col-md-offset-9">
	      				<div class="form-group">
	      					<button type="button" class="btn btn-primary btn-sm" onclick="closewindow()">提 交</button>
	      				</div>
	      			</div>
	      		</div>
      		</form>
      	</div>
	</div>
  </div>
</div>
<script src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function closewindow() {
		var inputpassword = document.getElementById("inputpassword").value;
		var confirmpassword = document.getElementById("confirmpassword").value;
		if (inputpassword == confirmpassword) {
			document.getElementById("form1").submit();
		} else {
			alert("输入密码和确认密码不一致！");
		}
	}
	function isidrepeat() {
		var id = document.getElementById("username").value; //证件号码
		$.ajax({
			type : 'post',
			async : false,
			url : 'queryuser.action?username=' + id,
			success : function(result) {
				if (result == "1") {
					alert("该身份证号已经存在,请勿重复添加!");
					return;
				}
			},
			error : function() {
				alert("error");
			}
		});
	}
</script>
</body>
</html>
