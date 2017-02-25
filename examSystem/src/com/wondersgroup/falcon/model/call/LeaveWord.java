package com.wondersgroup.falcon.model.call;

import com.wondersgroup.falcon.model.auth.User;
import java.util.Date;

public class LeaveWord {
        private Long id;                    //���Ե�Ψһ��ʶ
        private Long callid;                //������ˮ��
        private String telephone;           //�������
        private Date leavetime;             //����ʱ��
        private String wavefilename;        //���������ļ�����
        
        private Character leavewordtype;    //��������
        private String name;                //����
        private String company;             //��λ
        private String address;             //��ַ
        private String postalcode;          //�ʱ�
        private String leavewordsummary;    //���Ը�Ҫ
        private Boolean needreply;          //�Ƿ���Ҫ�ظ�
        private Character replymode;        //�ظ���ʽ
        private String replytelephone;      //�ظ��绰����
        private String replyfax;            //�ظ��������
        private String replyemail;          //�ظ�E-mail��ַ
        private Date expectreplytime;       //ϣ���ظ�ʱ��
        private User dealuser;              //������ϯ
        private Date dealtime;              //���Դ���ʱ��
        private Character dealstatus;       //���Դ���״̬
        
        private String replysummary;        //�ظ���Ҫ��Ϣ����Ҫ��Ե绰��ʽ��
        private Character replyresult;      //�ظ����
        private User replyuser;             //�ظ���ϯ
        private Date replytime;             //�ظ�ʱ��
        
        
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
