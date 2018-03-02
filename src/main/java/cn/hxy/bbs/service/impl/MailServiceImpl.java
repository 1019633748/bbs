package cn.hxy.bbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import cn.hxy.bbs.service.MailService;
@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(String email,String random) {
		SimpleMailMessage mail = new SimpleMailMessage();
		try{
			mail.setTo(email);
			mail.setFrom("1422948122@qq.com");
			mail.setSubject("修改密码");
			mail.setText("你的4位验证码为"+random);
			javaMailSender.send(mail);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
