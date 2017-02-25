package com.wondersgroup.technocracy.service.impl;

import java.util.List;

import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.bo.EuserTest;
import com.wondersgroup.popedom.bo.ExamStaff;
import com.wondersgroup.popedom.dao.AddExamineeDao;
import com.wondersgroup.popedom.service.AddExamineeService;
import com.wondersgroup.technocracy.bo.Addexpert;
import com.wondersgroup.technocracy.bo.Addexperts;
import com.wondersgroup.technocracy.bo.HZ93;
import com.wondersgroup.technocracy.dao.AddExpertDao;
import com.wondersgroup.technocracy.service.AddExpertService;

public class AddExpertServiceImpl implements AddExpertService{
	private AddExpertDao addExpertDao;
	 
	public void addexpert(Addexpert expert) {
		try {
			this.addExpertDao.addexpert(expert);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addexperts(Addexperts experts) {
		try {
			this.addExpertDao.addexperts(experts);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 public void addexpertHz93(HZ93 expertHz93){
		 
		 try {
			this.addExpertDao.addexpertHz93(expertHz93);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	
public List<Addexpert> queryexpert(String idnumber) {
		 
		
		  try {
			return this.addExpertDao.queryexpert(idnumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return null;
		
	}
	
	
	public void setAddExpertDao(AddExpertDao addExpertDao) {
		this.addExpertDao = addExpertDao;
	}


}
