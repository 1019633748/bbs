package cn.hxy.bbs.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

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
	
	List<UserDetail> getFollowListByUserId(int userId,String params);
	
	List<UserDetail> getFansListByUserId(int userId,String params);
	
	void uploadAvatar(MultipartFile avatar,HttpSession session)throws IllegalStateException, IOException;
	
	 User getUserByNameOrEmail(String nameOrEmail);

	String getCode(String nameOrEmail, HttpSession session);

	String modifyPassword(String password, HttpSession session);

	String verifyCode(String code, HttpSession session);
	
	@SuppressWarnings("rawtypes")
	List<Map> getMaleFemaleAmount();
	
	@SuppressWarnings("rawtypes")
	List<Map> getCreateDate();
	
	int getEmail(String email);
}
