package com.wondersgroup.falcon.model.citizeninfo;

import java.util.Date;

public class PersonInfo {

    private Long id;                    //个人唯一标识
    private Long callid;                //来电流水号
    private String dialingno;           //来电号码
    private Long adduser;               //录入用户

    private String name;                //个人姓名（限10个文字）
    private String idcard;              //身份证号码
    private char local;                 //户口所在地
    private char workstatus;            //就业状态    
    private String company;             //所在单位名称（限100个文字）
    private char companyproperty;       //所在单位性质  (现用作是否接收短信)  
    private String mobile;              //手机号码
    private String PASNumber;           //小灵通号码
    private String telephone;           //电话号码
    private String email;               //E-mail地址（限60位）
    private boolean receivemessage;     //是否接受短消息
    private boolean receivemail;        //是否接受E-mail
    private String comments;            //备注信息（限200个汉字）
    private String way;					//获知途径
    private Date dt;					//登记时间
    private Long groupId;				//组ID
    //private String isZctz;				//是否被分配到政策通知 0-未分配；1-已分配
    //private String isShdc;				//是否被分配到社会调查 0-未分配；1-已分配
    
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
    //获知途径
    
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
    //登记时间
    
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
