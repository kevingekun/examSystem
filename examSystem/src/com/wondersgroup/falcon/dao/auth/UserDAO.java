package com.wondersgroup.falcon.dao.auth;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wondersgroup.falcon.exceptions.ConstraintViolationException;
import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.auth.Authority;
import com.wondersgroup.falcon.model.auth.Group;
import com.wondersgroup.falcon.model.auth.User;
import com.wondersgroup.falcon.model.auth.UserType;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.popedom.bo.EUser;

public class UserDAO extends HibernateDaoSupport {

	// 用户帐号状态: enabled, locked, disabled
	public static final Byte ENABLED = new Byte((byte) 1);
	public static final Byte LOCKED = new Byte((byte) 2);
	public static final Byte DISABLED = new Byte((byte) 3);

	// // 座席状态
	// 未登录
	public static final Byte US_LOGOFF = new Byte((byte) 1);
	public static final Byte US_NOTREADY = new Byte((byte) 4);

	public static final Long GROUP_AGENT = new Long(1);
	public static final Long GROUP_SPECIAL = new Long(2);
	public static final Long GROUP_CAPTAIN = new Long(3);
	public static final Long GROUP_MANAGER = new Long(4);
	public static final Long GROUP_LEADER = new Long(5);
	public static final Long GROUP_OTHER = new Long(99);

	// 用户类型
	public static final Byte UT_NORMAL = new Byte((byte) 1);
	public static final Byte UT_TEST = new Byte((byte) 2);

	public UserDAO() {
		// HibernateUtil.beginTransaction();
	}

