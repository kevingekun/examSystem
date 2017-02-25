/** 
* 
* author:mxk 
*/ 
package com.wondersgroup.popedom.service;

import java.util.List;

import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.popedom.bo.EUser;

/**
 * @author Administrator
 *
 */
public interface EUserTeamService {
	public List<EUser> queryUserByid(String tmeamid); 
	public UserTeam queryTeamByid(String teamid);
	//更新考点信息
	public UserTeam updateTeam(UserTeam userteam);
	public boolean getTeamByName(String teamName);
	public String addTeam(UserTeam userTeam);

}
