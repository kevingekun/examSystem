package com.wondersgroup.falcon.dao.auth;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.wondersgroup.falcon.model.auth.User;
import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.falcon.model.citizeninfo.DicInfo;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.popedom.bo.EExamArrange;

/**
 * 
 * <p>Title:[����Ϣ�־û�������] </p>
 * <p>Description: [�๦������]</p> 
 *
 * Created by [Kevin Liang] [2009-8-17]
 * Midified by [�޸���] [�޸�ʱ��]
 *
 */

public class UserTeamDAO
{
	private static final Log log = LogFactory.getLog(UserTeamDAO.class);
    public UserTeamDAO()
    {
    }

    public void saveOrUpdate(final UserTeam transientInstance)
    {
        HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                session.saveOrUpdate(transientInstance);
                return null;
            }

           
        }
);
    }
    public void DicInfosaveOrUpdate(final DicInfo transientInstance)
    {
        HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                session.saveOrUpdate(transientInstance);
                return null;
            }

           
        }
);
    }

   

    public void delete(final UserTeam persistentInstance)
    {
        HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                session.delete(persistentInstance);
                return null;
            }

            
        }
);
    }

    public UserTeam findById(final Long id)
    {
        return (UserTeam)HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                UserTeam instance = (UserTeam)session.get(UserTeam.class, id);
                return instance;
            }

           
        }
);
    }
    public DicInfo DicInfofindById(final Long id)
    {
        return (DicInfo)HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
            	DicInfo instance = (DicInfo)session.get(DicInfo.class, id);
                return instance;
            }

           
        }
);
    }

    public List findById_fenpei(final Long id)
    {
        return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                String sql = (new StringBuilder("select aa.user_id, aa.username,aa.realname,aa.team_name,bb.dealuserrealname  from (select u.user_id, u.username, u.realname, e.team_name from users u, user_team_relation t, user_team e    where u.user_id = t.user_id     and t.team_id = e.team_id     and t.team_id = ")).append(id).append("  and t.isleader is null) aa,").append("  errorcall_user_temporary bb  ").append("      where aa.username = bb.username(+) order by aa.username asc").toString();
                System.out.println(sql);
                SQLQuery squery = session.createSQLQuery(sql);
                List result = squery.list();
                return result;
            }

           
        }
);
    }

    public List<UserTeam> findAll()
    {
        return (List<UserTeam>)HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                String queryString = "from UserTeam ut order by ut.teamId";
                Query queryObject = session.createQuery(queryString);
                return queryObject.list();
            }

          
        }
);
    }
    public List<UserTeam> findTeamBylistid(final List idlist)
    {
        return (List<UserTeam>)HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                String queryString = "from UserTeam ut where ut.teamId in (:ids) order by ut.teamId";
                Query queryObject = session.createQuery(queryString);
                queryObject.setParameterList("ids", idlist); 
                return queryObject.list();
            }

          
        }
);
    }
    public List<UserTeam> getExamByUserid(final List idlist)
    {
        return (List<UserTeam>)HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                String queryString = "from UserTeam ut where ut.teamId in (:ids) order by ut.teamId";
                Query queryObject = session.createQuery(queryString);
                queryObject.setParameterList("ids", idlist); 
                return queryObject.list();
            }

          
        }
);
    }
    public List<EExamArrange> findArrange(final String examid)
    {
        return (List<EExamArrange>)HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
            	Criteria criteria=session.createCriteria(EExamArrange.class);
        		criteria.add(Restrictions.eq("examid", Long.valueOf(examid)));
        		
        		List<EExamArrange> list=criteria.list();
                return list;
            }

          
        }
);
    }
    @SuppressWarnings("unchecked")
	public List<Object[]> findExamUser(){
        return (List<Object[]>)HibernateUtil.doInSession(new HibernateSessionCallback() {
            public Object execute(Session session)
                throws Throwable
            {
                String queryString = "SELECT p.sj_id, p.sj_mc from E_PAPERS p,(select e.examid from e_exam_arrange e group by e.examid) w where " +
                		"w.examid = p.sj_id and TO_number(TO_CHAR(sysdate, 'YYYYMMDD')) between TO_number(TO_CHAR(p.sj_Kksj, 'YYYYMMDD')) and " +
                		"TO_number(TO_CHAR(p.sj_Yxqjzsj, 'YYYYMMDD')) and (p.sj_zt != '2' and p.sj_zt != '3' and p.sj_zt != '5')";
                Query queryObject = session.createSQLQuery(queryString);
                return queryObject.list();
            }
        });
        }

    public List findAll_fenpei()
    {
        return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                String queryString = "from UserTeam ut where ut.teamId in(52,53,56) order by ut.teamId";
                Query queryObject = session.createQuery(queryString);
                return queryObject.list();
            }

          
        }
);
    }

    public void cleanUsers(final UserTeam team)
    {
        HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                session.update(team);
                return null;
            }

        }
);
    }

   

    

}