<%@page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@page import="com.wondersgroup.falcon.beans.auth.ProfessionBean"%>
<%@page import="com.wondersgroup.kaoshi.bo.Tjobsubject"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	ProfessionBean professionBean = new ProfessionBean();//工种
String s = request.getParameter("id_job");//idjob
String id = request.getParameter("gzid");//id
int flag =1;
List<Object> list_dj = professionBean.getztdj(s,flag);//通过工种id获取对应等级
Tjobsubject t = new Tjobsubject();
//EPapersSet  e = new EPapersSet();
if(request.getParameter("gzid")!=null){
	t = professionBean.findTjobsubjectBygzid(Integer.parseInt(id));
}

String grade="";
Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
String dt = sdf.format(date);
String gzid = request.getParameter("gzid");
String sjmc = request.getParameter("sjmc");
if(sjmc!=null){
	sjmc = new String(sjmc.getBytes("ISO-8859-1"),"utf-8");

//if(gzid!=null)
	//gzid = new String(gzid.getBytes("ISO-8859-1"),"utf-8");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>机考抽卷组卷</title>
<link href="newcss/style.css" rel="stylesheet" type="text/css" />
<link href="inc/all.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript" src="js/dateMy97/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery/jquery.1.3.min.js"></script>
<script language="javascript">
function  scsj (){
	var gzid = "<%=request.getParameter("id_job")%>";
		var dj = $("#grade").val();
		var flag1 = 1;
		var sjmc = "";
		//var djValue = $("#grade").find("option:selected").val();
		//var dj = $("#grade").find("option:selected").text();
		if (gzid != null && dj != "0") {
			$.ajax({
				type : 'post',
				async : false,
				url : 'ztzj.action?gzid=' + gzid + '&dj=' + dj + '&flag1='
						+ flag1,
				success : function(result) {
					var data = eval(result);
					$("#sjid").val(data[0][0]);
					$("#sjmc").val(data[0][1]);
					$("#sjMcc").val(data[0][1]);
					$("#sjZf").val(data[0][2]);
					$("#Zf").val(data[0][2]);
				},
				error : function() {
					alert("error");
				}
			});
		} else {
			alert("请先选择考试的工种和等级！");
		}

	}
	function changeKksj() {
		var kksj = $("#Kksj").val();
		var sjmc = $("#sjMcc").val();
		kksj = kksj.replace(/-/g, "");
		sjmc += kksj;
		$("#sjmc").val(sjmc);
	}

	function checkSubmit() {

		if (document.all.sjDjsx.value == "") {
			alert("请选择输入答卷时限！");
			return;
		}
		if (document.all.sjBhgfs.value == "") {
			alert("请输不及格分数线！");
			return;
		}

		if (document.all.Yxqjzsj.value == "") {
			alert("请选择输入有效期截止日期！");
			return;
		}
		document.myform.submit();
	}
</script>
</head>
<body class="nrbj">
	<table width="99%" border="0" align="right" cellpadding="0" cellspacing="0" style="margin-top:10px; margin-left:8px; ">
		<tr>
			<td width="45%" align="left">
				<table border="0" align="left" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="middle" class="header1"></td>
						<td class="header2">
							<%
								if(gzid!=null){
									out.print(t.getJobname());
								}else{
									out.print("请先选择工种");
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
			<td colspan="2" valign="top"><div id="content1" class="borader">
					<form name="myform" action="<%=request.getContextPath()%>/papersServlet" method="post">
						<input type=hidden name="actionType" value="auto"> 
						<input id="index_id" type="hidden" name="indexs" value="" /> 
						<input id="outway" type="hidden" name="outway" value="3" /> 
						<input id="sjid" type="hidden" name="sjid" value="" /> 
						<input id="servicetype" type="hidden" name="servicetype" value="<%=t.getId_job()%>" />

						<!-- 误删 -->
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr class="row_height">
								<td height="26" colspan="8" align="center">
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tablistys">
										<tr class="row_height">
											<td width="10%" align="right">工种名称：</td>
											<td width="18%"><input type="text" readonly="true" name="gzMc" value="<%=t.getJobname()%>" id="gzmc" class="input1" style="float: left;"> <font color="#FF0000" style="float: left;">*</font></td>
											<td width="10%" align="right">等级：</td>
											<td width="18%">
												<select id="grade" name="grade" onchange="showDrade(this.value)" style="width: 100px">
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
											<td width="10%" align="right">试卷名称：</td>
											<td width="20%"><input type="text" name="sjMc" value="" id="sjmc" class="input1" style="float: left;"> <input type="hidden" name="sjMcc" id="sjMcc" /> <font color="#FF0000" style="float: left;">*</font></td>
											<td align="right"><input type="button" class="submit_2" onClick="javascript:scsj();" value="生成试卷" /></td>
											<td></td>

										</tr>
										<tr>
											<td width="18%" align="right">试卷总分：</td>
											<td><input type="text" name="Zf" id="Zf" class="input1" style="float: left;" disabled="disabled" /> <input type="hidden" name="sjZf" id="sjZf" /> <font color="#FF0000" style="float: right;">*</font></td>

											<td width="13%" align="right">答卷时限：</td>
											<td><input type="text" name="sjDjsx" value="60" class="input1" style="float: left; width: 100px" /> <font color="#FF0000" style="float: left;">*</font></td>
											<td align="right">及格分数：</td>
											<td><input type="text" name="sjBhgfs" value="60" class="input1" style="float: left;" /> <font color="#FF0000" style="float: left;">*</font></td>


										</tr>
										<tr class="row_height">
											<td width="8%" align="right">即显分数：</td>
											<td width="18%">
												<div style="width:40px;float: left;">
													<input type=radio name="sjLjcf" value="1" style="width:17px;" checked="checked" />是
												</div>
												<div style="width:40px;float: left;">
													<input type=radio name="sjLjcf" value="0" style="width:17px;" />否
												</div>
											</td>
											<td width="8%" align="right">考试类型：</td>
											<td width="10%"><select id="paper_Type" name="paperType" style="width: 100px">
													<option value=1 selected>鉴定类考试</option>
													<option value=2>其他类考试</option>
											</select></td>
											<td align="right">开考后：</td>
											<td><select name="tqjj" style="width:50px">
													<option value="10">10</option>
													<option value="30" selected="selected">30</option>
													<option value="45">45</option>
													<option value="60">60</option>
											</select> <font>分钟可交卷</font></td>


										</tr>
										<tr>
											<td width="10%" align="right">开考时间：</td>
											<td><input type="text" id="Kksj" name="Kksj" onclick="WdatePicker()" onchange="changeKksj()" class="Wdate" style="width:120px" /> <font color="#FF0000">*</font></td>
											<td width="10%" align="right">有效期至：</td>
											<td><input type="text" id="Yxqjzsj" name="Yxqjzsj" onclick="WdatePicker()" class="Wdate" style="width:120px" /> <font color="#FF0000">*</font></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr height="6px">
								<td colspan="6"></td>
							</tr>
							<tr class="row_height">
								<td rowspan="6">
									<div>
										<div style="margin-left: 200px;float: left;">
											<input name="button" type="button" class="submit_2" onClick="javascript:checkSubmit();" value="试卷预览" />
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
						</table>
					</form>
				</div></td>
		</tr>
	</table>
</body>
</html>