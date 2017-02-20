package com.huang.TibetanLibrary.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huang.TibetanLibrary.domain.DialectDetial;
import com.huang.TibetanLibrary.domain.Interpretation;
import com.huang.TibetanLibrary.domain.Pronunciation;
import com.huang.TibetanLibrary.domain.SyllableCluster;
import com.huang.TibetanLibrary.domain.SyllableTibet;
import com.huang.TibetanLibrary.domain.TibetanTranslateEntry;
import com.huang.TibetanLibrary.domain.TibetanWordStructure;
import com.huang.TibetanLibrary.mapper.DialectDetialMapper;
import com.huang.TibetanLibrary.mapper.InterpretationMapper;
import com.huang.TibetanLibrary.mapper.PronunciationMapper;
import com.huang.TibetanLibrary.mapper.SyllableClusterMapper;
import com.huang.TibetanLibrary.mapper.SyllableTibetMapper;
import com.huang.TibetanLibrary.mapper.TibetanTranslateEntryMapper;
import com.huang.TibetanLibrary.util.CentralUtil;

@Service
public class TibetanLibraryService {
	
	@Autowired
	private TibetanTranslateEntryMapper tibetanTranslateEntryMapper;
	
	@Autowired
	private InterpretationMapper interpretationMapper;
	
	@Autowired
	private PronunciationMapper pronunciationMapper;
	
	@Autowired
	private DialectDetialMapper dialectDetialMapper;
	
	@Autowired
	private SyllableClusterMapper syllableClusterMapper;
	
	@Autowired
	private SyllableTibetMapper SyllableTibetMapper;
	
	public TibetanTranslateEntry getTibetanTranslateEntry(String searchWord){
		
		ArrayList<Interpretation> interpretationList= interpretationMapper.findInterpretationEntityLike(searchWord);
		ArrayList<TibetanTranslateEntry> tibetanTranslateEntryList= tibetanTranslateEntryMapper.findTibetanTranslateEntryLike(searchWord);
		ArrayList<TibetanTranslateEntry> resultList = new ArrayList<TibetanTranslateEntry>();
		
		for(int i = 0; i < interpretationList.size(); i++){
			long id = interpretationList.get(i).getRID();
			TibetanTranslateEntry tmp = tibetanTranslateEntryMapper.findTibetanTranslateEntryById(id);
			resultList.add(getTibetanTranslateEntryByRID(id, tmp));
		}
		
		for(int i = 0; i < tibetanTranslateEntryList.size(); i++){
			long id = tibetanTranslateEntryList.get(i).getID();
			resultList.add(getTibetanTranslateEntryByRID(id, tibetanTranslateEntryList.get(i)));
		}
		
		TibetanTranslateEntry result = resultList.size()!=0?resultList.get(0):new TibetanTranslateEntry();
		
		return result;
	}
	
	public TibetanTranslateEntry getTibetanTranslateEntryByRID(long RID, TibetanTranslateEntry tmp){
		
		TibetanTranslateEntry result = tmp;
		
		ArrayList<Interpretation> tibetanInterpretationList= interpretationMapper.findTibetanInterpretationEntityByRId(RID);
		ArrayList<Interpretation> ChineseInterpretationList= interpretationMapper.findChineseInterpretationEntityByRId(RID);
		
		ArrayList<Pronunciation> pronunciationList= pronunciationMapper.findPronunciationEntitysByRId(RID);
		result.setLanguagePoinPronunciation(pronunciationList);
		
		for(Interpretation entry : tibetanInterpretationList){
			if(!(entry.getInterpretation().length()==0&&entry.getInterpretationExample().length()==0)){
				result.getTibetanInterpretation().add(entry);
			}
		}
		
		for(Interpretation entry : ChineseInterpretationList){
			if(!(entry.getInterpretation().length()==0&&entry.getInterpretationExample().length()==0)){
				result.getChineseInterpretation().add(entry);
			}
		}
		
		return result;
	}
	
