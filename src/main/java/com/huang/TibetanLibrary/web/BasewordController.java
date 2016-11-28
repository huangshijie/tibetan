package com.huang.TibetanLibrary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huang.TibetanLibrary.domain.SyllableStructure;
import com.huang.TibetanLibrary.service.BasewordService;

@Controller
public class BasewordController {
	
	@Autowired
	BasewordService basewordSerice;
	
	@RequestMapping(value = "/getBaseWord", method = {RequestMethod.POST})
	public String getBaseWord(@RequestParam String originalWord, Model model){
		SyllableStructure s = basewordSerice.getWordSplit(originalWord);
		model.addAttribute("data", s);
		return "/index";
	}
	
	@RequestMapping(value = "/indexHTML",method = RequestMethod.GET)
	public String getIndexHTML(Model model){
		return "/index";
	}
	
	@RequestMapping(value = "/searchdetial",method = RequestMethod.GET)
	public String getSearchDetial(Model model){
		return "/searchdetial";
	}
	
}
