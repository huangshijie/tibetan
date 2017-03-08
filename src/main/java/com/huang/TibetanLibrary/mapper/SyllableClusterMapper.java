package com.huang.TibetanLibrary.mapper;

import java.util.ArrayList;

import com.huang.TibetanLibrary.domain.SyllableCluster;

public interface SyllableClusterMapper {
	
	public void insertSyllableClusterSingle(SyllableCluster syllableCluster);

	public ArrayList<SyllableCluster> findAllDialectDetials(String id);
	
}
