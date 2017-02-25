<%@page import="java.text.SimpleDateFormat"%>
<%@page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.wondersgroup.falcon.question.dao.EBusinesstypeDAO,com.wondersgroup.falcon.model.citizeninfo.HisNode" %>
<%@page import="com.wondersgroup.falcon.model.citizeninfo.HisAttr,com.wondersgroup.falcon.question.model.EQuestiontype,com.wondersgroup.falcon.question.model.EBusinesstype" %>
<%@page import="com.wondersgroup.falcon.Util.*,com.wondersgroup.falcon.question.beans.*,com.wondersgroup.falcon.model.citizeninfo.PaperType"%>
<%@page import="com.wondersgroup.falcon.question.model.EImportance"%>
<%@page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%-- <%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
ProfessionBean professionBean = new ProfessionBean();//工种
List<Tjobsubject> professions = professionBean.getDistinctProfessions();
String s = request.getParameter("id_job");
String id = request.getParameter("gzid");
List<Object> list_dj = professionBean.getDjById_job(s);//通过工种id获取对应等级

Tjobsubject t = new Tjobsubject();
if(request.getParameter("gzid")!=null){
	t = professionBean.findTjobsubjectBygzid(Integer.parseInt(id));
}
Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
String dt = sdf.format(date);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>试卷查询</title>
	<link href="newcss/style.css" rel="stylesheet" type="text/css" />
	<link href="inc/all.css" rel="stylesheet" type="text/css"/>
	<script type="text/JavaScript" src="<%=request.getContextPath() %>/js/dateMy97/WdatePicker.js"></script>
	<!-- <script type="text/javascript" src="js/laydate/laydate.js"></script> -->
	<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
  <script language="javascript">
/* var bXmlHttpSupport = (typeof XMLHttpRequest != "undefined" || window.ActiveXObject);
if (typeof XMLHttpRequest == "undefined" && window.ActiveXObject) {
        function XMLHttpRequest() {
            var arrSignatures = ["MSXML2.XMLHTTP.5.0", "MSXML2.XMLHTTP.4.0",
                                 "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP",
                                 "Microsoft.XMLHTTP"];
                             
            for (var i=0; i < arrSignatures.length; i++) {
                try {        
                    var oRequest = new ActiveXObject(arrSignatures[i]);            
                    return oRequest;        
                } catch (oError) {
                	//ignore
                }
            }          
    
            throw new Error("MSXML is not installed on your system.");               
        }
}  */ 	
function account(geshi,fenshi,zongfen){
	countRegTotal(geshi);
	document.getElementById(zongfen).value=document.getElementById(geshi).value*document.getElementById(fenshi).value;
}
function changeKksj(){
	var gz = "<%=t.getJobname() %>";
	var djValue = $("#grade").find("option:selected").val();
	var dj = $("#grade").find("option:selected").text();
	if(djValue=="0"){
		dj="";
	}
	var kksj = $("#Kksj").val();
	kksj=kksj.replace(/-/g,"");
	var sjmc = gz+dj+kksj;
	$("#sjmc").val(sjmc);
	/* var sjmc = $("#sjmc").val();
	sjmc = sjmc.substr(0,sjmc.length-8);
	var kksj = $("#Kksj").val();
	kksj=kksj.replace(/-/g,"");
	sjmc = sjmc+kksj;
	$("#sjmc").val(sjmc); */
}
function checkSubmit(){
	if(document.all.sjMc.value==""){
		alert("请输入试卷名称！");
		return ;
	}
	if(document.all.sjZf.value==""){
		alert("请输入试卷总分！");
		return ;
	}
	if(document.all.sjDjsx.value==""){
		alert("请选择输入答卷时限！");
		return ;
	}
	if(document.all.sjBhgfs.value==""){
		alert("请输不及格分数线！");
		return ;
	}
	
	if(document.all.Yxqjzsj.value==""){
		alert("请选择输入有效期截止日期！");
		return ;
	}
	if(document.all.grade.value=="0"){
		alert("请选择等级！");
		return ;
	}
	//获取增加的index数向后台传值
	var indexs=document.getElementsByName("indexname");
	var ind="";
	for(var j=0;j<indexs.length;j++){
		var ind1=indexs[j].value.substring(1,2);
		ind=ind+ind1+",";
	}
		ind=ind.substring(0,ind.length-1);
		document.getElementById('index_id').value=ind;

	//获取页面的分数总和
	var radio = $("input[name='wayout']:checked").val();
	if(radio==2){
		var ctZf=0;
		var arr=document.getElementsByTagName("input");  
		for(var i=0;i<arr.length;i++){ 
			if(arr[i].id.indexOf("zf_")>-1){  
				ctZf+=parseFloat(arr[i].value); 
			 } 
		 }
		  
		if(ctZf!=document.all.sjZf.value){
			alert("选择试题数量*分数="+ctZf+",与总分不符！");
			return ;
		}
	}

	document.myform.submit();
}
		
