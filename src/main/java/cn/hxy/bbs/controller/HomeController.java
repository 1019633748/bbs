package cn.hxy.bbs.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.service.impl.PostServiceImpl;
import cn.hxy.bbs.service.impl.ReplyServiceImpl;
import cn.hxy.bbs.service.impl.SectionServiceImpl;

@Controller
public class HomeController {
	
	
	@Autowired
	private SectionServiceImpl sectionService;
	
	@Autowired
	private PostServiceImpl postService;
	
	@Autowired
	private ReplyServiceImpl replyService;
	// ��ת��ҳ
	@GetMapping("get/home")
	public String toHomePage(Model model) {
		model.addAttribute("sectionSize",sectionService.findAllSection());
		PageHelper.startPage(0, 5);
		model.addAttribute("hotpost", postService.findHotPost());
		model.addAttribute("advice",replyService.getAdviceByName(""));
		PageHelper.startPage(0, 5);
		model.addAttribute("hotReply",replyService.findHotReply());
		return "home";
	}

	// ��ת��¼ҳ��
	@GetMapping("get/login")
	public String toLoginPage() {
		return "login";
	}

	// ��תע��ҳ��
	@GetMapping("get/register")
	public String toRegisterPage() {
		return "register";
	}

	// ��ת������ҳ��
	@GetMapping("get/post")
	public String toPostMessagePage() {
		return "post_message";
	}

	// ��ת����ҳ��
	@GetMapping("get/posts")
	public String toPostsPage() {
		return "post";
	}

	//�˳���¼
	@GetMapping("logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			try {
				subject.logout();
			} catch (Exception ex) {
			}
		}
		response.sendRedirect("/bbs/get/home");
	}
	
	//����ĳ���
	@GetMapping("get/sections/{id}")
	public String getSectionById(@PathVariable("id")int id,Model model){
		model.addAttribute("section",sectionService.getSectionById(id));
		model.addAttribute("post",postService.getPostDetailBySectionId(id));
		model.addAttribute("hot", postService.getHotPostDetailBySectionId(id));
		model.addAttribute("last", postService.getLastPostDetailBySectionId(id));
		return "post_list";
	}
	
}
