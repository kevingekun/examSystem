package com.wondersgroup.popedom.service.impl;

import java.util.List;

import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.bo.EuserTest;
import com.wondersgroup.popedom.bo.ExamStaff;
import com.wondersgroup.popedom.bo.Users_yth;
import com.wondersgroup.popedom.dao.AddExamineeDao;
import com.wondersgroup.popedom.service.AddExamineeService;

public class AddExamineeServiceImpl implements AddExamineeService {
	private AddExamineeDao addExamineeDao;

	public void addexaminee(EUser euser, String role) {

		try {
			this.addExamineeDao.addexaminee(euser, role);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void addexamineeYth(Users_yth euser, String role) {

		try {
			this.addExamineeDao.addexamineeYth(euser, role);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<EUser> queryuser(String idnumber) {
		try {
			return addExamineeDao.queryuser(idnumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setAddExamineeDao(AddExamineeDao addExamineeDao) {
		this.addExamineeDao = addExamineeDao;
	}

	public List<Object[]> queryRole() {

		try {
			return this.addExamineeDao.queryRole();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<UserTeam> geTeams() {
		return this.addExamineeDao.getTeams();
	}

}
