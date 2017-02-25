package com.wondersgroup.popedom.service.impl;

import java.sql.Connection;
import java.util.List;

import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.local.bo.PaperInfo;
import com.wondersgroup.popedom.dao.DownloadPaperDao;
import com.wondersgroup.popedom.service.DownloadPaperService;

public class DownloadPaperServiceImpl implements DownloadPaperService {

	private DownloadPaperDao downloadPaperDao;

	public void setDownloadPaperDao(DownloadPaperDao downloadPaperDao) {
		this.downloadPaperDao = downloadPaperDao;
	}

	@Override
	public List<EPapers> getDownloadablePapers() {
		return downloadPaperDao.getDownloadablePapers();
	}
	@Override
	public List<EPapers> get_xf_Papers() {
		return downloadPaperDao.get_xf_Papers();
	}
	@Override
	public List<Object> downloadPaperFromYth(String sjid,String teamId){
		return downloadPaperDao.downloadPaperFromYth(sjid,teamId);
	}
	@Override
	public List<Object> downloaPaperFromJdzx(String sjid,String teamId){
		return downloadPaperDao.downloaPaperFromJdzx(sjid,teamId);
	}

	@Override
	public List<PaperInfo> getPaperInfo() {
		return downloadPaperDao.getPaperInfo();
	}

	@Override
	public String downloadPaperFromJdzxByWs(String sjid) throws Exception {
		return downloadPaperDao.downloadPaperFromJdzxByWs(sjid);
	}
	
	@Override
	public String deletePaperBySjid(String sjid,Connection connection) throws Exception {
		return downloadPaperDao.deletePaperBySjid(sjid,connection);
	}
	
	@Override
	public String deleteUserBySjid(String sjid) throws Exception {
		return downloadPaperDao.deleteUserBySjid(sjid);
	}
}
