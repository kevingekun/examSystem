<%@ page contentType = "text/html;charset=utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>录音库</title>
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
</head>

<script language="javascript">
	// parent.window.opener.location.reload();
   //window.opener.location.href=window.opener.location.href;
   //parent.window.opener.location.reload(); 
   //setTimeout("window.close()",300);
   
   function goto(url){
   		if(url=="good"){
   			document.frm.location.href="<%=request.getContextPath()%>/question/gooddianxingQuery.jsp";
   		}
   		
   		if(url=="jqqj"){
   			document.frm.location.href="<%=request.getContextPath()%>/question/jiqiaodianxingQuery.jsp";
   		}
   		if(url=="ywqj"){
   			document.frm.location.href="<%=request.getContextPath()%>/question/yewuerrordianxingQuery.jsp";
   		}
   		if(url=="tdqj"){
   			document.frm.location.href="<%=request.getContextPath()%>/question/baddianxingQuery.jsp";
   		}

	}
</script>

<body  scroll="yes">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="44" height="33" background="<%=request.getContextPath()%>/newimages/nav_background.jpg"></td>
    <td width="95%" align="left" background="<%=request.getContextPath()%>/newimages/nav_background.jpg"  id="td_w">
    	<table height="30" border="0" align="left" cellpadding="0" cellspacing="0">
		  <tr>
		    <td align="left"> 
			    	<table width="100" border="0" align="left" cellpadding="0" cellspacing="0">
			        	<tr>
			          		<td width="*" align="center" class="nav"><a href="javascript:goto('good')">优秀录音</td>
			          		<td width="2"> | </c:if></td>
			        	</tr>
			      	</table>  
			      	<table width="100" border="0" align="left" cellpadding="0" cellspacing="0">
			        	<tr>
			          		<td width="*" align="center" class="nav"><a href="javascript:goto('jqqj')">技巧欠佳</td>
			          		<td width="2"> | </c:if></td>
			        	</tr>
			      	</table>  
			      	<table width="100" border="0" align="left" cellpadding="0" cellspacing="0">
			        	<tr>
			          		<td width="*" align="center" class="nav"><a href="javascript:goto('ywqj')">业务欠佳</td>
			          		<td width="2"> | </c:if></td>
			        	</tr>
			      	</table>  
			        <table width="100" border="0" align="left" cellpadding="0" cellspacing="0">
			        	<tr>
			          		<td width="*" align="center" class="nav"><a href="javascript:goto('tdqj')">态度欠佳</td>
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
          <td width="6" height="100%" bgcolor="#FFFFFF">&nbsp;</td>
          <td valign="top" height="100%" bgcolor="#FFFFFF"> 
		  			<iframe  id="frm" name="frm" src="<%=request.getContextPath()%>/question/gooddianxingQuery.jsp" width="100%" height="600"  frameborder="0"></iframe>
			</td>
          <td width="22" background="<%=request.getContextPath()%>/kaoshi/question/images/lf_02.gif" bgcolor="#FFFFFF">&nbsp;</td>
        </tr>
        <!-- onload="this.height=frm.document.body.scrollHeight" -->
      </table>

</body>
</html>
