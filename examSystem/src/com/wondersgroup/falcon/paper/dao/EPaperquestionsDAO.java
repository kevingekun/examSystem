package com.wondersgroup.falcon.paper.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Property;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.falcon.hibernate.ListenableDataSource;
import com.wondersgroup.falcon.paper.model.EPaperquestions;
import com.wondersgroup.falcon.paper.model.EPapers;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.question.hibernate.BaseHibernateDAO;
import com.wondersgroup.falcon.question.model.EQuestions;
import com.wondersgroup.falcon.question.model.EQuestiontype;
import com.wondersgroup.framework.core.exception.BusinessException;

/**
 * A data access object (DAO) providing persistence and search support for
 * EPaperquestions entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wondersgroup.falcon.paper.model.EPaperquestions
 * @author MyEclipse Persistence Tools
 */

public class EPaperquestionsDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(EPaperquestionsDAO.class);

	public void save(EPaperquestions transientInstance) {
		log.debug("saving EPaperquestions instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EPaperquestions persistentInstance) {
		log.debug("deleting EPaperquestions instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EPaperquestions findById(Long id) {
		log.debug("getting EPaperquestions instance with id: " + id);
		try {
			EPaperquestions instance = (EPaperquestions) getSession().get(
					"com.wondersgroup.falcon.paper.model.EPaperquestions", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EPaperquestions instance) {
		log.debug("finding EPaperquestions instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wondersgroup.falcon.paper.model.EPaperquestions").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding EPaperquestions instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EPaperquestions as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all EPaperquestions instances");
		try {
			String queryString = "from EPaperquestions";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<Object> paperPreview(EPapers paper, List<Object> list,Connection conn) {
		//String driver = "oracle.jdbc.driver.OracleDriver";
		//String strUrl = ProcedureUrl.PROC_URL_STRING;
		List<Object> returnList = new ArrayList<Object>();
		//Connection conn = null;
		CallableStatement proc = null;
		try {
			//Class.forName(driver);
			//conn = DriverManager.getConnection(strUrl, "","");
			proc = conn.prepareCall("{ call automatic_MakePapers(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
			proc.setString(1, paper.getSjMc());
			proc.setString(2, paper.getSjKslx());
			proc.setString(3, paper.getSjGzid());
			proc.setString(4, paper.getSjDj());
			proc.setDouble(5, paper.getSjZf());
			proc.setLong(6, paper.getSjDjsx());
			proc.setDouble(7, paper.getSjBhgfs());
			//proc.setDate(8, new Date(paper.getSjKksj().getTime()));
			//proc.setDate(9, new Date(paper.getSjYxqjzsj().getTime()));
			proc.setTimestamp(8, new Timestamp(paper.getSjKksj().getTime()));//Timestamp可以防止时分秒丢失
			proc.setTimestamp(9, new Timestamp(paper.getSjYxqjzsj().getTime()));
			proc.setLong(10, paper.getSjJjsj());
			proc.setString(11, paper.getSjLjcf());
			proc.setString(12, paper.getSjKslx());
			proc.setString(13, paper.getSjZych());
			proc.setString(14, paper.getSjNych());
			proc.setString(15, paper.getSjDj());
			proc.setLong(16, Long.valueOf((String)list.get(0)));
			proc.setLong(17, Long.valueOf((String)list.get(1)));
			proc.setLong(18, Long.valueOf((String)list.get(2)));
			proc.setLong(19, Long.valueOf((String)list.get(3)));
			proc.setLong(20, Long.valueOf((String)list.get(4)));
			proc.setLong(21, Long.valueOf((String)list.get(5)));
			proc.setString(22, paper.getSjZjrid());
			proc.registerOutParameter(23, Types.INTEGER);
			proc.registerOutParameter(24, Types.VARCHAR);
			proc.execute();
			returnList.add(proc.getLong(23));
			returnList.add(proc.getString(24));
			return returnList;
		} catch (SQLException ex2) {
			returnList.add(Long.valueOf("2"));
			returnList.add(ex2.getMessage());
			ex2.printStackTrace();
		} catch (Exception ex2) {
			returnList.add(Long.valueOf("3"));
			returnList.add(ex2.getMessage());
			ex2.printStackTrace();
		} finally {
			if (proc != null) {
				try {
					proc.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (returnList.get(0) != Long.valueOf("0")) {
			throw new BusinessException((String) returnList.get(1));
		}
		return returnList;
	}
	public List<Object> paperPreview_check(EPapers paper, String sjid,Connection conn) {
		//String driver = "oracle.jdbc.driver.OracleDriver";
		//String strUrl = ProcedureUrl.PROC_URL_STRING;
		List<Object> returnList = new ArrayList<Object>();
		//Connection conn = null;
		CallableStatement proc = null;
		try {
			//Class.forName(driver);
			//conn = DriverManager.getConnection(strUrl, "","");
			proc = conn.prepareCall("{ call checkmatic_MakePapers(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
			proc.setString(1, paper.getSjMc());
			proc.setString(2, paper.getSjKslx());
			proc.setString(3, paper.getSjGzid());
			proc.setString(4, paper.getSjDj());
			proc.setDouble(5, paper.getSjZf());
			proc.setLong(6, paper.getSjDjsx());
			proc.setDouble(7, paper.getSjBhgfs());
			//proc.setDate(8, new Date(paper.getSjKksj().getTime()));
			//proc.setDate(9, new Date(paper.getSjYxqjzsj().getTime()));
			proc.setTimestamp(8, new Timestamp(paper.getSjKksj().getTime()));//Timestamp可以防止时分秒丢失
			proc.setTimestamp(9, new Timestamp(paper.getSjYxqjzsj().getTime()));
			proc.setLong(10, paper.getSjJjsj());
			proc.setString(11, paper.getSjLjcf());
			proc.setString(12, paper.getSjKslx());
			proc.setString(13, paper.getSjZych());
			proc.setString(14, paper.getSjNych());
			proc.setString(15, paper.getSjDj());
			proc.setString(16, paper.getSjZjrid());
			proc.setString(17, sjid);
			proc.registerOutParameter(18, Types.INTEGER);
			proc.registerOutParameter(19, Types.VARCHAR);
			proc.execute();
			returnList.add(proc.getLong(18));
			returnList.add(proc.getString(19));
			return returnList;
		} catch (SQLException ex2) {
			returnList.add(Long.valueOf("2"));
			returnList.add(ex2.getMessage());
			ex2.printStackTrace();
		} catch (Exception ex2) {
			returnList.add(Long.valueOf("3"));
			returnList.add(ex2.getMessage());
			ex2.printStackTrace();
		} finally {
			if (proc != null) {
				try {
					proc.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (returnList.get(0) != Long.valueOf("0")) {
			throw new BusinessException((String) returnList.get(1));
		}
		return returnList;
	}
	public List<Object> savePaper(final String sjid,Connection conn){
		//String driver = "oracle.jdbc.driver.OracleDriver";
		//String strUrl = ProcedureUrl.PROC_URL_STRING;
		List<Object> returnList = new ArrayList<Object>();
		//Connection conn = null;
		CallableStatement proc = null;
		try {
			//Class.forName(driver);
			//conn = DriverManager.getConnection(strUrl, "","");

			proc = conn.prepareCall("{ call exam_Save(?,?,?) }");
			proc.setString(1, sjid);
			proc.registerOutParameter(2, Types.INTEGER);
			proc.registerOutParameter(3, Types.VARCHAR);
			proc.execute();
			returnList.add(proc.getLong(2));
			returnList.add(proc.getString(3));
			return returnList;
		} catch (SQLException ex2) {
			returnList.add(Long.valueOf("2"));
			returnList.add(ex2.getMessage());
			ex2.printStackTrace();
		} catch (Exception ex2) {
			returnList.add(Long.valueOf("3"));
			returnList.add(ex2.getMessage());
			ex2.printStackTrace();
		} finally {
			if (proc != null) {
				try {
					proc.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (returnList.get(0) != Long.valueOf("0")) {
			throw new BusinessException((String) returnList.get(1));
		}
		return returnList;
	}
	/**
	 * 获取系试卷下的所有试题
	 * @param paperid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, List<EQuestions>> getInfoOfPaper(final long paperid){
		return (Map<String, List<EQuestions>>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Map<String, List<EQuestions>> questionMap = new HashMap<String, List<EQuestions>>();
				Criteria criteria = session.createCriteria(EQuestiontype.class);
				criteria.add(Property.forName("zt").eq(new Long(1)));
				List<EQuestiontype> tpList = criteria.list();
				Iterator<EQuestiontype> it = tpList.iterator();
				String sql = "";
				while(it.hasNext()){
					EQuestiontype etp = it.next();
					sql = "select e.* from E_QUESTIONS e,e_Paperquestions_Tmp et where e.st_id=et.sj_stid and e.st_Check=1 and et.sj_id=:sjid and et.st_lxid=:stlx";
					List<EQuestions> list = session.createSQLQuery(sql)
							.addEntity("e",EQuestions.class)
							.setLong("sjid", paperid)
							.setLong("stlx", etp.getPriority()).list();
					if(list.size()>0){
						questionMap.put(etp.getDescriptor(), list);
					}
				}
				return questionMap;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getNumAndTotalOfPaper(final long paperid){
		return (List<Map<String, String>>) HibernateUtil.doInSession(new HibernateSessionCallback() {
			
			public Object execute(Session session) throws Throwable {
				List<Map<String, String>> list = new ArrayList<Map<String,String>>();
				Map<String, String> questionNumMap = new HashMap<String, String>();
				Map<String, String> totalMap = new HashMap<String, String>();
				Criteria criteria = session.createCriteria(EQuestiontype.class);
				criteria.add(Property.forName("zt").eq(new Long(1)));
				List<EQuestiontype> tpList = criteria.list();
				Iterator<EQuestiontype> it = tpList.iterator();
				String sql = "";
				while(it.hasNext()){
					EQuestiontype etp = it.next();
					sql = "select count(e.st_id),sum(e.st_fz) from E_QUESTIONS e,e_Paperquestions_Tmp et where e.st_id=et.sj_stid and e.st_Check=1 and et.sj_id=:sjid and et.st_lxid=:stlx  group by e.st_lxid";
					List<Object[]> list2 = session.createSQLQuery(sql)
							.setLong("sjid", paperid)
							.setLong("stlx", etp.getPriority()).list();
					if(list2.size()>0){
						Object[] o = list2.get(0);
						questionNumMap.put(etp.getDescriptor(),String.valueOf(o[0]));
						totalMap.put(etp.getDescriptor(), String.valueOf(o[1]));
					}
				}
				list.add(questionNumMap);
				list.add(totalMap);
				return list;
			}
		});
	}
}