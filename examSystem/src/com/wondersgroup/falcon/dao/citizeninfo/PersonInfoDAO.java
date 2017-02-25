package com.wondersgroup.falcon.dao.citizeninfo;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.wondersgroup.falcon.Util.PageTool;
import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.model.citizeninfo.PersonInfo;
import com.wondersgroup.falcon.model.rc.IpInform;
import com.wondersgroup.falcon.model.rc.RrResource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonInfoDAO {

	public PersonInfoDAO() throws InfrastructureException {

	} 


	//�û��б�
	public List getPersonInfo(final Long id)
	throws InfrastructureException{
		return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				List groupList;
				Criteria c = s.createCriteria(PersonInfo.class);
				c.add(Expression.eq("id",id));
				groupList=c.list();
				return groupList;
			}
		});
	}    


	public int count(final Criterion criterion) {
		return (Integer)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				Criteria c = s.createCriteria(PersonInfo.class);
				c.add(criterion);
				c.setProjection(Projections.projectionList().add(Projections.rowCount()));
				return (Integer.valueOf(c.uniqueResult().toString())).intValue();
			}
		});
	}


	public void getPersonInfoById(final PersonInfo pi, final Serializable id)
	throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				s.load(pi, id);
				return null;
			}
		});
	}

	public void addPersonInfo(final PersonInfo pi)
	throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				s.save(pi);
				s.flush();
				return null;
			}
		});
	}

	//�û��б�
	public List personList(final String name,
			final String idcard,
			final String mobile,
			final String email,
			final Long groupId,
			final Date dtstart,
			final Date dtend,
			final PageTool pageTool)
	throws InfrastructureException{
		return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {		
				List personList;

				Criteria c = s.createCriteria(PersonInfo.class);			         
				if(name!=null){
					if(!name.equals("")){
						c.add(Expression.like("name","%"+name+"%"));
					}
				}
				if(idcard!=null){
					if(!idcard.equals("")){
						c.add(Expression.like("idcard","%"+idcard+"%"));
					}
				}
				if(mobile!=null){
					if(!mobile.equals("")){
						c.add(Expression.like("mobile","%"+mobile+"%"));
					}
				}
				if(email!=null){
					if(!email.equals("")){
						c.add(Expression.like("email","%"+email+"%"));
					}
				}
				if(groupId.intValue()!=-1){
					c.add(Expression.eq("groupId", groupId));
				}

				if(dtstart==null){
					if(dtend!=null){
						c.add(Expression.le("dt",dtend));
					}
				}
				else{
					if(dtend==null){
						c.add(Expression.ge("dt",dtstart));
					}
					else{
						c.add(Expression.between("dt",dtstart,dtend));
					}
				}
				c.addOrder(Order.desc("id")); 
				personList=c.setMaxResults(pageTool.getSize()).setFirstResult(pageTool.getStart()).list();
				return personList;
			}
		});
	}

	public List personList1(final String name,
			final String idcard,
			final String mobile,
			final String email,
			final Long groupId,
			final Date dtstart,
			final Date dtend,
			final String receivemessage,
			final PageTool pageTool)
	throws InfrastructureException{
		return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				List personList;

				Criteria c = s.createCriteria(PersonInfo.class);
				if(!receivemessage.equals("")){
					if(receivemessage.equals("1"))
						c.add(Expression.eq("companyproperty",""+receivemessage+""));
					if(receivemessage.equals("0"))
						c.add(Expression.eq("companyproperty",""+receivemessage+""));
				}
				if(name!=null){
					if(!name.equals("")){
						c.add(Expression.like("name","%"+name+"%"));
					}
				}
				if(idcard!=null){
					if(!idcard.equals("")){
						c.add(Expression.like("idcard","%"+idcard+"%"));
					}
				}
				if(mobile!=null){
					if(!mobile.equals("")){
						c.add(Expression.like("mobile","%"+mobile+"%"));
					}
				}
				if(email!=null){
					if(!email.equals("")){
						c.add(Expression.like("email","%"+email+"%"));
					}
				}
				if(groupId.intValue()!=-1){
					c.add(Expression.eq("groupId", groupId));
				}

				if(dtstart==null){
					if(dtend!=null){
						c.add(Expression.le("dt",dtend));
					}
				}
				else{
					if(dtend==null){
						c.add(Expression.ge("dt",dtstart));
					}
					else{
						c.add(Expression.between("dt",dtstart,dtend));
					}
				}
				c.addOrder(Order.desc("id")); 
				personList=c.setMaxResults(pageTool.getSize()).setFirstResult(pageTool.getStart()).list();
				return personList;
			}
		});
	}

	//������ݵ���toExcel
	public List pList(final String name,
			final String idcard,
			final String mobile,
			final String email,
			final Long groupId,
			final Date dtstart,
			final Date dtend)
	throws InfrastructureException{
		return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				List personList;

				Criteria c = s.createCriteria(PersonInfo.class);			         
				if(name!=null){
					if(!name.equals("")){
						c.add(Expression.like("name","%"+name+"%"));
					}
				}
				if(idcard!=null){
					if(!idcard.equals("")){
						c.add(Expression.like("idcard","%"+idcard+"%"));
					}
				}
				if(mobile!=null){
					if(!mobile.equals("")){
						c.add(Expression.like("mobile","%"+mobile+"%"));
					}
				}
				if(email!=null){
					if(!email.equals("")){
						c.add(Expression.like("email","%"+email+"%"));
					}
				}

				if(groupId.intValue()!=-1){
					c.add(Expression.eq("groupId", groupId));
				}

				if(dtstart==null){
					if(dtend!=null){
						c.add(Expression.le("dt",dtend));
					}
				}
				else{
					if(dtend==null){
						c.add(Expression.ge("dt",dtstart));
					}
					else{
						c.add(Expression.between("dt",dtstart,dtend));
					}
				}

				c.addOrder(Order.desc("id")); 
				personList=c.list();
				return personList;
			}
		});
	}

	public List pList1(final String name,
			final String idcard,
			final String mobile,
			final String email,
			final Long groupId,
			final Date dtstart,
			final String receivemessage,
			final Date dtend)
	throws InfrastructureException{
		return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				List personList;

				Criteria c = s.createCriteria(PersonInfo.class);			         
				if(!receivemessage.equals("")){
					if(receivemessage.equals("1"))
						c.add(Expression.eq("companyproperty",""+receivemessage+""));
					if(receivemessage.equals("0"))
						c.add(Expression.eq("companyproperty",""+receivemessage+""));
				}
				if(name!=null){
					if(!name.equals("")){
						c.add(Expression.like("name","%"+name+"%"));
					}
				}
				if(idcard!=null){
					if(!idcard.equals("")){
						c.add(Expression.like("idcard","%"+idcard+"%"));
					}
				}
				if(mobile!=null){
					if(!mobile.equals("")){
						c.add(Expression.like("mobile","%"+mobile+"%"));
					}
				}
				if(email!=null){
					if(!email.equals("")){
						c.add(Expression.like("email","%"+email+"%"));
					}
				}

				if(groupId.intValue()!=-1){
					c.add(Expression.eq("groupId", groupId));
				}

				if(dtstart==null){
					if(dtend!=null){
						c.add(Expression.le("dt",dtend));
					}
				}
				else{
					if(dtend==null){
						c.add(Expression.ge("dt",dtstart));
					}
					else{
						c.add(Expression.between("dt",dtstart,dtend));
					}
				}

				c.addOrder(Order.desc("id")); 
				personList=c.list();
				return personList;
			}
		});
	}

	//��ѯ����ܼ�
	public int getQueryCount(final String name,
			final String idcard,
			final String mobile,
			final String email,
			final Long groupId,
			final Date dtstart,
			final Date dtend)
	throws InfrastructureException{
		return (Integer)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				Integer i = new Integer(0);
				Criteria c = s.createCriteria(PersonInfo.class);
				if(name!=null){
					if(!name.equals("")){
						c.add(Expression.like("name","%"+name+"%"));
					}
				}
				if(idcard!=null){
					if(!idcard.equals("")){
						c.add(Expression.like("idcard","%"+idcard+"%"));
					}
				}
				if(mobile!=null){
					if(!mobile.equals("")){
						c.add(Expression.like("mobile","%"+mobile+"%"));
					}
				}
				if(email!=null){
					if(!email.equals("")){
						c.add(Expression.like("email","%"+email+"%"));
					}
				}

				if(groupId.intValue()!=-1){
					c.add(Expression.eq("groupId", groupId));
				}


				if(dtstart==null){
					if(dtend!=null){
						c.add(Expression.le("dt",dtend));
					}
				}
				else{
					if(dtend==null){
						c.add(Expression.ge("dt",dtstart));
					}
					else{
						c.add(Expression.between("dt",dtstart,dtend));
					}
				}			
				i = Integer.valueOf(c.setProjection(Projections.count("id")).uniqueResult().toString());
				return i.intValue();
			}
		});
	}

