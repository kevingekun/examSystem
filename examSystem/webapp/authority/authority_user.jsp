<%@ page contentType = "text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
	<title>用户赋角色</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<script src="authority/js/dhtmlXCommon.js"></script>
<script src="authority/js/dhtmlXTree.js"></script>
<script>
function save(){
	document.aForm.eauthorityides.value=tree.getAllCheckedBranches();
	if(document.aForm.eauthorities_old.value ==document.aForm.eauthorityides.value){
		alert("没有变更");
		return false;
	}
	return true;
}
function cancel(){
	window.location.href="<%=request.getContextPath() %>/userview.action";
}
</script>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">用户：<s:property value="euser.username"/></h3>
		</div>
		<form class="form-horizontal" name="aForm" action="updateAuthUser.action" method="post" onsubmit="return save();">
			<s:hidden name="userid"/>
			<s:hidden name="eauthorities_old"/>
			<s:hidden name="eauthorityides"/>
			<div id="treebox" style="height:300;width:99%; overflow:auto;">
				<script>
					tree = new dhtmlXTreeObject("treebox","100%","100%",0);
			
					tree.setImagePath("<%=request.getContextPath() %>/authority/images/");
					tree.enableTreeLines(true);
					tree.enableCheckBoxes(true);
					tree.enableThreeStateCheckboxes(true);
					
					tree.setImageArrays("plus","plus2.gif","plus3.gif","plus4.gif","plus.gif","plus5.gif");
					tree.setImageArrays("minus","minus2.gif","minus3.gif","minus4.gif","minus.gif","minus5.gif");
					tree.setStdImages("book.gif","books_open.gif","books_close.gif");					
					
					<s:iterator value="eauthorities" status="status" id="aBean">
						tree.insertNewItem('0','<s:property value="#aBean.id"/>','<s:property value="#aBean.description"/>',
					 	'0','folder.gif','folderOpen.gif','folder.gif','');
					</s:iterator>
					
					 <c:forEach var="m" items="${euser.authorities}" varStatus="status">
					 tree.setCheck('<c:out value="${m.id}"/>',true);
					 //alert('<c:out value="${m.id}"/>');
					 </c:forEach>
					tree.closeAllItems(0);
					document.aForm.eauthorities_old.value =tree.getAllCheckedBranches();
					//alert(tree.getAllCheckedBranches());
				</script>
			</div>
			<div class="form-group">
	  			<div class="col-md-offset-4 col-md-1">
	  				<button type="submit" class="btn btn-success">
	  					<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 确定
	  				</button>
	  			</div>
	  			<div class="col-md-offset-1 col-md-1">
	  				<button type="submit" class="btn btn-warning" onclick="cancel()">
	  					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> 返回
	  				</button>
	  			</div>
	  		</div>
		</form>
	</div>
</div>
</body>
</html>
