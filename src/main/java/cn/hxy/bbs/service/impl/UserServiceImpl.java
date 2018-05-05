package cn.hxy.bbs.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.hxy.bbs.dto.UserDetail;
import cn.hxy.bbs.mapper.AvatarMapper;
import cn.hxy.bbs.mapper.UserAndRoleMapper;
import cn.hxy.bbs.mapper.UserMapper;
import cn.hxy.bbs.model.Avatar;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.model.UserAndRole;
import cn.hxy.bbs.realm.UserRealm;
import cn.hxy.bbs.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserAndRoleMapper urMapper;
	@Autowired
	private AvatarMapper avatarMapper;
	@Autowired
	private MailServiceImpl mailService;
	
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
		Avatar avatar = new Avatar();
		avatar.setUserId(user.getId());
		avatarMapper.insertSelective(avatar);
		return user.getId();
	}
	
	@Override
	public int addAdmin(User user) {
		String password = new SimpleHash("MD5",user.getPassword(),user.getNickname(),2).toHex();	
		user.setPassword(password);
		user.setSex((byte)1);
		userMapper.register(user);
		UserAndRole ur = new UserAndRole();
		ur.setRoleId(2);
		ur.setUserId(user.getId());
		urMapper.insert(ur);
		Avatar avatar = new Avatar();
		avatar.setUserId(user.getId());
		avatarMapper.insertSelective(avatar);
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

	@Override
	public List<UserDetail> getFollowListByUserId(int userId,String params) {
		return userMapper.getFollowListByUserId(userId,params);
	}

	@Override
	public List<UserDetail> getFansListByUserId(int userId, String params) {
		return userMapper.getFansListByUserId(userId, params);
	}

	@Override
	public void uploadAvatar(MultipartFile avatar, HttpSession session) throws IllegalStateException, IOException {
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		String avatarName=UUID.randomUUID()+avatar.getOriginalFilename();
		avatar.transferTo(new File("D:\\tool\\apache-tomcat-8.0.38\\webapps\\images\\avatar"+File.separator+avatarName));
		avatarMapper.updateByUserId(user.getId(), avatarName);
		session.setAttribute(UserRealm.SESSION_USER_KEY, userMapper.getUserDetailByUserId(user.getId()));
	}

	@Override
	public User getUserByNameOrEmail(String nameOrEmail) {
		return userMapper.getUserByNameOrEmail(nameOrEmail);
	}
	
	@Override
	public String getCode(String nameOrEmail,HttpSession session) {
		User user = userMapper.getUserByNameOrEmail(nameOrEmail);
		if(user!=null){
			session.setAttribute("oldUser", user);
			int random = (int)(Math.random()*(9999-1000+1))+1000;//产生1000-9999的随机数
			System.out.println(random);
			session.setAttribute("random", random+"");
			session.setAttribute("username", user.getNickname());
			mailService.sendEmail(user.getEmail(), random+"");
		}else{
			return "FAIL";
		}	
			return "SUC";
	}
	
	@Override
	public String verifyCode(String code, HttpSession session) {
		String random = (String) session.getAttribute("random");
		if(code.equals(random)){
			return "correct";
		}
		return "error";
	}
	
	
	@Override
	public String modifyPassword(String password, HttpSession session) {
		User user = (User) session.getAttribute("oldUser");
		String newPassword = new SimpleHash("MD5",password,user.getNickname(),2).toHex();
		userMapper.updatePassword(newPassword, user.getId());
		session.invalidate();
		return user.getNickname();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getMaleFemaleAmount() {
		return userMapper.getMaleFemaleAmount();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getCreateDate() {
		return userMapper.getCreateDate();
	}
}
