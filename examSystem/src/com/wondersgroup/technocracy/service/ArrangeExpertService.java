package com.wondersgroup.technocracy.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.EuserTest;
import com.wondersgroup.technocracy.bo.Addexperts;
import com.wondersgroup.technocracy.bo.ExpertInfo;

public interface ArrangeExpertService {

	public PageReturn arrangequery(PageTool pageTool, String sj_mc);

	public void arrangeExperts(String ksid,String kcid, String cid, String nduty,
			String nremark, String ZJID);

	public List<Addexperts> findcid(Long id);

	public List<ExpertInfo> checkExpert(String kcid,String sjid);
	
	public boolean deleteUseOfExpert(String idof92);
	
	public boolean replaceExpert(String duty,String remark,String reson,String expertid,String idof92);
	
	public List<Object> arrangeExpertAuto(String sjmc,Connection conn);
	
	public Map<String, List<Object>> arrangeExpertAuto2(String examid);
	
	public String arrangeExpertAutoSubmit(List<Object> list,String sjid);
	
	public boolean relateMajorAndProfession(String zymc,String checkedid);
}
