/**
 * Copyright (c) 2003-2007 Wonders Information Co.,Ltd. All Rights Reserved.
 * 5-6/F, 20 Bldg, 481 Guiping RD. Shanghai 200233,PRC
 *
 * This software is the confidential and proprietary information of Wonders Group.
 * (Research & Development Center). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 */
package com.wondersgroup.falcon.abstracts;

import org.hibernate.type.Type;

/**
 * Hibernate中Query对象�?要的参数，包括参数名、�?��?�类�?

 * @author chencheng
 *
 */
public class HqlParameter {
	/**
	 * 参数名，可以为空，将按顺序猜�?

	 */
	private String name;
	
	/**
	 * 参数值，可能是一个Collection
	 */
	private Object value;
	
	/**
	 * 参数类型，可以为空，将按顺序猜测
	 */
	private Type type;
	
	public HqlParameter() {
		super();
	}

	public HqlParameter(Object value) {
		this.value = value;
	}
	
	public HqlParameter(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	public HqlParameter(String name, Object value, Type type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the type.
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return Returns the value.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value The value to set.
	 */
	public void setValue(Object value) {
		this.value = value;
	}

}