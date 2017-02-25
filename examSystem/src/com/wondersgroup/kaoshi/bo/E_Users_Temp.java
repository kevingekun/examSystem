package com.wondersgroup.kaoshi.bo;


import java.math.BigDecimal;
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
* E_users_temp
*/
@Entity
@Table(name="E_USERS_TEMP")
public class E_Users_Temp  implements java.io.Serializable {

     private static final long serialVersionUID = 1L;
     private long id;//undefined
     private long jd_id;//undefined
     private String username;//undefined
     private String realname;//undefined
     private String password;//undefined
     private Date importdate;//undefined
     private Integer flag;//undefined

     public E_Users_Temp(){
     }

     public E_Users_Temp(long id,Long jd_id,String username,String realname,String password,Date importdate,Integer flag){
          this.id = id;
          this.jd_id = jd_id;
          this.username = username;
          this.realname = realname;
          this.password = password;
          this.importdate = importdate;
          this.flag = flag;
     }

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
     @SequenceGenerator(name = "SEQUENCE", sequenceName = "SEQ_E_USERS_TEMP_ID",allocationSize=1)
     @Column(name = "ID",  unique = true, nullable = false,precision = 16, scale = 0)
     public long getId(){
          return this.id;
     }
     public void setId(long id){
          this.id = id;
     }

     @Column(name = "JD_ID",  nullable = false,precision = 16, scale = 0)
     public Long getJd_id(){
          return this.jd_id;
     }
     public void setJd_id(Long jd_id){
          this.jd_id = jd_id;
     }

     @Column(name = "USERNAME", length = 20)
     public String getUsername(){
          return this.username;
     }
     public void setUsername(String username){
          this.username = username;
     }

     @Column(name = "REALNAME", length = 20)
     public String getRealname(){
          return this.realname;
     }
     public void setRealname(String realname){
          this.realname = realname;
     }

     @Column(name = "PASSWORD", length = 20)
     public String getPassword(){
          return this.password;
     }
     public void setPassword(String password){
          this.password = password;
     }

     @Temporal(TemporalType.DATE)
     @Column(name = "IMPORTDATE", length = 7)
     public Date getImportdate(){
          return this.importdate;
     }
     public void setImportdate(Date importdate){
          this.importdate = importdate;
     }

     @Column(name = "FLAG", precision = 1, scale = 0)
     public Integer getFlag(){
          return this.flag;
     }
     public void setFlag(Integer flag){
          this.flag = flag;
     }

     @Override
     public String toString() {
          return ToStringBuilder.reflectionToString(this);
     }

}