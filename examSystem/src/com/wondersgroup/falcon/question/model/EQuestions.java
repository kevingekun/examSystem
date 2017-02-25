package com.wondersgroup.falcon.question.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.falcon.Util.StringUtil;
import com.wondersgroup.falcon.question.servlet.QuestionServlet;
import com.wondersgroup.kaoshi.bo.Tjobsubject;

/**
 * EQuestions entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EQuestions implements java.io.Serializable {
	private static final Log log = LogFactory.getLog(EQuestions.class);

	// Fields

	private long stId; // 题目ID
	private EImportance eimportance; // 难易度
	private EBusinesstype ebusinesstype; // 业务日志
	private EQuestiontype equestiontype; // 试题类型
	private String stCc; // 题目来源
	private String stFjlj; // 备注
	private String bxType;// 重要程度
	private String stTg; // 题干

	// private long stLxId;; //试题类型
	
	private String stXx; // 试题选项
	private String[] manyXx;

	private String stDa; // 试题答案
	private String[] manyDa;

	private String stDasm; // 试题答案说明
	
	private long stKszy; // 考试专用 1 是 0非
	private Date stLrsj; // 试题录入时间
	private String stMtryId; // 命题人员ID
	private Date stXgsj; // 试题修改时间
	
	private String stWh; // 等级
	private long stJyxgcs; // 建议修改次数
	private long stScbz; //
	// private Set EAnswerquestionses = new HashSet(0);//答卷试题集
	// private Set EPaperquestionses = new HashSet(0);//试卷试题集
	private Date stlrsjend;
	
	// Constructors
	// 临时标识
	private int has;
	private String stNodeName;//题库id
	private String stNodeId;
	private long stModefy;
	private long stCheck;

	private String stSyryId; // 出题专家
	private String stAdvice; // 审阅人员意见
	private Date stSysj;
	
	private String stSjid;//所属试卷
	private byte[] stImg;//图片二进制
	
	private String stFz;//本题分值
	private byte[] stImgA;//A选项图片
	private byte[] stImgB;
	private byte[] stImgC;
	private byte[] stImgD;
	private byte[] stImgE;
	private byte[] stImgF;
	private byte[] stImgG;

	public long getStCheck() {
		return stCheck;
	}

	public void setStCheck(long stCheck) {
		this.stCheck = stCheck;
	}

	public String getStNodeName() {
		return stNodeName;
	}

	public void setStNodeName(String stNodeName) {
		this.stNodeName = stNodeName;
	}

	public String getStNodeId() {
		return stNodeId;
	}

	public void setStNodeId(String stNodeId) {
		this.stNodeId = stNodeId;
	}

	public static Log getLog() {
		return log;
	}

	public void setManyXx(String[] manyXx) {
		this.manyXx = manyXx;
	}

	public void setManyDa(String[] manyDa) {
		this.manyDa = manyDa;
	}

	public int getHas() {
		return has;
	}

	public void setHas(int has) {
		this.has = has;
	}

	/** default constructor */
	public EQuestions() {
	}

	/** minimal constructor */
	public EQuestions(EImportance eimportance, EBusinesstype eBusinesstype,
			EQuestiontype equestiontype, String stTg, String stDa, long stKszy,
			long stLxId) {
		this.eimportance = eimportance;
		this.equestiontype = equestiontype;
		this.ebusinesstype = eBusinesstype;
		this.stTg = stTg;
		this.stDa = stDa;
		this.stKszy = stKszy;
		// this.stLxId = stLxId;
	}

	/** full constructor */
	public EQuestions(EImportance eimportance, EBusinesstype eBusinesstype,
			EQuestiontype equestiontype, long stLxId, String stTg, String stXx,
			String stDa, String stDasm, String stFjlj, long stKszy,
			Date stLrsj, String stMtryId, Date stXgsj, String stCc,
			String stWh, long stJyxgcs, long stScbz, String bxType
	// ,Set EAnswerquestionses
	// ,Set EPaperquestionses
	) {
		this.eimportance = eimportance;
		this.ebusinesstype = eBusinesstype;
		this.equestiontype = equestiontype;
		this.stTg = stTg;
		this.stXx = stXx;
		this.stDa = stDa;
		this.stDasm = stDasm;
		this.stFjlj = stFjlj;
		this.stKszy = stKszy;
		this.stLrsj = stLrsj;
		this.stMtryId = stMtryId;
		this.stXgsj = stXgsj;
		this.stCc = stCc;
		this.stWh = stWh;
		this.stJyxgcs = stJyxgcs;
		this.stScbz = stScbz;
		this.bxType = bxType;
		// this.EAnswerquestionses = EAnswerquestionses;
		// this.EPaperquestionses = EPaperquestionses;
	}

	// -------------------------------数据处理
	public String[] getManyXx() {

		return StringUtil.stringSplit(this.stXx, "||");
	}

	public String[] getManyDa() {

		return StringUtil.stringSplit(this.stDa, "||");
	}

	// -------------------------------

	// Property accessors

	public long getStId() {
		return this.stId;
	}

	public void setStId(long stId) {
		this.stId = stId;
	}

	public EImportance getEimportance() {
		return eimportance;
	}

	public void setEimportance(EImportance eimportance) {
		this.eimportance = eimportance;
	}

	public EBusinesstype getEbusinesstype() {
		return ebusinesstype;
	}

	public void setEbusinesstype(EBusinesstype ebusinesstype) {
		this.ebusinesstype = ebusinesstype;
	}

	public EQuestiontype getEquestiontype() {
		return equestiontype;
	}

	public void setEquestiontype(EQuestiontype equestiontype) {
		this.equestiontype = equestiontype;
	}

	public String getStTg() {
		return this.stTg;
	}

	private String shortStTg; //

	public void setShortStTg(String shortStTg) {
		this.shortStTg = shortStTg;
	}

	public String getShortStTg() {
		String str = "";
		if (this.shortStTg != null && this.shortStTg.length() > 20) {
			str = this.shortStTg.substring(0, 20) + "...";
			// log.info("if length："+str.length()+"  - "+str);
		} else {
			str = this.shortStTg;
			// log.info("else length："+str.length()+" - "+str);
		}
		return str;
	}

	public void setStTg(String stTg) {
		this.stTg = stTg;
	}

	public String getStXx() {
		return this.stXx;
	}

	public void setStXx(String stXx) {
		this.stXx = stXx;
	}

	public String getStDa() {
		return this.stDa;
	}

	public void setStDa(String stDa) {
		this.stDa = stDa;
	}

	public String getStDasm() {
		return this.stDasm;
	}

	public void setStDasm(String stDasm) {
		this.stDasm = stDasm;
	}

	public String getStFjlj() {
		return this.stFjlj;
	}

	public void setStFjlj(String stFjlj) {
		this.stFjlj = stFjlj;
	}

	public long getStKszy() {
		return this.stKszy;
	}

	public void setStKszy(long stKszy) {
		this.stKszy = stKszy;
	}

	public Date getStLrsj() {
		return this.stLrsj;
	}

	public void setStLrsj(Date stLrsj) {
		this.stLrsj = stLrsj;
	}

	public String getStMtryId() {
		return this.stMtryId;
	}

	public void setStMtryId(String stMtryId) {
		this.stMtryId = stMtryId;
	}

	public Date getStXgsj() {
		return this.stXgsj;
	}

	public void setStXgsj(Date stXgsj) {
		this.stXgsj = stXgsj;
	}

	public String getStCc() {
		return this.stCc;
	}

	public void setStCc(String stCc) {
		this.stCc = stCc;
	}

	public String getStWh() {
		return this.stWh;
	}

	public void setStWh(String stWh) {
		this.stWh = stWh;
	}

	public long getStJyxgcs() {
		return this.stJyxgcs;
	}

	public void setStJyxgcs(long stJyxgcs) {
		this.stJyxgcs = stJyxgcs;
	}

	public long getStScbz() {
		return this.stScbz;
	}

	public void setStScbz(long stScbz) {
		this.stScbz = stScbz;
	}

	public Date getStlrsjend() {
		return stlrsjend;
	}

	public String getBxType() {
		return bxType;
	}

	public void setBxType(String bxType) {
		this.bxType = bxType;
	}

	public void setStlrsjend(Date stlrsjend) {
		this.stlrsjend = stlrsjend;
	}

	public long getStModefy() {
		return stModefy;
	}

	public void setStModefy(long stModefy) {
		this.stModefy = stModefy;
	}

	public String getStSyryId() {
		return stSyryId;
	}

	public void setStSyryId(String stSyryId) {
		this.stSyryId = stSyryId;
	}

	public String getStAdvice() {
		return stAdvice;
	}

	public void setStAdvice(String stAdvice) {
		this.stAdvice = stAdvice;
	}

	public Date getStSysj() {
		return stSysj;
	}

	public void setStSysj(Date stSysj) {
		this.stSysj = stSysj;
	}

	public String getStSjid() {
		return stSjid;
	}

	public void setStSjid(String stSjid) {
		this.stSjid = stSjid;
	}

	public byte[] getStImg() {
		return stImg;
	}

	public void setStImg(byte[] stImg) {
		this.stImg = stImg;
	}

	public String getStFz() {
		return stFz;
	}

	public void setStFz(String stFz) {
		this.stFz = stFz;
	}

	public byte[] getStImgA() {
		return stImgA;
	}

	public void setStImgA(byte[] stImgA) {
		this.stImgA = stImgA;
	}

	public byte[] getStImgB() {
		return stImgB;
	}

	public void setStImgB(byte[] stImgB) {
		this.stImgB = stImgB;
	}

	public byte[] getStImgC() {
		return stImgC;
	}

	public void setStImgC(byte[] stImgC) {
		this.stImgC = stImgC;
	}

	public byte[] getStImgD() {
		return stImgD;
	}

	public void setStImgD(byte[] stImgD) {
		this.stImgD = stImgD;
	}

	public byte[] getStImgE() {
		return stImgE;
	}

	public void setStImgE(byte[] stImgE) {
		this.stImgE = stImgE;
	}

	public byte[] getStImgF() {
		return stImgF;
	}

	public void setStImgF(byte[] stImgF) {
		this.stImgF = stImgF;
	}

	public byte[] getStImgG() {
		return stImgG;
	}

	public void setStImgG(byte[] stImgG) {
		this.stImgG = stImgG;
	}

	// public Set getEAnswerquestionses() {
	// return this.EAnswerquestionses;
	// }

	// public void setEAnswerquestionses(Set EAnswerquestionses) {
	// this.EAnswerquestionses = EAnswerquestionses;
	// }

	// public Set getEPaperquestionses() {
	// return this.EPaperquestionses;
	// }

	// public void setEPaperquestionses(Set EPaperquestionses) {
	// this.EPaperquestionses = EPaperquestionses;
	// }

}