<%@ page contentType = "text/html;charset=gb2312" %>

<jsp:useBean id = "list" class="com.wondersgroup.falcon.beans.archives.PolicyTree"/>
<%@ page import="java.util.List,java.util.Iterator" %>
<%@ page import="com.wondersgroup.falcon.model.archives.PolicyNode" %>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function jiancha(){
		if(document.searchForm.searchWord.value==null||document.searchForm.searchWord.value==""){
			return false
		}
		return true
	}
	
	function select(){
		var selectList=document.getElementById("searchSelect");
		var holeSearch=document.getElementById("holeSearch");
		var keyWordSearch=document.getElementById("keyWordSearch");
		
		var selectValue=selectList.options[selectList.selectedIndex].value;
		if(selectValue==1){
			holeSearch.style.display="block";
			keyWordSearch.style.display="none";
		}
		if(selectValue==2){
			holeSearch.style.display="none";
			keyWordSearch.style.display="block";
		}
	}

	function refresh(){
		if(window.confirm("这可能需要几分钟时间，您确定要刷新索引吗?")){
			return true;
		}else{
			return false;
		}
	}
//-->
</SCRIPT>
	
<%
//String keyword = request.getParameter("keyword");
//if (keyword == null) keyword = "";
String title = request.getParameter("title");
if (title == null) title = "";
String institude = request.getParameter("institude");
if (institude == null) institude = "";

String	year =request.getParameter("year") ;


String	number = request.getParameter("number");

//if (year == null) year = "";
 
//if(number==null) number="";

String bgcolor=(String)session.getAttribute("bgcolor");
System.out.println("bgcolor====>>>"+bgcolor);
if(bgcolor==null)bgcolor="#ffffff";
%>
	
<link href="../inc/all.css" rel="stylesheet" type="text/css">
<body bgcolor="<%=bgcolor%>">
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
                        
            <td><strong> &nbsp;文件目录查询</strong> 
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
            
            <table width="100%" height="33" border="0" align="center" cellpadding="0" cellspacing="2" bgcolor="#D2E8FF" style="border-width:1px; border-style:solid; border-color:#8DD6F4; ">
	          <tr> 
	             <td bgcolor="#FFFFFF">
	               <table width="98%" border="0" align="center" cellpadding="1" cellspacing="2">
	         	 		<tr>
				            <td width="90">搜索方式选择：</td>	         	 		 
				            <td>
								<select id="searchSelect" onchange="javascript:select()">
									<option value=1 selected>全文检索</option>
									<option value=2 >其它搜索</option>
								</select>
							</td>	            				
	          			</tr>
	       				</table>
	       			</td>
	           </tr>
	        </table>
            
            <div id="holeSearch">
            <FORM id=searchForm name="searchForm" action=law_query.jsp onsubmit="return jiancha()">
	            <table width="100%" height="33" border="0" align="center" cellpadding="0" cellspacing="2" bgcolor="#D2E8FF" style="border-width:1px; border-style:solid; border-color:#8DD6F4; ">
	              <tr> 
	                <td bgcolor="#FFFFFF">
	                	<table width="98%" border="0" align="center" cellpadding="1" cellspacing="2">
	         	 			<tr> 
		            			<td width="49" rowspan="2"><div align="center"><img src="../images/ico/sc.gif" width="54" ></div></td>
					            <td width="66" nowrap>全文检索：</td>
					            <td width="128"><input name="searchWord" type="text" size="20"></td>
					            <td>
									 <INPUT class = "SmallButton" id=doSearch type=submit value=搜索>
		           				</td>		           				
	          				</tr>
	       				</table>
	       			</td>
	              </tr>
	            </table>
		  </FORM>
          </div>
          
          <div id="keyWordSearch" style="display:none">
            <form method = "post" action = "law_lookup.jsp" name="lookup">
            <table width="100%" height="33" border="0" align="center" cellpadding="0" cellspacing="2" bgcolor="#D2E8FF" style="border-width:1px; border-style:solid; border-color:#8DD6F4; ">
              <tr> 
                <td bgcolor="#FFFFFF">
                	<table width="98%" border="0" align="center" cellpadding="1" cellspacing="2">
         	 			<tr> 
	            			<td width="49" rowspan="2"><div align="center"><img src="../images/ico/sc.gif" width="54" ></div></td>
				            <input name="keyword" type="hidden" size="20">
				            <td width="68" nowrap align="center">标题：</td>
				            <td width="90" nowrap><input name="title" type="text" size="20">&nbsp;&nbsp;</td>
				            <td  nowrap align="center">文号：</td>
				            <td  nowrap>
				            	<input name="institude" type="text" size="20"> 单位
				            	<input name="year" type="text" size="4"> 年
				            	<input name="number" type="text" size="3"> 号				            					            	
				            </td>			            
				            <td>
								<input type = "submit" class = "SmallButton" name = "button_editfile" value = "查 询&nbsp;&nbsp;" >
	           				</td>
          				</tr>
       				</table>
       			</td>
              </tr>
            </table>
          	</form>
          </div>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="5" colspan="4"></td>
              </tr>
              <tr> 
                <td width="3"><img src="../images/k1_01.gif" width="3" height="30"></td>
                <td width="21" background="../images/k1_03.gif"><div align="center"><img src="../images/k1_02.gif" width="21" height="30"></div></td>
                <td width="98%" background="../images/k1_03.gif">文件目录列表</td>
                <td width="4" valign="top"><img src="../images/k1_04.gif" width="4" height="30"></td>
              </tr>
              <tr> 
                <td background="../images/k1_05.gif"></td>
                <td colspan="2" valign="top"> 
                  <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#E0F0FD">
                    <tr bgcolor="#FFFFFF"> 
                      <td width="763" height="23" colspan=2><div align="center">标 题 </div></td>
                    </tr>
                    <tr bgcolor="#FFFFFF"> 
                      <td height="2" colspan="1" bgcolor="#F1F8FE" colspan=2></td>
                    </tr>
                    
					<%
					
					if (request.getParameter("title") != null){
						System.out.println("111111111111111");
						//if (title.equals("")) title = null;
						//if (fileno.equals("")) fileno = null;
						//if (keyword.equals("")) keyword = null;
						//if (businesstype.equals("")) businesstype = null;
						List l = list.findByExample(title,institude,year,number,null);    
						System.out.print("aaaaaaaaaa"+l.size());
						if(l!=null&&l.size()!=0){
							for (Iterator it = l.iterator(); it.hasNext();) {
								PolicyNode tmp = (PolicyNode) it.next();
									if(tmp.getVisible()!=2&&tmp.getForecansee()!=2){
									//out.println(tmp.getId());
									out.println("<tr bgcolor=\"#FFFFFF\" > ");
									session.removeAttribute("isdir");
									session.removeAttribute("filename");
									session.removeAttribute("fileid");
									out.println("<td width=160 style='font-size:10.5pt'><font style='font-family:Times New Roman'>"+tmp.getAttribute().getFileno()+"</font></td>");
									out.println("<td align=left style='font-size:10.5pt'><font style='font-family:Times New Roman'><a href=law_file.jsp?para=lookup&filename="+tmp.getName()+"&fileid=" + tmp.getAttribute().getHtmlFile() + "&id=" + tmp.getId() + " target='_self'>" + tmp.getName() + "</a></font></td>");
									out.println("</tr>");
								}
							}						
						}
					}
					
					%>
                    
                    
                    <tr bgcolor="#FFFFFF"> 

                      <td colspan=2>&nbsp;</td>
                    </tr>
                    <tr bgcolor="#FFFFFF"> 

                      <td colspan=2>&nbsp;</td>
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
</body>