package com.wondersgroup.kaoshi.bo;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
* E_paper_team
*/
@Entity
@Table(name="qdyth.E_PAPER_TEAM@QDYTH")
public class EPaperTeam  implements java.io.Serializable {

     private static final long serialVersionUID = 1L;
     private long id;//undefined
     private Long sj_id;//undefined
     private Long team_id;//undefined
     private Date relate_time;//undefined
     private Integer flag;//undefined
     private String remark;//undefined
     private Integer download;//undefined
     private Date download_time;//undefined
     private Integer upload;//undefined
     private Date upload_time;//undefined

     public EPaperTeam(){
     }

     public EPaperTeam(long id,Long sj_id,Long team_id,Date relate_time,Integer flag,String remark,Integer download,Date download_time,Integer upload,Date upload_time){
          this.id = id;
          this.sj_id = sj_id;
          this.team_id = team_id;
          this.relate_time = relate_time;
          this.flag = flag;
          this.remark = remark;
          this.download = download;
          this.download_time = download_time;
          this.upload = upload;
          this.upload_time = upload_time;
     }

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
     @SequenceGenerator(name = "SEQUENCE", sequenceName = "qdyth.SEQ_PT_ID",allocationSize=1)
     @Column(name = "ID",  unique = true, nullable = false,precision = 18, scale = 0)
     public long getId(){
          return this.id;
     }
     public void setId(long id){
          this.id = id;
     }

     @Column(name = "SJ_ID", precision = 18, scale = 0)
     public Long getSj_id(){
          return this.sj_id;
     }
     public void setSj_id(Long sj_id){
          this.sj_id = sj_id;
     }

     @Column(name = "TEAM_ID", precision = 18, scale = 0)
     public Long getTeam_id(){
          return this.team_id;
     }
     public void setTeam_id(Long team_id){
          this.team_id = team_id;
     }

     @Temporal(TemporalType.TIMESTAMP)
     @Column(name = "RELATE_TIME", length = 7)
     public Date getRelate_time(){
          return this.relate_time;
     }
     public void setRelate_time(Date relate_time){
          this.relate_time = relate_time;
     }

     @Column(name = "FLAG", precision = 1, scale = 0)
     public Integer getFlag(){
          return this.flag;
     }
     public void setFlag(Integer flag){
          this.flag = flag;
     }

     @Column(name = "REMARK", length = 1000)
     public String getRemark(){
          return this.remark;
     }
     public void setRemark(String remark){
          this.remark = remark;
     }

     @Column(name = "DOWNLOAD", precision = 1, scale = 0)
     public Integer getDownload(){
          return this.download;
     }
     public void setDownload(Integer download){
          this.download = download;
     }

     @Temporal(TemporalType.TIMESTAMP)
     @Column(name = "DOWNLOAD_TIME", length = 7)
     public Date getDownload_time(){
          return this.download_time;
     }
     public void setDownload_time(Date download_time){
          this.download_time = download_time;
     }

     @Column(name = "UPLOAD", precision = 1, scale = 0)
     public Integer getUpload(){
          return this.upload;
     }
     public void setUpload(Integer upload){
          this.upload = upload;
     }

     @Temporal(TemporalType.TIMESTAMP)
     @Column(name = "UPLOAD_TIME", length = 7)
     public Date getUpload_time(){
          return this.upload_time;
     }
     public void setUpload_time(Date upload_time){
          this.upload_time = upload_time;
     }

     @Override
     public String toString() {
          return ToStringBuilder.reflectionToString(this);
     }

}
