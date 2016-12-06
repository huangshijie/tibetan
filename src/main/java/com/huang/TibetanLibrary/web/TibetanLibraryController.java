package com.huang.TibetanLibrary.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huang.TibetanLibrary.service.TibetanLibraryService;

@Controller
public class TibetanLibraryController {
	
	@Autowired
	TibetanLibraryService tibeService;
	
	@RequestMapping(value = "/uploadFileHTML",method = RequestMethod.GET)
	public String getUploadFileHTML(Model model){
		return "uploadFile";
	}
	
	@RequestMapping(value = "/searchdetialHTML",method = RequestMethod.GET)
	public String getSearchDetialHTML(){
		
		return "searchdetial";
	}
	
	@RequestMapping(value = "/searchdetial",method = RequestMethod.POST)
	public String getSearchDetial(@RequestParam String searchWord, Model model){
		model.addAttribute("data", tibeService.getTibetanTranslateEntry(searchWord));
		return "searchdetial";
	}
	
	@RequestMapping(value = "/uploadTibetanLibraryFile",method = RequestMethod.POST)
	@ResponseBody 
	public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response){
		
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
                tibeService.readTibetanLibraryXlsxFile(uploadFile.getAbsolutePath());
                
                return "You successfully uploaded " + file.getOriginalFilename() + " into " + file.getOriginalFilename() + "-uploaded !";  
            } catch (Exception e) {  
                return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();   
            }  
        } else {  
            return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";  
        }  
    }  
}
