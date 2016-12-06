package com.huang.TibetanLibrary.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huang.TibetanLibrary.domain.Interpretation;
import com.huang.TibetanLibrary.domain.Pronunciation;
import com.huang.TibetanLibrary.domain.TibetanTranslateEntry;
import com.huang.TibetanLibrary.mapper.InterpretationMapper;
import com.huang.TibetanLibrary.mapper.PronunciationMapper;
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
	
	public TibetanTranslateEntry getTibetanTranslateEntry(String searchWord){
		TibetanTranslateEntry result = new TibetanTranslateEntry();
		System.out.println(interpretationMapper.findInterpretationEntityLike(searchWord)!=null);
		System.out.println(tibetanTranslateEntryMapper.findTibetanTranslateEntryLike(searchWord)!=null);
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
								 tmpInterpretation.setInterpretation("");
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
	
	public TibetanTranslateEntry searchTibetanTranslateEntry(String searchContext, boolean searchTibetan){
		TibetanTranslateEntry result = new TibetanTranslateEntry();
		
		return result;
	}
	
	public static void main(String[] args){
//		readTibetanLibraryXlsxFile("C:\\upload\\夏河甘加地区.xlsx");
	}
}
