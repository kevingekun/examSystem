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
	 * 刷新树，并输出到jsp页面
	 * 
	 * @param out
	 *            输出到jsp页面
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
				
				log.info("传真资料库数据未初始化！");
			}
			return null;
			
			}

		});
		
	}

	// 详细输出的递归
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
	 * 根据唯一标识，取得节点
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
	 * 在给定节点下，增加一个孩子节点
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
	 * 更新已修改过的节点
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
	 * 更新上传文件字段htmlfile
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
	 * 移动节点
	 * 
	 * @param id
	 *            被移动节点的id
	 * @param displacement
	 *            移动的步长，正数表示往下移，负数表示往
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
