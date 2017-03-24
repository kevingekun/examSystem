<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="elile.tld" prefix="elile"%>

<html>
<head>
<title>准考证打印-考试信息管理</title> 
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<script language="JavaScript" type="text/JavaScript" >
function add(){
	var zkzbt = $.trim($("#zkzbt").val());
	var pcmc = $.trim($("#pcmc").val());
	var address = $.trim($("#address").val());
	var kdmc = $.trim($("#kdmc").val());
	var kssj = $.trim($("#kssj").val());
	var jssj = $.trim($("#jssj").val());
	if(zkzbt==""||pcmc==""||address==""||kdmc==""||kssj==""||jssj==""){
		alert("前6项考试信息均不能为空！");
		return;
	}
	document.addform.submit();
}
//全选
function selectall()
{
	var check=document.getElementById("checkAll");
    var values = document.getElementsByName("checkid");  
      for (var i = 0; i < values.length; i++)
       	values[i].checked = check.checked;
}
</script>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">准考证打印-考试信息管理</td>
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
                              <td align="left" valign="middle" class="header7"></td>
                              <td class="header8">新增考试信息</td>
                            </tr>
                        </table></td>
                      </tr>
                    </table>
		<form name="addform" action="<%=request.getContextPath() %>/servlet/PrintCardServlet" method="post">
		<input type=hidden id="actionType" name="actionType" value="add">
		<input type=hidden id="myaction" name="myaction" value="">
		 <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
           <tr>
	            <td width="13%" height="28" align="right">准考证标题：</td>
	            <td width="18%" align="left"><input type="text" id="zkzbt" name="zkzbt" style="width:150px"></td>
                <td width="13%" align="right">批次名称：</td>
	            <td width="18%"><input type="text" id="pcmc" name="pcmc" style="width:150px"></td>
	            <td width="13%" height="28" align="right">考点地址：</td>
	            <td width="18%" align="left"><input type="text" id="address" name="address" style="width:150px"></td>
            </tr>
          <tr>
	            <td width="13%" align="right">考点名称：</td>
	            <td width="18%">
	            	<input type="text" id="kdmc" name="kdmc" style="width:150px">
                </td>
                <td width="13%" align="right">开始时间：</td>
	            <td width="18%">
	            	<input type="text" id="kssj" name="kssj" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:150px" class="Wdate"/>
                </td>
	            <td width="13%" align="right">结束时间：</td>
	            <td width="18%" >
            		<input type="text" id="jssj" name="jssj" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:150px" class="Wdate"/>
            	</td>
            </tr>
            <tr>
            	<td width="13%" align="right" height="28">专业：</td>
	            <td width="18%">
	            	<input type="text" id="major" name="major" style="width:150px">
                </td>
                <td width="13%" align="right">等级：</td>
	            <td width="18%">
	            	<input type="text" id="rank" name="rank" style="width:150px">
                </td>
            </tr>
  			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px; padding-right:16px;" >
               <tr>
                 <td align="center">
                 	<input name="button" type="button" class="submit_2" onClick="javascript:add();" value="增加" /> 
                 </td>
                 <td align="center">
                 	<input name="button" type="button" class="submit_2" onClick="javascript:doQuery();" value="查询" /> 
                 </td>
               </tr>
               <tr height="8"></tr>
             </table>
		   </table>
		 </form>
 
		<form action="<%=request.getContextPath() %>/servlet/PrintCardServlet?myaction=checklist"  name="aForm" method="POST">
		 <input type=hidden name="actionType" value="query">
		 <input type="hidden" name="subject"  value="<c:out value="${subject}"/>"/>
		 <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td  align="left" valign="middle" class="header7"></td>
                        <td  class="header8">
                        	<font style="float: left;">查询结果</font>
                        </td>
                      </tr>
                  </table></td>
                </tr>
            </table>
	  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
         <tr class="title_font"> 
         		<td width="5%" align="center" bgcolor="#C7E2F8"><input id="checkAll" name="all1" type="checkbox" onClick="selectall()" /></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">序号</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">批次名称</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">考试名称</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">考点名称</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">考点地址</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">开始时间</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">结束时间</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">专业</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">等级</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
          </tr>
        <c:forEach var="aBean" items="${list}" varStatus="status">
          <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
          		<td align='center' class='num_font'>
                	<input type="checkbox" id="checkbox" name="checkid" value="<c:out value="${aBean.id}"/>">
                </td>
                <td align='center' class='num_font'>
	            	<c:out value="${status.index+1}" />                     
	            </td>
	     	    <td class='center' align='num_font'><a href="#" style="display:block;width:150px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.pc_name}"/>" >
	           		<c:out value="${aBean.pc_name}"/></a>
	            </td>
	            <td align='center' class='num_font'>
	            	<a style="display:block;width:150;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.ks_name}"/>">
	            	<c:out value="${aBean.ks_name}"/></a>
	            </td>
	            <td align='center' class='num_font'>
	            	<a style="display:block;width:80;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.kd_name}"/>">
	            	<c:out value="${aBean.kd_name}"/></a>
	            </td>
	            <td align='center' class='num_font'>
	            	<a style="display:block;width:100;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.kd_address}"/>">
	            	<c:out value="${aBean.kd_address}"/></a>
	            </td>
	            <td align='center' class='num_font'>
	            	<a style="display:block;width:140;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.kssj}"/>">
	            	<c:out value="${aBean.kssj}"/></a>
	            </td>
	            <td align='center' class='num_font'>
	            	<a style="display:block;width:140;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.jssj}"/>">
	            	<c:out value="${aBean.jssj}"/></a>
	            </td>
	            <td align='center' class='num_font'>
	            	<a style="display:block;width:80;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.major}"/>">
	            	<c:out value="${aBean.major}"/></a>
	            </td>
	            <td align='center' class='num_font'>
	            	<a style="display:block;width:80;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="<c:out value="${aBean.rank}"/>">
	            	<c:out value="${aBean.rank}"/></a>
	            </td>
	            <td align='center' class='num_font'>
	            	<input type="button" class="submit_2" onClick="deletePI(<c:out value='${aBean.id}'/>)"  value="删除"/>
	            </td>
	            
           </tr>                   
         </c:forEach>
	  </table>
	  <!--   分页       -->
	  <c:if test="${list!= null}">
		<elile:navigateBar navigateform="navigateform" actionName="PrintCardServlet?myaction=checklist" formName="aForm"/>
	  </c:if>
  	    </td>
      </tr>
    </table>
   </form>
<script language="javascript" type="text/JavaScript">
function doQuery() {
	//document.addform.actionType.value="query";
	//document.addform.myaction.value="checklist";
	$("#actionType").val("query");
	$("#myaction").val("checklist");
  	document.addform.submit();
}
function deletePI(id){
	$.ajax({
		type:'post',
		async: false,
		url:'deletePrintCardInfo.action?id='+id,
		success:function(result){
			if(result=="success"){
				alert("删除成功");
				window.location.reload();
			}else if(result=="error"){
				alert("删除失败！");
			}
		},
		error:function(){
			alert("系统出错，请稍后再试！");			
		}
	});
}
</script>
</body>
</html>

