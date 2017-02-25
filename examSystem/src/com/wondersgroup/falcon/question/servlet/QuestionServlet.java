package com.wondersgroup.falcon.question.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import com.wondersgroup.falcon.Util.DateUtil;
import com.wondersgroup.falcon.Util.NavigateForm;
import com.wondersgroup.falcon.Util.RequestUtils;
import com.wondersgroup.falcon.Util.StringUtil;
import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.beans.auth.ProfessionBean;
import com.wondersgroup.falcon.question.beans.EBusinesstypeService;
import com.wondersgroup.falcon.question.beans.EImportanceService;
import com.wondersgroup.falcon.question.beans.EQuestionsService;
import com.wondersgroup.falcon.question.beans.EQuestiontypeService;
import com.wondersgroup.falcon.question.model.EBusinesstype;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.falcon.question.model.EQuestionsVO;
import com.wondersgroup.falcon.question.model.Tmdot;
import com.wondersgroup.kaoshi.bo.Ae02;
import com.wondersgroup.popedom.bo.EUser;

/**
 * 
 * <p>
 * Title:[试题信息管理类]
 * </p>
 * <p>
 * Description: [实现对试题题库的增、删、改、查等功能]
 * </p>
 * 
 * Created by [Kevin Liang] [2009-6-26] Midified by [修改人] [修改时间]
 * 
 */
public class QuestionServlet extends HttpServlet {
	private static final Log log = LogFactory.getLog(QuestionServlet.class);

	private int defaultsize = 10;
	String mes = "";

	/**
	 * Constructor of the object.
	 */
	public QuestionServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String actionType = RequestUtils.getString(request, "actionType", "");
		String myaction = RequestUtils.getString(request, "myaction", "");
		log.info("试题管理servlet开始了！！！ actionType：" + actionType);

