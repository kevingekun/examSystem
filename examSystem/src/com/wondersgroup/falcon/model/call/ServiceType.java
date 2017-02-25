package com.wondersgroup.falcon.model.call;

import java.util.Date;

public class ServiceType {

    private Long id;                //来电服务类别的唯一标识
    private Long callid;            //来电流水号
    private Long userid;            //填写坐席工号（包括监理和普通咨询员）
    private String servicetype;     //服务类别
    private String flag;
    private Date eventtime;         //填写时间

    public ServiceType(){
        
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

    public Long getUserid(){
        return this.userid;
    }
    
    public void setUserid(Long userid){
        this.userid = userid;
    }

    public String getServicetype(){
        return this.servicetype;
    }
    
    public void setServicetype(String servicetype){
        this.servicetype = servicetype;
    }

    public Date getEventtime(){
        return this.eventtime;
    }
    
    public void setEventtime(Date eventtime){
        this.eventtime = eventtime;
    }

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}



    
    
    
}
