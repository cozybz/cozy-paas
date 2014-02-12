package org.cozy.paas.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.cozy.paas.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@Resource
	private UserService userServiceImpl;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		if (request.getSession().getAttribute("name") == null)
			return "login";
		return "index";
	}

	@RequestMapping("/login")
	public String login(String name, String password, HttpServletRequest request) {
		if (1 == userServiceImpl.vertify(name, password)) {
			request.getSession().setAttribute("name", name);
			return "index";
		}
		return "login";
	}

	@RequestMapping("/logout")
	public String loginout(HttpServletRequest request) {
		request.getSession().removeAttribute("name");
		return "login";
	}
}
