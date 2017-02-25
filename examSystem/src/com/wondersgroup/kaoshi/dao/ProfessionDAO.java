package com.wondersgroup.kaoshi.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.kaoshi.bo.Cz70;
import com.wondersgroup.kaoshi.bo.Tjobsubject;

public class ProfessionDAO extends HibernateDaoSupport{
	@SuppressWarnings("unchecked")
	public List<Cz70> findCz70ByName(final String name)throws Exception{
		return (List<Cz70>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(Cz70.class);
				criteria.add(Restrictions.eq("ccz136", name));
				return criteria.list();	 

			}
		});
	}
	/**
	 * 根据工种名称和等级获取题库(Tjobsubject)中的一条记录
	 * @param jobname
	 * @param dj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Tjobsubject> findByjobnameAndDj(final String jobname,final String dj)throws Exception{
		return (List<Tjobsubject>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(Tjobsubject.class);
				criteria.add(Restrictions.eq("jobname", jobname));
				criteria.add(Restrictions.eq("rankname", dj));
				return criteria.list();

			}
		});
	}
	/**
	 * 根据工种名称查看鉴定要素是否存在
	 * @param jobname
	 * @return
	 * @throws Exception
	 */
	public boolean findByjobname(final String jobname)throws Exception{
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String sql = "select count(a.jdysid) from TDJOBEXAMDOT a where a.ccz137 in (select distinct b.id_job from TJOBSUBJECT b where b.jobname='"+jobname+"')";
				Query query = session.createSQLQuery(sql);
				BigDecimal count = (BigDecimal) query.uniqueResult();
				if(count.intValue()>0){
					return true;
				}else{
					return false;
				}
			}
		});
	}
}
