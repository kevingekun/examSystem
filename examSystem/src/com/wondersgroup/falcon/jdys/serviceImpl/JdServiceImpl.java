package com.wondersgroup.falcon.jdys.serviceImpl;

import java.util.List;

import com.wondersgroup.falcon.jdys.dao.JdDAO;
import com.wondersgroup.falcon.jdys.service.JdService;
import com.wondersgroup.kaoshi.bo.Tdjobexamdot;
import com.wondersgroup.kaoshi.bo.Tjobsubject;
import com.wondersgroup.kaoshi.bo.Tkcategory;

public class JdServiceImpl implements JdService{

	private JdDAO jdDAO;

	public List<Object> findJdByGzAndDj(String gz, int dj){
		try {
			return jdDAO.searchByGzAndDj(gz, dj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Tjobsubject> findJdysByJobsubjectname(String jobsubjectname) {
		return jdDAO.findJdysByJobsubjectname(jobsubjectname);
	}

	public void saveOrUpdateTk(Tjobsubject tjobsubject){
		jdDAO.saveOrUpdateTk(tjobsubject);
	}
	public void saveOrUpdateJdys(Tdjobexamdot tdjobexamdot){
		jdDAO.saveOrUpdateJdys(tdjobexamdot);
	}
	
	public void setJdDAO(JdDAO jdDAO) {
		this.jdDAO = jdDAO;
	}
	public void changeState(String[] s) {
		jdDAO.changeState(s);
	}
	public List<Object> getRelatedPaper() {
		return jdDAO.getRelatedPaper();
	}
	public List<Object> getRelatedJdpc(String sjid){
		return jdDAO.getRelatedJdpc(sjid);
	}
	public boolean removeJdpc(String jdid){
		return jdDAO.removeJdpc(jdid);
	}
	
	
	public List<Object> getRelatekc() {
		return jdDAO.getRelatekc();
	}
	public List<Object> getRelatedsj(String kcid){
		return jdDAO.getRelatedsj(kcid);
	}
	public boolean removesj(String sjid){
		return jdDAO.removesj(sjid);
	}
	public List<Object[]> changepaper(String sjid){
		return jdDAO.changepaper(sjid);
	}
	public String updatePaper(String sjid, String sjmc, String sjzf,
			String bhgfs, String djsx, String kksj, String yxqjzsj, String zt,
			String sj_kslx, String jjsj, String ljcf){
		return jdDAO.updatePaper(sjid,sjmc,sjzf, bhgfs,  djsx,  kksj,  yxqjzsj,  zt,
			 sj_kslx,  jjsj,  ljcf);
	}
	
	public String clearTime(String sjid){
		return jdDAO.clearTime(sjid);
	}
	public Tdjobexamdot findJdysById(Integer id) {
		return jdDAO.findJdysById(id);
	}

	@Override
	public String tkCategoryAdd(Tkcategory t) {
		return jdDAO.tkCategoryAdd(t);
	}

	@Override
	public String gzCheck(String gzmc, String gzid) {
		return jdDAO.gzCheck(gzmc, gzid);
	}

	@Override
	public String gzAdd(String gzmc, String gzid) {
		return jdDAO.gzAdd(gzmc, gzid);
	}
}
