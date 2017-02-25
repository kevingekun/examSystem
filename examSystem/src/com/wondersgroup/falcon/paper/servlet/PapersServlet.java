package com.wondersgroup.falcon.paper.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.mira.lucene.analysis.e;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wondersgroup.falcon.Util.DateUtil;
import com.wondersgroup.falcon.Util.NavigateForm;
import com.wondersgroup.falcon.Util.RequestUtils;
import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.paper.beans.EPapersService;
import com.wondersgroup.falcon.paper.dao.EPaperquestionsDAO;
import com.wondersgroup.falcon.paper.model.EPaperquestions;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.question.beans.EQuestionsService;
import com.wondersgroup.falcon.question.beans.EQuestiontypeService;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.falcon.question.model.EQuestiontype;
import com.wondersgroup.kaoshi.bo.Tdjobexamdot;

public class PapersServlet extends HttpServlet {

	private static final Log log = LogFactory.getLog(PapersServlet.class);

	EPapersService papersService = new EPapersService();

	private int defaultsize = 10;

	/**
	 * Constructor of the object.
	 */
	public PapersServlet() {
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

		if (actionType != null && actionType.equals("auto")) {
			autoLoad(request, response);
		} else if (actionType != null && actionType.equals("savepaper")) {
			savePaper(request, response);
		} else if (actionType != null && actionType.equals("query")) {
			queryPaper(request, response);
		} else if (actionType != null && actionType.equals("queryUnchecked")) {
			queryPaperUnchecked(request, response);
		} else if (actionType != null && actionType.equals("queryUnpass")) {
			queryPaperUnpass(request, response);

		} else {
			log.info("********************未知组卷类型********************");
		}
	}

	private void queryPaper(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("********************试卷查询********************");
		String sjMc = RequestUtils.getString(request, "sjMc", "");
		long sjZt = RequestUtils.getLong(request, "sjZt", -1);
		Date sjKksjbegin = RequestUtils.getDate(request, "sjKksjbegin",
				"00:00:00", null, "yyyy-MM-dd HH:mm:ss");
		Date sjKksjend = RequestUtils.getDate(request, "sjKksjend", "23:59:59",
				null, "yyyy-MM-dd HH:mm:ss");
		Date sjZjsjbegin = RequestUtils.getDate(request, "sjZjsjbegin",
				"00:00:00", null, "yyyy-MM-dd HH:mm:ss");
		Date sjZjsjend = RequestUtils.getDate(request, "sjZjsjend", "23:59:59",
				null, "yyyy-MM-dd HH:mm:ss");

		// 排序（组卷时间）
		String orderby = RequestUtils.getString(request, "orderby", "sjZjsj");
		// 分页
		// currpage
		int currpage = RequestUtils.getInt(request, "currpage", 1);// 默认1
		// pagesize
		int pagesize = RequestUtils.getInt(request, "pagesize", defaultsize);// 默认行行数
		int pagenum = RequestUtils.getInt(request, "pagenum", 5);

		EPapersService s = new EPapersService();
		// 查询总录数
		int total = s.getPapersTotalCount(sjMc, sjZt, sjKksjbegin, sjKksjend,
				sjZjsjbegin, sjZjsjend);
		// 分页
		NavigateForm navigateform = new NavigateForm();
		navigateform.setCurrpage(currpage);
		navigateform.setPagesize(pagesize);
		navigateform.setTotal(total);
		navigateform.setPagenum(pagenum);
		// 查询记录集
		List list = s.findPapers(sjMc, sjZt, sjKksjbegin, sjKksjend,
				sjZjsjbegin, sjZjsjend, currpage, pagesize, orderby);
		log.info("查询结果记录数：" + list.size());
		String color = AcegiUtil.getEUser().getColor();
		String colorCheck = "";
		if("".equals(color)||color==null){
			colorCheck="0";
		}else{
			colorCheck="1";
		}
		// 保存查询结果
		request.setAttribute("list", list);
		request.setAttribute("navigateform", navigateform);
		// 保存查询条件
		request.setAttribute("sjMc", sjMc);
		request.setAttribute("sjZt", new java.lang.Long(sjZt));
		request.setAttribute("sjKksjbegin", sjKksjbegin);
		request.setAttribute("sjKksjend", sjKksjend);
		request.setAttribute("sjZjsjbegin", sjZjsjbegin);
		request.setAttribute("sjZjsjend", sjZjsjend);
		request.setAttribute("orderby", orderby);
		request.setAttribute("colorCheck", colorCheck);

		request.getRequestDispatcher("paper/paper_list.jsp").forward(request,
				response);

	}

