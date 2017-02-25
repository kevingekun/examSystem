package com.wondersgroup.kaoshi.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.acegi.AcegiUtil;
import com.wondersgroup.falcon.acegi.UserDetailsImpl;
import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.kaoshi.bo.EExercise;
import com.wondersgroup.kaoshi.bo.EExercisequestions;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.EUser;

public class EExerciseDAOImpl extends HibernateDaoSupport{

	/**
	 * 
	 *
	 * <p>Description:保存练习 </p>
	 * 
	 * Created by [www] [Aug 25, 2009]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param eexercise
	 */
	public void save(EExercise eexercise){
		this.getHibernateTemplate().save(eexercise);
	}
	
	/**
	 * 
	 *
	 * <p>Description:根据人员找出自己的练习题 </p>
	 * 
	 * Created by [www] [Aug 25, 2009]
	 * Midified by [修改人] [修改时间]
	 * 
	 * @param pagetool
	 * @param username
	 * @return
	 */
	public  PageReturn findMyLianxiQuestion(final PageTool pagetool,final String username,final Date sjbegin,final Date sjend)throws Exception{
		return (PageReturn) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Criteria criteria=session.createCriteria(EExercise.class);
				//设置查询条件
				
				criteria.add(Restrictions.eq("lxRyid",username));
				if (sjbegin!=null&&sjend!=null){
					criteria.add(Restrictions.between("lxKssj", sjbegin, sjend));
				}else if (sjbegin!=null&&sjend==null){
					criteria.add(Restrictions.ge("lxKssj", sjbegin));
				}else if (sjbegin==null&&sjend!=null){
					criteria.add(Restrictions.le("lxKssj", sjend));
				}
				PageReturn pageReturn=new PageReturn();
				pageReturn.setTotal(criteria.list().size());

				criteria.setMaxResults(pagetool.getSize());
				criteria.setFirstResult(pagetool.getStart());
				pageReturn.setReturnList(criteria.list());
				return pageReturn; 

			}
		});
	}
	/**
	 * 
	 *
	 * <p>Description:练习统计 </p>
	 * 
	 * Created by [www] [Aug 25, 2009]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param id
	 * @return
	 */
	public Object[][] getLianxiALL(final String dtstart,final String dtend, final int cur,final int pagesize)
	throws InfrastructureException{
		return (Object[][])HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {	
				List l = null;
				StringBuffer sql = new StringBuffer();
               //System.out.println(dtstart+"----------------");
				sql.append(" select un, dn,sum(sc) as ss ,avg(zql) as zq ,count(zql) as tt");
				sql.append("  from (select  ");
				sql.append("   t.lx_ryid as un, ");
				sql.append("    t.lx_ryxm as dn, ");
				sql.append("    round((t.lx_jssj - t.lx_kssj) * 24 * 60, 1) as sc, ");
				sql.append("    t.lx_kgtzql as zql ");
				sql.append("   from e_exercise t    ");
				sql.append("   where 1=1    ");
				if(dtstart!=null&&dtstart.length()==10&&dtend!=null&&dtend.length()==10){
					sql.append("and t.lx_jssj <= to_date('"+dtend+"','YYYY-MM-DD') ");
					sql.append("and t.lx_jssj >= to_date('"+dtstart+"','YYYY-MM-DD') ");
				}	
				sql.append("   order by  t.lx_ryid ) d ");
				sql.append("  group by un,dn     ");

				
				
				
				SQLQuery sqlQuery = s.createSQLQuery(sql.toString());
				sqlQuery.addScalar("un", new org.hibernate.type.StringType());
				sqlQuery.addScalar("dn", new org.hibernate.type.StringType());
				sqlQuery.addScalar("ss", new org.hibernate.type.DoubleType());
				sqlQuery.addScalar("zq", new org.hibernate.type.IntegerType());
				sqlQuery.addScalar("tt", new org.hibernate.type.IntegerType());
			
				//SQLQuery sqlQuery = s.createSQLQuery(sql.toString());
					l = sqlQuery.setFirstResult(cur).setMaxResults(pagesize).list();
				
				Object[][] o  = new Object[l.size()][5]; 
				for(int i=0;i<l.size();i++){
					Object[] oj = (Object[])l.get(i);
					o[i][0] = (Object)oj[0];			
					o[i][1] = (Object)oj[1];
					o[i][2] = (Object)oj[2];
					o[i][3] = (Object)oj[3];
					o[i][4] = (Object)oj[4];
					
					
				}		                                	
				return o;
			}
		});
	}
	
	
	
	public int getLianxiALLNum(final String dtstart,final String dtend)
	throws InfrastructureException{

		return (Integer)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {	
				Integer i = new Integer(0);
				StringBuffer sql = new StringBuffer();
				String aa ="";
				sql.append(" select count(*) as count from");
				sql.append(" (select un, dn,sum(sc) ,avg(zql) ");
				sql.append("  from (select ");
				sql.append("   t.lx_ryid as un, ");
				sql.append("    t.lx_ryxm as dn, ");
				sql.append("    round((t.lx_jssj - t.lx_kssj) * 24 * 60, 1) as sc, ");
				sql.append("    t.lx_kgtzql as zql ");
				sql.append("   from e_exercise t    ");
				sql.append("   where 1=1    ");
				if(dtstart!=null&&dtstart.length()==10&&dtend!=null&&dtend.length()==10){
					sql.append("and t.lx_jssj <= to_date('"+dtend+"','YYYY-MM-DD') ");
					sql.append("and t.lx_jssj >= to_date('"+dtstart+"','YYYY-MM-DD') ");
				}
				sql.append("   order by  t.lx_ryid ) d ");
				sql.append("  group by un,dn  )p   ");
				
               
			   SQLQuery sqlQuery = s.createSQLQuery(sql.toString());
			   sqlQuery.addScalar("count", new org.hibernate.type.IntegerType());
               //sqlQuery.addScalar("un", new org.hibernate.type.StringType());
               //sqlQuery.addScalar("dn", new org.hibernate.type.StringType());
			//  SQLQuery sqlQuery = s.createSQLQuery(sql.toString()).addScalar("un", new org.hibernate.type.StringType());
			   aa = sqlQuery.uniqueResult().toString();
			   i = Integer.valueOf(aa);
			   System.out.println(i.toString());
			   return i.intValue();
			}
		});
	}
	
	
	
	/**
	 * 
	 *
	 * <p>Description:加载一个练习 </p>
	 * 
	 * Created by [www] [Aug 25, 2009]
	 * Midified by [修改人] [修改时间]
	 *
	 * @param id
	 * @return
	 */
	public  EExercise load(final Long id)throws Exception{
		return (EExercise) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				return (EExercise) session.load(EExercise.class, id);

			}
		});
	}
}
