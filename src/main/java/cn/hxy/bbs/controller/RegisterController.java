package cn.hxy.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hxy.bbs.model.User;
import cn.hxy.bbs.service.impl.UserServiceImpl;

@Controller
public class RegisterController {
	@Autowired
	private UserServiceImpl userService;

	@PostMapping("register")
	public String register(User user, Model model) {
		userService.register(user);
		model.addAttribute("newUser", user.getNickname() + ",»¶Ó­");
		return "login";
	}

	@PostMapping("verify/name")
	@ResponseBody
	public int verifyName(String name) {
		return userService.verifyName(name);
	}

	@PostMapping("verify/email")
	@ResponseBody
	public int verifyEmail(String email) {
		return userService.getEmail(email);
	}

	@PostMapping("post/admin")
	public String addAdmin(User user) {
		userService.addAdmin(user);
		return "admin_user";
	}
}
