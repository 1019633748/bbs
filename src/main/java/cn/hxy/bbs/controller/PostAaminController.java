package cn.hxy.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.dto.PostDetail;
import cn.hxy.bbs.dto.TableData;
import cn.hxy.bbs.service.impl.PostServiceImpl;

@Controller
public class PostAaminController {
	@Autowired
	private PostServiceImpl postService;
	
	@PostMapping("get/admin/post")
	@ResponseBody
	public  TableData<PostDetail> getPostdetail(int pageSize,int pageIndex,String param){
		TableData<PostDetail> tableData = new TableData<PostDetail>();
		PageHelper.startPage(pageIndex, pageSize);
		tableData.setRows(postService.getAdminPostDetailByName(param));
		tableData.setTotal(postService.getAdminPostDetailByName(param).size());
		return tableData;
	}
		
	@PostMapping("hide/posts/{id}")
	@ResponseBody
	public String hideReplyById(@PathVariable("id")int id){
		postService.hidePostById(id,1);
		return null;
	}
	@PostMapping("show/posts/{id}")
	@ResponseBody
	public String showPostById(@PathVariable("id")int id){
		postService.hidePostById(id,0);
		return null;
	}
}
