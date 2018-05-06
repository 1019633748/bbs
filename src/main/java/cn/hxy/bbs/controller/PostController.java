package cn.hxy.bbs.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.dto.PostDetail;
import cn.hxy.bbs.dto.ReplyDetail;
import cn.hxy.bbs.dto.TableData;
import cn.hxy.bbs.log.adminLog;
import cn.hxy.bbs.model.Post;
import cn.hxy.bbs.model.Reply;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.realm.UserRealm;
import cn.hxy.bbs.service.impl.PostServiceImpl;
import cn.hxy.bbs.service.impl.ReplyServiceImpl;
import cn.hxy.bbs.service.impl.SectionServiceImpl;
import cn.hxy.bbs.service.impl.SensitiveServiceImpl;
import cn.hxy.bbs.service.impl.TopServiceImpl;

@Controller
public class PostController {
	@Autowired
	private PostServiceImpl postService;
	@Autowired
	private ReplyServiceImpl replyService;
	@Autowired
	private SensitiveServiceImpl sensitiveService;
	@Autowired
	private SectionServiceImpl sectionService;
	@Autowired
	private TopServiceImpl topService;

	//
	@GetMapping("get/posts/{id}")
	public String getPostsById(@PathVariable("id") int id, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "5") int pageSize, Model model, HttpSession session) {
		model.addAttribute("section", sectionService.getSectionByPostId(id));
		model.addAttribute("post", postService.getPostById(id));
		PageHelper.startPage(pageNum, pageSize);
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		List<ReplyDetail> replyDetails = replyService.getReplyDetailByPostId(id);
		if (user != null) {
			for (ReplyDetail replyDetail : replyDetails) {
				replyDetail.setTopOrDown(topService.getStatus(user.getId(), replyDetail.getId()) + "");
			}
		}
		model.addAttribute("reply", replyDetails);
		int total = replyService.getReplyDetailByPostId(id).size();
		model.addAttribute("total", total);
		model.addAttribute("totalPage", (int) Math.ceil((double) total / pageSize));
		model.addAttribute("pageNum", pageNum);
		return "post";
	}

	@GetMapping("get/advices/{id}")
	public String getAdviceById(@PathVariable("id") int id, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "5") int pageSize, Model model) {
		model.addAttribute("section", sectionService.getSectionByPostId(id));
		model.addAttribute("post", postService.getPostById(id));
		PageHelper.startPage(pageNum, pageSize);
		model.addAttribute("reply", replyService.getReplyDetailByPostId(id));
		int total = replyService.getReplyDetailByPostId(id).size();
		model.addAttribute("total", total);
		model.addAttribute("totalPage", (int) Math.ceil((double) total / pageSize));
		model.addAttribute("pageNum", pageNum);
		return "post";
	}

	// 发帖
	@PostMapping("post/post")
	@ResponseBody
	public String post(Post post, Reply reply, HttpSession session) {
		for (String word : sensitiveService.getAllWord()) {
			post.setPost(post.getPost().replace(word, "***"));
			reply.setReply(reply.getReply().replace(word, "***"));
		}
		postService.addPost(post, session);
		if (post.getId() > 0) {
			replyService.addReply(reply, post.getId(), session);
		} else {
			return "FAIL";
		}
		return "SUC";
	}

	// 管理帖子

	@PostMapping("get/admin/post")
	@ResponseBody
	public TableData<PostDetail> getPostdetail(int pageSize, int pageIndex, String param) {
		TableData<PostDetail> tableData = new TableData<PostDetail>();
		PageHelper.startPage(pageIndex, pageSize);
		tableData.setRows(postService.getAdminPostDetailByName(param));
		tableData.setTotal(postService.getAdminPostDetailByName(param).size());
		return tableData;
	}

	@adminLog
	@PostMapping("hide/posts/{id}")
	@ResponseBody
	public String hidePostById(@PathVariable("id") int id) {
		postService.hidePostById(id, 1);
		return null;
	}

	@adminLog
	@PostMapping("show/posts/{id}")
	@ResponseBody
	public String showPostById(@PathVariable("id") int id) {
		postService.hidePostById(id, 0);
		return null;
	}

	//
	@PostMapping("/get/posts/{userId}")
	@ResponseBody
	public TableData<PostDetail> getPostByUserId(@PathVariable("userId") int userId, int pageIndex, int pageSize,
			String param) {
		TableData<PostDetail> td = new TableData<PostDetail>();
		PageHelper.startPage(pageIndex, pageSize);
		td.setRows(postService.getPostByUserId(userId, param));
		td.setTotal(postService.getPostByUserId(userId, param).size());
		return td;
	}

	// chart
	@SuppressWarnings("rawtypes")
	@PostMapping("get/post/create")
	@ResponseBody
	public List<Map> getPostCreateDate() {
		return postService.getPostCreate();
	}

	@InitBinder("post")
	public void postBinder(WebDataBinder wenDataBinder) {
		wenDataBinder.setFieldDefaultPrefix("post.");
	}

	@InitBinder("reply")
	public void replyBinder(WebDataBinder wenDataBinder) {
		wenDataBinder.setFieldDefaultPrefix("reply.");
	}

	// 管理通知
	@GetMapping("get/advice")
	public String toAdvice() {
		return "post_advice";
	}

	@adminLog
	@PostMapping("post/advice")
	@ResponseBody
	public String postAdvice(Post post, Reply reply, HttpSession session) {
		postService.addAdviceTitle(post, session);
		if (post.getId() > 0) {
			replyService.addAdviceContent(reply, post.getId(), session);
		} else {
			return "FAIL";
		}
		return "SUC";
	}

	@PostMapping("get/admin/advice")
	@ResponseBody
	public TableData<ReplyDetail> getAdvice(int pageSize, int pageIndex, String param) {
		TableData<ReplyDetail> td = new TableData<ReplyDetail>();
		PageHelper.startPage(pageIndex, pageSize);
		td.setRows(replyService.getAdviceByName(param));
		td.setTotal(replyService.getAdviceByName(param).size());
		return td;
	}

	@adminLog
	@PostMapping("hide/advices/{id}")
	@ResponseBody
	public String hideAdviceById(@PathVariable("id") int id) {
		replyService.hideReplyById(id, 8);
		return null;
	}

	@adminLog
	@PostMapping("show/advices/{id}")
	@ResponseBody
	public String showReplyById(@PathVariable("id") int id) {
		replyService.hideReplyById(id, 9);
		return null;
	}
}
