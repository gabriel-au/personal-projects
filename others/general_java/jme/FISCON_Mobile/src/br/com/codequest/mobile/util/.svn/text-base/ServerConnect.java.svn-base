package br.com.codequest.mobile.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import br.com.codequest.mobile.MobileException;

public class ServerConnect extends Thread{

    private String parameters = null;
    private static String sessionid;

    public ServerConnect(){
    	
    }
    
    public String connect() throws MobileException {
        return connect(Propriedades.URL_SERVER);
    }

    public synchronized String connect(String url) throws MobileException {
        HttpConnection hc = null;
        OutputStream output = null;
        InputStream input = null;
        ByteArrayOutputStream outResult = null;
        try {
            //url = url;// + "?" + parameters; //criarHash(parameters);

            if (sessionid != null && !sessionid.equals("")) {
            	parameters = "jsessionid" + sessionid +"&" +parameters;
            }else{
            	sessionid=null;
            }
            
            System.out.println(url);

            hc = (HttpConnection) Connector.open(url);
            if (sessionid != null)
                hc.setRequestProperty("cookie", sessionid);
            hc.setRequestMethod(HttpConnection.POST);
            hc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStreamWriter outputWriter = new OutputStreamWriter(hc.openOutputStream());
			outputWriter.write(parameters);
			System.out.println("PARAMETROS: "+parameters);
			outputWriter.flush();
            
            input = hc.openInputStream();

            outResult = new ByteArrayOutputStream();

            int c = 0;
            while ((c = input.read()) != -1) {
                outResult.write(c);
            }

            /*int k = 0; 
            while (hc.getHeaderFieldKey(k) != null) { 
            	String key = hc.getHeaderFieldKey(k); 
            	String value = hc.getHeaderField(k);
            	System.out.println(key+":"+value);
            	k++;
            }*/
            int k = 0; 
            while (hc.getHeaderFieldKey(k) != null) { 
            	String key = hc.getHeaderFieldKey(k); 
            	System.out.println(key+":"+hc.getHeaderField(key));
            	k++;
            }
            
            String cookie = hc.getHeaderField("Set-cookie");
            if (cookie != null) {
              int semicolon = cookie.indexOf(';');
              sessionid = cookie.substring(0, semicolon);
            }
            System.out.println("SESSAO "+sessionid);
            
            hc.close();
            
            byte[] compactado = outResult.toByteArray();
            
            byte[] descompactado = GZIP.inflate(compactado);

            return new String(descompactado);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new MobileException(ex);

        } finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (input != null) {
                    input.close();
                }

                if (hc != null) {
                    hc.close();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
                throw new MobileException(ex);
            }
        }

    }

    public void addProperty(String key, String value) {
        if (parameters == null) {
            parameters = key + "=" + value;
        } else {
            parameters += "&" + key + "=" + value;
        }
    }
   
}
