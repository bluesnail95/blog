package gdut.ff.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mail.fromMail.addr}")
	private String from;
	
	
	@Override
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		
		try {
			mailSender.send(message);
			logger.info("简单邮件已经发送");
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("发送简单邮件时发生异常!!!");
		}
	}


	@Override
	public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
        	MimeMessageHelper helper = new MimeMessageHelper(message,true);
        	helper.setFrom(from);
        	helper.setTo(to);
        	helper.setSubject(subject);
        	helper.setText(content,true);
        	
        	FileSystemResource file = new FileSystemResource(new File(filePath));
        	String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
        	helper.addAttachment(fileName, file);
        	
        	mailSender.send(message);
        	logger.info("带附件的邮件已经发送");
        }catch(MessagingException e) {
        	logger.error("发送带附件的邮件时发送异常！！！");
        }
	}

}
