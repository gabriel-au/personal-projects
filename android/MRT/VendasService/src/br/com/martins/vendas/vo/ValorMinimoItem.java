package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import br.com.martins.vendas.services.calculodepreco.Item;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

/**
 * Classe DTO para metodo ItemPedidoService.verificaValorMinimoItemBeneficio
 * @author floliveira
 *
 */
public class ValorMinimoItem extends BaseVo{
	
	private static final long serialVersionUID = 8784592739541776629L;

	private final static String TAG = ValorMinimoItem.class.getName();
	
	public MensagemValorMinimoItem mensagemValorMinimoItem;
	
	public boolean isAbaixoValorMinimo;
	public BigDecimal percentualDescontoFlexivel;
	public BigDecimal percentualAcrescimoConcedido;
	public BigDecimal percentualDescontoItem;
	public BigDecimal valorLiquidoNovo;

	/**
	 * Item apos calculo de preco
	 * para aplicar beneficio
	 */
	public Item item;
	
	@Override
	public Map<String, Object> toMap() {
		try {
			Map<String, Object> propriedades = new  HashMap<String, Object>();
			propriedades.put("percentualDescontoFlexivel", percentualDescontoFlexivel );
			propriedades.put("percentualAcrescimoConcedido", percentualAcrescimoConcedido );
			propriedades.put("percentualDescontoItem", percentualDescontoItem );
			propriedades.put("valorLiquidoNovo", valorLiquidoNovo );
			propriedades.put("mensagemValorMinimoItem", JsonUtil.toJson(mensagemValorMinimoItem));
			propriedades.put("isAcimaValorMinimo", isAbaixoValorMinimo);
			return propriedades;
		} catch (JSONException e) {
			Log.e(TAG, "Erro ao executar toMap", e);
			return null;
		}
	}
}
