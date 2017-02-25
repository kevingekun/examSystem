package com.wondersgroup.falcon.dao.archives;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Expression;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.wondersgroup.falcon.beans.archives.CaseTree;
import com.wondersgroup.falcon.exceptions.InfrastructureException;
import com.wondersgroup.falcon.model.archives.CaseNode;
import com.wondersgroup.falcon.model.archives.FaqNode;
import com.wondersgroup.falcon.model.archives.Node;
import com.wondersgroup.falcon.model.archives.PolicyNode;
import com.wondersgroup.falcon.model.archives.ServiceNode;
import com.wondersgroup.falcon.persistence.HibernateSessionCallback;
import com.wondersgroup.falcon.persistence.HibernateUtil;

public abstract class NodeDAO {

	private static Log log = LogFactory.getLog(NodeDAO.class);	
	public static long DOCUMENT_STATE_PUBLISH=6;//����
	public static long DOCUMENT_STATE_REPEAL=5;//����


	//���е�Ŀ¼�ṹ  ���CLASS��� ����ArrayList����
	private static Map nodeItems = new HashMap();
	//ʹ�õ�������Ϣ   ���CLASS��� ����HashMap���� ��ݸ���ID���
	private static Map childNodeItems = new HashMap();
	//������ ����õĵ����� ��һ��������
	private static Map treeList= new HashMap();

	/**
	 * 
	 * <p>Description:[��ʼ����ݼ���] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-7]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 *	�˵��޸�ʱҪ���³�ʼ��
	 *
	 */	
	public void init(){
		treeList=new HashMap();
		nodeItems=new HashMap();
		childNodeItems=new HashMap();

	}

	protected NodeDAO() throws InfrastructureException {

//		init();
	}

