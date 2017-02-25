<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="com.wondersgroup.utils.GroupUtil" %>

<html>
<head>
<title>未参加考试人员列表</title>
<link href="<%=request.getContextPath() %>/newcss/all.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery/autoresize.min.js"></script>
<script language="JavaScript" type="text/JavaScript" ><!--
	function commit(id,ryid){
		var paperId=document.getElementById("paperid").value;
		var cause=id.previousSibling.previousSibling.value;
		var temp=new Date().getTime();
		var url="nopartinexamCause.action?paperId="+paperId+"&ryid="+ryid+"&cause="+cause+"&temp="+temp;
	$.ajax({ 
		type: "POST", 
		url: url, 
		success: function(data) { 
			var id="#"+ryid+"_td";
				$(id).text("已填写未参考原因");
			} 
		}); 
	}
	//<input type="checkbox" name="deleteid" value='<s:property value="#user.id"/>'>
	//<input type="button" style="display:none" value="提交" onclick='commit(this,"<s:property value='#user.id'/>");' />
	function psubmit(){
	var ids="";
	var causes="";
	var paperId=document.getElementById("paperid").value;
	var selects = document.getElementsByName("deleteid");  
	var flag=false;
    for (var i = 0; i < selects.length; i++){
    	if(selects[i].checked){
       		ids=ids+selects[i].value+",";
       		causes=causes+selects[i].previousSibling.previousSibling.value+",";
       		flag=true
       	}
      }
   if(!flag){
      	alert("您还没有选择");
    	return false;
     }
     var temp=new Date().getTime();
     var url="nopartinexamCause.action?paperId="+paperId+"&ryid="+ids+"&cause="+causes+"&temp="+temp;
	$.ajax({ 
		type: "POST", 
		url: url, 
		success: function(data) { 
			for (var i = 0; i < selects.length; i++){
				if(selects[i].checked){
					selects[i].style.display="none";
				}
			} 
		}}); 
	}
	//新的提交方式
	function newsubmit(){
		var ids="";
		var causes="";
		var flag=false;
		var paperId=document.getElementById("paperid").value;
		var selects = document.getElementsByName("selectcausename");  
		for(var i=0;i<selects.length;i++){
			if(selects[i].value!=''){
				ids=ids+selects[i].previousSibling.previousSibling.value+",";
	       		causes=causes+selects[i].value+",";
	       		flag=true
			}
		}
		 if(!flag){
	      	alert("您还没有选择");
	    	return false;
	     }
	    var temp=new Date().getTime();
	    var url="nopartinexamCause.action?paperId="+paperId+"&ryid="+ids+"&cause="+causes+"&temp="+temp;
		$.ajax({ 
			type: "POST", 
			url: url, 
			success: function(data) { 
				for (var i = 0; i < selects.length; i++){
					if(selects[i].value!=''){
						selects[i].disabled="disabled";
					}
				} 
			}}); 
	}
</script>
</head>
<body class="nrbj">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
	<input type="hidden" id="paperid" value='<s:property value="paperid"/>'/>
  <tr>
    <td colspan="2" valign="top" >
		<div id="content1" class="borader"> 			  
			  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
			<tr>
	            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
	                      <tr>
	                         <td align="center" ><input name="button" type="button" class="submit_2" onclick="goback();" value="返回" /></td>
						<td align="center"><input name="button" type="button" class="submit_2" onclick="newsubmit();" value="提交" /></td>      
	                      </tr>
	                  </table></td>
	                </tr>
	          <tr>
	            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
	                      <tr>
	                        <td  align="left" valign="middle" class="header7"></td>
	                        <td  class="header8">未参加考试人员列表</td>
	                      </tr>
	                  </table></td>
	                </tr>
              </table>
              <table  width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" id="tb" >
             <tr class="title_font">
				<td  align="center" bgcolor="#C7E2F8"><span class="out">序号 </span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">人员编号 </span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">人员名称</span></td>
                <td  align="center" bgcolor="#C7E2F8"><span class="out">人员性别</span></td>
				<td  align="center" bgcolor="#C7E2F8"><span class="out">人员星级</span></td>
				<td  align="center" bgcolor="#C7E2F8"><span class="out">进入中心时间</span></td>
				<td  align="center" bgcolor="#C7E2F8"><span class="out">未考试原因</span></td>
             </tr>
         <s:iterator value="users" id="user" status="status">
           <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
             <td align='center' class='num_font'><s:property value="#status.index+1"/></td>
             <td align='center' class='num_font'><s:property value="#user.id"/></td>
             <td align='center' class='num_font'><s:property value="#user.realname"/></td>
			 <td align='center' class='num_font'><c:if test="${user.sex=='0'}">女</c:if><c:if test="${user.sex=='1'}">男</c:if> </td>
			 <td align='center' class='num_font'><s:property value="#user.userstar"/></td>
			 <td align='center' class='num_font'><s:date name="#user.createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
			 <td align='center' class='num_font' id="<s:property value='#user.id'/>_td">
				<form>
					<input type="hidden"  value='<s:property value="#user.id"/>'>
					<select name="selectcausename" style="width:150px">
						<option value="">请选择</option>
						<s:iterator value="groupList" id="qingkong">
							<option value='<s:property value="#qingkong.id"/>'><s:property value="#qingkong.message"/></option>
						</s:iterator>	
				</select>
				</form>
			</td>
          </tr>
          </s:iterator>  
    </table> 
            </td>
        </tr>
              </table>
	</div>
</td></tr>
</table>
</body>
</html>         
