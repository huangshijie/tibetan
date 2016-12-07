package com.huang.TibetanLibrary.mapper;

import java.util.ArrayList;

import com.huang.TibetanLibrary.domain.Interpretation;

public interface InterpretationMapper {
	
	public Interpretation findInterpretationEntityById(long ID);
	
	public ArrayList<Interpretation> findTibetanInterpretationEntityByRId(long ID);
	
	public ArrayList<Interpretation> findChineseInterpretationEntityByRId(long ID);
	
	public ArrayList<Interpretation> findInterpretationEntityLike(String searchWord);
	
	public void insertInterpretationSingle(Interpretation interpretation);
	
}
