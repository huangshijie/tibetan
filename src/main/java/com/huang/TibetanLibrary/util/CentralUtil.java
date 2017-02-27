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
	    // 要验证的字符串
	    String str = "hnam";
	    // 正则表达式规则
	    String regEx = "[aeiou]";
	    // 编译正则表达式
	    Pattern pattern = Pattern.compile(regEx);
	    // 忽略大小写的写法
	    // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(str);
	    // 查找字符串中是否有匹配正则表达式的字符/字符串
	    boolean rs = matcher.find();
	    System.out.println(rs);
	}
}
