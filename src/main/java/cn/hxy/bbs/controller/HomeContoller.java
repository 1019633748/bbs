package cn.hxy.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.hxy.bbs.service.impl.SectionServiceImpl;

@Controller()
@RequestMapping("/home")
public class HomeContoller {
	@Autowired
	private SectionServiceImpl homeServiceImpl;

	@GetMapping("/get/home")
	public String toHomePage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "2") int pageSize, Model model) {
		model.addAttribute("sections", homeServiceImpl.getSections(pageNum, pageSize));
		return "home";
	}

}
