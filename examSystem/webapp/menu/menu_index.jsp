<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	<title>菜单</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<style type="text/css">
.panel-body{
	padding:1px 20px;
}
.container-fluid{
	padding:0;
}
.panel{
	border-radius:0px;
	border:0px;
	margin-bottom:0px;
}
.panel-collapse a{
	width:100%;
	height:25px;
}
.nav>li>a{
	padding:4px 20px;
}
.panel-collapse a:hover{
	text-decoration:none;
	font-size:11pt;
	color:#FF0000;
	display:block;
	width:100%;
	height:25px;
}
.panel-heading a:hover{
	text-decoration:none;
}
.panel-heading a{
	width:100%;
}
.panel-primary > .panel-heading{
	border-top-left-radius:0px;
	border-top-right-radius:0px;
	background-color:#2F6B8A;
}
</style>
<script src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
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
		window.showModalDialog("kaoshi/paper/ExamNotice.jsp", 
				"","dialogWidth=1200px;dialogHeight=600px;center:yes;scroll:yes;status:no;controlbox:false");
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
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-primary">
			<c:if test="${userStar!='1' }">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>  菜单导航
					</h3>
				</div>
			</c:if>
			<c:if test="${userStar!='1' }">
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					<ul class="nav nav-pills nav-stacked">
						<s:iterator value="eusermenus" status="status" id="menu">
							<li role="presentation">
								<div class="panel panel-info">
									<div class="panel-heading" role="tab" id="menu<s:property value="#status.index+1"/>">
										<h4 class="panel-title">
											<a role="button" data-toggle="collapse" data-parent="#accordion" style="display:block" href="#collapse<s:property value="#status.index+1"/>" aria-expanded="true" aria-controls="collapse<s:property value="#status.index+1"/>">
												<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>  
												<s:property value="#menu.menuname" />
											</a>
										</h4>
									</div>
									<div id="collapse<s:property value="#status.index+1"/>" class="panel-collapse collapse <s:if test="#status.first==true">in</s:if>" role="tabpanel" aria-labelledby="menu<s:property value="#status.index+1"/>">
										<ul class="nav nav-pills nav-stacked">
											<s:iterator value="#menu.childMenusView" id="childMenu" status="st">
												<li>
													<s:if test="#status.first==true">
														<c:if test="${st.first==true}">
															<script language="JavaScript">
										         				LoadRight('<c:out value="${childMenu.menueurl}"/>');
										         			</script>
														</c:if>
													</s:if>
													<a href='<%=request.getContextPath()%>/<s:property value="#childMenu.menueurl" />' target="content"><s:property value="#childMenu.menuname" /> </a>
												</li>
											</s:iterator>
										</ul>
									</div>
								</div>
							</li>
						</s:iterator>
					</ul>
				</div>
			</c:if>
			<c:if test="${userStar=='1' }">
				<s:iterator value="eusermenus" status="status" id="menu">
					<s:iterator value="#menu.childMenusView" id="childMenu" status="st">
						<s:if test="#status.first==true">
							<c:if test="${st.first==true}">
								<script language="JavaScript">
									showGg('<c:out value="${childMenu.menueurl}"/>');
		         				</script>
							</c:if>
						</s:if>
					</s:iterator>
				</s:iterator>
			</c:if>
		</div>
	</div>
	<c:if test="${userStar=='1' }">
		<div style="margin-top: 10px" id="d_index">
			<table id="paperindex" style="font-size: 20px;margin-left: 5px;text-align:center;" cellspacing="5" ></table>
		</div>
	</c:if>
</body>
</html>

