package com.brq.mobile.framework.mail;

import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.brq.mobile.framework.core.ConfigAccess;
import com.brq.mobile.framework.log.Log;

public class MailSender {

	private static final String	TAG	= MailSender.class.getName();
	
	public MailSender() {
		
		MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		CommandMap.setDefaultCommandMap(mc);
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @param subject
	 * @param message
	 * @param file
	 * @throws Exception
	 */
	public boolean sendMail(String from, String[] to, String subject, String message) throws Exception {
		return sendMail(from, to, subject, message, new String[0]);
	}
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @param subject
	 * @param message
	 * @param file
	 * @throws Exception
	 */
	public boolean sendMail(String from, String[] to, String subject, String message, String file) throws Exception {
		return sendMail(from, to, subject, message, new String[] { file });
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @param subject
	 * @param message
	 * @param files
	 * @return 
	 * @throws Exception
	 */
	public boolean sendMail(String from, String[] to, String subject, String message, String[] files) throws Exception {

		Properties properties = ConfigAccess.getConfig().getProperties();

		String user = properties.getProperty("mail.smtp.user");
		String pass = properties.getProperty("mail.smtp.password");

		Session session = Session.getInstance(properties, new MailAuthenticator(user, pass));
		session.setDebug(true);

		Message msg = new MimeMessage(session);

		try {

			// Setando o destinatário
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to[0]));
			for (int count = 1; count < to.length; count++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[count]));
			}			
			
			// Setando a origem do email
			if (from == null || from.isEmpty()) {
				from = properties.getProperty("mail.smtp.from");
			}
			//
			msg.setFrom(new InternetAddress(from));

			// Setando o assunto
			msg.setSubject(subject);

			if (files == null || files.length == 0) {

				/* Setando o content/type do email */
				msg.setContent(message, "text/html");

			} else {

				// Cria a primeira parte da mensagem
				MimeBodyPart mineBodyPart = new MimeBodyPart();
				mineBodyPart.setContent(message, "text/html");

				// Cria a Multipart
				Multipart multiPart = new MimeMultipart();
				multiPart.addBodyPart(mineBodyPart);

				for (String file : files) {

					// Cria partes dos arquivos em anexo
					MimeBodyPart mineBodyPartFiles = new MimeBodyPart();

					// Anexa o arquivo na mensagem
					FileDataSource fileDataSource = new FileDataSource(file);
					mineBodyPartFiles.setDataHandler(new DataHandler(fileDataSource));
					mineBodyPartFiles.setFileName(fileDataSource.getName());
					multiPart.addBodyPart(mineBodyPartFiles);
				}

				// adiciona a Multipart na mensagem
				msg.setContent(multiPart);
			}

		} catch (Exception e) {

			Log.e(TAG, e.getLocalizedMessage());
			
			throw new Exception(e.getLocalizedMessage(), e);
		}

		/*
		 * 
		 */

		try {
			
			Transport transport = session.getTransport();
			transport.connect();

			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			
			return true;
			
		} catch (Exception e) {

			Log.e(TAG, e.getLocalizedMessage());
		}
		
		return false;
	}
}