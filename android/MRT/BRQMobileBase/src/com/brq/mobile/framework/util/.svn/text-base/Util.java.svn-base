package com.brq.mobile.framework.util;

import java.math.BigDecimal;

public class Util {

	/**
	 * Método que retorna um Integer caso o parâmetro seja diferente de nulo e vazio
	 * 
	 * @param valor
	 *            - campo a ser convertido
	 * @return Integer - valor convertido para Integer
	 */
	public static Integer getInteger(String valor) {
		if (valor == null || "".equals(valor)) {
			return null;
		}
		return Integer.valueOf(valor);
	}
	
	/**
	 * Método que retorna um Integer caso o parâmetro seja diferente de nulo e vazio
	 * 
	 * @param valor
	 *            - campo a ser convertido
	 * @param defaultValue
	 *            - valor default caso não seja possível tranformar a string em inteiro
	 * @return Integer - valor convertido para Integer
	 */
	public static Integer getInteger(String valor, Integer defaultValue) {
		try {
			Integer integer = getInteger(valor);
			return integer == null ? defaultValue : integer;
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * Método que retorna um Long caso o parâmetro seja diferente de nulo e vazio
	 * 
	 * @param valor
	 *            - campo a ser convertido
	 * @return Long - valor convertido para Long
	 */
	public static Long getLong(String valor) {
		if (valor == null || "".equals(valor)) {
			return null;
		}
		return Long.valueOf(valor);
	}

	/**
	 * Método que retorna um Double caso o par�metro seja diferente de nulo e vazio
	 * 
	 * @param valor
	 *            - campo a ser convertido
	 * @return Double - valor convertido para Double
	 */
	public static Double getDouble(String valor) {
		if (valor == null || "".equals(valor)) {
			return null;
		}
		return Double.valueOf(valor);
	}

	/**
	 * Método que retorna um BigDecimal caso o parâmetro seja diferente de nulo e vazio
	 * 
	 * @param valor
	 *            - campo a ser convertido
	 * @return BigDecimal - valor convertido para BigDecimal ou zero em caso de erro na conversão
	 */
	public static BigDecimal getBigDecimal(String valor) {
		return getBigDecimal(valor, BigDecimal.ZERO);
	}
	
	/**
	 * Método que retorna um BigDecimal caso o parâmetro seja diferente de nulo e vazio
	 * 
	 * @param valor campo a ser convertido
	 * 
	 * @param defaultValue valor default caso ocorra erro na conversão
	 * 
	 * @return valor convertido
	 */
	public static BigDecimal getBigDecimal(String valor, BigDecimal defaultValue) {
		try {
			if (valor == null || "".equals(valor)) {
				return defaultValue;
			}
			return new BigDecimal(valor);
		} catch (Exception e) {
			return defaultValue;
		}
		
	}

	public static boolean getBoolean(Object valor) {
		if(valor == null){
			return false;
		}
		return Integer.parseInt(valor.toString()) == 1 ? true : false;
	}
	
	
	public static String getStringValue(Object valor) {
		return getStringValue(valor, StringUtil.EMPTY);
	}
	
	public static String getStringValue(Object valor, String defaulValue) {
		try {
			return valor.toString();
		} catch (Exception e) {
			return defaulValue;
		}
	}
}