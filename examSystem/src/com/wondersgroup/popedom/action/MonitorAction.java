/** 
* 
* author:mxk 
*/ 
package com.wondersgroup.popedom.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.EExamArrange;
import com.wondersgroup.popedom.service.MonitorService;

/**监控考生情况
 * @author mxk
 *
 */
public class MonitorAction extends AbstractPageNavAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MonitorService monitorservice;
	private List<Object[]> monitorlist;
	private List<EExamArrange> arrangelist;
	private String examid;
	private List<Object[]> arrangeuserlist;
	private String teamid;
	private String delayTime;//延长时间
	private EPapers ePapers;
	private List<EPapers> pList;
	
	//强制交卷参数
	/**
	 * 是否清零0否1是
	 */
	private String flag;
	private String userid;
	/**
	 * 是否作弊0否1是
	 */
	private String cheatflag;
	private String remarks;
	/* (non-Javadoc)
	 * @see com.wondersgroup.kaoshi.util.AbstractPageNavAction#doAcion()
	 */
	@Override
	public String doAcion() throws Exception {
		this.monitorlist=this.monitorservice.queryMonitor();
		this.pList = this.monitorservice.getPaperInfo();//显示试卷名称
		//System.out.println("监控数量"+this.monitorlist.size());
		return SUCCESS;
	}
	/**
	 * 开始考试，可以考试
	 */
	public void startExam(){
		String state = monitorservice.startExam();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(state.equals("1")){
				getRequest().getSession().setAttribute("alreadyStart", state);
				response.getWriter().write(state);
			}else{
				response.getWriter().write(state);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
	}
	/**
	 * 开始登录，登录后可以看考生须知，但不能考试
	 */
	public void startLogin(){
		String state = monitorservice.startLogin();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(state.equals("1")||state.equals("3")){
				getRequest().getSession().setAttribute("alreadyLogin", "1");
				response.getWriter().write(state);
			}else{
				response.getWriter().write(state);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void forbiddenExam(){
		try {
			monitorservice.forbiddenExam();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write("false");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String queryArrange(){
		this.arrangelist=this.monitorservice.queryArrange(this.examid);
		
		return SUCCESS;
	}
	public String queryArrangeUser(){
	this.arrangeuserlist=this.monitorservice.queryArrangeUser(this.teamid,this.examid);
		return SUCCESS;
	}
	/*
	 * 监控网络
	 */
	public String queryPingIp(){
		this.monitorservice.queryPingIp();
		return SUCCESS;
	}
	
	
	//添加强制交卷信息
	public String updateMonitor(){
		 
		this.monitorservice.updateqiangzhi(this.userid, this.examid, this.flag, this.cheatflag,this.remarks);
		return SUCCESS;
	}
	/**
	 * 考生特殊情况，需要换电脑考试，清理ip
	 */
	public void cleanIp(){
		String result = this.monitorservice.cleanIp(userid, examid);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 清除考生的考试信息，以当前时间为开始考试时间，该考生可以重新答题
	 * @author gkk
	 * @date 2017-1-16 上午9:53:42
	 */
	public void cleanExam(){
		String result = this.monitorservice.cleanExam(userid, examid);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 考生延时操作
	 */
	public void delayTime(){
		try {
			remarks = java.net.URLDecoder.decode(remarks , "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String result = this.monitorservice.delayTime(examid, userid, delayTime, remarks);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getUnExamedUser(){
		List<Object> list = monitorservice.getUnExamedUser();
		JSONArray json = JSONArray.fromObject(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * getter setter
	 */
	
	public MonitorService getMonitorservice() {
		return monitorservice;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCheatflag() {
		return cheatflag;
	}
	public void setCheatflag(String cheatflag) {
		this.cheatflag = cheatflag;
	}
	public void setMonitorservice(MonitorService monitorservice) {
		this.monitorservice = monitorservice;
	}
	public List<Object[]> getMonitorlist() {
		return monitorlist;
	}
	public void setMonitorlist(List<Object[]> monitorlist) {
		this.monitorlist = monitorlist;
	}
	public List<EExamArrange> getArrangelist() {
		return arrangelist;
	}
	public void setArrangelist(List<EExamArrange> arrangelist) {
		this.arrangelist = arrangelist;
	}
	public String getExamid() {
		return examid;
	}
	public void setExamid(String examid) {
		this.examid = examid;
	}
	public List<Object[]> getArrangeuserlist() {
		return arrangeuserlist;
	}
	public void setArrangeuserlist(List<Object[]> arrangeuserlist) {
		this.arrangeuserlist = arrangeuserlist;
	}
	public String getTeamid() {
		return teamid;
	}
	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}
	public String getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(String delayTime) {
		this.delayTime = delayTime;
	}
	public EPapers getePapers() {
		return ePapers;
	}
	public void setePapers(EPapers ePapers) {
		this.ePapers = ePapers;
	}
	public List<EPapers> getpList() {
		return pList;
	}
	public void setpList(List<EPapers> pList) {
		this.pList = pList;
	}
	
	
	
}
