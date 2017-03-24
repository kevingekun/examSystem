<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="com.wondersgroup.utils.GroupUtil" %>
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
	<title>详细信息</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">成绩排名</h3>
		</div>
		<table class="table table-striped table-bordered table-hover table-condensed" id="tb" >
			<tr class="info">
				<td align="center"><span class="out">排名</span></td>
				<td align="center"><span class="out">试卷名称</span></td>
				<td align="center"><span class="out">考生姓名</span></td>
				<td align="center"><span class="out">准考证号</span></td>
				<td align="center"><span class="out">身份证号</span></td>
				<td align="center"><span class="out">开考时间</span></td>
				<td align="center"><span class="out">结束时间</span></td>
				<td align="center"><span class="out">得分</span></td>
				<td align="center"><span class="out">状态</span></td>
			</tr>
			<s:iterator value="gradeList" id="answerpaper" status="status">
	           <tr id='r1'>
	             <td align='center'><s:property value="#status.index+1"/></td>
	             <td align='center' title="<s:property value='#answerpaper[0]'/>"><s:property value="#answerpaper[0]"/></td>
	             <td align='center'><s:property value="#answerpaper[1]"/></td>
				 <td align='center'><s:property value="#answerpaper[2]"/></td>
				 <td align='center'><s:property value="#answerpaper[3]"/></td>
				 <td align='center'><s:property value="#answerpaper[4]"/></td>
	     	     <td align='center'><s:property value="#answerpaper[5]"/></td>
	             <td align='center'><s:property value="#answerpaper[6]"/></td>
	 			 <td align='center'>
		 			 <c:choose>
						<c:when test="${answerpaper[7]=='0'}"><font color="green">正常</font></c:when>
						<c:when test="${answerpaper[7]=='1'}"><font color="red">作弊</font></c:when>
						<c:when test="${answerpaper[7]=='2'}"><font color="orange">缺考</font></c:when>
					 </c:choose>
				 </td>
	           </tr>
	         </s:iterator>
	    </table>
	    <div class="row" style="margin-top:5px;margin-left:3px;">
		    <div class="col-md-6">
		    	<div class="row">
			    	<div class="col-md-3">
			    		参考总人数:<s:property value="all"/>
			    	</div>
			    	<div class="col-md-3">
			    		不及格人数:<s:property value="unpass"/>
			    	</div>
			    	<div class="col-md-3">
			       		平均分：<s:property value="average"/>
			    	</div>
			    	<div class="col-md-3">
			       		合格率：<s:property value="percent"/>%
			    	</div>
			    </div>
		    </div>
	    </div>
	    <div class="row" style="margin-top:5px;margin-bottom:5px;">
	    	<div class="col-md-1 col-md-offset-3">
	    		<button class="btn btn-primary" onclick="goback();">返回</button>
	    	</div>
	    	<div class="col-md-1 col-md-offset-3">
	    		<button class="btn btn-primary" onclick="excel(<s:property value="#answerpaper[9]"/>);">导出excel</button>
	    	</div>
	    </div>
    </div>
</div>
<script type="text/javascript" src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/JavaScript">
//导出excel
function excel(paperid){
	window.location.href="cjpmexcelAction.action?paperid="+paperid;
}
//查看参加过的答卷
function selectInPapers(id){
	var url="usercjpmAction.action?djryid="+id;
	window.open(url,"列表查询","height=500,width=1000,top=100,left=200,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=yes");
}
function goback(){
	window.location.href="answerpaperpmquery.action";
}
</script>
</body>
</html>