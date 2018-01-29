package gdut.ff.service;

public interface MailService {

	public void sendSimpleMail(String to,String subject,String content);
	
	public void sendAttachmentsMail(String to,String subject,String content,String filePath);
}
