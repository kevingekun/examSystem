package com.wondersgroup.falcon.model.archives;

public class FaxNode extends Node {

	private String voiceFile;
	private String faxFile;
	private String faxid;
	private String htmlfile;
	
	public String getHtmlfile() {
		return htmlfile;
	}

	public void setHtmlfile(String htmlfile) {
		this.htmlfile = htmlfile;
	}

	public FaxNode() {
		super();
	}

	public FaxNode(String name) {
		super(name);
	}

	public String getFaxFile() {
		return faxFile;
	}

	public void setFaxFile(String faxFile) {
		this.faxFile = faxFile;
	}

	public String getVoiceFile() {
		return voiceFile;
	}

	public void setVoiceFile(String voiceFile) {
		this.voiceFile = voiceFile;
	}

	public String getFaxid() {
		return faxid;
	}

	public void setFaxid(String faxid) {
		this.faxid = faxid;
	}
	
	
}
