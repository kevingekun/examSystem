/** 
 * @(#)HibernateSessionCallback.java May 16, 2010
 * 
 * Copyright (c) 1995-2010 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */

package com.wondersgroup.falcon.persistence;

import org.hibernate.Session;

/**
 * @author suhualin
 * @version $Revision$ May 16, 2010
 * @author (lastest modification by $Author$)
 * @since 1.0
 */
public interface HibernateSessionCallback {
	Object execute(Session session) throws Throwable;
}
