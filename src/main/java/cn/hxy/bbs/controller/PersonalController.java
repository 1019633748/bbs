package cn.hxy.bbs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.dto.PostDetail;
import cn.hxy.bbs.dto.ReplyDetail;
import cn.hxy.bbs.dto.TableData;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.realm.UserRealm;
import cn.hxy.bbs.service.impl.AttentionServiceImpl;
import cn.hxy.bbs.service.impl.PostServiceImpl;
import cn.hxy.bbs.service.impl.ReplyServiceImpl;
import cn.hxy.bbs.service.impl.UserServiceImpl;

@Controller
public class PersonalController {
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private AttentionServiceImpl attentionService;
	@Autowired
	private PostServiceImpl postService;
	@Autowired
	private ReplyServiceImpl replyService;

	@GetMapping("get/users/{userId}")
	public String toPersonlPage(@PathVariable("userId") int userId, Model model, HttpSession session) {
		User logined = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);		
		int currentUserId = logined==null?0:logined.getId();
		model.addAttribute("user", userService.getUserDetailByUserId(userId));
		model.addAttribute("isAttention", attentionService.isAttention(currentUserId, userId));
		return "personal";
	}

	@PostMapping("put/user")
	public String updateUser(User user, Model model, HttpSession session) {
		User logined = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		user.setId(logined.getId());
		userService.updateUser(user);
		model.addAttribute("user", userService.getUserDetailByUserId(user.getId()));
		return "personal";
	}
	
	@GetMapping("attention/users/{targetUserId}")
	public String attention(@PathVariable("targetUserId")int targetUserId,Model model,HttpSession session){
		User logined = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		attentionService.attention(logined.getId(), targetUserId);
		model.addAttribute("user", userService.getUserDetailByUserId(targetUserId));
		model.addAttribute("isAttention", attentionService.isAttention(logined.getId(), targetUserId));
		return "personal";
	}
	
	@GetMapping("unfollow/users/{targetUserId}")
	public String unfollow(@PathVariable("targetUserId")int targetUserId,Model model,HttpSession session){
		User logined = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		attentionService.unfollow(logined.getId(), targetUserId);
		model.addAttribute("user", userService.getUserDetailByUserId(targetUserId));
		model.addAttribute("isAttention", attentionService.isAttention(logined.getId(), targetUserId));
		return "personal";
	}
	
	@PostMapping("/get/posts/{userId}")
	@ResponseBody
	public TableData<PostDetail> getPostByUserId(@PathVariable("userId")int userId,int pageIndex,int pageSize,String param){
		TableData<PostDetail> td = new TableData<PostDetail>();
		PageHelper.startPage(pageIndex, pageSize);
		td.setRows(postService.getPostByUserId(userId, param));
		td.setTotal(postService.getPostByUserId(userId, param).size());
		return td;
	}
	
	@PostMapping("/get/replys/{userId}")
	@ResponseBody
	public TableData<ReplyDetail> getReplyByUserId(@PathVariable("userId")int userId,int pageIndex,int pageSize,String param){
		TableData<ReplyDetail> td = new TableData<ReplyDetail>();
		PageHelper.startPage(pageIndex, pageSize);
		td.setRows(replyService.getReplyByUserId(userId, param));
		td.setTotal(replyService.getReplyByUserId(userId, param).size());
		return td;
	}
	
	
	public String getUserList(Model model){
		model.addAttribute("userList", "取list的方法");
	return null;
	}
}
