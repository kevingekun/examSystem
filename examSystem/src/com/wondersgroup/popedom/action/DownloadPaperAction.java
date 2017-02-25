package com.wondersgroup.popedom.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.local.bo.PaperInfo;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.service.DownloadPaperService;

public class DownloadPaperAction extends AbstractPageNavAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DownloadPaperService downloadPaperService;
	
	private List<PaperInfo> list;
	private String sjid;

	@Override
	public String doAcion() throws Exception {
		//this.list = downloadPaperService.getDownloadablePapers();
		//this.list=downloadPaperService.get_xf_Papers();
		this.list=downloadPaperService.getPaperInfo();//webservice方式获取要下载的试卷信息
		return SUCCESS;
	}
	
	public void downloadPaperBySjid(){
		/*EUser eUser = AcegiUtil.getEUser();
		List<Object> upList = this.downloadPaperService.downloaPaperFromJdzx(sjid,eUser.getColor());
		if(upList.size()>0){
			JSONArray json = JSONArray.fromObject(upList);
			//System.out.println("json"+json);
			HttpServletResponse response = ServletActionContext.getResponse();
			try {
				response.getWriter().write(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		String msg = "";
		try {
			msg = this.downloadPaperService.downloadPaperFromJdzxByWs(sjid);
		} catch (Exception e1) {
			e1.printStackTrace();
			msg = e1.getMessage();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void deletePaperBySjid(){
		
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getRequest().getSession().getServletContext()); 
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String msg = "";
		try {
			msg = this.downloadPaperService.deletePaperBySjid(sjid,conn);
		} catch (Exception e1) {
			e1.printStackTrace();
			msg = e1.getMessage();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
public void deleteUserBySjid(){
		String msg = "";
		try {
			msg = this.downloadPaperService.deleteUserBySjid(sjid);
		} catch (Exception e1) {
			e1.printStackTrace();
			msg = e1.getMessage();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDownloadPaperService(DownloadPaperService downloadPaperService) {
		this.downloadPaperService = downloadPaperService;
	}

	public List<PaperInfo> getList() {
		return list;
	}

	public void setList(List<PaperInfo> list) {
		this.list = list;
	}

	public String getSjid() {
		return sjid;
	}

	public void setSjid(String sjid) {
		this.sjid = sjid;
	}

}
