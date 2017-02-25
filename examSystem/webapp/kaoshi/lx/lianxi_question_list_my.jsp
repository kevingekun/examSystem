<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<html>
<head>
<title>我练习过的题目</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/date.js"></script>
<script language="JavaScript" type="text/JavaScript" >
function modify(lianxi_id){
	window.location.href="viewlianxiquestion.action?eexercises_id="+lianxi_id;
}
</script>
<!--  
<STYLE type="text/css">
	.td_over { background-color:#00ffff;}
</STYLE>
-->
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">练习统计</td>
        <td  class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">
              <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
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

 <s:form name="lianxi" method="post" action="lianxiquestionQuery.action">
     <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
               <tr>      
                  <td width="22%" align="right">练习时间：</td>
                  <td width="46%"><input type="text" value='<s:date name="sjbegin" format="yyyy-MM-dd"/>' class="Wdate" id="djsj" name="sjbegin" onClick="WdatePicker()"/>
                    至
                  <input type="text" value='<s:date name="sjend" format="yyyy-MM-dd"/>' class="Wdate" id="djsjend" name="sjend" onClick="WdatePicker()"/></td>
                </tr>
   </table>
   <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
                <tr>
                  <td align="center"><input   name="button" type="submit" class="submit_2"  value="查 询" />
                    </td>
                </tr>
              </table>
    </s:form>             

 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">试题查询列表</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
       <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td  align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">答题数量</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">客观题数量</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">客观题正确率</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">开始时间</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">结束时间</span></td>
             </tr>                     
          <s:iterator value="eexercises" id="myeexercises" status="status">
          	
           <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1' onclick='modify(<s:property value="#myeexercises.lxId"/>);'>        	
             <td align='center' class='num_font'><s:property value="#status.index+1"/></td>
                <td align='center' class='num_font'><s:property value="#myeexercises.lxDtsl"/></td>
	     	    <td align='center' class='num_font'><s:property value="#myeexercises.lxKgtsl"/></td>
	            <td align='center' class='num_font'><s:property value="#myeexercises.lxKgtzql"/></td>
	            <td align='center' class='num_font'><s:date name="#myeexercises.lxKssj" format="yyyy-MM-dd HH:mm"/></td>
	            <td align='center' class='num_font'><s:date name="#myeexercises.lxJssj" format="yyyy-MM-dd HH:mm"/></td>
             </tr>  
          </s:iterator>
		 </table>	 
		  </td>
    </tr>
    <s:form name="lxActionform" method="post" action="lianxiquestionQuery">
        		<s:hidden name='sjbegin'/>
        		<s:hidden name='sjend' />
        <elile:navigateBar navigateform="navigateform" actionName="lianxiquestionQuery.action" formName="lxActionform"/>
 </s:form>
         </table></td></tr></table></div></td></tr></table> 
</body>
</html>

