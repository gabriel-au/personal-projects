package br.gov.infraero.site.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.gov.infraero.site.Componente;

public class CriarArquivo {

	private static String path;
	
	private CriarArquivo(){
		
	}
	
	public static void salvar(String fileName,Componente componente){
		try {
			FileWriter writer = new FileWriter(getPath()+fileName);
			writer.write(componente.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setPath(String path) {
		CriarArquivo.path=path;
		
	}
	public static String getPath(){
		if(path==null){
			path = "WebContent"+File.separator;
		}
		return path;
	}
	
}