	public void readTibetanLibraryXlsxFile(String path){
		
		try(
			InputStream is = new FileInputStream(path);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		){
			
			for(int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++){
				 XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				 
				 for(int numRow = 1; numRow < xssfSheet.getLastRowNum(); numRow++){
					 XSSFRow xssfRow = xssfSheet.getRow(numRow);
					 if (xssfRow != null) {
						 TibetanTranslateEntry tmpEntry = new TibetanTranslateEntry();
						 if(xssfRow.getCell(1) != null){
							 tmpEntry.setRepresentationText(xssfRow.getCell(1).toString());
						 }else{
							 tmpEntry.setRepresentationText("");
						 }
						 
						 if(xssfRow.getCell(2) != null){
							 tmpEntry.setTranscriptionText(xssfRow.getCell(2).toString());
						 }else{
							 tmpEntry.setTranscriptionText("");
						 }
						 tibetanTranslateEntryMapper.insertTibetanTranslateEntry(tmpEntry);
						 long RID = tmpEntry.getID();
						 
						 for(int ipNum = 3; ipNum < 42; ipNum += 2){
							 Interpretation tmpInterpretation= new Interpretation();
							 tmpInterpretation.setRID(RID);
							 
							 if(ipNum < 22){
								 tmpInterpretation.setLanguageCode("TI");
								 tmpInterpretation.setLanguageDes("Tibetan");
							 }else{
								 tmpInterpretation.setLanguageCode("ZH");
								 tmpInterpretation.setLanguageDes("Chinese");
							 }
							 
							 if(xssfRow.getCell(ipNum) != null){
								 tmpInterpretation.setInterpretation(xssfRow.getCell(ipNum).toString());
							 }else{
								 tmpInterpretation.setInterpretation("");
							 }
							 
							 int exampleNum = ipNum+1;
							 if(xssfRow.getCell(exampleNum) != null){
								 tmpInterpretation.setInterpretationExample(xssfRow.getCell(exampleNum).toString());
							 }else{
								 tmpInterpretation.setInterpretationExample("");
							 }
							 
							 interpretationMapper.insertInterpretationSingle(tmpInterpretation);
						 }
						 int prNum = 43;
						 int ipaNum = 46;
						 for(int i = 0; i < 3; i++){
							 Pronunciation tmpPronunciation = new Pronunciation();
							 
							 tmpPronunciation.setRID(RID);
							 tmpPronunciation.setLanguagePointCode(CentralUtil.LANGUAGEPOINTLIST.get(i).getLanguageCode());
							 tmpPronunciation.setLanguagePointDes(CentralUtil.LANGUAGEPOINTLIST.get(i).getLanguageDes());
							 
							 if(xssfRow.getCell(prNum) != null){
								 tmpPronunciation.setPronunciation(xssfRow.getCell(prNum).toString());
							 }else{
								 tmpPronunciation.setPronunciation("");
							 }
							 
							 if(xssfRow.getCell(ipaNum) != null){
								 tmpPronunciation.setPronunciationIPA(xssfRow.getCell(ipaNum).toString());
							 }else{
								 tmpPronunciation.setPronunciationIPA("");
							 }
							 
							 pronunciationMapper.insertPronunciationSingle(tmpPronunciation);
							 
							 prNum++;
							 ipaNum++;
						 }
					 }
				 }
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	public static void readSyllableClusterXlsxFile(String absolutePath, String locationCode, String locationDes) {
	public ArrayList<DialectDetial> readSyllableClusterXlsxFile(String absolutePath, String locationCode, String locationDes) {
		try(
				InputStream is = new FileInputStream(absolutePath);
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			){
				long currentTimeMillis = System.currentTimeMillis();
				DialectDetial dialectDetial= new DialectDetial();
				
				dialectDetial.setDdtimestamp(currentTimeMillis);
				dialectDetial.setLocationCode(locationCode);
				dialectDetial.setLocationDes(locationDes);
				
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
								 ArrayList<String> wlStringList= new ArrayList<String>();
								 String returnStr = "";
								 
								 for (int i = 0; i < orginalChars.length; i++) {
									 if(String.valueOf(orginalChars[i]).equals("་")||String.valueOf(orginalChars[i]).equals("།")){
										 wlStringList.add(returnStr);
										 returnStr = "";
										 syllablesCount++;
									 }else{
										 returnStr += orginalChars[i];
									 }
								 }
								 if(returnStr != ""){
									 syllablesCount++;
									 wlStringList.add(returnStr);
								 }
								 
								 String wiStr = "";
								 if(wlStringList.size() == transcriptionChars.length){
									 for(int i = 0; i<wlStringList.size() ;i++){
										 TibetanWordStructure tmpTWStructure = new TibetanWordStructure(wlStringList.get(i));
										 wiStr += tmpTWStructure.getWillieTransfer() + " ";
										 
										 SyllableTibet tmp = new SyllableTibet();
										 tmp.setDID(DID);
										 tmp.setWltranscriptionText(tmpTWStructure.getWillieTransfer());
										 tmp.setTranscriptionText(transcriptionChars[i]);
										 tmp.setRepresentationText(tmpTWStructure.getRepresentationText());
										 tmp.setTranslationText(tmpSyllableCluster.getRepresentationText()); 
										 
										 syllableTibetList.add(tmp);
									 }
								 }
								 
								 tmpSyllableCluster.setWltranscriptionText(wiStr);
							 }else{
								 tmpSyllableCluster.setWltranscriptionText("");
							 }
							 if(xssfRow.getCell(4) != null){tmpSyllableCluster.setTranscriptionText(xssfRow.getCell(4).toString());}else{tmpSyllableCluster.setTranscriptionText("");}							 
							 
							 if(xssfRow.getCell(6) != null){tmpSyllableCluster.setPronunciationText(xssfRow.getCell(6).toString());}else{tmpSyllableCluster.setTranscriptionText("");}							 
							 if(xssfRow.getCell(7) != null){tmpSyllableCluster.setVideoText(xssfRow.getCell(7).toString());}else{tmpSyllableCluster.setTranscriptionText("");}							 
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
									 SyllableTibetMapper.insertSingleSyllableTibet(tmp);
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
//		readSyllableClusterXlsxFile("C:\\upload\\tianjun.xlsx", "1", "3");
	}


}
