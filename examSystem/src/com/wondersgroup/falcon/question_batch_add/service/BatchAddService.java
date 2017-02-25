package com.wondersgroup.falcon.question_batch_add.service;

import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.falcon.question.model.EQuestions_temp;
import com.wondersgroup.falcon.question.model.Tmdot;
import com.wondersgroup.kaoshi.bo.Admission_card_file;
import com.wondersgroup.kaoshi.bo.Admission_card_user;
import com.wondersgroup.kaoshi.bo.Ae02;
import com.wondersgroup.kaoshi.bo.EPapersSet;
import com.wondersgroup.kaoshi.bo.E_Users_Temp;
import com.wondersgroup.kaoshi.bo.Tdjobexamdot;
import com.wondersgroup.kaoshi.bo.Tjobsubject;

public interface BatchAddService {

	public void save(EQuestions_temp e);
	
	public String getjdid(String jdmc);
	
	public void saveEuserstemp(E_Users_Temp users);
	
	public List<Object> excuteProc(String jdid,Connection conn);
	
	public void saveEquestions(EQuestions q);
	
	public void saveTmdot(Tmdot tmdot);
	
	public void saveAe02(Ae02 ae02);
	
	public int checkQuestions(String sjid);
	
	public List<EQuestions_temp> findByStateAndBatchNumber(long state,long bcn);
	
	public Tjobsubject findTkByid_jobAndRankname(String id_job, String rankname);
	
	public List<Tdjobexamdot> findJdysBygzid(String gzid);
	
	public InputStream exportJdysInfo(List<Tdjobexamdot> list);

	public List<E_Users_Temp> getusers(String jdid);
	
	EPapersSet getById(String sjid);
	
	void savePrintCardFileInfo(Admission_card_file acf);
	
	boolean savePrintCardUsers(List<Admission_card_user> users);

	
}
