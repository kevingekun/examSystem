package com.wondersgroup.kaoshi.bo;

import java.io.Serializable;

public class PrintCardVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;//undefined
    private String pc_name;//undefined
    private String ks_name;//undefined
    private String kd_address;//undefined
    private String kd_name;//undefined
    private String num;// undefined
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPc_name() {
		return pc_name;
	}
	public void setPc_name(String pc_name) {
		this.pc_name = pc_name;
	}
	public String getKs_name() {
		return ks_name;
	}
	public void setKs_name(String ks_name) {
		this.ks_name = ks_name;
	}
	public String getKd_address() {
		return kd_address;
	}
	public void setKd_address(String kd_address) {
		this.kd_address = kd_address;
	}
	public String getKd_name() {
		return kd_name;
	}
	public void setKd_name(String kd_name) {
		this.kd_name = kd_name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
}
