package com.wondersgroup.popedom.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.paper.model.EAnswerpaper;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.kaoshi.bo.EKaoshi;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.bo.EUserMenus;

public class EUserMenusDao extends HibernateDaoSupport {

	/**
	 * 
	 * 
	 * <p>
	 * Description:��ѯһ���˵�
	 * </p>
	 * 
	 * Created by [www] [Nov 12, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param userid
	 *            �û�id
	 * @param parentid
	 *            �����˵�
	 * @param systemtype
	 *            ϵͳ1����ѯ��2������
	 * @param status
	 *            ״̬ 0Ϊ���� 1Ϊ���� 2����
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EUserMenus> findTopMenus(final Long userid,
			final String parentid, final Long systemtype, final Long status)
			throws Exception {
		return (List<EUserMenus>) HibernateUtil.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						StringBuffer qs = new StringBuffer();
						qs.append("select distinct m from EUser u join u.authorities a join a.menus m ");
						qs.append("where u.id=:userid and m.parentid=:parentid and m.systemtype=:systemtype and m.status=:status ");
						qs.append("order by m.ordering");
						Query queryObject = session.createQuery(qs.toString());
						queryObject.setParameter("userid", userid,
								Hibernate.LONG);
						queryObject.setParameter("parentid", parentid,
								Hibernate.STRING);
						queryObject.setParameter("systemtype", systemtype,
								Hibernate.LONG);
						queryObject.setParameter("status", status,
								Hibernate.LONG);
						List li = queryObject.list();
						return li;
					}
				});
	}

	/**
	 * 
	 * 
	 * <p>
	 * Description:�ж��Ƿ��ڿ�����
	 * </p>
	 * 
	 * Created by [hch] [2.3, 2010] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @return
	 */
	public EKaoshi findZt() throws Exception {
		return (EKaoshi) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						EKaoshi n = new EKaoshi();
						Criteria criteria = session
								.createCriteria(EKaoshi.class);
						AcegiUtil acegiUtil = new AcegiUtil();
						EUser user = ((UserDetailsImpl) acegiUtil
								.getUserDetails()).getUser();
						criteria.add(Restrictions.eq("Ryid", user.getId()));
						List li = criteria.list();
						if (li != null && li.size() > 0) {
							n = (EKaoshi) li.get(0);
						}
						return n;

					}
				});

	}

	// 判断是否是考生
	public String findst() throws Exception {
		return (String) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						AcegiUtil acegiUtil = new AcegiUtil();
						EUser user = ((UserDetailsImpl) acegiUtil
								.getUserDetails()).getUser();
						String sql = "select e.id from e_exam_arrange e where  e.usertype ='1'and  e.userid='"
								+ user.getId() + "'";
						Query query = session.createSQLQuery(sql.toString());
						List list = query.list();
						String isst = "";// 是考生为1 不是为0
						if (list.size() > 0) {
							isst = "1";
						} else {
							isst = "0";
						}
						return isst;

					}
				});

	}

	/**
	 * 
	 * 
	 * <p>
	 * Description:��ݸ����ҵ����еĲ˵�
	 * </p>
	 * 
	 * Created by [www] [Nov 16, 2009] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EUserMenus> findMenusByParent(final String parentid)
			throws Exception {
		return (List<EUserMenus>) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						Criteria criteria = session
								.createCriteria(EUserMenus.class);
						criteria.add(Restrictions.eq("parentid", parentid));
						criteria.add(Restrictions.eq("status", Long.valueOf(1)));
						return criteria.list();

					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<EUserMenus> findMenus() throws Exception {
		return (List<EUserMenus>) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						Criteria criteria = session
								.createCriteria(EUserMenus.class);
						return criteria.list();

					}
				});
	}

}
