package com.huang.TibetanLibrary.domain;

import java.util.ArrayList;

public class TibetanTranslateEntry {
	
	private long ID;
	private String representationText;
	private String transcriptionText;
	private ArrayList<Pronunciation> languagePoinPronunciation;
	private ArrayList<Interpretation> tibetanInterpretation;
	private ArrayList<Interpretation> ChineseInterpretation;
	
	public long getID() {
		return ID;
	}
	public void setID(long ID) {
		this.ID = ID;
	}
	public String getRepresentationText() {
		return representationText;
	}
	public void setRepresentationText(String representationText) {
		this.representationText = representationText;
	}
	public String getTranscriptionText() {
		return transcriptionText;
	}
	public void setTranscriptionText(String transcriptionText) {
		this.transcriptionText = transcriptionText;
	}
	
	public ArrayList<Pronunciation> getLanguagePoinPronunciation() {
		return languagePoinPronunciation;
	}
	public void setLanguagePoinPronunciation(ArrayList<Pronunciation> languagePoinPronunciation) {
		this.languagePoinPronunciation = languagePoinPronunciation;
	}
	public ArrayList<Interpretation> getTibetanInterpretation() {
		return tibetanInterpretation;
	}
	public void setTibetanInterpretation(ArrayList<Interpretation> tibetanInterpretation) {
		this.tibetanInterpretation = tibetanInterpretation;
	}
	public ArrayList<Interpretation> getChineseInterpretation() {
		return ChineseInterpretation;
	}
	public void setChineseInterpretation(ArrayList<Interpretation> chineseInterpretation) {
		ChineseInterpretation = chineseInterpretation;
	}
	
	public TibetanTranslateEntry(){
		this.languagePoinPronunciation = new ArrayList<Pronunciation>();
		this.ChineseInterpretation = new ArrayList<Interpretation>();
		this.tibetanInterpretation = new ArrayList<Interpretation>();
	}
}
