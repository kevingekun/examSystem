package com.wondersgroup.falcon.question_batch_add.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.question.beans.EImportanceService;
import com.wondersgroup.falcon.question.beans.EQuestiontypeService;
import com.wondersgroup.falcon.question.model.EBusinesstype;
import com.wondersgroup.falcon.question.model.EImportance;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.falcon.question.model.EQuestions_temp;
import com.wondersgroup.falcon.question.model.EQuestiontype;
import com.wondersgroup.falcon.question.model.Tmdot;
import com.wondersgroup.falcon.question_batch_add.service.BatchAddService;
import com.wondersgroup.kaoshi.bo.Admission_card_file;
import com.wondersgroup.kaoshi.bo.Admission_card_pc;
import com.wondersgroup.kaoshi.bo.Admission_card_user;
import com.wondersgroup.kaoshi.bo.Ae02;
import com.wondersgroup.kaoshi.bo.EPapersSet;
import com.wondersgroup.kaoshi.bo.E_Users_Temp;
import com.wondersgroup.kaoshi.bo.Tjobsubject;
import com.wondersgroup.kaoshi.util.AbstractAction;

@SuppressWarnings("serial")
public class BatchAddAction extends AbstractAction {
	private BatchAddService batchAddService;
	private String path;
	private File file_excel;
	private List<EQuestions_temp> list1;
	private List<EQuestions_temp> list2;
	private List<E_Users_Temp>  list_users;
	private List<E_Users_Temp>  list_error;
	private String id_job;
	private String rankname;
	private String batchNumber;
	private String sjid;
	private String jdmc;
	private String flag;//批量提交标识
	private String list_1_size;
	private String list_2_size;
	private String wrongMessage;
	
	private List<Admission_card_user> usersList;
	private String file_excelFileName;
	
	@Override
	public String execute(){
		return SUCCESS;
	}
	
