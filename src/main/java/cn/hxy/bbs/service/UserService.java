package cn.hxy.bbs.service;

import java.util.List;
import java.util.Set;

import cn.hxy.bbs.model.Content;
import cn.hxy.bbs.model.User;

public interface UserService {

	User getUserByID(int id);
	
	Set<String> getRolesByUsername(String username);
	
	List<Content> getAllContentByUserId(int id,int pageNum,int pageSize);
	
	int getUserIdByName(String name);
	
}
