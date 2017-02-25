<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="elile.tld" prefix="elile"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="newcss/style.css"/>
<link rel="stylesheet" type="text/css" href="inc/all.css"/>
<script language="JavaScript" type="text/JavaScript" src="js/dateMy97/WdatePicker.js"></script>
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
    </table>
    </td>
    <td width="53%"  align="left"></td>
    </tr>
  <tr>
    <td colspan="2" align="left"> <div id="content1" class="borader">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td class="borader3">
          	<table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                    <tr>
                      <td  align="left" valign="middle" class="header7"></td>
                      <td  class="header8">查询条件</td>
                    </tr>
                </table></td>
              </tr>
            </table>
        <s:form name="gglist" method="post" action="ggview.action">
              <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="8" colspan="7"><input type="hidden" name="currpage" value="1"/></td>
                </tr>
                <tr>
                  <td width="20%" height="28" align="right">标题：</td>
                  <td width="10%" align="left"><s:textfield name="biaoti" /></td>
                  <td width="22%" align="right">发布时间：</td>
                  <td width="46%">
                  	<input type="text" value='<s:date name="sjbegin" format="yyyy-MM-dd"/>' class="Wdate" id="djsj" name="sjbegin" onClick="WdatePicker()"/>至
                  	<input type="text" value='<s:date name="sjend" format="yyyy-MM-dd"/>' class="Wdate" id="djsjend" name="sjend" onClick="WdatePicker()"/>
                  </td>
                </tr>
               
              </table>
            <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
                <tr>
                  <td align="center">
                  	<input name="button" type="submit" class="submit_2" value="查 询" />
                  </td>
                </tr>
                <tr>
                  <td height="8" colspan="7"></td>
                </tr>
            </table>
            </s:form>
            </td>
            <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3">
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle" class="header7"></td>
                        <td class="header8">公告列表</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
       <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">栏目 </span></td>
                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">标题</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">发布人员</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">发布时间</span></td>
                <td width="17%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
             </tr>
          <s:iterator value="tggs" id="gg" status="status">
             <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><s:property value="#gg.ggglm.lxmc"/>
                </td>
                <td align='center' class='num_font'>
	            <s:url id="viewgg" action="ggdetial.action">
		          <s:param name="ggId" value="#gg.ggid"></s:param>
		        </s:url>
		        <s:a href="%{#viewgg}">
		        <s:property value="#gg.ggbt"/>
		        </s:a>
	     	    </td>
	     	    <td align='center' class='num_font'><s:property value="#gg.ryid"/></td>
	            <td align='center' class='num_font'><s:date name="#gg.ggrq" format="yyyy-MM-dd"/></td>
	            <td align='center' class='num_font'>
	            	<s:url id="delgg" action="ggdel.action">
		            	<s:param name="ggId" value="#gg.ggid"></s:param>
		            </s:url>
		            <s:a href="%{#delgg}">删除</s:a>
		        </td>
	           </tr>
	           </s:iterator>
    </table>
     <s:form name="ggActionform" method="post" action="ggview">
        <s:hidden name="biaoti" />
        <s:hidden name="sjbegin" />
        <s:hidden name="sjend" />                
        <elile:navigateBar navigateform="navigateform" actionName="ggview.action" formName="ggActionform"/>
 	 </s:form>
 		 
</td></tr></table></tr>
</table></div>
</td></tr></table>        
</body>
</html>