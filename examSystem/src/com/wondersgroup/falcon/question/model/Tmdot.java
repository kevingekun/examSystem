package com.wondersgroup.falcon.question.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
* Tmdot
*/
@Entity
@Table(name="TMDOT")
public class Tmdot  implements java.io.Serializable {

     private static final long serialVersionUID = 1L;
     private long tmm001;//主键ID
     private Long st_id;//题目主键
     private Long jdysid;//鉴定要素主键
     private String aae013;//备注

     public Tmdot(){
     }

     public Tmdot(long tmm001,Long st_id,Long jdysid,String aae013){
          this.tmm001 = tmm001;
          this.st_id = st_id;
          this.jdysid = jdysid;
          this.aae013 = aae013;
     }

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
     @SequenceGenerator(name = "SEQUENCE", sequenceName = "SEQ_TMM001",allocationSize=1)
     @Column(name = "TMM001",  unique = true, nullable = false,precision = 16, scale = 0)
     public long getTmm001(){
          return this.tmm001;
     }
     public void setTmm001(long tmm001){
          this.tmm001 = tmm001;
     }

     @Column(name = "ST_ID", precision = 18, scale = 0)
     public Long getSt_id(){
          return this.st_id;
     }
     public void setSt_id(Long st_id){
          this.st_id = st_id;
     }

     @Column(name = "JDYSID", precision = 16, scale = 0)
     public Long getJdysid(){
          return this.jdysid;
     }
     public void setJdysid(Long jdysid){
          this.jdysid = jdysid;
     }

     @Column(name = "AAE013", length = 200)
     public String getAae013(){
          return this.aae013;
     }
     public void setAae013(String aae013){
          this.aae013 = aae013;
     }

     @Override
     public String toString() {
          return ToStringBuilder.reflectionToString(this);
     }

}