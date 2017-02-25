package com.wondersgroup.technocracy.service;

import java.sql.Connection;
import java.util.List;

import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.technocracy.bo.Addexpert;

public interface QueryExpertService {

	public  List<Object> detailquery(Long hzz001);
	public PageReturn checkExpert(PageTool pageTool, String name, String org, String c,String expertstyle);
	
	public List<String> countAge(Connection conn);
	public List<String> countUsenum();
	public List<Integer> countAcademic();
	public List<Integer> countCategory();
	

}
