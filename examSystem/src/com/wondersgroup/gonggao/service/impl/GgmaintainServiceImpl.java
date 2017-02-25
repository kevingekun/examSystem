package com.wondersgroup.gonggao.service.impl;

import java.util.List;

import com.wondersgroup.gonggao.bo.Mgg;
import com.wondersgroup.gonggao.dao.MggDao;
import com.wondersgroup.gonggao.service.GgmaintainService;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public class GgmaintainServiceImpl implements GgmaintainService {
	MggDao mggDao;
	
	
	public void setMggDao(MggDao mggDao) {
		this.mggDao = mggDao;
	}


	public PageReturn findgg(PageTool pageTool,String sj_mc) {
		
		try {
			return mggDao.findgg(pageTool, sj_mc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void savenotice(String hzz092,String sjmc) {
		  try {
			this.mggDao.savenotice(hzz092, sjmc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Mgg> getadd(String sjid){
		
		try {
			return mggDao.getadd(sjid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void updateMgg(final Mgg mgg){
		this.mggDao.updateMgg(mgg);
	}
	public List<Object[]> queryNotice(){
		try {
			return this.mggDao.queryNotice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
