package com.wondersgroup.falcon.servlet.exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wondersgroup.falcon.beans.common.CommFunc;
import com.wondersgroup.falcon.beans.exam.ExamPaperService;
import com.wondersgroup.falcon.beans.exam.ExamQuestionService;
import com.wondersgroup.falcon.model.exam.ExamAnswerStatus;
import com.wondersgroup.falcon.model.exam.ExamAnswers;
import com.wondersgroup.falcon.model.exam.ExamAnswersQuestions;
import com.wondersgroup.falcon.model.exam.ExamPaper;
import com.wondersgroup.falcon.model.exam.ExamPaperQuestions;
import com.wondersgroup.falcon.model.exam.ExamRealQuestions;
import com.wondersgroup.falcon.model.exam.ExamQuestionType;
import com.wondersgroup.falcon.model.exam.ExamRqueKeys;

public class ExamPaperServlet extends HttpServlet{
	private String actionType="";
	private int onechoicenum =0;
	private int onechoicemark=0;
	private int manychoicenum =0;
	private int manychoicemark=0;	
	private int judgenum =0;
	private int judgemark=0;	
	private int dialognum =0;
	private int dialogmark=0;
	private int recordmark=0;	
	private String examname="";
	private String exammark="";
	private String examtime = "";
	private String effectstarttime = "";
	private String effectendtime = "";
	
	private int ocgainmark = 0;
	private int mcgainmark = 0;
	private int jdgainmark = 0;
	private int dlgainmark = 0;
	
	private int ordering = 0;
	
	private ExamPaper exampaper = new ExamPaper();
	
	private ExamPaperService exampaperservice ;
	private ExamQuestionService examquestionservice;

	
	public void init() throws ServletException {
		try {
			exampaperservice = (ExamPaperService) Class.forName("com.wondersgroup.falcon.beans.exam.ExamPaperServiceImple").newInstance();
			examquestionservice = (ExamQuestionService) Class.forName("com.wondersgroup.falcon.beans.exam.ExamQuestionServiceImple").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {

	}

	
	public void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(arg0, arg1);
	}

	
	public void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		actionType = getValue(arg0,arg1,"actionType");
		if(actionType!=null&&actionType.equals("autocreate")){
			getPaperValues(arg0,arg1);
			autoCreate(arg0,arg1);
		}else if(actionType!=null&&actionType.equals("savepaper")){
			getPaperValues(arg0,arg1);
			savePaper(arg0,arg1);
		}else if(actionType.equals("papersubmit")){
			paperSubmit(arg0,arg1);
		}else if(actionType.equals("handcreate")){
			getPaperValues(arg0,arg1);
			handCreate(arg0,arg1);
		}else if(actionType.equals("checkPaper")){
			paperCheck(arg0,arg1);
		}
	}
	
	public void autoCreate(HttpServletRequest arg0,HttpServletResponse arg1) throws ServletException, IOException{
		long start = System.currentTimeMillis();
		Map groupmap = new HashMap();
		List markmap = new ArrayList();
		
		List subtypelist = exampaperservice.findQuestionType(1);
		for(int i=0;i<subtypelist.size();i++){
			
			List sublistgroup = new ArrayList();
			
			ExamQuestionType est = (ExamQuestionType)subtypelist.get(i);
			String numvalue = getValue(arg0,arg1,""+est.getIdentiname()+"num");
			String markvalue = getValue(arg0,arg1,""+est.getIdentiname()+"mark");
			
			int numintvalue = ( numvalue.equals("")?0:(Integer.parseInt(numvalue)) );
			System.out.println("est.getType and numintvalue"+est.getTypename()+" and "+numintvalue);
			int markintvalue = ( markvalue.equals("")?0:(Integer.parseInt(markvalue)) );
			
			if(numintvalue!=0){
				int[] nums = exampaperservice.getImportanceNum(numintvalue);
				if(nums!=null&&nums.length>0){
					
					for(int j=0;j<nums.length;j++){
						List sublist = exampaperservice.findByNumAndImp(nums[j],(j+1),est.getTypeid());
						sublistgroup.add(sublist);
					}
				}
			}
			groupmap.put(est.getIdentiname(),sublistgroup);
			markmap.add(new java.lang.Integer(markintvalue));
		}

		arg0.setAttribute("groupmap", groupmap);
		arg0.setAttribute("markmap", markmap);
		arg0.setAttribute("exampaper",exampaper);
		
		long end = System.currentTimeMillis();
		System.out.println("create paper use time==>>>"+(end-start));
		arg0.getRequestDispatcher("admin/exam_preview.jsp").forward(arg0, arg1);
	}
	