//	�û��б�,��ҳ��ʾ,��������֪ͨ�������
	public List getAll (final String name,
			final String idcard,
			final String mobile,
			final String email,
			final Long groupId,
			final String dtstart,
			final String dtend,
			final PageTool pageTool) 
	throws InfrastructureException{
		return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				//List personList;
				List l2 = new ArrayList();

				StringBuffer sql = new StringBuffer();

				sql.append("  select * from personinfo t ");
				sql.append(" 	where not exists (select * from ip_inform v where t.id=v.caller_sn) ");
				if((name!=null)&&!name.equals("")){
					sql.append(" 	and  t.name like '%"+name+"%' ");
				}
				if((idcard!=null)&&!idcard.equals("")){
					sql.append(" 	and  t.idcard like '%"+idcard+"%' ");
				}
				if((mobile!=null)&&!mobile.equals("")){
					sql.append(" 	and  t.mobile like '%"+mobile+"%' ");
				}
				if((email!=null)&&!email.equals("")){
					sql.append(" 	and  t.email like '%"+email+"%' ");
				}
				if((groupId.intValue()!=-1)&&!groupId.equals("")){
					sql.append(" 	and  t.groupId = "+groupId+" ");
				}
				if((dtstart!=null)&&!dtstart.equals(""))
					sql.append("  AND t.DT>=TO_DATE('" + dtstart + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");
				if((dtend!=null)&&!dtend.equals(""))
					sql.append("  AND t.DT<=TO_DATE('" + dtend + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");

				sql.append(" 	order by t.id desc ");		

				SQLQuery sqlQuery = s.createSQLQuery(sql.toString());
				l2 = sqlQuery.setMaxResults(pageTool.getSize()).setFirstResult(pageTool.getStart()).list();

//				for (int i = 0; i < personList.size(); i++) 
//				{
//				PersonInfo pi = new PersonInfo();
//				Object[] objects = (Object[])personList.get(i);
//				pi.setId(new Long(String.valueOf(objects[0])));
//				pi.setName((String)objects[4]);
//				pi.setIdcard((String)objects[5]);
//				pi.setMobile((String)objects[10]);
//				pi.setEmail((String)objects[13]);
//				pi.setCompany((String)objects[8]);
//				pi.setTelephone((String)objects[12]);
//				pi.setCompanyproperty(objects[9].toString().toCharArray()[0]);
//				if(String.valueOf(objects[15]).equals("1")){
//				pi.setReceivemail(true);}
//				else {pi.setReceivemail(false);}
//				l2.add(pi);
//				}

				return l2;
			}
		});
	}

