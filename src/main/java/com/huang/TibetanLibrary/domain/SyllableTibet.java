package com.huang.TibetanLibrary.domain;

public class SyllableTibet {
	private long ID;
	private long DID;
	private long SID;
	private String syllableText;
	private String wlSyllableText;
	
	private String translationText; 		//汉语词条 
	private String representationText; 		//藏语词条（表记）
	private String wltranscriptionText; 	//表记威利转写
	private String transcriptionText; 		//国际音标转写（读音）
	
	private String shellText; 				//声韵  
	private String onsetText; 				//声母 
	private String finalText; 				//韵母 
	private String nuclensText; 			//韵核  
	private String codaText; 				//韵尾  
	private String toneText; 				//声调 
	private String integrated; 				//是否完整的音节（true
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getDID() {
		return DID;
	}
	public void setDID(long dID) {
		DID = dID;
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
