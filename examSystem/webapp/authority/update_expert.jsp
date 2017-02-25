<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wondersgroup.technocracy.bo.Addexpert"%>
<%@ page import="com.wondersgroup.technocracy.bo.Addexperts"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
String eid = request.getParameter("expertid");
String birthday = request.getParameter("birthday");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>修改专家信息</title>
<link href="<%=request.getContextPath() %>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/JavaScript" src="js/dateMy97/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<style>
.required {
	color: red;
}
</style>
<script type="text/javascript">
function oload(){
	var bd = "<%=birthday%>";
	var y = bd.substring(0,4);
	var m = bd.substring(4,6);
	var d = bd.substring(6,8);
	bd = (y+'-'+m+'-'+d);
	$.ajax({
		type:'post',
		async:false,
		url:'findExpertById.action?expertId='+<%=eid%>,
		success:function(result){
			var data = eval(result);
			$("#hzz001").val(data[0].hzz001);
			$("#indicate").val(data[0].indicate);
			$("#idstyle").val(data[0].idstyle);
			$("#idnumber").val(data[0].idnumber);
			$("#name").val(data[0].name);
			if(data[0].sex==1){
				$("#displaysex").val("男");
			}else{
				$("#displaysex").val("女");
			}
			$("#date").val(data[0].date);
			$("#education").val(data[0].education);
			$("#org").val(data[0].org);
			
			//alert(data[2][0])
			for(var i=0;i<data[2].length;i++){
				$("input:checkbox").eq(data[2][i]-1).attr('checked', 'true');
			}
			$("#jtitle").val(data[0].jtitle);
			$("#date").val(bd);
			$("#major").val(data[0].major);
			$("#address").val(data[0].eaddress);
			$("#phone").val(data[0].phone);
			$("#remark").val(data[0].remark);
			$("#workphone").val(data[0].workphone);
			$("#studymajor").val(data[0].studymajor);
			$("#qq").val(data[0].qq);
			$("#email").val(data[0].email);
			$("#committee").val(data[1].committee);
			$("#duty").val(data[1].duty);
			$("#group").val(data[1].group);
			//alert(data[0].date[0])
			//$("#date").attr("value",data[0].date.format());
		},
		error:function(){
			alert("操作失败！");
		}
	});
}
function idcardToBirthAndSex(){
	var aac003 =  document.getElementById("name").value; //姓名 
	var aac058 = document.getElementById("idstyle").value;
	var aac147 =document.getElementById("idnumber").value; //证件号码
	var aac004 =document.getElementById("sex").value;  //性别
	var aac006 = document.getElementById("date").value;//出生日期
	if(aac058 == ''){
	   alert('请先选择证件类型');
		return;
	}
	if(aac058=='1'){
		var aac147 = document.getElementById("idnumber").value;
		if(aac147 == ''){
		    alert('请输入证件号码');
			return;
	    }
		if (aac147.length != 15 && aac147.length != 18) {
		    alert('身份证号码长度应该为15位或者18位!');
			return;
		}
		if(aac147.length == 15){
			aac147=idCardTo18(aac147);
		}
		if(aac147.length == 18){
			var ifIdValid = validateIdCard(aac147);
			if(ifIdValid!= true){
				alert(ifIdValid,'提示');
				return false;
			}
		}
		var dateAndSex = idcardToDateAndSex(aac147);
		 //alert(dateAndSex[0]);
		document.getElementById("date").value=dateAndSex[0];
		document.getElementById("sex").value=dateAndSex[1];
		if(document.getElementById("sex").value==1) 
			{
			//document.getElementById("displaysex").value='男';
			$("#displaysex").val("1");
			}
		else if(document.getElementById("sex").value==2)
			{
				//document.getElementById("displaysex").value='女';
				$("#displaysex").val("2");
			}
		 
 		}
	 //判断输入的证件号码在提交时校验数据库中是否存在
	  	$.ajax({
			  type:'post',
			  async : false,
			  url:'queryexpert.action?idnumber='+aac147,
			  success:function(result){
				  if(result=="1")
				 	 {
					  alert("输入证件号码与专家库中的证件号码重复，请确认专家信息是否已存在!");
					  return ;
					   }
			  },
			 error:function(){
				  alert("error");
			 }
		 });
	}
	function idcardToBirthAndSex1(){
	 var aac003 =  document.getElementById("name").value; //姓名 
		var aac058 = document.getElementById("idstyle").value;
		var aac147 =document.getElementById("idnumber").value; //证件号码
	   if(aac058=='4' || aac058=='6'|| aac058=='7' || aac058=='8'){
		   var aac147 =document.getElementById("idnumber").value; //证件号码
		     if(aac147.length > 14){
		          alert('输入证件号码过长，请确认是否正确');
			      return;
		     }
		}
	}
