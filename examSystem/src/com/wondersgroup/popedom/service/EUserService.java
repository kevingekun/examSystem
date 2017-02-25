package com.wondersgroup.popedom.service;

import java.util.List;

import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.EUser;

public interface EUserService {
	public PageReturn findAllUsers(PageTool pageTool,EUser eu);
	public EUser  load(Long id);
	public void updateUserAuth(Long userId,String[] authid);
	public void deleteUser(Long userId) throws Exception;
	public PageReturn getallkaoshi()throws Exception;
	public void deleteeKaoshi(String[] deleteeKaoshi) throws Exception;
	public void updateUser(EUser user);
	
}
