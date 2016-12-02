package com.huang.TibetanLibrary.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.huang.TibetanLibrary.util.FontUtil;

/**
 * @author Alex
 * 
 * @param representationText    表记 ;
 * @param radical				基字 ;
 * @param prefix  				前加字 ;
 * @param suffix				后加字 ;
 * @param postffix				再后加字 ;
 * @param superscript			上加字 ;
 * @param subscript				下加字 ;
 * @param vowelMark				元音附标 ;
 * @param anusvara				随韵点 ;
 * @param visarga				止韵点 ;
 * @param willieTransfer		威力转写 ;
 */
public class SyllableStructure {
	
	private long ID;
	private String representationText;
	private String radical;
	private String prefix;
	private String suffix;
	private String postffix;
	private String superscript;
	private String subscript;
	private String vowelMark;
	private String anusvara;
	private String visarga;
	private String willieTransfer;
	
	public long getID() {
		return ID;
	}
	public void setID(long ID) {
		this.ID = ID;
	}
	public String getRepresentationText() {
		return representationText;
	}
	public void setRepresentationText(String representationText) {
		this.representationText = representationText;
	}
	public String getRadical() {
		return radical;
	}
	public void setRadical(String radical) {
		this.radical = radical;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getPostffix() {
		return postffix;
	}
	public void setPostffix(String postffix) {
		this.postffix = postffix;
	}
	public String getSuperscript() {
		return superscript;
	}
	public void setSuperscript(String superscript) {
		this.superscript = superscript;
	}
	public String getSubscript() {
		return subscript;
	}
	public void setSubscript(String subscript) {
		this.subscript = subscript;
	}
	public String getVowelMark() {
		return vowelMark;
	}
	public void setVowelMark(String vowelMark) {
		this.vowelMark = vowelMark;
	}
	public String getAnusvara() {
		return anusvara;
	}
	public void setAnusvara(String anusvara) {
		this.anusvara = anusvara;
	}
	public String getVisarga() {
		return visarga;
	}
	public void setVisarga(String visarga) {
		this.visarga = visarga;
	}
	public String getWillieTransfer() {
		return willieTransfer;
	}
	public void setWillieTransfer(String willieTransfer) {
		this.willieTransfer = willieTransfer;
	}
	
	public SyllableStructure(){
		
	}
	
	public SyllableStructure(String originalWord){
		this.setRepresentationText(originalWord);
		
		char[] orginalChars = originalWord.toCharArray();
		ArrayList<Character> transferChars = new ArrayList<Character>();
		for(int i = 0; i < orginalChars.length; i++){
			if(String.valueOf(orginalChars[i]).equals("་")||String.valueOf(orginalChars[i]).equals("།")){
				break;
			}
			transferChars.add(orginalChars[i]);
		}
		
		int radicalNum = 0;
	    String returnStr = "";
	    for (int i = 0; i < transferChars.size(); i++) {
	      returnStr += FontUtil.WILLIESET.get(String.valueOf(transferChars.get(i)));
	    }
	    this.setWillieTransfer(returnStr);
	    
		if(transferChars.size() == 1){
			this.setRadical(String.valueOf(transferChars.get(0)));
		}else if(transferChars.size() == 2){
			this.setRadical(String.valueOf(transferChars.get(0)));
		}else if(transferChars.size() >= 3){
			
			if(exitInPrefixSet(transferChars.get(0))){
				if(exitInSuperscriptSet(transferChars.get(1))){
					this.setRadical(String.valueOf(transferChars.get(2)));
					radicalNum = 2;
				}else{
					this.setRadical(String.valueOf(transferChars.get(1)));
					radicalNum = 1;
				}
			}else{
				if(exitInSuperscriptSet(transferChars.get(0))){
					this.setRadical(String.valueOf(transferChars.get(1)));
					radicalNum = 1;
				}else{
					this.setRadical(String.valueOf(transferChars.get(0)));
					radicalNum = 0;
				}
			}
		}
		
		int preNum = radicalNum;
		int suNum = transferChars.size() - radicalNum - 1;
		
		if(suNum == 1){
			if(exitInSubscriptSet(transferChars.get(radicalNum+1))){
				this.setSubscript(String.valueOf(transferChars.get(radicalNum+1)));
			}else{
				if(exitInVowelSet(transferChars.get(radicalNum+1))){
					this.setVowelMark(String.valueOf(transferChars.get(radicalNum+1)));
				}else{
					this.setSuffix(String.valueOf(transferChars.get(radicalNum+1)));
				}
			}
		}
		
		if(suNum == 2){
			if(exitInSubscriptSet(transferChars.get(radicalNum+1))){
				String combinStr = FontUtil.INTERNATIONALPHONETICALPHABETSET.get(String.valueOf(transferChars.get(radicalNum+1))) 
											+ FontUtil.INTERNATIONALPHONETICALPHABETSET.get(String.valueOf(transferChars.get(radicalNum+2)));
				
				if(findInExhaustiveSet(combinStr)){
					if(exitInVowelSet(transferChars.get(radicalNum+2))){
						this.setSubscript(String.valueOf(transferChars.get(radicalNum+1)));
						this.setVowelMark(String.valueOf(transferChars.get(radicalNum+2)));
					}else{
						this.setSubscript(String.valueOf(transferChars.get(radicalNum+1)));
						this.setSuffix(String.valueOf(transferChars.get(radicalNum+2)));
					}
				}else{
					this.setSuffix(String.valueOf(transferChars.get(radicalNum+1)));
					this.setPostffix(String.valueOf(transferChars.get(radicalNum+2)));
				}
				
			}else{
				if(exitInVowelSet(transferChars.get(radicalNum+1))){
					this.setVowelMark(String.valueOf(transferChars.get(radicalNum+1)));
					this.setSuffix(String.valueOf(transferChars.get(radicalNum+2)));
				}else{
					this.setSuffix(String.valueOf(transferChars.get(radicalNum+1)));
					this.setPostffix(String.valueOf(transferChars.get(radicalNum+2)));
				}
			}
		}
		
		if(suNum == 3){
			if(exitInSubscriptSet(transferChars.get(radicalNum+1))){
				String combinStr = FontUtil.INTERNATIONALPHONETICALPHABETSET.get(String.valueOf(transferChars.get(radicalNum))) 
						+ FontUtil.INTERNATIONALPHONETICALPHABETSET.get(String.valueOf(transferChars.get(radicalNum+1)));
				if(findInExhaustiveSet(combinStr)){
					if(exitInVowelSet(transferChars.get(radicalNum+2))){
						this.setSubscript(String.valueOf(transferChars.get(radicalNum+1)));
						this.setVowelMark(String.valueOf(transferChars.get(radicalNum+2)));
						this.setSuffix(String.valueOf(transferChars.get(radicalNum+3)));
					}else{
						this.setSubscript(String.valueOf(transferChars.get(radicalNum+1)));
						this.setSuffix(String.valueOf(transferChars.get(radicalNum+2)));
						this.setPostffix(String.valueOf(transferChars.get(radicalNum+3)));
					}
				}else{
					this.setVowelMark(String.valueOf(transferChars.get(radicalNum+1)));
					this.setSuffix(String.valueOf(transferChars.get(radicalNum+2)));
					this.setPostffix(String.valueOf(transferChars.get(radicalNum+3)));
				}
			}else{
				this.setVowelMark(String.valueOf(transferChars.get(radicalNum+1)));
				this.setSuffix(String.valueOf(transferChars.get(radicalNum+2)));
				this.setPostffix(String.valueOf(transferChars.get(radicalNum+3)));
			}
		}
		
		if(suNum == 4){
			this.setSubscript(String.valueOf(transferChars.get(radicalNum+1)));
			this.setVowelMark(String.valueOf(transferChars.get(radicalNum+2)));
			this.setSuffix(String.valueOf(transferChars.get(radicalNum+3)));
			this.setPostffix(String.valueOf(transferChars.get(radicalNum+4)));
		}
		
		if(preNum == 1){
			if(exitInPrefixSet(transferChars.get(0))){
				String combinStr = FontUtil.INTERNATIONALPHONETICALPHABETSET.get(String.valueOf(transferChars.get(0))) 
						+ FontUtil.INTERNATIONALPHONETICALPHABETSET.get(String.valueOf(transferChars.get(radicalNum)));
				if(findInExhaustiveSet(combinStr)){
					this.setPrefix(String.valueOf(transferChars.get(0)));
				}else{
					this.setSuperscript(String.valueOf(transferChars.get(0)));
				}
			}else{
				this.setSuperscript(String.valueOf(transferChars.get(0)));
			}
		}
		
		if(preNum == 2){
			this.setPrefix(String.valueOf(transferChars.get(0)));
			this.setSuperscript(String.valueOf(transferChars.get(1)));
		}
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
	
	public static boolean exitInPrefixSet(char c){
		return FontUtil.PREFIXSET.containsKey(String.valueOf(c));
	}
	
	public static boolean exitInSuperscriptSet(char c){
		return FontUtil.SUPERSCRIPTSET.containsKey(String.valueOf(c));
	}
	
	public static boolean exitInVowelSet(char c){
		return FontUtil.VOWELSET.containsKey(String.valueOf(c));
	}
	
	public static boolean exitInSubscriptSet(char c){
		return FontUtil.SUBSCRIPTSET.containsKey(String.valueOf(c));
	}
}