function dosubmit() {
		 var aac003 = document.getElementById("name").value;// 姓名
			var aac058 = document.getElementById("idstyle").value;
			var aac147 =document.getElementById("idnumber").value; //证件号码
			var aac004 =document.getElementById("displaysex").value;  //性别
			//var aac006 = document.getElementById("date").value;//出生日期
			//var now = (new Date()).format('Ymd');
			//alert(now);
			if (aac003 == '') {
				alert("姓名不可以为空!");
				return;
			}
			 
			if((/\d+/gi).test(aac003)){
				alert("输入姓名不能包含数字！");
			    return;
			}
			if (aac058 == '') {
				alert("证件类型不可以为空!");
				return;
			}
			if (aac147 == '') {
				alert("证件号码不可以为空!");
				return;
			}
			//提交时再次校验身份证号是否正确
			if(aac058=='1'){
				var aac147 = document.getElementById("idnumber").value;
				if (aac147.length != 15 && aac147.length != 18) {
				    alert('身份证号码长度应该为15位或者18位!');
					return;
				}
				
				if(aac147.length == 15){
					aac147=  idCardTo18(aac147);
				}
				if(aac147.length == 18){
					var ifIdValid = validateIdCard(aac147);
					if(ifIdValid!= true){
						alert(ifIdValid,'提示');
						return false;
					}
			}
				var dateAndSex = idcardToDateAndSex(aac147);
				//alert(dateAndSex[0]);
				document.getElementById("date").value=dateAndSex[0];
				document.getElementById("sex").value=dateAndSex[1];
				if(document.getElementById("sex").value==2) 
					{
					//document.getElementById("displaysex").value="女";
						$("#displaysex").val("2");
					}
				else if(document.getElementById("sex").value==1)
					{
						//document.getElementById("displaysex").value="男";
						$("#displaysex").val("1");
					}
				//alert(document.getElementById("displaysex").value); 
				//Ext.getCmp('aac006').setValue(dateAndSex[0]); //出生日期
				//Ext.getCmp('aac004').setValue(dateAndSex[1]); //性别
				var aac006 = document.getElementById("date").value;//出生日期
				//var now = (new Date()).format('Ymd');
				//if(aac006 > now){
					//alert('身份证号中出生日期不应晚于当前日期!');
					//return;
				//}
			
			}
			
			if (aac004 == '') {
				alert('性别不可以为空!');
				return;
			}
			if (aac006 == '') {
				alert('出生日期不可以为空!');
				return;
			}
			$.ajax({
				type:'post',
				url:'updateExpert.action',
				data:$('#addform').serialize(),
				dataType:'text',
				success:function(result){
					alert("修改成功！");
					window.close();
					window.opener.location.reload();
				},
				error:function(){
					alert("程序出错！");
					window.close();
				}
			});
}
function doreset() {
	//Ext.getCmp('form1').form.reset();
	document.addform.reset();
	
}
/**
 *** 转化身份证，将15位或者18位的身份证转换为正确的18为身份证
 **/
  idCardTo18 =function(idcard) {
 	var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
 	var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
 	var sum = 0;
 	 
 	if(idcard.length != 15 &&idcard.length != 18) {
 		return idcard;
 	}

 	if (idcard.length == 15) {
 		idcard = idcard.substr(0, 6) + '19'+ idcard.substr(6, idcard.length - 6);
 	} else {
 		idcard = idcard.substr(0, 17);
 	}

 	for (var i = 0; i < idcard.length; i++) {
 		sum += idcard.substr(i, 1) * arrInt[i];
 	}

 	idcard += arrCh[sum % 11];

 	return idcard;
 }

 /**
 **  校验身份证是否合法
 **/	
  validateIdCard = function(value) {
 	
 	if(typeof value =='undefined' || !value || value ==''){
 		return "身份证号码为空!";	
 	}	
 	
 	if (value.length != 15 && value.length != 18) {
 		return '身份证号码长度应该为15位或者18位!';
 	}
 	
 	var patten1 = "^\\d{15}$";
 	var patten2 = "^\\d{17}[0123456789Xx]$";

 	if (!new RegExp(patten1, "g").test(value)&& !new RegExp(patten2, "g").test(value)) {
 		return '身份证号码只能包含数字和X';
 	}
 	
 	var id18 = idCardTo18(value);

 	var csrqstr = id18.substr(6, 8);
 	var year = parseInt(csrqstr.substr(0, 4), 10);
 	var month = parseInt(csrqstr.substr(4, 2), 10) - 1;
 	var date = parseInt(csrqstr.substr(6, 2), 10);
 	
 	var csrq = new Date(year, month, date);

 	
 	
 	if (year != csrq.getFullYear() || month != csrq.getMonth()|| date != csrq.getDate()) {
 		return '身份证中的出生日期['+csrqstr +']非法!';
 	}

 	if(value !=id18){
 		return "身份证不正确，正确的身份证号码为:["+id18+"]";
 	}	
 	
 	return true; 
 }

 /**
 *** 将身份证转换为 出生日期和性别
 *** 返回：错误身份证返回空
 *** 返回：正确身份证，返回数据，第一个为Date类型的生日，第二个为整形的性别(0:男,1:女)
 **/
  idcardToDateAndSex =function(idcard){
 	
 	var id18 =idCardTo18(idcard);
 	
 	var dateAndSex =[];
 	
 	if(validateIdCard(id18) !==true){
 		return dateAndSex;
 	}
 	
 	//var csrqstr = id18.substr(6, 8);
 	//var year = parseInt(csrqstr.substr(0, 4), 10);
 	////var month = parseInt(csrqstr.substr(4, 2), 10) - 1;
 	//var date = parseInt(csrqstr.substr(6, 2), 10);
 	//var csrq = new Date(year, month, date);
 	tmpStr = id18.substring(6, 14);
    tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6);

 
 	dateAndSex[0] =tmpStr;
 	
 	var sexInt = parseInt(id18.substr(16, 1));
 	 
 	if(sexInt % 2 ===0)
 		 dateAndSex[1] =2; 
 	else
 		dateAndSex[1] =1;	 
 		
 	return dateAndSex;
 	
 }
