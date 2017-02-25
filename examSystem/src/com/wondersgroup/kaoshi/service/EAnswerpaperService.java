package com.wondersgroup.kaoshi.service;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.wondersgroup.falcon.paper.model.EAnswerpaper;
import com.wondersgroup.falcon.paper.model.EAnswerquestions;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.local.bo.WrongPersent_ws;
import com.wondersgroup.popedom.bo.ELogMonitor;
import com.wondersgroup.popedom.bo.EUser;

public interface EAnswerpaperService {
	public PageReturn findmypaper(PageTool pagetool, String username,
			EPapers epaper);

	public PageReturn findsypaper(PageTool pagetool, Long djSyzt, String sjMc,
			Date kksj,String flag);

	public PageReturn findsypaper(PageTool pagetool, String sjMc, Date djsj,
			Date djsjend);

	public PageReturn findAnswerPaperBySjId(PageTool pagetool, Long sjId);

	public PageReturn findAnswerPaper(PageTool pagetool, String sjMc);

	public long saveEAnswerpaper(EAnswerpaper eanswerpaper);
	
	public List<Object> submitPaper(long djid,String userid,Connection conn);

	public void saveEAnswerquentions(long paperId);

	public void saveEAnswerresult(long paperId);

	public void saveEAnswerquestions(EAnswerquestions eanswerquestions);

	public void updateEAnswerquestions(EAnswerquestions eanswerquestions);

	public EAnswerpaper load(Long id);

	public PageReturn findanswerpaperbypaper(PageTool pagetool, Long paperid,
			String groupId, String startScore, String endScore, String sex,
			String userstart, Date startctime, Date endctime);
	
	public List<Object[]> getWrongPercentList(long paperid,int state);
	
	public EPapers getPaperByid(long paperid);
	
	public InputStream exportExcel(List<Object[]> list,int all,int unpass,double percent,double average);
	
	public InputStream exportWronPercentExcel(List<Object[]> list);

	public PageReturn findwrongPercent(PageTool pagetool, long paperId);
	
	public List<WrongPersent_ws> getpaperXfState(long paperid);

	public int countall(Long paperid, String groupId, String startScore,
			String endScore, String sex, String userstart, Date startctime,
			Date endctime);

	public int countunpass(Long paperid, String groupId, String startScore,
			String endScore, String sex, String userstart, Date startctime,
			Date endctime);

	public double counttotal(Long paperid, String groupId, String startScore,
			String endScore, String sex, String userstart, Date startctime,
			Date endctime);

	public List getGroupsList();

	/** 根据相关信息查找某个人员参加过的考试信息 */
	public List findAllanswerpaperbyUser(EAnswerpaper info);

	/** 组合试卷排名 */
	public List selectZhuHeAnswerpaper(String papers);

	/** 查询所有考试小组 */
	public List selectALLExamGroups();

	/** 查询所有考试小组的人员 */
	public List selectALLExamGroupsIdsNames(String paperid);

	/** 设置未参加考试原因 */
	public void nopartinexamCause(EPapers paperId, String ryid, String cause);

	public EUser findEUser(String ryid);

	/** 不考试原因列表 */
	public List findCauseList();

	/** 查询时间范围内容的时间 并且考试状态为2的 */
	public List queryEPapersByDate(Date startctime, Date endctime);

	/** 按试卷id获取相关的答卷列表 */
	public List queryEAnswerPapersBySjIds(String sjids);

	public List selectALLExamGroupsInformations(String paperid, String sjids);

	public List getGroupsUserByGroupId(String paperid);

	/** 获取星级列表 */
	public List getStarList();
	/**
	 * 进行更新考生的剩余时间
	 */
	public ELogMonitor updateSurplus(String paperid,String times);
	/*
	 * 更新答题登记界面总分
	 */
	public void updateEanserPapersForZf(String paperid,double defen);
}
