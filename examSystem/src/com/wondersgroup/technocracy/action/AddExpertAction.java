package com.wondersgroup.technocracy.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

 
import com.wondersgroup.kaoshi.util.AbstractAction;
import com.wondersgroup.technocracy.bo.Addexpert;
import com.wondersgroup.technocracy.bo.Addexperts;
import com.wondersgroup.technocracy.bo.HZ93;
import com.wondersgroup.technocracy.service.AddExpertService;
 

@SuppressWarnings("serial")
public class AddExpertAction extends AbstractAction {
	private Addexpert expert;
	private Addexperts experts;
	private HZ93  expertHz93;
	private AddExpertService addExpertService;
    private  String idnumber;
    private Date birthdate;
    List<Addexpert> list;
    private String[] hzz911;
	public void saveExpert() {
		expert.setIndicate("1");
		experts.setIndicate("1");

		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
		String sDateTime = sdf.format(birthdate);
		expert.setDate(Long.valueOf(sDateTime));
		addExpertService.addexpert(expert);
		experts.setHzz001(expert.getHzz001());
		addExpertService.addexperts(experts);
		//int s=hzz911.length;
	if(hzz911.length==1){
		expertHz93.setAaa131("1");
		expertHz93=new HZ93();
		expertHz93.setHzz001(expert.getHzz001());
		addExpertService.addexpertHz93(expertHz93);
	}
	else {
		for(int i=1;i<hzz911.length;i++){
			expertHz93=new HZ93();
			expertHz93.setAaa131("1");
			expertHz93.setHzz001(expert.getHzz001());
			expertHz93.setHzz911(hzz911[i]);
			addExpertService.addexpertHz93(expertHz93);
		}
		
	}
		//return SUCCESS;
	}
	public void queryexpert() {
		
		  idnumber=getRequest().getParameter("idnumber");
		  list= addExpertService.queryexpert(idnumber);
		  HttpServletResponse response = ServletActionContext.getResponse();
		 if (list.size()==0)
		 {
				try {
					response.getWriter().write("0");
				} catch (IOException e) {
					e.printStackTrace();
				}
		 }
		 else {
				try {
					response.getWriter().write("1");
				} catch (IOException e) {
					e.printStackTrace();
				}
		 }
		 
	}
	@Override
	public String execute() {
		return SUCCESS;
	}

	public Addexpert getExpert() {
		return expert;
	}

	public void setExpert(Addexpert expert) {
		this.expert = expert;
	}

	public AddExpertService getAddExpertService() {
		return addExpertService;
	}

	public void setAddExpertService(AddExpertService addExpertService) {
		this.addExpertService = addExpertService;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public List<Addexpert> getList() {
		return list;
	}
	public void setList(List<Addexpert> list) {
		this.list = list;
	}
	public Addexperts getExperts() {
		return experts;
	}
	public void setExperts(Addexperts experts) {
		this.experts = experts;
	}
	public HZ93 getExpertHz93() {
		return expertHz93;
	}
	public void setExpertHz93(HZ93 expertHz93) {
		this.expertHz93 = expertHz93;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String[] getHzz911() {
		return hzz911;
	}
	public void setHzz911(String[] hzz911) {
		this.hzz911 = hzz911;
	}
	 
	
	
	
}
