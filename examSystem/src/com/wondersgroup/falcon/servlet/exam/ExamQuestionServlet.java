package com.wondersgroup.falcon.servlet.exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wondersgroup.falcon.beans.exam.ExamQuestionService;
import com.wondersgroup.falcon.model.exam.ExamImportance;
import com.wondersgroup.falcon.model.exam.ExamKeys;
import com.wondersgroup.falcon.model.exam.ExamRealQuestions;
import com.wondersgroup.falcon.model.exam.ExamRqueKeys;
import com.wondersgroup.falcon.model.exam.ExamQuestionType;
import com.wondersgroup.falcon.model.exam.ExamQuestions;

public class ExamQuestionServlet extends HttpServlet{
	
	private ExamQuestionService examquestionservice;
	private String actionType = "";
	private String realquesname;
	private String realquesid;
	private String businesstype;
	private String importance;
	private String questiontype;
	
	public void init() throws ServletException {
		try {
			examquestionservice = (ExamQuestionService)Class.forName("com.wondersgroup.falcon.beans.exam.ExamQuestionServiceImple").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		doGet(arg0,arg1);
	}

	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		actionType = getValue(arg0,arg1,"actionType");
		System.out.println("servlet 开始了！！！ actionType："+actionType);
		
		
		if(actionType.equals("queryquestions")){
			querySubject(arg0,arg1);
		}else if(actionType.equals("querykey")){
			queryKey(arg0,arg1);
		}else if(actionType.equals("ocsubadd")){
			getSubjectValues(arg0,arg1);
			addOneChoiceQues(arg0,arg1);
		}else if(actionType.equals("mcsubadd")){
			getSubjectValues(arg0,arg1);			
			addManyChoiceQues(arg0,arg1);
		}else if(actionType.equals("jdsubadd")){
			getSubjectValues(arg0,arg1);			
			addjudgeQues(arg0,arg1);
		}else if(actionType.equals("dlsubadd")){
			getSubjectValues(arg0,arg1);			
			addDiologQues(arg0,arg1);
		}else if(actionType.equals("rcsubadd")){
			getSubjectValues(arg0,arg1);			
			addRecordQues(arg0,arg1);
		}else if(actionType.equals("delete")){
			deleteQuestions(arg0,arg1);
		}else if(actionType.equals("update")){
			updateQuestions(arg0,arg1);
		}else if(actionType.equals("add")){
			
			System.out.println(arg0.getParameter("ST_YWLX_ID"));
			System.out.println(arg0.getParameter("ST_ZYXID"));
			System.out.println(arg0.getParameter("ST_LX_ID"));
			System.out.println(arg0.getParameter("ST_KSZY"));
			System.out.println(arg0.getParameter("ST_WJCC"));
			System.out.println(arg0.getParameter("ST_WH"));
			System.out.println(arg0.getParameter("ST_TG"));
			System.out.println("singleoptionkey:"+arg0.getParameter("singleoptionkey"));
			System.out.println("singleoption:"+arg0.getParameter("singleoption"));
			
			//单选选项
			String singleoption[] = arg0.getParameterValues("singleoption");
			if(singleoption!=null)
			for(int i=0;i<singleoption.length;i++)
			{
				System.out.println(singleoption[i]);
			}

			
			String manychoosekey[] = arg0.getParameterValues("manychoosekey");
			if(manychoosekey!=null)
			for(int i=0;i<manychoosekey.length;i++)
			{
				System.out.println(manychoosekey[i]);
			}
			
			System.out.println("manyoption:"+arg0.getParameter("manyoption"));
			//多选选项
			String manyoption[] = arg0.getParameterValues("manyoption");
			if(manyoption!=null)
			for(int i=0;i<manyoption.length;i++)
			{
				System.out.println(manyoption[i]);
			}
			System.out.println("verdictkey:"+arg0.getParameter("verdictkey"));
			System.out.println("verdictsaykey:"+arg0.getParameter("verdictsaykey"));
			
			
			System.out.println("verdictsay:"+arg0.getParameter("verdictsay"));
			System.out.println("recordkey:"+arg0.getParameter("recordkey"));
			System.out.println("fillkey:"+arg0.getParameter("fillkey"));
			System.out.println("askkey:"+arg0.getParameter("askkey"));
			System.out.println("casekey:"+arg0.getParameter("casekey"));
			

			
		}
	}
	
