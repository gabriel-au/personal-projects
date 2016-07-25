package br.com.martins.vendas.services;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.brq.mobile.framework.core.ConfigAccess;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.FileUtil;
import com.brq.mobile.framework.util.StringUtil;

/**
 * Cadastro de chave de acesso NFe
 * Arquivo fonte C++ FormCadNfe.cpp
 * Modulo Sistema Pedidos
 * 
 * @author BRQ Mobile Team
 * @since 19/01/12
 */
public class CadastroChaveAcessoNFeService {

	static final String RADICAL_CNPJ_MARTINS = "43214055";
	static final String NOME_ARQUIVO_EXPORTACAO_NFE = "CADNFE00.NOT";
	static final String DIR_ARQUIVO_EXPORTACAO_NFE = ConfigAccess.getConfig().diretoryBase();

	private static final String TAG = CadastroChaveAcessoNFeService.class.getName();

	public static boolean cadastrarChaveAcessoNFe(String chaveAcesso, StringBuilder mensagem, String tipoEntidade, String codigoRepresentante) throws Exception {
		if (validarDados(chaveAcesso.trim(), mensagem)) {
			exportarDaddos(chaveAcesso.trim(), tipoEntidade, codigoRepresentante);
			mensagem.append("Chave de acesso cadastrada com sucesso.");
		} else {
			return false;
		}
		return true;
	}

	private static void exportarDaddos(String chaveAcesso, String tipoEntidade, String codigoRepresentante) throws Exception {
		try {

			String chaveAcessoAux = chaveAcesso;

			//Representante rep = RepresentanteService.findRepresentante();

			/*String tipoEntidade = StringUtil.preencheZerosEsquerda(rep.tipoEntidade, 2);
			String codigoRepresentante = StringUtil.preencheZerosEsquerda(rep.codigoRepresentante, 5);
*/

			chaveAcessoAux += StringUtil.preencheZerosEsquerda(tipoEntidade, 2);
			chaveAcessoAux += StringUtil.preencheZerosEsquerda(codigoRepresentante, 5);

			gravaArquivo(chaveAcessoAux, DIR_ARQUIVO_EXPORTACAO_NFE + "/" + NOME_ARQUIVO_EXPORTACAO_NFE);

		} catch (IOException e) {
			Log.e(TAG, e.getMessage());
			throw e;
		}
	}

	private static void gravaArquivo(String chaveAcessoAux, String arquivo) throws Exception {
		FileUtil.gravaArquivo(chaveAcessoAux, arquivo, true);
	}

	private static boolean validarDados(String chaveAcesso, StringBuilder mensagem) {
		if (!validarChaveAcesso(chaveAcesso.trim(), mensagem)) {
			return false;
		}

		String cnpj = chaveAcesso.substring(6, 20).trim();
		if (!validarCNPJ(cnpj)) {
			mensagem.append("Chave inválida - Erro 1");
			return false;
		}

		if (isCNPJMartins(cnpj)) {
			// Verificar cnpj do Martins
			mensagem.append("Chave inválida - Esta chave de acesso é de uma nota fiscal do Martins!");
			return false;
		}

		// Validar uf da NFe
		if (!isEstadoExiste(chaveAcesso)) {
			// 'UF inexistente!"
			mensagem.append("Chave inválida - Erro 2");
			return false;
		}
		return true;
	}

	private static boolean isEstadoExiste(String chaveAcesso) {

		int uf = Integer.valueOf(chaveAcesso.substring(0, 2));
		return ((uf == 11 || uf == 12 || uf == 13 || uf == 14 || uf == 15 || uf == 16 || uf == 17 || uf == 21 || uf == 22 || uf == 23 || uf == 24 || uf == 25 || uf == 26 || uf == 27 || uf == 28 || uf == 29 || uf == 31 || uf == 32 || uf == 33
				|| uf == 35 || uf == 41 || uf == 42 || uf == 43 || uf == 50 || uf == 51 || uf == 52 || uf == 53));
	}

