package com.wondersgroup.popedom.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.EAuthority;
import com.wondersgroup.popedom.bo.ELogMonitor;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.bo.ExamLimitKs;

public class EuserDao  extends HibernateDaoSupport{
	public  PageReturn findAllUers(final PageTool pageTool,final EUser eu)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pr=new PageReturn();
				Criteria criteria=session.createCriteria(EUser.class);
				if(eu.getUsername()!=null && !eu.getUsername().equals("")){
					criteria.add(Restrictions.like("username", eu.getUsername(),MatchMode.ANYWHERE));
				}
				if(eu.getRealname()!=null && !eu.getRealname().equals("")){
					criteria.add(Restrictions.like("realname", eu.getRealname(), MatchMode.ANYWHERE));
				}
				/*if(eu.getUserstar()!=null && !"".equals(eu.getUserstar())){
					criteria.add(Restrictions.eq("userstar", eu.getUserstar()));
				}*/
				criteria.add(Restrictions.eq("userstar", "0"));
//				if(eu.getEnabled()!=null && eu.getEnabled().shortValue()!=0){
//					criteria.add(Restrictions.eq("enabled", eu.getEnabled()));
//				}
				byte b=1;
				criteria.add(Restrictions.eq("enabled", b));
				criteria.addOrder(Order.asc("username"));
				pr.setTotal(criteria.list().size());
				
				criteria.setFirstResult(pageTool.getStart());
				criteria.setMaxResults(pageTool.getSize());
				pr.setReturnList(criteria.list());
				return pr;

			}
		});
	}
	public  EUser load(final Long id)throws Exception{
		return (EUser) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				return (EUser) session.load(EUser.class, id);

			}
		});
	}
	
	
	public EUser getUserByUsername(final String username) {
		return (EUser) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				EUser user = (EUser)session.createCriteria(EUser.class)
				.add(Expression.eq("username", username))
				.uniqueResult();

				return user;
			}
		});	 


	}
	
	
	public EUser getUserByID(final String userid) {
		return (EUser) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				EUser user = (EUser)session.createCriteria(EUser.class)
				.add(Expression.eq("id", Long.valueOf(userid)))
				.uniqueResult();

				return user;
			}
		});	 
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getELogMonitorByUserId(final String userid,final String sjid){
		return (List<Object>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				ELogMonitor eLogMonitor = (ELogMonitor) session.createCriteria(ELogMonitor.class)
						.add(Restrictions.eq("userid", Long.valueOf(userid)))
						.uniqueResult();
				EPapers ePapers = (EPapers) session.createCriteria(EPapers.class)
						.add(Restrictions.eq("sjId", Long.valueOf(sjid)))
						.uniqueResult();
				List<Object> list = new ArrayList<Object>();
				list.add(eLogMonitor);
				list.add(ePapers);
				return list;
			}
		});
	}
	
	public EUser getUserByAgentid(final String username) {
		return (EUser) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				EUser user = (EUser)session.createCriteria(EUser.class)
				.add(Expression.eq("agentId", username))
				.uniqueResult();

				return user;
			}
		});	 


	} 

	public void UpdateUserAuth(final Long userId, final String[] authid)
			throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				EUser euser = (EUser) session.load(EUser.class, userId);
				euser.setAuthorities(null);
				Set<EAuthority> set = new HashSet<EAuthority>();
				for (int i = 0; i < authid.length; i++) {
					if (authid[i] != null && !authid[i].equals("")) {
						set.add((EAuthority) session.load(EAuthority.class,
								new Long(authid[i])));
					}
				}
				euser.setAuthorities(set);
				session.save(euser);
				session.flush();
				session.clear();
				return null;
			}
		});
	}
	public void deleteUser(final Long id)  
	throws Exception {
        HibernateUtil.doInSession(new HibernateSessionCallback() {
	public Object execute(Session session) throws Throwable {
		EUser euser=(EUser) session.load(EUser.class, id);
		session.delete(euser);
		return null;
	}
});
}

	public void updateUser(final Long id, final Byte sign) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				EUser euser = (EUser) session.load(EUser.class, id);
				euser.setEnabled(sign);
				session.update(euser);
				return null;
			}
		});
	}
	public  PageReturn getallkaoshi()throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PageReturn pageReturn=new PageReturn();
				 StringBuffer qs = new StringBuffer();
					qs.append("select a.password  ,a.realname ,a.username ,to_char(b.sj_Kksj,'yyyy/mm/dd')  ,c.cheatflag ,a.user_id ");
					qs.append(" from users  a, E_Papers b, E_LogMonitor c where a.user_id = c.userid and c.examid = b.sj_Id  ");
					qs.append(" and b.sj_Zt in ('0', '1', '6') and a.enabled='0' and c.cheatflag in('1','2') and c.ifxz='1' ");
					Query queryObject = session.createSQLQuery(qs.toString());
					pageReturn.setTotal(queryObject.list().size());
					List li=queryObject.list();
					List<ExamLimitKs> list = new ArrayList<ExamLimitKs>();
					Iterator<Object[]> it = li.iterator();
					while(it.hasNext()){
						Object[] o = it.next();
						ExamLimitKs e = new ExamLimitKs();
						e.setZkzh((String)o[0]);
						e.setXm((String)o[1]);
						e.setIDnumber((String)o[2]);
						e.setKssj((String)o[3]);
						if("1".equals((String)o[4])){
						e.setSign("作弊");
						}else{
							e.setSign("迟到");	
						}
						e.setUserID(((BigDecimal)o[5]).longValue());
						list.add(e);
					}  
					pageReturn.setReturnList(list);
					return pageReturn;
				//Criteria criteria=this.getSession().createCriteria(EKaoshi.class); 
			}
		});
	}

	public void deleteeKaoshi(final String[] deleteeKaoshi) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				for (int i = 0; i < deleteeKaoshi.length; i++) {

					updateUser(new Long(deleteeKaoshi[i]), Byte.valueOf("1"));// 讲考生状态置为可登录状态
					zckaoshi(new Long(deleteeKaoshi[i]));// 迟到置为正常
					/*
					 * EuserDao euserDao=new EuserDao();
					 * 
					 * EKaoshi eKaoshi=(EKaoshi)s.load(EKaoshi.class,new
					 * Long(deleteeKaoshi[i])); s.delete(eKaoshi);
					 */

				}
				return null;
			}
		});
	}
	public void zckaoshi(final long id) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				String hql = "update ELogMonitor e set e.ifxz='' where e.userid="
						+ id;
				try {
					session.createQuery(hql).executeUpdate();
				} catch (HibernateException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	}
	//找到考试临时表的记录
	public  List findbyRyid(final String ryid)throws Exception{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Query query=session.createQuery("from EPaperquestions_temp  e where  e.syid='"+ryid+"' ") ;
				List lst=null;
				lst=query.list();
				return lst; 
			}
		});
	}
	public void updateUser(final EUser user){
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.saveOrUpdate(user);
				return null;
			}
		});
	}
}
