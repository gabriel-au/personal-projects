package br.com.codequest.mobile.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Classe para formatar as datas
 * 
 * <code>
 * Exemplos:
 * 	DateConverter dc = new DateConverter();
 *	System.out.println(dc.dateToString(new Date(), "dd/MM/yyyy"));
 *	System.out.println(dc.dateToString(new Date(), "dd/MM/yyyy ss mil"));
 *	System.out.println(dc.dateToString(new Date(), "teste dd-MM@yyyy ss mil"));
 * </code>
 * 
 * @author Gustavo Marcelo
 * 
 */
public class DateConverter {

	public static final String ANO = "yy";
	public static final String ANO4 = "yyyy";
	public static final String MES = "MM";
	public static final String DIA = "dd";
	public static final String HORA = "hh";
	public static final String HORA24 = "HH";
	public static final String MINUTO = "mm";
	public static final String SEGUNDOS = "ss";
	public static final String MILISEGUNDOS = "mil";

	/**
	 * Receb uma data no formato Long e uma mascara
	 * @param lgData
	 * @param mascara
	 * @return data
	 */
	public static String dateToString(long lgData, String mascara) {
		return dateToString(new Date(lgData), mascara);
	}

	/**
	 * Receb uma data e uma mascara
	 * @param data
	 * @param mascara
	 * @return data
	 */
	public static String dateToString(Date data, String mascara) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);

		int dtDia = cal.get(Calendar.DATE);
		int dtMes = cal.get(Calendar.MONTH) + 1;
		int dtAno = cal.get(Calendar.YEAR);
		int dtHr = cal.get(Calendar.HOUR);
		int dtMin = cal.get(Calendar.MINUTE);
		int dtSeg = cal.get(Calendar.SECOND);
		int dtMil = cal.get(Calendar.MILLISECOND);
		int dtAmPm = cal.get(Calendar.AM_PM);

		if (dtAmPm == 1) {
			dtHr += 12;
		}

		String strDia = String.valueOf(dtDia);
		String strMes = String.valueOf(dtMes);
		String strAno = String.valueOf(dtAno);
		String strHr = String.valueOf(dtHr);
		String strMin = String.valueOf(dtMin);
		String strSeg = String.valueOf(dtSeg);
		String strMil = String.valueOf(dtMil);

		if (strDia.length() == 1) {
			strDia = "0" + strDia;
		}
		if (strMes.length() == 1) {
			strMes = "0" + strMes;
		}
		if (strHr.length() == 1) {
			strHr = "0" + strHr;
		}
		if (strMin.length() == 1) {
			strMin = "0" + strMin;
		}
		if (strSeg.length() == 1) {
			strSeg = "0" + strSeg;
		}
		if (strMil.length() == 1) {
			strMil = "00" + strMil;
		}
		if (strMil.length() == 2) {
			strMil = "0" + strMil;
		}

		mascara = StringUtils.replaceAll(mascara, ANO4, strAno);
		mascara = StringUtils.replaceAll(mascara, ANO, strAno);
		mascara = StringUtils.replaceAll(mascara, MES, strMes);
		mascara = StringUtils.replaceAll(mascara, DIA, strDia);
		mascara = StringUtils.replaceAll(mascara, HORA, strHr);
		mascara = StringUtils.replaceAll(mascara, MINUTO, strMin);
		mascara = StringUtils.replaceAll(mascara, SEGUNDOS, strSeg);
		mascara = StringUtils.replaceAll(mascara, MILISEGUNDOS, strMil);

		return mascara;
	}

}
