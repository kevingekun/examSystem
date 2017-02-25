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
 * Admission_card_user
 */
@Entity
@Table(name = "ADMISSION_CARD_USER")
public class Admission_card_user implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;// undefined
	private String name;// undefined
	private String idcard;// undefined
	private String zkh;// undefined
	private String dw_name;// undefined
	private String kc_name;// undefined
	private String seat_no;// undefined
	private String valid;// undefined
	private String reserved1;// undefined
	private String reserved2;// undefined
	private Integer file_id;// undefined

	public Admission_card_user() {
	}

	public Admission_card_user(Long id, String name, String idcard, String zkh,
			String dw_name, String kc_name, String seat_no, String valid,
			String reserved1, String reserved2, Integer file_id) {
		super();
		this.id = id;
		this.name = name;
		this.idcard = idcard;
		this.zkh = zkh;
		this.dw_name = dw_name;
		this.kc_name = kc_name;
		this.seat_no = seat_no;
		this.valid = valid;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
		this.file_id = file_id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
	@SequenceGenerator(name = "SEQUENCE", sequenceName = "ADMISSION_CARD_PC_ID", allocationSize = 1)
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "IDCARD", length = 20)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "ZKH", length = 20)
	public String getZkh() {
		return this.zkh;
	}

	public void setZkh(String zkh) {
		this.zkh = zkh;
	}

	@Column(name = "DW_NAME", length = 200)
	public String getDw_name() {
		return this.dw_name;
	}

	public void setDw_name(String dw_name) {
		this.dw_name = dw_name;
	}

	@Column(name = "KC_NAME", length = 100)
	public String getKc_name() {
		return this.kc_name;
	}

	public void setKc_name(String kc_name) {
		this.kc_name = kc_name;
	}

	@Column(name = "SEAT_NO", length = 4)
	public String getSeat_no() {
		return this.seat_no;
	}

	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}

	@Column(name = "VALID", length = 1)
	public String getValid() {
		return this.valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	@Column(name = "RESERVED1", length = 200)
	public String getReserved1() {
		return this.reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	@Column(name = "RESERVED2", length = 200)
	public String getReserved2() {
		return this.reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	@Column(name = "FILE_ID", precision = 8, scale = 0)
	public Integer getFile_id() {
		return this.file_id;
	}

	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
