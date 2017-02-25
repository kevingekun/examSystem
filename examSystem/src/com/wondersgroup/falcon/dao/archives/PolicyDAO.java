package com.wondersgroup.falcon.dao.archives;

import java.io.Serializable;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oracle.sql.CLOB;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.lob.SerializableClob;

import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.archives.Node;
import com.wondersgroup.falcon.model.archives.PolicyNode;
import com.wondersgroup.falcon.model.dic.DicReleasestate;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.persistence.lob.OracleLobUtil;

public class PolicyDAO extends NodeDAO {

	public PolicyDAO() throws InfrastructureException {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.wondersgroup.falcon.dao.archives.a#findById(java.io.Serializable)
	 */
	public PolicyNode findById(Serializable id) throws InfrastructureException {
		return (PolicyNode) super.findById(PolicyNode.class, id);
	}

	/**
	 * 
	 * <p>
	 * Description:[��ѯһҳ���]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-15] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param node
	 * @param currpage
	 * @param pagesize
	 * @param orderby
	 * @return
	 * @throws InfrastructureException
	 */
	public List findPaginationData(PolicyNode node, String chuti, int currpage,
			int pagesize, String orderby) throws InfrastructureException {

		return this.findPaginationData(node, chuti, currpage, pagesize,
				orderby, null);
	}

	public List findPaginationData(final PolicyNode node, final String chuti,
			final int currpage, final int pagesize, final String orderby,
			final String ordertype) throws InfrastructureException {

		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				List result = null;
				System.out.println("-----------------------");
				Criteria c = s.createCriteria(Node.NODE_POLICYTYPE);
				c.createAlias("attribute", "a");
				c.createAlias("parent", "p");

				if (chuti != null && !chuti.equals("")) {
					List lst = null;
					lst = findNode();
					System.out.print(lst.size());
					Long[] NodeIds = new Long[lst.size()];
					if (lst.size() > 0) {
						for (int i = 0; i < lst.size(); i++) {
							if (lst.get(i) != null) {
								NodeIds[i] = new Long(String
										.valueOf(lst.get(i)));
							}
						}
					}
					c.add(Restrictions.not(Restrictions.in("id", NodeIds)));

				}

				if (node.getParent() != null)
					c.add(Restrictions.eq("p.id", node.getParent().getId()));
				if (node.getAttribute().getEffective() != null)
					c.add(Restrictions.eq("a.effective", node.getAttribute()
							.getEffective()));
				if(node.getAttribute().getYear()!=null){
					c.add(Restrictions.eq("a.year", node.getAttribute().getYear()));
				}
				if (node.getAttribute().getFileno() != null)
					c.add(Property.forName("a.fileno").like(
							node.getAttribute().getFileno().trim(),
							MatchMode.ANYWHERE));
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

				result = c.list();

				return result;

			}
		});

	}

	public List findNode() throws InfrastructureException {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				List lst = null;
				Query query = s
						.createQuery(" select e.stNodeId from EQuestions e  where e.stNodeName='P'");
				lst = query.list();
				return lst;
			}
		});

	}

	/**
	 * 
	 * <p>
	 * Description:[��ݲ�ѯ���ȡ�÷������Ľ������]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-15] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param node
	 * @param currpage
	 * @param pagesize
	 * @param orderby
	 * @return
	 */
	public int findPaginationTotalCount(final PolicyNode node,
			final String chuti, final int currpage, final int pagesize,
			final String orderby) {

		return (Integer) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session s) throws Throwable {
						List result = null;

						Criteria c = s.createCriteria(Node.NODE_POLICYTYPE);
						c.createAlias("attribute", "a");
						c.createAlias("parent", "p");

						if (chuti != null && !chuti.equals("")) {
							List lst = null;
							lst = findNode();
							// System.out.print(lst.size());
							Long[] NodeIds = new Long[lst.size()];
							if (lst.size() > 0) {
								for (int i = 0; i < lst.size(); i++) {
									if (lst.get(i) != null) {
										NodeIds[i] = new Long(String
												.valueOf(lst.get(i)));
									}
								}
							}
							c.add(Restrictions.not(Restrictions.in("id",
									NodeIds)));
						}

						if (node.getParent() != null)
							c.add(Restrictions.eq("p.id", node.getParent()
									.getId()));
						if (node.getAttribute().getEffective() != null)
							c.add(Restrictions.eq("a.effective", node
									.getAttribute().getEffective()));
						if (node.getAttribute().getFileno() != null)
							c.add(Property.forName("a.fileno").like(
									node.getAttribute().getFileno().trim(),
									MatchMode.ANYWHERE));
						if(node.getAttribute().getYear()!=null){
							c.add(Restrictions.eq("a.year", node.getAttribute().getYear()));
						}
						/*if (node.getAttribute().getDicReleasestate() != null)
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
						return (Integer.valueOf(c.uniqueResult().toString())).intValue();
					}
				});

	}

	/**
	 * 
	 * <p>
	 * Description:[�����ĵ��Ƿ�ɼ����� ]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-15] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param nodeType
	 * @param nodeid
	 *            null ȫ����Χ���о����IDʱ��ֻ�޸ĵ�ǰ�ڵ㷶Χ�ڵ�
	 * @param docid
	 *            �ĵ�ID����
	 */
	public void reSetDocumentVisible(final Long nodeid, final Long[] docid) {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				// �Ȳ�ѯ��Ҫ�޸ĵ����ʵ��
				Criteria c = s.createCriteria(Node.NODE_POLICYTYPE);
				c.createAlias("attribute", "a");
				c.createAlias("parent", "p");
				if (nodeid != null)
					c.add(Restrictions.eq("p.id", nodeid));
				c.add(Restrictions.in("a.id", docid));

				List list = c.list();

				// ѭ���޸�
				for (Iterator it = list.iterator(); it.hasNext();) {
					PolicyNode child = (PolicyNode) it.next();
					child.setVisible(!child.isVisible());
					s.saveOrUpdate(child);
				}

				return null;
			}
		});

	}

	/**
	 * 
	 * <p>
	 * Description:[ɾ��ѡ����ĵ���Ϣ��ͬʱҪ�����ѷ�����̬�ļ���ɾ��]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-15] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param docid
	 */
	public void deleteDocument(final Long[] docid) {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
				for (int i = 0; i < docid.length; i++) {

					PolicyNode node = (PolicyNode) s.load(Node.NODE_POLICYTYPE,
							docid[i]);
					Node f = node.getParent();
					f.getChildren().remove(node);
					node.setParent(null);

					s.delete(node);
				}
				return null;
			}
		});

	}

	/**
	 * 
	 * <p>
	 * Description:[����ĳ�ڵ��µ��ĵ���������]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-16] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param parentid
	 * @param docid
	 */
	public void setChildNodeOrdering(final Long parentid, final Long[] docid,
			final Boolean isDoc) {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				// �Ȳ�ѯ��Ҫ�޸ĵ����ʵ��
				Criteria c = s.createCriteria(PolicyNode.class);
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
					PolicyNode child = (PolicyNode) it.next();

					for (int i = 0; i < docid.length; i++) {
						// System.out.println("docid :"+docid[i]);
						if (isDoc.booleanValue())
							if (child.getAttribute().getId().intValue() == docid[i]
									.intValue()) {
								child.setOrdering(i);
								s.saveOrUpdate(child);
							}
						if (!isDoc.booleanValue())
							if (child.getId().longValue() == docid[i]
									.longValue()) {
								child.setOrdering(i);
								s.saveOrUpdate(child);
							}
					}
				}
				if (!isDoc.booleanValue())
					init();

				return null;
			}
		});

	}

	/**
	 * 
	 * <p>
	 * Description:[����������������]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-25] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param parentid
	 * @param node
	 * @throws Exception
	 */
	public Long addDocument(final Long parentid, final PolicyNode node)
			throws Exception {
		return (Long) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				PolicyNode parent = (PolicyNode) s.load(node.getClass(),
						parentid);
				Iterator it = parent.getChildren().iterator();
				while (it.hasNext()) {
					Node n = (Node) it.next();
					n.setOrdering(n.getOrdering() + 1);
				}
				node.setOrdering(0);
				node.setParent(parent);
				parent.addChild(node);
				Long id = (Long) s.save(node);
				s.flush();

				s.refresh(node, LockMode.UPGRADE);
				SerializableClob sc = (SerializableClob) node.getAttribute()
						.getFiletext();
				Clob wrapped = sc.getWrappedClob();
				CLOB clob = OracleLobUtil.unwrap(wrapped);
				clob.putString(1, node.getAttribute().getNeirongstring());
				return id;

			}
		});

	}

	/**
	 * 
	 * <p>
	 * Description:[����������������]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-25] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param node
	 * @throws Exception
	 */
	public void updateDocument(final PolicyNode node) throws Exception {

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

	/**
	 * 
	 * <p>
	 * Description:[����������������]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-25] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param docid
	 */
	public void publishDocument(final Long[] docid) {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				for (int i = 0; i < docid.length; i++) {

					PolicyNode node = (PolicyNode) s.load(Node.NODE_POLICYTYPE,
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

	/**
	 * 
	 * <p>
	 * Description:[����������������]
	 * </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-25] Midified by [�޸���] [�޸�ʱ��]
	 * 
	 * @param docid
	 */
	public void unPublishDocument(final Long[] docid) {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				for (int i = 0; i < docid.length; i++) {

					PolicyNode node = (PolicyNode) s.load(Node.NODE_POLICYTYPE,
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
