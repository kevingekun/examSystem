package com.wondersgroup.kaoshi.bo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
* Tjobsubject
*/
@Entity
@Table(name="TJOBSUBJECT")
public class Tjobsubject  implements java.io.Serializable {

     private static final long serialVersionUID = 1L;
     private long id;//undefined
     private String id_job;//undefined
     private String jobname;//undefined
     private String rankname;//undefined
     private String jobsubjectname;//undefined
     private String creator;//undefined
     private String createdate;//undefined
     private String auditor;//undefined
     private String auditdate;//undefined
     private String subjecttablename;//undefined
     private Long isreadonly;//undefined
     private String updatepowerlist;//undefined
     private Long haspersonal;//undefined
     private BigDecimal personalscore;//undefined
     private String note;//undefined
     private String aliasname;//undefined
     private String category;//题库类型（国家1、省2、市3、企业4、日常鉴定5）

     public Tjobsubject(){
     }

     public Tjobsubject(long id,String id_job,String jobname,String rankname,String jobsubjectname,String creator,String createdate,String auditor,String auditdate,String subjecttablename,Long isreadonly,String updatepowerlist,Long haspersonal,BigDecimal personalscore,String note,String aliasname){
          this.id = id;
          this.id_job = id_job;
          this.jobname = jobname;
          this.rankname = rankname;
          this.jobsubjectname = jobsubjectname;
          this.creator = creator;
          this.createdate = createdate;
          this.auditor = auditor;
          this.auditdate = auditdate;
          this.subjecttablename = subjecttablename;
          this.isreadonly = isreadonly;
          this.updatepowerlist = updatepowerlist;
          this.haspersonal = haspersonal;
          this.personalscore = personalscore;
          this.note = note;
          this.aliasname = aliasname;
     }

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
     @SequenceGenerator(name = "SEQUENCE", sequenceName = "SEQ_ID",allocationSize=1)
     @Column(name = "ID",  unique = true, nullable = false, scale = 0)
     public long getId(){
          return this.id;
     }
     public void setId(long id){
          this.id = id;
     }

     @Column(name = "ID_JOB", length = 20)
     public String getId_job(){
          return this.id_job;
     }
     public void setId_job(String id_job){
          this.id_job = id_job;
     }

     @Column(name = "JOBNAME", length = 50)
     public String getJobname(){
          return this.jobname;
     }
     public void setJobname(String jobname){
          this.jobname = jobname;
     }

     @Column(name = "RANKNAME", length = 50)
     public String getRankname(){
          return this.rankname;
     }
     public void setRankname(String rankname){
          this.rankname = rankname;
     }

     @Column(name = "JOBSUBJECTNAME", length = 50)
     public String getJobsubjectname(){
          return this.jobsubjectname;
     }
     public void setJobsubjectname(String jobsubjectname){
          this.jobsubjectname = jobsubjectname;
     }

     @Column(name = "CREATOR", length = 20)
     public String getCreator(){
          return this.creator;
     }
     public void setCreator(String creator){
          this.creator = creator;
     }

     @Column(name = "CREATEDATE", length = 20)
     public String getCreatedate(){
          return this.createdate;
     }
     public void setCreatedate(String createdate){
          this.createdate = createdate;
     }

     @Column(name = "AUDITOR", length = 20)
     public String getAuditor(){
          return this.auditor;
     }
     public void setAuditor(String auditor){
          this.auditor = auditor;
     }

     @Column(name = "AUDITDATE", length = 20)
     public String getAuditdate(){
          return this.auditdate;
     }
     public void setAuditdate(String auditdate){
          this.auditdate = auditdate;
     }

     @Column(name = "SUBJECTTABLENAME", length = 200)
     public String getSubjecttablename(){
          return this.subjecttablename;
     }
     public void setSubjecttablename(String subjecttablename){
          this.subjecttablename = subjecttablename;
     }

     @Column(name = "ISREADONLY",  scale = 0)
     public Long getIsreadonly(){
          return this.isreadonly;
     }
     public void setIsreadonly(Long isreadonly){
          this.isreadonly = isreadonly;
     }

     @Column(name = "UPDATEPOWERLIST", length = 200)
     public String getUpdatepowerlist(){
          return this.updatepowerlist;
     }
     public void setUpdatepowerlist(String updatepowerlist){
          this.updatepowerlist = updatepowerlist;
     }

     @Column(name = "HASPERSONAL", scale = 0)
     public Long getHaspersonal(){
          return this.haspersonal;
     }
     public void setHaspersonal(Long haspersonal){
          this.haspersonal = haspersonal;
     }

     @Column(name = "PERSONALSCORE", precision = 10, scale = 2)
     public BigDecimal getPersonalscore(){
          return this.personalscore;
     }
     public void setPersonalscore(BigDecimal personalscore){
          this.personalscore = personalscore;
     }

     @Column(name = "NOTE", length = 200)
     public String getNote(){
          return this.note;
     }
     public void setNote(String note){
          this.note = note;
     }

     @Column(name = "ALIASNAME", length = 50)
     public String getAliasname(){
          return this.aliasname;
     }
     public void setAliasname(String aliasname){
          this.aliasname = aliasname;
     }
     
    @Column(name = "CATEGORY", length = 20)
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
     public String toString() {
          return ToStringBuilder.reflectionToString(this);
     }

}