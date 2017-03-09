package com.huang.TibetanLibrary.mapper;

import java.util.ArrayList;

import com.huang.TibetanLibrary.domain.SyllableTibet;

public interface SyllableTibetMapper {
	public void insertSingleSyllableTibet(SyllableTibet syllableTibet);
	
	public ArrayList<SyllableTibet> findAllSyllableTibetById(String id);
}
