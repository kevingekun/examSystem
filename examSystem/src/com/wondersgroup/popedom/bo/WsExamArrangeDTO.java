package  com.wondersgroup.popedom.bo;


import com.wondersgroup.framework.core5.storeprocedure.annotation.SpIn;
import com.wondersgroup.framework.core5.storeprocedure.annotation.SpOut;
import com.wondersgroup.framework.core5.storeprocedure.bean.SpSupportBean;

public class WsExamArrangeDTO implements SpSupportBean {
 
	/**
	* 鉴定批次号
	*/
	@SpIn(order = 1)
	private String jdpcId;
	
	@SpIn(order = 2)
	private String batch;
	/**
	 * 返回代码
	 */
	@SpOut(order = 3)
	private Long retCode;

	/**
	 * 返回消息
	 */
	@SpOut(order = 4)
	private String retMsg;
	
	public WsExamArrangeDTO(String jdpcId,String batch) {
		super();
		this.jdpcId = jdpcId;
		this.batch = batch;
	}

	public Long getRetCode() {
		return retCode;
	}

	public void setRetCode(Long retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}


	public String getJdpcId() {
		return jdpcId;
	}

	public void setJdpcId(String jdpcId) {
		this.jdpcId = jdpcId;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}
	
}
