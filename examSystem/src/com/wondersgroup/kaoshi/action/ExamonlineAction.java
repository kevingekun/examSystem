package com.wondersgroup.kaoshi.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.paper.model.EAnswerpaper;
import com.wondersgroup.falcon.paper.model.EAnswerquestions;
import com.wondersgroup.falcon.paper.model.EPaperquestions;
import com.wondersgroup.falcon.paper.model.EPaperquestions_temp;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.kaoshi.bo.EKaoshi;
import com.wondersgroup.kaoshi.service.EAnswerpaperService;
import com.wondersgroup.kaoshi.service.EPapersService;
import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.ELogMonitor;
import com.wondersgroup.popedom.bo.EUser;

public class ExamonlineAction extends AbstractPageNavAction {
	// 查询条件
	private String sjMc;
	private Long sjZt;
	// 所有的试题类型
	private List equestiontypes;

	// 一道题目
	private EPaperquestions_temp equestions;

	// 未完成的一道题目
	private EPaperquestions_temp equestionsUnfinish;

	// 题目Id
	private String epqtId;

	// 题目集合
	private List<EPaperquestions_temp> epqts;

	// 第几题
	private Long Undone;

	// 第几题
	private Long sequence;

	// 第几题
	private Long aftersequence;

	// 类型下总共多少题
	private int Typeall;

	// 类型下总共完成多少题
	private int Typealldone;

	// 总共多少题
	private int all;

	// 总共完成多少题
	private int alldone;

	// 题目类型
	private Long equestions_type;

	// 是否已答题

	private Long state;

	// 考试人员工号

	private String ryid;

	// 试卷信息
	private Set paperquestions;
	// 人员信息
	private EUser user;
	// 试卷
	private EPapers epapers;
	// 试卷id
	private String paperId;
	// 未参加考试原因
	private String cause;
	// 结束时间
	private long jssj;
	// 可以考试的试卷
	private List papers;
	//当前考生的剩余时间getSurplus
	private String surplus;
	//总分
	private double sjdf;
	
	private String gzmc;//工种名称
	private String gzdj;//工种等级
	
	private double jgfs;//及格分数
	private EPapersService epaperservice;

	// 答题service
	private EAnswerpaperService eanswerpaperService;

	// 查询试卷和实体的连接表
	private EpaperquestionsService epaperquestionsService;

	public void setEpaperservice(EPapersService epaperservice) {
		this.epaperservice = epaperservice;
	}

	@Override
	public String doAcion() throws Exception {
		//根据users表中存的试卷id查询试卷（findpaperbyCanexam方法）
		this.pageReturn = this.epaperservice.findpaperbyCanexam(pageTool, null,
				null);
		this.papers = this.pageReturn.getReturnList();
		if(this.papers.size()!=0){
			this.epapers = (EPapers) this.papers.get(0);
			List<String> list = this.epaperservice.getGz(this.epapers.getSjGzid(), this.epapers.getSjDj());
			if(list!=null){
				this.gzmc = list.get(0);
				this.gzdj = list.get(1);
			}
			Long sjid = this.epapers.getSjId();
			//当进入在线考试试卷查询的时候获得当前用户的登录ip地址
			String remoteAddr = ServletActionContext.getRequest().getRemoteAddr();
			this.epaperservice.updateUserIP(remoteAddr,sjid);
		}
		return SUCCESS;
	}

