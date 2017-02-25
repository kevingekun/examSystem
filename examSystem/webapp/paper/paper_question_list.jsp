<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<html>
<head>
<title>试题信息列表</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>

<style>
<!--
.hh {
width:200px;
overflow:hidden;
text-overflow:ellipsis;
white-space:nowrap}
-->
</style>  
<script type="text/javascript">
	function jumpback(){
		window.location.href='<%=request.getContextPath() %>/paper/paper_list.jsp';
		window.location.href='<%=request.getContextPath() %>/papersServlet?actionType=query&sjZt=-1';
	}
	
	function questionshow(questionid){
		window.open("<%=request.getContextPath() %>/questionDetailshow.action?questionId="+questionid,"试题详细信息",'height=400, width=800, top=100, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=yes, status=yes');
	}
	
</script>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">所属试卷：<s:property value="epapers.sjMc"/></td>
        <td  class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">
              <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
              <s:form action="epaperquestion" name="aForm" method="post">
                <s:hidden name="sjid" />
                <tr>
                  <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                            <tr>
                              <td  align="left" valign="middle" class="header7"></td>
                              <td  class="header8">查询条件</td>
                            </tr>
                        </table></td>
                      </tr>
                    </table>
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">                    
                          <tr>
                            <td height="8" colspan="7"></td>
                          </tr>
                          <tr>
                            <td width="15%" height="28" align="right">试题题目：</td>
                            <td width="10%" align="left"><input type="text" name="stTg" value='<s:property value="stTg"/>'></td>
                            <td width="8%" align="right">录入时间：</td>
                            <td width="17%"><input type="text" class="Wdate"style="width:90px" id="stlrsjbegin" name="stlrsjbegin" value="<fmt:formatDate value='${stlrsjbegin}' type='date' timeStyle='default'/>" onclick="WdatePicker()"/>
            				至
				            <input type="text" class="Wdate" style="width:90px" id="stlrsjend" name="stlrsjend" value="<fmt:formatDate value='${stlrsjend}' type='date' timeStyle='default'/>" onclick="WdatePicker()"/>
				            </td>
                            </tr>
                          <tr>
                            <td height="8" colspan="7"></td>
                          </tr>  
  </table>
            <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
                <tr>
                  <td align="center"><input type="submit" class="submit_2" value="查询">
                    <button class="submit_2" onclick="jumpback();">返回</button></td>
                </tr>
              </table> 
         </td>
      </tr>
        </s:form>
</table>            
  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">查询结果</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
        <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td  align="center" bgcolor="#C7E2F8"><span class="out">试题题目</span></td>
                <%-- <td  align="center" bgcolor="#C7E2F8"><span class="out">工种</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">等级</span></td> --%>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">试题类型</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">重要性</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">出处</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">专家</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">录入时间</span></td>
             </tr>
          <s:iterator value="lis" id="epaper" status="index">
            <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><a href="#" class="hh" onclick='questionshow(<s:property value="#epaper.equestions.stId"/>);'><s:property value="#epaper.equestions.stTg"/></a>
                </td>
                <%-- <td><s:property value="#epaper.equestions.ebusinesstype.name"/></td>
	     	    <td align='center' class='num_font'><s:property value="#epaper.equestions.equestiontype.name"/></td> --%>
	     	    <td align='center' class='num_font'><s:property value="#epaper.equestions.equestiontype.name"/></td>
	            <td align='center' class='num_font'><s:property value="#epaper.equestions.eimportance.name"/></td>
	            <td align='center' class='num_font'><s:property value="#epaper.equestions.stCc"/></td>
	            <td align='center' class='num_font'>
                <s:property value="#epaper.equestions.stSyryId"/>
               </td>
	            <td align="center">
	            	<s:date name="#epaper.equestions.stLrsj" format="yyyy-MM-dd HH:mm"/>
	            </td>
            </tr>       
         </s:iterator>
	</table>
	<s:form name="queryform1" method="post" action="epaperquestion">
		 <s:hidden name="sjid" />
    	 <s:hidden  name="stTg" />
    	 <input type="hidden" name="stlrsjbegin" value="<fmt:formatDate value='${stlrsjbegin}' type='date' timeStyle='default'/>"/>
    	 <input type="hidden" name="stlrsjend" value="<fmt:formatDate value='${stlrsjend}' type='date' timeStyle='default'/>"/>
         <elile:navigateBar navigateform="navigateform" actionName="epaperquestion.action" formName="queryform1"/>
    </s:form> 
    	   </td>
    </tr>
    </table></div></td></tr></table>   
</body>
<script type="text/javascript">

</script>
</html>

