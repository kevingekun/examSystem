package com.wondersgroup.popedom.service;

import java.util.List;

import com.wondersgroup.kaoshi.bo.EKaoshi;
import com.wondersgroup.popedom.bo.EUserMenus;

public interface EUserMenusService {
	/**
	 * 
	 *
	 * <p>Description:��ѯһ���˵�</p>
	 * 
	 * Created by [www] [Nov 12, 2009]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param userid  �û�id
	 * @param parentid �����˵�
	 * @param systemtype ϵͳ1����ѯ��2������
	 * @param status ״̬ 0Ϊ���� 1Ϊ���� 2����
	 * @return
	 */
	public List<EUserMenus> findTopMenus(Long userid,String parentid,Long systemtype,Long status);
	
	public List<EUserMenus> findMenusByParent(String parentid);
	
	public List<EUserMenus> findAllMenus();
	
}
