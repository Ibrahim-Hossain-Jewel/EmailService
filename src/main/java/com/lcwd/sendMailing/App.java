package com.lcwd.sendMailing;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//Properties -> thats container mail server information
//Session.getInstance() -> I need to pass the properties to the session object as constructor value. and also accept an authenticator.
//MimeMessage accept the session and carry email datal.
//Transport class used to send email.
public class App{
	public static void main(String args[]) {
		System.out.println("Mail service implementaion");
		String from = "ibrahimhossain495@gmail.com";
		String to = "ibrahimhossaincse@gmail.com";
		String subject = "Alhamdulillha wow!";
		String message = "Email description";
		sendMail(from, to, subject, message);
	}
	public static void sendMail(String from, String to, String subject, String message) {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		//Session for authentication.
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ibrahimhossain495@gmail.com","ftahcegwxmepbzaz");	
			}
		});
		MimeMessage mimemessage = new MimeMessage(session); 
		//mimeMessage
		try {
			mimemessage.setFrom(new InternetAddress(from));
			mimemessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			mimemessage.setSubject(subject);
			mimemessage.setText(message);
			Transport.send(mimemessage);
			System.out.println("Send Successful.");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}