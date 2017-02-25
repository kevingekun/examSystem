package com.wondersgroup.technocracy.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sun.jdi.LongValue;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.technocracy.bo.Addexpert;
import com.wondersgroup.technocracy.bo.Addexperts;
import com.wondersgroup.technocracy.bo.ExpertInfo;
import com.wondersgroup.technocracy.bo.IdentifyInfo;
import com.wondersgroup.technocracy.service.ArrangeExpertService;

@SuppressWarnings("serial")
public class ArrangeExpertAction extends AbstractPageNavAction {
	private ArrangeExpertService arrangeExpertService;
	private List<IdentifyInfo> identifyInfo;;
	private String teamId;
	private String examId;
	private String sj_mc;
	private String Kcid;
	private String Ksid;
	private String KCID;
	private String nduty;
	private String nremark;
	private String committeeId;
	private String committeeid;
	private String zjId;
	private String ZJID;
 
	private String KSID;
 
	private List<ExpertInfo> expertInfos;

	private String examid;
	private String idof92;
	private String expertid;
	private String reson;
	
	private String checkid;
	private String zymc;
	private String info="";
	
	private String experts;

	@Override
	public String doAcion() throws Exception {
		this.pageReturn = this.arrangeExpertService.arrangequery(this.pageTool,
				sj_mc);
		this.identifyInfo = this.pageReturn.getReturnList();
		return SUCCESS;
	}

	public String arrangeexpert() {
		zjId = getRequest().getParameter("zjid");
		committeeid = getRequest().getParameter("cid");// //从后台得到前台查出来的委员会id,赋制给committeeid，第二个页面前台接收
		return SUCCESS;
	}

	public String replept(){
		return SUCCESS;
	}
	public String replaceExpert(){
		boolean b = arrangeExpertService.replaceExpert(nduty, nremark, reson, expertid, idof92);
		if(b){
			return SUCCESS;
		}else {
			return "error";
		}
	}
	public String redict() {
		teamId = getRequest().getParameter("teamid");
		examId = getRequest().getParameter("eid");
		return SUCCESS;
	}
	
