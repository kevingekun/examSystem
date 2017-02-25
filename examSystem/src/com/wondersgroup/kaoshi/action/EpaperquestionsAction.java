package com.wondersgroup.kaoshi.action;

import java.util.Date;
import java.util.List;

import com.wondersgroup.falcon.paper.model.EPaperquestions;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.kaoshi.service.EPapersService;
import com.wondersgroup.kaoshi.service.EAnswerpaperService;
import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;

public class EpaperquestionsAction extends AbstractPageNavAction {
	//试卷的id
	private String sjid;
	
	//查询条件
	private String stTg;
	private Date stlrsjbegin;
	private Date stlrsjend;
	
	//试题集合
	private List<EPaperquestions> lis;
	
	//试题错误率
	private String percent;
	//试卷
	private EPapers epapers;
	
	private String paperId;
	
	private List epaperquention;
	
	//查询试卷和实体的连接表
	private EpaperquestionsService  epaperquestionsService;
	//查询试卷
	private EPapersService epaperservice;
	
	private EAnswerpaperService eanswerpaperService;
	
	@Override
	public String doAcion() throws Exception {
		//查询试卷
		EPapers epapers=epaperservice.getEPapersById(sjid);
		this.epapers=epapers;
		//新建一个试题类，设置查询的参数
		EQuestions equestion=new EQuestions();
		equestion.setStTg(stTg);
		equestion.setStLrsj(stlrsjbegin);
		equestion.setStlrsjend(stlrsjend);
		this.pageReturn=epaperquestionsService.findAllByPage(this.pageTool,this.sjid,equestion);
		this.lis=pageReturn.getReturnList();
		return SUCCESS;
	}
	
	//查看错误率

	
	
//	public String cjpm() throws Exception {
//		//查询试卷
//		EPapers epapers=epaperservice.getEPapersById(sjid);
//		this.epapers=epapers;
//		//新建一个试题类，设置查询的参数
//		EQuestions equestion=new EQuestions();
//		equestion.setStTg(stTg);
//		equestion.setStLrsj(stlrsjbegin);
//		equestion.setStlrsjend(stlrsjend);
//		this.pageReturn=epaperquestionsService.findAllByPage(this.pageTool,this.sjid,equestion);
//		this.lis=pageReturn.getReturnList();
//		return SUCCESS;
//	}
	
	public String getSjid() {
		return sjid;
	}
	public void setSjid(String sjid) {
		this.sjid = sjid;
	}
	public List getLis() {
		return lis;
	}


	public EpaperquestionsService getEpaperquestionsService() {
		return epaperquestionsService;
	}


	public void setEpaperquestionsService(
			EpaperquestionsService epaperquestionsService) {
		this.epaperquestionsService = epaperquestionsService;
	}



	public String getStTg() {
		return stTg;
	}


	public void setStTg(String stTg) {
		this.stTg = stTg;
	}


	public void setLis(List<EPaperquestions> lis) {
		this.lis = lis;
	}


	public EPapers getEpapers() {
		return epapers;
	}


	public void setEpapers(EPapers epapers) {
		this.epapers = epapers;
	}


	public void setEpaperservice(EPapersService epaperservice) {
		this.epaperservice = epaperservice;
	}


	public Date getStlrsjbegin() {
		return stlrsjbegin;
	}


	public void setStlrsjbegin(Date stlrsjbegin) {
		this.stlrsjbegin = stlrsjbegin;
	}


	public Date getStlrsjend() {
		return stlrsjend;
	}


	public void setStlrsjend(Date stlrsjend) {
		this.stlrsjend = stlrsjend;
	}


	public String getPercent() {
		return percent;
	}


	public void setPercent(String percent) {
		this.percent = percent;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public List getEpaperquention() {
		return epaperquention;
	}

	public void setEpaperquention(List epaperquention) {
		this.epaperquention = epaperquention;
	}

}
