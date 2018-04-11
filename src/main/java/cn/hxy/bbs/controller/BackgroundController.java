package cn.hxy.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.dto.PostDetail;
import cn.hxy.bbs.dto.SectionSize;
import cn.hxy.bbs.dto.TableData;
import cn.hxy.bbs.model.Section;
import cn.hxy.bbs.service.impl.PostServiceImpl;
import cn.hxy.bbs.service.impl.ReplyServiceImpl;
import cn.hxy.bbs.service.impl.SectionServiceImpl;
import cn.hxy.bbs.service.impl.UserServiceImpl;

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
	
	@GetMapping("get/bg")
	public String toBgPage(){
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

	
	
	
	//Ìû×Ó¹ÜÀí
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
