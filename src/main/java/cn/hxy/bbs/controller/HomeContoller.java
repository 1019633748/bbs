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

import cn.hxy.bbs.service.impl.ImageServiceImpl;
import cn.hxy.bbs.service.impl.SectionServiceImpl;

@Controller()
@RequestMapping("/home")
public class HomeContoller {
	@Autowired
	private SectionServiceImpl homeServiceImpl;
	
	@Autowired
	private ImageServiceImpl imageService;

	@GetMapping("/get/home")
	public String toHomePage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "4") int pageSize, Model model) {
		model.addAttribute("sections", homeServiceImpl.getSections(pageNum, pageSize));
		return "home";
	}
	
	@PostMapping("get/avatar/{id}")
	@ResponseBody
	public String getAvatarById(@PathVariable("id")int id){
		return imageService.getAvatarByUserId(id);
	}

}
