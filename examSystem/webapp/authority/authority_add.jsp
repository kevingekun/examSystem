<%@ page contentType = "text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>增加角色</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
</head>
<script>

function onDblSelect(id){
	if (tree.hasChildren(id) > 0){
		if (tree.getOpenState(id) == 1) 
			tree.closeItem(id);
		else tree.openItem(id);
			return;
	}

}

		
function initMenu(){
	
	for(var i=1; i<=2; i++){
		document.all["menudiv"+i].style.display="none";
		document.all["menulink"+i].style.background="#CCCCCC";
	}
 }

function showMenu(i){
		document.all["menudiv"+i].style.display="";
		document.all["menulink"+i].style.background="#EEEEEE";
}

function changemenu(i){
	initMenu();
	showMenu(i);
}
function checkform(form){
	if(form.name.value==""){
		alert("请输入角色名称!"); return false;
	}
	if(form.value==""){
		alert("请输入角色说明!"); return false;
	}
	return true;
	
}


function cancel(){
	history.go(-1);
}
</script>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">新增角色</td>
        <td  class="header3"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">
<s:form action="addNewEauthority"  name="aForm" method="POST" onsubmit="return checkform(this);">
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >		  
				   <tr class="row_height"> 
				   	 <td align="right">角色名称：</td>
				   	 <td colspan="1" align="left" >
					 <s:textfield   maxlength="10"  name="name" value="" cssStyle="width: 160px;"></s:textfield>
				   	   <font color="#FF0000">*</font></td>
				   	 </tr>           
				   <tr class="row_height"> 
				   	 <td align="right">说明：</td>
				   	 <td colspan="1" align="left" >
				   	 <s:textfield  maxlength="100" name="description" value="" cssStyle="width: 160px;"></s:textfield>
			   	     <font color="#FF0000">*</font></td>
				   	 </tr> 					          	
</table>
	<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="40" align="center" valign="bottom">
                      <input type="submit" name="Submit2" value="确定"  class="submit_2"/>
                      <input type = "button" onclick = "cancel()" name = "button_editfile1" value="返回"  class="submit_2"/>
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
