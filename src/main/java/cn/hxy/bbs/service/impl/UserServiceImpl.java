package cn.hxy.bbs.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.mapper.ContentMapper;
import cn.hxy.bbs.mapper.UserMapper;
import cn.hxy.bbs.model.Content;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.realm.UserRealm;
import cn.hxy.bbs.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ContentMapper contentMapper;
	
	@Autowired
	private ImageServiceImpl imageService;
	
	@Autowired
	private FriendServiceImpl friendService;
	
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

	@Override
	public User doUserLogin(String name, String password) {
		return userMapper.doUserLogin(name, password);
	}

	@Override
	public Boolean verifyUsername(String username) {
		return userMapper.verifyUsername(username);
	}

	@Override
	public int addUser(User user) {
		String password = new SimpleHash("MD5",user.getPassword(),user.getName(),2).toHex();
		user.setPassword(password);
		return userMapper.insert(user);
	}

	@Override
	public void uploadAvatar(MultipartFile avatar,HttpSession session) throws IllegalStateException, IOException {
		String avatarName=UUID.randomUUID()+avatar.getOriginalFilename();
		avatar.transferTo(new File("D:\\tool\\apache-tomcat-8.0.38\\webapps\\images\\avatar"+File.separator+avatarName));
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		imageService.updateAvatar(user.getId(), avatarName);
	}


	public boolean isAttention(int userId, int targetId) {
		return friendService.isAttention(userId, targetId);
	}

	
	
	
}
