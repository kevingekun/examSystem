<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page import="java.util.*,com.wondersgroup.falcon.question.model.*,com.wondersgroup.falcon.Util.*"%>
<%@ page import="com.wondersgroup.falcon.question.beans.*"%>
<%@ page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@ page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%
	EBusinesstypeService  eBusinesstypeService =new EBusinesstypeService(); 
EImportanceService eImportanceService =new EImportanceService();
EQuestiontypeService eQuestiontypeService = new EQuestiontypeService();
List businesstypelist=eBusinesstypeService.findEBusinesstypeAll();
List importiontypelist=eImportanceService.findEImportanceAll();
List questiontypelist=eQuestiontypeService.findEQuestiontypeAll();
request.setAttribute("businesstypelist",businesstypelist);
request.setAttribute("importiontypelist",importiontypelist);
request.setAttribute("questiontypelist",questiontypelist);

ProfessionBean professionBean = new ProfessionBean();//工种
List<Tjobsubject> professions = professionBean.getDistinctProfessions();
request.setAttribute("professions",professions);

String s = request.getParameter("teamId");
List<Object> list_dj = professionBean.getDjById_job(s);//通过工种id获取对应等级
%>
<HTML>
<HEAD>
<TITLE>题目修改</TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<%-- <script src="<%=request.getContextPath() %>/js/customer.js"></script> --%>
<link href="<%=request.getContextPath()%>/newcss/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery/jquery.1.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/imgUploadPreview/uploadPreview.js"></script>
</HEAD>
<style type="text/css">
.img_xx{
	height: 40px;
}
.imgPv{
	width: 300px;
	height: 200px;
}
</style>
<script language="JavaScript">
var rowIndex=0;
function addLine(obj,num){
	var objSourceRow=obj.parentNode.parentNode;
	var objTable=obj.parentNode.parentNode.parentNode.parentNode;
	
	if(obj.value=='增加'){		
		if(rowIndex<6){
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
			if (rowIndex==4){
				var tm='H';
			}	
			if (rowIndex==5){
				var tm='I';
			}	
			if (rowIndex==6){
				var tm='J';
			}						
			var objRow=objTable.insertRow(rowIndex);
			var objCell;
			objCell=objRow.insertCell(0);
			if(num==1){
			objCell.innerHTML="<input type=radio name='singleoptionkey' value='"+tm+"'> "+tm+":";;
			}
			if(num==2){
			objCell.innerHTML="<input type=checkbox name='manyoptionkey' value='"+tm+"'> "+tm+":";;
			}
			objCell=objRow.insertCell(1);
			objCell.innerHTML=objSourceRow.cells[1].innerHTML;
	
			objCell=objRow.insertCell(2);
			objCell.innerHTML=objSourceRow.cells[2].innerHTML.replace(/增加/,'删除');
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
		function showSubData(){
			var k = window.showModalDialog("exam_newsubwindow.jsp",window,"");
			if(k!=null){
				add_realquesname.value = k.value;
				add_realquesid.value = k.id;
			}
		}		
		function showKeyData(type,fig){
			var k = window.showModalDialog("exam_newkeywindow.jsp",window,"");
			switch(type){
				case 1:
					document.getElementById("add_ockeyname"+fig).value=k.value;
					document.getElementById("add_ockeyid"+fig).value=k.id;
					break;
				case 2:
					document.getElementById("add_mckeyname"+fig).value=k.value;
					document.getElementById("add_mckeyid"+fig).value=k.id;
					break;					
			}
		}
		function query(){			
			document.all.myaction.value="";	
			document.addform.submit();
		}	
				
		function checkSubmit(){
			if(document.all.gzid.value==""){
				alert("请选择工种");
				return ;
			}
			
			if(document.all.gzdj.value==""){
				alert("请选择等级！");
				return ;
			}
			if(document.all.ST_LXID.value=="0"){
				alert("请选择试题类型");
				return ;
			}	
			/* if(document.all.ST_ZYXID.value=="0"){
				alert("请选择难易度");
				return ;
			} */
			/* if(document.all.ST_CC.value==""){
				alert("请填写试题来源");
				return ;
			} */
			/* if(document.all.ST_TG.innerText==""){
				alert("题干不能为空！");
				return ;
			} */
			if(document.getElementById("ST_TG1").value==""){
				alert("题目不能为空！");
				return ;
			}
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
					break;
				case "14"://判断说明题
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
					break;
				case "5"://录音题
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
					break;					
				case "18"://案例分析题
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
					break;	
				case "110"://不定项选择题
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
					break;	
				case "12"://点库题
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
					break;	
				case "13"://拨测题
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
	function aaaaa(){
		window.open('<%=request.getContextPath()%>/wenhao_view.action','文号选择','height=600, width=1000, top=100, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=yes, status=yes');
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

	function stdel(){
	    if(confirm("确定要删除吗？")){
	    	document.addform.action="QuestionServlet";
			document.addform.myaction.value="del";
			document.addform.submit();
	    }else{
	    	return false;
	    }
		
	}
	function checkDj(v){
		var value = v.value;
		var dj = document.getElementById("gzdj");
		dj.length = 1;
		$.ajax({
			type:'post',
			async : false,
			url:'findDjByGzid.action?gzid='+value,
			success:function(result){
				var data = eval(result);
				$.each(data, function(i, n) {
					document.getElementById("gzdj").options
					.add(new Option(data[i][1],
							data[i][2]));
				});
			},
			error:function(){
				alert("error");
			}
		});
	}
	function checkJdys(v){
		var tkid = v.value;
		//alert(tkid);
		$("#tikuid").val(tkid);
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
	</script>

<%
	String messge= (String)request.getAttribute("messge");
if(messge!=null){
%>
<script>
	alert('<%=messge%>
	');
</script>
<%
	}
 
String []arry=new String[]{"A","B","C","D","E","F","G","H","I","J"};
request.setAttribute("arry",arry);
%>

<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left"><table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">试题修改</td>
						<td class="header3"></td>
					</tr>
				</table></td>
			<td width="53%" align="left"></td>
		</tr>
		<tr>
			<td colspan="2" valign="top"><div id="content1" class="borader">
					<form name="addform" action="<%=request.getContextPath()%>/QuestionServlet" method="post" enctype="multipart/form-data">
						<input type=hidden name="actionType" value="query" /> 
						<input type=hidden name="myaction" value="modify" /> 
						<input type="hidden" name="stId" value="<c:out value="${question.stId}"/>" /> 
						<input type="hidden" name="subject" value="<c:out value="${subject}"/>" /> 
						<input type="hidden" name="documentnum" value="<c:out value="${documentnum}"/>" /> 
						<input type="hidden" name="examsign" value="<c:out value="${examsign}"/>" /> 
						<input type="hidden" name="recorddatebegin" value="<c:out value="${recorddatebegin}"/>" /> 
						<input type="hidden" name="recorddateend" value="<c:out value="${recorddateend}"/>" /> 
						<input type="hidden" name="modifiydatebegin" value="<c:out value="${modifiydatebegin}"/>" /> 
						<input type="hidden" name="modifiydateend" value="<c:out value="${modifiydateend}"/>" /> 
						<input type="hidden" name="businesstype" value="<c:out value="${businesstype}"/>" /> 
						<input type="hidden" name="importance" value="<c:out value="${importance}"/>" /> 
						<input type="hidden" name="questiontype" value="<c:out value="${questiontype}"/>" /> 
						<input type="hidden" name="orderby" value="<c:out value="${orderby}"/>" /> 
						<input type="hidden" name="currpage" value="<c:out value="${currpage}"/>" /> 
						<input type="hidden" name="pagesize" value="<c:out value="${pagesize}"/>" /> 
						<input type="hidden" name="pagenum" value="<c:out value="${pagenum}"/>" />
						<input type="hidden" name="stsjid" value="<c:out value="${stsjid}"/>" />
						<input type="hidden" name="sjid" value="<c:out value="${sjid}"/>" />

						<table width="760" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr class="row_height">
								<td height="30" width="60" align="right">工种：</td>
								<td>
									<input name="forward" value="1" style="display: none;" /> 
									<select id="gzid" name="gzid" onchange="checkDj(this)" style="width:200px">
										<option value="">请选择</option>
										<c:forEach var="aBean" items="${professions}">
											<c:choose>
												<c:when test="${question.id_job==aBean.id_job}">
													<option value="<c:out value="${aBean.id_job}"/>" selected="selected">
														<c:out value="${aBean.jobname}" />
												</c:when>
												<c:otherwise>
													<option value="<c:out value="${aBean.id_job}"/>">
														<c:out value="${aBean.jobname}" />
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</td>
								<td align="left">&nbsp;&nbsp;图片：
					        		<div style="position: relative;width: 100%">
								  		<div style="width: 300px;height:200px;float:left;left:42;top:-18;position:absolute; z-index:100;border: 2;border-color: red ">
								  			<c:if test="${question.stImg!=''}">
								  				<img class="imgPv" id="imgPv" src="<%=request.getContextPath()%>/servlet/ImgServlet?stid=<c:out value="${question.stId}"/>" />
								  			</c:if>
								  			<c:if test="${question.stImg==''}">
								  				<img class="imgPv" id="imgPv" src="<%=request.getContextPath()%>/images/default/default.png" />
								  			</c:if>
										</div>
									</div>
					        	</td>
							</tr>
							<tr>
								<td width="13%" align="right" height="30">等级：</td>
								<td width="18%">
									<input id="tikuid" name="ST_NODE_NAME" type="hidden" /> 
									<select id="gzdj" name="gzdj" style="width:200px" onchange="checkJdys(this)">
										<option value="">请选择</option>
										<c:forEach var="aBean" items="${dqdj}">
											<c:choose>
												<c:when test="${question.rankname==aBean }">
													<option value="<c:out value="${aBean}"/>" selected="selected">
														<c:out value="${aBean}" />
												</c:when>
												<c:otherwise>
													<option value="<c:out value="${aBean}"/>">
														<c:out value="${aBean}" />
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<%-- <option value="<c:out value="${question.rankname}"/>" selected="selected"><c:out value="${question.rankname}"/></option> --%>
									</select>
								</td>
							</tr>
							<tr>
								<td height="30" align="right">试题题型：</td>
								<td>
									<select name="ST_LXID" style="width:200px" onChange="changeDiv(this.options[selectedIndex].value)">
										<!-- onChange="changeDiv(this.options[selectedIndex].value)" -->
										<option value=0>请选择</option>
										<c:forEach var="aBean" items="${questiontypelist}">
											<c:choose>
												<c:when test="${question.stlx==aBean.id}">
													<option value="<c:out value="${aBean.id}"/>" selected="selected">
														<c:out value="${aBean.name}" />
													</option>
												</c:when>
												<c:otherwise>
													<option value="<c:out value="${aBean.id}"/>">
														<c:out value="${aBean.name}" />
													</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right" height="30">难易度：</td>
								<td>
									<select id="ST_ZYXID" name="ST_ZYXID" style="width:200px">
										<option value="">请选择</option>
										<c:forEach var="aBean" items="${importiontypelist}">
											<c:choose>
												<c:when test="${question.nyd==aBean.id}">
													<option value="<c:out value="${aBean.id}"/>" selected="selected">
														<c:out value="${aBean.name}" />
												</c:when>
												<c:otherwise>
													<option value="<c:out value="${aBean.id}"/>">
														<c:out value="${aBean.name}" />
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td height="30" align="right">试题来源：</td>
								<td colspan=1>
									<input type=text id="ST_CC" name="ST_CC" value="<c:out value="${question.stCc}"/>" style="width:200px">
								</td>
							</tr>
							<tr>
								<td align="right" height="30">重要程度：</td>
								<td colspan="1">
									<input id="bxtype" value="${question.bxType}" style="display: none;"> 
									<input type="radio" name="BX_TYPE" value="X" style="width:20px;" <c:if test="${question.bxType== 'X'}">checked="checked"</c:if> />X 
									<input type="radio" name="BX_TYPE" value="Y" style="width:20px;" <c:if test="${question.bxType== 'Y'}">checked="checked"</c:if> />Y 
									<input type="radio" name="BX_TYPE" value="Z" style="width:20px;" <c:if test="${question.bxType== 'Z'}">checked="checked"</c:if> />Z
								</td>
							</tr>
							<tr height="30">
								<td align="right">图片：</td>
								<td>
									<input name="img" id="img" type="file">
								</td>
							</tr>
							<tr height="10"></tr>
							<tr>
								<td height="30" align="right">题目：</td>
								<td colspan="1">
									<div style="position: relative;width: 100%">
								  		<div style="float:left;top:-18;position:absolute; z-index:100;border: 2;border-color: red ">
								  			<textarea id="ST_TG1" name="ST_TG" style="width:550px;" rows="4">
												<c:out value="${question.stTg}" />
											</textarea>
										</div>
							  	   </div>
							  	</td>
							</tr>
							<tr height="30"></tr>
							<table width="100%" height="30" border="0" cellpadding="0" cellspacing="0">
							 <tr>
								<td height="40" align="center" valign="bottom">
									<input name="add" type=button class="submit_2" onClick="jvavscript:checkSubmit();" value="保 存" />&nbsp; 
									<!-- <input name="res" type="reset"  class="submit_2" value="重 置"/>&nbsp; --> 
									<input name="del" type=button class="submit_2" onClick="jvavscript:stdel();" value="删 除" />
								</td>
							 </tr>
							</table>
							
							<div id="div1" <c:if test="${question.stlx!=2}"> style="display:none"</c:if>>
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td width=40% colspan="3">&nbsp;单选题答案区：</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td width="10%" align="right">可选项：</td>
										<td width="80%" align="center">
											<table width=760 border=0 align="center" cellspacing="1" cellspadding=1>
											<c:if test="${question.stImgA==''}">
												<c:forTokens items="${question.stXx}" delims="||" var="it" varStatus="s">
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=radio name="singleoptionkey" value="<c:out value="${arry[s.count-1]}" />" <c:forTokens items="${question.stDa}" delims="||" var="da">
																		<c:if test="${arry[s.count-1]==da}">checked</c:if>
																		</c:forTokens>> <c:out
																			value="${arry[s.count-1]}" />：</td>
																	<td><textarea id="singleoption" name="singleoption" style="width:580px;" rows="3">
																			<c:out value="${it}" />
																		</textarea></td>

																	<td><c:if test="${s.count==4}">
																			<input name="add1" type="button" id="add2" value="增加" onClick="addLine(this,1)">
																		</c:if> <c:if test="${s.count>4}">
																			<input name="add1" type="button" id="add1" value="删除" onClick="addLine(this,1)">
																			<script language="JavaScript">
																				rowIndex++
																			</script>
																		</c:if></td>
																</tr>
															</table>
														</td>
													</tr>
												</c:forTokens>
												</c:if>
												<c:if test="${question.stImgA!=''}">
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=radio name="singleoptionkey" value="A" 
																	<c:forTokens items="${question.stDa}" delims="||" var="da">
																		<c:if test="${'A'==da}">checked</c:if>
																		</c:forTokens>/> A:</td>
																	<td>
																		<input name="ST_IMG_A" id="ST_IMG_A" type="file">
																	</td>
																	<td>
																		<img class="img_xx" id="imgPvA" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${question.stId}"/>&xx=A"/>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=radio name="singleoptionkey" value="B" 
																		<c:forTokens items="${question.stDa}" delims="||" var="da">
																		<c:if test="${'B'==da}">checked</c:if>
																		</c:forTokens>/> B:</td>
																	<td>
																		<input name="ST_IMG_B" id="ST_IMG_B" type="file">
																	</td>
																	<td>
																		<img class="img_xx" id="imgPvB" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${question.stId}"/>&xx=B"/>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=radio name="singleoptionkey" value="C" 
																		<c:forTokens items="${question.stDa}" delims="||" var="da">
																		<c:if test="${'C'==da}">checked</c:if>
																		</c:forTokens>/> C:</td>
																	<td>
																		<input name="ST_IMG_C" id="ST_IMG_C" type="file">
																	</td>
																	<td>
																		<img class="img_xx" id="imgPvC" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${question.stId}"/>&xx=C"/>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=radio name="singleoptionkey" value="D" 
																		<c:forTokens items="${question.stDa}" delims="||" var="da">
																		<c:if test="${'D'==da}">checked</c:if>
																		</c:forTokens>/> D:</td>
																	<td>
																		<input name="ST_IMG_D" id="ST_IMG_D" type="file">
																	</td>
																	<td>
																		<img class="img_xx" id="imgPvD" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${question.stId}"/>&xx=D"/>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<c:if test="${question.stImgE!=''}">
														<tr>
															<td>
																<table border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="60"><input type=radio name="singleoptionkey" value="E" 
																		<c:forTokens items="${question.stDa}" delims="||" var="da">
																			<c:if test="${'E'==da}">checked</c:if>
																			</c:forTokens>/> E:</td>
																		<td>
																			<input name="ST_IMG_E" id="ST_IMG_E" type="file">
																		</td>
																		<td>
																			<img class="img_xx" id="imgPvE" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${question.stId}"/>&xx=E"/>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</c:if>
													<c:if test="${question.stImgF!=''}">
														<tr>
															<td>
																<table border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="60"><input type=radio name="singleoptionkey" value="F" 
																			<c:forTokens items="${question.stDa}" delims="||" var="da">
																			<c:if test="${'F'==da}">checked</c:if>
																			</c:forTokens>/> F:</td>
																		<td>
																			<input name="ST_IMG_F" id="ST_IMG_F" type="file">
																		</td>
																		<td>
																			<img class="img_xx" id="imgPvF" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${question.stId}"/>&xx=F"/>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</c:if>
													<c:if test="${question.stImgG!=''}">
														<tr>
															<td>
																<table border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="60"><input type=radio name="singleoptionkey" value="G" 
																			<c:forTokens items="${question.stDa}" delims="||" var="da">
																			<c:if test="${'G'==da}">checked</c:if>
																			</c:forTokens>/> G:</td>
																		<td>
																			<input name="ST_IMG_G" id="ST_IMG_G" type="file">
																		</td>
																		<td>
																			<img class="img_xx" id="imgPvG" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${question.stId}"/>&xx=G"/>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</c:if>
												</c:if>
												<%-- <c:if test="${question.stXx==null}">
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=radio name="singleoptionkey" value="A">A:</td>
																	<td><textarea id="singleoption" name="singleoption" style="width:580px;" rows="3"></textarea></td>
																	<td></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>

															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=radio name="singleoptionkey" value="B">B:</td>
																	<td><textarea id="singleoption" name="singleoption" style="width:580px;" rows="3"></textarea></td>
																	<td></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=radio name="singleoptionkey" value="C">C:</td>
																	<td><textarea id="singleoption" name="singleoption" style="width:580px;" rows="3"></textarea></td>
																	<td></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=radio name="singleoptionkey" value="D">D:</td>
																	<td><textarea id="singleoption" name="singleoption" style="width:580px;" rows="3"></textarea></td>
																	<td><input name="add2" type="button" id="add2" value="增加" onClick="addLine(this,1)"></td>
																</tr>
															</table>
														</td>
													</tr>

												</c:if> --%>


											</table>
										</td>
										<td width="10%">&nbsp;</td>
									</tr>
								</table>
							</div>

							<div id="div2" <c:if test="${question.stlx!=8}"> style="display:none"</c:if>>
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td width=40% colspan="3">&nbsp;多选题答案区：</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td width="10%" align="right">可选项：</td>
										<td width="80%" align="center">
											<table width=760 border=0 align="center" cellspacing="1" cellspadding=1>
											<c:if test="${question.stImgA==''}">
												<c:forTokens items="${question.stXx}" delims="||" var="it" varStatus="s">
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=checkbox name="manyoptionkey" value="<c:out value="${arry[s.count-1]}" />" <c:forTokens items="${question.stDa}" delims="||" var="da">
																		<c:if test="${arry[s.count-1]==da}">checked</c:if>
																		</c:forTokens>> 
																		<c:out value="${arry[s.count-1]}" />：</td>
																	<td><textarea id="manyoption" name="manyoption" style="width:580px;" rows="2">
																			<c:out value="${it}" />
																		</textarea></td>
																	<td><c:if test="${s.count==4}">
																			<input name="add2" type="button" id="add2" value="增加" onClick="addLine(this,2)">
																		</c:if> <c:if test="${s.count>4}">
																			<input name="add2" type="button" id="add2" value="删除" onClick="addLine(this,2)">
																			<script language="JavaScript">
																				rowIndex++
																			</script>
																		</c:if></td>
																</tr>
															</table>
														</td>
													</tr>
												</c:forTokens>
												</c:if>
												<c:if test="${question.stImgA!=''}">
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=checkbox name="manyoptionkey" value="A" 
																	<c:forTokens items="${question.stDa}" delims="||" var="da">
																		<c:if test="${'A'==da}">checked</c:if>
																		</c:forTokens>/> A:</td>
																	<td>
																		<input name="ST_IMG_A" id="ST_IMG_Amany" type="file">
																	</td>
																	<td>
																		<img class="img_xx" id="imgPvAmany" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${question.stId}"/>&xx=A"/>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=checkbox name="manyoptionkey" value="B" 
																		<c:forTokens items="${question.stDa}" delims="||" var="da">
																		<c:if test="${'B'==da}">checked</c:if>
																		</c:forTokens>/> B:</td>
																	<td>
																		<input name="ST_IMG_B" id="ST_IMG_Bmany" type="file">
																	</td>
																	<td>
																		<img class="img_xx" id="imgPvBmany" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${question.stId}"/>&xx=B"/>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=checkbox name="manyoptionkey" value="C" 
																		<c:forTokens items="${question.stDa}" delims="||" var="da">
																		<c:if test="${'C'==da}">checked</c:if>
																		</c:forTokens>/> C:</td>
																	<td>
																		<input name="ST_IMG_C" id="ST_IMG_Cmany" type="file">
																	</td>
																	<td>
																		<img class="img_xx" id="imgPvCmany" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${question.stId}"/>&xx=C"/>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=checkbox name="manyoptionkey" value="D" 
																		<c:forTokens items="${question.stDa}" delims="||" var="da">
																		<c:if test="${'D'==da}">checked</c:if>
																		</c:forTokens>/> D:</td>
																	<td>
																		<input name="ST_IMG_D" id="ST_IMG_Dmany" type="file">
																	</td>
																	<td>
																		<img class="img_xx" id="imgPvDmany" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${question.stId}"/>&xx=D"/>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<c:if test="${question.stImgE!=''}">
														<tr>
															<td>
																<table border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="60"><input type=checkbox name="manyoptionkey" value="E" 
																		<c:forTokens items="${question.stDa}" delims="||" var="da">
																			<c:if test="${'E'==da}">checked</c:if>
																			</c:forTokens>/> E:</td>
																		<td>
																			<input name="ST_IMG_E" id="ST_IMG_Emany" type="file">
																		</td>
																		<td>
																			<img class="img_xx" id="imgPvEmany" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${question.stId}"/>&xx=E"/>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</c:if>
													<c:if test="${question.stImgF!=''}">
														<tr>
															<td>
																<table border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="60"><input type=checkbox name="manyoptionkey" value="F" 
																			<c:forTokens items="${question.stDa}" delims="||" var="da">
																			<c:if test="${'F'==da}">checked</c:if>
																			</c:forTokens>/> F:</td>
																		<td>
																			<input name="ST_IMG_F" id="ST_IMG_Fmany" type="file">
																		</td>
																		<td>
																			<img class="img_xx" id="imgPvFmany" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${question.stId}"/>&xx=F"/>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</c:if>
													<c:if test="${question.stImgG!=''}">
														<tr>
															<td>
																<table border="0" cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="60"><input type=checkbox name="singleoptionkey" value="G" 
																			<c:forTokens items="${question.stDa}" delims="||" var="da">
																			<c:if test="${'G'==da}">checked</c:if>
																			</c:forTokens>/> G:</td>
																		<td>
																			<input name="ST_IMG_G" id="ST_IMG_Gmany" type="file">
																		</td>
																		<td>
																			<img class="img_xx" id="imgPvGmany" src="<%=request.getContextPath()%>/servlet/ImgServlet_xx?stid=<c:out value="${question.stId}"/>&xx=G"/>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</c:if>
												</c:if>
												<%-- <c:if test="${question.stXx==null}">
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=checkbox name="manyoptionkey" value="A">A:</td>
																	<td><textarea name="manyoption" id="manyoption1" style="width:580px;" rows="2"></textarea></td>
																	<td></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>

															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=checkbox name="manyoptionkey" value="B">B:</td>
																	<td><textarea name="manyoption" id="manyoption2" style="width:580px;" rows="2"></textarea></td>
																	<td></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=checkbox name="manyoptionkey" value="C">C:</td>
																	<td><textarea name="manyoption" id="manyoption3" style="width:580px;" rows="2"></textarea></td>
																	<td></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=checkbox name="manyoptionkey" value="D">D:</td>
																	<td><textarea name="manyoption" id="manyoption4" style="width:580px;" rows="2"></textarea></td>
																	<td><input name="add2" type="button" id="add2" value="增加" onClick="addLine(this,2)"></td>
																</tr>
															</table>
														</td>
													</tr>
												</c:if> --%>
											</table>

										</td>
										<td width="10%">&nbsp;</td>
									</tr>
								</table>
							</div>

							<div id="div3" <c:if test="${question.stlx!=3}"> style="display:none"</c:if>>
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td width=40% colspan="3">&nbsp;判断题答案区：</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td width="20%" align="right">判断选项：</td>
										<td width="30%" align="center">
											<input type=radio name="verdictkey" value="T" <c:if test="${question.stDa=='T'}">checked</c:if>> 对
										</td>
										<td width="30%" align="left">
											<input type=radio name="verdictkey" value="F" <c:if test="${question.stDa=='F'}">checked</c:if>> 错
										</td>
									</tr>
								</table>
							</div>
							<div id="div4" <c:if test="${question.stlx!=14}">style="display:none"</c:if>>
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td width=40% colspan="3">&nbsp;判断说明题答案区：</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td width="10%" align="right">判断选项：</td>
										<td width="80%" align="center">
											<table width=760 border=0 align="center" cellspacing="1" cellspadding=1>
												<tr>
													<td>
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td><input type=radio name="verdictsaykey" value="T" <c:if test="${question.stDa=='T'}">checked</c:if>> 对:</td>
																<td><input type=radio name="verdictsaykey" value="F" <c:if test="${question.stDa=='F'}">checked</c:if>> 错</td>
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
														<table>
															<tr>
																<td><textarea id="verdictsay" name="verdictsay" style="width:580px;" rows="5">
																		<c:if test="${question.stlx==4}">
																			<c:out value="${question.stDasm}" />
																		</c:if>
																	</textarea></td>
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
							<div id="div5" <c:if test="${question.stlx!=15}"> style="display:none"</c:if>>
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td width=40% colspan="3">&nbsp;录音题答案区：</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td width="10%" align="right">答案：</td>
										<td width="80%" align="center">
											<table width=760 border=0 align="center" cellspacing="1" cellspadding=1>
												<tr>
													<td>
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td><textarea name="recordkey" id="recordkey" style="width:580px;" rows="5">
																		<c:if test="${question.stlx==5}">
																			<c:out value="${question.stDa}" />
																		</c:if>
																	</textarea></td>
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
										<td width="80%" align="center">
											<table width=760 border=0 align="center" cellspacing="1" cellspadding=1>
												<tr>
													<td>
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td><input type=text name="recordpath" size="80" value="<c:if test="${question.stlx==5}"><c:out value="${question.stFjlj}"/></c:if>"></td>
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

							<div id="div6" <c:if test="${question.stlx!=1}"> style="display:none"</c:if>>


								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td width=40% colspan="3">&nbsp;填空题答案区：</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td width="10%" align="right">答案：</td>
										<td width="80%" align="center">
											<table width=760 border=0 align="center" cellspacing="1" cellspadding=1>
												<tr>
													<td>
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td><textarea name="fillkey" id="fillkey" style="width:580px;" rows="5">
																		<c:if test="${question.stlx==6}">
																			<c:out value="${question.stDa}" />
																		</c:if>
																	</textarea></td>
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
							<div id="div7" <c:if test="${question.stlx!=4}"> style="display:none"</c:if>>

								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td width=40% colspan="3">&nbsp;问答题答案区：</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td width="10%" align="right">答案：</td>
										<td width="80%" align="center">
											<table width=760 border=0 align="center" cellspacing="1" cellspadding=1>
												<tr>
													<td>
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td><textarea name="askkey" id="askkey" style="width:580px;" rows="5">
																		<c:if test="${question.stlx==7}">
																			<c:out value="${question.stDa}" />
																		</c:if>
																	</textarea></td>
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


							<div id="div8" <c:if test="${question.stlx!=18}"> style="display:none"</c:if>>

								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td width=40% colspan="3">&nbsp;案例分析题答案区：</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td width="10%" align="right">答案：</td>
										<td width="80%" align="center">
											<table width=760 border=0 align="center" cellspacing="1" cellspadding=1>
												<tr>
													<td>
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td><textarea name="casekey" id="casekey" style="width:580px;" rows="5">
																		<c:if test="${question.stlx==8}">
																			<c:out value="${question.stDa}" />
																		</c:if>
																	</textarea></td>
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
							<div id="div9" <c:if test="${question.stlx!=5}"> style="display:none"</c:if>>

								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td width=40% colspan="3">&nbsp;计算题题答案区：</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td width="10%" align="right">答案：</td>
										<td width="80%" align="center">
											<table width=760 border=0 align="center" cellspacing="1" cellspadding=1>
												<tr>
													<td>
														<table border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td><textarea name="calculatekey" id="calculatekey" style="width:580px;" rows="5">
																		<c:if test="${question.stlx==9}">
																			<c:out value="${question.stDa}" />
																		</c:if>
																	</textarea></td>
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
							<div id="div10" <c:if test="${question.stlx!=110}"> style="display:none"</c:if>>
								<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td width=40% colspan="3">&nbsp;不定项选择题答案区：</td>
									</tr>
									<tr bgcolor="#ffffff">
										<td width="10%" align="right">可选项：</td>
										<td width="80%" align="center">
											<table width=760 border=0 align="center" cellspacing="1" cellspadding=1>

												<c:forTokens items="${question.stXx}" delims="||" var="it" varStatus="s">
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=checkbox name="uncertainoptionkey" value="<c:out value="${arry[s.count-1]}" />" <c:forTokens items="${question.stDa}" delims="||" var="da">
									<c:if test="${arry[s.count-1]==da}">checked</c:if>
									</c:forTokens>> <c:out
																			value="${arry[s.count-1]}" />：</td>
																	<td><textarea id="uncertainoption" name="uncertainoption" style="width:580px;" rows="2">
																			<c:out value="${it}" />
																		</textarea></td>
																	<td><c:if test="${s.count==4}">
																			<input name="add2" type="button" id="add2" value="增加" onClick="addLine(this,2)">
																		</c:if> <c:if test="${s.count>4}">
																			<input name="add2" type="button" id="add2" value="删除" onClick="addLine(this,2)">
																			<script language="JavaScript">
																				rowIndex++
																			</script>
																		</c:if></td>
																</tr>
															</table>
														</td>
													</tr>
												</c:forTokens>

												<c:if test="${question.stXx==null}">
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=checkbox name="uncertainoptionkey" value="A">A:</td>
																	<td><textarea name="uncertainoption" id="uncertainoption1" style="width:580px;" rows="2"></textarea></td>
																	<td></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>

															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=checkbox name="manyoptionkey" value="B">B:</td>
																	<td><textarea name="uncertainoption" id="uncertainoption2" style="width:580px;" rows="2"></textarea></td>
																	<td></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=checkbox name="uncertainoptionkey" value="C">C:</td>
																	<td><textarea name="uncertainoption" id="uncertainoption3" style="width:580px;" rows="2"></textarea></td>
																	<td></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="60"><input type=checkbox name="uncertainoptionkey" value="D">D:</td>
																	<td><textarea name="uncertainoption" id="uncertainoption4" style="width:580px;" rows="2"></textarea></td>
																	<td><input name="add2" type="button" id="add2" value="增加" onClick="addLine(this,2)"></td>
																</tr>
															</table>
														</td>
													</tr>
												</c:if>
											</table>

										</td>
										<td width="10%">&nbsp;</td>
									</tr>
								</table>
							</div>
							<div id="div11" <c:if test="${question.stlx!=6}"> style="display:none"</c:if>>
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
																<td><textarea name="discusskey" id="discusskey" style="width: 750px; height: 50px;">
																		<c:if test="${question.stlx==11}">
																			<c:out value="${question.stDa}" />
																		</c:if>
																	</textarea></td>
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
							<div id="div12" <c:if test="${question.stlx!=12}"> style="display:none"</c:if>>
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
																<td><textarea name="databasekey" id="databasekey" style="width: 750px; height: 50px;">
																		<c:if test="${question.stlx==12}">
																			<c:out value="${question.stDa}" />
																		</c:if>
																	</textarea></td>
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
							<div id="div13" <c:if test="${question.stlx!=13}"> style="display:none"</c:if>>
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
																<td><textarea name="telephonekey" id="telephonekey" style="width: 750px; height: 50px;">
																		<c:if test="${question.stlx==13}">
																			<c:out value="${question.stDa}" />
																		</c:if>
																	</textarea></td>
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
						</table>
					</form>
				</div></td>
		</tr>
	</table>
</body>

</HTML>
