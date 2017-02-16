package com.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*Main application controller*/

@Controller
public class MainController {

	//Returns the main index, all implementation is done with JQuery
	@RequestMapping("/")
	public String client() {
		return "index";
	}
	

}
