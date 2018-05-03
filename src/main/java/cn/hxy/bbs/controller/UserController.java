package cn.hxy.bbs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hxy.bbs.service.impl.UserServiceImpl;

@Controller
public class UserController {
	@Autowired
	private UserServiceImpl userService;

	@PostMapping("post/nameoremail")
	@ResponseBody
	public String getNameOrEmail(String nameOrEmail,HttpSession session) {
		return userService.getCode(nameOrEmail, session);
	}
	
	@PostMapping("post/code")
	@ResponseBody
	public String verifyCode(String code,HttpSession session){
		return userService.verifyCode(code, session);
	}
	
	@PostMapping("post/password")
	@ResponseBody
	public String modityPassword(String password,HttpSession session){
		return userService.modifyPassword(password, session);
	}
}
