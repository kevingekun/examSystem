package com.wondersgroup.falcon.question.model;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.falcon.Util.StringUtil;

public class EQuestionsVO {
	private static final Log log = LogFactory.getLog(EQuestionsVO.class);
	 private String id_job;//工种号 05-03-2-11
     private String rankname;//等级
	
	
     private long stId; // 题目ID
 	private EImportance eimportance; // 难易度
 	private String nyd;
 	private EBusinesstype ebusinesstype; // 业务日志
 	private String stlx;
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
 	
 	private String stSjId;
 	
 	private String stImg;
 	private String stImgA;
 	private String stImgB;
 	private String stImgC;
 	private String stImgD;
 	private String stImgE;
 	private String stImgF;
 	private String stImgG;
 	

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

	public String getId_job() {
		return id_job;
	}

	public void setId_job(String id_job) {
		this.id_job = id_job;
	}

	public String getRankname() {
		return rankname;
	}

	public void setRankname(String rankname) {
		this.rankname = rankname;
	}

	public String getNyd() {
		return nyd;
	}

	public void setNyd(String nyd) {
		this.nyd = nyd;
	}

	public String getStlx() {
		return stlx;
	}

	public void setStlx(String stlx) {
		this.stlx = stlx;
	}

	public String getStImg() {
		return stImg;
	}

	public void setStImg(String stImg) {
		this.stImg = stImg;
	}

	public String getStImgA() {
		return stImgA;
	}

	public void setStImgA(String stImgA) {
		this.stImgA = stImgA;
	}

	public String getStImgB() {
		return stImgB;
	}

	public void setStImgB(String stImgB) {
		this.stImgB = stImgB;
	}

	public String getStImgC() {
		return stImgC;
	}

	public void setStImgC(String stImgC) {
		this.stImgC = stImgC;
	}

	public String getStImgD() {
		return stImgD;
	}

	public void setStImgD(String stImgD) {
		this.stImgD = stImgD;
	}

	public String getStImgE() {
		return stImgE;
	}

	public void setStImgE(String stImgE) {
		this.stImgE = stImgE;
	}

	public String getStImgF() {
		return stImgF;
	}

	public void setStImgF(String stImgF) {
		this.stImgF = stImgF;
	}

	public String getStImgG() {
		return stImgG;
	}

	public void setStImgG(String stImgG) {
		this.stImgG = stImgG;
	}

	public String getStSjId() {
		return stSjId;
	}

	public void setStSjId(String stSjId) {
		this.stSjId = stSjId;
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
