package com.wondersgroup.falcon.dao.exam;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class ExamQuestionDAO extends ExamDAO{
	
	public List findByCrits(final String realsubname,final String businesstype,final String subjecttype,final String importance)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					int i=1;
					StringBuffer sb = new StringBuffer();
					sb.append("from ExamRealQuestions t where t.examquestions.questionname like ? ");
					if(!businesstype.equals("0")){
						sb.append(" and t.businesstype = ? ");
					}
					if(!subjecttype.equals("0")){
						sb.append(" and t.examquestype.typeid = ?");
					}
					if(!importance.equals("0")){
						sb.append(" and t.importance.impid = ? ");
					}
					Query query = session.createQuery(sb.toString());
					query.setString(0, "%"+realsubname+"%");
					if(!businesstype.equals("0")){
						query.setString(i, ""+businesstype+"");
						i++;
					}
					if(!subjecttype.equals("0")){
						query.setString(i, ""+subjecttype+"");
						i++;
					}
					if(!importance.equals("0")){
						query.setString(i, ""+importance+"");
						i++;
					}	
					List list = query.list();
					return list;
				}catch(Exception ex){
					ex.printStackTrace();
				}
				return null;	 

			}
		});


	}
	
	public List findKeysByCrit(final String realkeyname)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try{
					String hql = "from ExamKeys t where t.keycontent like ?";
					Query query = session.createQuery(hql);
					query.setString(0, "%"+realkeyname+"%");
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
