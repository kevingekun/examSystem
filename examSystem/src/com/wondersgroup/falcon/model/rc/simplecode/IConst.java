package com.wondersgroup.falcon.model.rc.simplecode;

public interface IConst {
	public interface RcCategory{
		public interface Id {
			public static final String JGXN_TS = "21"; //����Ч��Ͷ��
			public static final String JGXN_JB = "22";   //����Ч�ܾٱ�
			public static final String JGXN_JY = "23";   //����Ч�ܽ���
			public static final String JC_TS = "11";	   //���Ͷ��
			public static final String JC_JB = "12";     //���ٱ�
			public static final String JC_JY = "13";     //��콨��
		}
		public interface Stat {
			public static final String TS = "1";  //���Ͷ��
			public static final String JB = "2";  //���ٱ�
			public static final String JY = "3";  //��콨��
			public static final String TS_JGXN = "4";  //����Ч��Ͷ��
			public static final String JB_JGXN = "5";  //����Ч�ܾٱ�
			public static final String JY_JGXN = "6";  //����Ч�ܽ���
		}
	}
	public interface RcForm {
		public interface Stat {//״̬
			public static final String ACTIVE = "00";//�
			public static final String END = "01";	//�ѽ���
			public static final String ENDCASE = "02";	//�ѽ᰸
			public static final String RESEARCH = "03";	//����ȵ���
		}
		
		public interface Hid {//������
			public static final String HIDDEN_ON = "1";//����
			public static final String HIDDEN_OFF = "0";//����
		}
		
		public interface Resultable {
			public static final String YES = "1"; //Ҫ���֮���
			public static final String NO = "2";  //��Ҫ���֮���
		}
	}
	
	public interface RcReference {
		public interface Stat {//��λ����
			public static final String INSIDE = "00";//�ڲ�
			public static final String OUTSIDE = "01";//�ⲿ
		}
	}
	
	public interface RcCaller {
		public interface Sex {
			public static final String MALE = "1";//��
			public static final String FEMALE = "0";//Ů
			public static final String UNKNOWN = "2";//δ֪
		}
		
		public interface Household {
			public static final String UNKNOWN = "0"; //δ֪
			public static final String COUNTRY = "1"; //ũ��
			public static final String NONE_COUNTRY = "2"; //��ũ
		}
	}
	
	public interface RrReq {
		public interface Confirm {
			public static final String CHECKING = "1"; //�����
			public static final String CHECKED = "2";	 //�����
			public static final String REJECT = "3";     //�Ѿܾ�
		}
	}
	
	public interface RrSubject {
		public interface SubjectStat {
			public static final String OPENING = "1"; //����
			public static final String CLOSED = "2";  //����
		}
	}
	
	public interface RrQuestion {
		public interface QuestionType {
			public static final String INTERLOCUTION = "01";//�ʴ�ʽ
			public static final String SELECTION = "02";//ѡ��ʽ
			public static final String RESEARCH = "03"; //����ʽ
		}
		 
		public interface AnswerType {
			public static final String MONO = "01";//��ѡ
			public static final String SONO = "02";//��ѡ
			public static final String SELF = "03";//�ش�
		}
	}
	
	public interface RrResource{
		public interface Stat{
			public static final String ON = "1";//���ڵ�����
			public static final String OFF = "2";//�����ѽ���
			public static final String FAILURE = "3"; //���鲻�ɹ�
		}
	}
	
	public interface IpInform {
		public interface Stat {
			public static final String READY = "1"; //׼����
			public static final String SUCCESS = "2"; //�ɹ�
			public static final String FAILURE = "3"; //���ɹ�
		}
	}
	
	
	
}
