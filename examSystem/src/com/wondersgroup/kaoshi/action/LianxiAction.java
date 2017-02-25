package com.wondersgroup.kaoshi.action;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.model.auth.User;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.kaoshi.bo.EExercise;
import com.wondersgroup.kaoshi.bo.EExercisequestions;
import com.wondersgroup.kaoshi.service.EExerciseService;
import com.wondersgroup.kaoshi.service.EQuestionsService;
import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.popedom.bo.EUser;

public class LianxiAction extends ActionSupport implements SessionAware,ServletRequestAware{
	private HttpServletRequest request;
	private  final String TRAINING_SEQUENCE="TRAINING_SEQUENCE";
	private  final String TRAINING_SEQUENCE_OBJECT="TRAINING_SEQUENCE_OBJECT";
	
	private EExercise eexercise;
	//正确率
	private double zql=0.0d;
	
	private String stTg;
	
	private Long sequence;
	//一个题目
	private EQuestions equestions;
	
	//一个题目id
	private String equestions_id;
	
	//题目类型
	private Long equestions_type;
	
	//题目业务类型
	private Long seequestionBuTypes;
	
	//题目难易度
	private Long important;
	
	private Map session;
	//所有的试题类型
	private List equestiontypes;
	
	//所有的业务类型
	private List equestionBuTypes;
	
	//所有的难易度
	private List eimportances;
	
	//查询试卷和实体的连接表
	private EpaperquestionsService  epaperquestionsService;
	
	private EQuestionsService equestionsService;
	
	private EExerciseService eexerciseService;
	
