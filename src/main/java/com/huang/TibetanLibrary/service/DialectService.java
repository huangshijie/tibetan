package com.huang.TibetanLibrary.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huang.TibetanLibrary.domain.DialectDetial;
import com.huang.TibetanLibrary.domain.SyllableCluster;
import com.huang.TibetanLibrary.domain.SyllableTibet;
import com.huang.TibetanLibrary.domain.TibetanWordStructure;
import com.huang.TibetanLibrary.mapper.DialectDetialMapper;
import com.huang.TibetanLibrary.mapper.SyllableClusterMapper;
import com.huang.TibetanLibrary.mapper.SyllableTibetMapper;

@Service
public class DialectService {
	
	@Autowired
	private DialectDetialMapper dialectDetialMapper;
	
	@Autowired
	private SyllableClusterMapper syllableClusterMapper;
	
	@Autowired
	private SyllableTibetMapper syllableTibetMapper;
	
	public ArrayList<SyllableCluster> getSyllableClusterListByid(String id){
		ArrayList<SyllableCluster> result = new ArrayList<SyllableCluster>();
		result = syllableClusterMapper.findAllDialectDetials(id);
		return result;
	}
	
	public ArrayList<SyllableTibet> getSyllableTibetDrawedListByid(String id) {
		ArrayList<SyllableTibet> result = new ArrayList<SyllableTibet>();
		result = syllableTibetMapper.findAllSyllableTibetById(id);
		return result;
	}
	
	public ArrayList<DialectDetial> getAllDialectDetialsList(){
		return dialectDetialMapper.findAllDialectDetials();
	}
	
	public ArrayList<DialectDetial> readSyllableClusterXlsxFile(String absolutePath, String locationDes) {
		try(
				InputStream is = new FileInputStream(absolutePath);
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			){
				long currentTimeMillis = System.currentTimeMillis();
				DialectDetial dialectDetial= new DialectDetial();
				
				JSONObject locationDesJSON = new JSONObject(locationDes);
				dialectDetial.setDdtimestamp(currentTimeMillis);

				dialectDetial.setLanguagePoint(locationDesJSON.get("languagePoint").toString());
				dialectDetial.setLocationProvince(locationDesJSON.get("locationProvince").toString());
				dialectDetial.setLocationCity(locationDesJSON.get("locationCity").toString());
				dialectDetial.setLocationCounty(locationDesJSON.get("locationCounty").toString());
				dialectDetial.setLocationTown(locationDesJSON.get("locationTown").toString());
				dialectDetial.setLocationVillage(locationDesJSON.get("locationVillage").toString());
				dialectDetial.setLocationDetial(locationDesJSON.get("locationDetial").toString());
				dialectDetial.setName(locationDesJSON.get("name").toString());
				dialectDetial.setBirthday(locationDesJSON.get("birthday").toString());
				dialectDetial.setSex(locationDesJSON.get("sex").toString());
				dialectDetial.setEducation(locationDesJSON.get("education").toString());
				dialectDetial.setUsedLanguage(locationDesJSON.get("usedLanguage").toString());
				dialectDetial.setTel(locationDesJSON.get("tel").toString());
				dialectDetial.setProfession(locationDesJSON.get("profession").toString());
				dialectDetial.setContactAddress(locationDesJSON.get("contactAddress").toString());
				dialectDetial.setRemarks(locationDesJSON.get("remarks").toString());
				
				dialectDetialMapper.insertDialectDetial(dialectDetial);
				
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
									if(i<representationTextList.size()){
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
										tmp.setNuclensWilleText(wilStr.substring(matchWilStr.length(), matchWilStr.length()+1));
										tmp.setCodaWilleText(wilStr.substring(matchWilStr.length()+1, wilStr.length()));
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
											tmp.setFinalText(transcriptionStr.substring(matchAddition.length()-1, matchAddition.length()+1));
											tmp.setNuclensText(transcriptionStr.substring(matchAddition.length()-1, matchAddition.length()+1));
											tmp.setCodaText(transcriptionStr.substring(matchAddition.length()+1, transcriptionStr.length()));
											
										}else{
											tmp.setOnsetText(transcriptionStr.substring(0, matchTran.length()));
											tmp.setFinalText(transcriptionStr.substring(matchTran.length(), transcriptionStr.length()));
											tmp.setNuclensText(transcriptionStr.substring(matchTran.length(), matchTran.length()+1));
											tmp.setCodaText(transcriptionStr.substring(matchTran.length()+1, transcriptionStr.length()));
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
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		ArrayList<DialectDetial> result = dialectDetialMapper.findAllDialectDetials();
		return result;
	}
	
	public static void main(String[] args){
		String transcriptionStr = "nɑ̃55";
		String regEx="[^0-9]";
		Pattern pRegEx = Pattern.compile(regEx);  
		Matcher mRegEx = pRegEx.matcher(transcriptionStr);  
		System.out.println("ToneText:"+mRegEx.replaceAll("").trim().toString());
			
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
			System.out.println(transcriptionStr.substring(matchAddition.length()-1, matchAddition.length()+1));
		}
		
		System.out.println("声母 :"+(transcriptionStr.substring(0, matchTran.length())));
		System.out.println("韵母:"+(transcriptionStr.substring(matchAddition.length()-1, matchAddition.length()+1)));
		System.out.println("韵核:"+(transcriptionStr.substring(matchAddition.length()-1, matchAddition.length()+1)));
		System.out.println("韵尾:"+(transcriptionStr.substring(matchAddition.length()+1, transcriptionStr.length())));
	}
	
}
