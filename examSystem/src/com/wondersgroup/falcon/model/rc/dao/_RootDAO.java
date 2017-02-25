package com.wondersgroup.falcon.model.rc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;




public abstract class _RootDAO extends com.wondersgroup.falcon.model.rc.base._BaseRootDAO {


//	If you are using lazy loading, uncomment this
//	Somewhere, you should call RootDAO.closeCurrentThreadSessions();
	public void closeSession (Session session) {
		// do nothing here because the session will be closed later
	} 
	



/*//	If you are pulling the SessionFactory from a JNDI tree, uncomment this
	protected SessionFactory getSessionFactory(String configFile) {
		// If you have a single session factory, ignore the configFile parameter
		// Otherwise, you can set a meta attribute under the class node called "config-file" which
		// will be passed in here so you can tell what session factory an individual mapping file
		// belongs to
		return (SessionFactory) new InitialContext().lookup("java:/{SessionFactoryName}");
	}*/

}