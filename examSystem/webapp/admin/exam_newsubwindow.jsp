<%@page contentType="text/html;charset=gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>试题查询页面</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">
		<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
	</HEAD>
	<script language="javascript">
		function queryQuestions(){
			var realquesname = document.getElementById("query_realquesname").value;
			var businesstype = document.getElementById("query_businesstype").value;
			var questiontype = document.getElementById("query_questiontype").value;
			var importance = document.getElementById("query_importance").value;
			requestXml("<%=request.getContextPath()%>/questionServlet?actionType=queryquestions&realquesname="+realquesname+"&businesstype="+businesstype+"&&questiontype="+questiontype+"&&importance="+importance+"&&type=newwindow");		
		}
		
		var xmlhttp = false;
		
		function requestXml(url){
			if(window.ActiveXObject){
				try{
					xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
				}catch(e){
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
			
			xmlhttp.onreadystatechange=jiexi;
			xmlhttp.open("GET",url,true);
			xmlhttp.send(null);
		}
		
		function jiexi(){
			if(xmlhttp.readystate==4){
				if(xmlhttp.status==200){
					if(xmlhttp.responseText!=null){
						//alert(xmlhttp.responseText);
						document.getElementById("queryresult").innerHTML=xmlhttp.responseText;
					}
				}
			}
		}	
		
		function getQuestions(){
			var s = new Object();
			var singlechecks = document.getElementsByName("singlecheck");
			for(var i=0;i<singlechecks.length;i++){
				if(singlechecks[i].checked==true){
					//alert(singlechecks[i].value);
					var quesname =document.getElementById(singlechecks[i].value).innerText;
					var questype = document.getElementById("type"+singlechecks[i].value).innerText;
					s.value=quesname;
					s.id=singlechecks[i].value;
					singlechecks[i].checked=false;
				}
			}
			window.returnValue=s;
			alert("添加完毕，请注意查看！");
			window.close();
		}			
	</script>
	<BODY >
		<br>
		<form name="exam_handcreform" action="<%=request.getContextPath() %>/questionServlet" method="post">
		
			<table width=99% border=0 cellspadding=2 cellspacing =2 >
				<tr bgcolor=#ffffff>
					<td>
						<table  width=100% border=0 cellspadding=1 cellspacing =1 bgcolor="#E0F0FD">
						<tr bgcolor="#ffffff" height=50 >
							<td  width=8% align=right >&nbsp;题目名：</td>
							<td colspan=5>
								<input type=text id="query_realquesname" name=realquesname >
							</td>
						</tr>
						<tr bgcolor="#ffffff" >
							<td align=right width=8%>业务类型：</td> 
							<td align=left width=8%>
								<select id="query_businesstype">
									<option value=0>请选择</option>
									<option value=1>城保类</option>
									<option value=2>镇保类</option>
								</select>
							</td>
							<td align=right width=8%>试题题型：</td>
							<td align=left width=8%>
								<select id="query_questiontype">
									<option value=0>请选择</option>
									<option value=1>单选题</option>
									<option value=2>多选题</option>
									<option value=3>判断题</option>
									<option value=4>问答题</option>
								</select>
							</td>
							<td align=right width=8%>重要性：</td>
							<td align=left width=8%>
								<select id="query_importance">
									<option value=0>请选择</option>
									<option value=1>普通</option>
									<option value=2>较高</option>
									<option value=3>很高</option>
								</select>								
							</td>
							</tr>
							<tr bgcolor="#E0F0FD">
								<td colspan=6 align=center>
									<input type=button onclick="queryQuestions()" value="查询试题">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr bgcolor="#ffffff">
					<td >
						<div id="queryresult"></div>
					</td>
				</tr>	

			</table>
		</form>
	</BODY>
</HTML>
