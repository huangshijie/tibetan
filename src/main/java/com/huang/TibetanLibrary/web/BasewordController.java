package com.huang.TibetanLibrary.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huang.TibetanLibrary.domain.TibetanWordStructure;
import com.huang.TibetanLibrary.service.BasewordService;

@Controller
public class BasewordController {
	
	@Autowired
	BasewordService basewordSerice;
	
	@RequestMapping(value = "/indexHTML",method = RequestMethod.GET)
	public String getIndexHTML(Model model){
		return "index";
	}
	
	@RequestMapping(value = "/transcriptionsHTML",method = RequestMethod.GET)
	public String getTranscriptions(Model model){
		TibetanWordStructure s = new TibetanWordStructure();
		model.addAttribute("data", s);
		return "transcriptions";
	}
	
	@RequestMapping(value = "/getBaseWord", method = {RequestMethod.POST})
	public String getBaseWord(@RequestParam String tibetanWord, @RequestParam String willeWord,Model model){
		TibetanWordStructure s = basewordSerice.getWordSplit(tibetanWord);
		model.addAttribute("data", s);
		return "transcriptions";
	}
}
