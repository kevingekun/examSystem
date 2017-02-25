package com.wondersgroup.popedom.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.model.auth.UserTeam;
import com.wondersgroup.kaoshi.bo.Station_xf;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.service.UploadPaperService;

@SuppressWarnings("serial")
public class uploadPaper_xfAction extends AbstractPageNavAction{

	private UploadPaperService uploadPaperService;
	private List<UserTeam> list;
	private List<Station_xf> xfList;
	
	private String checkid;
	private String sjid;
	private String info;
	private String teamid;
	
	private String station_id;
	
	
	@Override
	public String doAcion() throws Exception {
		//this.list = uploadPaperService.geTeams();
		this.xfList = uploadPaperService.getXfs();
		return SUCCESS;
	}
	
	public void relatePaperAndTeam_xf() {
		boolean b = this.uploadPaperService.relatePaperAndTeam_xf(sjid, checkid);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if (b) {
				response.getWriter().write("success");
			} else {
				response.getWriter().write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// getRequest().getSession().setAttribute("info2", "0");
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
	
	public void getAlready_paper_xf(){
		List<Object> list = uploadPaperService.getRelatedPaper_xf();
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
	
	
	public void getAlready_team_xf(){
		List<Object> list = uploadPaperService.getRelatedteam_xf(sjid);
		JSONArray json = JSONArray.fromObject(list);
		//System.out.println("json"+json);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据鉴定所id查看下放记录
	 * 
	 * @author gkk
	 * @date 2017-2-8 下午2:17:50
	 */
	public void getStationRecordOfXf(){
		List<Object> list = uploadPaperService.getStationRecordOfXf(station_id);
		JSONArray json = JSONArray.fromObject(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getAlready_xf_Paper(){
		List<Object> list = uploadPaperService.getAlready_xf_Paper();
		JSONArray json = JSONArray.fromObject(list);
		//System.out.println("json"+json);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getAlreadyDloadTeamDetails_xf(){
		List<Object> list = uploadPaperService.getAlreadyDloadTeamDetails_xf(sjid);
		JSONArray json = JSONArray.fromObject(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void remove_team_xf(){
		boolean b = uploadPaperService.remove_team_xf(teamid,sjid);
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
	
	public void xf_paper(){
		String  b = uploadPaperService.xf_paper(sjid);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(b=="true"){
				response.getWriter().write("success");
			}else if (b=="flase"){
				response.getWriter().write("error");
			}else if (b=="No_users"){
				response.getWriter().write("No_users");
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

	public List<Station_xf> getXfList() {
		return xfList;
	}

	public void setXfList(List<Station_xf> xfList) {
		this.xfList = xfList;
	}

	public String getStation_id() {
		return station_id;
	}

	public void setStation_id(String station_id) {
		this.station_id = station_id;
	}
	

}

