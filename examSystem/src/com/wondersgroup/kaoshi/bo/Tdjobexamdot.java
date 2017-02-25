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
* Tdjobexamdot
*/
@Entity
@Table(name="TDJOBEXAMDOT")
public class Tdjobexamdot  implements java.io.Serializable {

     private static final long serialVersionUID = 1L;
     private long jdysid;//undefined
     private String ccz137;//undefined
     private String l1name;//undefined
     private String l2name;//undefined
     private String dotname;//undefined
     private Integer rank1pent;//undefined
     private Integer rank2pent;//undefined
     private Integer rank3pent;//undefined
     private Integer rank4pent;//undefined
     private Integer rank5pent;//undefined
     private Integer specialpent;//undefined
     private String source;//undefined
     private String aaa131;//undefined
     private String aae130;//undefined
     private String sourfrom;//undefined

     public Tdjobexamdot(){
     }

     public Tdjobexamdot(long jdysid,String ccz137,String l1name,String l2name,String dotname,Integer rank1pent,Integer rank2pent,Integer rank3pent,Integer rank4pent,Integer rank5pent,Integer specialpent,String source,String aaa131,String aae130,String sourfrom){
          this.jdysid = jdysid;
          this.ccz137 = ccz137;
          this.l1name = l1name;
          this.l2name = l2name;
          this.dotname = dotname;
          this.rank1pent = rank1pent;
          this.rank2pent = rank2pent;
          this.rank3pent = rank3pent;
          this.rank4pent = rank4pent;
          this.rank5pent = rank5pent;
          this.specialpent = specialpent;
          this.source = source;
          this.aaa131 = aaa131;
          this.aae130 = aae130;
          this.sourfrom = sourfrom;
     }

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
     @SequenceGenerator(name = "SEQUENCE", sequenceName = "SEQ_JDYSID",allocationSize=1)
     @Column(name = "JDYSID",  unique = true, nullable = false,precision = 16, scale = 0)
     public long getJdysid(){
          return this.jdysid;
     }
     public void setJdysid(long jdysid){
          this.jdysid = jdysid;
     }

     @Column(name = "CCZ137", length = 20)
     public String getCcz137(){
          return this.ccz137;
     }
     public void setCcz137(String ccz137){
          this.ccz137 = ccz137;
     }

     @Column(name = "L1NAME", length = 50)
     public String getL1name(){
          return this.l1name;
     }
     public void setL1name(String l1name){
          this.l1name = l1name;
     }

     @Column(name = "L2NAME", length = 50)
     public String getL2name(){
          return this.l2name;
     }
     public void setL2name(String l2name){
          this.l2name = l2name;
     }

     @Column(name = "DOTNAME", length = 50)
     public String getDotname(){
          return this.dotname;
     }
     public void setDotname(String dotname){
          this.dotname = dotname;
     }

     @Column(name = "RANK1PENT", precision = 3, scale = 0)
     public Integer getRank1pent(){
          return this.rank1pent;
     }
     public void setRank1pent(Integer rank1pent){
          this.rank1pent = rank1pent;
     }

     @Column(name = "RANK2PENT", precision = 3, scale = 0)
     public Integer getRank2pent(){
          return this.rank2pent;
     }
     public void setRank2pent(Integer rank2pent){
          this.rank2pent = rank2pent;
     }

     @Column(name = "RANK3PENT", precision = 3, scale = 0)
     public Integer getRank3pent(){
          return this.rank3pent;
     }
     public void setRank3pent(Integer rank3pent){
          this.rank3pent = rank3pent;
     }

     @Column(name = "RANK4PENT", precision = 3, scale = 0)
     public Integer getRank4pent(){
          return this.rank4pent;
     }
     public void setRank4pent(Integer rank4pent){
          this.rank4pent = rank4pent;
     }

     @Column(name = "RANK5PENT", precision = 3, scale = 0)
     public Integer getRank5pent(){
          return this.rank5pent;
     }
     public void setRank5pent(Integer rank5pent){
          this.rank5pent = rank5pent;
     }

     @Column(name = "SPECIALPENT", precision = 3, scale = 0)
     public Integer getSpecialpent(){
          return this.specialpent;
     }
     public void setSpecialpent(Integer specialpent){
          this.specialpent = specialpent;
     }

     @Column(name = "SOURCE", length = 200)
     public String getSource(){
          return this.source;
     }
     public void setSource(String source){
          this.source = source;
     }

     @Column(name = "AAA131", length = 1)
     public String getAaa131(){
          return this.aaa131;
     }
     public void setAaa131(String aaa131){
          this.aaa131 = aaa131;
     }

     @Column(name = "AAE130", length = 50)
     public String getAae130(){
          return this.aae130;
     }
     public void setAae130(String aae130){
          this.aae130 = aae130;
     }

     @Column(name = "SOURFROM", length = 50)
     public String getSourfrom(){
          return this.sourfrom;
     }
     public void setSourfrom(String sourfrom){
          this.sourfrom = sourfrom;
     }

     @Override
     public String toString() {
          return ToStringBuilder.reflectionToString(this);
     }

}