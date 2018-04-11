package cn.hxy.bbs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hxy.bbs.model.User;
import cn.hxy.bbs.realm.UserRealm;
import cn.hxy.bbs.service.impl.PostServiceImpl;
import cn.hxy.bbs.service.impl.TopServiceImpl;
import cn.hxy.bbs.service.impl.VerifyServiceImpl;

@Controller
public class PostController {
	@Autowired
	private PostServiceImpl postService;
	
	@Autowired
	private TopServiceImpl topService;
	
	@Autowired
	private VerifyServiceImpl verifyService;
	
	@PostMapping("put/top/{replyId}")
	@ResponseBody
	public int clickTop(@PathVariable("replyId")int replyId,HttpSession session) {
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		return topService.clickTop(user.getId(), replyId);
	}
	
	@PostMapping("put/down/{replyId}")
	@ResponseBody
	public int clickDown(@PathVariable("replyId")int replyId,HttpSession session) {
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		return topService.clickDown(user.getId(), replyId);
	}
	
	@GetMapping("/report/posts/{postId}")
	public String report(@PathVariable("postId")int postId,HttpSession session){
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		verifyService.reportPost(user.getId(), postId);
		return "redirect:/get/posts/"+postId;
	}
	
	@GetMapping("/report/replys/{replyId}/{postId}")
	public String report(@PathVariable("replyId")int replyId,@PathVariable("postId")int postId,HttpSession session){
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		verifyService.reportReply(user.getId(), replyId, postId);
		return "redirect:/get/posts/"+postId;
	}
}
