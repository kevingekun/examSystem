<%@ page language="java"  pageEncoding="gbk"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>jump</title>

  </head>
  
  <body>
  	<form action="<%=request.getContextPath() %>/papersServlet"  name="aForm" method="POST">
  		<s:hidden name="actionType" value="query"/>
  		<s:hidden name="sjZt" value="%{preState}" />
  		<s:hidden name="sjMc" value="%{sjMc}" />
  	</form>
  </body>
  <script type="text/javascript">
  	document.aForm.submit();
  	//alert(<s:property value="preState" />);
  </script>
</html>
