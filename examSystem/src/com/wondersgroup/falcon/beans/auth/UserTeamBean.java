
package com.wondersgroup.falcon.beans.auth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.wondersgroup.falcon.dao.auth.UserTeamDAO;
import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.falcon.model.citizeninfo.DicInfo;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.popedom.bo.EExamArrange;

public class UserTeamBean
{
	private static final Log log = LogFactory.getLog(UserTeamBean.class);
    public UserTeamBean()
    {
    }

    public void saveOrUpdate(final UserTeam team)
    {
        HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                UserTeamDAO userTeamDAO = new UserTeamDAO();
                userTeamDAO.saveOrUpdate(team);
                return null;
            }

           
        }
);
    }
    public void DicInfosaveOrUpdate(final DicInfo info)
    {
        HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                UserTeamDAO userTeamDAO = new UserTeamDAO();
                userTeamDAO.DicInfosaveOrUpdate(info);
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
                UserTeam t = null;
                UserTeamDAO dao = new UserTeamDAO();
                t = dao.findById(id);
                return t;
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
            	DicInfo t = null;
                UserTeamDAO dao = new UserTeamDAO();
                t = dao.DicInfofindById(id);
                return t;
            }

            
        }
);
    }

    public void deleteUserTeam(final UserTeam userTeam)
    {
        HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                UserTeamDAO userTeamDAO = new UserTeamDAO();
                userTeamDAO.delete(userTeam);
                return null;
            }

            
        }
);
    }

  

    public List<UserTeam> getAllTeams()
    {
        return (List<UserTeam>)HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                List<UserTeam> list = null;
                UserTeamDAO userTeamDAO = new UserTeamDAO();
                list = userTeamDAO.findAll();
                return list;
            }

            
        }
);
    }
    @SuppressWarnings("unchecked")
	public List<UserTeam> getArrange(final String examid)
    {
        return (List<UserTeam>)HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
            System.out.println("examid"+examid);
            	Set<String> set=new HashSet<String>();
                List<EExamArrange> list = null;
                UserTeamDAO userTeamDAO = new UserTeamDAO();
                list = userTeamDAO.findArrange(examid);
                for(EExamArrange arrange:list){
                	System.out.println(arrange.getTeamid().getPteamid()+"====="+arrange.getTeamid().getTeamId());
                	set.add((arrange.getTeamid().getPteamid()).toString());
                	set.add((arrange.getTeamid().getTeamId()).toString());
                }
                List<Long> idlist=new ArrayList<Long>();
                for (String str : set) {  
                    idlist.add(Long.valueOf(str));
              } 
                List<UserTeam> teamlist=new ArrayList<UserTeam>();
                if(idlist.size()>0){
                	teamlist=userTeamDAO.findTeamBylistid(idlist);
                }
                return teamlist;
            }

            
        }
);
    }
    /*
     * 查询当前老师监考考试
     */
    public String  getExamByUserid()
    {
        return (String )HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
            	String examid=null;
            	return examid;
            }

            
        }
);
    }
    
    
    public List<Object[]> getExam()
    {
        return (List<Object[]>)HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                List<Object[]> list = null;
                UserTeamDAO userTeamDAO = new UserTeamDAO();
                list = userTeamDAO.findExamUser();
                System.out.println("查询考试安排："+list.size());
                return list;
            }

            
        }
);
    }

    public List getAllTeams_fenpei()
    {
        return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                List list = null;
                UserTeamDAO userTeamDAO = new UserTeamDAO();
                list = userTeamDAO.findAll_fenpei();
                return list;
            }

           
        }
);
    }

    public UserTeam getUserTeamById(final Long id)
    {
        return (UserTeam)HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                UserTeam t = null;
                UserTeamDAO dao = new UserTeamDAO();
                t = dao.findById(id);
                return t;
            }

           
        }
);
    }

    public List getUserTeamById_fenpei(final Long id)
    {
        return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {

            public Object execute(Session session)
                throws Throwable
            {
                List t = null;
                UserTeamDAO dao = new UserTeamDAO();
                t = dao.findById_fenpei(id);
                return t;
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
                UserTeamDAO dao = new UserTeamDAO();
                dao.cleanUsers(team);
                return null;
            }

            
        }
      );
    }

}
