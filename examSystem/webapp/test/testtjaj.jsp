<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <script type="text/javascript">
  function change(){
  alert();
  var a=document.getElementById('tjaj');
  alert(a);
  a.disable=true;
  
  }
  
  </script>
  <head>

  
  <body>
    This is my JSP page. <br>
  </body>
   <form name="aa">
<input name="tjaj11" id="tjaj" type="button" value="Ìá½»" onclick=" change();">
</form>

</html>
