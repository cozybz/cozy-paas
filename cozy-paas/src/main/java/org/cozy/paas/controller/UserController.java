package org.cozy.paas.controller;

import javax.annotation.Resource;

import org.cozy.paas.pojo.UserDB;
import org.cozy.paas.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paas/user")
public class UserController {
	@Resource
	private UserService userServiceImpl;

	@RequestMapping("update")
	public int update(UserDB user) {
		return userServiceImpl.update(user);
	}
}
