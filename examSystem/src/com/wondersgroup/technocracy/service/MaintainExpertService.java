package com.wondersgroup.technocracy.service;


import java.util.List;

import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.technocracy.bo.Addexpert;
import com.wondersgroup.technocracy.bo.Addexperts;

public interface MaintainExpertService {

	 public PageReturn checkExpert(PageTool pageTool, String name, String org, String c,String style);
	 public void deleteExpert(String id);
	 public List<Object> findById(String id);
	 public void updateExpert(Addexpert expert,Addexperts fexpert);
	 public void updateExpertStyle(String[] s,long id);
}
