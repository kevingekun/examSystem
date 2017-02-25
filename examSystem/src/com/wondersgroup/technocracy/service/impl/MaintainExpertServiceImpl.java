package com.wondersgroup.technocracy.service.impl;


import java.util.List;

import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.technocracy.bo.Addexpert;
import com.wondersgroup.technocracy.bo.Addexperts;
import com.wondersgroup.technocracy.dao.MaintainExpertDao;
import com.wondersgroup.technocracy.service.MaintainExpertService;

public class MaintainExpertServiceImpl implements MaintainExpertService {

	private MaintainExpertDao maintainExpertDao;
	
	public PageReturn checkExpert(PageTool pageTool, String name, String org, String c,String style){
		return maintainExpertDao.checkExpert(pageTool, name, org, c, style);
	}
	public void deleteExpert(String id){
		maintainExpertDao.deleteExpert(id);
	}
	public List<Object> findById(String id){
		return maintainExpertDao.findById(id);
	}
	public void updateExpert(Addexpert expert,Addexperts fexpert){
		maintainExpertDao.updateExpert(expert,fexpert);
	}
	public void updateExpertStyle(String[] s,long id) {
		maintainExpertDao.updateExpertStyle(s,id);
	}
	
	
	public void setMaintainExpertDao(MaintainExpertDao maintainExpertDao) {
		this.maintainExpertDao = maintainExpertDao;
	}
	
	
	
}
