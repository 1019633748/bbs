package cn.hxy.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.service.impl.PostServiceImpl;
import cn.hxy.bbs.service.impl.ReplyServiceImpl;
import cn.hxy.bbs.service.impl.SectionServiceImpl;

@Controller
public class SectionController {
	@Autowired
	private PostServiceImpl postService;
	
	@Autowired
	private SectionServiceImpl sectionService;
	
	@Autowired
	private ReplyServiceImpl replyService;
	
	@GetMapping("get/posts/{id}")
	public String getPostsById(@PathVariable("id")int id,@RequestParam(defaultValue="1")int pageNum,@RequestParam(defaultValue="5")int pageSize,Model model){
		model.addAttribute("section",sectionService.getSectionByPostId(id));
		model.addAttribute("post", postService.getPostById(id));
		PageHelper.startPage(pageNum, pageSize);
		model.addAttribute("reply", replyService.getReplyDetailByPostId(id));
		int total = replyService.getReplyDetailByPostId(id).size();
		model.addAttribute("total",total );
		model.addAttribute("totalPage",(int)Math.ceil((double)total/pageSize) );
		model.addAttribute("pageNum", pageNum);
		return "post";
	}
	
	@GetMapping("get/advices/{id}")
	public String getAdviceById(@PathVariable("id")int id,@RequestParam(defaultValue="1")int pageNum,@RequestParam(defaultValue="5")int pageSize,Model model){
		model.addAttribute("section",sectionService.getSectionByPostId(id));
		model.addAttribute("post", postService.getPostById(id));
		PageHelper.startPage(pageNum, pageSize);
		model.addAttribute("reply", replyService.getReplyDetailByPostId(id));
		int total = replyService.getReplyDetailByPostId(id).size();
		model.addAttribute("total",total );
		model.addAttribute("totalPage",(int)Math.ceil((double)total/pageSize) );
		model.addAttribute("pageNum", pageNum);
		return "post";
	}
	
	@GetMapping("get/posts/{id}/{replyId}")
	public String getPostsbyReplyId(@PathVariable("id")int id,@RequestParam(defaultValue="1")int pageNum,@RequestParam(defaultValue="5")int pageSize,@PathVariable("replyId")int replyId,Model model){
		model.addAttribute("section",sectionService.getSectionByPostId(id));
		model.addAttribute("post", postService.getPostById(id));
		int pageIndex = (int) Math.ceil((double)replyService.getFollor(id, replyId)/pageSize);
		PageHelper.startPage(pageIndex, pageSize);
		model.addAttribute("reply", replyService.getReplyDetailByPostId(id));
		int total = replyService.getReplyDetailByPostId(id).size();
		model.addAttribute("total",total );
		model.addAttribute("totalPage",(int)Math.ceil((double)total/pageSize) );
		model.addAttribute("pageNum", pageIndex);
		return "post";
	}

}
