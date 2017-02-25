function fPopUpCalendarDlg(textname,startYear,endYear,q)
{
	var pattern = /^(19|20)([0-9]){2}$/;
	flag=pattern.test(startYear);
	if(!flag)startYear=1900;
	flag=pattern.test(endYear);
	if(!flag)endYear=2050;
	
	today=new Date();
					
	var currentDate = today.getYear() + "-" + today.getMonth() + "-" + today.getDay();
				
	var arguments = new Array(startYear,endYear,0,0,0);
					
	var pattern = /^(19|20)([0-9]){2}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
	flag=pattern.test(currentDate);
	if(flag)
	{
		iYear=currentDate.substring(0,4);
		iMonth=currentDate.substring(5,7);
		iDay=currentDate.substring(8,10);
		arguments = new Array(startYear,endYear,iYear,iMonth,iDay);
	}

	showx = event.screenX - event.offsetX + 18; 
	showy = event.screenY - event.offsetY - 210; 
					
	var features =
		'dialogWidth:'  + 10 + 'px;' +
		'dialogHeight:' + 180 + 'px;' +
		'dialogLeft:'   + showx     + 'px;' +
		'dialogTop:'    + showy     + 'px;' +
		'directories:no; location:no; status:no; menubar:no; toolbar=no;scrollbars=no;Resizeable=no; help:0';
					
	retval = window.showModalDialog("../js/calendar.htm", arguments , features );
					
	if( retval != null ){
		textname.value = retval;
	}
}

