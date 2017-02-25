package com.wondersgroup.falcon.model.call;

import java.util.Date;

public class Summary {

    private Long id;                //来电概要的唯一标识
    private Long callid;            //来电流水号
    private Long userid;            //填写坐席工号（包括监理和普通咨询员）
    private String summary;         //来电概要
    private Date eventtime;         //来电概要填写时间
    
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
