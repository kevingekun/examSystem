<%@page contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page
	import="java.util.List,java.util.Set,java.util.ArrayList,java.util.Date,java.util.Iterator,com.wondersgroup.falcon.paper.model.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>New Document</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">
		<link href="<%=request.getContextPath()%>/inc/all.css"
			rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/newcss/style.css"
			rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	</HEAD>

	<%
		//����ֵ�������β˵�
		request.getSession().setAttribute("Flag", "1");

		String surplus=request.getAttribute("surplus");
		EPapers p = (EPapers) request.getAttribute("epapers");

		//System.out.println("==========="+p.getSjDjsx());
	%>

	<%
		String[] arry = new String[]{"A", "B", "C", "D", "E", "F"};
		String[] arry1 = new String[]{"һ", "��", "��", "��", "��", "��", "��",
				"��", "��", "ʮ", "ʮһ", "ʮ��"};
		request.setAttribute("arry", arry);
		request.setAttribute("arry1", arry1);

		Date st = new Date();
		if (session.getAttribute("st") == null) {
			session.setAttribute("st", st);
		}
	%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery/jquery.1.3.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery/autoresize.min.js"></script>
	<script>
var bXmlHttpSupport = (typeof XMLHttpRequest != "undefined" || window.ActiveXObject);
if (typeof XMLHttpRequest == "undefined" && window.ActiveXObject) {
        function XMLHttpRequest() {
            var arrSignatures = ["MSXML2.XMLHTTP.5.0", "MSXML2.XMLHTTP.4.0",
                                 "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP",
                                 "Microsoft.XMLHTTP"];
                             
            for (var i=0; i < arrSignatures.length; i++) {
                try {        
                    var oRequest = new ActiveXObject(arrSignatures[i]);            
                    return oRequest;        
                } catch (oError) { /*ignore*/ }
            }          
    
            throw new Error("MSXML is not installed on your system.");               
        }
} 
	
	var endtime = <%=((Date) session.getAttribute("st")).getTime()%> + <%=surplus%>*60*1000;
	var timeid;
    var currenttime;
	var timems;
    
	function getCurrentTime(){
		//var currenttime = new Date();	
		 //var timems=<%=p.getSjDjsx()%>*60*1000;
		 if(timems>=300000&&timems<301000){
		 	alert("����ʱ�仹ʣ5���ӣ���");
		 }
		// var timems = endtime-currenttime.getTime();
		 //alert(timems);
		if(timems<=0){
			autoSubmit();
			window.clearTimeout();
		}
		var timeh = (Math.floor(timems/(1000*60*60))%24)<=0?0:(Math.floor(timems/(1000*60*60))%24);
		if(timeh/10<1){
			timeh="0"+timeh
		}
		var timem = (Math.floor(timems/(1000*60))%60)<=0?0:(Math.floor(timems/(1000*60))%60);
		if(timem/10<1){
			timem="0"+timem
		}
		var times = (Math.floor(timems/1000)%60)<=0?0:(Math.floor(timems/1000)%60);
		if(times/10<1){
			times="0"+times
		}
		document.getElementById("timeh").innerHTML=timeh+":";
		document.getElementById("timem").innerHTML=timem+":";		
		document.getElementById("times").innerHTML=times;		
		//alert(timeh+":"+timem+":"+times);
		timems=timems-1000;
		setTimeout("getCurrentTime()",1000);
	}

	
	function changeTopage(){
		currenttime='<%=new java.util.Date().getTime()%>';
		timems = endtime-currenttime;
		getCurrentTime();
		parent.parent.topFrame.location.reload();

	}
	
	function savebeforeunload(){
		if(window.confirm("��ȷ��Ҫ�뿪������")){
			autoSubmit();
		}
	}
	
	function mediaplayer(wav_name){
		var MediaPlayer = document.getElementById("MediaPlayer");
		MediaPlayer.Filename = wav_name;
		//alert(MediaPlayer.Filename);
	}	



function delete_login(){
window.location = "delete_login.jsp" ;
}

function retrieveBook(spnid,typeid) {
  var to_score=0.0;
  var single=document.getElementsByTagName("span");
  for(var i=0;i<single.length;i++){
   if(single[i].id.indexOf(typeid)>-1){
     to_score=to_score+parseFloat(single[i].innerHTML);
   }
  }
document.getElementById(spnid).innerHTML=parseFloat(to_score).toFixed(1);
}	

