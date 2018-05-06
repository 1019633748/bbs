package cn.hxy.bbs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hxy.bbs.model.User;
import cn.hxy.bbs.realm.UserRealm;
import cn.hxy.bbs.service.impl.TopServiceImpl;

@Controller
public class TopController {
	@Autowired
	private TopServiceImpl topService;

	// ¶¥²È
	@PostMapping("put/top/{replyId}")
	@ResponseBody
	public int clickTop(@PathVariable("replyId") int replyId, HttpSession session) {
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		return topService.clickTop(user.getId(), replyId);
	}

	@PostMapping("put/down/{replyId}")
	@ResponseBody
	public int clickDown(@PathVariable("replyId") int replyId, HttpSession session) {
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		return topService.clickDown(user.getId(), replyId);
	}
}
