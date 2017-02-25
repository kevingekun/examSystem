package com.wondersgroup.gonggao.service;

import java.util.Date;
import java.util.List;

import com.wondersgroup.gonggao.bo.TBm;
import com.wondersgroup.gonggao.bo.TGg;
import com.wondersgroup.gonggao.bo.TGgfj;
import com.wondersgroup.gonggao.bo.TGglm;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public interface GgService {
	public void addGg(TGg tgg);
	public PageReturn findAll(PageTool pageTool,String biaoti,Date sjbegin,Date sjend );
	public TGg loadTgg(String ggid);
	public TGgfj loadTGgfj(String ggid);
	public TGglm loadTGglm(String lxmc);
	public List<TGglm> getAllgglm();
	public List<TBm> getAlltbm();
	public void removegg(String ggid);
	public void saveFf(TGgfj fj)throws  Exception;
}

