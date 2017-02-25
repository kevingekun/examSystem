<%@page contentType="text/html;charset=gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>答案查询页面</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">
		<link href="<%=request.getContextPath() %>/inc/all.css" rel="stylesheet" type="text/css">
	</HEAD>
	<script language="javascript">
		function queryKeys(){
			var realkeyname = document.getElementById("query_realkeyname").value;
			requestXml("<%=request.getContextPath()%>/querysubservlet?actionType=querykey&realkeyname="+realkeyname+"");		
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
		function getKeyValues(keycontent,keyid){
			//alert(keycontent+" and "+keyid);
			var s = new Object();
			s.value = keycontent;
			s.id = keyid;
			window.returnValue=s;
			alert("添加完毕，请注意查看！");
			window.close();
		}			
	</script>
	<BODY >
		<br>
		<form name="exam_handcreform" action="<%=request.getContextPath() %>/examServlet" method="post">
		
			<table width=99% border=0 cellspadding=2 cellspacing =2 >
				<tr bgcolor=#ffffff>
					<td>
						<table  width=100% border=0 cellspadding=1 cellspacing =1 bgcolor="#E0F0FD">
						<tr bgcolor="#ffffff" height=50 >
							<td  width=15% align=right >&nbsp;答案名：</td>
							<td colspan=5>
								<input type=text id="query_realkeyname" name=realsubname >
							</td>
						</tr>
						<tr bgcolor="#E0F0FD">
							<td colspan=6 align=center>
								<input type=button onclick="queryKeys()" value="查询答案">
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr bgcolor="#ffffff">
					<td >
						<div id="queryresult">
							<table width=100% border=0 cellspadding=0 cellspacing =0>
								<tr bgcolor="#E0F0FD" align=center>
									<td width=7%>序号</td>
									<td>答案名</td>
									<td width=7%>操作</td>
								</tr>								
							</table>
						</div>
					</td>
				</tr>	

			</table>
		</form>
	</BODY>
</HTML>
