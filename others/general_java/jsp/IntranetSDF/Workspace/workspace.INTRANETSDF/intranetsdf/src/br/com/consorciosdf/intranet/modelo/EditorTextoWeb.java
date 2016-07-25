/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.consorciosdf.intranet.modelo;

/**
 *
 * @author Administrador
 */
public class EditorTextoWeb {

    public static String nl2br(String str) {
        String output = "";

        if (str != null) {
            String x = str;

            for (int i = 0; i < x.length(); i++) {
                if (x.codePointAt(i) == 10) {
                    output += "<br>";
                } else {
                    output += x.charAt(i);
                }
            }
        }

        return output;
    }

    public static String br2nl(String str) {
        String output = "";

        output=str.replaceAll("<br>", "\n");

        return output;
    }
}