	public void checkExam(){
		AcegiUtil acegiUtil = new AcegiUtil();
		EUser user = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser();
		boolean b = this.epaperservice.checkSubmit(String.valueOf(user.getId()), paperId);
		ELogMonitor eLogMonitor = this.epaperservice.getELogMonitor(user.getId(),paperId);
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		HttpServletResponse response = ServletActionContext.getResponse();
		String info = this.epaperservice.checkIp(ip, user.getId(), paperId);
		try {
			if (b) {//已经提交过试卷
				response.getWriter().write("submitAlready");
			}else{
				if (eLogMonitor.getStartdate()!=null) {//教师机点已经点击开始考试，可以进行考试判断
					if("1".equals(ProcedureUrl.IP_LIMIT)){//如果IP_LIMIT为1则限制ip，否则不限制
						response.getWriter().write(info);//鉴定中心
					}else{
						response.getWriter().write("success");//鉴定所用路由器，因此不限制ip
					}
				}else{
					response.getWriter().write("0");//教师机没有点 开始考试，不能考试
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * <p>
	 * Description:到考试页面
	 * </p>
	 * 
	 * Created by [www] [Aug 18, 2009] Midified by
	 * 
	 * @return
	 */
	public String exam() {
	
		// 得到人员
		AcegiUtil acegiUtil = new AcegiUtil();
		this.user = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser();
		// 设置考试人考试状态
		if (this.epaperservice.findbyryid().getRyid() != null
				&& this.epaperservice.findbyryid().getRyid().intValue() == (user
						.getId().intValue())) {
		} else {
			EKaoshi eKaoshi = new EKaoshi();
			eKaoshi.setRyid(user.getId());
			eKaoshi.setZt(1);
			this.epaperservice.addEKaoshi(eKaoshi);
		}
		// 找到试卷
		this.epapers = this.epaperservice.getEPapersById(paperId);
		// 找到试卷的所有题目
		if (this.epapers.getEpaperquestionses() != null) {
			//System.out.println("开始："+new Date().getTime());
			this.paperquestions =  this.epapers.getEpaperquestionses() ;
			List list = new ArrayList();
			list.addAll(this.paperquestions);
			Collections.shuffle(list);//随机排序，生成顺序不一样的试题
			this.paperquestions = new HashSet(list);
			this.all = this.epapers.getEpaperquestionses().size();
			//this.paperquestions.addAll(this.epapers.getEpaperquestionses());//速度比较慢
			/*Iterator it=this.epapers.getEpaperquestionses().iterator();  
			   //判断是否还有元素可以迭代  
			   while(it.hasNext())  
			   {  
				   this.paperquestions.add(it.next());
			   }*/
			//System.out.println("中间："+new Date().getTime());
			//Collections.shuffle(this.paperquestions);
			//System.out.println("结束："+new Date().getTime());
		}
		// 找到所有的类型
		this.equestiontypes = this.epaperquestionsService.findEQuestiontype();
		//获得当前考生的剩余时间
		List<Object> olist = this.epaperservice.getSurplus(paperId);
		String time=(String) olist.get(0);
		Date startDate = (Date) olist.get(1);
		/*if(time==null||time==""){
			//this.surplus=String.valueOf(this.epapers.getSjDjsx());
			Date date = new Date();
			String s = String.valueOf(this.epapers.getSjDjsx()-(date.getTime()-startDate.getTime())/60/1000);
			System.out.println(s);
			this.surplus = s;
		}else{
			this.surplus=time;
		}*/
		Date date = new Date();
		String s = String.valueOf(this.epapers.getSjDjsx()-(date.getTime()-startDate.getTime())/60/1000);
		//System.out.println(s);
		this.surplus = s;
		//记录开始考试
		this.epaperservice.updateUserStratDt(paperId);
		long times=new Date().getTime();
		return SUCCESS;
	}

	public String findceping() throws Exception {
		this.pageReturn = this.epaperservice.findpaperbyCanceping(pageTool,
				sjMc, sjZt);
		this.papers = this.pageReturn.getReturnList();
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:民主测试页面
	 * </p>
	 * 
	 * Created by [www] [Aug 18, 2009] Midified by
	 * 
	 * @return
	 */
	public String ceping() {
		// 得到人员
		AcegiUtil acegiUtil = new AcegiUtil();
		this.user = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser();
		// 设置考试人考试状态
		// if(this.epaperservice.findbyryid().getRyid()!=null&&this.epaperservice
		// .findbyryid().getRyid().intValue()==(user.getId().intValue())){
		// }else {
		// EKaoshi eKaoshi=new EKaoshi();
		// eKaoshi.setRyid(user.getId());
		// eKaoshi.setZt(1);
		// this.epaperservice.addEKaoshi(eKaoshi);
		// }
		// 找到试卷
		this.epapers = this.epaperservice.getEPapersById(paperId);
		// 找到试卷的所有题目
		if (this.epapers.getEpaperquestionses() != null) {
			//this.paperquestions = new ArrayList();
			this.paperquestions=this.epapers.getEpaperquestionses();
			//this.paperquestions.addAll(this.epapers.getEpaperquestionses());
		}
		// 找到所有的类型
		this.equestiontypes = this.epaperquestionsService.findEQuestiontype();
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:随机插入考题
	 * </p>
	 * 
	 * Created by [www] [Aug 18, 2009] Midified by
	 * 
	 * @return
	 */
	public String creatQuestions() {
		// 得到人员
		AcegiUtil acegiUtil = new AcegiUtil();
		this.user = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser();
		// 设置考试人考试状态
		if (this.epaperservice.findbyryid().getRyid() != null
				&& this.epaperservice.findbyryid().getRyid().intValue() == (user
						.getId().intValue())) {
		} else {
			EKaoshi eKaoshi = new EKaoshi();
			eKaoshi.setRyid(user.getId());
			eKaoshi.setZt(1);
			this.epaperservice.addEKaoshi(eKaoshi);
		}
		// 找到试卷
		this.epapers = this.epaperservice.getEPapersById(paperId);

		this.ryid = user.getUsername();

		// 找到所有的类型
		this.equestiontypes = this.epaperquestionsService.findEQuestiontype();

		// 找到试卷的所有题目
		if (this.epapers.getEpaperquestionses() != null) {
//			this.paperquestions = new ArrayList();
//			this.paperquestions.addAll(this.epapers.getEpaperquestionses());
			this.paperquestions=this.epapers.getEpaperquestionses();
			this.epaperservice.saveEpaperquestions_temp(paperId, user
					.getUsername());
			this.all = this.epaperservice.findQuestionsTotal(this.ryid);

		}

		this.aftersequence = new Long(0);
		String backflag = this.getRequest().getParameter("backflag");
		String turn = this.getRequest().getParameter("turn");
		String first = this.getRequest().getParameter("first");

		if (first != null && first.equals("1")) {

			this.sequence = new Long(0);

		} else if (backflag != null && backflag.equals("1")) {

			this.sequence = sequence - 1;
		} else if (turn != null && turn.equals("1")) {

			this.sequence = sequence + 1;
		} else {

			this.sequence = sequence;
		}

		String exambegin = this.getRequest().getParameter("exambegin");
		if (first == null && exambegin != null && exambegin.equals("1")) {
			EPaperquestions_temp epqt = this.epaperservice
					.loadePaperquestions_temp(this.epqtId);
			if (this.equestions_type.longValue() == 2) {// 如果是多选题
				String[] daan = this.getRequest().getParameterValues("daan");
				if (daan != null) {
					String str_daan = "";
					for (int i = 0; i < daan.length; i++) {
						if (i == daan.length - 1) {
							str_daan += daan[i];
						} else {
							str_daan += daan[i] + "||";
						}
					}
					epqt.setStDa(str_daan);
					epqt.setState(new Long(1).longValue()); // 设置答题
					this.epaperservice.saveEpaperquestions_temp(epqt);

				}

			} else if (this.equestions_type.longValue() == 4) {// 如果是判断说明题

				String daansm = this.getRequest().getParameter("daansm");
				if (daansm != null) {
					epqt.setStDasm(daansm);
				}
				String daan = this.getRequest().getParameter("daan");
				if (daan != null) {
					epqt.setStDa(daan);// 设置答题
					epqt.setState(new Long(1).longValue());
					this.epaperservice.saveEpaperquestions_temp(epqt);
				}

			} else {

				String daan = this.getRequest().getParameter("daan");
				if (daan != null) {
					epqt.setStDa(daan);// 设置答题
					epqt.setState(new Long(1).longValue());
					this.epaperservice.saveEpaperquestions_temp(epqt);
				}

			}
		}

		if (equestions_type != null) {
			this.equestions = this.epaperservice.findOneQuestion(this.sequence
					.intValue(), this.equestions_type.longValue(), this.ryid);
			this.Typealldone = this.epaperservice.findQuestionsBytypeDone(
					this.sequence.intValue(), this.equestions_type, this.ryid);
			this.Typeall = this.epaperservice.findQuestionsBytypeTotal(
					this.sequence.intValue(), this.equestions_type, this.ryid);
			this.equestions_type = equestions_type;
			// 显示是第几题
			this.aftersequence = sequence + 1;
		}
		this.paperId = paperId;

		// this.ryid=ryid;
		if (this.equestions != null) {
			this.epqtId = new Long(this.equestions.getId()).toString();

		}
		this.alldone = this.epaperservice.findQuestionsDone(this.ryid);

		return SUCCESS;
	}

	// /Ajax得到下一题目 上一题目
	public String QuestionsByajax(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 得到人员
		AcegiUtil acegiUtil = new AcegiUtil();
		this.user = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser();
		// 设置考试人考试状态
		if (this.epaperservice.findbyryid().getRyid() != null
				&& this.epaperservice.findbyryid().getRyid().intValue() == (user
						.getId().intValue())) {
		} else {
			EKaoshi eKaoshi = new EKaoshi();
			eKaoshi.setRyid(user.getId());
			eKaoshi.setZt(1);
			this.epaperservice.addEKaoshi(eKaoshi);
		}
		// 找到试卷
		this.epapers = this.epaperservice.getEPapersById(paperId);

		this.ryid = user.getUsername();

		// 找到所有的类型
		this.equestiontypes = this.epaperquestionsService.findEQuestiontype();

		// 找到试卷的所有题目
		if (this.epapers.getEpaperquestionses() != null) {
//			this.paperquestions = new ArrayList();
//			this.paperquestions.addAll(this.epapers.getEpaperquestionses());
			this.paperquestions = this.epapers.getEpaperquestionses();
			this.epaperservice.saveEpaperquestions_temp(paperId, user
					.getUsername());
			this.all = this.epaperservice.findQuestionsTotal(this.ryid);
			this.alldone = this.epaperservice.findQuestionsDone(this.ryid);
		}

		this.aftersequence = new Long(0);
		String backflag = this.getRequest().getParameter("backflag");
		String turn = this.getRequest().getParameter("turn");
		String first = this.getRequest().getParameter("first");

		if (first != null && first.equals("1")) {

			this.sequence = new Long(0);

		} else if (backflag != null && backflag.equals("1")) {

			this.sequence = sequence - 1;
		} else if (turn != null && turn.equals("1")) {

			this.sequence = sequence + 1;
		} else {

			this.sequence = sequence;
		}

		String exambegin = this.getRequest().getParameter("exambegin");

		String unfinish = this.getRequest().getParameter("unfinish");
		if (first == null && exambegin != null && exambegin.equals("1")) {
			EPaperquestions_temp epqt = this.epaperservice
					.loadePaperquestions_temp(this.epqtId);
			if (this.equestions_type.longValue() == 2) {// 如果是多选题
				String[] daan = this.getRequest().getParameterValues("daan");
				if (daan != null) {
					String str_daan = "";
					for (int i = 0; i < daan.length; i++) {
						if (i == daan.length - 1) {
							str_daan += daan[i];
						} else {
							str_daan += daan[i] + "||";
						}
					}
					epqt.setStDa(str_daan);
					epqt.setState(new Long(1).longValue()); // 设置答题
					this.epaperservice.saveEpaperquestions_temp(epqt);

				}

			} else if (this.equestions_type.longValue() == 4) {// 如果是判断说明题

				String daansm = this.getRequest().getParameter("daansm");
				if (daansm != null) {
					epqt.setStDasm(daansm);
				}
				String daan = this.getRequest().getParameter("daan");
				if (daan != null) {
					epqt.setStDa(daan);// 设置答题
					epqt.setState(new Long(1).longValue());
					this.epaperservice.saveEpaperquestions_temp(epqt);
				}

			} else {

				String daan = this.getRequest().getParameter("daan");
				if (daan != null) {
					epqt.setStDa(daan);// 设置答题
					epqt.setState(new Long(1).longValue());
					this.epaperservice.saveEpaperquestions_temp(epqt);
				}

			}
		}

		if (equestions_type != null) {
			this.equestions = this.epaperservice.findOneQuestion(this.sequence
					.intValue(), this.equestions_type.longValue(), this.ryid);
			this.Typealldone = this.epaperservice.findQuestionsBytypeDone(
					this.sequence.intValue(), this.equestions_type, this.ryid);
			this.Typeall = this.epaperservice.findQuestionsBytypeTotal(
					this.sequence.intValue(), this.equestions_type, this.ryid);
			this.equestions_type = equestions_type;
			// 显示是第几题
			this.aftersequence = sequence + 1;
		}
		this.paperId = paperId;
		// this.ryid=ryid;
		if (this.equestions != null) {
			this.epqtId = new Long(this.equestions.getId()).toString();

		}

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");

		PrintWriter out = response.getWriter();

		String target = request.getParameter("target");

		StringBuffer responseXML = new StringBuffer("<admin>");

		if (target != null && !"".equals(target)) {
			int id = Integer.parseInt(target);
			responseXML.append("<equestions>" + this.equestions
					+ "</equestions>");
			responseXML.append("<equestions_type>" + this.equestions_type
					+ "</equestions_type>");
			responseXML.append("<Typealldone>" + this.Typealldone
					+ "</Typealldone>");
			responseXML.append("<Typeall>" + this.Typeall + "</Typeall>");
			responseXML.append("<paperId>" + this.paperId + "</paperId>");
			responseXML.append("<epqtId>" + this.epqtId + "</epqtId>");
			responseXML.append("<sequence>" + this.sequence + "</sequence>");
			responseXML.append("<aftersequence>" + this.aftersequence
					+ "</aftersequence>");
			responseXML.append("<all>" + this.all + "</all>");
			responseXML.append("<alldone>" + this.alldone + "</alldone>");
			responseXML.append("<paperquestions>" + this.paperquestions
					+ "</paperquestions>");
			responseXML.append("<equestiontypes>" + this.equestiontypes
					+ "</equestiontypes>");
			responseXML.append("<epapers>" + this.epapers + "</epapers>");
		}

		responseXML.append("</admin>");

		out.print(responseXML);

		return SUCCESS;
	}

	public String findUnfinish() {
		// 找到试卷
		this.epapers = this.epaperservice.getEPapersById(paperId);
		// 找到所有的类型
		this.equestiontypes = this.epaperquestionsService.findEQuestiontype();

		// 找到试卷的所有题目
		if (this.epapers.getEpaperquestionses() != null) {
//			this.paperquestions = new ArrayList();
//			this.paperquestions.addAll(this.epapers.getEpaperquestionses());
			this.paperquestions=this.epapers.getEpaperquestionses();
			this.epaperservice.saveEpaperquestions_temp(paperId, this.ryid);
			this.all = this.epaperservice.findQuestionsTotal(this.ryid);

		}

		this.aftersequence = new Long(0);
		String backflag = this.getRequest().getParameter("backflag");
		String turn = this.getRequest().getParameter("turn");
		String first = this.getRequest().getParameter("first");

		if (first != null && first.equals("1")) {

			this.sequence = new Long(0);

		} else if (backflag != null && backflag.equals("1")) {

			this.sequence = sequence - 1;
		} else if (turn != null && turn.equals("1")) {

			this.sequence = sequence + 1;
		} else {

			this.sequence = sequence;
		}

		String exambegin = this.getRequest().getParameter("exambegin");

		if (first == null && exambegin != null && exambegin.equals("1")) {
			EPaperquestions_temp epqt = this.epaperservice
					.loadePaperquestions_temp(this.epqtId);
			if (this.equestions_type.longValue() == 2) {// 如果是多选题
				String[] daan = this.getRequest().getParameterValues("daan");
				if (daan != null) {
					String str_daan = "";
					for (int i = 0; i < daan.length; i++) {
						if (i == daan.length - 1) {
							str_daan += daan[i];
						} else {
							str_daan += daan[i] + "||";
						}
					}
					epqt.setStDa(str_daan);
					epqt.setState(new Long(1).longValue()); // 设置答题
					this.epaperservice.saveEpaperquestions_temp(epqt);

				}

			} else if (this.equestions_type.longValue() == 4) {// 如果是判断说明题

				String daansm = this.getRequest().getParameter("daansm");
				if (daansm != null) {
					epqt.setStDasm(daansm);
				}
				String daan = this.getRequest().getParameter("daan");
				if (daan != null) {
					epqt.setStDa(daan);// 设置答题
					epqt.setState(new Long(1).longValue());
					this.epaperservice.saveEpaperquestions_temp(epqt);
				}

			} else {

				String daan = this.getRequest().getParameter("daan");
				if (daan != null) {
					epqt.setStDa(daan);// 设置答题
					epqt.setState(new Long(1).longValue());
					this.epaperservice.saveEpaperquestions_temp(epqt);
				}

			}
		}

		this.equestions = this.epaperservice.findUnfinish(new Long(sequence)
				.intValue(), ryid);
		// 显示是第几题
		this.aftersequence = sequence + 1;
		// System.out.println(this.Unfinish+"!!!!!!!!!!!!!!!");
		this.paperId = paperId;
		this.ryid = ryid;
		if (this.equestions != null) {
			this.epqtId = new Long(this.equestions.getId()).toString();
			this.equestions_type = this.equestions.getEquestions()
					.getEquestiontype().getPriority();
			this.Undone = new Long(1);

		}
		this.alldone = this.epaperservice.findQuestionsDone(this.ryid);

		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:选择题目
	 * </p>
	 * 
	 * Created by [www] [Aug 18, 2009] Midified by
	 * 
	 * @return
	 */
	public String findQuestions() {

		this.aftersequence = new Long(0);
		String backflag = this.getRequest().getParameter("backflag");
		String turn = this.getRequest().getParameter("turn");
		String first = this.getRequest().getParameter("first");

		if (first != null && first.equals("1")) {

			this.sequence = new Long(0);

		} else if (backflag != null && backflag.equals("1")) {

			this.sequence = sequence - 1;
		} else if (turn != null && turn.equals("1")) {

			this.sequence = sequence + 1;
		} else {

			this.sequence = sequence;
		}
		this.aftersequence = sequence + 1;

		if (first == null) {
			EPaperquestions_temp epqt = this.epaperservice
					.loadePaperquestions_temp(this.epqtId);
			if (this.equestions_type.longValue() == 2) {// 如果是多选题
				String[] daan = this.getRequest().getParameterValues("daan");
				if (daan != null) {
					String str_daan = "";
					for (int i = 0; i < daan.length; i++) {
						if (i == daan.length - 1) {
							str_daan += daan[i];
						} else {
							str_daan += daan[i] + "||";
						}
					}
					epqt.setStDa(str_daan);
					epqt.setState(new Long(1).longValue()); // 设置答题
					this.epaperservice.saveEpaperquestions_temp(epqt);

				}

			} else if (this.equestions_type.longValue() == 4) {// 如果是判断说明题

				String daansm = this.getRequest().getParameter("daansm");
				if (daansm != null) {
					epqt.setStDasm(daansm);
				}
				String daan = this.getRequest().getParameter("daan");
				if (daan != null) {
					epqt.setStDa(daan);// 设置答题
					epqt.setState(new Long(1).longValue());
					this.epaperservice.saveEpaperquestions_temp(epqt);
				}

			} else {

				String daan = this.getRequest().getParameter("daan");
				if (daan != null) {
					epqt.setStDa(daan);// 设置答题
					epqt.setState(new Long(1).longValue());
					this.epaperservice.saveEpaperquestions_temp(epqt);
				}

			}
		}

		this.equestions = this.epaperservice.findOneQuestion(this.sequence
				.intValue(), this.equestions_type.longValue(), this.ryid);
		this.Typealldone = this.epaperservice.findQuestionsBytypeDone(
				this.sequence.intValue(), this.equestions_type, this.ryid);
		this.Typeall = this.epaperservice.findQuestionsBytypeTotal(
				this.sequence.intValue(), this.equestions_type, this.ryid);

		this.equestions_type = equestions_type;
		this.ryid = ryid;
		if (this.equestions != null) {
			this.epqtId = new Long(this.equestions.getId()).toString();

		}
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:找到题目类型下的题目
	 * </p>
	 * 
	 * Created by [www] [Aug 18, 2009] Midified by
	 * 
	 * @return
	 */
	public String findtype() {

		epqts = this.epaperservice.findtype(paperId, equestions_type, ryid);

		this.ryid = ryid;

		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:考试后提交
	 * </p>
	 * 
	 * Created by [www] [Aug 18, 2009] Midified by
	 * 
	 */
	public String examover() {
		// 得到人员
		this.user = ((UserDetailsImpl) AcegiUtil.getUserDetails()).getUser();
		// 删除考试人考试状态
		this.epaperservice.delete();

		// 找到试卷
		this.epapers = this.epaperservice.getEPapersById(paperId);
		// 新建一个答卷
		EAnswerpaper eanswerpaper = new EAnswerpaper();
		eanswerpaper.setEpapers(epapers);
		eanswerpaper.setDjRyid(user.getUsername());
		eanswerpaper.setDjRymc(user.getRealname());
		eanswerpaper.setGroupId(user.getGroup().getId().toString());// 添加小组的id
		eanswerpaper.setDjJssj(new Date());
		Date jssj = new Date(this.jssj);
		eanswerpaper.setDjKssj(jssj);
		// System.out.println(new Date()+"时间啊");
		// /System.out.println(eanswerpaper.getDjJssj()+"数据里面的时间啊");
		this.eanswerpaperService.saveEAnswerpaper(eanswerpaper);
		this.epaperservice.saveEpaperquestions_tempAll(paperId, user
				.getUsername());
		return SUCCESS;
	}

	/**
	 * <p>
	 * Description:考试后提交
	 * </p>
	 * 
	 * Created by [www] [Aug 18, 2009] Midified by
	 * 
	 */
	public String exampaper() {
		
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getRequest().getSession().getServletContext()); 
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 得到人员
		this.user = ((UserDetailsImpl) AcegiUtil.getUserDetails()).getUser();
		// 删除考试人考试状态
		this.epaperservice.delete();
		this.epaperservice.updateLogMonitore(paperId, String.valueOf(user.getId()));
		// 找到试卷
		this.epapers = this.epaperservice.getEPapersById(paperId);
		this.jgfs = this.epapers.getSjBhgfs();
		// 新建一个答卷
		EAnswerpaper eanswerpaper = new EAnswerpaper();
		eanswerpaper.setEpapers(epapers);
		eanswerpaper.setDjRyid(user.getUsername().toString());//考试人员用户名(身份证号)
		eanswerpaper.setDjRymc(user.getRealname());
		eanswerpaper.setUserSex(user.getPassword());// 考试人员密码(准考证号)
		eanswerpaper.setUserStar(String.valueOf(user.getId()));// 考试人员id
		eanswerpaper.setUserDate(user.getCreatetime());// 考试人进中心时间
		if (!"4".equals(epapers.getPaperType())) {
			eanswerpaper.setFlag("0");
		} else {
			eanswerpaper.setFlag("1");
		}
		eanswerpaper.setDjSyzt(new Long(2));//2表示立即出分
		/*if("1".equals(epapers.getSjLjcf())){
			eanswerpaper.setDjSyzt(new Long(2));
		}*/
		eanswerpaper.setGroupId(user.getGroup().getId().toString());// 添加小组的id
		eanswerpaper.setDjJssj(new Date());
		Date jssj = new Date(this.jssj);
		eanswerpaper.setDjKssj(jssj);
		long eapid=this.eanswerpaperService.saveEAnswerpaper(eanswerpaper);
		List<Object> list = this.eanswerpaperService.submitPaper(eapid, String.valueOf(user.getId()),conn);
		// 找到试卷的所有题目
		/*因计算成绩比较慢，改用存储过程实现（submitPaper中调用）
		Iterator it = this.epapers.getEpaperquestionses().iterator();//比较慢
		double sjzf=0.0;
		while (it.hasNext()) {
			int px = 0;
			EPaperquestions epaperquestions = (EPaperquestions) it.next();
			long type = epaperquestions.getEquestions().getEquestiontype().getPriority();
			String truedaan=epaperquestions.getEquestions().getStDa();
			double defen=epaperquestions.getSjStfs();
			String stid = new Long(epaperquestions.getEquestions().getStId()).toString();
			// 新建一个答题
			EAnswerquestions eanswerquestions = new EAnswerquestions();
			eanswerquestions.setEanswerpaper(eanswerpaper);// 设置答题的答卷
			eanswerquestions.setEpaperquestions(epaperquestions);// 设置答题对应的试题
			eanswerquestions.setStPx(px);
			if (type == 8) {// 如果是多选题
				String[] daan = this.getRequest().getParameterValues("answer" + stid);
				if (daan != null) {
					String str_daan = "";
					for (int i = 0; i < daan.length; i++) {
						if (i == daan.length - 1) {
							str_daan += daan[i];
						} else {
							str_daan += daan[i] + "||";
						}
					}
					//if("1".equals(epapers.getSjLjcf())){
						if(str_daan.equals(truedaan)){
							eanswerquestions.setStDf(defen);
							eanswerquestions.setRight(new Long(1));
							sjzf=sjzf+defen;
						}
					//}
					eanswerquestions.setStDa(str_daan);// 设置答题
				} else {
					eanswerquestions.setStDa(null);// 设置答题
				}

			} else if (type == 3) {// 如果是判断题
				String daan = this.getRequest().getParameter("answer" + stid);
				eanswerquestions.setStDa(daan);// 设置答题
//					String daansm = this.getRequest().getParameter(
//							"answer" + stid + "sm");
				//if("1".equals(epapers.getSjLjcf())){
					if(truedaan.equals(daan)){
						eanswerquestions.setStDf(defen);
						eanswerquestions.setRight(new Long(1));
						sjzf=sjzf+defen;
					}
				//}
//				eanswerquestions.setStDasm(daansm);
			} else {
				String daan = this.getRequest().getParameter("answer" + stid);
				eanswerquestions.setStDa(daan);// 设置答题
				if(truedaan.equals(daan)){
					eanswerquestions.setStDf(defen);
					eanswerquestions.setRight(new Long(1));
					sjzf=sjzf+defen;
				}
			}
			this.eanswerpaperService.saveEAnswerquestions(eanswerquestions);
			px++;
		}
		this.eanswerpaperService.updateEanserPapersForZf(String.valueOf(eapid), sjzf);
		*/
		if("1".equals(epapers.getSjLjcf())){//判断是否立即处分
			//this.sjdf=sjzf;
			this.sjdf=Double.valueOf(String.valueOf(list.get(2)));
		}
		return SUCCESS;
	}
	
	public String checkResult(){
		EUser eUser = AcegiUtil.getEUser();
		List<Object> list = this.epaperservice.checkResult(eUser.getId(), paperId);
		this.epapers = (EPapers) list.get(0);
		this.sjdf = ((EAnswerpaper)list.get(1)).getDjZf();
		return SUCCESS;
	}

	/** 设置未参加考试原因 */
	public String nopartinexamCause() {
		this.epaperservice.delete();
		this.epapers = this.epaperservice.getEPapersById(paperId);
		String[] rys = ryid.split(",");
		String[] cas = cause.split(",");
		for (int i = 0; i < rys.length; i++) {
			this.eanswerpaperService.nopartinexamCause(epapers, rys[i], cas[i]);
		}
		return SUCCESS;
	}

	/** 提交拨测试卷 */
	public String examTelephonePaper() {
		//epaperservice.updateState(paperId, "1",null);// 试卷状态改完考试中
		epapers = this.epaperservice.getEPapersById(paperId);// 获取试卷信息
		user = epaperservice.queryUserById(ryid);// 获取拨测试卷答卷人员
		epaperservice.delete();// 删除考试人考试状态
		EAnswerpaper eanswerpaper = new EAnswerpaper();// 新建一个答卷
		eanswerpaper.setEpapers(epapers);
		eanswerpaper.setFlag("1");
		eanswerpaper.setDjRyid(user.getId().toString());
		eanswerpaper.setDjRymc(user.getRealname());
		eanswerpaper.setUserSex(user.getSex());// 考试人性别
		eanswerpaper.setUserStar(user.getUserstar());// 考试人进中心时间
		eanswerpaper.setUserDate(user.getCreatetime());// 考试人星级
		eanswerpaper.setGroupId(user.getGroup().getId().toString());// 添加小组的id
		this.eanswerpaperService.saveEAnswerpaper(eanswerpaper);
		// 找到试卷的所有题目
		Iterator it = this.epapers.getEpaperquestionses().iterator();
		while (it.hasNext()) {
			int px = 0;
			EPaperquestions epaperquestions = (EPaperquestions) it.next();
			long type = epaperquestions.getEquestions().getEquestiontype()
					.getPriority();
			String stid = new Long(epaperquestions.getEquestions().getStId())
					.toString();
			// 新建一个答题
			EAnswerquestions eanswerquestions = new EAnswerquestions();
			eanswerquestions.setEanswerpaper(eanswerpaper);// 设置答题的答卷
			eanswerquestions.setEpaperquestions(epaperquestions);// 设置答题对应的试题
			eanswerquestions.setStPx(px);
			this.eanswerpaperService.saveEAnswerquestions(eanswerquestions);
			px++;
		}
		return SUCCESS;
	}

	
	public double getSjdf() {
		return sjdf;
	}

	public void setSjdf(double sjdf) {
		this.sjdf = sjdf;
	}

	public String getSurplus() {
		return surplus;
	}

	public void setSurplus(String surplus) {
		this.surplus = surplus;
	}

	public List getPapers() {
		return papers;
	}

	public void setPapers(List papers) {
		this.papers = papers;
	}

	public EPapers getEpapers() {
		return epapers;
	}

	public void setEpapers(EPapers epapers) {
		this.epapers = epapers;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public EUser getUser() {
		return user;
	}

	public void setUser(EUser user) {
		this.user = user;
	}

	

	public Set getPaperquestions() {
		return paperquestions;
	}

	public void setPaperquestions(Set paperquestions) {
		this.paperquestions = paperquestions;
	}

	public List getEquestiontypes() {
		return equestiontypes;
	}

	public void setEquestiontypes(List equestiontypes) {
		this.equestiontypes = equestiontypes;
	}

	public void setEpaperquestionsService(
			EpaperquestionsService epaperquestionsService) {
		this.epaperquestionsService = epaperquestionsService;
	}

	public long getJssj() {
		return jssj;
	}

	public void setJssj(long jssj) {
		this.jssj = jssj;
	}

	public void setEanswerpaperService(EAnswerpaperService eanswerpaperService) {
		this.eanswerpaperService = eanswerpaperService;
	}

	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	public Long getSjZt() {
		return sjZt;
	}

	public void setSjZt(Long sjZt) {
		this.sjZt = sjZt;
	}

	public EPaperquestions_temp getEquestions() {
		return equestions;
	}

	public void setEquestions(EPaperquestions_temp equestions) {
		this.equestions = equestions;
	}

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public Long getEquestions_type() {
		return equestions_type;
	}

	public void setEquestions_type(Long equestions_type) {
		this.equestions_type = equestions_type;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public String getRyid() {
		return ryid;
	}

	public void setRyid(String ryid) {
		this.ryid = ryid;
	}

	public String getEpqtId() {
		return epqtId;
	}

	public void setEpqtId(String epqtId) {
		this.epqtId = epqtId;
	}

	public List<EPaperquestions_temp> getEpqts() {
		return epqts;
	}

	public void setEpqts(List<EPaperquestions_temp> epqts) {
		this.epqts = epqts;
	}

	public Long getAftersequence() {
		return aftersequence;
	}

	public void setAftersequence(Long aftersequence) {
		this.aftersequence = aftersequence;
	}

	public int getTypeall() {
		return Typeall;
	}

	public void setTypeall(int typeall) {
		Typeall = typeall;
	}

	public int getTypealldone() {
		return Typealldone;
	}

	public void setTypealldone(int typealldone) {
		Typealldone = typealldone;
	}

	public int getAll() {
		return all;
	}

	public void setAll(int all) {
		this.all = all;
	}

	public int getAlldone() {
		return alldone;
	}

	public void setAlldone(int alldone) {
		this.alldone = alldone;
	}

	public EPaperquestions_temp getEquestionsUnfinish() {
		return equestionsUnfinish;
	}

	public void setEquestionsUnfinish(EPaperquestions_temp equestionsUnfinish) {
		this.equestionsUnfinish = equestionsUnfinish;
	}

	public Long getUndone() {
		return Undone;
	}

	public void setUndone(Long undone) {
		Undone = undone;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getGzmc() {
		return gzmc;
	}

	public void setGzmc(String gzmc) {
		this.gzmc = gzmc;
	}

	public String getGzdj() {
		return gzdj;
	}

	public void setGzdj(String gzdj) {
		this.gzdj = gzdj;
	}

	public double getJgfs() {
		return jgfs;
	}

	public void setJgfs(double jgfs) {
		this.jgfs = jgfs;
	}
}
