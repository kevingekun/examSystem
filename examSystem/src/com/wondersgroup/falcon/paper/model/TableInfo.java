package com.wondersgroup.falcon.paper.model;

import java.util.ArrayList;

public class TableInfo implements java.io.Serializable {

	private String bianId;// 编号
	private String gongId;// 工号
	private String name;// 姓名
	private double avg;// 平均分
	private ArrayList<String> scoreList = new ArrayList<String>();// 所有分数

	public String getBianId() {
		return bianId;
	}

	public void setBianId(String bianId) {
		this.bianId = bianId;
	}

	public String getGongId() {
		return gongId;
	}

	public void setGongId(String gongId) {
		this.gongId = gongId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public ArrayList<String> getScoreList() {
		return scoreList;
	}

	public void setScoreList(ArrayList<String> scoreList) {
		this.scoreList = scoreList;
	}
}
