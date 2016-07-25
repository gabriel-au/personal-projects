package br.com.xtrategia.fiscon.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Utilitário para manipulação de datas
 * 
 * @author Gustavo Marcelo Costa
 * 
 */
public class DataUtil {

	/**
	 * Utilitário de <i>logging </i>
	 */
	static private Log log = LogFactory.getLog(DataUtil.class);

	/**
	 * Formatador de data
	 */
	static private SimpleDateFormat formatador = new SimpleDateFormat(
			"dd/MM/yyyy");

	/**
	 * Retorna o dia de hoje
	 */
	public static Date getDiaHoje() {

		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Retorna o primeiro dia do ano corrente
	 */
	public static Date getPrimeiroDiaAnoAtual() {

		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Retorna o último dia do ano corrente
	 */
	public static Date getUltimoDiaAnoAtual() {

		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * Recupera o ano atual.
	 */
	public static int getAnoAtual() {

		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * Transforma uma data, no formato dd/MM/yyyy, para o tipo Date
	 */
	public static Date getDate(String data) {

		Date retorno = null;

		try {
			retorno = DataUtil.formatador.parse(data);

		} catch (ParseException parse) {
			log.error(parse);
		}
		return retorno;
	}

	/**
	 * Retorna uma data, informada formatada dd/MM/yyyy
	 */
	public static String getDateAsString(Date data) {

		if (data == null) {
			return "";
		}

		return DataUtil.formatador.format(data);
	}
	
	/**
	 * Retorna uma data, informada formatada dd/MM/yyyy
	 */
	public static String getDateXML() {
		SimpleDateFormat formatador = new SimpleDateFormat(
		"dd/MM/yyyy HH:mm:ss");
		return formatador.format(new Date());
	}

	/**
	 * Dada uma data, retorna o ano
	 */
	public static int getAno(Date data) {

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		return calendar.get(Calendar.YEAR);
	}
}