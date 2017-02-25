package com.wondersgroup.falcon.persistence.jdbc;

import java.sql.ResultSet;

/**
 * ResultSet»Øµ÷
 * 
 * @author suhualin
 * @version $Revision$ May 16, 2010
 * @author (lastest modification by $Author$)
 * @since 1.0
 */
public interface ResultSetCallback {
	/**
	 * 
	 * @param rs
	 * @return
	 */
	Object execute(ResultSet rs) throws Throwable;
}
