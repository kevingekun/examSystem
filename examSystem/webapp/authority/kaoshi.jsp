<%@ page contentType = "text/html;charset=gb2312" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>���Ƶ�¼�����б�</title>
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
		alert("��ѡ��Ҫȡ���ļ�¼��");
		return false;
	}
    var tt=confirm("ȷ��Ҫȡ���ÿ��������Ƶ�¼��");  //ȷ���Ƿ�ɾ��
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
        <td  class="header2">���Ƶ�¼����</td>
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
                        <td  class="header8">���Ƶ�¼�����б�</td>
                      </tr>
                  </table></td>
                </tr>
              </table>   
         <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td width="2%" align="center" bgcolor="#C7E2F8"><span class="out">ѡ�� </span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">׼��֤��</span></td>
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">����</span></td>
                 <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">���֤��</span></td>
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">����ʱ��</span></td>
                <td width="4%" align="center" bgcolor="#C7E2F8"><span class="out">���Ƶ�¼ԭ��</span></td>
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
		<input type="button" class="submit_2" name="button_editfile2" value="ȡ��" onclick="del()">
		</td></tr>
	</table>	
	</div>
	</td>
	</tr>
	</table>
</s:form>
</body>
</html>
