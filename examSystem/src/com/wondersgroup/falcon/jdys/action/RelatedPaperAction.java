package com.wondersgroup.falcon.jdys.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.wondersgroup.falcon.jdys.service.JdService;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.kaoshi.util.AbstractAction;

public class RelatedPaperAction extends AbstractAction{

	private JdService jdService;
	private String sjid;
	private String jdid;
	private String kcid;
	private EPapers epapers;
	private String  sjmc ;
	private String   sjzf ;
	private String   bhgfs;
	private String  djsx ;
	private String   kksj ;
	private String   yxqjzsj ;
	private String  zt ;
	private String   sj_kslx ;
	private String  jjsj ;
	private String  ljcf;
	
	
	
	public JdService getJdService() {
		return jdService;
	}
	@Override
	public String execute(){
		return SUCCESS;
	}
	/**
	 * 获取已关联考生的试卷
	 */
	public void getRelatedPaper(){
		List<Object> list = jdService.getRelatedPaper();
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
	 * 按试卷获取已关联的鉴定批次
	 */
	public void getRelatedJdpc(){
		List<Object> list = jdService.getRelatedJdpc(sjid);
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
	 * 取消鉴定批次与试卷的关联
	 */
	public void removeJdpc(){
		boolean b = jdService.removeJdpc(jdid);
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
	
	/**
	 * 获取已关联试卷的考场
	 */
	public void getRelatekc(){
		List<Object> list = jdService.getRelatekc();
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
	 * 按考场获取已关联的试卷
	 */
	public void getRelatedsj(){
		List<Object> list = jdService.getRelatedsj(kcid);
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
	 * 取消鉴定批次与试卷的关联
	 */
	public void removesj(){
		boolean b = jdService.removesj(sjid);
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
	//加载试卷信息
	public void changePaper(){
		List<Object[]> list = jdService.changepaper(sjid);
		JSONArray json = JSONArray.fromObject(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	//更改试卷信息
		public void updatePaper(){
			String b = jdService.updatePaper(sjid,sjmc, sjzf , bhgfs, djsx ,kksj ,yxqjzsj ,zt ,sj_kslx , jjsj ,ljcf);
			HttpServletResponse response = ServletActionContext.getResponse();
			try {
				response.getWriter().write(String.valueOf(b));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void clearTime(){
			String b = jdService.clearTime(sjid);
			HttpServletResponse response = ServletActionContext.getResponse();
			try {
				response.getWriter().write(String.valueOf(b));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	
	
	public void setJdService(JdService jdService) {
		this.jdService = jdService;
	}
	public String getSjid() {
		return sjid;
	}
	public void setSjid(String sjid) {
		this.sjid = sjid;
	}
	public String getJdid() {
		return jdid;
	}
	public void setJdid(String jdid) {
		this.jdid = jdid;
	}
	public String getKcid() {
		return kcid;
	}
	public void setKcid(String kcid) {
		this.kcid = kcid;
	}
	public EPapers getEpapers() {
		return epapers;
	}
	public void setEpapers(EPapers epapers) {
		this.epapers = epapers;
	}
	public String getSjmc() {
		return sjmc;
	}
	public void setSjmc(String sjmc) {
		this.sjmc = sjmc;
	}
	public String getSjzf() {
		return sjzf;
	}
	public void setSjzf(String sjzf) {
		this.sjzf = sjzf;
	}
	public String getBhgfs() {
		return bhgfs;
	}
	public void setBhgfs(String bhgfs) {
		this.bhgfs = bhgfs;
	}
	public String getDjsx() {
		return djsx;
	}
	public void setDjsx(String djsx) {
		this.djsx = djsx;
	}
	public String getKksj() {
		return kksj;
	}
	public void setKksj(String kksj) {
		this.kksj = kksj;
	}
	public String getYxqjzsj() {
		return yxqjzsj;
	}
	public void setYxqjzsj(String yxqjzsj) {
		this.yxqjzsj = yxqjzsj;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getSj_kslx() {
		return sj_kslx;
	}
	public void setSj_kslx(String sj_kslx) {
		this.sj_kslx = sj_kslx;
	}
	public String getJjsj() {
		return jjsj;
	}
	public void setJjsj(String jjsj) {
		this.jjsj = jjsj;
	}
	public String getLjcf() {
		return ljcf;
	}
	public void setLjcf(String ljcf) {
		this.ljcf = ljcf;
	}
	
}