	public void querySubject(HttpServletRequest arg0,HttpServletResponse arg1) throws IOException{
		String realquesname = new String(getValue(arg0,arg1,"realquesname").getBytes("iso-8859-1"));
		String businesstype = getValue(arg0,arg1,"businesstype");
		String questiontype = getValue(arg0,arg1,"questiontype");
		String importance = getValue(arg0,arg1,"importance");
		String type = getValue(arg0,arg1,"type");
		arg1.setHeader("Cache-Control","no-store");
		arg1.setHeader("Pragrma","no-cache");
		arg1.setDateHeader("Expires",0);
		PrintWriter out = arg1.getWriter();
		System.out.println("all parameters are==>>>"+realquesname+" and "+businesstype+" and "+questiontype+" and "+importance);
		
		List list = examquestionservice.findByCrits(realquesname, businesstype, questiontype, importance); 
		System.out.println("queryquesservlet -===>>>>"+list);
		if(list!=null&&list.size()>0){
			out.println("<table border=0 width=100% cellpadding=1 cellspacing=1  align=center bgcolor='#b0F0ff' >");
			out.println("<tr bgcolor='#E0F0Fd'>");
			out.println("<td width=10% align=left align=center>&nbsp;");
			if(type.equals("newwindow")){
				out.println("<a href=# onclick='getQuestions()' class=b>添加</a>");
			}else{
				out.println("<input type=checkbox name='allcheck' onclick='checkall(this.checked)'>");
				out.println("<a href=# onclick='getQuestions()' class=b >添加</a>");
			}
			out.println("</td>");
			out.println("<td  width=8% align=center align=center>序号</td>");
			out.println("<td align=center  >试题名</td>");
			out.println("<td width=15% align=center >试题类型</td>");
			out.println("<td  width=15% align=center >重要性</td>");
			out.println("</tr>");
			for(int i=0;i<list.size();i++){
				ExamRealQuestions ers = (ExamRealQuestions)list.get(i);
				out.println("<tr bgcolor='#ffffff'>");
				out.println("<td width=10% align=left align=center>&nbsp;");
				if(type.equals("newwindow")){
					out.println("<input type=radio name='singlecheck' value='"+ers.getRealquesid()+"'>");
				}else{
					out.println("<input type=checkbox name='singlecheck' value='"+ers.getRealquesid()+"'>");
				}
				
				out.println("</td>");
				out.println("<td  width=8% align=center align=center>"+(i+1)+"</td>");
				out.println("<td align=center  ><div id='"+ers.getRealquesid()+"'>"+(ers.getExamquestions()==null?"":ers.getExamquestions().getQuestionname().trim())+"</div></td>");
				out.println("<td width=15% align=center ><div id='type"+ers.getRealquesid()+"'>"+(ers.getExamquestype()==null?"":ers.getExamquestype().getTypename().trim())+"</div></td>");
				out.println("<td  width=15% align=center >"+(ers.getImportance()==null?"":ers.getImportance().getImpname().trim())+"</td>");
				out.println("</tr>");	
			}
			out.println("</table>");
		}	
	}
	
	public void queryKey(HttpServletRequest arg0,HttpServletResponse arg1) throws IOException{
		String realkeyname = getValue(arg0,arg1,"realkeyname");
		PrintWriter out = arg1.getWriter();
		
		List list = examquestionservice.findKeysByCrit(realkeyname);  
		System.out.println("querysubservlet -===>>>>"+list);
		if(list!=null&&list.size()>0){
			out.println("<table width=100% border=0 cellspadding=1 cellspacing =1 bgcolor='#E0F0FD'>");
			out.println("<tr bgcolor='#E0F0FD' align=center>");
			out.println("<td width=7%>序号</td>");
			out.println("<td>答案名</td>");
			out.println("<td width=7%>操作</td>");
			out.println("</tr>	");	
			for(int i=0;i<list.size();i++){
				ExamKeys ek = (ExamKeys)list.get(i);
				out.println("<tr bgcolor='#ffffff' align=center>");
				out.println("<td width=7%>"+(i+1)+"</td>");
				out.println("<td>"+ek.getKeycontent()+"</td>");
				out.println("<td width=7%><a href='#' class=b onclick=\"getKeyValues('"+ek.getKeycontent()+"','"+ek.getKeyid()+"')\">添加</a></td>");				
			}
			out.println("</table>");
		}			
	}
	
