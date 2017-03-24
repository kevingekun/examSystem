<%@ page contentType = "text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
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
	<title>菜单赋权</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<script type="text/javascript" src="authority/js/dhtmlXCommon.js"></script>
<script type="text/javascript" src="authority/js/dhtmlXTree.js"></script>
<script>
function save(){
	document.aForm.usermenusids.value=tree.getAllCheckedBranches();
	if(document.aForm.usermenusids_old.value ==document.aForm.usermenusids.value){
		alert("没有变更");
		return false;
	}
	return true;
}
function cancel(){
	history.go(-1);
}
</script>
<body>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">角色:<s:property value="eauthority.name"/></h3>
		</div>
		<form class="form-horizontal" action="updateuserMeanu.action" name="aForm" method="post" onsubmit="return save();">
			<s:hidden name="authorityid"/>
			<s:hidden name="usermenusids_old"/>
			<s:hidden name="usermenusids"/>
			<div id="treebox" style="margin-top: 8px;">
				<script>
					tree = new dhtmlXTreeObject("treebox","100%","100%",0);
			
					tree.setImagePath("<%=request.getContextPath() %>/authority/images/");
					tree.enableTreeLines(true);
					tree.enableCheckBoxes(true);
					// tree.enableThreeStateCheckboxes(true);
					
					tree.setImageArrays("plus","plus2.gif","plus3.gif","plus4.gif","plus.gif","plus5.gif");
					tree.setImageArrays("minus","minus2.gif","minus3.gif","minus4.gif","minus.gif","minus5.gif");
					tree.setStdImages("book.gif","books_open.gif","books_close.gif");					
					
					<s:iterator value="emenus" status="status" id="aBean">
						tree.insertNewItem('<s:property value="#aBean.parentid"/>','<s:property value="#aBean.id"/>','<s:property value="#aBean.menuname"/>',
					 	'0','folder.gif','folderOpen.gif','folder.gif','');
					 	//二级菜单
					 	<s:iterator value="#aBean.childMenus" id="childMenu"> 
					 		tree.insertNewItem('<s:property value="#childMenu.parentid"/>','<s:property value="#childMenu.id"/>','<s:property value="#childMenu.menuname"/>',
					 		'0','folder.gif','folderOpen.gif','folder.gif','');
					 		//三级菜单
					 		<s:iterator value="#childMenu.childMenus" id="childMenu3"> 
						 		tree.insertNewItem('<s:property value="#childMenu3.parentid"/>','<s:property value="#childMenu3.id"/>','<s:property value="#childMenu3.menuname"/>',
						 		'0','folder.gif','folderOpen.gif','folder.gif','');
						 	</s:iterator> 
					 	</s:iterator> 
					</s:iterator>
					
					 <c:forEach var="m" items="${eauthority.menus}" varStatus="status">
					 tree.setCheck('<c:out value="${m.id}"/>',true);
					 //alert('<c:out value="${m.id}"/>');
					 </c:forEach>
					tree.closeAllItems(0);
					document.aForm.usermenusids_old.value =tree.getAllCheckedBranches();
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
