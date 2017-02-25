package com.wondersgroup.falcon.question.model;

import java.util.HashSet;
import java.util.Set;

/**
 * EImportance entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EImportance implements java.io.Serializable {

	// Fields

	private long id;
	private String name;
	private String descriptor;
	private long zt;
	private long priority;
//	private Set EQuestionses = new HashSet(0);

	// Constructors

	/** default constructor */
	public EImportance() {
	}

	/** minimal constructor */
	public EImportance(String name, long zt, long priority) {
		this.name = name;
		this.zt = zt;
		this.priority = priority;
	}

	/** full constructor */
	public EImportance(String name, String descriptor, long zt, long priority
//			,Set EQuestionses
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

//	public Set getEQuestionses() {
//		return this.EQuestionses;
//	}
//
//	public void setEQuestionses(Set EQuestionses) {
//		this.EQuestionses = EQuestionses;
//	}

}