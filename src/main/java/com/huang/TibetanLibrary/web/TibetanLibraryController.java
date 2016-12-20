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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huang.TibetanLibrary.domain.DialectDetial;
import com.huang.TibetanLibrary.service.TibetanLibraryService;

@Controller
public class TibetanLibraryController {
	
	@Autowired
	TibetanLibraryService tibetanService;
	
	@RequestMapping(value = "/searchHTML",method = RequestMethod.GET)
	public String getSearchHTML(Model model){
		return "search";
	}
	
	@RequestMapping(value = "/uploadFileHTML",method = RequestMethod.GET)
	public String getUploadFileHTML(Model model){
		return "uploadFile";
	}
	
	@RequestMapping(value = "/uploadSyllableClusterFileHTML",method = RequestMethod.GET)
	public String getuploadSyllableClusterFileHTML(Model model){
		ArrayList<DialectDetial> dialectDetialList= new ArrayList<DialectDetial>();
		model.addAttribute("dialectDetialList", dialectDetialList);
		return "uploadSyllableClusterFile";
	}
	
	@RequestMapping(value = "/libraryintroduceHTML",method = RequestMethod.GET)
	public String getlibraryintroduceHTML(Model model){
		return "libraryintroduce";
	}
	
	@RequestMapping(value = "/tibetansearchdetial",method = RequestMethod.POST)
	public String getTibetanSearchDetial(@RequestParam String searchWord, Model model){
		model.addAttribute("data", tibetanService.getTibetanTranslateEntry(searchWord));
		return "tibetansearchdetial";
	}
	
	@RequestMapping(value = "/Chinesesearchdetial",method = RequestMethod.POST)
	public String getChineseSearchDetial(@RequestParam String searchWord, Model model){
		model.addAttribute("data", tibetanService.getTibetanTranslateEntry(searchWord));
		model.addAttribute("Chinenseword", searchWord);
		return "Chinesesearchdetial";
	}

	@RequestMapping(value = "/uploadSyllableClusterFile",method = RequestMethod.POST)
	public String getuploadSyllableClusterFile(@RequestParam("file") MultipartFile file, @RequestParam String locationCode, 
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
                tibetanService.readSyllableClusterXlsxFile(uploadFile.getAbsolutePath(), locationCode, locationDes);
                
                model.addAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + " into " + file.getOriginalFilename() + "-uploaded !");  
                return "uploadSyllableClusterFile";
            } catch (Exception e) {  
                model.addAttribute("message", "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage());  
                return "uploadSyllableClusterFile";
            }  
        } else {  
            model.addAttribute("message", "You failed to upload " + file.getOriginalFilename() + " because the file was empty.");  
            return "uploadSyllableClusterFile";
        } 
	}
	
	@RequestMapping(value = "/uploadTibetanLibraryFile",method = RequestMethod.POST)
	@ResponseBody 
	public String uploadTibetanLibraryFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response){
		
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
                tibetanService.readTibetanLibraryXlsxFile(uploadFile.getAbsolutePath());
                
                return "You successfully uploaded " + file.getOriginalFilename() + " into " + file.getOriginalFilename() + "-uploaded !";  
            } catch (Exception e) {  
                return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();   
            }  
        } else {  
            return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";  
        }  
    }  
}
