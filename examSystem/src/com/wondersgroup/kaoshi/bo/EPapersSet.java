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
 * E_papers_set
 */
@Entity
@Table(name = "E_PAPERS_SET")
public class EPapersSet implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long sj_id;// 试卷id
	private String sj_mc;// 试卷名称
	private double sj_zf;// 试卷总分
	private Date sj_zjsj;// 录入时间
	private String sj_zjrid;// 录入人员id
	private String sj_tkid;// 工种id
	private Integer sj_state;// 试卷状态（0为正常，1为删除）
	private Integer sj_type;// 试卷类型（1为机考，2为笔答）
	

	public EPapersSet() {
	}

	public EPapersSet(long sj_id, String sj_mc, double sj_zf, Date sj_zjsj,
			String sj_zjrid, String sj_gzid, Integer sj_state) {
		this.sj_id = sj_id;
		this.sj_mc = sj_mc;
		this.sj_zf = sj_zf;
		this.sj_zjsj = sj_zjsj;
		this.sj_zjrid = sj_zjrid;
		this.sj_tkid = sj_gzid;
		this.sj_state = sj_state;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE")
	@SequenceGenerator(name = "SEQUENCE", sequenceName = "SEQ_SJ_ID", allocationSize = 1)
	@Column(name = "SJ_ID", unique = true, nullable = false, precision = 18, scale = 0)
	public long getSj_id() {
		return this.sj_id;
	}

	public void setSj_id(long sj_id) {
		this.sj_id = sj_id;
	}

	@Column(name = "SJ_MC", length = 100)
	public String getSj_mc() {
		return this.sj_mc;
	}

	public void setSj_mc(String sj_mc) {
		this.sj_mc = sj_mc;
	}

	@Column(name = "SJ_ZF", precision = 5, scale = 2)
	public double getSj_zf() {
		return this.sj_zf;
	}

	public void setSj_zf(double sj_zf) {
		this.sj_zf = sj_zf;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SJ_ZJSJ", length = 7)
	public Date getSj_zjsj() {
		return this.sj_zjsj;
	}

	public void setSj_zjsj(Date sj_zjsj) {
		this.sj_zjsj = sj_zjsj;
	}

	@Column(name = "SJ_ZJRID", length = 20)
	public String getSj_zjrid() {
		return this.sj_zjrid;
	}

	public void setSj_zjrid(String sj_zjrid) {
		this.sj_zjrid = sj_zjrid;
	}

	@Column(name = "SJ_TKID", length = 30)
	public String getSj_tkid() {
		return sj_tkid;
	}

	public void setSj_tkid(String sj_tkid) {
		this.sj_tkid = sj_tkid;
	}

	@Column(name = "SJ_STATE", precision = 1, scale = 0)
	public Integer getSj_state() {
		return this.sj_state;
	}

	public void setSj_state(Integer sj_state) {
		this.sj_state = sj_state;
	}
	
	@Column(name = "SJ_TYPE", precision = 1, scale = 0)
	public Integer getSj_type() {
		return sj_type;
	}

	public void setSj_type(Integer sj_type) {
		this.sj_type = sj_type;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}