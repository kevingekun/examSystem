package com.wondersgroup.falcon.model.rc.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.wondersgroup.falcon.model.rc.dao.RcUrgentDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseRcUrgentDAO extends com.wondersgroup.falcon.model.rc.dao._RootDAO {

	// query name references


	public static RcUrgentDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static RcUrgentDAO getInstance () {
		if (null == instance) instance = new RcUrgentDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.wondersgroup.falcon.model.rc.RcUrgent.class;
	}

    public Order getDefaultOrder () {
		return Order.asc("Name");
    }

	/**
	 * Cast the object as a com.wondersgroup.falcon.model.rc.RcUrgent
	 */
	public com.wondersgroup.falcon.model.rc.RcUrgent cast (Object object) {
		return (com.wondersgroup.falcon.model.rc.RcUrgent) object;
	}

	public com.wondersgroup.falcon.model.rc.RcUrgent get(java.lang.String key)
	{
		return (com.wondersgroup.falcon.model.rc.RcUrgent) get(getReferenceClass(), key);
	}

	public com.wondersgroup.falcon.model.rc.RcUrgent get(java.lang.String key, Session s)
	{
		return (com.wondersgroup.falcon.model.rc.RcUrgent) get(getReferenceClass(), key, s);
	}

	public com.wondersgroup.falcon.model.rc.RcUrgent load(java.lang.String key)
	{
		return (com.wondersgroup.falcon.model.rc.RcUrgent) load(getReferenceClass(), key);
	}

	public com.wondersgroup.falcon.model.rc.RcUrgent load(java.lang.String key, Session s)
	{
		return (com.wondersgroup.falcon.model.rc.RcUrgent) load(getReferenceClass(), key, s);
	}

	public com.wondersgroup.falcon.model.rc.RcUrgent loadInitialize(java.lang.String key, Session s) 
	{ 
		com.wondersgroup.falcon.model.rc.RcUrgent obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}


	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param rcUrgent a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.String save(com.wondersgroup.falcon.model.rc.RcUrgent rcUrgent)
	{
		return (java.lang.String) super.save(rcUrgent);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param rcUrgent a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.String save(com.wondersgroup.falcon.model.rc.RcUrgent rcUrgent, Session s)
	{
		return (java.lang.String) save((Object) rcUrgent, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param rcUrgent a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.wondersgroup.falcon.model.rc.RcUrgent rcUrgent)
	{
		saveOrUpdate((Object) rcUrgent);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param rcUrgent a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.wondersgroup.falcon.model.rc.RcUrgent rcUrgent, Session s)
	{
		saveOrUpdate((Object) rcUrgent, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param rcUrgent a transient instance containing updated state
	 */
	public void update(com.wondersgroup.falcon.model.rc.RcUrgent rcUrgent) 
	{
		update((Object) rcUrgent);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param rcUrgent a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.wondersgroup.falcon.model.rc.RcUrgent rcUrgent, Session s)
	{
		update((Object) rcUrgent, s);
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
	 * @param rcUrgent the instance to be removed
	 */
	public void delete(com.wondersgroup.falcon.model.rc.RcUrgent rcUrgent)
	{
		delete((Object) rcUrgent);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param rcUrgent the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.wondersgroup.falcon.model.rc.RcUrgent rcUrgent, Session s)
	{
		delete((Object) rcUrgent, s);
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
	public void refresh (com.wondersgroup.falcon.model.rc.RcUrgent rcUrgent, Session s)
	{
		refresh((Object) rcUrgent, s);
	}


}