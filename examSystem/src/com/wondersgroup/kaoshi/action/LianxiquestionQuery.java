package com.wondersgroup.kaoshi.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.model.auth.User;
import com.wondersgroup.kaoshi.bo.EExercise;
import com.wondersgroup.kaoshi.service.EExerciseService;
import com.wondersgroup.kaoshi.service.EpaperquestionsService;
import com.wondersgroup.kaoshi.util.AbstractPageAction;
import com.wondersgroup.kaoshi.util.AbstractPageNavAction;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.popedom.bo.EUser;

public class LianxiquestionQuery extends AbstractPageNavAction {
	
	//所有的试题类型
	private List equestiontypes;
	
	private EExercise eexercise;
	
	private String eexercises_id;
	
	private String username;
	
	private EExerciseService eexerciseService;
	
	//练习题集合
	private List eexercisequestionses;
	
	private List eexercises;
	
	//查询试卷和实体的连接表
	private EpaperquestionsService  epaperquestionsService;
	public void setEpaperquestionsService(
			EpaperquestionsService epaperquestionsService) {
		this.epaperquestionsService = epaperquestionsService;
	}
	public List getEexercisequestionses() {
		return eexercisequestionses;
	}
	public void setEexercisequestionses(List eexercisequestionses) {
		this.eexercisequestionses = eexercisequestionses;
	}
	public List getEexercises() {
		return eexercises;
	}
	public void setEexercises(List eexercises) {
		this.eexercises = eexercises;
	}
	public void setEexerciseService(EExerciseService eexerciseService) {
		this.eexerciseService = eexerciseService;
	}
	//传参
	private Date sjbegin;
	private Date sjend;
	
	public Date getSjbegin() {
		return sjbegin;
	}
	public void setSjbegin(Date sjbegin) {
		this.sjbegin = sjbegin;
	}
	public Date getSjend() {
		return sjend;
	}
	public void setSjend(Date sjend) {
		this.sjend = sjend;
	}
	@Override
	
	public String doAcion() throws Exception {
		AcegiUtil acegiUtil=new AcegiUtil();
		EUser usre=((UserDetailsImpl)acegiUtil.getUserDetails()).getUser();
		System.out.println(username+"姓名啊");
		if(username!=null){
			this.pageReturn=this.eexerciseService.findMyLianxiQuestion(this.pageTool,username.toString(),this.sjbegin,this.sjend);
		}else {
			this.pageReturn=this.eexerciseService.findMyLianxiQuestion(this.pageTool, usre.getUsername(),this.sjbegin,this.sjend);
			
		}
		
		eexercises=this.pageReturn.getReturnList();
		return SUCCESS;
	}
	
	public String myLianxi() throws Exception {
		this.eexercise=this.eexerciseService.load(new Long(eexercises_id));
		this.eexercisequestionses=new ArrayList();
		eexercisequestionses.addAll(eexercise.getEExercisequestionses());
		
		//找到所有的类型
		this.equestiontypes=this.epaperquestionsService.findEQuestiontype();
		return SUCCESS;
	}
	public String getEexercises_id() {
		return eexercises_id;
	}
	public void setEexercises_id(String eexercises_id) {
		this.eexercises_id = eexercises_id;
	}
	public EExercise getEexercise() {
		return eexercise;
	}
	public void setEexercise(EExercise eexercise) {
		this.eexercise = eexercise;
	}
	public List getEquestiontypes() {
		return equestiontypes;
	}
	public void setEquestiontypes(List equestiontypes) {
		this.equestiontypes = equestiontypes;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

}
