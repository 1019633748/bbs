package cn.hxy.bbs.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hxy.bbs.model.User;
import cn.hxy.bbs.service.impl.UserServiceImpl;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("login")
	public String doUserLogin(String name, String password,Model model) {
		 String info = userLogin(name,password); 
		    if (!"SUCC".equals(info)) {  
		        model.addAttribute("failMsg", "�û������ڻ��������");  
		        return "fail";  
		    }else{  
		        model.addAttribute("successMsg", "��½�ɹ���");//���ص�ҳ��˵�д��Ĳ���  
		        model.addAttribute("name", name);  
		        return "success";//���ص�ҳ��  
		    }  
	}
	
	
	private String userLogin(String name,String password){
		if (isRelogin(userService.doUserLogin(name, password))) return "SUCC"; // ����Ѿ���½���������µ�¼  
		return shiroLogin(name, password);
	}
	private String shiroLogin(String name,String password){
		UsernamePasswordToken token = new UsernamePasswordToken(name,password.toCharArray(),null);
		token.setRememberMe(true);
		try {  
	        SecurityUtils.getSubject().login(token);  
	    } catch (UnknownAccountException ex) {  
	        return "�û������ڻ����������";  
	    } catch (IncorrectCredentialsException ex) {  
	        return "�û������ڻ����������";  
	    } catch (AuthenticationException ex) {  
	        return ex.getMessage(); // �Զ��屨����Ϣ  
	    } catch (Exception ex) {  
	        ex.printStackTrace();  
	        return "�ڲ����������ԣ�";  
	    }  
		
		return "SUCC";
	}

	private boolean isRelogin(User user) {  
	    Subject us = SecurityUtils.getSubject();  
	        if (us.isAuthenticated()) {  
	           return true; // ����δ�ı䣬�������µ�¼��Ĭ��Ϊ�Ѿ���¼�ɹ�  
	        }  
	        return false; // ��Ҫ���µ�½  
	}  
}
