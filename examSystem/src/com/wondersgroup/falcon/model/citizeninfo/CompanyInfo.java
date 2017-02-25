package com.wondersgroup.falcon.model.citizeninfo; 

import java.util.Date;

public class CompanyInfo {

    private Long id;                    //单位唯一标识
    private Long callid;                //来电流水号
    private String dialingno;           //来电号码
    private Long adduser;               //录入用户

    private String name;                //单位名称
    private String property;            //单位性质
    private String address;				//单位地址
    private String postcode;			//邮政编码
    private String telephone;           //单位联系电话    
    private String comments;            //备注信息
    private String person;				//劳资联系人
    private String receivemessage;     	//是否接受短消息
    private String receivemail;        	//是否接受E-mail
    private String email;				//E-mail
    private Date dt;					//登记时间
    private String mobile;				//手机号码	
    private Long groupId;				//组ID
    
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
