package br.com.martins.vendas.vendas.action;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.BuscarDescricaoProdutoService;
import br.com.martins.vendas.services.ProdutoService;
import br.com.martins.vendas.vo.DetalheMercadoria;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.util.JsonUtil;

public class BuscarDescricaoProdutoAction {

	public ActionResult execute(JSONArray array) throws Exception {
		Integer codigoMercadoria = array.getInt(0);
		Integer codigoFilialExpedicao = array.getInt(1);
		Integer codigoFilialFaturamento = array.getInt(2);
		Integer codigoCliente = array.getInt(3);
		Integer codigoTerritorioCliente = array.getInt(4);
		Integer codigoRegraDistribuicao = array.getInt(5);
		String codigoEstadoDestino = array.getString(6);

		DetalheMercadoria detalheMercadoria = ProdutoService
				.carregaDetalheProduto(codigoMercadoria, codigoFilialExpedicao,
						codigoFilialFaturamento, codigoCliente,
						codigoTerritorioCliente, codigoRegraDistribuicao,
						codigoEstadoDestino);

		// C�digo necess�rio para poder testar, ja que n possui o codigo do
		// banco (Tempor�rio)
		//detalheMercadoria.codigo = 8;
		//detalheMercadoria.codigoGrupo = 33;

		if (detalheMercadoria.codigo != null) {
			detalheMercadoria.diretorioImagemResolucaoMinima = BuscarDescricaoProdutoService.encontrarPathImagens(detalheMercadoria.codigoGrupo,detalheMercadoria.codigo, 120);
			detalheMercadoria.diretorioImagemResolucaoMaxima = BuscarDescricaoProdutoService.encontrarPathImagens(detalheMercadoria.codigoGrupo,detalheMercadoria.codigo, 400);
			detalheMercadoria.fichaTecnica = BuscarDescricaoProdutoService.encontrarPathFichaTecnica(detalheMercadoria.codigo);
			if (detalheMercadoria.diretorioImagemResolucaoMaxima.isEmpty() || detalheMercadoria.diretorioImagemResolucaoMinima.isEmpty()) {
				detalheMercadoria.diretorioImagemResolucaoMinima.add("../includes/images/noimage.jpg");
				detalheMercadoria.diretorioImagemResolucaoMaxima.add("../includes/images/noimage.jpg");
			}
			return new ActionResult(Status.OK, JsonUtil.toJson( "detalheMercadoria", detalheMercadoria));
		}
		return new ActionResult(Status.ERROR, new JSONObject().put("mensagem",
				"Dados da Mercadoria não encontrados"));
	}

}
