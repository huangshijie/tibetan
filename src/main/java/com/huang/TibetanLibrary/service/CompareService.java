package com.huang.TibetanLibrary.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huang.TibetanLibrary.domain.DialectDetial;
import com.huang.TibetanLibrary.domain.SyllableCluster;
import com.huang.TibetanLibrary.domain.SyllableTibet;
import com.huang.TibetanLibrary.domain.SyllableTibetTmpForDiaDia;
import com.huang.TibetanLibrary.domain.TibetanWordStructure;
import com.huang.TibetanLibrary.mapper.DialectDetialMapper;
import com.huang.TibetanLibrary.mapper.SyllableClusterMapper;
import com.huang.TibetanLibrary.mapper.SyllableTibetMapper;
import com.huang.TibetanLibrary.util.FontUtil;

@Service
public class CompareService {
	
	@Autowired
	private SyllableTibetMapper syllableTibetMapper;
	
	@Autowired
	private DialectDetialMapper dialectDetialMapper;
	
	@Autowired
	private SyllableClusterMapper syllableClusterMapper;
	
	public ArrayList<SyllableTibet> getSpecialSyllableTibetByTiDidByType(String did, String compareType, String compareEntity, ArrayList<String> queryList){
		Map<String, Object> map = new HashMap<String, Object>();  
		ArrayList<String> queryTypeList = new ArrayList<String>();
		
		for(int i = 0;i<queryList.size();i++){
			if(!queryList.get(i).equals("")){
				char[] orginalChars = queryList.get(i).toCharArray();
				boolean flag = true;
				String returnStr = "";
			    for (int m = 0; m < orginalChars.length; m++) {
			    	if(FontUtil.WILLIESET.get(Integer.toHexString(orginalChars[m]))!=null){
			    		returnStr +=FontUtil.WILLIESET.get(Integer.toHexString(orginalChars[m]));
			    		flag = false;
			    	}
			    }
			    if(flag){
			    	queryTypeList.add(queryList.get(i));
			    }else{
			    	queryTypeList.add(returnStr);
			    }
			}
		}
		
	    map.put("did", did);
	    if(compareType.equals("diaCompTi")){
	    	if(compareEntity.equals("onSet")){
	    		map.put("queryOnsetList", queryTypeList);  
	    	}
	    	if(compareEntity.equals("final")){
	    		map.put("queryFinalList", queryTypeList);  
	    	}
	    }
	    if(compareType.equals("tiCompDia")){
	    	if(compareEntity.equals("onSetWille")){
	    		map.put("queryOnsetWilleList", queryTypeList);  
	    	}
	    	if(compareEntity.equals("finalWille")){
	    		map.put("queryFinalWilleList", queryTypeList);  
	    	}
	    }
		return syllableTibetMapper.findSpecialSyllableTibetByTiDidByType(map);
	}
	
	public ArrayList<SyllableTibetTmpForDiaDia> getSpecialSyllableTibetByDiaDidByType(ArrayList<String> did, String compareEntity, ArrayList<String> queryStrList) {
		Map<String, Object> map = new HashMap<String, Object>();  
		
		ArrayList<String> queryTypeList = new ArrayList<String>();
		
		for(int i = 0;i<queryStrList.size();i++){
			if(!queryStrList.get(i).equals("")){
				char[] orginalChars = queryStrList.get(i).toCharArray();
				boolean flag = true;
				String returnStr = "";
			    for (int m = 0; m < orginalChars.length; m++) {
			    	if(FontUtil.WILLIESET.get(Integer.toHexString(orginalChars[m]))!=null){
			    		returnStr +=FontUtil.WILLIESET.get(Integer.toHexString(orginalChars[m]));
			    		flag = false;
			    	}
			    }
			    if(flag){
			    	queryTypeList.add(queryStrList.get(i));
			    }else{
			    	queryTypeList.add(returnStr);
			    }
			}
		}
		
		map.put("didList", did);
		map.put("queryList", queryTypeList); 
		ArrayList<SyllableTibetTmpForDiaDia> result = new ArrayList<SyllableTibetTmpForDiaDia>();
		if(compareEntity.equals("onSet")){
			result = syllableTibetMapper.findSpecialSyllableTibetByDiaDidByOnset(map);
    	}
    	if(compareEntity.equals("final")){
    		result = syllableTibetMapper.findSpecialSyllableTibetByDiaDidByFinal(map);
    	}
    	return result;
	}
	
	public ArrayList<DialectDetial> getAllDialectsList(){
		return dialectDetialMapper.findAllDialectDetials();
	}
	
