package com.huang.TibetanLibrary.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huang.TibetanLibrary.domain.SyllableStructure;
import com.huang.TibetanLibrary.util.FontUtil;

@Service
public class BasewordService {
	
	public static Logger log = LoggerFactory.getLogger(BasewordService.class);
	
//	@Autowired
//	private BasewordMapper basewordMapper;
	
	public SyllableStructure getWordSplit(String originalWord){
		SyllableStructure result = new SyllableStructure(originalWord);
		return result;
	}
	
	public static void main(String[] args){
		
//		BasewordService s = new BasewordService();
		
		String cn = "གངས།";
		
//		String cn1 = "བསྒྲིགས";
//		String cn2 = "དང་།";
//		String cn3 = "གངས།";
//		String cn4 = "རི།";
//		String cn5 = "ཀླུ";
//		String cn6 = "འགྲན";
//		String cn7 = "རི།";
//		System.out.println(s.getWordSplit(cn).getRadical());
//		System.out.println(s.getWordSplit(cn1).getRadical());
//		System.out.println(s.getWordSplit(cn2).getRadical());
//		System.out.println(s.getWordSplit(cn3).getRadical());
//		System.out.println(s.getWordSplit(cn4).getRadical());
//		System.out.println(s.getWordSplit(cn5).getRadical());
//		System.out.println(s.getWordSplit(cn6).getRadical());
//		System.out.println(s.getWordSplit(cn7).getRadical());
		
	    char[] chars = cn.toCharArray();
	    
	    String returnStr = "";
	    for (int i = 0; i < chars.length; i++) {
	    	
	      returnStr += "\\u" + Integer.toString(chars[i], 16);
	      System.out.println(chars[i]);
	      System.out.println(Integer.toHexString(chars[i]));
	      System.out.println(FontUtil.WILLIESET.get(Integer.toHexString(chars[i])));
	    }
	    System.out.println(returnStr);
		
	}
}
