package com.huang.TibetanLibrary.mapper;

import com.huang.TibetanLibrary.domain.Interpretation;

public interface InterpretationMapper {
	
	public Interpretation findInterpretationEntityById(long ID);
	
	public Interpretation findInterpretationEntityLike(String searchWord);
	
	public void insertInterpretationSingle(Interpretation interpretation);
	
}
