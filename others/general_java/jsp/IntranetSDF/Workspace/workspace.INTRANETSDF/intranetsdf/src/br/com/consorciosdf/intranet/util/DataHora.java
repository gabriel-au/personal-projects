/*
 * ConverteData.java
 *
 * Created on 26 de Julho de 2006, 15:06
 *
 */

package br.com.consorciosdf.intranet.util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class DataHora {
    
    private Date data;
    
    private long lgData;
    
    private String strDia = "";
    private String strMes = "";
    private String strAno = "";
    private String strHr = "";
    private String strMin = "";
    private String strSeg = "";
    
    private int dtDia = 0;
    private int dtMes = 0;
    private int dtAno = 0;
    private int dtHr = 0;
    private int dtMin = 0;
    private int dtSeg = 0;
    private int dtAmPm = 0;
    
    
    public String converter(long lgData) throws Exception {
        Date data = new Date(lgData);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        
        int dtDia = cal.get(Calendar.DATE);
        int dtMes = cal.get(Calendar.MONTH) + 1;
        int dtAno = cal.get(Calendar.YEAR);
        int dtHr = cal.get(Calendar.HOUR);
        int dtMin = cal.get(Calendar.MINUTE);
        int dtSeg = cal.get(Calendar.SECOND);
        int dtAmPm = cal.get(Calendar.AM_PM);
        
        if (dtAmPm == 1) {
            dtHr += 12;
        }
        
        strDia = String.valueOf(dtDia);
        strMes = String.valueOf(dtMes);
        strAno = String.valueOf(dtAno);
        strHr = String.valueOf(dtHr);
        strMin = String.valueOf(dtMin);
        strSeg = String.valueOf(dtSeg);
        
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
        
        String strData = strDia + "/" + strMes + "/" + strAno + " " +
                strHr + ":" + strMin + ":" + strSeg;
        
        return strData;
    }
    
    public String converter(Date data) throws Exception {
        this.data = data;
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        
        int dtDia = cal.get(Calendar.DATE);
        int dtMes = cal.get(Calendar.MONTH) + 1;
        int dtAno = cal.get(Calendar.YEAR);
        int dtHr = cal.get(Calendar.HOUR);
        int dtMin = cal.get(Calendar.MINUTE);
        int dtSeg = cal.get(Calendar.SECOND);
        int dtAmPm = cal.get(Calendar.AM_PM);
        
        if (dtAmPm == 1) {
            dtHr += 12;
        }
        
        strDia = String.valueOf(dtDia);
        strMes = String.valueOf(dtMes);
        strAno = String.valueOf(dtAno);
        strHr = String.valueOf(dtHr);
        strMin = String.valueOf(dtMin);
        strSeg = String.valueOf(dtSeg);
        
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
        
        String strData = strDia + "/" + strMes + "/" + strAno + " " +
                strHr + ":" + strMin + ":" + strSeg;
        
        return strData;
    }
    
}
