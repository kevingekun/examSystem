package com.wondersgroup.kaoshi.bo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
* Station_xf
*/
@Entity
@Table(name="STATION_XF")
public class Station_xf  implements java.io.Serializable {

     private static final long serialVersionUID = 1L;
     private Integer id;//undefined
     private String station_name;//undefined
     private String contacts;//undefined
     private String contact_number;//undefined
     private String address;//undefined
     private String remark;//undefined
     private String valid;//undefined

     public Station_xf(){
     }

     public Station_xf(Integer id,String station_name,String contacts,String contact_number,String address,String remark,String valid){
          this.id = id;
          this.station_name = station_name;
          this.contacts = contacts;
          this.contact_number = contact_number;
          this.address = address;
          this.remark = remark;
          this.valid = valid;
     }

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
     @SequenceGenerator(name = "SEQUENCE", sequenceName = "SEQ_ID",allocationSize=1)
     @Column(name = "ID",  unique = true, nullable = false,precision = 8, scale = 0)
     public Integer getId(){
          return this.id;
     }
     public void setId(Integer id){
          this.id = id;
     }

     @Column(name = "STATION_NAME", length = 200)
     public String getStation_name(){
          return this.station_name;
     }
     public void setStation_name(String station_name){
          this.station_name = station_name;
     }

     @Column(name = "CONTACTS", length = 50)
     public String getContacts(){
          return this.contacts;
     }
     public void setContacts(String contacts){
          this.contacts = contacts;
     }

     @Column(name = "CONTACT_NUMBER", length = 20)
     public String getContact_number(){
          return this.contact_number;
     }
     public void setContact_number(String contact_number){
          this.contact_number = contact_number;
     }

     @Column(name = "ADDRESS", length = 200)
     public String getAddress(){
          return this.address;
     }
     public void setAddress(String address){
          this.address = address;
     }

     @Column(name = "REMARK", length = 200)
     public String getRemark(){
          return this.remark;
     }
     public void setRemark(String remark){
          this.remark = remark;
     }

     @Column(name = "VALID", length = 1)
     public String getValid(){
          return this.valid;
     }
     public void setValid(String valid){
          this.valid = valid;
     }

     @Override
     public String toString() {
          return ToStringBuilder.reflectionToString(this);
     }

}
