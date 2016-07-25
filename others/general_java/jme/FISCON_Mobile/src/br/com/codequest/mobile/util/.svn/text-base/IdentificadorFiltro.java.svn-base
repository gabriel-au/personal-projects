package br.com.codequest.mobile.util;

import java.util.Hashtable;

import javax.microedition.rms.RecordFilter;

public class IdentificadorFiltro extends Filtro implements RecordFilter {
	private String searchText = null;

    public IdentificadorFiltro(String searchText)
    {
      // Text to find
      this.searchText = searchText.toLowerCase();
    }

    public boolean matches(byte[] candidate){
      String str = new String(candidate).toLowerCase();
      Hashtable registro = StringUtils.stringToHashtable(str);
      // Does the text exist?
      if(registro.get("identificador")!=null && ((String)registro.get("identificador")).equals(searchText)){
    	  return true;  
      }else{
    	  return false;
      }
    	  
    }



}
