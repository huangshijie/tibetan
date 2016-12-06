package com.huang.TibetanLibrary.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	public static void readXlsxFile(String path){
		
		try(
			InputStream is = new FileInputStream(path);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		){
			
			for(int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++){
				 XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				 
				 for(int numRow = 0; numRow < xssfSheet.getLastRowNum(); numRow++){
					 XSSFRow xssfRow = xssfSheet.getRow(numRow);
					 if (xssfRow != null) {
						 if(xssfRow.getCell(0) != null&&xssfRow.getCell(1) != null){
							 System.out.print("WILLIESET.put(\""+xssfRow.getCell(0).toString());
							 System.out.println(xssfRow.getCell(1).toString()+"\");");
						 }
						 
						 
					 }
				 }
				 for(int numRow = 0; numRow < xssfSheet.getLastRowNum(); numRow++){
					 XSSFRow xssfRow = xssfSheet.getRow(numRow);
					 if (xssfRow != null) {
						if(xssfRow.getCell(0) != null&&xssfRow.getCell(1) != null){
							System.out.println("EXHAUSTIVESET.add(\""+xssfRow.getCell(0).toString()+"\");");
						}
					 }
				 }
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void readWilleXlsxFile(String path){
		
		try(
			InputStream is = new FileInputStream(path);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		){
			
			for(int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++){
				 XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
				 
				 for(int numRow = 0; numRow <= xssfSheet.getLastRowNum(); numRow++){
					 XSSFRow xssfRow = xssfSheet.getRow(numRow);
					 if (xssfRow != null) {
						 if(xssfRow.getCell(0) != null&&xssfRow.getCell(1) != null){
							 if(xssfRow.getCell(0).toString().length()<=1){
								 char[] charList = xssfRow.getCell(0).toString().toCharArray();
								 System.out.println("WILLIESET.put(\"0"+ Integer.toHexString(charList[0]) +"\", \""+ xssfRow.getCell(1).toString() +"\");");
							 }else{
								 System.out.println("WILLIESET.put(\""+ xssfRow.getCell(0).toString().toLowerCase() +"\", \""+ xssfRow.getCell(1).toString() +"\");");
							 }
						 }
					 }
				 }
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		readWilleXlsxFile("C:\\upload\\威利转写表格 (1).xlsx");
	}
	
}
