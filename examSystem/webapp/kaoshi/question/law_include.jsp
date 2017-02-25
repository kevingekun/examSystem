<%@ page contentType = "text/html;charset=gb2312" %>
<%
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires",0);

String filename = request.getParameter("filename");
%>
<link href="../inc/all.css" rel="stylesheet" type="text/css">
<jsp:useBean id = "policy" class="com.wondersgroup.falcon.beans.archives.PolicyTree"/>
<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<in>
<HEAD>
<META HTTP-EQUIV=¡±Pragma¡± CONTENT=¡±no-cache¡±>
<META HTTP-EQUIV=¡±Cache-Control¡± CONTENT=¡±no-cache¡±>
<META HTTP-EQUIV=¡±Expires¡± CONTENT=¡±0¡±>
</HEAD>
<%

if(session.getAttribute("callid")!=null){
		if(!session.getAttribute("callid").toString().equals("")){
			//out.print(session.getAttribute("callid"));
			policy.writeLog(new Long(filename.substring(0,filename.length()-5)), new Long(session.getAttribute("callid").toString()), ((UserDetailsImpl)util.getUserDetails()).getUser().getId());
		}
}
%>
<body scroll= "no">
<iframe name=roll4  src=../libstore/policy/<%=filename%> width="100%" height="100%" Frameborder=0 Border=0 Marginwidth=0 Marginheight=0 Scrolling=auto ></iframe>
</body>