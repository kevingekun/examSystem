package com.wondersgroup.falcon.paper.model;

import com.wondersgroup.falcon.question.model.EQuestions;

/**
 * EAnswerquestions entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EAnswerquestions implements java.io.Serializable {

	// Fields

	private long id;
	private EPaperquestions epaperquestions;
	private EAnswerpaper eanswerpaper;
	private String stDa;
	private String stDasm;
	private long stPx;
	private double stDf;
	private Long stsyzt;
	private Long right;
	private String stContent;//拨测题备注
	private String stUsername;//拨测人员

	// Constructors

	public Long getRight() {
		return right;
	}

	public void setRight(Long right) {
		this.right = right;
	}

	public Long getStsyzt() {
		return stsyzt;
	}

	public void setStsyzt(Long stsyzt) {
		this.stsyzt = stsyzt;
	}

	/** default constructor */
	public EAnswerquestions() {
	}

	/** minimal constructor */
	public EAnswerquestions(String stDa, long stPx) {
		this.stDa = stDa;
		this.stPx = stPx;
	}

	/** full constructor */
	public EAnswerquestions(EQuestions equestions, EAnswerpaper eanswerpaper,
			String stDa, String stDasm, long stPx, double stDf) {
		this.eanswerpaper = eanswerpaper;
		this.stDa = stDa;
		this.stDasm = stDasm;
		this.stPx = stPx;
		this.stDf = stDf;
	}

	// Property accessors

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EPaperquestions getEpaperquestions() {
		return epaperquestions;
	}

	public void setEpaperquestions(EPaperquestions epaperquestions) {
		this.epaperquestions = epaperquestions;
	}

	public EAnswerpaper getEanswerpaper() {
		return eanswerpaper;
	}

	public void setEanswerpaper(EAnswerpaper eanswerpaper) {
		this.eanswerpaper = eanswerpaper;
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

	public long getStPx() {
		return this.stPx;
	}

	public void setStPx(long stPx) {
		this.stPx = stPx;
	}

	public double getStDf() {
		return this.stDf;
	}

	public void setStDf(double stDf) {
		this.stDf = stDf;
	}

	public String getStContent() {
		return stContent;
	}

	public void setStContent(String stContent) {
		this.stContent = stContent;
	}

	public String getStUsername() {
		return stUsername;
	}

	public void setStUsername(String stUsername) {
		this.stUsername = stUsername;
	}

}