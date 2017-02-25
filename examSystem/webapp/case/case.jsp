<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="com.wondersgroup.falcon.Util.*"%>
<%@page import="com.wondersgroup.falcon.beans.archives.*"%>
<%@page import="com.wondersgroup.falcon.model.archives.*"%>

<%
	String cname = RequestUtils.getString(request, "cname", "");
	String docid = RequestUtils.getString(request, "docid", "");

	//System.out.println("policy.jsp----cname:"+cname);
	//System.out.println("policy.jsp----docid:"+docid);
	
	CaseNode node = (CaseNode) FactoryBean.creator(cname)
			.getNodeById(Node.getNodeType(cname), new Long(docid));
	String html = "";

	if (node != null) {

		java.sql.Clob c = node.getAttribute().getFiletext();
		StringBuffer str = new StringBuffer();

		if (c != null) {
			html = c.getSubString(1, (int) c.length());
		}
	}
	String wenhao="||"+node.getName()+"||"+node.getId();
	request.setAttribute("wenhao_id",node.getId());
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title><%=node.getName() %></title>
	</head>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css" />
	
	<style>
body,td,table {
	MARGIN: 0px;
	font-size: 14px;
	line-height: 160%;
	color: #333333;
	SCROLLBAR-FACE-COLOR: #E4F5FC;
	SCROLLBAR-HIGHLIGHT-COLOR: #ffffff;
	SCROLLBAR-SHADOW-COLOR: #4E9BC9;
	SCROLLBAR-3DLIGHT-COLOR: #A6D2F7;
	SCROLLBAR-ARROW-COLOR: #A6D2F7;
	SCROLLBAR-TRACK-COLOR: #E0F0FD;
	SCROLLBAR-DARKSHADOW-COLOR: #E0F0FD;
	SCROLLBAR-BASE-COLOR: #F2F2F2;
	text-decoration: none;
} 

A:link {
	COLOR: #0000ff;

}

A:visited {
	COLOR: #0000ff;
	
}

A:active {
	color: purple
}

A:hover {
	COLOR: #0000ff
}

.t1 {
	color: #FF0000;
	font-family: "宋体";
	font-size: 24px;
	font-weight: 800;
	text-align: justify;
	text-decoration: none;
	line-height: 120%;
	letter-spacing: 2px;
}

.t10 {
	color: #FF0000;
	font-family: "宋体";
	font-size: 24px;
	font-weight: 800;
	text-align: justify;
	text-decoration: none;
	line-height: 120%;
	letter-spacing: 35px;
}

.t11 {
	color: #FF0000;
	font-family: "宋体";
	font-size: 20px;
	font-weight: 800;
	text-align: justify;
	text-decoration: none;
	line-height: 120%;
	letter-spacing: 2px;
}

.t12 {
	color: #FF0000;
	font-family: "宋体";
	font-size: 22px;
	font-weight: 800;
	text-align: justify;
	text-decoration: none;
	line-height: 120%;
	letter-spacing: 10px;
}

.t2 {
	color: #FF0000;
	font-family: "宋体";
	font-size: 36px;
	font-weight: 800;
	text-align: justify;
	text-decoration: none;
}

.t3 {
	color: #333333;
	font-family: "宋体";
	font-size: 18px;
	font-weight: bolder;
	text-align: center;
	text-decoration: none;
	background-position: center;
}

.t4 {
	font-size: 12px;
}

.t5 {
	font-size: 12px;
	font-family: "楷体_GB2312";
	text-decoration: none;
}
.t6{
	font-size: 20px;
	font-family: "黑体";
	text-decoration: none;
	color: #FF0000;
	font-weight: normal;
	text-align: center;
	
}	
.t {
	font-size: 14px;
	font-family: "宋体";
	line-height: 160%;
	text-decoration: none;
	text-indent: 20pt;
}

p {
	font-size: 14px;
	font-family: "宋体";
	line-height: 160%;
	text-decoration: none;
	text-indent: 20pt;
}

.t55 {
	font-size: 25px;
	color: #FF0000;
	letter-spacing: 25px;
	text-align: justify;
	font-family: "宋体";
	line-height: 120%;
}
.t77 {
	font-family: "宋体";
	font-size: 24px;
	line-height: 120%;
	text-align: justify;
	color: #FF0000;
	letter-spacing: 8px;
}
.t88 {
	font-family: "宋体";
	font-size: 24px;
	line-height: 120%;
	color: #FF0000;
	letter-spacing: 3px;
	text-align: justify;
	font-weight: bolder;
}
.t99 {
	font-family: "宋体";
	font-size: 24px;
	line-height: 120%;
	color: #FF0000;
	text-align: justify;
}
.t1212 {
	font-family: "宋体";
	font-size: 24px;
	line-height: 120%;
	color: #FF0000;
	text-decoration: none;
	text-align: justify;
	font-weight: bold;
}
.t1010 {
	font-family: "宋体";
	font-size: 24px;
	line-height: 120%;
	font-weight: bold;
	color: #FF0000;
	letter-spacing: 14px;
	text-align: justify;
	vertical-align: sub;
}
.t66 {
	font-family: "宋体";
	font-size: 26px;
	line-height: 120%;
	color: #FF0000;
	letter-spacing: 11px;
	text-align: justify;
}
.t33 {
	font-family: "宋体";
	font-size: 24px;
	line-height: 120%;
	color: #FF0000;
	font-weight: bold;
	letter-spacing: 85px;
	text-align: justify;
}
.t44 {
	font-size: 24px;
	letter-spacing: 41px;
	line-height: 120%;
	font-weight: bold;
	color: #FF0000;
}
</style>
<div align="right"><input  type ="button" value="返回" onclick="window.history.go(-1)"/>&nbsp;&nbsp;&nbsp;<input  type ="button" value="确定并关闭" onclick="aaaa();"/></div>
	
	<body>
		<%=html%>
	</body>
</html>
<script type="text/javascript">

	function aaaa(){
		window.parent.opener.fuzhi('<%=wenhao%>','c');
		window.parent.close();
	}
</script>
