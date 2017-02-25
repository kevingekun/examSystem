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
 * Admission_card_file
 */
@Entity
@Table(name = "ADMISSION_CARD_FILE")
public class Admission_card_file implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;// undefined
	private String name;// undefined
	private Integer num;// undefined
	private Date time;// undefined
	private String valid;// undefined
	private Integer pc_id;

	public Admission_card_file() {
	}

	public Admission_card_file(Integer id, String name, Integer num, Date time,
			String valid) {
		this.id = id;
		this.name = name;
		this.num = num;
		this.time = time;
		this.valid = valid;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
	@SequenceGenerator(name = "SEQUENCE", sequenceName = "ADMISSION_CARD_FILE_ID", allocationSize = 1)
	@Column(name = "ID", unique = true, nullable = false, precision = 8, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NUM", precision = 8, scale = 0)
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "TIME")
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "VALID", length = 1)
	public String getValid() {
		return this.valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	@Column(name = "PC_ID", precision = 8, scale = 0)
	public Integer getPc_id() {
		return pc_id;
	}

	public void setPc_id(Integer pc_id) {
		this.pc_id = pc_id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
