/** 
* 
* author:mxk 
*/ 
package com.wondersgroup.popedom.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.popedom.bo.EExamArrange;
import com.wondersgroup.popedom.dao.MonitorDao;
import com.wondersgroup.popedom.service.MonitorService;
import com.wondersgroup.utils.PingIp;

/**
 * @author Administrator
 *
 */
public class MonitorServiceImpl implements MonitorService{
	private MonitorDao monitorDao;
	/* (non-Javadoc)
	 * @see com.wondersgroup.popedom.service.MonitorService#queryMonitor()
	 */
	public List<Object[]> queryMonitor() {
		List<Object[]> list  =this.monitorDao.queryMoitor();
		return list;
	}
	public List<EPapers> getPaperInfo(){
		return this.monitorDao.getPaperInfo();
	}
	/* (non-Javadoc)
	 * @see com.wondersgroup.popedom.service.MonitorService#queryArrange()
	 */
	public List<EExamArrange> queryArrange(String examid) {
		List<EExamArrange> list;
		try {
			list = this.monitorDao.queryArrange(examid);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Object[]> queryArrangeUser(String teamid,String examid){
		List<Object[]> list=this.monitorDao.queryArrangeUser(teamid,examid);
		return list;
	}

	public void updateqiangzhi(String userid,String examid,String flag,String cheatflag,String remarks){
		this.monitorDao.updateQZJJ(userid, examid, flag, cheatflag,remarks);
	}
	
	public void queryPingIp(){
		this.monitorDao.queryPingIp();
		
	}
	
	
	/*
	 * getter setter
	 */
	public MonitorDao getMonitorDao() {
		return monitorDao;
	}
	public void setMonitorDao(MonitorDao monitorDao) {
		this.monitorDao = monitorDao;
	}
	
	public String startExam(){
		return monitorDao.startExam();
	}
	public String startLogin(){
		return monitorDao.startLogin();
	}
	public void forbiddenExam(){
		monitorDao.forbiddenExam();
	}
	public String cleanIp(String userid, String examid) {
		return monitorDao.cleanIp(userid, examid);
	}
	public String cleanExam(String userid, String examid) {
		return monitorDao.cleanExam(userid, examid);
	}
	public String delayTime(String examid, String userid, String delayTime,String remarks) {
		return monitorDao.delayTime(examid, userid, delayTime, remarks);
	}
	@Override
	public List<Object> getUnExamedUser() {
		return monitorDao.getUnExamedUser();
	}
}
