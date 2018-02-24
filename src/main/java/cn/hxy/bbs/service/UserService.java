package cn.hxy.bbs.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import cn.hxy.bbs.model.Content;
import cn.hxy.bbs.model.User;

public interface UserService {

	User getUserByID(int id);
	
	Set<String> getRolesByUsername(String username);
	
	List<Content> getAllContentByUserId(int id,int pageNum,int pageSize);
	
	int getUserIdByName(String name);
	
	User doUserLogin(String name,String password);
	
	Boolean verifyUsername(String username);
	
	int addUser(User user);
	
	void uploadAvatar(MultipartFile avatar,HttpSession session)throws IllegalStateException, IOException;
}
