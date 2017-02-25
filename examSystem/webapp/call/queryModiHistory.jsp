<%@page language="java" contentType="text/html;charset=gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>无标题文档</title>
		
		<link href="../css/style.css" rel="stylesheet" type="text/css" />
	</head>
	<style type="text/css">

	html{ height:100%;}
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background-color: #ebf3f6;
		height:100%;	
	}

</style>
<script language="javascript">

function autoHeight() 
{
	var obj = document.getElementById("content1");
	var h = document.body.clientHeight;
	obj.style.height=(h-34)+"px";
	
	
}

</script>	

	
<script language="JavaScript" type="text/JavaScript" src="../js/dateMy97/WdatePicker.js"></script>
		<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
		<jsp:useBean id = "authbean" class="com.wondersgroup.falcon.beans.auth.AuthBeans"/>
	<%@ page import="java.util.*,java.text.SimpleDateFormat" %>
	<%@ page import="com.wondersgroup.falcon.model.select.*" %>
	<%@ page import="com.wondersgroup.falcon.persistence.HibernateUtil,java.util.Date"%>
	<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl"%>
	<%@ page import="com.wondersgroup.falcon.acegi.AcegiUtil" %>
	<%@ page import="com.wondersgroup.falcon.dao.chouyang.*"%>
	<%@ page import="com.wondersgroup.falcon.model.citizeninfo.HisAttr" %>
	<%@ page import="com.wondersgroup.falcon.dao.citizeninfo.*"%>
	<%@ page import="org.apache.commons.beanutils.*" %>

    <%@ page import="com.wondersgroup.falcon.beans.archives.HisTree"  %>
    <%@ page import="com.wondersgroup.falcon.model.citizeninfo.*"%>

		<%
		ChouyangDAO chouyangService = new ChouyangDAO();
			
			List sqlist=null;
			List shtglist=null;
			List listquery;
			int count = 0; 
			String recordlength = request.getParameter("recordlength");
			String zhijianid = request.getParameter("zhijianid");
			String btime = request.getParameter("startrecordtime");
			String etime = request.getParameter("stoprecordtime");
			String callerid = request.getParameter("callerid");
			String id = request.getParameter("id");
			 
            String A = request.getParameter("A");
            if (A==null||A.equals(""))
				A = "";
			if (zhijianid==null||zhijianid.equals(""))
				zhijianid = "";
			if (id==null||id.equals(""))
				id = "";	
			if (recordlength==null||recordlength.equals("0")||recordlength.equals(""))
				recordlength = "";
			
			if (btime==null||btime.equals(""))
				btime = "";
			
			if (etime==null||etime.equals(""))
				etime = "";
			
			if (callerid==null||callerid.equals(""))
				callerid = "";
			
		    
			
			if(A.equals("1")){
			
			sqlist = chouyangService.tjmodifyapplyQuery(btime, etime);
			
			}
		
	%>
	<script type="text/javascript">
		 function getZhijianName(id){
	    //var id=document.getElementById("id").value;
	   
	   //id='006c01bd72c1cf01';
	
	     //alert(document.getElementById("id").value);
	    //var zjid = showModalDialog('select_zhijian.jsp?id='+id,window,'dialogWidth:400px;dialogHeight:200px;');
	    //window.open("");
	    window.open("select_zhijian.jsp?id="+id,"","height=400,width=200,top=0,left=0,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=yes");
	    //document.getElementById("zhijianid").value=zjid.value;
	      //alert(zjid);
	     // alert(zjid.value);
		//document.lookup4.zhijianid.value=zjid.value;
}
 function queryRecoedlength(){
	   
	    window.open("queryRecoedlength.jsp?id","","height=400,width=200,top=0,left=0,toolbar=yes,menubar=yes,scrollbars=yes, resizable=yes,location=yes");
	 
}
	 function getMembers(){
	var members = showModalDialog('message_receiver.jsp',window,'dialogWidth:600px;dialogHeight:500px;');
	var value1 = "";
	if(members!=null){
		document.getElementById("agentid").value=members.value;
		value1=members.id;
		value1=value1.substring(0,value1.length-1);
		
		document.lookup4.username.value=value1;
		
	}
	
}
	function go(){
			document.zhonglei.pa.value=document.getElementById("page").value;
			document.zhonglei.submit();
		}
	
   		function fPopUpCalendarDlg(textname,startYear,endYear,q)
		{
			var pattern = /^(19|20)([0-9]){2}$/;
			flag=pattern.test(startYear);
			if(!flag)startYear=1900;
			flag=pattern.test(endYear);
			if(!flag)endYear=2050;
		
			today=new Date();
			
			var currentDate = today.getYear() + "-" + today.getMonth() + "-" + today.getDay();
		
			var arguments = new Array(startYear,endYear,0,0,0);
				
			var pattern = /^(19|20)([0-9]){2}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
			flag=pattern.test(currentDate);
			if(flag)
			{
				iYear=currentDate.substring(0,4);
				iMonth=currentDate.substring(5,7);
				iDay=currentDate.substring(8,10);
				arguments = new Array(startYear,endYear,iYear,iMonth,iDay);
			}
		
		
			showx = event.screenX - event.offsetX + 18; 
			showy = event.screenY - event.offsetY - 210; 
				
			var features =
				'dialogWidth:'  + 190 + 'px;' +
				'dialogHeight:' + 230 + 'px;' +
				'dialogLeft:'   + showx     + 'px;' +
				'dialogTop:'    + showy     + 'px;' +
				'directories:no; location:no; status:no; menubar:no; toolbar=no;scrollbars=no;Resizeable=no; help:0';
			
			retval = window.showModalDialog("8.htm", arguments , features );
				
			if( retval != null ){
				textname.value = retval;
			}
		}
			
	function checksubmit_2(){
    document.lookup4.submit();
	}
		
		

	</script>

	<body>
	<table width="98%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px;">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td  align="left" valign="middle" class="header1"></td>
          <td  class="header2">质检修改打分申请情况</td>
          <td  class="header3"></td>
        </tr> 
      </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="10"></td>
        </tr>
      </table>
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td class="borader3">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                    <tr>
                      <td  align="left" valign="middle" class="header7"></td>
                      <td  class="header8">查询条件</td>
                    </tr>
                </table></td>
              </tr>
            </table>

			<form name="lookup4" method="post" >
	<input type=hidden name="username" value=""></input>
	<input type=hidden name="A" value="1"></input>
	<input type=hidden name="zhijianid" value=""></input>
    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
 	
   	
    		
            
            	 <td width="10%">&nbsp;</td>
          <td width="12%" height="26" align="right">时间：</td>
          <td width="20%"> <input type="text" style="width:40%" class="Wdate Wdate_30" id="startrecordtime" name="startrecordtime" onclick="WdatePicker()"></input> 至<input style="width:40%" type="text" class="Wdate Wdate_30" id="stoprecordtime" name="stoprecordtime" onclick="WdatePicker()"></input></input>
			
          <td width="10%">&nbsp;</td>
          <td width="12%" align="right"></td>
          <td width="20%">
			
			</td>
          <td width="15%">&nbsp;</td>
        </tr>
      
        
		  
		
          
          
          
        <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="30" align="center" valign="center"><input name="submit111" type="button" class="submit_2"  value="查 询" onclick="checksubmit_2()"></input> 
                  </tr>	
	   	</table>                                           
