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
	 * ˢ�������������jspҳ��
	 * 
	 * @param out
	 *            �����jspҳ��
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
					
					log.info("¼�����Ͽ�����δ��ʼ����");
				}
				
				return null ;


			}
		});
		
	}

	// ��ϸ����ĵݹ�
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
	 * ����Ψһ��ʶ��ȡ�ýڵ�
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
	 * ���ø����ڵ�visible��invisible�����ڵ����ӽڵ㣬��ô�ӽڵ�Ҳ��ͬʱ������
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
	 * �õ���һ��Ľڵ����ϸ���: �������ע���ͷ�ע���������ļ���Ŀ¼ <br>
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
	 * �ڸ����ڵ��£�����һ�����ӽڵ�
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
	 * �������޸Ĺ��Ľڵ�
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
	 * ɾ���ڵ�
	 */
	public void delNode() throws Exception {
		
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
			
			VoiceDAO dao = new VoiceDAO();
			VoiceNode node = dao.findById(id);
			dao.move(node, displacement);
			return null;
			}

		});
	}
}
