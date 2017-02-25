<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.wondersgroup.falcon.jdys.bo.TreeOfJdys"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
String gzid = request.getParameter("gzid");
String dj = request.getParameter("dj");
%>
<html>
  <head>
    <%-- <base href="<%=basePath%>"> --%>
    <title>鉴定要素</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
	<link rel="StyleSheet" type="text/css" href="js/dtree2/dtree.css" />
	<script language="javascript" src="js/dtree2/dtree.js"></script>
	
  </head>
  
  <body bgcolor="#ebf3f6">
	<script type="text/javascript">
  	function click(id,name){
		window.opener.document.getElementById("jdysid").value=id;
		window.opener.document.getElementById("jdys").value=name;
		this.close();
	}
	$(document).ready(function(){
		var t = new dTree('t');
		t.add(0, -1, '鉴定要素', '', '', '');
		var gzid = <%=request.getParameter("gzid")%>;
		var dj = <%=request.getParameter("dj")%>;
		var val;
		$.ajax({
			type:'post',
			async : false,
			url:'findTreeOfJdys.action?gzid='+gzid+'&dj='+dj,
			dataType: "json", 
			success:function(result){
				val = result;
				for(var i = 0;i<result.length;i++){
					var obj = result[i];
					if(obj.dj==3){
						//javascript:click(\''+obj.jd_id+'\',\''+obj.name+'\');href="javascript:click(\''+obj.jd_id+'\',\''+obj.name+'\');"
						t.add(obj.id,obj.parentid,obj.name,'javascript:click(\''+obj.jd_id+'\',\''+obj.name+'\')',obj.jd_id);
					}else{
						t.add(obj.id,obj.parentid,obj.name,'#',obj.jd_id);
					}
				}
			},
			error:function(){
				alert("error");
			}
		});
		document.write(t);
		t.openAll();
	})
	</script>
  </body>
</html>
