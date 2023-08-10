package app.project.Mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import app.project.entities.User;

@Service
public class Mail {
	
	@Autowired
	private JavaMailSender javaMailSender;
	private final TemplateEngine templateEngine;
	
	public Mail(TemplateEngine templateEngine) {
		this.templateEngine=templateEngine;
	}
	
	public void sendVerificationEmail(User user)
			throws MessagingException, UnsupportedEncodingException {

		Context context=new Context();
		context.setVariable("name", user.getLastName());
		context.setVariable("link","http://localhost:8081/logIn?email="+user.getEmail());
		String body=templateEngine.process("VerifyEmail", context);
		
		String fromAddress = "villageconnectt@gmail.com";
		String senderName = "VillageConnect";
         
		MimeMessage message = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		String toAddress = user.getEmail();
		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject("Email address Verification");

		helper.setText(body, true);

		javaMailSender.send(message);
	}
	
	public void SendForgotPassword(User user,String Token)
			throws MessagingException, UnsupportedEncodingException {

		Context context=new Context();
		context.setVariable("token", Token);
		context.setVariable("link","http://localhost:8081/#/changerpassword");
		String body=templateEngine.process("ForgotPassword", context);
		
		String fromAddress = "villageconnectt@gmail.com";
		String senderName = "VillageConnect";
         
		MimeMessage message = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		String toAddress = user.getEmail();
		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject("Email Forgot Password");

		helper.setText(body, true);

		javaMailSender.send(message);
	}
	
	public void SendMailConfirm(String email_current,String new_email) throws MessagingException ,UnsupportedEncodingException{
		Context context=new Context();
		context.setVariable("email_new", new_email);
		context.setVariable("link","http://localhost:8081/login?email="+email_current+"&email_new="+new_email);
		String body=templateEngine.process("ConfirmChangeEmail", context);
		
		String fromAddress = "villageconnectt@gmail.com";
		String senderName = "VillageConnect";
         
		MimeMessage message = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		String toAddress = email_current;
		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject("Confirm Change Email");

		helper.setText(body, true);

		javaMailSender.send(message);
	}

}
