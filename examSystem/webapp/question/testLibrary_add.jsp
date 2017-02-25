<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>鉴定要素</title>
	<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
	<!-- ** CSS ** -->
    <!-- base library -->
    <link rel="stylesheet" type="text/css" href="js/editgrid/resc/ext-all.css" />

    <!-- overrides to base library -->

    <!-- page specific -->
    <link rel="stylesheet" type="text/css" href="resc/examples.css" />
    <link rel="stylesheet" type="text/css" href="resc/grid-examples.css" />

    <style type="text/css">

    </style>

    <!-- ** Javascript ** -->
    <!-- ExtJS library: base/adapter -->
    <script type="text/javascript" src="js/editgrid/resc/ext-base.js"></script>

    <!-- ExtJS library: all widgets -->
    <script type="text/javascript" src="js/editgrid/resc/ext-all.js"></script>

    <!-- overrides to base library -->

    <!-- extensions -->
    <script type="text/javascript" src="js/editgrid/resc/CheckColumn.js"></script>

    <!-- page specific -->
    <script type="text/javascript" src="resc/shared/examples.js"></script>
    <script type="text/javascript" src="js/editgrid/resc/edit-grid.js" charset="utf-8"></script>
	
	<script type="text/javascript">
	function checkGz(v){
		var jobname = v.value;
		$.ajax({
			type:'post',
			url:'',
			success:function(result){
				
			},
			error:function(){
				alert("error");
			}
		});
	}
	</script>
  </head>
  
  <body>
    <div>
    	<div style="margin-left: 20px;margin-top: 20px;" >
    		<form action="">
    			<div>
	    			<a>工种：</a>
	    			<input name="gz" type="text" onblur="checkGz(this)" />
    			</div>
    			<div style="margin-top: 10px;" >
    				<a>等级：</a>
	    			<select name="grade" style="width: 100px;" >
	    				<option value="0">请选择</option>
	    				<option value="1">一级</option>
	    				<option value="2">二级</option>
	    				<option value="3">三级</option>
	    				<option value="4">四级</option>
	    				<option value="5">五级</option>
	    				<option value="6">专项</option>
	    			</select>
    			</div>
    		</form>
    	</div>
    	<div>
    		<div id="editor-grid"></div>
    	</div>
    </div>
  </body>
</html>
