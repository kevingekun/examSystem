package com.wondersgroup.falcon.jdys.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import net.sf.json.JSONArray;

import com.wondersgroup.kaoshi.util.AbstractAction;
import com.wondersgroup.kaoshi.bo.EPapersSet;

import com.wondersgroup.falcon.dao.auth.ProfessionDAO;


@SuppressWarnings("serial")
public class ztzjAction extends AbstractAction  {
	private String sjmc;
	private String gzid;
	private String dj;
	private  String flag1;
	private List<EPapersSet> list;
	

	public void getEPapers_set(){
		ProfessionDAO professionDAO = new ProfessionDAO();
		List<Object> list1 =professionDAO.getEPapers_set1(gzid , dj, flag1);
		JSONArray json = JSONArray.fromObject(list1);
	//	System.out.println("json"+json);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public String getSjmc() {
		return sjmc;
	}

	public void setSjmc(String sjmc) {
		this.sjmc = sjmc;
	}

	public String getGzid() {
		return gzid;
	}

	public void setGzid(String gzid) {
		this.gzid = gzid;
	}
	
	public String getDj() {
		return dj;
	}

	public void setDj(String dj) {
		this.dj = dj;
	}

	public List<EPapersSet> getList() {
		return list;
	}

	public void setList(List<EPapersSet> list) {
		this.list = list;
	}

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}
	
}
