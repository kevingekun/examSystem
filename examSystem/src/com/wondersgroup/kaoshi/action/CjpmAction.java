package com.wondersgroup.kaoshi.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.wondersgroup.falcon.paper.model.EAnswerpaper;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.paper.model.TableInfo;
import com.wondersgroup.kaoshi.bo.EKaoshiGroup;
import com.wondersgroup.kaoshi.bo.Qingkuang;
import com.wondersgroup.kaoshi.service.EAnswerpaperService;
import com.wondersgroup.kaoshi.util.AbstractPageAction;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.local.bo.WrongPersent_ws;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.utils.SortByAvg;

public class CjpmAction extends AbstractPageAction {
	// �Ծ�id
	private String paperid;
	// ���
	// all������ unpass ���������� average ƽ��� percent �ϸ���
	private int all;
	private int unpass;
	private double average = 0.0d;
	private double percent = 0.0d;
	private double total = 0.0d;
	private String countaveage;
	private String paperId;
	private List epaperquention;
	private String groupId;// 小组编号
	private String startScore;// 开始分数
	private String endScore;// 结束分数
	private String djryid;// 答卷人员id

	private String sex;
	private String userstar;
	private Date startctime;
	private Date endctime;
	
	private List gradeList;
	private String sjMc;

	private List groupList;// 用户组集合
	private List starList;// 星级集合
	private List<EUser> users;// 为参考人员
	private List eanswerpapers;
	private List<TableInfo> tableList = new ArrayList<TableInfo>();// 报表
	private int count = 0;// 试卷个数
	private EAnswerpaperService eanswerpaperService;
	
	private InputStream excelStream;
	private String downloadFileName;
	
	private List<WrongPersent_ws> wpList;

	public void setEanswerpaperService(EAnswerpaperService eanswerpaperService) {
		this.eanswerpaperService = eanswerpaperService;
	}

	@Override
	public String doAcion() throws Exception {
		DecimalFormat per = new DecimalFormat("0.00");
		this.pageTool.setSize(1000);
		PageReturn pagereturn = this.eanswerpaperService
				.findanswerpaperbypaper(this.pageTool, new Long(paperid),
						groupId, startScore, endScore, sex, userstar,
						startctime, endctime);
		this.setFY(pagereturn);
		//this.eanswerpapers = pagereturn.getReturnList();
		this.gradeList = pagereturn.getReturnList();
		if(gradeList.size()!=0){
			Object[] o = (Object[]) pagereturn.getReturnList().get(0);
			this.sjMc = (String) o[0];
		}
		this.all = pagereturn.getTotal();
		/*this.all = this.eanswerpaperService.countall(new Long(paperid),
				groupId, startScore, endScore, sex, userstar, startctime,
				endctime);*/
		this.unpass = this.eanswerpaperService.countunpass(new Long(paperid),
				groupId, startScore, endScore, sex, userstar, startctime,
				endctime);
		this.total = this.eanswerpaperService.counttotal(new Long(paperid),
				groupId, startScore, endScore, sex, userstar, startctime,
				endctime);

		if (all != 0) {
			this.percent = Double.parseDouble(per.format(100
					* (double) (all - unpass) / (double) all));
			// System.out.println(total+"----------------------------------");
			this.average = Double.parseDouble(per.format(total / all));
		} else {
			this.percent = 0.0;
			this.average = 0.0;
		}
		if ("".equals(countaveage) || countaveage == null) {
			if(all==0){
				countaveage = "0.00";
			}else{
				countaveage = per.format(total / all);
			}
		}
		// 用户组集合
		this.groupList = this.eanswerpaperService.getGroupsList();
		this.starList = this.eanswerpaperService.getStarList();
		return SUCCESS;
	}
	/**
	 * 导出excel排名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportExcel(){
		DecimalFormat per = new DecimalFormat("0.00");
		this.pageTool.setSize(1000);
		PageReturn pagereturn = this.eanswerpaperService
				.findanswerpaperbypaper(this.pageTool, new Long(paperid),
						groupId, startScore, endScore, sex, userstar,
						startctime, endctime);
		this.setFY(pagereturn);
		//this.eanswerpapers = pagereturn.getReturnList();
		this.gradeList = pagereturn.getReturnList();
		if(gradeList.size()!=0){
			Object[] o = (Object[]) pagereturn.getReturnList().get(0);
			this.sjMc = (String) o[0];
		}
		this.all = pagereturn.getTotal();
		/*this.all = this.eanswerpaperService.countall(new Long(paperid),
				groupId, startScore, endScore, sex, userstar, startctime,
				endctime);*/
		this.unpass = this.eanswerpaperService.countunpass(new Long(paperid),
				groupId, startScore, endScore, sex, userstar, startctime,
				endctime);
		this.total = this.eanswerpaperService.counttotal(new Long(paperid),
				groupId, startScore, endScore, sex, userstar, startctime,
				endctime);

