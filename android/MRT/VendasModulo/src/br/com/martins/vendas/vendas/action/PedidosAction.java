package br.com.martins.vendas.vendas.action;


import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.martins.vendas.enums.PedidoStatus;
import br.com.martins.vendas.enums.TipoVendaPedido;
import br.com.martins.vendas.services.CaminhaoService;
import br.com.martins.vendas.services.ClienteService;
import br.com.martins.vendas.services.CondicaoPagamentoService;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.services.pedido.FecharPedidoService;
import br.com.martins.vendas.services.pedido.PedidoService;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.CondicaoPagamento;
import br.com.martins.vendas.vo.Pedido;
import br.com.martins.vendas.vo.Representante;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class PedidosAction {

	private static final String TAG = PedidosAction.class.getName();

	/**
	 * 
	 * @param array
	 * @return
	 */
	public ActionResult execute(JSONArray array) {
		ActionResult result = new ActionResult(Status.OK);

		try {
			Integer codigoCliente = !array.isNull(0) ? array.getInt(0) : null;
			List<Pedido> pedidos = PedidoService.obtemTodosPedidos(codigoCliente);
			result = new ActionResult(Status.OK, JsonUtil.toJson("pedidos", pedidos));
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return result;
	}

	/**
	 * 
	 * @param array
	 * @return
	 */
	public ActionResult detalhaPedidoPorCAD(JSONArray array) {
		ActionResult result = new ActionResult(Status.OK);
		
		try {			

			List<Pedido> pedidos = PedidoService.carregaDetalhesPedido();	
			result = new ActionResult(Status.OK, JsonUtil.toJson("detalhesPedido", pedidos));

		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return result;
	}

	/**
	 * 
	 * @param array
	 * @return
	 */
	public ActionResult calculaDetalhes(JSONArray array) {
		ActionResult result = new ActionResult(Status.OK);

		try {			
			Integer filialExp = !array.isNull(13) ? array.getInt(13) : null;

			List<Pedido> lista = PedidoService.calculaValoresFilial(filialExp);
			result = new ActionResult(Status.OK, JsonUtil.toJson("calculoPedido", lista));

		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return result;
	}

	/**
	 * 
	 * @return
	 */
	public ActionResult carregaDetalhePedidoCaminhao() {
		ActionResult result = new ActionResult(Status.OK);

		try {
			List<Item> itensCaminhao = PedidoService.carregaDetalhesPedidoCaminhao();
			result = new ActionResult(Status.OK, JsonUtil.toJson("itensCaminhao", itensCaminhao));
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return result;
	}

	/**
	 * Método responsável por excluir um pedido e seus itens.
	 * 
	 * @param codigoPedido
	 *            objeto do tipo <code>java.lang.Integer</code> com o valor do
	 *            código do pedido.
	 * @return objeto do tipo
	 *         <code>com.brq.mobile.framework.core.ActionResult</code> referente
	 *         a ao retorno da ação solicitada.
	 */
	public ActionResult excluiPedido(JSONArray array) {
		try {
			boolean resultado = PedidoService.excluiPedido(array.getInt(0));
			if (resultado) {
				return new ActionResult(Status.OK, new JSONObject().put("mensagem", "Pedido excluído com sucesso!"));
			} else {
				return new ActionResult(Status.ERROR, new JSONObject().put("mensagem", "O pedido não pode ser excluído, pois o mesmo já se encontra preparado."));
			}
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
	}

	public ActionResult excluiMercadoriaLista(JSONArray array) throws JSONException {
		ActionResult result = new ActionResult(Status.OK);

		try {
			Integer codigoMercadoria=array.getInt(0);
			Integer numeroNotaFiscal=array.getInt(1);
			boolean resultado = CaminhaoService.excluiMercadoriaLista(codigoMercadoria,numeroNotaFiscal);		
			if (resultado) {
				return new ActionResult(Status.OK, new JSONObject().put("mensagem", "Item excluído com sucesso!"));
			}
		}catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}		
		return result;				
	}
	
	public ActionResult calculaValoresNF(JSONArray array) throws JSONException {

		ActionResult result = new ActionResult(Status.OK);
		try {	
			Integer codigoFilial = array.getInt(0);
			List<Pedido> listaValores = PedidoService.calculaValoresNF(codigoFilial);
			result = new ActionResult(Status.OK, JsonUtil.toJson("listaValores", listaValores));
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return result;
	}
	
	public ActionResult gravaPedido(JSONArray array) throws Exception {
		Representante representante = new Representante();
		Pedido pedido = new Pedido();
		Cliente cliente = new Cliente();
		
		JSONObject json = array.getJSONObject(0); 
		representante.codigoRepresentante = json.getString("codigoRepresentante");
		representante.percentualBonificacaoPedido = json.getDouble("percentualBonificacaoPedido");
		
		// Obtem o tipo de venda do pedido selecionado na tela pelo usuario
		Integer tipoVendaPedido = TipoVendaPedido.toInteger(json.getString("tipoVendaPedido"));
		switch (tipoVendaPedido) {
		case 0:
			pedido.tipoVendaPedido = TipoVendaPedido.NORMAL;
			break;
		case 1:
			pedido.tipoVendaPedido = TipoVendaPedido.SIMPLIFICADA;
			break;
		default:
			pedido.tipoVendaPedido = null;
			break;
		}
		
		// Obtem o tipo de pedido, C = Cotacao e P = Pedido
		pedido.tipoPedido = json.getString("tipoPedido");
		
		// Obtem dados do cliente
		cliente = ClienteService.obterDetalheCliente(json.getInt("codigoCliente"));
		pedido.cliente = cliente;
		
		// Obtem a versao do sistema
		String versaoSistema = json.getString("versao_sistema");
		versaoSistema = versaoSistema.replace(".", "");
		
		// Obtem o numero do pedido
		pedido.numeroPedido = String.valueOf(json.getInt("numeroPedido"));
		
		// Obtem as filiais de expedicao e faturamento
		pedido.filialExpedicao = json.getInt("filialExpedicao");
		pedido.filialFaturamento = json.getInt("filialFaturamento");
		
		// Realiza calculos de totalizacao do pedido
		pedido = FecharPedidoService.fechaPedido(pedido, representante, versaoSistema, false); // TODO MUDAR PARA TRUE
		
		// Obtem flat para preparar, ou nao, o pedido
		boolean seraPreparado = json.getBoolean("seraPreparado");
				
		// Realiza a gravacao (insercao ou alteracao) de um pedido, podendo este ser preparado ou nao.
		FecharPedidoService.gravaPedido(pedido, Integer.valueOf(representante.codigoRepresentante), versaoSistema, seraPreparado);
		
//		json = new JSONObject();
//		json.put("condicaoPagamento", JsonUtil.toJson(condicaoPagamento));
//		return new ActionResult(Status.OK, json);
		
		return new ActionResult(Status.OK);
	}

	/**
	 * Metodo responsavel pela realizacao do fechamento do pedido.
	 * 
	 * @param array
	 *            objeto do tipo <code>org.json.JSONArray</code> com os dados
	 *            enviados da tela pelo usuario.
	 * @return objeto do tipo
	 *         <code>com.brq.mobile.framework.core.ActionResult</code> com os
	 *         dados que deverao ser retornados para uma determinada tela.
	 * @throws Exception
	 *             objeto do tipo <code>java.lang.Exception</code> que sera
	 *             lancado caso algum erro inesperado ocorra.
	 */
	public ActionResult fechaPedido(JSONArray array) throws Exception {
		Representante representante = new Representante();
		Pedido pedido = new Pedido();
		Cliente cliente = new Cliente();
		CondicaoPagamento condicaoPagamento = new CondicaoPagamento();
		
		JSONObject json = array.getJSONObject(0); 
		representante.codigoRepresentante = json.getString("codigoRepresentante");
		representante.percentualBonificacaoPedido = json.getDouble("percentualBonificacaoPedido");
		
		// Obtem o tipo de venda do pedido selecionado na tela pelo usuario
		Integer tipoVendaPedido = TipoVendaPedido.toInteger(json.getString("tipoVendaPedido"));
		switch (tipoVendaPedido) {
		case 0:
			pedido.tipoVendaPedido = TipoVendaPedido.NORMAL;
			break;
		case 1:
			pedido.tipoVendaPedido = TipoVendaPedido.SIMPLIFICADA;
			break;
		default:
			pedido.tipoVendaPedido = null;
			break;
		}
		
		// Obtem dados do cliente
		cliente = ClienteService.obterDetalheCliente(json.getInt("codigoCliente"));
		pedido.cliente = cliente;
		
		// Obtem dados da Condicao de Pagamento
		condicaoPagamento = CondicaoPagamentoService.buscaCondicaoPagamento(json.getInt("codigoCondicaoPagamento"));
//		condicaoPagamento = new CondicaoPagamento().toObject(json.get("condicaoPagamento"));

		// Obtem a versao do sistema
		String versaoSistema = json.getString("versao_sistema");
		versaoSistema = versaoSistema.replace(".", "");
		
		// Obtem o numero do pedido
		pedido.numeroPedido = String.valueOf(json.getInt("numeroPedido"));
		
		// Obtem o status do pedido
//		pedido.statusPedido = PedidoService.obtemStatusPcasos();

		// Obtem as filiais de expedicao e faturamento
		pedido.filialExpedicao = json.getInt("filialExpedicao");
		pedido.filialFaturamento = json.getInt("filialFaturamento");
		
		// Inicializa os totalizadores do fechamento do pedido
		pedido = FecharPedidoService.fechaPedido(pedido, representante, versaoSistema, false); // TODO MUDAR PARA TRUE
		
		//
		json = new JSONObject();
		json.put("pedido", JsonUtil.toJson(pedido));
		json.put("condicaoPagamento", JsonUtil.toJson(condicaoPagamento));
		
		// Verifica se ha erros no pedido e redireciona a tela pertinente
		if (pedido.mensagens.size() > 0){
			// Tela de Mensagens de Erro do Pedido
			return new ActionResult(Status.ERROR, json);
		} else {
			// Tela de Totalizacao do Pedido
			return new ActionResult(Status.OK, json);
		}
		
	}
	
	public ActionResult verificaStatusPcasos(JSONArray array) throws Exception {
		// Verifica se existe uma linha na tabela, caso negativo, realiza a correcao.
		PedidoService.verificaTabelasStatusPedido();
		
		// Verifica o status do pedido na tabela PCASOS
		PedidoStatus pedidoStatus = PedidoService.obtemStatusPcasos();
		
		// Obtem o codigo do cliente
		String[] dados = PedidoService.obtemDadosPcasos();
		Integer codigoCliente = dados[0] != null ? Integer.valueOf(dados[0]) : 0;
		Integer codigoClienteTerritorio = dados[5] != null ? Integer.valueOf(dados[5]) : 0;
		Cliente cliente = ClienteService.obterDetalheCliente(codigoCliente, codigoClienteTerritorio);
		
		JSONObject json = new JSONObject();
		if (null == pedidoStatus) { // || codigoClientePcasos != array.getInt(0)) {
			json.put("numeroStatus", PedidoStatus.EM_CRIACAO.getValue());
		} else {
			json.put("numeroStatus", pedidoStatus.getValue());
		}
		
		if (null != cliente) {
			json.put("descricaoCliente", cliente.codigoCliente + " - " + cliente.nomeCliente);
		}
		
		return new ActionResult(Status.OK, json);
	}
	
	/**
	 * Metodo responsavel por limpar todas as tabelas temporarias ao iniciar um
	 * novo pedido.
	 * 
	 * @param array
	 *            objeto do tipo <code>org.json.JSONArray</code> com os dados
	 *            enviados da tela pelo usuario.
	 * @return objeto do tipo
	 *         <code>com.brq.mobile.framework.core.ActionResult</code> com os
	 *         dados que deverao ser retornados para uma determinada tela.
	 * @throws Exception
	 *             objeto do tipo <code>java.lang.Exception</code> que sera
	 *             lancado caso algum erro inesperado ocorra.
	 */
	public ActionResult criaNovoPedido(JSONArray array) throws Exception {
		PedidoStatus pedidoStatus = PedidoService.obtemStatusPcasos();
		
		boolean recuperavel = array.getBoolean(0);
		Integer numeroPedido;
		
		JSONObject json = new JSONObject();
		
		// Verificar se o pedido precisa ser recuperado
		if (recuperavel && null != pedidoStatus && (pedidoStatus.equals(PedidoStatus.CRIADO))) {
			
			// Obtem os dados da tabela PCASOS
			String[] dadosClientePcasos = PedidoService.obtemDadosPcasos();
			
			// Obtem dados do cliente a ser recuperado
			Cliente cliente = ClienteService.obterDetalheCliente(Integer.valueOf(dadosClientePcasos[0]), Integer.valueOf(dadosClientePcasos[5]));

			// Verifica se eh um pedido existente em PCAPED, o processo pode ser interrompido em uma alteracao de pedido.
			numeroPedido = Integer.valueOf(dadosClientePcasos[7]);
			if (!PedidoService.isPedidoExiste(numeroPedido)) {
				numeroPedido = PedidoService.obtemNumeroUltimoPedidoPcapar() + 1;
			}
			
			json.put("cliente", JsonUtil.toJson(cliente));
			json.put("numeroPedido", numeroPedido);

		} else {
			// Gera um novo numero de pedido
			numeroPedido = PedidoService.criaNovoPedido();
			json.put("numeroPedido", numeroPedido);

		}
		
		return new ActionResult(Status.OK, json);
	}
	
	public ActionResult abandonaPedido(JSONArray array) throws Exception {
		PedidoService.atualizaStatusPcasos(PedidoStatus.EM_CRIACAO);
		PedidoService.limpaTabelasTemporarias();
		return new ActionResult(Status.OK);
	}
	
	public ActionResult limpaTabelasTemporaria(JSONArray array) throws Exception {
		ActionResult result = new ActionResult(Status.OK);
		
		JSONObject json = array.getJSONObject(0); 
		Integer numeroPedido = json.getInt("numeroPedido");
		
		try {
			if(!PedidoService.isPedidoPreparado(numeroPedido)){
				PedidoService.limpaTabelasTemporarias();
			}else{
				return new ActionResult(Status.ERROR, new JSONObject().put("mensagem", "O pedido não pode ser alterado, pois o mesmo já se encontra preparado."));
			}
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}
		return result;
	}
	
	public ActionResult regeraPedidoByFilial(JSONArray array) throws Exception {
		ActionResult result = new ActionResult(Status.OK);
		
		JSONObject json = array.getJSONObject(0); 
		Integer numeroPedido = json.getInt("numeroPedido");
		
		try {			
			if(PedidoService.isPedidoPreparadoReprovado(numeroPedido)){
				PedidoService.limpaTabelasTemporarias();
			}else{
				return new ActionResult(Status.ERROR, new JSONObject().put("mensagem", "O sistema permite a regeração de pedidos apenas se estiverem\nreprovados. Para regeração de itens use a opção disponível na\nlistagem de itens cortados."));
			}
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return result;
	}
	
	public ActionResult regeraPedidoByCortes(JSONArray array) throws Exception {

//		ActionResult result = new ActionResult(Status.OK);
		
	//	JSONObject json = array.getJSONObject(0); 
		JSONObject json = new JSONObject();
		Integer numeroPedido = array.getInt(0);
		
		try {			
			if(PedidoService.verificaMotivoCortes(numeroPedido)){
				PedidoService.limpaTabelasTemporarias();			
				
				json.put("numeroPedido", numeroPedido);
			}else{
				return new ActionResult(Status.ERROR, new JSONObject().put("mensagem", "Nenhum item foi recuperado !!!"));
			}
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e.getLocalizedMessage(), e);
		}

		return new ActionResult(Status.OK, json);
	}
	
}