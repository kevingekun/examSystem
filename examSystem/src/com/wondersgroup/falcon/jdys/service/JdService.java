package com.wondersgroup.falcon.jdys.service;

import java.util.List;

import com.wondersgroup.kaoshi.bo.Tdjobexamdot;
import com.wondersgroup.kaoshi.bo.Tjobsubject;
import com.wondersgroup.kaoshi.bo.Tkcategory;

public interface JdService {

	public List<Object> findJdByGzAndDj(String gz, int dj);
	
	public List<Tjobsubject> findJdysByJobsubjectname(String jobsubjectname);
	
	public void saveOrUpdateTk(Tjobsubject tjobsubject);
	
	public void saveOrUpdateJdys(Tdjobexamdot tdjobexamdot);

	public void changeState(String[] s);
	
	public List<Object> getRelatedPaper();
	
	public List<Object> getRelatedJdpc(String sjid);
	
	public boolean removeJdpc(String jdid);


	public List<Object> getRelatekc();

	public List<Object> getRelatedsj(String kcid);

	public boolean removesj(String sjid);

	public List<Object[]> changepaper(String sjid);

	public String updatePaper(String sjid, String sjmc, String sjzf,
			String bhgfs, String djsx, String kksj, String yxqjzsj, String zt,
			String sj_kslx, String jjsj, String ljcf);

	public String clearTime(String sjid);
	
	public Tdjobexamdot findJdysById(Integer id);
	
	public String tkCategoryAdd(Tkcategory t);
	
	String gzCheck(String gzmc,String gzid);
	
	String gzAdd(String gzmc,String gzid);
}
