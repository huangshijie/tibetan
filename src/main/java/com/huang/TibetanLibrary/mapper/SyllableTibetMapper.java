package com.huang.TibetanLibrary.mapper;

import java.util.ArrayList;
import java.util.Map;

import com.huang.TibetanLibrary.domain.SyllableTibet;
import com.huang.TibetanLibrary.domain.SyllableTibetTmpForDiaDia;

public interface SyllableTibetMapper {
	public void insertSingleSyllableTibet(SyllableTibet syllableTibet);
	
	public ArrayList<SyllableTibet> findAllSyllableTibetById(String id);
	
	public ArrayList<SyllableTibet> findSpecialSyllableTibetByTiDidByType(Map<String, Object> map);
	
	public ArrayList<SyllableTibetTmpForDiaDia> findSpecialSyllableTibetByDiaDidByOnset(Map<String, Object> map);
	
	public ArrayList<SyllableTibetTmpForDiaDia> findSpecialSyllableTibetByDiaDidByFinal(Map<String, Object> map);
}
