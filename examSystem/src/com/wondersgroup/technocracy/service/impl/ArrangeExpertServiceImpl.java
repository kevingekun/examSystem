package com.wondersgroup.technocracy.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.EuserTest;
import com.wondersgroup.technocracy.bo.Addexperts;
import com.wondersgroup.technocracy.bo.ExpertInfo;
import com.wondersgroup.technocracy.dao.AddExpertDao;
import com.wondersgroup.technocracy.dao.ArrangeExpertsDao;
import com.wondersgroup.technocracy.service.AddExpertService;
import com.wondersgroup.technocracy.service.ArrangeExpertService;

public class ArrangeExpertServiceImpl implements ArrangeExpertService {
	
	private ArrangeExpertsDao arrangeexpertsDao;
	
	
	public ArrangeExpertsDao getArrangeexpertsDao() {
		return arrangeexpertsDao;
	}


	public void setArrangeexpertsDao(ArrangeExpertsDao arrangeexpertsDao) {
		this.arrangeexpertsDao = arrangeexpertsDao;
	}


	public PageReturn arrangequery(PageTool pageTool, String sj_mc ) {
		 
		try {
			return arrangeexpertsDao.arrangequery(pageTool, sj_mc ) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	 public void arrangeExperts(String ksid,String kcid,String committeeId,String nduty,String nremark,String ZJID) {
		 try {
			this.arrangeexpertsDao.arrangeExperts(ksid,kcid,committeeId,nduty,nremark,ZJID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} 
	public List<Addexperts> findcid(Long id) {
		try {
			return arrangeexpertsDao.findcid(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public List<ExpertInfo> checkExpert(String kcid,String sjid) {
		return arrangeexpertsDao.checkExpert(kcid,sjid);
	}


	public boolean deleteUseOfExpert(String idof92) {
		return arrangeexpertsDao.deleteUseOfExpert(idof92);		
	}


	public boolean replaceExpert(String duty, String remark, String reson, String expertid,
			String idof92) {
		return arrangeexpertsDao.replaceExpert(duty, remark,reson, expertid, idof92);
	}
	
	public List<Object> arrangeExpertAuto(String sjmc,Connection conn) {
		List<Object> list = new ArrayList<Object>();
		list.add(arrangeexpertsDao.arrangeExpertAuto(sjmc,conn));
		list.add(arrangeexpertsDao.findTeamidAndExamidBysjmc(sjmc));
		return list;
	}
	public Map<String, List<Object>> arrangeExpertAuto2(String examid) {
		
		return arrangeexpertsDao.arrangeExpertAuto2(examid);
	}


	public boolean relateMajorAndProfession(String zymc, String checkedid) {
		return arrangeexpertsDao.relateMajorAndProfession(zymc, checkedid);
	}


	@Override
	public String arrangeExpertAutoSubmit(List<Object> list, String sjid) {
		return arrangeexpertsDao.arrangeExpertAutoSubmit(list, sjid);
	}

}
