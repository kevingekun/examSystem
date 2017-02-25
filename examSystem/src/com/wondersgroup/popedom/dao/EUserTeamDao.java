/** 
 * 
 * author:mxk 
 */
package com.wondersgroup.popedom.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.popedom.bo.EGroup;
import com.wondersgroup.popedom.bo.EUser;

/**
 * @author Administrator
 * 
 */
public class EUserTeamDao extends HibernateDaoSupport {
	@SuppressWarnings("unchecked")
	public List<EUser> findTeamUser(final String teamid) throws Exception {
		return (List<EUser>) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						Criteria criteria = session.createCriteria(EUser.class);
						EGroup group = new EGroup();
						group.setId(Long.valueOf(teamid));
						criteria.add(Restrictions.eq("group", group));
						byte b = 1;
						criteria.add(Restrictions.eq("enabled", b));
						criteria.addOrder(Order.asc("username"));
						List<EUser> list = criteria.list();
						return list;

					}
				});
	}

	public UserTeam findTeamByid(final String teamid) throws Exception {
		return (UserTeam) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						Criteria criteria = session
								.createCriteria(UserTeam.class);
						criteria.add(Restrictions.eq("teamId",
								Long.valueOf(teamid)));
						List list = criteria.list();
						UserTeam team = new UserTeam();
						if (list.size() > 0) {
							team = (UserTeam) list.get(0);
						}
						return team;

					}
				});
	}

	public UserTeam updateTeam(final UserTeam userteam) throws Exception {
		return (UserTeam) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {

						UserTeam uTeam = (UserTeam) session.load(
								UserTeam.class, userteam.getTeamId());
						uTeam.setContactname(userteam.getContactname());
						uTeam.setTeamaddress(userteam.getTeamaddress());
						uTeam.setTeamName(userteam.getTeamName());
						uTeam.setContactph(userteam.getContactph());
						uTeam.setCount(userteam.getCount());
						uTeam.setNumend(userteam.getNumend());
						uTeam.setNumstart(userteam.getNumstart());
						session.update(uTeam);
						return uTeam;

					}
				});
	}
	
	public boolean getTeamByName(final String teamName){
		return (Boolean) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				String name = teamName.trim();
				Criteria criteria = session.createCriteria(UserTeam.class);
				criteria.add(Restrictions.eq("teamName", name))
				.add(Restrictions.eq("flag", "1"));
				if(criteria.list().size()>0){
					return true;
				}else{
					return false;
				}
			}
		});
	}
	
	public String addTeam(final UserTeam userTeam){
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				String state = "";
				try {
					userTeam.setPteamid(new Long(0));
					userTeam.setFlag("1");
					session.save(userTeam);
					state = "success";
				} catch (Exception e) {
					state = "error";
					e.printStackTrace();
				}
				return state;
			}
		});
	}

}