//	�û��б�,��ҳ��ʾ,���������鰴�����
	public List getAllShdc(final String name,
			final String idcard,
			final String mobile,
			final String email,
			final Long groupId,
			final String dtstart,
			final String dtend,
			final String subjectSn,
			final PageTool pageTool) 
	throws InfrastructureException{
		return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				//List personList;
				List l2 = new ArrayList();

				StringBuffer sql = new StringBuffer();

				sql.append("  select * from personinfo t ");
				sql.append(" 	where  not exists (select * from rr_resource v where v.subject_sn = '"+subjectSn+"' and v.caller_sn=t.id) ");
				if((name!=null)&&!name.equals("")){
					sql.append(" 	and  t.name like '%"+name+"%' ");
				}
				if((idcard!=null)&&!idcard.equals("")){
					sql.append(" 	and  t.idcard like '%"+idcard+"%' ");
				}
				if((mobile!=null)&&!mobile.equals("")){
					sql.append(" 	and  t.mobile like '%"+mobile+"%' ");
				}
				if((email!=null)&&!email.equals("")){ 
					sql.append(" 	and  t.email like '%"+email+"%' ");
				}
				if((groupId.intValue()!=-1)&&!groupId.equals("")){
					sql.append(" 	and  t.groupId = "+groupId+" ");
				}
				if((dtstart!=null)&&!dtstart.equals(""))
					sql.append("  and t.DT>=TO_DATE('" + dtstart + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");
				if((dtend!=null)&&!dtend.equals(""))
					sql.append("  and t.DT<=TO_DATE('" + dtend + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");

				sql.append(" 	order by t.id desc ");		


				SQLQuery sqlQuery = s.createSQLQuery(sql.toString());
				l2 = sqlQuery.setMaxResults(pageTool.getSize()).setFirstResult(pageTool.getStart()).list();

//				for (int i = 0; i < personList.size(); i++) 
//				{
//				PersonInfo pi = new PersonInfo();
//				Object[] objects = (Object[])personList.get(i);
//				pi.setId(new Long(String.valueOf(objects[0])));
//				pi.setName((String)objects[4]);
//				pi.setIdcard((String)objects[5]);
//				pi.setMobile((String)objects[10]);
//				pi.setEmail((String)objects[13]);
//				pi.setCompany((String)objects[8]);
//				pi.setTelephone((String)objects[12]);
//				if(objects[19]!=null&&!((String)objects[19]).equals("")){
//				pi.setGroupId(Long.valueOf(objects[19].toString()));
//				}
//				pi.setCompanyproperty(objects[9].toString().toCharArray()[0]);
//				if(String.valueOf(objects[15]).equals("1")){
//				pi.setReceivemail(true);}
//				else {pi.setReceivemail(false);}
//				l2.add(pi);
//				}
				return l2;
			}
		});
	}


