<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>报名信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var pc_id = "<%=request.getParameter("id")%>";
	$("#pc_id").val(pc_id);
	$("#form1").submit();
});
</script>
</head>
<body>
	<form id="form1" action="${pageContext.request.contextPath}/printView.action" >
		<input id="pc_id" name="pc_id" type="hidden" />
	</form>
</body>
</html>
