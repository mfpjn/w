package at.mfpjn.workflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service("sendMail")
public class SendMail {

		@Autowired
		private MailSender mailSender;
		
		@Autowired
		SimpleMailMessage templateMailMessage;
		
		@Autowired
		private JavaMailSenderImpl javaMailSender;

		
		public void setSimpleMailMessage(SimpleMailMessage templateMailMessage){
			
			this.templateMailMessage = templateMailMessage;
		}
		
		public void setMailSender(MailSender mailSender){
			
			this.mailSender = mailSender;
		}
		
		public void sendMail(String from, String to, String subject, String msg){
			
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			
			simpleMailMessage.setFrom(from);
			simpleMailMessage.setTo(to);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(msg);
			mailSender.send(simpleMailMessage);
		}
		
		public void sendMailWithTemplate(String dear, String content){
			SimpleMailMessage message = new SimpleMailMessage(templateMailMessage);
			message.setText(String.format(templateMailMessage.getText(),dear,content));
			mailSender.send(message);
		}
		
		
}
