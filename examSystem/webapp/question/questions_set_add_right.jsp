<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@page import="com.wondersgroup.kaoshi.bo.EPapersSet"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
ProfessionBean professionBean = new ProfessionBean();
String sjid = request.getParameter("sjid");
String tkid = request.getParameter("tkid");
String sjmc = "";
if(sjid!=null&&!"".equals(sjid)){
	EPapersSet e = professionBean.getPapersBySjid(sjid);
	sjmc = e.getSj_mc();
	session.setAttribute("session_sjmc_cpt", sjmc);
	session.setAttribute("sjid", sjid);
	session.setAttribute("tkid", tkid);
}
String session_sjmc = (String)session.getAttribute("session_sjmc_cpt");
String session_sjid = (String)session.getAttribute("sjid");
String session_tkid = (String)session.getAttribute("tkid");
Integer stlx = (Integer)session.getAttribute("stlx_cpt");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>试题新增</title>
<script type="text/javascript" src="js/jquery/autoresize.min.js"></script>
<link rel="stylesheet" type="text/css" href="newcss/style.css" />
<link rel="stylesheet" type="text/css" href="inc/all.css" />
<link rel="StyleSheet" type="text/css" href="authority/js/dtree.css" />
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/imgUploadPreview/uploadPreview.js"></script>
<script type="text/javascript" src="js/util/numeral.js"></script>
<style type="text/css">
.img_xx{
	height: 40px;
}
.checkoption{
	width:200px;
}
</style>
<script type="text/javascript">
var sjid = "<%=sjid%>";
var tkid = "<%=tkid%>";
var stlx = "<%=stlx%>";
$(function(){
	//文本框只能输入数字，并屏蔽输入法和粘贴  
	$("#ST_FZ").numeral();
	
	if(sjid==""||sjid=="null"){
		sjid="<%=session_sjid%>";
	}
	if(tkid==""||tkid=="null"){
		tkid="<%=session_tkid%>";
	}
	$("#sjid").val(sjid);
	$("#ST_NODE_NAME").val(tkid);
	$(".selector").val(stlx);
	changeDiv(stlx);
});
</script>
<script type="text/javascript">
var rowIndex=0;
function addLine(obj,num){
	var objSourceRow=obj.parentNode.parentNode;
	var objTable=obj.parentNode.parentNode.parentNode.parentNode;
	if(obj.value=='增加'){
		if(rowIndex<3){
			rowIndex++;
			var tm='E';
			if(rowIndex==1){
				var tm='E';
			}
		    if (rowIndex==2){
				var tm='F';
			}
			if (rowIndex==3){
				var tm='G';
			}	
			/* if (rowIndex==4){
				var tm='H';
			}	
			if (rowIndex==5){
				var tm='I';
			}	
			if (rowIndex==6){
				var tm='J';
			}	 */					
			
			
			var objRow=objTable.insertRow(rowIndex);
			var objCell;
			objCell=objRow.insertCell(0);
			if(num==1){
				objCell.innerHTML="<input type=radio name='singleoptionkey' value='"+tm+"'>"+tm+":";
				
				objCell=objRow.insertCell(1);
				objCell.innerHTML=objSourceRow.cells[1].innerHTML;
				
				objCell=objRow.insertCell(2);
				objCell.innerHTML="<input name='ST_IMG_"+tm+"' id='ST_IMG_"+tm+"' type='file' onchange='fileChange(this)' title='选择图片'>";
				
				objCell=objRow.insertCell(3);
				objCell.innerHTML="<img id='imgPv"+tm+"' style='height: 40px'/>";
			}
			if(num==2){
				objCell.innerHTML="<input type=checkbox name='manyoptionkey' value='"+tm+"'>"+tm+":";
				
				objCell=objRow.insertCell(1);
				objCell.innerHTML=objSourceRow.cells[1].innerHTML;
				
				objCell=objRow.insertCell(2);
				objCell.innerHTML="<input name='ST_IMG_"+tm+"' id='ST_IMG_"+tm+"many' type='file' onchange='fileChange(this)' title='选择图片'>";
				
				objCell=objRow.insertCell(3);
				objCell.innerHTML="<img id='imgPv"+tm+"many' style='height: 40px'/>";
			}
			
			
			objCell=objRow.insertCell(4);
			objCell.innerHTML=objSourceRow.cells[4].innerHTML.replace(/增加/,'删除');
		}else{
			alert("超出系统支持数量。");
		}
	}
	else{
		objTable.lastChild.removeChild(objSourceRow);
		rowIndex--;
	} 
}
</script>
<script language="javascript">	
		function checkSubmit(){
			if($("#sjid").val()=="null"){
				alert("请选择试题所属试卷！")
				return ;
			}
			if(document.all.ST_LXID.value=="0"){
				alert("请选择试题题型！");
				return ;
			}
			if(document.all.ST_FZ.value==""){
				alert("请填写试题分值！");
				return ;
			}else{
				if($.trim($("#ST_TG1").val())==""){
					alert("题目不能为空！");
					return;
				}
				//判断试题的答案
				if(document.all.ST_LXID.value=='2'){
					var daan=document.all.singleoptionkey;
					var hastrue=false;
					for(var i=0;i<daan.length;i++){
						if(daan[i].checked){
							hastrue=true;
							break;
						}
					}
					if(!hastrue){
						alert("请选择答案！");
						return ;
					}
					
				}else if(document.all.ST_LXID.value=='8'){
					var daan=document.all.manyoptionkey;
					var hastrue=false;
					for(var i=0;i<daan.length;i++){
						if(daan[i].checked){
							hastrue=true;
							break;
						}
					}
					if(!hastrue){
						alert("请选择答案！");
						return ;
					}
				}else if(document.all.ST_LXID.value=='3'){
					var daan=document.all.verdictkey;
					var hastrue=false;
					for(var i=0;i<daan.length;i++){
						if(daan[i].checked){
							hastrue=true;
							break;
						}
					}
					if(!hastrue){
						alert("请选择答案！");
						return ;
					}
				}else if(document.all.ST_LXID.value=='22'){
					var daan=document.all.verdictsaykey;
					var xxx="";
					var hastrue=false;
					for(var i=0;i<daan.length;i++){
						if(daan[i].checked){
							hastrue=true;
							xxx=daan[i].value;
							break;
						}
					}
					if(!hastrue){
						alert("请选择答案！");
						return ;
					}
					var daan2=document.all.verdictsay.value;
					if(daan2.length==0&&xxx=='F'){
						alert("请输入判断说明");
						return ;
					}
				}else if(document.all.ST_LXID.value=='23'){
					
					var daan2=document.all.recordkey.value;
					if(daan2.length==0){
						alert("请输入答案");
						return ;
					}
					var recordpath=document.all.recordpath.value;
					//alert(recordpath);
					if(recordpath.length==0){
						alert("请选择录音！");
						return ;
					}
					var recordLuyinId=document.all.recordLuyinId.value;
					//alert(recordLuyinId);
					if(recordLuyinId.length==0){
						alert("录音选择错误！请重新选择");
						return ;
					}
				}else if(document.all.ST_LXID.value=='1'){
					
					var daan2=document.all.fillkey.value;
					if(daan2.length==0){
						alert("请输入答案");
						return ;
					}
				}else if(document.all.ST_LXID.value=='4'){
					var daan2=document.all.askkey.value;
					if(daan2.length==0){
						alert("请输入答案");
						return ;
					}
				}else if(document.all.ST_LXID.value=='24'){
					var daan2=document.all.casekey.value;
					if(daan2.length==0){
						alert("请输入答案");
						return ;
					}
				}else if(document.all.ST_LXID.value=='5'){
					var daan2=document.all.calculatekey.value;
					if(daan2.length==0){
						alert("请输入答案");
						return ;
					}
				}else if(document.all.ST_LXID.value=='10'){
					var daan=document.all.uncertainoptionkey;
					var hastrue=false;
					for(var i=0;i<daan.length;i++){
						if(daan[i].checked){
							hastrue=true;
							break;
						}
					}
					if(!hastrue){
						alert("请选择答案！");
						return ;
					}
				}else if(document.all.ST_LXID.value=='6'){
					var daan2=document.all.discusskey.value;
					if(daan2.length==0){
						alert("请输入答案");
						return ;
					}
				}else if(document.all.ST_LXID.value=='12'){
					var daan2=document.all.databasekey.value;
					if(daan2.length==0){
						alert("请输入答案");
						return ;
					}
				}
				
			}
			
			var filepath=$("input[name='img']").val();
			if(filepath!=""){
				var extStart=filepath.lastIndexOf(".");
		        var ext=filepath.substring(extStart,filepath.length).toUpperCase();
		        if(ext!=".PNG"&&ext!=".JPG"&&ext!=".JPEG"){
		         	alert("图片限于png,jpeg,jpg格式");
		         	return ;
		        }
		        var size;//byte
		        if ($.browser.msie) {//ie旧版浏览器
	                var fileMgr = new ActiveXObject("Scripting.FileSystemObject");
	                 var filePath = $("#img")[0].value;
	                 var fileObj = fileMgr.getFile(filePath);
	                 size = fileObj.size; //byte
	                 size = size / 1024;//kb
	                 //size = size / 1024;//mb
	            } else {//其它浏览器
	                size = $("#img")[0].files[0].size;//byte
	                 size = size / 1024;//kb
	                 //size = size / 1024;//mb
	            }
		        if(size>100){
		        	alert("上传图片大小不能超过100KB");
		        	return ;
		        }
			}
			document.addform.submit();
			$("input[name='add']").attr("disabled",true);
		}
		
		function changeDiv(sv){
			switch(sv){
				case "0":
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";
					div9.style.display="none";					
					div10.style.display="none";	
					div11.style.display="none";					
					div12.style.display="none";	
					div13.style.display="none";				
					break;			
				case "2"://单选
					div1.style.display="block";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";	
					div9.style.display="none";					
					div10.style.display="none";	
					div11.style.display="none";					
					div12.style.display="none";
					div13.style.display="none";							
					break;
				case "8"://多选
					div1.style.display="none";
					div2.style.display="block";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";	
					div9.style.display="none";					
					div10.style.display="none";
					div11.style.display="none";					
					div12.style.display="none";
					div13.style.display="none";							
					break;
				case "3"://判断题
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="block";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";
					div9.style.display="none";					
					div10.style.display="none";
					div11.style.display="none";					
					div12.style.display="none";
					div13.style.display="none";								
					break;
				case "11"://判断说明题
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="block";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";
					div9.style.display="none";					
					div10.style.display="none";
					div11.style.display="none";					
					div12.style.display="none";	
					div13.style.display="none";							
					break;
				case "12"://录音题
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="block";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";
					div9.style.display="none";					
					div10.style.display="none";	
					div11.style.display="none";					
					div12.style.display="none";	
					div13.style.display="none";								
					break;
				case "1"://填空题
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="block";
					div7.style.display="none";					
					div8.style.display="none";
					div9.style.display="none";					
					div10.style.display="none";
					div11.style.display="none";					
					div12.style.display="none";	
					div13.style.display="none";									
					break;					
				case "4"://问答题
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="block";					
					div8.style.display="none";	
					div9.style.display="none";					
					div10.style.display="none";
					div11.style.display="none";					
					div12.style.display="none";	
					div13.style.display="none";							
					break;					
				case "13"://案例分析题
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="block";	
					div9.style.display="none";					
					div10.style.display="none";
					div11.style.display="none";					
					div12.style.display="none";
					div13.style.display="none";								
					break;	
				case "5"://计算题
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";	
					div9.style.display="block";					
					div10.style.display="none";
					div11.style.display="none";					
					div12.style.display="none";
					div13.style.display="none";									
					break;	
				case "18"://不定项选择题
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";	
					div9.style.display="none";					
					div10.style.display="block";
					div11.style.display="none";					
					div12.style.display="none";	
					div13.style.display="none";								
					break;	
				case "6"://论述题
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";	
					div9.style.display="none";					
					div10.style.display="none";	
					div11.style.display="block";					
					div12.style.display="none";
					div13.style.display="none";								
					break;	
				case "15"://点库题
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";	
					div9.style.display="none";					
					div10.style.display="none";
					div11.style.display="none";					
					div12.style.display="block";
					div13.style.display="none";								
					break;	
				case "16"://拨测题
					div1.style.display="none";
					div2.style.display="none";					
					div3.style.display="none";
					div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";	
					div9.style.display="none";					
					div10.style.display="none";
					div11.style.display="none";					
					div12.style.display="none";
					div13.style.display="block";								
					break;			
			}
		}
	function showluyinti(){
		window.open('<%=request.getContextPath()%>/question/Luyin_list.jsp','录音题列表','height=600, width=800, top=100, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=yes, status=yes');
	}
	function getluyintiValue(id,url){
		document.getElementById("recordLuyinId").value=id;
		document.getElementById("recordpath").value=url;
	}
	
	function aaaaa(){
		var url='<%=request.getContextPath()%>/wenhao_view.action';
		window.open(url,'文号选择','height=600, width=1000, top=100, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=yes, status=yes');
	}
	
	function fuzhi(st_wh,st_cc,wh_id,type){
		var tempstwh=document.getElementById("ST_WH").value;
		var tempstcc=document.getElementById("ST_CC").value;
		var tempwhid=document.getElementById("WH_ID").value;
		var temptype=document.getElementById("WH_TYPE").value;
		document.getElementById("ST_WH").value=tempstwh+st_wh;
		document.getElementById("ST_CC").value=tempstcc+st_cc;
		document.getElementById("WH_ID").value=tempwhid+wh_id;
		document.getElementById("WH_TYPE").value=temptype+type;
		
	}
	$(function () {
		$("#img").uploadPreview({ Img: "imgPv", Width: 300, Height: 200 });
		$("#ST_IMG_A").uploadPreview({ Img: "imgPvA", Width: 300, Height: 40 });
		$("#ST_IMG_B").uploadPreview({ Img: "imgPvB", Width: 300, Height: 40 });
		$("#ST_IMG_C").uploadPreview({ Img: "imgPvC", Width: 300, Height: 40 });
		$("#ST_IMG_D").uploadPreview({ Img: "imgPvD", Width: 300, Height: 40 });
		$("#ST_IMG_E").uploadPreview({ Img: "imgPvE", Width: 300, Height: 40 });
		$("#ST_IMG_F").uploadPreview({ Img: "imgPvF", Width: 300, Height: 40 });
		$("#ST_IMG_G").uploadPreview({ Img: "imgPvG", Width: 300, Height: 40 });
		
		$("#ST_IMG_Amany").uploadPreview({ Img: "imgPvAmany", Width: 300, Height: 40 });
		$("#ST_IMG_Bmany").uploadPreview({ Img: "imgPvBmany", Width: 300, Height: 40 });
		$("#ST_IMG_Cmany").uploadPreview({ Img: "imgPvCmany", Width: 300, Height: 40 });
		$("#ST_IMG_Dmany").uploadPreview({ Img: "imgPvDmany", Width: 300, Height: 40 });
		$("#ST_IMG_Emany").uploadPreview({ Img: "imgPvEmany", Width: 300, Height: 40 });
		$("#ST_IMG_Fmany").uploadPreview({ Img: "imgPvFmany", Width: 300, Height: 40 });
		$("#ST_IMG_Gmany").uploadPreview({ Img: "imgPvGmany", Width: 300, Height: 40 });
	});
	function fileChange(v){
		var file = v.value;
		if(file!=''){
			$("textarea[name='singleoption']").val("");
			$("textarea[name='singleoption']").attr("disabled",true);
			$("textarea[name='manyoption']").val("");
			$("textarea[name='manyoption']").attr("disabled",true);
		}else{
			$("textarea[name='singleoption']").attr("disabled",false);
			$("textarea[name='manyoption']").attr("disabled",false);
		}
	}