		if (actionType.equals("add")) {// 试题添加操作
			add(request, response);
		} else if (actionType.equals("query") && myaction.equals("")) {// 试题查询
			query(request, response);
		} else if (actionType.equals("query") && myaction.equals("del")) {// 试题删除、
			del(request, response);
		} else if (actionType.equals("query") && myaction.equals("chek")) {// 试题审核-选中试题审核通过
			chek(request, response);
		}else if(actionType.equals("query") && myaction.equals("passAll")){//试题全部审核通过
			passAll(request, response);
		} else if (actionType.equals("query") && myaction.equals("modifyload")) {// 试题修改页面加载
			loadModify(request, response);
		} else if (actionType.equals("query") && myaction.equals("modify")) {// 试题修改
			doModify(request, response);
		} else if (actionType.equals("query") && myaction.equals("checklist")) {// 试题审核列表查询
			querychecklist(request, response);
		} else if (actionType.equals("query") && myaction.equals("checkload")) {// 试题审核页面加载
			loadCheck(request, response);
		} else if (actionType.equals("query") && myaction.equals("unpasslist")) {// 试题审核不通过列表
			queryunpasslist(request, response);
		} else if (actionType.equals("query") && myaction.equals("check")) {// 试题审核
			doCheck(request, response);
		} else if (actionType.equals("query")
				&& myaction.equals("modifyload_again")) {// 试题不通过页面加载
			loadModify_again(request, response);
		} else if (actionType.equals("query")
				&& myaction.equals("modify_again")) {// 不通过试题修改
			doModify_again(request, response);
		} else if (actionType.equals("query") && myaction.equals("modify_st")) {// 由于政策法规修改引起的试题修改
			doModify_st(request, response);
		} else if (actionType.equals("query")
				&& myaction.equals("modifyload_st")) {// 由于政策法规修改试题修改页面加载
			loadModify_st(request, response);
		} else if (actionType.equals("query")
				&& myaction.equals("modify_st_del")) {// 由于政策法规删除试题
			delModify_st(request, response);
		} else if (actionType.equals("query") && myaction.equals("turn")) {// 题目(
			// 为考试题
			// )
			// 的批量修改
			turn(request, response);
		} else if (actionType.equals("query") && myaction.equals("turnjd")) {// 题目
			// (
			// 为阶段性试题
			// )
			// 的批量修改
			turnjd(request, response);
		} else if (actionType.equals("query") && myaction.equals("unturnjd")) {// 题目
			// (
			// 为非阶段性试题
			// )
			// 的批量修改
			unturnjd(request, response);
		} else if (actionType.equals("query") && myaction.equals("turnback")) {// 题目
			// (
			// 是非考试题
			// )
			// 的批量修改
			turnback(request, response);
		} else {
			log.info("********************试题信息未知操作类型********************");
		}

	}

	/**
	 * 
	 * <p>
	 * Description:[执行修改]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-15] Midified by [修改人] [修改时间]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings({ "unused" })
	private void doModify(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("执行修改操作");
		EQuestionsService eQuestionsService = new EQuestionsService();
		EBusinesstypeService eBusinesstypeService = new EBusinesstypeService();
		EImportanceService eImportanceService = new EImportanceService();
		EQuestiontypeService eQuestiontypeService = new EQuestiontypeService();

		// 查询要修改记录
		EQuestions question = eQuestionsService.findById(new Long(RequestUtils
				.getLong(request, "stId", 0)));
		// 题目来源 ST_CC
		question.setStCc(RequestUtils.getString(request, "ST_CC", ""));
		// 出题专家
		// question.setStSyryId(RequestUtils.getString(request, "ST_SYRY_ID",
		// ""));
		// 难易度 ST_ZYXID
		long zyxid = RequestUtils.getLong(request, "ST_ZYXID", 1);
		question.setEimportance(eImportanceService
				.findEImportanceById(new Long(zyxid)));
		// 备注
		// question.setStFjlj(RequestUtils.getString(request, "ST_FJLJ", ""));

		// 试题类型
		int STLX = RequestUtils.getInt(request, "ST_LXID", 0);
		question.setEquestiontype(eQuestiontypeService
				.findEQuestiontypeById(new Long(STLX)));
		question.setStModefy(0);
		// 重要程度 BX_TYPE
		question.setBxType(RequestUtils.getString(request, "BX_TYPE", ""));
		// 题干
		question.setStTg(RequestUtils.getString(request, "ST_TG", "").trim());
		// 审核状态
		question.setStJyxgcs(0);
		// 鉴定要素 jdys
		// long jdysid = RequestUtils.getLong(request, "jdysid", 0);
		// 题库id
		String TKID = RequestUtils.getString(request, "ST_NODE_NAME",
				question.getStNodeName());
		question.setStNodeName(TKID);

		question.setStCheck(new Long(0));

		/*
		 * // 鉴定要素 ST_YWLX_ID long ywlxid = RequestUtils.getLong(request,
		 * "ST_YWLXID", 0);
		 * //question.setEbusinesstype(eBusinesstypeService.findEBusinesstypeById
		 * (new Long(ywlxid))); // 重要性 ST_ZYXID long zyxid =
		 * RequestUtils.getLong(request, "ST_ZYXID", 0);
		 * question.setEimportance(eImportanceService .findEImportanceById(new
		 * Long(zyxid)));
		 * 
		 * // 是否考试题 question.setStKszy(RequestUtils.getLong(request, "ST_KSZY",
		 * 0)); // 文件出处 question.setStCc(RequestUtils.getString(request,
		 * "ST_CC", "")); // 文号 question.setStWh(RequestUtils.getString(request,
		 * "ST_WH", "")); // 文号id if (question.getStNodeId() == null ||
		 * question.getStNodeId().equals("")) {
		 * question.setStNodeId(RequestUtils.getString(request, "WH_ID", ""));
		 * question.setStNodeName(RequestUtils.getString(request, "WH_TYPE",
		 * "")); } // 题干 question.setStTg(RequestUtils.getString(request,
		 * "ST_TG", "")); // question.setStLrsj(RequestUtils.getDate(request,
		 * "lrsj", new // Date()));
		 * question.setStXgsj(RequestUtils.getDate(request, "xgsj", new
		 * Date())); // 设为未审核状态 question.setStCheck(new Long(0).longValue()); //
		 * 录入人员 AcegiUtil acegiUtil = new AcegiUtil();
		 * question.setStMtryId(((UserDetailsImpl) acegiUtil.getUserDetails())
		 * .getUser().getUsername()); // 根据试题类型 int STLX =
		 * RequestUtils.getInt(request, "ST_LXID", 0); // 试题类型
		 * question.setEquestiontype(eQuestiontypeService
		 * .findEQuestiontypeById(new Long(STLX)));
		 * 
		 * // 审核状态 1表示通过 2表示不通过 0表示未审核 question.setStCheck(new
		 * Long(0).longValue()); // 将修改次数置为0 question.setStJyxgcs(new
		 * Long(0).longValue());
		 */

		switch (STLX) {
		case 2:// 单选
			log.info("====================单选题===========================");
			// 单选题选项，规则是以||分割
			question.setStXx(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "singleoption"), "||"));
			// 单选题答案，规则是以||分割
			question.setStDa(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "singleoptionkey"),
					"||"));
			break;
		case 8:// 多选
			log.info("======================多选题=========================");
			// 多选题选项，规则是以||分割
			question.setStXx(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "manyoption"), "||"));
			// 多选题答案，规则是以||分割
			question.setStDa(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "manyoptionkey"),
					"||"));
			break;
		case 3:// 判断题
			log.info("========================判断题=======================");
			// 判断题答案，如果是-1则为无法取值
			question.setStDa(RequestUtils
					.getString(request, "verdictkey", "-1"));
			break;
		/*
		 * case 4:// 判断说明题
		 * log.info("=======================判断说明题========================"); //
		 * 判断说明题答案，如果是-1则为无法取值 question.setStDa(RequestUtils.getString(request,
		 * "verdictsaykey", "-1")); // 判断说明题答案 question.setStDasm(RequestUtils
		 * .getString(request, "verdictsay", "")); break; case 5:// 录音题
		 * log.info("========================录音题=======================");
		 * 
		 * // 录音题答案 question.setStDa(RequestUtils.getString(request,
		 * "recordkey", "")); // 录音题录音文件路径 question.setStFjlj(RequestUtils
		 * .getString(request, "recordpath", "")); break;
		 */
		case 1:// 填空题
			log.info("======================填空题=========================");
			// 填空题答案
			question.setStDa(RequestUtils.getString(request, "fillkey", ""));
			break;
		case 4:// 问答题
				// 问答题答案
			log.info("======================问答题答案=========================");
			question.setStDa(RequestUtils.getString(request, "askkey", ""));
			break;
		/*
		 * case 8:// 案例分析题 // 案例分析题答案
		 * log.info("=========================案例分析题答案======================");
		 * question.setStDa(RequestUtils.getString(request, "casekey", ""));
		 * break;
		 */
		case 5:// 计算题
				// 计算题题答案
			log.info("=========================计算题题答案======================");
			question.setStDa(RequestUtils
					.getString(request, "calculatekey", ""));
			break;
		/*
		 * case 10:// 不定向选择题
		 * log.info("=========================不定向选择题答案======================");
		 * // 不定向选择题选项，规则是以||分割
		 * question.setStXx(StringUtil.stringSpell(RequestUtils
		 * .getStringValues(request, "uncertainoption"), "||")); //
		 * 不定向选择题答案，规则是以||分割
		 * question.setStDa(StringUtil.stringSpell(RequestUtils
		 * .getStringValues(request, "uncertainoptionkey"), "||")); break;
		 */
		case 6:// 论述题
				// 论述题答案
			log.info("=========================论述题答案======================");
			question.setStDa(RequestUtils.getString(request, "discusskey", ""));
			break;
		case 7:// 绘图
				// 点库题答案
			log.info("=========================绘图题答案======================");
			question.setStDa(RequestUtils.getString(request, "", ""));
			break;
		/*
		 * case 12:// 点库题 // 点库题答案
		 * log.info("=========================点库题答案======================");
		 * question .setStDa(RequestUtils.getString(request, "databasekey",
		 * "")); break; case 13:// 拨测题 // 点库题答案
		 * log.info("=========================点库题答案======================");
		 * question.setStDa(RequestUtils .getString(request, "telephonekey",
		 * "")); break;
		 */
		default:
			log.info("试题类型选择错误！");
		}
		if(question.getStXx()!=null){
			question.setStXx(question.getStXx().replace(" ", ""));
		}
		String sjid = RequestUtils.getString(request, "stsjid", "");
		String errorInfo = "";
		try {
			/*if (!"".equals(sjid)&&!"null".equals(sjid)) {
				int count = eQuestionsService.getNumOfQuestionSet(sjid);
				if (count >= 100) {
					errorInfo = "本套试卷试题数量已经满100题!";
					throw new Exception();
				}
			}*/
			//图片上传
			MultiPartRequestWrapper mWrapper = (MultiPartRequestWrapper) request;//struts2与servlet可能有冲突，不能用fileupload
			if(mWrapper.getFiles("img")!=null){
				File file = mWrapper.getFiles("img")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "题目图片大小超出范围！";
					throw new Exception();
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImg(b);
			}
			if(mWrapper.getFiles("ST_IMG_A")!=null){
				File file = mWrapper.getFiles("ST_IMG_A")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "选项A:图片大小超出范围！";
					throw new Exception();
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImgA(b);
			}
			if(mWrapper.getFiles("ST_IMG_B")!=null){
				File file = mWrapper.getFiles("ST_IMG_B")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "选项B:图片大小超出范围！";
					throw new Exception();
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImgB(b);
			}
			if(mWrapper.getFiles("ST_IMG_C")!=null){
				File file = mWrapper.getFiles("ST_IMG_C")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "选项C:图片大小超出范围！";
					throw new Exception();
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImgC(b);
			}
			if(mWrapper.getFiles("ST_IMG_D")!=null){
				File file = mWrapper.getFiles("ST_IMG_D")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "选项D:图片大小超出范围！";
					throw new Exception();
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImgD(b);
			}
			if(mWrapper.getFiles("ST_IMG_E")!=null){
				File file = mWrapper.getFiles("ST_IMG_E")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "选项E:图片大小超出范围！";
					throw new Exception();
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImgE(b);
			}
			if(mWrapper.getFiles("ST_IMG_F")!=null){
				File file = mWrapper.getFiles("ST_IMG_F")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "选项F:图片大小超出范围！";
					throw new Exception();
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImgF(b);
			}
			if(mWrapper.getFiles("ST_IMG_G")!=null){
				File file = mWrapper.getFiles("ST_IMG_G")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "选项G:图片大小超出范围！";
					throw new Exception();
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImgG(b);
			}
			
			eQuestionsService.saveOrUpdateQuestion(question);
			queryunpasslist(request, response);
			
		} catch (Exception e) {
			log.debug("修改失败！");
			e.printStackTrace();
			request.setAttribute("info", "试题信息修改");
			request.setAttribute("errorinfo", "试题信息修改失败!");
			request.getRequestDispatcher("common/errorPage.jsp").forward(
					request, response);
		}

	}

	private void doModify_again(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("执行修改操作");
		EQuestionsService eQuestionsService = new EQuestionsService();
		EBusinesstypeService eBusinesstypeService = new EBusinesstypeService();
		EImportanceService eImportanceService = new EImportanceService();
		EQuestiontypeService eQuestiontypeService = new EQuestiontypeService();

		// 查询要修改记录
		EQuestions question = eQuestionsService.findById(new Long(RequestUtils
				.getLong(request, "stId", 0)));
		// 鉴定要素 ST_YWLX_ID
		long ywlxid = RequestUtils.getLong(request, "ST_YWLXID", 0);
		// question.setEbusinesstype(eBusinesstypeService
		// .findEBusinesstypeById(new Long(ywlxid)));
		// 重要性 ST_ZYXID
		long zyxid = RequestUtils.getLong(request, "ST_ZYXID", 0);
		question.setEimportance(eImportanceService
				.findEImportanceById(new Long(zyxid)));

		// 是否考试题
		question.setStKszy(RequestUtils.getLong(request, "ST_KSZY", 0));
		// 文件出处
		question.setStCc(RequestUtils.getString(request, "ST_CC", ""));
		// 文号
		question.setStWh(RequestUtils.getString(request, "ST_WH", ""));
		// 文号id
		if (question.getStNodeId() == null || question.getStNodeId().equals("")) {
			question.setStNodeId(RequestUtils.getString(request, "WH_ID", ""));
			question.setStNodeName(RequestUtils.getString(request, "WH_TYPE",
					""));
		}
		// 题干
		question.setStTg(RequestUtils.getString(request, "ST_TG", ""));
		// question.setStLrsj(RequestUtils.getDate(request, "lrsj", new
		// Date()));
		question.setStXgsj(RequestUtils.getDate(request, "xgsj", new Date()));
		// 设为未审核状态
		question.setStCheck(new Long(0).longValue());
		// 录入人员
		AcegiUtil acegiUtil = new AcegiUtil();
		question.setStMtryId(((UserDetailsImpl) acegiUtil.getUserDetails())
				.getUser().getUsername());
		// 根据试题类型
		int STLX = RequestUtils.getInt(request, "ST_LXID", 0);
		// 试题类型
		question.setEquestiontype(eQuestiontypeService
				.findEQuestiontypeById(new Long(STLX)));

		// 审核状态 1表示通过 2表示不通过 0表示未审核
		question.setStCheck(new Long(0).longValue());

		switch (STLX) {
		case 1:// 单选
			log.info("====================单选题===========================");
			// 单选题选项，规则是以||分割
			question.setStXx(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "singleoption"), "||"));
			// 单选题答案，规则是以||分割
			question.setStDa(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "singleoptionkey"),
					"||"));
			break;
		case 2:// 多选
			log.info("======================多选题=========================");
			// 多选题选项，规则是以||分割
			question.setStXx(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "manyoption"), "||"));
			// 多选题答案，规则是以||分割
			question.setStDa(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "manyoptionkey"),
					"||"));
			break;
		case 3:// 判断题
			log.info("========================判断题=======================");
			// 判断题答案，如果是-1则为无法取值
			question.setStDa(RequestUtils
					.getString(request, "verdictkey", "-1"));
			break;
		case 4:// 判断说明题
			log.info("=======================判断说明题========================");
			// 判断说明题答案，如果是-1则为无法取值
			question.setStDa(RequestUtils.getString(request, "verdictsaykey",
					"-1"));
			// 判断说明题答案
			question.setStDasm(RequestUtils
					.getString(request, "verdictsay", ""));
			break;
		case 5:// 录音题
			log.info("========================录音题=======================");
			// 录音题答案
			question.setStDa(RequestUtils.getString(request, "recordkey", ""));
			// 录音题录音文件路径
			question.setStFjlj(RequestUtils
					.getString(request, "recordpath", ""));
			break;
		case 6:// 填空题
			log.info("======================填空题=========================");
			// 填空题答案
			question.setStDa(RequestUtils.getString(request, "fillkey", ""));
			break;
		case 7:// 问答题
				// 问答题答案
			log.info("======================问答题答案=========================");
			question.setStDa(RequestUtils.getString(request, "askkey", ""));
			break;
		case 8:// 案例分析题
				// 案例分析题答案
			log.info("=========================案例分析题答案======================");
			question.setStDa(RequestUtils.getString(request, "casekey", ""));
			break;
		case 9:// 计算题
				// 计算题题答案
			log.info("=========================计算题题答案======================");
			question.setStDa(RequestUtils
					.getString(request, "calculatekey", ""));
			break;
		case 10:// 不定向选择题
			log.info("=========================不定向选择题答案======================");
			// 不定向选择题选项，规则是以||分割
			question.setStXx(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "uncertainoption"),
					"||"));
			// 不定向选择题答案，规则是以||分割
			question.setStDa(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "uncertainoptionkey"),
					"||"));
			break;
		case 11:// 论述题
			// 论述题答案
			log.info("=========================论述题答案======================");
			question.setStDa(RequestUtils.getString(request, "discusskey", ""));
			break;
		case 12:// 点库题
			// 点库题答案
			log.info("=========================点库题答案======================");
			question.setStDa(RequestUtils.getString(request, "databasekey", ""));
			break;
		case 13:// 拨测题
			// 点库题答案
			log.info("=========================点库题答案======================");
			question.setStDa(RequestUtils
					.getString(request, "telephonekey", ""));
			break;
		default:
			log.info("试题类型选择错误！");
		}
		try {
			eQuestionsService.saveOrUpdateQuestion(question);
			queryunpasslist(request, response);

		} catch (Exception e) {
			log.debug("修改失败！");
			e.printStackTrace();
			request.setAttribute("info", "试题信息修改");
			request.setAttribute("errorinfo", "试题信息修改失败!");
			request.getRequestDispatcher("common/errorPage.jsp").forward(
					request, response);
		}

	}
	/**
	 * 试题批量审核，全部审核通过
	 * 
	 * @author gkk
	 * @date 2017-1-4 上午10:14:24
	 */
	private void passAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		EQuestionsService eQuestionsService = new EQuestionsService();
		try {
			eQuestionsService.passAll();
			querychecklist(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("backUrl","QuestionServlet?myaction=checklist");
			request.setAttribute("wrongMessage", "批量审核失败!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	private void doCheck(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("执行修改操作");
		EQuestionsService eQuestionsService = new EQuestionsService();

		String stId = request.getParameter("stId");
		String state = request.getParameter("state");
		String advice = request.getParameter("ST_ADVICE");
		EQuestions question = eQuestionsService.findById(Long.parseLong(stId));
		question.setStCheck(Long.parseLong(state));
		question.setStAdvice(advice);
		// 业务日志
		if (question.getEbusinesstype() != null) {
			Ae02 ae02 = new Ae02();
			ae02 = eQuestionsService.findAe02BystId(Long.parseLong(stId));
			ae02.setCae220("011");
			eQuestionsService.saveOrUpdateAe02(ae02);
		}
		try {
			eQuestionsService.saveOrUpdateQuestion(question);
			querychecklist(request, response);

		} catch (Exception e) {
			log.debug("审核失败！");
			e.printStackTrace();
			request.setAttribute("info", "试题信息审核");
			request.setAttribute("errorinfo", "试题信息审核失败!");
			request.getRequestDispatcher("common/errorPage.jsp").forward(
					request, response);
		}
	}

	private void doModify_st(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("执行修改操作");
		EQuestionsService eQuestionsService = new EQuestionsService();
		EBusinesstypeService eBusinesstypeService = new EBusinesstypeService();
		EImportanceService eImportanceService = new EImportanceService();
		EQuestiontypeService eQuestiontypeService = new EQuestiontypeService();

		// 查询要修改记录
		EQuestions question = eQuestionsService.findById(new Long(RequestUtils
				.getLong(request, "stId", 0)));
		// 鉴定要素 ST_YWLX_ID
		long ywlxid = RequestUtils.getLong(request, "ST_YWLXID", 0);
		// question.setEbusinesstype(eBusinesstypeService
		// .findEBusinesstypeById(new Long(ywlxid)));
		// 重要性 ST_ZYXID
		long zyxid = RequestUtils.getLong(request, "ST_ZYXID", 0);
		question.setEimportance(eImportanceService
				.findEImportanceById(new Long(zyxid)));

		// 是否考试题
		question.setStKszy(RequestUtils.getLong(request, "ST_KSZY", 0));
		// 文件出处
		question.setStCc(RequestUtils.getString(request, "ST_CC", ""));
		// 文号
		question.setStWh(RequestUtils.getString(request, "ST_WH", ""));

		// 工种
		question.setBxType(RequestUtils.getString(request, "ST_BAOXIAN", ""));
		// 文号id
		if (question.getStNodeId() == null || question.getStNodeId().equals("")) {
			question.setStNodeId(RequestUtils.getString(request, "WH_ID", ""));
			question.setStNodeName(RequestUtils.getString(request, "WH_TYPE",
					""));
		}
		// 题干
		question.setStTg(RequestUtils.getString(request, "ST_TG", ""));
		// question.setStLrsj(RequestUtils.getDate(request, "lrsj", new
		// Date()));
		question.setStXgsj(RequestUtils.getDate(request, "xgsj", new Date()));

		// 录入人员
		AcegiUtil acegiUtil = new AcegiUtil();
		question.setStMtryId(((UserDetailsImpl) acegiUtil.getUserDetails())
				.getUser().getUsername());
		// 根据试题类型
		int STLX = RequestUtils.getInt(request, "ST_LXID", 0);
		// 试题类型
		question.setEquestiontype(eQuestiontypeService
				.findEQuestiontypeById(new Long(STLX)));
		// 修改完需要审核
		question.setStCheck(new Long(0).longValue());
		// 有关资料库的修改状态
		question.setStModefy(new Long(0).longValue());

		switch (STLX) {
		case 1:// 单选
			log.info("====================单选题===========================");
			// 单选题选项，规则是以||分割
			question.setStXx(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "singleoption"), "||"));
			// 单选题答案，规则是以||分割
			question.setStDa(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "singleoptionkey"),
					"||"));
			break;
		case 2:// 多选
			log.info("======================多选题=========================");
			// 多选题选项，规则是以||分割
			question.setStXx(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "manyoption"), "||"));
			// 多选题答案，规则是以||分割
			question.setStDa(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "manyoptionkey"),
					"||"));
			break;
		case 3:// 判断题
			log.info("========================判断题=======================");
			// 判断题答案，如果是-1则为无法取值
			question.setStDa(RequestUtils
					.getString(request, "verdictkey", "-1"));
			break;
		case 4:// 判断说明题
			log.info("=======================判断说明题========================");
			// 判断说明题答案，如果是-1则为无法取值
			question.setStDa(RequestUtils.getString(request, "verdictsaykey",
					"-1"));
			// 判断说明题答案
			question.setStDasm(RequestUtils
					.getString(request, "verdictsay", ""));
			break;
		case 5:// 录音题
			log.info("========================录音题=======================");
			// 录音题答案
			question.setStDa(RequestUtils.getString(request, "recordkey", ""));
			// 录音题录音文件路径
			question.setStFjlj(RequestUtils
					.getString(request, "recordpath", ""));
			break;
		case 6:// 填空题
			log.info("======================填空题=========================");
			// 填空题答案
			question.setStDa(RequestUtils.getString(request, "fillkey", ""));
			break;
		case 7:// 问答题
				// 问答题答案
			log.info("======================问答题答案=========================");
			question.setStDa(RequestUtils.getString(request, "askkey", ""));
			break;
		case 8:// 案例分析题
				// 案例分析题答案
			log.info("=========================案例分析题答案======================");
			question.setStDa(RequestUtils.getString(request, "casekey", ""));
			break;
		case 9:// 计算题
				// 计算题题答案
			log.info("=========================计算题题答案======================");
			question.setStDa(RequestUtils
					.getString(request, "calculatekey", ""));
			break;
		case 10:// 不定向选择题
			log.info("=========================不定向选择题答案======================");
			// 不定向选择题选项，规则是以||分割
			question.setStXx(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "uncertainoption"),
					"||"));
			// 不定向选择题答案，规则是以||分割
			question.setStDa(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "uncertainoptionkey"),
					"||"));
			break;
		case 11:// 论述题
			// 论述题答案
			log.info("=========================论述题答案======================");
			question.setStDa(RequestUtils.getString(request, "discusskey", ""));
			break;
		case 12:// 点库题
			// 点库题答案
			log.info("=========================点库题答案======================");
			question.setStDa(RequestUtils.getString(request, "databasekey", ""));
			break;
		case 13:// 拨测题
			// 点库题答案
			log.info("=========================点库题答案======================");
			question.setStDa(RequestUtils
					.getString(request, "telephonekey", ""));
			break;
		default:
			log.info("试题类型选择错误！");
		}
		try {
			eQuestionsService.saveOrUpdateQuestion(question);
			// EModefy eModefy=new EModefy();
			// eModefy.setSt_id(question.getStId());
			// eModefy.setZt(1);
			// eModefy.setDt(new Date());
			// eQuestionsService.savelog(eModefy)
			log.info("试题修改成功！");
			request.getRequestDispatcher(
					"question/sucess.jsp?id=" + question.getStNodeId()
							+ "&name=" + question.getStNodeName()).forward(
					request, response);
			// System.out.println("----------------");
			// 修改后查询

		} catch (Exception e) {
			log.debug("修改失败！");
			e.printStackTrace();
			request.setAttribute("info", "试题信息修改");
			request.setAttribute("errorinfo", "试题信息修改失败!");
			request.getRequestDispatcher("common/errorPage.jsp").forward(
					request, response);
		}

	}

	/**
	 * 
	 * <p>
	 * Description:[加载修改页面]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-15] Midified by [修改人] [修改时间]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void loadModify_st(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("执行修改页面加载");
		long stid = RequestUtils.getLong(request, "stid", 0);
		log.info("================" + stid);

		// 查询要修改的试题系信息
		EQuestionsService eQuestionsService = new EQuestionsService();
		EQuestions o = eQuestionsService.findById(new Long(stid));
		request.setAttribute("question", o);

		// 查询条件----------------------------------------------------------
		String subject = RequestUtils.getString(request, "subject", "");
		String documentnum = RequestUtils.getString(request, "documentnum", "");
		long examsign = RequestUtils.getLong(request, "examsign", 0);
		Date recorddatebegin = RequestUtils.getDate(request, "recorddatebegin",
				null, "yyyy-MM-dd");
		Date recorddateend = RequestUtils.getDate(request, "recorddateend",
				null, "yyyy-MM-dd");
		Date modifiydatebegin = RequestUtils.getDate(request,
				"modifiydatebegin", null, "yyyy-MM-dd");
		Date modifiydateend = RequestUtils.getDate(request, "modifiydateend",
				null, "yyyy-MM-dd");
		long businesstype = RequestUtils.getLong(request, "businesstype", 0);
		long importance = RequestUtils.getLong(request, "importance", 0);
		long questiontype = RequestUtils.getLong(request, "questiontype", 0);
		// 排序
		String orderby = RequestUtils.getString(request, "orderby", "stLrsj");
		// 分页
		int currpage = RequestUtils.getInt(request, "currpage", 1);// 默认1
		// currpage
		int pagesize = RequestUtils.getInt(request, "pagesize", defaultsize);// 默认行行数
		// pagesize
		int pagenum = RequestUtils.getInt(request, "pagenum", 5);

		// 保存查询条件----------------------------------------------------------------
		// ----
		request.setAttribute("subject", subject);
		request.setAttribute("documentnum", documentnum);
		request.setAttribute("examsign", new Long(examsign));
		request.setAttribute("recorddatebegin", recorddatebegin);
		request.setAttribute("recorddateend", recorddateend);
		request.setAttribute("modifiydatebegin", modifiydatebegin);
		request.setAttribute("modifiydateend", modifiydateend);
		request.setAttribute("businesstype", new Long(businesstype));
		request.setAttribute("importance", new Long(importance));
		request.setAttribute("questiontype", new Long(questiontype));
		request.setAttribute("orderby", orderby);
		request.setAttribute("currpage", new Long(currpage));
		request.setAttribute("pagesize", new Long(pagesize));
		request.setAttribute("pagenum", new Long(pagenum));

		request.getRequestDispatcher("question/st_modefy.jsp").forward(request,
				response);

	}

	/**
	 * 
	 * <p>
	 * Description:[执行单个删除操作]
	 * </p>
	 * 
	 * Created by [zhuyuanping] [2011-1-13] Midified by [修改人] [修改时间]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delModify_st(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String myaction = RequestUtils.getString(request, "myaction", "");
		// 执行单个删除操作
		EQuestionsService eQuestionsService = new EQuestionsService();
		EQuestions question = eQuestionsService.findById(new Long(RequestUtils
				.getLong(request, "stId", 0)));
		long[] deleteid = RequestUtils.getLongValues(request, "stId", 0);
		log.info("要删除记录数:" + deleteid.length);
		try {
			int row = eQuestionsService.delete(RequestUtils.toArray(deleteid));

			if (row < 1) {
				request.setAttribute("backUrl",
						"QuestionServlet?actionType=query&examsign=1");
				request.setAttribute("wrongMessage", "信息删除失败!试题已经被试卷使用。");
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			} else if (row == 0) {
				request.setAttribute("backUrl",
						"QuestionServlet?actionType=query&examsign=1");
				request.setAttribute("wrongMessage", "您未选择要删除记录!");
				request.getRequestDispatcher("error.jsp").forward(request,
						response);

			} else {
				request.setAttribute("myaction", "");
				request.getRequestDispatcher(
						"question/sucess.jsp?id=" + question.getStNodeId()
								+ "&name=" + question.getStNodeName()).forward(
						request, response);
			}
		} catch (Exception e) {
			request.setAttribute("backUrl",
					"QuestionServlet?actionType=query&examsign=1");
			request.setAttribute("wrongMessage", "信息删除失败!试题已经被试卷使用。");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}

	}

	/**
	 * 
	 * <p>
	 * Description:[加载修改页面]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-15] Midified by [修改人] [修改时间]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void loadModify(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("执行修改页面加载");
		long stid = RequestUtils.getLong(request, "stid", 0);
		log.info("================" + stid);

		// 查询要修改的试题系信息
		EQuestionsService eQuestionsService = new EQuestionsService();
		EQuestionsVO o = eQuestionsService.findById_loadcheck(new Long(stid));
		request.setAttribute("question", o);

		String gzid = o.getId_job();
		List<Object> list = new ProfessionBean().getDjById_job(gzid);
		Iterator<Object> it = list.iterator();
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			List<Object> object = (List<Object>) it.next();
			sb.append(object.get(1) + ",");
		}
		String dqdj = sb.toString();
		dqdj = dqdj.substring(0, dqdj.length() - 1);
		
		String sjid = RequestUtils.getString(request, "sjid", "0");

		// 查询条件----------------------------------------------------------
		String subject = RequestUtils.getString(request, "subject", "");
		String documentnum = RequestUtils.getString(request, "documentnum", "");
		long examsign = RequestUtils.getLong(request, "examsign", 0);
		Date recorddatebegin = RequestUtils.getDate(request, "recorddatebegin",
				null, "yyyy-MM-dd");
		Date recorddateend = RequestUtils.getDate(request, "recorddateend",
				null, "yyyy-MM-dd");
		Date modifiydatebegin = RequestUtils.getDate(request,
				"modifiydatebegin", null, "yyyy-MM-dd");
		Date modifiydateend = RequestUtils.getDate(request, "modifiydateend",
				null, "yyyy-MM-dd");
		long businesstype = RequestUtils.getLong(request, "businesstype", 0);
		long importance = RequestUtils.getLong(request, "importance", 0);
		long questiontype = RequestUtils.getLong(request, "questiontype", 0);
		// 排序
		String orderby = RequestUtils.getString(request, "orderby", "stLrsj");
		// 分页
		int currpage = RequestUtils.getInt(request, "currpage", 1);// 默认1
		// currpage
		int pagesize = RequestUtils.getInt(request, "pagesize", defaultsize);// 默认行行数
		// pagesize
		int pagenum = RequestUtils.getInt(request, "pagenum", 5);

		// 保存查询条件----------------------------------------------------------------
		// ----
		request.setAttribute("subject", subject);
		request.setAttribute("documentnum", documentnum);
		request.setAttribute("examsign", new Long(examsign));
		request.setAttribute("recorddatebegin", recorddatebegin);
		request.setAttribute("recorddateend", recorddateend);
		request.setAttribute("modifiydatebegin", modifiydatebegin);
		request.setAttribute("modifiydateend", modifiydateend);
		request.setAttribute("businesstype", new Long(businesstype));
		request.setAttribute("importance", new Long(importance));
		request.setAttribute("questiontype", new Long(questiontype));
		request.setAttribute("orderby", orderby);
		request.setAttribute("currpage", new Long(currpage));
		request.setAttribute("pagesize", new Long(pagesize));
		request.setAttribute("pagenum", new Long(pagenum));
		request.setAttribute("dqdj", dqdj);
		request.setAttribute("stsjid", o.getStSjId());
		request.setAttribute("sjid", sjid);

		request.getRequestDispatcher("question/question_modify.jsp").forward(
				request, response);

	}

	private void loadModify_again(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("执行修改页面加载");
		long stid = RequestUtils.getLong(request, "stid", 0);
		log.info("================" + stid);

		// 查询要修改的试题系信息
		EQuestionsService eQuestionsService = new EQuestionsService();
		EQuestions o = eQuestionsService.findById(new Long(stid));
		request.setAttribute("question", o);

		// 查询条件----------------------------------------------------------
		String subject = RequestUtils.getString(request, "subject", "");
		String documentnum = RequestUtils.getString(request, "documentnum", "");
		long examsign = RequestUtils.getLong(request, "examsign", 0);
		Date recorddatebegin = RequestUtils.getDate(request, "recorddatebegin",
				null, "yyyy-MM-dd");
		Date recorddateend = RequestUtils.getDate(request, "recorddateend",
				null, "yyyy-MM-dd");
		Date modifiydatebegin = RequestUtils.getDate(request,
				"modifiydatebegin", null, "yyyy-MM-dd");
		Date modifiydateend = RequestUtils.getDate(request, "modifiydateend",
				null, "yyyy-MM-dd");
		long businesstype = RequestUtils.getLong(request, "businesstype", 0);
		long importance = RequestUtils.getLong(request, "importance", 0);
		long questiontype = RequestUtils.getLong(request, "questiontype", 0);
		// 排序
		String orderby = RequestUtils.getString(request, "orderby", "stLrsj");
		// 分页
		int currpage = RequestUtils.getInt(request, "currpage", 1);// 默认1
		// currpage
		int pagesize = RequestUtils.getInt(request, "pagesize", defaultsize);// 默认行行数
		// pagesize
		int pagenum = RequestUtils.getInt(request, "pagenum", 5);

		// 保存查询条件----------------------------------------------------------------
		// ----
		request.setAttribute("subject", subject);
		request.setAttribute("documentnum", documentnum);
		request.setAttribute("examsign", new Long(examsign));
		request.setAttribute("recorddatebegin", recorddatebegin);
		request.setAttribute("recorddateend", recorddateend);
		request.setAttribute("modifiydatebegin", modifiydatebegin);
		request.setAttribute("modifiydateend", modifiydateend);
		request.setAttribute("businesstype", new Long(businesstype));
		request.setAttribute("importance", new Long(importance));
		request.setAttribute("questiontype", new Long(questiontype));
		request.setAttribute("orderby", orderby);
		request.setAttribute("currpage", new Long(currpage));
		request.setAttribute("pagesize", new Long(pagesize));
		request.setAttribute("pagenum", new Long(pagenum));

		request.getRequestDispatcher("question/modefy_again.jsp").forward(
				request, response);

	}

	/**
	 * 
	 * <p>
	 * Description:[审核页面加载]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-15] Midified by [修改人] [修改时间]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void loadCheck(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("执行修改页面加载");
		long stid = RequestUtils.getLong(request, "stid", 0);
		log.info("================" + stid);

		// 查询要修改的试题系信息
		EQuestionsService eQuestionsService = new EQuestionsService();
		EQuestionsVO o = eQuestionsService.findById_loadcheck(new Long(stid));
		request.setAttribute("question", o);
		// 获取当前工种的所有等级
		String gzid = o.getId_job();
		List<Object> list = new ProfessionBean().getDjById_job(gzid);
		Iterator<Object> it = list.iterator();
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			List<Object> object = (List<Object>) it.next();
			sb.append(object.get(1) + ",");
		}
		String dqdj = sb.toString();
		dqdj = dqdj.substring(0, dqdj.length() - 1);
		
		String sjid = RequestUtils.getString(request, "sjid", "");

		// 查询条件----------------------------------------------------------
		String subject = RequestUtils.getString(request, "subject", "");
		String documentnum = RequestUtils.getString(request, "documentnum", "");
		long examsign = RequestUtils.getLong(request, "examsign", 0);
		Date recorddatebegin = RequestUtils.getDate(request, "recorddatebegin",
				null, "yyyy-MM-dd");
		Date recorddateend = RequestUtils.getDate(request, "recorddateend",
				null, "yyyy-MM-dd");
		Date modifiydatebegin = RequestUtils.getDate(request,
				"modifiydatebegin", null, "yyyy-MM-dd");
		Date modifiydateend = RequestUtils.getDate(request, "modifiydateend",
				null, "yyyy-MM-dd");
		// long businesstype = RequestUtils.getLong(request, "businesstype", 0);
		long importance = RequestUtils.getLong(request, "importance", 0);
		long questiontype = RequestUtils.getLong(request, "questiontype", 0);
		// 排序
		String orderby = RequestUtils.getString(request, "orderby", "stLrsj");
		// 分页
		int currpage = RequestUtils.getInt(request, "currpage", 1);// 默认1
		// currpage
		int pagesize = RequestUtils.getInt(request, "pagesize", defaultsize);// 默认行行数
		// pagesize
		int pagenum = RequestUtils.getInt(request, "pagenum", 5);
		// 保存查询条件----------------------------------------------------------------
		// ----
		request.setAttribute("subject", subject);
		request.setAttribute("documentnum", documentnum);
		request.setAttribute("examsign", new Long(examsign));
		request.setAttribute("recorddatebegin", recorddatebegin);
		request.setAttribute("recorddateend", recorddateend);
		request.setAttribute("modifiydatebegin", modifiydatebegin);
		request.setAttribute("modifiydateend", modifiydateend);
		// request.setAttribute("businesstype", new Long(businesstype));
		request.setAttribute("importance", new Long(importance));
		request.setAttribute("questiontype", new Long(questiontype));
		request.setAttribute("orderby", orderby);
		request.setAttribute("currpage", new Long(currpage));
		request.setAttribute("pagesize", new Long(pagesize));
		request.setAttribute("pagenum", new Long(pagenum));
		request.setAttribute("dqdj", dqdj);
		request.setAttribute("sjid", sjid);

		request.getRequestDispatcher("question/question_check.jsp").forward(
				request, response);

	}

	/**
	 * 
	 * <p>
	 * Description:[执行删除操作]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-15] Midified by [zhuyuanping]
	 * [2011-1-13]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String myaction = RequestUtils.getString(request, "myaction", "");
		String deleteidValue = RequestUtils.getString(request, "deleteid", "");
		long[] deleteid;
		// 执行删除操作
		EQuestionsService eQuestionsService = new EQuestionsService();

		if ("".equals(deleteidValue)) {
			deleteid = RequestUtils.getLongValues(request, "stId", 0);// 单个删除
		} else {
			deleteid = RequestUtils.getLongValues(request, "deleteid", 0);// 批量删除
		}

		log.info("要删除记录数:" + deleteid.length);
		try {
			int row = eQuestionsService.delete(RequestUtils.toArray(deleteid));

			if (row < 1) {
				request.setAttribute("backUrl",
						"QuestionServlet?actionType=query&examsign=1");
				request.setAttribute("wrongMessage", "信息删除失败!试题已经被试卷使用。");
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			} else if (row == 0) {
				request.setAttribute("backUrl",
						"QuestionServlet?actionType=query&examsign=1");
				request.setAttribute("wrongMessage", "您未选择要删除记录!");
				request.getRequestDispatcher("error.jsp").forward(request,
						response);

			} else {
				request.setAttribute("myaction", "");
				// querychecklist(request, response);
				query(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("backUrl",
					"QuestionServlet?actionType=query&examsign=1");
			request.setAttribute("wrongMessage", "信息删除失败!试题已经被试卷使用。");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}

	}
	/**
	 * 批量审核
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void chek(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] ids = RequestUtils.getStringValues(request, "checkid");
		EQuestionsService eQuestionsService = new EQuestionsService();
		try {
			eQuestionsService.batchCheck(ids);
			querychecklist(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("backUrl","QuestionServlet?myaction=checklist");
			request.setAttribute("wrongMessage", "批量审核失败!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * 
	 * <p>
	 * Description:[执行删除操作]
	 * </p>
	 * 
	 * Created by [Godspeed He] [2009-7-15] Midified by [修改人] [修改时间]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void turn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String myaction = RequestUtils.getString(request, "myaction", "");
		// 执行删除操作
		EQuestionsService eQuestionsService = new EQuestionsService();
		long[] deleteid = RequestUtils.getLongValues(request, "deleteid", 0);
		log.info("要转化记录数:" + deleteid.length);
		try {
			int row = eQuestionsService.turn(RequestUtils.toArray(deleteid));

			if (row < 1) {
				request.setAttribute("backUrl",
						"QuestionServlet?actionType=query&examsign=1");
				request.setAttribute("wrongMessage", "信息转化失败!");
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			} else if (row == 0) {
				request.setAttribute("backUrl",
						"QuestionServlet?actionType=query&examsign=1");
				request.setAttribute("wrongMessage", "您未选择要转化记录!");
				request.getRequestDispatcher("error.jsp").forward(request,
						response);

			} else {
				request.setAttribute("myaction", "");
				query(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("backUrl",
					"QuestionServlet?actionType=query&examsign=1");
			request.setAttribute("wrongMessage", "信息转化失败!");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}

	}

	private void turnjd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String myaction = RequestUtils.getString(request, "myaction", "");
		// 执行删除操作
		EQuestionsService eQuestionsService = new EQuestionsService();
		long[] deleteid = RequestUtils.getLongValues(request, "deleteid", 0);
		log.info("要转化记录数:" + deleteid.length);
		try {
			int row = eQuestionsService.turnjd(RequestUtils.toArray(deleteid));

			if (row < 1) {
				request.setAttribute("backUrl",
						"QuestionServlet?actionType=query&examsign=1");
				request.setAttribute("wrongMessage", "信息转化失败!");
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			} else if (row == 0) {
				request.setAttribute("backUrl",
						"QuestionServlet?actionType=query&examsign=1");
				request.setAttribute("wrongMessage", "您未选择要转化记录!");
				request.getRequestDispatcher("error.jsp").forward(request,
						response);

			} else {
				request.setAttribute("myaction", "");
				query(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("backUrl",
					"QuestionServlet?actionType=query&examsign=1");
			request.setAttribute("wrongMessage", "信息转化失败!");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}

	}

	private void unturnjd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String myaction = RequestUtils.getString(request, "myaction", "");
		// 执行删除操作
		EQuestionsService eQuestionsService = new EQuestionsService();
		long[] deleteid = RequestUtils.getLongValues(request, "deleteid", 0);
		log.info("要转化记录数:" + deleteid.length);
		try {
			int row = eQuestionsService
					.unturnjd(RequestUtils.toArray(deleteid));

			if (row < 1) {
				request.setAttribute("backUrl",
						"QuestionServlet?actionType=query&examsign=1");
				request.setAttribute("wrongMessage", "信息转化失败!");
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			} else if (row == 0) {
				request.setAttribute("backUrl",
						"QuestionServlet?actionType=query&examsign=1");
				request.setAttribute("wrongMessage", "您未选择要转化记录!");
				request.getRequestDispatcher("error.jsp").forward(request,
						response);

			} else {
				request.setAttribute("myaction", "");
				query(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("backUrl",
					"QuestionServlet?actionType=query&examsign=1");
			request.setAttribute("wrongMessage", "信息转化失败!");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}

	}

	private void turnback(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String myaction = RequestUtils.getString(request, "myaction", "");
		// 执行删除操作
		EQuestionsService eQuestionsService = new EQuestionsService();
		long[] deleteid = RequestUtils.getLongValues(request, "deleteid", 0);
		log.info("要转化记录数:" + deleteid.length);
		try {
			int row = eQuestionsService
					.turnback(RequestUtils.toArray(deleteid));

			if (row < 1) {
				request.setAttribute("backUrl",
						"QuestionServlet?actionType=query&examsign=1");
				request.setAttribute("wrongMessage", "信息转化失败!");
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			} else if (row == 0) {
				request.setAttribute("backUrl",
						"QuestionServlet?actionType=query&examsign=1");
				request.setAttribute("wrongMessage", "您未选择要转化记录!");
				request.getRequestDispatcher("error.jsp").forward(request,
						response);

			} else {
				request.setAttribute("myaction", "");
				query(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("backUrl",
					"QuestionServlet?actionType=query&examsign=1");
			request.setAttribute("wrongMessage", "信息转化失败!");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}

	}

	/**
	 * 
	 * <p>
	 * Description:[审核列表查询操作]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-15] Midified by [修改人] [修改时间]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void querychecklist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		EQuestionsService eQuestionsService = new EQuestionsService();

		log.info("********************试题信息查询条件********************");
		log.info("工种id：" + request.getParameter("gzid"));
		log.info("重要性：" + request.getParameter("importance"));
		log.info("试题类型：" + request.getParameter("questiontype"));
		log.info("考试题：" + request.getParameter("examsign"));
		log.info("文号：" + request.getParameter("documentnum"));
		log.info("题干：" + request.getParameter("subject"));
		log.info("录入时间：" + request.getParameter("recorddatebegin") + "--"
				+ request.getParameter("recorddateend"));
		log.info("修改时间：" + request.getParameter("modifiydatebegin") + "--"
				+ request.getParameter("modifiydateend"));
		log.info("********************试题信息查询条件end********************");

		// 题目
		String subject = RequestUtils.getString(request, "subject", "");
		// 录入时间
		String rdbegin = RequestUtils.getString(request, "recorddatebegin", "");
		String rdend = RequestUtils.getString(request, "recorddateend", "");

		Date recorddatebegin = DateUtil.formatDate(rdbegin.equals("") ? ""
				: rdbegin + " 00:00", "yyyy-MM-dd HH:mm");
		Date recorddateend = DateUtil.formatDate(rdend.equals("") ? "" : rdend
				+ " 23:59", "yyyy-MM-dd HH:mm");

		long professions = RequestUtils.getLong(request, "gzid", 0);
		String forward = RequestUtils.getString(request, "forward", "");// 审核页面参数
		String gzid = "";
		String gzdj = "";
		// 审核页面跳转到此，需判断
		if (!forward.equals("1")) {
			// 工种id
			gzid = RequestUtils.getString(request, "gzid", "");
			// 工种等级
			gzdj = RequestUtils.getString(request, "gzdj", "");
		}
		// 当前工种所有等级
		String dqdj = RequestUtils.getString(request, "dqdj", "");
		// int gzdj = RequestUtils.getInt(request, "gzdj", 0);
		// 难易度
		long difficulty = RequestUtils.getLong(request, "difficulty", 0);
		// 试题类型
		long questiontype = RequestUtils.getLong(request, "questiontype", 0);
		// ---未知
		long Paperid = RequestUtils.getLong(request, "Paperid", 0);
		// 排序
		String orderby = RequestUtils.getString(request, "orderby", "stLrsj");
		// 分页
		// currpage
		int currpage = RequestUtils.getInt(request, "currpage", 1);// 默认1
		// pagesize
		int pagesize = RequestUtils.getInt(request, "pagesize", defaultsize);// 默认行行数
		// pagenum
		int pagenum = RequestUtils.getInt(request, "pagenum", 10);
		// 所属试卷（整套试题录入时使用）
		String sjid = RequestUtils.getString(request, "sjid", "0");

		// 以下未用到
		// 文号
		String documentnum = RequestUtils.getString(request, "documentnum", "");
		// 试题性质
		long examsign = RequestUtils.getLong(request, "examsign", 0);
		// 试题出处
		String chuchu = RequestUtils.getString(request, "chuchu", "");
		// 修改时间
		String mdbegin = RequestUtils
				.getString(request, "modifiydatebegin", "");
		String mdend = RequestUtils.getString(request, "modifiydateend", "");
		Date modifiydatebegin = DateUtil.formatDate(mdbegin.equals("") ? ""
				: mdbegin + " 00:00", "yyyy-MM-dd HH:mm");
		Date modifiydateend = DateUtil.formatDate(mdend.equals("") ? "" : mdend
				+ " 23:59", "yyyy-MM-dd HH:mm");

		// 查询总录数
		int total = eQuestionsService.getQuestionsUncheckedTotalCount(chuchu,
				subject, documentnum, examsign, recorddatebegin, recorddateend,
				modifiydatebegin, modifiydateend, professions, difficulty,
				questiontype, Paperid, 0, gzid, gzdj, sjid);
		//System.out.println("ttttttttt" + total);
		// 分页
		NavigateForm navigateform = new NavigateForm();
		navigateform.setCurrpage(currpage);
		navigateform.setPagesize(pagesize);
		navigateform.setTotal(total);
		navigateform.setPagenum(pagenum);

		// 查询记录集
		List list = eQuestionsService.findQuestionsUnchecked(chuchu, subject,
				documentnum, examsign, recorddatebegin, recorddateend,
				modifiydatebegin, modifiydateend, professions, difficulty,
				questiontype, Paperid, currpage, pagesize, orderby, 0, gzid,
				gzdj, sjid);

		log.info("查询结果记录数：" + list.size());
		// 保存查询结果
		request.setAttribute("list", list);
		request.setAttribute("navigateform", navigateform);
		// 保存查询条件
		request.setAttribute("subject", subject);
		request.setAttribute("documentnum", documentnum);
		request.setAttribute("examsign", new Long(examsign));
		request.setAttribute("recorddatebegin", recorddatebegin);
		request.setAttribute("recorddateend", recorddateend);
		request.setAttribute("modifiydatebegin", modifiydatebegin);
		request.setAttribute("modifiydateend", modifiydateend);
		request.setAttribute("professions", professions);
		request.setAttribute("difficulty", new Long(difficulty));
		request.setAttribute("questiontype", new Long(questiontype));
		request.setAttribute("Paperid", new Long(Paperid));
		request.setAttribute("orderby", orderby);
		request.setAttribute("gzid", gzid);
		request.setAttribute("gzdj", gzdj);
		request.setAttribute("dqdj", dqdj);
		request.setAttribute("sjid", sjid);

		log.info(request.getContextPath());
		request.getRequestDispatcher("question/check_list.jsp").forward(
				request, response);

	}

	/**
	 * 
	 * <p>
	 * Description:[审核不通过列表查询操作]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-15] Midified by [修改人] [修改时间]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	private void queryunpasslist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		EQuestionsService eQuestionsService = new EQuestionsService();

		log.info("********************试题信息查询条件********************");
		log.info("鉴定要素：" + request.getParameter("businesstype"));
		log.info("重要性：" + request.getParameter("importance"));
		log.info("试题类型：" + request.getParameter("questiontype"));
		log.info("考试题：" + request.getParameter("examsign"));
		log.info("文号：" + request.getParameter("documentnum"));
		log.info("题干：" + request.getParameter("subject"));
		log.info("录入时间：" + request.getParameter("recorddatebegin") + "--"
				+ request.getParameter("recorddateend"));
		log.info("修改时间：" + request.getParameter("modifiydatebegin") + "--"
				+ request.getParameter("modifiydateend"));
		log.info("题干：" + request.getParameter("subject"));
		log.info("********************试题信息查询条件end********************");

		// 题目
		String subject = RequestUtils.getString(request, "subject", "");
		// 录入时间
		String rdbegin = RequestUtils.getString(request, "recorddatebegin", "");
		String rdend = RequestUtils.getString(request, "recorddateend", "");

		Date recorddatebegin = DateUtil.formatDate(rdbegin.equals("") ? ""
				: rdbegin + " 00:00", "yyyy-MM-dd HH:mm");
		Date recorddateend = DateUtil.formatDate(rdend.equals("") ? "" : rdend
				+ " 23:59", "yyyy-MM-dd HH:mm");

		long professions = RequestUtils.getLong(request, "gzid", 0);
		// 工种id
		String gzid = RequestUtils.getString(request, "gzid", "");
		// 工种等级
		String gzdj = RequestUtils.getString(request, "gzdj", "");
		// 难易度
		long difficulty = RequestUtils.getLong(request, "difficulty", 0);
		// 试题类型
		long questiontype = RequestUtils.getLong(request, "questiontype", 0);
		// ---未知
		long Paperid = RequestUtils.getLong(request, "Paperid", 0);
		// 排序
		String orderby = RequestUtils.getString(request, "orderby", "stLrsj");
		// 分页
		// currpage
		int currpage = RequestUtils.getInt(request, "currpage", 1);// 默认1
		// pagesize
		int pagesize = RequestUtils.getInt(request, "pagesize", defaultsize);// 默认行行数
		// pagenum
		int pagenum = RequestUtils.getInt(request, "pagenum", 10);

		// 以下未用到
		// 文号
		String documentnum = RequestUtils.getString(request, "documentnum", "");
		// 试题性质
		long examsign = RequestUtils.getLong(request, "examsign", 0);
		// 试题出处
		String chuchu = RequestUtils.getString(request, "chuchu", "");
		// 修改时间
		String mdbegin = RequestUtils
				.getString(request, "modifiydatebegin", "");
		String mdend = RequestUtils.getString(request, "modifiydateend", "");
		Date modifiydatebegin = DateUtil.formatDate(mdbegin.equals("") ? ""
				: mdbegin + " 00:00", "yyyy-MM-dd HH:mm");
		Date modifiydateend = DateUtil.formatDate(mdend.equals("") ? "" : mdend
				+ " 23:59", "yyyy-MM-dd HH:mm");

		AcegiUtil acegiUtil = new AcegiUtil();
		EUser user = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser();
		// 查询总录数
		int total = eQuestionsService.getQuestionsUnpassTotalCount(chuchu,
				subject, documentnum, examsign, recorddatebegin, recorddateend,
				modifiydatebegin, modifiydateend, professions, difficulty,
				questiontype, Paperid, 0, gzid, gzdj);
		// 分页
		NavigateForm navigateform = new NavigateForm();
		navigateform.setCurrpage(currpage);
		navigateform.setPagesize(pagesize);
		navigateform.setTotal(total);
		navigateform.setPagenum(pagenum);

		// 查询记录集
		List list = eQuestionsService.findQuestionsUnpass(chuchu, subject,
				documentnum, examsign, recorddatebegin, recorddateend,
				modifiydatebegin, modifiydateend, professions, difficulty,
				questiontype, Paperid, currpage, pagesize, orderby, 0, gzid,
				gzdj);

		log.info("查询结果记录数：" + list.size());
		// 保存查询结果
		request.setAttribute("list", list);
		request.setAttribute("navigateform", navigateform);
		// 保存查询条件
		request.setAttribute("subject", subject);
		request.setAttribute("documentnum", documentnum);
		request.setAttribute("examsign", new Long(examsign));
		request.setAttribute("recorddatebegin", recorddatebegin);
		request.setAttribute("recorddateend", recorddateend);
		request.setAttribute("modifiydatebegin", modifiydatebegin);
		request.setAttribute("modifiydateend", modifiydateend);
		request.setAttribute("professions", professions);
		request.setAttribute("difficulty", new Long(difficulty));
		request.setAttribute("questiontype", new Long(questiontype));
		request.setAttribute("Paperid", new Long(Paperid));
		request.setAttribute("orderby", orderby);
		request.setAttribute("gzid", gzid);
		request.setAttribute("gzdj", gzdj);

		log.info(request.getContextPath());
		request.getRequestDispatcher("/question/unpass_list.jsp").forward(
				request, response);

	}

	/**
	 * 
	 * <p>
	 * Description:[查询操作]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-15] Midified by [修改人] [修改时间]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes" })
	private void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EQuestionsService eQuestionsService = new EQuestionsService();

		log.info("********************试题信息查询条件********************");
		log.info("鉴定要素：" + request.getParameter("businesstype"));
		log.info("重要性：" + request.getParameter("importance"));
		log.info("试题类型：" + request.getParameter("questiontype"));
		log.info("考试题：" + request.getParameter("examsign"));
		log.info("文号：" + request.getParameter("documentnum"));
		log.info("题干：" + request.getParameter("subject"));
		log.info("录入时间：" + request.getParameter("recorddatebegin") + "--"
				+ request.getParameter("recorddateend"));
		log.info("修改时间：" + request.getParameter("modifiydatebegin") + "--"
				+ request.getParameter("modifiydateend"));
		log.info("题干：" + request.getParameter("subject"));
		log.info("出题人：" + request.getParameter("mtryid"));
		log.info("********************试题信息查询条件end********************");

		// 查询条件
		// 题目
		String subject = RequestUtils.getString(request, "subject", "");
		// 录入时间
		String rdbegin = RequestUtils.getString(request, "recorddatebegin", "");
		String rdend = RequestUtils.getString(request, "recorddateend", "");

		Date recorddatebegin = DateUtil.formatDate(rdbegin.equals("") ? ""
				: rdbegin + " 00:00", "yyyy-MM-dd HH:mm");
		Date recorddateend = DateUtil.formatDate(rdend.equals("") ? "" : rdend
				+ " 23:59", "yyyy-MM-dd HH:mm");

		long professions = RequestUtils.getLong(request, "gzid", 0);
		// 工种id
		String gzid = RequestUtils.getString(request, "gzid", "");
		// 工种等级
		String gzdj = RequestUtils.getString(request, "gzdj", "");
		// 当前工种对应的所有的等级
		String dqdj = RequestUtils.getString(request, "dqdj", "");
		// 难易度
		long difficulty = RequestUtils.getLong(request, "difficulty", 0);
		// 试题类型
		long questiontype = RequestUtils.getLong(request, "questiontype", 0);
		// ---未知
		long Paperid = RequestUtils.getLong(request, "Paperid", 0);
		// 排序
		String orderby = RequestUtils.getString(request, "orderby", "stLrsj");
		// 分页
		// currpage
		int currpage = RequestUtils.getInt(request, "currpage", 1);// 默认1
		// pagesize
		int pagesize = RequestUtils.getInt(request, "pagesize", defaultsize);// 默认行行数
		// pagenum
		int pagenum = RequestUtils.getInt(request, "pagenum", 10);
		// 审核状态
		int state = RequestUtils.getInt(request, "state", 3);
		// 所属试卷（整套录入的试题）
		String sjid = RequestUtils.getString(request, "sjid", "0");

		// 以下未用到
		// 文号
		String documentnum = RequestUtils.getString(request, "documentnum", "");
		// 试题性质
		long examsign = RequestUtils.getLong(request, "examsign", 0);
		// 试题出处
		String chuchu = RequestUtils.getString(request, "chuchu", "");
		// 修改时间
		String mdbegin = RequestUtils
				.getString(request, "modifiydatebegin", "");
		String mdend = RequestUtils.getString(request, "modifiydateend", "");
		Date modifiydatebegin = DateUtil.formatDate(mdbegin.equals("") ? ""
				: mdbegin + " 00:00", "yyyy-MM-dd HH:mm");
		Date modifiydateend = DateUtil.formatDate(mdend.equals("") ? "" : mdend
				+ " 23:59", "yyyy-MM-dd HH:mm");

		// 查询总录数
		int total = eQuestionsService.getQuestionsTotalCount(chuchu, subject,
				documentnum, examsign, recorddatebegin, recorddateend,
				modifiydatebegin, modifiydateend, professions, difficulty,
				questiontype, Paperid, 0, gzid, gzdj, state, sjid);
		// 分页
		NavigateForm navigateform = new NavigateForm();
		navigateform.setCurrpage(currpage);
		navigateform.setPagesize(pagesize);
		navigateform.setTotal(total);
		navigateform.setPagenum(pagenum);

		// 查询记录集
		List list = eQuestionsService.findQuestions(chuchu, subject,
				documentnum, examsign, recorddatebegin, recorddateend,
				modifiydatebegin, modifiydateend, professions, difficulty,
				questiontype, Paperid, currpage, pagesize, orderby, 0, gzid,
				gzdj, state, sjid);

		log.info("查询结果记录数：" + list.size());
		// 保存查询结果
		request.setAttribute("list", list);
		request.setAttribute("navigateform", navigateform);
		// 保存查询条件
		request.setAttribute("subject", subject);
		request.setAttribute("documentnum", documentnum);
		request.setAttribute("examsign", new Long(examsign));
		request.setAttribute("recorddatebegin", recorddatebegin);
		request.setAttribute("recorddateend", recorddateend);
		request.setAttribute("modifiydatebegin", modifiydatebegin);
		request.setAttribute("modifiydateend", modifiydateend);
		request.setAttribute("professions", new Long(professions));
		request.setAttribute("difficulty", new Long(difficulty));
		request.setAttribute("questiontype", new Long(questiontype));
		request.setAttribute("Paperid", new Long(Paperid));
		request.setAttribute("orderby", orderby);
		request.setAttribute("gzid", gzid);
		request.setAttribute("gzdj", gzdj);
		request.setAttribute("state", state);
		request.setAttribute("dqdj", dqdj);
		request.setAttribute("sjid", sjid);

		log.info(request.getContextPath());
		request.getRequestDispatcher("question/question_list.jsp").forward(
				request, response);

	}

	/**
	 * 
	 * <p>
	 * Description:[增加操作]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-1] Midified by [修改人] [修改时间]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// //拿到表单的formhash
		// String formhash=request.getParameter("formhash");
		// ServletRequest session = null;
		// //拿到session里面的集合
		// Set<String>formhashSession=(Set<String>)session.getAttribute(
		// "formhashSession");
		// //如果没有 ,则是重复提交 或者是非法提交
		// if(formhashSession==null||formhashSession.contains(formhash)){
		// System.out.println("重复提交");
		//
		// }else {
		//
		// log.info("====================参数===========================");
		// log.info("鉴定要素："+request.getParameter("ST_YWLXID"));
		// log.info("重要性："+request.getParameter("ST_ZYXID"));
		// log.info("试题类型："+request.getParameter("ST_LXID"));
		// log.info("考试题："+request.getParameter("ST_KSZY"));
		// log.info("出处："+request.getParameter("ST_CC"));
		// log.info("文号："+request.getParameter("ST_WH"));
		// log.info("题干："+request.getParameter("ST_TG"));
		// log.info("=====================参数========================");
		
		/*request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");*/
		/*// 为解析类提供配置信息
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(getServletContext().getRealPath("/upload")));  
		factory.setSizeThreshold(10);
		// 创建解析类的实例
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 开始解析
		//sfu.setFileSizeMax(1024 * 400);
		// 每个表单域中数据会封装到一个对应的FileItem对象上
		try {
			List items = sfu.parseRequest(mWrapper);
			// 区分表单域
			for (int i = 0; i < items.size(); i++) {
				DiskFileItem c= (DiskFileItem)items.get(i);
				FileItem item = (FileItem)items.get(i);
				// isFormField为true，表示这不是文件上传表单域
				if (!item.isFormField()) {
					ServletContext sctx = getServletContext();
					// 获得存放文件的物理路径
					// upload下的某个文件夹 得到当前在线的用户 找到对应的文件夹

					String path = sctx.getRealPath("/upload");
					System.out.println(path);
					// 获得文件名
					String fileName = item.getName();
					System.out.println(fileName);
					// 该方法在某些平台(操作系统),会返回路径+文件名
					fileName = fileName
							.substring(fileName.lastIndexOf("/") + 1);
					File file = new File(path + "\\" + fileName);
					if (!file.exists()) {
						item.write(file);
						// 将上传图片的名字记录到数据库中

						response.sendRedirect("/upload/ok.html");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		log.info("********************试题信息增加操作********************");

		EQuestionsService eQuestionsService = new EQuestionsService();
		EImportanceService eImportanceService = new EImportanceService();
		EQuestiontypeService eQuestiontypeService = new EQuestiontypeService();
		EQuestions question = new EQuestions();
		Ae02 ae02 = new Ae02();
		// 题目来源 ST_CC
		question.setStCc(RequestUtils.getString(request, "ST_CC", ""));
		// 等级
		// question.setStWh(RequestUtils.getString(request, "ST_WH", ""));
		// 出题专家
		question.setStSyryId(RequestUtils.getString(request, "ST_SYRY_ID", ""));
		// 难易度 ST_ZYXID
		long zyxid = RequestUtils.getLong(request, "ST_ZYXID", 1);// 整套试题录入时重要程度默认为1
		question.setEimportance(eImportanceService
				.findEImportanceById(new Long(zyxid)));
		// 备注
		question.setStFjlj(RequestUtils.getString(request, "ST_FJLJ", ""));

		// 试题类型
		int STLX = RequestUtils.getInt(request, "ST_LXID", 0);
		question.setEquestiontype(eQuestiontypeService
				.findEQuestiontypeById(new Long(STLX)));
		question.setStModefy(0);
		// 重要程度 BX_TYPE
		question.setBxType(RequestUtils.getString(request, "BX_TYPE", ""));
		// 题干
		question.setStTg(RequestUtils.getString(request, "ST_TG", "").trim());
		// 审核状态
		question.setStJyxgcs(0);
		// 鉴定要素 jdys
		long jdysid = RequestUtils.getLong(request, "jdysid", 0);
		// 题库id
		String TKID = RequestUtils.getString(request, "ST_NODE_NAME", "");
		question.setStNodeName(TKID);
		// 所属试卷
		String sjid = RequestUtils.getString(request, "sjid", "");
		question.setStSjid(sjid);
		
		String stfz = RequestUtils.getString(request, "ST_FZ", "1");
		question.setStFz(stfz);
		
		String sjType = RequestUtils.getString(request, "sjType", "");

		// question.setEbusinesstype(eBusinesstypeService
		// .findEBusinesstypeById(new Long(ywlxid)));
		// 是否考试题
		// question.setStKszy(RequestUtils.getLong(request, "ST_KSZY", 0));
		// 保险类型
		// question.setBxType(RequestUtils.getString(request, "ST_BAOXIAN",
		// ""));
		question.setStLrsj(RequestUtils.getDate(request, "lrsj", new Date()));
		question.setStXgsj(RequestUtils.getDate(request, "xgsj", new Date()));
		log.info(question.getStLrsj());
		// 重要程度 BX_TYPE

		// question.setStNodeId(RequestUtils.getString(request, "ST_NODE_ID",
		// ""));
		// question.setStNodeName(RequestUtils.getString(request, "WH_TYPE",
		// ""));
		// 录入人员
		AcegiUtil acegiUtil = new AcegiUtil();
		long userId = ((UserDetailsImpl) acegiUtil.getUserDetails()).getUser()
				.getId();
		String userName = ((UserDetailsImpl) acegiUtil.getUserDetails())
				.getUser().getUsername();
		question.setStMtryId(userName);
		// 根据试题类型
		
		switch (STLX) {
		case 2:// 单选
			log.info("====================单选题===========================");
			// 单选题选项，规则是以||分割
			question.setStXx(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "singleoption"), "||"));
			// 单选题答案，规则是以||分割
			question.setStDa(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "singleoptionkey"),
					"||"));
			break;
		case 8:// 多选
			log.info("======================多选题=========================");
			// 多选题选项，规则是以||分割
			question.setStXx(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "manyoption"), "||"));
			// 多选题答案，规则是以||分割
			question.setStDa(StringUtil.stringSpell(
					RequestUtils.getStringValues(request, "manyoptionkey"),
					"||"));
			break;
		case 3:// 判断题
			log.info("========================判断题=======================");
			// 判断题答案，如果是-1则为无法取值
			question.setStDa(RequestUtils
					.getString(request, "verdictkey", "-1"));
			break;
		/*
		 * case 4:// 判断说明题
		 * log.info("=======================判断说明题========================"); //
		 * 判断说明题答案，如果是-1则为无法取值 question.setStDa(RequestUtils.getString(request,
		 * "verdictsaykey", "-1")); // 判断说明题答案 question.setStDasm(RequestUtils
		 * .getString(request, "verdictsay", "")); break; case 5:// 录音题
		 * log.info("========================录音题=======================");
		 * 
		 * // 录音题答案 question.setStDa(RequestUtils.getString(request,
		 * "recordkey", "")); // 录音题录音文件路径 question.setStFjlj(RequestUtils
		 * .getString(request, "recordpath", "")); break;
		 */
		case 1:// 填空题
			log.info("======================填空题=========================");
			// 填空题答案
			question.setStDa(RequestUtils.getString(request, "fillkey", ""));
			break;
		case 4:// 问答题
				// 问答题答案
			log.info("======================问答题答案=========================");
			question.setStDa(RequestUtils.getString(request, "askkey", ""));
			break;
		/*
		 * case 8:// 案例分析题 // 案例分析题答案
		 * log.info("=========================案例分析题答案======================");
		 * question.setStDa(RequestUtils.getString(request, "casekey", ""));
		 * break;
		 */
		case 5:// 计算题
				// 计算题题答案
			log.info("=========================计算题题答案======================");
			question.setStDa(RequestUtils
					.getString(request, "calculatekey", ""));
			break;
		/*
		 * case 10:// 不定向选择题
		 * log.info("=========================不定向选择题答案======================");
		 * // 不定向选择题选项，规则是以||分割
		 * question.setStXx(StringUtil.stringSpell(RequestUtils
		 * .getStringValues(request, "uncertainoption"), "||")); //
		 * 不定向选择题答案，规则是以||分割
		 * question.setStDa(StringUtil.stringSpell(RequestUtils
		 * .getStringValues(request, "uncertainoptionkey"), "||")); break;
		 */
		case 6:// 论述题
				// 论述题答案
			log.info("=========================论述题答案======================");
			question.setStDa(RequestUtils.getString(request, "discusskey", ""));
			break;
		case 7:// 绘图
				// 点库题答案
			log.info("=========================绘图题答案======================");
			question.setStDa(RequestUtils.getString(request, "", ""));
			break;
		/*
		 * case 12:// 点库题 // 点库题答案
		 * log.info("=========================点库题答案======================");
		 * question .setStDa(RequestUtils.getString(request, "databasekey",
		 * "")); break; case 13:// 拨测题 // 点库题答案
		 * log.info("=========================点库题答案======================");
		 * question.setStDa(RequestUtils .getString(request, "telephonekey",
		 * "")); break;
		 */
		default:
			log.info("试题类型选择错误！");
		}
		if(question.getStXx()!=null){
			question.setStXx(question.getStXx().replace(" ", ""));
		}
		
		
		ae02.setAaa121("试题录入");// 业务类型
		ae02.setCae219("1");// 业务办理渠道
		ae02.setCae220("010");// 业务办理状态
		ae02.setCae251(String.valueOf(userId));// 登记经办人id
		ae02.setCae249(userName);// 经办人姓名
		ae02.setAae016("0");// 终审状态
		ae02.setAae217(new Date());// 办理日起
		String errorInfo = "";
		try {
			if (!"".equals(sjid)&&!"null".equals(sjid)) {
				int count = eQuestionsService.getNumOfQuestionSet(sjid);
				boolean b = eQuestionsService.checkSumOfQuestionSet(sjid,stfz);
				if (count >= 100) {
					errorInfo = "本套试卷试题数量已经满100题!";
					throw new Exception("本套试卷试题数量已经满100题!");
				}
				if(!b){
					errorInfo = "超出本套试题总分!";
					throw new Exception("超出本套试题总分!");
				}
				
			}
			//图片上传
			MultiPartRequestWrapper mWrapper = (MultiPartRequestWrapper) request;//struts2与servlet可能有冲突，不能用fileupload
			if(mWrapper.getFiles("img")!=null){
				File file = mWrapper.getFiles("img")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "题目图片大小超出范围！";
					throw new Exception("题目图片大小超出范围！");
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImg(b);
			}
			if(mWrapper.getFiles("ST_IMG_A")!=null){
				File file = mWrapper.getFiles("ST_IMG_A")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "选项A:图片大小超出范围！";
					throw new Exception("选项A:图片大小超出范围！");
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImgA(b);
			}
			if(mWrapper.getFiles("ST_IMG_B")!=null){
				File file = mWrapper.getFiles("ST_IMG_B")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "选项B:图片大小超出范围！";
					throw new Exception("选项B:图片大小超出范围！");
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImgB(b);
			}
			if(mWrapper.getFiles("ST_IMG_C")!=null){
				File file = mWrapper.getFiles("ST_IMG_C")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "选项C:图片大小超出范围！";
					throw new Exception("选项C:图片大小超出范围！");
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImgC(b);
			}
			if(mWrapper.getFiles("ST_IMG_D")!=null){
				File file = mWrapper.getFiles("ST_IMG_D")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "选项D:图片大小超出范围！";
					throw new Exception("选项D:图片大小超出范围！");
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImgD(b);
			}
			if(mWrapper.getFiles("ST_IMG_E")!=null){
				File file = mWrapper.getFiles("ST_IMG_E")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "选项E:图片大小超出范围！";
					throw new Exception("选项E:图片大小超出范围！");
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImgE(b);
			}
			if(mWrapper.getFiles("ST_IMG_F")!=null){
				File file = mWrapper.getFiles("ST_IMG_F")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "选项F:图片大小超出范围！";
					throw new Exception("选项F:图片大小超出范围！");
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImgF(b);
			}
			if(mWrapper.getFiles("ST_IMG_G")!=null){
				File file = mWrapper.getFiles("ST_IMG_G")[0];
				FileInputStream fis = new FileInputStream(file);
				int imgLenth = fis.available();
				if(imgLenth>102400){
					errorInfo = "选项G:图片大小超出范围！";
					throw new Exception("选项G:图片大小超出范围！");
				}
				byte[] b = new byte[imgLenth];
				fis.read(b);
				fis.close();
				question.setStImgG(b);
			}
			
			eQuestionsService.saveOrUpdateAe02(ae02);// 添加业务日志
			EBusinesstype eBusinesstype = new EBusinesstype();
			eBusinesstype.setId(ae02.getAaz002());
			eQuestionsService.saveEBusinesstype(eBusinesstype);
			question.setEbusinesstype(eBusinesstype);
			eQuestionsService.saveOrUpdateQuestion(question);

			Tmdot tmdot = new Tmdot();
			tmdot.setSt_id(question.getStId());
			tmdot.setJdysid(jdysid);
			eQuestionsService.saveTmdot(tmdot);
			
			if (!"".equals(sjid)) {
				log.info("试题信息保存成功！");
				mes = "试题录入成功！";
				request.setAttribute("messge", mes);
				request.setAttribute("sjType", sjType);
				if("cpt".equals(sjType)){
					request.getSession().setAttribute("stlx_cpt", STLX);
				}else if("written".equals(sjType)){
					request.getSession().setAttribute("stlx_written", STLX);
				}
				request.getRequestDispatcher("question/question_set_save.jsp").forward(request, response);
				//request.getRequestDispatcher("question/questions_set_add_right.jsp").forward(request, response);
			} else {
				log.info("试题信息保存成功！");
				mes = "试题录入成功！";
				request.setAttribute("messge", mes);
				request.getRequestDispatcher("question/save.jsp").forward(
						request, response);
			}
		} catch (Exception e) {
			log.debug("保存失败！");
			e.printStackTrace();
			request.setAttribute("info", "试题信息增加");
			if("".equals(errorInfo)){
				errorInfo = "试题信息增加失败!";
			}
			request.setAttribute("errorinfo", errorInfo);
			request.getRequestDispatcher("common/errorPage.jsp").forward(
					request, response);
		}
		// }
		// //清除session中的formhash
		// formhashSession.remove(formhash);
		// session.setAttribute("formhashSession", formhashSession);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
