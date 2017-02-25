package com.wondersgroup.utils;


import junit.framework.TestCase;

import com.wondersgroup.wsscclib.client.RemoteInvoker;
import com.wondersgroup.wsscclib.core.DecryptUtils;
import com.wondersgroup.wsscclib.core.EncryptUtils;
import com.wondersgroup.wsscclib.core.MessageUtils;
import com.wondersgroup.wsscclib.core.model.Message;
import com.wondersgroup.wsscclib.core.model.ReturnResult;

/**
 * @author xieguoking
 * @version $Revision$ 2015-5-14
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public abstract class ClientTestCase<I, O> extends TestCase{

	protected abstract String getUrl();

	protected O invoke(String code, I input, Class<O> retType) {
        
		Message request = new Message();
		request.getHead().setCode(code);
		//System.out.println("----------"+1);
		request.setBody(EncryptUtils.encrypt(MessageUtils.marshall(input)));
		//System.out.println("----------"+2);
		RemoteInvoker invoker = new RemoteInvoker(getUrl());
		Message response = invoker.invoke(request);
		if (response == null) {
			throw new NullPointerException();
		}
		ReturnResult rst = response.getHead().getRst();
		if (!"00".equals(rst.getSysCode())) {
			throw new RuntimeException("[" + rst.getSysCode() + "]" + (rst.getErrMsg() == null ? "" : rst.getErrMsg()));
		}
		if (!"000000".equals(response.getHead().getRst().getBusCode())) {
			throw new RuntimeException("[" + rst.getBusCode() + "]" + (rst.getErrMsg() == null ? "" : rst.getErrMsg()));
		}
		return MessageUtils.unmarshal(DecryptUtils.decrypt(response.getBody()), retType);
	}

}
