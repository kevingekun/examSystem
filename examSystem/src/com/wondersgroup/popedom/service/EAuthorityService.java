package com.wondersgroup.popedom.service;


import java.util.List;

import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.EAuthority;

public interface EAuthorityService {
	public PageReturn findAllauthority(PageTool pageTool);
	public EAuthority loadEAuthority(Long id);
	public void updateEAuthorities(String eauthorityid,String[] userMenuids);
	public void saveNewEauthority(EAuthority eauthority);
	public void updateEauthority(Long id,EAuthority eauthority);
	public void deleteEauthority(String[] deleteEauthorities)throws Exception;
	public List<EAuthority> findAllauthority();
	
	public List<Object> renewal(String userid);
}
