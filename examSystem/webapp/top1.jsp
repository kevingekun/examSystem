<%@ page contentType = "text/html;charset=gbk" %>
<head>
<title>topҳ��</title>
<script type=text/javascript>
/**
*�˵��Ĺ���,��Ҫ�󶨵�onload
*/
startList = function() {
 if (document.all&&document.getElementById) {
  dropmenuRoot = document.getElementById("dropmenu");
   for (i=0; i<dropmenuRoot.childNodes.length; i++) {
    node = dropmenuRoot.childNodes[i];
    if (node.nodeName=="LI") {
     node.onmouseover=function() {
     this.className+=" over";
     //��ʼ��ˮ
       for(j=0;j<this.childNodes.length;j++){
      	 if (this.childNodes[j].nodeName=="UL"){
        		if (this.childNodes[j].childNodes[0].nodeName=="LI"){
         				//if (this.offsetLeft+this.childNodes[j].childNodes.length*this.childNodes[j].childNodes[0].offsetWidth-dropmenuRoot.offsetLeft>dropmenuRoot.offsetWidth){ 
        					var len=this.childNodes[j].childNodes.length*this.childNodes[j].childNodes[0].offsetWidth; //�˵��ĳ���
        					this.childNodes[j].style.width=len+50+"px"; //��ul���ÿ�� ����Ӳ˵��Ƚ϶࣬���������Ŀ�ȣ���ôul�Ŀ�Ⱦ��������Ŀ��
        					this.childNodes[j].style.marginLeft="0px";
        					len =this.offsetLeft+len-dropmenuRoot.offsetLeft-dropmenuRoot.offsetWidth;
        					//(len>this.offsetLeft-dropmenuRoot.offsetLeft) ? this.childNodes[j].style.marginLeft=-(this.offsetLeft-dropmenuRoot.offsetLeft) : this.childNodes[j].style.marginLeft=-len+"px";        
        					//this.childNodes[j].style.marginLeft=
        				//}
        		}
       		}
      	}
    }
    //��ˮ���
    node.onmouseout=function() {
     this.className=this.className.replace(" over", "");
    }
   }
  }
 }
}
window.onload=startList;

function secBoard2(n,path)
{
 		parent.frames.LeftFrm.location = path;			
}
</script>
</head>
<style type="text/css">
BODY {
	MARGIN: 0px
}
BODY,td {
    font-size: 12px;
}
ul { margin: 0; padding: 0; }
li { list-style-position: outside; list-style: none;}
a { text-decoration: none;  color: #666;}
ul#dropmenu,ul#dropmenu ul{
 margin: 0 auto;
 text-align: left;
 padding: 0;
 list-style: none;
 z-index: 99;
}
ul#dropmenu {
 width:auto
 display: block;
 height: 24px;
 clear: both;
 border: 0px solid #ccc;
 text-align: left;
 background: #fff;
}
ul#dropmenu li {
 position: relative;
 z-index: 999;
 float: left;
 
}
ul#dropmenu ul li{
 float: left;
 display: block;
}
ul#dropmenu ul {
 height: auto;
 position: absolute;
 text-align: center;
 left: 0px;
 display: none;
 border: solid 0px #ccc;
}
/*��������Ӳ˵��͸��˵���ʱ�����˵�����ʽ*/
ul#dropmenu li.over a,ul#dropmenu li:hover a{
 background: #f6f6f6;
 color:#000000;
 font-weight:bolder;
}
/*���Ӳ˵�����ʽ���*/
ul#dropmenu li.over ul a,ul#dropmenu li:hover ul a{
 font-weight: normal;
 color: #666;
 background: #fff;
}
/*�Ӳ˵���hover��ʽ*/
ul#dropmenu li.over ul a:hover,ul#dropmenu li:hover ul a:hover{
 font-weight: normal;
 color:#000000;
 font-weight: bolder;
 background: #f6f6f6;
 width:110px;
}
/* Styles for Menu Items */
ul#dropmenu a {
 display: block;
 padding: 0 0 0 10px;
 width: 106px;
 width: 96px;
 color: #666;
 line-height: 24px;
}
ul#dropmenu ul li{
 width: 110px;
 border: 0;
}
/* End */
ul#dropmenu ul a {
 FILTER: progid:DXImageTransform.Microsoft.Alpha(opacity=70);   /*   ͸��  */
 padding: 2px 0px 2px  10px;
 border: 0;
 width: 120px; 
 width: 110px;/* Sub Menu Styles */
}
ul#dropmenu li:hover ul,ul#dropmenu  li.over ul {
 display: block; /* The magic */
}
</style>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="523"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="1024" height="106">
      <param name="movie" value="images/banner_new.swf" />
      <param name="quality" value="high" />
      <embed src="images/banner_new.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="1024" height="106"></embed>
    </object></td>
    <td width="*" background="images/banner_background3.jpg">&nbsp;</td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">

	<tr align="left" > 
		<td height="24px"   bgcolor="#0C73C7" background="images/bg1_jinshu.gif"> 
			<div id="nav">
			  <ul id="dropmenu"  style="background-image:url(images/bg1_jinshu.gif)">
			  	<li><a href="#">������</a>
			    	<ul>
			        <li><a href="#" onClick="secBoard2('20','question/question_menu.jsp')" >������</a></li>
			      </ul>	
			    </li>
			  	<li><a href="#">�Ծ����</a>
			    	<ul>
			        <li><a href="#" onClick="secBoard2('21','paper/paper_menu.jsp')" >�Ծ����</a></li>
			        <li><a href="#" onClick="secBoard2('22','answer/answer_menu.jsp')">������</a></li>
			      </ul>	
			    </li>
			    <li><a href="#">���߿���</a>
			      <ul>
			        <li><a href="#" onClick="secBoard2('0','examPaperAction.action')">���߿���</a></li>
			        <li><a href="#" onclick="secBoard2('3','myPaperAction.action?epaper.sjZt=-1')">�ҵ��Ծ�</a></li>
			      </ul>
			    </li>
			    <li><a href="#">������ϰ</a>
			      <ul>
			        <li><a href="#" onClick="secBoard2('5','admin/exam_question_manage.jsp')">������ϰ</a></li>
			        <!--  <li><a href="#" onClick="secBoard2('6','admin/exam_testpaper_manage.jsp')">��ϰ��ѯ</a></li>-->
			      </ul>
			    </li>
			    <li><a href="#">�ɼ�����</a>
			      <ul>
			        <li><a href="#" onClick="secBoard2('15','answerpaperpmquery.action')" >�ɼ�����</a></li>
			        <!--  <li><a href="#" onClick="secBoard2('16','admin/train_example_manage.jsp')">�ɼ���ѯ</a></li>-->
			      </ul>
			    </li>
			    
			  </ul>
			</div>
		</td>
	</tr>
</table>
<table  cellspacing=0 cellpadding=0 width=100% id=mainTable border=0>
	<tr height=24  align=center >
		<td background="images/bg_jinshu.gif"></td>
	</tr>
</table>
</body>
</html> 