function countRegTotal(id){
		var tage=document.getElementsByTagName("input");
		var pro=id.substring(0,id.length-1);
		var sum=0;
		for(var i=0;i<tage.length;i++){ 
	 		if(tage[i].id.indexOf(pro)>-1){  
		 		sum+=parseInt(tage[i].value); 
	 		 } 
 		 }
 		 var total=document.getElementById(id.substring(0,id.length-8)).value;
 		 if(sum<=total){
 			 return true;
 		 }else{
 		 	alert("超出题数！！！");
 		 	document.getElementById(id.substring(0,id.length-8)).focus();
 		 };

}
function retrieveBook(serviceId,bxId,index) {
	//if(bXmlHttpSupport) {
	//	var temp=new Date().getTime();
    //   var sUrl = "<%=request.getContextPath() %>/findQuestionCountByType.action?temp="+temp;
    //   if(document.getElementById(serviceId).value!=""){
	//		sUrl=sUrl+"&serviceType="+document.getElementById(serviceId).value
    //  	 }
    //   if(document.getElementById(bxId).value!=""){
    //   		sUrl=sUrl+"&byType="+document.getElementById(bxId).value
    //   }
    //   var oRequest = new XMLHttpRequest();
    //   oRequest.onreadystatechange = function() {
    //   		if(oRequest.readyState == 4) {
    //   			var paperfenshu = eval('(' + oRequest.responseText + ')');
    //   			var string=paperfenshu.counts;
    //   			var counts=string.substring(1,string.length-1).split(",");
    //   			for(var i=0;i<counts.length;i++){
    //   				//document.getElementById("ticount"+i+"["+index).innerText=counts[i];
    //   			}
    //   		}
    //   }
    //   oRequest.open('POST', sUrl);
    //   oRequest.send(null);
   // }
}	
String.prototype.replaceAll  = function(s1,s2){    
	return this.replace(new RegExp(s1,"gm"),s2);    
}
function totalMethod1(){
countRegTotal('singlenumber_0');// 单选
countRegTotal('manynumber_0');//多选
countRegTotal('verdictnumber_0');//判断
countRegTotal('fillnumber_0');//填空
countRegTotal('asknumber_0');//问答
countRegTotal('drawdnumber_0');//绘图
countRegTotal('calculatenumber_0');//计算
countRegTotal('discussnumber_0');//论述
}

//添加
function funAddInquire(){
	var tbodyInquires=document.getElementById("tbodyInquires");
	var inquireRow= tbodyInquires.insertRow(tbodyInquires.rows.length);
	var investorCell=inquireRow.insertCell();
	var maxIndex = parseInt(tbodyInquires.getAttribute('maxIndex'));
	var innerHTML = document.getElementById("template").innerHTML;
	innerHTML = innerHTML.replaceAll("_0","_"+maxIndex);
	investorCell.innerHTML= innerHTML;
	inquireRow.setAttribute('index',maxIndex);
	document.getElementById("ST_YWLX_ID_"+maxIndex).selectedIndex="";
	document.getElementById("ST_BAOXIAN_"+maxIndex).selectedIndex="";
	tbodyInquires.setAttribute('maxIndex',maxIndex+1);
	retrieveBook('ST_YWLX_ID_'+maxIndex,'ST_BAOXIAN_'+maxIndex,'_'+maxIndex);
	totalMethod1();//试题个数
}
//删除
function deleteInquire(obj){
	var tbodyInquires=document.getElementById("tbodyInquires");
	if(tbodyInquires.rows.length>1){
		rowObj=getRowObj();
		if(rowObj.getAttribute('index')==0){
			alert('第一行不能删除!');
			return;
		}
		if(confirm('确定'+obj.title+'?')){
			rowObj.removeNode(true);
			totalMethod1();//试题个数
		}
	}else{
		alert('至少保留一条！');
	}
}

