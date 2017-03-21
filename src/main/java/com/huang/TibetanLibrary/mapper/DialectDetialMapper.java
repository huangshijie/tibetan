package com.huang.TibetanLibrary.mapper;

import java.util.ArrayList;

import com.huang.TibetanLibrary.domain.DialectDetial;

public interface DialectDetialMapper {
	
	public void insertDialectDetial(DialectDetial dialectDetial);
	
	public ArrayList<DialectDetial> findAllDialectDetials();

	public DialectDetial findDialectDetialById(long dID);
	
}
