package com.huang.TibetanLibrary.domain;

public class SyllableTibet {
	private long ID;
	private long DID;
	private long SID;
	
	private String orignRepresentationText; 		//原藏语词条（表记）
	private String orignWltranscriptionText; 		//原表记威利转写
	private String orignTranscriptionText;			//原国际音标转写（读音）
	
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
	
	private String shellWilleText; 			//声韵  
	private String onsetWilleText; 			//声母 
	private String finalWilleText; 			//韵母 
	private String nuclensWilleText; 		//韵核  
	private String codaWilleText; 			//韵尾  
	
	private boolean integrated; 			//是否完整的音节（true
	
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
	public String getOrignTranscriptionText() {
		return orignTranscriptionText;
	}
	public void setOrignTranscriptionText(String orignTranscriptionText) {
		this.orignTranscriptionText = orignTranscriptionText;
	}
	public String getTranslationText() {
		return translationText;
	}
	public void setTranslationText(String translationText) {
		this.translationText = translationText;
	}
	public String getOrignRepresentationText() {
		return orignRepresentationText;
	}
	public void setOrignRepresentationText(String orignRepresentationText) {
		this.orignRepresentationText = orignRepresentationText;
	}
	public String getOrignWltranscriptionText() {
		return orignWltranscriptionText;
	}
	public void setOrignWltranscriptionText(String orignWltranscriptionText) {
		this.orignWltranscriptionText = orignWltranscriptionText;
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
	public String getShellWilleText() {
		return shellWilleText;
	}
	public void setShellWilleText(String shellWilleText) {
		this.shellWilleText = shellWilleText;
	}
	public String getOnsetWilleText() {
		return onsetWilleText;
	}
	public void setOnsetWilleText(String onsetWilleText) {
		this.onsetWilleText = onsetWilleText;
	}
	public String getFinalWilleText() {
		return finalWilleText;
	}
	public void setFinalWilleText(String finalWilleText) {
		this.finalWilleText = finalWilleText;
	}
	public String getNuclensWilleText() {
		return nuclensWilleText;
	}
	public void setNuclensWilleText(String nuclensWilleText) {
		this.nuclensWilleText = nuclensWilleText;
	}
	public String getCodaWilleText() {
		return codaWilleText;
	}
	public void setCodaWilleText(String codaWilleText) {
		this.codaWilleText = codaWilleText;
	}
	public boolean getIntegrated() {
		return integrated;
	}
	public void setIntegrated(boolean integrated) {
		this.integrated = integrated;
	}
	public String getToneText() {
		return toneText;
	}
	public void setToneText(String toneText) {
		this.toneText = toneText;
	}
	
}
