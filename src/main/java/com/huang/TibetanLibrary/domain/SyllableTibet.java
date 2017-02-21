package com.huang.TibetanLibrary.domain;

public class SyllableTibet {
	private long ID;
	private long DID;
	private long SID;
	
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
	private boolean integrated; 				//是否完整的音节（true
	
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
	public String getTranslationText() {
		return translationText;
	}
	public void setTranslationText(String translationText) {
		this.translationText = translationText;
	}
	public String getRepresentationText() {
		return representationText;
	}
	public void setRepresentationText(String representationText) {
		this.representationText = representationText;
	}
	public String getWltranscriptionText() {
		return wltranscriptionText;
	}
	public void setWltranscriptionText(String wltranscriptionText) {
		this.wltranscriptionText = wltranscriptionText;
	}
	public String getTranscriptionText() {
		return transcriptionText;
	}
	public void setTranscriptionText(String transcriptionText) {
		this.transcriptionText = transcriptionText;
	}
	public String getShellText() {
		return shellText;
	}
	public void setShellText(String shellText) {
		this.shellText = shellText;
	}
	public String getOnsetText() {
		return onsetText;
	}
	public void setOnsetText(String onsetText) {
		this.onsetText = onsetText;
	}
	public String getFinalText() {
		return finalText;
	}
	public void setFinalText(String finalText) {
		this.finalText = finalText;
	}
	public String getNuclensText() {
		return nuclensText;
	}
	public void setNuclensText(String nuclensText) {
		this.nuclensText = nuclensText;
	}
	public String getCodaText() {
		return codaText;
	}
	public void setCodaText(String codaText) {
		this.codaText = codaText;
	}
	public String getToneText() {
		return toneText;
	}
	public void setToneText(String toneText) {
		this.toneText = toneText;
	}
	public boolean getIntegrated() {
		return integrated;
	}
	public void setIntegrated(boolean integrated) {
		this.integrated = integrated;
	}
	
}
