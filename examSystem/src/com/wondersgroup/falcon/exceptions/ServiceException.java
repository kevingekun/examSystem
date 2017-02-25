/** 
 * Copyright (c) 2003-2007 Wonders Information Co.,Ltd. All Rights Reserved.
 * 5-6/F, 20 Bldg, 481 Guiping RD. Shanghai 200233,PRC
 *
 * This software is the confidential and proprietary information of Wonders Group.
 * (Research & Development Center). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gun.org
 */

package com.wondersgroup.falcon.exceptions;

/**
 * <p>Title:[Service异常类] </p>
 * <p>Description: [类功能描述]</p> 
 *
 * Created by [Kevin Liang] [2009-6-30]
 * Midified by [修改人] [修改时间]
 *
 */

public class ServiceException extends RuntimeException {
	public ServiceException() {
		super();
	}

	public ServiceException(Throwable ex) {
		super(ex);
	}

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(String msg, Throwable ex) {
		super(msg, ex);
	}
}
