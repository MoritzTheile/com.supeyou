package com.supeyou.app.server;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail
{

	public static void main(String[] args)
	{

		sendEmail("test@moritztheile.de", "aSubject", "atoken");
	}

	public static void sendEmail(String to, String subject, String text)
	{
		sendEmail(to, subject, text, false);
	}

	public static void sendEmail(String to, String subject, String text, boolean isHTML) {
		sendEmail(SendEmail.getMailSystemAddress(), to, subject, text, isHTML);
	}

	public static void sendEmail(String from, String to, String subject, String text, boolean isHTML) {
		sendEmail(from, to, subject, text, isHTML, new String[] {});
	}

	public static void sendEmail(final String from, String to, String subject, String text, boolean isHTML, String[] fileNames) {
		// Sender's email ID needs to be mentioned

		// Get system properties
		Properties properties = new Properties();

		// Setup mail server
		properties.put("mail.transport.protocol", getMailTransportProtocol());
		properties.put("mail.smtp.auth", getMailSMTPAuth());
		properties.put("mail.smtp.host", getMailSMTPHost());
		properties.put("mail.smtp.port", getMailSMTPPort());
		Session session = Session.getInstance(properties, new Authenticator()
		{
			public PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(SendEmail.getMailSystemAddress(), SendEmail.getMailSystemPassword());
			}

		});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Maybe to contains more than one address - so split the to-list by ,
			String[] receipants = to.split(",");

			for (String receipant : receipants) {
				// Set To: header field of the header.

				InternetAddress address = new InternetAddress(receipant.trim());
				message.addRecipient(Message.RecipientType.TO,
						address);
			}

			// Set Subject: header field
			message.setSubject(subject);

			Multipart multipart = new MimeMultipart();
			// Now set the actual message

			MimeBodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText(text);
			if (isHTML)
				messageBodyPart.setHeader("Content-Type", "text/html");
			multipart.addBodyPart(messageBodyPart);

			if (fileNames.length > 0) {
				for (String fileName : fileNames) {
					// first - check, if the File exists
					File file = new File(fileName);
					if (file.exists()) {
						messageBodyPart = new MimeBodyPart();
						DataSource source = new FileDataSource(fileName);
						messageBodyPart.setDataHandler(new DataHandler(source));
						messageBodyPart.setFileName(file.getName());
						multipart.addBodyPart(messageBodyPart);
					}
				}
			}

			message.setContent(multipart);

			// Send message
			Transport.send(message, message.getAllRecipients());

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	private static String getMailSystemAddress() {

		return "support@supeyou.com";

	}

	private static Object getMailTransportProtocol() {
		return "smtp";
	}

	private static String getMailSystemPassword() {
		return "summit5close";
	}

	private static Object getMailSMTPAuth() {
		return "true";
	}

	private static Object getMailSMTPHost() {
		return "smtp.1und1.de";
	}

	private static Object getMailSMTPPort() {
		return "587";
	}
}