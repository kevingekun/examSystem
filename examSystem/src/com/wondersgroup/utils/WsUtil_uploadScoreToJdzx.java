package com.wondersgroup.utils;



import java.util.List;

import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.shaoxing.model.Answerpaper_ws;
import com.wondersgroup.shaoxing.model.Elogmonitor_ws;
import com.wondersgroup.shaoxing.model.ggjyBaseInfoRequest;
import com.wondersgroup.shaoxing.model.ggjyBaseInfoResponse;


/**
 * @author Administrator
 * @version $Revision$ 2015-5-18
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class WsUtil_uploadScoreToJdzx extends ClientTestCase<ggjyBaseInfoRequest, ggjyBaseInfoResponse>{

	/** 
	 * @see com.wondersgroup.shaoxing.service.ClientTestCase#getUrl()
	 */
	@Override
	protected String getUrl() {
		//return "http://35.1.132.199:8080/ws_qdskillmonitor/services/dispatchService?wsdl";
		return ProcedureUrl.WS_URL;
	}
	
	public ggjyBaseInfoResponse uploadScoreToJdzx(String sjid,List<Elogmonitor_ws> list1,List<Answerpaper_ws> list2){
		ggjyBaseInfoRequest input = new ggjyBaseInfoRequest();
		input.setSjid(sjid);
		input.setList1(list1);
		input.setList2(list2);
		ggjyBaseInfoResponse response = invoke("0011", input, ggjyBaseInfoResponse.class);
		return response;
	}
}