</script>
<script type="text/javascript">

$(function(){
	$('textarea#ST_TG1').autoResize({
    // On resize:
    onResize : function() {
        $(this).css({opacity:0.8});
    },
    // After resize:
    animateCallback : function() {
        $(this).css({opacity:1});
    },
    // Quite slow animation:
    animateDuration : 100,
    // More extra space:
    extraSpace : 20,
		limit: 150
});
})

$(function(){
	$('textarea#casekey').autoResize({
    // On resize:
    onResize : function() {
        $(this).css({opacity:0.8});
    },
    // After resize:
    animateCallback : function() {
        $(this).css({opacity:1});
    },
    // Quite slow animation:
    animateDuration : 100,
    // More extra space:
    extraSpace : 20,
	limit: 300
});
})
$(function(){
	$('textarea#verdictsay').autoResize({
    // On resize:
    onResize : function() {
        $(this).css({opacity:0.8});
    },
    // After resize:
    animateCallback : function() {
        $(this).css({opacity:1});
    },
    // Quite slow animation:
    animateDuration : 100,
    // More extra space:
    extraSpace : 20,
	limit: 300
});
})
</script>
<%
	String messge = (String) request.getAttribute("messge");
	if (messge != null) {
%>
<script language="javascript">
	alert('<%=messge%>');
</script>
<%
	}
