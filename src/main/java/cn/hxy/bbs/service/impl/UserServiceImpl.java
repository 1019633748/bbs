package cn.hxy.bbs.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.mapper.ContentMapper;
import cn.hxy.bbs.mapper.UserMapper;
import cn.hxy.bbs.model.Content;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ContentMapper contentMapper;
	
	@Override
	public User getUserByID(int id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public Set<String> getRolesByUsername(String username) {
		return userMapper.getRolesByUsername(username);
	}

	@Override
	public List<Content> getAllContentByUserId(int id,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return contentMapper.getAllByUserId(id);
	}

	@Override
	public int getUserIdByName(String name) {
		return userMapper.getUserIdByName(name);
	}
	
	
}
