package com.wondersgroup.popedom.service.impl;

import java.util.List;

import com.wondersgroup.kaoshi.bo.EKaoshi;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.bo.EuserTest;
import com.wondersgroup.popedom.dao.EuserDao;
import com.wondersgroup.popedom.service.EUserService;

public class EUserServiceImpl implements EUserService {
	private EuserDao euserDao;

	public void setEuserDao(EuserDao euserDao) {
		this.euserDao = euserDao;
	}
	
	public PageReturn findAllUsers(PageTool pageTool,EUser eu){
		try {
			return this.euserDao.findAllUers(pageTool,eu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void updateUser(EUser user) {
		try {
			this.euserDao.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public EUser  load(Long id){
		try {
			return this.euserDao.load(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateUserAuth(Long userId, String[] authid) {
		try {
			this.euserDao.UpdateUserAuth(userId, authid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(Long userId) throws Exception{
		this.euserDao.deleteUser(userId);
	}
	
	public PageReturn getallkaoshi()throws Exception{
		return this.euserDao.getallkaoshi();
		
	}
	
	public void deleteeKaoshi(String[] deleteeKaoshi )throws Exception{
		 this.euserDao.deleteeKaoshi(deleteeKaoshi);
		
	}

	
}
