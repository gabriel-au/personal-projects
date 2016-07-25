package br.com.martins.vendas.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.StringUtil;

public class DetalheMercadoria extends BaseVo {

	private static final long serialVersionUID = 8280855875724803020L;

	/**
	 * COMER - tabela CADMER
	 */
	public Integer	codigo;

	/**
	 * DESMER - tabela CADMER
	 */
	public String	descricao				= StringUtil.EMPTY;
	/**
	 * Categoria do Produto: Tabela PCAFML
	 */
	public String	categoria				= StringUtil.EMPTY;

	/**
	 * Sub Categoria do Produto: Tabela PCACLS
	 */
	public String	subCategoria			= StringUtil.EMPTY;	;

	/**
	 * Nome do Fornecedor: TABELA PCAPCH, TIPTERCHV = 2
	 */
	public String	fornecedor				= StringUtil.EMPTY;	;

	/**
	 * 
	 */
	public Integer	quantidadeCaixaFechada;

	/**
	 * Descrição Modelo Distribuição
	 */
	public String	descModeloDistribuicao	= StringUtil.EMPTY;	;

	/**
	 * Descricao dataVencimento
	 */
	public Date		dataVencimento;

	/**
	 * Descrição unidade venda
	 */
	public String	unidadeVenda			= StringUtil.EMPTY;	;

	/**
	 * Esgotamento Incentivo. TABELA: PCAITE, TIPICTMER
	 */
	public String	esgotamentoIncentivo	= StringUtil.EMPTY;

	/**
	 * Quantidade Mínima
	 */
	public Integer	qtdMinima;

	/**
	 * Quota
	 */
	public Integer	quota;

	/**
	 * Código de Barras. TABELA: PCAMER, CODBRRMER
	 */
	public String	codigoBarras			= StringUtil.EMPTY;
	
	/**
	 * TABELA: PCAMER, INDMERKIT
	 */
	public boolean	kit;
	
	/**
	 * TABELA: PCALVR, FLGPEE
	 */
	public boolean	pee;

	/**
	 * TABELA: PCALVR, TIPMCOREP
	 */
	public String	tipMarcacaoRepresentande;
	
	/**
	 * 
	 */
	public String	image;
	
	/**
	 * CODGRPMER
	 */
	public Integer codigoGrupo;
	
	public List<String> imagens = new ArrayList<String>();
	
	public List<String> diretorioImagemResolucaoMaxima = new ArrayList<String>();
	public List<String> diretorioImagemResolucaoMinima = new ArrayList<String>();
	public String fichaTecnica = StringUtil.EMPTY;

	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("codigo", codigo);
		propriedades.put("descricao", descricao);

		propriedades.put("fornecedor", fornecedor);
		propriedades.put("categoria", categoria);
		propriedades.put("subCategoria", subCategoria);

		propriedades.put("quantidadeCaixaFechada", quantidadeCaixaFechada);
		propriedades.put("dataVencimento", StringUtil.formataData(dataVencimento, DateUtil.DEFAULT_DATE_PATTERN));
		propriedades.put("descModeloDistribuicao", descModeloDistribuicao);

		propriedades.put("unidadeVenda", unidadeVenda);
		propriedades.put("esgotamentoIncentivo", esgotamentoIncentivo);
		propriedades.put("qtdMinima", qtdMinima);
		propriedades.put("quota", quota);
		propriedades.put("codigoBarras", codigoBarras);
		
		propriedades.put("pee", pee);
		propriedades.put("tipMarcacaoRepresentande", tipMarcacaoRepresentande);
		
		propriedades.put("image", image);
		propriedades.put("imagens", imagens);
		propriedades.put("diretorioImagemResolucaoMaxima", diretorioImagemResolucaoMaxima);
		propriedades.put("diretorioImagemResolucaoMinima", diretorioImagemResolucaoMinima);
		propriedades.put("fichaTecnica", fichaTecnica);
		propriedades.put("codigoGrupo", codigoGrupo);
		
		return propriedades;
	}
}