package com.wondersgroup.falcon.model.rc.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.wondersgroup.falcon.model.rc.dao.RcResearchDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseRcResearchDAO extends com.wondersgroup.falcon.model.rc.dao._RootDAO {

	// query name references


	public static RcResearchDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static RcResearchDAO getInstance () {
		if (null == instance) instance = new RcResearchDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.wondersgroup.falcon.model.rc.RcResearch.class;
	}

    public Order getDefaultOrder () {
		return Order.asc("Name");
    }

	/**
	 * Cast the object as a com.wondersgroup.falcon.model.rc.RcResearch
	 */
	public com.wondersgroup.falcon.model.rc.RcResearch cast (Object object) {
		return (com.wondersgroup.falcon.model.rc.RcResearch) object;
	}

	public com.wondersgroup.falcon.model.rc.RcResearch get(java.lang.String key)
	{
		return (com.wondersgroup.falcon.model.rc.RcResearch) get(getReferenceClass(), key);
	}

	public com.wondersgroup.falcon.model.rc.RcResearch get(java.lang.String key, Session s)
	{
		return (com.wondersgroup.falcon.model.rc.RcResearch) get(getReferenceClass(), key, s);
	}

	public com.wondersgroup.falcon.model.rc.RcResearch load(java.lang.String key)
	{
		return (com.wondersgroup.falcon.model.rc.RcResearch) load(getReferenceClass(), key);
	}

	public com.wondersgroup.falcon.model.rc.RcResearch load(java.lang.String key, Session s)
	{
		return (com.wondersgroup.falcon.model.rc.RcResearch) load(getReferenceClass(), key, s);
	}

	public com.wondersgroup.falcon.model.rc.RcResearch loadInitialize(java.lang.String key, Session s) 
	{ 
		com.wondersgroup.falcon.model.rc.RcResearch obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param rcResearch a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.String save(com.wondersgroup.falcon.model.rc.RcResearch rcResearch)
	{
		return (java.lang.String) super.save(rcResearch);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param rcResearch a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.String save(com.wondersgroup.falcon.model.rc.RcResearch rcResearch, Session s)
	{
		return (java.lang.String) save((Object) rcResearch, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param rcResearch a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.wondersgroup.falcon.model.rc.RcResearch rcResearch)
	{
		saveOrUpdate((Object) rcResearch);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param rcResearch a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.wondersgroup.falcon.model.rc.RcResearch rcResearch, Session s)
	{
		saveOrUpdate((Object) rcResearch, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param rcResearch a transient instance containing updated state
	 */
	public void update(com.wondersgroup.falcon.model.rc.RcResearch rcResearch) 
	{
		update((Object) rcResearch);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param rcResearch a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.wondersgroup.falcon.model.rc.RcResearch rcResearch, Session s)
	{
		update((Object) rcResearch, s);
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
	 * @param rcResearch the instance to be removed
	 */
	public void delete(com.wondersgroup.falcon.model.rc.RcResearch rcResearch)
	{
		delete((Object) rcResearch);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param rcResearch the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.wondersgroup.falcon.model.rc.RcResearch rcResearch, Session s)
	{
		delete((Object) rcResearch, s);
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
	public void refresh (com.wondersgroup.falcon.model.rc.RcResearch rcResearch, Session s)
	{
		refresh((Object) rcResearch, s);
	}


}