package com.huang.TibetanLibrary.domain;

public class SyllableText {
	private long ID;
	private long SID;
	private String syllableText;
	private String wlSyllableText;
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getSID() {
		return SID;
	}
	public void setSID(long sID) {
		SID = sID;
	}
	public String getSyllableText() {
		return syllableText;
	}
	public void setSyllableText(String syllableText) {
		this.syllableText = syllableText;
	}
	public String getWlSyllableText() {
		return wlSyllableText;
	}
	public void setWlSyllableText(String wlSyllableText) {
		this.wlSyllableText = wlSyllableText;
	}
	
	
}
