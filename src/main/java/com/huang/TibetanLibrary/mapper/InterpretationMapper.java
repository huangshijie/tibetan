package com.huang.TibetanLibrary.mapper;

import com.huang.TibetanLibrary.domain.Interpretation;

public interface InterpretationMapper {
	
	public Interpretation findInterpretationEntityById(long ID);
	
	public void insertInterpretationSingle(Interpretation interpretation);
	
}
