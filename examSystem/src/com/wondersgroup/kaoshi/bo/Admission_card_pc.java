package com.wondersgroup.kaoshi.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Admission_card_pc
 */
@Entity
@Table(name = "ADMISSION_CARD_PC")
public class Admission_card_pc implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;// undefined
	private String pc_name;// undefined
	private String ks_name;// undefined
	private String kd_address;// undefined
	private String kd_name;// undefined
	private String kssj;// undefined
	private String jssj;// undefined
	private String remark;// undefined
	private String valid;// undefined
	private Date time;// undefined

	public Admission_card_pc() {
	}

	public Admission_card_pc(Integer id, String pc_name, String ks_name,
			String kd_address, String kd_name, String kssj, String jssj,
			String remark, String valid, Date time) {
		this.id = id;
		this.pc_name = pc_name;
		this.ks_name = ks_name;
		this.kd_address = kd_address;
		this.kd_name = kd_name;
		this.kssj = kssj;
		this.jssj = jssj;
		this.remark = remark;
		this.valid = valid;
		this.time = time;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
	@SequenceGenerator(name = "SEQUENCE", sequenceName = "ADMISSION_CARD_PC_ID", allocationSize = 1)
	@Column(name = "ID", unique = true, nullable = false, precision = 8, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "PC_NAME", length = 200)
	public String getPc_name() {
		return this.pc_name;
	}

	public void setPc_name(String pc_name) {
		this.pc_name = pc_name;
	}

	@Column(name = "KS_NAME", length = 200)
	public String getKs_name() {
		return this.ks_name;
	}

	public void setKs_name(String ks_name) {
		this.ks_name = ks_name;
	}

	@Column(name = "KD_ADDRESS", length = 200)
	public String getKd_address() {
		return this.kd_address;
	}

	public void setKd_address(String kd_address) {
		this.kd_address = kd_address;
	}

	@Column(name = "KD_NAME", length = 100)
	public String getKd_name() {
		return this.kd_name;
	}

	public void setKd_name(String kd_name) {
		this.kd_name = kd_name;
	}

	@Column(name = "KSSJ", length = 50)
	public String getKssj() {
		return this.kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	@Column(name = "JSSJ", length = 50)
	public String getJssj() {
		return this.jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	@Column(name = "REMARK", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "VALID", length = 1)
	public String getValid() {
		return this.valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	@Column(name = "TIME")
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
