package br.com.codequest.mobile.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Classe utilitária para manipular strings
 * 
 * @author Gustavo Marcelo
 * 
 */
public class StringUtils {

	/**
	 * Implementação do split
	 * 
	 * @param str
	 * @param sep
	 * @param maxNum
	 * @return
	 */
	public static String[] split(String str, char sep, int maxNum) {
		// se n�o encontrar o texto do arquivo retorna um array v�zio
		if (str == null || str.length() == 0) { /* [1] */
			return new String[0];
		}

		// inicializar o vetor de dados
		Vector results = maxNum == 0 ? new Vector() : new Vector(maxNum);

		// ler as linhas do arquivo
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (c == sep) {
				if (maxNum != 0) {
					if ((results.size() + 1) == maxNum) {
						for (; i < str.length(); i++) {
							buf.append(str.charAt(i));
						}
						break;
					}
				}
				// coloca no vetor somente as propriedades
				if (!buf.toString().trim().startsWith("#")
						&& buf.toString().trim().length() > 0) {
					results.addElement(buf.toString());
				}
				buf.setLength(0);
			} else {
				buf.append(c);
			}
		}

		if (buf.length() > 0) {
			results.addElement(buf.toString());
		}

		return toStringArray(results); /* [5] */
	}

	/**
	 * Converte um vetor em um array de string
	 * 
	 * @param strings
	 * @return
	 */
	public static String[] toStringArray(Vector strings) {
		String[] result = new String[strings.size()];
		for (int i = 0; i < strings.size(); i++) {
			result[i] = strings.elementAt(i).toString();
		}
		return result;
	}

	/**
	 * Separa o nome e o valor da propriedade
	 * 
	 * @param inStr
	 * @return
	 */
	public static String separarNomePropriedade(String inStr) {
		if (inStr == null || "".equals(inStr)) {
			return inStr;
		}

		char lastChar = inStr.charAt(inStr.length() - 1);
		if (lastChar == 13) {
			return inStr.substring(0, inStr.length() - 1);
		} else if (lastChar == 10) {
			String tmp = inStr.substring(0, inStr.length() - 1);
			if ("".equals(tmp)) {
				return tmp;
			}
			lastChar = tmp.charAt(tmp.length() - 1);
			if (lastChar == 13) {
				return tmp.substring(0, tmp.length() - 1);
			} else {
				return tmp;
			}
		} else {
			return inStr;
		}
	}

	/**
	 * Transformar string em Hashtable
	 */
	public static Hashtable stringToHashtable(String texto) {
		Hashtable mapa = new Hashtable();

		texto = replaceAll(texto, "\n", "");
		texto = replaceAll(texto, "\r", "");
		texto = replaceAll(texto, "&quot;", "");
		String linhas[] = split(texto, ';', 0);

		for (int i = 0; i < linhas.length; i++) {
			int index = linhas[i].indexOf("=");
			mapa.put(linhas[i].substring(0, index), linhas[i]
					.substring(index + 1));
		}

		return mapa;
	}

	/**
	 * Implementação do replaceALL
	 * 
	 * @param texto
	 * @param valorAntigo
	 * @param valorNovo
	 * @return
	 */
	public static String replaceAll(String texto, String valorAntigo,
			String valorNovo) {
		StringBuffer valor = new StringBuffer(texto);

		while (valor.toString().indexOf(valorAntigo) >= 0) {
			int indice = valor.toString().indexOf(valorAntigo);
			valor.insert(indice, valorNovo);

			int inicio = indice + valorNovo.length() - 1;
			int quantidade = valorAntigo.length();

			for (int i = inicio + quantidade; i > inicio; i--) {
				valor.deleteCharAt(i);
			}
		}

		return valor.toString();
	}

	public static String calculaStringCanonica(String texto) {
		String textoUp = texto.toUpperCase();
		String nomeCalculado = "";

		for (int i = 0; i < textoUp.length(); i++) {
			char c = textoUp.charAt(i);
			switch (c) {
			case 'Á':
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
			case 'È':
			case 'Ë':
				nomeCalculado = nomeCalculado + 'E';
				break;
			case 'Í':
			case 'Ì':
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
			case 'Û':
			case 'Ü':
				nomeCalculado = nomeCalculado + 'U';
				break;
			case 'Ý':
				nomeCalculado = nomeCalculado + 'Y';
				break;
			case ' ':
				nomeCalculado = nomeCalculado + '_';
				break;
			default:
				nomeCalculado = nomeCalculado + c;
			}
		}

		return nomeCalculado.toLowerCase();
	}

	public static String removeCaracterEspecial(String texto) {
		String textoUp = texto.toUpperCase();
		String nomeCalculado = "";

		for (int i = 0; i < textoUp.length(); i++) {
			char c = textoUp.charAt(i);
			switch (c) {
			case 'Á':
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
			case 'È':
			case 'Ë':
				nomeCalculado = nomeCalculado + 'E';
				break;
			case 'Í':
			case 'Ì':
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
			case 'Û':
			case 'Ü':
				nomeCalculado = nomeCalculado + 'U';
				break;
			case 'Ý':
				nomeCalculado = nomeCalculado + 'Y';
				break;
			case '¡':
				// nomeCalculado = nomeCalculado + ' ';
				break;
			default:
				nomeCalculado = nomeCalculado + c;
			}
		}

		return nomeCalculado.toUpperCase();
	}

	public static String processaTexto(String texto, Hashtable campos) {
		String tempKey;
		String tempValue;
		Enumeration keys;
		if (campos != null) {
			keys = campos.keys();
			while (keys.hasMoreElements()) {
				tempKey = (String) keys.nextElement();
				if (tempKey != null) {
					tempValue = (String) campos.get(tempKey);
					if (tempValue != null) {
						texto = StringUtils.replaceAll(texto, tempKey,
								tempValue);
					}
				}
			}
		} else {

			return null;
		}
		return texto;
	}

	public static String toConcatena(Hashtable registro) {
		Enumeration keys = registro.keys();
		String parameters = "";
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			addProperty((String) key, (String) registro.get(key), parameters);
		}
		return parameters;
	}

	public static void addProperty(String key, String value, String parameters) {
		if (parameters == null) {
			parameters = key + "=" + value;
		} else {
			parameters += ";" + key + "=" + value;
		}
	}

	public static boolean toBoolean(String name) {
		return ((name != null) && name.equals("true"));
	}


}
