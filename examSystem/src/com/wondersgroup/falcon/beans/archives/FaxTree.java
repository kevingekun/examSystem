package com.wondersgroup.falcon.beans.archives;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.wondersgroup.falcon.dao.archives.FaxDAO;
import com.wondersgroup.falcon.model.archives.FaxNode;
import com.wondersgroup.falcon.model.zhijian.Dxlyfl;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class FaxTree {

	private static Log log = LogFactory.getLog(FaxTree.class);

	/**
	 * ˢ�������������jspҳ��
	 * 
	 * @param out
	 *            �����jspҳ��
	 * @throws Exception
	 */
	public void freshTree(final JspWriter out,final  String treeName) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			
			FaxDAO dao = new FaxDAO();
			FaxNode root = dao.findRoot();
			if(root!=null){
				out.print(treeName + ".insertNewItem(");
				out.print("0," + root.getId() + ",\"" + root.getName() + "\",");
				out.println("0,\"drafts.gif\",\"drafts.gif\",\"drafts.gif\",\"\");");

				output(root, out, treeName);
			}else{
				
				log.info("�������Ͽ�����δ��ʼ����");
			}
			return null;
			
			}

		});
		
	}

	// ��ϸ����ĵݹ�
	private void output(FaxNode parent, JspWriter out, String treeName)
	throws Exception {
		for (Iterator it = parent.getChildren().iterator(); it.hasNext();) {
			FaxNode child = (FaxNode) it.next();

			out.print(treeName + ".insertNewItem(");
			out.print(parent.getId() + "," + child.getId() + ",\""
					+ child.getName() + "\",0,");
			if (child.getVoiceFile() == null) {
				out.println("\"folder.gif\",\"folder.gif\",\"folder.gif\",\"\");");
				out.println(treeName + ".setUserData(" + child.getId()
						+ ", \"isDir\", \"YES\");");
			} else {
				out.println("\"book.gif\",\"book.gif\",\"book.gif\",\"\");");
				out.println(treeName + ".setUserData(" + child.getId()
						+ ", \"isDir\", \"NO\");");
			}
			output(child, out, treeName);
		}
	}



	/**
	 * ����Ψһ��ʶ��ȡ�ýڵ�
	 * 
	 * @param id
	 * @return FaxNode
	 * @throws Exception
	 */
	public FaxNode getNodeById(final Serializable id) throws Exception {
		return (FaxNode)	HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			FaxNode node = new FaxDAO().findById(id);
			Hibernate.initialize(node);
			return node;
			}

		});
	}

	/**
	 * �ڸ����ڵ��£�����һ�����ӽڵ�
	 * 
	 * @param pid
	 * @param child
	 * @throws Exception
	 */
	public FaxNode addNode(final Serializable pid, final FaxNode child)
	throws Exception {
		return (FaxNode)	HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			FaxDAO dao = new FaxDAO();
			FaxNode parent = dao.findById(pid);
			child.setOrdering(parent.getChildren().size());
			dao.addNode(parent, child);

			return child;
			}

		});
	}

	/**
	 * �������޸Ĺ��Ľڵ�
	 * 
	 * @param newNode
	 * @throws Exception
	 */
	public void updateNode(final FaxNode newNode) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			FaxDAO dao = new FaxDAO();
			FaxNode oldNode = dao.findById(newNode.getId());
			oldNode.setName(newNode.getName());
			oldNode.setVoiceFile(newNode.getVoiceFile());
			oldNode.setFaxFile(newNode.getFaxFile());
			oldNode.setFaxid(newNode.getFaxid());
			return null;
			}

		});
	}


	/**
	 * �����ϴ��ļ��ֶ�htmlfile
	 * 
	 * @param newNode
	 * @throws Exception
	 */
	public void updateHtmlfile(final FaxNode newNode) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			FaxDAO dao = new FaxDAO();
			FaxNode oldNode = dao.findById(newNode.getId());
			oldNode.setName(newNode.getName());
			oldNode.setVoiceFile(newNode.getVoiceFile());
			oldNode.setFaxFile(newNode.getFaxFile());
			oldNode.setFaxid(newNode.getFaxid());
			oldNode.setHtmlfile(newNode.getHtmlfile());
			return null;
			}

		});
	}

	/**
	 * �ƶ��ڵ�
	 * 
	 * @param id
	 *            ���ƶ��ڵ��id
	 * @param displacement
	 *            �ƶ��Ĳ�����������ʾ�����ƣ�������ʾ��
	 * @throws Exception
	 */
	public void move(final Serializable id,final  int displacement) throws Exception {
		
		if (displacement == 0)
			return;
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			FaxDAO dao = new FaxDAO();
			FaxNode node = dao.findById(id);
			dao.move(node, displacement);
			return null;
			}

		});
	}
}
