package br.com.martins.vendas.vo.pedido;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class ReducaoItem extends BaseVo {

	private static final long serialVersionUID = -5445621067056686799L;

/*	struct DdoFchRdcIte	{
		int m_iValRdcIte;
		int m_iQtdMerVnd;

		CString m_strCodFilFat;
		CString m_strCodFilEpd;
		CString m_strCodMer;
	};*/
	
	
	public Integer codigoFilialFaturamento;
	public Integer codigoFilialExpedicao;
	public Integer codigoMercadoria;
	
	public Integer valorReducaoItem;
	public Integer quantidadeMercadoriaVenda;
	
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		return propriedades;
	}

}