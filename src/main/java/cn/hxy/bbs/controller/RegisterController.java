package cn.hxy.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hxy.bbs.model.User;
import cn.hxy.bbs.service.impl.UserServiceImpl;

@Controller
public class RegisterController {
	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("register")
	public String register(User user){
		userService.register(user);
		return "login";
	}
	
	@PostMapping("verify/name")
	@ResponseBody
	public int verifyName(String name){
		return userService.verifyName(name);
	}
	
	@PostMapping("post/admin")
	public String addAdmin(User user){
		userService.addAdmin(user);
		return "admin_user";
	}
}
