<%@ page contentType = "text/html;charset=gb2312" %>

<jsp:useBean id = "list" class="com.wondersgroup.falcon.beans.archives.PolicyTree"/>
	
<%
		String selectitem = request.getParameter("selectitem");	//��ǰѡ��
		if (selectitem == null) selectitem = "";
%>

<link href="../inc/all.css" rel="stylesheet" type="text/css">
 <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="3" colspan="3"></td>
              </tr>
              <tr> 
                <td width="8"><img src="../images/min_01.gif" width="8" height="32"></td>
                <td background="../images/min_02.gif"><div align="center">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
              				<tr> 
                        
            <td width="15"><strong><img src="../images/ico/search.gif" width="16" height="16"></strong></td>
                        
            <td><strong> &nbsp;���߷����ѯ</strong> 
              <div align="left"></div></td>
                      </tr>
                    </table>
                  </div></td>
                <td width="8"><img src="../images/min_03.gif" width="8" height="32"></td>
              </tr>
              <tr> 
                <td height="5" colspan="3"></td>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="5" colspan="4"></td>
              </tr>
              <tr> 
                <td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
                <td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
                <td width="98%" background="../images/k1_03.gif">�����б�</td>
                <td width="4" valign="top"><img src="../images/k1_04.gif" width="4" height="30"></td>
              </tr>
              <tr> 
                <td background="../images/k1_05.gif"></td>
                <td  colspan="2" valign="top"> 
                  <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#E0F0FD">
                    <tr bgcolor="#FFFFFF"> 
                      <td width="30%" height="23"><div align="center">�ļ����</div></td>
                      <td width="70%"><div align="center">�� �� </div></td>
                    </tr>
                    <tr bgcolor="#FFFFFF"> 
                      <td height="2" colspan="2" bgcolor="#F1F8FE"></td>
                    </tr>
                    <%
                    	list.getFiles(new Long(selectitem),out);
                    %>
                    <tr bgcolor="#FFFFFF"> 
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr bgcolor="#FFFFFF"> 
                      <td>&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                  </table>
                  
                </td>
                <td background="../images/k1_06.gif"><img src="../images/k1_06.gif" width="4" height="2"></td>
              </tr>
              <tr> 
                <td><img src="../images/k1_07.gif" width="2" height="5"></td>
                <td colspan="2" background="../images/k1_08.gif"> </td>
                <td><img src="../images/k1_09.gif"></td>
              </tr>
            </table>
            
        
          <td width="22" background="../images/lf_02.gif" bgcolor="#FFFFFF">&nbsp;</td>
        
        <tr> 
          <td background="../images/lf_03.gif"></td>
          <td background="../images/menu_04.gif" bgcolor="#FFFFFF"></td>
          <td background="../images/menu_04.gif" bgcolor="#FFFFFF"></td>
          <td background="../images/lf_02.gif" bgcolor="#FFFFFF">
          </td>
        </tr>
<form method = "post" action = "law_list.jsp" name="list">
		<input type=hidden name=selectitem value="">
</form>