	public void getPaperValues(HttpServletRequest arg0,HttpServletResponse arg1){
	
		examname=getValue(arg0,arg1,"examname");
		exammark=getValue(arg0,arg1,"exammark");
		examtime = getValue(arg0,arg1,"examtime");
		effectstarttime =getValue(arg0,arg1,"effectstarttime");
		effectendtime = getValue(arg0,arg1,"effectendtime");
		
		exampaper.setEffectendtime(CommFunc.strToDate(effectendtime));
		exampaper.setEffectstarttime(CommFunc.strToDate(effectstarttime));
		exampaper.setExamname(examname);
		exampaper.setExamtime(examtime);
		exampaper.setExammark(exammark);
	}
	
	public void savePaper(HttpServletRequest arg0,HttpServletResponse arg1) throws IOException, ServletException{
		String[] subjectids = arg0.getParameterValues("subjectid");
		
		onechoicemark= Integer.parseInt((String)(getValue(arg0, arg1, "onechoicemark").equals("")?"0":getValue(arg0, arg1, "onechoicemark")));
		manychoicemark=Integer.parseInt((String) (getValue(arg0, arg1, "manychoicemark").equals("")?"0":getValue(arg0, arg1, "manychoicemark")));
		judgemark= Integer.parseInt(getValue(arg0, arg1, "judgemark").equals("")?"0":getValue(arg0, arg1, "judgemark"));	
		dialogmark= Integer.parseInt(getValue(arg0, arg1, "dialogmark").equals("")?"0":getValue(arg0, arg1, "dialogmark"));
		recordmark= Integer.parseInt(getValue(arg0, arg1, "recordmark").equals("")?"0":getValue(arg0, arg1, "recordmark"));		
		
		ExamPaper newexampaper = new ExamPaper();
		newexampaper.setEffectendtime(exampaper.getEffectendtime());
		newexampaper.setEffectstarttime(exampaper.getEffectstarttime());
		newexampaper.setExamtype("");
		newexampaper.setExamname(exampaper.getExamname());
		newexampaper.setExamtime(exampaper.getExamtime());
		newexampaper.setExammark(exampaper.getExammark());
		PrintWriter out = arg1.getWriter();
		try{
			exampaperservice.save(newexampaper);
			for(int i=0;i<subjectids.length;i++){
				System.out.println("examquestionservice===>>>"+examquestionservice);
				ExamRealQuestions ers = examquestionservice.findRealQuesById(subjectids[i]);
				ExamPaperQuestions eps = new ExamPaperQuestions();
				if(ers.getExamquestype().getTypeid().equals("1")){
					eps.setQuestionmarks(""+onechoicemark+"");
				}else if(ers.getExamquestype().getTypeid().equals("2")){
					eps.setQuestionmarks(""+manychoicemark+"");					
				}else if(ers.getExamquestype().getTypeid().equals("3")){
					eps.setQuestionmarks(""+judgemark+"");					
				}else if(ers.getExamquestype().getTypeid().equals("4")){
					eps.setQuestionmarks(""+dialogmark+"");					
				}else if(ers.getExamquestype().getTypeid().equals("5")){
					eps.setQuestionmarks(""+recordmark+"");					
				}
				eps.setRealquestion(ers);
				eps.setExampaper(newexampaper);
				eps.setOrdering(i);
				exampaperservice.save(eps);
			}			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		out.println("<script>");
		out.println("alert('试卷生成成功!')");
		out.println("</script>");
		
		arg0.getRequestDispatcher("admin/exam_testpaper_list.jsp").forward(arg0, arg1);
	}
	
	/**
	 * 答卷提交，保存答卷，计算客观题得分情况
	 * @param request
	 * @param response
	 */
	public void paperSubmit(HttpServletRequest request,HttpServletResponse response){
		Date endtime = new Date();
		String starttime = getValue(request,response,"starttime");	
		String username = getValue(request,response,"username");		
		String examid = getValue(request,response,"examid");
		
		ExamPaper ep = exampaperservice.findExamPaperById(examid);
		
		//得到答卷对应的题目ID
		String[] ocrealsubid = request.getParameterValues("ocrealsubid");	
		String[] mcrealsubid = request.getParameterValues("mcrealsubid");			
		String[] jdrealsubid = request.getParameterValues("jdrealsubid");			
		String[] dlrealsubid = request.getParameterValues("dlrealsubid");
		String[] rcrealsubid = request.getParameterValues("rcrealsubid");
		
		//得到用户提交的考卷答案
		String ockeysstr = getValue(request,response,"ockeysstr");
		String[] ockeysstrs = ockeysstr.split(",");
		String mckeysstr = getValue(request,response,"mckeysstr");
		String[] mckeysstrs = mckeysstr.split(",");
		String jdkeysstr = getValue(request,response,"jdkeysstr");
		String[] jdkeysstrs = jdkeysstr.split(",");		
		String[] dialogkeys = request.getParameterValues("dialogkeys");
		String[] recordkeys = request.getParameterValues("recordkeys");
		
		//得到题库中对应的正确答案
			//客观题的直接得到。
		String ocrealkeys = getValue(request,response,"ocrealkeys");
		String[] ocrealkeyss = ocrealkeys.split(",");			
		String mcrealkeys = getValue(request,response,"mcrealkeys");
		String[] mcrealkeyss = mcrealkeys.split(",");			
		String jdrealkeys = getValue(request,response,"jdrealkeys");
		String[] jdrealkeyss = jdrealkeys.split(",");	
			//主观题的从答案库中查询
		String [] dlrealkeyss = new String[(dlrealsubid==null?0:dlrealsubid.length)];
		for(int i=0;i<dlrealsubid.length;i++){
			ExamRealQuestions ers =  examquestionservice.findRealQuesById(dlrealsubid[i]);
			Set keyset = ers.getExamrqueskeys();
			if(keyset!=null) {
				ExamRqueKeys erk = (ExamRqueKeys)keyset.toArray()[0];
				if(erk!=null){
					dlrealkeyss[i] = erk.getExamkey().getKeycontent();
				}
			}
		}
		
		String [] rcrealkeyss = new String[(rcrealsubid==null?0:rcrealsubid.length)];
		for(int i=0;i<rcrealsubid.length;i++){
			ExamRealQuestions ers =  examquestionservice.findRealQuesById(rcrealsubid[i]);
			Set keyset = ers.getExamrqueskeys();
			if(keyset!=null) {
				ExamRqueKeys erk = (ExamRqueKeys)keyset.toArray()[0];
				if(erk!=null){
					rcrealkeyss[i] = erk.getExamkey().getKeycontent();
				}
			}
		}		
		
		//得到每题对应的分数
		int ocmark = getValue(request,response,"ocmark")==null?0:Integer.parseInt(getValue(request,response,"ocmark"));
		int mcmark = getValue(request,response,"mcmark")==null?0:Integer.parseInt(getValue(request,response,"mcmark"));
		int jdmark = getValue(request,response,"jdmark")==null?0:Integer.parseInt(getValue(request,response,"jdmark"));
		int dlmark = getValue(request,response,"dlmark")==null?0:Integer.parseInt(getValue(request,response,"dlmark"));
		int rcmark = getValue(request,response,"rcmark")==null?0:Integer.parseInt(getValue(request,response,"rcmark"));
		
		
		//计算客观题分数
		ocgainmark = gainMarks(ockeysstrs,ocrealkeyss,ocmark);
		mcgainmark = gainMarks(mckeysstrs,mcrealkeyss,mcmark);		
		jdgainmark = gainMarks(jdkeysstrs,jdrealkeyss,jdmark);	
		
		ExamAnswers ea = new ExamAnswers();
		ExamAnswerStatus eas = exampaperservice.findExamAnsStaById("1");
		ea.setExamanswerstatus(eas);
		ea.setEndtime(endtime);
		ea.setExampaper(ep);
		ea.setStarttime(CommFunc.convertString(starttime));
		ea.setUsername(username);
		ea.setUsetime("");
		ea.setWholemark((jdgainmark+mcgainmark+ocgainmark));
		exampaperservice.save(ea);
		//保存答卷
			//保存 由于上面计算分数方便的关系导致题目ID分开，此处分题型保存。因此，要保持原试卷题型及题目顺序不变，这里的程序的顺序也相应不能改变。
			//ordering 属性为排序数
		saveAnswerResub(ocrealsubid,ockeysstrs,ocrealkeyss,ea,ocmark);
		saveAnswerResub(mcrealsubid,mckeysstrs,mcrealkeyss,ea,mcmark);		
		saveAnswerResub(jdrealsubid,jdkeysstrs,jdrealkeyss,ea,jdmark);		
		saveAnswerResub(dlrealsubid,dialogkeys,dlrealkeyss,ea,dlmark);	
		saveAnswerResub(rcrealsubid,recordkeys,rcrealkeyss,ea,rcmark);	
		
		//排序计数重新从0开始
		ordering=0;
		
		request.setAttribute("examanserpager", ea);
		try {
			request.getRequestDispatcher("exam/examReview.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int gainMarks(String[] strs1,String[] strs2,int permarks){
		try{
			int gainmarks = 0;
			if(strs1!=null&&strs2!=null){
				for(int i=0;i<strs1.length;i++){
					System.out.println("str1[i] and str2[i] are ==>>>"+strs1[i]+"and "+strs2[i]);
					if(strs1[i].equals(strs2[i])){
						gainmarks+=permarks;
					}
				}
			}
			return gainmarks;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return 0;
	}
	
	public void saveAnswerResub(String[] realsubid,String[] keys,String[] realkeys,ExamAnswers ea,int marks){
		if(realsubid!=null){
			for(int i=0;i<realsubid.length;i++){
				ExamRealQuestions ers = examquestionservice.findRealQuesById(realsubid[i]);
				ExamAnswersQuestions eas = new ExamAnswersQuestions();
				eas.setAnswerkey(keys[i]);
				eas.setCorrectkey(realkeys[i]);
				
				if(keys[i].equals(realkeys[i])){
					eas.setGainmark(marks);
				}else{
					eas.setGainmark(0);
				}
				
				eas.setExamanswer(ea);
				eas.setExamrealques(ers);
				eas.setMarks(""+marks+"");
				eas.setOrdering(ordering);
				ordering++;
				exampaperservice.save(eas);
			}
		}
	}
	
	public void handCreate(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String[] realsubids = request.getParameterValues("realsubid");
		List oclist = new ArrayList();
		List mclist = new ArrayList();
		List jdlist = new ArrayList();
		List dllist = new ArrayList();
		System.out.println("realsubids.length==>>>"+realsubids.length);
		if(realsubids!=null){
			for(int i=0;i<realsubids.length;i++){
				System.out.println("realsubids["+i+"]==>>"+realsubids[i]);
				ExamRealQuestions ers = examquestionservice.findRealQuesById(realsubids[i]);
				if(ers!=null&&ers.getExamquestype()!=null){
					if(ers.getExamquestype().getTypename().trim().equals("单选题")){
						oclist.add(ers);
					}else if(ers.getExamquestype().getTypename().trim().equals("多选题")){
						mclist.add(ers);
					}else if(ers.getExamquestype().getTypename().trim().equals("判断题")){
						jdlist.add(ers);
					}else if(ers.getExamquestype().getTypename().trim().equals("问答题")){
						dllist.add(ers);
					}
				}
			}
		}
		
		request.setAttribute("oclist",oclist);
		request.setAttribute("mclist",mclist);
		request.setAttribute("jdlist",jdlist);
		request.setAttribute("dllist",dllist);
		
		request.setAttribute("onechoicemark", new java.lang.Integer(onechoicemark));
		request.setAttribute("manychoicemark", new java.lang.Integer(manychoicemark));		
		request.setAttribute("judgemark", new java.lang.Integer(judgemark));	
		request.setAttribute("dialogmark", new java.lang.Integer(dialogmark));
		
		
		request.setAttribute("exampaper",exampaper);	
		request.getRequestDispatcher("admin/exam_handpreview.jsp").forward(request,response);		
	}
	/**
	 * 审阅答卷
	 * @author JiangLi
	 * @param arg0
	 * @param arg1
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void paperCheck(HttpServletRequest arg0,HttpServletResponse arg1) throws ServletException, IOException{
		String answerpageid = getValue(arg0,arg1,"answerpageid");
		String[] recordmark = arg0.getParameterValues("recordmark");
		String[] dialogmark = arg0.getParameterValues("dialogmark");
		PrintWriter out = arg1.getWriter();
		try{
			String[] rcrealsubid = arg0.getParameterValues("rcrealsubid");
			String[] dlrealsubid = arg0.getParameterValues("dlrealsubid");
			int newmarks = 0;
			for(int i=0;i<rcrealsubid.length;i++){
				ExamAnswersQuestions eas = exampaperservice.findAnswerQuestionsByCrit(answerpageid,rcrealsubid[i]);
				System.out.println("Integer.parseInt(recordmark[i])====>>>"+Integer.parseInt(recordmark[i]));
				eas.setGainmark(Integer.parseInt(recordmark[i]));
				exampaperservice.save(eas);
				//ea.setWholemark(ea.getWholemark()+Integer.parseInt(recordmark[i]));
				newmarks+=Integer.parseInt(recordmark[i]);
			}
			
			for(int i=0;i<dlrealsubid.length;i++){
				ExamAnswersQuestions eas = exampaperservice.findAnswerQuestionsByCrit(answerpageid,dlrealsubid[i]);
				System.out.println("Integer.parseInt(dialogmark[i])====>>>"+Integer.parseInt(dialogmark[i]));
				eas.setGainmark(Integer.parseInt(dialogmark[i]));	
				exampaperservice.save(eas);
				//ea.setWholemark(ea.getWholemark()+Integer.parseInt(dialogmark[i]));
				newmarks+=Integer.parseInt(dialogmark[i]);
			}
			ExamAnswers ea = exampaperservice.finAnswerPaperById(answerpageid);
			ExamAnswerStatus east = exampaperservice.findExamAnsStaById("2");
			ea.setExamanswerstatus(east);
			ea.setWholemark(ea.getWholemark()+newmarks);
			exampaperservice.save(ea);			
		}catch(Exception ex){
			ex.printStackTrace();
			out.println("<script>");
			out.println("alert('操作失败，请重新输入！')");
			out.println("window.location='admin/exam_answerentity.jsp?examanswerid="+answerpageid+"'");
			out.println("</script>");
		}
		out.println("<script>");
		out.println("alert('操作成功！')");
		out.println("window.location='admin/exam_answerentity.jsp?examanswerid="+answerpageid+"'");
		out.println("</script>");
	}
	
	public String getValue(HttpServletRequest request,HttpServletResponse response,String para){
		String tempvalue = "";
		if(request.getParameter(para)!=null){
			tempvalue = request.getParameter(para);
			return tempvalue;
		}
		return tempvalue;
	}
	
	public void destroy() {

	}
}
