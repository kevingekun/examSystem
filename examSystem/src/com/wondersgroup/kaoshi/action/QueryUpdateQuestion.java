package com.wondersgroup.kaoshi.action;

import java.util.List;

import com.wondersgroup.falcon.Util.NavigateForm;
import com.wondersgroup.falcon.model.archives.CaseNode;
import com.wondersgroup.falcon.model.archives.FaqNode;
import com.wondersgroup.falcon.model.archives.PolicyNode;
import com.wondersgroup.falcon.model.archives.ServiceNode;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.kaoshi.service.EQuestionsService;
import com.wondersgroup.kaoshi.util.AbstractPageNavActionNew;


public class QueryUpdateQuestion extends AbstractPageNavActionNew{
	
	private EQuestionsService equestionsService;
	
	public List questions;
	
	
	public List policyNode;
	
	public List serviceNode;
	
	public List faqNode;

	public List caseNode;
	
	
	public List policyNode_st;
	
	public List serviceNode_st;
	
	public List faqNode_st;
	
	public List caseNode_st;
	
	
	public String policyNodeId;
	
	public String serviceNodeId;
	
	public String faqNodeId;
	
	public String caseNodeId;


	//政策法规开始
	public String findPolicyNode() throws Exception {
		pageTool.setCur(this.getCurrpage());
		pageTool.setSize(this.getPagenum());
		
		this.pageReturn=this.equestionsService.findPolicyNodeByNodeState(this.pageTool);
		this.policyNode=this.pageReturn.getReturnList();	
		
        NavigateForm navigateform = new NavigateForm();
	  	navigateform.setCurrpage(this.getCurrpage());
	  	navigateform.setPagesize(this.getPagenum());
	  	navigateform.setTotal(this.pageReturn.getTotal());
	  	navigateform.setPagenum(this.getPagenum());
	  	this.getRequest().setAttribute("navigateform", navigateform);
	  	
		return SUCCESS;
	}
	
	public String findst() throws Exception {
		pageTool.setCur(this.getCurrpage());
		pageTool.setSize(this.getPagenum());
		
		this.equestionsService.updateStmodefy(policyNodeId);
		this.equestionsService.updateModefy(policyNodeId);
		this.pageReturn=this.equestionsService.findstBypolicyNodeId(policyNodeId,pageTool);
		this.policyNode_st=this.pageReturn.getReturnList();
		
        NavigateForm navigateform = new NavigateForm();
	  	navigateform.setCurrpage(this.getCurrpage());
	  	navigateform.setPagesize(this.getPagenum());
	  	navigateform.setTotal(this.pageReturn.getTotal());
	  	navigateform.setPagenum(this.getPagenum());
	  	this.getRequest().setAttribute("navigateform", navigateform);
	  	
		return SUCCESS; 
	}
	
	public String modefystate() throws Exception {
		this.equestionsService.updatestate(policyNodeId); 
		return SUCCESS; 
	}

	
	//办事指南开始
	public String findServiceNode() throws Exception {
		pageTool.setCur(this.getCurrpage());
		pageTool.setSize(this.getPagenum());
		
		this.pageReturn=this.equestionsService.findServiceNodeByNodeState(this.pageTool);
		this.serviceNode=this.pageReturn.getReturnList();
		
        NavigateForm navigateform = new NavigateForm();
	  	navigateform.setCurrpage(this.getCurrpage());
	  	navigateform.setPagesize(this.getPagenum());
	  	navigateform.setTotal(this.pageReturn.getTotal());
	  	navigateform.setPagenum(this.getPagenum());
	  	this.getRequest().setAttribute("navigateform", navigateform);
	  	
		return SUCCESS;  
	}
	
	public String findSst() throws Exception {
		pageTool.setCur(this.getCurrpage());
		pageTool.setSize(this.getPagenum());
		
		this.equestionsService.updateSmodefy(serviceNodeId);
		this.equestionsService.updateSModefy(serviceNodeId);
		this.pageReturn=this.equestionsService.findSstBypolicyNodeId(serviceNodeId,this.pageTool);
		this.serviceNode_st=this.pageReturn.getReturnList();
		
        NavigateForm navigateform = new NavigateForm();
	  	navigateform.setCurrpage(this.getCurrpage());
	  	navigateform.setPagesize(this.getPagenum());
	  	navigateform.setTotal(this.pageReturn.getTotal());
	  	navigateform.setPagenum(this.getPagenum());
	  	this.getRequest().setAttribute("navigateform", navigateform);
	  	
		return SUCCESS;  
	}

	public String modefySstate() throws Exception {
		this.equestionsService.updateSstate(serviceNodeId); 
		return SUCCESS; 
	}	
	

	//问答资料
	public String findFaqNode() throws Exception {
		pageTool.setCur(this.getCurrpage());
		pageTool.setSize(this.getPagenum());
		
		this.pageReturn=this.equestionsService.findFaqNodeByNodeState(this.pageTool);
		this.faqNode=this.pageReturn.getReturnList();
		
        NavigateForm navigateform = new NavigateForm();
	  	navigateform.setCurrpage(this.getCurrpage());
	  	navigateform.setPagesize(this.getPagenum());
	  	navigateform.setTotal(this.pageReturn.getTotal());
	  	navigateform.setPagenum(this.getPagenum());
	  	this.getRequest().setAttribute("navigateform", navigateform);
	  	
		return SUCCESS;  
	}
	
