package com.wondersgroup.falcon.beans.archives;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;

import com.wondersgroup.falcon.dao.citizeninfo.HisAttrDAO;
import com.wondersgroup.falcon.dao.citizeninfo.HisNode1DAO;
import com.wondersgroup.falcon.dao.citizeninfo.HisNodeDAO;

import com.wondersgroup.falcon.model.citizeninfo.HisAttr;
import com.wondersgroup.falcon.model.citizeninfo.HisNode;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public class HisTree extends BaseTree{

	private String uploadPath = basepath+"libstore\\policy\\"; // ���ڴ���ϴ��ļ���Ŀ¼

	private static Log log = LogFactory.getLog(HisTree.class);

	/**
	 * ˢ�������������jspҳ��
	 * 
	 * @param out
	 *            �����jspҳ��
	 * @throws Exception
	 */


	// ��ϸ����ĵݹ�
	private void output(HisNode parents, JspWriter out, String treeName,
			boolean foh) throws Exception {
		System.out.println("parent.getChildren().size()==>>>"
				+ parents.getChildren().size());

		for (Iterator its = parents.getChildren().iterator(); its.hasNext();) {
			// System.out.println("parent.getChildren().getClass()==>>>"+it.next().toString());
			HisNode child = (HisNode) its.next();
			if ((foh && child.getVisible() != 2)) {
				out.print(treeName + ".insertNewItem(");
				out.print(parents.getId() + "," + child.getId() + ",\""
						+ child.getName() + "\",0,");

				out
						.println("\"folder.gif\",\"folder.gif\",\"folder.gif\",\"\");");
				out.println(treeName + ".setUserData(" + child.getId()
						+ ", \"isDir\", \"YES\");");

				if (child.getVisible() == 0) {
					out.println(treeName + ".setItemColor(" + child.getId()
							+ ", '#ff3333', '#00FFFF');");
					out.println(treeName + ".setUserData(" + child.getId()
							+ ", 'isVisible', 'NO');");
				} else {
					out.println(treeName + ".setUserData(" + child.getId()
							+ ", 'isVisible', 'YES');");
				}

				output(child, out, treeName, foh);
				out.println(treeName + ".closeItem(" + child.getId() + ")");

			}
		}
	}

	/**
	 * ���Ŀ¼����ļ��б�
	 */


	/**
	 * ����Ψһ��ʶ��ȡ�ýڵ�
	 * 
	 * @param id
	 * @return HisNode
	 * @throws Exception
	 */


	/**
	 * �ڸ����ڵ��£�����һ�����ӽڵ�
	 * 
	 * @param pid
	 * @param child
	 * @throws Exception
	 */


	/**
	 * �������޸Ĺ��Ľڵ�
	 * 
	 * @param newNode
	 * @throws Exception
	 */


	/**
	 * ���ø����ڵ�visible��invisible�����ڵ����ӽڵ㣬��ô�ӽڵ�Ҳ��ͬʱ������


	/**
	 * ��ѯ
	 */


	/**
	 * �ƶ��ڵ�
	 * 
	 * @param id
	 *            ���ƶ��ڵ��id
	 * @param displacement
	 *            �ƶ��Ĳ�����������ʾ�����ƣ�������ʾ��
	 * @throws Exception
	 */


	/**
	 * �õ���һ��Ľڵ����ϸ���: �������ע���ͷ�ע���������ļ���Ŀ¼ <br>
	 * 
	 * @throws Exception
	 */




	/**
	 * �õ���һ��Ľڵ�ķ���ϸ���: ֻ�����ע����Ŀ¼
	 * 
	 * @throws Exception
	 */
	

	/**
	 * �õ��ڵ��·��
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */


	/**
	 * ���ȫ�����
	 */



}