	public DialectDetial uploadLocalDialect(String absolutePath, String locationDes) {
		DialectDetial result = new DialectDetial();
		try(
				InputStream is = new FileInputStream(absolutePath);
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			){
				long currentTimeMillis = System.currentTimeMillis();
				DialectDetial dialectDetial= new DialectDetial();
				
				dialectDetial.setDdtimestamp(currentTimeMillis);

				dialectDetial.setLanguagePoint(locationDes);
				
				dialectDetialMapper.insertTmpDialectDetial(dialectDetial);
				
				long DID = dialectDetial.getID();
				
				for(int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++){
					XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
					
					//for(int numRow = 1; numRow < 2; numRow++){
					for(int numRow = 1; numRow < xssfSheet.getLastRowNum(); numRow++){
						XSSFRow xssfRow = xssfSheet.getRow(numRow);
						if (xssfRow != null) {
							
							SyllableCluster tmpSyllableCluster = new SyllableCluster();
							ArrayList<SyllableTibet> syllableTibetList = new ArrayList<SyllableTibet>();
							int syllablesCount = 0;
							
							tmpSyllableCluster.setDID(DID);
							
							if(xssfRow.getCell(1) != null){tmpSyllableCluster.setTranslationText(xssfRow.getCell(1).toString());}else{tmpSyllableCluster.setTranslationText("");}
							if(xssfRow.getCell(2) != null && xssfRow.getCell(4) != null){
								
								tmpSyllableCluster.setRepresentationText(xssfRow.getCell(2).toString());
								
								char[] orginalChars = xssfRow.getCell(2).toString().toCharArray();
								String[] transcriptionChars = xssfRow.getCell(4).toString().split(" ");
								ArrayList<String> representationTextList= new ArrayList<String>();
								String returnStr = "";
								
								for (int i = 0; i < orginalChars.length; i++) {
									if(String.valueOf(orginalChars[i]).equals("་")||String.valueOf(orginalChars[i]).equals("།")){
										representationTextList.add(returnStr);
										returnStr = "";
										syllablesCount++;
									}else{
										returnStr += orginalChars[i];
									}
								}
								if(returnStr != ""){
									syllablesCount++;
									representationTextList.add(returnStr);
								}
								
								String wiStr = "";
								int length = 0;
								if(representationTextList.size() >= transcriptionChars.length){
									length = representationTextList.size();
								}else{
									length = transcriptionChars.length;
								}
								for(int i = 0; i<length ;i++){
									SyllableTibet tmp = new SyllableTibet();
									tmp.setDID(DID);
									tmp.setOrignRepresentationText(tmpSyllableCluster.getRepresentationText());
									if(length == 1){
										tmp.setTranslationText(tmpSyllableCluster.getTranslationText()); 
									}else{
										tmp.setTranslationText(tmpSyllableCluster.getTranslationText()+"(第"+(i+1)+"音节)"); 
									}
									
									
									//音节威利转写
									if(i<representationTextList.size()&&!representationTextList.get(i).equals("")){
										TibetanWordStructure tmpTWStructure = new TibetanWordStructure(representationTextList.get(i));
										wiStr += tmpTWStructure.getWillieTransfer() + " ";
										tmp.setWltranscriptionText(tmpTWStructure.getWillieTransfer());
										tmp.setRepresentationText(tmpTWStructure.getRepresentationText());
										
										String wilStr = tmpTWStructure.getWillieTransfer();
										String matchWilStr = "";
										if(TibetanWordStructure.checkMutliVowels(wilStr)){
											Pattern p = Pattern.compile("(.*)[i o u e]v[i o u e](.*)");
											Matcher m = p.matcher(wilStr);
											if(m.find()){
												matchWilStr = m.group(1);
											}
										}else{
											Pattern p = Pattern.compile("(.*)[aeiou](.*)");
											Matcher m = p.matcher(wilStr);
											while(m.find()){
												matchWilStr = m.group(1);
											}
										}
										tmp.setOnsetWilleText(wilStr.substring(0, matchWilStr.length()));
										tmp.setFinalWilleText(wilStr.substring(matchWilStr.length(), wilStr.length()));
										if(matchWilStr.length()+1 > wilStr.length()){
											tmp.setNuclensWilleText("");
											tmp.setCodaWilleText("");
										}else{
											tmp.setNuclensWilleText(wilStr.substring(matchWilStr.length(), matchWilStr.length()+1));
											tmp.setCodaWilleText(wilStr.substring(matchWilStr.length()+1, wilStr.length()));
										}
									}else{
										tmp.setWltranscriptionText("");
										tmp.setRepresentationText("");
										tmp.setOnsetWilleText("");
										tmp.setFinalWilleText("");
										tmp.setNuclensWilleText("");
										tmp.setCodaWilleText("");
									}
									
									//音节国际音标
									if(i < transcriptionChars.length){
										tmp.setTranscriptionText(transcriptionChars[i]);
										String transcriptionStr = transcriptionChars[i];
										String regEx="[^0-9]";
										Pattern pRegEx = Pattern.compile(regEx);  
										Matcher mRegEx = pRegEx.matcher(transcriptionStr);  
										tmp.setToneText(mRegEx.replaceAll("").trim().toString());
											
										transcriptionStr = transcriptionStr.replaceAll("\\d+","");
										Pattern pTran = Pattern.compile("(.*)[iyɨʉɯuIʏʊeøəɵɤoɛœɜɞʌɔæɐaɶɑɒ](.*)");
										Matcher mTran = pTran.matcher(transcriptionStr);
										String matchTran = "";
										while(mTran.find()){
											matchTran = mTran.group(1);
										}
										
										Pattern pAddition = Pattern.compile("(.*)[̃̈ ](.*)");
										Matcher mAddition = pAddition.matcher(transcriptionStr);
										String matchAddition = "";
										if(mAddition.find()){
											matchAddition = mAddition.group(1);
											tmp.setOnsetText(transcriptionStr.substring(0, matchTran.length()));
											if(matchAddition.length()+1 > transcriptionStr.length()){
												tmp.setFinalText("");
												tmp.setNuclensText("");
												tmp.setCodaText("");
											}else{
												tmp.setFinalText(transcriptionStr.substring(matchAddition.length()-1, matchAddition.length()+1));
												tmp.setNuclensText(transcriptionStr.substring(matchAddition.length()-1, matchAddition.length()+1));
												tmp.setCodaText(transcriptionStr.substring(matchAddition.length()+1, transcriptionStr.length()));
											}
											
										}else{
											tmp.setOnsetText(transcriptionStr.substring(0, matchTran.length()));
											tmp.setFinalText(transcriptionStr.substring(matchTran.length(), transcriptionStr.length()));
											if(matchTran.length()+1 > transcriptionStr.length()){
												tmp.setNuclensText("");
												tmp.setCodaText("");
											}else{
												tmp.setNuclensText(transcriptionStr.substring(matchTran.length(), matchTran.length()+1));
												tmp.setCodaText(transcriptionStr.substring(matchTran.length()+1, transcriptionStr.length()));
											}
										}
										
									}else{
										tmp.setTranscriptionText("");
										tmp.setToneText("");
										tmp.setOnsetText("");
										tmp.setFinalText("");
										tmp.setNuclensText("");
										tmp.setCodaText("");
									}
									syllableTibetList.add(tmp);
								}
								
								tmpSyllableCluster.setWltranscriptionText(wiStr);
							}else{
								tmpSyllableCluster.setWltranscriptionText("");
							}
							if(xssfRow.getCell(4) != null){tmpSyllableCluster.setTranscriptionText(xssfRow.getCell(4).toString());}else{tmpSyllableCluster.setTranscriptionText("");}							
							
							if(xssfRow.getCell(6) != null){tmpSyllableCluster.setPronunciationText(xssfRow.getCell(6).toString());}else{tmpSyllableCluster.setPronunciationText("");}							
							if(xssfRow.getCell(7) != null){tmpSyllableCluster.setVideoText(xssfRow.getCell(7).toString());}else{tmpSyllableCluster.setVideoText("");}							
							if(xssfRow.getCell(8) != null){tmpSyllableCluster.setPrimaryStressedPosition(xssfRow.getCell(8).toString());}else{tmpSyllableCluster.setPrimaryStressedPosition("");}
							if(xssfRow.getCell(9) != null){tmpSyllableCluster.setSecondaryBtressedPosition(xssfRow.getCell(9).toString());}else{tmpSyllableCluster.setSecondaryBtressedPosition("");}
							
							if(xssfRow.getCell(2) != null){
								tmpSyllableCluster.setSyllablesCount(syllablesCount);
							}else{
								tmpSyllableCluster.setSyllablesCount(0);
							}
							
							syllableClusterMapper.insertSyllableClusterSingle(tmpSyllableCluster);
							
							long SID = tmpSyllableCluster.getID();
							
							if(syllableTibetList.size() != 0){
								for(int i = 0; i<syllableTibetList.size(); i++){
									SyllableTibet tmp = syllableTibetList.get(i);
									tmp.setSID(SID);
									tmp.setOrignWltranscriptionText(tmpSyllableCluster.getWltranscriptionText());
									tmp.setOrignTranscriptionText(tmpSyllableCluster.getTranscriptionText());
									syllableTibetMapper.insertSingleSyllableTibet(tmp);
								}
							}
						}
					}
				} 
				result = dialectDetialMapper.findDialectDetialById(DID);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		return result;
	}
}
