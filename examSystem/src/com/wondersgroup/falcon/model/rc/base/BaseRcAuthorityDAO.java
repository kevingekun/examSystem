package com.wondersgroup.falcon.model.rc.base;

import com.wondersgroup.falcon.model.auth.Authority;
import com.wondersgroup.falcon.model.rc.dao.RcAuthorityDAO;
import com.wondersgroup.falcon.model.rc.dao.RcCallerDAO;
import com.wondersgroup.falcon.model.rc.dao._RootDAO;

public class BaseRcAuthorityDAO extends _RootDAO {
	
	public static RcAuthorityDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static RcAuthorityDAO getInstance () {
		if (null == instance) instance = new RcAuthorityDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return Authority.class;
	}
	
	public java.util.List findAll () {
		//liangkd 修改加载系统角色
		String authHQL = "select a from Authority a where a.state="+Authority.SYSTEM_STATE+""+" order by a.id asc";
		return super.getQuery(authHQL).list();
		//return super.findAll();
	}

}
