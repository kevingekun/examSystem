package com.wondersgroup.falcon.question.model;

import java.util.Date;

public class EModefy {
	
	private long id;
	private long st_id;
	private Date dt;
	private long zt;
	
	public EModefy()
	 {
	
}
	
	
	/** minimal constructor */
	public EModefy( long st_id,Date dt ,long zt) {
		this.st_id = st_id;
		this.dt = dt;
		this.zt = zt;
	}

	/** full constructor */
	public EModefy(long id, long st_id,Date dt ,long zt)
		 {
		this.id = id;
		this.st_id = st_id;
		this.dt = dt;
		this.zt = zt;
	}
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSt_id() {
		return st_id;
	}
	public void setSt_id(long st_id) {
		this.st_id = st_id;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public long getZt() {
		return zt;
	}
	public void setZt(long zt) {
		this.zt = zt;
	}

}
