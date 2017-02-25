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

	private String uploadPath = basepath+"libstore\\policy\\"; // 用于存放上传文件的目录

	private static Log log = LogFactory.getLog(HisTree.class);

	/**
	 * 刷新树，并输出到jsp页面
	 * 
	 * @param out
	 *            输出到jsp页面
	 * @throws Exception
	 */


	// 详细输出的递归
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
	 * 点击目录输出文件列表
	 */


	/**
	 * 根据唯一标识，取得节点
	 * 
	 * @param id
	 * @return HisNode
	 * @throws Exception
	 */


	/**
	 * 在给定节点下，增加一个孩子节点
	 * 
	 * @param pid
	 * @param child
	 * @throws Exception
	 */


	/**
	 * 更新已修改过的节点
	 * 
	 * @param newNode
	 * @throws Exception
	 */


	/**
	 * 设置给定节点visible或invisible；若节点有子节点，那么子节点也将同时被更新


	/**
	 * 查询
	 */


	/**
	 * 移动节点
	 * 
	 * @param id
	 *            被移动节点的id
	 * @param displacement
	 *            移动的步长，正数表示往下移，负数表示往
	 * @throws Exception
	 */


	/**
	 * 得到下一层的节点的详细输出: 输出包括注销和非注销的所有文件和目录 <br>
	 * 
	 * @throws Exception
	 */




	/**
	 * 得到下一层的节点的非详细输出: 只输出非注销的目录
	 * 
	 * @throws Exception
	 */
	

	/**
	 * 得到节点的路径
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */


	/**
	 * 获得全部结点
	 */



}
