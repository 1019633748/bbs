package cn.hxy.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.model.Content;
import cn.hxy.bbs.model.Section;
import cn.hxy.bbs.model.Title;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.service.impl.ContentServiceImpl;
import cn.hxy.bbs.service.impl.SectionServiceImpl;
import cn.hxy.bbs.service.impl.TitleServiceImpl;
import cn.hxy.bbs.service.impl.UserServiceImpl;

@Controller
@RequestMapping("bg")
public class BackgroundController {

	@Autowired
	private SectionServiceImpl sectionService;

	@Autowired
	private TitleServiceImpl titleService;

	@Autowired
	private ContentServiceImpl contentService;
	
	@Autowired
	private UserServiceImpl userService;

	@GetMapping("get/bg")
	public String toBgPage() {
		return "background";
	}

	@PostMapping("get/section")
	@ResponseBody
	public List<Section> getSection() {
		return sectionService.getSections();
	}

	@PostMapping("get/title")
	@ResponseBody
	public List<Title> getTitle() {
		return titleService.getTitles();
	}

	@PostMapping("get/content")
	@ResponseBody
	public List<Content> getContnet() {
		PageHelper.startPage(0, 5);
		return contentService.getContent();
	}

	@PostMapping("get/users")
	@ResponseBody
	public List<User> getUsers() {
		PageHelper.startPage(0, 5);
		return userService.getUsers();
	}
}
