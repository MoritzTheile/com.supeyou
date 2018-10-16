package com.supeyou.app.server;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail
{

	public static void main(String[] args) {

		String link = "http://supeyou.com";

		SendEmail.sendEmail("testa3489@moritztheile.de", "text Your SupeYou invitation was successful",

				// "<a href=\"" + link + "\">" + link + "</a>", true
				link, false
				);
	}

	public static void sendEmail(String to, String subject, String text) {
		sendEmail(to, subject, text, false);
	}

	public static void sendEmail(String to, String subject, String text, boolean isHTML) {
		sendEmail(to, null, subject, text, isHTML);
	}

	public static void sendEmail(String to, String bcc, String subject, String text, boolean isHTML) {
		sendEmail(to, bcc, subject, text, isHTML, new String[] {});
	}

	public static void sendEmail(String to, String bcc, String subject, String text, boolean isHTML, String[] fileNames) {
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
			message.setFrom(new InternetAddress(getMailSystemAddress()));

			if (bcc != null) {
				message.addRecipient(RecipientType.BCC, new InternetAddress(bcc));
			}

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

			if (isHTML) {
				message.setContent(text, "text/html; charset=utf-8");
			} else {
				message.setText(text);
			}

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
		return "Supeyou.com75???18";
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