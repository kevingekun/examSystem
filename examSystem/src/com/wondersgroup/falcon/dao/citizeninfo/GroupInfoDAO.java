/*
 * @author: cjj
 * @��������: 2009-8-18
 *
 */

package com.wondersgroup.falcon.dao.citizeninfo;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.citizeninfo.DicInfo;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class GroupInfoDAO {
	public GroupInfoDAO() throws InfrastructureException {
	}

	public void getGroupInfoById(final DicInfo di,final Serializable id)
	throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {	

				s.load(di, id);
				return null;
			}
		});
	}

	public void addGroupInfo(final DicInfo di)
	throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {	
				s.save(di);
				s.flush();
				return null;
			}
		});
	}


	//ɾ��
	public void delete(final DicInfo di)
	throws InfrastructureException {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {	
				s.delete(di);
				return null;
			}
		});
	}


	//���б�
	public List getGroupList(final Long id)
	throws InfrastructureException{
		return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {				
				List groupList;

				Criteria c = s.createCriteria(DicInfo.class);
				c.add(Expression.eq("id",id));
				groupList=c.list();
				return groupList;
			}
		});
	}


	//������Ϣ�б�
	public List getPersonList()
	throws InfrastructureException{
		return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {			
				List personList;

				Criteria c = s.createCriteria(DicInfo.class);
				c.add(Expression.eq("type",new Character('1')));
				c.add(Expression.eq("removed",new Character('1')));
				c.addOrder(Order.asc("id"));
				personList=c.list();

				return personList;
			}
		});
	}


	//��λ��Ϣ�б�
	public List getCompanyList()
	throws InfrastructureException{
		return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {	
				List companyList;
				Criteria c = s.createCriteria(DicInfo.class);
				c.add(Expression.eq("type",new Character('2')));
				c.add(Expression.eq("removed",new Character('1')));
				c.addOrder(Order.asc("id"));
				companyList=c.list();
				return companyList;
			}
		});
	}



	public void makePersistent(final DicInfo di) throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {	
				s.saveOrUpdate(di);
				return null;
			}
		});
	}

}

