package com.wondersgroup.technocracy.service.impl;

import java.sql.Connection;
import java.util.List;

import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.technocracy.bo.Addexpert;
import com.wondersgroup.technocracy.dao.QueryExpertDao;
import com.wondersgroup.technocracy.service.AddExpertService;
import com.wondersgroup.technocracy.service.QueryExpertService;

public class QueryExpertServiceImpl implements QueryExpertService {
	private QueryExpertDao queryexpertDao;

	public void setQueryexpertDao(QueryExpertDao queryexpertDao) {
		this.queryexpertDao = queryexpertDao;
	}

	public List<Object> detailquery(Long hzz001) {
		try {
			return queryexpertDao.detailquery(hzz001);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public PageReturn checkExpert(PageTool pageTool, String name, String org,
			String c, String expertstyle) {
		return queryexpertDao.checkExpert(pageTool, name, org, c, expertstyle);
	}

	public List<String> countAge(Connection conn) {
		return queryexpertDao.countAge(conn);
	}

	public List<Integer> countAcademic() {
		return queryexpertDao.countAcademic();
	}

	public List<Integer> countCategory() {
		return queryexpertDao.countCategory();
	}

	public List<String> countUsenum() {
		return queryexpertDao.countUsenum();
	}
	
	

}
