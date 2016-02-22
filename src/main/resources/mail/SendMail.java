package mail;

import java.util.Calendar;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import auth.User;

public class SendMail {
	private final static String fromEmail = "v2bteam1secur@gmail.com";
    private final static String password = "tokensecurity";
	
	public static void send(User user, String token){		
		 Properties props = new Properties();
         props.put("mail.smtp.host", "smtp.gmail.com");
         props.put("mail.smtp.port", "587");
         props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.starttls.enable", "true");
		
		String to = user.getEmail();
		String name = user.getUsername();
		String text = "User: "+name+"\nToken: "+token;
		
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(fromEmail, password);
					}
				  });

				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(fromEmail));
					message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(to));
					message.setSubject("Authentication token");
					message.setText(text);

					Transport.send(message);

				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
	}
}
