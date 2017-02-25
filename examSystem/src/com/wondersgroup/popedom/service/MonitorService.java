/** 
* 
* author:mxk 
*/ 
package com.wondersgroup.popedom.service;

import java.util.List;

import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.popedom.bo.EExamArrange;

/**
 * @author Administrator
 *
 */
public interface MonitorService {
	/**
	 * 查询监控表中信息
	 */
	public List<Object[]> queryMonitor();
	
	public List<EPapers> getPaperInfo();
	
	public List<EExamArrange> queryArrange(String examid);
	
	public List<Object[]> queryArrangeUser(String teamid,String examid);
	/**
	 * 添加强制交卷信息
	 */
	public void updateqiangzhi(String userid,String examid,String flag,String cheatflag,String remarks);
	
	public String cleanIp(String userid,String examid);
	
	public String cleanExam(String userid,String examid);
	/*
	 * 监控网络
	 */
	public void queryPingIp();
	
	public String startExam();//开始考试
	public String startLogin();//开始登录
	
	public void forbiddenExam();
	/**
	 * 考生延时
	 * @param examid
	 * @param userid
	 * @param delayTime
	 * @return
	 */
	public String delayTime(String examid,String userid,String delayTime,String remarks);
	/**
	 * 获取缺考考生信息
	 * @return
	 */
	public List<Object> getUnExamedUser();
}
