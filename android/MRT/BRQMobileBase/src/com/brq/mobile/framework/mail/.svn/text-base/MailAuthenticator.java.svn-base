package com.brq.mobile.framework.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Classe de encapsulamento de informações para uso durante o processo de
 * autenticação no servidor de e-mail.
 */
public class MailAuthenticator extends Authenticator {

	private String username, password;

	public MailAuthenticator(String user, String pwd) {
		username = user;
		password = pwd;
	}

	/**
	 * Retorna um elemento de autenticação para e-mail
	 * a partir de usuário e senha pré-configurados
	 */
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}

}
