package com.huang.TibetanLibrary.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huang.TibetanLibrary.domain.DialectDetial;
import com.huang.TibetanLibrary.domain.SyllableTibet;
import com.huang.TibetanLibrary.service.CompareService;

@Controller
public class CompareController {
	
	@Autowired
	CompareService compareService;
	
	@RequestMapping(value = "/getDiaTiComTiHTML",method = RequestMethod.GET)
	public String getdiaComTiHTML(@RequestParam String comparetype, Model model){
		model.addAttribute("comparetype", comparetype);
		model.addAttribute("dialectsList", compareService.getAllDialectsList());
		return "diaTiComHTML";
	}
	
	@RequestMapping(value = "/getdiaCompdiaDetialHTML",method = RequestMethod.GET)
	public String getdiaCompdiaDetialHTML(Model model){
		return "diaCompDiaDetial";
	}
	
	@RequestMapping(value = "/getQueryListHTML",method = RequestMethod.GET)
	public String getQueryListHTML(@RequestParam String did,@RequestParam String comparetype,
			@RequestParam String compareentity,@RequestParam String dlocation,Model model){
		model.addAttribute("did", did);
		model.addAttribute("dlocation", dlocation);
		model.addAttribute("comparetype", comparetype);
		model.addAttribute("compareentity", compareentity);
		if(compareentity.equals("onSet")){
			return "onSetQueryListHTML";
		}
		if(compareentity.equals("final")){
			return "finalQueryListHTML";
		}
		if(compareentity.equals("onSetWille")){
			return "onSetWilleQueryListHTML";
		}
		if(compareentity.equals("finalWille")){
			return "finalWilleQueryListHTML";
		}
		return "diaComdiaHTML";
	}
	
	@RequestMapping(value = "/getDiaComdiaHTML",method = RequestMethod.GET)
	public String getDiaComdiaHTML(Model model){
		model.addAttribute("dialectsList", compareService.getAllDialectsList());
		return "diaComdiaHTML";
	}
	
	@RequestMapping(value = "/getDiaTiComTiTable",method = RequestMethod.GET)
	public String getDiaTiComTiDetial(@RequestParam String did, @RequestParam String comparetype, @RequestParam String compareentity, 
			@RequestParam ArrayList<String> queryStrList, HttpServletRequest request, HttpServletResponse response, Model model){
		ArrayList<SyllableTibet> result = compareService.getSpecialSyllableTibetByDidByType(did,comparetype, compareentity, queryStrList);
		model.addAttribute("compDetialList", result);
		model.addAttribute("did", did);
		model.addAttribute("comparetype", comparetype);
		model.addAttribute("compareentity", compareentity);
		return "compDiaTiDetial";
	}
	
	@RequestMapping(value = "/uploadLocalClusterFile",method = RequestMethod.POST)
	@ResponseBody
	public String uploadLocalFile(@RequestParam("file") MultipartFile file, @RequestParam String locationDes, 
			@RequestParam String comparetype, Model model,
			HttpServletRequest request, HttpServletResponse response){
		
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
                
                DialectDetial uploadDialectDetial = compareService.uploadLocalDialect(uploadFile.getAbsolutePath(), locationDes);
        		model.addAttribute("comparetype", comparetype);
        		model.addAttribute("did", uploadDialectDetial.getID());
        		model.addAttribute("dlocation", uploadDialectDetial.getLanguagePoint());
        		model.addAttribute("dialectsList", compareService.getAllDialectsList());
        		return "diaTiComHTML";
            }catch (Exception e){
            	return "diaTiComHTML";
            }
		}else{
			return "diaTiComHTML";
		}
	}
	
	@RequestMapping(value = "/uploadLocalClusterFileDia",method = RequestMethod.POST)
	@ResponseBody
	public String uploadLocalFileDia(@RequestParam("file") MultipartFile file, @RequestParam String locationDes,
			Model model, HttpServletRequest request, HttpServletResponse response){
		JSONObject result = new JSONObject();
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
                
                DialectDetial uploadDialectDetial = compareService.uploadLocalDialect(uploadFile.getAbsolutePath(), locationDes);
                result.put("did", uploadDialectDetial.getID());
                result.put("dlocation", uploadDialectDetial.getLanguagePoint());
                result.put("result", "Seccuss");
            }catch (Exception e){
            	model.addAttribute("result", e);
            	e.printStackTrace();
            }
		}else{
			
			result.put("result", "File is Empty!");
		}
		return result.toString();
	}
}
