package com.wondersgroup.falcon.model.rc.simplecode;

public interface IConst {
	public interface RcCategory{
		public interface Id {
			public static final String JGXN_TS = "21"; //机关效能投诉
			public static final String JGXN_JB = "22";   //机关效能举报
			public static final String JGXN_JY = "23";   //机关效能建议
			public static final String JC_TS = "11";	   //监察投诉
			public static final String JC_JB = "12";     //监察举报
			public static final String JC_JY = "13";     //监察建议
		}
		public interface Stat {
			public static final String TS = "1";  //监察投诉
			public static final String JB = "2";  //监察举报
			public static final String JY = "3";  //监察建议
			public static final String TS_JGXN = "4";  //机关效能投诉
			public static final String JB_JGXN = "5";  //机关效能举报
			public static final String JY_JGXN = "6";  //机关效能建议
		}
	}
	public interface RcForm {
		public interface Stat {//状态
			public static final String ACTIVE = "00";//活动
			public static final String END = "01";	//已结束
			public static final String ENDCASE = "02";	//已结案
			public static final String RESEARCH = "03";	//满意度调查
		}
		
		public interface Hid {//保密性
			public static final String HIDDEN_ON = "1";//保密
			public static final String HIDDEN_OFF = "0";//公开
		}
		
		public interface Resultable {
			public static final String YES = "1"; //要求告之结果
			public static final String NO = "2";  //不要求告之结果
		}
	}
	
	public interface RcReference {
		public interface Stat {//单位范畴
			public static final String INSIDE = "00";//内部
			public static final String OUTSIDE = "01";//外部
		}
	}
	
	public interface RcCaller {
		public interface Sex {
			public static final String MALE = "1";//男
			public static final String FEMALE = "0";//女
			public static final String UNKNOWN = "2";//未知
		}
		
		public interface Household {
			public static final String UNKNOWN = "0"; //未知
			public static final String COUNTRY = "1"; //农村
			public static final String NONE_COUNTRY = "2"; //非农
		}
	}
	
	public interface RrReq {
		public interface Confirm {
			public static final String CHECKING = "1"; //待审核
			public static final String CHECKED = "2";	 //已审核
			public static final String REJECT = "3";     //已拒绝
		}
	}
	
	public interface RrSubject {
		public interface SubjectStat {
			public static final String OPENING = "1"; //开放
			public static final String CLOSED = "2";  //结束
		}
	}
	
	public interface RrQuestion {
		public interface QuestionType {
			public static final String INTERLOCUTION = "01";//问答式
			public static final String SELECTION = "02";//选择式
			public static final String RESEARCH = "03"; //调查式
		}
		 
		public interface AnswerType {
			public static final String MONO = "01";//单选
			public static final String SONO = "02";//多选
			public static final String SELF = "03";//回答
		}
	}
	
	public interface RrResource{
		public interface Stat{
			public static final String ON = "1";//正在调查中
			public static final String OFF = "2";//调查已结束
			public static final String FAILURE = "3"; //调查不成功
		}
	}
	
	public interface IpInform {
		public interface Stat {
			public static final String READY = "1"; //准备中
			public static final String SUCCESS = "2"; //成功
			public static final String FAILURE = "3"; //不成功
		}
	}
	
	
	
}
