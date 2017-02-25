package com.wondersgroup.falcon.dao.exam;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class ExamMarksDAO extends ExamDAO{

	
	public List finByAnswersByUser(final String username)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					
					String hql = "from ExamAnswers t where t.username=?";
					Query query = session.createQuery(hql);
					query.setString(0, ""+username+"");
					List list = null;
					list = query.list();
					return list;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null;

			}
		});


	}
}
