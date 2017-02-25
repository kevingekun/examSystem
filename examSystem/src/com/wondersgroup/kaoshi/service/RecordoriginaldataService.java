package com.wondersgroup.kaoshi.service;

import com.wondersgroup.kaoshi.bo.RECORDORIGINALDATA;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public interface RecordoriginaldataService {
	public PageReturn findAllRecordoriginaldata(PageTool pageTool);
	public RECORDORIGINALDATA loadRecord(String recordreference);
}
