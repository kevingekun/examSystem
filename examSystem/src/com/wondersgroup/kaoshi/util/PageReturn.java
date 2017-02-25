/**
 * 
 */
package com.wondersgroup.kaoshi.util;

import java.util.List;

/**
 * <p>Title:[�����] </p>
 * <p>Description: [�๦������]</p> 
 *
 * Created by [www] [Aug 6, 2009]
 * Midified by [�޸���] [�޸�ʱ��]
 *
 */
public class PageReturn implements java.io.Serializable{
	private List returnList;
	
	
	private int total;


	public List getReturnList() {
		return returnList;
	}


	public void setReturnList(List returnList) {
		this.returnList = returnList;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}
}
