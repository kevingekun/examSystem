<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%

String sjid = request.getParameter("sjid");
String tkid = request.getParameter("tkid");
String sjmc = request.getParameter("sjmc");
if(sjmc!=null){
	session.setAttribute("session_sjmc_written", sjmc);
	sjmc = new String(sjmc.getBytes("ISO-8859-1"),"utf-8");
	session.setAttribute("sjid", sjid);
	session.setAttribute("tkid", tkid);
}
String session_sjmc = (String)session.getAttribute("session_sjmc_written");
if(session_sjmc!=null&&!"".equals(session_sjmc)){
	session_sjmc = new String(session_sjmc.getBytes("ISO-8859-1"),"utf-8");
}
String session_sjid = (String)session.getAttribute("sjid");
String session_tkid = (String)session.getAttribute("tkid");
Integer stlx = (Integer)session.getAttribute("stlx_written");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>笔答试题录入</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/imgUploadPreview/uploadPreview.js"></script>
<style type="text/css">
.img_xx{
	height: 40px;
}
.checkoption{
	width:200px;
}
.row-a{
	margin-top:10px;
}
</style>

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
<body>
<div class="container-fluid">
	<div class="panel panel-info">
		<div class="panel-heading">
			<%
				if(!"".equals(session_sjmc)&&session_sjmc!=null){
					out.print("<span class=\"label label-success\">"+session_sjmc+"_机考</span>");
				}else{
					out.print("<span class=\"label label-warning\">请选择试卷</span>");
				}
			%>
		</div>
		<div class="panel-body">
			<form name="addform" action="<%=request.getContextPath()%>/QuestionServlet" method="post" enctype="multipart/form-data">
				<input type=hidden name="actionType" value="add" />
				<input type=hidden name="sjType" value="written" /><!-- 笔答试卷 -->
				<div class="row form-inline">
					<div class="col-md-4 form-group">
						<label for="ST_LXID">试题类型</label>
		    			<select class="form-control input-sm" name="ST_LXID" id="ST_LXID" onChange="changeDiv(this.options[selectedIndex].value)" style="width: 160px">
							<option value=0>请选择</option>
							<option value=2>单选题</option>
							<option value=8>多选题</option>
							<option value=3>判断题</option>
						</select>
		    			<font color="red">*</font>
					</div>
					<div class="col-md-4 form-group" style="width:200px;"></div>
					<div class="col-md-4 form-group" style="position: relative;z-index:100;">
						<div class="thumbnail form-group" style="position:absolute;width:300px;margin-top:-15px;">
							<img id="imgPv" src="<%=request.getContextPath()%>/images/default/imgPv.png" />
							<div class="caption form-group">
								<label for="img">图片：</label>
				    			<input class="form-control input-sm" name="img" id="img" type="file" style="width:220px">
							</div>
						</div>
					</div>
				</div>
				<div class="row form-inline row-a">
					<div class="col-md-4 form-group">
						<label for="ST_FZ">本题分值</label>
						<input class="form-control input-sm" name="ST_FZ" id="ST_FZ" type="number" value="1">
						<font color="red">*</font>
					</div>
				</div>
				<div class="row form-inline row-a">
					<div class="col-md-4 form-group">
						<label for="ST_FJLJ">题目备注</label>
						<input class="form-control input-sm" name="ST_FJLJ" id="ST_FJLJ" type="text" >
						<input id="sjid" name="sjid" type="hidden" />
						<input id="ST_ZYXID" name="ST_ZYXID" type="hidden" />
						<input id="ST_NODE_NAME" name="ST_NODE_NAME" type="hidden" />
					</div>
				</div>
				<div class="row form-inline row-a">
					<div class="col-md-4 form-group">
						<label for="ST_TG1">试题题干</label>
						<textarea class="form-control" id="ST_TG1" name="ST_TG" style="width:350px;" rows="6"></textarea>
						<font color="red">*</font>
					</div>
				</div>
				<div id="div1" style="margin-top:10px;display:none">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60">
											<label class="radio-inline">
												<input type="radio" name="singleoptionkey" value="A"> A
											</label>
										<td><textarea class="form-control" id="singleoption" name="singleoption" rows="2" ></textarea></td>
										<td>
											<input class="form-control input-sm input-img" name="ST_IMG_A" id="ST_IMG_A" type="file" onchange="fileChange(this)" title="选择图片">
										</td>
										<td>
											<img id="imgPvA" class="img_xx" />
										</td>
									</tr>
								</table>
							</td>	
						</tr>
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60">
											<label class="radio-inline">
												<input type="radio" name="singleoptionkey" value="B"> B
											</label>
										</td>
										<td><textarea class="form-control" id="singleoption" name="singleoption" rows="2"></textarea></td>
										<td>
											<input class="form-control input-sm input-img" name="ST_IMG_B" id="ST_IMG_B" type="file" onchange="fileChange(this)" title="选择图片">
										</td>
										<td>
											<img id="imgPvB" class="img_xx" />
										</td>
									</tr>
								</table>
							</td>	
						</tr>
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60">
											<label class="radio-inline">
												<input type="radio" name="singleoptionkey" value="C"> C
											</label>
										</td>
										<td><textarea class="form-control" id="singleoption" name="singleoption" rows="2"></textarea></td>
										<td>
											<input class="form-control input-sm input-img" name="ST_IMG_C" id="ST_IMG_C" type="file" onchange="fileChange(this)" title="选择图片">
										</td>
										<td>
											<img id="imgPvC" class="img_xx" />
										</td>
									</tr>
								</table>
							</td>	
						</tr>
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60">
											<label class="radio-inline">
												<input type="radio" name="singleoptionkey" value="D"> D
											</label>
										</td>
										<td><textarea class="form-control" id="singleoption" name="singleoption" rows="2"></textarea></td>
										<td>
											<input class="form-control input-sm input-img" name="ST_IMG_D" id="ST_IMG_D" type="file" onchange="fileChange(this)" title="选择图片">
										</td>
										<td>
											<img id="imgPvD" class="img_xx" />
										</td>
										<td>
											<input class="btn btn-success btn-sm" type="button" onclick="addLine(this,1)" value="增加"/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<div id="div2" style="margin-top:10px;display:none">
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60">
											<label class="checkbox-inline">
												<input type="checkbox" name="manyoptionkey" value="A"> A
											</label>
										</td>
										<td><textarea name="manyoption" id="manyoption1" class="form-control manyoptionkey" rows="2"></textarea></td>
										<td>
										<input class="form-control input-sm input-img" name="ST_IMG_A" id="ST_IMG_Amany" type="file" onchange='fileChange(this)' title="选择图片">
									</td>
									<td>
										<img id="imgPvAmany" class="img_xx" />
									</td>
									</tr>
								</table>
						</tr>
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60">
											<label class="checkbox-inline">
												<input type="checkbox" name="manyoptionkey" value="B"> B
											</label>
										</td>
										<td><textarea name="manyoption" id="manyoption2" class="form-control manyoptionkey" rows="2"></textarea></td>
										<td>
											<input class="form-control input-sm input-img" name="ST_IMG_B" id="ST_IMG_Bmany" type="file" onchange='fileChange(this)' title="选择图片">
										</td>
										<td>
											<img id="imgPvBmany" class="img_xx" />
										</td>
									</tr>
								</table>
								</td>
						</tr>
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60">
											<label class="checkbox-inline">
												<input type="checkbox" name="manyoptionkey" value="C"> C
											</label>
										</td>
										<td><textarea name="manyoption" id="manyoption3" class="form-control manyoptionkey" rows="2"></textarea></td>
										<td>
											<input class="form-control input-sm input-img" name="ST_IMG_C" id="ST_IMG_Cmany" type="file" onchange='fileChange(this)' title="选择图片">
										</td>
										<td>
											<img id="imgPvCmany" class="img_xx" />
										</td>
									</tr>
								</table>
								</td>
						</tr>
						<tr>
							<td>
								<table border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60">
											<label class="checkbox-inline">
												<input type="checkbox" name="manyoptionkey" value="D"> D
											</label>
										</td>
										<td><textarea name="manyoption" id="manyoption4" class="form-control manyoptionkey" rows="2"></textarea></td>
										<td>
											<input class="form-control input-sm input-img" name="ST_IMG_D" id="ST_IMG_Dmany" type="file" onchange='fileChange(this)' title="选择图片">
										</td>
										<td>
											<img id="imgPvDmany" class="img_xx" />
										</td>
										<td>
											<input class="btn btn-success btn-sm" type="button" onclick="addLine(this,2)" value="增加"/>
										</td>
									</tr>
								</table>
								</td>
						</tr>
					</table>
				</div>
				<div id="div3" style="margin-top:10px;display:none">
					<label class="radio-inline">
						<input type="radio" name="verdictkey" value="T"> 对
					</label>
					<label class="radio-inline">
						<input type="radio" name="verdictkey" value="F"> 错
					</label>
				</div>
				<div class="row" style="margin-top:8px;">
					<div class="col-md-4" style="margin-left:200px;float:left;">
						<button name="add" type="button" class="btn btn-success" onclick="jvavscript:checkSubmit();" >确定</button>
					</div>
					<div class="col-md-4" style="margin-left:100px;float:left;">
						<button name="res" type="reset" class="btn btn-warning">重置</button>
					</div>
				</div>
			</form>
		</div>
