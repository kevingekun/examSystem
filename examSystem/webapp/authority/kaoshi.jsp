<%@ page contentType = "text/html;charset=gb2312" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>限制登录考生列表</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>

</head>
<script language="JavaScript" type="text/JavaScript" >
function del(){
	var row=0;
	var t=window.document.getElementsByName('deleteeKaoshi');
	for (var i=0; i<t.length; i++){	
    	if(t[i].type=='checkbox'&& t[i].checked==true ){row=row+1;}
    }	
	if(row<1){
		alert("请选择要取消的记录！");
		return false;
	}
    var tt=confirm("确定要取消该考生的限制登录吗？");  //确认是否删除
    if(tt){
		document.aForm.submit();
    }else{
    	return false;
    }
	
}
</script>
<body class="nrbj">
<s:form action="deleteeKaoshi"  name="aForm" method="POST">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">限制登录管理</td>
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
                        <td  class="header8">限制登录考生列表</td>
                      </tr>
                  </table></td>
                </tr>
              </table>   
         <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td width="2%" align="center" bgcolor="#C7E2F8"><span class="out">选择 </span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">准考证号</span></td>
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">姓名</span></td>
                 <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">身份证号</span></td>
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">考试时间</span></td>
                <td width="4%" align="center" bgcolor="#C7E2F8"><span class="out">限制登录原因</span></td>
              </tr>
             <s:iterator value="staff" id="user"  status="state">
              <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
               <td align='center' class='num_font'><input type='checkbox'  name='deleteeKaoshi' value='<s:property value="#user.userID"/>'>
			    </td>
                <td align='center' class='num_font'>
	            <s:property value="#user.zkzh"/>           
	     	    </td>
	             <td align='center' class='num_font'>
	            <s:property value="#user.xm"/>           
	     	    </td>
	     	      <td align='center' class='num_font'>
	            <s:property value="#user.IDnumber"/>           
	     	    </td>
	     	      <td align='center' class='num_font'>
	            <s:property value="#user.kssj"/>           
	     	    </td>
	     	      <td align='center' class='num_font'>
	            <s:property value="#user.sign"/>           
	     	    </td>
             </tr>
           </s:iterator>
			</table>
	</td>
	</tr>
</table>
	  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
		<tr><td align="center">
		<input type="button" class="submit_2" name="button_editfile2" value="取消" onclick="del()">
		</td></tr>
	</table>	
	</div>
	</td>
	</tr>
	</table>
</s:form>
</body>
</html>
