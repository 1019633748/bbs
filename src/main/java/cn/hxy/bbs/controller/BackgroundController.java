package cn.hxy.bbs.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cn.hxy.bbs.service.impl.PostServiceImpl;
import cn.hxy.bbs.service.impl.ReplyServiceImpl;
import cn.hxy.bbs.service.impl.SectionServiceImpl;
import cn.hxy.bbs.service.impl.UserServiceImpl;
import cn.hxy.bbs.service.impl.VerifyServiceImpl;

@Controller
public class BackgroundController {

	@Autowired
	private SectionServiceImpl sectionService;
	@Autowired
	private PostServiceImpl postService;
	@Autowired
	private ReplyServiceImpl replyService;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private VerifyServiceImpl verifyService;
	//@ExceptionHandler
	public String error(Exception ex){
		System.out.println(ex);
		return"error";
	}
	
	//@RequiresRoles("管理员")
	@GetMapping("get/bg")
	public String toBgPage(Model model){
		model.addAttribute("auditAmount",verifyService.getAmount());
		model.addAttribute("userAmount", userService.getAdminUserDetailByName("").size());
		model.addAttribute("sectionAmount", sectionService.getAdminSectionByParam("").size());
		model.addAttribute("postAmount", postService.getAdminPostDetailByName("").size());
		model.addAttribute("replyAmount",replyService.getReplyDetailByName("").size());
		return "bg";
	}
	
	
	@GetMapping("admin/section")
	public String toAdminSection(){
		return "admin_section";
	}
	
	@GetMapping("admin/post")
	public String toAdminPost(){
		return "admin_post";
	}
	
	@GetMapping("admin/reply")
	public String toAdminReply(){
		return "admin_reply";
	}
	
	@GetMapping("admin/user")
	public String toAdminUser(){
		return "admin_user";
	}
	
	@GetMapping("admin/sensitive")
	public String toAdminsensitive(){
		return "admin_sensitive";
	}
	
	@GetMapping("audit/content")
	public String toAuditContent(){
		return "audit_report";
	}
	
	@GetMapping("admin/advice")
	public String toAdminAdvice() {
		return "admin_advice";
	}
	
	@GetMapping("audit/avatar")
	public String toAdminAvater(){
		return "admin_avatar";
	}
	
	@GetMapping("get/chart/user")
	public String toUserChart(){
		return "user_chart";
	}
	
	@GetMapping("get/chart/content")
	public String toContentChart(){
		return "content_chart";
	}
	
	/*@PostMapping("admin/section")
	@ResponseBody
	public TableData<SectionSize> adminSection(int pageSize,int pageIndex,String param){
		TableData<SectionSize> tableData = new TableData<SectionSize>();
		PageHelper.startPage(pageIndex, pageSize);
		tableData.setRows(sectionService.getAdminSectionByParam(param));
		tableData.setTotal(sectionService.getAdminSectionByParam(param).size());
		return tableData;
	}
	
	@PostMapping("hide/sections/{id}")
	@ResponseBody
	public String hideSectionById(@PathVariable("id")int id){
		sectionService.hideSectionById(id,1);
		return null;
	}
	@PostMapping("show/sections/{id}")
	@ResponseBody
	public String showSectionById(@PathVariable("id")int id){
		sectionService.hideSectionById(id,0);
		return null;
	}
	
	@PostMapping("verify/section/{section}")
	@ResponseBody
	public int verifySection(@PathVariable("section")String section){
		if(sectionService.getSectionByName(section)==null){
			return 0;
		}
		return 1;
	}
	
	@PostMapping("post/section")
	@ResponseBody
	public void addSection(Section section){
		sectionService.addSection(section.getSection());
	}

	
	
	
	//帖子管理
	@PostMapping("admin/post")
	@ResponseBody
	public  TableData<PostDetail> getPostdetail(int pageSize,int pageIndex,String param){
		TableData<PostDetail> tableData = new TableData<PostDetail>();
		PageHelper.startPage(pageIndex, pageSize);
		tableData.setRows(postService.findAllAdminPost());
		tableData.setTotal(postService.findAllAdminPost().size());
		return tableData;
	}
	
	
	
	@PostMapping("hide/posts/{id}")
	@ResponseBody
	public String hidePostById(@PathVariable("id")int id){
		postService.hidePostById(id,1);
		return null;
	}
	@PostMapping("show/posts/{id}")
	@ResponseBody
	public String showPostById(@PathVariable("id")int id){
		postService.hidePostById(id,0);
		return null;
	}*/
	
}
