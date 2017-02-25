<%@page contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.List,java.util.Set,java.util.HashSet,java.util.ArrayList,com.wondersgroup.kaoshi.bo.EKaoshiGroup,com.wondersgroup.popedom.bo.EUser" %>
<%@page import="com.wondersgroup.falcon.question.dao.EBusinesstypeDAO,java.util.List,com.wondersgroup.falcon.model.citizeninfo.HisNode,com.wondersgroup.falcon.model.citizeninfo.HisAttr,com.wondersgroup.falcon.question.model.EQuestiontype" %>
<%@page import="com.wondersgroup.falcon.Util.*,com.wondersgroup.falcon.question.beans.*,com.wondersgroup.falcon.model.citizeninfo.PaperType"%>
<%@page import="com.wondersgroup.falcon.Util.*,com.wondersgroup.falcon.question.beans.*,com.wondersgroup.falcon.question.model.EImportance"%>
<% 
	EBusinesstypeDAO ht=new EBusinesstypeDAO();
	List<EKaoshiGroup> grouplist = (List<EKaoshiGroup>)ht.queryGroupList();
%>
<html>
<head>
<title>
人员选择
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
		var value = "";
		var usernames = "";
		for(var a=1;a<=i;a++){
			var memberboxes = document.getElementsByName("teamer"+a);
			for(var b=0;b<memberboxes.length;b++){
				if(memberboxes[b].checked==true){
					var username = document.getElementById(memberboxes[b].value);
					usernames+=username.innerHTML+",";
					value+= memberboxes[b].value+",";
				}
			}
		}
		var members = new Object();
		members.value=usernames.split(",")[0];
		members.id=value.split(",")[0];
		window.returnValue=members;
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
			                <td><strong> &nbsp;波测试题添加人员</strong>
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
				EKaoshiGroup group = (EKaoshiGroup)grouplist.get(i);
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
						List<EUser> userset = ht.findUserByGroup(group.getId()); 
						if(userset!=null){
							for(int j=0;j<(userset.toArray().length/4+1);j++){
								out.println("<tr><td>&nbsp;</td>");
								for(int k=0;k<(j==(userset.toArray().length/4)?(userset.toArray().length%4):4);k++){
									EUser user = (EUser)userset.toArray()[j*4+k];
									out.println("<td><input type=checkbox name='teamer"+(i+1)+"' value="+user.getId()+"><font id='"+user.getId()+"'>"+user.getRealname()+"</font></td>");
								}
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
