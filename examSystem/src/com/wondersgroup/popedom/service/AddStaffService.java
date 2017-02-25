package com.wondersgroup.popedom.service;

import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

import com.wondersgroup.falcon.paper.model.JiaoShiJi;
import com.wondersgroup.kaoshi.bo.Admission_card_file;
import com.wondersgroup.kaoshi.bo.Admission_card_pc;
import com.wondersgroup.kaoshi.bo.Admission_card_user;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.CalcBsFhDTO;
import com.wondersgroup.popedom.bo.ExamStaff;
import com.wondersgroup.popedom.bo.Exam_Arrangement;
import com.wondersgroup.popedom.bo.HZ95;
import com.wondersgroup.popedom.bo.WsExamArrangeDTO;

public interface AddStaffService {

	public CalcBsFhDTO uploadExamneeInfo(String examId);// 导入考生信息

	public WsExamArrangeDTO downloadUserInfo(String pcid);// 考生导入web service

	public WsExamArrangeDTO excuteWsExamArrange(WsExamArrangeDTO dto,
			Connection conn);

	public PageReturn addStaff(PageTool pageTool, String examid);// 根据考卷查询考生信息

	public List<Exam_Arrangement> searchArrangements(String sjmc);

	public List<HZ95> findJdpc();
	
	public List<Admission_card_pc> find_admission_card_pc();
	
	public List<Admission_card_file> find_admission_card_file();

	public String findSjmc(String sjmc);

	public boolean relateJdpcAndSjmc(String sjid, String checkedid);
	
	public boolean relateksAndkspc(String sjid, String checkedid);
	

	public PageReturn queryExamInfo(PageTool pageTool, String jdpcid);// 根据鉴定批次号查询考生信息

	public List<ExamStaff> getAllExamStaffs(String sj_mc);

	public InputStream exportSinfo(List<ExamStaff> list,
			List<Exam_Arrangement> list2);

	public InputStream exportRinfo(List<Exam_Arrangement> list);

	public List<JiaoShiJi> findsj();

	public boolean relatesj(String checkid);

	public boolean deleteJdpc(String checkid);
	
	public List<Object[]> printView(String pcid);
	
	public boolean deletePrintCard(String checkid);

	public List<Object> getDetailJdpc(String jdid);
	
	public List<Admission_card_user> getDetailPrintCard(String file_id);
	
	public boolean checkJdpc(String pcid);

}
