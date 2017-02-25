<%@ page contentType = "text/html;charset=gb2312" %>

<jsp:useBean id = "list" class="com.wondersgroup.falcon.beans.archives.ServiceTree"/>
<%@ page import="java.util.*" %>
<%@ page import="com.wondersgroup.falcon.model.archives.ServiceNode" %>

	
<%

String keyword = request.getParameter("keyword");
if (keyword == null) keyword = "";
String title = request.getParameter("title");
if (title == null) title = "";
String businesstype = request.getParameter("businesstype");
if (businesstype == null) businesstype = "";
String fileno = request.getParameter("fileno");
if (fileno == null) fileno = "";



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
                        
            <td><strong> &nbsp;办事指南查询</strong> 
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
            <form method = "post" action = "ser_lookup.jsp" name="lookup">
            <table width="100%" height="33" border="0" align="center" cellpadding="0" cellspacing="2" bgcolor="#D2E8FF" style="border-width:1px; border-style:solid; border-color:#8DD6F4; ">
              <tr> 
                <td bgcolor="#FFFFFF"><table width="98%" border="0" align="center" cellpadding="1" cellspacing="2">
          <tr> 
            <td width="49" rowspan="2"><div align="center"><img src="../images/ico/sc.gif" width="54" ></div></td>
            <td width="66" nowrap>关键字：</td>
            <td width="128"><input name="keyword" type="text" size="20"></td>
            <td width="68" nowrap align="center">标题：</td>
            <td width="90" nowrap><input name="title" type="text" size="20"></td>
            <td>
<input type = "button" class = "SmallButton" name = "button_editfile" value = "查 询&nbsp;&nbsp;" onclick = "submit()">
            </td>
          </tr>
        
        </table></td>
              </tr>
            </table>
          	</form>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="5" colspan="4"></td>
              </tr>
              <tr> 
                <td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
                <td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
                <td width="98%" background="../images/k1_03.gif">法规列表</td>
                <td width="4" valign="top"><img src="../images/k1_04.gif" width="4" height="30"></td>
              </tr>
              <tr> 
                <td background="../images/k1_05.gif"></td>
                <td colspan="2" valign="top"> 
                  <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#E0F0FD">
                    <tr bgcolor="#FFFFFF"> 
                      <td width="763" height="23"><div align="center">标 题 </div></td>
                    </tr>
                    <tr bgcolor="#FFFFFF"> 
                      <td height="2" colspan="1" bgcolor="#F1F8FE"></td>
                    </tr>
                    
										<%
										if (request.getParameter("keyword") != null){
											if (title.equals("")) title = null;
											if (fileno.equals("")) fileno = null;
											if (keyword.equals("")) keyword = null;
											if (businesstype.equals("")) businesstype = null;
											List l = list.findByExample(title,fileno,keyword,businesstype);
											//out.print(l.size());
												for (Iterator it = l.iterator(); it.hasNext();) {
													ServiceNode tmp = (ServiceNode) it.next();
													//out.println(tmp.getId());
													out.println("<tr bgcolor=\"#FFFFFF\"> ");

														//out.println("<td>&nbsp;</td>");
													out.println("<td align=left><a href=ser_file.jsp?fileid=" + tmp.getAttribute().getHtmlFile() + "&id=" + tmp.getId() + " target='_self'>" + tmp.getName() + "</a></td>");
													out.println("</tr>");
												}
										}
										%>
                    
                    
                    <tr bgcolor="#FFFFFF"> 

                      <td>&nbsp;</td>
                    </tr>
                    <tr bgcolor="#FFFFFF"> 

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
            
          </td>
          <td width="22" background="../images/lf_02.gif" bgcolor="#FFFFFF">&nbsp;</td>
        </tr>
        <tr> 
          <td background="../images/lf_03.gif"></td>
          <td background="../images/menu_04.gif" bgcolor="#FFFFFF"></td>
          <td background="../images/menu_04.gif" bgcolor="#FFFFFF"></td>
          <td background="../images/lf_02.gif" bgcolor="#FFFFFF">
          </td>
        </tr>
      </table></td>
  </tr>
</table>
<form method = "post" action = "ser_list.jsp" name="list">
		<input type=hidden name=selectitem value="">
</form>