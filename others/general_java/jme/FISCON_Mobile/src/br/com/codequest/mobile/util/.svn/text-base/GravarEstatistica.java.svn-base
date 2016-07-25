package br.com.codequest.mobile.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

/**
 * 
 * @author Gustavo Marcelo
 *
 */
public class GravarEstatistica {

	private static String path = System.getProperty("fileconn.dir.private");
	private static String fileName = "estatistica";

	public static void gravarConsulta(int somaMulta, int somaConsultaVeiculo, int somaConsultaCNH) {
		String texto = loadFile();
		String parametros[] = StringUtils.split(texto, '#', 0);
		int multas;
		int consultasVeiculos;
		int consultasCNH;
		if (parametros == null || parametros.length != 3) {
			multas = 0;
			consultasVeiculos = 0;
			consultasCNH = 0;
		}else{
			multas = Integer.parseInt(parametros[0]);
			consultasVeiculos = Integer.parseInt(parametros[1]);
			consultasCNH = Integer.parseInt(parametros[2]);
		}
		multas+=somaMulta;
		consultasVeiculos+=somaConsultaVeiculo;
		consultasCNH+=somaConsultaCNH;
		
		texto= multas +"#"+consultasVeiculos+"#"+consultasCNH;
		saveFile(texto);
	}

	public static String[] LerConsula(){
		String texto = loadFile();
		if(texto!=null){
			String parametros[] = StringUtils.split(texto, '#', 0);
			if (parametros != null && parametros.length == 3) {
				return parametros;
			}
		}
		return new String[]{"0","0","0"};
	}
	
	// salvar
	private static void saveFile(String texto) {
		try {
			String url = path + fileName;
			// String string = textBox.getString();
			byte data[] = texto.getBytes();
			FileConnection fconn = (FileConnection) Connector.open(url,
					Connector.READ_WRITE);
			if (!fconn.exists()) {
				fconn.create();
			} else {
				fconn.delete();
				fconn.create();
			}
			OutputStream ops = fconn.openOutputStream();
			ops.write(data);
			ops.close();
			fconn.close();
		} catch (IOException ioe) {
			//System.out.println("IOException: " + ioe.getMessage());
		} catch (SecurityException se) {
			//System.out.println("Exceção de segurança:" + se.getMessage());
		}
	}

	//  
	// ler
	public static String loadFile() {
		String retorno = "";
		try {
			if(path==null){
				path="";
			}
			FileConnection fc = (FileConnection) Connector
					.open(path + fileName);
			if (!fc.exists()) {
				//throw new IOException("Arquivo não existe");
				return null;
			}
			InputStream is = fc.openInputStream();
			byte b[] = new byte[1024];
			int length = is.read(b, 0, 1024);
			retorno = new String(b, 0, length);
			is.close();
			fc.close();
		} catch (IOException ioe) {
			//System.out.println("IOException: " + ioe.getMessage());
		} catch (SecurityException se) {
			//System.out.println("Exceção de segurança:" + se.getMessage());
		} catch (Exception e) {
			//System.out.println("Exceção de segurança:" + se.getMessage());
		}
		return retorno;

	}

}