<script type="text/javascript">
var sjid = "<%=sjid%>";
var tkid = "<%=tkid%>";
var stlx = "<%=stlx%>";
$(function(){
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
				objCell.innerHTML="<label class=\"radio-inline\"><input type='radio' name='singleoptionkey' value='"+tm+"'>"+tm+"</label>";
				
				objCell=objRow.insertCell(1);
				objCell.innerHTML=objSourceRow.cells[1].innerHTML;
				
				objCell=objRow.insertCell(2);
				objCell.innerHTML="<input class='form-control input-sm input-img' name='ST_IMG_"+tm+"' id='ST_IMG_"+tm+"' type='file' onchange='fileChange(this)' title='选择图片'>";
				
				objCell=objRow.insertCell(3);
				objCell.innerHTML="<img id='imgPv"+tm+"' style='height: 40px'/>";
			}
			if(num==2){
				objCell.innerHTML="<label  class=\"checkbox-inline\"><input type='checkbox' name='manyoptionkey' value='"+tm+"'>"+tm+"</label>";
				
				objCell=objRow.insertCell(1);
				objCell.innerHTML=objSourceRow.cells[1].innerHTML;
				
				objCell=objRow.insertCell(2);
				objCell.innerHTML="<input class=\"form-control input-sm input-img\" name='ST_IMG_"+tm+"' id='ST_IMG_"+tm+"many' type='file' onchange='fileChange(this)' title='选择图片'>";
				
				objCell=objRow.insertCell(3);
				objCell.innerHTML="<img id='imgPv"+tm+"many' style='height: 40px'/>";
			}
			objCell=objRow.insertCell(4);
			/* objCell.innerHTML=objSourceRow.cells[4].innerHTML.replace("btn-success",'btn-danger'); */
			objCell.innerHTML=objSourceRow.cells[4].innerHTML.replace("增加",'删除');
			objCell.innerHTML=objCell.innerHTML.replace("btn-success",'btn-danger');
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
					/* div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";
					div9.style.display="none";					
					div10.style.display="none";	
					div11.style.display="none";					
					div12.style.display="none";	
					div13.style.display="none";				 */
					break;			
				case "2"://单选
					div1.style.display="block";
					div2.style.display="none";					
					div3.style.display="none";
					/* div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";	
					div9.style.display="none";					
					div10.style.display="none";	
					div11.style.display="none";					
					div12.style.display="none";
					div13.style.display="none";	 */						
					break;
				case "8"://多选
					div1.style.display="none";
					div2.style.display="block";					
					div3.style.display="none";
					/* div4.style.display="none";	
					div5.style.display="none";
					div6.style.display="none";
					div7.style.display="none";					
					div8.style.display="none";	
					div9.style.display="none";					
					div10.style.display="none";
					div11.style.display="none";					
					div12.style.display="none";
					div13.style.display="none"; */							
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
</body>
</html>
