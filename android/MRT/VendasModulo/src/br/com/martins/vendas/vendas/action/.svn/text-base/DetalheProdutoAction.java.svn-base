package br.com.martins.vendas.vendas.action;

import java.math.BigDecimal;

import org.json.JSONArray;

import br.com.martins.vendas.services.ClienteService;
import br.com.martins.vendas.services.ItemPedidoTemporarioService;
import br.com.martins.vendas.services.MercadoriaService;
import br.com.martins.vendas.services.SimulacaoBeneficioService;
import br.com.martins.vendas.services.calculodepreco.CalculoUtil;
import br.com.martins.vendas.services.calculodepreco.Item;
import br.com.martins.vendas.vo.Cliente;
import br.com.martins.vendas.vo.ItemPedidoTemporario;
import br.com.martins.vendas.vo.SimulacaoBeneficio;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class DetalheProdutoAction {

	private static final String TAG = ItensVendidosSemanaAction.class.getName();
	
	public ActionResult execute(JSONArray array) throws Exception {
		SimulacaoBeneficio simulacaoBeneficio;
		Item item;
		ItemPedidoTemporario itemPedidoTemporario;
		Cliente cliente;
		
		try {
			simulacaoBeneficio = SimulacaoBeneficioService.criaObjetoSimulacaoBeneficio(array);
			itemPedidoTemporario = ItemPedidoTemporarioService.buscaItemTemporario(MercadoriaService.obtemCodigoMercadoria(simulacaoBeneficio.codigoMercadoria), simulacaoBeneficio.codigoFilialExpedicao, simulacaoBeneficio.codigoFilialFaturamento);

			cliente = ClienteService.obterDetalheCliente(simulacaoBeneficio.codigoCliente);
			cliente.numeroRelacionamentoCliente = ClienteService.obtemNumeroRelacionamentoCliente(simulacaoBeneficio.codigoFilialExpedicao, simulacaoBeneficio.codigoFilialFaturamento, cliente.codigoCidadePreco, simulacaoBeneficio.codigoTerritorioVenda);
			
			item = SimulacaoBeneficioService.executaChamadaCalculoPreco(simulacaoBeneficio, itemPedidoTemporario, cliente);
			if (simulacaoBeneficio.isSimulacaoCaixa) {
				simulacaoBeneficio.precoLiquidoUnitarioSimulado = item.valorUnitarioCaixaComImposto;
			} else {
				simulacaoBeneficio.precoLiquidoUnitarioSimulado = item.valorUnitarioComImposto;
			}
			
			simulacaoBeneficio.precoLiquidoSimulado = CalculoUtil.roundUP(simulacaoBeneficio.precoLiquidoUnitarioSimulado.multiply(BigDecimal.valueOf(item.quantidadeMercadoria)),2);
			return new ActionResult(Status.OK, JsonUtil.toJson("simulacaoBeneficio", simulacaoBeneficio));
		} catch (Exception e) {
			Log.e("CalculoPreco","Erro ao calcular preco", e);
			Log.e(TAG, e.getLocalizedMessage(), e);
			throw e;
		}
	}
}
