package com.wondersgroup.falcon.question_batch_add.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.falcon.question.model.EQuestions_temp;
import com.wondersgroup.falcon.question.model.Tmdot;
import com.wondersgroup.falcon.question_batch_add.dao.BatchAddDAO;
import com.wondersgroup.falcon.question_batch_add.service.BatchAddService;
import com.wondersgroup.kaoshi.bo.Admission_card_file;
import com.wondersgroup.kaoshi.bo.Admission_card_user;
import com.wondersgroup.kaoshi.bo.Ae02;
import com.wondersgroup.kaoshi.bo.EPapersSet;
import com.wondersgroup.kaoshi.bo.E_Users_Temp;
import com.wondersgroup.kaoshi.bo.Tdjobexamdot;
import com.wondersgroup.kaoshi.bo.Tjobsubject;

public class BatchAddServiceImpl implements BatchAddService{

	private BatchAddDAO batchAddDAO;

	public void save(EQuestions_temp e){
		try {
			batchAddDAO.save(e);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public void saveEuserstemp(E_Users_Temp users){
		try {
			batchAddDAO.saveEuserstemp(users);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void saveEquestions(EQuestions q){
		try {
			batchAddDAO.saveEquestions(q);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveTmdot(Tmdot tmdot){
		try {
			batchAddDAO.saveTmdot(tmdot);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void saveAe02(Ae02 ae02){
		try {
			batchAddDAO.saveAe02(ae02);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<EQuestions_temp> findByStateAndBatchNumber(long state,long bcn){
		try {
			return batchAddDAO.findByStateAndBatchNumber(state, bcn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getjdid(String jdmc){
		try {
			return batchAddDAO.getjdid(jdmc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Tjobsubject findTkByid_jobAndRankname(String id_job, String rankname){
		try {
			return batchAddDAO.findTkByid_jobAndRankname(id_job, rankname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Tdjobexamdot> findJdysBygzid(String gzid){
		try {
			return batchAddDAO.findJdysBygzid(gzid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<E_Users_Temp> getusers(String jdid){
		try {
			return batchAddDAO.getusers(jdid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public InputStream exportJdysInfo(List<Tdjobexamdot> list){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
		HSSFSheet sh = wb.createSheet();
		HSSFRow row0 = sh.createRow(0);
        HSSFCell cell0 = row0.createCell(0);
        cell0.setCellValue("序号");
        cell0.setCellStyle(style);
        HSSFCell cell1 = row0.createCell(1);
        cell1.setCellValue("行为领域");
        cell1.setCellStyle(style);
        HSSFCell cell2 = row0.createCell(2);
        cell2.setCellValue("鉴定范围");
        cell2.setCellStyle(style);
        HSSFCell cell3 = row0.createCell(3);
        cell3.setCellValue("鉴定点");
        cell3.setCellStyle(style);
        HSSFCell cell4 = row0.createCell(4);
        cell4.setCellValue("鉴定编号");
        cell4.setCellStyle(style);
        
        sh.setColumnWidth(0, 6*256);
        sh.setColumnWidth(1, 12*256);
        sh.setColumnWidth(2, 12*256);
        sh.setColumnWidth(4, 8*256);
        
        int r3=0;
        for(int i=0;i<list.size();i++){
        	Tdjobexamdot tdj = list.get(i);
        	HSSFRow row = sh.createRow(i+1);
        	HSSFCell cell00 = row.createCell(0);
            cell00.setCellValue(i+1);
            cell00.setCellStyle(style);
            HSSFCell cell01 = row.createCell(1);
            cell01.setCellValue(tdj.getL1name()==null?"":tdj.getL1name());
            cell01.setCellStyle(style);
            HSSFCell cell02 = row.createCell(2);
            cell02.setCellValue(tdj.getL2name()==null?"":tdj.getL2name());
            cell02.setCellStyle(style);
            HSSFCell cell03 = row.createCell(3);
            String c3 = "";
            if(!"".equals(tdj.getDotname())){
            	c3 = tdj.getDotname();
            	if(c3.length()>r3){
            		r3 = c3.length();
            	}
            }
            cell03.setCellValue(tdj.getDotname()==null?"":tdj.getDotname());
            HSSFCell cell04 = row.createCell(4);
            cell04.setCellValue(tdj.getJdysid());
            cell04.setCellStyle(style);
        }
        sh.setColumnWidth(3, r3*512);
        
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
	
	public void setBatchAddDAO(BatchAddDAO batchAddDAO) {
		this.batchAddDAO = batchAddDAO;
	}

	public int checkQuestions(String sjid) {
		return batchAddDAO.checkQuestions(sjid);
	}
	public List<Object> excuteProc(String jdid,Connection conn) {
		return batchAddDAO.excuteProc(jdid,conn);
	}
	@Override
	public EPapersSet getById(String sjid) {
		return batchAddDAO.getById(sjid);
	}
	@Override
	public void savePrintCardFileInfo(Admission_card_file acf) {
		batchAddDAO.savePrintCardFileInfo(acf);
	}
	@Override
	public boolean savePrintCardUsers(List<Admission_card_user> users) {
		return batchAddDAO.savePrintCardUsers(users);
	}
	@Override
	public boolean getUserByPass(String password) {
		return batchAddDAO.getUserByPass(password);
	}
	
	
}
