<%@ page contentType = "text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.List,com.wondersgroup.popedom.bo.ExamStaff"  %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="elile.tld" prefix="elile"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户列表</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
</head>
<%--<%
List<ExamStaff> list=(List<ExamStaff>)request.getAttribute("infolist");
%>
--%><script>
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
        <td  class="header2">导入考生信息</td>
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
                              <td  class="header8">查询条件</td>
                            </tr>
                        </table></td>
                      </tr>
                    </table>
      <s:form method = "post"   action="addstaff.action" name="addstaffForm" >
              <table width="60%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;">
                <tr>
                 <td width="13%" height="28" align="center">鉴定批次号：</td>
                  <td width="18%" align="center"><s:textfield name="jdpcId" id="examId" size="25"/></td>
                  <td width="3%"></td>
                  <td align="left"><input  name = "button_editfile" type="submit" class="submit_2"  value="导入" />
                    </td>
                </tr>
              </table>  
                   
    </s:form>      
      
    
                      </td>  
                </tr>
              </table>
            
         <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left"  class="header7"></td>
                        <td  class="header8">查询结果</td>
                      </tr>
                  </table></td>
                </tr>
              </table>     
           <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">准考证号 </span></td>
                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">考生姓名</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">身份证号</span></td>
                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">鉴定批次号</span></td>
                  <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">考场</span></td>
                   <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">考场地址</span></td>
             </tr>  
               <s:iterator value="staff" id="user"  status="state">
              <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
                <td align='center' class='num_font'><s:property value="#user.zkh"/>
                </td>
                <td align='center' class='num_font'>
	            <s:property value="#user.examineename"/>           
	     	    </td>
	             <td align='center' class='num_font'>
	            <s:property value="#user.IDnumber"/>           
	     	    </td>
	     	      <td align='center' class='num_font'>
	            <s:property value="#user.examid"/>           
	     	    </td>
	     	      <td align='center' class='num_font'>
	            <s:property value="#user.examroom"/>           
	     	    </td>
	     	      <td align='center' class='num_font'>
	            <s:property value="#user.examroomadress"/>           
	     	    </td>
             </tr>
           </s:iterator>
             
			</table>
			<c:if test="${staff!=null }">
				<s:form action="addstaff.action" name="chaForm">
					<elile:navigateBar navigateform="navigateform" actionName="addstaff.action" formName="chaForm"/>
				</s:form>
			</c:if>
					</td>
				</tr>
				
			</table>
			  
  	</div></td>
  	</tr>
  	</table>
</body>
</html>
