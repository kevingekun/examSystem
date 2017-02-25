package com.wondersgroup.falcon.question_batch_add.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;
import org.mira.lucene.analysis.f;

import com.wondersgroup.kaoshi.util.AbstractAction;

public class TemplateDownloadAction extends AbstractAction{
	
	private String downloadFileName;

	@Override
    public String execute() throws Exception {  
        return SUCCESS;
    }
	public InputStream getTemplateFile(){
		String fileName = "";
		String flag = getRequest().getParameter("flag");
		if("cmp".equals(flag)){
			downloadFileName = "整套试卷导入模板.xls";
			fileName = "questionTemplate_cmp.xlsx";
		}else if("jdysTemplate".equals(flag)){
			downloadFileName = "鉴定要素导入模板.xls";
			fileName = "jdysTempleate.xlsx";
		}else if ("manual".equals(flag)){
			downloadFileName = "手工导入考生模板.xls";
			fileName = "ManualImport_Template.xls";
		}else if("printcard".equals(flag)){
			downloadFileName = "准考证打印-考生导入模板.xls";
			fileName = "ManualImport_printcard.xls";
		}else{
			downloadFileName = "批量导入模板.xls";
			fileName = "questionTemplate.xlsx";
		}
		try {
			downloadFileName=new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return ServletActionContext.getServletContext().getResourceAsStream("/templates/"+fileName);
	}
	
	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}
	
}
