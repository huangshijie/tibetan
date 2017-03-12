package com.huang.TibetanLibrary.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.huang.TibetanLibrary.domain.DialectDetial;
import com.huang.TibetanLibrary.service.DialectService;

@Controller
public class DialectController {

	@Autowired
	DialectService dialectService;
	
	@RequestMapping(value = "/getCurrentDialectsTable",method = RequestMethod.GET)
	public String getDialectsTableHTML(Model model){
		model.addAttribute("dialectRoughlyList" , dialectService.getAllDialectDetialsList());
        return "dialectstable";
	}
	
	@RequestMapping(value = "/uploadSyllableClusterFileHTML",method = RequestMethod.GET)
	public String getuploadSyllableClusterFileHTML(Model model){
		ArrayList<DialectDetial> dialectDetialList= new ArrayList<DialectDetial>();
		model.addAttribute("dialectDetialList", dialectDetialList);
		return "uploadSyllableClusterFile";
	}
	
	@RequestMapping(value = "/uploadSyllableClusterFile",method = RequestMethod.POST)
	public String getuploadSyllableClusterFile(@RequestParam("file") MultipartFile file,
			@RequestParam String locationDes, Model model,HttpServletRequest request, HttpServletResponse response){
		
		if (!file.isEmpty()) {  
            try {  
            	request.setCharacterEncoding("UTF-8");

                byte[] bytes = file.getBytes();  
                File fileSourcePath = new File("C:/upload"); 
                if (!fileSourcePath.exists()) {
                    fileSourcePath.mkdirs();
                }
                
                File uploadFile = new File(fileSourcePath, file.getOriginalFilename());
                
                BufferedOutputStream stream =  new BufferedOutputStream(new FileOutputStream(uploadFile));  
                stream.write(bytes);  
                stream.close();  
                
                model.addAttribute("dialectRoughlyList" , dialectService.readSyllableClusterXlsxFile(uploadFile.getAbsolutePath(), locationDes));
                return "dialectstable";
            } catch (Exception e) {  
                model.addAttribute("message", "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage());  
                return "dialectstable";
            }  
        } else {  
            model.addAttribute("message", "You failed to upload " + file.getOriginalFilename() + " because the file was empty.");  
            return "dialectstable";
        } 
	}
	
	@RequestMapping(value = "/getDialectDetialByid",method = RequestMethod.GET)
	public String getDialectDetialByid(@RequestParam String id, HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("dialectDetialList",dialectService.getSyllableClusterListByid(id));
		model.addAttribute("id",id);
		return "dialectsdetial"; 
    } 
	
	@RequestMapping(value = "/getDialectDetialDrawedBydid",method = RequestMethod.GET)
	public String getDialectDetialDrawedBydid(@RequestParam String id, @RequestParam String drawtype, 
			HttpServletRequest request, HttpServletResponse response, Model model){
		model.addAttribute("dialectDetialDrawedList",dialectService.getSyllableTibetDrawedListByid(id));
		model.addAttribute("id", id);
		model.addAttribute("drawtype", drawtype);
//		return drawtype; 
		return "drawdialectsdetial";
    } 
}
