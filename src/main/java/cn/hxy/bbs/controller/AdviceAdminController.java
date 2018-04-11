package cn.hxy.bbs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.dto.ReplyDetail;
import cn.hxy.bbs.dto.TableData;
import cn.hxy.bbs.model.Post;
import cn.hxy.bbs.model.Reply;
import cn.hxy.bbs.service.impl.PostServiceImpl;
import cn.hxy.bbs.service.impl.ReplyServiceImpl;

@Controller
public class AdviceAdminController {
	@Autowired
	private PostServiceImpl postService;
	
	@Autowired
	private ReplyServiceImpl replyService;
	

	
	
	
	@GetMapping("get/advice")
	public String toAdvice() {
		return "post_advice";
	}
	
	@PostMapping("post/advice")
	@ResponseBody
	public String post(Post post,Reply reply,HttpSession session){
		postService.addAdviceTitle(post, session);
		if(post.getId()>0){
			replyService.addAdviceContent(reply, post.getId(), session);
		}else{
			return "FAIL";
		}
		return "SUC";
	}
	
	@InitBinder("post")
	public void postBinder(WebDataBinder wenDataBinder){
		wenDataBinder.setFieldDefaultPrefix("post.");
	}
	@InitBinder("reply")
	public void replyBinder(WebDataBinder wenDataBinder){
		wenDataBinder.setFieldDefaultPrefix("reply.");
	}
	
	@PostMapping("get/admin/advice")
	@ResponseBody
	public TableData<ReplyDetail> getAdvice(int pageSize,int pageIndex,String param){
		TableData<ReplyDetail> td = new TableData<ReplyDetail>();
		PageHelper.startPage(pageIndex, pageSize);
		td.setRows(replyService.getAdviceByName(param));
		td.setTotal(replyService.getAdviceByName(param).size());
		return td;
	}
	
	@PostMapping("hide/advices/{id}")
	@ResponseBody
	public String hideReplyById(@PathVariable("id")int id){
		replyService.hideReplyById(id,8);
		return null;
	}
	@PostMapping("show/advices/{id}")
	@ResponseBody
	public String showReplyById(@PathVariable("id")int id){
		replyService.hideReplyById(id,9);
		return null;
	}
}
