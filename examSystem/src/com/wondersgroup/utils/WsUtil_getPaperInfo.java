package com.wondersgroup.utils;



import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.shaoxing.model.JobTainningRequest2;
import com.wondersgroup.shaoxing.model.JobTainningResponse2;


/**
 * @author Administrator
 * @version $Revision$ 2015-5-18
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class WsUtil_getPaperInfo extends ClientTestCase<JobTainningRequest2, JobTainningResponse2>{

	/** 
	 * @see com.wondersgroup.shaoxing.service.ClientTestCase#getUrl()
	 */
	@Override
	protected String getUrl() {
		//return "http://35.1.132.199:8080/ws_qdskillmonitor/services/dispatchService?wsdl";
		return ProcedureUrl.WS_URL;
	}
	/**
	 * 获取对应鉴定所可下载试卷信息
	 * @param teamid
	 * @return
	 */
	public JobTainningResponse2 getPaperInfo(String teamid){
		JobTainningRequest2 input = new JobTainningRequest2();
		input.setTeamid(teamid);
		JobTainningResponse2 response  = invoke("0009", input, JobTainningResponse2.class);
		return response;
	}
	
}