	private static boolean isCNPJMartins(String cnpj) {
		String radical = cnpj.substring(0, 8);
		return radical.equals(RADICAL_CNPJ_MARTINS);
	}

	private static boolean validarCNPJ(String cnpj) {
		Integer[] iNumero = new Integer[14];
		int iSoma;
		int i;

		int iResultado1;
		int iResultado2;

		for (i = 0; i < 14; i++) {
			iNumero[i] = Integer.valueOf(cnpj.substring(i, i + 1));
		}

		iSoma = iNumero[0] * 5 + iNumero[1] * 4 + iNumero[2] * 3 + iNumero[3] * 2 + iNumero[4] * 9 + iNumero[5] * 8 + iNumero[6] * 7 + iNumero[7] * 6 + iNumero[8] * 5 + iNumero[9] * 4 + iNumero[10] * 3 + iNumero[11] * 2;

		iSoma = iSoma - (11 * (iSoma / 11));

		if (iSoma == 0 || iSoma == 1) {
			iResultado1 = 0;
		} else {
			iResultado1 = 11 - iSoma;
		}

		if (iResultado1 == iNumero[12]) {
			iSoma = iNumero[0] * 6 + iNumero[1] * 5 + iNumero[2] * 4 + iNumero[3] * 3 + iNumero[4] * 2 + iNumero[5] * 9 + iNumero[6] * 8 + iNumero[7] * 7 + iNumero[8] * 6 + iNumero[9] * 5 + iNumero[10] * 4 + iNumero[11] * 3 + iNumero[12] * 2;

			iSoma = iSoma - (11 * (iSoma / 11));
			if (iSoma == 0 || iSoma == 1) {
				iResultado2 = 0;
			} else {
				iResultado2 = 11 - iSoma;
			}

			if (iResultado2 == iNumero[13]) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}
	public static boolean validarChaveAcesso(String chaveAcesso, StringBuilder mensagem) {

		// Gera a chave de acesso da nota fiscal
		// sendo que a mesma deve conter 44 digitos da seguinte forma
		// ________________________________________________________________________________________
		// |_______________| UF | AnoMes | CNPJ Emit | Modelo | S�rie | Num NFe | Codigo Num | DV |
		// | Qtd Caracters | 02 | 04 | 14 | 02 | 03 | 09 | 09 | 01 |
		// ����������������������������������������������������������������������������������������

		if (chaveAcesso.length() < 44) {
			mensagem.append("Chave de acesso faltando digitos!");
			return false;
		} else {
			if (chaveAcesso.length() > 44) {
				mensagem.append("Chave de acesso com digitos a mais!");
				return false;
			}
		}

		if (!hasOnlyNumber(chaveAcesso)) {
			mensagem.append("Chave de acesso contem valores inválidos!");
			return false;
		}

		int iDigitoVerificadorEntrada = Integer.valueOf(chaveAcesso.substring(43, 44));
		int iDigitoVerificador = geraDVChaveAcesso(chaveAcesso.substring(0, 43));

		if (iDigitoVerificador != iDigitoVerificadorEntrada) {
			mensagem.append("Chave de acesso inválida!");
			return false;
		}

		return true;
	}

	private static boolean hasOnlyNumber(String chaveAcesso) {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(chaveAcesso);
		return m.matches();
	}

	private static int geraDVChaveAcesso(String strChaveAcesso) {
		if (strChaveAcesso.length() == 43) {
			int iPeso = 4;
			int iVlrSoma = 0;
			int iVlrVar;
			for (int i = 0; i <= 42; i++) {
				iVlrVar = Integer.valueOf(strChaveAcesso.substring(i, i + 1));
				iVlrSoma = iVlrSoma + (iPeso * iVlrVar);
				if (iPeso != 2) {
					iPeso = iPeso - 1;
				} else {
					iPeso = 9;
				}
			}
			int iResultado;
			iResultado = iVlrSoma % 11;
			if (iResultado == 0 || iResultado == 1) {
				return 0;
			} else {
				return (11 - iResultado);
			}
		} else {
			return -1;
		}
	}

}