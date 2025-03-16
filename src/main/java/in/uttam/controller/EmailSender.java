package in.uttam.controller;

//package in.uttam.email;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import in.uttam.entity.User;
import jakarta.mail.internet.MimeMessage;

@Controller
public class EmailSender {

	// .....

	Date date = new Date();
	LocalDate myObj = LocalDate.now();

	@Autowired
	private JavaMailSender mailSender;

	public EmailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@RequestMapping("/send-Email")
	public String sendEmail(User user) {
		try {
			SimpleMailMessage mailMassage = new SimpleMailMessage();

			mailMassage.setFrom("unayek91@gmail.com");
			mailMassage.setTo(user.getEmail());
			mailMassage.setText(
					"This mail from Spring Boot to Souma The Januwar ......your account blocked by google for doing some suspisious activity like visiting pornography website,not response the massage of your friend(avoid/ignore),not peak up video call.");
			// mailMassage.setText("OTP : 8205");
			mailMassage.setSentDate(date);
			mailMassage.setSubject("Account Blocked");

			mailSender.send(mailMassage);
			return "sending info to user sucessfully done...!";
		} catch (Exception e) {
			return "bokachoda!!";
		}
	}

	// ..................................Attachment
	@RequestMapping("/send-Email-with-attachment")
	public String sendEmailAttachment(User user) {
		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom("unayek91@gmail.com");
			// helper.setText("uttamnayek2004@gmail.com");
			helper.setTo(user.getEmail());

			helper.setSubject("Attachment Project | from Uttam");
			helper.setSentDate(date);
			helper.setText(
					"This is a massage From Spring Boot | By uttam.\nCheck out the Attachment file.\nthe memory of a years ago in 'Kolkata Station' #friendforever.");

//			helper.addAttachment("Friend.jpg", new File("C:\\Users\\HP\\Downloads\\hi.jpg"));
			// helper.addAttachment("musium.jpg", new
			// File("D:\\Videos\\me\\IMG_20220922_103848.jpg"));

			mailSender.send(message);
			return "send to user done..";
		}

		catch (Exception e) {
			e.printStackTrace();
			return "hoyni bokachoda!!";
		}
	}

	// ..................................Attachment
	@RequestMapping("/send-Email-with-HTML-attachment")
	public String sendEmailHtmlAttachment(User user) {
		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom("unayek91@gmail.com");
			// helper.setText("uttamnayek2004@gmail.com");
			helper.setTo(user.getEmail());

			helper.setSubject("Attachment   Project | from Uttam");
			helper.setSentDate(date);
			helper.setText("");

			try (var inputStrem = EmailSender.class.getResourceAsStream("/templates/attachment.html")) {
				helper.setText(new String(inputStrem.readAllBytes(), StandardCharsets.UTF_8), true);
			}

//			helper.addAttachment("uttam.png", new File("C:\\Users\\HP\\Downloads\\1733027804289.png"));

			mailSender.send(message);
			return "otp send to user done..";
		}

		catch (Exception e) {
			e.printStackTrace();
			return "hoyni bokachoda!!";
		}
	}
}