	public String displayExcel(){
		InputStream inp;
		Workbook wb = null;
		try {
			inp = new FileInputStream(file_excel);
			wb = WorkbookFactory.create(inp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheetAt(0);
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	    Cell cell;
    	String s = "";
    	//String sequence = CommonHibernateDaoUtils.getSequenceValue("question_sequence");
    	long sequence = 0;
    	long state =1;
    	int num = 0;
    	Random random = new Random();
    	sequence = random.nextInt(999999);
    	Date date = new Date();
    	sequence = date.getTime()+sequence;
    	Tjobsubject tj = findTkByid_jobAndRankname();
    	String userName= ((UserDetailsImpl) AcegiUtil.getUserDetails()).getUser().getUsername();
	    for (int i = 1; i < rowCount+1; i++) {
	    	EQuestions_temp et = new EQuestions_temp();
	    	Row row = sheet.getRow(i);
	    	try {
	    		cell = row.getCell(0);
	    		s=(cell==null)?"":cell.getStringCellValue();
	    		state=(cell==null)?state*0:state*1;
	    		et.setEquestiontype(checkEQuestiontype(s));//试题类型
	    		
	    		cell = row.getCell(1);
		    	s=(cell==null)?"":cell.getStringCellValue();
	    		state=(cell==null)?state*0:state*1;
		    	et.setBxType(s);//重要程度
		    	
		    	cell = row.getCell(2);
		    	s=(cell==null)?"":cell.getStringCellValue();
	    		state=(cell==null)?state*0:state*1;
		    	et.setEimportance(checkImportance(s));//难易度
		    	
		    	cell = row.getCell(3);
		    	s=(cell==null)?"":cell.getStringCellValue();
		    	et.setStCc(s);//试题出处
		    	
		    	cell = row.getCell(4);
		    	s=(cell==null)?"":cell.getStringCellValue();
		    	et.setStSyryId(s);//专家
		    	
		    	cell = row.getCell(5);
		    	s=(cell==null)?"":String.valueOf((int)cell.getNumericCellValue());
		    	state=(cell==null)?state*0:state*1;
		    	et.setJdysId(Integer.parseInt(s));//关联鉴定要素时的要素id
	    		
	    		cell = row.getCell(6);
	    		s=(cell==null)?"":cell.getStringCellValue().trim();
	    		state=(cell==null)?state*0:state*1;
	    		et.setStTg(s);//题目
	    		
	    		cell = row.getCell(7);
		    	s=(cell==null)?"":cell.getStringCellValue().replace(" ", "");
	    		state=(cell==null)?state*0:state*1;
	    		String da = "";
	    		if(!"".equals(s)&&s.length()>1){
	    			for(int k=0;k<s.length();k++){
	    				da = da + s.substring(k, k+1)+"||";
	    			}
	    			da = da.substring(0, da.length()-2);
	    		}else{
	    			da = s;
	    		}
		    	et.setStDa(da.toUpperCase());//答案
		    	
			    String xx = "";
		    	if(et.getEquestiontype().getId()==3){
		    		xx = "正确||错误";
		    	}else{
		    		int m = 0;
		    		for(;m<8;m++){
			    		cell = row.getCell(8+m);
			    		if(cell==null){
			    			break;
			    		}
				    	xx = xx + cell.getStringCellValue().trim() + "||";
			    	}
		    		if(m>0){
		    			xx = xx.substring(0, xx.length()-2);
		    		}
		    	}
		    	state = ("".equals(xx))?state*0:state*1;
		    	et.setStXx(xx);//单选、多选可选项
		    	
		    	et.setStNodeName(String.valueOf(tj.getId()));
		    	et.setState(state);// 1表示正常，0表示有误
		    	state = 1;
		    	et.setRemark("");
		    	et.setBatchNumber(sequence);
		    	et.setStMtryId(userName);
		    	et.setStLrsj(new Date());
		    	et.setStCheck(0);
		    	batchAddService.save(et);
		    	
			} catch (Exception e) {
				e.printStackTrace();
				num++;
			}
	    	if(num>10){
				break;
			}
		}
	    list1 = batchAddService.findByStateAndBatchNumber(1, sequence);
	    list2 = batchAddService.findByStateAndBatchNumber(0, sequence);
	    list_1_size = String.valueOf(list1!=null?list1.size():0);
	    list_2_size = String.valueOf(list2!=null?list2.size():0);
	    getRequest().setAttribute("jobname", tj.getJobname());
	    getRequest().setAttribute("rankname", tj.getRankname());
	    getRequest().setAttribute("batchNumber", sequence);
	    return SUCCESS;
	}
	/**
	 * 准考证打印 考生信息导入
	 * @return
	 * @author gkk
	 * @date 2016-12-1 下午4:43:25
	 */
	public String displayExcel_printcard(){
		InputStream inp;
		Workbook wb = null;
		
		try {
			inp = new FileInputStream(file_excel);
			wb = WorkbookFactory.create(inp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Sheet sheet = wb.getSheetAt(0);
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		
		Admission_card_file acf = new Admission_card_file();
		System.out.println(file_excelFileName);
		String[] filename = file_excelFileName.split("\\.");
		acf.setName(filename[0]);
		acf.setTime(new Date());
		acf.setNum(rowCount);
		acf.setValid("1");
		
		batchAddService.savePrintCardFileInfo(acf);
		
		Integer fileId = acf.getId();
		if(fileId==null){
			return "file_save_error";
		}
		
	    Cell cell;
    	String s = "";
    	usersList = new ArrayList<Admission_card_user>();
    	for (int i = 1; i < rowCount+1; i++) {
    		Admission_card_user acu = new Admission_card_user();
    		Row row = sheet.getRow(i);
    		
    		cell = row.getCell(0);
    		s=(cell==null)?"":cell.getStringCellValue().trim();
    		acu.setZkh(s);//准考证号
    		
    		cell = row.getCell(1);
    		s=(cell==null)?"":cell.getStringCellValue().trim();
    		acu.setName(s);//姓名
    		
    		cell = row.getCell(2);
    		s=(cell==null)?"":cell.getStringCellValue().trim();
    		acu.setIdcard(s);//身份证号
    		
    		cell = row.getCell(3);
    		s=(cell==null)?"":cell.getStringCellValue().trim();
    		acu.setDw_name(s);//单位名称
    		
    		cell = row.getCell(4);
    		s=(cell==null)?"":cell.getStringCellValue().trim();
    		acu.setKc_name(s);//考场名称
    		
    		cell = row.getCell(5);
    		s=(cell==null)?"":cell.getStringCellValue().trim();
    		acu.setSeat_no(s);//座号
    		
    		acu.setFile_id(fileId);
    		acu.setValid("1");
    		usersList.add(acu);
    	}
    	boolean b = batchAddService.savePrintCardUsers(usersList);
    	if (b) {
			return "users_save_success";
		}else{
			return "users_save_error";
		}
	}
/*
 * 人工成绩导入
 */
	public String displayExcel_manual() throws ClassNotFoundException{
		InputStream inp;
		Workbook wb = null;
		
		try {
			inp = new FileInputStream(file_excel);
			wb = WorkbookFactory.create(inp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheetAt(0);
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	    Cell cell;
    	String password = "",realname="",username="";
    	long sequence = 0;
    	boolean flag=true,state=true;
    	Random random = new Random();
    	sequence = random.nextInt(999999);
    	String jdid = batchAddService.getjdid(jdmc);
    	if(jdid==""){
    		wrongMessage="鉴定批次名称重复，请核对后重新操作";
    		return "error1";
    	}
    	else{
    	list_error = new ArrayList<E_Users_Temp>();
    	List<Object> list = new ArrayList<Object>();
	    for (int i = 1; i < rowCount+1; i++) {
	    	boolean f = true;
	    	E_Users_Temp users = new E_Users_Temp();
	    	E_Users_Temp error = new E_Users_Temp();
	    	Row row = sheet.getRow(i);
	    	try {
	    		if(row.getCell(0)!=null){
	    	          row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
	    	          password=(row.getCell(0).getStringCellValue().trim());
	    	          if("".equals(password)||f==false){
	    	        	 f = false&f; 
	    	          }
	    	     }
	    		else{
	    	    	 f = false&f; 
	    	    	 password="";
	    	     }
	    		if(row.getCell(1)!=null){
	    	          row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
	    	          realname=(row.getCell(1).getStringCellValue().trim());
	    	          if("".equals(realname)||f==false){
		    	        	 f = false&f; 
		    	          }
	    	     }
	    		else{
	    			 f = false&f; 
	    			 realname="";
	    		}
	    		if(row.getCell(2)!=null){
	    	          row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
	    	          username=(row.getCell(2).getStringCellValue().trim());
	    	          if("".equals(username)||f==false){
		    	        	 f = false&f;
		    	          }
	    	         
	    	     }
	    		else{
	    			f=false&f;
	    			username="";
	    		} 
	    	    	 
	    		if(f==true){
	    			users.setPassword(password);
	    			users.setRealname(realname);
	    			users.setUsername(username);
	    			users.setFlag(1);
	    			users.setJd_id(Long.valueOf(jdid));
	    			Date time = new Date();
	    			users.setImportdate(time);//导入时间
	    			flag=true&flag;
	    			batchAddService.saveEuserstemp(users);
		    	}
	    		else if(f==false){
	    			error.setPassword(password);
	    			error.setRealname(realname);
	    			error.setUsername(username);
	    			list_error.add(error);
		    	}
			} catch (Exception e) {
				e.printStackTrace();
				flag=false&flag;
			}
		}
	    if(flag==true)
	    {
	    	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getRequest().getSession().getServletContext()); 
			DataSource dataSource = (DataSource) ctx.getBean("dataSource");
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	list = this.batchAddService.excuteProc(jdid,conn);
	  	    
	    }
	    if("0".equals(String.valueOf(list.get(0)))&&flag==true){
	    	list_users =batchAddService.getusers(jdid);	
		    return "success_manual";
	    }
	   /* if(state==true&&flag==true){
	    list_users =batchAddService.getusers(jdid);	
	    return "success_manual";
	    }*/
	    else
	    	 return "error";
	}
    	}
	
	public String displayExcel_cmp(){
		InputStream inp;
		Workbook wb = null;
		try {
			inp = new FileInputStream(file_excel);
			wb = WorkbookFactory.create(inp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheetAt(0);
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	    Cell cell;
    	String s = "";
    	long sequence = 0;
    	long state =1;
    	int num = 0;
    	sequence = new Date().getTime();
    	EPapersSet eps = batchAddService.getById(sjid);
    	String userName= ((UserDetailsImpl) AcegiUtil.getUserDetails()).getUser().getUsername();
	    for (int i = 1; i < rowCount+1; i++) {
	    	EQuestions_temp et = new EQuestions_temp();
	    	Row row = sheet.getRow(i);
	    	try {
	    		
	    		cell = row.getCell(0);
		    	s=(cell==null)?"":cell.getStringCellValue();
	    		state=(cell==null)?state*0:state*1;
		    	et.setEquestiontype(checkEQuestiontype(s));//试题类型
		    	
		    	cell = row.getCell(1);
		    	s=(cell==null)?"":cell.getStringCellValue();
		    	et.setStCc(s);//试题出处
		    	
		    	cell = row.getCell(2);
		    	s=(cell==null)?"":cell.getStringCellValue();
		    	et.setStSyryId(s);//专家
		    	
		    	cell = row.getCell(3);
		    	s=(cell==null)?"":String.valueOf((int)cell.getNumericCellValue());
		    	state=(cell==null)?state*0:state*1;
		    	et.setStFz(s);//分值
	    		
	    		cell = row.getCell(4);
	    		s=(cell==null)?"":cell.getStringCellValue().trim();
	    		state=(cell==null)?state*0:state*1;
	    		if(s==null||"".equals(s)){
	    			break;
	    		}
	    		et.setStTg(s);//题目
	    		
	    		cell = row.getCell(5);
		    	s=(cell==null)?"":cell.getStringCellValue().replace(" ", "");
	    		state=(cell==null)?state*0:state*1;
	    		String da = "";
	    		if(!"".equals(s)&&s.length()>1){
	    			for(int k=0;k<s.length();k++){
	    				da = da + s.substring(k, k+1)+"||";
	    			}
	    			da = da.substring(0, da.length()-2);
	    		}else{
	    			da = s;
	    		}
		    	et.setStDa(da.toUpperCase());//答案
		    	
			    String xx = "";
		    	if(et.getEquestiontype().getId()==3){
		    		xx = "正确||错误";
		    	}else{
		    		int m = 0;
		    		for(;m<8;m++){
			    		cell = row.getCell(6+m);
			    		String scell = (cell==null)?"":cell.getStringCellValue().trim();
			    		if("".equals(scell)){
			    			break;
			    		}
				    	xx = xx + cell.getStringCellValue().trim() + "||";
			    	}
		    		if(m>0){
		    			xx = xx.substring(0, xx.length()-2);
		    		}
		    	}
		    	state = ("".equals(xx))?state*0:state*1;
		    	et.setStXx(xx);//单选、多选可选项
		    	
		    	/*cell = row.getCell(4);
		    	s=(cell==null)?"":cell.getStringCellValue();
	    		state=(cell==null)?state*0:state*1;
		    	et.setBxType(s);//重要程度
		    	
		    	cell = row.getCell(5);
		    	s=(cell==null)?"":cell.getStringCellValue();
	    		state=(cell==null)?state*0:state*1;
		    	et.setEimportance(checkImportance(s));//难易度
*/		    	
		    	
		    	et.setStNodeName(eps.getSj_tkid());
		    	et.setStSjid(sjid);
		    	et.setState(state);// 1表示正常，0表示有误
		    	state = 1;
		    	et.setRemark("");
		    	et.setBatchNumber(sequence);
		    	et.setStMtryId(userName);
		    	et.setStLrsj(new Date());
		    	et.setStCheck(0);
		    	batchAddService.save(et);
		    	
			} catch (Exception e) {
				e.printStackTrace();
				num++;
			}
	    	if(num>10){
				break;
			}
		}
	    list1 = batchAddService.findByStateAndBatchNumber(1, sequence);
	    list2 = batchAddService.findByStateAndBatchNumber(0, sequence);
	    list_1_size = String.valueOf(list1!=null?list1.size():0);
	    list_2_size = String.valueOf(list2!=null?list2.size():0);
	    /*getRequest().setAttribute("jobname", tj.getJobname());
	    getRequest().setAttribute("rankname", tj.getRankname());*/
	    getRequest().setAttribute("batchNumber", sequence);
	    return "success_cmp";
	}
	
	@SuppressWarnings("static-access")
	public void batchSubmit(){
		HttpServletResponse response = ServletActionContext.getResponse();
		long sequence = Long.parseLong(batchNumber);
		list1 = batchAddService.findByStateAndBatchNumber(1, sequence);
		Iterator<EQuestions_temp> it = list1.iterator();
		if("batchSet".equals(flag)){//整套试题导入
			int count = batchAddService.checkQuestions(sjid);
			if(count+list1.size()>100){
				try {
					response.getWriter().write("试卷试题总数量超过100！");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		AcegiUtil acegiUtil = new AcegiUtil();
    	String userName= ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser().getUsername();
    	long userId = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser().getId();
    	Ae02 ae02 = new Ae02();
		ae02.setAaa121("试题录入");//业务类型
		ae02.setCae219("1");//业务办理渠道
		ae02.setCae220("010");//业务办理状态
		ae02.setCae251(String.valueOf(userId));//登记经办人id
		ae02.setCae249(userName);//经办人姓名
		ae02.setAae016("0");//终审状态
		ae02.setAae217(new Date());//办理日起
		
		String[] ignore = { "stId", "state", "batchNumber", "remark", "jdysId" };
		while (it.hasNext()) {
			Tmdot tmdot = new Tmdot();
			EQuestions eQuestions = new EQuestions();
			EQuestions_temp eqt = it.next();
			BeanUtils.copyProperties(eqt, eQuestions, ignore);
			batchAddService.saveAe02(ae02);
			EBusinesstype eBusinesstype = new EBusinesstype();
			eBusinesstype.setId(ae02.getAaz002());
			eQuestions.setEbusinesstype(eBusinesstype);
			
			if("batchSet".equals(flag)){//整套试题导入
				EImportanceService eImportanceService = new EImportanceService();
				eQuestions.setEimportance(eImportanceService
						.findEImportanceById(new Long(1)));
			}
			
			batchAddService.saveEquestions(eQuestions);
			
			// 与鉴定要素关联
			long jdysid = eqt.getJdysId();
			if(jdysid!=0){
				tmdot.setJdysid(jdysid);
				tmdot.setSt_id(eQuestions.getStId());
				batchAddService.saveTmdot(tmdot);
			}
		}
		
		try {
			response.getWriter().write("导入成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static EQuestiontype checkEQuestiontype(String s){
		//单选题,多选题,判断题,填空题,问答题,计算题,论述题,绘图题
		int i = 0;
		EQuestiontypeService eQuestiontypeService = new EQuestiontypeService();
		if("单选题".equals(s)){
			i = 2;
		}else if("多选题".equals(s)){
			i = 8;
		}else if("判断题".equals(s)){
			i = 3;
		}else if("填空题".equals(s)){
			i = 1;
		}else if("问答题".equals(s)){
			i = 4;
		}else if("计算题".equals(s)){
			i = 5;
		}else if("论述题".equals(s)){
			i = 6;
		}else if("绘图题".equals(s)){
			i = 7;
		}
		return eQuestiontypeService.findEQuestiontypeById(new Long(i));
	}
	
	private static EImportance checkImportance(String s){
		int i = 0;
		EImportanceService eImportanceService = new EImportanceService();
		if("很简单".equals(s)){
			i = 1;
		}else if("简单".equals(s)){
			i = 2;
		}else if("难".equals(s)){
			i = 3;
		}else if("一般难".equals(s)){
			i = 4;
		}else if("困难".equals(s)){
			i = 5;
		}
		return eImportanceService.findEImportanceById(new Long(i));
	}
	/**
	 * 检查选项是否符合要求
	 * @param s
	 * @return
	 */
	private static boolean checkOptions(String s){
		//String regEx = "([u4e00-u9fa5]|(w+))+||([u4e00-u9fa5]|(w+))+";
		//^([\u4e00-\u9fa5]|(\w+))+([\u4e00-\u9fa5]|(\w+)|(\|\|))+([\u4e00-\u9fa5]|(\w+))+ 匹配汉字字母数字
		String regEx = "^[^\\|\\|]([\\s\\S]*)[\\^\\|\\|]";//匹配所有
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}
	
	private Tjobsubject findTkByid_jobAndRankname(){
		return batchAddService.findTkByid_jobAndRankname(id_job, rankname);
	}
	
	public void saveQuestions(){
		
	}
	
	public void setBatchAddService(BatchAddService batchAddService) {
		this.batchAddService = batchAddService;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public File getFile_excel() {
		return file_excel;
	}

	public void setFile_excel(File file_excel) {
		this.file_excel = file_excel;
	}

	public List<EQuestions_temp> getList1() {
		return list1;
	}

	public void setList1(List<EQuestions_temp> list1) {
		this.list1 = list1;
	}

	public List<EQuestions_temp> getList2() {
		return list2;
	}

	public void setList2(List<EQuestions_temp> list2) {
		this.list2 = list2;
	}

	public String getId_job() {
		return id_job;
	}

	public void setId_job(String id_job) {
		this.id_job = id_job;
	}

	public String getRankname() {
		return rankname;
	}

	public void setRankname(String rankname) {
		this.rankname = rankname;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getSjid() {
		return sjid;
	}

	public void setSjid(String sjid) {
		this.sjid = sjid;
	}

	public String getJdmc() {
		return jdmc;
	}

	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
	}

	public List<E_Users_Temp> getList_users() {
		return list_users;
	}

	public void setList_users(List<E_Users_Temp> list_users) {
		this.list_users = list_users;
	}

	public List<E_Users_Temp> getList_error() {
		return list_error;
	}

	public void setList_error(List<E_Users_Temp> list_error) {
		this.list_error = list_error;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getList_1_size() {
		return list_1_size;
	}

	public void setList_1_size(String list_1_size) {
		this.list_1_size = list_1_size;
	}

	public String getList_2_size() {
		return list_2_size;
	}

	public void setList_2_size(String list_2_size) {
		this.list_2_size = list_2_size;
	}

	public String getWrongMessage() {
		return wrongMessage;
	}

	public void setWrongMessage(String wrongMessage) {
		this.wrongMessage = wrongMessage;
	}

	public List<Admission_card_user> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Admission_card_user> usersList) {
		this.usersList = usersList;
	}

	public String getFile_excelFileName() {
		return file_excelFileName;
	}

	public void setFile_excelFileName(String file_excelFileName) {
		this.file_excelFileName = file_excelFileName;
	}

	
}
