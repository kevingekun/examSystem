package com.wondersgroup.falcon.model.archives;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Stephen.Cheng
 */
public abstract class Node {

	private Long id;

	private Node parent;

	private SortedSet children = new TreeSet();

	private String name;//�ļ���
	
	private Byte usertype;//�û�����
	
	private boolean visible;//�Ƿ�ɼ� �Ƿ�ע��

	private int ordering; //����

	
	//liangkd
	public static Class  NODE_POLICYTYPE = PolicyNode.class;
	public static Class  NODE_FAQTYPE = FaqNode.class;
	public static Class  NODE_SERVICETYPE = ServiceNode.class;
	public static Class  NODE_CASETYPE = CaseNode.class;
	
	public static String  NODE_Policy = "policy";//���߷���
	public static String  NODE_Faq = "faq";//�ʴ�����
	public static String  NODE_Service = "service";//����ָ��
	public static String  NODE_Case = "case";//ѧϰ����
	
	/**
	 * 
	 * <p>Description:[ȡ�ò�ͬ���������] </p>
	 * 
	 * Created by [Kevin Liang] [2009-9-16]
	 * Midified by [�޸���] [�޸�ʱ��]
	 *
	 * @param type
	 * @return
	 */
	public static Class getNodeType(String nodetype){
		
		if(nodetype==null) return null;
		
		if(nodetype.equals(Node.NODE_Policy))
			return Node.NODE_POLICYTYPE;
		
		else if(nodetype.equals(Node.NODE_Case))
			return Node.NODE_CASETYPE;
	
		else if(nodetype.equals(Node.NODE_Faq))
			return Node.NODE_FAQTYPE;
		
		else if(nodetype.equals(Node.NODE_Service))
			return Node.NODE_SERVICETYPE;
		else
			return null;
	}
	
	
	public Node() {
		this.visible = true;
		ordering = 0;
	}

	public Node(String name) {
		this.name = name;
		this.visible = true;
		ordering = 0;
	}

	/**
	 * ���ӽڵ�ļ���
	 * 
	 * @see com.wondersgroup.falcon.model.archives.Node#getChildren()
	 */
	public SortedSet getChildren() {
		return children;
	}

	public void setChildren(SortedSet children) {
		this.children = children;
	}

	/**
	 * Ψһ��ʶ
	 * 
	 * @see com.wondersgroup.falcon.model.archives.Node#getId()
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * �ڵ��Ƿ�ɼ�
	 * 
	 * @see com.wondersgroup.falcon.model.archives.Node#isVisible()
	 */
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * �ڵ����
	 * 
	 * @see com.wondersgroup.falcon.model.archives.Node#getName()
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ���ڵ�
	 * 
	 * @see com.wondersgroup.falcon.model.archives.Node#getParent()
	 */
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * �ڵ��˳��
	 * 
	 */
	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	// ***********************************************
	/**
	 * Ϊ��ǰ�ڵ���Ӻ���
	 * 
	 * @see com.wondersgroup.falcon.model.archives.Node#addChild(com.wondersgroup.falcon.model.archives.Node)
	 */
	public void addChild(Node child) {
		child.setParent(this);
		getChildren().add(child);
	}

	/**
	 * �ƶ�
	 */
	public void move(int displacement) {
		ordering += displacement;
	}
	

	/**
	 * ���⴦����ƹ�����
	 */
	private String shortName;

	public String getShortName() {
		
		String str="";
		this.shortName = this.name;
		
		if(this.shortName!=null && this.shortName.length()>30)
		{
			str=this.shortName.substring(0,30)+"...";
		}else{
			str=this.shortName;
		}
		return str;
		
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
		
	}


	public Byte getUsertype() {
		return usertype;
	}


	public void setUsertype(Byte usertype) {
		this.usertype = usertype;
	}





}