function getRowObj(){
	var eSrc = window.event.srcElement;	
	event.returnValue = false;
	var i = 0;
	while(true){
		if(eSrc.tagName.toUpperCase()=="TR"){	
			if(i<1){
				i++;
			}else{		
				return eSrc;
				break;
			}
		}
		eSrc=eSrc.parentElement;
	}
}
function onselectchange(id){
var idvalue=id.value;
 if(idvalue==""){
 	document.getElementById('to_userid').style.display="none";
 	document.getElementById('to_useridspan').style.display="none"
 	alert("试卷类型不能为空");
 	return false;
 }else if(idvalue==4){
  document.getElementById('to_useridshow').style.display=""
  document.getElementById('to_useridspan').style.display=""
 }else{
 	document.getElementById('to_userid').value="";
 	document.getElementById('to_useridshow').value="";
 	document.getElementById('to_useridshow').style.display="none";
 	document.getElementById('to_useridspan').style.display="none"
 }
}

function getMembers(){
   var temp=new Date().getTime();
   var members = showModalDialog('message_receiver.jsp?temp='+temp,window,'dialogWidth:600px;dialogHeight:500px;');
   var value1 = "";
   if(members!=null){
		document.getElementById('to_userid').value=members.id;
		document.getElementById('to_useridshow').value=members.value;
	}
}

function showDrade(v){
	var gz = "<%=t.getJobname() %>";
	var djValue = $("#grade").find("option:selected").val();
	var dj = $("#grade").find("option:selected").text();
	if(djValue=="0"){
		dj="";
	}
	var kksj = $("#Kksj").val();
	kksj=kksj.replace(/-/g,"");
	var sjmc = gz+dj+kksj;
	$("#sjmc").val(sjmc);
	<%-- var gdj = $("#grade").find("option:selected").text();
	var kksj = $("#Kksj").val();
	kksj=kksj.replace(/-/g,"");
	var sjmc = '<%=t.getJobname() %>'+gdj+kksj;
	$("#sjmc").val(sjmc); --%>
	$("#table_jdys tr:gt(0)").remove();//清空table
	var gzid = "<%=request.getParameter("id_job") %>";
	var radio = $("input[name='wayout']:checked").val();
	if(radio==1){
		if(v==0){
			//document.getElementById("jdys").style.display="none";
			$("#jdys").css('display','none');
		}else{
			var dj = v;
			$.ajax({
				type: 'post',
				async: false,
				url: 'findJdys.action?gzid='+gzid+'&dj='+dj,
				success:function(result){
					var data = eval(result);
					var theTable = document.getElementById("table_jdys");
					$.each(data, function(i, n) {
						var rowCount = theTable.rows.length;
						var row = theTable.insertRow(rowCount);
						var cell1 = row.insertCell(0);
						cell1.innerHTML=data[i][1];
						var cell2 = row.insertCell(1);
						cell2.innerHTML=data[i][2];
						var cell3 = row.insertCell(2);
						cell3.innerHTML=data[i][3];
						var cell4 = row.insertCell(3);
						cell4.innerHTML=data[i][4];
					});
				},
				error:function(){
					alert("error");
				}
			});
			//document.getElementById("jdys").style.display="block";
			$("#jdys").css('display','');
		}
	}else{
		$("#jdys").css('display','none');
	}
	
}
function newExam(){
	document.getElementById("jdys").style.display="none";
}
function gzChange(v){
	var value = v.value;
	var dj = document.getElementById("ST_BAOXIAN_0");
	dj.length = 1;
	$.ajax({
		type:'post',
		async : false,
		url:'findDjByGzid.action?gzid='+value,
		success:function(result){
			var data = eval(result);
			$.each(data, function(i, n) {
				document.getElementById("ST_BAOXIAN_0").options
				.add(new Option(data[i][1],
						data[i][0]));
			});
		},
		error:function(){
			alert("error");
		}
	});
}
function show(){
	$("#tbodyInquires").css('display','block');
	$("#add").css('display','block');
	$("#jdys").css('display','none');
}
function unshow(){
	$("#tbodyInquires").css('display','none'); 
	$("#add").css('display','none');
}
</script>
</head>
<body class="nrbj" onload="retrieveBook('ST_YWLX_ID_0','ST_BAOXIAN_0','_0')">
<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
  <tr>
    <td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td  align="left" valign="middle" class="header1"></td>
        <td  class="header2">
        <%
		if(request.getParameter("gzid")!=null){
			out.print(t.getJobname());
		}else{
			out.print("请选择工种");
		}
		%>
		
        </td>
        <td  class="header3"></td>
      </tr>
    </table></td>
    <td width="53%"  align="left"></td>
  </tr>
  <tr>
    <td colspan="2" valign="top" ><div id="content1" class="borader">
