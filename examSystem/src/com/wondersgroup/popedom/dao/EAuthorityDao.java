package com.wondersgroup.popedom.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.Util.ProcedureUrl;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.kaoshi.util.PageReturn;
import com.wondersgroup.kaoshi.util.PageTool;
import com.wondersgroup.popedom.bo.EAuthority;
import com.wondersgroup.popedom.bo.EUserMenus;

public class EAuthorityDao extends HibernateDaoSupport {
	/**
	 * 
	 * 
	 * <p>
	 * Description:分页查询角色
	 * </p>
	 * 
	 * Created by [www] [Nov 16, 2009] Midified by [修改人] [修改时间]
	 * 
	 * @param pageTool
	 * @return
	 */
	public PageReturn findAllauthority(final PageTool pageTool)
			throws Exception {
		return (PageReturn) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						PageReturn pageReturn = new PageReturn();
						Criteria criteria = session
								.createCriteria(EAuthority.class);

						pageReturn.setTotal(criteria.list().size());
						criteria.setMaxResults(pageTool.getSize());
						criteria.setFirstResult(pageTool.getStart());
						criteria.addOrder(Order.asc("id"));
						// System.out.println("------------总共："+criteria.list().size());
						pageReturn.setReturnList(criteria.list());
						return pageReturn;

					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<EAuthority> findAllauthority() throws Exception {
		return (List<EAuthority>) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						PageReturn pageReturn = new PageReturn();
						Criteria criteria = session
								.createCriteria(EAuthority.class);
						criteria.addOrder(Order.asc("id"));
						return criteria.list();

					}
				});
	}

	public EAuthority load(final Long id) throws Exception {
		return (EAuthority) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						return (EAuthority) session.load(EAuthority.class, id);

					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<EAuthority> findAllEAuthorities() throws Exception {
		return (List<EAuthority>) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						Criteria criteria = session
								.createCriteria(EAuthority.class);
						return criteria.list();

					}
				});
	}

	/**
	 * 
	 * 
	 * <p>
	 * Description:跟新角色菜单
	 * </p>
	 * 
	 * Created by [www] [Nov 16, 2009] Midified by [修改人] [修改时间]
	 * 
	 * @param eauthorityid
	 * @param userMenuids
	 */
	public void updateEAuthorities(final String eauthorityid,
			final String[] userMenuids) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				// 先把角色的所有菜单都删掉
				EAuthority ea = (EAuthority) session.load(EAuthority.class,
						new Long(eauthorityid));
				ea.setMenus(null);
				session.update(ea);
				Set<EUserMenus> set = new HashSet<EUserMenus>();
				for (int i = 0; i < userMenuids.length; i++) {
					if (userMenuids[i] != null && !userMenuids[i].equals("")) {
						EUserMenus euserMenus = (EUserMenus) session.load(EUserMenus.class, userMenuids[i]);
						set.add(euserMenus);
					}
				}
				ea.setMenus(set);
				session.update(ea);
				return null;
			}
		});
	}

	/**
	 * 
	 * 
	 * <p>
	 * Description:保存一个角色
	 * </p>
	 * 
	 * Created by [www] [Nov 16, 2009] Midified by [修改人] [修改时间]
	 * 
	 * @param eauthority
	 */
	public void saveNewEauthority(final EAuthority eauthority) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.save(eauthority);
				return null;
			}
		});
	}

	public void updateEauthority(final Long id, final EAuthority eauthority)
			throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				EAuthority eauthorityold = (EAuthority) session.load(
						EAuthority.class, id);
				eauthorityold.setDescription(eauthority.getDescription());
				eauthorityold.setName(eauthority.getName());
				session.update(eauthorityold);
				return null;
			}
		});
	}

	public void deleteEauthority(final String[] deleteEauthorities)
			throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				for (int i = 0; i < deleteEauthorities.length; i++) {
					EAuthority eauthorityold = (EAuthority) session.load(
							EAuthority.class, new Long(deleteEauthorities[i]));
					session.delete(eauthorityold);
				}
				return null;
			}
		});
	}
	
	public List<Object> renewal(String userid){
		String driver = "oracle.jdbc.driver.OracleDriver";
		String strUrl = ProcedureUrl.PROC_URL_STRING;
		List<Object> returnList = new ArrayList<Object>();
		Connection conn = null;
		CallableStatement proc = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(strUrl, "","");

			proc = conn
					.prepareCall("{ call authority_renewal(?,?,?) }");
			proc.setString(1, userid);
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
}
