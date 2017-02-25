<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="java.util.Date,com.wondersgroup.falcon.Util.DateUtil" %>
<html>
<head>
<title>组合试卷成绩排名</title>
<link href="<%=request.getContextPath() %>/newcss/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/date.js"></script>
</head>
 <SCRIPT type="text/javascript">
 function goback(){
 	history.go(-1);
 }
 function expExcle(){
	var curTbl = document.getElementById("PrintA");
    var oXL = new ActiveXObject("Excel.Application");
    var oWB = oXL.Workbooks.Add();
    var oSheet = oWB.ActiveSheet;
    var sel = document.body.createTextRange();
    sel.moveToElementText(curTbl);
    sel.select();
    sel.execCommand("Copy");
    oSheet.Paste();
    oXL.Visible = true;
 }
 </SCRIPT>
<body class="nrbj">
<table id="PrintA" width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">成绩排名</td>
        <td  class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader"> 			  
			  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">排名列表</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
              <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr>
                <td  align="center" bgcolor="#C7E2F8">名次</td>
				<td  align="center" bgcolor="#C7E2F8">组号</td>
				<td  align="center" bgcolor="#C7E2F8">平均分</td>
				<td  align="center" bgcolor="#C7E2F8" colspan="4">季度明细</td>
             </tr>
         <s:iterator value="tableList" id="tableinfo" status="status">
           <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
             <td align='center'><s:property value="#status.index+1"/></td>
			 <td align='center'><s:property value="#tableinfo.name"/></td>
			 <td align='center'><s:property value="#tableinfo.avg"/></td>
			 <s:iterator value="%{#tableinfo.scoreList}" id="score"><td align='center'><s:property value="%{#score}"/></td></s:iterator>
          </tr>
          </s:iterator>  
    </table> 
     </td>
        </tr>
              </table>
       <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
                <tr>
				 <td width="20%" align="right">&nbsp;&nbsp;制表日期：<%=DateUtil.DateToStr(new Date(),"yyyy-MM-dd HH:mm:ss")%></td>
				</tr>
          </table>
              <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
                <tr>
                  <td align="center" ><input name="button" type="button" class="submit_2" onclick="goback();" value="返回" />
                  </td>
                  <td align="center"><input name="button" type="button" class="submit_2" onclick="expExcle();" value="导出excel" />
                  </td>                              
                </tr>
                <tr>                
                </tr>
              </table>
</div></td></tr></table>
</body>
</html>         
