package com.wondersgroup.gonggao.service;

import java.util.List;

import com.wondersgroup.gonggao.bo.Mgg;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public interface GgmaintainService {
	public PageReturn findgg(PageTool pageTool,String sj_mc);
	public void savenotice(String hzz092,String sjmc);
	public List<Mgg> getadd(String sjid);
	public void updateMgg(final Mgg mgg);
	public List<Object[]> queryNotice();
}
