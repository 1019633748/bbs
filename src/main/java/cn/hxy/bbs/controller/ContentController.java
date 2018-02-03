package cn.hxy.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hxy.bbs.model.Down;
import cn.hxy.bbs.model.Up;
import cn.hxy.bbs.service.impl.ContentServiceImpl;
import cn.hxy.bbs.service.impl.FriendServiceImpl;
import cn.hxy.bbs.service.impl.ImageServiceImpl;
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
	
}
