package com.wondersgroup.popedom.service;

import java.util.List;

import com.wondersgroup.kaoshi.bo.EKaoshi;
import com.wondersgroup.popedom.bo.EUserMenus;

public interface EUserMenusService {
	/**
	 * 
	 *
	 * <p>Description:查询一级菜单</p>
	 * 
	 * Created by [www] [Nov 12, 2009]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param userid  用户id
	 * @param parentid 父级菜单
	 * @param systemtype 系统1：咨询；2：考试
	 * @param status 状态 0为弃用 1为在用 2特殊
	 * @return
	 */
	public List<EUserMenus> findTopMenus(Long userid,String parentid,Long systemtype,Long status);
	
	public List<EUserMenus> findMenusByParent(String parentid);
	
	public List<EUserMenus> findAllMenus();
	
}
