
<%@ page contentType = "text/html;charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="elile.tld" prefix="elile"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>考点信息维护</title>
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
        <td  class="header2">考点管理</td>
        <td  class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">
              <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                            <tr>
                              <td  align="left" valign="middle" class="header7"></td>
                              <td  class="header8">考点分组</td>
                            </tr>
                        </table></td>
                      </tr>
                    </table>
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="2"></td>
          </tr>
        </table>        
        <table  width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;">
          <tr>
            <td align="center" valign="middle" class="num_font">
<table width="98%" height="33" border="0" align="center" cellpadding="0" 
	cellspacing="2" bgcolor="#D2E8FF" style="border-width:1px; border-style:solid; border-color:#8DD6F4; ">
    <tr> 
      <td bgcolor="#FFFFFF" width="20%"><div align="center">
		<iframe name="LeftTree" src="query_grouptree.jsp" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" width="200" height="1700px"></iframe></div>
	  </td><td bgcolor="#FFFFFF" width="80%">
	 	 <iframe name="MainWin" src="user_teamadd.jsp" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" width="100%" height="1700px"></iframe>
	  </td>
    </tr>
     
 </table>      
                      </td>  
                </tr>
              </table>
            
      
  	</div></td>
  	</tr>
  	</table>
</body>
</html>
