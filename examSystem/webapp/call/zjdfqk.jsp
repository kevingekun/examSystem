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

	<%@taglib uri="elile.tld" prefix="elile"%>
	<%@ page import="com.wondersgroup.falcon.Util.*"%>
<script language="JavaScript" type="text/JavaScript" src="../js/dateMy97/WdatePicker.js"></script>
<jsp:useBean id="Wonders"
		class="com.wondersgroup.falcon.beans.archives.HisTree" />
	<jsp:useBean id="Comm"
		class="com.wondersgroup.falcon.beans.common.CommFunc" />
		<jsp:useBean id = "util" class="com.wondersgroup.falcon.acegi.AcegiUtil"/>
		<jsp:useBean id = "authbean" class="com.wondersgroup.falcon.beans.auth.AuthBeans"/>
	<%@ page import="java.util.*,java.text.SimpleDateFormat" %>
	<%@ page import="com.wondersgroup.falcon.model.archives.Users" %>
	<%@ page import="com.wondersgroup.falcon.model.auth.*" %>
	<%@ page import="com.wondersgroup.falcon.model.citizeninfo.*"%>
	<%@ page import="com.wondersgroup.falcon.dao.citizeninfo.*"%>
	<%@ page import="com.wondersgroup.falcon.dao.auth.*"%>
	<%@ page import="com.wondersgroup.falcon.persistence.HibernateUtil,java.util.Date"%>
	<%@ page import="com.wondersgroup.falcon.acegi.UserDetailsImpl"%>
	<%@ page import="com.wondersgroup.falcon.acegi.AcegiUtil" %>
	<%@ page import="com.wondersgroup.falcon.beans.archives.HisTree"  %>
	<%@ page import="com.wondersgroup.falcon.model.auth.User" %>
	<%@ page import="com.wondersgroup.falcon.model.select.*" %>
	<%@ page import="com.wondersgroup.falcon.model.call.Zhijianpingfen" %>
	<%@ page import="com.wondersgroup.falcon.dao.chouyang.*"%>
	<%@ page import="com.wondersgroup.falcon.model.call.*" %>
<%@ page import="java.util.List,java.util.Date,java.text.SimpleDateFormat" %>
		<%
		User user = new User();
		UserDAO udao = new UserDAO();
		int currpage = RequestUtils.getInt(request, "currpage", 1);//默认1 currpage
	int pagesize = RequestUtils.getInt(request, "pagesize", 20);//默认行行数 pagesize
	int pagenum = RequestUtils.getInt(request, "pagenum", 5);
	
      User uuser = ((UserDetailsImpl)util.getUserDetails()).getUser();
 	String name=uuser.getUsername();
	// User uuser = ((UserDetailsImpl)util.getUserDetails()).getUser();
 	////String name=uuser.getUsername();
 //典型录音判断
   		String good="";
   		String a=request.getParameter("a");
		if(a==null)a="";
		String yewuerror=request.getParameter("yewuerror");
		if(yewuerror==null)yewuerror="";
		String jiqiao=request.getParameter("jiqiao");
		if(jiqiao==null)jiqiao="";
		String bad=request.getParameter("bad");
		if(bad==null)bad="";
 	
   		String username="";
   	
		String mutex="";
		mutex=request.getParameter("mutex");
		if(mutex==null)mutex="";
		
		Integer count=new Integer(0);
		
		username=request.getParameter("username");
		if(username==null)username="";
		String realname=request.getParameter("realname");
		if(realname==null)realname="";
		if(!realname.equals("")){
		user = udao.getUserByRealname(realname);
		if(user!=null)
		username = user.getUsername();
		else username="777";
		}
		
		String flag=request.getParameter("flag");
		if(flag==null)flag="";

		String callid=request.getParameter("callid");
		if(callid==null)callid="";
		String starttime=request.getParameter("starttime");
		if(starttime==null)starttime="";
		String endtime=request.getParameter("endtime");
		if(endtime==null)endtime="";
		String start=request.getParameter("start");
		if(start==null)start="";
		String end=request.getParameter("end");
		if(end==null)end="";
		String dn=request.getParameter("dn");
		if(dn==null)dn="";

		Object[][] o =null;
 		Object[][] o1 =null;
 		String job = "";
 		String jobname = "";
 		String jobname1 = "";
		ZhijianDAO pit = new ZhijianDAO();
		o=pit.getzuzhang(name);
		if(o!=null&&o.length>0){
		jobname = o[0][0].toString();
		job = o[0][1].toString();
		}
		if(a.equals("1")){
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
				String formatime = sdf.format(date);
				starttime = formatime.substring(0,10);
				endtime = formatime.substring(0,10);
		}
		
		List list=null;
		
		History pn = new History();
		Zhijianpingfen pf = new Zhijianpingfen();
		
		
	%>
	<script type="text/javascript">
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
				'dialogWidth:'  + 180 + 'px;' +
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
			document.lookup2.submit();
		}	
		
		function queryHistory(){
			document.queryform.submit();
		}
		
