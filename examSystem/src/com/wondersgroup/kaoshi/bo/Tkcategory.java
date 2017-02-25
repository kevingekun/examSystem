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
* Tkcategory
*/
@Entity
@Table(name="TKCATEGORY")
public class Tkcategory  implements java.io.Serializable {

     private static final long serialVersionUID = 1L;
     private Integer id;//undefined
     private String name;//undefined
     private Integer parentid;//undefined
     private Long operatorid;//操作人id
     private Date addtime;//undefined
     private String remark;//undefined
     private String flag;//有效标识（0可用，1不可用）

     public Tkcategory(){
     }

     public Tkcategory(Integer id,String name,Integer parentid,Long operatorid,Date addtime,String remark){
          this.id = id;
          this.name = name;
          this.parentid = parentid;
          this.operatorid = operatorid;
          this.addtime = addtime;
          this.remark = remark;
     }

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
     @SequenceGenerator(name = "SEQUENCE", sequenceName = "SEQ_TKCATEGORYID",allocationSize=1)
     @Column(name = "ID",  unique = true, nullable = false,precision = 6, scale = 0)
     public Integer getId(){
          return this.id;
     }
     public void setId(Integer id){
          this.id = id;
     }

     @Column(name = "NAME",  nullable = false,length = 50)
     public String getName(){
          return this.name;
     }
     public void setName(String name){
          this.name = name;
     }

     @Column(name = "PARENTID",  nullable = false,precision = 6, scale = 0)
     public Integer getParentid(){
          return this.parentid;
     }
     public void setParentid(Integer parentid){
          this.parentid = parentid;
     }

     @Column(name = "OPERATORID",  nullable = false,precision = 19, scale = 0)
     public Long getOperatorid(){
          return this.operatorid;
     }
     public void setOperatorid(Long operatorid){
          this.operatorid = operatorid;
     }

     @Temporal(TemporalType.TIMESTAMP)
     @Column(name = "ADDTIME",nullable = false, length = 7)
     public Date getAddtime(){
          return this.addtime;
     }
     public void setAddtime(Date addtime){
          this.addtime = addtime;
     }

     @Column(name = "REMARK", length = 100)
     public String getRemark(){
          return this.remark;
     }
     public void setRemark(String remark){
          this.remark = remark;
     }
     @Column(name = "FLAG",  nullable = false,length = 1)
     public String getFlag(){
          return this.flag;
     }
     public void setFlag(String flag){
          this.flag = flag;
     }
     @Override
     public String toString() {
          return ToStringBuilder.reflectionToString(this);
     }

}