<form name="myform" action="<%=request.getContextPath() %>/papersServlet" method="post">
<input type="hidden" name="servicetype" id="servicetype" value="<%=t.getId_job() %>">
<input type=hidden name="actionType" value="auto">
<input id="index_id" type="hidden" name="indexs" value=""/><!-- 误删 -->
<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" >
			     <tr class="row_height">
			       <td height="26" colspan="8" align="center"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="tablistys">
                     <tr class="row_height">
                       <td width="8%" align="right">考试标题：</td>
                       <td width="23%">
                       	   <input type="text" name="sjMc" value="<%=t.getJobname() %><%=dt %>" id="sjmc" class="input1" style="float: left;">
                           <font color="#FF0000" style="float: left;" >*</font>
                       </td>
                       <td width="10%" align="right">试卷总分：</td>
                       <td width="15%">
                       	   <input type="text" name="sjZf" id="sjZf" value="100" class="input1" style="float: left;width: 150px"/>
                           <font color="#FF0000" style="float: left;" >*</font>
                       </td>
                       <td width="10%" align="right">答卷时限：</td>
                       <td>
						   <input type="text" name="sjDjsx" value="60" class="input1" style="float: left;width: 150px"/>
                           <font color="#FF0000" style="float: left;" >*</font>
                       </td>
                     </tr>
                     <tr>
                       <td align="right" nowrap="nowrap">及格分数：</td>
                       <td><input type="text" name="sjBhgfs" value="60" class="input1" style="float: left;"/>
                           <font color="#FF0000" style="float: left;" >*</font></td>
                       <td align="right">有效期至：</td>
                       <td>
                       	<!-- <input type="text" id="Yxqjzsj" class="laydate-icon" onclick="laydate()" name="Yxqjzsj" style="width:170px"/>
                           <font color="#FF0000" >*</font>onclick="WdatePicker( {el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'} )" -->
                        <input type="text" id="Yxqjzsj" name="Yxqjzsj" onclick="WdatePicker()" class="Wdate" style="width:150px"/>
                        <font color="#FF0000" >*</font>
                       </td>
                       <td align="right">开考后：</td>
                       <td><select name="tqjj" style="width:60px">
                        		<option value="10">10</option>
                        		<option value="30" selected="selected">30</option>
                        		<option value="45">45</option>
                        		<option value="60">60</option>
                        	</select> 
                       		<!-- <font color="#FF0000" >*</font> -->
                       		<font>分钟可交卷</font>
                       </td>
                     </tr>
 					<tr class="row_height">
                       <td width="10%" align="right">即显分数：</td>
                       <td width="23%">
                       	<div style="width:40px;float: left;">
                       		<input type=radio name="sjLjcf" value="1" style="width:17px;" checked="checked"/>是
                       	</div>
                       	<div style="width:40px;float: left;">
                       		<input type=radio name="sjLjcf" value="0" style="width:17px;" />否
                       	</div>
                       </td>
                       <td width="8%" align="right">考试类型：</td>
                       <td width="23%">
							<select id="paper_Type" name="paperType" style="width: 150px">
								<option value="1" selected>鉴定类考试</option>
								<option value="2">其他类考试</option>
							</select>
						</td>
                       <!-- <td width="10%" align="right"><span id="to_useridspan" style="display:none">播测题考试人</span></td>
                       <td>
                       		<input id="to_userid" name="toUserId" type="hidden"/>
                       		<input id="to_useridshow" name="show" type="text" style="display:none" onfocus="getMembers();"/>
                       </td> -->
                       <td width="10%" align="right">重要程度：</td>
                       <td width="23%">
                       	<div style="width:40px;float: left;">
                       		<input type="radio" name="zycd" value="X" style="width:17px;" checked="checked"/>X
                       	</div>
                       	<div style="width:40px;float: left;">
                       		<input type="radio" name="zycd" value="Y" style="width:17px;"/>Y
                       	</div>
                       	<div style="width:40px;float: left;">
                       		<input type="radio" name="zycd" value="Z" style="width:17px;"/>Z
                       	</div>
                       </td>
                     </tr>
                     <tr>
                     	<td width="10%" align="right">生成份数：</td>
                       	<td>
                       		<input type="number" name="sjfs" value="100" min="1" style="width: 100px"/>
                       	</td>
                       	<td width="10%" align="right">开考时间：</td>
                       	<td>
	                       	<input type="text" id="Kksj" name="Kksj" onclick="WdatePicker()" onchange="changeKksj()" class="Wdate" style="width:150px"/>
	                        <font color="#FF0000" >*</font>
                       		<!-- <input type="text" id="Kksj" name="Kksj" style="width:170px" class="laydate-icon" onclick="laydate()"/>
                            <font color="#FF0000" >*</font> -->
                       </td>
                       	
                       	<td width="10%" align="right">难度系数：</td>
                       	<td>
                       		<select name="ndxs" style="width:60px">
                       			<option value="1" selected="selected">易</option>
                       			<option value="2">较易</option>
                       			<option value="3">普通</option>
                       			<option value="4">较难</option>
                       			<option value="5">难</option>
                       		</select>
                       	</td>
                     </tr>
				<tr>
            			<td width="10%" align="right">单选题：</td>
           	 			<td>
           	 			题数:<input size="4" type="text" id="single"  name="single" value="40" />
            			分数:<input size="4" type="text" id="singlepoint" name="singlepoint" value="1" />
           	 			</td>
           	 			
           	 			<td width="10%" align="right">多选题：</td>
           	 			<td>
           	 			题数:<input size="4" type="text" id="many"  name="many" value="20" />
            			分数:<input size="4" type="text" id="manypoint" name="manypoint" value="1" />
           	 			</td>
           	 			<td width="10%" align="right">判断题：</td>
           	 			<td> 
           	 			题数:<input size="4" type="text" id="verdict"  name="verdict" value="40" />
            			分数:<input size="4" type="text" id="verdictpoint" name="verdictpoint" value="1" />
           	 			</td>
           	 		</tr>
           	 		<tr>
           	 			<td width="10%" align="right">填空题：</td>
           	 			<td>
           	 			题数:<input size="4" type="text" id="fill"  name="fill" value="0" />
            			分数:<input size="4" type="text" id="filltpoint" name="fillpoint" value="0" />
            			</td> 
           	 			<td width="10%" align="right">问答题：</td>
           	 			<td>
           	 			题数:<input size="4" type="text" id="ask"  name="ask" value="0" />
            			分数:<input size="4" type="text" id="asktpoint" name="askpoint" value="0" />
           	 			</td>
           	 			<td width="10%" align="right">计算题：</td>
           	 			<td>
           	 			题数:<input size="4" type="text" id="calculate"  name="calculate" value="0" />
            			分数:<input size="4" type="text" id="calculatepoint" name="calculatepoint" value="0" />
           	 			</td>
           	 		</tr>
           	 		<tr>
           	 		</tr>
           	 		<tr>
						<td width="10%" align="right">论述题：</td>
           	 			<td> 
           	 			题数:<input size="4" type="text" id="discuss"  name="discuss" value="0" />
            			分数:<input size="4" type="text" id="discusspoint" name="discusspoint" value="0" />
           	 			</td>
           	 			<td width="10%" align="right">绘图题：</td>
           	 			<td> 
           	 			题数:<input size="4" type="text" id="draw"  name="draw" value="0" />
            			分数:<input size="4" type="text" id="drawpoint" name="drawpoint" value="0" />
           	 			</td>
						<td></td>
           	 			<td></td>
						<td></td>
           	 			<td></td>
           	 		 </tr>
           	 		 <tr>
						<td width="10%" align="right">等级：</td>
                     	<td>
                     		<select id="grade" name="grade" onchange="showDrade(this.value)">
                     			<option value="0">请选择</option>
                     			<%
									for (int i = 0; i < list_dj.size(); i++) {
											List<String> l = (List<String>)list_dj.get(i);
											out.println("<option value=" + l.get(0) + "");
											out.println(">" + l.get(1) + "");
											out.println("</option>");
										}
								%>
                     		</select>
                     	</td>
                     	<td width="10%" align="right">出题方式：</td>
                     	<td>
                     		<input type="radio" id="wayout" name="wayout" value="1" checked="checked" onclick="unshow()">比例出题
                     		<input type="radio" id="wayout" name="wayout" value="2" onclick="show()">输入出题
                     	</td>
                     </tr>
                     <tr>
                     	<td id="jdys" style="width: 100%;display:none;" colspan="6" rowspan="20">
	                     	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:12px;">
					          <tr>
					            <td class="borader3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
					                <tr>
					                  <td><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
					                      <tr>
					                        <td  align="left"  class="header7"></td>
					                        <td  class="header8">鉴定要素</td>
					                      </tr>
					                  </table></td>
					                </tr>
					              </table>
					              <div style="height:270px; overflow:auto" >
						           <table id="table_jdys" width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="table_list">
						             <tr class="title_font">
						                <td width="5%" align="center" bgcolor="#C7E2F8"><span class="out">行为领域 </span></td>
						                <td width="12%" align="center" bgcolor="#C7E2F8"><span class="out">鉴定范围</span></td>
						                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">鉴定点</span></td>
						                <td width="9%" align="center" bgcolor="#C7E2F8"><span class="out">比例</span></td>
						             </tr>
								   </table>
								  </div>
							   </td>
							</tr>
						  </table>
                     	</td>
                     </tr>
                     
                   </table>
                  </td>
	        	</tr>	
	        	<tr height="6px">
                   <td colspan="6"></td>
                </tr>
                 <tr class="row_height" >
			          <!-- <td>&nbsp;</td>
			          <td height="26" align="right">&nbsp;</td>
			          <td >&nbsp;</td>
			          <td align="right">&nbsp;</td>
			          <td >&nbsp;</td>
			          <td align="center">&nbsp;</td> -->
					  <td rowspan="6">
					  	<div>
					  		<div style="margin-left: 200px;float: left;">
						  		<input name="button" type="button" class="submit_2" onClick="javascript:checkSubmit();" value="试卷预览" /><!-- </td> -->
					  		</div>
						  <!-- <td align="center"> -->
						  	<div style="margin-left: 240px;float: left; display: none" id="add">
								<input name="button" type="button" class="submit_2" onClick="javascript:funAddInquire();" value="增加" />
						  	</div>
					  	</div>
					</td>
			     </tr>               
		           <tr>
		            <td colspan="6" align="center">&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
		          </tr>
		          <!-- 单独部分出题 -->
