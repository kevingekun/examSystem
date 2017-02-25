package com.wondersgroup.falcon.beans.archives;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
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
import com.wondersgroup.falcon.model.archives.Node;
import com.wondersgroup.falcon.model.archives.PolicyNode;
import com.wondersgroup.falcon.model.archives.ServiceNode;
import com.wondersgroup.falcon.model.archives.ServiceAttr;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
/**
 * 
 * <p>Title:[����ָ�����Ͽ����BEAN] </p>
 * <p>Description: [�๦������]</p> 
 *
 * Created by [Kevin Liang] [2009-9-27]
 * Midified by [�޸���] [�޸�ʱ��]
 *
 */ 
public class ServiceTree  extends AbstractTree{

	//private String uploadPath = "d:\\libstore\\libstore.war\\service\\"; // ���ڴ���ϴ��ļ���Ŀ¼

	private static Log log = LogFactory.getLog(ServiceTree.class);

	public ServiceTree(){

		super.init(new ServiceDAO());
	}



	public Hashtable getPolicy(Serializable id) throws Exception {
		try {

			ServiceNode node = (ServiceNode)FactoryBean.creator("service").getNodeById(Node.getNodeType(Node.NODE_Service),id);
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
		} catch (Exception ex) {
			log.debug(ex);
			throw ex;
		}
	}


//	/**
//	* �õ��ڵ��·��
//	* 
//	* @param id
//	* @return
//	* @throws Exception
//	*/
	public String getPath(final Serializable id) throws Exception {

		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				ServiceDAO dao = new ServiceDAO();
				ServiceNode node = dao.findById(id);
				String path = dao.getPath(node);

				return path;

			}
		});


	}

	/**
	 * ��¼��־
	 */
	public void writeLog(final Long archiveid,final  Long callid,final  Long userid) throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				ArchiveLog al = new ArchiveLog();
				al.setArchiveid(archiveid);
				al.setCallid(callid);
				al.setUserid(userid);
				al.setType(ArchiveLog.SERVICE);
				al.setDt(new Date());

				session.save(al);
				return null  ; 
			}
		});

	}


	public Long addDocument(final Long parentid,final  Node node) throws Exception {

		return (Long) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				ServiceDAO dao = new ServiceDAO();
				Long nodeid = null ;

				nodeid = dao.addDocument(parentid,(ServiceNode)node);


				return nodeid ; 
			}
		});



	}


	public void deleteDocument(final Long[] docid) throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				ServiceDAO dao = new ServiceDAO();

				dao.deleteDocument(docid);
				return null ; 
			}
		});

	}


	public List findPaginationData(Node node, String chuti,int currpage, int pagesize,
			String orderby) throws Exception {
		return this.findPaginationData(node, chuti,currpage, pagesize, orderby, null);
	}
	public List findPaginationData(final Node node,final String chuti,final  int currpage, final int pagesize,
			final String orderby, final String ordertype) throws Exception {

		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				ServiceDAO dao = new ServiceDAO();

				List result = dao.findPaginationData((ServiceNode)node,chuti,currpage, pagesize, orderby,ordertype);

				return result;
			}
		});


	}

	public int findPaginationTotalCount(final Node node,final String chuti, final int currpage, final int pagesize,
			final String orderby) throws Exception {

		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				ServiceDAO dao = new ServiceDAO();

				int result = dao.findPaginationTotalCount((ServiceNode)node,chuti,currpage, pagesize, orderby);


				return (Integer)result;
			}
		});

	}


	public void publishDocument(final Long[] docid) throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				ServiceDAO dao = new ServiceDAO();


				dao.publishDocument(docid);

				dao.init();

				return null ; 
			}
		});

	}


	public void setChildNodeOrdering(final Long parentid,final  Long[] docid,final Boolean isDoc)
	throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				ServiceDAO pdao = new ServiceDAO();
				pdao.setChildNodeOrdering(parentid, docid,isDoc);

				return null ; 
			}
		});



	}


	public void unPublishDocument(final Long[] docid) throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				ServiceDAO dao = new ServiceDAO();
				dao.unPublishDocument(docid);


				//��ݿ⴦��û������ʱ���Ծ�̬ҳ����д���

				dao.init();

				return null ; 
			}
		});



	}


	public void updateDocument(final Node node) throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				ServiceDAO dao = new ServiceDAO();

				dao.updateDocument( (ServiceNode)node);
				return null ; 
			}
		});


	}

	public String getCatalogPath(final Serializable id) throws Exception {

		return (String)	HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				ServiceDAO dao = new ServiceDAO();

				String path = dao.getCatalogPath(Node.NODE_SERVICETYPE,id);

				return path;
			}
		});

	}


}
