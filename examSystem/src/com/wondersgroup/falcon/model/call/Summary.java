package com.wondersgroup.falcon.model.call;

import java.util.Date;

public class Summary {

    private Long id;                //�����Ҫ��Ψһ��ʶ
    private Long callid;            //������ˮ��
    private Long userid;            //��д��ϯ���ţ������������ͨ��ѯԱ��
    private String summary;         //�����Ҫ
    private Date eventtime;         //�����Ҫ��дʱ��
    
    public Summary(){
        
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

    public String getSummary(){
        return this.summary;
    }
    
    public void setSummary(String summary){
        this.summary = summary;
    }

    public Date getEventtime(){
        return this.eventtime;
    }
    
    public void setEventtime(Date eventtime){
        this.eventtime = eventtime;
    }


}