<tbody id="tbodyInquires" maxIndex='1' style="width:100%;display: none">
	<tr id="template" index="0" class="row_height">
		<td colspan="8">
				<table width="99%" style="border-collapse:collapse;width: 100%; "border="0" align="center" cellpadding="0" cellspacing="0" >
		 			 <tr>
                       <td width="10%" align="right">工种1：</td>
                       <td colspan="3">
                       		<input type="hidden" name="indexname" value="_0"/>
							<!-- <select id="ST_YWLX_ID_0" name="serviceType_0" style="width: 280px" onchange="retrieveBook('ST_YWLX_ID_0','ST_BAOXIAN_0','_0');"> -->
							<select id="ST_YWLX_ID_0" name="serviceType_0" style="width: 280px" onchange="gzChange(this)">
								<option value="0">请选择</option>
								<%
									for (int i = 0; i < professions.size(); i++) {
											Tjobsubject tj = professions.get(i);
											out.println("<option value=" + tj.getId_job() + "");
											/* if(tj.getId_job().equals(request.getParameter("id_job"))){
												out.println(" selected='selected' ");
											} */
											out.println(">" + tj.getJobname() + "");
											out.println("</option>");
										}
								%>
							</select>
					   </td>
                       <td width="10%" align="right">等级：</td>
                       <td colspan="3">
							<select id="ST_BAOXIAN_0" name="bxType_0" style="width:80px" onchange="retrieveBook('ST_YWLX_ID_0','ST_BAOXIAN_0','_0');">
								<option value="">请选择</option>
							</select>
					  </td>
                     </tr>
 					<tr>
                       <td width="10%" height="30" align="right">重要程度：</td>
						<td colspan="3" >
	                       	<div style="width:40px;float: left;">
	                       		<input type="radio" name="zycd_0" value="X" style="width:17px;" checked="checked"/>X
	                       	</div>
	                       	<div style="width:40px;float: left;">
	                       		<input type="radio" name="zycd_0" value="Y" style="width:17px;"/>Y
	                       	</div>
	                       	<div style="width:40px;float: left;">
	                       		<input type="radio" name="zycd_0" value="Z" style="width:17px;"/>Z
	                       	</div>
					   </td>
					  <td width="10%" align="right">难度系数：</td>
                       	<td>
                       		<select name="ndxs_0" style="width:60px">
                       			<option value="1" selected="selected">易</option>
                       			<option value="2">较易</option>
                       			<option value="3">普通</option>
                       			<option value="4">较难</option>
                       			<option value="5">难</option>
                       		</select>
                       	</td>
                     </tr>
         <tr class="row_height">
            <td>单选题:</td>
            <td>题数:
            <input size="4" type="text" id="singlenumber_0"  name="singlenumber_0" value="5" onBlur="account('singlenumber_0','singlepoint_0','singlezf_0');"/>个</td>
            <td> 分数:
            <input size="4" type="text" id="singlepoint_0" name="singlepoint_0" value="20" onBlur="account('singlenumber_0','singlepoint_0','singlezf_0');"/>分</td>
			<td> 总分:
              <input size="4" type="text" id="singlezf_0" name="singlezf_0" readonly="readonly"/>分</td>
            <td>多选题:</td>
            <td>题数:
             <input size="4" type="text" id="manynumber_0" name="manynumber_0" value="0" onBlur="account('manynumber_0','manypoint_0','manyzf_0');"/>个</td>
            <td>分数:
              <input size="4" type="text" id="manypoint_0" name="manypoint_0" value="5" onBlur="account('manynumber_0','manypoint_0','manyzf_0');"/>分</td>
			<td>总分:
            <input size="4" type="text" id="manyzf_0" name="manyzf_0" value="" readonly="readonly"/>分</td>
          </tr>

         <tr>
            <td>判断题: </td>
            <td>题数:
            <input size="4" type="text" id="verdictnumber_0" name="verdictnumber_0" value="0" onBlur="account('verdictnumber_0','verdictpoint_0','verdictzf_0');"/>个</td>
            <td> 分数:
            <input size="4" type="text" id="verdictpoint_0" name="verdictpoint_0" value="5" onBlur="account('verdictnumber_0','verdictpoint_0','verdictzf_0');"/>分</td>
			<td>总分:
            <input size="4" type="text" id="verdictzf_0" name="verdictzf_0" readonly="readonly"/>分</td>
            <td >填空题:</td>
            <td >题数:
              <input size="4" type="text" id="fillnumber_0" name="fillnumber_0" value="0" onBlur="account('fillnumber_0','fillpoint_0','fillzf_0');"/>个</td>
            <td >分数:
              <input size="4" type="text" id="fillpoint_0" name="fillpoint_0" value="5" onBlur="account('fillnumber_0','fillpoint_0','fillzf_0');"/>分</td>
			  <td>总分:
            <input size="4" type="text" id="fillzf_0" name="fillzf_0" value="0" readonly="readonly"/>分</td>
          </tr>
          <tr>
            <td>问答题:</td>
            <td>题数:
            <input size="4" type="text" id="asknumber_0" name="asknumber_0" value="0" onBlur="account('asknumber_0','askpoint_0','askzf_0');"/>个</td>
            <td> 分数:
            <input size="4" type="text" id="askpoint_0" name="askpoint_0" value="5" onBlur="account('asknumber_0','askpoint_0','askzf_0');"/>分</td>
			<td>总分:
            <input size="4" type="text" id="askzf_0" name="askzf_0" value="0" readonly="readonly"/>分</td>
            <td >计算题:</td>
            <td>题数:
              <input size="4" type="text" id="calculatenumber_0" name="calculatenumber_0" value="0" onBlur="account('calculatenumber_0','calculatepoint_0','calculatezf_0');"/>个</td>
            <td >分数:
              <input size="4" type="text" id="calculatepoint_0" name="calculatepoint_0" value="10" onBlur="account('calculatenumber_0','calculatepoint_0','calculatezf_0');"/>分</td>
			  <td>总分:
            <input size="4" type="text"  id="calculatezf_0" value="0"  name="calculatezf_0" readonly="readonly"/>分</td>
          </tr>
  		 <tr>
  		 	<td>论述题:</td>
            <td >题数:
            <input size="4" type="text" id="discussnumber_0" name="discussnumber_0" value="0" onBlur="account('discussnumber_0','discusspoint_0','discusszf_0');"/>个</td>
            <td> 分数:
            <input size="4" type="text" id="discusspoint_0" name="discusspoint_0" value="5" onBlur="account('discussnumber_0','discusspoint_0','discusszf_0');"/>分</td>
			<td>总分:
            <input size="4" type="text" id="discusszf_0" value="0"  name="discusszf_0" readonly="readonly"/>分</td>
            <td>绘图题:</td>
            <td >题数:
            <input size="4" type="text" id="uncertainnumber_0" name="uncertainnumber_0" value="0" onBlur="account('uncertainnumber_0','uncertainpoint_0','uncertainzf_0');"/>个</td>
            <td> 分数:
            <input size="4" type="text" id="uncertainpoint_0" name="uncertainpoint_0" value="5" onBlur="account('uncertainnumber_0','uncertainpoint_0','uncertainzf_0');"/>分</td>
			<td>总分:
            <input size="4" type="text" id="uncertainzf_0" value="0"  name="uncertainzf_0" readonly="readonly"/>分</td>
            
          </tr>
			<tr>
            <td ></td>
            <td></td>
            <td ></td><td></td>
          </tr>
          <tr class="row_height">
            <td height="30" colspan="8" align="center">&nbsp;<input name="button" type="button" class="submit_2" onClick="deleteInquire(this);" value="删除" /></td>
          </tr>
	</table>
</td>
</tr>
</tbody>
        </table>     
</form>
</div>
</td>
</tr>
</table>
</body>
</html>
<script language="javascript">	
account('singlenumber_0','singlepoint_0','singlezf_0');//单选题
account('manynumber_0','manypoint_0','manyzf_0');//多选题
account('verdictnumber_0','verdictpoint_0','verdictzf_0');//判断题
account('fillnumber_0','fillpoint_0','fillzf_0');//填空题
account('asknumber_0','askpoint_0','askzf_0');//问答题
account('calculatenumber_0','calculatepoint_0','calculatezf_0');//计算题
account('discussnumber_0','discusspoint_0','discusszf_0');//论述题
</script>