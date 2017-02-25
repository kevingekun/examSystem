package com.wondersgroup.kaoshi.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.oa.util.StringUtil;
import com.wondersgroup.falcon.paper.model.EAnswerpaper;
import com.wondersgroup.falcon.paper.model.EAnswerquestions;
import com.wondersgroup.kaoshi.service.EAnswerpaperService;
import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.popedom.bo.EUser;
import com.wondersgroup.popedom.service.EUserService;

public class SyPaperAction extends AbstractPageNavAction {
	// 查询条件
	private String sjMc;
	private Date kksj;
	private String userid;// 当前登录的用户id

	// 审阅试卷
	private EAnswerpaper eanswerpaper;

	// 所有的试题类型
	private List equestiontypes;

	// 答卷id
	private String answerpaperid;
	// 可以审阅的试卷
	private List answerpapers;

	// 题目
	private List eanswerquestionses;

	// 审阅状态；
	private long djSyzt = 0;

	private String flag;

	// 答题service
	private EAnswerpaperService eanswerpaperService;
	private EUserService euserService;

	// 查询试卷和实体的连接表
	private EpaperquestionsService epaperquestionsService;

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

	public void setEanswerpaperService(EAnswerpaperService eanswerpaperService) {
		this.eanswerpaperService = eanswerpaperService;
	}

	@Override
	public String doAcion() throws Exception {
		this.pageReturn = this.eanswerpaperService.findsypaper(this.pageTool,
				new Long(djSyzt), this.sjMc, this.kksj, this.flag);
		this.answerpapers = pageReturn.getReturnList();
		AcegiUtil acegiUtil = new AcegiUtil();
		this.userid = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser()
				.getId().toString();
		return SUCCESS;
	}

	/**
	 * 
	 * <p>
	 * Description:显示审阅试卷的详细信息
	 * </p>
	 * 
	 * Created by [www] [Aug 19, 2009] Midified by [修改人] [修改时间]
	 * 
	 * @return
	 */
	public String toViewQuestion() {
		this.eanswerpaper = this.eanswerpaperService.load(new Long(
				answerpaperid));
		// 找到所有的答题
		eanswerquestionses = new ArrayList();
		eanswerquestionses.addAll(eanswerpaper.getEanswerquestionses());

		// 找到所有的类型
		this.equestiontypes = this.epaperquestionsService
				.findEQuestiontypeAll();
		int count = equestiontypes.size();

		// 找到我的角色
		AcegiUtil acegiUtil = new AcegiUtil();
		EUser user = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser();
		EUser euser = this.euserService.load(user.getId());
		/*
		 * //把这个人不能审批的类型去掉 for(int i=0;i<count;i++){ EQuestiontype
		 * eqt=(EQuestiontype) this.equestiontypes.get(i); String
		 * role_name="ROLE_"+eqt.getPriority(); System.out.println(role_name);
		 * boolean has=false; Iterator it=euser.getAuthorities().iterator();
		 * while(it.hasNext()){ EAuthority ea=(EAuthority) it.next();
		 * System.out.println(ea.getName()); if(role_name.equals(ea.getName())){
		 * has=true; break; } } if(!has){ //设置为不可用 eqt.setCanSy(1); } }
		 */
		return SUCCESS;
	}

