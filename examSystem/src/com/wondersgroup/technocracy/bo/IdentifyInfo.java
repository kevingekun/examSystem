package com.wondersgroup.technocracy.bo;

import java.util.Date;
 
@SuppressWarnings("serial")
public class IdentifyInfo implements java.io.Serializable {
	
	private Long examid;//考试id
	private Long teamid;
	private String jdpch;//鉴定批次号
    private String kcmc;//考场名称
    private String lxr;//联系人姓名
    private String lxdh;//联系人电话
    private String dz;//考场地址
    private String ksrs;//考试总人数
	public Long getExamid() {
		return examid;
	}
	public void setExamid(Long examid) {
		this.examid = examid;
	}
	public String getKcmc() {
		return kcmc;
	}
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	public String getLxr() {
		return lxr;
	}
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getDz() {
		return dz;
	}
	public void setDz(String dz) {
		this.dz = dz;
	}
	public String getKsrs() {
		return ksrs;
	}
	public void setKsrs(String ksrs) {
		this.ksrs = ksrs;
	}
	public Long getTeamid() {
		return teamid;
	}
	public void setTeamid(Long teamid) {
		this.teamid = teamid;
	}
	public String getJdpch() {
		return jdpch;
	}
	public void setJdpch(String jdpch) {
		this.jdpch = jdpch;
	}
	 
    
	 
    
    
    
	}
	
 
 
