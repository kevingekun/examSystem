package com.wondersgroup.popedom.service.impl;

import java.util.List;

import com.wondersgroup.kaoshi.bo.EKaoshi;
import com.wondersgroup.popedom.bo.EUserMenus;
import com.wondersgroup.popedom.dao.EUserMenusDao;
import com.wondersgroup.popedom.service.EUserMenusService;

public class EUserMenusServiceImpl implements EUserMenusService {
	
	private EUserMenusDao euserMenusDao;
	public List<EUserMenus> findTopMenus(Long userid,String parentid,Long systemtype,Long status){
		try {
			return this.euserMenusDao.findTopMenus(userid, parentid, systemtype, status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void setEuserMenusDao(EUserMenusDao euserMenusDao) {
		this.euserMenusDao = euserMenusDao;
	}
	public List<EUserMenus> findMenusByParent(String parentid) {
		try {
			return this.euserMenusDao.findMenusByParent(parentid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<EUserMenus> findAllMenus(){
		try {
			return this.euserMenusDao.findMenus();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
