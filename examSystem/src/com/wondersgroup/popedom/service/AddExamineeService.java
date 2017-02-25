package com.wondersgroup.popedom.service;

import java.util.List;

import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.bo.Users_yth;

public interface AddExamineeService {
	public void addexaminee(EUser euser, String role);
	
	public void addexamineeYth(Users_yth euser, String role);

	public List<EUser> queryuser(String idnumber);

	public List<Object[]> queryRole();
	
	public List<UserTeam> geTeams();

}
