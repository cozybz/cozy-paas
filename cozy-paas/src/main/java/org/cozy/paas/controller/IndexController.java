package org.cozy.paas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	public String login(){
		return "login";
	}
}
