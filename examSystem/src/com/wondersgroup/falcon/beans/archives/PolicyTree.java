package com.wondersgroup.falcon.beans.archives;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspWriter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.wondersgroup.falcon.dao.archives.FaqDAO;
import com.wondersgroup.falcon.dao.archives.PolicyDAO;
import com.wondersgroup.falcon.dao.archives.ServiceDAO;
import com.wondersgroup.falcon.model.archives.ArchiveLog;
import com.wondersgroup.falcon.model.archives.FaqNode;
import com.wondersgroup.falcon.model.archives.Node;
import com.wondersgroup.falcon.model.archives.PolicyNode;
import com.wondersgroup.falcon.model.archives.PolicyAttr;
import com.wondersgroup.falcon.model.archives.ServiceNode;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class PolicyTree extends AbstractTree{

	//�� config.properties  ʹ�ù�����PropertiesUtil���н����4

	//private String uploadPath = "d:\\libstore\\libstore.war\\policy\\"; // ���ڴ���ϴ��ļ���Ŀ¼

	private static Log log = LogFactory.getLog(PolicyTree.class);

	public PolicyTree() {

		super.init(new PolicyDAO());

	}


	/**
	 * �õ���һ��Ľڵ����ϸ���: ����(ע��ͷ�ע��������ļ���Ŀ¼ <br>
	 * 
	 * @throws Exception
	 */
	public List getChildren(final Serializable id) throws Exception {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PolicyDAO dao = new PolicyDAO();
				PolicyNode node = dao.findById(id);

				ArrayList list = new ArrayList();
				for (Iterator it = node.getChildren().iterator(); it.hasNext();) {
					list.add((PolicyNode) it.next());
				}


				return list;

			}
		});


	}


	public Hashtable getPolicy(final Serializable id) throws Exception {
		return (Hashtable) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				PolicyDAO sdao = new PolicyDAO();
				PolicyNode node = (PolicyNode)FactoryBean.creator("policy").getNodeById(Node.getNodeType(Node.NODE_Policy),id);
				//ServiceNode node = sdao.findById(ServiceNode.classid);
				String str = node.getAttribute().getPolicy();
				Hashtable policy = new Hashtable();

				if (str == null || str.equals("")) {
					policy = null;
				} else {
					StringTokenizer st = new StringTokenizer(str, ",");
					//PolicyDAO pdao = new PolicyDAO();
					PolicyNode pn = null;
					while (st.hasMoreTokens()) {
						pn = (PolicyNode)FactoryBean.creator("policy").getNodeById(Node.getNodeType(Node.NODE_Policy),new Long(st.nextToken()));
						if(pn!=null&&pn.getAttribute().getEffective().intValue()!=0)
							policy.put(pn.getAttribute().getHtmlFile(), pn.getName());
					}
				}

				return policy;				

			}
		});


	}


	/**
	 * �õ��ڵ��·��
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getPath(final Serializable id) throws Exception {

		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				PolicyDAO dao = new PolicyDAO();
				PolicyNode node = dao.findById(id);
				String path = dao.getPath(node);

				return path;		

			}
		});

	}
	public String getCatalogPath(final Serializable id) throws Exception {
		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				PolicyDAO dao = new PolicyDAO();

				String path = dao.getCatalogPath(Node.NODE_POLICYTYPE,id);


				return path;	

			}
		});


	}

	/**
	 * ��¼��־
	 */
	public void writeLog(final Long archiveid,final Long callid,final Long userid) throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				ArchiveLog al = new ArchiveLog();
				al.setArchiveid(archiveid);
				al.setCallid(callid);
				
				al.setType(ArchiveLog.POLICY);
				al.setDt(new Date());				

				session.save(al);

				return null ;

			}
		});

	}




	/**
	 * ��ҳ��ѯ���
	 */

	public List findPaginationData(Node node, String chuti,int currpage, int pagesize, String orderby) throws Exception {


		return this.findPaginationData(node, chuti,currpage, pagesize, orderby, null);
	}
	public List findPaginationData(final Node node, final String chuti,final int currpage, final int pagesize,
			final String orderby, final String ordertype) throws Exception {

		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PolicyDAO dao = new PolicyDAO();

				List result = dao.findPaginationData((PolicyNode)node,chuti,currpage, pagesize, orderby,ordertype);
				System.out.println("-------->"+result.size());
				return result;

			}
		});


	}


	/**
	 * ��ҳͳ����
	 */

	public int findPaginationTotalCount(final Node node, final String chuti,final int currpage, final int pagesize,
			final String orderby) throws Exception {

		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PolicyDAO dao = new PolicyDAO();

				int result = dao.findPaginationTotalCount((PolicyNode)node,chuti,currpage, pagesize, orderby);

				return (Integer)result;

			}
		});

	}


	public void deleteDocument(final Long[] docid) throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PolicyDAO dao = new PolicyDAO();

				dao.deleteDocument(docid);
				return null ; 

			}
		});



	}

	/**
	 * �ĵ�����
	 */

	public void publishDocument(final Long[] docid)
	throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				PolicyDAO dao = new PolicyDAO();

				dao.publishDocument(docid);

				dao.init();

				return  null ; 
			}
		});

	}
	/**
	 * �ĵ���������
	 */

	public void unPublishDocument(final Long[] docid)
	throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				PolicyDAO dao = new PolicyDAO();
				dao.unPublishDocument(docid);


				//��ݿ⴦��û������ʱ���Ծ�̬ҳ����д���

				dao.init();

				return null; 

			}
		});

	}

	public void setChildNodeOrdering(final Long parentid,final Long[] docid,final Boolean isDoc)
	throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				PolicyDAO pdao = new PolicyDAO();
				pdao.setChildNodeOrdering(parentid, docid,isDoc);


				return null; 

			}
		});


	}



	public Long addDocument(final Long parentid,final  Node node) throws Exception {

		return (Long) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PolicyDAO dao = new PolicyDAO();
				Long nodeid = null;

				nodeid = dao.addDocument(parentid,(PolicyNode)node);

				return nodeid;

			}
		});

	}


	public void updateDocument(final Node node) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				PolicyDAO dao = new PolicyDAO();


				dao.updateDocument( (PolicyNode)node);

				return null ; 

			}
		});

	}

}