	public String checkexpert(){
		this.expertInfos = arrangeExpertService.checkExpert(teamId,examid);
		return SUCCESS;
	}
	public void deleteUseOfExpert(){
		boolean b = arrangeExpertService.deleteUseOfExpert(idof92);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(b){
				response.getWriter().write("删除成功！");
			}else{
				response.getWriter().write("删除失败！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String duty() {
		arrangeExpertService.arrangeExperts(KSID,KCID, committeeId, nduty, nremark,ZJID);
		return SUCCESS;
	}
	/**
	 * 自动安排专家（规则多）调存过
	 * 
	 * @author gkk
	 * @date 2016-10-25 下午2:00:48
	 */
	public void arrangeExpertAuto2(){
		String sjmc = "";
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getRequest().getSession().getServletContext()); 
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		Connection conn = null;
		try {
			sjmc = java.net.URLDecoder.decode(sj_mc , "UTF-8");
			conn = dataSource.getConnection();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Object> list = arrangeExpertService.arrangeExpertAuto(sjmc,conn);
		JSONArray json = JSONArray.fromObject(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
			/*if(Integer.valueOf((String)list.get(0))==0){
				
				response.getWriter().write("1");
			}else {
				response.getWriter().write((String)list.get(1));
			}*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 自动安排专家,抽取专家信息
	 * 
	 * @author gkk
	 * @date 2016-10-25 下午2:00:28
	 */
	public void arrangeExpertAuto(){
		Map<String, List<Object>> map = arrangeExpertService.arrangeExpertAuto2(examId);
		JSONArray json = JSONArray.fromObject(map);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
			//System.out.println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpSession session = getRequest().getSession();
		session.setAttribute("expertMap", map);
	}
	public void arrangeExpertAutoSubmit(){
		Map<String, List<Object>> map = (Map<String, List<Object>>) getRequest().getSession().getAttribute("expertMap");
		List<Object> list = map.get(experts);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(list.size()<1){
				response.getWriter().write("null");
			}else{
				String rtMsg = arrangeExpertService.arrangeExpertAutoSubmit(list, examId);
				response.getWriter().write(rtMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String relateMajorAndProfession(){
		boolean b = arrangeExpertService.relateMajorAndProfession(zymc, checkid);
		if (b) {
			info="关联成功";
		}else{
			info="关联失败";
		}
		return SUCCESS;
	}
	public void findcid() {
		List<Object> list = new ArrayList<Object>();

		String hzz001 = getRequest().getParameter("cid");
		Long ccid = arrangeExpertService.findcid(Long.valueOf(hzz001)).get(0)
				.getCommitteeid();
		list.add(hzz001);
		list.add(ccid);
		JSONArray json = JSONArray.fromObject(list);
		// System.out.println("json"+json);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * HttpServletResponse response = ServletActionContext.getResponse();
		 * try { response.getWriter().write(String.valueOf(ccid)); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
	}

	public ArrangeExpertService getArrangeExpertService() {
		return arrangeExpertService;
	}

	public void setArrangeExpertService(
			ArrangeExpertService arrangeExpertService) {
		this.arrangeExpertService = arrangeExpertService;
	}

	public List<IdentifyInfo> getIdentifyInfo() {
		return identifyInfo;
	}

	public void setIdentifyInfo(List<IdentifyInfo> identifyInfo) {
		this.identifyInfo = identifyInfo;
	}

	public String getSj_mc() {
		return sj_mc;
	}

	public void setSj_mc(String sj_mc) {
		this.sj_mc = sj_mc;
	}

	public String getKcid() {
		return Kcid;
	}

	public void setKcid(String kcid) {
		Kcid = kcid;
	}

	public String getNduty() {
		return nduty;
	}

	public String getCommitteeId() {
		return committeeId;
	}

	public void setCommitteeId(String committeeId) {
		this.committeeId = committeeId;
	}

	public String getNremark() {
		return nremark;
	}

	public void setNremark(String nremark) {
		this.nremark = nremark;
	}

	public void setNduty(String nduty) {
		this.nduty = nduty;
	}

	public String getCommitteeid() {
		return committeeid;
	}

	public void setCommitteeid(String committeeid) {
		this.committeeid = committeeid;
	}

	public String getKCID() {
		return KCID;
	}

	public void setKCID(String kCID) {
		KCID = kCID;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getZjId() {
		return zjId;
	}

	public void setZjId(String zjId) {
		this.zjId = zjId;
	}

	public String getZJID() {
		return ZJID;
	}

	public void setZJID(String zJID) {
		ZJID = zJID;
	}

 
	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public String getKsid() {
		return Ksid;
	}

	public void setKsid(String ksid) {
		Ksid = ksid;
	}

	public String getKSID() {
		return KSID;
	}

	public void setKSID(String kSID) {
		KSID = kSID;
	}

 
	public List<ExpertInfo> getExpertInfos() {
		return expertInfos;
	}

	public void setExpertInfos(List<ExpertInfo> expertInfos) {
		this.expertInfos = expertInfos;
	}

	public String getExamid() {
		return examid;
	}

	public void setExamid(String examid) {
		this.examid = examid;
	}

	public String getIdof92() {
		return idof92;
	}

	public void setIdof92(String idof92) {
		this.idof92 = idof92;
	}

	public String getExpertid() {
		return expertid;
	}

	public void setExpertid(String expertid) {
		this.expertid = expertid;
	}

	public String getReson() {
		return reson;
	}

	public void setReson(String reson) {
		this.reson = reson;
	}

	public String getCheckid() {
		return checkid;
	}

	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getExperts() {
		return experts;
	}

	public void setExperts(String experts) {
		this.experts = experts;
	}

}
