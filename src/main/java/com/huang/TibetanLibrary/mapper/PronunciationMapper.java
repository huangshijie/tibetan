package com.huang.TibetanLibrary.mapper;

import java.util.ArrayList;

import com.huang.TibetanLibrary.domain.Pronunciation;

public interface PronunciationMapper {

	public Pronunciation findPronunciationEntityById(long ID);
	
	public ArrayList<Pronunciation> findPronunciationEntitysByRId(long ID);
	
	public void insertPronunciationSingle(Pronunciation pronunciation);
}