//	���и����û�
	public List getAllPerson()
	throws InfrastructureException{
		return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				List l;

				try{
					Criteria c = s.createCriteria(PersonInfo.class);
					c.addOrder(Order.desc("id"));
					l=c.list();
				}catch(HibernateException ex){
					throw new InfrastructureException(ex);
				}
				return l;
			}
		});
	}

//	����
	public int getCount()
	throws InfrastructureException{
		return (Integer)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				Integer i = new Integer(0);
				Criteria c = s.createCriteria(PersonInfo.class);
				i = Integer.valueOf(c.setProjection(Projections.count("id")).uniqueResult().toString());
				return i.intValue();	
			}
		});
	}


//	����,��������֪ͨ
	public int getCountZc(final	String name,
			final String idcard,
			final String mobile,
			final String email,
			final Long groupId,
			final String dtstart,
			final String dtend)
	throws InfrastructureException{
		return (Integer)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				int i =0;
				StringBuffer sql = new StringBuffer();

				sql.append("  select * from personinfo t ");
				sql.append(" 	where not exists (select * from ip_inform v where t.id=v.caller_sn) ");
				if((name!=null)&&!name.equals("")){
					sql.append(" 	and  t.name like '%"+name+"%' ");
				}
				if((idcard!=null)&&!idcard.equals("")){
					sql.append(" 	and  t.idcard like '%"+idcard+"%' ");
				}
				if((mobile!=null)&&!mobile.equals("")){
					sql.append(" 	and  t.mobile like '%"+mobile+"%' ");
				}
				if((email!=null)&&!email.equals("")){
					sql.append(" 	and  t.email like '%"+email+"%' ");
				}
				if((groupId.intValue()!=-1)&&!groupId.equals("")){
					sql.append(" 	and  t.groupId = "+groupId+" ");
				}
				if((dtstart!=null)&&!dtstart.equals("")){
					sql.append("  and t.DT>=TO_DATE('" + dtstart + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");
				}
				if((dtend!=null)&&!dtend.equals("")){
					sql.append("  and t.DT<=TO_DATE('" + dtend + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");
				}

				sql.append(" 	order by t.id desc ");		

				SQLQuery sqlQuery = s.createSQLQuery(sql.toString());
				//System.out.println("countxiangxi!!!"+list.size());

				i = sqlQuery.list().size();

				return i;
			}
		});
	}

