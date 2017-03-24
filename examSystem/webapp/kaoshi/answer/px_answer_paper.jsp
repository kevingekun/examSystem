<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
	<title>试卷排名</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
   <!--  <link href="bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen"> -->
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">试卷排名</h3>
		</div>
		<div class="panel-body">
			<form action="answerpaperpmquery.action" name="aForm" method="post" class="form-inline">
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label for="sjmc_id">试卷名称：</label>
		    				<input type="text" class="form-control" id="sjmc_id" name="epaper.sjMc" value='<s:property value="epaper.sjMc"/>' >
	    				</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="kksj_id">开考时间：</label>
							<input type="date" class="form-control" id="kksj_id" name="sjKksjbegin" value='<s:property value="sjKksjbegin"/>'>
	    				</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="zjsj_id">组卷时间：</label>
							<input type="date" class="form-control" id="zjsj_id" name="sjZjsjbegin" value='<s:property value="sjZjsjbegin"/>'>
	    				</div>
					</div>
				</div>
				<div class="row" style="margin-top:10px;">
					<div class="col-md-4 col-md-offset-5">
						<input type="button" class="btn btn-default" onClick="javascript:doQuery();" value="查 询"  />
					</div>
				</div>
			</form>
		</div>
		<table class="table table-striped table-bordered table-hover table-condensed" id="tb" >
             <tr class="info">
                <td width="5%" align="center"><input id="checkAll" name="all1" type="checkbox" onClick="selectall()">&nbsp;<span class="out">序号 </span></td>
                <td width="30%" align="center"><span class="out">试卷名称</span></td>
                <td width="9%" align="center"><span class="out">开考日期</span></td>
                <td width="9%" align="center"><span class="out">有效截止日期</span></td>
                <td width="9%" align="center"><span class="out">组卷时间</span></td>
                <td width="8%" align="center"><span class="out">状态</span></td>
                <td width="20%" align="center"><span class="out">操作</span></td>
             </tr>
        	 <c:forEach var="aBean" items="${epapers}" varStatus="status">
	             <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
	                <td align='center'><input type="checkbox" id="checkbox" name="deleteid" value="<c:out value='${aBean.sjId}'/>"/>&nbsp;<c:out value="${status.index+1}"/></td>
	                <td align='center'><c:out value="${aBean.sjMc}"/></td>
		     	    <td align='center'><fmt:formatDate value="${aBean.sjKksj}" type="date" timeStyle="default" pattern="yyyy-MM-dd"/></td>
		            <td align='center'><fmt:formatDate value="${aBean.sjYxqjzsj}" type="date" timeStyle="default" pattern="yyyy-MM-dd"/></td>
		            <td align='center'><fmt:formatDate value="${aBean.sjZjsj}" type="date" timeStyle="default" pattern="yyyy-MM-dd"/></td>
		            <td align='center'>
	                <c:choose>
	                <c:when test="${aBean.sjZt == '0'}">
	                  <font color="green">未考试</font>
	                </c:when>
	                <c:when test="${aBean.sjZt == '1'}">
	                  <font color="red">考试中</font>
	                </c:when>
	                <c:when test="${aBean.sjZt == '2'}">
	                  <font color="orange">已结束</font>
	                </c:when>
	               </c:choose>
	               </td>
		            <td align="center">
		            	<button class="btn btn-xs btn-primary" onclick='viewpam(<c:out value="${ aBean.sjId}"/>);'>查看试卷排名</button>
		            	<button id="vr" class="btn btn-xs btn-primary" data-loading-text="Loading..." autocomplete="off" onclick='viewRight(<c:out value="${ aBean.sjId}"/>);'>查看错误率</button>
		            </td>
	             </tr>
        	</c:forEach>        
    	</table>
    	<c:if test="${epapers!= null }">
	    	<div style="margin-top:-5px;">
			  	<form action="answerpaperpmquery.action" name="answerpaperpmqueryForm" method="post" >
			  		<s:hidden name="epaper.sjMc"></s:hidden>
			  		<s:hidden name="epaper.sjZt"></s:hidden>
			  		<input type="hidden" name="sjKksjbegin" value='<s:date name="sjKksjbegin" format="yyyy-MM-dd HH:mm:ss"/>'/>
			  		<input type="hidden" name="sjZjsjbegin" value='<s:date name="sjZjsjbegin" format="yyyy-MM-dd HH:mm:ss"/>'/>
			  		<s:hidden name="sjZjsjend"></s:hidden>
					<elile:navigateBar navigateform="navigateform" actionName="answerpaperpmquery.action" formName="answerpaperpmqueryForm"/>
				</form>
			</div>
	    </c:if>
	</div>
</div>
<script type="text/javascript" src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script><%-- 
<script type="text/javascript" src="bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script> --%>
<script type="text/JavaScript" >
/* $(".form_datetime").datetimepicker({
    format: "dd MM yyyy - hh:ii",
    autoclose: true,
    todayBtn: true,
    startDate: "2013-02-14 10:00",
    minuteStep: 10
}); */
function doQuery() {
  document.aForm.submit();
}
function viewpam(paperid){
	window.location.href="cjpmAction.action?paperid="+paperid;
}
//查看错误率
function viewRight(paperid){
	$("#vr").button('loading');
	window.location.href="wrongPercent.action?paperId="+paperid;
	$("#vr").button('reset');
}
//全体成绩报表
function allscoreCount(){
  var url="kaoshi/answer/allscore.jsp";
 window.location.href=url;
}
//全选
function selectall()
{
	var check=document.getElementById("checkAll");
    var values = document.getElementsByName("deleteid");  
      for (var i = 0; i < values.length; i++)
       	values[i].checked = check.checked;
}
</script>
</body>
</html>
              
       

