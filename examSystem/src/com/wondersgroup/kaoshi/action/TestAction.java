package com.wondersgroup.kaoshi.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.wondersgroup.kaoshi.service.EQuestionsService;
import com.wondersgroup.kaoshi.util.AbstractPageAction;
import com.wondersgroup.kaoshi.util.PageMaker;
import com.wondersgroup.kaoshi.util.PageTool;
/**
 * 
 * <p>Title:[类标题] </p>
 * <p>Description: [类功能描述]</p> 
 *
 * Created by [www] [Aug 4, 2009]
 * Midified by [修改人] [修改时间]
 *
 */
public class TestAction extends AbstractPageAction {
	
	private EQuestionsService es;
	
	private List result;

	public String doAcion() throws Exception {
//		this.setAction("testqq");
//		this.pageInfo.setTotal(this.es.getRow());
//		List lis=this.es.getKindEntity(pageInfo);
//		this.result=lis;
		
		 
		return SUCCESS;
	}
	
	public void setEs(EQuestionsService es) {
		this.es = es;
	}


	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}
	

}
	