var http_request = false;   
function initRequest(){   
http_request = false;   
//initialize XMLHttpRequest object   
if(window.XMLHttpRequest){//Mozilla browser   
  http_request = new XMLHttpRequest();   
  if(http_request.overrideMimeType){//MiME type   
   http_request.overrideMimeType("text/xml");   
  }   
}   
else if (window.ActiveXObject){//IE browser   
  try {   
   http_request = new ActiveXObject("Msxml2.XMLHTTP");   
  }catch (e) {   
   try {   
    http_request = new ActiveXObject("Microsoft.XMLHTTP");   
   } catch (e) {}   
  }   
}   
if (!http_request){ // cann't initialize XMLHttpRequest object   
  window.alert("cann't initialize XMLHttpRequest object instance");   
  return false;   
}   
}   
function byId(str){   
return document.getElementById(str);   
}   
function byName(str){   
return document.getElementsByName(str);   
}   
function byTagName(str){   
return document.getElementsByTagName(str);   
}   
//check browser type: IE ,FireFox...   
var isIE = false;   
function checkType(){   
if(document.all){   
  isIE = true;   
}   
}   
/**   
计算包含中文的长度   
*/   
function strlen(str){   
return str.replace(/[^\x00-\xff]/g,"**").length;   
}   
//设置查询结果iframe的高度,让它的横向滚动条显示在浏览器的最下方   
function changeHeight(hei){   
//document.getElementById("resultHeight").height = hei;   
var he = document.body.clientHeight;   
var obj = document.getElementById("resultHeight");   
var rec = getoffset(obj);   
if(he<rec[0]) return;   
obj.height = he - rec[0];   
}   
//获得元素的绝对位置,返回一个数组,长度为2,rec[0]为top value(距离网页顶端的px),rec[1]为left value(距离网页左边的px)   
function getoffset(e)    
{     
  var t=e.offsetTop;     
  var l=e.offsetLeft;     
  while(ee=e.offsetParent)    
  {     
   t+=e.offsetTop;     
   l+=e.offsetLeft;     
  }     
  var rec = new Array(1);    
  rec[0]  = t;    
  rec[1] = l;    
  return rec    
}   
/*   
判断是否为整数,例如:   
isNumber("+1234");返回true;   
isNumber("-1234");返回true;   
isNumber("1234");返回true;   
也就是说输入的数可以为正整数（前面可以有+号，也可以没有），也可以为负整数   
*/   
function isNumber(str){   
var pattern = /^[+-]{0,1}\d+$/;   
if(pattern.test(str)){   
  return true;   
}   
return false;   
}   
/*   
判断是否为小数,例如:   
isNumber("+1234.00");返回true;   
isNumber("-1234.01");返回true;   
isNumber("1234.11");返回true;   
也就是说输入的数可以为正数（前面可以有+号，也可以没有），也可以为负数   
*/   
function isDec(str){   
var pattern = /^[+-]{0,1}\d+\.{0,1}\d*$/;   
if(pattern.test(str)){   
  return true;   
}   
else{   
  return false;   
}   
}   
/*   
给String增加trim函数,用法如下:   
var str = "  test  ".trim();   
这样得到的str的内容就是test   
*/   
String.prototype.trim=function(){   
return this.replace(/(^\s*)|(\s*$)/g,"");   
}   
/*   
表格排序,参数说明   
id : 待排序的表格的名称   
type : 排序的类型(num:按数字;str:按字符串)   
obj : 排序的列(使用的时候写入this即可)   
start : 排序的起始行(主要是去掉无须排序的其它行)   
end : table最后无需参与排序的行数   
例如:   
需要对table1进行排序,由于第一行是表头,所以不参与排序,其余行全部都需要参与排序,所以写法如下,需要增加一个sortType   
<td onClick="sortTable('table1','str',this,1,0)" sortType="asc">  
*/   
function sortTable(id,type,obj,start,end) {   
var tblEl = document.getElementById(id);   
var i, j;   
var minVal, minIdx;   
var testVal;   
var cmp;   
var col = obj.cellIndex;   
var start = 1;   
var total = new Array();   
var str = new Array();   
var order = obj.sortType;   
var rowCount = tblEl.rows.length;//得到行数   
//alert(obj.cellIndex);
if (isNaN(rowCount) || rowCount==start) return;//没有纪录就不需要排序了   
for(i = start;i<rowCount - end;i++)   
{   
  total[i - start] = tblEl.rows[i];   
  str[i - start] = tblEl.rows[i].cells[col].innerText.trim();   
}   
    
for (var step = str.length >> 1; step > 0; step >>= 1)   
    {   
        for (var i = 0; i < step; ++i)   
        {   
            for (var j = i + step; j < str.length; j += step)   
            {   
                var k = j;   
                var value = str[j];   
                var rowValue = total[j];   
                while (k >= step && compareValues(str[k - step],value,type,order) > 0 )   
                {   
                    str[k] = str[k - step];   
                    total[k] = total[k - step];   
                    k -= step;   
                }   
                str[k] = value;   
                total[k] = rowValue;   
            }   
        }   
    }   
    
for(i = 0;i<total.length;i++)   
{    
  tblEl.rows[start - 1].insertAdjacentElement("beforeEnd",total[i]);   
}   
    
if(order=="asc"){   
  obj.sortType = "desc";   
}   
else{   
  obj.sortType = "asc";   
}   
}   
function compareValues(v1, v2,type,order) {   
var f1, f2;   
    
if(v1=="" && order=="asc") {   
  return 1;//如果内容为空,排序时就放置在最后一行   
}   
if(v2=="" && order=="asc") {   
  return -1;//如果内容为空,排序时就放置在最后一行   
}   
    
if (type=="num"){   
  re = /,/g;   
  v1v1 = v1.replace(re,"");   
  v2v2 = v2.replace(re,"");   
  if (isDec(v1)){   
   v1 = parseFloat(v1);   
  }   
      
  if (isDec(v2)){   
   v2 = parseFloat(v2);   
  }   
}   
    
if (v1 == v2) {   
return 0;   
}   
if (v1 > v2){   
  if(order=="asc"){   
      
   return 1;   
  }   
  else{   
   return -1;   
    
  }   
}   
else{   
  if(order=="asc"){   
   return -1;   
  }   
  else{   
   return 1;   
    
  }   
}   
}   
	</script>

	<body>
	<table width="98%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px;">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
        <tr>
          <td  align="left" valign="middle" class="header1"></td>
          <td  class="header2">质检打分情况</td>
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

		<form name="queryform" method="post" action="zjdfqk.jsp">
    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
 	
   	
    	<tr class="row_height">
			 <td width="10%">&nbsp;</td>
          <td width="12%" height="26" align="right">工号：</td>
          <td width="20%"><input type=text name="username" class="input3" size=15></td>
          <td width="10%">&nbsp;</td>
          <td width="12%" align="right">姓名：</td>
          <td width="20%">
         <input type=text name="realname" class="input3" size=15></input>
         
          </td>
    	</tr>
		<tr>		
            
            	 <td width="10%">&nbsp;</td>
          <td width="12%" height="26" align="right">来电号码：</td>
          <td width="20%"> <input type=text name="callid" class="input3"></input>
			
          <td width="10%">&nbsp;</td>
          <td width="12%" align="right">时间：</td>
          <td width="30%"><input type="text" style="width:45%" class="Wdate Wdate_30" id="starttime" name="starttime" onclick="WdatePicker()"></input> 至<input style="width:45%" type="text" class="Wdate Wdate_30" id="endtime" name="endtime" onclick="WdatePicker()"></input>
			
			</td>
          <td width="15%">&nbsp;</td>
        </tr>
       <tr>		
            
            	 <td width="10%">&nbsp;</td>
          <td width="12%" height="26" align="right">实时监听：</td>
          <td width="20%"> <select name="flag" class="select3">
          <option value="">请选择</option>
					<option value=99>是</option>
					<option value="1">否</option>
				</select>
			
          <td width="10%">&nbsp;</td>
          <td width="12%" align="right">时长：</td>
          <td width="20%">
			 <input type=text class="input3" name="start" size=6 maxlength="6"></input> --
			<input type=text class="input3" name="end" size=6 maxlength="6"></input>
			</td>
          <td width="15%">&nbsp;</td>
        </tr>
        
		  
		
          
          
          
        <table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="30" align="center" valign="center"><input name="submit111" type="button" class="submit_2"  value="查 询" onclick="queryHistory()"></input> 
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
	
	
		
		<table  id="table1"  width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_list" id="tb" >
			<tr class="title_font">
				<td width=5% align="center">
					序号
				</td>	
				<td width=5% align="center" onclick="sortTable('table1','num',this,1,0)" sortType="asc">
					<a href="javascript:void(0);">工号</a>
				</td>		
				<td align="center" onclick="sortTable('table1','num',this,1,0)" sortType="asc">
					<a href="javascript:void(0);">来电时间</a>
				</td>
				<td align="center" onclick="sortTable('table1','num',this,1,0)" sortType="asc">
					<a href="javascript:void(0);">打分时间</a>
				</td>
			
				<td align="center">
					来电号码
				</td>
				<td width=7% align="center">
					通话时间
				</td>
				<td align="center" onclick="sortTable('table1','num',this,1,0)" sortType="asc">
					<a href="javascript:void(0);">分数</a>
				</td>
				
				
				<td width=7% align="center">
					听录音


				</td>
				
				<td width=7% align="center">
					历史分数
				</td>
				<td align="center" width=35%>
					典型录音库

				</td>
			</tr>
