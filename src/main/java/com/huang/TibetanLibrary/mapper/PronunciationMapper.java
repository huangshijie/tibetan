package com.huang.TibetanLibrary.mapper;

import com.huang.TibetanLibrary.domain.Pronunciation;

public interface PronunciationMapper {

	public Pronunciation findPronunciationEntityById(long ID);
	
	public void insertPronunciationSingle(Pronunciation pronunciation);
}
