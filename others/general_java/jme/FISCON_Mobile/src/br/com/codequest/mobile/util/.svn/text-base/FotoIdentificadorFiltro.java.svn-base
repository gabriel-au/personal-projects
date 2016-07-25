package br.com.codequest.mobile.util;

import java.util.Hashtable;

import javax.microedition.rms.RecordFilter;

import br.com.prime.fiscon.mobile.pojo.FotoPojo;
import br.com.prime.fiscon.mobile.pojo.TransactionObject;

public class FotoIdentificadorFiltro extends Filtro implements RecordFilter {
	private String id = null;
	private String tipoFoto = null;
    public FotoIdentificadorFiltro(String id, String tipoFoto)
    {
      // Text to find
      this.id = id;
      this.tipoFoto = tipoFoto;
    }

    public boolean matches(byte[] candidate){
      String str = new String(candidate);
      Hashtable registro = StringUtils.stringToHashtable(str);
      // Does the text exist?
      if((registro.get(TransactionObject.ID)!=null && 
    		  ((String)registro.get(TransactionObject.ID)).equals(id))
    		  &&
    		  (registro.get(FotoPojo.TIPOFOTO)!=null && ((String)registro.get(FotoPojo.TIPOFOTO)).equals(tipoFoto))){
    	  return true;  
      }else{
    	  return false;
      }
    	  
    }



}
