<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>无标题文档</title>
<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css"/>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">公告查询</td>
        <td  class="header3" width="24"><img src="newimages/content_right_bj.gif " width="24" height="22"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
    </tr>
  <tr>
    <td colspan="2" align="left"><div id="content1" class="borader">
        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
           <tr>
             <td width="80" height="40" align="right">标题：</td>
                <td align="left"> <b> <s:property value="tgg.ggbt"/></b> </td>
                  
                <td align="right">&nbsp;</td>
           </tr>
           <tr>
             <td height="40" align="right" valign="top">公告内容：</td>
                 <td height="8"  align=left style="line-height:22px;">
                  <s:property value="tgg.ggbt"/>
                  </td>
                 <td></td>
           </tr>
      
           <tr>
             <td height="40" align="right">附件下载：</td>
                 <td height="4" align=left>
                 	<s:url id="download" action="download.action"></s:url>
		            <s:a href="%{#download}">
		            	<s:property value="fj.fjmc"/>
		            </s:a>
		         </td>
             <td rowspan="2"></td>
           </tr>
           <tr>
             <td height="40" align="right">发布人员： </td>
             <td height="4"  align=left><s:property value="tgg.ryid"/></td>
           </tr>
 
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr><td align="center"><button class="submit_2" onClick="window.history.go(-1);">返回</button>
	</td></tr>
	</table>
      </td>
  </tr>
</table>
</body>
</html>           
       
