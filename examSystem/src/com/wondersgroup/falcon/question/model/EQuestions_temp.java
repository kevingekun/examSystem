package com.wondersgroup.falcon.question.model;

import java.util.Date;

@SuppressWarnings("serial")
public class EQuestions_temp {
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
	private long state; //本条记录状态
	private long batchNumber;//批次号
	private String remark;//备注
	private long jdysId;//鉴定要素id
	private String stSjid;//所属试卷
	
	private String stFz;//试题分值
	
	
	public long getState() {
		return state;
	}
	public void setState(long state) {
		this.state = state;
	}
	public long getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(long batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getStId() {
		return stId;
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
	public String getStCc() {
		return stCc;
	}
	public void setStCc(String stCc) {
		this.stCc = stCc;
	}
	public String getStFjlj() {
		return stFjlj;
	}
	public void setStFjlj(String stFjlj) {
		this.stFjlj = stFjlj;
	}
	public String getBxType() {
		return bxType;
	}
	public void setBxType(String bxType) {
		this.bxType = bxType;
	}
	public String getStTg() {
		return stTg;
	}
	public void setStTg(String stTg) {
		this.stTg = stTg;
	}
	public String getStXx() {
		return stXx;
	}
	public void setStXx(String stXx) {
		this.stXx = stXx;
	}
	public String[] getManyXx() {
		return manyXx;
	}
	public void setManyXx(String[] manyXx) {
		this.manyXx = manyXx;
	}
	public String getStDa() {
		return stDa;
	}
	public void setStDa(String stDa) {
		this.stDa = stDa;
	}
	public String[] getManyDa() {
		return manyDa;
	}
	public void setManyDa(String[] manyDa) {
		this.manyDa = manyDa;
	}
	public String getStDasm() {
		return stDasm;
	}
	public void setStDasm(String stDasm) {
		this.stDasm = stDasm;
	}
	public long getStKszy() {
		return stKszy;
	}
	public void setStKszy(long stKszy) {
		this.stKszy = stKszy;
	}
	public Date getStLrsj() {
		return stLrsj;
	}
	public void setStLrsj(Date stLrsj) {
		this.stLrsj = stLrsj;
	}
	public String getStMtryId() {
		return stMtryId;
	}
	public void setStMtryId(String stMtryId) {
		this.stMtryId = stMtryId;
	}
	public Date getStXgsj() {
		return stXgsj;
	}
	public void setStXgsj(Date stXgsj) {
		this.stXgsj = stXgsj;
	}
	public String getStWh() {
		return stWh;
	}
	public void setStWh(String stWh) {
		this.stWh = stWh;
	}
	public long getStJyxgcs() {
		return stJyxgcs;
	}
	public void setStJyxgcs(long stJyxgcs) {
		this.stJyxgcs = stJyxgcs;
	}
	public long getStScbz() {
		return stScbz;
	}
	public void setStScbz(long stScbz) {
		this.stScbz = stScbz;
	}
	public Date getStlrsjend() {
		return stlrsjend;
	}
	public void setStlrsjend(Date stlrsjend) {
		this.stlrsjend = stlrsjend;
	}
	public int getHas() {
		return has;
	}
	public void setHas(int has) {
		this.has = has;
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
	public long getStModefy() {
		return stModefy;
	}
	public void setStModefy(long stModefy) {
		this.stModefy = stModefy;
	}
	public long getStCheck() {
		return stCheck;
	}
	public void setStCheck(long stCheck) {
		this.stCheck = stCheck;
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
	public long getJdysId() {
		return jdysId;
	}
	public void setJdysId(long jdysId) {
		this.jdysId = jdysId;
	}
	public String getStSjid() {
		return stSjid;
	}
	public void setStSjid(String stSjid) {
		this.stSjid = stSjid;
	}
	public String getStFz() {
		return stFz;
	}
	public void setStFz(String stFz) {
		this.stFz = stFz;
	}
	
}

