<%@page contentType="text/html;charset=GBK" %>
<%@ page import="java.util.List,java.util.Set,java.util.HashSet,java.util.ArrayList" %>
<%@ page import="com.wondersgroup.falcon.model.auth.Group" %>
<%@ page import="com.wondersgroup.falcon.model.auth.User" %>
<%@ page import="com.wondersgroup.falcon.model.archives.Users" %>
<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl" %>
<%@ page import="com.wondersgroup.falcon.model.auth.UserType" %>
<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
<jsp:useBean id = "authbean" class="com.wondersgroup.falcon.dao.auth.AuthorityDAO"/>
<% 
	List grouplist = authbean.findAllGroups();

%>
<html>
<head>
<title>
咨询人员选择
</title>
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
<SCRIPT LANGUAGE="JavaScript">
	function checkTeam(i,ischeck){
		var memberboxes = document.getElementsByName("teamer"+i);
		
		for(var a=0;a<memberboxes.length;a++){
			memberboxes[a].checked=ischeck;
		}
	}
	function checkall(i,ischeck){
			for(var a =1;a<=i;a++){
				document.getElementById("team"+a).checked=ischeck;
				var memberboxes = document.getElementsByName("teamer"+a);
				
				for(var b=0;b<memberboxes.length;b++){
					memberboxes[b].checked=ischeck;
				}
			}
	}

	function checkSubmit(i){
	//alert("aa");
		var value = "";
		var usernames = "";
		for(var a=1;a<=i;a++){
			var memberboxes = document.getElementsByName("teamer"+a);
			for(var b=0;b<memberboxes.length;b++){
				if(memberboxes[b].checked==true){
					var username = document.getElementById(memberboxes[b].value);
					usernames+=username.innerHTML+", ";
					value+= memberboxes[b].value+",";
				}
			}
		}
		
		var members = new Object();
		members.value=usernames;
		members.id=value;
		window.returnValue=members;
		
	    value=value.substring(0,value.length-1);
	 
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
			                <td><strong> &nbsp;信息交流公告添加人员</strong>
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
	<table width="99%"  border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#84B7C6">
		<tr bgcolor=#F1F8FE> 
			<td width="763" align=center >
				<input type=checkbox name="team0" id="team0" onclick="checkall(<%=grouplist.size() %>,this.checked)">全选
			</td>
		</tr>
		<% 
			for(int i=0;i<grouplist.size();i++){
				Group group = (Group)grouplist.get(i);
		%>
		<tr bgcolor=#F1F8FE> 
			<td width="763" >
				<input type=checkbox name="team<%=(i+1)%>" id="team<%=(i+1)%>" onclick="checkTeam(<%=(i+1)%>,this.checked)"><%=group.getName() %>
			</td>
		</tr>
		<tr bgcolor="#ffffff">
			<td>
				<table border=0 width=100% cellspacing=0 cellpadding=0 >
					<% 
						List userset = authbean.findUserByGroup(group.getId()); 
						//System.out.println("userset.size()===>>>"+userset.size());
						if(userset!=null){
							for(int j=0;j<(userset.toArray().length/4+1);j++){
								//System.out.println("j and (userset.toArray().length/4+1) and  (j==(userset.toArray().length/4+1)?(userset.toArray().length%4):4)=======>>>"+j+" and "+(userset.toArray().length/4)+" and "+(j==(userset.toArray().length/4+1)?((userset.toArray().length)%4):4));
								out.println("<tr><td>&nbsp;</td>");
								for(int k=0;k<(j==(userset.toArray().length/4)?(userset.toArray().length%4):4);k++){
									//System.out.println("j*4 and k=======>>>"+(j*4)+" and "+k);
									Users user = (Users)userset.toArray()[j*4+k];
									out.println("<td><input type=checkbox name='teamer"+(i+1)+"' value="+user.getUsername()+"><font id='"+user.getUsername()+"'>"+user.getRealname()+"</font></td>");
								}
								
								out.println("</tr>");
							}
						}
					%>
				</table>
			</td>
		</tr>		
		<tr bgcolor="#ffffff" height=5><td colspan=4></td></tr>
		<% 
			}
		%>
		<tr>
			<td align=center>
				<input type=button onclick="checkSubmit(<%=grouplist.size() %>)" value="提交">
			</td>
		</tr>
	</table>   
</body>
</html>
