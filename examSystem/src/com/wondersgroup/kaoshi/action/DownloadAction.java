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

public class DownloadAction extends AbstractAction{
	
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
		//map = this.epaperservice.getInfoOfWord(epapers.getSjGzid(), epapers.getSjDj(), epaperquestions);
		String sjid = String.valueOf(epapers.getSjId());
		map = this.epaperservice.getInfoOfWord_New(epapers.getSjGzid(), epapers.getSjDj(), sjid, epaperquestions);
		downloadFileName = epapers.getSjMc()+".doc";
		try {
			downloadFileName=new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.epaperservice.exportWordExam(map);
		
		downloadFile = ServletActionContext.getServletContext().getResourceAsStream("/templates/"+"examWord.doc");
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

	public EPapersService getEpaperservice() {
		return epaperservice;
	}

	public void setEpaperservice(EPapersService epaperservice) {
		this.epaperservice = epaperservice;
	}

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public EpaperquestionsService getEpaperquestionsService() {
		return epaperquestionsService;
	}

	public void setEpaperquestionsService(
			EpaperquestionsService epaperquestionsService) {
		this.epaperquestionsService = epaperquestionsService;
	}

}
