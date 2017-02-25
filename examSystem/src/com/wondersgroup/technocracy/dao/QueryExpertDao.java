package com.wondersgroup.technocracy.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.gonggao.bo.Mgg;
import com.wondersgroup.kaoshi.bo.UploadToYth;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.CalcBsFhDTO;
import com.wondersgroup.popedom.bo.EuserTest;
import com.wondersgroup.popedom.bo.ExamStaff;
import com.wondersgroup.technocracy.bo.Addexpert;
import com.wondersgroup.technocracy.bo.Addexperts;
import com.wondersgroup.technocracy.bo.HZ93;
import com.wondersgroup.technocracy.bo.Querydisplay;

public class QueryExpertDao extends HibernateDaoSupport {
	/**
	 * 考生人员新增
	 */
	@SuppressWarnings("unchecked")
	public List<Object> detailquery(final Long hzz001) throws Exception {
		return (List<Object>) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {

						List<Object> list = new ArrayList<Object>();
						Criteria criteria1 = session
								.createCriteria(Addexpert.class);
						criteria1.add(Restrictions.eq("hzz001", hzz001));
						list.add(criteria1.list().get(0));
						Criteria criteria2 = session
								.createCriteria(Addexperts.class);
						criteria2.add(Restrictions.eq("hzz001", hzz001));
						list.add(criteria2.list().get(0));
						Criteria criteria3 = session.createCriteria(HZ93.class);
						criteria3.add(Restrictions.eq("hzz001", hzz001));

						List li = criteria3.list();
						List<HZ93> list1 = new ArrayList<HZ93>();
						Iterator<HZ93> it = li.iterator();
						StringBuffer qs = new StringBuffer();
						while (it.hasNext()) {
							HZ93 o = it.next();
							// qs.append(o.getHzz911());
							if ("1".equals(o.getHzz911()))
								qs.append("考评专家" + " ");
							if ("2".equals(o.getHzz911()))
								qs.append("命题专家" + " ");
							if ("3".equals(o.getHzz911()))
								qs.append("监考老师" + " ");
							if ("4".equals(o.getHzz911()))
								qs.append("督导专家");
						}

						list.add(qs.toString());
						// list.add(criteria3.list().get(0));
						return list;

					}
				});
	}

	public PageReturn checkExpert(final PageTool pageTool, final String name,
			final String org, final String c, final String expertstyle) {
		return (PageReturn) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						PageReturn pageReturn = new PageReturn();
						StringBuffer sb = new StringBuffer();
						// sb.append(" select a.* from hz90  a, hz99  ad ,hz93 b where a.hzz001 = ad.hzz001 and a.hzz001=b.hzz001 and a.aaa131 = '1' and ad.aaa131='1'");
						sb.append(" select distinct a.aac147,a.hzz001,a.aac058,a.aac003,a.aac045,a.hzz003,a.aae005,a.hzz210 from hz90  a, hz99  ad  ");

						if (!"".equals(expertstyle) & expertstyle != null) {
							sb.append(",hz93 b ");
						}
						sb.append("where a.hzz001 = ad.hzz001 and a.aaa131 = '1' and ad.aaa131='1'");
						if (!"".equals(name) & name != null) {
							sb.append(" and a.aac003=" + "'" + name + "'");
						}
						if (!"".equals(org) & org != null) {
							sb.append(" and a.aac045=" + "'" + org + "'");
						}
						if (!"".equals(c) & c != null) {
							sb.append(" and ad.hzz118=" + "'" + c + "'");
						}
						if (!"".equals(expertstyle) & expertstyle != null) {
							sb.append("and a.hzz001=b.hzz001 and b.aaa131='1' and b.hzz911="
									+ "'" + expertstyle + "'");
						}

						Query query = session.createSQLQuery(sb.toString());
						pageReturn.setTotal(query.list().size());
						query.setFirstResult(pageTool.getStart());
						query.setMaxResults(pageTool.getSize());
						pageReturn.setReturnList(query.list());
						List li = query.list();
						List<Querydisplay> list = new ArrayList<Querydisplay>();
						Iterator<Object[]> it = li.iterator();
						while (it.hasNext()) {
							Object[] o = it.next();
							Querydisplay e = new Querydisplay();

							e.setHzz001(((BigDecimal) o[1]).longValue());

							e.setName((String) o[3]);
							e.setIdstyle((String) o[2]);
							e.setIdnumber((String) o[0]);
							e.setOrg((String) o[4]);
							e.setMajor((String) o[5]);
							e.setPhone((String) o[6]);

							e.setJtitle((String) o[7]);

							list.add(e);
						}

						pageReturn.setReturnList(list);
						return pageReturn;
					}
				});
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Integer> countAge(){
		return (List<Integer>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				
				return null;
			}
		});
	}*/
	public List<String> countAge(Connection conn){
		//String driver = "oracle.jdbc.driver.OracleDriver";  
	   // String strUrl = ProcedureUrl.PROC_URL_STRING;
	    // Connection conn = null;
	     CallableStatement proc = null;  
	     List<String> list = new ArrayList<String>();
	     try {  
	     //Class.forName(driver);  
	     //conn = DriverManager.getConnection(strUrl, "","");  
	      
	      proc = conn.prepareCall("{ call age_count(?,?,?,?,?,?,?,?,?,?) }");  
	      proc.registerOutParameter(1, Types.INTEGER);
	      proc.registerOutParameter(2, Types.INTEGER);
	      proc.registerOutParameter(3, Types.INTEGER);
	      proc.registerOutParameter(4, Types.INTEGER);
	      proc.registerOutParameter(5, Types.INTEGER);
	      proc.registerOutParameter(6, Types.INTEGER);
	      proc.registerOutParameter(7, Types.INTEGER);
	      proc.registerOutParameter(8, Types.INTEGER);
	      proc.registerOutParameter(9, Types.INTEGER);
	      proc.registerOutParameter(10, Types.INTEGER);
	      proc.execute();  
	      list.add(proc.getString(1));
	      list.add(proc.getString(2));
	      list.add(proc.getString(3));
	      list.add(proc.getString(4));
	      list.add(proc.getString(5));
	      list.add(proc.getString(6));
	      list.add(proc.getString(7));
	      list.add(proc.getString(8));
	      list.add(proc.getString(9));
	      list.add(proc.getString(10));
	      return list;
	   }  
	    catch (SQLException ex2) {
	      ex2.printStackTrace();  
	    }  
	    catch (Exception ex2) {  
	      ex2.printStackTrace();
	    }finally{  
	         if(proc!=null){  
	             try {  
	                proc.close();  
	       	    } catch (SQLException e) {  
	       	     e.printStackTrace();  
	       	      }  
	       	    }  
	       	   if(conn!=null){  
	             try {  
	                     conn.close();  
	       	     } catch (SQLException e) {  
	       	        e.printStackTrace();  
	       }  
	       	     }  
	        }
		return list; 
	}
	@SuppressWarnings("unchecked")
	public List<String> countUsenum(){
		return (List<String>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List<String> list = new ArrayList<String>();
				String sql1 = "select count(a.hzz004) from hz93 a where a.hzz004=1";
				String sql2 = "select count(a.hzz004) from hz93 a where a.hzz004=2";
				String sql3 = "select count(a.hzz004) from hz93 a where a.hzz004=3";
				String sql4 = "select count(a.hzz004) from hz93 a where a.hzz004>3";
				Query query1 = session.createSQLQuery(sql1);
				Query query2 = session.createSQLQuery(sql2);
				Query query3 = session.createSQLQuery(sql3);
				Query query4 = session.createSQLQuery(sql4);
				list.add(String.valueOf(query1.list().get(0)));
				list.add(String.valueOf(query2.list().get(0)));
				list.add(String.valueOf(query3.list().get(0)));
				list.add(String.valueOf(query4.list().get(0)));
				return list;
			}
		}); 
	}
	@SuppressWarnings("unchecked")
	public List<Integer> countAcademic(){
		return (List<Integer>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				
				return null;
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List<Integer> countCategory(){
		return (List<Integer>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				
				return null;
			}
		});
	}

}
