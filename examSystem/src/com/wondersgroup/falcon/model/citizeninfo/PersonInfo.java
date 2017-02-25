package com.wondersgroup.falcon.model.citizeninfo;

import java.util.Date;

public class PersonInfo {

    private Long id;                    //����Ψһ��ʶ
    private Long callid;                //������ˮ��
    private String dialingno;           //�������
    private Long adduser;               //¼���û�

    private String name;                //������������10�����֣�
    private String idcard;              //���֤����
    private char local;                 //�������ڵ�
    private char workstatus;            //��ҵ״̬    
    private String company;             //���ڵ�λ���ƣ���100�����֣�
    private char companyproperty;       //���ڵ�λ����  (�������Ƿ���ն���)  
    private String mobile;              //�ֻ�����
    private String PASNumber;           //С��ͨ����
    private String telephone;           //�绰����
    private String email;               //E-mail��ַ����60λ��
    private boolean receivemessage;     //�Ƿ���ܶ���Ϣ
    private boolean receivemail;        //�Ƿ����E-mail
    private String comments;            //��ע��Ϣ����200�����֣�
    private String way;					//��֪;��
    private Date dt;					//�Ǽ�ʱ��
    private Long groupId;				//��ID
    //private String isZctz;				//�Ƿ񱻷��䵽����֪ͨ 0-δ���䣻1-�ѷ���
    //private String isShdc;				//�Ƿ񱻷��䵽������ 0-δ���䣻1-�ѷ���
    
	// collections
	private java.util.Set rrResources;
	private java.util.Set rrResults;


	public PersonInfo(){
        
    }
    
    public Long getId(){
        return this.id;  
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public Long getCallid(){
        return this.callid;  
    }
    
    public void setCallid(Long callid){
        this.callid = callid;
    }
    
    public String getDialingno(){
        return this.dialingno;
    }
    
    public void setDialingno(String dialingno){
        this.dialingno = dialingno;
    }
    
    public Long getAdduser(){
        return this.adduser;  
    }
    
    public void setAdduser(Long adduser){
        this.adduser = adduser;
    }

    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getIdcard(){
        return this.idcard;
    }
    
    public void setIdcard(String idcard){
        this.idcard=idcard;
    }
    
    public char getLocal(){
        return this.local;
    }
    
    public void setLocal(char local){
        this.local=local;
    }
    //��֪;��
    
    public String getWay(){
        return this.way;
    }
    
    public void setWay(String way){
        this.way=way;
    }
    
    public char getWorkstatus(){
        return this.workstatus;
    }
    
    public void setWorkstatus(char workstatus){
        this.workstatus=workstatus;
    }
    
    public String getCompany(){
        return this.company;
    }
    
    public void setCompany(String company){
        this.company=company;
    }
    
    public char getCompanyproperty(){
        return this.companyproperty;
    }
    
    public void setCompanyproperty(char companyproperty){
        this.companyproperty=companyproperty;
    }

    public  String getMobile(){
        return this.mobile;
    }
    
    public void setMobile(String mobile){
        this.mobile=mobile;
    }
    
    public String getPASNumber(){
        return this.PASNumber;
    }
    
    public void setPASNumber(String PASNumber){
        this.PASNumber=PASNumber;
    }
    
    public String getTelephone(){
        return this.telephone;
    }
    
    public void setTelephone(String telephone){
        this.telephone=telephone;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String email){
        this.email=email;
    }
    /*
    public String getReceivemessage(){
        return this.receivemessage;
    }
    
    public void setReceivemessage(String receivemessage){
        this.receivemessage=receivemessage;
    }
    */
    
    public boolean getReceivemessage(){
        return this.receivemessage;
    }
    
    public void setReceivemessage(boolean receivemessage){
        this.receivemessage=receivemessage;
    }
    
    public boolean getReceivemail(){
        return this.receivemail;
    }
    
    public void setReceivemail(boolean receivemail){
        this.receivemail=receivemail;
    }
    //�Ǽ�ʱ��
    
    public Date getDt(){
        return this.dt;
    }

    public void setDt(Date dt){
        this.dt = dt;
    }
    
    public String getComments(){
        return this.comments;
    }
    
    public void setComments(String comments){
        this.comments=comments;
    }

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public java.util.Set getRrResources() {
		return rrResources;
	}

	public void setRrResources(java.util.Set rrResources) {
		this.rrResources = rrResources;
	}

	public java.util.Set getRrResults() {
		return rrResults;
	}

	public void setRrResults(java.util.Set rrResults) {
		this.rrResults = rrResults;
	}







  

}
