package com.wondersgroup.kaoshi.service;

import java.util.Date;

import com.wondersgroup.kaoshi.bo.EExercise;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public interface EExerciseService {
	public void save(EExercise eexercise);
	public PageReturn findMyLianxiQuestion(PageTool pagetool,String username,Date sjbegin,Date sjend);
	
	public EExercise load(Long id);
}
