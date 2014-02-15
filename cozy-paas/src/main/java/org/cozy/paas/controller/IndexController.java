package org.cozy.paas.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.cozy.paas.pojo.UserDB;
import org.cozy.paas.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@Resource
	private UserService userServiceImpl;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		if (request.getSession().getAttribute("id") == null)
			return "login";
		return "index";
	}

	@RequestMapping("/login")
	public String login(String name, String password, HttpServletRequest request) {
		UserDB user = null;
		if ((user = userServiceImpl.vertify(name, password)) != null) {
			request.getSession().setAttribute("name", user.getName());
			request.getSession().setAttribute("id", user.getId());
			return "index";
		}
		return "login";
	}

	@RequestMapping("signup")
	public String signup() {
		return "signup";
	}

	@RequestMapping("signupdo")
	public String signupdo(UserDB user, HttpServletRequest request) {
		if (userServiceImpl.selectByName(user.getName()) == null) {
			userServiceImpl.insert(user);
			request.getSession().setAttribute("name", user.getName());
			request.getSession().setAttribute("id", user.getId());
			return "index";
		}
		return "signup";
	}

	@RequestMapping("/logout")
	public String loginout(HttpServletRequest request) {
		request.getSession().removeAttribute("id");
		request.getSession().removeAttribute("name");
		return "login";
	}
}
