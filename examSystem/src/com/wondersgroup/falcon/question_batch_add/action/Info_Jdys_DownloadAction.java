package com.wondersgroup.falcon.question_batch_add.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.wondersgroup.falcon.question_batch_add.service.BatchAddService;
import com.wondersgroup.kaoshi.bo.Tdjobexamdot;
import com.wondersgroup.kaoshi.util.AbstractAction;

public class Info_Jdys_DownloadAction extends AbstractAction{
	
	private BatchAddService batchAddService;
	private String gzid;
	private String gzmc;
	
	private InputStream excelStream;
	private String downloadFileName;
	
	@Override
    public String execute() throws Exception {  
        return SUCCESS;
    }

	public String jdysInfoDownload(){
		List<Tdjobexamdot> list = batchAddService.findJdysBygzid(gzid);
		excelStream = this.batchAddService.exportJdysInfo(list);
		try {
			gzmc = java.net.URLDecoder.decode(gzmc, "UTF-8");
			downloadFileName = gzmc+"-鉴定要素.xls";
			downloadFileName=new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public BatchAddService getBatchAddService() {
		return batchAddService;
	}

	public void setBatchAddService(BatchAddService batchAddService) {
		this.batchAddService = batchAddService;
	}

	public String getGzid() {
		return gzid;
	}

	public void setGzid(String gzid) {
		this.gzid = gzid;
	}

	public String getGzmc() {
		return gzmc;
	}

	public void setGzmc(String gzmc) {
		this.gzmc = gzmc;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	
}