	public void addOneChoiceQues(HttpServletRequest arg0,HttpServletResponse arg1) throws ServletException, IOException{
		PrintWriter out = arg1.getWriter();
		try{
			String[] add_ockeyname = arg0.getParameterValues("add_ockeyname");
			String[] add_ockeyid = arg0.getParameterValues("add_ockeyid");
			String add_occrctkey = getValue(arg0,arg1,"add_occrctkey");
			
			saveExamQuestions(add_occrctkey,add_ockeyname,add_ockeyid);
			printJs(arg0,arg1);
		}catch(Exception ex){
			ex.printStackTrace();
			out.println("<script>");
			out.println("alert('操作失败，请重新输入！')");
			out.println("</script>");				
		}
		//arg0.getRequestDispatcher("/admin/exam_subjectadd.jsp").forward(arg0, arg1);
	}
	
	public void addDiologQues(HttpServletRequest arg0, HttpServletResponse arg1)throws ServletException, IOException {
		PrintWriter out = arg1.getWriter();
		try{		
		String[] add_dlkeyname = arg0.getParameterValues("add_dlkeyname");
		saveExamQuestions(add_dlkeyname[0],add_dlkeyname,null);
		printJs(arg0,arg1);				
		}catch(Exception ex){
			out.println("<script>");
			out.println("alert('操作失败，请重新输入！')");
			out.println("</script>");				
			ex.printStackTrace();
		}
	}

	public void addRecordQues(HttpServletRequest arg0, HttpServletResponse arg1)throws ServletException, IOException {
		PrintWriter out = arg1.getWriter();
		try{		
		String[] add_rckeyname = arg0.getParameterValues("add_rckeyname");
		saveExamQuestions(add_rckeyname[0],add_rckeyname,null);
		printJs(arg0,arg1);				
		}catch(Exception ex){
			out.println("<script>");
			out.println("alert('操作失败，请重新输入！')");
			out.println("</script>");				
			ex.printStackTrace();
		}
	}	
	

	public void addjudgeQues(HttpServletRequest arg0, HttpServletResponse arg1)throws ServletException, IOException {
		PrintWriter out = arg1.getWriter();
		try{			
			String[] add_jdkeyname = arg0.getParameterValues("add_jdkeyname");
			saveExamQuestions(add_jdkeyname[0],add_jdkeyname,null);
			printJs(arg0,arg1);				
		}catch(Exception ex){
			out.println("<script>");
			out.println("alert('操作失败，请重新输入！')");
			out.println("</script>");				
			ex.printStackTrace();
		}		
	}

	public void addManyChoiceQues(HttpServletRequest arg0, HttpServletResponse arg1)throws ServletException, IOException {
		PrintWriter out = arg1.getWriter();
		try{			
			String[] add_mckeyname = arg0.getParameterValues("add_mckeyname");
			String[] add_mckeyid = arg0.getParameterValues("add_mckeyid");		
			String[] add_mcorctkey = arg0.getParameterValues("add_mcorctkey");	
			
			String add_mcorctkeys = "";
			if(add_mcorctkey!=null){
				for(int i=0;i<add_mcorctkey.length;i++){
					add_mcorctkeys+=add_mcorctkey[i];
				}			
			}
			System.out.println("add_mcorctkeys is ==>>>"+add_mcorctkeys);
			saveExamQuestions(add_mcorctkeys,add_mckeyname,add_mckeyid);	
			printJs(arg0,arg1);				
		}catch(Exception ex){
			out.println("<script>");
			out.println("alert('操作失败，请重新输入！')");
			out.println("</script>");				
			ex.printStackTrace();
		}			
	}
	
