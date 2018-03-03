package cn.hxy.bbs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.hxy.bbs.model.Content;
import cn.hxy.bbs.model.Down;
import cn.hxy.bbs.model.Section;
import cn.hxy.bbs.model.Title;
import cn.hxy.bbs.model.Up;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.realm.UserRealm;
import cn.hxy.bbs.service.impl.ContentServiceImpl;
import cn.hxy.bbs.service.impl.FriendServiceImpl;
import cn.hxy.bbs.service.impl.ImageServiceImpl;
import cn.hxy.bbs.service.impl.SectionServiceImpl;
import cn.hxy.bbs.service.impl.TitleServiceImpl;
import cn.hxy.bbs.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentServiceImpl contentService;
	@Autowired
	private TitleServiceImpl titleService;
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private ImageServiceImpl imageService;
	
	@Autowired
	private FriendServiceImpl friendService;
	
	@Autowired
	private SectionServiceImpl sectionService;
	
	@GetMapping("/get/content/{id}/{pageNum}")
	public String getContent(@PathVariable("id") int id,
			@PathVariable("pageNum") int pageNum,@RequestParam(value="pageSize",defaultValue="5") int pageSize,Model model) {
		model.addAttribute("title", titleService.getTitleById(id));
		model.addAttribute("content", contentService.getContent(id,pageNum,pageSize));
		model.addAttribute("totalPageSize", contentService.getTotalContentByTitleId(id));
		model.addAttribute("pageNum", pageNum);
		return "content";
	}

	@PostMapping("/put/up")
	@ResponseBody
	public String up(Up up) {
		return contentService.getAllUpAfterClick(up) + "";
	}
	
	@PostMapping("/put/down")
	@ResponseBody
	public String down(Down down) {
		return contentService.getAllDownAfterClick(down) + "";
	}
	
	@GetMapping("/get/user/{name}")
	public String toUserInfoPage(@PathVariable("name")String name,Model model){
		int id = userService.getUserIdByName(name);
		model.addAttribute("user", userService.getUserByID(id));
		model.addAttribute("uri", imageService.getAvatarByUserId(id));
		model.addAttribute("attention", friendService.getAttentionByUserId(id));
		model.addAttribute("fans", friendService.getFansByUserId(id));
		return "user_info";
	}
	
	@PostMapping("post/content")
	public String postContent(Content content,@RequestParam("contentImg") MultipartFile contentImg,HttpSession session,Model model) throws IllegalStateException, IOException{
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		contentService.addContent(content);
		if(contentImg.getSize()>0)
		contentService.uploadContentImg(contentImg,content.getId() , user.getId());
		model.addAttribute("title", titleService.getTitleById(content.getTitleId()));
		model.addAttribute("content", contentService.getContent(content.getTitleId(),contentService.getTotalContentByTitleId(content.getTitleId()),5));
		model.addAttribute("totalPageSize", contentService.getTotalContentByTitleId(content.getTitleId()));
		model.addAttribute("pageNum", (int) Math.ceil(contentService.getTotalContentByTitleId(content.getTitleId())/5.0));
		return"content";
	}
	
	@GetMapping("get/title")
	public String toPostContentPage(){
		return "post_title";
	}
	
	@PostMapping("get/sections")
	@ResponseBody
	public List<Section> getSections(){
		return sectionService.getSections();
	}
	
	@PostMapping("post/title")
	@ResponseBody
	public String postTitle(Title title,HttpSession session){
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		title.setAuthor(user.getName());
		title.setUserId(user.getId());
		System.out.println(title);
		titleService.insert(title);
		return "";
	}
	
}
