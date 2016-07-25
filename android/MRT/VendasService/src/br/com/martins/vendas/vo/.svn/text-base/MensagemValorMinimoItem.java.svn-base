package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class MensagemValorMinimoItem extends BaseVo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int MENSAGEM_NAO_POSSIVEL_CONCESSAO_BENEFICIO = 1;
	public static final int MENSAGEM_CONFIRMACAO_VARIACAO_PRECO = 2;
	public static final int MENSAGEM_NAO_POSSIVEL_CONCESSAO_BENEFICIO_SUJEITO_CORTES = 3;
	
	public int tipoMensagem;
	public String mensagem;
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new  HashMap<String, Object>();
		propriedades.put("tipoMensagemSimulacaoBeneficio", tipoMensagem );
		propriedades.put("mensagemSimulacaoBeneficio", mensagem );
		return propriedades;
	}
}
