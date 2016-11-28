package com.huang.TibetanLibrary.domain;

public class LanguagePoint {
	
	private long ID;
	private String languageCode;
	private String languageDes;
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public String getLanguageDes() {
		return languageDes;
	}
	public void setLanguageDes(String languageDes) {
		this.languageDes = languageDes;
	}
	
	public LanguagePoint(){
		
	}
	
	public LanguagePoint(String languageCode, String languageDes){
		this.languageCode = languageCode;
		this.languageDes = languageDes;
	}
}
