package br.com.xtrategia.fiscon.application;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Classe para carregar os arquivos
 * @author Gustavo
 *
 */
public abstract class FileLoad {

	protected String path;
	
	public FileLoad(String path){
		this.path=path;
	}
	
	public abstract void carregar(String linha);
	public abstract void iniciarBD() throws Exception;
	public abstract void finalizarBD() throws Exception;
	
	public void realizarCarga() throws Exception{
		//preparar banco
		LogApplicacao.gravar("Estabelecendo conexão com o Banco");
		iniciarBD();
		
		//ler arquivo
		LogApplicacao.gravar("Localizando Arquivo");
		FileReader arquivo = new FileReader(path);
		BufferedReader in = new BufferedReader(arquivo);
        String line = null;
        LogApplicacao.gravar("Iniciando a leitura do arquivo");
        int i=0;
		while((line=in.readLine())!=null){
			if(FisconMain.INTERROMPER_EXECUCAO){
				FisconMain.INTERROMPER_EXECUCAO=false;
				break;
			}
			//remover linhas em branco
			if(line.trim().length()>0)
				carregar(line);
			//futuramente usar para o commit
			if((++i % 100)==0){
				LogApplicacao.gravar("gravados "+ i + " registros");
			}
		}
		//finaliza o BD
		LogApplicacao.gravar("Finalizando conexão com mo Mysql");
		finalizarBD();
		LogApplicacao.gravar("Fim do Processo");
	}
	
	
}
