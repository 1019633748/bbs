package cn.hxy.bbs.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.dto.TableData;
import cn.hxy.bbs.dto.UserDetail;
import cn.hxy.bbs.log.adminLog;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.realm.UserRealm;
import cn.hxy.bbs.service.impl.AttentionServiceImpl;
import cn.hxy.bbs.service.impl.UserServiceImpl;

@Controller
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private AttentionServiceImpl attentionService;

	// 改密
	@PostMapping("post/nameoremail")
	@ResponseBody
	public String getNameOrEmail(String nameOrEmail, HttpSession session) {
		return userService.getCode(nameOrEmail, session);
	}

	@PostMapping("post/code")
	@ResponseBody
	public String verifyCode(String code, HttpSession session) {
		return userService.verifyCode(code, session);
	}

	@PostMapping("post/password")
	@ResponseBody
	public String modityPassword(String password, HttpSession session) {
		return userService.modifyPassword(password, session);
	}

	// 管理用户
	@PostMapping("get/admin/user")
	@ResponseBody
	public TableData<UserDetail> getUserdetail(int pageSize, int pageIndex, String param) {
		TableData<UserDetail> tableData = new TableData<UserDetail>();
		PageHelper.startPage(pageIndex, pageSize);
		tableData.setRows(userService.getAdminUserDetailByName(param));
		tableData.setTotal(userService.getAdminUserDetailByName(param).size());
		return tableData;
	}

	@adminLog
	@PostMapping("hide/users/{id}")
	@ResponseBody
	public String hideUserById(@PathVariable("id") int id) {
		userService.hideUserById(id, 1);
		return null;
	}

	@adminLog
	@PostMapping("show/users/{id}")
	@ResponseBody
	public String showUserById(@PathVariable("id") int id) {
		userService.hideUserById(id, 0);
		return null;
	}

	// 个人信息
	@GetMapping("get/users/{userId}")
	public String toPersonlPage(@PathVariable("userId") int userId, Model model, HttpSession session) {
		User logined = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		int currentUserId = logined == null ? 0 : logined.getId();
		model.addAttribute("user", userService.getUserDetailByUserId(userId));
		model.addAttribute("isAttention", attentionService.isAttention(currentUserId, userId));
		return "personal";
	}

	@PostMapping("put/user")
	public String updateUser(User user, Model model, HttpSession session) {
		User logined = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		user.setId(logined.getId());
		userService.updateUser(user);
		model.addAttribute("user", userService.getUserDetailByUserId(user.getId()));
		return "personal";
	}

	@GetMapping("attention/users/{targetUserId}")
	public String attention(@PathVariable("targetUserId") int targetUserId, Model model, HttpSession session) {
		User logined = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		attentionService.attention(logined.getId(), targetUserId);
		model.addAttribute("user", userService.getUserDetailByUserId(targetUserId));
		model.addAttribute("isAttention", attentionService.isAttention(logined.getId(), targetUserId));
		return "personal";
	}

	@GetMapping("unfollow/users/{targetUserId}")
	public String unfollow(@PathVariable("targetUserId") int targetUserId, Model model, HttpSession session) {
		User logined = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		attentionService.unfollow(logined.getId(), targetUserId);
		model.addAttribute("user", userService.getUserDetailByUserId(targetUserId));
		model.addAttribute("isAttention", attentionService.isAttention(logined.getId(), targetUserId));
		return "personal";
	}

	@PostMapping("/get/follow/{userId}")
	@ResponseBody
	public TableData<UserDetail> getFollowListByUserId(@PathVariable("userId") int userId, int pageIndex, int pageSize,
			String param) {
		TableData<UserDetail> td = new TableData<UserDetail>();
		PageHelper.startPage(pageIndex, pageSize);
		td.setRows(userService.getFollowListByUserId(userId, param));
		td.setTotal(userService.getFollowListByUserId(userId, param).size());
		return td;
	}

	@PostMapping("/get/fans/{userId}")
	@ResponseBody
	public TableData<UserDetail> getFansListByUserId(@PathVariable("userId") int userId, int pageIndex, int pageSize,
			String param) {
		TableData<UserDetail> td = new TableData<UserDetail>();
		PageHelper.startPage(pageIndex, pageSize);
		td.setRows(userService.getFansListByUserId(userId, param));
		td.setTotal(userService.getFansListByUserId(userId, param).size());
		return td;
	}

	@GetMapping("get/avatar")
	public String alterAvatar() {
		return "alter_avatar";
	}

	@PostMapping("post/avatar")
	@ResponseBody
	public String uploadAvatar(MultipartFile avatar, HttpSession session) throws IllegalStateException, IOException {
		userService.uploadAvatar(avatar, session);
		return "SUC";
	}

	@GetMapping("alter/password")
	public String toAlterPassword() {
		return "password";
	}

	// chart
	@SuppressWarnings("rawtypes")
	@PostMapping("get/malefemale")
	@ResponseBody
	public List<Map> getMaleFemale() {
		return userService.getMaleFemaleAmount();
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("get/user/create")
	@ResponseBody
	public List<Map> getCreateDate() {
		return userService.getCreateDate();
	}
}
