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

import com.wondersgroup.falcon.dao.archives.CaseDAO;
import com.wondersgroup.falcon.dao.archives.FaqDAO;
import com.wondersgroup.falcon.dao.archives.PolicyDAO;
import com.wondersgroup.falcon.model.archives.ArchiveLog;
import com.wondersgroup.falcon.model.archives.CaseAttr;
import com.wondersgroup.falcon.model.archives.CaseNode;
import com.wondersgroup.falcon.model.archives.FaqNode;
import com.wondersgroup.falcon.model.archives.Node;
import com.wondersgroup.falcon.model.archives.PolicyNode;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class CaseTree  extends AbstractTree{

//	private String uploadPath = "d:\\libstore\\libstore.war\\case\\"; // ���ڴ���ϴ��ļ���Ŀ¼

	private static Log log = LogFactory.getLog(CaseTree.class);
	public CaseTree() {

		super.init(new CaseDAO());
	}


	public Hashtable getPolicy(Serializable id) throws Exception {
		try {

			CaseNode node = (CaseNode)FactoryBean.creator("case").getNodeById(Node.getNodeType(Node.NODE_Case),id);
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
//			HibernateUtil.commitTransaction();
			return policy;
		} catch (Exception ex) {
//			HibernateUtil.rollbackTransaction();
			log.debug(ex);
			throw ex;
		} 
//		finally {
//		HibernateUtil.closeSession();
//		}
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

				CaseDAO dao = new CaseDAO();
				CaseNode node = dao.findById(id);
				String path = dao.getPath(node);

				return path;

			}
		});


	}

	/**
	 * ��¼��־
	 */
	public void writeLog(final Long archiveid, final Long callid,final  Long userid) throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				ArchiveLog al = new ArchiveLog();
				al.setArchiveid(archiveid);
				al.setCallid(callid);
				al.setUserid(userid);
				al.setType(ArchiveLog.CASE);
				al.setDt(new Date());

				session.save(al);

				return null ;
			}
		});


	}


	public Long addDocument(final Long parentid, final Node node) throws Exception {

		return (Long) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				CaseDAO dao = new CaseDAO();
				Long nodeid = null ;

				nodeid = dao.addDocument(parentid,(CaseNode)node);

				return nodeid;

			}
		});



	}

	public void deleteDocument(final Long[] docid) throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				CaseDAO dao = new CaseDAO();

				dao.deleteDocument(docid);
				return null ;

			}
		});



	}


	public List findPaginationData(Node node, final String chuti,int currpage, int pagesize,
			String orderby) throws Exception {

		return this.findPaginationData(node, chuti,currpage, pagesize, orderby, null);

	}
	public List findPaginationData(final Node node,final String chuti,final  int currpage, final int pagesize,
			final String orderby, final String ordertype) throws Exception {

		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				CaseDAO dao = new CaseDAO();

				List result = dao.findPaginationData((CaseNode)node,chuti,currpage, pagesize, orderby,ordertype);

				return result;

			}
		});


	}

	public int findPaginationTotalCount(final Node node, final String chuti,final int currpage, final int pagesize,
			final String orderby) throws Exception {


		return (Integer) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				CaseDAO dao = new CaseDAO();

				int result = dao.findPaginationTotalCount((CaseNode)node,chuti,currpage, pagesize, orderby);

				return result;

			}
		});


	}


	public void publishDocument(final Long[] docid) throws Exception {


		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				CaseDAO dao = new CaseDAO();

				dao.publishDocument(docid);

				dao.init();
				return null ;


			}
		});





	}


	public void setChildNodeOrdering(final Long parentid, final Long[] docid,final Boolean isDoc)
	throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				CaseDAO pdao = new CaseDAO();
				pdao.setChildNodeOrdering(parentid, docid,isDoc);
				return null ;
			}
		});


	}


	public void unPublishDocument(final Long[] docid) throws Exception {


		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				CaseDAO dao = new CaseDAO();

				dao.unPublishDocument(docid);

				//��ݿ⴦��û������ʱ���Ծ�̬ҳ����д���

				dao.init();

				return  null ;
			}
		});



	}


	public void updateDocument(final Node node) throws Exception {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				CaseDAO dao = new CaseDAO();

				dao.updateDocument( (CaseNode)node);

				return  null ;
			}
		});




	}

	public Long transferPolicy(final PolicyNode node,final String fatherNodeid)throws Exception {

		return (Long)	HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				Long cnodeid = null ;
				CaseDAO dao = new CaseDAO();
				CaseNode cnode = dao.isExistDocument(node.getName());

				CaseDAO d = new CaseDAO();

				if(cnode == null ){//ִ�����	

					CaseNode cn = new CaseNode(node.getName());
					CaseAttr ca = new CaseAttr();
					ca.setKeyword(node.getAttribute().getKeyword());
					ca.setBusinessType(node.getAttribute().getBusinessType());
					ca.setCreatetime(node.getAttribute().getCreatetime());
					ca.setCreateuser(node.getAttribute().getCreateuser());
					ca.setRemark(node.getAttribute().getRemark());
					ca.setNeirongstring(node.getAttribute().getNeirongstring());
					ca.setHtmlFile("");//δ�����ļ���Ϊ ��
					ca.setFiletext(Hibernate.createClob(" "));			
					cn.setAttribute(ca);
					//cn.getAttribute().setDicReleasestate(node.getAttribute().getDicReleasestate());
					cn.setUsertype(node.getUsertype());

					log.info("ִ�����·������");
					cnodeid = d.addDocument(new Long(fatherNodeid), cn);
//					log.info("ִ�����·������2");

//					dao.setChildNodeOrdering(fatherNodeid);

				}else {//�޸�

					cnode.getAttribute().setKeyword(node.getAttribute().getKeyword());
					cnode.getAttribute().setBusinessType(node.getAttribute().getBusinessType());
					cnode.getAttribute().setCreatetime(node.getAttribute().getCreatetime());
					cnode.getAttribute().setCreateuser(node.getAttribute().getCreateuser());
					cnode.getAttribute().setRemark(node.getAttribute().getRemark());
					cnode.getAttribute().setNeirongstring(node.getAttribute().getNeirongstring());
					cnode.getAttribute().setHtmlFile("");//δ�����ļ���Ϊ ��
					cnode.getAttribute().setFiletext(Hibernate.createClob(" "));			

					//cnode.getAttribute().setDicReleasestate(node.getAttribute().getDicReleasestate());
					log.info("ִ�����·����޸�");

					d.updateDocument(cnode);


					cnodeid = cnode.getId(); 
				}


				return cnodeid ;

			}
		});



	}

	/**
	 * 
	 * <p>Description:[����ѧϰ���Ͽ��У��������߷����ļ�����������ɾ���ϵ����] </p>
	 * 
	 * Created by [Kevin Liang] [2009-12-16]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param parentid
	 * @param number
	 * @throws Exception
	 */
	public void controlNewPolicyNumber(final String parentid,final int number)throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				CaseDAO dao = new CaseDAO();

				CaseNode node = new CaseNode();

				CaseAttr a = new CaseAttr();
				node.setAttribute(a);
				node.setParent(dao.getNodeById(Node.NODE_CASETYPE, new Long(parentid)));

				List result = dao.findPaginationData(node,null,1,100, "ordering",null);
				//log.info("�������·��棺"+result.size() +"");
				Long []docid = null;

				if(result!=null && result.size()>number){

					docid = new Long[result.size()-number];

					//log.info("======="+docid.length);
					for(int i = 0 ;i<docid.length;i++){

						docid[i] = ((CaseNode)result.get(number+i)).getId();

					}				

					dao.deleteDocument(docid);
				}

				return null ;


			}
		});


	}

	public String getCatalogPath(final Serializable id) throws Exception {

		return (String) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {


				CaseDAO dao = new CaseDAO();

				String path = dao.getCatalogPath(Node.NODE_CASETYPE,id);

				return path;

			}
		});

	}

}
