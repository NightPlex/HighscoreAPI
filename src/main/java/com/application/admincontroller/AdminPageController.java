package com.application.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller()
public class AdminPageController {

	@RequestMapping("/admin")
	public String adminPage() {
		return "admin";
	}
}