%>
</head>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top: 10px; margin-left: 8px;">
		<tr>
			<td width="45%" align="left">
				<table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">
							<%
							if(!"".equals(session_sjmc)&&session_sjmc!=null){
								out.print(session_sjmc+"_机考");
							}else{
								out.print("请选择试卷");
							}
							%>
						</td>
						<td class="header3"></td>
					</tr>
				</table>
			</td>
			<td width="53%" align="left"></td>
		</tr>
		<tr>
			<td colspan="2" valign="top">
				<div id="content1" class="borader">
					<form name="addform" action="<%=request.getContextPath()%>/QuestionServlet" method="post" enctype="multipart/form-data">
						<input type=hidden name="actionType" value="add" />
						<input type=hidden name="sjType" value="cpt" /><!-- 上机试卷 -->
						<table width="760" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr class="row_height">
								
								<td height="26" align="right" width="10%">试题题型：</td>
								<td width="20%">
									<select class="selector" name="ST_LXID" onChange="changeDiv(this.options[selectedIndex].value)" style="width: 160px">
										<option value=0>请选择</option>
										<option value=2>单选题</option>
										<option value=8>多选题</option>
										<option value=3>判断题</option>
										<!-- <option value=1>填空题</option>
										<option value=4>问答题</option>
										<option value=5>计算题</option>
										<option value=6>论述题</option>
										<option value=7>绘图题</option> -->
									</select>
									<font color="red">*</font>
								</td>
								<td align="left">
					        		<div style="position: relative;width: 100%">
								  		<div style="width: 300px;height:200px;float:right;right:1;top:-10;position:absolute; z-index:100; ">
								  			<img id="imgPv" src="<%=request.getContextPath()%>/images/default/imgPv.png" style="height: 200px;width: 300px"/>
										</div>
									</div>
					        	</td>
							</tr>
							<tr>
								<td align="right" height="26">本题分值：</td>
								<td>
									<input name="ST_FZ" id="ST_FZ" type="text" style="width: 160px" value="1">
								<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<td align="right" height="26">备注：</td>
								<td>
									<input name="ST_FJLJ" id="ST_FJLJ" type="text" style="width: 160px">
									<input id="sjid" name="sjid" type="hidden" />
									<input id="ST_ZYXID" name="ST_ZYXID" type="hidden" />
									<input id="ST_NODE_NAME" name="ST_NODE_NAME" type="hidden" />
								</td>
							</tr>
							<tr>
								<td align="right" height="26">图片：</td>
								<td>
									<input name="img" id="img" type="file" title="选择图片" />
								</td>
							</tr>
							<tr>
								<td height="26" align="right">题目：</td>
								<td colspan="1"><textarea id="ST_TG1" name="ST_TG" style="width:300px;" rows="6"></textarea></td>
							</tr>
						</table>

						<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td height="40" align="center" valign="bottom" width="40%">
									<input name="add" type=button class="submit_2" onClick="jvavscript:checkSubmit();" value="新 增" /> &nbsp; 
								</td>
								<td height="40" align="left" valign="bottom" >
									<input name="res" type="reset" class="submit_2" value="重 置" />
								</td>
							</tr>
						</table>

						<div id="div1" style="display: none">
							<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width=40% colspan="3">&nbsp;单选题答案区：</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td width="10%" align="right">可选项：</td>
									<td width="80%" align="center">
										<table width=760 border=0 align="center" cellspacing="1" cellspadding=1>
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="60"><input type=radio name="singleoptionkey" value="A" /> A:</td>
															<td><textarea id="singleoption" name="singleoption" class="checkoption" rows="3"></textarea></td>
															<td>
																<input name="ST_IMG_A" id="ST_IMG_A" type="file" onchange="fileChange(this)" title="选择图片">
															</td>
															<td>
																<img id="imgPvA" class="img_xx"/>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td>

													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="60"><input type=radio name="singleoptionkey" value="B" /> B:</td>
															<td><textarea id="singleoption" name="singleoption" class="checkoption" rows="3"></textarea></td>
															<td>
																<input name="ST_IMG_B" id="ST_IMG_B" type="file" onchange="fileChange(this)" title="选择图片">
															</td>
															<td>
																<img id="imgPvB" class="img_xx"/>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="60"><input type=radio name="singleoptionkey" value="C" /> C:</td>
															<td><textarea id="singleoption" name="singleoption" class="checkoption" rows="3"></textarea></td>
															<td>
																<input name="ST_IMG_C" id="ST_IMG_C" type="file" onchange="fileChange(this)" title="选择图片">
															</td>
															<td>
																<img id="imgPvC" class="img_xx"/>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="60"><input type=radio name="singleoptionkey" value="D" /> D:</td>
															<td><textarea id="singleoption" name="singleoption" class="checkoption" rows="3"></textarea></td>
															<td>
																<input name="ST_IMG_D" id="ST_IMG_D" type="file" onchange="fileChange(this)" title="选择图片">
															</td>
															<td>
																<img id="imgPvD" class="img_xx"/>
															</td>
															<td><input name="add2" type="button" id="add2" value="增加" onClick="addLine(this,1)" /></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
									<td width="10%">&nbsp;</td>
								</tr>
							</table>
						</div>

						<div id="div2" style="display: none">
							<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width=40% colspan="3">&nbsp;多选题答案区：</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td width="10%" align="right">可选项：</td>
									<td width="80%" align="center">
										<table width=760 border=0 align="center" cellspacing="1" cellspadding=1>
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="60"><input type=checkbox name="manyoptionkey" value="A" /> A:</td>
															<td><textarea name="manyoption" id="manyoption1" class="checkoption" rows="3"></textarea></td>
															<td>
																<input name="ST_IMG_A" id="ST_IMG_Amany" type="file" onchange="fileChange(this)" title="选择图片">
															</td>
															<td>
																<img id="imgPvAmany" class="img_xx"/>
															</td>
														</tr>
													</table>
											</tr>
											<tr>
												<td>

													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="60"><input type=checkbox name="manyoptionkey" value="B" /> B:</td>
															<td><textarea name="manyoption" id="manyoption2" class="checkoption" rows="3"></textarea></td>
															<td>
																<input name="ST_IMG_B" id="ST_IMG_Bmany" type="file" onchange="fileChange(this)" title="选择图片">
															</td>
															<td>
																<img id="imgPvBmany" class="img_xx"/>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="60"><input type=checkbox name="manyoptionkey" value="C" /> C:</td>
															<td><textarea name="manyoption" id="manyoption3" class="checkoption" rows="3"></textarea></td>
															<td>
																<input name="ST_IMG_C" id="ST_IMG_Cmany" type="file" onchange="fileChange(this)" title="选择图片">
															</td>
															<td>
																<img id="imgPvCmany" class="img_xx"/>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="60"><input type=checkbox name="manyoptionkey" value="D" /> D:</td>
															<td><textarea name="manyoption" id="manyoption4" class="checkoption" rows="3"></textarea></td>
															<td>
																<input name="ST_IMG_D" id="ST_IMG_Dmany" type="file" onchange="fileChange(this)" title="选择图片">
															</td>
															<td>
																<img id="imgPvDmany" class="img_xx"/>
															</td>
															<td><input name="add2" type="button" id="add2" value="增加" onClick="addLine(this,2)" /></td>
														</tr>
													</table>
												</td>
											</tr>

										</table>
									</td>
								</tr>


							</table>
						</div>


						<div id="div3" style="display: none">
							<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width=40% colspan="3">&nbsp;判断题答案区：</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td width="10%" align="right">判断选项：</td>
									<td width="80%" align="center">
										<table width=760 border=0 align="center" cellspacing="1" cellspadding=1>
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="60"><input type=radio name="verdictkey" value="T" /> 对:</td>
															<td><input type=radio name="verdictkey" value="F" /> 错</td>
															<td></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>


							</table>
						</div>
						<div id="div4" style="display: none">
							<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width=40% colspan="3">&nbsp;判断说明题答案区：</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td width="10%" align="right">判断选项：</td>
									<td width="80%" align="center">
										<table width=760 border=0 align="center" cellspacing="1" cellspadding="1">
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="60"><input type=radio name="verdictsaykey" value="T" /> 对:</td>
															<td><input type=radio name="verdictsaykey" value="F" /> 错</td>
															<td></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr bgcolor="#ffffff"> 
									<td width="10%" align="right">说明：</td>
									<td width="80%" align="center">
										<table width=760 border=0 align="center" cellspacing="1" cellspadding=1>
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="60"><textarea id="verdictsay" name="verdictsay" style="width: 750px;" rows="4"></textarea></td>
															<td>&nbsp;</td>
															<td></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>

							</table>
						</div>
						<div id="div5" style="display: none">
							<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width=40% colspan="2">&nbsp;录音题答案区：</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td width="10%" align="right">答案：</td>
									<td width="90%">
										<table width=100% border=0 cellspadding=1 cellspacing="1">
											<tr>
												<td>
													<table>
														<tr>
															<td><textarea name="recordkey" id="recordkey" style="width: 580px;" rows="5"></textarea></td>
															<td>&nbsp;</td>
															<td></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>

								<tr bgcolor="#ffffff">
									<td width="10%" align="right">录音文件：</td>
									<td width="90%">
										<table width=100% border=0 cellspadding=1 cellspacing="1">
											<tr>
												<td>
													<table>
														<tr>
															<td><input id="recordpath" readonly="readonly" type=text name="recordpath" size="80" value="" /></td>
															<td>
																<button onclick="showluyinti();">选择录音</button>
															</td>
															<td><input type="hidden" id="recordLuyinId" /></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>

						<div id="div6" style="display: none">


							<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width=40% colspan="2">&nbsp;填空题答案区：</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td width="10%" align="right">答案：</td>
									<td width="90%">
										<table width=100% border=0 cellspadding=1 cellspacing="1">
											<tr>
												<td>
													<table>
														<tr>
															<td><textarea name="fillkey" id="fillkey" style="width: 750px; height: 80px;"></textarea></td>
															<td>请以一定符号间隔。</td>
															<td></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>


							</table>
						</div>
						<div id="div7" style="display: none">

							<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width=40% colspan="2">&nbsp;问答题答案区：</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td width="10%" align="right">答案：</td>
									<td width="90%">
										<table width=100% border=0 cellspadding=1 cellspacing="1">
											<tr>
												<td>
													<table>
														<tr>
															<td><textarea name="askkey" id="askkey" style="width: 750px; height: 50px;"></textarea></td>
															<td>&nbsp;</td>
															<td></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>


							</table>


						</div>


						<div id="div8" style="display: none">

							<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width=40% colspan="2">&nbsp;案例分析题答案区：</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td width="10%" align="right">答案：</td>
									<td width="90%">
										<table width=100% border=0 cellspadding=1 cellspacing="1">
											<tr>
												<td>
													<table>
														<tr>
															<td><textarea name="casekey" id="casekey" style="width: 750px;" rows="5"></textarea></td>
															<td>&nbsp;</td>
															<td></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>


							</table>

						</div>
						<div id="div9" style="display: none">

							<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width=40% colspan="2">&nbsp;计算题答案区：</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td width="10%" align="right">答案：</td>
									<td width="90%">
										<table width=100% border=0 cellspadding=1 cellspacing="1">
											<tr>
												<td>
													<table>
														<tr>
															<td><textarea name="calculatekey" id="calculatekey" style="width: 750px;" rows="5"></textarea></td>
															<td>&nbsp;</td>
															<td></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>


							</table>

						</div>
						<div id="div10" style="display: none">
							<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width=40% colspan="3">&nbsp;不定项选择题答案区：</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td width="10%" align="right">可选项：</td>
									<td width="80%" align="center">
										<table width=760 border=0 align="center" cellspacing="1" cellspadding=1>
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="60"><input type=checkbox name="uncertainoptionkey" value="A" /> A:</td>
															<td><textarea name="uncertainoption" id="uncertainoption1" style="width:580px;" rows="3"></textarea></td>
															<td></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td>

													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="60"><input type=checkbox name="uncertainoptionkey" value="B" /> B:</td>
															<td><textarea name="uncertainoption" id="uncertainoption2" style="width:580px;" rows="3"></textarea></td>
															<td></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="60"><input type=checkbox name="uncertainoptionkey" value="C" /> C:</td>
															<td><textarea name="uncertainoption" id="uncertainoption3" style="width:580px;" rows="3"></textarea></td>
															<td></td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td>
													<table border="0" cellpadding="0" cellspacing="0">
														<tr>
															<td width="60"><input type=checkbox name="uncertainoptionkey" value="D" /> D:</td>
															<td><textarea name="uncertainoption" id="uncertainoption4" style="width:580px;" rows="3"></textarea></td>
															<td><input name="add2" type="button" id="add2" value="增加" onClick="addLine(this,2)" /></td>
														</tr>
													</table>
												</td>
											</tr>

										</table>
									</td>
								</tr>


							</table>
						</div>
						<div id="div11" style="display: none">
							<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width=40% colspan="2">&nbsp;论述题答案区：</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td width="10%" align="right">答案：</td>
									<td width="90%">
										<table width=100% border=0 cellspadding=1 cellspacing="1">
											<tr>
												<td>
													<table>
														<tr>
															<td><textarea name="discusskey" id="discusskey" style="width: 750px; height: 50px;"></textarea></td>
															<td>&nbsp;</td>
															<td></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
						<div id="div12" style="display: none">
							<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width=40% colspan="2">&nbsp;点库题答案区：</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td width="10%" align="right">答案：</td>
									<td width="90%">
										<table width=100% border=0 cellspadding=1 cellspacing="1">
											<tr>
												<td>
													<table>
														<tr>
															<td><textarea name="databasekey" id="databasekey" style="width: 750px; height: 50px;"></textarea></td>
															<td>&nbsp;</td>
															<td></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
						<div id="div13" style="display: none">
							<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
								<tr>
									<td width=40% colspan="2">&nbsp;拨测题答案区：</td>
								</tr>
								<tr bgcolor="#ffffff">
									<td width="10%" align="right">答案：</td>
									<td width="90%">
										<table width=100% border=0 cellspadding=1 cellspacing="1">
											<tr>
												<td>
													<table>
														<tr>
															<td><textarea name="telephonekey" id="telephonekey" style="width: 750px; height: 50px;"></textarea></td>
															<td>&nbsp;</td>
															<td></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</form>
				</div>

			</td>
		</tr>
	</table>

</body>
</html>
