package cn.hxy.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hxy.bbs.model.Content;
import cn.hxy.bbs.service.impl.ContentServiceImpl;
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

	@GetMapping("/get/user/{id}")
	public String getUserInfo(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userService.getUserByID(id));
		model.addAttribute("uri", imageService.getAvatarByUserId(id));
		return "user_info";
	}

	@PostMapping("/get/content/{userId}/{pageNum}/{pageSize}")
	@ResponseBody
	public List<Content> getContentByUserId(@PathVariable("userId") int id, @PathVariable("pageNum") int pageNum,
			@PathVariable("pageSize") int pageSize) {
		return userService.getAllContentByUserId(id, pageNum, pageSize);
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
}
