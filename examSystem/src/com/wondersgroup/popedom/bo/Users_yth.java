package com.wondersgroup.popedom.bo;

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
 * Users_yth
 */
@Entity
@Table(name = "qdyth.USERS_YTH@QDYTH")
public class Users_yth implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long user_id;// undefined
	private String username;// undefined
	private String realname;// undefined
	private String password;// undefined
	private String agentid;// undefined
	private Long group_id;// undefined
	private Integer status;// undefined
	private Integer enabled;// undefined
	private String devicedn;// undefined
	private String usertype;// undefined
	private String color;// undefined
	private Integer visible;// undefined
	private String userbianhao;// undefined
	private Long flag;// undefined
	private String sex;// undefined
	private String putong;// undefined
	private String userflag;// undefined
	private String iskaohe;// undefined
	private String userstar;// undefined
	private Date createtime;// undefined
	private String olduserbianhao;// undefined

	public Users_yth() {
	}

	public Users_yth(long user_id, String username, String realname,
			String password, String agentid, Long group_id, Integer status,
			Integer enabled, String devicedn, String usertype, String color,
			Integer visible, String userbianhao, Long flag, String sex,
			String putong, String userflag, String iskaohe, String userstar,
			Date createtime, String olduserbianhao) {
		this.user_id = user_id;
		this.username = username;
		this.realname = realname;
		this.password = password;
		this.agentid = agentid;
		this.group_id = group_id;
		this.status = status;
		this.enabled = enabled;
		this.devicedn = devicedn;
		this.usertype = usertype;
		this.color = color;
		this.visible = visible;
		this.userbianhao = userbianhao;
		this.flag = flag;
		this.sex = sex;
		this.putong = putong;
		this.userflag = userflag;
		this.iskaohe = iskaohe;
		this.userstar = userstar;
		this.createtime = createtime;
		this.olduserbianhao = olduserbianhao;
	}

	@Id
	@Column(name = "USER_ID", unique = true, nullable = false, precision = 19, scale = 0)
	public long getUser_id() {
		return this.user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	@Column(name = "USERNAME", length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "REALNAME", length = 100)
	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "PASSWORD", length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "AGENTID", length = 10)
	public String getAgentid() {
		return this.agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	@Column(name = "GROUP_ID", precision = 19, scale = 0)
	public Long getGroup_id() {
		return this.group_id;
	}

	public void setGroup_id(Long group_id) {
		this.group_id = group_id;
	}

	@Column(name = "STATUS", precision = 3, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "ENABLED", precision = 3, scale = 0)
	public Integer getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Column(name = "DEVICEDN", length = 10)
	public String getDevicedn() {
		return this.devicedn;
	}

	public void setDevicedn(String devicedn) {
		this.devicedn = devicedn;
	}

	@Column(name = "USERTYPE", length = 10)
	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	@Column(name = "COLOR", length = 255)
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "VISIBLE", precision = 3, scale = 0)
	public Integer getVisible() {
		return this.visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	@Column(name = "USERBIANHAO", length = 255)
	public String getUserbianhao() {
		return this.userbianhao;
	}

	public void setUserbianhao(String userbianhao) {
		this.userbianhao = userbianhao;
	}

	@Column(name = "FLAG", precision = 1, scale = 0)
	public Long getFlag() {
		return this.flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}

	@Column(name = "SEX", length = 20)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "PUTONG", length = 20)
	public String getPutong() {
		return this.putong;
	}

	public void setPutong(String putong) {
		this.putong = putong;
	}

	@Column(name = "USERFLAG", length = 20)
	public String getUserflag() {
		return this.userflag;
	}

	public void setUserflag(String userflag) {
		this.userflag = userflag;
	}

	@Column(name = "ISKAOHE", length = 20)
	public String getIskaohe() {
		return this.iskaohe;
	}

	public void setIskaohe(String iskaohe) {
		this.iskaohe = iskaohe;
	}

	public String getUserstar() {
		return this.userstar;
	}

	public void setUserstar(String userstar) {
		this.userstar = userstar;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "OLDUSERBIANHAO", length = 255)
	public String getOlduserbianhao() {
		return this.olduserbianhao;
	}

	public void setOlduserbianhao(String olduserbianhao) {
		this.olduserbianhao = olduserbianhao;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
