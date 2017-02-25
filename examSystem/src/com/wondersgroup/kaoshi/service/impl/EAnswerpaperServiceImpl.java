package com.wondersgroup.kaoshi.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.wondersgroup.falcon.paper.model.EAnswerpaper;
import com.wondersgroup.falcon.paper.model.EAnswerquestions;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.kaoshi.dao.EAnswerpaperDAOImpl;
import com.wondersgroup.kaoshi.service.EAnswerpaperService;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.local.bo.WrongPersent_ws;
import com.wondersgroup.popedom.bo.ELogMonitor;
import com.wondersgroup.popedom.bo.EUser;

public class EAnswerpaperServiceImpl implements EAnswerpaperService {
	private EAnswerpaperDAOImpl eanswerpaperDAOImpl = new EAnswerpaperDAOImpl();

	public void setEanswerpaperDAOImpl(EAnswerpaperDAOImpl eanswerpaperDAOImpl) {
		this.eanswerpaperDAOImpl = eanswerpaperDAOImpl;
	}

	public PageReturn findmypaper(PageTool pagetool, String username,
			EPapers epaper) {

		try {
			return this.eanswerpaperDAOImpl.findByUser(pagetool, username, epaper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public long saveEAnswerpaper(EAnswerpaper eanswerpaper) {
		try {
			return this.eanswerpaperDAOImpl.saveEAnswerpaper(eanswerpaper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Long(0);
	}

	public void saveEAnswerquestions(EAnswerquestions eanswerquestions) {
		try {
			this.eanswerpaperDAOImpl.saveEAnswerquestions(eanswerquestions);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateEAnswerquestions(EAnswerquestions eanswerquestions) {
		try {
			this.eanswerpaperDAOImpl.updateEAnswerquestions(eanswerquestions);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PageReturn findsypaper(PageTool pagetool, Long djSyzt, String sjMc,
			Date kksj,String flag) {
		try {
			return this.eanswerpaperDAOImpl.findbyZt(pagetool, djSyzt, sjMc, kksj,flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public PageReturn findAnswerPaperBySjId(PageTool pagetool, Long SjId) {
		try {
			return this.eanswerpaperDAOImpl.findAnswerPaperBySjId(pagetool, SjId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public EAnswerpaper load(Long id) {
		try {
			return this.eanswerpaperDAOImpl.load(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public PageReturn findsypaper(PageTool pagetool, String sjMc, Date djsj,
			Date djsjend) {
		try {
			return this.eanswerpaperDAOImpl.findall(pagetool, sjMc, djsj, djsjend);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public PageReturn findAnswerPaper(PageTool pagetool, String sjMc) {
		try {
			return this.eanswerpaperDAOImpl.findAnswerPaper(pagetool, sjMc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public PageReturn findanswerpaperbypaper(PageTool pagetool, Long paperid,
			String groupId, String startScore, String endScore, String sex,
			String userstar, Date startctime, Date endctime) {
		try {
			return this.eanswerpaperDAOImpl.findanswerpaperbypaper(pagetool,
					paperid, groupId, startScore, endScore, sex, userstar,
					startctime, endctime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public double counttotal(Long paperid, String groupId, String startScore,
			String endScore, String sex, String userstar, Date startctime,
			Date endctime) {
		try {
			return this.eanswerpaperDAOImpl.counttotal(paperid, groupId,
					startScore, endScore, sex, userstar, startctime, endctime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Double(0);
	}

	public int countall(Long paperid, String groupId, String startScore,
			String endScore, String sex, String userstar, Date startctime,
			Date endctime) {
		try {
			return this.eanswerpaperDAOImpl.countall(paperid, groupId, startScore,
					endScore, sex, userstar, startctime, endctime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int countunpass(Long paperid, String groupId, String startScore,
			String endScore, String sex, String userstar, Date startctime,
			Date endctime) {
		try {
			return this.eanswerpaperDAOImpl.countunpass(paperid, groupId,
					startScore, endScore, sex, userstar, startctime, endctime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void saveEAnswerquentions(long paperId) {

		try {
			this.eanswerpaperDAOImpl.saveEAnswerquentions(paperId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public PageReturn findwrongPercent(PageTool pagetool, long paperId) {

		try {
			return this.eanswerpaperDAOImpl.findwrongPercent(pagetool, paperId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<WrongPersent_ws> getpaperXfState(long paperid){
		try {
			return this.eanswerpaperDAOImpl.getpaperXfState(paperid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveEAnswerresult(long paperId) {

		try {
			this.eanswerpaperDAOImpl.saveEAnswerresult(paperId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List getGroupsList() {
		try {
			return this.eanswerpaperDAOImpl.queryGroupList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List findAllanswerpaperbyUser(EAnswerpaper info) {
		try {
			return this.eanswerpaperDAOImpl.findAllanswerpaperbyUser(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List selectZhuHeAnswerpaper(String papers) {
		try {
			return this.eanswerpaperDAOImpl.selectZhuHeAnswerpaper(papers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 查询所有考试小组 */
	public List selectALLExamGroups() {
		try {
			return this.eanswerpaperDAOImpl.selectALLExamGroups();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 查询所有考试小组的id */
	public List selectALLExamGroupsIdsNames(String paperid) {
		try {
			return this.eanswerpaperDAOImpl.selectALLExamGroupsIdsNames(paperid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 设置未参加考试原因 */
	public void nopartinexamCause(EPapers epapers, String ryid, String cause) {
		try {
			this.eanswerpaperDAOImpl.nopartinexamCause(epapers, ryid, cause);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public EUser findEUser(String ryid) {
		try {
			return this.eanswerpaperDAOImpl.findEUser(ryid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List findCauseList() {
		try {
			return this.eanswerpaperDAOImpl.findCauseList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List queryEPapersByDate(Date startctime, Date endctime) {
		try {
			return this.eanswerpaperDAOImpl
					.queryEPapersByDate(startctime, endctime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List queryEAnswerPapersBySjIds(String sjids) {
		try {
			return this.eanswerpaperDAOImpl.queryEAnswerPapersBySjIds(sjids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List selectALLExamGroupsInformations(String paperid, String sjids) {
		try {
			return this.eanswerpaperDAOImpl.selectALLExamGroupsInformations(
					paperid, sjids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List getGroupsUserByGroupId(String groupId) {
		try {
			return this.eanswerpaperDAOImpl.getGroupsUserByGroupId(groupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List getStarList() {
		try {
			return this.eanswerpaperDAOImpl.getStarList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ELogMonitor updateSurplus(String paperid,String times){
		try {
			return this.eanswerpaperDAOImpl.updateSurplus(paperid, times);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void updateEanserPapersForZf(String paperid,double defen){
		try {
			this.eanswerpaperDAOImpl.updateEanserPapersForZf(paperid,defen);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 试卷错误率导出
	 * @param list
	 * @return
	 * @author gkk
	 * @date 2017-1-11 上午11:00:29
	 */
	public InputStream exportWronPercentExcel(List<Object[]> list){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle style = wb.createCellStyle();
		HSSFCellStyle style1 = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setColor(HSSFFont.COLOR_RED); //字体颜色
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		style1.setFont(font);
		HSSFSheet sh = wb.createSheet();
		HSSFRow row0 = sh.createRow(0);
		
		HSSFCell cell0 = row0.createCell(0);
        cell0.setCellValue("序号");
        cell0.setCellStyle(style);
        HSSFCell cell1 = row0.createCell(1);
        cell1.setCellValue("试题题目");
        cell1.setCellStyle(style);
        HSSFCell cell2 = row0.createCell(2);
        cell2.setCellValue("试题类型");
        cell2.setCellStyle(style);
        HSSFCell cell3 = row0.createCell(3);
        cell3.setCellValue("错误率");
        cell3.setCellStyle(style);
        sh.setColumnWidth(0, 6*256);
        sh.setColumnWidth(1, 30*256);
        sh.setColumnWidth(2, 12*256);
        sh.setColumnWidth(3, 12*256);
        
        int j = 0;
        for(int i = 1; i <= list.size(); i++){
        	Object[] o = list.get(j);
        	HSSFRow row = sh.createRow(i);
        	HSSFCell cell00 = row.createCell(0);
            cell00.setCellValue(j+1);
            cell00.setCellStyle(style);
            HSSFCell cell01 = row.createCell(1);
            cell01.setCellValue(o[0]==null?"":(String)o[0]);
            cell01.setCellStyle(style);
            HSSFCell cell02 = row.createCell(2);
            String s = "";
            switch (Integer.valueOf(String.valueOf(o[1]))) {
			case 2:
				s="单选题";
				break;
			case 3:
				s="判断题";
				break;
			case 8:
				s = "多选题";
				break;
			default:
				break;
			}
            cell02.setCellValue(s);
            cell02.setCellStyle(style);
            HSSFCell cell03 = row.createCell(3);
            cell03.setCellValue(o[2]==null?"":String.valueOf((Object)o[2])+"%");
            cell03.setCellStyle(style);
            j++;
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
		return is;
	}

	public InputStream exportExcel(List<Object[]> list, int all, int unpass,
			double percent, double average) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle style = wb.createCellStyle();
		HSSFCellStyle style1 = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setColor(HSSFFont.COLOR_RED); //字体颜色
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		style1.setFont(font);
		HSSFSheet sh = wb.createSheet();
		HSSFRow row0 = sh.createRow(0);
        HSSFCell cell0 = row0.createCell(0);
        cell0.setCellValue("排名");
        cell0.setCellStyle(style);
        HSSFCell cell1 = row0.createCell(1);
        cell1.setCellValue("试卷名称");
        cell1.setCellStyle(style);
        HSSFCell cell2 = row0.createCell(2);
        cell2.setCellValue("考生姓名");
        cell2.setCellStyle(style);
        HSSFCell cell3 = row0.createCell(3);
        cell3.setCellValue("准考证号");
        cell3.setCellStyle(style);
        HSSFCell cell4 = row0.createCell(4);
        cell4.setCellValue("身份证号");
        cell4.setCellStyle(style);
        HSSFCell cell5 = row0.createCell(5);
        cell5.setCellValue("开考时间");
        cell5.setCellStyle(style);
        HSSFCell cell6 = row0.createCell(6);
        cell6.setCellValue("结束时间");
        cell6.setCellStyle(style);
        HSSFCell cell7 = row0.createCell(7);
        cell7.setCellValue("得分");
        cell7.setCellStyle(style);
        HSSFCell cell8 = row0.createCell(8);
        cell8.setCellValue("状态");
        cell8.setCellStyle(style);
        sh.setColumnWidth(0, 6*256);
        sh.setColumnWidth(2, 10*256);
        sh.setColumnWidth(3, 18*256);
        sh.setColumnWidth(4, 22*256);
        sh.setColumnWidth(5, 20*256);
        sh.setColumnWidth(6, 20*256);
        sh.setColumnWidth(7, 7*256);
        sh.setColumnWidth(8, 8*256);
        int j = 0;
        int r1=0;
        for(int i = 1; i <= list.size(); i++){
        	Object[] o = list.get(j);
        	HSSFRow row = sh.createRow(i);
        	HSSFCell cell00 = row.createCell(0);
            cell00.setCellValue(j+1);
            cell00.setCellStyle(style);
            HSSFCell cell01 = row.createCell(1);
            String c1 = "";
            if(!"".equals((String)o[0])){
            	c1 = (String) o[0];
            	if(c1.length()>r1){
            		r1 = c1.length();
            	}
            }
            cell01.setCellValue(c1);
            cell01.setCellStyle(style);
            HSSFCell cell02 = row.createCell(2);
            cell02.setCellValue(o[1]==null?"":(String)o[1]);
            cell02.setCellStyle(style);
            HSSFCell cell03 = row.createCell(3);
            cell03.setCellValue(o[2]==null?"":(String)o[2]);
            cell03.setCellStyle(style);
            HSSFCell cell04 = row.createCell(4);
            cell04.setCellValue(o[3]==null?"":(String)o[3]);
            cell04.setCellStyle(style);
            HSSFCell cell05 = row.createCell(5);
            cell05.setCellValue(o[4]==null?"":(String)o[4]);
            cell05.setCellStyle(style);
            HSSFCell cell06 = row.createCell(6);
            cell06.setCellValue(o[5]==null?"":(String)o[5]);
            cell06.setCellStyle(style);
            HSSFCell cell07 = row.createCell(7);
            cell07.setCellValue(o[6]==null?"":String.valueOf((BigDecimal)o[6]));
            cell07.setCellStyle(style);
            HSSFCell cell08 = row.createCell(8);
            String state = "";
            if("0".equals((String)o[7])){
            	state = "正常";
            	cell08.setCellStyle(style);
            }else if("1".equals((String)o[7])){
            	state = "作弊";
            	cell08.setCellStyle(style1);//红色字体
            }else if ("2".equals((String)o[7])) {
				state = "缺考";
				cell08.setCellStyle(style);
			}
            cell08.setCellValue(state);
            j++;
        }
        int start2 = sh.getLastRowNum()+3;
        HSSFRow row1 = sh.createRow(start2);
        HSSFCell cell11 = row1.createCell(1);
        cell11.setCellValue("参考人数");
        cell11.setCellStyle(style);
        HSSFCell cell12 = row1.createCell(2);
        cell12.setCellValue("平均分");
        cell12.setCellStyle(style);
        HSSFCell cell13 = row1.createCell(3);
        cell13.setCellValue("不及格人数");
        cell13.setCellStyle(style);
        HSSFCell cell14 = row1.createCell(4);
        cell14.setCellValue("合格率");
        cell14.setCellStyle(style);
        HSSFRow row = sh.createRow(start2+1);
        HSSFCell cell000 = row.createCell(1);
        cell000.setCellValue(all);
        cell000.setCellStyle(style);
        HSSFCell cell001 = row.createCell(2);
        cell001.setCellValue(average);
        cell001.setCellStyle(style);
        HSSFCell cell002 = row.createCell(3);
        cell002.setCellValue(unpass);
        cell002.setCellStyle(style);
        HSSFCell cell003 = row.createCell(4);
        cell003.setCellValue(percent);
        cell003.setCellStyle(style);
        
        sh.setColumnWidth(1, r1*512);
        
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

	public List<Object> submitPaper(long djid, String userid,Connection conn) {
		return this.eanswerpaperDAOImpl.submitPaper(djid, userid,conn);
	}

	@Override
	public List<Object[]> getWrongPercentList(long paperid,int state) {
		return this.eanswerpaperDAOImpl.getWrongPercentList(paperid, state);
	}

	@Override
	public EPapers getPaperByid(long paperid) {
		return this.eanswerpaperDAOImpl.getPaperByid(paperid);
	}

}