<%
		
			List list2=null;
			List list3=null;
			String zhijianid="";
			
			//if(mutex.equals("1")){
			if(name.equals("061")){
			username = "'061','037','103','058','090','083'";
			}
			if(name.equals("066")){
			username = "'066','059','092','100','073','052'";
			}
			if(name.equals("065")){
			username = "'065','089','067','105','091','026','081'";
			}
			if(name.equals("055")){
			username = "'055','102','078','056','080','079'";
			}
			if(name.equals("039")){
			username = "'039','088','053','095','087','035'";
			}
			if(name.equals("050")){
			username = "'050','068','082','101','057','076'";
			}
			else if(!username.equals(""))
			username = "'"+username+"'";
			if(uuser.getGroup().getId().toString().equals("1")){
			username = name;
			}
			if(uuser.getGroup().getId().toString().equals("4")&&!name.equals("015")){
			zhijianid = name;
			}
			
			list2=pit.findHistory1(username,zhijianid,callid,starttime,endtime,start,end,flag,currpage,pagesize);
			list3=pit.findHistory1(username,zhijianid,callid,starttime,endtime,start,end,flag,1,65566);
			
			NavigateForm navigateform = new NavigateForm();
		navigateform.setCurrpage(currpage);
		navigateform.setPagesize(pagesize);
		
		navigateform.setPagenum(pagenum);
		request.setAttribute("navigateform", navigateform);
			
			//}
			if(list2!=null&&list2.size()!=0){
			
				int j = list2.size();
				for (int i = 0; i < j; i++) {
					Select1 hospital = (Select1) list2.get(i);
					
					navigateform.setTotal(list3.size());		
					String mark = "";
					String dafenmemo="";
					String id = hospital.getId();
					//System.out.println("id===>"+id);
					if( id != null ){
					pf = pit.getMarkByID(id);
					}
					if( pf!=null&&pf.getMark() != null ){
					
					 mark = pf.getMark();
					
					}
				if( pf!=null&&pf.getDafenmemo() != null ){
					 dafenmemo = pf.getDafenmemo();
					}
	              List modifycslist;
      	          int countcs=0;
      	          ChouyangDAO chouyangService = new ChouyangDAO();
                  modifycslist = chouyangService.countmodifycs(hospital.getId());
	              ZhijianpingfenmxVO rec2 = (ZhijianpingfenmxVO)modifycslist.get(0);
	              countcs=rec2.getModifycs();
					
					if(job.equals("组长")){
						o1 = pit.getzuzhang(hospital.getAgentid());
						jobname1 = o1[0][0].toString();
					}
					
					
					
					%>
					<tr onmouseover=this.className='td_over' onmouseout=this.className='' id='r1'>
			          <td align="center"><%=i+1%></td>
					  <td align="center"><%=hospital.getAgentid()%></td>
					  <td align="center"><%=hospital.getStartrecordtime().toString().substring(0,19)%></td>
					  <td align="center"><%=Comm.convertDT(pf.getRiqi()) %></td>
					  <td align="center"><%=hospital.getCallerid()==null?"":hospital.getCallerid()%></td>
					  <td align="center"><%=hospital.getRecordlength()==null?"":hospital.getRecordlength()%></td>
					  <td align="center"><%=mark%></td>
					  <td align="center"><a href="luyin1.jsp?callid=<%=hospital.getId()%>&dafenmemo1=<%=dafenmemo%>" target=_self>
							听录音 </a></td>
					  <td align="center"><a href="luyin111.jsp?callid=<%=hospital.getId()%>" target=_self>
							历史分数 </a></td>
							  <td align="center"><a  href="good1.jsp?callid=<%=hospital.getId()%>" target=_self>
							<%if(hospital.getDianxingflag()!=null&&hospital.getDianxingflag().equals("1")) {%><font  color=#C3C3C3><%} %>优秀录音</a>
							<a  href="yewuerror1.jsp?callid=<%=hospital.getId()%>" target=_self>
							<%if(hospital.getDianxingflag3()!=null&&hospital.getDianxingflag3().equals("3")) {%><font  color=#C3C3C3><%} %>业务欠佳</a>
							<a  href="jiqiao1.jsp?callid=<%=hospital.getId()%>" target=_self>
							<%if(hospital.getDianxingflag4()!=null&&hospital.getDianxingflag4().equals("4")) {%><font  color=#C3C3C3><%} %>服务差错</a>
							<a  href="bad1.jsp?callid=<%=hospital.getId()%>" target=_self>
							<%if(hospital.getDianxingflag2()!=null&&hospital.getDianxingflag2().equals("2")) {%><font  color=#C3C3C3><%} %>技巧欠佳</a>
							</td>
	       		</tr>
					<%
					
					
					}
					//out.println("<td align='center'>");
					//out.println(time2==null||time2.length==0?"":time2[0][1]);
					//out.println("</td>");
					
				}
			
			
			%>
			
         
		</table>
		
			</form>
		<table  width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;">
		<form name="queryform1" action="zjdfqk.jsp" method="post">
		 <input type="hidden" name="currpage"  />
						<input type="hidden" name="username" value="<%=username%>"></input>
						<input type="hidden" name="name" value="<%=name%>"></input>
						<input type="hidden" name="start" value="<%=start%>"></input>
						<input type="hidden" name="end" value="<%=end%>"></input>
						<input type="hidden" name="starttime" value="<%=starttime%>"></input>
						<input type="hidden" name="endtime" value="<%=endtime%>"></input>
					<input type="hidden" name="flag" value="<%=flag%>"></input>
					<input type="hidden" name="mutex" value="<%=mutex%>"></input>
					</form>
	 <td align="center" valign="middle" class="num_font"><div class="page1_box">
			<elile:navigateBar navigateform="navigateform"
								actionName="zjdfqk.jsp" formName="queryform1" />	
								  </div></td>
        </tr>
	 </table>
		
			<form name="deleteform" method="post" action="delete.jsp">
			</form>		
		<form name="lookup5" method="post" action="fenpeizj.jsp">
		</form>
			<form name="lookup6" method="post" action="weitiao.jsp">
			</form>
			<form name="queryForm" method="post" action="queryBychouyangTime.jsp">
			</form>
			<form name="grbcqztsQueryForm" method="post" action="grbcqzts.jsp">
			</form>

	</body>
</html>
