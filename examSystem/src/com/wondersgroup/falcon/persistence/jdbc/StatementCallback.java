package com.wondersgroup.falcon.persistence.jdbc;

import java.sql.Statement;

/**
 * Statement»Øµ÷
 * 
 * @author suhualin
 * @version $Revision$ May 16, 2010
 * @author (lastest modification by $Author$)
 * @since 1.0
 */
public interface StatementCallback<T extends Statement> {
	/**
	 * @param statement
	 * @return
	 */
	Object execute(T statement) throws Throwable;
}
