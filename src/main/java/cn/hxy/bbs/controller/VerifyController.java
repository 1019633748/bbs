package cn.hxy.bbs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.dto.TableData;
import cn.hxy.bbs.dto.VerifyPost;
import cn.hxy.bbs.dto.VerifyReply;
import cn.hxy.bbs.log.adminLog;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.realm.UserRealm;
import cn.hxy.bbs.service.impl.VerifyServiceImpl;

@Controller
public class VerifyController {
	@Autowired
	private VerifyServiceImpl verifyService;

	// ¾Ù±¨
	@GetMapping("/report/posts/{postId}")
	public String report(@PathVariable("postId") int postId, HttpSession session) {
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		verifyService.reportPost(user.getId(), postId);
		return "redirect:/get/posts/" + postId;
	}

	@GetMapping("/report/replys/{replyId}/{postId}")
	public String report(@PathVariable("replyId") int replyId, @PathVariable("postId") int postId,
			HttpSession session) {
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		verifyService.reportReply(user.getId(), replyId, postId);
		return "redirect:/get/posts/" + postId;
	}

	// ÉóºË

	@PostMapping("get/audit/post")
	@ResponseBody
	public TableData<VerifyPost> getAuditPost(int pageSize, int pageIndex, String params) {
		TableData<VerifyPost> tableData = new TableData<VerifyPost>();
		PageHelper.startPage(pageIndex, pageSize);
		tableData.setRows(verifyService.getAuditPostByParams(params));
		tableData.setTotal(verifyService.getAuditPostByParams(params).size());
		return tableData;
	}

	@adminLog
	@PostMapping("hide/verify/posts/{id}/{postId}")
	@ResponseBody
	public String hideVerifyPostById(@PathVariable("id") int id, @PathVariable("postId") int postId) {
		verifyService.hideVerifyPostById(id, 1, postId);
		return null;
	}

	@adminLog
	@PostMapping("show/verify/posts/{id}/{postId}")
	@ResponseBody
	public String showVerifyPostById(@PathVariable("id") int id, @PathVariable("postId") int postId) {
		verifyService.hideVerifyPostById(id, 0, postId);
		return null;
	}

	@adminLog
	@PostMapping("ignore/posts/{id}")
	@ResponseBody
	public String ignorePost(@PathVariable("id") int id) {
		verifyService.deleteReportPost(id);
		return null;
	}

	@PostMapping("get/audit/reply")
	@ResponseBody
	public TableData<VerifyReply> getReplyPost(int pageSize, int pageIndex, String params) {
		TableData<VerifyReply> tableData = new TableData<VerifyReply>();
		PageHelper.startPage(pageIndex, pageSize);
		tableData.setRows(verifyService.getAuditReplyByParams(params));
		tableData.setTotal(verifyService.getAuditReplyByParams(params).size());
		return tableData;
	}

	@adminLog
	@PostMapping("hide/verify/replys/{id}/{replyId}")
	@ResponseBody
	public String hideVerifyReplyById(@PathVariable("id") int id, @PathVariable("replyId") int replyId) {
		verifyService.hideVerifyReplyById(id, 1, replyId);
		return null;
	}

	@adminLog
	@PostMapping("show/verify/replys/{id}/{replyId}")
	@ResponseBody
	public String showVerifyReplyById(@PathVariable("id") int id, @PathVariable("replyId") int replyId) {
		verifyService.hideVerifyReplyById(id, 0, replyId);
		return null;
	}

	@adminLog
	@PostMapping("ignore/replys/{id}")
	@ResponseBody
	public String ignoreReply(@PathVariable("id") int id) {
		verifyService.deleteReportPost(id);
		return null;
	}
}
