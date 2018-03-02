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
            mail.setTo("1019633748@qq.com");// ������
            mail.setFrom("1422948122@qq.com");// ������,
            mail.setSubject("��������");// ����
            mail.setText("�����ʼ����ݲ���");// �ʼ�����
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