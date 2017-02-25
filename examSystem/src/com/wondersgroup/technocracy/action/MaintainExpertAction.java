package com.wondersgroup.technocracy.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.technocracy.bo.Addexpert;
import com.wondersgroup.technocracy.bo.Addexperts;
import com.wondersgroup.technocracy.bo.HZ93;
import com.wondersgroup.technocracy.service.MaintainExpertService;

public class MaintainExpertAction extends AbstractPageNavAction{

	private MaintainExpertService maintainExpertService;
	private Addexpert expert;
	private Addexperts fexpert;
	private Addexpert expert2;
	private Addexperts fexpert2;
	private List<HZ93> hz93;
	private List<Addexpert> experts;
	private String expertId;
	private String[] expertStyle;
	private String date;
	private String style;
	
	
	@SuppressWarnings("unchecked")
	public String checkExpert(){
		this.pageReturn = maintainExpertService.checkExpert(pageTool, 
				expert.getName(), expert.getOrg(),fexpert.getCommittee(),style);
		this.experts = this.pageReturn.getReturnList();
		return SUCCESS;
	}
	public void deleteExpert(){
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			maintainExpertService.deleteExpert(expertId);
			response.getWriter().write("d_success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void findExpertById(){
		List<Object> list = maintainExpertService.findById(expertId);
		expert2 = (Addexpert) list.get(0);
		fexpert2 = (Addexperts) list.get(1);
		hz93 = (List<HZ93>)list.get(2);
		Object[] o = {expert2,fexpert2,hz93};
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONArray json = JSONArray.fromObject(o);
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void updateExpert(){
		StringBuffer sb = new StringBuffer();
		sb.append(date.substring(0, 4));
		sb.append(date.substring(5, 7));
		sb.append(date.substring(8, 10));
		expert.setDate(Long.valueOf(sb.toString()));
		maintainExpertService.updateExpertStyle(expertStyle, expert.getHzz001());
		maintainExpertService.updateExpert(expert, fexpert);
	}
	
	@Override
	public String doAcion() throws Exception {
		if(expert!=null){
			this.pageReturn = maintainExpertService.checkExpert(pageTool, 
					expert.getName(), expert.getOrg(),fexpert.getCommittee(),style);
			this.experts = this.pageReturn.getReturnList();
		}
		return SUCCESS;
	}


	public void setMaintainExpertService(MaintainExpertService maintainExpertService) {
		this.maintainExpertService = maintainExpertService;
	}

	public Addexpert getExpert() {
		return expert;
	}

	public void setExpert(Addexpert expert) {
		this.expert = expert;
	}


	public List<Addexpert> getExperts() {
		return experts;
	}


	public void setExperts(List<Addexpert> experts) {
		this.experts = experts;
	}
	public String getExpertId() {
		return expertId;
	}
	public void setExpertId(String expertId) {
		this.expertId = expertId;
	}
	public Addexperts getFexpert() {
		return fexpert;
	}
	public void setFexpert(Addexperts fexpert) {
		this.fexpert = fexpert;
	}
	public List<HZ93> getHz93() {
		return hz93;
	}
	public void setHz93(List<HZ93> hz93) {
		this.hz93 = hz93;
	}
	public Addexpert getExpert2() {
		return expert2;
	}
	public void setExpert2(Addexpert expert2) {
		this.expert2 = expert2;
	}
	public Addexperts getFexpert2() {
		return fexpert2;
	}
	public void setFexpert2(Addexperts fexpert2) {
		this.fexpert2 = fexpert2;
	}
	public String[] getExpertStyle() {
		return expertStyle;
	}
	public void setExpertStyle(String[] expertStyle) {
		this.expertStyle = expertStyle;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	

}
