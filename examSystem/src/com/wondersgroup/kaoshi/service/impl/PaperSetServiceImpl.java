package com.wondersgroup.kaoshi.service.impl;

import com.wondersgroup.kaoshi.dao.PaperSetDAOImpl;
import com.wondersgroup.kaoshi.service.PaperSetService;

public class PaperSetServiceImpl implements PaperSetService {

	private PaperSetDAOImpl paperSetDao;

	public void setPaperSetDao(PaperSetDAOImpl paperSetDao) {
		this.paperSetDao = paperSetDao;
	}

	public String paperAdd(String sjmc, String tkid, String sjzf, String sjType) {
		return paperSetDao.paperAdd(sjmc, tkid, sjzf, sjType);
	}

}
