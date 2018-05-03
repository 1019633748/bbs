package cn.hxy.bbs.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import cn.hxy.bbs.model.User;
import cn.hxy.bbs.service.impl.UserServiceImpl;

@Controller
public class LoginController {
	@Autowired
	private UserServiceImpl userService;
	

	//登录
	@PostMapping("login")
	public String doUserLogin(String nickname, String password,Model model) {
		 String info = userLogin(nickname,password); 
		    if (!"SUCC".equals(info)) {  
		        model.addAttribute("failMsg", "用户不存在或密码错误！");  
		        return "fail";  
		    }else{  
		        model.addAttribute("successMsg", "登陆成功！");//返回到页面说夹带的参数  
		        model.addAttribute("name", nickname);  
		        return "success";//返回的页面  
		    }  
	}
	
	private String userLogin(String name,String password){
		if (isRelogin(userService.doLogin(name, password))) return "SUCC"; // 如果已经登陆，无需重新登录  
		return shiroLogin(name, password);
	}
	private String shiroLogin(String name,String password){
		String passwordMD5 = new SimpleHash("MD5",password,name,2).toHex();
		//System.out.println(passwordMD5);
		UsernamePasswordToken token = new UsernamePasswordToken(name,passwordMD5.toCharArray(),null);
		token.setRememberMe(true);
		try {  
	        SecurityUtils.getSubject().login(token);  
	    } catch (UnknownAccountException ex) {  
	        return "用户不存在或者密码错误！";  
	    } catch (IncorrectCredentialsException ex) {  
	        return "用户不存在或者密码错误！";  
	    } catch (AuthenticationException ex) {  
	        return ex.getMessage(); // 自定义报错信息  
	    } catch (Exception ex) {  
	        ex.printStackTrace();  
	        return "内部错误，请重试！";  
	    }
		return "SUCC";
	}

	private boolean isRelogin(User user) {  
	    Subject us = SecurityUtils.getSubject();
	        if (us.isAuthenticated()) {  
	           return true; // 参数未改变，无需重新登录，默认为已经登录成功  
	        }  
	        return false; // 需要重新登陆  
	}  
}
