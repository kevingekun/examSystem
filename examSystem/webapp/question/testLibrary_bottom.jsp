<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String gzid = request.getParameter("gzid");
	String gzdj = request.getParameter("gzdj");
	String gzmc = request.getParameter("gzmc");
	String state = request.getParameter("state");
	String cpyFS = request.getParameter("companyForShort");
	String tkCat = request.getParameter("tkCategory");
	String cpyBC = request.getParameter("companyBriefCode");
	if(gzmc!=null&&!"".equals(gzmc)){
		gzmc = java.net.URLDecoder.decode(gzmc , "UTF-8");
		gzdj = java.net.URLDecoder.decode(gzdj , "UTF-8");
	}
	if(cpyFS!=null&&!"".equals(cpyFS)){
		cpyFS = java.net.URLDecoder.decode(cpyFS , "UTF-8");
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>鉴定要素bottom</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery/jquery.1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="js/ext-editgrid/resources/css/ext-all.css" />
<script type="text/javascript" src="js/ext-editgrid/examples/shared/include-ext.js"></script>
<!-- <script type="text/javascript" src="js/ext-editgrid/examples/shared/options-toolbar.js"></script>右上角grid样式变更 -->
<script type="text/javascript">
var gzid = '<%=gzid%>',
	dj='<%=gzdj%>',
	gzmc='<%=gzmc%>',
	state='<%=state%>';
	cpyFS='<%=cpyFS%>';
	tkCat='<%=tkCat%>';
	cpyBC='<%=cpyBC%>';
	//alert(gzid+' '+dj+gzmc);
//var gzid = '4030301',dj='2';
//var gzid = '6-04-02-05',dj='5';
</script>
<!-- <script type="text/javascript" src="js/ext-editgrid/examples/grid/ext-all.js"></script> -->
<script type="text/javascript" src="js/ext-editgrid/examples/grid/row-editing.js"></script>

</head>

<body bgcolor="#ebf3f6">
	<div id="editor-grid"></div>
</body>
</html>
