package com.huang.TibetanLibrary.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		SyllableStructure result = new SyllableStructure();
		result.setRepresentationText(originalWord);
		
		char[] orginalChars = originalWord.toCharArray();
		ArrayList<Character> transferChars = new ArrayList<Character>();
		for(int i = 0; i < orginalChars.length; i++){
			if(String.valueOf(orginalChars[i]).equals("་")||String.valueOf(orginalChars[i]).equals("།")){
				break;
			}
			transferChars.add(orginalChars[i]);
		}
		
	    String returnStr = "";
	    for (int i = 0; i < transferChars.size(); i++) {
	      returnStr += FontUtil.WILLIESET.get(String.valueOf(transferChars.get(i)));
	    }
	    result.setWillieTransfer(returnStr);
	    
		if(transferChars.size() == 1){
			result.setRadical(String.valueOf(transferChars.get(0)));
		}else if(transferChars.size() == 2){
			result.setRadical(String.valueOf(transferChars.get(0)));
		}else if(transferChars.size() >= 3){
			
			String combinStr = FontUtil.INTERNATIONALPHONETICALPHABETSET.get(String.valueOf(transferChars.get(0))) 
					+ FontUtil.INTERNATIONALPHONETICALPHABETSET.get(String.valueOf(transferChars.get(1)));
			
			if(exitInPrefixSet(transferChars.get(0))){
				if(findInExhaustiveSet(combinStr)){
					
				}else{
					if(exitInPerscriptSet(transferChars.get(1))){
						result.setRadical(String.valueOf(transferChars.get(2)));
					}else{
						result.setRadical(String.valueOf(transferChars.get(1)));
					}
				}
			}else{
				if(exitInPerscriptSet(transferChars.get(0))){
					result.setRadical(String.valueOf(transferChars.get(1)));
				}else{
					result.setRadical(String.valueOf(transferChars.get(0)));
				}
			}
		}
		return result;
	}
	
	public static boolean exitInPrefixSet(char c){
		return FontUtil.PREFIXSET.containsKey(String.valueOf(c));
	}
	
	public static boolean exitInPerscriptSet(char c){
		return FontUtil.SUPERSCRIPTSET.containsKey(String.valueOf(c));
	}
	
	/**
	 * @author Huang
	 * Used to find the combine String in the exhaustive set
	 * @param combinStr
	 * @return true/false
	 */
	public static boolean findInExhaustiveSet(String combinStr){
		
		boolean result = false;
		String pattern = "^"+combinStr+".*";
		Pattern r = Pattern.compile(pattern);
		
		Iterator<String> iterator = FontUtil.EXHAUSTIVESET.iterator();
		while(iterator.hasNext()){
			String tmp = iterator.next();
			Matcher m = r.matcher(tmp);
			if(m.find()){
				result = true;
				break;
			}
		}
		return result;
		
	}
	
	public static void main(String[] args){
		
//		BasewordService s = new BasewordService();
		
		String cn = "གྲྷ";
		
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
	      System.out.println(FontUtil.WILLIESET.get(String.valueOf(chars[i])));
	    }
	    System.out.println(returnStr);
		
	}
}