	public User getUserById(final Long userId, final boolean lock)
			throws Exception {
		return (User) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				User user = null;
				try {
					if (lock) {
						user = (User) session.load(User.class, userId,
								LockMode.UPGRADE);
					} else {
						user = (User) session.load(User.class, userId);
					}
				} catch (HibernateException ex) {
					throw new InfrastructureException(ex);
				}
				return user;

			}
		});
	}

	/**
	 * Get user according to usrename.
	 * 
	 * @param username
	 * @return
	 */
	public EUser getUserByUsername(final String username) throws Exception {
		return (EUser) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						EUser user = null;
						try {
							user = (EUser) session.createCriteria(EUser.class)
									.add(Expression.eq("username", username))
									.uniqueResult();
						} catch (HibernateException ex) {
							throw new InfrastructureException(ex);
						}
						return user;

					}
				});
	}
	public EUser getUserByUsernameAndPassword(final String username,final String password) throws Exception {
		return (EUser) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						EUser user = null;
						try {
							user = (EUser) session.createCriteria(EUser.class)
									.add(Restrictions.eq("username", username))
									.add(Restrictions.eq("password", password))
									.uniqueResult();
						} catch (HibernateException ex) {
							throw new InfrastructureException(ex);
						}
						return user;

					}
				});
	}

	/**
	 * Get user according to usrename.
	 * 
	 * @param username
	 * @return
	 */
	public List getByName(final String username) throws Exception {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try {
					String hql = "from User t where t.visible=1 and t.realname='"
							+ username + "'";
					Query query = session.createQuery(hql);
					System.out.println(query.list().size());
					return query.list();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return null;
			}
		});
	}

	/**
	 * Get user according to usrename.
	 * 
	 * @param username
	 * @return
	 */
	public List getByGonghao(final String username) throws Exception {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				List user = null;
				try {
					user = (List) session.createCriteria(User.class)
							.add(Expression.eq("username", username))
							.uniqueResult();
				} catch (HibernateException ex) {
					throw new InfrastructureException(ex);
				}
				return user;

			}
		});
	}

	public Authority getAuthById(final Long id) throws Exception {
		return (Authority) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						Authority user = null;
						try {
							user = (Authority) session
									.createCriteria(Authority.class)
									.add(Expression.eq("id", id))
									.uniqueResult();
						} catch (HibernateException ex) {
							throw new InfrastructureException(ex);
						}
						return user;

					}
				});
	}

	/**
	 * Get all users.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findAll() throws Exception {
		return (Collection) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {

						Collection users;
						try {
							users = session.createCriteria(User.class).list();
						} catch (HibernateException ex) {
							throw new InfrastructureException(ex);
						}
						return users;

					}
				});
	}

	/**
	 * Get all users.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */

	public List findByAll() throws Exception {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try {
					String hql = "from User t order by t.id";
					Query query = session.createQuery(hql);
					System.out.println(query.list().size());
					return query.list();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return null;

			}
		});
	}

	public void addUserinfo(final User pi) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try {
					session.saveOrUpdate(pi);
					session.flush();
				} catch (HibernateException ex) {
					throw new InfrastructureException(ex);
				}
				return null;
			}
		});
	}

	public void addAuth(final Authority pi) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try {
					session.save(pi);
					session.flush();
				} catch (HibernateException ex) {
					throw new InfrastructureException(ex);
				}
				return null;
			}
		});
	}

	/**
	 * Find users according example.
	 * 
	 * @param exampleUser
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection findByExample(final User exampleUser, final Long groupId,
			final Long authorityId) throws Exception {
		return (Collection) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						Collection users;
						try {
							Criteria crit = session.createCriteria(User.class);
							if (groupId != null)
								crit = crit.add(Restrictions.eq("group.id",
										groupId));
							if (authorityId == null)
								crit = crit.add(Example.create(exampleUser)
										.enableLike(MatchMode.ANYWHERE));
							else
								crit = crit
										.add(Example.create(exampleUser)
												.enableLike(MatchMode.ANYWHERE))
										.createCriteria("authorities")
										.add(Restrictions.idEq(authorityId));
							users = crit.list();
						} catch (HibernateException ex) {
							throw new InfrastructureException(ex);
						}
						return users;

					}
				});
	}

	/**
	 * Get logoff users.
	 * 
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getLogoffUsers() throws Exception {
		return (Collection) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						Collection users;
						try {
							Criteria crit = session
									.createCriteria(User.class)
									.add(Restrictions.eq("enabled",
											UserDAO.ENABLED))
									.add(Restrictions.eq("userType",
											UserDAO.UT_NORMAL))
									.add(Restrictions.eq("status",
											UserDAO.US_LOGOFF))
									.add(Restrictions.ne("group.id",
											UserDAO.GROUP_OTHER))
									.add(Restrictions.ne("group.id",
											UserDAO.GROUP_LEADER));
							users = crit.list();
						} catch (HibernateException ex) {
							throw new InfrastructureException(ex);
						}
						return users;

					}
				});
	}

	/**
	 * Get users by authority id.
	 * 
	 * @param authorityId
	 * @return
	 * @throws InfrastructureException
	 */
	public Collection getUsersByAuthorityId(final Long authorityId)
			throws Exception {
		return (Collection) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						Collection users;
						try {
							Criteria crit = session
									.createCriteria(User.class)
									.add(Restrictions.eq("enabled",
											UserDAO.ENABLED))
									.add(Restrictions.ne("userType",
											UserDAO.UT_TEST));
							if (authorityId != null)
								crit = crit.createCriteria("authorities").add(
										Restrictions.idEq(authorityId));
							users = crit.list();
						} catch (HibernateException ex) {
							throw new InfrastructureException(ex);
						}
						return users;

					}
				});
	}

	/**
	 * Make user persistent.
	 * 
	 * @param user
	 * @throws InfrastructureException
	 * @throws ConstraintViolationException
	 */
	public void makePersistent(final User user) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try {
					session.saveOrUpdate(user);
				} catch (HibernateException ex) {
					throw new InfrastructureException(ex);
				}
				return null;
			}
		});
	}

	/**
	 * Delete user.
	 * 
	 * @param user
	 * @throws InfrastructureException
	 */
	public void makeTransient(final User user) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try {
					session.delete(user);
				} catch (HibernateException ex) {
					throw new InfrastructureException(ex);
				}
				return null;
			}
		});
	}

	/**
	 * Reset user's authorities.
	 * 
	 * @param userId
	 * @param authorityIds
	 * @throws InfrastructureException
	 */
	public void resetAuthorities(final Long userId, final Long[] authorityIds)
			throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try {
					User user = new UserDAO().getUserById(userId, false);
					user.clearAuthorities();

					for (int i = 0; i < authorityIds.length; i++) {
						Authority authority = (Authority) session.load(
								Authority.class, authorityIds[i]);
						user.addAuthority(authority);
					}
					session.update(user);
				} catch (HibernateException ex) {
					throw new InfrastructureException(ex);
				}
				return null;
			}
		});
	}

	public Authority findAuthById(final Long authid) throws Exception {
		return (Authority) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						try {
							Authority auth = (Authority) session.load(
									Authority.class, authid);
							return auth;
						} catch (HibernateException ex) {

						}
						return null;

					}
				});
	}

	public List findOnlyUsers() throws Exception {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try {
					String hql = "from Users ";
					Query query = session.createQuery(hql);
					List list = query.list();
					return list;

				} catch (HibernateException ex) {
					ex.printStackTrace();
				}
				return null;

			}
		});
	}

	public List findUserTypeAll() throws Exception {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try {
					String hql = "from UserType t";
					Query query = session.createQuery(hql);
					System.out.println(query.list().size());
					return query.list();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return null;
			}
		});
	}

	public List findGroupAll() throws Exception {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				try {
					String hql = "from Group t";
					Query query = session.createQuery(hql);
					System.out.println(query.list().size());
					return query.list();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return null;

			}
		});
	}

	public Group findGroupById(final Long id) throws Exception {
		return (Group) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						try {
							Group auth = (Group) session.load(Group.class, id);
							return auth;
						} catch (HibernateException ex) {

						}
						return null;

					}
				});
	}

	public UserType findUsertypeById(final String usertypeid) throws Exception {
		return (UserType) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {
						try {
							UserType auth = (UserType) session.load(
									UserType.class, usertypeid);
							return auth;
						} catch (HibernateException ex) {

						}
						return null;

					}
				});
	}
}