	public String findFst() throws Exception {
		pageTool.setCur(this.getCurrpage());
		pageTool.setSize(this.getPagenum());
		
		this.equestionsService.updateFmodefy(faqNodeId);
		this.equestionsService.updateFModefy(faqNodeId);
		this.pageReturn=this.equestionsService.findFstBypolicyNodeId(faqNodeId,this.pageTool);
		this.faqNode_st=this.pageReturn.getReturnList();
		
        NavigateForm navigateform = new NavigateForm();
	  	navigateform.setCurrpage(this.getCurrpage());
	  	navigateform.setPagesize(this.getPagenum());
	  	navigateform.setTotal(this.pageReturn.getTotal());
	  	navigateform.setPagenum(this.getPagenum());
	  	this.getRequest().setAttribute("navigateform", navigateform);
	  	
		return SUCCESS;  
	}
	
	public String modefyFstate() throws Exception {
		this.equestionsService.updateFstate(faqNodeId); 
		return SUCCESS; 
	}
	
	
	//学习资料
	public String findCaseNode() throws Exception {
		pageTool.setCur(this.getCurrpage());
		pageTool.setSize(this.getPagenum());
		
		this.pageReturn=this.equestionsService.findCaseNodeByNodeState(this.pageTool);
		this.caseNode=this.pageReturn.getReturnList();
		
        NavigateForm navigateform = new NavigateForm();
	  	navigateform.setCurrpage(this.getCurrpage());
	  	navigateform.setPagesize(this.getPagenum());
	  	navigateform.setTotal(this.pageReturn.getTotal());
	  	navigateform.setPagenum(this.getPagenum());
	  	this.getRequest().setAttribute("navigateform", navigateform);
	  	
		return SUCCESS;  
	}
	
	public String findCst() throws Exception {
		pageTool.setCur(this.getCurrpage());
		pageTool.setSize(this.getPagenum());
		
		this.equestionsService.updateCmodefy(caseNodeId);
		this.equestionsService.updateCModefy(caseNodeId);
		this.pageReturn=this.equestionsService.findCstBypolicyNodeId(caseNodeId,this.pageTool);
		this.caseNode_st=this.pageReturn.getReturnList();
		
        NavigateForm navigateform = new NavigateForm();
	  	navigateform.setCurrpage(this.getCurrpage());
	  	navigateform.setPagesize(this.getPagenum());
	  	navigateform.setTotal(this.pageReturn.getTotal());
	  	navigateform.setPagenum(this.getPagenum());
	  	this.getRequest().setAttribute("navigateform", navigateform);
	  	
		return SUCCESS;  
	}
	
	public String modefyCstate() throws Exception {
		this.equestionsService.updateCstate(caseNodeId); 
		return SUCCESS; 
	}
	

	
	
	public void setEquestionsService(EQuestionsService equestionsService) {
		this.equestionsService = equestionsService;
	}

	public String getServiceNodeId() {
		return serviceNodeId;
	}

	public void setServiceNodeId(String serviceNodeId) {
		this.serviceNodeId = serviceNodeId;
	}

	public String getFaqNodeId() {
		return faqNodeId;
	}

	public void setFaqNodeId(String faqNodeId) {
		this.faqNodeId = faqNodeId;
	}

	public String getCaseNodeId() {
		return caseNodeId;
	}

	public void setCaseNodeId(String caseNodeId) {
		this.caseNodeId = caseNodeId;
	}

	public List<EQuestions> getPolicyNode_st() {
		return policyNode_st;
	}
	
	public void setPolicyNode_st(List policyNode_st) {
		this.policyNode_st = policyNode_st;
	}

	public String getPolicyNodeId() {
		return policyNodeId;
	}

	public void setPolicyNodeId(String policyNodeId) {
		this.policyNodeId = policyNodeId;
	}
	
	public List<ServiceNode> getServiceNode() {
		return serviceNode;
	}

	public void setServiceNode(List serviceNode) {
		this.serviceNode = serviceNode;
	}
	
	public List<FaqNode> getFaqNode() {
		return faqNode;
	}
	
	
	public void setFaqNode(List faqNode) {
		this.faqNode = faqNode;
	}
	
	public List<CaseNode> getCaseNode() {
		return caseNode;
	}

	
	public void setCaseNode(List caseNode) {
		this.caseNode = caseNode;
	}
	
	public List<PolicyNode> getPolicyNode() {
		return policyNode;
	}

	
	public void setPolicyNode(List policyNode) {
		this.policyNode = policyNode;
	}
	
	public List<EQuestions> getQuestions() {
		return questions;
	}
	
	public void setQuestions(List questions) {
		this.questions = questions;
	}
	
	public List getServiceNode_st() {
		return serviceNode_st;
	}

	public void setServiceNode_st(List serviceNode_st) {
		this.serviceNode_st = serviceNode_st;
	}

	public List getFaqNode_st() {
		return faqNode_st;
	}

	public void setFaqNode_st(List faqNode_st) {
		this.faqNode_st = faqNode_st;
	}

	public List getCaseNode_st() {
		return caseNode_st;
	}

	public void setCaseNode_st(List caseNode_st) {
		this.caseNode_st = caseNode_st;
	}
	
}
