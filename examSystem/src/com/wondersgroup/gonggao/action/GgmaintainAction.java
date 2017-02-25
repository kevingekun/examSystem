package com.wondersgroup.gonggao.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.poifs.storage.ListManagedBlock;
import org.apache.struts2.ServletActionContext;

import com.wondersgroup.gonggao.bo.Mgg;
import com.wondersgroup.gonggao.bo.TGg;
import com.wondersgroup.gonggao.service.GgService;
import com.wondersgroup.gonggao.service.GgmaintainService;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;

public class GgmaintainAction extends AbstractPageNavAction{

	private GgmaintainService ggmaintainService;
	private Mgg mgg;
	private List<Mgg> mggs;//页面显示
	
	private String sj_mc;
	private String notice;
	private String  sjmcname;
	private String sj_Id;
	private  Mgg adds;
	private String addg;
	
	private List<Object[]> addlist;
	
	@Override
	public String doAcion() throws Exception {
		this.pageReturn=this.ggmaintainService.findgg(this.pageTool, sj_mc);
		mggs=this.pageReturn.getReturnList();
		return SUCCESS;
	}

	public String maintainadd(){
		  this.addlist =this.ggmaintainService.queryNotice();
		   return SUCCESS;
	}
	
	public String addnotice(){
     ggmaintainService.savenotice(notice,sjmcname);
     
     return SUCCESS;
		  
	}
	
	public String queryadd(){
		//sj_Id=getRequest().getParameter("sj_id");
		adds=ggmaintainService.getadd(sj_Id).get(0);
		sj_mc="";
		return SUCCESS;
	}
	public String saveadd(){
		ggmaintainService.updateMgg(adds);
		return SUCCESS;
	}
	
	public GgmaintainService getGgmaintainService() {
		return ggmaintainService;
	}

	public void setGgmaintainService(GgmaintainService ggmaintainService) {
		this.ggmaintainService = ggmaintainService;
	}

	public List<Mgg> getMggs() {
		return mggs;
	}

	public void setMggs(List<Mgg> mggs) {
		this.mggs = mggs;
	}

	public String getSj_mc() {
		return sj_mc;
	}

	public void setSj_mc(String sj_mc) {
		this.sj_mc = sj_mc;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	 

	public String getSjmcname() {
		return sjmcname;
	}

	public void setSjmcname(String sjmcname) {
		this.sjmcname = sjmcname;
	}

	public Mgg getMgg() {
		return mgg;
	}

	public void setMgg(Mgg mgg) {
		this.mgg = mgg;
	}

	 

	 

	public Mgg getAdds() {
		return adds;
	}

	public void setAdds(Mgg adds) {
		this.adds = adds;
	}

	public String getSj_Id() {
		return sj_Id;
	}

	public void setSj_Id(String sj_Id) {
		this.sj_Id = sj_Id;
	}

	public String getAddg() {
		return addg;
	}

	public void setAddg(String addg) {
		this.addg = addg;
	}

	public List<Object[]> getAddlist() {
		return addlist;
	}

	public void setAddlist(List<Object[]> addlist) {
		this.addlist = addlist;
	}
	
	
	
}
