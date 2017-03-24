package com.wondersgroup.falcon.question.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.falcon.Util.NavigateForm;
import com.wondersgroup.falcon.Util.RequestUtils;
import com.wondersgroup.falcon.question.PrintCardService;
import com.wondersgroup.kaoshi.bo.Admission_card_pc;
import com.wondersgroup.kaoshi.bo.PrintCardVO;

public class PrintCardServlet extends HttpServlet {

	private static final Log log = LogFactory.getLog(PrintCardServlet.class);

	private int defaultsize = 10;

	/**
	 * Constructor of the object.
	 */
	public PrintCardServlet() {
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
		}else if(actionType.equals("query")&&myaction.equals("checklist")){
			query(request, response);
		}else if(actionType.equals("query")&&myaction.equals("checkPrintList")){
			checkPrintList(request, response);
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admission_card_pc acp = new Admission_card_pc();
		acp.setKs_name(RequestUtils.getString(request, "zkzbt", ""));
		acp.setPc_name(RequestUtils.getString(request, "pcmc", ""));
		acp.setKd_name(RequestUtils.getString(request, "kdmc", ""));
		acp.setKd_address(RequestUtils.getString(request, "address", ""));
		acp.setKssj(RequestUtils.getString(request, "kssj", ""));
		acp.setJssj(RequestUtils.getString(request, "jssj", ""));
		acp.setValid("1");
		acp.setTime(new Date());
		acp.setMajor(RequestUtils.getString(request, "major", ""));
		acp.setRank(RequestUtils.getString(request, "rank", ""));

		PrintCardService pcService = new PrintCardService();
		String rtUrl = "";
		try {
			pcService.add(acp);
			//rtUrl = "PrintCardServlet?myaction=checklist&actionType=query";
			query(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			rtUrl = "../error.jsp";
			request.setAttribute("wrongMessage", e.getMessage());
			request.getRequestDispatcher(rtUrl).forward(request, response);
		}

	}

	public void checkPrintList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admission_card_pc acp = new Admission_card_pc();
		acp.setKs_name(RequestUtils.getString(request, "zkzbt", ""));
		acp.setPc_name(RequestUtils.getString(request, "pcmc", ""));
		acp.setKd_name(RequestUtils.getString(request, "kdmc", ""));

		// 分页
		// currpage
		int currpage = RequestUtils.getInt(request, "currpage", 1);// 默认1
		// pagesize
		int pagesize = RequestUtils.getInt(request, "pagesize", defaultsize);// 默认行行数
		// pagenum
		int pagenum = RequestUtils.getInt(request, "pagenum", 10);

		PrintCardService pService = new PrintCardService();
		
		int total = pService.queryPrintListSize(acp);
		// 分页
		NavigateForm navigateform = new NavigateForm();
		navigateform.setCurrpage(currpage);
		navigateform.setPagesize(pagesize);
		navigateform.setTotal(total);
		navigateform.setPagenum(pagenum);
		
		List<Object[]> list = pService.queryPrintCardList(acp, currpage, pagesize);
		
		request.setAttribute("printList", list);
		request.setAttribute("navigateform", navigateform);
		
		request.getRequestDispatcher("../authority/print_card_list.jsp").forward(request, response);
	}
	
	public void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Admission_card_pc acp = new Admission_card_pc();
		acp.setKs_name(RequestUtils.getString(request, "zkzbt", ""));
		acp.setPc_name(RequestUtils.getString(request, "pcmc", ""));
		acp.setKd_name(RequestUtils.getString(request, "kdmc", ""));
		acp.setKd_address(RequestUtils.getString(request, "address", ""));
		acp.setKssj(RequestUtils.getString(request, "kssj", ""));
		acp.setJssj(RequestUtils.getString(request, "jssj", ""));

		// 分页
		// currpage
		int currpage = RequestUtils.getInt(request, "currpage", 1);// 默认1
		// pagesize
		int pagesize = RequestUtils.getInt(request, "pagesize", defaultsize);// 默认行行数
		// pagenum
		int pagenum = RequestUtils.getInt(request, "pagenum", 10);
		
		PrintCardService pService = new PrintCardService();

		int total = pService.querySize(acp);
		// 分页
		NavigateForm navigateform = new NavigateForm();
		navigateform.setCurrpage(currpage);
		navigateform.setPagesize(pagesize);
		navigateform.setTotal(total);
		navigateform.setPagenum(pagenum);
		
		List<Admission_card_pc> list = pService.queryList(acp, currpage, pagesize);
		
		request.setAttribute("list", list);
		request.setAttribute("navigateform", navigateform);
		
		request.getRequestDispatcher("../authority/paper_printcard.jsp").forward(request, response);
		
		
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