	/**
	 * 
	 * 
	 * <p>
	 * Description:审阅试卷
	 * </p>
	 * 
	 * Created by [www] [Aug 19, 2009] Midified by [修改人] [修改时间]
	 * 
	 * @return
	 * @throws Exception
	 */
	public String eanswerQuestion() throws Exception {
		this.eanswerpaper = this.eanswerpaperService.load(new Long(
				answerpaperid));
		double sjdf = 0;
		int syover = 0;
		int over = 0;
		Iterator it = this.eanswerpaper.getEanswerquestionses().iterator();
		while (it.hasNext()) {
			EAnswerquestions eanswerquestions = (EAnswerquestions) it.next();
			String name = "defen" + eanswerquestions.getId();
			String defen = this.getRequest().getParameter(name);
			String stContentname = "content" + eanswerquestions.getId();
			String stContent = getRequest().getParameter(stContentname);
			String rrid = eanswerpaper.getEpapers().getPaperType();
			if (defen != null && !defen.equals("")) {
				Double stDf = new Double(defen);
				eanswerquestions.setStDf(stDf);
				sjdf += stDf.doubleValue();
				// 如果是一审，就把试题的状态设为一审完毕，如果是二则设置为二审完毕
				if (eanswerpaper.getDjSyzt() == 0) {
					eanswerquestions.setStsyzt(new Long(1));
				} else if (eanswerpaper.getDjSyzt() == 1) {
					eanswerquestions.setStsyzt(new Long(2));
				}
				if ("4".equals(rrid)) {// 如果是拨测试卷 则添加备注 和答卷的开始时间
					eanswerquestions.setStContent(stContent);// 添加备注
					if (eanswerpaper.getDjSyzt() == 0) {
						eanswerpaper.setDjKssj(new Date());// 如果是第一次审阅 设置开始时间
					}
					if (StringUtils.isBlank(eanswerquestions.getStUsername())) {
						if ((stContent != null && !"".equals(stContent))
								|| stDf > 0.0) {
							AcegiUtil acegiUtil = new AcegiUtil();
							EUser tts = ((UserDetailsImpl) acegiUtil
									.getUserDetails()).getUser();
							eanswerquestions.setStUsername(tts.getUsername()); // 记录答卷人id
						}
					}
				}
				eanswerpaperService.saveEAnswerquestions(eanswerquestions);
				// 如果是一审，则判断试题的一审状态都是1
				if (eanswerpaper.getDjSyzt() == 0) {
					if (eanswerquestions.getStsyzt() != null
							&& eanswerquestions.getStsyzt().longValue() == 1) {
						syover++;
					}
				} else if (eanswerpaper.getDjSyzt() == 1) {
					if (eanswerquestions.getStsyzt() != null
							&& eanswerquestions.getStsyzt().longValue() == 2) {
						syover++;
					}
				}
			}

		}
		// 审阅后的状态
		if (syover == eanswerpaper.getEanswerquestionses().size()) {
			long syhzt = this.djSyzt + 1;
			eanswerpaper.setDjSyzt(syhzt);
			over = 1;

		}
		eanswerpaper.setDjZf(sjdf);
		this.eanswerpaperService.saveEAnswerpaper(eanswerpaper);
		return doAcion();
	}

	/**
	 * 
	 * 
	 * <p>
	 * Description:审阅试卷
	 * </p>
	 * 
	 * Created by [www] [Aug 19, 2009] Midified by [修改人] [修改时间]
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String commit() throws Exception {
		String[] ids = answerpaperid.split(",");
		for (String id : ids) {
			this.eanswerpaper = this.eanswerpaperService.load(new Long(id));
			Iterator<EAnswerquestions> it = (Iterator<EAnswerquestions>) this.eanswerpaper
					.getEanswerquestionses().iterator();
			while (it.hasNext()) {
				EAnswerquestions eanswerquestions = (EAnswerquestions) it
						.next();
				eanswerquestions.setStsyzt(new Long(2));// 提交
				this.eanswerpaperService.saveEAnswerquestions(eanswerquestions);
			}
			eanswerpaper.setDjSyzt(2);
			System.out.println("--------------------"
					+ eanswerpaper.getDjSyzt());
			String rrid = eanswerpaper.getEpapers().getPaperType();
			if ("4".equals(rrid)) {
				eanswerpaper.setDjJssj(new Date());// 如果是拨测题 在提交的时候设置答卷结束时间
			}
			this.eanswerpaperService.saveEAnswerpaper(eanswerpaper);
			this.eanswerpaperService.saveEAnswerquentions(new Long(id)
					.longValue());
		}
		this.djSyzt = 1;
		this.pageReturn = this.eanswerpaperService.findsypaper(this.pageTool,
				new Long(djSyzt), this.sjMc, this.kksj, this.flag);
		this.answerpapers = pageReturn.getReturnList();
		return SUCCESS;
	}

	public List getAnswerpapers() {
		return answerpapers;
	}

	public void setAnswerpapers(List answerpapers) {
		this.answerpapers = answerpapers;
	}

	public long getDjSyzt() {
		return djSyzt;
	}

	public void setDjSyzt(long djSyzt) {
		this.djSyzt = djSyzt;
	}

	public String getAnswerpaperid() {
		return answerpaperid;
	}

	public void setAnswerpaperid(String answerpaperid) {
		this.answerpaperid = answerpaperid;
	}

	public List getEanswerquestionses() {
		return eanswerquestionses;
	}

	public void setEanswerquestionses(List eanswerquestionses) {
		this.eanswerquestionses = eanswerquestionses;
	}

	public EAnswerpaper getEanswerpaper() {
		return eanswerpaper;
	}

	public void setEanswerpaper(EAnswerpaper eanswerpaper) {
		this.eanswerpaper = eanswerpaper;
	}

	public void setEuserService(EUserService euserService) {
		this.euserService = euserService;
	}

	public String getSjMc() {
		return sjMc;
	}

	public void setSjMc(String sjMc) {
		this.sjMc = sjMc;
	}

	public Date getKksj() {
		return kksj;
	}

	public void setKksj(Date kksj) {
		this.kksj = kksj;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
