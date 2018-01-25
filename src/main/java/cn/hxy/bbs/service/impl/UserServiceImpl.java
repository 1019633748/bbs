package cn.hxy.bbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hxy.bbs.mapper.UserMapper;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserByID(int id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

}
