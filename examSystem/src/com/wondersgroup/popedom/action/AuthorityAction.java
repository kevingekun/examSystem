package com.wondersgroup.popedom.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.EAuthority;
import com.wondersgroup.popedom.service.EAuthorityService;

public class AuthorityAction extends AbstractPageNavAction {
	private EAuthorityService eauthorityService;
	
	//页面显示
	private List<EAuthority> eauthoritys;
	
	public List<EAuthority> getEauthoritys() {
		return eauthoritys;
	}

	public void setEauthoritys(List<EAuthority> eauthoritys) {
		this.eauthoritys = eauthoritys;
	}

	public void setEauthorityService(EAuthorityService eauthorityService) {
		this.eauthorityService = eauthorityService;
	}

	public String doAcion() throws Exception {
		this.pageReturn=this.eauthorityService.findAllauthority(this.pageTool);
		this.eauthoritys=this.pageReturn.getReturnList();
		return SUCCESS;
	}
	
	private String name;
	private String description;
	 

	public String addEauthority() throws Exception{
		EAuthority eauthority=new EAuthority();
		eauthority.setDescription(description);
		eauthority.setName(name);
		eauthority.setState(new Byte("0"));
		this.eauthorityService.saveNewEauthority(eauthority);
		return SUCCESS;
	}
	
	private String authorityid;
	
	private EAuthority eauthority;
	/**
	 * 
	 *
	 * <p>Description:去修改角色</p>
	 * 
	 * Created by [www] [Nov 16, 2009]
	 * Midified by [修改人] [修改时间]
	 *
	 * @return
	 * @throws Exception
	 */
	public String toupdateEauthority() throws Exception{
		eauthority=this.eauthorityService.loadEAuthority(new Long(authorityid));
		this.authorityid=eauthority.getId().toString();
		this.name=eauthority.getName();
		this.description=eauthority.getDescription();
		return SUCCESS;
	}
	public String updateEauthority() throws Exception{
		EAuthority eauthority=new EAuthority();
		 eauthority.setDescription(description);
		 eauthority.setName(name);
		this.eauthorityService.updateEauthority(new Long(authorityid), eauthority);
		
		return SUCCESS;
	}
	
	/**
	 * 
	 *
	 * <p>Description:角色删除</p>
	 * 
	 * Created by [www] [Nov 17, 2009]
	 * Midified by [修改人] [修改时间]
	 *
	 * @return
	 * @throws Exception
	 */
	private String[] deleEauthories;
	

	public String deleteEauthority() throws Exception{
		try{
			this.eauthorityService.deleteEauthority(deleEauthories);
		}catch(Exception e){
			this.getRequest().setAttribute("wrongMessage", "不能删除，此角色已经与用户关联");
			this.getRequest().setAttribute("backUrl", "eauthority.action");
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String authorityRenewal(){
		return SUCCESS;
	}
	/**
	 * 从一体化更新权限
	 */
	public void renewal(){
		String userid = String.valueOf(AcegiUtil.getEUser().getId());
		List<Object> list = this.eauthorityService.renewal(userid);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(String.valueOf(list.get(1)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String[] getDeleEauthories() {
		return deleEauthories;
	}

	public void setDeleEauthories(String[] deleEauthories) {
		this.deleEauthories = deleEauthories;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthorityid() {
		return authorityid;
	}

	public void setAuthorityid(String authorityid) {
		this.authorityid = authorityid;
	}

	public EAuthority getEauthority() {
		return eauthority;
	}

	public void setEauthority(EAuthority eauthority) {
		this.eauthority = eauthority;
	}


}
