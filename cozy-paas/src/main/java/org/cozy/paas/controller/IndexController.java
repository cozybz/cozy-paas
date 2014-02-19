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

	@RequestMapping("/paas/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/signup")
	public String signup() {
		return "signup";
	}

	@RequestMapping("/logindo")
	public void logindo(String name, String password,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		UserDB user = null;
		if (name != "" && password != null
				&& (user = userServiceImpl.vertify(name, password)) != null) {
			request.getSession().setAttribute("name", user.getName());
			request.getSession().setAttribute("id", user.getId());
		}
		response.sendRedirect("/paas/index");
	}

	@RequestMapping("/signupdo")
	public void signupdo(UserDB user, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if (user != null && user.getName() != null
				&& user.getPassword() != null
				&& userServiceImpl.selectByName(user.getName()) == null) {
			userServiceImpl.insert(user);
			request.getSession().setAttribute("name", user.getName());
			request.getSession().setAttribute("id", user.getId());
		}
		response.sendRedirect("/paas/index");
	}

	@RequestMapping("/logout")
	public void loginout(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("id");
		request.getSession().removeAttribute("name");
		response.sendRedirect("login");
	}

	@RequestMapping("/paas/containerlist")
	public String containerlist() {
		return "containerlist";
	}

	@RequestMapping("/paas/hostlist")
	public String hostlist() {
		return "hostlist";
	}
}
