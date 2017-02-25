package com.wondersgroup.popedom.service;

import java.sql.Connection;
import java.util.List;

import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.local.bo.PaperInfo;

public interface DownloadPaperService {

	/**
	 * 获取可以下载的试卷
	 * @return
	 */
	List<EPapers> getDownloadablePapers();
	/**
	 * 根据试卷id下载试卷
	 * @param sjid
	 * @param teamId
	 * @return
	 */
	public List<Object> downloadPaperFromYth(String sjid,String teamId);
	List<EPapers> get_xf_Papers();
	/**
	 * webservice方式获取要下载的试卷信息
	 * @return
	 */
	List<PaperInfo> getPaperInfo();
	List<Object> downloaPaperFromJdzx(String sjid, String color);
	/**
	 * webservice方式下载试题考生信息
	 * @return
	 * @throws Exception 
	 */
	String downloadPaperFromJdzxByWs(String sjid) throws Exception;
	/**
	 * 删除已下载试卷信息
	 * @param sjid
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	String deletePaperBySjid(String sjid,Connection connection) throws Exception;
	/**
	 * 删除已下载试卷下的考生信息
	 * @param sjid
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	String deleteUserBySjid(String sjid) throws Exception;
}
