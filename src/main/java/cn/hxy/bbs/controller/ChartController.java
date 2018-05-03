package cn.hxy.bbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hxy.bbs.service.impl.PostServiceImpl;
import cn.hxy.bbs.service.impl.ReplyServiceImpl;
import cn.hxy.bbs.service.impl.UserServiceImpl;

@Controller
public class ChartController {
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private PostServiceImpl postService;
	
	@Autowired
	private ReplyServiceImpl replyService;
	
	@PostMapping("get/malefemale")
	@ResponseBody
	public List<Map> getMaleFemale(){
		return userService.getMaleFemaleAmount();
	}
	
	@PostMapping("get/user/create")
	@ResponseBody
	public List<Map> getCreateDate(){
		return userService.getCreateDate();
	}
	
	@PostMapping("get/post/create")
	@ResponseBody
	public List<Map> getPostCreateDate(){
		return postService.getPostCreate();
	}
	
	@PostMapping("get/reply/create")
	@ResponseBody
	public List<Map> getReplyCreateDate(){
		return replyService.getReplyCreate();
	}
}
