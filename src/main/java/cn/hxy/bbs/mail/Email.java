package cn.hxy.bbs.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class Email {

    public void send(ApplicationContext ctx) {
        JavaMailSender sender = (JavaMailSender) ctx.getBean("javaMailSender");
        SimpleMailMessage mail = new SimpleMailMessage(); 
        try {
            mail.setTo("1019633748@qq.com");// 接受者
            mail.setFrom("1422948122@qq.com");// 发送者,
            mail.setSubject("测试主题");// 主题
            mail.setText("发送邮件内容测试");// 邮件内容
            sender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "application-mail.xml");
        Email e = new Email();
        e.send(ctx);
    }
}