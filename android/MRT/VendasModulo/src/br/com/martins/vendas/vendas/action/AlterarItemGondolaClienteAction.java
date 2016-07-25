package br.com.martins.vendas.vendas.action;

import java.math.BigDecimal;

import org.json.JSONArray;
import org.json.JSONException;

import br.com.martins.vendas.services.GondolaService;
import br.com.martins.vendas.vo.Gondola;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DateUtil;

public class AlterarItemGondolaClienteAction {

	private static final String TAG = AlterarItemGondolaClienteAction.class.getName();

	public ActionResult execute(JSONArray args) {
		ActionResult result = new ActionResult(Status.ERROR);

		try {
            Gondola gondola = preencherDadosGondola(args);
 			boolean sucesso = GondolaService.alterarItemGondola(gondola); 
 			result =  new ActionResult(sucesso ? Status.OK : Status.ERROR, "Erro ao alterar item na gôndola.");
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage());
			result = new ActionResult(Status.ERROR, e.getMessage());
		}

		return result;
	}

	/**
	 * Método que preenche o objeto Gondola com informações recuperadas em tela
	 * @param args - JSONArray com informações de tela
	 * @return Gondola - objeto preenchido com informações da tela
	 * @throws JSONException
	 */
	private Gondola preencherDadosGondola(JSONArray args) throws JSONException {

		Gondola gondola = new Gondola();
		gondola.codigoCliente = args.getInt(0);
		gondola.posicaoMercadoria = args.getInt(1);
		gondola.precoCliente = tratamentoPrecoCliente(args.getString(2));
		gondola.quantidadeMercadoriaAtual = Integer.valueOf(args.getString(3));
		gondola.dataAtual = DateUtil.obterDataAtual();
        		
		return gondola;
		
	}
	
	/**
	 * Método que trata o preenchimento do campo preço 
	 * @param precoCliente - informação de preço digitada na tela
	 * @return BigDecimal - retorna o valor do preço
	 */
	private BigDecimal tratamentoPrecoCliente(String precoCliente){
		BigDecimal preco = BigDecimal.ZERO;
		if(precoCliente != null && !"".equals(precoCliente)){
			preco = new BigDecimal(precoCliente);
		}
		return preco;
	}

}