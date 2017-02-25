package com.wondersgroup.utils;


import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.shaoxing.model.JobTainningRequest;
import com.wondersgroup.shaoxing.model.JobTainningResponse;


/**
 * @author Administrator
 * @version $Revision$ 2015-5-18
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class WsUtil_downloadPaperAndUsers extends ClientTestCase<JobTainningRequest,JobTainningResponse>{

	@Override
	protected String getUrl() {
		return ProcedureUrl.WS_URL;
	}
	
	public JobTainningResponse downloadUserFromYth(String sjid){
		JobTainningRequest input = new JobTainningRequest();
		input.setSjid(sjid);
		JobTainningResponse  response = invoke("0010", input, JobTainningResponse.class);
		return response;
	}
}
