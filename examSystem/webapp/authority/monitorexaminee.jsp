<%@ page language="java" contentType = "text/html;charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
<title>考生列表</title>
<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript" src="js/sort/sort.js"></script>
<script language="javascript" type="text/javascript">
window.onload = function()
{
    //new TableSorter("tb");
    new TableSorter("tb", 0,1, 3 , 5, 6,7,8);
    /* new TableSorter("tb").OnSorted = function(c, t)
    {
        alert("table is sorted by " + c.innerHTML + " " + (t ? "Asc" : "Desc"));
    } */
}
    </script>
    <style type="text/css">
        .SortDescCss{background-image:url(js/sort/Desc.gif);background-repeat:no-repeat;background-position:85% center;}
        .SortAscCss{background-image:url(js/sort/Asc.gif);background-repeat:no-repeat;background-position:85% center;}
    </style>

</head>

<script language="javascript" type="text/javascript">
	function qianzhi(userid,examid){
	   window.open ("<%=request.getContextPath() %>/authority/qianzhi.jsp?userid="+userid+"&examid="+examid+"", "newwindow", "height=300, width=400, top=100, left=100, toolbar=no, menubar=no, scrollbars, resizable=no, location=no, status=no");
	   //var return_val = window.showModalDialog("authority/qianzhi.jsp?userid="+userid+"&examid="+examid+"","","dialogWidth=400px;dialogHeight=250px;scroll:yes;status:no");
	   //window.location.href="authority/qianzhi.jsp?userid="+userid+"&examid="+examid+"";
	   /* if(return_val.code=="true"){
	 		location.reload();
	   } */
	}
	
	function delayed(userid,examid){
		window.open ("<%=request.getContextPath() %>/authority/delayed.jsp?userid="+userid+"&examid="+examid+"", "newwindow", "height=300, width=400, top=100, left=100, toolbar=no, menubar=no, scrollbars, resizable=no, location=no, status=no");
	}
	
	function startLogin(){
		//felct();
		$.ajax({
			type: 'post',
			url: 'startLogin.action',
			success: function(result){
				if(result=="1"){
					alert("考生可以登录！");
					felct();
				}else if(result=="2"){
					alert("操作失败！");
				}else if(result=="3"){
					alert("考生已经可以登录!");
					felct();
				}else if(result=="4"){
					alert("找不到试卷，请联系管理员!");
				}
			},
			error: function(){
				alert("系统出错，请联系管理员！");
			}
		});
	}
	function startExam(){
		//felct();
		var state = <%=request.getSession().getAttribute("alreadyStart")==null?"0":request.getSession().getAttribute("alreadyStart")%>;
		var login = <%=request.getSession().getAttribute("alreadyLogin")==null?"0":request.getSession().getAttribute("alreadyLogin")%>;
		if(login=='1'){
			if(state=='1'){
				alert("已经开始考试！");
			}else{
				$.ajax({
					type: 'post',
					url: 'startExam.action',
					success: function(result){
						if(result=="1"){
							alert("考生可以考试！");
							felct();
						}else if(result=="2"){
							alert("操作失败！");
						}else if(result=="3"){
							alert("已经开始考试！");
						}
					},
					error: function(){
						alert("系统出错，请联系管理员！");
					}
				});
			}
		}else{
			alert("考生还没有登陆！");
		}
	}
	function cleanIp(userid,examid){
		$.ajax({
			type: 'post',
			url: 'cleanIp.action?userid='+userid+'&examid='+examid,
			success:function(result){
				if(result=='success'){
					alert("IP清理成功！")
				}else if(result=='failed'){
					alert("IP清理失败...");
				}else{
					alert("未知错误！请联系管理员")
				}
			},
			error:function(){
				alert("系统出错，请联系管理员！");
			}
		});
	}
	
	function cleanExam(userid,examid){
		if(window.confirm("此功能只有在考生答题数量为零并且已经交卷的特殊情况下使用,确定使用？")){
			$.ajax({
				type: 'post',
				url: 'cleanExam.action?userid='+userid+'&examid='+examid,
				success:function(result){
					if(result=='success'){
						alert("该考生考试信息清除成功！");
					}else if(result=='failed'){
						alert("清除失败...该考生未提交试卷或者成绩不为零分！");
					}else{
						alert("未知错误！请联系管理员");
					}
				},
				error:function(){
					alert("系统出错，请联系管理员！");
				}
			});
		}else{
			return false;
		}
		
	}
	
	function forbiddenExam(){
		$.ajax({
			type: 'post',
			url: 'forbiddenExam.action',
			success: function(result){
				if(result=="false"){
					alert("操作成功！");
				}else{
					alert("操作失败！");
				}
			},
			error: function(){
				alert("操作失败！");
			}
		});
	}
	
	function felct(){
		location.reload();
	}
	function stopExam(){
		window.open ("<%=request.getContextPath() %>/authority/examStop.jsp", "newwindow", "height=500, width=600, top=100, left=100, toolbar=no, menubar=no, scrollbars, resizable=no, location=no, status=no");
	}
	
	/* 
	setTimeout("felct()",10000); */
