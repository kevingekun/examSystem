package com.wondersgroup.popedom.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.ExamStaff;
import com.wondersgroup.popedom.bo.WsExamArrangeDTO;
import com.wondersgroup.popedom.service.AddStaffService;

public class AddStaffAction extends AbstractPageNavAction {
	private AddStaffService addstaffService;
	private ExamStaff examStaff;
	private List<ExamStaff> staff;
	private String jdpcId;// 鉴定批次号
	private String wrongMessage;
	private String backUrl;

	@Override
	public String doAcion() throws Exception {

		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(getRequest().getSession()
						.getServletContext());
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jdpcId = jdpcId.replaceAll("\\s", "");
		String rt = "success";
		boolean b = this.addstaffService.checkJdpc(jdpcId);
		if (b) {
			rt = "error";
			wrongMessage = "该批次已经导入，请勿重复导入！";
		}else{
			if (jdpcId.length() >= 9) {
				// this.addstaffService.uploadExamneeInfo(jdpcId);//dblink导入考生信息
				WsExamArrangeDTO dto = this.addstaffService.downloadUserInfo(jdpcId);// webservice方式导入考生信息
				this.addstaffService.excuteWsExamArrange(dto, conn);
			}else{
				rt = "error";
				wrongMessage = "鉴定批次号有误，请检查批次号（不能少于9位）";
			}
			this.pageReturn = this.addstaffService.queryExamInfo(this.pageTool,
					jdpcId);
			this.staff = this.pageReturn.getReturnList();
		}
		backUrl = "authority/addstaff.jsp";
		return rt;
	}

	public List<ExamStaff> getStaff() {
		return staff;
	}

	public void setStaff(List<ExamStaff> staff) {
		this.staff = staff;
	}

	public AddStaffService getAddstaffService() {
		return addstaffService;
	}

	public void setAddstaffService(AddStaffService addstaffService) {
		this.addstaffService = addstaffService;
	}

	public ExamStaff getExamStaff() {
		return examStaff;
	}

	public void setExamStaff(ExamStaff examStaff) {
		this.examStaff = examStaff;
	}

	public String getJdpcId() {
		return jdpcId;
	}

	public void setJdpcId(String jdpcId) {
		this.jdpcId = jdpcId;
	}

	public String getWrongMessage() {
		return wrongMessage;
	}

	public void setWrongMessage(String wrongMessage) {
		this.wrongMessage = wrongMessage;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

}
