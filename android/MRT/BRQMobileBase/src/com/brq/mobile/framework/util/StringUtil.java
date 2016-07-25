package com.brq.mobile.framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

public class StringUtil {

	public static final String	EMPTY			= "";
	
	public static final String	SPACE			= " ";

	/*
	 * Do not use to format number. reason: the result is diferent between android and windows
	 * enviroment. Exemple: 1232.90 at android: R$1.232,90 at windows R$ 1.232,90. The problem is
	 * the character between R$ and first number!
	 */
	private static final Locale	DEFAULT_LOCALE	= new Locale("pt", "BR");

	// Joining
	// -----------------------------------------------------------------------

	/**
	 * <p>
	 * Joins the elements of the provided {@code Iterator} into a single String containing the
	 * provided elements.
	 * </p>
	 * 
	 * <p>
	 * No delimiter is added before or after the list. A {@code null} separator is the same as an
	 * empty String ("").
	 * </p>
	 * 
	 * <p>
	 * See the examples here: {@link #join(Object[],String)}.
	 * </p>
	 * 
	 * @param iterator
	 *            the {@code Iterator} of values to join together, may be null
	 * @param separator
	 *            the separator character to use, null treated as ""
	 * @return the joined String, {@code null} if null iterator input
	 */
	public static String join(Iterator<?> iterator, String separator) {
			// handle null, zero and one elements before building a buffer
			if (iterator == null) {
				return null;
			}
			if (!iterator.hasNext()) {
				return EMPTY;
			}
			Object first = iterator.next();
			if (!iterator.hasNext()) {
				return objToString(first);
			}

			// two or more elements
			StringBuilder buf = new StringBuilder(256); // Java default is 16,
														// probably too small
			if (first != null) {
				buf.append(first);
			}

			while (iterator.hasNext()) {
				if (separator != null) {
					buf.append(separator);
				}
				Object obj = iterator.next();
				if (obj != null) {
					buf.append(obj);
				}
			}
			return buf.toString();
	}

	/**
	 * <p>
	 * Joins the elements of the provided {@code Iterable} into a single String containing the
	 * provided elements.
	 * </p>
	 * 
	 * <p>
	 * No delimiter is added before or after the list. A {@code null} separator is the same as an
	 * empty String ("").
	 * </p>
	 * 
	 * <p>
	 * See the examples here: {@link #join(Object[],String)}.
	 * </p>
	 * 
	 * @param iterable
	 *            the {@code Iterable} providing the values to join together, may be null
	 * @param separator
	 *            the separator character to use, null treated as ""
	 * @return the joined String, {@code null} if null iterator input
	 * @since 2.3
	 */
	public static String join(Iterable<?> iterable, String separator) {
		if (iterable == null) {
			return null;
		}
		return join(iterable.iterator(), separator);
	}

	public static String objToString(Object obj) {
		return obj == null ? EMPTY : obj.toString();
	}

	/**
	 * Formata data no formato passado por parametro
	 * 
	 * @param data
	 *            data a ser formatada
	 * @param formato
	 *            pattern para formtacao
	 * @return
	 */
	public static String formataData(Date data, String pattern) {
		SimpleDateFormat SDF = new SimpleDateFormat(pattern);
		if (data == null || EMPTY.equals(pattern)) {
			return EMPTY;
		}
		return SDF.format(data);
	}
	
	public static String formataMonetario(Object valor) {
		return formataMonetario(valor, 2, true);
	}
	
	public static String formataMonetario(Object valor, int casasDecimais) {
		return formataMonetario(valor, 2, true);
	}
	
	public static String formataMonetario(Object valor, boolean simbolo) {
		return formataMonetario(valor, 2, simbolo);
	}

	/**
	 * Formata valores no formato monetario
	 * 
	 * @param valor
	 *            valor a ser formatado
	 * @return
	 */
	public static String formataMonetario(Object valor, int casasDecimais, boolean simbolo) {
		if (valor == null) {
			return EMPTY;
		}
		if (valor instanceof String) {
			valor = new BigDecimal(valor.toString());
		}
		
		DecimalFormatSymbols newSymbols = new DecimalFormatSymbols(DEFAULT_LOCALE);
		newSymbols.setDecimalSeparator(',');
		newSymbols.setGroupingSeparator('.');
		
		String mask = simbolo ? "R$ #,##0.00" : "#,##0.00";
		
		DecimalFormat decimalFormat = new DecimalFormat(mask);
		decimalFormat.setDecimalFormatSymbols(newSymbols);
		decimalFormat.setMinimumFractionDigits(casasDecimais);
		decimalFormat.setMaximumFractionDigits(casasDecimais);
		
		return decimalFormat.format(valor);
	}

	/**
	 * Formata valores no formato monetario
	 * @param valor - valor a ser formatado
	 * @return
	 */
	public static String formataDecimal(Object valor) {
		if (valor == null) {
			return EMPTY;
		}
		if (valor instanceof String) {
			valor = new BigDecimal(valor.toString());
		}
		DecimalFormatSymbols newSymbols = new DecimalFormatSymbols(DEFAULT_LOCALE);
		newSymbols.setDecimalSeparator(',');
		newSymbols.setGroupingSeparator('.');
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		return decimalFormat.format(valor);
	}
	
	/**
	 * Formata valores no formato decimal de acordo com o número de casas decimais definida
	 * @param valor - valor a ser formatado
	 * @param casasDecimais - quantidade de casas decimais
	 * @return
	 */
	public static String formataDecimal(Object valor, int casasDecimais) {
		if (valor == null) {
			return EMPTY;
		}
		if (valor instanceof String) {
			valor = new BigDecimal(valor.toString());
		}
		
		StringBuilder mascara = new StringBuilder().append("#,##0.");
		for(int i = 0; i < casasDecimais; i++){
			mascara.append("0");
		}
		
		DecimalFormatSymbols newSymbols = new DecimalFormatSymbols(DEFAULT_LOCALE);
		newSymbols.setDecimalSeparator(',');
		newSymbols.setGroupingSeparator('.');
		DecimalFormat decimalFormat = new DecimalFormat(mascara.toString());

		return decimalFormat.format(valor);
	}
	
	/**
	 * Remove caracteres de marcacao de uma string Atuais pulo de linha e tabulacao \n\t
	 * 
	 * @param string
	 *            string para remocao dos caracteres de marcacao
	 * @return
	 */
	public static String removeMarcacao(String string) {
		String regex = "[\r\n\t]+";
		return string == null ? "" : string.replaceAll(regex, EMPTY);
	}

	/**
	 * Preenche com zeros a esquerda de acordo com o tamanho da string passado por parametro a ser
	 * alterada
	 * 
	 * @param valor
	 * @param tamanhoString
	 * @return
	 */
	public static String preencheZerosEsquerda(String valor, int tamanhoString) {
		StringBuilder s = new StringBuilder(valor);
		for (int i = tamanhoString; i > valor.length(); i--) {
			s.insert(0, "0");
		}
		return s.toString();
	}
	
	public static String formataCep(String cep){
		if(cep!=null && !"".equals(cep)){
			String c1=cep.substring(0,5);
			String c2=cep.substring(5,8);
			cep=c1.concat("-").concat(c2);
		}		
		return cep;
	}
	
	public static String formataTelefone(String numero){
		if(numero!=null && !"".equals(numero)){
			String n1=numero.substring(0,3);
			String n2=numero.substring(3,7);
			String n3=numero.substring(7,11);
			numero=n1.concat(" ").concat(n2).concat("-").concat(n3);
		}
		
		return numero;
	}
}