package com.wondersgroup.falcon.question;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.falcon.question.dao.PrintCardDAO;
import com.wondersgroup.kaoshi.bo.Admission_card_pc;
import com.wondersgroup.kaoshi.bo.PrintCardVO;

public class PrintCardService {
	private static final Log log = LogFactory.getLog(PrintCardService.class);
	
	public void add(Admission_card_pc acp) throws Exception{
		try {
			PrintCardDAO pDao = new PrintCardDAO();
			pDao.add(acp);
			log.info("新增打印批次信息成功");
		} catch (Exception e) {
			throw e;
		}
	}
	public int querySize(Admission_card_pc acp){
		PrintCardDAO pDao = new PrintCardDAO();
		return pDao.querySize(acp);
	}
	
	public int queryPrintListSize(Admission_card_pc acp){
		PrintCardDAO pDao = new PrintCardDAO();
		return pDao.queryPrintListSize(acp);
	}
	
	public List<Admission_card_pc> queryList(Admission_card_pc acp,int currentPage,int pageSize){
		PrintCardDAO pDao = new PrintCardDAO();
		return pDao.queryList(acp, currentPage, pageSize);
	}
	public List<Object[]> queryPrintCardList(Admission_card_pc acp,int currentPage,int pageSize){
		PrintCardDAO pDao = new PrintCardDAO();
		return pDao.queryPrintCardList(acp, currentPage, pageSize);
	}
}
