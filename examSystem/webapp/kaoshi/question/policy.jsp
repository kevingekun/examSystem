<%@ page contentType="text/html;charset=GBK"%>
<%@ page import="com.wondersgroup.falcon.Util.*"%>
<%@page import="com.wondersgroup.falcon.beans.archives.*"%>
<%@page import="com.wondersgroup.falcon.model.archives.*"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%
	String cname = RequestUtils.getString(request, "cname", "");
	String docid = RequestUtils.getString(request, "docid", "");
	PolicyNode node = (PolicyNode) FactoryBean.creator(cname)
			.getNodeById(Node.getNodeType(cname), new Long(docid));
	String html = "";
	if (node != null) {     
		java.sql.Clob c = node.getAttribute().getFiletext();
		StringBuffer str = new StringBuffer();
		if (c != null) {
			html = c.getSubString(1, (int) c.length());
		}
	} 
	request.setAttribute("wenhao",node.getAttribute().getFileno()+"||"+node.getName()+"||"+node.getId());
	request.setAttribute("wenhao_id",node.getAttribute().getId());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		
		<title><%=node.getName() %></title>
	</head>

<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css" />

<style>


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
p { line-height:25px; font-size:14px; text-align:left; }
p {
	font-size: 14px;
	font-family: "宋体";
	line-height: 160%;
	text-decoration: none;
	
}

.t1 {
	color: #FF0000;
	font-family: "宋体";
	font-size: 24px;
	font-weight: 800;
	text-align: justify;
	text-decoration: none;
	line-height: 120%;
}

.t10 {
	color: #FF0000;
	font-family: "宋体";
	font-size: 24px;
	font-weight: 800;
	text-align: justify;
	text-decoration: none;
	line-height: 120%;
	letter-spacing: 25px;
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
	letter-spacing: 12px;
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


.t55 {
	font-size: 25px;
	color: #FF0000;
	letter-spacing: 22px;
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
	letter-spacing: 7px;
}
.t88 {
	font-family: "宋体";
	font-size: 23px;
	line-height: 120%;
	color: #FF0000;
	letter-spacing: 12px;
	text-align: justify;
	font-weight: bolder;
}
.t99 {
	font-family: "宋体";
	font-size: 23px;
	line-height: 120%;
	color: #FF0000;
	letter-spacing: 5px;
	text-align: justify;
}
.t1212 {
	font-family: "宋体";
	font-size: 22px;
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
	letter-spacing: 3px;
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
	font-size: 26px;
	line-height: 120%;
	color: #FF0000;
	font-weight: bold;
	letter-spacing: 95px;
	text-align: justify;
}

.titles { margin:0px auto; position:relative; text-align:center; margin-top:16px; padding:0px; }
.left1 {  *float:left; margin:0px; }
.left1 div { margin:0px; white-space:nowrap; width:100%; *text-align:justify; *text-justify:distribute-all-lines; margin-bottom:5px; font-size:24px; color:#ff0000; font-weight:bold; font-family:"宋体"; line-height:24px;}
.right1{   font-size:36px; color:#ff0000; font-weight:bold; vertical-align:middle; *float:left; font-family:"宋体"; height:36px; margin:0px; text-align:left;}
.hr { border:6px solid #ff0000; *height:4px; border:2px solid #ff0000; }

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

</style>

<%
	String issuer = node.getAttribute().getIssuer();
	ArrayList issuers = null;
	if (issuer != null && !issuer.trim().equals(""))
		issuers = StringUtils.stringToArray(issuer.trim(), " ");
%>
<body <%if(node.getAttribute().getRedtop() != null && node.getAttribute().getRedtop().intValue() == 1){ %>onload="show();"<%} %> style="text-align: left;">
<div align="right"><input  type ="button" value="返回" onclick="window.history.go(-1)"/>&nbsp;&nbsp;&nbsp;<input  type ="button" value="确定并关闭" onclick="aaaa();"/></div>
<table align=center width=98%>
<tr height="24"><td align=left><font style=" font-weight:bold; font-size:14px;" > 
<%
long ef = node.getAttribute().getEffective().longValue();
if(ef==2) out.print("【部分失效】");
else if (ef==0) out.print("【失效】");
else if (ef==3) out.print("【废止】");
else{}%></font></td>
</tr></table>

	<%
		if (node.getAttribute().getRedtop() != null
				&& node.getAttribute().getRedtop().intValue() == 1) {
	%>
	<%
		if (issuer != null && !issuer.trim().equals("")) {
	%>
	<div class="titles" id="titles">
		<div class="left1" id="justlr">
			<%
				for (int i = 0; i < issuers.size(); i++) {
			%>
			<div><%=issuers.get(i)%></div>
			<%
				}
			%>
		</div>
		<div class="right1" id="midd">
			<span><%=node.getAttribute().getFiletype()%></span>
		</div>
	</div>
	<div style="margin: 5px auto; text-align: center; font-size: 12px;"><%=node.getAttribute().getFileno()%></div>
	<hr style="border:6px solid #ff0000; *height:4px; border:2px solid #ff0000; width:98%"/>
	<%
		}
		}
	%>
<table align=center width=98%>
<tr><td align=left>	
<%=html%>
</td>
</tr></table>
</body>
</html>
<script type="text/javascript">
function show(){
	var dname=document.getElementById("justlr").getElementsByTagName("div");
	var dlen=dname.length;
	var dwid=new Array();
	var dword=new Array();
	for(i=0; i<dlen; i++){
         dword[i]=dname[i].innerHTML;
		 dwid[i]=dword[i].length;
	}
	var maxs=dwid[0];
	for(j=0;j<dlen;j++){
	if(dwid[j]>maxs)
	maxs=dwid[j];
	}
	maxs=maxs*25;
	document.getElementById("justlr").style.width=maxs;
	var mid_top=document.getElementById("midd");
	var mid_h=dlen*30;
	mid_top.style.marginTop=(mid_h-54)/2;
	var title_wid=document.getElementById("titles");
	title_wid.style.width=maxs+74;
	if(dlen==1)
	{ mid_top.style.fontSize=24;
	title_wid.style.width=maxs+52;	
	mid_top.style.marginTop=-2;	
	}	
}
	function aaaa(){
		window.parent.opener.fuzhi('<c:out value="${wenhao}"/>','P');
		window.parent.close();
	}
</script>