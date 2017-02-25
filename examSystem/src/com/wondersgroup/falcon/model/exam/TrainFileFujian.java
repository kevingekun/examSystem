package com.wondersgroup.falcon.model.exam;

public class TrainFileFujian {
	private String id;
	private String name;
	private String address;
	private String code;
	private TrainFile trainfile;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TrainFile getTrainfile() {
		return trainfile;
	}
	public void setTrainfile(TrainFile trainfile) {
		this.trainfile = trainfile;
	}
	
	
}
