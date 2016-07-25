/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.consorciosdf.intranet.util;

import java.util.Date;

/**
 *
 * @author André
 */
public class DataUtil {

    public String diferecaData(Date inicial, Date fim) {// retorna a diferença entre a primeira e a segunda
        if (inicial != null && fim != null) {           // data em uma string pre difinida contendo a qtd
            long mspd = 86400000;                       // de dia, horas e minutos.
            long mshr = 3600000;
            long msm = 60000;
            long difLong = fim.getTime() - inicial.getTime();
            long dias = difLong / mspd;
            long hr = difLong / mshr;
            long mim = difLong / msm;

            while (hr >= 24) {
                hr -= 24;
            }
            while (mim >= 60) {
                mim -= 60;
            }

            if(dias < 0 || hr < 0 || mim < 0){
                return "";
            }

            return "" + dias + " dia(s), " + hr
                    + " hora(s),  e " + mim + " minuto(s)";
        }

        return "";

    }
}
