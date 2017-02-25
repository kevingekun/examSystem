package com.wondersgroup.falcon.model.dic;


/**
 * DicReleasestate entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DicReleasestate implements java.io.Serializable {

	// Fields

	private long id;
	private String name;
	private String descriptor;
	private long zt;
	private long priority;


	// Constructors

	/** default constructor */
	public DicReleasestate() {
	}

	/** minimal constructor */
	public DicReleasestate(long id, String name, long zt, long priority) {
		this.id = id;
		this.name = name;
		this.zt = zt;
		this.priority = priority;
	}

	/** full constructor */
	public DicReleasestate(long id, String name, String descriptor, long zt,
			long priority) {
		this.id = id;
		this.name = name;
		this.descriptor = descriptor;
		this.zt = zt;
		this.priority = priority;

	}

	// Property accessors

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescriptor() {
		return this.descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public long getZt() {
		return this.zt;
	}

	public void setZt(long zt) {
		this.zt = zt;
	}

	public long getPriority() {
		return this.priority;
	}

	public void setPriority(long priority) {
		this.priority = priority;
	}


}