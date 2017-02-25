package com.wondersgroup.popedom.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;


/**
* Euserauthorith
*/
@Entity
@Table(name="E_USER_AUTHORITIES")
public class Euserauthorith  implements java.io.Serializable {

     private static final long serialVersionUID = 1L;
     private Long user_id;//undefined
     private Long authority_id;//undefined

     public Euserauthorith(){
     }

     public Euserauthorith(Long authority_id,Long user_id){
          this.authority_id = authority_id;
          this.user_id = user_id;
     }

    
     /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
     @SequenceGenerator(name = "SEQUENCE", sequenceName = "SEQ_AUTHORITY_ID",allocationSize=1)*/
     
     @Column(name = "AUTHORITY_ID",  unique = true, nullable = false,precision = 19, scale = 0)
     public Long getAuthority_id(){
          return this.authority_id;
     }
     public void setAuthority_id(Long authority_id){
          this.authority_id = authority_id;
     }
     @Id
     @GeneratedValue(generator = "paymentableGenerator")     
     @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
     @Column(name = "USER_ID",  nullable = false,precision = 19, scale = 0)
     public Long getUser_id(){
          return this.user_id;
     }
     public void setUser_id(Long user_id){
          this.user_id = user_id;
     }

     @Override
     public String toString() {
          return ToStringBuilder.reflectionToString(this);
     }

}
