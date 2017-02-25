package com.wondersgroup.kaoshi.action;

import java.util.List;

import com.wondersgroup.kaoshi.bo.RECORDORIGINALDATA;
import com.wondersgroup.kaoshi.service.RecordoriginaldataService;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;

public class LuyingAction extends AbstractPageNavAction {

	private RecordoriginaldataService  recordoriginaldataService;
	
	//页面显示
	private List<RECORDORIGINALDATA> recordoriginaldates;
	@Override
	public String doAcion() throws Exception {
		this.pageReturn=this.recordoriginaldataService.findAllRecordoriginaldata(pageTool);
		this.recordoriginaldates=this.pageReturn.getReturnList();
		return SUCCESS;
	}
	
	//传参
	private String id;
	
	//显示
	private RECORDORIGINALDATA recordoriginaldata;
	
	public String viewDetail() throws Exception {
		this.recordoriginaldata=this.recordoriginaldataService.loadRecord(id);
		
		return SUCCESS;
	} 
	public void setRecordoriginaldataService(
			RecordoriginaldataService recordoriginaldataService) {
		this.recordoriginaldataService = recordoriginaldataService;
	}
	
	public RECORDORIGINALDATA getRecordoriginaldata() {
		return recordoriginaldata;
	}
	public void setRecordoriginaldata(RECORDORIGINALDATA recordoriginaldata) {
		this.recordoriginaldata = recordoriginaldata;
	}
	public List<RECORDORIGINALDATA> getRecordoriginaldates() {
		return recordoriginaldates;
	}
	public void setRecordoriginaldates(List<RECORDORIGINALDATA> recordoriginaldates) {
		this.recordoriginaldates = recordoriginaldates;
	}

}
