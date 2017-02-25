package com.wondersgroup.kaoshi.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.sf.json.JSONArray;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.paper.model.EAnswerpaper;
import com.wondersgroup.falcon.paper.model.EAnswerquestions;
import com.wondersgroup.falcon.paper.model.EPaperquestions;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.kaoshi.bo.EAnswertemp;
import com.wondersgroup.kaoshi.service.EAnswerpaperService;
import com.wondersgroup.kaoshi.service.EPapersService;
import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.kaoshi.util.AbstractAction;
import com.wondersgroup.popedom.bo.ELogMonitor;
import com.wondersgroup.popedom.bo.EUser;

public class AjaxPaperFenshu extends AbstractAction {
	// 查询试卷和实体的连接表
	private EpaperquestionsService epaperquestionsService;
	private String typeId;
	private String paperId;
	private String typeFenshu;
	//answer='+answer+'&paperid='+paperid+'&questionid
	private String answer;
	private String shijuanid;
	private String questionid;
	private String tempflag;
	private String questiontype;
	private String times;
	private String returnmessage;
	
	//判断是否因为网络断开
	private String pingaddress;
	//返回listjson；
	private JSONArray listjson;
	
	// 试卷
	private EPapers epapers;
	// 考试人员工号
	private String ryid;
	// 未参加考试原因
	private String cause;
	private EPapersService epaperservice;
	// 答题service
	private EAnswerpaperService eanswerpaperService;
	
	
	
	 
	

	public String getReturnmessage() {
		return returnmessage;
	}

	public void setReturnmessage(String returnmessage) {
		this.returnmessage = returnmessage;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public JSONArray getListjson() {
		return listjson;
	}

	public void setListjson(JSONArray listjson) {
		this.listjson = listjson;
	}

	public String getPingaddress() {
		return pingaddress;
	}

	public void setPingaddress(String pingaddress) {
		this.pingaddress = pingaddress;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	 
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getShijuanid() {
		return shijuanid;
	}

	public void setShijuanid(String shijuanid) {
		this.shijuanid = shijuanid;
	}

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public String getTempflag() {
		return tempflag;
	}

	public void setTempflag(String tempflag) {
		this.tempflag = tempflag;
	}
	

	
	public String getQuestiontype() {
		return questiontype;
	}

	public void setQuestiontype(String questiontype) {
		this.questiontype = questiontype;
	}

	public String execute() throws Exception {
		/*
		 * 找到试卷的某个类型的所有的分数
		 */
		this.typeFenshu = String.valueOf(this.epaperquestionsService.findFenshuByTypeAnd(paperId, typeId));

		return SUCCESS;
	}

	public String addAnswerTemp() {
		// System.out.println("测试插入临时表");
		String utfanswer = null;
		try {
			utfanswer = URLDecoder.decode(this.answer, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(getRequest().getSession()
						.getServletContext());
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.tempflag = "1";
		EUser user = ((UserDetailsImpl) AcegiUtil.getUserDetails()).getUser();
		this.epaperquestionsService.addAnswerTemp(user.getId().toString(),
				utfanswer, this.shijuanid, this.questionid, this.questiontype,
				conn);
		return SUCCESS;
	}
	//判断是否网络断开
	public String pingAddress(){
		List<EAnswertemp> list=this.epaperquestionsService.pingIp(this.pingaddress);
		 this.listjson = JSONArray.fromObject(list); 
		return SUCCESS;
	}
	/** 设置未参加考试原因 */
	public String nopartinexamCause() {
		this.epaperservice.delete();
		EUser user = this.eanswerpaperService.findEUser(ryid);
		this.epapers = this.epaperservice.getEPapersById(paperId);
		EAnswerpaper answerpaper = new EAnswerpaper();
		answerpaper.setDjId(new Long(0));
		answerpaper.setEpapers(epapers);
		answerpaper.setDjRyid(user.getId().toString());
		answerpaper.setDjRymc(user.getRealname());
		answerpaper.setEanswerquestionses(null);
		answerpaper.setUserSex(user.getSex());// 考试人性别
		answerpaper.setUserStar(user.getUserstar());// 考试人进中心时间
		answerpaper.setUserDate(user.getCreatetime());// 考试人星级
		answerpaper.setGroupId(user.getGroup().getId().toString());// 添加小组的id
		answerpaper.setDjJssj(new Date());
		answerpaper.setDjKssj(new Date());
		answerpaper.setCause(cause);
		this.eanswerpaperService.saveEAnswerpaper(answerpaper);
		// 找到试卷的所有题目
		Iterator it = this.epapers.getEpaperquestionses().iterator();
		while (it.hasNext()) {
			int px = 0;
			EPaperquestions epaperquestions = (EPaperquestions) it.next();
			long type = epaperquestions.getEquestions().getEquestiontype()
					.getPriority();
			String stid = new Long(epaperquestions.getEquestions().getStId())
					.toString();
			// 新建一个答题
			EAnswerquestions eanswerquestions = new EAnswerquestions();
			eanswerquestions.setEanswerpaper(answerpaper);// 设置答题的答卷
			eanswerquestions.setEpaperquestions(epaperquestions);// 设置答题对应的试题
			eanswerquestions.setStPx(px);
			if (type == 2) {// 如果是多选题
				String[] daan = this.getRequest().getParameterValues(
						"answer" + stid);
				if (daan != null) {
					String str_daan = "";
					for (int i = 0; i < daan.length; i++) {
						if (i == daan.length - 1) {
							str_daan += daan[i];
						} else {
							str_daan += daan[i] + "||";
						}
					}
					eanswerquestions.setStDa(str_daan);// 设置答题
				} else {
					eanswerquestions.setStDa(null);// 设置答题
				}

			} else if (type == 4) {// 如果是判断说明题
				String daan = this.getRequest().getParameter("answer" + stid);
				eanswerquestions.setStDa(daan);// 设置答题
				String daansm = this.getRequest().getParameter(
						"answer" + stid + "sm");
				eanswerquestions.setStDasm(daansm);
			} else {
				String daan = this.getRequest().getParameter("answer" + stid);
				eanswerquestions.setStDa(daan);// 设置答题
			}
			this.eanswerpaperService.saveEAnswerquestions(eanswerquestions);
			px++;
		}
		return SUCCESS;
	}
	
	//更新考生剩余时间
	public String updateSurplus(){
		String time=String.valueOf(Math.round(Integer.valueOf(this.times)/60000));
		ELogMonitor logmonitor=this.eanswerpaperService.updateSurplus(this.paperId,time);
		this.returnmessage=logmonitor.getCheatflag();
		return SUCCESS;
	}

	public void setEpaperquestionsService(
			EpaperquestionsService epaperquestionsService) {
		this.epaperquestionsService = epaperquestionsService;
	}

	public String getTypeFenshu() {
		return typeFenshu;
	}

	public void setTypeFenshu(String typeFenshu) {
		this.typeFenshu = typeFenshu;
	}

	public EPapers getEpapers() {
		return epapers;
	}

	public void setEpapers(EPapers epapers) {
		this.epapers = epapers;
	}

	public String getRyid() {
		return ryid;
	}

	public void setRyid(String ryid) {
		this.ryid = ryid;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public void setEpaperservice(EPapersService epaperservice) {
		this.epaperservice = epaperservice;
	}

	public void setEanswerpaperService(EAnswerpaperService eanswerpaperService) {
		this.eanswerpaperService = eanswerpaperService;
	}

	public EpaperquestionsService getEpaperquestionsService() {
		return epaperquestionsService;
	}

}