	public void setEexerciseService(EExerciseService eexerciseService) {
		this.eexerciseService = eexerciseService;
	}
	public String execute() throws Exception{
		//先把做完的题目保存到session中
		EExercise eexercise=(EExercise) session.get(TRAINING_SEQUENCE_OBJECT);
		if(eexercise==null){
			eexercise=new EExercise();
			//设置开始时间
			eexercise.setLxKssj(new Date());
		}
		//设置答题数量
		eexercise.setLxDtsl(this.sequence+1);
		
		EExercisequestions eexercisequestionse=new EExercisequestions();
		EQuestions equestions=this.equestionsService.load(new Long(this.equestions_id));
		//如果是判断题
		if(equestions.getEquestiontype().getPriority()==2){
			String[] daan=this.request.getParameterValues("daan");
			String daan_str="";
			if(daan!=null){
				for(int i=0;i<daan.length;i++){
					if(i==(daan.length-1)){
						daan_str+=daan[i];
					}else{
						daan_str+=daan[i]+"||";
					}
				}
			}
			eexercisequestionse.setStDa(daan_str);
		}else if(equestions.getEquestiontype().getPriority()==4){
			eexercisequestionse.setStDa(this.request.getParameter("daan"));
			eexercisequestionse.setStDasm(this.request.getParameter("daansm"));
		}else{
			eexercisequestionse.setStDa(this.request.getParameter("daan"));
		}
		//如果是判断说明题要设置答案说明
		if(equestions.getEquestiontype().getPriority()==4){
			eexercisequestionse.setStDasm(this.request.getParameter("daansm"));
		}
		eexercisequestionse.setEquestions(equestions);
		eexercisequestionse.setEExercise(eexercise);
		eexercise.getEExercisequestionses().add(eexercisequestionse);
		session.put(TRAINING_SEQUENCE_OBJECT, eexercise);
		
		this.sequence++;
		this.equestions=this.equestionsService.findOneQuestion(this.sequence.intValue(),this.stTg,this.equestions_type.longValue(),this.seequestionBuTypes.longValue(),this.important.longValue());
		if(this.equestions==null){
			return "questionisnull";
		}else{
			this.equestions_id=new Long(equestions.getStId()).toString();
			return SUCCESS;
		}
		
		
	}
	/**
	 *
	 * <p>Description:第一道题 </p>
	 * 
	 * Created by [www] [Aug 25, 2009]
	 * Midified by [修改人] [修改时间]
	 *
	 * @return
	 * @throws Exception
	 */
	public String queryOneQuestion() throws Exception{
		session.remove(TRAINING_SEQUENCE_OBJECT);
		EExercise eexercise=(EExercise) session.get(TRAINING_SEQUENCE_OBJECT);
		if(eexercise==null){
			eexercise=new EExercise();
			//设置开始时间
			eexercise.setLxKssj(new Date());
		}
		session.put(TRAINING_SEQUENCE_OBJECT, eexercise);
		
		this.sequence=new Long(0);
		
		/*
		 *catch一个错误，如果超出了范围，证明这个类型的题目已经练习完了 
		 */
		this.equestions=this.equestionsService.findOneQuestion(0,this.stTg,this.equestions_type.longValue(),this.seequestionBuTypes.longValue(),this.important.longValue());
		
		//this.equestions_id=new Long(equestions.getStId()).toString();
		
		return SUCCESS;
		
	}
	
	
	/**
	 *
	 * <p>Description:开始页面 </p>
	 * 
	 * Created by [www] [Aug 21, 2009]
	 * Midified by [修改人] [修改时间]
	 *
	 * @return
	 * @throws Exception
	 */
	public String selectedQuestion() throws Exception{
		//找到所有的类型
		this.equestiontypes=this.epaperquestionsService.findEQuestiontype();
		
		this.equestionBuTypes=this.epaperquestionsService.findallbuType();
		
		this.eimportances=this.epaperquestionsService.findallEimportances();
		
		return SUCCESS;
	}
	/**
	 * 结束练习
	 */
	public String save() throws Exception{
		//先把做完的题目保存到session中
		EExercise eexercise=(EExercise) session.get(TRAINING_SEQUENCE_OBJECT);
		//设置结束时间
		eexercise.setLxJssj(new Date());
		//计算客观题的正确率
		Iterator it=eexercise.getEExercisequestionses().iterator();
		int count=0;
		int zqs=0;
		int kgtsl=0; //客观题数量
		while(it.hasNext()){
			EExercisequestions eexercisequestions=(EExercisequestions) it.next();
			count++;
			EQuestions equestions=eexercisequestions.getEquestions();
			//判断客观题的正确率
			long priority=equestions.getEquestiontype().getPriority();
			if(priority==1 || priority==2 || priority==3){
				kgtsl++;
				if(equestions.getStDa().equals(eexercisequestions.getStDa())){
					zqs++;
				}
			}
		}
		//得出正确率
		if(count!=0){
			//临时方法
			this.zql=(double)(Math.round((double)zqs/count*10000))/100;
		}else{
			this.zql=0.0d;
		}
		eexercise.setLxDtsl(new Long(count));
		eexercise.setLxKgtsl(new Long(kgtsl));
		eexercise.setLxKgtzql(new Double(zql));
		
		//得到人员
		AcegiUtil acegiUtil=new AcegiUtil();
		EUser user=((UserDetailsImpl)acegiUtil.getUserDetails()).getUser();
		//设置练习人员
		eexercise.setLxRyid(user.getUsername());
		eexercise.setLxRyxm(user.getRealname());
		
		if(eexercise.getLxDtsl()==null){
			eexercise.setLxDtsl(new Long(0));
		}
		this.eexerciseService.save(eexercise);
		
		this.eexercise=eexercise;
		return SUCCESS;
		
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

	public List getEquestionBuTypes() {
		return equestionBuTypes;
	}

	public void setEquestionBuTypes(List equestionBuTypes) {
		this.equestionBuTypes = equestionBuTypes;
	}

	public void setSession(Map arg0) {
		this.session=arg0;
	}
	public void setEquestionsService(EQuestionsService equestionsService) {
		this.equestionsService = equestionsService;
	}

	public EQuestions getEquestions() {
		return equestions;
	}

	public void setEquestions(EQuestions equestions) {
		this.equestions = equestions;
	}

	public String getEquestions_id() {
		return equestions_id;
	}

	public void setEquestions_id(String equestions_id) {
		this.equestions_id = equestions_id;
	}
	public Long getEquestions_type() {
		return equestions_type;
	}
	public void setEquestions_type(Long equestions_type) {
		this.equestions_type = equestions_type;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
		
	}
	public Long getSequence() {
		return sequence;
	}
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
	
	public String getStTg() {
		return stTg;
	}
	public void setStTg(String stTg) {
		this.stTg = stTg;
	}
	public Long getSeequestionBuTypes() {
		return seequestionBuTypes;
	}
	public void setSeequestionBuTypes(Long seequestionBuTypes) {
		this.seequestionBuTypes = seequestionBuTypes;
	}
	public double getZql() {
		return zql;
	}
	public void setZql(double zql) {
		this.zql = zql;
	}
	public EExercise getEexercise() {
		return eexercise;
	}
	public void setEexercise(EExercise eexercise) {
		this.eexercise = eexercise;
	}
	public List getEimportances() {
		return eimportances;
	}
	public void setEimportances(List eimportances) {
		this.eimportances = eimportances;
	}
	public Long getImportant() {
		return important;
	}
	public void setImportant(Long important) {
		this.important = important;
	}
}
