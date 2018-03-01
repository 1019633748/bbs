package cn.hxy.bbs.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.model.Content;
import cn.hxy.bbs.model.Title;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.realm.UserRealm;
import cn.hxy.bbs.service.impl.ContentServiceImpl;
import cn.hxy.bbs.service.impl.FriendServiceImpl;
import cn.hxy.bbs.service.impl.ImageServiceImpl;
import cn.hxy.bbs.service.impl.SectionServiceImpl;
import cn.hxy.bbs.service.impl.TitleServiceImpl;
import cn.hxy.bbs.service.impl.UserServiceImpl;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private ImageServiceImpl imageService;
	
	@Autowired
	private SectionServiceImpl sectionService;
	
	@Autowired
	private ContentServiceImpl contentService;
	
	@Autowired
	private TitleServiceImpl titleService;
	
	@Autowired
	private FriendServiceImpl friendService;

	@GetMapping("/get/user/{id}")
	public String getUserInfo(@PathVariable("id") int id, Model model,HttpSession session) {
		model.addAttribute("user", userService.getUserByID(id));
		model.addAttribute("uri", imageService.getAvatarByUserId(id));
		model.addAttribute("attention", friendService.getAttentionByUserId(id));
		model.addAttribute("fans", friendService.getFansByUserId(id));
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		model.addAttribute("isAttention",userService.isAttention(user.getId(),id ));
		return "user_info";
	}

	@GetMapping("/get/users/{name}")
	public String toUserInfoPage(@PathVariable("name")String name,Model model,HttpSession session){
		int id = userService.getUserIdByName(name);
		model.addAttribute("user", userService.getUserByID(id));
		model.addAttribute("uri", imageService.getAvatarByUserId(id));
		model.addAttribute("attention", friendService.getAttentionByUserId(id));
		model.addAttribute("fans", friendService.getFansByUserId(id));
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		model.addAttribute("isAttention",userService.isAttention(user.getId(),id ));
		return "user_info";
	}
	
	@PostMapping("post/friend/{id}")
	@ResponseBody
	public String addAttention(@PathVariable("id")int id,HttpSession session){
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		friendService.addAttention(user.getId(), id);
		return "SUC";
	}
	
	@PostMapping("delete/friend/{id}")
	@ResponseBody
	public String removeAttention(@PathVariable("id")int id,HttpSession session){
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		friendService.removeAttention(user.getId(), id);
		return "SUC";
	}
	
	
	@PostMapping("/get/content/{userId}/{pageNum}/{pageSize}")
	@ResponseBody
	public List<Content> getContentByUserId(@PathVariable("userId") int id, @PathVariable("pageNum") int pageNum,
			@PathVariable("pageSize") int pageSize) {
		return contentService.getAllContentByUserId(id, pageNum, pageSize);
	}
	
	@GetMapping("/get/titles/{sectionName}")
	public String toTitlePage(@PathVariable("sectionName")String name,Model model){
		model.addAttribute("section",sectionService.getSection(name));
		return "title";
	}
	@GetMapping("/get/content/{name}")
	public String toContentPage(@PathVariable("name")String name,Model model){
		int titleId=titleService.getTitleIdByName(name);
		model.addAttribute("title", titleService.getTitleById(titleId));
		model.addAttribute("content", contentService.getContent(titleId,1,5));
		model.addAttribute("totalPageSize", contentService.getTotalContentByTitleId(titleId));
		model.addAttribute("pageNum", 1);
		return"content";
	}
	
	@PostMapping("/get/attention/{id}")
	@ResponseBody
	public Map<String,String> getAttention(@PathVariable("id")int id,Model model){
		return friendService.getAttentionIdList(id);
    }
	
	@PostMapping("/get/fans/{id}")
	@ResponseBody
	public Map<String,String> getfans(@PathVariable("id")int id,Model model){
		return friendService.getFansIdList(id);
    }
	
	@PostMapping("get/titles/{id}/{pageNum}")
	@ResponseBody
	public List<Title> getTitlesById(@PathVariable("id")int id,@PathVariable("pageNum") int pageNum,Model model){
		PageHelper.startPage(pageNum,3);
		return titleService.getTitlesByUserId(id);
	}
	
	
	
	@GetMapping("register")
	public String toRegisterPage(){
		return "register";
	}
	
	@PostMapping(value="verify/{username}",produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String verifyUsername(@PathVariable("username")String username){
		if(userService.verifyUsername(username)){
			return "该用户名已存在";
		}
		return "可以使用";
	}
	
	@PostMapping("post/user")
	@ResponseBody
	public String register(User user){
		userService.addUser(user);
		return "";
	}
	
	@GetMapping("get/avatar")
	public String toAlterAvatarPage(){
		return"alter_avatar";
	}
	
	@PostMapping("post/avatar")
	public String uploadAvatar(MultipartFile avatar,HttpSession session) throws IllegalStateException, IOException{
		userService.uploadAvatar(avatar, session);
		return"";
	}
}
