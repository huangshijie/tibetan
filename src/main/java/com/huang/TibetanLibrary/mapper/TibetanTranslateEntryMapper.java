package com.huang.TibetanLibrary.mapper;

import com.huang.TibetanLibrary.domain.TibetanTranslateEntry;

public interface TibetanTranslateEntryMapper{
	
	public TibetanTranslateEntry findTibetanTranslateEntryById(long ID);

	public TibetanTranslateEntry findTibetanTranslateEntryLike(String searchWord);
	
	public void insertTibetanTranslateEntry(TibetanTranslateEntry tibetanTranslateEntry);
}
