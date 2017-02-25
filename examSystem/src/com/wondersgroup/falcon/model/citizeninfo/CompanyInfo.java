package com.wondersgroup.falcon.model.citizeninfo; 

import java.util.Date;

public class CompanyInfo {

    private Long id;                    //��λΨһ��ʶ
    private Long callid;                //������ˮ��
    private String dialingno;           //�������
    private Long adduser;               //¼���û�

    private String name;                //��λ����
    private String property;            //��λ����
    private String address;				//��λ��ַ
    private String postcode;			//��������
    private String telephone;           //��λ��ϵ�绰    
    private String comments;            //��ע��Ϣ
    private String person;				//������ϵ��
    private String receivemessage;     	//�Ƿ���ܶ���Ϣ
    private String receivemail;        	//�Ƿ����E-mail
    private String email;				//E-mail
    private Date dt;					//�Ǽ�ʱ��
    private String mobile;				//�ֻ�����	
    private Long groupId;				//��ID
    
    private java.util.Set rrResources;
	private java.util.Set rrResults;
	
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

	public CompanyInfo(){
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

    public String getProperty(){
        return this.property;  
    }
    
    public void setProperty(String property){
        this.property = property;
    }

    public String getTelephone(){
        return this.telephone;  
    }
    
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public String getAddress(){
        return this.address;  
    }
    
    public void setAddress(String address){
        this.address = address;
    }

    public String getPostcode(){
        return this.postcode;  
    }
    
    public void setPostcode(String postcode){
        this.postcode = postcode;
    }
    
    public void setPerson(String person){
        this.person = person;
    }
    
    public String getPerson(){
        return this.person;  
    }
       
    public String getReceivemessage(){
        return this.receivemessage;  
    }
    
    public void setReceivemessage(String receivemessage){
        this.receivemessage = receivemessage;
    }
    
    public String getReceivemail(){
        return this.receivemail;  
    }
    
    public void setReceivemail(String receivemail){
        this.receivemail = receivemail;
    }
    
    
    public String getEmail(){
        return this.email;  
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public Date getDt(){
        return this.dt;
    }

    public void setDt(Date dt){
        this.dt = dt;
    }
    
    public String getMobile(){
        return this.mobile;  
    }
    
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    
    public String getComments(){
        return this.comments;  
    }
    
    public void setComments(String comments){
        this.comments = comments;
    }

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
}
