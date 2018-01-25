package cn.hxy.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hxy.bbs.service.impl.SectionServiceImpl;

@Controller
@RequestMapping("/section")
public class SectionController {
	@Autowired
	private SectionServiceImpl sectionService;
	@GetMapping("/get/section/{id}")
	public String getSection(@PathVariable("id") int id,Model model){
		model.addAttribute("section", sectionService.getSection(id));
		return "title";
	}
}
