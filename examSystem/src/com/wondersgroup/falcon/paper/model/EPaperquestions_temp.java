package com.wondersgroup.falcon.paper.model;

import com.wondersgroup.falcon.question.model.EQuestions;

/**
 * EPaperquestions entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EPaperquestions_temp implements java.io.Serializable {

	// Fields

	private long id;
	private EQuestions equestions;
	private EPapers epapers;
	private double sjStfs;
	private long sjStpx;
	private String stDa;
	private String stDasm;
	private long state;
	private String syid;
	private long Stpx;

	// Constructors




	/** default constructor */
	public EPaperquestions_temp() {
	}

	/** full constructor */
	public EPaperquestions_temp(EQuestions equestions, EPapers epapers,
			double sjStfs, long sjStpx,long state,String stDa,String stDasm,String syid) {
		this.equestions = equestions;
		this.epapers = epapers;
		this.sjStfs = sjStfs;
		this.sjStpx = sjStpx;
		this.state = state;
		this.stDa = stDa;
		this.stDasm = stDasm;
		this.syid = syid;
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

	public String getStDa() {
		return stDa;
	}

	public void setStDa(String stDa) {
		this.stDa = stDa;
	}

	public String getStDasm() {
		return stDasm;
	}

	public void setStDasm(String stDasm) {
		this.stDasm = stDasm;
	}

	public long getState() {
		return state;
	}

	public void setState(long state) {
		this.state = state;
	}

	public String getSyid() {
		return syid;
	}

	public void setSyid(String syid) {
		this.syid = syid;
	}
	
	public long getStpx() {
		return Stpx;
	}

	public void setStpx(long stpx) {
		Stpx = stpx;
	}


}