<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<html>
<head>
<s:head theme="ajax" /> 
<title>试题查询列表</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/date.js"></script>
</head>
<body class="nrbj">
<s:form action="queryOneQuestion" method="post">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">在线练习</td>
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
                              <td  class="header8">选择条件</td>
                            </tr>
                        </table></td>
                      </tr>
                    </table>
      <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">                       
                          <tr>
                            <td height="8" colspan="7"></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td width="18%" align="right">业务类型：</td>
                            <td width="23%"><s:select name="seequestionBuTypes" list="equestionBuTypes" listKey="id" listValue="name" headerKey="-1" headerValue="--全部--"/></td>
                            <td width="18%" align="right">试题类型：</td>
                            <td width="23%" ><s:select name="equestions_type" list="equestiontypes" listKey="id"  listValue="name" headerKey="-1" headerValue="--全部--"/></td>
                            <td>&nbsp;</td>
                          </tr>
                          
                          <tr>
                            <td>&nbsp;</td>
                            <td width="18%" align="right">难易程度：</td>
                            <td width="23%"><s:select   name="important" list="eimportances" listKey="id"  listValue="name" headerKey="-1" headerValue="--全部--"/></td>
                            <td width="18%" height="28" align="right">试题名称：</td>
                            <td width="23%" align="left"><s:textfield name="stTg"/></td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td height="8" colspan="7"></td>
                          </tr>
        </table>
			     </td>
				</tr>
             </table>
             <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
                <tr>
                  <td align="center"><input  class="submit_2" name="button" type="submit" class="submit_2"  value="开始练习" /></td>
                </tr>
              </table>
     </div>
   </td>
  </tr>
</table>
    </s:form>
</body>
</html>