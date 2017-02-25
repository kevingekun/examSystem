package com.wondersgroup.popedom.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.ExamStaff;
import com.wondersgroup.popedom.bo.Exam_Arrangement;
import com.wondersgroup.popedom.service.AddStaffService;

public class ExamArrangementAction extends AbstractPageNavAction {

	private AddStaffService addstaffService;
	private ExamStaff examStaff;
	private List<ExamStaff> staff;
	private String examId;// 鉴定批次号
	private List<Exam_Arrangement> eArrangement;

	private InputStream excelStream;
	private String downloadFileName;

	@SuppressWarnings("unchecked")
	@Override
	public String doAcion() throws Exception {
		examId = examId.replaceAll("\\s","");
		this.pageReturn = this.addstaffService.addStaff(this.pageTool, examId);
		this.staff = this.pageReturn.getReturnList();
		this.eArrangement = this.addstaffService.searchArrangements(examId);
		return SUCCESS;
	}

	/**
	 * 导出考生及考场信息excel
	 * 
	 * @return
	 */
	public String exportSinfo() {
		List<ExamStaff> list = this.addstaffService.getAllExamStaffs(examId);
		excelStream = this.addstaffService.exportSinfo(list,eArrangement);
		downloadFileName = examId+"-考场信息.xls";
		try {
			downloadFileName=new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 导出考场信息excel------弃用
	 * 
	 * @return
	 */
	public String exportRinfo() {
		excelStream = this.addstaffService.exportRinfo(eArrangement);
		downloadFileName = examId+"-考场信息.xls";
		try {
			downloadFileName=new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
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

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public List<Exam_Arrangement> geteArrangement() {
		return eArrangement;
	}

	public void seteArrangement(List<Exam_Arrangement> eArrangement) {
		this.eArrangement = eArrangement;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

}
