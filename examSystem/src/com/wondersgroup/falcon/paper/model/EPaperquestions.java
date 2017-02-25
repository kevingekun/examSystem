package com.wondersgroup.falcon.paper.model;

import com.wondersgroup.falcon.question.model.EQuestions;

/**
 * EPaperquestions entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EPaperquestions implements java.io.Serializable {

	// Fields

	private long id;
	private EQuestions equestions;
	private EPapers epapers;
	private double sjStfs;
	private long sjStpx;
	private double wrong_percent;

	// Constructors

	public double getWrong_percent() {
		return wrong_percent;
	}

	public void setWrong_percent(double wrong_percent) {
		this.wrong_percent = wrong_percent;
	}

	/** default constructor */
	public EPaperquestions() {
	}

	/** full constructor */
	public EPaperquestions(EQuestions equestions, EPapers epapers,
			double sjStfs, long sjStpx,long wrong_percent) {
		this.equestions = equestions;
		this.epapers = epapers;
		this.sjStfs = sjStfs;
		this.sjStpx = sjStpx;
		this.wrong_percent = wrong_percent;
	}

	// Property accessors

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public EQuestions getEquestions() {
		return equestions;
	}

	public void setEquestions(EQuestions equestions) {
		this.equestions = equestions;
	}

	public EPapers getEpapers() {
		return epapers;
	}

	public void setEpapers(EPapers epapers) {
		this.epapers = epapers;
	}

	public double getSjStfs() {
		return this.sjStfs;
	}

	public void setSjStfs(double sjStfs) {
		this.sjStfs = sjStfs;
	}

	public long getSjStpx() {
		return this.sjStpx;
	}

	public void setSjStpx(long sjStpx) {
		this.sjStpx = sjStpx;
	}

}