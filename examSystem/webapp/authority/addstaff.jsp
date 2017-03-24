<%@ page contentType = "text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
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
	<title>导入考生(一体化)</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div class="container-fluid">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">导入考生<span class="label label-success">一体化</span></h3>
		</div>
		<div class="panel-body">
			<form id="form-imp" method="post" action="addstaff.action" name="addstaffForm" class="form-inline">
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
							<label for="examId">鉴定批次号：</label>
		    				<input type="text" class="form-control" name="jdpcId" id="examId" value='<s:property value="jdpcId"/>' >
	    				</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<button class="btn btn-sm btn-primary" id="btn-imp" type="button" data-loading-text="Loading..." autocomplete="off">
								<span class="glyphicon glyphicon-import" aria-hidden="true"></span> 导入
							</button>
	    				</div>
					</div>
				</div>
			</form>
		</div>
		<table class="table table-striped table-bordered table-hover table-condensed text-center" id="tb" >
			<tr class="info">
				<td width="5%"><span class="out">序号</span></td>
				<td width="5%"><span class="out">准考证号 </span></td>
				<td width="8%"><span class="out">考生姓名</span></td>
				<td width="9%"><span class="out">身份证号</span></td>
				<td width="20%"><span class="out">鉴定批次号</span></td>
				<td width="10%"><span class="out">考场</span></td>
				<td width="20%"><span class="out">考场地址</span></td>
			</tr>
			<s:iterator value="staff" id="user" status="state">
              <tr id='r1'>
              	<td><s:property value="#state.index+1" /></td>
                <td><s:property value="#user.zkh"/></td>
                <td><s:property value="#user.examineename"/></td>
	            <td><s:property value="#user.IDnumber"/></td>
	     	    <td>
	     	    	<font style="display:block;width:240px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<s:property value="#user.examid" />">
						<s:property value="#user.examid" />
					</font>
	     	    </td>
	     	    <td><s:property value="#user.examroom"/></td>
	     	    <td>
	     	    	<font style="display:block;width:260px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<s:property value="#user.examroomadress" />">
						<s:property value="#user.examroomadress" />
					</font>
	     	    </td>
             </tr>
           </s:iterator>
		</table>
		<c:if test="${staff!=null }">
			<s:form action="addstaff.action" name="chaForm">
				<elile:navigateBar navigateform="navigateform" actionName="addstaff.action" formName="chaForm"/>
			</s:form>
		</c:if>
	</div>
</div>
<script type="text/javascript" src="bootstrap/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
$('#btn-imp').on('click',function(){
	var $btn = $(this).button('loading');
	$("#form-imp").submit();
	$btn.button('reset');
});

</script>
</body>
</html>
