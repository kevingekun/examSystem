package com.wondersgroup.popedom.service;

import java.util.List;

import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.kaoshi.bo.Station_xf;

public interface UploadPaperService {

	public List<UserTeam> geTeams();
	/**
	 * 获取下放考点信息
	 * @return
	 * @author gkk
	 * @date 2016-11-8 上午9:27:05
	 */
	List<Station_xf> getXfs();
	
	public boolean relatePaperAndTeam(String sjid,String checkid);

	/**
	 * 试卷信息上传
	 * @param sjid 试卷id
	 * @param idCard 上传者用户名
	 * @return
	 */
	public List<Object> uploadPaperToYth(String sjid,String idCard);
	
	public List<Object> getRelatedPaper();
	/**
	 * 获取已上传试卷
	 * @return
	 */
	public List<Object> getAlreadyUploadPaper();

	public List<Object> getRelatedteam(String sjid);
	/**
	 * 根据已上传试卷查询已关联的鉴定所
	 * @param sjid
	 * @return
	 */
	public List<Object> getAlreadyTeamDetails(String sjid);
	
	public boolean remove_team(String teamid, String sjid);
    /*
     * 试卷下放
     * */
	
	public boolean relatePaperAndTeam_xf(String sjid, String checkid);

	public List<Object> getRelatedPaper_xf();

	public List<Object> getRelatedteam_xf(String sjid);
	
	public List<Object> getStationRecordOfXf(String station_id);

	public boolean remove_team_xf(String teamid, String sjid);

	public String xf_paper(String sjid);

	public List<Object> getAlready_xf_Paper();

	public List<Object> getAlreadyDloadTeamDetails_xf(String sjid);

	
	
}
