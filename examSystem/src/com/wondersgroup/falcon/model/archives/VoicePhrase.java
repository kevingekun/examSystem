package com.wondersgroup.falcon.model.archives;

public class VoicePhrase {

	private Long voiceId;
	
	private Long parent_id;

	private Long phraseId;
	
	private int ordering;
	
	private String body;
	
	private boolean dir;


	public boolean isDir() {
		return dir;
	}

	public void setDir(boolean dir) {
		this.dir = dir;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public Long getPhraseId() {
		return phraseId;
	}

	public void setPhraseId(Long phraseId) {
		this.phraseId = phraseId;
	}

	public Long getVoiceId() {
		return voiceId;
	}

	public void setVoiceId(Long voiceId) {
		this.voiceId = voiceId;
	}
	
}
