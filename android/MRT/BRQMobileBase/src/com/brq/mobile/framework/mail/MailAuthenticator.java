package com.brq.mobile.framework.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Classe de encapsulamento de informa��es para uso durante o processo de
 * autentica��o no servidor de e-mail.
 */
public class MailAuthenticator extends Authenticator {

	private String username, password;

	public MailAuthenticator(String user, String pwd) {
		username = user;
		password = pwd;
	}

	/**
	 * Retorna um elemento de autentica��o para e-mail
	 * a partir de usu�rio e senha pr�-configurados
	 */
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}

}
