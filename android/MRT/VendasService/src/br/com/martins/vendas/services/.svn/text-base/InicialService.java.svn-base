package br.com.martins.vendas.services;

import java.util.Date;

import br.com.martins.vendas.vo.DadosComerciaisRepresentante;
import br.com.martins.vendas.vo.Inicial;
import br.com.martins.vendas.vo.Representante;
import br.com.martins.vendas.vo.Sistema;

import com.brq.mobile.framework.log.Log;

public class InicialService {

	private static final String TAG = InicialService.class.getName();

	public static Inicial carregaDadosIniciais(Integer codigoRepresentanteEquipe, Integer codigoTerritorio) throws Exception {
		try {
			Inicial inicial = new Inicial();
			carregaDadosComerciaisRepresentante(inicial);
			carregaDadosCadastraisRepresentante(inicial, codigoRepresentanteEquipe);
			carregaDadosSistema(inicial);
			carregaAvisos(inicial, codigoTerritorio);
			return inicial;
		} catch(Exception e){
			Log.e(TAG, "Erro ao buscar dados iniciais", e);
			throw e;
		}
	}

	private static void carregaAvisos(Inicial inicial, Integer codigoTerritorio) throws Exception {
		try {

			inicial.avisosCliente = AvisosService.findAvisosClientes(codigoTerritorio);
			inicial.avisosPedido = AvisosService.findAvisosPedidos();

		} catch(Exception e){
			Log.e(TAG, "Erro ao buscar dados de avisos");
			throw e;
		}

	}

	private static void carregaDadosSistema(Inicial inicial) throws Exception {
		Sistema sistema = new Sistema();
		sistema.data = new Date();
		sistema.versao = "2.0";
		sistema.ultimaSincronizacao = new Date();
		inicial.sistema = sistema;
	}

	private static void carregaDadosComerciaisRepresentante(Inicial inicial) throws Exception {
		try {
			DadosComerciaisRepresentante dadosComerciaisRepresentante = DadosComerciaisRepresentanteService.findDadosComerciaisRepresentante();
			inicial.dadosComerciaisRepresentante = dadosComerciaisRepresentante;
		} catch(Exception e){
			Log.e(TAG, "Erro ao buscar dados comerciais do representante");
			throw e;
		}
	}

	private static void carregaDadosCadastraisRepresentante(Inicial inicial, Integer codigoRepresentanteEquipe) throws Exception {
		try {
			Representante representante = RepresentanteService.findRepresentante(codigoRepresentanteEquipe);
			inicial.representante = representante;
		} catch(Exception e){
			Log.e(TAG, "Erro ao buscar dados cadastrais do representante");
			throw e;
		}
	}
}
