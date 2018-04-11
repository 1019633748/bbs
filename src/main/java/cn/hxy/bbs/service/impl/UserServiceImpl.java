package cn.hxy.bbs.service.impl;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hxy.bbs.dto.UserDetail;
import cn.hxy.bbs.mapper.UserAndRoleMapper;
import cn.hxy.bbs.mapper.UserMapper;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.model.UserAndRole;
import cn.hxy.bbs.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserAndRoleMapper urMapper;
	
	@Override
	public User doLogin(String nickname, String password) {
		return userMapper.login(nickname, password);
	}

	@Override
	public int register(User user) {
		String password = new SimpleHash("MD5",user.getPassword(),user.getNickname(),2).toHex();	
		user.setPassword(password);
		userMapper.register(user);
		UserAndRole ur = new UserAndRole();
		ur.setRoleId(1);
		ur.setUserId(user.getId());
		urMapper.insert(ur);
		return user.getId();
	}
	
	@Override
	public int addAdmin(User user) {
		String password = new SimpleHash("MD5",user.getPassword(),user.getNickname(),2).toHex();	
		user.setPassword(password);
		userMapper.register(user);
		UserAndRole ur = new UserAndRole();
		ur.setRoleId(2);
		ur.setUserId(user.getId());
		urMapper.insert(ur);
		return user.getId();
	}

	@Override
	public List<User> getCheckUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDetail> getAdminUserDetailByName(String params) {
		return userMapper.getAdminUserDetailByName(params);
	}

	@Override
	public int hideUserById(int id, int status) {
		return userMapper.hideUserById(id, status);
	}

	@Override
	public int verifyName(String name) {
		if(userMapper.getUserByName(name)==null){
			return 0;
		}
		return 1;
	}

	@Override
	public UserDetail getUserDetailByUserId(int userId) {
		return userMapper.getUserDetailByUserId(userId);
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public String getRoleByUserId(int userId) {
		return userMapper.getRoleByUserId(userId);
	}

	

}
