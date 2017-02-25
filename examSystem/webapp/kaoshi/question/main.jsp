<%@ page contentType="text/html;charset=gb2312" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>main</title>
<script>
function showTree(){
	parent.fr.cols = "210,*";
	parent.tree.location.href = "law_tree.jsp";
}
</script>
<link href="css/main.css" rel="stylesheet" type="text/css">
</head>

<body>
<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#AAC1DE" height="1"></td>
  </tr>
  <tr>
    <td height="25" valign="middle" bgcolor="#CCE0F5"><img src="images/open.gif" width="20" height="10">操作提示</td>
  </tr>
  <tr>
    <td bgcolor="#AAC1DE" height="1"></td>
  </tr>
</table>
<table width="90%" height="50"  border="0" align="center" cellpadding="0" cellspacing="2" class="font1">
  <tr>
    <td align="left">◎ 请从左边的网站&nbsp;<a href="#" onClick="showTree()">站点导航</a>&nbsp;中选择<img src="images/unit.gif" width="16" height="20" align="absmiddle">&nbsp;&lt;站点&gt; 或 <img src="images/folder.gif" width="16" height="20" align="absmiddle">&nbsp;&lt;栏目&gt; 进行内容维护</td>
  </tr>
</table>
</body>
</html>
