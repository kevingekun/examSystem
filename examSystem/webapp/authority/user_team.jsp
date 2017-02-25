<%@ page contentType = "text/html;charset=gb2312" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="elile.tld" prefix="elile"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户列表</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
</head>
<script>
			function adduser() {
        		var oBol = /[^\d\.]/g.test(adduser_form.id.value);
        		//window.alert(oBol ? '错误' : '正确');
        		if(oBol) 
					alert("ID 字段只能输入数字");
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
        <td  class="header2">人员管理</td>
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
                      <s:iterator value="userlist" id="user" status="list">
             <s:iterator value="#user.authorities" id="it" status="set">
              <s:if test="#it.id==143">
             <%jinkao++; %>
             </s:if>
              <s:if test="#it.id!=143">
             <%kaosheng++; %>
             </s:if>
             </s:iterator>
             </s:iterator>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">考场人员 &nbsp;监考人：<<%=jinkao %>>人&nbsp;考生：<<%=kaosheng %>>人</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
           <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">监考用户名 </span></td>
                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">监考姓名</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">帐户状态</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
             </tr> 
             	
             <s:iterator value="userlist" id="user" status="list">
             <s:iterator value="#user.authorities" id="it" status="set">
            
             <s:if test="#it.id==143">
              <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><s:property value="#user.username"/>
                </td>
                <td align='center' class='num_font'>
	            <s:property value="#user.realname"/>           
	     	    </td>
	            <td align='center' class='num_font'>
                <s:if test="#user.enabled==1">
										正常
									</s:if>
									<s:elseif test="#user.enabled==2">
										锁定
									</s:elseif>
									<s:elseif test="#user.enabled==3">
										不可用
									</s:elseif></td>
	            <td align="center">
									<a href="viewAuthUser.action?userid=<s:property value='#user.id'/>">角色分配</a>
	            </td>
             </tr>
				</s:if>					
		   		</s:iterator>				
           </s:iterator>
             <tr class="title_font">
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">准考证号码 </span></td>
                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">考生姓名</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">帐户状态</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
             </tr>   
             <s:iterator value="userlist" id="user" status="list">
              <s:iterator value="#user.authorities" id="it" status="set">
              
             <s:if test="#it.id!=143">
              <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><s:property value="#user.username"/>
                </td>
                <td align='center' class='num_font'>
	            <s:property value="#user.realname"/>           
	     	    </td>
	            <td align='center' class='num_font'>
                <s:if test="#user.enabled==1">
										正常
									</s:if>
									<s:elseif test="#user.enabled==2">
										锁定
									</s:elseif>
									<s:elseif test="#user.enabled==3">
										不可用
									</s:elseif></td>
	            <td align="center">
									<a href="viewAuthUser.action?userid=<s:property value='#user.id'/>">角色分配</a>
	            </td>
             </tr>
				</s:if>					
		   		</s:iterator>			
           </s:iterator>
                    		<s:form action="userview.action" name="userViewForm" method="post">
						  		<s:hidden name="username"></s:hidden>
						  		<s:hidden name="realname"></s:hidden>
						  		<s:hidden name="enabled"></s:hidden>
								<elile:navigateBar navigateform="navigateform" actionName="userview.action" formName="userViewForm"/>
							</s:form>

			</table>
					</td>
				</tr>
			</table>
  	</div></td>
  	</tr>
  	</table>
</body>
</html>
