package com.wondersgroup.falcon.dao.archives;

import java.io.Serializable;
import java.sql.Clob;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import oracle.sql.CLOB;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.lob.SerializableClob;

import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.archives.CaseNode;
import com.wondersgroup.falcon.model.archives.Node;
import com.wondersgroup.falcon.model.dic.DicReleasestate;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.persistence.lob.OracleLobUtil;

public class CaseDAO extends NodeDAO {

	public CaseDAO() throws InfrastructureException {
		super();
	}

	public CaseNode findById(Serializable id) throws InfrastructureException {
		return (CaseNode) super.findById(CaseNode.class, id);
	}

	public List findPaginationData(CaseNode node, String chuti,int currpage, int pagesize,
			String orderby) throws InfrastructureException {

		List result = null;
		result = this.findPaginationData(node, chuti,currpage, pagesize, orderby,
				null);
		return result;
	}

	public List findPaginationData(final CaseNode node, final String chuti,final int currpage,
			final int pagesize, final String orderby, final String ordertype)
			throws InfrastructureException {

		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				List result = null;

				Criteria c = session.createCriteria(Node.NODE_CASETYPE);
				c.createAlias("attribute", "a");
				c.createAlias("parent", "p");
				
				if(chuti!=null&&!chuti.equals("")){
	                List lst=null;
					lst=findNode();
					//System.out.print(lst.size());
					Long[] NodeIds=new Long[lst.size()];
					if(lst.size()>0){
						for(int i=0;i<lst.size();i++){
							if(lst.get(i)!=null){
							NodeIds[i]=new Long(String.valueOf(lst.get(i)));
							}
						}
						c.add(Restrictions.not(Restrictions.in("id",NodeIds )));	
					}
					
				}
				
				if (node.getParent() != null)
					c.add(Restrictions.eq("p.id", node.getParent().getId()));
				/*if (node.getAttribute().getDicReleasestate() != null)
					c.add(Restrictions.eq("a.dicReleasestate.id", new Long(node
							.getAttribute().getDicReleasestate().getId())));*/
				if (node.getName() != null)
					c.add(Property.forName("name").like(node.getName().trim(),
							MatchMode.ANYWHERE));
				if (node.getUsertype() != null)
					c.add(Restrictions.eq("usertype", node.getUsertype()));

				c
						.add(Restrictions.eq("visible", new Boolean(node
								.isVisible())));

				c.setFirstResult((currpage - 1) * pagesize);
				c.setMaxResults(pagesize);
				if (ordertype == null || ordertype.equals("asc"))
					c.addOrder(Order.asc(orderby));
				else
					c.addOrder(Order.desc(orderby));

				// System.out.print("list====>"+c.list().size());
				result = c.list();

				return result;

			}
		});

	}

	public int findPaginationTotalCount(final CaseNode node,final String chuti,
			final int currpage, final int pagesize, final String orderby) {

		return (Integer) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {

						List result = null;

						Criteria c = session.createCriteria(Node.NODE_CASETYPE);
						c.createAlias("attribute", "a");
						c.createAlias("parent", "p");
						
						if(chuti!=null&&!chuti.equals("")){
			                List lst=null;
							lst=findNode();
							//System.out.print(lst.size());
							Long[] NodeIds=new Long[lst.size()];
							if(lst.size()>0){
								for(int i=0;i<lst.size();i++){
									if(lst.get(i)!=null){
									NodeIds[i]=new Long(String.valueOf(lst.get(i)));
									}
								}
								c.add(Restrictions.not(Restrictions.in("id",NodeIds )));	
							}
							
						}

						if (node.getParent() != null)
							c.add(Restrictions.eq("p.id", node.getParent()
									.getId()));
					/*	if (node.getAttribute().getDicReleasestate() != null)
							c.add(Restrictions.eq("a.dicReleasestate.id",
									new Long(node.getAttribute()
											.getDicReleasestate().getId())));*/
						if (node.getName() != null)
							c.add(Property.forName("name").like(
									node.getName().trim(), MatchMode.ANYWHERE));
						if (node.getUsertype() != null)
							c.add(Restrictions.eq("usertype", node
									.getUsertype()));
						c.add(Restrictions.eq("visible", new Boolean(node
								.isVisible())));

						c.setProjection(Projections.projectionList().add(
								Projections.rowCount()));
						return (Integer.valueOf(c.uniqueResult().toString()).intValue());

					}
				});

	}
	
	public List findNode()
	throws InfrastructureException{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
		List lst= null;
		Query query=s.createQuery(" select e.stNodeId from EQuestions e where e.stNodeName='c'");
		lst=query.list();
		return lst;
			}
		});

	}

	public void reSetDocumentVisible(final Long nodeid, final Long[] docid) {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				// �Ȳ�ѯ��Ҫ�޸ĵ����ʵ��
				Criteria c = session.createCriteria(Node.NODE_CASETYPE);
				c.createAlias("attribute", "a");
				c.createAlias("parent", "p");
				if (nodeid != null)
					c.add(Restrictions.eq("p.id", nodeid));
				c.add(Restrictions.in("a.id", docid));

				List list = c.list();

				// ѭ���޸�
				for (Iterator it = list.iterator(); it.hasNext();) {
					CaseNode child = (CaseNode) it.next();
					child.setVisible(!child.isVisible());
					session.saveOrUpdate(child);
				}

				return null;

			}
		});

	}

	/**
	 * 
	 * <p>
	 * Description:[�жϸñ����Ƿ����]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-12-16] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param name
	 * @return
	 */
	public CaseNode isExistDocument(final String name) {

		return (CaseNode) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session session) throws Throwable {

						boolean flag = false;

						CaseNode node = null;

						Criteria c = session.createCriteria(Node.NODE_CASETYPE);
						c.createAlias("attribute", "a");
						c.createAlias("parent", "p");

						if (name != null)
							c.add(Property.forName("name").like(name.trim(),
									MatchMode.EXACT));

						List result = c.list();

						if (result != null || !result.isEmpty()) {
							if (result.size() > 0)
								node = (CaseNode) result.get(0);
						}

						return node;

					}
				});

	}

	public void deleteDocument(final Long[] docid) {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				for (int i = 0; i < docid.length; i++) {

					CaseNode node = (CaseNode) session.load(Node.NODE_CASETYPE,
							docid[i]);
					Node f = node.getParent();
					f.getChildren().remove(node);
					node.setParent(null);

					session.delete(node);
				}

				return null;

			}
		});

	}

	public void setChildNodeOrdering(final Long parentid, final Long[] docid,
			final Boolean isDoc) {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				// �Ȳ�ѯ��Ҫ�޸ĵ����ʵ��
				Criteria c = session.createCriteria(CaseNode.class);
				// c.createAlias("attribute", "a");
				c.createAlias("parent", "p");
				if (parentid != null)
					c.add(Restrictions.eq("p.id", parentid));

				if (isDoc != null)
					if (isDoc.booleanValue())
						c.add(Restrictions.isNotNull("attribute"));
					else
						c.add(Restrictions.isNull("attribute"));

				List list = c.list();

				// ѭ���޸�
				for (Iterator it = list.iterator(); it.hasNext();) {
					CaseNode child = (CaseNode) it.next();
					for (int i = 0; i < docid.length; i++) {
						if (isDoc.booleanValue())
							if (child.getAttribute().getId().intValue() == docid[i]
									.intValue()) {
								child.setOrdering(i);
								session.saveOrUpdate(child);
							}
						if (!isDoc.booleanValue())
							if (child.getId().longValue() == docid[i]
									.longValue()) {
								child.setOrdering(i);
								session.saveOrUpdate(child);
							}
					}
				}
				if (!isDoc.booleanValue())
					init();

				return null;

			}
		});

	}

	public void setChildNodeOrdering(final String parentid) {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				CaseNode parentNode = findById(new Long(parentid));
				Set set = parentNode.getChildren();
				int i = 0;
				// ѭ���޸�
				for (Iterator it = set.iterator(); it.hasNext();) {
					CaseNode child = (CaseNode) it.next();
					child.setOrdering(i);
					session.saveOrUpdate(child);
					i++;
				}

				return null;

			}
		});

	}

	public Long addDocument(final Long parentid, final CaseNode node)
			throws Exception {

		return (Long) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				CaseNode parent = (CaseNode) s.load(node.getClass(), parentid);

				Iterator it = parent.getChildren().iterator();
				while (it.hasNext()) {
					Node n = (Node) it.next();
					n.setOrdering(n.getOrdering() + 1);
				}
				node.setOrdering(0);
				node.setParent(parent);
				parent.addChild(node);
				Long nodeid = (Long) s.save(node);
				s.flush();

				s.refresh(node, LockMode.UPGRADE);
				SerializableClob sc = (SerializableClob) node.getAttribute()
						.getFiletext();
				Clob wrapped = sc.getWrappedClob();
				CLOB clob = OracleLobUtil.unwrap(wrapped);
				clob.putString(1, node.getAttribute().getNeirongstring());

				return nodeid;

			}
		});

	}

	public void updateDocument(final CaseNode node) throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				s.update(node);
				s.flush();

				s.refresh(node, LockMode.UPGRADE);
				SerializableClob sc = (SerializableClob) node.getAttribute()
						.getFiletext();
				Clob wrapped = sc.getWrappedClob();
				CLOB clob = OracleLobUtil.unwrap(wrapped);
				clob.putString(1, node.getAttribute().getNeirongstring());

				return null;

			}
		});

	}

	public void publishDocument(final Long[] docid) {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				for (int i = 0; i < docid.length; i++) {

					CaseNode node = (CaseNode) s.load(Node.NODE_CASETYPE,
							docid[i]);

					// �ļ����
					node.getAttribute().setHtmlFile(node.getId() + ".html");

					DicReleasestate dic = new DicReleasestate();
					dic.setId(DOCUMENT_STATE_PUBLISH);// ����״̬
					//node.getAttribute().setDicReleasestate(dic);

					s.update(node);
				}

				return null;

			}
		});

	}

	public void unPublishDocument(final Long[] docid) {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				for (int i = 0; i < docid.length; i++) {

					CaseNode node = (CaseNode) s.load(Node.NODE_CASETYPE,
							docid[i]);
					DicReleasestate dic = new DicReleasestate();
					dic.setId(DOCUMENT_STATE_REPEAL);// ����״̬
					//node.getAttribute().setDicReleasestate(dic);

					s.update(node);
				}

				return null;

			}
		});

	}

}
