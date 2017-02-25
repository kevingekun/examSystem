package com.wondersgroup.kaoshi.service.impl;

import com.wondersgroup.kaoshi.bo.RECORDORIGINALDATA;
import com.wondersgroup.kaoshi.dao.RecordoriginaldataDao;
import com.wondersgroup.kaoshi.service.RecordoriginaldataService;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public class RecordoriginaldataServiceImpl implements RecordoriginaldataService {
	private  RecordoriginaldataDao rdDao;

	public void setRdDao(RecordoriginaldataDao rdDao) {
		this.rdDao = rdDao;
	}
	
	public PageReturn findAllRecordoriginaldata(PageTool pageTool){
		try {
			return this.rdDao.findAllRecord(pageTool);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public RECORDORIGINALDATA loadRecord(String recordreference){
		try {
			return this.rdDao.loadRecord(recordreference);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
