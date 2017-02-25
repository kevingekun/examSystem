/**
 * 
 */
package com.wondersgroup.falcon.beans.archives;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.wondersgroup.falcon.dao.archives.NodeDAO;
import com.wondersgroup.falcon.dao.archives.PolicyDAO;
import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.archives.CaseNode;
import com.wondersgroup.falcon.model.archives.FaqNode;
import com.wondersgroup.falcon.model.archives.Node;
import com.wondersgroup.falcon.model.archives.PolicyNode;
import com.wondersgroup.falcon.model.archives.ServiceNode;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

/**
 * <p>Title:[���Ͽ�Ŀ¼�ṹ���������] </p>
 * <p>Description: [���õķ�������CLASS����,����δ�ǹ��÷���]</p> 
 *
 * Created by [Kevin Liang] [2009-9-8]
 * Midified by [�޸���] [�޸�ʱ��]
 *
 */
public abstract class AbstractTree {

	NodeDAO dao = null;

	public void init(NodeDAO dao){		
		this.dao = dao;		

	}	

	public AbstractTree() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * <p>Description:[���8Ŀ�ڵ�ʵ�����] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-24]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param nodeName
	 * @return
	 */
	public Node createNewNode(String nodeType,String nodeName){

		if(nodeType==null) return null;

		if(nodeType.equals(Node.NODE_Policy))
			return new PolicyNode(nodeName);

		else if(nodeType.equals(Node.NODE_Case))
			return new CaseNode(nodeName);

		else if(nodeType.equals(Node.NODE_Faq))
			return new FaqNode(nodeName);

		else if(nodeType.equals(Node.NODE_Service))
			return new ServiceNode(nodeName);
		else
			return null;

	}
	/**
	 * 
	 * <p>Description:[���ID��ѯĿ¼�ṹ] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-8]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param nodeid
	 * @return
	 * @throws Exception
	 */
	public Node getNodeById(final Class nodeType ,final Serializable nodeid)throws Exception{

		return (Node) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				Node node =null;


				node =  dao.getNodeById(nodeType, nodeid);


				return node;

			}
		});


	}

	/**
	 * 
	 * <p>Description:[���Ŀ¼�ṹ] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-8]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param parentid
	 * @param child
	 * @throws Exception
	 */
	public Serializable addNode(final Class nodeType ,final Serializable parentid,final Node child) 
	throws Exception{

		return (Serializable) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {


				Serializable id = null;


				id =dao.addNode(nodeType, parentid, child);

				dao.init();

				return id;


			}
		});

	}

	/**
	 * 
	 * <p>Description:[�޸�Ŀ¼�ṹ] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-8]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param node
	 * @throws Exception
	 */
	public void updateNode(final Class nodeType ,final Serializable nodeid, final String name) 
	throws Exception{

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {


				dao.updateNode(nodeType, nodeid,name);							

				dao.init();

				return null ;

			}
		});


	}

	/**
	 * 
	 * <p>Description:[ɾ��Ŀ¼�ڵ�] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-9]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param nodeid
	 * @throws Exception
	 */
	public void delNode(final Class nodeType , final Serializable nodeid) 
	throws Exception{

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {


				dao.delNode(nodeType, nodeid);


				dao.init();

				return null ;

			}
		});



	}

	/**
	 * 
	 * <p>Description:[��������Ŀ¼Visible����] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-10]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param nodeid
	 * @throws Exception
	 */
	public void reSetNodeVisible(final Class nodeType,final  Serializable nodeid) 
	throws Exception{

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				Node n = dao.getNodeById(nodeType, nodeid);

				dao.reSetNodeVisible(nodeType, n, !n.isVisible());

				dao.init();

				return null ;

			}
		});

	}



	/**
	 * 
	 * <p>Description:[��ѯ��ǰ�ڵ��µ������ĵ���Ϣ] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-16]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param parentid
	 * @return
	 * @throws Exception
	 */
	public List getChildNode(final Class nodeType,final Long parentid,final Boolean isDoc) throws Exception{

		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				List list = dao.getChildNodes(nodeType, parentid,isDoc);

				return list;

			}
		});


	}

	/**
	 * 
	 * <p>Description:[ȡ�õ�������Ϣ��ǰ�ڵ��µ����нڵ���Ϣ] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-16]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param parentid
	 * @return
	 * @throws Exception
	 */	

	public List getNavigationTreeChildNodes(Class nodeType,Long parentid) throws Exception {

		return dao.getChildNodes(nodeType,parentid);

	}
	/**
	 * 
	 * <p>Description:[ȡ����������Ϣ] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-16]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param parentid
	 * @return
	 * @throws	 Exception
	 */
	public List getNavigationTree(Class nodeType,Long parentid) throws Exception {

		return  dao.getTree(nodeType,parentid);	

	}

	//-----------------------��Ϊ���󷽷�-----------------------

	/**
	 * 
	 * <p>Description:[��������ĳ�ڵ��µ��ĵ�����] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-16]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param parentid
	 * @param docid
	 * @throws Exception
	 */
	public abstract void setChildNodeOrdering(Long parentid,Long []docid,Boolean isDoc) throws Exception;
	/**
	 * 
	 * <p>Description:[�����ĵ�����] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-15]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param docid
	 * @throws Exception
	 */

	public abstract void unPublishDocument(Long []docid) throws Exception;
	public abstract void publishDocument(Long []docid) throws Exception;

	/**
	 * 
	 * <p>Description:[ɾ���ĵ�] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-15]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param docid
	 * @throws Exception
	 */
	public abstract void deleteDocument(Long []docid) throws Exception; 
	/**
	 * 
	 * <p>Description:[��ҳ��ʾ��ݲ�ѯ] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-10]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param node
	 * @param currpage
	 * @param pagesize
	 * @param orderby
	 * @return
	 * @throws Exception
	 */
	public abstract List findPaginationData(Node node, String chuti,int currpage, int pagesize, String orderby) throws Exception;
	public abstract List findPaginationData(Node node, String chuti,int currpage, int pagesize, String orderby,String ordertype) throws Exception;

	/**
	 * 
	 * <p>Description:[��ҳ��ʾ��ݷ������ļ�¼����] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-10]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param node
	 * @param currpage
	 * @param pagesize
	 * @param orderby
	 * @return
	 * @throws Exception
	 */
	public abstract int findPaginationTotalCount(Node node, String chuti,int currpage, int pagesize, String orderby) throws Exception;


	/**
	 * 
	 * <p>Description:[�ĵ���������޸�] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-10]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param parentid
	 * @param node
	 * @return
	 * @throws Exception
	 */
	public abstract Long addDocument(Long parentid,Node node) throws Exception;
	public abstract void updateDocument(Node node) throws Exception;

	/**
	 * 
	 * <p>Description:[ȡ�õ�ǰĿ¼���ĵ���Ŀ¼�ṹ] </p>
	 * 
	 * Created by [Kevin Liang] [2009-12-8]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public abstract String getPath(Serializable id) throws Exception ;
	public abstract String getCatalogPath(Serializable id) throws Exception ;

	/**
	 * 
	 * <p>Description:[��ѯ�����] </p>
	 * 
	 * Created by [Kevin Liang] [2009-12-24]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public Node getRootCatalog(final Class nodeType,final Byte userType) throws Exception {

		return (Node) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				Node node = null ; 

				node = dao.getRootCatalog(nodeType, userType);
				return node;


			}
		});


	}
}
