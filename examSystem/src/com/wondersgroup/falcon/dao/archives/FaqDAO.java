package com.wondersgroup.falcon.dao.archives;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

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
import com.wondersgroup.falcon.model.archives.FaqNode;
import com.wondersgroup.falcon.model.archives.Node;
import com.wondersgroup.falcon.model.dic.DicReleasestate;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
import com.wondersgroup.falcon.persistence.lob.OracleLobUtil;

/**
 * 
 * <p>
 * Title:[�ʴ����Ϲ���]
 * </p>
 * <p>
 * Description: [�๦������]
 * </p>
 * 
 * Created by [Kevin Liang] [2009-9-4] Midified by [�޸���] [�޸�ʱ��]
 * 
 */
public class FaqDAO extends NodeDAO {

	public FaqDAO() throws InfrastructureException {
		super();
	}

	public Long addDocument(final Long parentid, final FaqNode node)
			throws SQLException {
		return (Long) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				FaqNode parent = (FaqNode) s.load(Node.NODE_FAQTYPE, parentid);
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

	public void deleteDocument(final Long[] docid) {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				for (int i = 0; i < docid.length; i++) {

					FaqNode node = (FaqNode) s
							.load(Node.NODE_FAQTYPE, docid[i]);
					Node f = node.getParent();
					f.getChildren().remove(node);
					node.setParent(null);

					s.delete(node);
				}

				return null;

			}
		});

	}

	public List findPaginationData(FaqNode faqNode, String chuti,int currpage, int pagesize,
			String orderby) {
		return this.findPaginationData(faqNode, chuti,currpage, pagesize, orderby,
				null);
	}

	public List findPaginationData(final FaqNode faqNode, final String chuti,final int currpage,
			final int pagesize, final String orderby, final String ordertype) {

		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				List result = null;
                
				Criteria c = s.createCriteria(Node.NODE_FAQTYPE);
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
				if (faqNode.getParent() != null)
					c.add(Restrictions.eq("p.id", faqNode.getParent().getId()));

				/*if (faqNode.getAttribute().getDicReleasestate() != null)
					c.add(Restrictions.eq("a.dicReleasestate.id",
							new Long(faqNode.getAttribute()
									.getDicReleasestate().getId())));*/
				if (faqNode.getName() != null)
					c.add(Property.forName("name").like(
							faqNode.getName().trim(), MatchMode.ANYWHERE));
				if (faqNode.getUsertype() != null)
					c.add(Restrictions.eq("usertype", faqNode.getUsertype()));

				c.add(Restrictions.eq("visible", new Boolean(faqNode
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

	public int findPaginationTotalCount(final FaqNode faqNode,final String chuti,
			final int currpage, final int pagesize, final String orderby) {

		return (Integer) HibernateUtil
				.doInSession(new HibernateSessionCallback() {
					public Object execute(Session s) throws Throwable {

						List result = null;

						Criteria c = s.createCriteria(Node.NODE_FAQTYPE);
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
						if (faqNode.getParent() != null)
							c.add(Restrictions.eq("p.id", faqNode.getParent()
									.getId()));

						/*if (faqNode.getAttribute().getDicReleasestate() != null)
							c.add(Restrictions.eq("a.dicReleasestate.id",
									new Long(faqNode.getAttribute()
											.getDicReleasestate().getId())));*/
						if (faqNode.getName() != null)
							c.add(Property.forName("name").like(
									faqNode.getName().trim(),
									MatchMode.ANYWHERE));
						if (faqNode.getUsertype() != null)
							c.add(Restrictions.eq("usertype", faqNode
									.getUsertype()));
						c.add(Restrictions.eq("visible", new Boolean(faqNode
								.isVisible())));

						c.setProjection(Projections.projectionList().add(
								Projections.rowCount()));
						return (Integer.valueOf(c.uniqueResult().toString())).intValue();

					}
				});

	}
	
	public List findNode()
	throws InfrastructureException{
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {
		List lst= null;
		Query query=s.createQuery(" select e.stNodeId from EQuestions e  where e.stNodeName='f'");
		lst=query.list();
		return lst;
			}
		});

	}

	public void publishDocument(final Long[] docid) {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				for (int i = 0; i < docid.length; i++) {

					FaqNode node = (FaqNode) s
							.load(Node.NODE_FAQTYPE, docid[i]);

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

					FaqNode node = (FaqNode) s
							.load(Node.NODE_FAQTYPE, docid[i]);
					DicReleasestate dic = new DicReleasestate();
					dic.setId(DOCUMENT_STATE_REPEAL);// ����״̬
					//node.getAttribute().setDicReleasestate(dic);

					s.update(node);
				}

				return null;

			}
		});

	}

	public void setAllChildOrdering(final Long parentid, final Long[] docid,
			final Boolean isDoc) {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				// �Ȳ�ѯ��Ҫ�޸ĵ����ʵ��
				Criteria c = s.createCriteria(Node.NODE_FAQTYPE);
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
					FaqNode child = (FaqNode) it.next();
					for (int i = 0; i < docid.length; i++) {
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

	public void updateDocument(final FaqNode node) throws SQLException {

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

	public FaqNode findById(Serializable id) throws InfrastructureException {
		return (FaqNode) super.findById(FaqNode.class, id);
	}

}
