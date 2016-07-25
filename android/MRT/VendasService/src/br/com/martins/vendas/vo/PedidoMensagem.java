package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import br.com.martins.vendas.enums.TipoPedidoMensagem;

import com.brq.mobile.framework.core.BaseVo;

public class PedidoMensagem extends BaseVo {

	private static final long serialVersionUID = -3042517607187799280L;

	private static PedidoMensagem instancia;
	private TipoPedidoMensagem tipoPedidoMensagem;
	public String detalheMensagem;
	
	public static PedidoMensagem criaMensagem(Integer codigo) {
		instancia = new PedidoMensagem();
		instancia.tipoPedidoMensagem = TipoPedidoMensagem.getValue(codigo);
		return instancia;
	}

	public static PedidoMensagem criaMensagem(Integer codigo, String detalheMensagem) {
		instancia = new PedidoMensagem();
		instancia.detalheMensagem = detalheMensagem;
		instancia.tipoPedidoMensagem = TipoPedidoMensagem.getValue(codigo);
		return instancia;
	}
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codigo", tipoPedidoMensagem.codigo);
		map.put("descricao", tipoPedidoMensagem.descricao);
		map.put("isCritica", tipoPedidoMensagem.isCritica);
		map.put("detalheDescricao", detalheMensagem);
		return map;
	}

}
