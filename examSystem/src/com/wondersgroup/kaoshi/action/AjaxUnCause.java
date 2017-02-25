package com.wondersgroup.kaoshi.action;

import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.kaoshi.service.EAnswerpaperService;
import com.wondersgroup.kaoshi.service.EPapersService;
import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.kaoshi.util.AbstractAction;

public class AjaxUnCause extends AbstractAction {
	// 试卷
	private EPapers epapers;
	// 考试人员工号
	private String ryid;
	// 试卷id
	private String paperId;
	// 未参加考试原因
	private String cause;
	private EPapersService epaperservice;
	// 答题service
	private EAnswerpaperService eanswerpaperService;

	@Override
	public String execute() throws Exception {
		this.epaperservice.delete();
		this.epapers = this.epaperservice.getEPapersById(paperId);
		this.eanswerpaperService.nopartinexamCause(epapers, ryid, cause);
		return SUCCESS;
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

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public EPapersService getEpaperservice() {
		return epaperservice;
	}

	public void setEpaperservice(EPapersService epaperservice) {
		this.epaperservice = epaperservice;
	}

	public EAnswerpaperService getEanswerpaperService() {
		return eanswerpaperService;
	}

	public void setEanswerpaperService(EAnswerpaperService eanswerpaperService) {
		this.eanswerpaperService = eanswerpaperService;
	}
}
