package com.wondersgroup.popedom.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.hibernate.dialect.Ingres10Dialect;

import com.wondersgroup.falcon.paper.model.EPapers;
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
import com.wondersgroup.popedom.dao.AddStaffDao;
import com.wondersgroup.popedom.service.AddStaffService;

public class AddStaffServiceImpl implements AddStaffService {
	private AddStaffDao addStaffDao;

	public void setAddStaffDao(AddStaffDao addStaffDao) {
		this.addStaffDao = addStaffDao;
	}
	
	public PageReturn addStaff(PageTool pageTool,String sj_mc) {
		 
		try {
			return addStaffDao.addStaff(pageTool,sj_mc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<HZ95> findJdpc(){
		return addStaffDao.findJdpc();
	}
//教师机关联 查询试卷
	public List<JiaoShiJi> findsj(){
		return addStaffDao.findsj();
	}
	
	public String findSjmc(String sjmc){
		return addStaffDao.findSjmc(sjmc);
	}
	public boolean relateJdpcAndSjmc(String sjid,String checkedid){
		return addStaffDao.relateJdpcAndSjmc(sjid, checkedid);
	}
	
	public boolean relatesj(String checkedid){
		return addStaffDao.relatesj(checkedid);
	}
	public boolean deleteJdpc(String checkedid){
		return addStaffDao.deleteJdpc(checkedid);
	}
	public List<Object> getDetailJdpc(String jdid){
		return addStaffDao.getDetailJdpc(jdid);
	}
	
	public PageReturn queryExamInfo(PageTool pageTool,String jdpcid) {
		 
		try {
			return addStaffDao.queryExameen(pageTool,jdpcid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public CalcBsFhDTO uploadExamneeInfo(String jdpcId) {
		
	      CalcBsFhDTO validateDTO = new CalcBsFhDTO(jdpcId);
	      validateDTO=addStaffDao.UploadExamneeInfo(validateDTO);
	      
		  /*CommonHibernateDaoUtils.executeStoreProcedure(spName, validateDTO);
		  Long sign=validateDTO.getRetCode();*/
		
		return validateDTO;
	}
	
	public WsExamArrangeDTO downloadUserInfo(String pcid){
		String batch = addStaffDao.downloadUserInfo(pcid);
		WsExamArrangeDTO dto = new WsExamArrangeDTO(pcid, batch);
		return dto;
	}
	
	public WsExamArrangeDTO excuteWsExamArrange(WsExamArrangeDTO dto,Connection conn){
		return addStaffDao.WsExamArrange(dto,conn);
	}

	public List<Exam_Arrangement> searchArrangements(String sjmc) {
		return addStaffDao.searchArrangements(sjmc);
	}

	/**
	 * 导出考生及考场信息
	 */
	/*public InputStream exportSinfo(List<ExamStaff> list,List<Exam_Arrangement> list2) {
		SXSSFWorkbook wb = new SXSSFWorkbook();
        Sheet sh = wb.createSheet();
        Row row0 = sh.createRow(0);
        Cell cell0 = row0.createCell(0);
        cell0.setCellValue("序号");
        Cell cell1 = row0.createCell(1);
        cell1.setCellValue("准考证号");
        Cell cell2 = row0.createCell(2);
        cell2.setCellValue("考生姓名");
        Cell cell3 = row0.createCell(3);
        cell3.setCellValue("身份证号");
        Cell cell4 = row0.createCell(4);
        cell4.setCellValue("考卷");
        Cell cell5 = row0.createCell(5);
        cell5.setCellValue("考场");
        Cell cell6 = row0.createCell(6);
        cell6.setCellValue("考场地址");
       // cell6.setCellStyle(style);
        sh.setColumnWidth(0, 6*256);
        sh.setColumnWidth(1, 18*256);
        sh.setColumnWidth(2, 12*256);
        sh.setColumnWidth(3, 22*256);
        int r4=0,r5=0,r6=0;
        sh.setColumnWidth(0, 4*256);
        sh.setColumnWidth(1, 16*256);
        sh.setColumnWidth(2, 14*256);
        sh.setColumnWidth(3, 20*256);
        sh.setColumnWidth(4, 16*256);
        sh.setColumnWidth(5, 16*256);
        sh.setColumnWidth(6, 16*256);
        for(int i = 1; i <= list.size(); i++){
        	ExamStaff e = list.get(i-1);
            Row row = sh.createRow(i);
            Cell cell00 = row.createCell(0);
            cell00.setCellValue(i);
            Cell cell01 = row.createCell(1);
            cell01.setCellValue(e.getZkh()==null?"":e.getZkh());
            Cell cell02 = row.createCell(2);
            cell02.setCellValue(e.getExamineename()==null?"":e.getExamineename());
            Cell cell03 = row.createCell(3);
            cell03.setCellValue(e.getIDnumber()==null?"":e.getIDnumber());
            Cell cell04 = row.createCell(4);
            cell04.setCellValue(e.getExamid()==null?"":e.getExamid());
            Cell cell05 = row.createCell(5);
            cell05.setCellValue(e.getExamroom()==null?"":e.getExamroom());
            Cell cell06 = row.createCell(6);
            cell06.setCellValue(e.getExamroomadress()==null?"":e.getExamroomadress());
        }
        int start2 = sh.getLastRowNum()+3;
        Row row1 = sh.createRow(start2);
        Cell cell11 = row1.createCell(0);
        cell11.setCellValue("序号");
        Cell cell12 = row1.createCell(1);
        cell12.setCellValue("考点名称");
        Cell cell13 = row1.createCell(2);
        cell13.setCellValue("考场名称");
        Cell cell14 = row1.createCell(3);
        cell14.setCellValue("教师机用户名");
        Cell cell15 = row1.createCell(4);
        cell15.setCellValue("教师机密码");
        int j = 0;
        for(int i = start2+1; i < list2.size()+start2+1; i++){
        	Exam_Arrangement e = list2.get(j);
            Row row = sh.createRow(i);
            Cell cell00 = row.createCell(0);
            cell00.setCellValue(j+1);
            Cell cell01 = row.createCell(1);
            cell01.setCellValue(e.getKdid()==null?"":e.getKdid());
            Cell cell02 = row.createCell(2);
            cell02.setCellValue(e.getKcid()==null?"":e.getKcid());
            Cell cell03 = row.createCell(3);
            cell03.setCellValue(e.getUsername()==null?"":e.getUsername());
            Cell cell04 = row.createCell(4);
            cell04.setCellValue(e.getPassword()==null?"":e.getPassword());
            j++;
        }
        
        sh.setColumnWidth(4, r4*512);
        sh.setColumnWidth(5, r5*512);
        sh.setColumnWidth(6, r6*512);
        
        String fileName=RandomStringUtils.randomAlphanumeric(10);
        StringBuffer sb=new StringBuffer(fileName);
        final File file = new File(sb.append(".xls").toString());
        try {
			OutputStream os=new FileOutputStream(file);
			try {
				wb.write(os);
		        os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        InputStream is=null;
        try {
                is=new FileInputStream(file);
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
        return is;//返回的是一个输入流
	}
*/
	@SuppressWarnings("deprecation")
	public InputStream exportSinfo(List<ExamStaff> list,
			List<Exam_Arrangement> list2) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
		style.setVerticalAlignment(style.VERTICAL_CENTER);// 垂直居中
		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 15);// 设置字体大小

		HSSFCellStyle setBorder = wb.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		HSSFSheet sh = wb.createSheet();
		// 标题格式设置
		HSSFCellStyle style1 = wb.createCellStyle();
		style1.setFont(font);
		font.setFontHeightInPoints((short) 17);// 设置字体大小
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中

		HSSFCellStyle style3 = wb.createCellStyle();
		style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
		style3.setVerticalAlignment(style.VERTICAL_CENTER);// 垂直居中
		// 记录表格式
		HSSFCellStyle style23 = wb.createCellStyle();
		style23.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 居中
		style23.setVerticalAlignment(style.VERTICAL_BOTTOM);// 垂直居中
		style23.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style23.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style23.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style23.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// 年月日格式
		HSSFCellStyle style33 = wb.createCellStyle();
		style33.setVerticalAlignment(style.VERTICAL_CENTER);// 垂直居中

		String name = "";
		ExamStaff el = list.get(0);
		name = String.valueOf(el.getExamid());
		HSSFRow row = sh.createRow(0);
		HSSFCell cell11 = row.createCell(0);
		cell11.setCellValue(name + "考场记录表");
		cell11.setCellStyle(style1);
		sh.addMergedRegion(new Region(0, // first row (0-based)
				(short) 0, // first column (0-based)
				0, // last row (0-based)
				(short) 4 // last column (0-based)
		));
		String jdpc = addStaffDao.getJdpcBySjmc(name).toString();
		jdpc = jdpc.substring(1, jdpc.length() - 1);
		HSSFRow row_jdpc = sh.createRow(1);
		HSSFCell cell_jdpc = row_jdpc.createCell(0);
		cell_jdpc.setCellValue(jdpc);
		cell_jdpc.setCellStyle(style3);
		sh.addMergedRegion(new Region(1, // first row (0-based)
				(short) 0, // first column (0-based)
				1, // last row (0-based)
				(short) 4 // last column (0-based)
		));

		int start1 = sh.getLastRowNum() + 1;
		HSSFRow row1 = sh.createRow(start1);
		HSSFCell cell1 = row1.createCell(0);
		cell1.setCellValue("序号");
		cell1.setCellStyle(style);
		HSSFCell cell2 = row1.createCell(1);
		cell2.setCellValue("考生姓名");
		cell2.setCellStyle(style);
		HSSFCell cell3 = row1.createCell(2);
		cell3.setCellValue("准考证号");
		cell3.setCellStyle(style);
		HSSFCell cell4 = row1.createCell(3);
		cell4.setCellValue("身份证号");
		cell4.setCellStyle(style);
		/*
		 * HSSFCell cell5 = row1.createCell(4); cell5.setCellValue("考卷");
		 * cell5.setCellStyle(style);
		 */
		/*
		 * HSSFCell cell6 = row1.createCell(4); cell6.setCellValue("等级");
		 * cell6.setCellStyle(style);
		 */
		HSSFCell cell7 = row1.createCell(4);
		cell7.setCellValue("备注");
		cell7.setCellStyle(style);
		sh.setColumnWidth(0, 14 * 256);
		sh.setColumnWidth(1, 14 * 256);
		sh.setColumnWidth(2, 22 * 256);
		sh.setColumnWidth(3, 22 * 256);
		sh.setColumnWidth(4, 14 * 256);
		// sh.setColumnWidth(5, 9*256);
		// sh.setColumnWidth(6, 1*512);
		// sh.setColumnWidth(7, 5*512);

		int r4 = 0, r5 = 0, r6 = 0;
		int startExam = sh.getLastRowNum() + 1;
		for (int i = 1; i <= list.size(); i++) {
			ExamStaff e = list.get(i-1);
			// i = sh.getLastRowNum()+1;
			HSSFRow row2 = sh.createRow(startExam);
			startExam++;

			HSSFCell cell8 = row2.createCell(0);
			cell8.setCellValue(i);
			cell8.setCellStyle(style);

			HSSFCell cell9 = row2.createCell(1);
			cell9.setCellValue(e.getExamineename() == null ? "" : e
					.getExamineename());
			cell9.setCellStyle(style);

			HSSFCell cell10 = row2.createCell(2);
			cell10.setCellValue(e.getZkh() == null ? "" : e.getZkh());
			cell10.setCellStyle(style);

			HSSFCell cell111 = row2.createCell(3);
			cell111.setCellValue(e.getIDnumber() == null ? "" : e.getIDnumber());
			cell111.setCellStyle(style);

			/*
			 * HSSFCell cell12 = row2.createCell(4); String c4 = "";
			 * if(!"".equals(e.getExamid())){ c4 = e.getExamid();
			 * if(c4.length()>r4){ r4 = c4.length(); } }
			 * cell12.setCellValue(c4); cell12.setCellStyle(style);
			 */

			/*
			 * HSSFCell cell13 = row2.createCell(4); String c4 = "";
			 * if(!"".equals(dj)){ c4 = dj; if(c4.length()>r5){ r4 =
			 * c4.length(); } } cell13.setCellValue(c4);
			 * cell13.setCellStyle(style);
			 */

			HSSFCell cell14 = row2.createCell(4);
			String c4 = "               ";
			cell14.setCellValue(c4);
			cell14.setCellStyle(style);
		}

		int start222 = sh.getLastRowNum() + 3;
		HSSFRow row222 = sh.createRow(start222);
		HSSFCell cell121 = row222.createCell(0);
		cell121.setCellValue("序号");
		cell121.setCellStyle(style);
		HSSFCell cell122 = row222.createCell(1);
		cell122.setCellValue("考点名称");
		cell122.setCellStyle(style);
		HSSFCell cell123 = row222.createCell(2);
		cell123.setCellValue("考场名称");
		cell123.setCellStyle(style);
		HSSFCell cell124 = row222.createCell(3);
		cell124.setCellValue("教师机用户名");
		cell124.setCellStyle(style);
		HSSFCell cell125 = row222.createCell(4);
		cell125.setCellValue("教师机密码");
		cell125.setCellStyle(style);

		for (int p = 1; p <= list2.size(); p++) {
			Exam_Arrangement l = list2.get(p - 1);
			// p = sh.getLastRowNum()+1;
			HSSFRow row233 = sh.createRow(start222 + p);

			HSSFCell cell00 = row233.createCell(0);
			cell00.setCellValue(p);
			cell00.setCellStyle(style);

			HSSFCell cell201 = row233.createCell(1);
			cell201.setCellValue(l.getKdid() == null ? "" : l.getKdid());
			cell201.setCellStyle(style);

			HSSFCell cell202 = row233.createCell(2);
			cell202.setCellValue(l.getKcid() == null ? "" : l.getKcid());
			cell202.setCellStyle(style);

			HSSFCell cell203 = row233.createCell(3);
			cell203.setCellValue(l.getUsername() == null ? "" : l.getUsername());
			cell203.setCellStyle(style);

			HSSFCell cell204 = row233.createCell(4);
			cell204.setCellValue(l.getPassword() == null ? "" : l.getPassword());
			cell204.setCellStyle(style);
			// o++;
		}

		int start3 = sh.getLastRowNum() + 1;
		HSSFRow row3 = sh.createRow(start3);
		row3.setHeight((short) 400);
		HSSFCell cell15 = row3.createCell(0);
		cell15.setCellValue("    ");
		cell15.setCellStyle(style33);
		sh.addMergedRegion(new Region(start3, // first row (0-based)
				(short) 0, // first column (0-based)
				start3, // last row (0-based)
				(short) 4 // last column (0-based)
		));

		int start4 = sh.getLastRowNum() + 1;

		sh.addMergedRegion(new Region(start4, // first row (0-based)
				(short) 3, // first column (0-based)
				start4, // last row (0-based)
				(short) 4 // last column (0-based)
		));
		HSSFRow row4 = sh.createRow(start4);
		row4.setHeight((short) 500);
		HSSFCell cell16 = row4.createCell(0);
		cell16.setCellValue("考试形式");
		cell16.setCellStyle(style);
		HSSFCell cell17 = row4.createCell(1);
		cell17.setCellValue("机考     笔试");
		cell17.setCellStyle(style);
		HSSFCell cell18 = row4.createCell(2);
		cell18.setCellValue("考场号");
		cell18.setCellStyle(style);
		HSSFCell cell19 = row4.createCell(3);
		cell19.setCellValue("第        鉴定室 ");
		cell19.setCellStyle(style);
		HSSFCell cell129 = row4.createCell(4);
		cell129.setCellValue("  ");
		cell129.setCellStyle(style);

		int start5 = sh.getLastRowNum() + 1;
		sh.addMergedRegion(new Region(start5, // first row (0-based)
				(short) 3, // first column (0-based)
				start5, // last row (0-based)
				(short) 4 // last column (0-based)
		));
		HSSFRow row5 = sh.createRow(start5);
		row5.setHeight((short) 500);
		HSSFCell cell20 = row5.createCell(0);
		cell20.setCellValue("  实考人数  ");
		cell20.setCellStyle(style);
		HSSFCell cell21 = row5.createCell(1);
		cell21.setCellValue("   ");
		cell21.setCellStyle(style);
		HSSFCell cell22 = row5.createCell(2);
		cell22.setCellValue("  缺考人数 ");
		cell22.setCellStyle(style);
		HSSFCell cell23 = row5.createCell(3);
		cell23.setCellValue("    ");
		cell23.setCellStyle(style);
		HSSFCell cell629 = row5.createCell(4);
		cell629.setCellValue("  ");
		cell629.setCellStyle(style);

		int start6 = sh.getLastRowNum() + 1;
		sh.addMergedRegion(new Region(start6, // first row (0-based)
				(short) 1, // first column (0-based)
				start6, // last row (0-based)
				(short) 4 // last column (0-based)
		));
		HSSFRow row6 = sh.createRow(start6);
		HSSFCell cell24 = row6.createCell(0);
		cell24.setCellValue("  考试情况 : ");
		row6.setHeight((short) 900);
		cell24.setCellStyle(style);
		HSSFCell cell200 = row6.createCell(1);
		cell200.setCellValue("    ");
		cell200.setCellStyle(style);
		HSSFCell cell2001 = row6.createCell(2);
		cell2001.setCellValue("    ");
		cell2001.setCellStyle(style);
		HSSFCell cell2002 = row6.createCell(3);
		cell2002.setCellValue("    ");
		cell2002.setCellStyle(style);
		HSSFCell cell201 = row6.createCell(4);
		cell201.setCellValue("    ");
		cell201.setCellStyle(style);

		int start61 = sh.getLastRowNum() + 1;
		sh.addMergedRegion(new Region(start61, // first row (0-based)
				(short) 1, // first column (0-based)
				start61, // last row (0-based)
				(short) 4 // last column (0-based)
		));
		HSSFRow row66 = sh.createRow(start61);
		HSSFCell cell25 = row66.createCell(0);
		cell25.setCellValue("  监考员签字:  ");
		row66.setHeight((short) 900);
		cell25.setCellStyle(style);
		HSSFCell cell26 = row66.createCell(1);
		cell26.setCellValue(" 日期:   年     月      日  ");
		cell26.setCellStyle(style23);
		HSSFCell cell296 = row66.createCell(2);
		cell296.setCellValue("   ");
		cell296.setCellStyle(style);
		HSSFCell cell297 = row66.createCell(3);
		cell297.setCellValue("   ");
		cell297.setCellStyle(style);
		HSSFCell cell298 = row66.createCell(4);
		cell298.setCellValue(" ");
		cell298.setCellStyle(style);

		String fileName = RandomStringUtils.randomAlphanumeric(10);
		StringBuffer sb = new StringBuffer(fileName);
		final File file = new File(sb.append(".xls").toString());
		try {
			OutputStream os = new FileOutputStream(file);
			try {
				wb.write(os);
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return is;// 返回的是一个输入流
	}
	public List<ExamStaff> getAllExamStaffs(String sj_mc) {
		return addStaffDao.getExportInfo(sj_mc);
	}

	public InputStream exportRinfo(List<Exam_Arrangement> list) {//弃用，已合并到exportSinfo
		SXSSFWorkbook wb = new SXSSFWorkbook(); // keep 100 rows in memory, exceeding rows will be flushed to disk
        Sheet sh = wb.createSheet();
        Row row0 = sh.createRow(0);
        Cell cell0 = row0.createCell(0);
        cell0.setCellValue("序号");
        Cell cell1 = row0.createCell(1);
        cell1.setCellValue("考点名称");
        Cell cell2 = row0.createCell(2);
        cell2.setCellValue("考场名称");
        Cell cell3 = row0.createCell(3);
        cell3.setCellValue("教师机用户名");
        Cell cell4 = row0.createCell(4);
        cell4.setCellValue("教师机密码");
        sh.setColumnWidth(0, 4*256);
        sh.setColumnWidth(1, "考点名称".getBytes().length*2*256);
        sh.setColumnWidth(2, "考场名称".getBytes().length*2*256);
        sh.setColumnWidth(3, 12*256);
        sh.setColumnWidth(4, 12*256);
        for(int i = 1; i <= list.size(); i++){
        	Exam_Arrangement e = list.get(i-1);
            Row row = sh.createRow(i);
            Cell cell00 = row.createCell(0);
            cell00.setCellValue(i);
            Cell cell01 = row.createCell(1);
            cell01.setCellValue(e.getKdid()==null?"":e.getKdid());
            Cell cell02 = row.createCell(2);
            cell02.setCellValue(e.getKcid()==null?"":e.getKcid());
            Cell cell03 = row.createCell(3);
            cell03.setCellValue(e.getUsername()==null?"":e.getUsername());
            Cell cell04 = row.createCell(4);
            cell04.setCellValue(e.getPassword()==null?"":e.getPassword());
        }
        String fileName=RandomStringUtils.randomAlphanumeric(10);
        StringBuffer sb=new StringBuffer(fileName);
        final File file = new File(sb.append(".xls").toString());
        try {
			OutputStream os=new FileOutputStream(file);
			try {
				wb.write(os);
		        os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        InputStream is=null;
        try {
                is=new FileInputStream(file);
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
        return is;//返回的是一个输入流
	}

	@Override
	public boolean checkJdpc(String pcid) {
		return addStaffDao.checkJdpc(pcid);
	}

	@Override
	public List<Admission_card_pc> find_admission_card_pc() {
		return addStaffDao.find_admission_card_pc();
	}

	@Override
	public List<Admission_card_file> find_admission_card_file() {
		return addStaffDao.find_admission_card_file();
	}

	@Override
	public boolean relateksAndkspc(String pcid, String checkid) {
		return addStaffDao.relateksAndkspc(pcid, checkid);
	}

	@Override
	public List<Admission_card_user> getDetailPrintCard(String file_id) {
		return addStaffDao.getDetailPrintCard(file_id);
	}

	@Override
	public boolean deletePrintCard(String checkid) {
		return addStaffDao.deletePrintCard(checkid);
	}

	@Override
	public List<Object[]> printView(String pcid) {
		return addStaffDao.printView(pcid);
	}

}
