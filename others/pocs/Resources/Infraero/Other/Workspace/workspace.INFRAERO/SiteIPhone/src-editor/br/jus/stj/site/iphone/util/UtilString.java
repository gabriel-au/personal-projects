package br.jus.stj.site.iphone.util;

import java.nio.charset.Charset;

public class UtilString {

	private static String fullPath;

	/**
	 * O padrão é converter todos os caracteres especiais
	 * 
	 * @param texto
	 * @return
	 */
	public static String converter(String texto) {
		return converter(texto, false);
	}

	public static String converter(String texto, boolean contemQuebraLinha) {
		return converter(texto, contemQuebraLinha, false);
	}

	public static String converter(String texto, boolean contemQuebraLinha,
			boolean contemTagHtml) {
		if (texto == null) {
			return "";
		}
		texto = texto.replaceAll("&", "&amp;");
		texto = texto.replaceAll("Á", "&Aacute;");
		texto = texto.replaceAll("á", "&aacute;");
		texto = texto.replaceAll("Â", "&Acirc;");
		texto = texto.replaceAll("â", "&acirc;");
		texto = texto.replaceAll("À", "&Agrave;");
		texto = texto.replaceAll("à", "&agrave;");
		texto = texto.replaceAll("Å", "&Aring;");
		texto = texto.replaceAll("å", "&aring;");
		texto = texto.replaceAll("Ã", "&Atilde;");
		texto = texto.replaceAll("ã", "&atilde;");
		texto = texto.replaceAll("Ä", "&Auml;");
		texto = texto.replaceAll("ä", "&auml;");
		texto = texto.replaceAll("Æ", "&AElig;");
		texto = texto.replaceAll("æ", "&aelig;");
		texto = texto.replaceAll("É", "&Eacute;");
		texto = texto.replaceAll("é", "&eacute;");
		texto = texto.replaceAll("Ê", "&Ecirc;");
		texto = texto.replaceAll("ê", "&ecirc;");
		texto = texto.replaceAll("È", "&Egrave;");
		texto = texto.replaceAll("è", "&egrave;");
		texto = texto.replaceAll("Ë", "&Euml;");
		texto = texto.replaceAll("ë", "&euml;");
		texto = texto.replaceAll("Ð", "&ETH;");
		texto = texto.replaceAll("ð", "&eth;");
		texto = texto.replaceAll("Í", "&Iacute;");
		texto = texto.replaceAll("í", "&iacute;");
		texto = texto.replaceAll("Î", "&Icirc;");
		texto = texto.replaceAll("î", "&icirc;");
		texto = texto.replaceAll("Ì", "&Igrave;");
		texto = texto.replaceAll("ì", "&igrave;");
		texto = texto.replaceAll("Ï", "&Iuml;");
		texto = texto.replaceAll("ï", "&iuml;");
		texto = texto.replaceAll("Ó", "&Oacute;");
		texto = texto.replaceAll("ó", "&oacute;");
		texto = texto.replaceAll("Ô", "&Ocirc;");
		texto = texto.replaceAll("ô", "&ocirc;");
		texto = texto.replaceAll("Ò", "&Ograve;");
		texto = texto.replaceAll("ò", "&ograve;");
		texto = texto.replaceAll("Ø", "&Oslash;");
		texto = texto.replaceAll("ø", "&oslash;");
		texto = texto.replaceAll("Õ", "&Otilde;");
		texto = texto.replaceAll("õ", "&otilde;");
		texto = texto.replaceAll("Ö", "&Ouml;");
		texto = texto.replaceAll("ö", "&ouml;");
		texto = texto.replaceAll("Ú", "&Uacute;");
		texto = texto.replaceAll("ú", "&uacute;");
		texto = texto.replaceAll("Û", "&Ucirc;");
		texto = texto.replaceAll("û", "&ucirc;");
		texto = texto.replaceAll("Ù", "&Ugrave;");
		texto = texto.replaceAll("ù", "&ugrave;");
		texto = texto.replaceAll("Ü", "&Uuml;");
		texto = texto.replaceAll("ü", "&uuml;");
		texto = texto.replaceAll("Ç", "&Ccedil;");
		texto = texto.replaceAll("ç", "&ccedil;");
		texto = texto.replaceAll("Ñ", "&Ntilde;");
		texto = texto.replaceAll("ñ", "&ntilde;");
		if (contemQuebraLinha && !contemTagHtml) {
			texto = texto.replaceAll("\\n", "#Q#u#e#b#r#a#L#i#n#h#a##");
			texto = texto.replaceAll("<", "&lt;");
			texto = texto.replaceAll(">", "&gt;");
			texto = texto.replaceAll("#Q#u#e#b#r#a#L#i#n#h#a##", "\n<br/>\n");
		} else if (!contemTagHtml) {
			texto = texto.replaceAll("<", "&lt;");
			texto = texto.replaceAll(">", "&gt;");
		}
		texto = texto.replaceAll("\\\"", "&quot;");
		texto = texto.replaceAll("®", "&reg;");
		texto = texto.replaceAll("©", "&copy;");
		texto = texto.replaceAll("Ý", "&Yacute;");
		texto = texto.replaceAll("ý", "&yacute;");
		texto = texto.replaceAll("Þ", "&THORN;");
		texto = texto.replaceAll("þ", "&thorn;");
		texto = texto.replaceAll("ß", "&szlig;");
		texto = texto.replaceAll("º", "&ordm;");
		return texto;
	}

	// colocar o path completo
	public static String getFullPath() {
		return fullPath;
	}

	public static void setFullPath(String fullPath) {
		UtilString.fullPath = fullPath + "/";
	}

	public static void main(String[] args) {
		System.out.println(converter("\""));
		System.out.println(converter("aaa\naaa"));
		System.out.println(converter("aaa\naaa", true));
	}

	public static String decodeUtf8(String s) {
		return new String(s.getBytes(), Charset.forName("ISO-8859-1"));
	}
	public static String encodeUtf8(String s) {
		return new String(s.getBytes(), Charset.forName("UTF-8"));
	}

}
