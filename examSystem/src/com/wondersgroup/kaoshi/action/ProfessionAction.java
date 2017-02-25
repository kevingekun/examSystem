package com.wondersgroup.kaoshi.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wondersgroup.kaoshi.bo.Cz70;
import com.wondersgroup.kaoshi.bo.Tjobsubject;
import com.wondersgroup.kaoshi.service.ProfessionService;

public class ProfessionAction extends ActionSupport{

	private String jobname;
	private String dj;
	private String companyForShort;
	private ProfessionService professionService;
	public String excute(){
		return SUCCESS;
	}
	
	public void getProfessionByName(){
		try {
			jobname = URLDecoder.decode(jobname,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//System.out.println(jobname);
		List<Cz70> list = new ArrayList<Cz70>();
		list = professionService.findByName(jobname);
		HttpServletResponse response = ServletActionContext.getResponse();
		if(list.size()!=0){
			try {
				Cz70 cz70 = list.get(0);
				response.getWriter().write(cz70.getCcz137());//存在
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().write("0");//不存在
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 检查题库中对应工种和等级的记录是否已经存在
	 */
	public void findTkByjobnameAndDj(){
		try {
			jobname = URLDecoder.decode(jobname,"utf-8").trim();
			dj = URLDecoder.decode(dj,"utf-8");
			companyForShort = URLDecoder.decode(companyForShort,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(!"".equals(companyForShort)&&!"null".equals(companyForShort)&&companyForShort!=null){
			jobname = companyForShort+jobname;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		boolean b = professionService.findByjobname(jobname);
		try {
			if(b){
				response.getWriter().write("1");//存在
			}else{
				response.getWriter().write("0");//不存在
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	public void findTkByjobname(){
		try {
			jobname = URLDecoder.decode(jobname,"utf-8").trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		boolean b = professionService.findByjobname(jobname);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			if(b){
				response.getWriter().write("1");//存在
			}else{
				response.getWriter().write("0");//不存在
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public void setProfessionService(ProfessionService professionService) {
		this.professionService = professionService;
	}

	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public String getCompanyForShort() {
		return companyForShort;
	}

	public void setCompanyForShort(String companyForShort) {
		this.companyForShort = companyForShort;
	}


}
