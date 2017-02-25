<%@ page contentType="text/html;charset=gbk"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="elile.tld" prefix="elile"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>无标题文档</title>
<link href="/falnewexam/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="/falnewexam/inc/all.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
</head>	
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">修改建议详情</td>
        <td  class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
      </tr>
    </table>
    </td>
    <td width="53%"  align="left"></td>
    </tr>
  <tr>
    <td colspan="2" align="left"> <div id="content1" class="borader">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">修改建议</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
       <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">建议人</span></td>
                <td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">建议内容</span></td>
                <td width="20%" align="center" bgcolor="#C7E2F8"><span class="out">建议时间</span></td>
             </tr>
          <s:iterator value="eadvice" id="ads" status="status">
             <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><s:property value="#status.index+1"/>
                </td>
	     	    <td align='center' class='num_font'><s:property value="#ads.rymc"/></td>
	     	     <td align='center' class='num_font'><s:property value="#ads.content"/></td>
	            <td align='center' class='num_font'><s:date name="#ads.dt" format="yyyy-MM-dd"/></td>
	           </tr>
	           </s:iterator>
    </table>
     <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
                <tr>
                  <td align="center"><input   name="button" type="button" class="submit_2"   onclick="javascript:history.go(-1);" value="返 回" />
                  </td>
                </tr>
       </table>
     <s:form name="findAdviceForm" method="post" action="findAdvice">               
        		<elile:navigateBar navigateform="navigateform" actionName="findAdvice.action" formName="findAdviceForm"/>
 		 </s:form>
 		 
</td></tr></table></td></tr>
</table></div>
</td></tr></table>   
<script language="JavaScript" type="text/JavaScript" >

function del(){
	var row=0;
	var t=window.document.getElementsByName('deleteid');
	for (var i=0; i<t.length; i++){	
    	if(t[i].type=='checkbox'&& t[i].checked==true ){row=row+1;}
    }	
	if(row<1){
		alert("请选择要删除的记录！");
		return false;
	}
    var tt=confirm("确定要删除吗？");  //确认是否删除
    if(tt){
    	document.aForm.action="QuestionServlet";
		document.aForm.myaction.value="del";
		document.aForm.submit();
    }else{
    	return false;
    }
	
}

//更改试卷状态


</script>


     
</body>
</html>