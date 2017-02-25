package com.wondersgroup.kaoshi.service.impl;

import java.util.Date;

import com.wondersgroup.kaoshi.bo.EExercise;
import com.wondersgroup.kaoshi.dao.EExerciseDAOImpl;
import com.wondersgroup.kaoshi.service.EExerciseService;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public class EExerciseServiceImpl implements EExerciseService {
	private EExerciseDAOImpl eexerciseDAOImpl;

	public void setEexerciseDAOImpl(EExerciseDAOImpl eexerciseDAOImpl) {
		this.eexerciseDAOImpl = eexerciseDAOImpl;
	}
	public void save(EExercise eexercise){
		this.eexerciseDAOImpl.save(eexercise);
	}
	
	public PageReturn findMyLianxiQuestion(PageTool pagetool,String username,Date sjbegin,Date sjend){
		try {
			return this.eexerciseDAOImpl.findMyLianxiQuestion(pagetool, username,sjbegin,sjend);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public EExercise load(Long id){
		try {
			return this.eexerciseDAOImpl.load(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
