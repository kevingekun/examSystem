<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
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
	<title>试题错误率</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script type="text/javascript">
	//导出excel
	function excel(paperid){
		window.location.href="wrongPercentExcelAction.action?paperid="+paperid;
	}
	function questionshow(questionid){
		window.open("<%=request.getContextPath() %>/questionDetailshow.action?questionId="+questionid,"试题详细信息",'height=400, width=800, top=100, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=yes, status=yes');
	}
</script>
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">错误率  <span class="label label-info"><s:property value="sjMc"/></span></h3>
		</div>
		<table class="table table-striped table-bordered table-hover table-condensed" id="tb">
			<tr class="info">
				<td align="center"><span class="out">试题题目</span></td>
				<%--  <td  align="center"><span class="out">工种</span></td>
               <td  align="center"><span class="out">等级</span></td> --%>
				<td align="center"><span class="out">试题类型</span></td>
				<%-- <td  align="center"><span class="out">难度系数</span></td>
               <td  align="center"><span class="out">重要程度</span></td>
               <td  align="center"><span class="out">出处</span></td>
               <td  align="center"><span class="out">专家</span></td>
               <td  align="center"><span class="out">录入时间</span></td> --%>
				<td align="center"><span class="out">试题错误率</span></td>
			</tr>
			<s:iterator value="epaperquention" id="epaper" status="index">
            <tr id='r1'>
                <td align='center'><a style="display:block;width:900px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" 
                 title="<s:property value="#epaper.equestions.stTg"/>"><s:property value="#epaper.equestions.stTg"/></a>
                </td>
               <%--  <td>
                <s:property value="#epaper.equestions.ebusinesstype.name"/>          
	     	    </td>
	     	    <td align="center">
	            	<s:property value="#epaper.equestions.equestiontype.name"/>
	            </td> --%>
	     	    <td align='center'><s:property value="#epaper.equestions.equestiontype.name"/></td>
	            <%--<td align='center'><s:property value="#epaper.equestions.eimportance.id"/>
	             <s:if test="#epaper.equestions.eimportance.id==1" >容易</s:if>
	            <s:elseif test="#epaper.equestions.eimportance.id==2" >普通</s:elseif>
	            <s:else>难</s:else> --%>
	            <!-- <s:property value="#epaper.equestions.eimportance.name"/>
	            </td>
	            <td align='center'><s:property value="#epaper.equestions.bxType"/></td>
	            <td align='center'><s:property value="#epaper.equestions.stCc"/></td>
	            <td align='center'>
                <s:property value="#epaper.equestions.stSyryId"/>
                </td>
	            <td align="center">
	            	<s:date name="#epaper.equestions.stLrsj" format="yyyy-MM-dd"/>
	            </td>--> 
	            <td align='center'><s:property value="#epaper.wrong_percent"/>%</td>
            </tr>       
         </s:iterator>
		</table>
		<div class="row" style="margin:5px;">
			<div class="col-md-1 col-md-offset-5">
				<button class="btn btn-success btn-sm" onclick="excel(<s:property value="paperId"/>);" >导出excel</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>

