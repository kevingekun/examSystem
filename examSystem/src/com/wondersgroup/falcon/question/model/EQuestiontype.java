package com.wondersgroup.falcon.question.model;

import java.util.HashSet;
import java.util.Set;

/**
 * EQuestiontype entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EQuestiontype implements java.io.Serializable {

	// Fields

	private long id;
	private String name;
	private String descriptor;
	private long zt;
	private long priority;
	private int canSy;
//	private Set EQuestionses = new HashSet(0);

	// Constructors

	/** default constructor */
	public EQuestiontype() {
	}

	/** minimal constructor */
	public EQuestiontype(String name, long zt, long priority) {
		this.name = name;
		this.zt = zt;
		this.priority = priority;
	}

	/** full constructor */
	public EQuestiontype(String name, String descriptor, long zt,
			long priority
//			, Set EQuestionses
			) {
		this.name = name;
		this.descriptor = descriptor;
		this.zt = zt;
		this.priority = priority;
//		this.EQuestionses = EQuestionses;
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

	public int getCanSy() {
		return canSy;
	}

	public void setCanSy(int canSy) {
		this.canSy = canSy;
	}

//	public Set getEQuestionses() {
//		return this.EQuestionses;
//	}
//
//	public void setEQuestionses(Set EQuestionses) {
//		this.EQuestionses = EQuestionses;
//	}

}