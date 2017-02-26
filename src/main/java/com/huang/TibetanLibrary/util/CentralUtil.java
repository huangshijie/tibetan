package com.huang.TibetanLibrary.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.huang.TibetanLibrary.domain.LanguagePoint;

public class CentralUtil {
	
	public static ArrayList<LanguagePoint> LANGUAGEPOINTLIST = new ArrayList<LanguagePoint>();
	static{
		LANGUAGEPOINTLIST.add(new LanguagePoint("WEIZANG", "卫藏"));
		LANGUAGEPOINTLIST.add(new LanguagePoint("AMDO", "安多"));
		LANGUAGEPOINTLIST.add(new LanguagePoint("KANG", "康巴"));
	}
	
	public static void main(String[] args){
//		int i = 0;
//		 for(int tibetanUnm = 3; tibetanUnm < 22; tibetanUnm += 2){
//			 
//			 System.out.println(i++);
//			 System.out.println(tibetanUnm);
//		 }
		String str = "hinm";
		Pattern p = Pattern.compile("(.*)[aeiou](.*)");
        Matcher m = p.matcher(str);
        String matchStr = "";
		while(m.find()){
			matchStr = m.group(1);
		}
		System.out.println(str.substring(0, matchStr.length()));
		System.out.println(str.substring(matchStr.length(), matchStr.length()+1));
		System.out.println(str.substring(matchStr.length()+1, str.length()));
		System.out.println(matchStr.length());
        
	}
	
    private static void getStrings() {
        String str = "rrwerqq84461376qqasfdasdfrrwerqq84461377qqasfdasdaa654645aafrrwerqq84461378qqasfdaa654646aaasdfrrwerqq84461379qqasfdasdfrrwerqq84461376qqasfdasdf";
        Pattern p = Pattern.compile("qq(.*?)qq");
        Matcher m = p.matcher(str);
        ArrayList<String> strs = new ArrayList<String>();
        while (m.find()) {
            strs.add(m.group(1));            
        } 
        for (String s : strs){
            System.out.println(s);
        }        
    }
}