window.onload=oload;
</script>
</head>

<body style="background-color: rgba(243, 246, 248, 1)">
	<div>
	<form id="addform" name="addform">
		<input type="hidden" id="hzz001" name="expert.hzz001"/>
		<input type="hidden" id="indicate" name="expert.indicate"/>
		<table width="99%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="right" height="28"><span class="required">*</span>
					证件类型：</td>
				<td align="left"><select id="idstyle" name="expert.idstyle"
					style="width: 130px">
						<option value="">请选择</option>
						<option value="1">身份证</option>
						<option value="2">解放军证</option>
						<option value="3">身份证</option>
						<option value="4">护照</option>
				</select></td>

				<td height="28" align="right"><span class="required">*</span>
					证件号码：</td>
				<td align="left"><input type="text" id="idnumber" value=""
					name="expert.idnumber" onblur="idcardToBirthAndSex()"></td>
				<td align="right"><span class="required">*</span>姓名：</td>
				<td align="left"><input type="text" id="name" value=""
					name="expert.name" onblur="idcardToBirthAndSex1()"></td>
			</tr>
			<tr>
				<td width="13%" align="right" height="28"><span
					class="required">*</span> 性别：</td>
				<td>
					<input type="hidden" id="sex" name="expert.sex"/>
					<select id="displaysex" name="displaysex" >
						<option value="">请选择</option>
						<option value="1">男</option>
						<option value="2">女</option>
					</select>
				</td>
				<td width="13%" align="right"><span class="required">*</span>出生日期：</td>
				<td width="18%" align="left">
					<input type="text" id="date" name="date" onclick="WdatePicker()" style="width:130px" class="Wdate"/>
					<!-- <input type="date" id="date" name="expert.date"></td> -->
				<td width="13%" align="right">学历:</td>
				<td><select id="education" name="expert.education"
					style="width: 130px">
						<option value="0">请选择</option>
						<option value="1">博士研究生</option>
						<option value="2">硕士</option>
						<option value="3">本科</option>
						<option value="4">专科</option>
				</select></td>
			</tr>
			<tr>
				<td width="13%" align="right" height="28"><span class="required">*</span>所属单位：</td>
				<td width="18%" align="left"><input type="text" id="org"
					name="expert.org"></td>
				<td align="right"><span class="required">*</span>专家类别:</td>
				<td>
					<input type="hidden" name="expertStyle" value="0">
					<input type="checkbox" name="expertStyle" value="1">考评专家
                    <input type="checkbox" name="expertStyle" value="2">命题专家<br>
                    <input type="checkbox" name="expertStyle" value="3">监考老师
                    <input type="checkbox" name="expertStyle" value="4">督导专家
				</td>
				<td width="13%" align="right"><span class="required">*</span>职称：</td>
				<td width="18%" align="left"><input type="text" id="jtitle"
					name="expert.jtitle"></td>
			</tr>
			<tr>
				<td width="13%" align="right" height="28"><span class="required">*</span>擅长专业：</td>
				<td width="18%" align="left"><input type="text" id="major"
					name="expert.major"></td>

				<td width="13%" align="right"><span class="required">*</span>住址：</td>
				<td width="18%" align="left"><input type="text" id="address"
					name="expert.eaddress"></td>

				<td width="13%" align="right">联系电话：</td>
				<td width="18%" align="left"><input type="text" id="phone"
					name="expert.phone"></td>
			</tr>
			<tr>
				<td width="13%" align="right" height="28">单位电话：</td>
				<td width="18%" align="left"><input type="text" id="workphone"
					name="expert.workphone"></td>

				<td width="13%" align="right">所学专业：</td>
				<td width="18%" align="left"><input type="text" id="studymajor"
					name="expert.studymajor"></td>

				<td width="13%" align="right">QQ：</td>
				<td width="18%" align="left"><input type="text" id="qq"
					name="expert.qq"></td>
			</tr>
			<tr>
				<td width="13%" align="right">职务：</td>
				<td width="18%" align="left"><input type="text" id="duty"
					name="fexpert.duty"></td>
				<td width="13%" align="right" >组别：</td>
				<td width="18%" align="left"><input type="text" id="group"
					name="fexpert.group"></td></td>
				<td width="13%" align="right" height="28">委员会名称：</td>
				<td width="18%" align="left"><input type="text" id="committee"
					name="fexpert.committee"></td>
			</tr>
			<tr>
				<td width="13%" align="right">邮箱：</td>
				<td width="18%" align="left"><input type="text" id="email"
					name="expert.email"></td>
				<td width="13%" align="right" >备注：</td>
				<td width="18%" align="left"><textarea rows="3" cols="16"
						name="expert.remark" id="remark"></textarea></td>
			</tr>
		</table>
		</form>
		<div style="margin-top: 20px">
			<div style="margin-left: 150px; float:left">
				<input type="button" class="submit_2" value="提交" name="button_submit" onclick="dosubmit()" />
			</div>
			 
		</div>
			<!-- <tr height="75">
				<td colspan="2"></td>
				<td align="center"><input type="button" class="submit_2"
					value="提交" name="button_submit" onclick="dosubmit()" /></td>
				<td align="center"><input type="button" class="submit_2"
					value="重置" onclick="doreset()" name="button_reset" /></td>
			</tr> -->
	</div>
</body>
</html>
