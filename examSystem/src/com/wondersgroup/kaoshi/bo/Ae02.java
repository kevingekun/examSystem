package com.wondersgroup.kaoshi.bo;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
* Ae02
*/
@Entity
@Table(name="AE02")
public class Ae02  implements java.io.Serializable {

     private static final Long serialVersionUID = 1L;
     private Long aaz002;//undefined
     private Long caz219;//undefined
     private String aab301;//undefined
     private String aaa121;//undefined
     private String cae219;//undefined
     private String cae220;//undefined
     private String cae251;//undefined
     private String cae249;//undefined
     private String aae014;//undefined
     private String cae250;//undefined
     private String aae016;//undefined
     private Date aae217;//undefined
     private Long aaz001;//undefined
     private Long aac001;//undefined
     private String aab001;//undefined
     private String cae710;//undefined
     private String aae013;//undefined
     private String spname;//undefined

     public Ae02(){
     }

     public Ae02(Long aaz002,Long caz219,String aab301,String aaa121,String cae219,String cae220,String cae251,String cae249,String aae014,String cae250,String aae016,Date aae217,Long aaz001,Long aac001,String aab001,String cae710,String aae013,String spname){
          this.aaz002 = aaz002;
          this.caz219 = caz219;
          this.aab301 = aab301;
          this.aaa121 = aaa121;
          this.cae219 = cae219;
          this.cae220 = cae220;
          this.cae251 = cae251;
          this.cae249 = cae249;
          this.aae014 = aae014;
          this.cae250 = cae250;
          this.aae016 = aae016;
          this.aae217 = aae217;
          this.aaz001 = aaz001;
          this.aac001 = aac001;
          this.aab001 = aab001;
          this.cae710 = cae710;
          this.aae013 = aae013;
          this.spname = spname;
     }

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
     @SequenceGenerator(name = "SEQUENCE", sequenceName = "SEQ_AAZ002",allocationSize=1)
     @Column(name = "AAZ002",  unique = true, nullable = false,precision = 16, scale = 0)
     public Long getAaz002(){
          return this.aaz002;
     }
     public void setAaz002(Long aaz002){
          this.aaz002 = aaz002;
     }

     @Column(name = "CAZ219", precision = 16, scale = 0)
     public Long getCaz219(){
          return this.caz219;
     }
     public void setCaz219(Long caz219){
          this.caz219 = caz219;
     }

     @Column(name = "AAB301", length = 12)
     public String getAab301(){
          return this.aab301;
     }
     public void setAab301(String aab301){
          this.aab301 = aab301;
     }

     @Column(name = "AAA121", length = 50)
     public String getAaa121(){
          return this.aaa121;
     }
     public void setAaa121(String aaa121){
          this.aaa121 = aaa121;
     }

     @Column(name = "CAE219", length = 3)
     public String getCae219(){
          return this.cae219;
     }
     public void setCae219(String cae219){
          this.cae219 = cae219;
     }

     @Column(name = "CAE220", length = 3)
     public String getCae220(){
          return this.cae220;
     }
     public void setCae220(String cae220){
          this.cae220 = cae220;
     }

     @Column(name = "CAE251", length = 10)
     public String getCae251(){
          return this.cae251;
     }
     public void setCae251(String cae251){
          this.cae251 = cae251;
     }

     @Column(name = "CAE249", length = 50)
     public String getCae249(){
          return this.cae249;
     }
     public void setCae249(String cae249){
          this.cae249 = cae249;
     }

     @Column(name = "AAE014", length = 10)
     public String getAae014(){
          return this.aae014;
     }
     public void setAae014(String aae014){
          this.aae014 = aae014;
     }

     @Column(name = "CAE250", length = 50)
     public String getCae250(){
          return this.cae250;
     }
     public void setCae250(String cae250){
          this.cae250 = cae250;
     }

     @Column(name = "AAE016", length = 1)
     public String getAae016(){
          return this.aae016;
     }
     public void setAae016(String aae016){
          this.aae016 = aae016;
     }

     @Column(name = "AAE217", precision = 8, scale = 0)
     public Date getAae217(){
          return this.aae217;
     }
     public void setAae217(Date aae217){
          this.aae217 = aae217;
     }

     @Column(name = "AAZ001", precision = 16, scale = 0)
     public Long getAaz001(){
          return this.aaz001;
     }
     public void setAaz001(Long aaz001){
          this.aaz001 = aaz001;
     }

     @Column(name = "AAC001", precision = 16, scale = 0)
     public Long getAac001(){
          return this.aac001;
     }
     public void setAac001(Long aac001){
          this.aac001 = aac001;
     }

     @Column(name = "AAB001", length = 20)
     public String getAab001(){
          return this.aab001;
     }
     public void setAab001(String aab001){
          this.aab001 = aab001;
     }

     @Column(name = "CAE710", length = 20)
     public String getCae710(){
          return this.cae710;
     }
     public void setCae710(String cae710){
          this.cae710 = cae710;
     }

     @Column(name = "AAE013", length = 100)
     public String getAae013(){
          return this.aae013;
     }
     public void setAae013(String aae013){
          this.aae013 = aae013;
     }

     @Column(name = "SPNAME", length = 150)
     public String getSpname(){
          return this.spname;
     }
     public void setSpname(String spname){
          this.spname = spname;
     }

     @Override
     public String toString() {
          return ToStringBuilder.reflectionToString(this);
     }

}