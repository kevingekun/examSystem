package com.wondersgroup.falcon.model.rc.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.wondersgroup.falcon.model.rc.dao.RcCategoryDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseRcCategoryDAO extends com.wondersgroup.falcon.model.rc.dao._RootDAO {

	// query name references


	public static RcCategoryDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static RcCategoryDAO getInstance () {
		if (null == instance) instance = new RcCategoryDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.wondersgroup.falcon.model.rc.RcCategory.class;
	}

    public Order getDefaultOrder () {
		return Order.asc("Name");
    }

	/**
	 * Cast the object as a com.wondersgroup.falcon.model.rc.RcCategory
	 */
	public com.wondersgroup.falcon.model.rc.RcCategory cast (Object object) {
		return (com.wondersgroup.falcon.model.rc.RcCategory) object;
	}

	public com.wondersgroup.falcon.model.rc.RcCategory get(java.lang.String key)
	{
		return (com.wondersgroup.falcon.model.rc.RcCategory) get(getReferenceClass(), key);
	}

	public com.wondersgroup.falcon.model.rc.RcCategory get(java.lang.String key, Session s)
	{
		return (com.wondersgroup.falcon.model.rc.RcCategory) get(getReferenceClass(), key, s);
	}

	public com.wondersgroup.falcon.model.rc.RcCategory load(java.lang.String key)
	{
		return (com.wondersgroup.falcon.model.rc.RcCategory) load(getReferenceClass(), key);
	}

	public com.wondersgroup.falcon.model.rc.RcCategory load(java.lang.String key, Session s)
	{
		return (com.wondersgroup.falcon.model.rc.RcCategory) load(getReferenceClass(), key, s);
	}

	public com.wondersgroup.falcon.model.rc.RcCategory loadInitialize(java.lang.String key, Session s) 
	{ 
		com.wondersgroup.falcon.model.rc.RcCategory obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}


	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param rcCategory a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.String save(com.wondersgroup.falcon.model.rc.RcCategory rcCategory)
	{
		return (java.lang.String) super.save(rcCategory);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param rcCategory a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.String save(com.wondersgroup.falcon.model.rc.RcCategory rcCategory, Session s)
	{
		return (java.lang.String) save((Object) rcCategory, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param rcCategory a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.wondersgroup.falcon.model.rc.RcCategory rcCategory)
	{
		saveOrUpdate((Object) rcCategory);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param rcCategory a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.wondersgroup.falcon.model.rc.RcCategory rcCategory, Session s)
	{
		saveOrUpdate((Object) rcCategory, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param rcCategory a transient instance containing updated state
	 */
	public void update(com.wondersgroup.falcon.model.rc.RcCategory rcCategory) 
	{
		update((Object) rcCategory);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param rcCategory a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.wondersgroup.falcon.model.rc.RcCategory rcCategory, Session s)
	{
		update((Object) rcCategory, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(java.lang.String id)
	{
		delete((Object) load(id));
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param id the instance ID to be removed
	 * @param s the Session
	 */
	public void delete(java.lang.String id, Session s)
	{
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param rcCategory the instance to be removed
	 */
	public void delete(com.wondersgroup.falcon.model.rc.RcCategory rcCategory)
	{
		delete((Object) rcCategory);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param rcCategory the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.wondersgroup.falcon.model.rc.RcCategory rcCategory, Session s)
	{
		delete((Object) rcCategory, s);
	}
	
	/**
	 * Re-read the state of the given instance from the underlying database. It is inadvisable to use this to implement
	 * long-running sessions that span many business tasks. This method is, however, useful in certain special circumstances.
	 * For example 
	 * <ul> 
	 * <li>where a database trigger alters the object state upon insert or update</li>
	 * <li>after executing direct SQL (eg. a mass update) in the same session</li>
	 * <li>after inserting a Blob or Clob</li>
	 * </ul>
	 */
	public void refresh (com.wondersgroup.falcon.model.rc.RcCategory rcCategory, Session s)
	{
		refresh((Object) rcCategory, s);
	}


}