package com.huang.TibetanLibrary.domain;

public class Pronunciation {
	private long ID;
	private long RID;
	private String languagePointCode;
	private String languagePointDes;
	private String pronunciation;
	private String pronunciationIPA;
	
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getRID() {
		return RID;
	}
	public void setRID(long rID) {
		RID = rID;
	}
	public String getLanguagePointCode() {
		return languagePointCode;
	}
	public void setLanguagePointCode(String languagePointCode) {
		this.languagePointCode = languagePointCode;
	}
	public String getLanguagePointDes() {
		return languagePointDes;
	}
	public void setLanguagePointDes(String languagePointDes) {
		this.languagePointDes = languagePointDes;
	}
	public String getPronunciation() {
		return pronunciation;
	}
	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}
	public String getPronunciationIPA() {
		return pronunciationIPA;
	}
	public void setPronunciationIPA(String pronunciationIPA) {
		this.pronunciationIPA = pronunciationIPA;
	}
	
}
