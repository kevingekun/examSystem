package com.wondersgroup.technocracy.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sshtools.j2ssh.net.HttpResponse;
import com.wondersgroup.kaoshi.util.AbstractAction;
import com.wondersgroup.technocracy.service.QueryExpertService;

public class InfoCountAction extends AbstractAction {

	private QueryExpertService queryexpertService;
	
	public void ageCount() {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getRequest().getSession().getServletContext()); 
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<String> list = queryexpertService.countAge(conn);
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONArray json = JSONArray.fromObject(list);
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void usenumCount(){
		List<String> list = queryexpertService.countUsenum();
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONArray json = JSONArray.fromObject(list);
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void academicCount() {

	}

	public void categoryCount() {

	}

	public QueryExpertService getQueryexpertService() {
		return queryexpertService;
	}

	public void setQueryexpertService(QueryExpertService queryexpertService) {
		this.queryexpertService = queryexpertService;
	}
	
	
}
