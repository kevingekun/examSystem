<%@ page contentType = "text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>菜单赋权</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script  src="<%=request.getContextPath() %>/authority/js/dhtmlXCommon.js"></script>
<script  src="<%=request.getContextPath() %>/authority/js/dhtmlXTree.js"></script></head>
<script>
function onDblSelect(id){
	if (tree.hasChildren(id) > 0){
		if (tree.getOpenState(id) == 1) 
			tree.closeItem(id); 
		else tree.openItem(id);
			return;
	}

}

function onNodeSelect(id){
	alert();
}

function authorityuser() {
	document.aForm.list_id.value = "," + tree.getAllChecked() + ",";
  
}

function save(){
	document.aForm.usermenusids.value=tree.getAllCheckedBranches();
	if(document.aForm.usermenusids_old.value ==document.aForm.usermenusids.value){
		alert("没有变更");
		return false;
	}
	return true;
	
}



function cancel(){
	window.location.href="<%=request.getContextPath() %>/eauthority.action";
}
</script>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">角色：<s:property value="eauthority.name"/></td>
        <td  class="header3"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">
<s:form name="aForm" action="updateuserMeanu" method="post" onsubmit="return save();">
<s:hidden name="authorityid"/>
<s:hidden name="usermenusids_old"/>
<s:hidden name="usermenusids"/>
               <div id="treebox"  style="height:300;width:99%; overflow:auto;" />
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
	</script></div>
	<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="40" align="center" valign="bottom">
                      <input type="submit" name = "button_editfile" value="确定"  class="submit_2"/>
                      <input type = "button" onclick = "cancel()" name = "button_editfile1" value="返回"  class="submit_2" onclick = "cancel()"/>
                    </td>
                  </tr>
          </table>
</s:form>	
</div>
</td>
</tr>
</table>
</body>
</html>
