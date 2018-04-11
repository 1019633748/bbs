package cn.hxy.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.dto.ReplyDetail;
import cn.hxy.bbs.dto.TableData;
import cn.hxy.bbs.service.impl.ReplyServiceImpl;

@Controller
public class ReplyAdminController {
	@Autowired
	private ReplyServiceImpl replyService;
	
	@PostMapping("get/admin/reply")
	@ResponseBody
	public  TableData<ReplyDetail> getReplydetail(int pageSize,int pageIndex,String param){
		TableData<ReplyDetail> tableData = new TableData<ReplyDetail>();
		PageHelper.startPage(pageIndex, pageSize);
		tableData.setRows(replyService.getReplyDetailByName(param));
		tableData.setTotal(replyService.getReplyDetailByName(param).size());
		return tableData;
	}
	
	@PostMapping("hide/replys/{id}")
	@ResponseBody
	public String hideReplyById(@PathVariable("id")int id){
		replyService.hideReplyById(id,1);
		return null;
	}
	@PostMapping("show/replys/{id}")
	@ResponseBody
	public String showReplyById(@PathVariable("id")int id){
		replyService.hideReplyById(id,0);
		return null;
	}
}
