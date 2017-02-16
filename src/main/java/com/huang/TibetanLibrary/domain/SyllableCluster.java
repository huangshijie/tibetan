package com.huang.TibetanLibrary.domain;

import java.util.ArrayList;

public class SyllableCluster {
	private long ID;
	private long DID;
	private String translationText;
	private String representationText;
	private String wltranscriptionText;
	private String transcriptionText;
	private String pronunciationText;
	private String videoText;
	private int syllablesCount;
	private String primaryStressedPosition;
	private String secondaryBtressedPosition;
	private ArrayList<SyllableTibet> syllableTibetList;
	
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
	public String getTranslationText() {
		return translationText;
	}
	public void setTranslationText(String translationText) {
		this.translationText = translationText;
	}
	public String getPronunciationText() {
		return pronunciationText;
	}
	public void setPronunciationText(String pronunciationText) {
		this.pronunciationText = pronunciationText;
	}
	public String getVideoText() {
		return videoText;
	}
	public void setVideoText(String videoText) {
		this.videoText = videoText;
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
	public int getSyllablesCount() {
		return syllablesCount;
	}
	public void setSyllablesCount(int syllablesCount) {
		this.syllablesCount = syllablesCount;
	}
	public String getPrimaryStressedPosition() {
		return primaryStressedPosition;
	}
	public void setPrimaryStressedPosition(String primaryStressedPosition) {
		this.primaryStressedPosition = primaryStressedPosition;
	}
	public String getSecondaryBtressedPosition() {
		return secondaryBtressedPosition;
	}
	public void setSecondaryBtressedPosition(String secondaryBtressedPosition) {
		this.secondaryBtressedPosition = secondaryBtressedPosition;
	}
	public ArrayList<SyllableTibet> getSyllableTibetList() {
		return syllableTibetList;
	}
	public void setSyllableTibetList(ArrayList<SyllableTibet> syllableTibetList) {
		this.syllableTibetList = syllableTibetList;
	}
	
}
