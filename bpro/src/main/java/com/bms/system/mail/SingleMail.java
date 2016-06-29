package com.bms.system.mail;

import com.bms.system.inteface.Constant;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送简单文本邮件
 * 直接用邮件发送
 *
 * @author Administrator
 */
public class SingleMail
{
    public static boolean send(String mailAddress, String key)
    {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

        //设定mail server
        senderImpl.setHost(Constant.SMTP_HOST);

        //建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mailMessage = senderImpl.createMimeMessage();
        MimeMessageHelper messageHelper;
        boolean success = false;

        try
        {
            messageHelper = new MimeMessageHelper(mailMessage, true, "GBK");
            //设置收件人，寄件人
            messageHelper.setTo(mailAddress);
            messageHelper.setFrom(Constant.SEND_FROM);
            messageHelper.setSubject("测试简单文本邮件发送！ ");
            //true 表示启动HTML格式的邮件
            String activeMethod = Constant.HOST + Constant.PROJECT_NAME + "/tUser/active_mail?key=" + key + "&emil=" + mailAddress;
            messageHelper.setText("<html><head><META http-equiv=Content-Type content='text/html; charset=GBK'></head><body>" +
                    "<a href='" + activeMethod + "'>激活账号</a></body></html>", true);

            senderImpl.setUsername(Constant.USER_NAME);  //  根据自己的情况,设置username
            senderImpl.setPassword(Constant.PASSWORD);  //  根据自己的情况, 设置password

            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
            prop.put("mail.smtp.timeout", "25000");

            senderImpl.setJavaMailProperties(prop);
            //发送邮件
            senderImpl.send(mailMessage);
            success = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return success;
    }
}