package com.huang.TibetanLibrary.util;

import java.util.ArrayList;

import com.huang.TibetanLibrary.domain.LanguagePoint;

public class CentralUtil {
	
	public static ArrayList<LanguagePoint> LANGUAGEPOINTLIST = new ArrayList<LanguagePoint>();
	static{
		LANGUAGEPOINTLIST.add(new LanguagePoint("WEIZANG", "卫藏"));
		LANGUAGEPOINTLIST.add(new LanguagePoint("AMDO", "安多"));
		LANGUAGEPOINTLIST.add(new LanguagePoint("KANG", "康巴"));
	}
	
	public static void main(String[] args){
		int i = 0;
		 for(int tibetanUnm = 3; tibetanUnm < 22; tibetanUnm += 2){
			 
			 System.out.println(i++);
			 System.out.println(tibetanUnm);
		 }
	}
}
