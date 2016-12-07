package com.huang.TibetanLibrary.mapper;

import java.util.ArrayList;

import com.huang.TibetanLibrary.domain.TibetanTranslateEntry;

public interface TibetanTranslateEntryMapper{
	
	public TibetanTranslateEntry findTibetanTranslateEntryById(long ID);

	public ArrayList<TibetanTranslateEntry> findTibetanTranslateEntryLike(String searchWord);
	
	public void insertTibetanTranslateEntry(TibetanTranslateEntry tibetanTranslateEntry);
}
