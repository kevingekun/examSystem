package com.wondersgroup.falcon.beans.archives;


import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.wondersgroup.falcon.dao.archives.CaseDAO;
import com.wondersgroup.falcon.dao.archives.ExternalAnnexDAO;
import com.wondersgroup.falcon.dao.archives.FaqDAO;
import com.wondersgroup.falcon.dao.archives.ServiceDAO;
import com.wondersgroup.falcon.model.archives.ArchiveLog;
import com.wondersgroup.falcon.model.archives.FaqNode;
import com.wondersgroup.falcon.model.archives.PolicyNode;
import com.wondersgroup.falcon.model.archives.ServiceNode;

import com.wondersgroup.falcon.model.archives.Node;

import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;
/**
 * 
 * <p>Title:[�ʴ����Ͽ����BEAN] </p>
 * <p>Description: [�๦������]</p> 
 *
 * Created by [Kevin Liang] [2009-9-27]
 * Midified by [�޸���] [�޸�ʱ��]
 *
 */ 
public class FaqTree  extends AbstractTree{

	//private String uploadPath = "d:\\libstore\\libstore.war\\faq\\"; // ���ڴ���ϴ��ļ���Ŀ¼

	private static Log log = LogFactory.getLog(FaqTree.class);

	public FaqTree() {

		super.init(new FaqDAO());
	}

	public Hashtable getPolicy(Serializable id) throws Exception {
		try {


//			FaqDAO sdao = new FaqDAO();
			FaqNode node = (FaqNode)FactoryBean.creator("faq").getNodeById(Node.getNodeType(Node.NODE_Faq),id);
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

	public String getPath(final Serializable id) throws Exception {

		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				FaqDAO dao = new FaqDAO();
				FaqNode node = dao.findById(id);
				String path = dao.getPath(node);

				return path;
			}
		});


	}

	/**
	 * ��¼��־
	 */
	public void writeLog(final Long archiveid, final Long callid,final Long userid) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				ArchiveLog al = new ArchiveLog();
				al.setArchiveid(archiveid);
				al.setCallid(callid);
				al.setUserid(userid);
				al.setType(ArchiveLog.FAQ);
				al.setDt(new Date());

				session.save(al);
				return null ;

			}
		});

	}


	public Long addDocument(final Long parentid,final Node node) throws Exception {
		return (Long)HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				FaqDAO dao = new FaqDAO();
				Long nodeid = null;

				nodeid  = dao.addDocument(parentid,(FaqNode)node);

				return nodeid;

			}
		});


	}

	public void deleteDocument(final Long[] docid) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				FaqDAO dao = new FaqDAO();

				dao.deleteDocument(docid);

				return null ;

			}
		});




	}


	/**
	 * ��ҳ��ѯ���
	 */

	public List findPaginationData(Node node,final String chuti, int currpage, int pagesize, String orderby) throws Exception {

		return this.findPaginationData(node, chuti,currpage, pagesize, orderby, null);

	}
	public List findPaginationData(final Node node,final String chuti, final int currpage, final int pagesize,
			final String orderby,final  String ordertype) throws Exception {

		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				FaqDAO dao = new FaqDAO();

				List result = dao.findPaginationData((FaqNode)node,chuti,currpage, pagesize, orderby,ordertype);

				return result;
			}
		});

	}

	/**
	 * ��ҳͳ����
	 */

	public int findPaginationTotalCount(final Node node, final String chuti,final int currpage,final  int pagesize,
			final String orderby) throws Exception {

		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				FaqDAO dao = new FaqDAO();

				int result = dao.findPaginationTotalCount((FaqNode)node,chuti,currpage, pagesize, orderby);

				return (Integer)result;
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
				FaqDAO dao = new FaqDAO();


				dao.publishDocument(docid);

				dao.init();

				return null ; 
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
				FaqDAO dao = new FaqDAO();

				dao.unPublishDocument(docid);

				//��ݿ⴦��û������ʱ���Ծ�̬ҳ����д���

				dao.init();

				return null ; 
			}
		});




	}


	public void setChildNodeOrdering(final Long parentid, final Long[] docid,final Boolean isDoc)
	throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				FaqDAO pdao = new FaqDAO();
				pdao.setAllChildOrdering(parentid, docid,isDoc);

				return null ; 
			}
		});

	}
	public void updateDocument(final Node node) throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				FaqDAO dao = new FaqDAO();

				dao.updateDocument( (FaqNode)node);

				return null ; 
			}
		});


	}

	public String getCatalogPath(final Serializable id) throws Exception {

		return (String )HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				FaqDAO dao = new FaqDAO();

				String path = dao.getCatalogPath(Node.NODE_FAQTYPE,id);

				return path;
			}
		});

	}



}
