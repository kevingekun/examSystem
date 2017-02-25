/**
 * 
 */
package com.wondersgroup.kaoshi.action;

import java.util.List;

import com.wondersgroup.kaoshi.service.EPapersService;
import com.wondersgroup.kaoshi.util.AbstractPageAction;
import com.wondersgroup.kaoshi.util.PageReturn;

/**
 * <p>Title:[类标题] </p>
 * <p>Description: [类功能描述]</p> 
 *
 * Created by [www] [Aug 6, 2009]
 * Midified by [修改人] [修改时间]
 *
 */
public class QueryPaperAction extends AbstractPageAction {
	private EPapersService epapersService;

	
	public void setEpapersService(EPapersService epapersService) {
		this.epapersService = epapersService;
	}
	
	public List epapers;


	public List getEpapers() {
		return epapers;
	}


	public void setEpapers(List epapers) {
		this.epapers = epapers;
	}


	/* (non-Javadoc)
	 * @see com.wondersgroup.kaoshi.util.AbstractPageAction#doAcion()
	 */
	@Override
	public String doAcion() throws Exception {
//		PageReturn lis=this.epapersService.getPapers(this.pageInfo);
//		this.epapers=lis.getReturnList();
//		this.setFY(lis, "epapers");
		return SUCCESS;
	}

}
