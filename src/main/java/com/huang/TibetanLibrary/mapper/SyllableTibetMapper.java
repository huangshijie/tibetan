package com.huang.TibetanLibrary.mapper;

import java.util.ArrayList;
import java.util.Map;

import com.huang.TibetanLibrary.domain.SyllableTibet;

public interface SyllableTibetMapper {
	public void insertSingleSyllableTibet(SyllableTibet syllableTibet);
	
	public ArrayList<SyllableTibet> findAllSyllableTibetById(String id);
	
	public ArrayList<SyllableTibet> findSpecialSyllableTibetByDidByType(Map<String, Object> map);
}
