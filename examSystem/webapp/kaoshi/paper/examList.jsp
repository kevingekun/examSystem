
<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.List,java.util.Set,java.util.ArrayList,java.util.Date,java.util.Iterator,com.wondersgroup.falcon.paper.model.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></HEAD>



<BODY style="font-size:12px;MARGIN: 5px;">
<input type="hidden" name="aa" value="0" />
<IFRAME NAME="uploadfrm" id="uploadfrm" src="" STYLE="HEIGHT:0; LEFT: 0px; MARGIN-TOP: 0px; WIDTH:0; SCROLL: no;"   frameborder=0></IFRAME>


<s:form name="paperSubmitform" action="exampaperActioncommit" method="post" onsubmit="return submitForm();">
<s:hidden name="paperId"/>
<table width="98%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px;">
      
      
             <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
             <s:iterator value="epqts" id="gg" status="status">
	            <td>
	            <s:url id="viewgg" action="findQuestions.action">
		          <s:param name="epqtId" value="#gg.id"></s:param>
		           <s:param name="sequence" value="#status.index"></s:param>
		           <s:param name="ryid" value="ryid"></s:param>
		           <s:param name="equestions_type" value="#gg.equestions.equestiontype.priority"></s:param>
		        </s:url>
		        <s:a href="%{#viewgg}">
		        第<s:property value="#status.index+1"/>题
		        </s:a>
	     	    </td>
	     	    </s:iterator>
	           </tr>
	           
   </table>   
      
      
      
	<!--表格结束-->
</s:form>


</BODY>
</HTML>
