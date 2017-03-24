<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
					<link href="<%=request.getContextPath()%>/css2/style.css" rel="stylesheet" type="text/css" />
					<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
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

	function LoadRight(href){
		window.parent.content.location.replace(href);
	}
	function showGg(href){
		var return_val = window.showModalDialog("kaoshi/paper/ExamNotice.jsp", 
				"","dialogWidth=1200px;dialogHeight=600px;center:yes;scroll:yes;status:no;controlbox:false");
		/* if (return_val.code == "true") {
			window.parent.content.location.replace(href);
		} */
		window.parent.content.location.replace(href);
	}
	function dochange(id){
		document.getElementById(id).style.color="red";
	}
	function undochange(id){
		document.getElementById(id).style.color="black";
	}
	function drawtable(total,checkArray){
		//alert(checkArray);
		$("#paperindex").empty();//清空table
		var theTable = document.getElementById("paperindex");
		var v1 = Math.ceil(total/7);
		var index = 1;
		for(var i=0;i<v1;i++){
			var rowCount = theTable.rows.length;
			var row = theTable.insertRow(rowCount);
			for(var j=0;j<7;j++){
				if(index<=total){
					var cell = row.insertCell(j);
					cell.innerHTML="<a id="+index+" href='javascript:void(0)' onclick='numclick(this)' style='text-decoration:none;color: black;'>"+index+"</a>";
					index++;
				}else{
					break;
				}
				
			}
		}
		for(var k=0;k<checkArray.length;k++){
			$("#"+checkArray[k]).css('color','red');
		}
	}
	function numclick(index){
		parent.content.loadto(index.id);
	}
	function ushow(){
		$("#d_index").css('display','none');
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
	<table width="209" border="0" cellspacing="0" cellpadding="0">
		<c:if test="${userStar!='1' }">
		<tr>
			<td class="header05">&gt;&gt; 菜单导航</td>
			<td class="header06">&nbsp;</td>
		</tr>
		</c:if>
	</table>
	<table width="201" height="100%" border="0" cellpadding="0" cellspacing="0" class="left_menu_border">
		<tr class="left_users_info">
		<c:if test="${userStar!='1' }">
			<td align="left" valign="top">
				<s:iterator value="eusermenus" status="status" id="menu">
					<div id="left_menu">
						<div class="title_title" id='menu<s:property value="#status.index+1"/>' onclick='doMenu(<s:property value="#status.index+1"/>)' style="cursor: pointer" onmouseover="style.color='#ff3366';" onmouseout="style.color='#000';">
							<img src="<%=request.getContextPath()%>/images/left_menu/new_2.jpg" style="padding-top: 8px;" />
							<s:property value="#menu.menuname" />
						</div>
						<ul id='submenu<s:property value="#status.index+1"/>' <s:if test="#status.first!=true">style="display:none;"</s:if>>
							<s:iterator value="#menu.childMenusView" id="childMenu" status="st">
								<s:if test="#status.first==true">
									<c:if test="${st.first==true}">
										<script language="JavaScript">
				         			LoadRight('<c:out value="${childMenu.menueurl}"/>')
				         			</script>
									</c:if>
								</s:if>
								<li><a href='<%=request.getContextPath()%>/<s:property value="#childMenu.menueurl" />' target="content"><s:property value="#childMenu.menuname" /> </a></li>
							</s:iterator>
						</ul>
					</div>
				</s:iterator>
			</td>
		</c:if>
		<c:if test="${userStar=='1' }">
		<td align="left" valign="top">
				<s:iterator value="eusermenus" status="status" id="menu">
					<div id="left_menu">
						<%-- <div class="title_title" id='menu<s:property value="#status.index+1"/>' onclick='doMenu(<s:property value="#status.index+1"/>)' style="cursor: pointer" onmouseover="style.color='#ff3366';" onmouseout="style.color='#000';">
							<img src="<%=request.getContextPath()%>/images/left_menu/new_2.jpg" style="padding-top: 8px;" />
							<s:property value="#menu.menuname" />
						</div> --%>
						<%-- <ul id='submenu<s:property value="#status.index+1"/>' <s:if test="#status.first!=true">style="display:none;"</s:if>>
							<s:iterator value="#menu.childMenusView" id="childMenu" status="st">
								<s:if test="#status.first==true">
									<c:if test="${st.first==true}">
										<script language="JavaScript">
				         			LoadRight('<c:out value="${childMenu.menueurl}"/>')
				         			</script>
									</c:if>
								</s:if>
								<li><a href='<%=request.getContextPath()%>/<s:property value="#childMenu.menueurl" />' target="content"><s:property value="#childMenu.menuname" /> </a></li>
							</s:iterator>
						</ul> --%>
							<s:iterator value="#menu.childMenusView" id="childMenu" status="st">
								<s:if test="#status.first==true">
									<c:if test="${st.first==true}">
										<script language="JavaScript">
											showGg('<c:out value="${childMenu.menueurl}"/>');
				         					//LoadRight('<c:out value="${childMenu.menueurl}"/>');
				         				</script>
									</c:if>
								</s:if>
								<%-- <li><a href='<%=request.getContextPath()%>/<s:property value="#childMenu.menueurl" />' target="content"><s:property value="#childMenu.menuname" /> </a></li> --%>
							</s:iterator>
					</div>
				</s:iterator>
			</td>
			</c:if>
		</tr>
	</table>
	<c:if test="${userStar=='1' }">
		<div style="margin-top: 10px" id="d_index">
			<table id="paperindex" style="font-size: 20px;margin-left: 5px;text-align:center;" cellspacing="5" ></table>
		</div>
	</c:if>
</body>
</html>