	private void queryPaperUnchecked(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("********************试卷查询********************");
		String sjMc = RequestUtils.getString(request, "sjMc", "");
		long sjZt = RequestUtils.getLong(request, "sjZt", 0);
		Date sjKksjbegin = RequestUtils.getDate(request, "sjKksjbegin",
				"00:00:00", null, "yyyy-MM-dd HH:mm:ss");
		Date sjKksjend = RequestUtils.getDate(request, "sjKksjend", "23:59:59",
				null, "yyyy-MM-dd HH:mm:ss");
		Date sjZjsjbegin = RequestUtils.getDate(request, "sjZjsjbegin",
				"00:00:00", null, "yyyy-MM-dd HH:mm:ss");
		Date sjZjsjend = RequestUtils.getDate(request, "sjZjsjend", "23:59:59",
				null, "yyyy-MM-dd HH:mm:ss");

		// 排序（组卷时间）
		String orderby = RequestUtils.getString(request, "orderby", "sjZjsj");
		// 分页
		int currpage = RequestUtils.getInt(request, "currpage", 1);// 默认1
		// currpage
		int pageSize = RequestUtils.getInt(request, "pagesize", defaultsize);// 默认行行数
		// pagesize
		int pagenum = RequestUtils.getInt(request, "pagenum", 5);

		EPapersService s = new EPapersService();
		// 查询总录数
		int total = s.getPapersTotalCount(sjMc, sjZt, sjKksjbegin, sjKksjend,
				sjZjsjbegin, sjZjsjend);
		// 分页
		NavigateForm navigateform = new NavigateForm();
		navigateform.setCurrpage(currpage);
		navigateform.setPagesize(pageSize);
		navigateform.setTotal(total);
		navigateform.setPagenum(pagenum);
		// 查询记录集
		List list = s.findPapers(sjMc, sjZt, sjKksjbegin, sjKksjend,
				sjZjsjbegin, sjZjsjend, currpage, pageSize, orderby);
		log.info("查询结果记录数：" + list.size());
		// 保存查询结果
		request.setAttribute("list", list);
		request.setAttribute("navigateform", navigateform);
		// 保存查询条件
		request.setAttribute("sjMc", sjMc);
		request.setAttribute("sjZt", new java.lang.Long(sjZt));
		request.setAttribute("sjKksjbegin", sjKksjbegin);
		request.setAttribute("sjKksjend", sjKksjend);
		request.setAttribute("sjZjsjbegin", sjZjsjbegin);
		request.setAttribute("sjZjsjend", sjZjsjend);
		request.setAttribute("orderby", orderby);

		request.getRequestDispatcher("paper/paper_check_list.jsp").forward(
				request, response);

	}

	private void queryPaperUnpass(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("********************试卷查询********************");
		String sjMc = RequestUtils.getString(request, "sjMc", "");
		long sjZt = RequestUtils.getLong(request, "sjZt", 0);
		Date sjKksjbegin = RequestUtils.getDate(request, "sjKksjbegin",
				"00:00:00", null, "yyyy-MM-dd HH:mm:ss");
		Date sjKksjend = RequestUtils.getDate(request, "sjKksjend", "23:59:59",
				null, "yyyy-MM-dd HH:mm:ss");
		Date sjZjsjbegin = RequestUtils.getDate(request, "sjZjsjbegin",
				"00:00:00", null, "yyyy-MM-dd HH:mm:ss");
		Date sjZjsjend = RequestUtils.getDate(request, "sjZjsjend", "23:59:59",
				null, "yyyy-MM-dd HH:mm:ss");

		// 排序（组卷时间）
		String orderby = RequestUtils.getString(request, "orderby", "sjZjsj");
		// 分页
		int currentPage = RequestUtils.getInt(request, "currpage", 1);// 默认1
		// currpage
		int pageSize = RequestUtils.getInt(request, "pagesize", defaultsize);// 默认行行数
		// pagesize
		int pagenum = RequestUtils.getInt(request, "pagenum", 5);

		EPapersService s = new EPapersService();
		// 查询总录数
		int total = s.getPapersTotalCount(sjMc, sjZt, sjKksjbegin, sjKksjend,
				sjZjsjbegin, sjZjsjend);
		// 分页
		NavigateForm navigateform = new NavigateForm();
		navigateform.setCurrpage(currentPage);
		navigateform.setPagesize(pageSize);
		navigateform.setTotal(total);
		navigateform.setPagenum(pagenum);
		// 查询记录集
		List list = s.findPapers(sjMc, sjZt, sjKksjbegin, sjKksjend,
				sjZjsjbegin, sjZjsjend, currentPage, pageSize, orderby);
		log.info("查询结果记录数：" + list.size());
		// 保存查询结果
		request.setAttribute("list", list);
		request.setAttribute("navigateform", navigateform);
		// 保存查询条件
		request.setAttribute("sjMc", sjMc);
		request.setAttribute("sjZt", new java.lang.Long(sjZt));
		request.setAttribute("sjKksjbegin", sjKksjbegin);
		request.setAttribute("sjKksjend", sjKksjend);
		request.setAttribute("sjZjsjbegin", sjZjsjbegin);
		request.setAttribute("sjZjsjend", sjZjsjend);
		request.setAttribute("orderby", orderby);

		request.getRequestDispatcher("paper/paper_unpass_list.jsp").forward(
				request, response);

	}

