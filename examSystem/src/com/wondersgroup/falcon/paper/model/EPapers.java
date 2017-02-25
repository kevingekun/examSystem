package com.wondersgroup.falcon.paper.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * EPapers entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EPapers implements java.io.Serializable {

	// Fields

	private long sjId;
	// 试卷名称
	private String sjMc;
	private String shortSjMc;// 处理过长字符串
	// 试卷总分
	private double sjZf;
	// 不及格分数线
	private double sjBhgfs;
	// 答卷时限
	private long sjDjsx;
	// 开考时间
	private Date sjKksj;
	private Date sjKksjEnd;
	// 有效期截止时间
	private Date sjYxqjzsj;
	// 组卷时间
	private Date sjZjsj;
	// 组卷人ID
	private String sjZjrid;
	// 开卷 闭卷模式
	private String model;
	// 试卷类型
	private String paperType;
	// 指定播测试卷考试人
	private String toUserId;

	/**
	 * 试卷状态 0 未考试 1 考试中 2 已结束
	 * 
	 */
	private long sjZt;
	/**
	 * 试卷审阅状态 0 待审阅 1 一次审阅完成 2 二次审阅完成
	 * 
	 */
	private long sjSyzt;
	// 备注
	private String sjBz;
	// 成绩公布状态 0 未公布 1公布
	private long sjCjgbzt;
	// 审阅人ID
	private String sjSyrid;
	// 审阅时间
	private Date sjSysj;
	// 审核意见
	private String sjAdvice;
	private long sjJjsj;//多少分钟后可以交卷
	private String sjLjcf;//是否立即处分，1 是 0 否 默认为0
	private String sjKslx;//考试类型（1鉴定类考试，2其他考试）
	private String sjZych;//试卷重要成都
	private long sjFs;//试卷生成分数
	private String sjPc;//考试批次
	private String sjNych;//考试难易程度（1易2较易3普通4较难5难 ）
	private String sjDj;//试卷等级
	private String sjCtfs;//出题方式 1 按照鉴定比例出题 2 手动输入比例出题
	private String sjGzid;//工种id
	
	private long sjScyth;//上传一体化（0未上传，1已上传）
	private long sjCjhc;//成绩回传（0未上传，1已上传）

	private Set eanswerpapers = new HashSet(0);
	private Set epaperquestionses = new HashSet(0);

	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("\n");
		str.append("sjMc:" + this.getSjMc() + "\n");
		str.append("sjZf:" + this.getSjZf() + "\n");
		str.append("sjBhgfs:" + this.getSjBhgfs() + "\n");
		str.append("sjDjsx:" + this.getSjDjsx() + "\n");
		str.append("sjKksj" + this.getSjKksj() + "\n");
		str.append("sjYxqjzsj" + this.getSjYxqjzsj() + "\n");
		str.append("sjZjsj:" + this.getSjZjsj() + "\n");
		str.append("sjZjrid:" + this.getSjZjrid() + "\n");
		str.append("sjZt:" + this.getSjZt() + "\n");
		str.append("sjSyzt:" + this.getSjSyzt() + "\n");
		str.append("sjBz:" + this.getSjBz() + "\n");
		str.append("sjJjsj" + this.getSjJjsj() + "\n");
		str.append("sjCjgbzt:" + this.getSjCjgbzt() + "\n");
		str.append("sjLjcf" + this.getSjLjcf() + "\n");
		str.append("sjKslx" + this.getSjKslx() + "\n");
		str.append("sjZych" + this.getSjZych() + "\n");
		str.append("sjFs" + this.getSjFs() + "\n");
		str.append("sjPc" + this.getSjPc() + "\n");
		str.append("sjNych" + this.getSjNych() + "\n");
		str.append("sjDj" + this.getSjDj() + "\n");
		str.append("sjCtfs" + this.getSjCtfs() + "\n");
		str.append("sjGzid" + this.getSjGzid() + "\n");
		return str.toString();
	}

	// Constructors

	/** default constructor */
	public EPapers() {
	}

	/** minimal constructor */
	public EPapers(String sjMc, double sjZf, double sjBhgfs, long sjDjsx,
			Date sjKksj, Date sjYxqjzsj, String sjZjrid, long sjZt, long sjSyzt
			,long sjJjsj, String sjLjcf ,String sjKslx ,String sjZych, long sjFs,
			String sjPc,String sjNych,String sjDj ,String sjCtfs ,String sjGzid
			) {
		this.sjMc = sjMc;
		this.sjZf = sjZf;
		this.sjBhgfs = sjBhgfs;
		this.sjDjsx = sjDjsx;
		this.sjKksj = sjKksj;
		this.sjYxqjzsj = sjYxqjzsj;
		this.sjZjrid = sjZjrid;
		this.sjZt = sjZt;
		this.sjSyzt = sjSyzt;
		this.sjJjsj=sjJjsj;
		this.sjLjcf=sjLjcf;
		this.sjKslx=sjKslx;
		this.sjZych=sjZych;
		this.sjFs=sjFs;
		this.sjPc=sjPc;
		this.sjNych=sjNych;
		this.sjDj=sjDj;
		this.sjCtfs=sjCtfs;
		this.sjGzid=sjGzid;
	}

	/** full constructor */
	public EPapers(String sjMc, double sjZf, double sjBhgfs, long sjDjsx,
			Date sjKksj, Date sjYxqjzsj, Date sjZjsj, String sjZjrid,
			long sjZt, long sjSyzt, String sjBz, long sjCjgbzt, String sjSyrid,
			Date sjSysj, String sjAdvice, long sjJjsj, String sjLjcf ,String sjKslx ,String sjZych, long sjFs,
			String sjPc,String sjNych,String sjDj ,String sjCtfs ,String sjGzid,
			Set eanswerpapers,
			Set epaperquestionses) {
		this.sjMc = sjMc;
		this.sjZf = sjZf;
		this.sjBhgfs = sjBhgfs;
		this.sjDjsx = sjDjsx;
		this.sjKksj = sjKksj;
		this.sjYxqjzsj = sjYxqjzsj;
		this.sjZjsj = sjZjsj;
		this.sjZjrid = sjZjrid;
		this.sjZt = sjZt;
		this.sjSyzt = sjSyzt;
		this.sjBz = sjBz;
		this.sjCjgbzt = sjCjgbzt;
		this.sjSyrid = sjSyrid;
		this.sjSysj = sjSysj;
		this.sjAdvice = sjAdvice;
		this.sjJjsj=sjJjsj;
		this.sjLjcf=sjLjcf;
		this.sjKslx=sjKslx;
		this.sjZych=sjZych;
		this.sjFs=sjFs;
		this.sjPc=sjPc;
		this.sjNych=sjNych;
		this.sjDj=sjDj;
		this.sjCtfs=sjCtfs;
		this.sjGzid=sjGzid;
		this.eanswerpapers = eanswerpapers;
		this.epaperquestionses = epaperquestionses;
	}

	// Property accessors

	public String getSjSyrid() {
		return sjSyrid;
	}

	public void setSjSyrid(String sjSyrid) {
		this.sjSyrid = sjSyrid;
	}

	public Date getSjSysj() {
		return sjSysj;
	}

	public void setSjSysj(Date sjSysj) {
		this.sjSysj = sjSysj;
	}

	public String getSjAdvice() {
		return sjAdvice;
	}

	public void setSjAdvice(String sjAdvice) {
		this.sjAdvice = sjAdvice;
	}

	public long getSjId() {
		return this.sjId;
	}

	public void setSjId(long sjId) {
		this.sjId = sjId;
	}

	public String getSjMc() {
		return this.sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	public double getSjZf() {
		return this.sjZf;
	}

	public void setSjZf(double sjZf) {
		this.sjZf = sjZf;
	}

	public double getSjBhgfs() {
		return this.sjBhgfs;
	}

	public void setSjBhgfs(double sjBhgfs) {
		this.sjBhgfs = sjBhgfs;
	}

	public long getSjDjsx() {
		return this.sjDjsx;
	}

	public void setSjDjsx(long sjDjsx) {
		this.sjDjsx = sjDjsx;
	}

	public Date getSjKksj() {
		return this.sjKksj;
	}

	public void setSjKksj(Date sjKksj) {
		this.sjKksj = sjKksj;
	}

	public Date getSjYxqjzsj() {
		return this.sjYxqjzsj;
	}

	public void setSjYxqjzsj(Date sjYxqjzsj) {
		this.sjYxqjzsj = sjYxqjzsj;
	}

	public Date getSjZjsj() {
		return this.sjZjsj;
	}

	public void setSjZjsj(Date sjZjsj) {
		this.sjZjsj = sjZjsj;
	}

	public String getSjZjrid() {
		return this.sjZjrid;
	}

	public void setSjZjrid(String sjZjrid) {
		this.sjZjrid = sjZjrid;
	}

	public long getSjZt() {
		return this.sjZt;
	}

	public void setSjZt(long sjZt) {
		this.sjZt = sjZt;
	}

	public long getSjSyzt() {
		return this.sjSyzt;
	}

	public void setSjSyzt(long sjSyzt) {
		this.sjSyzt = sjSyzt;
	}

	public String getSjBz() {
		return this.sjBz;
	}

	public void setSjBz(String sjBz) {
		this.sjBz = sjBz;
	}

	public long getSjCjgbzt() {
		return this.sjCjgbzt;
	}

	public void setSjCjgbzt(long sjCjgbzt) {
		this.sjCjgbzt = sjCjgbzt;
	}

	public Set getEanswerpapers() {
		return eanswerpapers;
	}

	public void setEanswerpapers(Set eanswerpapers) {
		this.eanswerpapers = eanswerpapers;
	}

	public Set getEpaperquestionses() {
		return epaperquestionses;
	}

	public void setEpaperquestionses(Set epaperquestionses) {
		this.epaperquestionses = epaperquestionses;
	}

	// 减少名称
	public String getShortSjMc() {
		String str = "";
		if (this.shortSjMc != null && this.shortSjMc.length() > 20) {
			str = this.shortSjMc.substring(0, 20) + "...";
			// log.info("if length："+str.length()+"  - "+str);
		} else {
			str = this.shortSjMc;
			// log.info("else length："+str.length()+" - "+str);
		}
		return str;
	}

	public void setShortSjMc(String shortSjMc) {
		this.shortSjMc = shortSjMc;
	}

	public Date getSjKksjEnd() {
		return sjKksjEnd;
	}

	public void setSjKksjEnd(Date sjKksjEnd) {
		this.sjKksjEnd = sjKksjEnd;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	

	public String getSjLjcf() {
		return sjLjcf;
	}

	public void setSjLjcf(String sjLjcf) {
		this.sjLjcf = sjLjcf;
	}

	public String getSjKslx() {
		return sjKslx;
	}

	public void setSjKslx(String sjKslx) {
		this.sjKslx = sjKslx;
	}

	public String getSjZych() {
		return sjZych;
	}

	public void setSjZych(String sjZych) {
		this.sjZych = sjZych;
	}



	public String getSjPc() {
		return sjPc;
	}

	public void setSjPc(String sjPc) {
		this.sjPc = sjPc;
	}

	public String getSjNych() {
		return sjNych;
	}

	public void setSjNych(String sjNych) {
		this.sjNych = sjNych;
	}

	public String getSjDj() {
		return sjDj;
	}

	public void setSjDj(String sjDj) {
		this.sjDj = sjDj;
	}

	public String getSjCtfs() {
		return sjCtfs;
	}

	public void setSjCtfs(String sjCtfs) {
		this.sjCtfs = sjCtfs;
	}

	public long getSjJjsj() {
		return sjJjsj;
	}

	public void setSjJjsj(long sjJjsj) {
		this.sjJjsj = sjJjsj;
	}

	public long getSjFs() {
		return sjFs;
	}

	public void setSjFs(long sjFs) {
		this.sjFs = sjFs;
	}

	public String getSjGzid() {
		return sjGzid;
	}

	public void setSjGzid(String sjGzid) {
		this.sjGzid = sjGzid;
	}

	public long getSjScyth() {
		return sjScyth;
	}

	public void setSjScyth(long sjScyth) {
		this.sjScyth = sjScyth;
	}

	public long getSjCjhc() {
		return sjCjhc;
	}

	public void setSjCjhc(long sjCjhc) {
		this.sjCjhc = sjCjhc;
	}

}