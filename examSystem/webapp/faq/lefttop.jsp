<%@ page contentType = "text/html;charset=gb2312" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>无标题文档</title>
<link href="../inc/all.css" rel="stylesheet" type="text/css">
</head>

<jsp:useBean id = "Wonders" class="com.wondersgroup.falcon.beans.archives.FaqTree"/>
<%@ page import="java.util.List" %>
<%@ page import="com.wondersgroup.falcon.model.archives.FaqNode" %>

<script>

<%
		String selecteditem = request.getParameter("selecteditem");			//节点
		if (selecteditem == null) selecteditem = "";
		if (!selecteditem.equals("")){
			List tree = Wonders.getChildrenSimple(new Long(selecteditem));
			for (Iterator it = tree.iterator(); it.hasNext();) {
				FaqNode node =  (FaqNode) it.next();
				out.print("parent.tree.insertNewItem(");
				out.print(node.getParent().getId() + "," + node.getId() + ",'" + node.getName() + "',0,");
				if (node.getAttribute() == null) {
					out.println("\"folder.gif\",\"folder.gif\",\"folder.gif\",\"\");");
					out.println("parent.tree.setUserData(" + node.getId() + ", 'isDir', 'YES');");
				} else {
						out.println("'book.gif','book.gif','book.gif','');");
						out.println("parent.tree.setUserData(" + node.getId() + ", 'isDir', 'NO');");
				}
				if (!node.isVisible()) {
					out.println("parent.tree.setItemColor(" + node.getId()	+ ", '#CC0000', '#99FFCC');");
					out.println("parent.tree.setUserData(" + node.getId() + ", 'isVisible', 'NO');");
				} else {
					out.println("parent.tree.setUserData(" + node.getId() + ", 'isVisible', 'YES');");
				}
			}
		}
%>

		function closeAll() {
			if (parent.tree.getOpenState(101) == 1) parent.tree.closeAllItems(0);
			else parent.tree.openAllItems(0);
		}
		
</script>
<body>
	<table width="100%" height="25" border="0" cellpadding="0" cellspacing="1" bgcolor="#CEE7FC" class="small" >
  <tr> 
  	<td width = "10">
  </td>
    <td align="center"><a href="javascript:closeAll()">展开/收缩</a> 
            </td>
  </tr>
</table>

<form method = "post" action = "lefttop.jsp" name="spreadtree_form">
	<input type=hidden name=selecteditem value="">
</form>


</body>
</html>
