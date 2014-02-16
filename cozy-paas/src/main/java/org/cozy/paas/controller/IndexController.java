package org.cozy.paas.controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public void login(String name, String password,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		UserDB user = null;
		if ((user = userServiceImpl.vertify(name, password)) != null) {
			request.getSession().setAttribute("name", user.getName());
			request.getSession().setAttribute("id", user.getId());
		}
		response.sendRedirect("index");
	}

	@RequestMapping("/signup")
	public String signup() {
		return "signup";
	}

	@RequestMapping("/signupdo")
	public void signupdo(UserDB user, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (userServiceImpl.selectByName(user.getName()) == null) {
			userServiceImpl.insert(user);
			request.getSession().setAttribute("name", user.getName());
			request.getSession().setAttribute("id", user.getId());
		}
		response.sendRedirect("index");
	}

	@RequestMapping("/logout")
	public void loginout(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("id");
		request.getSession().removeAttribute("name");
		response.sendRedirect("login");
	}
}
