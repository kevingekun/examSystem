/**
 * 
 */
package com.wondersgroup.falcon.beans.archives;

import com.wondersgroup.falcon.model.archives.Node;

/**
 * <p>Title:[类标题] </p>
 * <p>Description: [类功能描述]</p> 
 *
 * Created by [Kevin Liang] [2009-9-15]
 * Midified by [修改人] [修改时间]
 *
 */
public class FactoryBean {

	/**
	 * 
	 */
	public FactoryBean() {
		// TODO Auto-generated constructor stub
	}

	public static AbstractTree creator(String which){

		if (which.equals(Node.NODE_Policy))
			
			return new PolicyTree();
		
		else if (which.equals(Node.NODE_Case))
			
			return new CaseTree();
		else if (which.equals(Node.NODE_Faq))
			
			return new FaqTree();
		else if (which.equals(Node.NODE_Service))
			
			return new ServiceTree();
		
		else
			return null;

	}


}
