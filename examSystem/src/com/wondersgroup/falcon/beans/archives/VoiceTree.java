package com.wondersgroup.falcon.beans.archives;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.wondersgroup.falcon.dao.archives.PolicyDAO;
import com.wondersgroup.falcon.dao.archives.VoiceDAO;
import com.wondersgroup.falcon.model.archives.Node;
import com.wondersgroup.falcon.model.archives.PolicyNode;
import com.wondersgroup.falcon.model.archives.VoiceNode;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class VoiceTree {

	private static Log log = LogFactory.getLog(VoiceTree.class);

	/**
	 * 刷新树，并输出到jsp页面
	 * 
	 * @param out
	 *            输出到jsp页面
	 * @throws Exception
	 */
	public void freshTree(final JspWriter out, final String treeName) throws Exception {
		
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				

				VoiceDAO dao = new VoiceDAO();
				VoiceNode root = dao.findRoot();
				if(root!=null){
				out.print(treeName + ".insertNewItem(");
				out.print("0," + root.getId() + ",\"" + root.getName() + "\",");
				out.println("0,\"drafts.gif\",\"drafts.gif\",\"drafts.gif\",\"\");");

				output(root, out, treeName);
				}else{
					
					log.info("录音资料库数据未初始化！");
				}
				
				return null ;


			}
		});
		
	}

	// 详细输出的递归
	private void output(VoiceNode parent, JspWriter out, String treeName)
			throws Exception {
		for (Iterator it = parent.getChildren().iterator(); it.hasNext();) {
			VoiceNode child = (VoiceNode) it.next();

			out.print(treeName + ".insertNewItem(");
			out.print(parent.getId() + "," + child.getId() + ",\"" + child.getName() + "\",0,");
			if (child.getVoiceFile() == null) {
				out.println("\"folder.gif\",\"folder.gif\",\"folder.gif\",\"\");");
				out.println(treeName + ".setUserData(" + child.getId() + ", \"isDir\", \"YES\");");
			} else {
				out.println("\"book.gif\",\"book.gif\",\"book.gif\",\"\");");
				out.println(treeName + ".setUserData(" + child.getId() + ", \"isDir\", \"NO\");");
			}
			
			if (!child.isVisible()) {
				out.println(treeName+".setItemColor(" + child.getId()	+ ", '#CC0000', '#99FFCC');");
				out.println(treeName+".setUserData(" + child.getId() + ", 'isVisible', 'NO');");
			} else {
				out.println(treeName+".setUserData(" + child.getId() + ", 'isVisible', 'YES');");
			}
			
			output(child, out, treeName);
		}
	}



	/**
	 * 根据唯一标识，取得节点
	 * 
	 * @param id
	 * @return VoiceNode
	 * @throws Exception
	 */
	public VoiceNode getNodeById(final Serializable id) throws Exception {
	return (VoiceNode)	HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				
				VoiceNode node = new VoiceDAO().findById(id);
				
//				Hibernate.initialize(node);
				return node;		


			}
		});
		
	}
	
	/**
	 * 设置给定节点visible或invisible；若节点有子节点，那么子节点也将同时被更新
	 * edit by cjj 08-3-31
	 */ 
	public void setVisible(final Serializable id,final boolean visible) throws Exception {
			HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				
				
				VoiceDAO dao = new VoiceDAO();
				VoiceNode node = dao.findById(id);
				//log.info("node name -----> "+node.getName());
				dao.setNodeVisible(node, visible);
				
				return null;		


			}
		});
		
		
	}
	
	/**
	 * 得到下一层的节点的详细输出: 输出包括注销和非注销的所有文件和目录 <br>
	 * add by cjj 08-3-31
	 * @throws Exception
	 */
	public List getChildren(final Serializable id) throws Exception {
		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			VoiceDAO dao = new VoiceDAO();
			VoiceNode node = dao.findById(id);
			ArrayList list = new ArrayList();
			for (Iterator it = node.getChildren().iterator(); it.hasNext();) {
				list.add((VoiceNode) it.next());
			}

			return list;
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
	public VoiceNode addNode(final Serializable pid,final  VoiceNode child)
			throws Exception {
		return (VoiceNode) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			
			VoiceDAO dao = new VoiceDAO();
			VoiceNode parent = dao.findById(pid);
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
	public void updateNode(final VoiceNode newNode) throws Exception {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
			VoiceDAO dao = new VoiceDAO();
			VoiceNode oldNode = dao.findById(newNode.getId());
			oldNode.setName(newNode.getName());
			oldNode.setVoiceFile(newNode.getVoiceFile());
			return null;
			}

		});
	}

	/**
	 * 删除节点
	 */
	public void delNode() throws Exception {
		
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
			
			VoiceDAO dao = new VoiceDAO();
			VoiceNode node = dao.findById(id);
			dao.move(node, displacement);
			return null;
			}

		});
	}
}
