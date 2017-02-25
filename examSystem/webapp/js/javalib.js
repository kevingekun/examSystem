
function lTrim(sString)
{
	var i;
	if (sString.length < 1)
		return "";
	for (i = 0; i < sString.length; i++)
		if (sString.charAt(i) != " ")
			break;
	if (i >= sString.length)
		return("");
	else
		return(sString.substring(i, sString.length));
}
function rTrim(sString)
{
	var i;
	if (sString.length < 1)
		return "";
	for (i = (sString.length - 1); i >= 0; i--)
		if (sString.charAt(i) != " ")
			break;
	if (i < 0)
		return("");
	else
		return(sString.substring(0, i + 1));
}
function allTrim(sString)
{
	if (sString.length < 1)
		return "";
	var dString = lTrim(sString);
	dString = rTrim(dString);
	return dString;
}
function isEmpty(sString)
{
	var s = allTrim(sString);
	if (s.length < 1)
		return true;
	else
		return false;
}
function getLength(sString)
{
	var s = allTrim(sString);
	return s.length;
}
function replace(sSource, sFind, sReplace)
{
	var pos1 = sSource.indexOf(sFind);
	while (pos1 > 0)
	{
		if ((pos1 + sFind.length) >= sSource.length)
		{
			sSource = sSource.substring(1, pos1) + sReplace;
		}
		else
		{
			sSource = sSource.substring(0, pos1) + sReplace + sSource.substring(pos1 + sFind.length, sSource.length);
		}

		pos1 = sSource.indexOf(sFind);
	}
	return sSource;
}
function isNumeric(sString)
{
	var s = allTrim(sString);
	if (isNaN(s) == true)
		return false;
	else
		return true;
}
function isInt(sString)
{
	var s = allTrim(sString);
	if (isNumeric(s) == false)
		return false;

	if ((s % 1) == 0)
		return true;
	else
		return false;
}
function DateIsValid(yy, mm, dd)
{
	var t, d, s, minDate, maxDate;

	d = new Date(yy, mm - 1, dd);
	if (isNaN(yy) || isNaN(mm) || isNaN(dd))
		return new Date();

	minDate = new Date(1970, 0, 1);
	maxDate = new Date(9999, 11, 31);
	if (d < minDate)
		d = minDate;
	if (d > maxDate)
		d = maxDate;
	return d;
}
function DateIsValid1(strDate)
{
	strDate = replace(strDate, "-", "/");
	var i = Date.parse(strDate);
        alert(i);

	if (isNaN(i))
		return "";

	var minDate = Date.parse("1/1/1970");
	var maxDate = Date.parse("12/31/9999");

	if (i > maxDate || i < minDate)
		return "";

	var d = new Date(i);
	var s = d.getYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();

        return s;
}

//���ڸ�ʽ��YYYY-MM-DD
function isDate(strDate){
   var strSeparator = "-"; //���ڷָ���
   var strDateArray;
   var intYear;
   var intMonth;
   var intDay;
   var boolLeapYear;

   strDateArray = strDate.split(strSeparator);
   if(strDateArray.length!=3) return false;

   // ���յĳ��ȱ�����2
   if(strDateArray[1].length!=2) return false;
   if(strDateArray[2].length!=2) return false;

   intYear = parseInt(strDateArray[0],10);
   intMonth = parseInt(strDateArray[1],10);
   intDay = parseInt(strDateArray[2],10);

   if(isNaN(intYear)||isNaN(intMonth)||isNaN(intDay)) return false;

   if(intMonth>12||intMonth<1) return false;

   if((intMonth==1||intMonth==3||intMonth==5||intMonth==7||intMonth==8||intMonth==10||intMonth==12)&&(intDay>31||intDay<1)) return false;

   if((intMonth==4||intMonth==6||intMonth==9||intMonth==11)&&(intDay>30||intDay<1)) return false;

   if(intMonth==2){
      if(intDay<1) return false;

      boolLeapYear = false;
      if((intYear%100)==0){
         if((intYear%400)==0) boolLeapYear = true;
      }
      else{
         if((intYear%4)==0) boolLeapYear = true;
      }

      if(boolLeapYear){
         if(intDay>29) return false;
      }
      else{
         if(intDay>28) return false;
      }
   }

   return true;
}

//���ڸ�ʽ��YYYY-MM-DD
//����һ�������������ڱ�����û�����³��ȵ�����
function isDate2(strDate){
   var strSeparator = "-"; //���ڷָ���
   var strDateArray;
   var intYear;
   var intMonth;
   var intDay;
   var boolLeapYear;

   strDateArray = strDate.split(strSeparator);

   if(strDateArray.length!=3) return false;

   intYear = parseInt(strDateArray[0],10);
   intMonth = parseInt(strDateArray[1],10);
   intDay = parseInt(strDateArray[2],10);

   if(isNaN(intYear)||isNaN(intMonth)||isNaN(intDay)) return false;

   if(intMonth>12||intMonth<1) return false;

   if((intMonth==1||intMonth==3||intMonth==5||intMonth==7||intMonth==8||intMonth==10||intMonth==12)&&(intDay>31||intDay<1)) return false;

   if((intMonth==4||intMonth==6||intMonth==9||intMonth==11)&&(intDay>30||intDay<1)) return false;

   if(intMonth==2){
      if(intDay<1) return false;

      boolLeapYear = false;
      if((intYear%100)==0){
         if((intYear%400)==0) boolLeapYear = true;
      }
      else{
         if((intYear%4)==0) boolLeapYear = true;
      }

      if(boolLeapYear){
         if(intDay>29) return false;
      }
      else{
         if(intDay>28) return false;
      }
   }

   return true;
}

