package com.wondersgroup.falcon.model.call;

import com.wondersgroup.falcon.model.auth.User;
import java.util.Date;

public class LeaveWord {
        private Long id;                    //留言的唯一标识
        private Long callid;                //来电流水号
        private String telephone;           //来电号码
        private Date leavetime;             //留言时间
        private String wavefilename;        //留言语音文件名称
        
        private Character leavewordtype;    //留言类型
        private String name;                //姓名
        private String company;             //单位
        private String address;             //地址
        private String postalcode;          //邮编
        private String leavewordsummary;    //留言概要
        private Boolean needreply;          //是否需要回复
        private Character replymode;        //回复方式
        private String replytelephone;      //回复电话号码
        private String replyfax;            //回复传真号码
        private String replyemail;          //回复E-mail地址
        private Date expectreplytime;       //希望回复时间
        private User dealuser;              //处理座席
        private Date dealtime;              //留言处理时间
        private Character dealstatus;       //留言处理状态
        
        private String replysummary;        //回复概要信息（主要针对电话方式）
        private Character replyresult;      //回复结果
        private User replyuser;             //回复座席
        private Date replytime;             //回复时间
        
        
        public  LeaveWord(){
            
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

        public String getTelephone(){
            return this.telephone;  
        }
        
        public void setTelephone(String telephone){
            this.telephone = telephone;
        }

        public Date getLeavetime(){
            return this.leavetime;  
        }
        
        public void setLeavetime(Date leavetime){
            this.leavetime = leavetime;
        }

        public String getWavefilename(){
            return this.wavefilename;  
        }
        
        public void setWavefilename(String wavefilename){
            this.wavefilename = wavefilename;
        }

        public Character getLeavewordtype(){
            return this.leavewordtype;  
        }
        
        public void setLeavewordtype(Character leavewordtype){
            this.leavewordtype = leavewordtype;
        }

        public String getName(){
            return this.name;  
        }
        
        public void setName(String name){
            this.name = name;
        }

        public String getCompany(){
            return this.company;  
        }
        
        public void setCompany(String company){
            this.company = company;
        }

        public String getAddress(){
            return this.address;  
        }
        
        public void setAddress(String address){
            this.address = address;
        }

        public String getPostalcode(){
            return this.postalcode;  
        }
        
        public void setPostalcode(String postalcode){
            this.postalcode = postalcode;
        }

        public String getLeavewordsummary(){
            return this.leavewordsummary;  
        }
        
        public void setLeavewordsummary(String leavewordsummary){
            this.leavewordsummary = leavewordsummary;
        }

        public Boolean getNeedreply(){
            return this.needreply;  
        }
        
        public void setNeedreply(Boolean needreply){
            this.needreply = needreply;
        }

        public Character getReplymode(){
            return this.replymode;  
        }
        
        public void setReplymode(Character replymode){
            this.replymode = replymode;
        }

        public String getReplytelephone(){
            return this.replytelephone;  
        }
        
        public void setReplytelephone(String replytelephone){
            this.replytelephone = replytelephone;
        }

        public String getReplyfax(){
            return this.replyfax;  
        }
        
        public void setReplyfax(String replyfax){
            this.replyfax = replyfax;
        }

        public String getReplyemail(){
            return this.replyemail;  
        }
        
        public void setReplyemail(String replyemail){
            this.replyemail = replyemail;
        }


        public Date getExpectreplytime(){
            return this.expectreplytime;  
        }
        
        public void setExpectreplytime(Date expectreplytime){
            this.expectreplytime = expectreplytime;
        }

        public User getDealuser(){
            return this.dealuser;  
        }
        
        public void setDealuser(User dealuser){
            this.dealuser = dealuser;
        }

        public Date getDealtime(){
            return this.dealtime;  
        }
        
        public void setDealtime(Date dealtime){
            this.dealtime = dealtime;
        }

        public Character getDealstatus(){
            return this.dealstatus;  
        }
        
        public void setDealstatus(Character dealstatus){
            this.dealstatus = dealstatus;
        }

        public String getReplysummary(){
            return this.replysummary;  
        }
        
        public void setReplysummary(String replysummary){
            this.replysummary = replysummary;
        }

        public Character getReplyresult(){
            return this.replyresult;  
        }
        
        public void setReplyresult(Character replyresult){
            this.replyresult = replyresult;
        }

        public User getReplyuser(){
            return this.replyuser;  
        }
        
        public void setReplyuser(User replyuser){
            this.replyuser = replyuser;
        }

        public Date getReplytime(){
            return this.replytime;  
        }
        
        public void setReplytime(Date replytime){
            this.replytime = replytime;
        }

}
