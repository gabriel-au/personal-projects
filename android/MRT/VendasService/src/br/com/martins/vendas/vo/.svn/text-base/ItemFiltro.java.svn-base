package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.Util;

public class ItemFiltro extends BaseVo {

	private static final long	serialVersionUID	= 7894869176606138751L;

	public boolean				comportamentoCompras;

	public boolean				mex;

	public boolean				buscaAvancada;

	public Integer				codigoMercadoria;

	public Integer				codigoMercadoriaPrincipal;

	public String				descricaoMercadoria;

	public String				codigoBarras;

	public int					numPagina;

	public Integer				codigoGrupo;

	public Integer				codigoCategoria;

	public Integer				codigoSubCategoria;

	public Integer				codigoFornecedor;
	
	public String				codigoListaCustomizada;

	public boolean				meio;
	
	/**
	 * Variável de controle para peculiaridades da busca por código
	 */
	public boolean				isBuscaPorCodigo = false;
	
	/**
	 *  Variável de controle, indica que esta na tela de itens disponíveis
	 */
	public boolean				isListaMercadoria = false;
	
	/**
	 *  Variável de controle, indica que esta na tela de itens similares
	 */
	 public boolean				isListaMercadoriaSimilar = false;
	
	public ItemFiltro() {}
	
	public ItemFiltro(Integer codigoMercadoria) {
		this.codigoMercadoria = codigoMercadoria;
		this.isBuscaPorCodigo = true;
	}

	public void preencheCodigoDescricao(String valor) {
		if (valor != null && valor.length() > 0) {
			if (valor.matches("[0-9]*")) {
				if (valor.length() < 8) {
					codigoMercadoria = Util.getInteger(valor);
					isBuscaPorCodigo = true;
				} else {
					codigoBarras = valor;
				}
			} else {
				descricaoMercadoria = valor;
			}
		}
	}

	@Override
	public Map<String, Object> toMap() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("comportamentoCompras",comportamentoCompras);
		map.put("mex",mex);
		map.put("buscaAvancada",buscaAvancada);
		map.put("codigoMercadoria",codigoMercadoria);
		map.put("codigoMercadoriaPrincipal",codigoMercadoriaPrincipal);
		map.put("descricaoMercadoria",descricaoMercadoria);
		map.put("codigoBarras",codigoBarras);
		map.put("numPagina",numPagina);
		map.put("codigoGrupo",codigoGrupo);
		map.put("codigoCategoria",codigoCategoria);
		map.put("codigoSubCategoria",codigoSubCategoria);
		map.put("codigoFornecedor",codigoFornecedor);
		map.put("codigoListaCustomizada",codigoListaCustomizada);
		map.put("meio",meio);
		map.put("isBuscaPorCodigo",isBuscaPorCodigo);
		map.put("isListaMercadoria",isListaMercadoria);
		map.put("isListaMercadoriaSimilar",isListaMercadoria);
		return map;
	}

}
