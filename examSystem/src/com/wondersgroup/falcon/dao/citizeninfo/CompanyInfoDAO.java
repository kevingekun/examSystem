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
import com.wondersgroup.falcon.model.citizeninfo.CompanyInfo;
import com.wondersgroup.falcon.model.citizeninfo.PersonInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompanyInfoDAO {

	public CompanyInfoDAO() throws InfrastructureException {
	}

    public void getCompanyInfoById(final CompanyInfo ci,final Serializable id) throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				s.load(ci, id);
				return null ;
			}})
			;
	}
	
    public void addCompanyInfo(final CompanyInfo ci) throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				s.save(ci);
				s.flush();
				return null ;
			}})
			;
	}

	public List getCompanyInfoList(final String name,
			final String property,
			final String telephone,
			final Long groupId,
			final Date dtstart,
			final Date dtend) throws InfrastructureException {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List companyinfo;
				Criteria criteria = session.createCriteria(CompanyInfo.class);
				if(name!=null){
					if(!name.equals("")){
						criteria.add(Expression.like("name","%"+name+"%"));
					}
				}
				if(property!=null){
					if(!property.equals("")){
						criteria.add(Expression.like("property","%"+property+"%"));
					}
				}
				if(telephone!=null){
					if(!telephone.equals("")){
						criteria.add(Expression.like("telephone","%"+telephone+"%"));
					}
				}

	            if(groupId.intValue()!=-1){
	            	criteria.add(Expression.eq("groupId", groupId));
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
				return companyinfo = criteria.list();
			}

		});
	}
    
	public List getAll(final String name,
			final String property,
			final String mobile,
			final String person,
			final Long groupId,
			final Date dtstart,
			final Date dtend,
			final PageTool pageTool) throws InfrastructureException {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List companyList;
				List l2 = new ArrayList();
				StringBuffer sql = new StringBuffer();
				
				sql.append("  select * from companyInfo t ");
				sql.append(" 	where not exists (select * from ip_inform v where t.id=v.company_sn) ");
				if((name!=null)&&!name.equals("")){
					sql.append(" 	and  t.name like '%"+name+"%' ");
				}
				if((property!=null)&&!property.equals("")){
					sql.append(" 	and  t.property like '%"+property+"%' ");
				}
				if((mobile!=null)&&!mobile.equals("")){
					sql.append(" 	and  t.mobile like '%"+mobile+"%' ");
				}
				if((person!=null)&&!person.equals("")){
					sql.append(" 	and  t.person like '%"+person+"%' ");
				}
				if((groupId.intValue()!=-1)&&!groupId.equals("")){
					sql.append(" 	and  t.groupId = "+groupId+" ");
				}
				 if((dtstart!=null)&&!dtstart.equals(""))
					 sql.append("  AND t.DT>=TO_DATE('" + dtstart + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");
			     if((dtend!=null)&&!dtend.equals(""))
			    	 sql.append("  AND t.DT<=TO_DATE('" + dtend + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");
				
				sql.append(" 	order by t.id desc ");		
				
				SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
				return l2 = sqlQuery.setMaxResults(pageTool.getSize()).setFirstResult(pageTool.getStart()).list();
			}

		});
	}
	
	//��λ�б�,��ҳ��ʾ,��������֪ͨ�������
	
	public Integer getCountZc(final String name,
			final String property,
			final String mobile,
			final  String person,
			final  Long groupId,
			final  Date dtstart,
			final  Date dtend) throws InfrastructureException {
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				int i =0;
				StringBuffer sql = new StringBuffer();
				
				sql.append("  select * from companyInfo t ");
				sql.append(" 	where not exists (select * from ip_inform v where t.id=v.company_sn) ");
				if((name!=null)&&!name.equals("")){
					sql.append(" 	and  t.name like '%"+name+"%' ");
				}
				if((property!=null)&&!property.equals("")){
					sql.append(" 	and  t.property like '%"+property+"%' ");
				}
				if((mobile!=null)&&!mobile.equals("")){
					sql.append(" 	and  t.mobile like '%"+mobile+"%' ");
				}
				if((person!=null)&&!person.equals("")){
					sql.append(" 	and  t.email like '%"+person+"%' ");
				}
				if((groupId.intValue()!=-1)&&!groupId.equals("")){
					sql.append(" 	and  t.groupId = "+groupId+" ");
				}
				 if((dtstart!=null)&&!dtstart.equals(""))
					 sql.append("  AND t.DT>=TO_DATE('" + dtstart + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");
			     if((dtend!=null)&&!dtend.equals(""))
			    	 sql.append("  AND t.DT<=TO_DATE('" + dtend + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");
				
				sql.append(" 	order by t.id desc ");		
					
				SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
				//System.out.println("countxiangxi!!!"+list.size());
				
				return i = sqlQuery.list().size();
			}

		});
	}
	
	
    public void delete(final CompanyInfo ci) throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				s.delete(ci);
				return null ;
			}})
			;
	}
	//ɾ��
    public void makePersistent(final CompanyInfo ci) throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				s.saveOrUpdate(ci);
				return null ;
			}})
			;
	}



	public List companyList(final String name,
			final String property,
			final String telephone,
			final String person,
			final Long groupId,
			final Date dtstart,
			final Date dtend,
			final PageTool pageTool) throws InfrastructureException {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List personList;
				Criteria c = session.createCriteria(CompanyInfo.class);			         
				if(name!=null){
					if(!name.equals("")){
						c.add(Expression.like("name","%"+name+"%"));
					}
				}
				if(property!=null){
					if(!property.equals("")){
						c.add(Expression.like("property","%"+property+"%"));
					}
				}
				if(telephone!=null){
					if(!telephone.equals("")){
						c.add(Expression.like("telephone","%"+telephone+"%"));
					}
				}   
				if(person!=null){
					if(!person.equals("")){
						c.add(Expression.like("person","%"+person+"%"));
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
				return personList=c.setMaxResults(pageTool.getSize()).setFirstResult(pageTool.getStart()).list();
			}

		});
	}
	//��λ��Ϣ�б�
	
	public int getQueryCount(final String name,
			final String property,
			final String telephone,
			final String person,
			final Long groupId,
			final Date dtstart,
			final Date dtend) throws InfrastructureException {
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Integer i = new Integer(0);
				Criteria c = session.createCriteria(CompanyInfo.class);
				if(name!=null){
					if(!name.equals("")){
						c.add(Expression.like("name","%"+name+"%"));
					}
				}
				if(property!=null){
					if(!property.equals("")){
						c.add(Expression.like("property","%"+property+"%"));
					}
				}
				if(telephone!=null){
					if(!telephone.equals("")){
						c.add(Expression.like("telephone","%"+telephone+"%"));
					}
				}  
				if(person!=null){
					if(!person.equals("")){
						c.add(Expression.like("person","%"+person+"%"));
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
				return i = Integer.valueOf(c.setProjection(Projections.count("id")).uniqueResult().toString());
			}

		});
	}

	//��ѯ����ܼ�
	public List getAllShdc(final String name,
			final String property,
			final String mobile,
			final String person,
			final Long groupId,
			final Date dtstart,
			final  Date dtend,
			final String subjectSn,
			final  PageTool pageTool) throws InfrastructureException {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List companyList;
				List l2 = new ArrayList();
				StringBuffer sql = new StringBuffer();
				
				sql.append("  select * from companyInfo t ");
				sql.append(" 	where  not exists (select * from rr_resource v where v.subject_sn = '"+subjectSn+"' and v.company_sn=t.id) ");
				if((name!=null)&&!name.equals("")){
					sql.append(" 	and  t.name like '%"+name+"%' ");
				}
				if((property!=null)&&!property.equals("")){
					sql.append(" 	and  t.property like '%"+property+"%' ");
				}
				if((mobile!=null)&&!mobile.equals("")){
					sql.append(" 	and  t.mobile like '%"+mobile+"%' ");
				}
				if((person!=null)&&!person.equals("")){
					sql.append(" 	and  t.person like '%"+person+"%' ");
				}
				if((groupId.intValue()!=-1)&&!groupId.equals("")){
					sql.append(" 	and  t.groupId = "+groupId+" ");
				}
				 if((dtstart!=null)&&!dtstart.equals(""))
					 sql.append("  AND t.DT>=TO_DATE('" + dtstart + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");
			  if((dtend!=null)&&!dtend.equals(""))
			 	 sql.append("  AND t.DT<=TO_DATE('" + dtend + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");
				
				sql.append(" 	order by t.id desc ");		
				
				SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
				return l2 = sqlQuery.setMaxResults(pageTool.getSize()).setFirstResult(pageTool.getStart()).list();
				
			}

		});
	}
	//�����鰴�����
	

	public int getCountShdc(final String name,
			final   String property,
			final   String mobile,
			final   String person,
			final   Long groupId,
			final   Date dtstart,
			final   Date dtend, 
			final   String subjectSn) throws InfrastructureException {
		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				int i =0;
				StringBuffer sql = new StringBuffer();
				
				sql.append("  select * from companyInfo t ");
				sql.append(" 	where  not exists (select * from rr_resource v where v.subject_sn = '"+subjectSn+"' and v.company_sn=t.id) ");
				if((name!=null)&&!name.equals("")){
					sql.append(" 	and  t.name like '%"+name+"%' ");
				}
				if((property!=null)&&!property.equals("")){
					sql.append(" 	and  t.property like '%"+property+"%' ");
				}
				if((mobile!=null)&&!mobile.equals("")){
					sql.append(" 	and  t.mobile like '%"+mobile+"%' ");
				}
				if((person!=null)&&!person.equals("")){
					sql.append(" 	and  t.email like '%"+person+"%' ");
				}
				if((groupId.intValue()!=-1)&&!groupId.equals("")){
					sql.append(" 	and  t.groupId = "+groupId+" ");
				}
				 if((dtstart!=null)&&!dtstart.equals(""))
					 sql.append("  AND t.DT>=TO_DATE('" + dtstart + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");
			     if((dtend!=null)&&!dtend.equals(""))
			    	 sql.append("  AND t.DT<=TO_DATE('" + dtend + " 00:00:00" + "','yyyy-MM-dd HH24:mi:ss') ");
				
				sql.append(" 	order by t.id desc ");		
					
				SQLQuery sqlQuery =session.createSQLQuery(sql.toString());
				//System.out.println("countxiangxi!!!"+list.size());
				
				return i = sqlQuery.list().size();
			}

		});
	}
	//����,����������
	
	public List getCompanyInfo(final Long id
			) throws InfrastructureException {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List groupList;
				Criteria c = session.createCriteria(CompanyInfo.class);
				c.add(Expression.eq("id",id));
				return groupList=c.list();
				
			}

		});
	}
	//��λ�б�
  
    
	public int count(final Criterion criterion
	) throws InfrastructureException {
return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
	public Object execute(Session session) throws Throwable {
		Criteria c = session.createCriteria(CompanyInfo.class);
		c.add(criterion);
		c.setProjection(Projections.projectionList().add(Projections.rowCount()));
		return (Integer.valueOf(c.uniqueResult().toString())).intValue();
		
	}

});
}


}
