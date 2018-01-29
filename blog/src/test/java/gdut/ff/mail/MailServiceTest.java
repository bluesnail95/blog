package gdut.ff.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gdut.ff.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
	
	@Autowired
	private MailService mailService;
	
	@Test
	public void testSimpleMail() throws Exception{
		mailService.sendSimpleMail("13642315483@163.com","新年快乐","祝您新年快乐！！！");
	}
	
	@Test
	public void sendAttachmentsMail() {
		String filePath = "D:\\1.jpg";
		mailService.sendAttachmentsMail("13642315483@163.com","附件：新年快乐!","有附件，请查收！", filePath);
	}
	
}