	protected Object findRoot(final Class nodeType) throws InfrastructureException {

		return (Object) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				Object obj = null;

				Criteria crit = session.createCriteria(nodeType);
				crit.add(Restrictions.isNull("parent"));
				obj = crit.uniqueResult();

				return obj;
			}
		});



	}

	protected Object findById(final Class nodeType, final Serializable id)
	throws InfrastructureException {

		return (Object) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				Object obj = null;

				obj = session.get(nodeType, id);

				return obj;
			}
		});


	}

	public void addNode(final Node parent,final Node child) throws InfrastructureException {


		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				parent.addChild(child);
				session.saveOrUpdate(child);
				session.flush();

				return null;
			}
		});

	}



	public String getPath(Node file) throws InfrastructureException {
		try {

			StringBuffer path = new StringBuffer();
			for (Node n = file.getParent(); n != null; n = n.getParent()) {
				path.insert(0, "-->" + n.getName());
			}
			return path.substring(3);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

	public String getCatalogPath(Class nodeType,Serializable id){
		try {

			StringBuffer path = new StringBuffer();
			Node node = this.getNodeById(nodeType, id);
			if(node!=null){
				path.append("-->"+node.getName());
				//log.info(node.getName());
			}
			for (Node n = node.getParent(); n != null; n = n.getParent()) {
				path.insert(0, "-->" + n.getName());
				//log.info(n.getName());
			}
			return path.substring(3);
		} catch (HibernateException ex) {
			ex.printStackTrace();
			throw new InfrastructureException(ex);
		}

	}
	protected void move(final Class nodeType, final Node n, final int displacement)
	throws InfrastructureException {
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session s) throws Throwable {

				
				Integer begin = new Integer(n.getOrdering());
				Integer end = new Integer(begin.intValue() + displacement);
				int step = 0;

				Criteria crit = s.createCriteria(nodeType);
				crit.add(Restrictions.eq("parent", n.getParent()));
				// ���� "set ordering = ordering + 1 where ordering >= :end and
				// ordering < :begin"
				if (displacement < 0) {
					crit.add(Restrictions.ge("ordering", end)).add(
							Restrictions.lt("ordering", begin));
					step = 1;
				}
				// ���� "set ordering = ordering - 1 where ordering > :begin and
				// ordering <= :end"
				else if (displacement > 0) {
					crit.add(Restrictions.gt("ordering", begin)).add(
							Restrictions.le("ordering", end));
					step = -1;
				}
				// û���ƶ�
				else {
					return null;
				}

				// �ƶ��ֵܽڵ�
				for (java.util.Iterator it = crit.list().iterator(); it.hasNext();) {
					Node sibling = (Node) it.next();
					sibling.move(step);
					// System.out.println(sibling.getId() + "--" + sibling.getName()
					// + "--" + sibling.getOrdering());
				}

				// �ƶ��Լ�
				n.move(displacement);

				return null;
			}
		});

	}

	/**
	 * ���ø�ڵ�visible��invisible�����ڵ����ӽڵ㣬��ô�ӽڵ�Ҳ��ͬʱ������
	 */
	public void setVisible(Class nodeType, Node n, boolean visible)
	throws InfrastructureException {
		try {
			if (visible) {
				while (!n.isVisible()) {
					n.setVisible(visible);
					if (n.getParent() == null)
						break;
					n = n.getParent();
				}
			} else {
				setSelf(n, visible);
			}
		} catch (Exception ex) {
			throw new InfrastructureException(ex);
		}
	}


	// �ݹ�
	private void setSelf(Node n, boolean visible) throws Exception {
		n.setVisible(visible);
		for (Iterator it = n.getChildren().iterator(); it.hasNext();) {
			Node child = (Node) it.next();
			child.setVisible(visible);
			setSelf(child, visible);
		}
	}


	/**
	 * 
	 * <p>Description:[����������������] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-7]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType	��ֲ�ͬ��������
	 * @param parentID	�����ڵ�ID
	 * @param isShowAllchild	true   ��ʾ��ѯȫ���ڵ�  δʵ��
	 * 							false  ��ʾ����ѯĩ�ڵ�
	 * @return
	 */
	public List getTree(Class nodeType,Long parentID){
		//����

//		System.out.println("nodeType.getName():"+nodeType.getName());
//		System.out.println("nodeType.getSimpleName():"+nodeType.getSimpleName());
//		System.out.println("nodeType.getCanonicalName():"+nodeType.getCanonicalName());

		//nodeItems  �������еĽڵ�
//	    System.out.println("qqqqqqq"+nodeType.getName());
//		System.out.println("ddf"+nodeItems.get(nodeType.getName()));
	//	System.out.println("jjjjjjjjj"+((List)nodeItems.get(nodeType.getName())).size());
		System.out.println("oooo"+parentID);
		if(nodeItems.get(nodeType.getName()) == null || ((List)nodeItems.get(nodeType.getName())).size()<=0){
			loadNodes(nodeType);	
		}	

		//���ص���tree �������ļ���
		try {
			if(!treeList.containsKey(nodeType.getName())){

				treeList.put(nodeType.getName(), new ArrayList());

				this.loadTree(nodeType,parentID);

				return (List)treeList.get(nodeType.getName());

			}else{

				return (List)treeList.get(nodeType.getName());
			}
//			if(((List)treeList.get(nodeType.getName()))==null || ((List)treeList.get(nodeType.getName())).size()<=0){


//			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}

	}

	/**
	 * 
	 * <p>Description:[�ݹ���ش����ӽڵ�Ľڵ�] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-8]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param parentID
	 */
	private void loadTree(Class nodeType,Long parentID){
		Iterator child=null;
		//���parentIDȡ���ӽڵ��б�		
		child=this.getChildNodes(nodeType,parentID).iterator();

		while(child!=null && child.hasNext()){
			Node n=(Node)child.next();
			boolean isExistsChild=true;

			//�ж��Ƿ����ӽڵ�
			//isExistsChild=this.isChildItemExist(nodeType,n.getId());

			if(isExistsChild){
				//log.info("�ڵ�name - "+n.getName());

				((List)treeList.get(nodeType.getName())).add(n);

				loadTree(nodeType,n.getId());
			}
		}
	}



	/**
	 * 
	 * <p>Description:[ͨ��ڵ��ѯ�����ӽӵ���б�  ��nodeItems���Ϸ���] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-8]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param parentID
	 * @return
	 */
	public List getChildNodes(Class nodeType,Long parentID){
 //        System.out.println("uuuuuuuu"+parentID);
		List clildItems=new ArrayList();

		if (!childNodeItems.containsKey(nodeType.getName()))
			childNodeItems.put(nodeType.getName(),new HashMap());


		//�����д��ڣ���ӳ���ȡ�����
		if((Map)childNodeItems.get(nodeType.getName())!=null && ((Map)childNodeItems.get(nodeType.getName())).containsKey(parentID)){

			clildItems=(List)((Map)childNodeItems.get(nodeType.getName())).get(parentID);

			//�����в����ڣ������ݿ���ȡ�����
		}else{

			//����
			if(nodeItems.get(nodeType.getName()) == null || ((List)nodeItems.get(nodeType.getName())).size()<=0){
				loadNodes(nodeType);	
			}

			List all = (List)nodeItems.get(nodeType.getName());
			try{
				Iterator it =  all.iterator();
				while(it.hasNext()){
					Node node = (Node)it.next();
					//��ID��parentID��ͬ ���뼯��
					Node father = node.getParent();
//				  System.out.println("mmmmmm"+father.getName());
                 System.out.println("eeeeeeee"+father);
                 if(node.getName().equals("文件目录资料库")&& parentID == 0){
               	   clildItems.add(node);
                  }
	//				if(father == null && parentID == 0){						
	//					clildItems.add(node);
						//log.info("clildItems:"+node.getName());	
	//				}
				else if(father != null && parentID != 0 && !node.getName().equals("文件目录资料库")){
						if(father.getId().longValue()==parentID.longValue()){
							clildItems.add(node);
							//log.info("clildItems:"+node.getName());
						}
					}					
				}	
			}catch(Exception e){
				e.printStackTrace();
			}
			//clildItems��Ϊnull�������
			if(clildItems!=null&&!clildItems.isEmpty()&&clildItems.size()>0){				
				((Map)childNodeItems.get(nodeType.getName())).put(parentID,clildItems);
			}
		}
		return clildItems;
	}


	/**
	 * 
	 * <p>Description:[����ĳһ���͵�Ŀ¼�ṹ��Ϣ] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-7]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType CaseNode��FaqNode��PolicyNode��ServiceNode
	 */
	protected void loadNodes(final Class nodeType){

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				List list = null;

				Criteria crit = session.createCriteria(nodeType);
				crit.add(Restrictions.isNull("attribute"));
//				crit.add(Restrictions.isNotNull("parent"));
   		        crit.add(Restrictions.not(Expression.eq("id", Long.parseLong(0+""))));
//				if(visibleAll)
//				crit.add(Restrictions.eq("visible", visibleAll));
//				crit.add(Expression.eq("visible", 1));
				crit.add(Restrictions.eq("visible",true));
//				crit.addOrder(Property.forName("parent").asc());
//				crit.add(Restrictions.not(expression))
				crit.addOrder(Property.forName("ordering").asc());

				list = crit.list();
				if(list != null ){
					//������ɾ��
					if(nodeItems.containsKey(nodeType.getName()))
						nodeItems.remove(nodeType.getName());
					//���� 
					nodeItems.put(nodeType.getName(), list);
					//log.info("load���β˵�������"+nodeItems.size()+"list:"+list.size());
				}
				return null ;

			}
		});


	}



	/**
	 * 
	 * <p>Description:[����Ŀ¼�ڵ� OK] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-8]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param parentid
	 * @param child
	 * @return
	 */
	public Serializable addNode(final Class nodeType ,final  Serializable parentid,final  Node child) 
	throws InfrastructureException{


		return (Serializable) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {


				Serializable id =null;

				Node parent = (Node)session.load(nodeType,parentid);
				child.setOrdering(parent.getChildren().size());

				child.setParent(parent);
				parent.addChild(child);

				id =session.save(child);

				return id;


			}
		});



	}

	/**
	 * 
	 * <p>Description:[�޸�Ŀ¼�ڵ� OK] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-8]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param node
	 */
	public void updateNode(final Class nodeType, final Serializable nodeid, final String name) {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				Node oldnode = (Node)session.load(nodeType,nodeid);
				oldnode.setName(name);

				session.saveOrUpdate(oldnode);			

				return null ;


			}
		});


	}


	public void updateNode(final Class nodeType,final Node node) {

		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				session.saveOrUpdate(node);			

				return null ;


			}
		});



	}

	/**
	 * 
	 * <p>Description:[ȡ��Ŀ¼�ڵ� OK] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-8]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param nodeid
	 * @return
	 */
	public Node getNodeById(final Class nodeType,final Serializable nodeid) {
		return (Node) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				
				 
				Node n = (Node) session.load(nodeType, nodeid);		
				
				return n;

			}
		});
		
	}

	/**
	 * 
	 * <p>Description:[ɾ��Ŀ¼�ڵ�] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-9]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param nodeid
	 */
	public void delNode(final Class nodeType, final Serializable nodeid) {
		
		HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {
				
				Node node = (Node)session.load(nodeType, nodeid);
				session.delete(node);			
				return null ;


			}
		});
		


	} 


	/**
	 * 
	 * <p>Description:[����������������] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-25]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param n
	 * @param visible
	 * @throws InfrastructureException
	 */
	public void reSetNodeVisible(Class nodeType, Node n, boolean visible)
	throws InfrastructureException {
		try {
//			if (visible) {
//			while (!n.isVisible()) {
//			n.setVisible(visible);
//			if (n.getParent() == null)
//			break;
//			n = n.getParent();
//			}
//			} else {
			setSelf(n, visible);
//			}
		} catch (Exception ex) {
			throw new InfrastructureException(ex);
		}
	}

	/**
	 * 
	 * <p>Description:[ȡ�õ�ǰ�ڵ��µ������ĵ���Ϣ] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-16]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param parentid
	 * @return
	 */
	public List getChildNodes(final Class nodeType, final Long parentid,final Boolean isDoc) {

		return (List) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				//�Ȳ�ѯ��Ҫ�޸ĵ����ʵ��
				Criteria c = session.createCriteria(nodeType);
				//c.createAlias("attribute", "a");
				c.createAlias("parent", "p");
				if(parentid!=null)
					c.add(Restrictions.eq("p.id", parentid));

				if(isDoc !=null)
					if(isDoc.booleanValue())
						c.add(Restrictions.isNotNull("attribute"));
					else
						c.add(Restrictions.isNull("attribute"));

				c.addOrder(Order.asc("ordering"));

				return c.list();

			}
		});

	}

	/**
	 * 
	 * <p>Description:[ȡ�ø�ڵ�] </p>
	 * 
	 * Created by [Kevin Liang] [2009-12-24]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param nodeType
	 * @param userType
	 * @return
	 */
	public Node getRootCatalog(final Class nodeType,final Byte userType) {
		return (Node) HibernateUtil.doInSession(new HibernateSessionCallback() {
			public Object execute(Session session) throws Throwable {

				//�Ȳ�ѯ��Ҫ�޸ĵ����ʵ��
				Criteria c = session.createCriteria(nodeType);
                System.out.println("vvvvvvv"+userType);

//				c.add(Restrictions.isNull("parent"));
				c.add(Restrictions.or(Restrictions.or(Restrictions.eq("name", "业务知识资料库"), Restrictions.eq("name", "答复口径资料库")),  Restrictions.eq("name", "各类培训资料库")));
				c.add(Restrictions.isNull("attribute"));

//				if(userType != null && !userType.equals(""))
//					c.add(Restrictions.eq("usertype", userType));

				List list = c.list() ;
				System.out.println("yyyyyyyy"+list+"qqqqqqqq"+list.isEmpty()+"lllllll"+list.size());
				//log.info("���Ͽ��Ŀ¼����:"+list.size());
				Node n = null;
				if(list!=null && !list.isEmpty() && list.size()>0){
					n=(Node)list.get(0);
					//log.info("���Ͽ��Ŀ¼���:"+n.getName());
				}
				return n;


			}
		});

	}




}
