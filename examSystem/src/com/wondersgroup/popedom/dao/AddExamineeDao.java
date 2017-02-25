package com.wondersgroup.popedom.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.kaoshi.bo.Station_xf;
import com.wondersgroup.popedom.bo.EAuthority;
import com.wondersgroup.popedom.bo.EGroup;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.bo.Users_yth;

public class AddExamineeDao extends HibernateDaoSupport {

	public void addexaminee(final EUser euser, final String role)
			throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Set<EAuthority> set = new HashSet<EAuthority>();// 获得角色
				set.add((EAuthority) session.load(EAuthority.class,
						Long.valueOf(role)));// 查询角色信息
				euser.setAuthorities(set);
				EGroup goup = new EGroup();// 获得关联组信息
				goup.setId(new Long(2));// 为固定值默认为2
				euser.setGroup(goup);
				session.save(euser);// 保存新增用户信息

				return null;
			}
		});
	}
	public void addexamineeYth(final Users_yth euser, final String role)
			throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				euser.setGroup_id(new Long(2));
				euser.setVisible(new Integer(1));
				session.save(euser);// 保存新增用户信息
				
				String sql = "insert into "+ProcedureUrl.E_USER_AUTHORITIES+"(AUTHORITY_ID,USER_ID) values("+Long.valueOf(role)+","+euser.getUser_id()+")";
				Query query = session.createSQLQuery(sql);
				query.executeUpdate();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<EUser> queryuser(final String idnumber) throws Exception {
		return (List<EUser>) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						Criteria criteria = session.createCriteria(EUser.class);
						criteria.add(Restrictions.eq("username", idnumber));
						return criteria.list();

					}
				});

	}

	/**
	 * 考生相关信息
	 */

	// 保存人员信息

	@SuppressWarnings("unchecked")
	public List<Object[]> queryRole() throws Exception {
		return (List<Object[]>) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						StringBuffer qs = new StringBuffer();
						qs.append("select e.AUTHORITY_ID,e.DESCRIPTION	from E_AUTHORITIES   e");
						Query queryObject = session.createSQLQuery(qs
								.toString());
						List li = queryObject.list();
						return li;

					}
				});
	}
	/**
	 * 获取所有考点信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserTeam> getTeams(){
		return (List<UserTeam>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(UserTeam.class);
				criteria.add(Restrictions.eq("pteamid", new Long(0)))
				.add(Restrictions.eq("flag", "1"))
				.addOrder(Order.asc("teamName"));
				return criteria.list();
			}
		});
	}
	/**
	 * 获取下放考点信息
	 * @return
	 * @author gkk
	 * @date 2016-11-8 上午9:30:23
	 */
	@SuppressWarnings("unchecked")
	public List<Station_xf> getXfs(){
		return (List<Station_xf>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			@Override
			public Object execute(Session session) throws Throwable {
				Criteria criteria = session.createCriteria(Station_xf.class);
				criteria.add(Restrictions.eq("valid", "1"))
				.addOrder(Order.asc("station_name"));
				return criteria.list();
			}
		});
	}

}
