<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>左侧菜单</title>
<link href="../css2/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript">
function doAction(i,u){
	
	 parent.head.location.href="head.jsp?menuid="+i;
	 parent.main.location.href=u;
}
function doMenu(i){
	var submenu_num = 0;
		for(var j=1; j<=submenu_num; j++){
		document.all["submenu" + j].style.display = "none";
	}
	if(document.all["submenu"+i].style.display == "none"){
		document.all["submenu"+i].style.display = "";
	}else{
		document.all["submenu"+i].style.display = "none";
	}
}
</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #97c2cf;
}
-->
</style>

</head>
<body style="height:100%;">
<table width="208" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="header05">&gt;&gt; 信息查询</td>
    <td class="header06">&nbsp;</td>
  </tr>
</table>
<table width="201" height="100%" border="0" cellpadding="0" cellspacing="0" class="left_menu_border">
  <tr class="left_users_info">
    <td align="left" valign="top"><div id="left_menu">
     <div class="title_title" id="menu1" onclick="doMenu(1)" style="cursor:pointer" onmouseover="style.color='#ff3366';" onmouseout="style.color='#000';"><img src="../images/left_menu/new_2.jpg" style="padding-top:8px;" /> 题库管理</div>
      <ul  id="submenu1" style="display:none;">
        <li><a href="../question/question_add.jsp" target="content">试题录入</a></li>
        <li><a href="<%=request.getContextPath() %>/QuestionServlet?actionType=query&examsign=1" target="content">试题管理</a></li>
        <li><a href="toViewUpdatedQuestions.action" target="content">需要更改题目</a></li>
      </ul>
    </div>
    </td>
  </tr>
</table>
</body>
</html>
