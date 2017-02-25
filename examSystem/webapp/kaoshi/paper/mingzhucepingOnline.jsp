<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
<title>测评试卷信息列表</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">测评试卷信息列表</td>
        <td  class="header3"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
  <td colspan="2" valign="top" ><div id="content1" class="borader">
        <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">试卷名称</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">有效截止日期</span></td>
                <td width="13%" align="center" bgcolor="#C7E2F8"><span class="out">状态</span></td>
                <td width="17%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
             </tr>
           <%String Flag=(String)session.getAttribute("Flag");
           if(Flag==null){
        	   Flag="0";
        	   }
           if(Flag.equals("0")){
           %>  
		  <s:iterator value="papers" id="paper" status="state">
				<tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
		                <td align='center' class='num_font'><s:property value="#state.index+1"/></td>
		                <td align='center' class='num_font'><s:property value="#paper.sjMc"/></td>
			     	    <td align='center' class='num_font'><s:date name="#paper.sjYxqjzsj" format="yyyy-MM-dd HH:mm"/></td>
			            <td align='center' class='num_font'><s:if test="#paper.sjZt==0">
											<font color="green">未测评</font>
										</s:if>
										<s:elseif test="#paper.sjZt==9">
											<font color="red">测评中</font>
										</s:elseif>
										<s:elseif test="#paper.sjZt==2">
											<font color="orange">已结束</font>
										</s:elseif></td>
			            <td align='center' class='num_font'>
										<s:url action="cepingAllAction.action" id="allcanjiakaoshi">
											<s:param name="paperId" value="#paper.sjId"/>
										</s:url>
										<s:a href="%{#allcanjiakaoshi}" >[参加测评]</s:a>
										</td>
	            </tr>
			</s:iterator>
			<%} %>
		</table>
<table  width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;">
   <tr>
	<td>
		<s:form name="queryform1" method="post" action="examPaperAction">
 			<s:hidden name="sjMc"/>
 			<s:hidden name="sjZt"></s:hidden>
         	<elile:navigateBar navigateform="navigateform" actionName="examPaperAction.action" formName="queryform1"/>
    	</s:form>
    </td>
    </tr>
    </table>
    </div>
	</td>
	</tr>
    </table> 
</body>
</html>