<input type="hidden" name="mutex" value="1"></input>
	
</table>

	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
        <tr>
          <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
                    <tr>
                      <td  align="left" valign="middle" class="header7"></td>
                      <td  class="header8">查询列表</td>
                    </tr>
                </table></td>
              </tr>
 </table>
	
		 <page:pagedb tid="2" pagesize="10" show="false"  output="result2">
		
		<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_list" id="tb" >
			<tr class="title_font">
					<td align="center" width=2%>
					序号
				</td>	
				<td align="center" width=2%>
					工号
				</td>		
			   <td align="center" width=3%>
					姓名
				</td>
				
			
				<td align="center" width=6%>
					申请修改次数
				</td>
				<td align="center" width=6%>
					业务部同意修改次数
				</td>
			</tr>
<% if (!(sqlist==null||sqlist.equals(""))){%>
           <% 
				for(int i=0;i<sqlist.size();i++){
			    SelectVO rec = (SelectVO)sqlist.get(i);
			     SelectVO rec1=chouyangService.shenhetongguoQuery(rec.getZhijianid(),btime, etime);
			    
			%>
		<tr onmouseover=this.className='td_over' onmouseout=this.className='' id='r1'>
				<td align="center" width=2%>
					<%=(i+1) %>
					
				</td>			
				<td align="center" width=2%>
				<%=rec.getZhijianid() %>
				</td>
				<td align="center" width=3%>
				  <%if(rec.getZhijianid().equals("029")){ %>
					章悦
					<%  }else if(rec.getZhijianid().equals("030")){%>
					 陈靓
					<% }else if(rec.getZhijianid().equals("107")){%>
					吴亚雯
					<% }else if(rec.getZhijianid().equals("108")){%>
					蔡爱娜
					<% }else if(rec.getZhijianid().equals("109")){%>
					楼海彬
					<% }else if(rec.getZhijianid().equals("110")){%>
					车笠
					<% }else {%>
					作废
					<%} %>
				</td>
				<td align="center" width=4%>
				<%=rec.getZhijiansqxgcs()%>
				</td>
				<td align="center" width=4%>
				<%=rec1.getShenhetongguocs()%>
				</td>
	</tr>
	
			<%		
				}}
			%>

		</table>
		  </page:pagedb>
	
	
			</form>
		
		

	</body>
</html>
