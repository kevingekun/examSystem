<%@ page contentType = "text/html;charset=gb2312" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>无标题文档</title>

<jsp:useBean id = "Wonders" class="com.wondersgroup.falcon.beans.archives.PolicyTree"/>

<link href="../inc/all.css" rel="stylesheet" type="text/css">

	<link rel="STYLESHEET" type="text/css" href="css/dhtmlXTree.css">
	<script  src="js/dhtmlXCommon.js"></script>
	<script  src="js/dhtmlXTree.js"></script>
<script>
		function onNodeSelect(id) {
			//alert(id +"Item was selected");
			parent.frm.list.selectitem.value = tree.getSelectedItemId();
			parent.frm.list.submit();
		}
		

		function closeAll() {
			//tree.deleteItem(tree.getSelectedItemId(),true);]
			if (tree.getOpenState(309) == 1) tree.closeAllItems(0);
			else tree.openAllItems(0);
			
		}
</script>

</head>

<body scroll="no">
            <table bgcolor="#F2F8FE" width="90%" height="100%" border="0" align="left" cellpadding="0" cellspacing="0">
            	<tr >
            		
	            	<td>
                  <!-- CALLCENTER树标题 -->
                  <table width="100%" height="25" border="0" cellpadding="0" cellspacing="1" bgcolor="#9FBED8">
                    <tr>
                    	<td align="center" bgcolor="#CEE7FC">
                     	<a href="javascript:closeAll()">展开/收缩</a>
                    	</td>
                    </tr>
                    <tr>
                      <td height="1" align="left"  bgcolor="#95EDFE">
					  					</td>
                    </tr>
            	<tr>
            		<td>
            			<div id="treebox" style="width:198;height:362;background-color:#F2F8FE;border :1px solid Silver;; overflow:auto;"/>
            		</td>
            	</tr>
             			</table>	            		
	            	</td>	            		
            	</tr>

								<script>
									tree = new dhtmlXTreeObject("treebox","100%","100%",0);
							
									tree.setImagePath("images/");
									tree.enableTreeLines(true);
									//tree.enableThreeStateCheckboxes(true);
									//tree.setOnCheckHandler(onCheckselect);
									tree.setOnClickHandler(onNodeSelect);
									tree.setImageArrays("plus","plus2.gif","plus3.gif","plus4.gif","plus.gif","plus5.gif");
									tree.setImageArrays("minus","minus2.gif","minus3.gif","minus4.gif","minus.gif","minus5.gif");
							//		tree.setStdImages("book.gif","books_open.gif","books_close.gif");					
							
									<%
										Wonders.freshTree(out,false,"tree");
									%>
									//tree.refreshItem(3);
									</script>
            </table>
</body>
</html>
