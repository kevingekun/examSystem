<%@ page contentType = "text/html;charset=gb2312" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="elile.tld" prefix="elile"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>�û��б�</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
</head>
<script>
			function adduser() {
        		var oBol = /[^\d\.]/g.test(adduser_form.id.value);
        		//window.alert(oBol ? '����' : '��ȷ');
        		if(oBol) 
					alert("ID �ֶ�ֻ����������");
      			else 
					adduser_form.submit();
			}
			
			function setdisVisible(id){
				setdisvisible_form.userid.value = id;
				setdisvisible_form.submit();
			}
			
			function edituser(id){
				edituser_form.userid.value = id;
				edituser_form.submit();
			}

			
			function individualdistribute(id){
				individualdistribute_form.userid.value = id;
				individualdistribute_form.submit();
			}
			
</script>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">��Ա����</td>
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
                      <tr><% int jinkao=0; int kaosheng=0; %>
                      <s:iterator value="arrangeuserlist" id="shuzi" status="list">
			              <s:if test="#shuzi[3]==2">
			             <%jinkao++; %>
			             </s:if>
			              <s:if test="#shuzi[3]==1">
			             <%kaosheng++; %>
			             </s:if>
             </s:iterator>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">������Ա &nbsp;�࿼�ˣ�<<%=jinkao %>>��&nbsp;������<<%=kaosheng %>>��</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
           <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                 <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">����</span></td>
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">��ʦ���˺� </span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">��ʦ������</span></td>
             </tr> 
             	
             <s:iterator value="arrangeuserlist" id="user" status="list">
            
             <s:if test="#user[3]==2">
              <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><s:property value="#user[0]"/>
                </td>
                <td align='center' class='num_font'>
	            <s:property value="#user[1]"/>           
	     	    </td>
	            <td align='center' class='num_font'>
                <s:property value="#user[2]"/> 
	            
             </tr>
				</s:if>					
           </s:iterator>
             <tr class="title_font">
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">��������</span></td>
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">���֤���� </span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">׼��֤����</span></td>
             </tr>   
             <s:iterator value="arrangeuserlist" id="users" status="list">
            
             <s:if test="#users[3]==1">
              <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><s:property value="#users[0]"/>
                </td>
                <td align='center' class='num_font'>
	            <s:property value="#users[1]"/>           
	     	    </td>
	            <td align='center' class='num_font'>
                <s:property value="#users[2]"/> 
	            
             </tr>
				</s:if>					
           </s:iterator>
			</table>
					</td>
				</tr>
			</table>
  	</div></td>
  	</tr>
  	</table>
</body>
</html>
