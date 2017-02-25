package com.wondersgroup.kaoshi.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;

import com.wondersgroup.falcon.paper.model.EPaperquestions;
import com.wondersgroup.falcon.paper.model.EPaperquestions_temp;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.kaoshi.action.SingletonFreemarker;
import com.wondersgroup.kaoshi.bo.EKaoshi;
import com.wondersgroup.kaoshi.bo.UploadToYth;
import com.wondersgroup.kaoshi.dao.EPapersDAOImpl;
import com.wondersgroup.kaoshi.dao.TEXTEPapersDAO;
import com.wondersgroup.kaoshi.service.EPapersService;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.ELogMonitor;
import com.wondersgroup.popedom.bo.EUser;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class EPaperserviceImpl implements EPapersService{
	private TEXTEPapersDAO epapersDAO;
	private EPapersDAOImpl epapersDAOImpl =new EPapersDAOImpl();


	public List getPapers(){
		try {
			return this.epapersDAO.getPapers();
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	public PageReturn getPapers(PageTool pageInfo){
		try {
			return this.epapersDAO.getPapers(pageInfo);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}



	public void setEpapersDAO(TEXTEPapersDAO epapersDAO) throws Exception{
		this.epapersDAO = epapersDAO;
	}
	/**
	 * 修改分数
	 */
	public boolean changeGrade(String id, String userid, String grade) {
		return this.epapersDAOImpl.changeGrade(id, userid, grade);
	}
	
	public UploadToYth uploadToYth(String examId,String sjMc,String name) {
		UploadToYth uploadToYth = new UploadToYth(examId, sjMc, name);
		return this.epapersDAOImpl.uploadToYth(uploadToYth);
	}
	public String uploadToYthByWs(String examId,String sjMc,String name) {
		UploadToYth uploadToYth = new UploadToYth(examId, sjMc, name);
		return this.epapersDAOImpl.uploadToYthByWs(uploadToYth);
	}
	/**
	 * 上传成绩到鉴定中心（webservice）
	 */
	public String uploadToJdzxByWs(String examId,String sjMc,String name) {
		UploadToYth uploadToYth = new UploadToYth(examId, sjMc, name);
		return this.epapersDAOImpl.uploadToJdzxByWs(uploadToYth);
	}
	/**
	 * 
	 * <p>Description:���id�����Ծ� </p>
	 * 
	 * Created by [www] [Aug 10, 2009]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param paperid
	 * @return
	 */
	public EPapers getEPapersById(String paperid){
		try {
			return this.epapersDAOImpl.load(paperid);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	
	
	public EPaperquestions_temp findOneQuestion(int sequence,long equestions_type,String ryid){
		try {
			return this.epapersDAOImpl.findOneQuestion(sequence, equestions_type,ryid);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	
	public EPaperquestions_temp findUnfinish(int sequence,String ryid){
		try {
			return this.epapersDAOImpl.findUndone(sequence, ryid);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	
	public int findQuestionsBytypeDone(int sequence,long equestions_type,String ryid){
		try {
			return this.epapersDAOImpl.findQuestionsBytypeDone(sequence, equestions_type, ryid);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return 0;
	}
	
	public int findQuestionsTotal(String ryid){
		try {
			return this.epapersDAOImpl.findQuestionsTotal(ryid);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return 0;
	}
	
	public int findQuestionsDone(String ryid){
		try {
			return this.epapersDAOImpl.findQuestionsDone(ryid);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return 0;
	}
	
	public int findQuestionsBytypeTotal(int sequence,long equestions_type,String ryid){
		try {
			return this.epapersDAOImpl.findQuestionsBytypeTotal(sequence, equestions_type, ryid);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return 0;
	}
	
	public EPaperquestions_temp loadePaperquestions_temp(String epqtId){
		try {
			return this.epapersDAOImpl.loadePaperquestions_temp(epqtId);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void saveEpaperquestions_temp(EPaperquestions_temp epqt){
		try {
			this.epapersDAOImpl.saveEpaperquestions_temp(epqt);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
	}
	
	
	public void saveEpaperquestions_tempAll(String paperId,String ryid){
		try {
			this.epapersDAOImpl.saveEpaperquestions_tempAll(paperId, ryid);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
	}
	
	
	public List<EPaperquestions_temp> findtype(String paperId,long equestions_type,String ryid){
		try {
			return this.epapersDAOImpl.findByType(paperId, equestions_type, ryid);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * <p>Description:���id�����Ծ� </p>
	 * 
	 * Created by [www] [Aug 10, 2009]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param paperid
	 * @return
	 */
	public void saveEpaperquestions_temp(String paperId,String ryid){
		 try {
			this.epapersDAOImpl.saveEpaperquestions_temp(paperId,ryid);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * <p>Description:���Ժ�����״̬ </p>
	 * 
	 * Created by [hch] [TURS 3, 2010]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param 
	 * @return
	 */
	
	
	
	/**
	 * <p>Description:�����Ծ� </p>
	 * 
	 * Created by [www] [Aug 14, 2009]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param epapers
	 */
	public void addEpaper(EPapers epapers){
		try {
			this.epapersDAOImpl.savePaper(epapers);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
	}
	
	public void addEKaoshi(EKaoshi eKaoshi){
		try {
			this.epapersDAOImpl.addEKaoshi(eKaoshi);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
	}
	
	public void delete(){
		try {
			this.epapersDAOImpl.delete();
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
	}
	
	public EPapersDAOImpl getEpapersDAOImpl() {
		return epapersDAOImpl;
	}
	public void setEpapersDAOImpl(EPapersDAOImpl epapersDAOImpl) {
		this.epapersDAOImpl = epapersDAOImpl;
	}
	
	public Map<Long, Long> updateState(String paperid,String paperState) throws Exception{
		Map<Long, Long> map = this.epapersDAOImpl.updateState(paperid, paperState);
		return map;
	}
	public void calculateScore(Map<Long, Long> map,ApplicationContext ac){
		this.epapersDAOImpl.calculateScore(map, ac);
	}
	
	public void updatecheckpaper(String paperid,String paperState,String advice,String rymc){
		try {
			this.epapersDAOImpl.updatecheckpaper(paperid, paperState, advice,rymc);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
	}
	
	/**
	 * �ҵ����п��Կ��Ե��Ծ�
	 */
	public PageReturn findpaperbyCanexam(PageTool pagetool,String sjMc,Long sjZt){
		try {
			return this.epapersDAOImpl.findpaperbyCanexam(pagetool,sjMc,sjZt);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * �ҵ����п��Կ��Ե��Ծ�
	 */
	public PageReturn findpaperbyCanceping(PageTool pagetool,String sjMc,Long sjZt){
		try {
			return this.epapersDAOImpl.findpaperbyCanceping(pagetool,sjMc,sjZt);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ����Ծ�״̬�����Ծ�
	 */
	public PageReturn findpaperbySjzt(PageTool pagetool,Long sjzt,EPapers epapers,Date begin,Date zjsj,String flag){
		try {
			return this.epapersDAOImpl.findpaperbysjzt(pagetool, sjzt,epapers,begin,zjsj,flag);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	
	public PageReturn findgrade(PageTool pagetool, Long sjzt, EPapers epapers,
			EUser eUser, Date begin, String flag,String pcid) {
		try {
			return this.epapersDAOImpl.findgrade(pagetool, sjzt, epapers, eUser, begin, flag,pcid);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	public PageReturn gradeSearch(PageTool pagetool, Long sjzt, EPapers epapers,
			EUser eUser, Date begin, String flag,String pcid) {
		try {
			return this.epapersDAOImpl.gradeSearch(pagetool, sjzt, epapers, eUser, begin, flag,pcid);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	public PageReturn findGradeForUploadToJdzx(PageTool pagetool, Long sjzt, EPapers epapers,
			EUser eUser, Date begin, String flag) {
		try {
			return this.epapersDAOImpl.findGradeForUploadToJdzx(pagetool, sjzt, epapers, eUser, begin, flag);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	public EKaoshi findbyryid(){
		try {
			return this.epapersDAOImpl.findbyryid();
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	public EUser queryUserById(String toUserId) {
		try {
			return this.epapersDAOImpl.queryUserById(toUserId);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	public void updateUserIP(String userIp,Long examid){
		try {
			this.epapersDAOImpl.updateUserIP(userIp,examid);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
	}
	public List<String> getGz(String gzid,String gzdj){
		return this.epapersDAOImpl.getGz(gzid, gzdj);
	}
	
	public Object[] getFs(long i,String sjid){
		return this.epapersDAOImpl.getFs(i,sjid);
	}
	
	public void updateUserStratDt(String paperId){
		try {
			this.epapersDAOImpl.updateUserStratDt(paperId);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
	}

	public List<Object> getSurplus(String paperid){
		try {
			return this.epapersDAOImpl.getSurplus(paperid);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 更新监控结束时间及考试状
	 */
	public void updateLogMonitore(String paperid,String userid){
		 try {
			this.epapersDAOImpl.updateLogMonitore(paperid, userid);
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
	}
	/**
	 * 构造只有答案的的导出 数据
	 * @param sjmc
	 * @param epqs
	 * @return
	 */
	public Map<String, Object> getAnswerOfPaper(String sjmc,List<EPaperquestions> epqs){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sjmc", sjmc);
		List<String> single_list = new ArrayList<String>();//单选
		List<String> verdict_list = new ArrayList<String>();//判断
		List<String> many_list = new ArrayList<String>();//多选
		Iterator<EPaperquestions> it = epqs.iterator();
		StringBuffer single_sb = new StringBuffer();
		StringBuffer verdict_sb = new StringBuffer();
		StringBuffer many_sb = new StringBuffer();
		int single_count = 1;
		int verdict_count = 1;
		int many_count = 1;
		while(it.hasNext()){
			EPaperquestions eps = it.next();
			EQuestions eqs = eps.getEquestions();
			if(eqs.getEquestiontype().getPriority()==2){//单选
				if(single_count<=5){
					single_sb.append(eqs.getStDa());
					single_count++;
				}else{
					single_list.add(single_sb.toString());
					single_sb.setLength(0);
					single_sb.append(eqs.getStDa());
					single_count = 2;
				}
			}else if(eqs.getEquestiontype().getPriority()==3){//判断
				if(verdict_count<=5){
					verdict_sb.append(eqs.getStDa());
					verdict_count++;
				}else{
					verdict_list.add(verdict_sb.toString());
					verdict_sb.setLength(0);
					verdict_sb.append(eqs.getStDa());
					verdict_count = 2;
				}
			}else if(eqs.getEquestiontype().getPriority()==8){//多选
				String da = eqs.getStDa();
				String da2 = da.replace("||", "");
				if(many_count<=5){
					many_sb.append(da2+"  ");
					many_count++;
				}else{
					many_list.add(many_sb.toString());
					many_sb.setLength(0);
					many_sb.append(da2+"  ");
					many_count = 2;
				}
			}
		}
		if(single_sb.length()>1){
			single_list.add(single_sb.toString());
		}
		if(verdict_sb.length()>1){
			verdict_list.add(verdict_sb.toString());
		}
		if(many_sb.length()>1){
			many_list.add(many_sb.toString());
		}
		map.put("single", single_list);
		map.put("verdict", verdict_list);
		map.put("many", many_list);
		return map;
	}
	
	
	/**
	 * 构造只有答案的的导出 数据  新版
	 * @param sjmc
	 * @param epqs
	 * @return
	 */
	public Map<String, Object> getAnswerOfPaper_New(String sjmc,List<EPaperquestions> epqs){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sjmc", sjmc);
		List<String> single_list = new ArrayList<String>();//单选
		List<String> verdict_list = new ArrayList<String>();//判断
		List<String> many_list = new ArrayList<String>();//多选
		List<String> fill_list = new ArrayList<String>();//填空
		List<String> ask_list = new ArrayList<String>();//问答
		List<String> calculate_list = new ArrayList<String>();//计算
		List<String> discuss_list = new ArrayList<String>();//论述
		List<String> draw_list = new ArrayList<String>();//画图
		Iterator<EPaperquestions> it = epqs.iterator();
		
		while(it.hasNext()){
			EPaperquestions eps = it.next();
			EQuestions eqs = eps.getEquestions();
			String st = eqs.getStDa();
			if(st==null)
			{
				st="  ";
			}
			if(eqs.getEquestiontype().getPriority()==1){
				st = st.trim();
				fill_list.add(st);
			}
			else if(eqs.getEquestiontype().getPriority()==2){
				st = st.trim();
				single_list.add(st);
			}
			else if(eqs.getEquestiontype().getPriority()==3){
				st = st.trim();
				verdict_list.add(st);
			}
			else if(eqs.getEquestiontype().getPriority()==4){
				st = st.trim();
				ask_list.add(st);
			}
			else if(eqs.getEquestiontype().getPriority()==5){
				st = st.trim();
				calculate_list.add(st);
			}
			else if(eqs.getEquestiontype().getPriority()==6){
				st = st.trim();
				discuss_list.add(st);
			}
			else if(eqs.getEquestiontype().getPriority()==7){
				st = st.trim();
				draw_list.add(st);
				
			}
			else if(eqs.getEquestiontype().getPriority()==8){
				st = st.trim();
				many_list.add(st);
			}
		}
		
		map.put("single", single_list);
		map.put("verdict", verdict_list);
		map.put("many", many_list);
		map.put("fill", fill_list);
		map.put("ask", ask_list);
		map.put("discuss", discuss_list);
		map.put("draw", draw_list);
		map.put("calculate", calculate_list);
		return map;
	}
	
	/**
	 * 构造标准word试题 数据
	 */
	public Map<String, Object> getInfoOfWord(String gzid, String gzdj, 
			List<EPaperquestions> leps) {
		Map<String, Object> map = new HashMap<String, Object>();
		String mc = "";
		String dj = "";
		List<String> list = getGz(gzid,gzdj);
		if(list!=null){
			mc = list.get(0);
			dj = list.get(1);
			map.put("gzmc", mc);
			map.put("gzdj", dj);
		}
		List<Map<String, List<Object>>> single_list =  new ArrayList<Map<String, List<Object>>>();//单选
		List<String> verdict_list =  new ArrayList<String>();//判断
		List<Map<String, List<Object>>> many_list =  new ArrayList<Map<String, List<Object>>>();//多选
		String[] temp = new String[]{"A","B","C","D","E","F","G","H"};
		Iterator<EPaperquestions> it = leps.iterator();
		while(it.hasNext()){
			Map<String, List<Object>> map2 = new HashMap<String, List<Object>>();
			EPaperquestions eps = it.next();
			EQuestions eqs = eps.getEquestions();
			if(eqs.getEquestiontype().getPriority()==2){//单选
				String st = eqs.getStTg();
				st = st.trim();
				List<Object> list_d = new ArrayList<Object>();
				String da = eqs.getStXx();
				if(!"".equals(da)&&da!=null){
					String[] xx =  da.split("\\|\\|");
					for(int i=0;i<xx.length;i++){
						list_d.add(temp[i]+"、"+xx[i].trim());
					}
				}
				map2.put(st, list_d);
				single_list.add(map2);
			}else if(eqs.getEquestiontype().getPriority()==3){//判断
				String st = eqs.getStTg();
				st = st.trim();
				verdict_list.add(st);
			}else if(eqs.getEquestiontype().getPriority()==8){//多选
				String st = eqs.getStTg();
				st = st.trim();
				List<Object> list_d = new ArrayList<Object>();
				String da = eqs.getStXx();
				if(!"".equals(da)&&da!=null){
					String[] xx =  da.split("\\|\\|");
					for(int i=0;i<xx.length;i++){
						list_d.add(temp[i]+"、"+xx[i].trim());
					}
				}
				map2.put(st, list_d);
				many_list.add(map2);
			}
		}
		map.put("single", single_list);
		map.put("verdict", verdict_list);
		map.put("many", many_list);
		return map;
	}
	
	/**
	 * 构造标准word试题 数据  NEW
	 */
	public Map<String, Object> getInfoOfWord_New(String gzid, String gzdj, String sjid ,
			List<EPaperquestions> leps) {
		Map<String, Object> map = new HashMap<String, Object>();
		String mc = "";
		String dj = "";
		List<String> list = getGz(gzid,gzdj);
		if(list!=null){
			mc = list.get(0);
			dj = list.get(1);
			map.put("gzmc", mc);
			map.put("gzdj", dj);
		}
		
		List<String> fill_list =  new ArrayList<String>();//
		List<Map<String, List<Object>>> single_list =  new ArrayList<Map<String, List<Object>>>();//单选2
		List<Map<String, List<Object>>> many_list =  new ArrayList<Map<String, List<Object>>>();//多选8
		List<String> verdict_list =  new ArrayList<String>();//判断3
		List<String> ask_list =  new ArrayList<String>();//
		List<String> calculate_list =  new ArrayList<String>();//
		List<String> draw_list =  new ArrayList<String>();//
		List<String> discuss_list =  new ArrayList<String>();//
		String[] temp = new String[]{"A","B","C","D","E","F","G","H"};
		Iterator<EPaperquestions> it = leps.iterator();
		int u=1,y=1,t=1,r=1,e=1,w=1,q=1,a=1;
		while(it.hasNext()){
			Map<String, List<Object>> map2 = new HashMap<String, List<Object>>();
			EPaperquestions eps = it.next();
			EQuestions eqs = eps.getEquestions();
			String zfs =eqs.getStFz();
			if(zfs==null)
			{
				zfs="1";
			}
			if(eqs.getEquestiontype().getPriority()==1){//fill填空
				String st = eqs.getStTg();
				st = st +"(";
				st = st+ zfs;
				st = st + "分)";
				st = st.trim();
				fill_list.add(st);
				while(u==1){
				Object[] o= getFs(1,sjid);
				String fillsm= String.valueOf(o[0]);//数目
				String fillfz=String.valueOf(o[1]);//分值
				map.put("fillsm", fillsm);
			    map.put("fillfz", fillfz);
			    u=u-1;
				}
			}
			else if(eqs.getEquestiontype().getPriority()==2){//单选
				String st = eqs.getStTg();
				st = st +"(";
				st = st+ zfs;
				st = st + "分)";
				st = st.trim();
				List<Object> list_d = new ArrayList<Object>();
				String da = eqs.getStXx();
				if(!"".equals(da)&&da!=null){
					String[] xx =  da.split("\\|\\|");
					for(int i=0;i<xx.length;i++){
						list_d.add(temp[i]+"、"+xx[i].trim());
					}
				}
				map2.put(st, list_d);
				single_list.add(map2);
				while(y==1){
				Object[] o= getFs(2,sjid);
				String singlesm= String.valueOf(o[0]);
				String singlefz=String.valueOf(o[1]);
				map.put("singlesm", singlesm);
			    map.put("singlefz", singlefz);
			    y=y-1;}
			}else if(eqs.getEquestiontype().getPriority()==8){//多选
				String st = eqs.getStTg();
				st = st +"(";
				st = st+ zfs;
				st = st + "分)";
				st = st.trim();
				List<Object> list_d = new ArrayList<Object>();
				String da = eqs.getStXx();
				if(!"".equals(da)&&da!=null){
					String[] xx =  da.split("\\|\\|");
					for(int i=0;i<xx.length;i++){
						list_d.add(temp[i]+"、"+xx[i].trim());
					}
				}
				map2.put(st, list_d);
				many_list.add(map2);
				while(t==1){
				Object[] o= getFs(8,sjid);
				String manysm= String.valueOf(o[0]);
				String manyfz=String.valueOf(o[1]);
				map.put("manysm", manysm);
			    map.put("manyfz", manyfz);
			    t=t-1;}
			}
			else if(eqs.getEquestiontype().getPriority()==3){//判断
				String st = eqs.getStTg();
				st = st +"(";
				st = st+ zfs;
				st = st + "分)";
				st = st.trim();
				verdict_list.add(st);
				while(r==1){
				Object[] o= getFs(3,sjid);
				String verdictsm= String.valueOf(o[0]);
				String verdictfz=String.valueOf(o[1]);
				map.put("verdictsm", verdictsm);
			    map.put("verdictfz", verdictfz);
			    r=r-1;}
			}
			else if(eqs.getEquestiontype().getPriority()==6){
				String st = eqs.getStTg();
				st = st +"(";
				st = st+ zfs;
				st = st + "分)";
				st = st.trim();
				discuss_list.add(st);
				while(e==1){
				Object[] o= getFs(6,sjid);
				String discusssm= String.valueOf(o[0]);
				String discussfz=String.valueOf(o[1]);
				map.put("discusssm", discusssm);
			    map.put("discussfz", discussfz);
			    e=e-1; }
			}
			else if(eqs.getEquestiontype().getPriority()==4){//问答题
			
				String st = eqs.getStTg();
				st = st +"(";
				st = st+ zfs;
				st = st + "分)";
				st = st.trim();
				ask_list.add(st);
				while(w==1){
				Object[] o= getFs(4,sjid);
				String asksm= String.valueOf(o[0]);
				String askfz=String.valueOf(o[1]);
				map.put("asksm", asksm);
			    map.put("askfz", askfz);
			    w=w-1;}
			}
			else if(eqs.getEquestiontype().getPriority()==5){//计算题
			
				String st = eqs.getStTg();
				st = st +"(";
				st = st+ zfs;
				st = st + "分)";
				st = st.trim();
				calculate_list.add(st);
				while(q==1){
				Object[] o= getFs(5,sjid);
				String calculatesm= String.valueOf(o[0]);
				String calculatefz=String.valueOf(o[1]);
				map.put("calculatesm", calculatesm);
			    map.put("calculatefz", calculatefz);
			    q=q-1;}
				
			}
			else if(eqs.getEquestiontype().getPriority()==7){//绘图题
				String st = eqs.getStTg();
				st = st +"(";
				st = st+ zfs;
				st = st + "分)";
				st = st.trim();
				draw_list.add(st);
				while(a==1){
				Object[] o= getFs(7,sjid);
				String drawsm= String.valueOf(o[0]);
				String drawfz=String.valueOf(o[1]);
				map.put("drawsm", drawsm);
			    map.put("drawfz", drawfz);
				a=a-1;}
			}
			
		}
		map.put("single", single_list);//单选
		map.put("verdict", verdict_list);//判断
		map.put("many", many_list);//多选
		map.put("fill", fill_list);//填空
		map.put("ask", ask_list);//问答
		map.put("discuss", discuss_list);//论述
		map.put("draw", draw_list);//画图
		map.put("calculate", calculate_list);//计算
		return map;
	}
	
	
	
	
	
	
	
	/**
	 * 单独答案导出
	 */
	public InputStream exportAnswerExam(Map<String, Object> map){
		Configuration cfg = SingletonFreemarker.getConfiguration();
		cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "/templates");
		cfg.setEncoding(Locale.getDefault(), "utf-8");
		Template temp;
		final File file = new File(ServletActionContext.getServletContext().getRealPath("/")+"templates/answerWord.doc");
		try {
			//temp = cfg.getTemplate("answer.ftl");
			temp = cfg.getTemplate("answer_New.ftl");
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "utf-8"));
			temp.process(map, out);
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 标准word试题导出
	 */
	public InputStream exportWordExam(Map<String, Object> map) {
		Configuration cfg = SingletonFreemarker.getConfiguration();
		cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "/templates");
		cfg.setEncoding(Locale.getDefault(), "utf-8");
		Template temp;
		final File file = new File(ServletActionContext.getServletContext().getRealPath("/")+"templates/examWord.doc");
		try {
			//temp = cfg.getTemplate("mb.ftl");
			temp = cfg.getTemplate("mb_New.ftl");
			//temp.setEncoding("gbk");
			//String fileName=RandomStringUtils.randomAlphanumeric(10);
	        //StringBuffer sb=new StringBuffer(fileName);
	        // System.out.println(fileName);
	        //sb.append(".doc").toString()"E:/mb.doc"
	        //System.out.println(ServletActionContext.getServletContext().getRealPath("/")+"webapp/templates/examWord.doc");
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "utf-8"));
			temp.process(map, out);
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ELogMonitor getELogMonitor(Long userid,String paperid) {
		return this.epapersDAOImpl.getELogMonitor(userid,paperid);
	}
	public List<String> getIpList(String paperid) {
		return this.epapersDAOImpl.getIpList(paperid);
	}
	public String checkIp(String ip,Long userid,String paperid) {
		return this.epapersDAOImpl.checkIp(ip,userid,paperid);
	}
	public boolean checkSubmit(String userid, String paperid) {
		return this.epapersDAOImpl.checkSubmit(userid, paperid);
	}
	public List<Object> checkResult(Long userid, String paperid) {
		return this.epapersDAOImpl.checkResult(userid, paperid);
	}
}
