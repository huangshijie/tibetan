package com.huang.TibetanLibrary.domain;

public class Interpretation {
	
	private long ID;
	private long RID;
	private String languageCode;
	private String languageDes;
	private String interpretation;
	private String interpretationExample;
	
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
	public String getInterpretation() {
		return interpretation;
	}
	public void setInterpretation(String interpretation) {
		this.interpretation = interpretation;
	}
	public String getInterpretationExample() {
		return interpretationExample;
	}
	public void setInterpretationExample(String interpretationExample) {
		this.interpretationExample = interpretationExample;
	}
}