		if (all != 0) {
			this.percent = Double.parseDouble(per.format(100
					* (double) (all - unpass) / (double) all));
			// System.out.println(total+"----------------------------------");
			this.average = Double.parseDouble(per.format(total / all));
		} else {
			this.percent = 0.0;
			this.average = 0.0;
		}
		excelStream = this.eanswerpaperService.exportExcel(gradeList, all, unpass, percent, average);
		downloadFileName = sjMc+"-成绩排名.xls";
		try {
			downloadFileName=new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String wrongPercentExport(){
		List<Object[]> list = this.eanswerpaperService.getWrongPercentList(Long.valueOf(paperid), 1);//1表示正常的未下放的试卷
		String sjmc = this.eanswerpaperService.getPaperByid(Long.valueOf(paperid)).getSjMc();
		excelStream = this.eanswerpaperService.exportWronPercentExcel(list);
		downloadFileName = sjmc+"-错误率.xls";
		try {
			downloadFileName=new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String wrongPercentExport_xf(){
		List<Object[]> list = this.eanswerpaperService.getWrongPercentList(Long.valueOf(paperid), 2);//2表示下放的试卷
		String sjmc = this.eanswerpaperService.getPaperByid(Long.valueOf(paperid)).getSjMc();
		excelStream = this.eanswerpaperService.exportWronPercentExcel(list);
		downloadFileName = sjmc+"-错误率.xls";
		try {
			downloadFileName=new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	// 查看错误率
	public String findwrongPercent() throws Exception {
		// System.out.println(paperId);
		//this.eanswerpaperService.saveEAnswerresult(new Long(paperId).longValue());
		wpList = this.eanswerpaperService.getpaperXfState(Long.valueOf(paperId));
		this.sjMc = this.eanswerpaperService.getPaperByid(Long.valueOf(paperId)).getSjMc();
		if (wpList.size()>0) {
			return "success_xf";
		}else{
			this.pageTool.setSize(1000);
			PageReturn pagereturn = this.eanswerpaperService.findwrongPercent(
					this.pageTool, new Long(paperId).longValue());
			this.epaperquention = pagereturn.getReturnList();
			return SUCCESS;
		}
	}

	// 查询某个用户参加过的答卷
	public String findAllanswerpaperbyUserId() {
		EAnswerpaper info = new EAnswerpaper();
		if (!"".equals(djryid) && djryid != null) {
			info.setDjRyid(djryid);
		}
		eanswerpapers = this.eanswerpaperService.findAllanswerpaperbyUser(info);
		return SUCCESS;
	}

	// 查询所有小组平均分统计报表
	public String findAllGroupAnswerpaper() {
		String sjids = "";
		groupList = this.eanswerpaperService.getGroupsList();
		List<EPapers> epapersList = this.eanswerpaperService
				.queryEPapersByDate(startctime, endctime);
		if (epapersList.size() != 0) {
			for (EPapers papers : epapersList) {
				sjids = sjids + papers.getSjId() + ",";
			}
			if (sjids.length() > 0) {
				sjids = sjids.substring(0, sjids.length() - 1);
			}
			eanswerpapers = this.eanswerpaperService
					.queryEAnswerPapersBySjIds(sjids);
			for (Object obj : groupList) {
				EKaoshiGroup kaoshiGroup = (EKaoshiGroup) obj;
				double[] seasonSore = new double[4];// 四个季度 每个季度的总分数
				int[] groupscount = new int[4];// 每个季度的小组人数
				int courperson = 0;// 记录当时答卷的人数
				TableInfo info = new TableInfo();
				double totalscore = 0.0d;
				for (Object object : eanswerpapers) {
					EAnswerpaper anser = (EAnswerpaper) object;
					info.setName(kaoshiGroup.getName());
					if (kaoshiGroup.getGroupId().equals(anser.getGroupId())) {
						courperson++;
						totalscore = totalscore + anser.getDjZf();
						// 第一季度 seasonSore[0]
						if ("1".equals(anser.getEpapers().getPaperType())) {
							seasonSore[0] = seasonSore[0] + anser.getDjZf();
							groupscount[0]++;
						}
						// 第二季度 seasonSore[1]
						if ("5".equals(anser.getEpapers().getPaperType())) {
							seasonSore[1] = seasonSore[1] + anser.getDjZf();
							groupscount[1]++;
						}
						// 第三季度 seasonSore[2]
						if ("6".equals(anser.getEpapers().getPaperType())) {
							seasonSore[2] = seasonSore[2] + anser.getDjZf();
							groupscount[2]++;
						}
						// 第四季度 seasonSore[3]
						if ("7".equals(anser.getEpapers().getPaperType())) {
							seasonSore[3] = seasonSore[3] + anser.getDjZf();
							groupscount[3]++;
						}

					}
				}
				info.getScoreList().add(
						String.format("%.2f", groupscount[0] == 0 ? 0
								: seasonSore[0] / groupscount[0]));// 第一季度
				info.getScoreList().add(
						String.format("%.2f", groupscount[1] == 0 ? 0
								: seasonSore[1] / groupscount[1]));// 第二季度
				info.getScoreList().add(
						String.format("%.2f", groupscount[2] == 0 ? 0
								: seasonSore[2] / groupscount[2]));// 第三季度
				info.getScoreList().add(
						String.format("%.2f", groupscount[3] == 0 ? 0
								: seasonSore[3] / groupscount[3]));// 第四季度
				info.setAvg(new Double(String.format("%.2f", totalscore
						/ courperson)));
				tableList.add(info);
			}
		}
		Collections.sort(tableList, new SortByAvg());// 按平均分进行排序
		/*
		 * for (TableInfo www : tableList) { for (String w : www.getScoreList())
		 * { System.out.print(w+"\t"); } System.out.println(); }
		 */
		return SUCCESS;
	}

	// 查询所有用户或者是某个小组下用户的平均分和每个答卷的分数
	public String findAllUserorGroupAnswerpaper() {
		List<Qingkuang> causeList = this.eanswerpaperService.findCauseList();
		Map<Object, String> map = new HashMap<Object, String>();
		for (Qingkuang qingkuang : causeList) {
			map.put(qingkuang.getId(), qingkuang.getMessage());
		}
		String sjids = "";
		groupList = this.eanswerpaperService.getGroupsUserByGroupId(paperid);
		List<EPapers> epapersList = this.eanswerpaperService
				.queryEPapersByDate(startctime, endctime);
		if (epapersList.size() != 0) {
			for (EPapers papers : epapersList) {
				sjids = sjids + papers.getSjId() + ",";
			}
			if (sjids.length() > 0) {
				sjids = sjids.substring(0, sjids.length() - 1);
			}
			eanswerpapers = this.eanswerpaperService
					.selectALLExamGroupsInformations(paperid, sjids);
			HashSet<String> set = new HashSet<String>();
			for (Object ss : groupList) {
				set.add(((EUser) ss).getId().toString());
			}
			for (String userid : set) {
				boolean flag = false;
				TableInfo table = new TableInfo();
				double totalscore = 0.0d;
				for (Object www : eanswerpapers) {
					EAnswerpaper pp = (EAnswerpaper) www;
					if (userid.equals(pp.getDjRyid())) {
						table.setBianId(userid);
						flag = true;
						table.setGongId(userid);
						table.setName(pp.getDjRymc());
						totalscore = totalscore + pp.getDjZf();
						if (pp.getCause() == null) {
							table.getScoreList().add(
									String.valueOf(pp.getDjZf()));
						} else {
							Long caseId = new Long(pp.getCause());
							table.getScoreList().add(map.get(caseId));
						}
					}

				}
				table.setAvg(new Double(String.format("%.2f", totalscore
						/ table.getScoreList().size())));
				if (flag)
					this.tableList.add(table);
			}
			count = 0;
			for (TableInfo tempTableInfo : tableList) {
				if (tempTableInfo.getScoreList().size() > count) {
					count = tempTableInfo.getScoreList().size();
				}
			}
		}
		Collections.sort(tableList, new SortByAvg());// 按平均分进行排序
		return SUCCESS;
	}

	// 组合试卷排名
	public String selectZhuHeAnswerpaper() {
		if (!"".equals(paperid) && paperid != null) {
			eanswerpapers = this.eanswerpaperService
					.selectZhuHeAnswerpaper(paperid);
			publicCountMethod(eanswerpapers);
			count = paperid.split(",").length;
		}
		return SUCCESS;
	}

	public void publicCountMethod(List ll) {
		List<Qingkuang> causeList = this.eanswerpaperService.findCauseList();
		Map<Object, String> map = new HashMap<Object, String>();
		map.put("", "未选择原因");
		for (Qingkuang qingkuang : causeList) {
			map.put(qingkuang.getId(), qingkuang.getMessage());
		}
		HashSet<String> set = new HashSet<String>();
		for (Object ss : ll) {
			set.add(((EAnswerpaper) ss).getDjRyid());
		}
		for (String id : set) {
			TableInfo table = new TableInfo();
			double totalscore = 0.0d;
			for (Object info : ll) {
				if (id.equals(((EAnswerpaper) info).getDjRyid())) {
					table.setBianId(id);
					table.setGongId(id);
					table.setName(((EAnswerpaper) info).getDjRymc());
					totalscore = totalscore + ((EAnswerpaper) info).getDjZf();
					if (((EAnswerpaper) info).getCause() == null) {
						table.getScoreList()
								.add(
										String.valueOf(((EAnswerpaper) info)
												.getDjZf()));
					} else {
						Long caseId = new Long(((EAnswerpaper) info).getCause());
						System.out.println(map.get(caseId));
						table.getScoreList().add(map.get(caseId));
					}
				}
			}
			table.setAvg(Math.round(totalscore / table.getScoreList().size()));
			tableList.add(table);
		}
		Collections.sort(tableList, new SortByAvg());// 按平均分进行排序
		for (TableInfo temp : tableList) {
			String str = temp.getBianId() + " " + temp.getGongId() + " "
					+ temp.getName() + " " + temp.getAvg();
			for (String tempss : temp.getScoreList()) {
				str = str + " " + tempss;
			}
			System.out.println("---" + str);
		}
	}

	/** 未参考人员列表 */
	public String nopartinexam() {
		users = this.eanswerpaperService.selectALLExamGroupsIdsNames(paperid);
		paperid = paperid.split(",")[0];
		groupList = this.eanswerpaperService.findCauseList();
		return SUCCESS;
	}

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}

	public List getEanswerpapers() {
		return eanswerpapers;
	}

	public void setEanswerpapers(List eanswerpapers) {
		this.eanswerpapers = eanswerpapers;
	}

	public int getAll() {
		return all;
	}

	public void setAll(int all) {
		this.all = all;
	}

	public int getUnpass() {
		return unpass;
	}

	public void setUnpass(int unpass) {
		this.unpass = unpass;
	}

	public double getAverage() {
		return average;
	}

	public List<EUser> getUsers() {
		return users;
	}

	public void setUsers(List<EUser> users) {
		this.users = users;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List getEpaperquention() {
		return epaperquention;
	}

	public void setEpaperquention(List epaperquention) {
		this.epaperquention = epaperquention;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getStartScore() {
		return startScore;
	}

	public void setStartScore(String startScore) {
		this.startScore = startScore;
	}

	public String getEndScore() {
		return endScore;
	}

	public void setEndScore(String endScore) {
		this.endScore = endScore;
	}

	public List getGroupList() {
		return groupList;
	}

	public void setGroupList(List groupList) {
		this.groupList = groupList;
	}

	public String getCountaveage() {
		return countaveage;
	}

	public void setCountaveage(String countaveage) {
		this.countaveage = countaveage;
	}

	public String getDjryid() {
		return djryid;
	}

	public void setDjryid(String djryid) {
		this.djryid = djryid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserstar() {
		return userstar;
	}

	public void setUserstar(String userstar) {
		this.userstar = userstar;
	}

	public Date getStartctime() {
		return startctime;
	}

	public void setStartctime(Date startctime) {
		this.startctime = startctime;
	}

	public Date getEndctime() {
		return endctime;
	}

	public void setEndctime(Date endctime) {
		this.endctime = endctime;
	}

	public List<TableInfo> getTableList() {
		return tableList;
	}

	public void setTableList(List<TableInfo> tableList) {
		this.tableList = tableList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List getStarList() {
		return starList;
	}

	public void setStarList(List starList) {
		this.starList = starList;
	}

	public List getGradeList() {
		return gradeList;
	}

	public void setGradeList(List gradeList) {
		this.gradeList = gradeList;
	}

	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
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

	public List<WrongPersent_ws> getWpList() {
		return wpList;
	}

	public void setWpList(List<WrongPersent_ws> wpList) {
		this.wpList = wpList;
	}

}
