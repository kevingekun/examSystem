/*
 * @author: cjj
 * @��������: 2010-4-20
 *
 */

package com.wondersgroup.falcon.model.rc;

import com.wondersgroup.falcon.model.rc.RcForm;



public class RcType {
		
	private Long id;
 
	private String typeid;			//�������
	
	private RcForm rcForm;			//�ٱ�����
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTypeid() {
		return typeid;
	}

	public RcForm getRcForm() {
		return rcForm;
	}

	public void setRcForm(RcForm rcForm) {
		this.rcForm = rcForm;
	}



}

