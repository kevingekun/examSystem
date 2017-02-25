package com.wondersgroup.popedom.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.service.UploadPaperService;

public class UploadPaperAction extends AbstractPageNavAction{

	private UploadPaperService uploadPaperService;
	private List<UserTeam> list;
	
	private String checkid;
	private String sjid;
	private String info;
	private String teamid;
	
	
	@Override
	public String doAcion() throws Exception {
		this.list = uploadPaperService.geTeams();
		return SUCCESS;
	}
	
	public String relatePaperAndTeam(){
		boolean b = this.uploadPaperService.relatePaperAndTeam(sjid, checkid);
		if (b) {
			info="1";
		}else{
			info="2";
		}
		getRequest().getSession().setAttribute("info2", "0");
		return SUCCESS;
	}
	/**
	 * 试卷信息上传
	 */
	public void uploadPaperToYth(){
		String idCard = AcegiUtil.getUserDetails().getUsername();
		List<Object> upList = this.uploadPaperService.uploadPaperToYth(sjid,idCard);
		if(upList.size()>0){
			JSONArray json = JSONArray.fromObject(upList);
			//System.out.println("json"+json);
			HttpServletResponse response = ServletActionContext.getResponse();
			try {
				response.getWriter().write(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getAlready_paper(){
		List<Object> list = uploadPaperService.getRelatedPaper();
		JSONArray json = JSONArray.fromObject(list);
		//System.out.println("json"+json);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void getAlreadyUploadPaper(){
		List<Object> list = uploadPaperService.getAlreadyUploadPaper();
		JSONArray json = JSONArray.fromObject(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void getAlready_team(){
		List<Object> list = uploadPaperService.getRelatedteam(sjid);
		JSONArray json = JSONArray.fromObject(list);
		//System.out.println("json"+json);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getAlreadyTeamDetails(){
		List<Object> list = uploadPaperService.getAlreadyTeamDetails(sjid);
		JSONArray json = JSONArray.fromObject(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void remove_team(){
		boolean b = uploadPaperService.remove_team(teamid,sjid);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(b){
				response.getWriter().write("success");
			}else{
				response.getWriter().write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void setUploadPaperService(UploadPaperService uploadPaperService) {
		this.uploadPaperService = uploadPaperService;
	}

	public List<UserTeam> getList() {
		return list;
	}

	public void setList(List<UserTeam> list) {
		this.list = list;
	}

	public String getCheckid() {
		return checkid;
	}

	public void setCheckid(String checkid) {
		this.checkid = checkid;
	}

	public String getSjid() {
		return sjid;
	}

	public void setSjid(String sjid) {
		this.sjid = sjid;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	public String getSTeamid() {
		return teamid;
	}

	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}
	

}
