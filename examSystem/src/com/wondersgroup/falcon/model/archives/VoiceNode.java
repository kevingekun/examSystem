package com.wondersgroup.falcon.model.archives;

public class VoiceNode extends Node {

	private String voiceFile;

	public VoiceNode() {
		super();
	}

	public VoiceNode(String name) {
		super(name);
	}

	public String getVoiceFile() {
		return voiceFile;
	}

	public void setVoiceFile(String voiceFile) {
		this.voiceFile = voiceFile;
	}
	

}
