package com.brq.mobile.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.brq.mobile.framework.log.Log;

public class DateUtil {

	public static final Locale DEFAULT_LOCALE = new Locale("pt", "BR");
	public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
	public static final String DEFAULT_DATE_DATABASE = "yyyyMMdd";
	public static final String DEFAULT_HOUR_DATABASE = "HH:mm";

	private static final String TAG = DateUtil.class.getName();

	/**
	 * Converte uma String em um java.util.Date utilizando o pattern informado. Faz tratamento para valores nulos.
	 * 
	 * @param data - data a ser formatada
	 * @param pattern - pattern para formtação
	 * @return Date convertido ou null caso seja passado null, vazio ou caso ocorra um erro na conversão.
	 * @throws ParseException
	 */
	public static Date formataData(String data, String pattern) throws ParseException {
		SimpleDateFormat SDF = new SimpleDateFormat(pattern);
		if (data == null || "".equals(data)) {
			return null;
		}
		return SDF.parse(data);
	}

	/**
	 * Formata uma Date em uma String no pattern informado. Faz tratamento para valores nulos.
	 * 
	 * @param date Objeto a ser verificado
	 * @param pattern Padrão de formataço para utilizar.
	 * @return String vazia ou data formatada
	 */
	public static String formataData(Date date, String pattern) {
		String dataFormatada = "";
		if (date != null) {
			dataFormatada = new SimpleDateFormat(pattern, DEFAULT_LOCALE).format(date);
		}
		return dataFormatada;
	}

	public static String formataHora(Date date) {
		String horaFormatada = "";
		if (date != null) {
			horaFormatada = new SimpleDateFormat(DEFAULT_HOUR_DATABASE, DEFAULT_LOCALE).format(date);
		}
		return horaFormatada;
	}
	
	public static String formataHora(Date date, String pattern) {
		String horaFormatada = "";
		if (date != null) {
			horaFormatada = new SimpleDateFormat(pattern, DEFAULT_LOCALE).format(date);
		}
		return horaFormatada;
	}
	
	/**
	 * Calcula a diferença de dias entre duas datas
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return quantidade de dias existentes entre a dataInicial e dataFinal.
	 */
	public static long diferencaEmDias(Date dataInicial, Date dataFinal) {
		dataInicial = zerarTempoData(dataInicial);
		dataFinal = zerarTempoData(dataFinal);
		return ((dataFinal.getTime() - dataInicial.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	/**
	 * Zerar Hora, minutos, segundos e milisegundos de uma data
	 * @param data
	 * @return Date - seus parâmetros inicializados com zero.
	 */
	public static Date zerarTempoData(Date data) {  
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(data);  
		calendar.set(Calendar.MILLISECOND, 0);  
		calendar.set(Calendar.SECOND, 0);  
		calendar.set(Calendar.MINUTE, 0);  
		calendar.set(Calendar.HOUR_OF_DAY, 0);  
		return calendar.getTime();  
	} 

	/**
	 * Método que adiciona a quantidade de dias a uma determinada data
	 * 
	 * @param data - data para ser adicionado dias
	 * @param qntDias - quantidade de dias a serem adicionados
	 * @return Date - nova data
	 */
	public static Date adicionarDias(Date data, int qntDias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DAY_OF_MONTH, qntDias);
		return calendar.getTime();
	}

	/**
	 * Retorna a descricao do dia da semana abreviada
	 * 
	 * @param diaSemana
	 * @return
	 */
	public static String getDescricaoAbreviadaDiaSemana(int diaSemana) {
		switch (diaSemana) {
		case 1:
			return "Dom";
		case 2:
			return "Seg";
		case 3:
			return "Ter";
		case 4:
			return "Qua";
		case 5:
			return "Qui";
		case 6:
			return "Sex";
		case 7:
			return "Sab";
		default:
			return "N/A";
		}
	}

	/**
	 * Método para retornar a data atual (hoje)
	 * 
	 * @return Date - data atual
	 */
	public static Date obterDataAtual() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * Metodo responsavel pela conversao de String para Date
	 * 
	 * @param dataString
	 * @return data
	 */
	public static Date converteStringToDate(String dataString) {
		try {
			if (dataString != null && !dataString.isEmpty()) {
				DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				return formatter.parse(dataString);
			}
			return null;
		} catch (ParseException e) {
			Log.e(TAG, e.getMessage());
			throw new RuntimeException("Erro no método converteStringToDate.", e);
		}
	}
	
	/**
	 * Método para retornar a data atual (hoje) trucanda, ou seja, sem horas, minutos ou segundos
	 * 
	 * @return Date - data atual
	 */
	public static Calendar obterDataAtualTruncada() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}
}