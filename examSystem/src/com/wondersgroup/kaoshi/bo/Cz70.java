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
* Cz70
*/
@Entity
@Table(name="CZ70")
public class Cz70  implements java.io.Serializable {

     private static final long serialVersionUID = 1L;
     private String ccz137;//undefined
     private String ccz136;//undefined
     private String trade;//undefined
     private String oldid;//undefined
     private String fwjg;//undefined
     private String fwh;//undefined
     
     @Column(name = "FEJG", length = 100)
     public String getFwjg() {
		return fwjg;
	}

	public void setFwjg(String fwjg) {
		this.fwjg = fwjg;
	}
	@Column(name = "FWH", length = 100)
	public String getFwh() {
		return fwh;
	}

	public void setFwh(String fwh) {
		this.fwh = fwh;
	}

	public Cz70(){
     }

     public Cz70(String ccz137,String ccz136,String trade,String oldid){
          this.ccz137 = ccz137;
          this.ccz136 = ccz136;
          this.trade = trade;
          this.oldid = oldid;
     }

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
     @SequenceGenerator(name = "SEQUENCE", sequenceName = "SEQ_CCZ137",allocationSize=1)
     @Column(name = "CCZ137",  unique = true, nullable = false,length = 16)
     public String getCcz137(){
          return this.ccz137;
     }
     public void setCcz137(String ccz137){
          this.ccz137 = ccz137;
     }

     @Column(name = "CCZ136", length = 50)
     public String getCcz136(){
          return this.ccz136;
     }
     public void setCcz136(String ccz136){
          this.ccz136 = ccz136;
     }

     @Column(name = "TRADE", length = 100)
     public String getTrade(){
          return this.trade;
     }
     public void setTrade(String trade){
          this.trade = trade;
     }

     @Column(name = "OLDID", length = 10)
     public String getOldid(){
          return this.oldid;
     }
     public void setOldid(String oldid){
          this.oldid = oldid;
     }

     @Override
     public String toString() {
          return ToStringBuilder.reflectionToString(this);
     }

}