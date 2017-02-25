package com.wondersgroup.kaoshi.service.impl;

import java.util.List;

import com.wondersgroup.kaoshi.bo.Cz70;
import com.wondersgroup.kaoshi.bo.Tjobsubject;
import com.wondersgroup.kaoshi.dao.ProfessionDAO;
import com.wondersgroup.kaoshi.service.ProfessionService;

public class ProfessionServiceImpl implements ProfessionService {

	private ProfessionDAO professionDao;

	public List<Cz70> findByName(String name){
		try {
			return professionDao.findCz70ByName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Tjobsubject> findByjobnameAndDj(String jobname, String dj){
		try {
			return professionDao.findByjobnameAndDj(jobname, dj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean findByjobname(String jobname){
		try {
			return professionDao.findByjobname(jobname);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void setProfessionDao(ProfessionDAO professionDao) {
		this.professionDao = professionDao;
	}
	
}