	public void saveExamQuestions(String correctkey,String[] add_keyname,String[] add_keyid) {
		ExamRealQuestions ers = new ExamRealQuestions();
		ers.setBusinesstype(businesstype);
		
		if(!realquesid.equals("")){
			ExamQuestions es = (ExamQuestions) examquestionservice.findQuestionById(realquesid);
			ers.setExamquestions(es);
		}else{
			ExamQuestions es = new ExamQuestions();
			es.setQuestionname(realquesname);
			examquestionservice.save(es);   
			ers.setExamquestions(es);
		}
		ExamQuestionType est = (ExamQuestionType)examquestionservice.findTypeById(questiontype);
		ExamImportance ei = (ExamImportance)examquestionservice.findImportanceById(importance);
		ers.setExamquestype(est);
		ers.setImportance(ei);
		ers.setCorrectkey(correctkey);
		examquestionservice.save(ers);
		
		
		if(add_keyname!=null&&add_keyname.length>0){
			for(int i=0;i<add_keyname.length;i++){
				ExamRqueKeys erk = new ExamRqueKeys();
				erk.setExamrques(ers);
				if(add_keyid!=null&&!add_keyid[i].equals("")){
					ExamKeys ek = (ExamKeys) examquestionservice.findKeysById(add_keyid[i]);
					examquestionservice.save(ek);
					erk.setExamkey(ek);
				}else{
					ExamKeys ek = new ExamKeys();
					ek.setKeycontent(add_keyname[i]);
					examquestionservice.save(ek);
					erk.setExamkey(ek);
				}
				erk.setOrdering(i);
				examquestionservice.save(erk);
			}
		}
	}	
	
	public void deleteQuestions(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException{
		String[] singlecheck = arg0.getParameterValues("singlecheck");
		if(singlecheck==null||singlecheck.length==0){
			arg0.setAttribute("error","<font color=#ff0000>您没有选择任何试题，请重新选择！</font>");
			arg0.getRequestDispatcher("admin/exam_question_query.jsp").forward(arg0, arg1);
		}else{
			for(int i=0;i<singlecheck.length;i++){
				ExamRealQuestions ers = examquestionservice.findRealQuesById(singlecheck[i]);
				examquestionservice.delete(ers);
				arg0.setAttribute("error","<font color=#ff0000>删除成功！</font>");
				arg0.getRequestDispatcher("admin/exam_question_query.jsp").forward(arg0, arg1);
			}
		}
	}
	
	public void updateQuestions(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException{
		String ersid = arg0.getParameter("ersid");
		String update_questionname  = arg0.getParameter("update_questionname");
		String update_businesstype = arg0.getParameter("update_businesstype");
		String update_importance = arg0.getParameter("update_importance");
		
		ExamRealQuestions ers = examquestionservice.findRealQuesById(ersid);
		ers.setBusinesstype(update_businesstype);
		ExamQuestions eq = ers.getExamquestions();
		eq.setQuestionname(update_questionname);
		examquestionservice.save(eq);
		ers.setExamquestions(eq);
		
		ExamImportance ei = (ExamImportance)examquestionservice.findImportanceById(update_importance);
		ers.setImportance(ei);
		
		examquestionservice.save(ers);
		arg0.getRequestDispatcher("admin/exam_question_query.jsp").forward(arg0, arg1);
	}	
	
	
	public void printJs(HttpServletRequest arg0, HttpServletResponse arg1) throws IOException{
		PrintWriter out = arg1.getWriter();
		out.println("<script>");
		out.println("alert('操作成功！')");
		out.println("window.location.href='"+arg0.getContextPath()+"/admin/exam_question_add.jsp'");			
		out.println("</script>");	
	}
	
	public void getSubjectValues(HttpServletRequest arg0,HttpServletResponse arg1){
		realquesname = getValue(arg0,arg1,"realquesname");
		realquesid = getValue(arg0,arg1,"realquesid");
		businesstype = getValue(arg0,arg1,"businesstype");
		importance = getValue(arg0,arg1,"importance");	
		questiontype = getValue(arg0,arg1,"questiontype");	
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
		super.destroy();
	}
}