</script>


	<BODY style="font-size: 12px; MARGIN: 5px;" onLoad="changeTopage()"
		onpaste="javascript:if('<s:property value="epapers.model"/>'=='1'){return true;}else{return false;}"
		onbeforeunload="javascript:if(document.all.aa.value == 0){return '';}">
		
		
		
		<input type="hidden" name="aa" value="0" />
		<IFRAME NAME="uploadfrm" id="uploadfrm" src=""
			STYLE="HEIGHT: 0; LEFT: 0px; MARGIN-TOP: 0px; WIDTH: 0; SCROLL: no;"
			frameborder=0></IFRAME>


		<s:form name="paperSubmitform" action="exampaperActioncommit"
			method="post" onsubmit="return submitForm();">
			<s:token></s:token>
			<s:hidden name="paperId" />
			<input type="hidden" name="jssj"
				value="<%=((Date) session.getAttribute("st")).getTime()%>" />
			<table width="98%" border="0" align="right" cellpadding="0"
				cellspacing="0" style="margin-top: 10px; margin-left: 8px;">
				<tr>
					<td width="45%" align="left">
						<table border="0" align="left" cellpadding="0" cellspacing="0">
							<tr>
								<td align="left" valign="middle" class="header1"></td>
								<td class="header2">
									���߿���
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
							<div class="con_bg">
								<div class="title">
									<h1>
										<s:property value="epapers.sjMc" />
									</h1>
									<ul>
										<li>
											����ģʽ��
											<s:if test="epapers.model==1">����</s:if>
											<s:elseif test="epapers.model!='1'">�վ�</s:elseif>
										</li>
										<li>
											�Ծ������
											<s:property value="epapers.sjZf" />
											��
										</li>
										<li>
											����ʱ�䣺
											<s:property value="epapers.sjDjsx" />
											����
										</li>
										<li>
											�ο���Ա��
											<s:property value="user.realname" />
										</li>
										<li>
											ʣ��ʱ�䣺
											<font id="remaintime"> <font id="timeh"
												color="#0000ff"></font> <font id="timem" color="#0000ff"></font>
												<font id="times" color="#0000ff"></font> </font>
										</li>
									</ul>
								</div>
								<ul class="first_t">
									<li>
										<%
											int zl = 0;
										%>
										<s:iterator value="equestiontypes" id="type">
											<!--����һ���������� -->
											<s:set name="priority" value="#type.priority" />
											<!--�����Ϲ��ˣ����˳ɷ���������͵� -->
											<s:set name="epaperquestions"
												value="paperquestions.{?#this.equestions.equestiontype.id==#priority}" />
											<!--�жϹ��˺�ļ��ϲ�Ϊ�� -->
											<s:if test="#epaperquestions.size!=0">
												<%
													zl++;
												%>
												<%=arry1[zl - 1]%>��&nbsp;<s:property value="#type.name" />&nbsp;����<s:property
													value="#epaperquestions.size" />��,��<SPAN
													id='<s:property value="#type.id"/>fenshuSpan'></SPAN>�֣�
	<ul class="second_t">
													<li>
														<!-- ������Ŀ -->
														<s:iterator value="#epaperquestions" status="index"
															id="epq">
															<!-- ��ʾ��� -->
															<s:property value="#index.index+1" />
						&nbsp;.&nbsp;<span
																id='<s:property value="%{'answer'+#epq.equestions.stId}"/>span'
																style="color: black"><s:property
																	value="#epq.equestions.stTg" />
															</span>&nbsp;&lt; <span id='<s:property value="#type.id"/>single'><s:property value="#epq.sjStfs" /></span>��&gt;
						<!--<s:if test="#epq.equestions.stWh!=null"><span class="blues">&lt;<s:property value="#epq.equestions.stWh"/>&gt;</span></s:if>
						<s:else>&nbsp;</s:else>
						<s:if test="#epq.equestions.stCc!=null"><span class="blues">&lt;<s:property value="#epq.equestions.stCc"/>&gt;</span></s:if>
						<s:else></s:else>-->
															<ul class="third_t">
																<!-- ����ǵ�ѡ����ѡ����Ҫ������ָ���ʾ -->
																<s:if
																	test="%{#priority==1  ||  #priority==2||#priority==10}">
																	<s:generator separator="||" val="#epq.equestions.stXx"
																		id="querson">
																		<s:iterator status="wenti">
																			<s:if test="%{#priority==1}">
																				<li>
																					<input style="float: left"
																						name='<s:property value="%{'answer'+#epq.equestions.stId}"/>'
																						type="radio"
																						onclick="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>');"
																						value='<s:property value="#request.arry[#wenti.index]"/>'>
																					<s:property value="#request.arry[#wenti.index]" />
																					.
																				</li>
																				<s:property />
																			</s:if>

																			<s:elseif test="%{#priority==2||#priority==10}">
																				<li>
																					<input style="float: left"
																						name='<s:property value="%{'answer'+#epq.equestions.stId}"/>'
																						type=checkbox
																						onclick="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>');"
																						value='<s:property value="#request.arry[#wenti.index]"/>'>
																					<s:property value="#request.arry[#wenti.index]" />
																					.
																				</li>
																				<s:property />
																			</s:elseif>
																		</s:iterator>
																	</s:generator>
																</s:if>
																<!-- ������ж��� -->
																<s:elseif test="#priority==3">
																	<li>
																		<input type="radio"
																			name='<s:property value="%{'answer'+#epq.equestions.stId}"/>'
																			onclick="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>');"
																			value="T" />
																		��ȷ
																		<input type="radio"
																			name='<s:property value="%{'answer'+#epq.equestions.stId}"/>'
																			onclick="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>');"
																			value="F" />
																		����
																	</li>
																</s:elseif>
																<!-- ������ж���˵���� -->
																<s:elseif test="#priority==4">
																	<li>
																		<input type="radio"
																			name='<s:property value="%{'answer'+#epq.equestions.stId}"/>'
																			onclick="changeColor(this,'<s:property value="%{this,'answer'+#epq.equestions.stId}"/>span','1');"
																			value="T" />
																		��ȷ
																		<input type="radio"
																			name='<s:property value="%{'answer'+#epq.equestions.stId}"/>'
																			onclick="changeColor(this,'<s:property value="%{this,'answer'+#epq.equestions.stId}"/>span','1');"
																			value="F" />
																		����
																	</li>
																	<li>
																		&nbsp;˵����
																	</li>
																	<li>
																		&nbsp;
																		<textarea
																			name='<s:property value="%{'answer'+#epq.equestions.stId}"/>sm'
																			rows="5"
																			style="border: 1px solid #90b9c6; width: 96%; font-size: 12px; margin: 0px; text-align: left; padding: 5px;"
																			onchange="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>');"></textarea>
																	</li>
																</s:elseif>
																<!-- �����¼���� -->
																<s:elseif test="#priority==5">
																	<li>
																		<object align="middle"
																			classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95"
																			id='MediaPlayer<s:property value="#epq.equestions.stId"/>'
																			width="400" height="69">
																			<param name="ShowStatusBar" value="-1">
																			<param name="AutoStart" value="0">
																			<param name="Filename"
																				value='<s:property value="#epq.equestions.stFjlj"/>'>
																			<embed type="application/x-oleobject"
																				codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701"
																				flename="mp" src="" width=200 height=50></embed>
																		</object>
																	</li>
																	<li>
																		<textarea
																			name='<s:property value="%{'answer'+#epq.equestions.stId}"/>'
																			rows="5"
																			style="border: 1px solid #90b9c6; width: 96%; font-size: 12px; margin: 0px; text-align: left; padding: 5px;"
																			onchange="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>');"></textarea>
																	</li>
																</s:elseif>
																<!-- ������ʴ��� -->
																<s:elseif
																	test="#priority==7 || #priority==8 || #priority==6||#priority==9||#priority==11||#priority=13">
																	<li>
																		&nbsp;�ش�
																	</li>
																	<li>
																		&nbsp;
																		<textarea
																			name='<s:property value="%{'answer'+#epq.equestions.stId}"/>'
																			rows="5"
																			style="border: 1px solid #90b9c6; width: 96%; font-size: 12px; margin: 0px; text-align: left; padding: 5px;"
																			onchange="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>');"></textarea>
																	</li>
																</s:elseif>
															<!-- ����� -->
																<s:elseif
																	test="#priority==12">
																	<li>
																		&nbsp;�ش�
																	</li>
																	<li>
																		&nbsp;
																		<textarea onpropertychange="if(value.length>220) value=value.substr(0,220)" name='<s:property value="%{'answer'+#epq.equestions.stId}"/>'rows="5" style="border: 1px solid #90b9c6; width: 96%; font-size: 12px; margin: 0px; text-align: left; padding: 5px;"onchange="changeColor(this,'<s:property value="%{'answer'+#epq.equestions.stId}"/>span','<s:property value="%{#priority}"/>');"></textarea>
<span style="text-algin:right">(����220�ַ�)</span>
																	</li>
																</s:elseif>
															</ul>
															
												
														</s:iterator>
<script type="text/javascript">retrieveBook('<s:property value="#type.id"/>fenshuSpan','<s:property value="#type.id"/>single');</script>
													</li>
												</ul>
											</s:if>
										</s:iterator>

										<ul>
											<li>
												<s:submit cssClass="BigButton" value="�ύ���"></s:submit>
											</li>
										</ul>
							</div>
					</td>
				</tr>



			</table>
			<!--������-->
		</s:form>
	<script type="text/javascript">notice()</script>
		<script type="text/javascript">

function submitForm(paperSubmitform){
//����id
var stid='';
//�����
var daan='';
//��ʾ
var al='';
//�Ƿ����
var boo=false;
var wwcsl=0;
<s:iterator value="equestiontypes" id="type">
	<s:set name="priority" value="#type.priority"/>
	<s:set name="epaperquestions" value="paperquestions.{?#this.equestions.equestiontype.id==#priority}"/>
	<s:if test="#epaperquestions.size!=0">
	    <s:iterator value="epaperquestions" id="epaperquestion">
	    	stid='<s:property value="#epaperquestion.equestions.stId"/>';
	    	daan=document.getElementsByName('answer'+stid);
	    	<s:if test="%{#priority==1 || #priority==2 || #priority==3 || #priority==4}">
	    		for(var i=0;i<daan.length;i++){
	    			if(daan[i].checked){
	    				boo=true;
	    			}
	    		}
	    		if(!boo){
	    			wwcsl++;
	    		}
	    		boo=false;
	    	</s:if>
	    	<s:if test="%{ #priority==6 || #priority==7 || #priority==8|| #priority==9|| #priority==10|| #priority==11|| #priority==12}">
	    		if(daan[0].value.length!=0){
	    			boo=true;
	    		}
	    		if(!boo){
	    			wwcsl++;
	    		}
	    		boo=false;
	    	</s:if>
	    </s:iterator>
    </s:if>
</s:iterator>
if(wwcsl!=0){
	alert('����'+wwcsl+'����Ŀû����ɣ�');
	if(window.confirm("ȷ���ύ��")){
		document.all.aa.value = '1';
		return true;
	}else{
		return false;
	}
}
if(window.confirm("ȷ���ύ��")){
	document.all.aa.value = '1';
	return true;
}else{
	return false;
}
}

function autoSubmit(){
	//����id
var stid='';
//�����
var daan='';
//��ʾ
var al='';
//�Ƿ����
var boo=false;
var wwcsl=0;
<s:iterator value="equestiontypes" id="type">
	<s:set name="priority" value="#type.priority"/>
	<s:set name="epaperquestions" value="paperquestions.{?#this.equestions.equestiontype.id==#priority}"/>
	<s:if test="#epaperquestions.size!=0">
	    <s:iterator value="epaperquestions" id="epaperquestion">
	    	stid='<s:property value="#epaperquestion.equestions.stId"/>';
	    	daan=document.getElementsByName('answer'+stid);
	    	<s:if test="%{#priority==1 || #priority==2 || #priority==3 || #priority==4}">
	    		for(var i=0;i<daan.length;i++){
	    			if(daan[i].checked){
	    				boo=true;
	    			}
	    		}
	    		if(!boo){
	    			wwcsl++;
	    		}
	    		boo=false;
	    	</s:if>
	    	<s:if test="%{ #priority==6 || #priority==7 || #priority==8}">
	    		if(daan[0].value.length!=0){
	    			boo=true;
	    		}
	    		if(!boo){
	    			wwcsl++;
	    		}
	    		boo=false;
	    	</s:if>
	    </s:iterator>
    </s:if>
</s:iterator>
if(wwcsl!=0){
	alert('ʱ�����������'+wwcsl+'����Ŀû����ɣ�ϵͳ�Զ��ύ��');
	document.all.aa.value = '1';
	document.paperSubmitform.submit();
	
}else{
	alert("ϵͳ�Զ��ύ��");
    document.all.aa.value = '1';
	document.paperSubmitform.submit();
}

}
/**�������Ժ�ı���ɫ*/
function changeColor(mid,id,priority){
	if(priority=='1'||priority=='3'){
		document.getElementById(id).style.color='#03C'; //1�ǵ�ѡ��������ж���
	}else if(priority=='2'||priority=='10'){
		document.getElementById(id).style.color='#03C';
		var tagName=document.getElementsByName(mid.name);
		var booleanflag=false;
		for(var i=0;i<tagName.length;i++){
			if(tagName[i].checked){
				booleanflag=true;
			}
		}
		if(booleanflag){document.getElementById(id).style.color='#03C';
		 }else{
			document.getElementById(id).style.color='black'; 
		}
	}else{
		smLength=mid.value.length;
		if(smLength>0){
			document.getElementById(id).style.color='#03C';
		}else{
			document.getElementById(id).style.color='black';
		}
	}
}
</script>
	</BODY>
</HTML>
