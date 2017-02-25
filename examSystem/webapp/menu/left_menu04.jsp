<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    
    <title>菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
  
  <body>
<table width="208" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="header05">&gt;&gt; 信息查询</td>
    <td class="header06">&nbsp;</td>
  </tr>
</table>
<table width="201" height="100%" border="0" cellpadding="0" cellspacing="0" class="left_menu_border">
  <tr class="left_users_info">
    <td align="left" valign="top">
    <div id="left_menu">
     <div class="title_title" id="menu1" onclick="doMenu(1)" style="cursor:pointer" onmouseover="style.color='#ff3366';" onmouseout="style.color='#000';"><img src="../images/left_menu/new_2.jpg" style="padding-top:8px;" /> 试卷管理</div>
      <ul  id="submenu1" style="display:none;">
        <li><a href="../paper/paper_init_auto.jsp" target="content">自动组卷</a></li>
        <li><a href="../kaoshi/rgzj/rgzj.jsp" target="content">手工组卷</a></li>
        <li><a href="<%=request.getContextPath() %>/papersServlet?actionType=query&sjZt=-1" target="content">试卷查询</a></li>
      </ul>
    </div>
    <div id="left_menu">
     <div class="title_title" id="menu2" onclick="doMenu(2)" style="cursor:pointer" onmouseover="style.color='#ff3366';" onmouseout="style.color='#000';"><img src="../images/left_menu/new_2.jpg" style="padding-top:8px;" /> 答卷管理</div>
      <ul  id="submenu2" style="display:none;">
        <li><a href="tosypaperAction.action?djSyzt=0&currpage=1" target="content">答卷审阅</a></li>
        <li><a href="tosypaperAction.action?djSyzt=1&currpage=1" target="content">二次审阅</a></li>
        <li><a href="answerPaperQuerryAction.action" target="content">答卷查询</a></li>
      </ul>
    </div>
    </td>
  </tr>
</table>
  </body>
</html>
