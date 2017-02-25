/** 
* 
* author:mxk 
*/ 
package com.wondersgroup.utils;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.popedom.bo.EExamArrange;
import com.wondersgroup.popedom.bo.ELogMonitor;
import com.wondersgroup.popedom.bo.EUser;


/**添加考生相关信息
 * @author Administrator
 *
 */
public class AddUserInformation extends HibernateDaoSupport{
	/**
	 * 考生相关信息
	 */
	public void addUser(EUser users ,String teamid ,String examid ,String jiankaoUid){
		//添加用户信息
		AddUserInformation add=new AddUserInformation();
		//add.SaveUser(users);
		//保存考场监控信息
		ELogMonitor elogmonitor=new ELogMonitor();
		elogmonitor.setExamid(Long.valueOf(examid));
		elogmonitor.setUserid(users.getId());
		elogmonitor.setState("2");
		try {
			add.SaveELogmonitor(elogmonitor);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//保存考场信息
		EExamArrange eexamarrange=new EExamArrange();
		UserTeam userteam=new UserTeam();
		userteam.setTeamId(Long.valueOf(teamid));
		eexamarrange.setExamid(Long.valueOf(examid));
		eexamarrange.setUserid(users);
		eexamarrange.setTeamid(userteam);
		eexamarrange.setUsertype("1");
		try {
			add.SaveEExamArrange(eexamarrange);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//保存人员信息
	public void  SaveUser(final EUser user)
	throws Exception {
        HibernateUtil.doInSession(new HibernateSessionCallback() {
	public Object execute(Session session) throws Throwable {
		session.save(user);
		return null;
	}
});
}
	//保存考场信息
	public void SaveEExamArrange(final EExamArrange eexamarrange) 
	throws Exception {
        HibernateUtil.doInSession(new HibernateSessionCallback() {
	public Object execute(Session session) throws Throwable {
		session.save(eexamarrange);
		return null;
	}
});
}
	//保存监控信息
	public void  SaveELogmonitor(final ELogMonitor elogmonitor)
	throws Exception {
        HibernateUtil.doInSession(new HibernateSessionCallback() {
	public Object execute(Session session) throws Throwable {
		session.save(elogmonitor);
		return null;
	}
});
}
	
}
