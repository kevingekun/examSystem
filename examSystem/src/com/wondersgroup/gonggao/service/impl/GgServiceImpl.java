package com.wondersgroup.gonggao.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.ObjectNotFoundException;

import com.wondersgroup.gonggao.bo.TBm;
import com.wondersgroup.gonggao.bo.TGg;
import com.wondersgroup.gonggao.bo.TGgfj;
import com.wondersgroup.gonggao.bo.TGglm;
import com.wondersgroup.gonggao.dao.GgDao;
import com.wondersgroup.gonggao.service.GgService;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;

public class GgServiceImpl implements GgService {
	private GgDao ggDao;
	public void setGgDao(GgDao ggDao) {
		this.ggDao = ggDao;
	}

	public void addGg(TGg tgg) {
		try {
			this.ggDao.addGg(tgg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public TGg loadTgg(String ggid) {
		try {
			return this.ggDao.loadTgg(ggid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public TGglm loadTGglm(String lxmc) {
		try {
			return this.ggDao.loadTGglm(lxmc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public TGgfj loadTGgfj(String ggid) {
		try {
			return this.ggDao.loadTGgfj(ggid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<TGglm> getAllgglm() {
		try {
			return this.ggDao.getAllgglm();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<TBm> getAlltbm() {
	
		try {
			return this.ggDao.getAlltbm();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public PageReturn findAll(PageTool pageTool, String biaoti, Date sjbegin,
			Date sjend) {
		try {
			return this.ggDao.findAll(pageTool,biaoti,sjbegin,sjend);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void removegg(String ggid) {
		try{
			try {
				this.ggDao.removegg(ggid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch (ObjectNotFoundException e) {
			System.out.println("��ݿ���û��������ݡ���������������������������������������������������������");
		}
	}
	
	public void saveFf(TGgfj fj)throws  Exception{
		this.ggDao.saveFj(fj);
		
	}
}
