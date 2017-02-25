/** 
* 
* author:mxk 
*/ 
package com.wondersgroup.popedom.bo;

import com.wondersgroup.falcon.model.auth.UserTeam;

/**
 * @author Administrator
 *
 */
public class EExamArrange {
	private Long id;//主键
	private Long examid;//考试id
	private UserTeam teamid;//考场id
	private EUser userid;//用户id
	private String usertype;//用户类型（1考生2监考人员）
	private String examtype;//考试状态（1为考试  2考试结束）
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	 
	 
	public Long getExamid() {
		return examid;
	}
	public void setExamid(Long examid) {
		this.examid = examid;
	}
	public UserTeam getTeamid() {
		return teamid;
	}
	public void setTeamid(UserTeam teamid) {
		this.teamid = teamid;
	}
	public EUser getUserid() {
		return userid;
	}
	public void setUserid(EUser userid) {
		this.userid = userid;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getExamtype() {
		return examtype;
	}
	public void setExamtype(String examtype) {
		this.examtype = examtype;
	}
	
}
