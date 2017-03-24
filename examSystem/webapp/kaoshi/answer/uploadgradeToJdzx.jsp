<%@page import="com.wondersgroup.falcon.paper.model.EPapers"%>
<%@page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
ProfessionBean professionBean = new ProfessionBean();
List<EPapers> p_list = professionBean.getUnUploadToJdzxPapers();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>成绩上传鉴定中心</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">成绩上传鉴定中心</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<form action="uploadgradeToJdzx.action" name="aForm" method="POST" class="form-inline">
		    		<input id="sjmc" name="epaper.sjMc" type="hidden" />
					<div class="col-md-4">
						<div class="form-group">
							<label for="sjmc_id">试卷名称：</label>
							<select class="form-control" id="paper" name="paper" onchange="checkPaper()">
								<option>请选择</option>
								<%
									for (int i = 0; i < p_list.size(); i++) {
										EPapers paper = p_list.get(i);
										out.print("<option value="+paper.getSjId()+">"+paper.getSjMc()+"</option>");
									}
								%>
							</select>
	    				</div>
					</div>
				</form>
				<div class="col-md-3 col-md-offset-5">
					<a class="btn btn-info" id="aUpload" role="button" onclick="upload('<c:out value="${sjMc}"/>','<c:out value="${sjid}"/>')">
						上传<font style="color: red;">&nbsp;&nbsp;<c:out value="${sjMc}"/>&nbsp;&nbsp;</font>到鉴定中心
					</a>
				</div>
			</div>
		</div>
		<table class="table table-striped table-bordered table-hover table-condensed" id="tb" >
             <tr class="info">
                <td width="5%" align="center">
                	<!-- <input id="checkAll" name="all1" type="checkbox" onClick="selectall()">&nbsp; --><span class="out">序号 </span>
                </td>
                <td width="30%" align="center"><span class="out">试卷名称</span></td>
                <td width="12%" align="center"><span class="out">考试日期</span></td>
                <td width="9%" align="center"><span class="out">姓名</span></td>
                <td width="12%" align="center"><span class="out">身份证号</span></td>
                <td width="6%" align="center"><span class="out">分数</span></td>
                <td width="6%" align="center"><span class="out">状态</span></td>
             </tr>
        	<c:forEach var="aBean" items="${list}" varStatus="status">
             <tr id='r1'>
                <td class="text-center">
                	<!-- <input type="checkbox"  id="checkbox"  name="deleteid" value=""/>&nbsp; --><c:out value="${status.index+1}"/>
                </td>
                <td class="text-center">
	            	<c:out value="${aBean.sjMc}"/>
	     	    </td>
	     	    <td class="text-center"><fmt:formatDate value="${aBean.sjKksj}" type="date" timeStyle="default" pattern="yyyy-MM-dd"/></td>
	            <td class="text-center"><c:out value="${aBean.realname }"></c:out></td>
	            <td class="text-center"><c:out value="${aBean.username }"></c:out></td>
	            <td class="text-center"><c:out value="${aBean.djZf}"/></td>
	            <td class="text-center">
                <c:choose>
                <c:when test="${aBean.cheatflag == '0'}">
                  <font color="green">正常</font>
                </c:when>
                <c:when test="${aBean.cheatflag == '1'}">
                  <font color="red">作弊</font>
                </c:when>
                <c:when test="${aBean.cheatflag == '2'}">
                  <font color="orange">缺考</font>
                </c:when>
               </c:choose>
               </td>
             </tr>
        	</c:forEach>
    	</table>
    	<c:if test="${list!= null }">
		    <div style="margin-top:-5px;">
			  	<s:form action="uploadgradeToJdzx.action" name="answerpaperpmqueryForm" method="post">
			  		<s:hidden name="epaper.sjMc"></s:hidden>
					<elile:navigateBar navigateform="navigateform" actionName="uploadgradeToJdzx.action" formName="answerpaperpmqueryForm"/>
				</s:form>
			</div>
	  	</c:if>
	</div>
</div>
<script src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script language="JavaScript" type="text/JavaScript" >
//全选
function selectall()
{
	var check=document.getElementById("checkAll");
    var values = document.getElementsByName("deleteid");  
      for (var i = 0; i < values.length; i++)
       	values[i].checked = check.checked;
}

function upload(v,id){
	$("#aUpload").removeAttr("onclick");
	if(v!=""){
		//var u = 'uploadtoyth.action?sjMc='+v;
		var u = 'uploadToJdzxByWs.action?sjMc='+v+'&sjid='+id;
		var url = encodeURI(encodeURI(u));
		$.ajax({
			type:'post',
			url:url,
			success:function(result){
				alert(result);
				window.location.reload();
			},
			error:function(){
				alert("程序出错！");
				window.location.reload();
			}
		});
	}else{
		alert("请选择试题");
	}
}
function checkPaper(){
	var sjmc = $("#paper").find("option:selected").text();
	$("#sjmc").val(sjmc);
	document.aForm.submit();
}
</script>
</body>
</html>
              
       