	// private void queryCjpmPaper(HttpServletRequest request,
	// HttpServletResponse response) throws ServletException, IOException {
	// log.info("********************试卷查询********************");
	// String sjMc =RequestUtils.getString(request, "sjMc", "");
	// long sjZt= RequestUtils.getLong(request, "sjZt", 0);
	// Date sjKksjbegin = RequestUtils.getDate(request, "sjKksjbegin",
	// "00:00:00", null,"yyyy-MM-dd HH:mm:ss");
	// Date sjKksjend = RequestUtils.getDate(request, "sjKksjend", "23:59:59",
	// null,"yyyy-MM-dd HH:mm:ss");
	// Date sjZjsjbegin = RequestUtils.getDate(request, "sjZjsjbegin",
	// "00:00:00", null,"yyyy-MM-dd HH:mm:ss");
	// Date sjZjsjend = RequestUtils.getDate(request, "sjZjsjend", "23:59:59",
	// null,"yyyy-MM-dd HH:mm:ss");
	//		
	// //排序（组卷时间）
	// String orderby =RequestUtils.getString(request, "orderby", "sjZjsj");
	// //分页
	// int currentPage =RequestUtils.getInt(request, "currpage", 1);//默认1
	// currpage
	// int pageSize =RequestUtils.getInt(request, "pagesize", defaultsize
	// );//默认行行数 pagesize
	// int pagenum =RequestUtils.getInt(request, "pagenum", 5);
	//		
	// EPapersService s = new EPapersService();
	// //查询总录数
	// int total=s.getPapersTotalCount(sjMc, sjZt, sjKksjbegin, sjKksjend,
	// sjZjsjbegin, sjZjsjend);
	// //分页
	// NavigateForm navigateform = new NavigateForm();
	// navigateform.setCurrpage(currentPage);
	// navigateform.setPagesize(pageSize);
	// navigateform.setTotal(total);
	// navigateform.setPagenum(pagenum);
	// //查询记录集
	// List list = s.findPapers(sjMc, sjZt, sjKksjbegin, sjKksjend, sjZjsjbegin,
	// sjZjsjend, currentPage, pageSize, orderby);
	// log.info("查询结果记录数："+list.size());
	// //保存查询结果
	// request.setAttribute("list", list);
	// request.setAttribute("navigateform", navigateform);
	// //保存查询条件
	// request.setAttribute("sjMc", sjMc);
	// request.setAttribute("sjZt", new java.lang.Long(sjZt));
	// request.setAttribute("sjKksjbegin", sjKksjbegin);
	// request.setAttribute("sjKksjend", sjKksjend);
	// request.setAttribute("sjZjsjbegin", sjZjsjbegin);
	// request.setAttribute("sjZjsjend", sjZjsjend);
	// request.setAttribute("orderby", orderby);
	//		
	//request.getRequestDispatcher("kaoshi/answer/px_answer_paper.jsp").forward(
	// request, response);
	//
	// }
	/**
	 * 
	 * <p>
	 * Description:[保存试卷试题]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-14] Midified by [修改人] [修改时间]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void savePaper(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext()); 
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String ctfs = RequestUtils.getString(request, "ctfs", "1");
		String outway = RequestUtils.getString(request, "outway", "1");
		String errorinfo = "";
		if(ctfs.equals("1")){
			String sjid = RequestUtils.getString(request, "sjid", "");
			if(!"".equals(sjid)){
				EPaperquestionsDAO ePaperquestionsDAO = new EPaperquestionsDAO();
				List<Object> list = ePaperquestionsDAO.savePaper(sjid,conn);
				if((Long)list.get(0)==0){
					//System.out.println("保存成功");
					//response.sendRedirect("paper/paper_init_auto_right.jsp");
				}else{
					//System.out.println((String)list.get(1));
					errorinfo = (String)list.get(1);
				}
			}
		}else{
			// 试卷信息
			EPapers paper = new EPapers();
			RequestUtils.populate(request, paper);
			// 日期参数
			String Kksj = RequestUtils.getString(request, "Kksj", "");
			String Yxqjzsj = RequestUtils.getString(request, "Yxqjzsj", "");
			String Zjsj = RequestUtils.getString(request, "Zjsj", "");
			paper.setSjKksj(DateUtil.formatDate(Kksj, "yyyy-MM-dd HH:mm"));
			paper.setSjYxqjzsj(DateUtil.formatDate(Yxqjzsj, "yyyy-MM-dd HH:mm"));
			paper.setSjZjsj(DateUtil.formatDate(Zjsj, "yyyy-MM-dd HH:mm"));
			paper.setModel(RequestUtils.getString(request, "model", "1"));
			paper.setSjZt(new Long(4).longValue());
			paper.setPaperType( RequestUtils.getString(request, "paperType", ""));
			paper.setToUserId(RequestUtils.getString(request, "toUserId", ""));
			
			paper.setSjJjsj(RequestUtils.getLong(request, "sjJjsj", new Long(1)));
			paper.setSjLjcf(RequestUtils.getString(request, "sjLjcf", "1"));
			paper.setSjZych(RequestUtils.getString(request, "sjZych", ""));
			paper.setSjDj(RequestUtils.getString(request, "sjDj", ""));
			paper.setSjGzid(RequestUtils.getString(request, "sjGzid", ""));
			
			
			paper.setSjCtfs(RequestUtils.getString(request, "sjCtfs", ""));
			paper.setSjNych(RequestUtils.getString(request, "sjNych", ""));
			paper.setSjPc(RequestUtils.getString(request, "sjPc", ""));
			paper.setSjKslx(RequestUtils.getString(request, "sjKslx", ""));
			paper.setSjFs(Long.valueOf(RequestUtils.getString(request, "sjFs", "")));
			long[] questionid = RequestUtils
					.getLongValues(request, "questionid", 0);
			Set<EPaperquestions> pqSet = new HashSet<EPaperquestions>(0);
			EQuestionsService qservice = new EQuestionsService();
			for (int i = 0; i < questionid.length; i++) {
				log.info("试题ID:" + questionid[i]);
				EPaperquestions pq = new EPaperquestions();// 试卷试题
				EQuestions q = qservice.findById(new Long(questionid[i]));// 试题

				double sjStfs = RequestUtils.getDouble(request, q.getEquestiontype().getDescriptor(), 0);
				pq.setEquestions(q);
				pq.setEpapers(paper);
				pq.setSjStfs(sjStfs);
				pq.setSjStpx(i + 1);

				pqSet.add(pq);
			}
			paper.setEpaperquestionses(pqSet);
			log.info(paper.toString());
			try {
				papersService.savePage(paper);
			} catch (Exception e) {
				e.printStackTrace();
				errorinfo = e.getMessage();
			}
		}
		if(!"".equals(errorinfo)){
			request.setAttribute("info", "试卷保存");
			request.setAttribute("errorinfo", errorinfo);
			request.getRequestDispatcher("common/errorPage.jsp").forward(
					request, response);
		}else{
			// 跳转
			if("3".equals(outway)){
				response.sendRedirect("paper/paper_init_check_right.jsp");
			}else{
				response.sendRedirect("paper/paper_init_auto_right.jsp");
			}
		}
		// request.getRequestDispatcher("paper/paper_init_auto.jsp").forward(
		// request, response);paper_init_auto.jsp
	}

	/**
	 * 
	 * <p>
	 * Description:[加载试卷试题]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-7-14] Midified by [修改人] [修改时间]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void autoLoad(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EPapersService paperservice=new EPapersService();
		// 试卷信息
		EPapers pager = new EPapers();
		RequestUtils.populate(request, pager);
		
		String Kksj = RequestUtils.getString(request, "Kksj", "");
		String Yxqjzsj = RequestUtils.getString(request, "Yxqjzsj", "");
		String tqjj=RequestUtils.getString(request, "tqjj", "");//开始多长时间后可以交卷
		
		String sjid = RequestUtils.getString(request, "sjid", "");//获取原始试卷id(抽取整套试题时使用)
		
		String outway=RequestUtils.getString(request, "outway", "1");//获得出题方式
		pager.setModel(RequestUtils.getString(request, "model", "1"));// 默认是开卷
		pager.setPaperType(RequestUtils.getString(request, "paperType", "1"));// 试卷类型默认培训评估考试
		pager.setToUserId(RequestUtils.getString(request, "toUserId", ""));// 播侧卷考试人员
		pager.setSjKksj(DateUtil.formatDate((Kksj.equals("") ? "": Kksj + " 00:00:01"),"yyyy-MM-dd HH:mm:ss"));
		pager.setSjYxqjzsj(DateUtil.formatDate((Yxqjzsj.equals("") ? "": Yxqjzsj + " 23:59:59"),"yyyy-MM-dd HH:mm:ss"));
		pager.setSjZjsj(new Date());
		pager.setSjJjsj(Long.valueOf(tqjj));
		pager.setSjLjcf(RequestUtils.getString(request, "sjLjcf", "0"));
		pager.setSjZych(RequestUtils.getString(request, "zycd", ""));
		pager.setSjDj(RequestUtils.getString(request, "grade", ""));
		pager.setSjGzid(RequestUtils.getString(request, "servicetype", ""));
		pager.setSjCtfs(outway);
		pager.setSjNych(RequestUtils.getString(request, "ndxs", ""));
		pager.setSjPc(RequestUtils.getString(request, "kspc", ""));
		pager.setSjKslx(RequestUtils.getString(request, "paperType", ""));
		pager.setSjFs(Long.valueOf(RequestUtils.getString(request, "sjfs", "100")));
		// 录入人员
		pager.setSjZjrid(((UserDetailsImpl) AcegiUtil.getUserDetails())
				.getUser().getUsername());
		// 根据试题类型分别处理
		EQuestiontypeService questionType = new EQuestiontypeService();
		List types = questionType.findEQuestiontypeAll();
		// 试卷试题
		Map questionmap = new HashMap();// 试题
		Map pointmap = new HashMap();// 分数
		Map totallmap = new HashMap();// 总分数
		Map questionnumbermap = new HashMap();// 题数
		String errorinfo = "";
		
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext()); 
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(outway.equals("3")){//抽卷组卷
			EPaperquestionsDAO ePaperquestionsDAO = new EPaperquestionsDAO();
			List<Object> returnList = ePaperquestionsDAO.paperPreview_check(pager, sjid,connection);
			if((Long)returnList.get(0)==0){
				request.setAttribute("sjid", returnList.get(1));
				questionmap = ePaperquestionsDAO.getInfoOfPaper(Long.valueOf((String)returnList.get(1)));
				List<Map<String, String>> listM = ePaperquestionsDAO.getNumAndTotalOfPaper(Long.valueOf((String)returnList.get(1)));
				if(listM.size()>0){
					questionnumbermap = listM.get(0);
					totallmap = listM.get(1);
				}
				/*pointmap.put("single", "1");
				pointmap.put("many", "1");
				pointmap.put("verdict", "1");*/
				
