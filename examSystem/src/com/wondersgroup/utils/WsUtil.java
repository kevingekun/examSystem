package com.wondersgroup.utils;



import java.util.List;

import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.falcon.paper.model.EAnswerpaper;
import com.wondersgroup.shaoxing.model.CompZpUploadRequest;
import com.wondersgroup.shaoxing.model.CompZpUploadRequest2;
import com.wondersgroup.shaoxing.model.CompZpUploadResponse;
import com.wondersgroup.shaoxing.model.CompZpUploadResponse2;


/**
 * @author Administrator
 * @version $Revision$ 2015-5-18
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class WsUtil extends ClientTestCase<CompZpUploadRequest, CompZpUploadResponse>{

	/** 
	 * @see com.wondersgroup.shaoxing.service.ClientTestCase#getUrl()
	 */
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		//return "http://35.1.132.199:8080/ws_qdskillmonitor/services/dispatchService?wsdl";
		return ProcedureUrl.WS_URL;
	}
	
	public CompZpUploadResponse uploadToYth(List<Object[]> list,List<Object[]> list2,String sjmc,String name,Integer hgfs){
		CompZpUploadRequest input = new CompZpUploadRequest();
		input.setList(list);
		input.setAnswerPaperList(list2);
		input.setSjmc(sjmc);
		input.setName(name);
		input.setBhgfs(hgfs);
		CompZpUploadResponse response = invoke("0008",input,CompZpUploadResponse.class);
		return response;
	}
	
}
