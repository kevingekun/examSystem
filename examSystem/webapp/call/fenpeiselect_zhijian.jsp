<%@page contentType="text/html;charset=GBK" %>
<%@ page import="java.util.List,java.util.Set,java.util.HashSet,java.util.ArrayList" %>
<%@ page import="com.wondersgroup.falcon.model.auth.Group" %>
<%@ page import="com.wondersgroup.falcon.model.auth.User" %>
<%@ page import="com.wondersgroup.falcon.model.archives.Users" %>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@ page import="com.wondersgroup.falcon.model.auth.UserType" %>
<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
<jsp:useBean id = "authbean" class="com.wondersgroup.falcon.beans.auth.AuthFacade"/>
<%@ page import="org.apache.commons.beanutils.*" %>
<%@ page import="com.wondersgroup.falcon.dao.chouyang.*"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat" %>


<html>
<head>
<title>
选则质检人员
</title>
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript">
function checkall(ischeck){
		var memberboxes = document.getElementsByName("zhijianid");
		
		for(var a=0;a<memberboxes.length;a++){
			memberboxes[a].checked=ischeck;
		}
	}

	//提交
	function checksubmit_2(){
      if(typeof(document.selectform.zhijianid.length)=="undefined"){
	    if(document.selectform.zhijianid.checked==true)
	        document.selectform.zhijianids.value=document.selectform.zhijianid.value+";";
	   }else{
	    for(var i=0;i<document.selectform.zhijianid.length;i++){
	      if(document.selectform.zhijianid[i].checked==true)
	        document.selectform.zhijianids.value=document.selectform.zhijianids.value+document.selectform.zhijianid[i].value+";";
	      
	    }
	    }
	    var zhijianids=document.selectform.zhijianids.value;
  //document.selectform.submit();
 // window.opener.lookup5.submit(); 
 //window.opener.location.href='fenpeizj.jsp?zhijianids='+zhijianids;
 var members = new Object();
		members.value=zhijianids;
		members.id=zhijianids;
		window.returnValue=members;
		
  //window.returnValue=zhijianids;
  window.close();
 }
</SCRIPT>
</head>
<body  leftmargin="5" topmargin="0" marginwidth="0" marginheight="0">
	<table width="99%" border="0" cellspacing="0" cellpadding="0" align=center>
		<tr> 
		  <td height="3" colspan="3"></td>
		</tr>
		<tr> 
			<td width="8"><img src="../images/min_01.gif" width="8" height="32"></td>
			<td background="../images/min_02.gif">
				<div align="center">
					<table width="99%" border="0" cellspacing="0" cellpadding="0">
		            	<tr> 
			            	<td width="15"><strong><img src="../images/ico/search.gif" width="16" height="16"></strong></td>
			                <td><strong> &nbsp;选则分配质检人员</strong>
		              			<div align="left"></div></td>
		                </tr>
		             </table>
	             </div>
	        </td>
	    	<td width="8"><img src="../images/min_03.gif" width="8" height="32"></td>
		</tr>
	    <tr> 
			<td height="5" colspan="3"></td>
	    </tr>
	</table>   
	<table><tr height=15><td></td></tr></table>
	<form name="selectform" method="post" >
	<input type=hidden name="zhijianids" value="">
	<input type=hidden name="C" value="1">
	<table width="99%"  border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#84B7C6">
		<tr bgcolor=#F1F8FE> 
			<td width="763" align=center >
			<input type=checkbox name="team0" id="team0" onclick="checkall(this.checked)">全选&nbsp;&nbsp;&nbsp;&nbsp;
				
				
				<input type=checkbox name="zhijianid" id="zj4" value="032">吴亚雯
				<input type=checkbox name="zhijianid" id="zj5" value="031">车笠
				<input type=checkbox name="zhijianid" id="zj6" value="036">蔡爱娜
				<input type=checkbox name="zhijianid" id="zj2" value="051">楼海彬
			</td>
		</tr>
	<tr height=15><td></td></tr>
		<tr>
			<td align=center>
				<input type=button class="SmallButton" value="提交" onclick="checksubmit_2()">
			</td>
		</tr>
	</table>  
	</form> 
</body>
</html>
