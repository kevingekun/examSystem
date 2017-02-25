package com.wondersgroup.technocracy.service;

import java.util.List;

import com.wondersgroup.popedom.bo.EuserTest;
import com.wondersgroup.technocracy.bo.Addexpert;
import com.wondersgroup.technocracy.bo.Addexperts;
import com.wondersgroup.technocracy.bo.HZ93;

 

/**
 * 专家管理-专家新增
 * @author ljt
 * @version $Revision$ 2015-07-23
 * @author ($Date$ modification by $ljt$)
 * @since 1.0
 */
public interface AddExpertService {

	public void addexpert(Addexpert expert);
	public void addexperts(Addexperts experts);
	 public void addexpertHz93(HZ93 expertHz93);
	 public List<Addexpert> queryexpert(String idnumber);
	 
}
