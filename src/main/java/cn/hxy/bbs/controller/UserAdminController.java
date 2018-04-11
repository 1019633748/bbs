package cn.hxy.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.dto.TableData;
import cn.hxy.bbs.dto.UserDetail;
import cn.hxy.bbs.service.impl.UserServiceImpl;

@Controller
public class UserAdminController {
	@Autowired
	private UserServiceImpl userService;
	@PostMapping("get/admin/user")
	@ResponseBody
	public  TableData<UserDetail> getUserdetail(int pageSize,int pageIndex,String param){
		TableData<UserDetail> tableData = new TableData<UserDetail>();
		PageHelper.startPage(pageIndex, pageSize);
		tableData.setRows(userService.getAdminUserDetailByName(param));
		tableData.setTotal(userService.getAdminUserDetailByName(param).size());
		return tableData;
	}
	
	@PostMapping("hide/users/{id}")
	@ResponseBody
	public String hideUserById(@PathVariable("id")int id){
		userService.hideUserById(id,1);
		return null;
	}
	@PostMapping("show/users/{id}")
	@ResponseBody
	public String showUserById(@PathVariable("id")int id){
		userService.hideUserById(id,0);
		return null;
	}
	
}

