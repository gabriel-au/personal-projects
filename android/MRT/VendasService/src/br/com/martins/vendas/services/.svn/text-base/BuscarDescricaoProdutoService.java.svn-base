package br.com.martins.vendas.services;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.brq.mobile.framework.util.EnviromentIdentifier;
import com.brq.mobile.framework.util.StringUtil;

public class BuscarDescricaoProdutoService {
	
	private static final String  DIRETORIO_FICHA_TECNICA_ANDROID = "mnt/sdcard/martins/fichaTecnica/";
	private static final String  DIRETORIO_IMAGEM_ANDROID = "mnt/sdcard/martins/imagens/";
	private static final String  DIRETORIO_FICHA_TECNICA_WINDOWS = "c:/mnt/sdcard/fichaTecnica/";
	private static final String  DIRETORIO_IMAGEM_WINDOWS = "c:/mnt/sdcard/imagens/";
	
	

	public static List<String> encontrarPathImagens(Integer codigoGrupo, Integer codigoMercadoria, Integer resolucao) {

		final Integer tempCodigoGrupo = codigoGrupo;
		final String tempCodigoGrupoProduto = StringUtil.preencheZerosEsquerda(codigoGrupo.toString(), 2).concat(StringUtil.preencheZerosEsquerda(codigoMercadoria.toString(), 6));

		String[] listaCaminhoImagens;

		// montando caminho da imagem, com o grupo do produto e resolucao
		File caminhoImagem = new File(montarDiretorioImagensRaiz(
				tempCodigoGrupo, resolucao));

		// adicionando o caminho dos aquivos encontrados\
		listaCaminhoImagens = caminhoImagem.list(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				return name.matches(tempCodigoGrupoProduto + ".*\\.jpg$");
			}
		});

		return montarDiretorioArquivo(listaCaminhoImagens, caminhoImagem);
	}

	public static String encontrarPathFichaTecnica(Integer codigoMercadoria) {

		final String tempCodigoMercadoria = StringUtil.preencheZerosEsquerda(
				codigoMercadoria.toString(), 6);

		String[] listaCaminhoFichaTecnica;

		List<String> listaDiretorios = new ArrayList<String>();
		
		// montando caminho da ficha tecnica
		File caminhoFichaTecnica= new File(montarDiretorioFichaTecnicaRaiz());

		// adicionando o caminho dos aquivos encontrados
		listaCaminhoFichaTecnica = caminhoFichaTecnica.list(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				return name.matches(tempCodigoMercadoria + ".*\\.html$");
			}
		});
		listaDiretorios = montarDiretorioArquivo(listaCaminhoFichaTecnica, caminhoFichaTecnica);
		
		return !listaDiretorios.isEmpty() ? listaDiretorios.get(0) : null ;

	}

	public static List<String> montarDiretorioArquivo(
			String[] listaCaminhoImagens, File caminhoImagem) {

		List<String> diretorioImagens = new ArrayList<String>();

		if (listaCaminhoImagens != null) {
			// monta caminho Imagens
			for (String caminho : listaCaminhoImagens) {
				String aux = "file:///" + caminhoImagem.getPath() + "/"	+ caminho.toString();
				String caminhoFinal = aux.replace("\\", "/");
				diretorioImagens.add(caminhoFinal);
			}
			Collections.sort(diretorioImagens);
		}
		return diretorioImagens;

	}

	public static String montarDiretorioImagensRaiz(Integer codigoGrupo,
			Integer resolucao) {

		StringBuffer patch = new StringBuffer();
		if (EnviromentIdentifier.isAndroid()) {
			patch.append(DIRETORIO_IMAGEM_ANDROID);
		} else if (EnviromentIdentifier.isWindows()) {
			patch.append(DIRETORIO_IMAGEM_WINDOWS);
		}
	
		return patch.append(codigoGrupo).append("/").append(resolucao).toString();
	}

	public static String montarDiretorioFichaTecnicaRaiz() {

		StringBuffer patch = new StringBuffer();
		if (EnviromentIdentifier.isAndroid()) {
			patch.append(DIRETORIO_FICHA_TECNICA_ANDROID);
		} else if (EnviromentIdentifier.isWindows()) {
			patch.append(DIRETORIO_FICHA_TECNICA_WINDOWS);
		}

		return patch.toString();
	}
	
	// public static String carregarImagem(String caminhoImagem) throws
		// FileNotFoundException, IOException{
		// FileImageInputStream image = new FileImageInputStream(new
		// File(caminhoImagem));
		// ByteArrayOutputStream out = new ByteArrayOutputStream();
		// byte[] buff = new byte[2048];
		// int read = 0;
		// while((read = image.read(buff)) != -1){
		// out.write(buff,0,read);
		// }
		// out.close();
		// image.close();
		//
		// return
		// (Constants.DATA_IMG_JPEG_BASE64.concat(Base64.encode(out.toByteArray())));
		// }

}
