package com.wondersgroup.falcon.model.call;

import java.util.Date;

public class ServiceType {

    private Long id;                //�����������Ψһ��ʶ
    private Long callid;            //������ˮ��
    private Long userid;            //��д��ϯ���ţ������������ͨ��ѯԱ��
    private String servicetype;     //�������
    private String flag;
    private Date eventtime;         //��дʱ��

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
