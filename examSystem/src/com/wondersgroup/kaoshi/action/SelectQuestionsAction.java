package com.wondersgroup.kaoshi.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wondersgroup.falcon.paper.model.EPaperquestions;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.falcon.question.model.EQuestionsDTO;
import com.wondersgroup.kaoshi.service.EPapersService;
import com.wondersgroup.kaoshi.service.EQuestionsService;
import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;

public class SelectQuestionsAction extends AbstractPageNavAction {
	private String fenshu;
	private String[] questionIds;

	// 各题的分值
	private String fenzhi;

	// 类型id
	private String typeId;

	// 试卷id
	private String paperid;

	// 试题
	private List questiongs;

	// 试卷
	private EPapers paper;

	// 试卷paperType
	private String paperType;

	// 业务类型
	private List busTypes;
	private List eimportances;

	// 已经存在的试题
	private List hasQuestions;

	private EQuestionsService equestionsService;

	private EPapersService epaperservice;

	private EpaperquestionsService epaperquestionsService;
	
	private String gzid;
	private String gzdj;
	private long difficulty;
	/*
	 * 试题查询条件
	 */
	private String stMc;
	private Long busType;
	private Date inputtime;
	private Date sjbegin;
	private Date sjend;
	private Long eimportance;
	private String xingzhi;

	public Date getInputtime() {
		return inputtime;
	}

	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}

	@Override
	public String doAcion() throws Exception {
		// 查询试卷
		EPapers epaper = epaperservice.getEPapersById(paperid);
		this.paper = epaper;
		// 找到试卷所有题目
		this.hasQuestions = epaperquestionsService.findAllByIdType(paperid,
				typeId);

		// 找到所属试题类型的试题
		this.pageReturn = this.equestionsService.findQuetionsByType(
				this.pageTool, typeId, stMc, busType, inputtime, eimportance,
				sjbegin, sjend, xingzhi,gzid,gzdj,difficulty);

		this.questiongs = pageReturn.getReturnList();
		for (int i = 0; i < questiongs.size(); i++) {
			/*EQuestions equestions = (EQuestions) questiongs.get(i);
			for (int j = 0; j < hasQuestions.size(); j++) {
				EPaperquestions epaperquestions = (EPaperquestions) hasQuestions
						.get(j);
				if (equestions.getStId() == epaperquestions.getEquestions()
						.getStId()) {
					equestions.setHas(1);
					break;
				}
			}*/
			EQuestionsDTO equestions = (EQuestionsDTO) questiongs.get(i);
			for (int j = 0; j < hasQuestions.size(); j++) {
				EPaperquestions epaperquestions = (EPaperquestions) hasQuestions
						.get(j);
				if (equestions.getId() == epaperquestions.getEquestions()
						.getStId()) {
					//equestions.setHas(1);
					break;
				}
			}
		}
		double fenshuww = this.epaperquestionsService
				.findPaperQuestionFensu(paperid);
		this.fenshu = String.valueOf(fenshuww);

		// 找到业务类型
		this.busTypes = this.epaperquestionsService.findallbuType();
		// 找到所有的难易度
		this.eimportances = this.epaperquestionsService.findallEimportances();

		return SUCCESS;
	}

	public String addQuestions() throws Exception {
		// 查询试卷
		this.paper = epaperservice.getEPapersById(paperid);
		// 根据试题id 找到 分数
		Map<String, Double> map = new HashMap<String, Double>();

		// for(int i =0;i<questionIds.length;i++){
		// String id=questionIds[i]+"fenshu";
		// Double dou=new Double(2);
		// if(this.getRequest().getParameter(id)!=null &&
		// !this.getRequest().getParameter(id).equals("")){
		// dou=new Double(this.getRequest().getParameter(id));
		// }
		// map.put(id, dou);
		// }
		// //向数据库插入几道题
		// this.epaperquestionsService.addQuestion(questionIds, paperid,map);
		/*
		 * 插入完了以后，计算分数
		 */
		Double db = new Double(2);
		db = new Double(fenzhi);
		this.epaperquestionsService.addQs(questionIds, paperid, db);
		double fenshuww = this.epaperquestionsService
				.findPaperQuestionFensu(paperid);
		this.fenshu = String.valueOf(fenshuww);
		return this.execute();
	}

	public void setEquestionsService(EQuestionsService equestionsService) {
		this.equestionsService = equestionsService;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public void setEpaperservice(EPapersService epaperservice) {
		this.epaperservice = epaperservice;
	}

	public List getQuestiongs() {
		return questiongs;
	}

	public void setQuestiongs(List questiongs) {
		this.questiongs = questiongs;
	}

	public EPapers getPaper() {
		return paper;
	}

	public void setPaper(EPapers paper) {
		this.paper = paper;
	}

	public String[] getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(String[] questionIds) {
		this.questionIds = questionIds;
	}

	public void setEpaperquestionsService(
			EpaperquestionsService epaperquestionsService) {
		this.epaperquestionsService = epaperquestionsService;
	}

	public List getHasQuestions() {
		return hasQuestions;
	}

	public void setHasQuestions(List hasQuestions) {
		this.hasQuestions = hasQuestions;
	}

	public String getFenshu() {
		return fenshu;
	}

	public void setFenshu(String fenshu) {
		this.fenshu = fenshu;
	}

	public List getBusTypes() {
		return busTypes;
	}

	public void setBusTypes(List busTypes) {
		this.busTypes = busTypes;
	}

	public String getStMc() {
		return stMc;
	}

	public void setStMc(String stMc) {
		this.stMc = stMc;
	}

	public Long getBusType() {
		return busType;
	}

	public void setBusType(Long busType) {
		this.busType = busType;
	}

	public List getEimportances() {
		return eimportances;
	}

	public void setEimportances(List eimportances) {
		this.eimportances = eimportances;
	}

	public Long getEimportance() {
		return eimportance;
	}

	public void setEimportance(Long eimportance) {
		this.eimportance = eimportance;
	}

	public Date getSjbegin() {
		return sjbegin;
	}

	public void setSjbegin(Date sjbegin) {
		this.sjbegin = sjbegin;
	}

	public Date getSjend() {
		return sjend;
	}

	public void setSjend(Date sjend) {
		this.sjend = sjend;
	}

	public String getFenzhi() {
		return fenzhi;
	}

	public void setFenzhi(String fenzhi) {
		this.fenzhi = fenzhi;
	}

	public String getXingzhi() {
		return xingzhi;
	}

	public void setXingzhi(String xingzhi) {
		this.xingzhi = xingzhi;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public String getGzid() {
		return gzid;
	}

	public void setGzid(String gzid) {
		this.gzid = gzid;
	}

	public String getGzdj() {
		return gzdj;
	}

	public void setGzdj(String gzdj) {
		this.gzdj = gzdj;
	}

	public long getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(long difficulty) {
		this.difficulty = difficulty;
	}

}