//	����,����������
	public int getCountShdc(final String name,
			final String idcard,
			final String mobile,
			final String email,
			final Long groupId,
			final String dtstart,
			final String dtend,
			final String subjectSn)
	throws InfrastructureException{
		return (Integer)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				int i =0;

				StringBuffer sql = new StringBuffer();

				sql.append("  select * from personinfo t ");
				sql.append(" 	where  not exists (select * from rr_resource v where v.subject_sn = '"+subjectSn+"' and v.caller_sn=t.id) ");
				if((name!=null)&&!name.equals("")){
					sql.append(" 	and  t.name like '%"+name+"%' ");
				}
				if((idcard!=null)&&!idcard.equals("")){
					sql.append(" 	and  t.idcard like '%"+idcard+"%' ");
				}
				if((mobile!=null)&&!mobile.equals("")){
					sql.append(" 	and  t.mobile like '%"+mobile+"%' ");
				}
				if((email!=null)&&!email.equals("")){
					sql.append(" 	and  t.email like '%"+email+"%' ");
				}
				if((groupId.intValue()!=-1)&&!groupId.equals("")){
					sql.append(" 	and  t.groupId = "+groupId+" ");
				}
				if((dtstart!=null)&&!dtstart.equals("")){
					sql.append("  and t.DT>=TO_DATE('" + dtstart + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");
				}
				if((dtend!=null)&&!dtend.equals("")){
					sql.append("  and t.DT<=TO_DATE('" + dtend + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");
				}

				sql.append(" 	order by t.id desc ");		

				SQLQuery sqlQuery = s.createSQLQuery(sql.toString());
				//System.out.println("countxiangxi!!!"+list.size());

				i = sqlQuery.list().size();

				return i;
			}
		});
	}


//	�ж��Ƿ���IpInfo���Ѵ���
	public boolean getIpInfoCount(final Long id)
	throws InfrastructureException{
		return (Boolean)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				Integer i = new Integer(0);

				Criteria c = s.createCriteria(IpInform.class);
				c.add(Expression.eq("personInfo.id",id));
				i = Integer.valueOf(c.setProjection(Projections.count("id")).uniqueResult().toString());

				if(i.intValue()>0){
					return true;
				}
				else{
					return false;
				}}
		});
	}

//	�ж��Ƿ���RrResource���Ѵ���
	public boolean getRrResourceCount(final Long id)
	throws InfrastructureException{
		return (Boolean)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				Integer i = new Integer(0);
				Criteria c = s.createCriteria(RrResource.class);
				c.add(Expression.eq("personInfo.id",id));
				i = Integer.valueOf(c.setProjection(Projections.count("id")).uniqueResult().toString());
				if(i.intValue()>0){
					return true;
				}
				else{
					return false;
				}
			}
		});
	}

//	ɾ��
	public void delete(final PersonInfo pi)
	throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				s.delete(pi);
				return null;
			}});
	}



	public List getPersonInfoList(final String name,
			final String idcard,
			final String mobile,
			final String PASNumber,
			final String telephone,
			final String email,
			final Date dtstart,
			final Date dtend)
	throws InfrastructureException{
		return (List)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				List personinfo;

				Criteria criteria = s.createCriteria(PersonInfo.class);
				if(name!=null){
					if(!name.equals("")){
						criteria.add(Expression.like("name","%"+name+"%"));
					}
				}
				if(idcard!=null){
					if(!idcard.equals("")){
						criteria.add(Expression.like("idcard","%"+idcard+"%"));
					}
				}
				if(mobile!=null){
					if(!mobile.equals("")){
						criteria.add(Expression.like("mobile","%"+mobile+"%"));
					}
				}
				if(PASNumber!=null){
					if(!PASNumber.equals("")){
						criteria.add(Expression.like("PASNumber","%"+PASNumber+"%"));
					}
				}
				if(telephone!=null){
					if(!telephone.equals("")){
						criteria.add(Expression.like("telephone","%"+telephone+"%"));
					}
				}
				if(email!=null){
					if(!email.equals("")){
						criteria.add(Expression.like("email","%"+email+"%"));
					}
				}

				if(dtstart==null){
					if(dtend!=null){
						criteria.add(Expression.le("dt",dtend));
					}
				}
				else{
					if(dtend==null){
						criteria.add(Expression.ge("dt",dtstart));
					}
					else{
						criteria.add(Expression.between("dt",dtstart,dtend));
					}
				}

				criteria.addOrder(Order.desc("id"));
				personinfo = criteria.list();

				return personinfo;
			}
		});
	}

	public void makePersistent(final PersonInfo pi) throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				s.saveOrUpdate(pi);
				return null;
			}
		});
	}

}
