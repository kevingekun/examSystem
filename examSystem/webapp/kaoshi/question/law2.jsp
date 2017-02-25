<%@ page contentType = "text/html;charset=utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>法规库</title>
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
</head>

<script language="javascript">
	// parent.window.opener.location.reload();
   //window.opener.location.href=window.opener.location.href;
   //parent.window.opener.location.reload(); 
   //setTimeout("window.close()",300);
   
   function goto(url){
   		if(url=="zcfg"){
   			document.tree.location.href="<%=request.getContextPath()%>/kaoshi/question/law_tree1.jsp?parentid=110000";
   			document.frm.location.href="<%=request.getContextPath()%>/kaoshi/question/policy_list.jsp";
   		}
   		
   		if(url=="bszn"){
   			document.tree.location.href="<%=request.getContextPath()%>/service/ser_tree.jsp?parentid=120000";
   			document.frm.location.href="<%=request.getContextPath()%>/service/ser_list.jsp";
   		}
   		if(url=="wdzl"){
   			document.tree.location.href="<%=request.getContextPath()%>/faq/faq_tree.jsp?parentid=130000";
   			document.frm.location.href="<%=request.getContextPath()%>/faq/faq_list.jsp";
   		}
   		if(url=="xxzl"){
   			document.tree.location.href="<%=request.getContextPath()%>/case/case_tree.jsp?parentid=140000";
   			document.frm.location.href="<%=request.getContextPath()%>/case/case_list.jsp";
   		}

	}
</script>

<body  scroll="yes">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="44" height="33" background="<%=request.getContextPath()%>/images/nav_background.jpg"></td>
    <td width="95%" align="left" background="<%=request.getContextPath()%>/images/nav_background.jpg"  id="td_w">
    	<table height="30" border="0" align="left" cellpadding="0" cellspacing="0">
		  <tr>
		    <td align="left"> 
			    	<table width="100" border="0" align="left" cellpadding="0" cellspacing="0">
			        	<tr>
			          		<td width="*" align="center" class="nav"><a href="javascript:goto('zcfg')">政策法规</td>
			          		<td width="2"> | </c:if></td>
			        	</tr>
			      	</table>  
			      	<table width="100" border="0" align="left" cellpadding="0" cellspacing="0">
			        	<tr>
			          		<td width="*" align="center" class="nav"><a href="javascript:goto('bszn')">办事指南</td>
			          		<td width="2"> | </c:if></td>
			        	</tr>
			      	</table>  
			      	<table width="100" border="0" align="left" cellpadding="0" cellspacing="0">
			        	<tr>
			          		<td width="*" align="center" class="nav"><a href="javascript:goto('wdzl')">问答资料</td>
			          		<td width="2"> | </c:if></td>
			        	</tr>
			      	</table>  
			        <table width="100" border="0" align="left" cellpadding="0" cellspacing="0">
			        	<tr>
			          		<td width="*" align="center" class="nav"><a href="javascript:goto('xxzl')">学习资料</td>
			          		<td width="2"> | </c:if></td>
			        	</tr>
			      	</table>  
		    </td>
		  </tr>
		</table>
  </tr>
</table>

      <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >
      	
        <tr> 
          <td width="208" height="100%" valign="top" background="<%=request.getContextPath()%>/kaoshi/question/images/lf_03.gif" bgcolor="#FFFFFF">
            <table width="95%" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td >
                	<iframe name="tree" id="tree" src="<%=request.getContextPath()%>/kaoshi/question/law_tree1.jsp?parentid=110000" width="100%" height="600" frameborder="0">
            			</iframe>
            		</td>
              </tr>
            </table>
           
          </td>
          <td width="6" height="100%" bgcolor="#FFFFFF">&nbsp;</td>
          <td valign="top" height="100%" bgcolor="#FFFFFF"> 
		  			<iframe  id="frm" name="frm" src="<%=request.getContextPath()%>/kaoshi/question/policy_list.jsp" width="100%" height="600"  frameborder="0"></iframe>
			</td>
          <td width="22" background="<%=request.getContextPath()%>/kaoshi/question/images/lf_02.gif" bgcolor="#FFFFFF">&nbsp;</td>
        </tr>
        <!-- onload="this.height=frm.document.body.scrollHeight" -->
      </table>

</body>
</html>
