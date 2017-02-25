package com.wondersgroup.popedom.service.impl;

import java.util.List;

import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.kaoshi.bo.Station_xf;
import com.wondersgroup.popedom.dao.AddExamineeDao;
import com.wondersgroup.popedom.dao.UploadPaperDao;
import com.wondersgroup.popedom.service.UploadPaperService;

public class UploadPaperServiceImpl implements UploadPaperService {

	private UploadPaperDao uploadPaperDao;
	private AddExamineeDao addExamineeDao;

	public List<UserTeam> geTeams() {
		return this.addExamineeDao.getTeams();
	}

	public void setUploadPaperDao(UploadPaperDao uploadPaperDao) {
		this.uploadPaperDao = uploadPaperDao;
	}

	public void setAddExamineeDao(AddExamineeDao addExamineeDao) {
		this.addExamineeDao = addExamineeDao;
	}

	public boolean relatePaperAndTeam(String sjid, String checkid) {
		return this.uploadPaperDao.relatePaperAndTeam(sjid, checkid);
	}

	public boolean relatePaperAndTeam_xf(String sjid, String checkid) {
		return this.uploadPaperDao.relatePaperAndTeam_xf(sjid, checkid);
	}

	public List<Object> getRelatedPaper() {
		return this.uploadPaperDao.getRelatedPaper();
	}

	public List<Object> getRelatedteam(String sjid) {
		return this.uploadPaperDao.getRelatedteam(sjid);
	}

	public boolean remove_team(String teamid, String sjid) {
		return this.uploadPaperDao.remove_teamid(teamid, sjid);
	}

	public List<Object> getRelatedPaper_xf() {
		return this.uploadPaperDao.getRelatedPaper_xf();
	}

	public List<Object> getRelatedteam_xf(String sjid) {
		return this.uploadPaperDao.getRelatedteam_xf(sjid);
	}
	public List<Object> getStationRecordOfXf(String station_id) {
		return this.uploadPaperDao.getStationRecordOfXf(station_id);
	}

	public boolean remove_team_xf(String teamid, String sjid) {
		return this.uploadPaperDao.remove_teamid_xf(teamid, sjid);
	}

	public List<Object> uploadPaperToYth(String sjid, String idCard) {
		return this.uploadPaperDao.uploadPaperToYth(sjid, idCard);
	}

	public String xf_paper(String sjid) {
		return this.uploadPaperDao.xf_paper(sjid);
	}

	public List<Object> getAlreadyUploadPaper() {
		return this.uploadPaperDao.getAlreadyUploadPaper();
	}

	public List<Object> getAlready_xf_Paper() {
		return this.uploadPaperDao.getAlready_xf_Paper();
	}

	public List<Object> getAlreadyTeamDetails(String sjid) {
		return this.uploadPaperDao.getAlreadyTeamDetails(sjid);
	}

	public List<Object> getAlreadyDloadTeamDetails_xf(String sjid) {
		return this.uploadPaperDao.getAlreadyDloadTeamDetails_xf(sjid);
	}

	@Override
	public List<Station_xf> getXfs() {
		return this.addExamineeDao.getXfs();
	}
}
