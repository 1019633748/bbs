package cn.hxy.bbs.service;

import java.util.List;

import cn.hxy.bbs.dto.UserDetail;
import cn.hxy.bbs.model.User;

public interface UserService {
	
	//µÇÂ¼
	User doLogin(String nickname,String password);
	
	//×¢²á
	int register(User user);
	
	int addAdmin(User user);
		
	List<User> getCheckUser();
	
	List<UserDetail> getAdminUserDetailByName(String params);
	
	int hideUserById(int id,int status);
	
	int verifyName(String name);
	
	UserDetail getUserDetailByUserId(int userId);
	
	int updateUser(User user);
	
	String getRoleByUserId(int userId);
}
