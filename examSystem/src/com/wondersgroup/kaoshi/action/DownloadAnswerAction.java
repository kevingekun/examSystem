package com.wondersgroup.kaoshi.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.wondersgroup.falcon.paper.model.EPaperquestions;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.kaoshi.service.EPapersService;
import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.kaoshi.util.AbstractAction;

public class DownloadAnswerAction extends AbstractAction{

	private String downloadFileName;

	private String paperid;
	private EPapersService epaperservice;
	private EpaperquestionsService epaperquestionsService;
	
	private InputStream downloadFile;
	
	@Override  
    public String execute() throws Exception {  
        return SUCCESS;
    }
	
	public String FileDownload(){
		EPapers epapers = epaperservice.getEPapersById(paperid);
		List<EPaperquestions> epaperquestions = epaperquestionsService.findAllById(paperid);
		Map<String, Object> map = new HashMap<String, Object>();
	//	map = this.epaperservice.getAnswerOfPaper(epapers.getSjMc(), epaperquestions);
		map = this.epaperservice.getAnswerOfPaper_New(epapers.getSjMc(), epaperquestions);
		
		downloadFileName = epapers.getSjMc()+"_答案.doc";
		try {
			downloadFileName=new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.epaperservice.exportAnswerExam(map);
		downloadFile = ServletActionContext.getServletContext().getResourceAsStream("/templates/"+"answerWord.doc");
		return SUCCESS;
		
	}

	public InputStream getDownloadFile() {
		return downloadFile;
	}

	public void setDownloadFile(InputStream downloadFile) {
		this.downloadFile = downloadFile;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public EPapersService getEpaperservice() {
		return epaperservice;
	}

	public void setEpaperservice(EPapersService epaperservice) {
		this.epaperservice = epaperservice;
	}

	public EpaperquestionsService getEpaperquestionsService() {
		return epaperquestionsService;
	}

	public void setEpaperquestionsService(
			EpaperquestionsService epaperquestionsService) {
		this.epaperquestionsService = epaperquestionsService;
	}
	
	
}
