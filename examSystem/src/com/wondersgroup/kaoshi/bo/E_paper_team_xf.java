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
* E_paper_team_xf
*/
@Entity
@Table(name="E_PAPER_TEAM_XF")
public class E_paper_team_xf  implements java.io.Serializable {

     private static final long serialVersionUID = 1L;
     private long id;//id
     private Long sj_id;//试卷id
     private Long team_id;//鉴定所id
     private Date relate_time;//关联时间
     private Integer flag;//标识（0可用1不可用）
     private String remark;//备注
     private Integer download;//是否下载
     private Date download_time;//下载时间
     private Integer upload;//试卷信息是否下放（0未下放 1下放）
     private Date upload_time;//试卷信息下放时间
     private String upload_person;//操作人证件号

     public E_paper_team_xf(){
     }

     public E_paper_team_xf(long id,Long sj_id,Long team_id,Date relate_time,Integer flag,String remark,Integer download,Date download_time,Integer upload,Date upload_time,String upload_person){
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
          this.upload_person = upload_person;
     }

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
     @SequenceGenerator(name = "SEQUENCE", sequenceName = "Seq_E_paper_team_xf",allocationSize=1)
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

     @Temporal(TemporalType.DATE)
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

     @Temporal(TemporalType.DATE)
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

     @Temporal(TemporalType.DATE)
     @Column(name = "UPLOAD_TIME", length = 7)
     public Date getUpload_time(){
          return this.upload_time;
     }
     public void setUpload_time(Date upload_time){
          this.upload_time = upload_time;
     }

     @Column(name = "UPLOAD_PERSON", length = 20)
     public String getUpload_person(){
          return this.upload_person;
     }
     public void setUpload_person(String upload_person){
          this.upload_person = upload_person;
     }

     @Override
     public String toString() {
          return ToStringBuilder.reflectionToString(this);
     }

}