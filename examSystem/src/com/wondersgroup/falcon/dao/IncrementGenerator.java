/**
 * 
 */
package com.wondersgroup.falcon.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.type.Type;

import com.wondersgroup.falcon.persistence.HibernateUtil;

/**
 * <p>Title:[主键生成工具] </p>
 * <p>Description: [根据表主键字段的最大值生成]</p> 
 *
 * Created by [Kevin Liang] [2009-6-29]
 * Midified by [修改人] [修改时间]
 *
 */
public class IncrementGenerator implements IdentifierGenerator, Configurable {

	private static final Log log = LogFactory.getLog(IncrementGenerator.class);

	
	private long next;
	private String sql;

	/* (non-Javadoc)
	 * @see org.hibernate.id.IdentifierGenerator#generate(org.hibernate.engine.SessionImplementor, java.lang.Object)
	 */
	public synchronized Serializable generate(SessionImplementor session, Object object)
	throws HibernateException {

		// TODO Auto-generated method stub
		if (sql!=null) {
			//获得下一个主键的编号，可以自己定义
			try {
				getNext(session.connection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info("主键生成结果："+next);
		
		return new Long(next);

	}

	/* (non-Javadoc)
	 * @see org.hibernate.id.Configurable#configure(org.hibernate.type.Type, java.util.Properties, org.hibernate.dialect.Dialect)
	 */
	public void configure(Type type, Properties params, Dialect arg2)
	throws MappingException {
		// TODO Auto-generated method stub
		String table = params.getProperty("table");
		if (table==null) table = params.getProperty(PersistentIdentifierGenerator.TABLE);
		String column = params.getProperty("column");
		if (column==null) column = params.getProperty(PersistentIdentifierGenerator.PK);
		String schema = params.getProperty(PersistentIdentifierGenerator.SCHEMA);
		Class returnClass = type.getReturnedClass();

		sql = "select max(to_number(" + column + ")) from " + ( schema==null ? table : schema + '.' + table );

		log.debug(sql);


	}
	/**
	 * 
	 * <p>Description:[生成主键] </p>
	 * 
	 * Created by [Kevin Liang] [2009-6-29]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param conn
	 * @throws SQLException
	 */
	private void getNext(Connection conn) throws SQLException {
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = null;
		try {
			rs = st.executeQuery();
			if ( rs.next() ) {
				next = rs.getLong(1) + 1;
				if ( rs.wasNull() ) next = 1;
			}
			else {
				next = 1;
			}
			sql=null;
			log.debug("first free id: " + next);
		}
		finally {
			if (rs!=null) rs.close();
			st.close();
		}
	}

}
