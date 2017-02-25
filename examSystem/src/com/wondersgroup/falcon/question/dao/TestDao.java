package com.wondersgroup.falcon.question.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.question.model.EQuestions;

public class TestDao extends HibernateDaoSupport implements Test{
	
	public void getName(){
		EQuestions e=(EQuestions) this.getHibernateTemplate().load(EQuestions.class, new Long(210));
		System.out.println(e.getStTg());
	}
}
