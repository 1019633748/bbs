package cn.hxy.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.dto.TableData;
import cn.hxy.bbs.log.adminLog;
import cn.hxy.bbs.model.Avatar;
import cn.hxy.bbs.service.impl.AvatarServiceImpl;

@Controller
public class AvatarController {
	@Autowired
	private AvatarServiceImpl avatarService;

	@PostMapping("get/audit/avatar")
	@ResponseBody
	public TableData<Avatar> findAllAvatar(int pageSize, int pageIndex) {
		TableData<Avatar> td = new TableData<Avatar>();
		PageHelper.startPage(pageIndex, pageSize);
		td.setRows(avatarService.findAll());
		td.setTotal(avatarService.findAll().size());
		return td;
	}

	@adminLog
	@PostMapping("hide/avatars/{id}")
	@ResponseBody
	public String hideAvatarById(@PathVariable("id") int id) {
		avatarService.banAvatarById(id);
		return null;
	}

}
