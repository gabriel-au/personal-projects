package br.com.xtrategia.fiscon.web.util;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;

/**
 * Classe para manipular Strings
 * 
 * @author Gustavo Marcelo Costa
 * 
 */
public class StringUtility {
	public static String calculaStringCanonica(String texto) {
		String textoUp = StringUtils.defaultString(texto);
		textoUp = textoUp.toUpperCase(new Locale("pt", "br"));
		textoUp = StringUtils.strip(textoUp);
		String nomeCalculado = "";

		for (int i = 0; i < textoUp.length(); i++) {
			char c = textoUp.charAt(i);
			switch (c) {
			case 'ç':
			case 'À':
			case 'Â':
			case 'Ã':
			case 'Ä':
				nomeCalculado = nomeCalculado + 'A';
				break;
			case 'Ç':
				nomeCalculado = nomeCalculado + 'C';
				break;
			case 'É':
			case 'Ê':
			case 'ê':
			case 'Ë':
				nomeCalculado = nomeCalculado + 'E';
				break;
			case 'î':
			case 'í':
			case 'Ï':
			case 'Î':
				nomeCalculado = nomeCalculado + 'I';
				break;
			case 'Ñ':
				nomeCalculado = nomeCalculado + 'N';
				break;
			case 'Ó':
			case 'Ò':
			case 'Ô':
			case 'Õ':
			case 'Ö':
				nomeCalculado = nomeCalculado + 'O';
				break;
			case 'Ú':
			case 'Ù':
			case 'ó':
			case 'Ü':
				nomeCalculado = nomeCalculado + 'U';
				break;
			case 'Ý':
				nomeCalculado = nomeCalculado + 'Y';
				break;
			default:
				nomeCalculado = nomeCalculado + c;
			}
		}

		return nomeCalculado;
	}

	public static String completaEsquerda(String texto, int tamanho) {
		while (texto.length() < tamanho) {
			texto = "0" + texto;
		}
		return texto;
	}

}
