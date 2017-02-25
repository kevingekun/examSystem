package com.wondersgroup.kaoshi.service;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.wondersgroup.falcon.paper.model.EPaperquestions;
import com.wondersgroup.falcon.paper.model.EPaperquestions_temp;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.kaoshi.bo.EKaoshi;
import com.wondersgroup.kaoshi.bo.UploadToYth;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.ELogMonitor;
import com.wondersgroup.popedom.bo.EUser;

public interface EPapersService {
	public PageReturn getPapers(PageTool pageInfo);
	public List getPapers();
	public EPapers getEPapersById(String paperid);
	
	public EPaperquestions_temp findOneQuestion(int sequence,long equestions_type,String ryid);
	
	public EPaperquestions_temp findUnfinish(int sequence,String ryid);
	
	public int findQuestionsBytypeTotal(int sequence,long equestions_type,String ryid);
	
	public int findQuestionsBytypeDone(int sequence,long equestions_type,String ryid);
	
	public int findQuestionsTotal(String ryid);
	
	public int findQuestionsDone(String ryid);
	
	public void saveEpaperquestions_temp(EPaperquestions_temp epqt);
	
	public void saveEpaperquestions_tempAll(String paperId,String ryid);
	
	public void saveEpaperquestions_temp(String paperId,String ryid);
	public EPaperquestions_temp loadePaperquestions_temp(String epqtId);
	
	public List<EPaperquestions_temp> findtype(String paperId, long equestions_type,String ryid);
	
	
	public void addEpaper(EPapers epapers);
	public void addEKaoshi(EKaoshi eKaoshi);
	public void delete();
	public Map<Long, Long> updateState(String paperid,String paperState)throws Exception;
	public void calculateScore(Map<Long, Long> map,ApplicationContext ac);
	public void updatecheckpaper(String paperid,String paperState,String advice,String rymc);
	public PageReturn findpaperbyCanexam(PageTool pagetool,String sjMc,Long sjZt);
	public PageReturn findpaperbyCanceping(PageTool pagetool,String sjMc,Long sjZt);
	public PageReturn findpaperbySjzt(PageTool pagetool,Long sjzt,EPapers epapers,Date begin,Date end,String flag);
	public PageReturn findgrade(PageTool pagetool,Long sjzt,EPapers epapers,EUser eUser,Date begin,String flag,String pcid);
	public PageReturn gradeSearch(PageTool pagetool,Long sjzt,EPapers epapers,EUser eUser,Date begin,String flag,String pcid);
	//获取可以上传成绩到鉴定中心的试卷考生信息
	public PageReturn findGradeForUploadToJdzx(PageTool pagetool,Long sjzt,EPapers epapers,EUser eUser,Date begin,String flag);
	public EKaoshi findbyryid();
	public EUser queryUserById(String toUserId);
	//获得当前用户登录ip地址
	public void updateUserIP(String userIP,Long examid);
	//添加考生开始考试时间
	public void updateUserStratDt(String paperId);
	//获取考生所考试卷 工种 等级
	public List<String> getGz(String gzid,String gzdj);
	//获取考生登录信息
	public ELogMonitor getELogMonitor(Long userid,String paperid);
	//获取本次考试已经登陆的ip集合
	public List<String> getIpList(String paperid);
	//检查考生登录ip是否存在异常
	public String checkIp(String ip,Long userid,String paperid);
	//检查是否已经交卷
	public boolean checkSubmit(String userid,String paperid);
	
	public List<Object> checkResult(Long userid,String paperid);
	
	//构造word试卷所需数据
	public Map<String, Object> getInfoOfWord(String gzid, String gzdj, List<EPaperquestions> eps);
	
	//构造word试卷所需数据   新版
	public Map<String, Object> getInfoOfWord_New(String gzid, String gzdj, String sjid,List<EPaperquestions> eps);
	
	public Map<String, Object> getAnswerOfPaper(String sjmc,List<EPaperquestions> epqs);
	public InputStream exportWordExam(Map<String, Object> map);
	public InputStream exportAnswerExam(Map<String, Object> map);
		
	

	//修改分数
	public boolean changeGrade(String id, String userid, String grade);
	//上传到一体化
	public UploadToYth uploadToYth(String examId,String sjMc,String name);
	
	//上传到一体化 webservice
	public String uploadToYthByWs(String examId,String sjMc,String name);
	//上传到鉴定中心 webservice
	public String uploadToJdzxByWs(String examId,String sjMc,String name);
	
	public List<Object> getSurplus(String paperid);
	/**
	 * 更新考生的考试的结束时间与结束状态
	 */
	public void updateLogMonitore(String paperid,String userid);
	//导出答案新版
	public Map<String, Object> getAnswerOfPaper_New(String sjMc,
			List<EPaperquestions> epaperquestions);
	
}
