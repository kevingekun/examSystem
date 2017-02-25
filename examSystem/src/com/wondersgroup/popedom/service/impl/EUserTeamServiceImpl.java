/** 
* 
* author:mxk 
*/ 
package com.wondersgroup.popedom.service.impl;

import java.util.List;

import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.dao.EUserTeamDao;
import com.wondersgroup.popedom.service.EUserTeamService;

/**
 * @author Administrator
 *
 */
public class EUserTeamServiceImpl implements EUserTeamService {
	private EUserTeamDao euserteamDao;
	
	public EUserTeamDao getEuserteamDao() {
		return euserteamDao;
	}

	public void setEuserteamDao(EUserTeamDao euserteamDao) {
		this.euserteamDao = euserteamDao;
	}

	/* (non-Javadoc)
	 * @see com.wondersgroup.popedom.service.EUserTeamService#queryUserByid(java.lang.String)
	 */
	public List<EUser> queryUserByid(String tmeamid) {
		List<EUser> list;
		try {
			list = this.euserteamDao.findTeamUser(tmeamid);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public UserTeam queryTeamByid(String teamid){
		UserTeam team;
		try {
			team = this.euserteamDao.findTeamByid(teamid);
			return team;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public UserTeam updateTeam(UserTeam userteam){
		UserTeam team;
		try {
			team = this.euserteamDao.updateTeam(userteam);
			return team;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean getTeamByName(String teamName) {
		return this.euserteamDao.getTeamByName(teamName);
	}

	public String addTeam(UserTeam userTeam) {
		return this.euserteamDao.addTeam(userTeam);
	}
	

}
