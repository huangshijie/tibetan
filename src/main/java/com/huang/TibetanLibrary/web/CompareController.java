package com.huang.TibetanLibrary.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CompareController {
	
	@RequestMapping(value = "/getdiaComTiHTML",method = RequestMethod.GET)
	public String getdiaComTiHTML(Model model){
		return "diaComTiHTML";
	}
	
	@RequestMapping(value = "/getTiComdiaHTML",method = RequestMethod.GET)
	public String getTiComdiaHTML(Model model){
		return "tiComdiaHTML";
	}
	
	@RequestMapping(value = "/getDiaComdiaHTML",method = RequestMethod.GET)
	public String getDiaComdiaHTML(Model model){
		return "diaComdiaHTML";
	}
}
