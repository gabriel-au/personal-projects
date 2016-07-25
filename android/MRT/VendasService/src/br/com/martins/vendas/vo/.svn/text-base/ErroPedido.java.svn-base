package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class ErroPedido extends BaseVo {

	private static final long serialVersionUID = 985933583804470613L;

	public Integer codigo;

	public String mensagem;
	
	public Integer quantidade;
	
	public boolean isCritical;

	@Override
	public Map<String, Object> toMap() {
			
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("codigo", codigo);
		propriedades.put("quantidade", quantidade);
		propriedades.put("mensagem", mensagem);
		propriedades.put("isCritical", isCritical);
		return propriedades;
	}

}