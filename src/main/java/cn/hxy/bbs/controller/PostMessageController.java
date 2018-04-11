package cn.hxy.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hxy.bbs.dto.SectionSize;
import cn.hxy.bbs.model.Post;
import cn.hxy.bbs.model.Reply;
import cn.hxy.bbs.service.impl.PostServiceImpl;
import cn.hxy.bbs.service.impl.ReplyServiceImpl;
import cn.hxy.bbs.service.impl.SectionServiceImpl;

@Controller
public class PostMessageController {
	
	@Autowired
	private PostServiceImpl postService;
	
	@Autowired
	private ReplyServiceImpl replyService;
	
	@Autowired
	private SectionServiceImpl sectionServicce;
	
	@PostMapping("get/sections")
	@ResponseBody
	public List<SectionSize> getSections(){
		return sectionServicce.findAllSection();
	}
	
	@PostMapping("post/post")
	@ResponseBody
	public String post(Post post,Reply reply,HttpSession session){
		postService.addPost(post, session);
		if(post.getId()>0){
			replyService.addReply(reply, post.getId(), session);
		}else{
			return "FAIL";
		}
		return "SUC";
	}
	
	@PostMapping("post/reply")
	@ResponseBody
	public String reply(Reply reply,HttpSession session){
		return replyService.reply(reply, session);
	}
	
	@InitBinder("post")
	public void postBinder(WebDataBinder wenDataBinder){
		wenDataBinder.setFieldDefaultPrefix("post.");
	}
	@InitBinder("reply")
	public void replyBinder(WebDataBinder wenDataBinder){
		wenDataBinder.setFieldDefaultPrefix("reply.");
	}
}
