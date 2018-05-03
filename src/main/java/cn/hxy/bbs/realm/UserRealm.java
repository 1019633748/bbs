package cn.hxy.bbs.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.hxy.bbs.model.User;
import cn.hxy.bbs.service.impl.UserServiceImpl;

public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	private UserServiceImpl userService;
	
	public static final String SESSION_USER_KEY="bbs";
	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute(SESSION_USER_KEY);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole(userService.getRoleByUserId(user.getId()));
		return info;
	}
	


	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		User userLogin = tokenToUser((UsernamePasswordToken) authcToken);
		User ui = userService.doLogin(userLogin.getNickname(), userLogin.getPassword());
		if(ui == null){
			return null;
		}
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(SESSION_USER_KEY, userService.getUserDetailByUserId(ui.getId()));
		String realName = getName();
		Object principal = authcToken.getPrincipal();
		return new SimpleAuthenticationInfo(principal,userLogin.getPassword(),realName);
	}

	
	
	
	
	
	
	
	
	private User tokenToUser(UsernamePasswordToken authcToken) {
		User user = new User();
		user.setNickname(authcToken.getUsername());
		user.setPassword(String.valueOf(authcToken.getPassword()));
		return user;
	}



	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	

}
