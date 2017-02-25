package com.wondersgroup.falcon.dao.call;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class ManuCallDAO {
	
	
	public String getUserIdByCallId(final String callid,final String date) {
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				StringBuilder sql = new StringBuilder();
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				String boundary = dateFormat.format(new Date());
				
				if(boundary.substring(0,10).equals(date)){
					sql.append("select t.userid from manualcall_log t where t.callid = '"+callid+"'");
				}else{
					sql.append("select t.userid from his_manualcall_log t where t.callid = '"+callid+"'");
				}
				
			
				SQLQuery query = session.createSQLQuery(sql.toString());
				
				List list = query.list();
				
				String userid = null;
				
				if(list !=  null && list.size() != 0){
					userid = String.valueOf(list.get(0));
				}
				return userid;
			}
		});	 


	} 
}
