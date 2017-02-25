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
public class WsUtil_download extends ClientTestCase<CompZpUploadRequest2, CompZpUploadResponse2>{

	/** 
	 * @see com.wondersgroup.shaoxing.service.ClientTestCase#getUrl()
	 */
	@Override
	protected String getUrl() {
		//return "http://35.1.132.199:8080/ws_qdskillmonitor/services/dispatchService?wsdl";
		return ProcedureUrl.WS_URL;
	}
	
	public CompZpUploadResponse2 downloadUserFromYth(String pcid){
		CompZpUploadRequest2 input = new CompZpUploadRequest2();
		input.setPcid(pcid);
		CompZpUploadResponse2  response = invoke("0007", input, CompZpUploadResponse2.class);
		return response;
	}
}