</script>
<body class="nrbj" >
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">考生列表</td>
        <td  class="header3" width="24"><img src="<%=request.getContextPath() %>/newimages/content_right_bj.gif " width="24" height="22"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">            
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:2px;">
	      <tr align="center" >
		  	<td>
	      <s:iterator value="pList" id="paper">
	      	<s:a cssStyle="font-size:18px" ><s:property value="#paper.sjMc" />&nbsp;&nbsp;</s:a>
		  </s:iterator>
		  	</td>
		  </tr>
          <tr>
            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle" class="header7"></td>
                        <td class="header8">考生状态
                        	<input id="startExam" type="button" value="开始登录" onclick="startLogin()" style="background-color: orange;"/>
                        	<input id="startExam" type="button" value="开始考试" onclick="startExam()" style="background-color: green"/>
                        	<input id="stopExam" type="button" value="缺考查看" onclick="stopExam()" style="background-color: red;float: right;margin-right:10px;"/>
                        	<!-- <input type="button" value="禁止考试" onclick="forbiddenExam()" style="background-color: orange"/> -->
                        </td>
                      </tr>
                  </table></td>
                </tr>
              </table>
           <table  width="100%" border="0" align="center" cellpadding="0" >
           		<tr>
           			<td>
           				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" style="width: 100%;border-right: 0px solid rgb(199, 226, 248);" >
	           				<tr class="title_font">
	                			<td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号</span></td>
	                		</tr>
	                		<s:iterator value="monitorlist" id="monitor" status="state">
				              <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
				              	<td align='center' class='num_font'><s:property value="#state.index+1"/> </td>
				              	</tr>
				            </s:iterator>
                		</table>
           			</td>
           			<td>
           				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list" style="width: 100%;" id="tb" >
	           				<tr class="title_font">
	                			<td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">准考证号码</span></td>
				                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">姓名</span></td>
				                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">ip地址</span></td>
				                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">开始时间</span></td>
				                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">结束时间</span></td>
				                <td width="7%" align="center" bgcolor="#C7E2F8"><span class="out">当前状态</span></td>
				                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">答题数量</span></td>
				                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">成绩</span></td>
				                <td width="22%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
	                		</tr>
	                		<s:iterator value="monitorlist" id="monitor" status="state">
				              <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
				              	<td align='center' class='num_font'><s:property value="#monitor[2]"/> </td>
				                <td align='center' class='num_font'><s:property value="#monitor[1]"/> </td>
				                <td align='center' class='num_font'><s:property value="#monitor[4]"/> </td>
				                
				                <td align='center' class='num_font'><s:property value="#monitor[5]"/> </td>
				                <td align='center' class='num_font'> <s:property value="#monitor[6]"/></td>
					            <td align='center' class='num_font'>
				                	<s:if test="#monitor[7]==1"> 未开始答题 </s:if>
									<s:elseif test="#monitor[7]==2">正在答题 </s:elseif>
									<s:elseif test="#monitor[7]==3">网络异常退出 </s:elseif>
									<s:elseif test="#monitor[7]==4">已交卷 </s:elseif>
								</td>
				                <td align='center' class='num_font'> <s:property value="#monitor[14]"/></td>
				                <td align='center' class='num_font'> <s:property value="#monitor[13]"/></td>
					            <td align="center">
					            	<a href="javascript:void(0)" <s:if test="#monitor[7]==2||#monitor[7]==3">onclick="qianzhi(<s:property value="#monitor[12]"/>,<s:property value="#monitor[11]"/>)"</s:if> >考生操作</a>&nbsp;
					            	<a href="javascript:void(0)" <s:if test="#monitor[7]==2||#monitor[7]==3">onclick="delayed(<s:property value="#monitor[12]"/>,<s:property value="#monitor[11]"/>)"</s:if> >延时</a>&nbsp;
					            	<a href="javascript:void(0)" <s:if test="#monitor[7]==2||#monitor[7]==3||#monitor[7]==1">onclick="cleanIp(<s:property value="#monitor[12]"/>,<s:property value="#monitor[11]"/>)"</s:if> >清理IP</a>&nbsp;
					            	<a href="javascript:void(0)" <s:if test="#monitor[7]==4">onclick="cleanExam(<s:property value="#monitor[12]"/>,<s:property value="#monitor[11]"/>)"</s:if> >重新考试</a>
					            </td>
				              </tr>
				            </s:iterator>
                		</table>
           			</td>
           		</tr>
           
             <%-- <tr class="title_font">
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">序号</span></td>
                <td width="10%" align="center" bgcolor="#C7E2F8"><span class="out">准考证号码</span></td>
                 <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">姓名</span></td>
                 <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">ip地址</span></td>
                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">开始时间</span></td>
                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">结束时间</span></td>
                <td width="7%" align="center" bgcolor="#C7E2F8"><span class="out">当前状态</span></td>
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">答题数量</span></td>
                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">成绩</span></td>
                <td width="22%" align="center" bgcolor="#C7E2F8"><span class="out">操作</span></td>
             </tr> 
             <s:iterator value="monitorlist" id="monitor" status="state">
              <tr onMouseOver="this.className='td_over'" onMouseOut="this.className=''" id='r1'>
              	<td align='center' class='num_font'><s:property value="#state.index+1"/> </td>
                <td align='center' class='num_font'><s:property value="#monitor[2]"/> </td>
                <td align='center' class='num_font'><s:property value="#monitor[1]"/> </td>
                <td align='center' class='num_font'><s:property value="#monitor[4]"/> </td>
                
                <td align='center' class='num_font'><s:property value="#monitor[5]"/> </td>
                <td align='center' class='num_font'> <s:property value="#monitor[6]"/></td>
	            <td align='center' class='num_font'>
                	<s:if test="#monitor[7]==1"> 未开始答题 </s:if>
					<s:elseif test="#monitor[7]==2">正在答题 </s:elseif>
					<s:elseif test="#monitor[7]==3">网络异常退出 </s:elseif>
					<s:elseif test="#monitor[7]==4">已交卷 </s:elseif>
				</td>
                <td align='center' class='num_font'> <s:property value="#monitor[14]"/></td>
                <td align='center' class='num_font'> <s:property value="#monitor[13]"/></td>
	            <td align="center">
	            	<a href="javascript:void(0)" <s:if test="#monitor[7]==2||#monitor[7]==3">onclick="qianzhi(<s:property value="#monitor[12]"/>,<s:property value="#monitor[11]"/>)"</s:if> >考生操作</a>&nbsp;
	            	<a href="javascript:void(0)" <s:if test="#monitor[7]==2||#monitor[7]==3">onclick="delayed(<s:property value="#monitor[12]"/>,<s:property value="#monitor[11]"/>)"</s:if> >延时</a>&nbsp;
	            	<a href="javascript:void(0)" <s:if test="#monitor[7]==2||#monitor[7]==3||#monitor[7]==1">onclick="cleanIp(<s:property value="#monitor[12]"/>,<s:property value="#monitor[11]"/>)"</s:if> >清理IP</a>&nbsp;
	            	<a href="javascript:void(0)" <s:if test="#monitor[7]==4">onclick="cleanExam(<s:property value="#monitor[12]"/>,<s:property value="#monitor[11]"/>)"</s:if> >重新考试</a>
	            </td>
             </tr>		   															
           </s:iterator>    --%>          		
			</table>
					</td>
				</tr>
			</table>
  	</div></td>
  	</tr>
  	</table>
</body>
</html>