				/*totallmap.put("single", 40);
				totallmap.put("many", 40);
				totallmap.put("verdict", 20);
				
				questionnumbermap.put("single", "40");
				questionnumbermap.put("many", "40");
				questionnumbermap.put("verdict", "20");*/
			}else{
				errorinfo = (String)returnList.get(1);
			}
		}else{
			if(outway.equals("1")){
				EPaperquestionsDAO ePaperquestionsDAO = new EPaperquestionsDAO();
				List<Object> list = new ArrayList<Object>();
				String single = RequestUtils.getString(request, "single", "40");
				String singlepoint = RequestUtils.getString(request, "singlepoint", "1");
				String many = RequestUtils.getString(request, "many", "40");
				String manypoint = RequestUtils.getString(request, "manypoint", "1");
				String verdict = RequestUtils.getString(request, "verdict", "20");
				String verdictpoint = RequestUtils.getString(request, "verdictpoint", "1");
				list.add(single);
				list.add(singlepoint);
				list.add(many);
				list.add(manypoint);
				list.add(verdict);
				list.add(verdictpoint);
				List<Object> returnList = ePaperquestionsDAO.paperPreview(pager, list,connection);
				if((Long)returnList.get(0)==0){
					request.setAttribute("ctfs", "1");//出题方式：1 按照鉴定比例出题 2 手动输入比例出题
					request.setAttribute("sjid", returnList.get(1));
					questionmap = ePaperquestionsDAO.getInfoOfPaper(Long.valueOf((String)returnList.get(1)));
					pointmap.put("single", singlepoint);
					pointmap.put("many", manypoint);
					pointmap.put("verdict", verdictpoint);
					
					totallmap.put("single", Integer.valueOf(single)*Integer.valueOf(singlepoint));
					totallmap.put("many", Integer.valueOf(many)*Integer.valueOf(manypoint));
					totallmap.put("verdict", Integer.valueOf(verdict)*Integer.valueOf(verdictpoint));
					
					questionnumbermap.put("single", single);
					questionnumbermap.put("many", many);
					questionnumbermap.put("verdict", verdict);
				}else{
					errorinfo = (String)returnList.get(1);
				}
			}else{
				//弃用（原始出题方法，现已改用存储过程，即上面List<Object> returnList = ePaperquestionsDAO.paperPreview(pager, list);）
				if("1".equals(outway)){//出题方式为按照比例进行出题
					//获得鉴定要素信息
					String serviceType = RequestUtils.getString(request, "servicetype", "");//接受组中的工种信息
					String bxType = RequestUtils.getString(request, "bxType", "");//接收等级参数
					String zycd=RequestUtils.getString(request, "zycd", "");//接受重要程度
					String ndxs=RequestUtils.getString(request, "ndxs", "");//接受难度系数
					String gradetype=RequestUtils.getString(request, "grade", "");//获得等级
					String grade="";
					if("1".equals(gradetype)){//判断级别
						grade="一级";
					}else if("2".equals(gradetype)){
						grade="二级";;
					}else if("3".equals(gradetype)){
						grade="三级";;
					}else if("4".equals(gradetype)){
						grade="四级";;
					}else if("5".equals(gradetype)){
						grade="五级";;
					}
					List<Tdjobexamdot> tdjoblist=paperservice.queryTdjobexamdot(serviceType);
					int sumZong=0;
					for (Tdjobexamdot tdjobexamdot : tdjoblist) {//循环坚定要素
						int sum=0; //鉴定要素比例
						if("1".equals(gradetype)){//判断级别
							sum=tdjobexamdot.getRank1pent();
						}else if("2".equals(gradetype)){
							sum=tdjobexamdot.getRank2pent();
						}else if("3".equals(gradetype)){
							sum=tdjobexamdot.getRank3pent();
						}else if("4".equals(gradetype)){
							sum=tdjobexamdot.getRank4pent();
						}else if("5".equals(gradetype)){
							sum=tdjobexamdot.getRank5pent();
						}
						sumZong=sumZong+sum;
						System.out.println("鉴定点总和"+gradetype+"："+sumZong);
					}
					if(sumZong<100){
						System.out.println("鉴定点综合："+sumZong);
						errorinfo = "组卷失败， 此级别鉴定要素不正确!请检查";
					}else{
					for(int i = 0; i < types.size(); i++){//获得当前题型的信息
						EQuestiontype t = (EQuestiontype) types.get(i);
						System.out.println("============================"+t.getName()+"================================");
						int questionNum = RequestUtils.getInt(request, t.getDescriptor(), 0);
						double point = RequestUtils.getDouble(request, t.getDescriptor()+ "point", 0.00);//获得当前题型的分数
						int xyz=0;
						double totall=questionNum*point;
						if(questionNum>0){
						List<Long> questionIds = new ArrayList<Long>();// 记录一下查找到的试题id
						List onetype = new ArrayList();//定义类型集合 存放第一层数据
						int qunum=0;
						for (Tdjobexamdot tdjobexamdot : tdjoblist) {//循环坚定要素
							int raskpent=0; //鉴定要素比例
							if("1".equals(gradetype)){//判断级别
								raskpent=tdjobexamdot.getRank1pent();
							}else if("2".equals(gradetype)){
								raskpent=tdjobexamdot.getRank2pent();
							}else if("3".equals(gradetype)){
								raskpent=tdjobexamdot.getRank3pent();
							}else if("4".equals(gradetype)){
								raskpent=tdjobexamdot.getRank4pent();
							}else if("5".equals(gradetype)){
								raskpent=tdjobexamdot.getRank5pent();
							}
							Long num=Math.round((Double.valueOf(String.valueOf(questionNum))*Double.valueOf(String.valueOf(raskpent)))/100);
							int qnum=Integer.parseInt(num.toString());//获得此检点点的出题个数
							xyz=xyz+raskpent;
							if(qnum>0){
							if(xyz<=5){
								//在x中取
							List onelevel = papersService.getPaperQuestionsRandomByJd(qnum,"Z" , new Long(t.getId()).intValue(),
										serviceType, bxType,zycd,ndxs,questionIds,grade,String.valueOf(tdjobexamdot.getJdysid()));
									if(onelevel.size()==qnum){
										for (int c = 0; c < onelevel.size(); c++) {
											EQuestions equestions = (EQuestions) onelevel.get(c);
											questionIds.add(new Long(equestions.getStId()));
										}
										System.out.println("Z中"+t.getName()+"应出："+qnum+"   实际出题："+onelevel.size());
										qunum=qunum+onelevel.size();
										onetype.add(onelevel);
									}else{
										List onelevelbuz = papersService.getPaperQuestionsRandomByJd((qnum-onelevel.size()),"X" , new Long(t.getId()).intValue(),
												serviceType, bxType,zycd,ndxs,questionIds,grade,String.valueOf(tdjobexamdot.getJdysid()));
										for (int c = 0; c < onelevelbuz.size(); c++) {
											EQuestions equestions = (EQuestions) onelevelbuz.get(c);
											questionIds.add(new Long(equestions.getStId()));
										}
										qunum=qunum+onelevelbuz.size();
										onetype.add(onelevelbuz);
										System.out.println("Z中"+t.getName()+"在x中补填："+(qnum-onelevel.size())+"   实际出题："+onelevelbuz.size());
									}
							}else if(xyz<=20){
								List onelevel = papersService.getPaperQuestionsRandomByJd(qnum,"Y" , new Long(t.getId()).intValue(),
										serviceType, bxType,zycd,ndxs,questionIds,grade,String.valueOf(tdjobexamdot.getJdysid()));
								if(onelevel.size()==qnum){
									for (int c = 0; c < onelevel.size(); c++) {
										EQuestions equestions = (EQuestions) onelevel.get(c);
										questionIds.add(new Long(equestions.getStId()));
									}
									qunum=qunum+onelevel.size();
									onetype.add(onelevel);
									System.out.println("Y中"+t.getName()+"应出："+qnum+"   实际出题："+onelevel.size());
								}else{
									List onelevelbuy = papersService.getPaperQuestionsRandomByJd((qnum-onelevel.size()),"X" , new Long(t.getId()).intValue(),
											serviceType, bxType,zycd,ndxs,questionIds,grade,String.valueOf(tdjobexamdot.getJdysid()));
									for (int c = 0; c < onelevelbuy.size(); c++) {
										EQuestions equestions = (EQuestions) onelevelbuy.get(c);
										questionIds.add(new Long(equestions.getStId()));
									}
									qunum=qunum+onelevelbuy.size();
									onetype.add(onelevelbuy);
									System.out.println("Y中"+t.getName()+"在x中补填："+(qnum-onelevel.size())+"   实际出题："+onelevelbuy.size());
								}
							}else if(xyz<=100){
								if(qunum<questionNum){
									List onelevel = papersService.getPaperQuestionsRandomByJd(qnum,"X" , new Long(t.getId()).intValue(),
											serviceType, bxType,zycd,ndxs,questionIds,grade,String.valueOf(tdjobexamdot.getJdysid()));
									qunum=qunum+onelevel.size();
									if(qunum>questionNum){
										for (int j = 0; j < (qunum-questionNum); j++) {
											onelevel.remove(onelevel.get(j));
										}
										qunum=questionNum;
									}
									System.out.println("x中"+t.getName()+"应出："+qnum+"   实际出题："+onelevel.size());
									for (int c = 0; c < onelevel.size(); c++) {
										EQuestions equestions = (EQuestions) onelevel.get(c);
										questionIds.add(new Long(equestions.getStId()));
									}
									onetype.add(onelevel);
								}
							}
							}
						}
						if(qunum<questionNum){
							List onelevel = papersService.getPaperQuestionsRandomByJd((questionNum-qunum),"X" , new Long(t.getId()).intValue(),
									serviceType, bxType,zycd,ndxs,questionIds,grade,"");
							for (int c = 0; c < onelevel.size(); c++) {
								EQuestions equestions = (EQuestions) onelevel.get(c);
								questionIds.add(new Long(equestions.getStId()));
							}
							System.out.println("x补充"+t.getName()+"中应出："+(questionNum-qunum)+"   实际出题："+onelevel.size());
							onetype.add(onelevel);
							if(onelevel.size()<(questionNum-qunum)){
								List onelevelxy = papersService.getPaperQuestionsRandomByJd((questionNum-qunum-onelevel.size()),"" , new Long(t.getId()).intValue(),
										serviceType, bxType,zycd,ndxs,questionIds,grade,"");
								for (int c = 0; c < onelevelxy.size(); c++) {
									EQuestions equestions = (EQuestions) onelevelxy.get(c);
									questionIds.add(new Long(equestions.getStId()));
								}
								System.out.println("yz中补充"+t.getName()+"中应出："+(questionNum-qunum-onelevel.size())+"   实际出题："+onelevelxy.size());
								onetype.add(onelevelxy);
								if((questionNum-qunum-onelevel.size())>onelevelxy.size()){
									errorinfo = "组卷失败，" + t.getName() + "试题库不足!";
								}
							}
						}
						if (onetype != null && onetype.size() > 0) {
							System.out.println("onetype size:开始" + onetype.size());
							System.out.println("questionmap size:开始" + questionmap.size());
							for (Object object : questionmap.keySet()) {
								System.out.println(object.toString() + "--------------" + ((ArrayList) questionmap.get(object)).size());
							}
							if (questionmap.containsKey(t.getDescriptor())) {
								List tmap = (ArrayList) questionmap.get(t.getDescriptor());
								for (Object object : tmap) {
									onetype.add(object);
								}
								questionmap.put(t.getDescriptor(), onetype);
							} else {
								questionmap.put(t.getDescriptor(), onetype);
							}
	
							System.out.println("onetype size:结束" + onetype.size());
							System.out.println("questionmap size:结束" + questionmap.size());
							for (Object object : questionmap.keySet()) {
								System.out.println(object.toString() + "--------------" + ((ArrayList) questionmap.get(object)).size());
							}
	
							if (pointmap.containsKey(t.getDescriptor())) {
								Double points = (Double) pointmap.get(t.getDescriptor()) + new Double(point);
								pointmap.put(t.getDescriptor(), point);
							} else {
								pointmap.put(t.getDescriptor(), new Double(point));
							}
							if (totallmap.containsKey(t.getDescriptor())) {
								Double totalls = (Double) totallmap.get(t.getDescriptor())+ new Double(totall);
								totallmap.put(t.getDescriptor(), totalls);
							} else {
								totallmap.put(t.getDescriptor(), new Double(totall));
							}
							if (questionnumbermap.containsKey(t.getDescriptor())) {
								Integer questionNums = Integer.valueOf( questionnumbermap.get(t.getDescriptor()).toString())+ new Integer(questionNum);
								questionnumbermap.put(t.getDescriptor(), questionNums);
							} else {
								questionnumbermap.put(t.getDescriptor(), new Integer(questionNum));
							}
						}
					}}}
				}else{
				// 开始批量  按照用户手动输入题目进行出题
				String[] indexs = RequestUtils.getString(request, "indexs", "").split(",");
				for (int ai = 0; ai < indexs.length; ai++) {
					String serviceType = RequestUtils.getString(request, "serviceType_"+ indexs[ai], "");//接受组中的工种信息
					String bxType = RequestUtils.getString(request, "bxType_"+ indexs[ai], "");//接收等级参数
					String zycd=RequestUtils.getString(request, "zycd_"+ indexs[ai], "");//接受重要程度
					String ndxs=RequestUtils.getString(request, "ndxs_"+ indexs[ai], "");//接受难度洗漱
					for (int i = 0; i < types.size(); i++) {
						if (!errorinfo.equals("")) {
							break;
						}
						EQuestiontype t = (EQuestiontype) types.get(i);
						// 取得题数参数、分数参数
						int questionNum = RequestUtils.getInt(request, t.getDescriptor()+ "number_" + indexs[ai], 0);//提醒个数
						double point = RequestUtils.getDouble(request, t.getDescriptor()+ "point_" + indexs[ai], 0.00);//每道题分数
						double totall = RequestUtils.getDouble(request, t.getDescriptor()+ "zf_" + indexs[ai], 0.00);//提醒总分数
						// 取一种类型的试题
						List onetype = new ArrayList();
						//  判断题库有没有题目 按照服务类型和保险类型
						int questioncount = papersService.getQuestionCountByType(new Long(t.getPriority()), serviceType, bxType,zycd,ndxs);
						if (questioncount < questionNum) {
							errorinfo = "组卷失败，" + t.getName() + "试题库不足!";
						} else {
							if (questionNum != 0) { // 动态生成不同级别的数量
								int[] nums = papersService.getNumbergfromImportance(questionNum);
								List<Long> questionIds = new ArrayList<Long>();// 记录一下查找到的试题id
								for (int j = 0; j < nums.length; j++) {
									if (nums[j] > 0) {// 取得该类型、某一个级别的试题
										List onelevel = papersService.getPaperQuestionsRandom(nums[j],j + 1, new Long(t.getId()).intValue(),
														serviceType, bxType,zycd,ndxs);
										if (onelevel != null && ((onelevel.size() <= nums[j] && j != 2) || (onelevel.size() == nums[j] && j == 2))) {
											for (int c = 0; c < onelevel.size(); c++) {
												EQuestions equestions = (EQuestions) onelevel.get(c);
												questionIds.add(new Long(equestions.getStId()));
											}
											onetype.add(onelevel);
											if (onelevel.size() < nums[j]) {
												nums[j + 1] += (nums[j] - onelevel.size());
											}
										} else {
											/*
											 * 如果是空的或者查找到的数量比应该查找到的数量少就往下一个类型增加一个
											 */
											if (j == 2) {
												// j==2 就在前面的两个级别里面再找试题
												int qc = nums[j] - onelevel.size();
												List ol1 = papersService
														.getPaperQuestionsQustionid(qc,1, new Long(t.getId()).intValue(),
																questionIds, serviceType, bxType);
												if (ol1 != null && ol1.size() != 0) {
													onetype.add(ol1);
													for (int c = 0; c < ol1.size(); c++) {
														EQuestions equestions = (EQuestions) ol1.get(c);
														questionIds.add(new Long(equestions.getStId()));
													}
												}
												if (qc != ol1.size()) {
													int oc = qc - ol1.size();
													List ol2 = papersService .getPaperQuestionsQustionid(oc,2,new Long(t.getId()).intValue(),
																	questionIds,serviceType, bxType);
													onetype.add(ol2);
												}
											} else {
												if (onelevel.size() != nums[j]) {
													nums[j + 1] += (nums[j] - onelevel.size());
												}
											}
	
										}
	
									}
								}
							}
						}
						if (onetype != null && onetype.size() > 0) {
							System.out.println("onetype size:开始" + onetype.size());
							System.out.println("questionmap size:开始" + questionmap.size());
							for (Object object : questionmap.keySet()) {
								System.out.println(object.toString() + "--------------" + ((ArrayList) questionmap.get(object)).size());
							}
							if (questionmap.containsKey(t.getDescriptor())) {
								List tmap = (ArrayList) questionmap.get(t.getDescriptor());
								for (Object object : tmap) {
									onetype.add(object);
								}
								questionmap.put(t.getDescriptor(), onetype);
							} else {
								questionmap.put(t.getDescriptor(), onetype);
							}
	
							System.out.println("onetype size:结束" + onetype.size());
							System.out.println("questionmap size:结束" + questionmap.size());
							for (Object object : questionmap.keySet()) {
								System.out.println(object.toString() + "--------------" + ((ArrayList) questionmap.get(object)).size());
							}
	
							if (pointmap.containsKey(t.getDescriptor())) {
								Double points = (Double) pointmap.get(t.getDescriptor()) + new Double(point);
								pointmap.put(t.getDescriptor(), point);
							} else {
								pointmap.put(t.getDescriptor(), new Double(point));
							}
							if (totallmap.containsKey(t.getDescriptor())) {
								Double totalls = (Double) totallmap.get(t.getDescriptor())+ new Double(totall);
								totallmap.put(t.getDescriptor(), totalls);
							} else {
								totallmap.put(t.getDescriptor(), new Double(totall));
							}
							if (questionnumbermap.containsKey(t.getDescriptor())) {
								Integer questionNums = Integer.valueOf( questionnumbermap.get(t.getDescriptor()).toString())+ new Integer(questionNum);
								questionnumbermap.put(t.getDescriptor(), questionNums);
							} else {
								questionnumbermap.put(t.getDescriptor(), new Integer(questionNum));
							}
						}
					}
				}
				}
			}
		}
		
		if (!errorinfo.equals("")) {
			request.setAttribute("info", "自动组卷");
			request.setAttribute("errorinfo", errorinfo);
			request.getRequestDispatcher("common/errorPage.jsp").forward(
					request, response);
		} else {
			request.setAttribute("questionmap", questionmap);// 试题
			request.setAttribute("pointmap", pointmap);// 分数
			request.setAttribute("totallmap", totallmap);// 总分数
			request.setAttribute("questionnumbermap", questionnumbermap);// 题数
			request.setAttribute("pager", pager);// 试卷信息
			request.setAttribute("types", types);
			request.setAttribute("outway", outway);
			request.getRequestDispatcher("paper/paper_preview.jsp").forward(
					request, response);
		}

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
