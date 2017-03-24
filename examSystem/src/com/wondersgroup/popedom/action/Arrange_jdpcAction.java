package com.wondersgroup.popedom.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.paper.model.JiaoShiJi;
import com.wondersgroup.kaoshi.bo.Admission_card_file;
import com.wondersgroup.kaoshi.bo.Admission_card_pc;
import com.wondersgroup.kaoshi.bo.Admission_card_user;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.HZ95;
import com.wondersgroup.popedom.bo.ImportExamSysDTO;
import com.wondersgroup.popedom.service.AddStaffService;

public class Arrange_jdpcAction extends AbstractPageNavAction {

	private AddStaffService addstaffService;
	private List<HZ95> list;
	private List<JiaoShiJi>  list2;
	private String sjmc = "";
	private String checkid;
	private String sjid;
	private String info;
	private String info4;
	private String flag;
	private String jdid;
	
	private String pcid;
	
	private List<Admission_card_pc> acpList;
	private List<Admission_card_file> acfList;
	
	private List<Object[]> printVOs;
	

	@Override
	public String doAcion() throws Exception {
		this.list = this.addstaffService.findJdpc();
		return SUCCESS;
	}
	
	public String arrangePrintCard(){
		this.acpList = addstaffService.find_admission_card_pc();
		this.acfList = addstaffService.find_admission_card_file();
		info="0";
		return SUCCESS;
	}
	/**
	 *将打印准考证考生信息导入考务系统 
	 * @author gkk
	 * @date 2017-3-3 上午9:49:02
	 */
	public void importExamSys(){
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
		ImportExamSysDTO dto = new ImportExamSysDTO();
		dto.setPcId(pcid);
		dto = addstaffService.importExamSys(dto, conn);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(dto.getRetMsg());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public void checkSjmc(){
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			sjmc = java.net.URLDecoder.decode(sjmc , "UTF-8");
			String sjid = this.addstaffService.findSjmc(sjmc);
			response.getWriter().write(sjid);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String relateJdpcAndSjmc(){
		boolean b = this.addstaffService.relateJdpcAndSjmc(sjid, checkid);
		if (b) {
			info="1";
		}else{
			info="2";
		}
		getRequest().getSession().setAttribute("info2", "0");
		return SUCCESS;
	}
	/**
	 * 考生与准考证批次关联
	 * @return
	 * @author gkk
	 * @date 2017-3-3 上午9:13:35
	 */
	public String relateksAndkspc(){
		boolean b = this.addstaffService.relateksAndkspc(sjid, checkid);
		if (b) {
			info="1";
		}else{
			info="2";
		}
		this.acpList = addstaffService.find_admission_card_pc();
		this.acfList = addstaffService.find_admission_card_file();
		//getRequest().getSession().setAttribute("info2", "1");
		return SUCCESS;
	}
	
	//教师机关联
	public String relatesj(){
		boolean c = this.addstaffService.relatesj(checkid);
		if (c) {
			info4="1";
		}else{
			info4="2";
		}	
		getRequest().getSession().setAttribute("info_kc", "0");
		return SUCCESS;
	}
	public void deleteJdpc(){
		boolean b = this.addstaffService.deleteJdpc(checkid);
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
	
	public String printView(){
		String pcid = getRequest().getParameter("pc_id");
		printVOs = addstaffService.printView(pcid);
		//getRequest().getSession().setAttribute("printVOs", list);
		return SUCCESS;
	}
	
	/**
	 * 准考证打印 删除考生信息
	 * 
	 * @author gkk
	 * @date 2016-12-6 下午3:29:25
	 */
	public void deletePrintCard(){
		boolean b = addstaffService.deletePrintCard(checkid);
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
	
	//查询 试卷 教师机关联		
	public String jiaoshiji(){
		this.list2=this.addstaffService.findsj();
		return SUCCESS;
	}
	public void getDetailJdpc(){
		List<Object> list = this.addstaffService.getDetailJdpc(jdid);
		JSONArray json = JSONArray.fromObject(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 打印准考证 查看导入文件信息
	 * 
	 * @author gkk
	 * @date 2016-12-6 下午3:11:52
	 */
	public void getDetailPrintCard(){
		List<Admission_card_user> list = addstaffService.getDetailPrintCard(jdid);
		JSONArray json = JSONArray.fromObject(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public AddStaffService getAddstaffService() {
		return addstaffService;
	}

	public void setAddstaffService(AddStaffService addstaffService) {
		this.addstaffService = addstaffService;
	}

	public List<HZ95> getList() {
		return list;
	}

	public void setList(List<HZ95> list) {
		this.list = list;
	}
	public String getSjmc() {
		return sjmc;
	}
	public void setSjmc(String sjmc) {
		this.sjmc = sjmc;
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
	public List<JiaoShiJi> getList2() {
		return list2;
	}
	public void setList2(List<JiaoShiJi> list2) {
		this.list2 = list2;
	}
	public String getInfo4() {
		return info4;
	}
	public void setInfo4(String info4) {
		this.info4 = info4;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getJdid() {
		return jdid;
	}
	public void setJdid(String jdid) {
		this.jdid = jdid;
	}

	public List<Admission_card_pc> getAcpList() {
		return acpList;
	}

	public void setAcpList(List<Admission_card_pc> acpList) {
		this.acpList = acpList;
	}

	public List<Admission_card_file> getAcfList() {
		return acfList;
	}

	public void setAcfList(List<Admission_card_file> acfList) {
		this.acfList = acfList;
	}

	public List<Object[]> getPrintVOs() {
		return printVOs;
	}

	public void setPrintVOs(List<Object[]> printVOs) {
		this.printVOs = printVOs;
	}

	public String getPcid() {
		return pcid;
	}

	public void setPcid(String pcid) {
		this.pcid = pcid;
	}

}
