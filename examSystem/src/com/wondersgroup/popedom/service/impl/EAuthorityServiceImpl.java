package com.wondersgroup.popedom.service.impl;


import java.util.List;

import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.EAuthority;
import com.wondersgroup.popedom.dao.EAuthorityDao;
import com.wondersgroup.popedom.service.EAuthorityService;

public class EAuthorityServiceImpl implements EAuthorityService {
	private EAuthorityDao eauthoritydao;

	public void setEauthoritydao(EAuthorityDao eauthoritydao) {
		this.eauthoritydao = eauthoritydao;
	}

	public PageReturn findAllauthority(PageTool pageTool) {
		try {
			return this.eauthoritydao.findAllauthority(pageTool);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<EAuthority> findAllauthority(){
		try {
			return this.eauthoritydao.findAllauthority();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public EAuthority loadEAuthority(Long id){
		try {
			return this.eauthoritydao.load(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateEAuthorities(String eauthorityid,String[] userMenuids){
		try {
			this.eauthoritydao.updateEAuthorities(eauthorityid, userMenuids);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveNewEauthority(EAuthority eauthority){
		try {
			this.eauthoritydao.saveNewEauthority(eauthority);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateEauthority(Long id,EAuthority eauthority){
		try {
			this.eauthoritydao.updateEauthority(id, eauthority);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteEauthority(String[] deleteEauthorities)throws Exception {
		this.eauthoritydao.deleteEauthority(deleteEauthorities);
	}

	public List<Object> renewal(String userid) {
		return this.eauthoritydao.renewal(userid);
	}

}
