<%@ page contentType = "text/html;charset=utf-8" %>
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
			
		function dosubmit(){
			document.aForm.submit();
		}	
			
			
</script>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">考场信息维护</td>
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
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">考场信息维护</td>
                      </tr>
                  </table></td>
                </tr>
              </table>
              <s:form action="updateTeam.action"  namespace="popedom" name="aForm" method="POST">
              	<table align="center">
              		<tr>
              			<td>考场名称:</td>
              			<td><input type="text" name="teamName" value="<s:property value="userteam.teamName"/>">
              				<input type="hidden" name="teamId" value="<s:property value="userteam.teamId"/>">
              			</td>
              		</tr>
              		<tr>
              			<td>考场人数:</td>
              			<td><input type="text" name="count" value="<s:property value="userteam.count"/>"></td>
              		</tr>
              		<tr>
              			<td>准考证号码:</td>
              			<td><input type="text" name="numstart" style="width:80px"  value="<s:property value="userteam.numstart"/>">
              			到<input type="text" name="numend" style="width:80px" value="<s:property value="userteam.numend"/>">
              			</td>
              		</tr>
              		<%--<tr>
              			<td><input type="submit" style="width:80px" value="修改"></td>
              			<td><input type="button" style="width:80px" value="新增"></td>
              		</tr>
              	--%></table>
              	</s:form>
              	<div style="margin-top: 20px">
			<div style="margin-left: 180px; float:left">
				<input type="button" class="submit_2" value="修改" name="button_submit" onclick="dosubmit()" />
			</div>
			 
		</div>
					</td>
				</tr>
			</table>
  	</div></td>
  	</tr>
  	</table>
</body>
</html>