//������ڸ�ʽ��YYYY-MM-DD hh:mm
function isLongDate1(strDate) {
	var intHour;
	var intMinute;

	//��鳤�ȣ��������16���򱨴�
	if(strDate.length != 16) return false;

	//	���ǰ10λ�Ƿ�������YYYY-MM-DD�����ڸ�ʽ
	var date1 = strDate.substr(0,10);
	if(!isDate(date1))	return false;

	//	���ʱ������Ƿ���ȷ������ʾΪ23:59
	intHour = parseInt(strDate.substr(11,2));
	intMinute = parseInt(strDate.substr(14,2));
	if(isNaN(intHour)||isNaN(intMinute)) return false;
	if(intHour > 23) return false;
	if(intMinute > 59) return false;

	return true;
}

function ValidEmail(item)
{
/*
this function is progammed by wang haitao and updated by Fan Yuansheng
*/
	var etext
	var elen
	var i
	var aa
	etext=item
	elen=etext.length
	if (elen<5)
		return true;
	i= etext.indexOf("@",0)
	if (i==0 || i==-1 || i==elen-1)
		return true;
	if (etext.indexOf("@",i+1)!=-1)
		return true;
	if (etext.indexOf("..",i+1)!=-1)
		return true;
	i=etext.indexOf(".",0)
	if (i==0 || i==-1 || etext.charAt(elen-1)=='.')
		return true;
	if ( etext.charAt(0)=='-' ||  etext.charAt(elen-1)=='-')
		return true;
	if ( etext.charAt(0)=='_' ||  etext.charAt(elen-1)=='_')
		return true;
	for (i=0;i<=elen-1;i++)
		{
		 aa=etext.charAt(i)
		if (!((aa=='.') || (aa=='@') || (aa=='-') ||(aa=='_') || (aa>='0' && aa<='9') || (aa>='a' && aa<='z') || (aa>='A' && aa<='Z')))
			return true;
		}
	return false;
}

function testfun()
{
	alert('Hello world');
}

function DateToString(o_date)
{
	var s_date;
	s_date=(o_date.getMonth()+1)+'/'+o_date.getDate()+'/'+o_date.getYear();
	return s_date;
}

function isDataValid(DataType,DataValue)
{
	//�˺���ֻ����������������
	//1.����
	//2.�ַ�
	//3.����
	//4.����
        var ret;

	if(DataType=="����" || DataType=="����" || DataType=="NUMBER" || DataType=="INTEGER" || DataType=="NUMERIC")
	{
	//	window.alert("type is numeric: "+ DataValue);
                ret = isNumeric(DataValue);
                if(!ret)
                  window.alert("����ȷ��������");
		return ;
	}

	if(DataType=="CHAR")
	{
	//	window.alert("type is char: "+ DataValue);
                ret = isEmpty(DataValue);
                if(ret)
                  window.alert("������ֵ");
		return !ret;
	}

	if(DataType=="DATE")
	{
	//	window.alert("type is Date: "+ DataValue);
                ret = isDate(DataValue);
                if(!ret)
                  window.alert("�������������������������ʽ\n\r4λ��ݣ�2λ�·ݣ�2λ�գ�YYYY-MM-DD��");
                return ret;
	}//
//	window.alert("type is unknown: "+ DataValue);
	return true;
}

function isDataValid2(DataType,DataValue,required)
{
	//�˺���ֻ����������������
	//1.����
	//2.�ַ�
	//3.����
	//4.����

    //�ú����ܴ��������
    var ret;

	if(DataType=="����" || DataType=="����" || DataType=="NUMBER" || DataType=="INTEGER" || DataType=="NUMERIC" )
	{

               // window.alert("type is numeric: "+ DataValue);
                if(isEmpty(DataValue) && !required) return true;
                if(isEmpty(DataValue) && required){
                 window.alert("������ֵ");
                 return false;}
                ret = isNumeric(DataValue);
                if(!ret)
                  window.alert("����ȷ��������");
		return ret;
	}

	if(DataType=="CHAR")
	{
                ret = isEmpty(DataValue);
                if(!required) return true;
                if(ret)
                  window.alert("������ֵ");
		return !ret;
	}

	if(DataType=="DATE")
	{
                if(isEmpty(DataValue) && !required) return true;
                ret = isDate(DataValue);
                if(!ret)
                  window.alert("�������������������������ʽ\n\r4λ��ݣ�2λ�·ݣ�2λ�գ�YYYY-MM-DD��");
                return ret;
	}

	if(DataType=="LONGDATE1")
	{
                if(isEmpty(DataValue) && !required) return true;
                ret = isLongDate1(DataValue);
                if(!ret)
                  window.alert("�������������������������ʽ\n\r�������磨YYYY-MM-DD hh:mm��");
                return ret;
	}
//	window.alert("type is unknown: "+ DataValue);
	return true;
}

function isDataValid3(DataType,DataValue)
{
        //�˺���ֻ����������������
        //1.����
        //2.�ַ�
        //3.����
        //4.����
  //  ֻ������Ч��У�飨boolean�ͣ���������ʾ������Ϣ
        var ret;

        if(DataType=="����" || DataType=="����" || DataType=="NUMBER" || DataType=="INTEGER" || DataType=="NUMERIC")
        {
                ret = isNumeric(DataValue);
                return ret;
        }

        if(DataType=="CHAR")
        {
                ret = isEmpty(DataValue);
                return !ret;
        }

        if(DataType=="DATE")
        {
              ret = isDate(DataValue);
              return ret;
        }
        return true;
}

