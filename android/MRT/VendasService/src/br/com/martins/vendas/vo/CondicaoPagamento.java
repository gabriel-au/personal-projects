package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.StringUtil;

public class CondicaoPagamento extends BaseVo{
	
	private static final long serialVersionUID = -6407132126334026738L;

	private static final String TAG = CondicaoPagamento.class.getName();
	
	/**
	 * CodFilEpd
	 */
	public Integer codigoFilialExpedicao;
	
	/**
	 * CodEstUni
	 */
	public String codigoEstadoUniao;
	
	/**
	 * CodCnl
	 */
	public String	codigoCanal;
	
	/**
	 * CodAti
	 */
	public Integer	codigoAtividade;
	
	/**
	 * CodGrpCli
	 */
	public Integer	codigoGrupoCliente;
	
	/**
	 * CodCndPgt
	 */
	public Integer codigoCondicaoPagamento;
	
	/**
	 * FtrCndPgt
	 */
	public String freteCondicaoPagamento;
	
	/**
	 * FtrEncVdr
	 */
	public String freteEncargoVendor;
	
	/**
	 * QdeDiaPrz
	 */
	public Integer quantidadeDiaPrazo;
	
	/**
	 * NumPclCnd
	 */
	public Integer numeroParcelasCondicao;
	
	public Integer periodicidade;
	
	/**
	 * NumPodPcl
	 */
	public Integer numeroPeriodoParcial;
	/**
	 * VlrLimMnm
	 */
	public BigDecimal valorLimiteMinimo;
	/**
	 * TipFnmCnd
	 */
	public Integer tipoFinanciamento;
	/**
	 * TipCobCnd
	 */
	public Integer tipoCobrancaCondicao;
	/**
	 * TipCttCnd
	 */
	public Integer tipoContratoCondicao;
	/**
	 * PerPriPcl
	 */
	public BigDecimal percentualPrimeiroParcial;
	/**
	 * DatIniVld
	 */
	public Date dataInicioValida;
	/**
	 * DatFimVld
	 */
	public Date dataFimValida;
	/**
	 * FlgCndEsp
	 */
	public Integer flagCondicaoEspecial;
	/**
	 * TipPrzCnd
	 */
	public Integer tipoPrazoCondicao;
	/**
	 * IndCndTbo
	 */
	public Integer indicaCondicaoTribanco;
	/**
	 * DesObsCnd
	 */
	public String descricaoObservacaoCondicao;
	
	/**
	 * Indvdrpmc
	 */
	public Integer indicaVendorPromocao;


	/**
	 * FTRCNDPGT
	 */
	public BigDecimal fatorCondicaoPagamento;

	public String descricaoCondicao;

	/**
	 * FTRENCVDR
	 */
	public BigDecimal fatorEncargoVendor;

	public Integer tipoCondicaoPagamento;
	
	//Utilizados no fechamento pedido
	public BigDecimal valorTotalLiquido;
	public BigDecimal valorTotalImposto;
	public BigDecimal valorTotalFrete;
	public boolean flagCondicaoTarifada;
	public Integer quantidadePrazoBonificacao;
	
	// TODO NOMENCLATURA DESCONHECIDA E NAO EH UTILIZADO EM NENHUM LOCAL DA APLICACAO
//	public boolean m_bIndRtcBfc0;
//	public boolean m_bIndRtcBfc1;
	///--
	
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new  HashMap<String, Object>();
		propriedades.put("codigoFilialExpedicao", codigoFilialExpedicao );
		propriedades.put("codigoEstadoUniao", codigoEstadoUniao);
		propriedades.put("codigoCanal", codigoCanal);
		propriedades.put("codigoAtividade", codigoAtividade );
		propriedades.put("codigoGrupoCliente", codigoGrupoCliente);
		propriedades.put("codigoCondicaoPagamento", codigoCondicaoPagamento );
		propriedades.put("freteCondicaoPagamento", freteCondicaoPagamento );
		propriedades.put("freteEncargoVendor", freteEncargoVendor );
		propriedades.put("quantidadeDiaPrazo", quantidadeDiaPrazo );
		propriedades.put("numeroParcelasCondicao", numeroParcelasCondicao );
		propriedades.put("numeroPeriodoParcial", numeroPeriodoParcial );
		propriedades.put("valorLimiteMinimo", StringUtil.formataMonetario(valorLimiteMinimo));
		propriedades.put("tipoFinanciamento", tipoFinanciamento );
		propriedades.put("tipoCobrancaCondicao", tipoCobrancaCondicao );
		propriedades.put("tipoContratoCondicao", tipoContratoCondicao );
		propriedades.put("percentualPrimeiroParcial", percentualPrimeiroParcial );
		propriedades.put("dataInicioValida", dataInicioValida );
		propriedades.put("dataFimValida", dataFimValida );
		propriedades.put("flagCondicaoEspecial", flagCondicaoEspecial );
		propriedades.put("tipoPrazoCondicao", tipoPrazoCondicao );
		propriedades.put("indicaCondicaoTribanco", indicaCondicaoTribanco );
		propriedades.put("descricaoObservacaoCondicao", descricaoObservacaoCondicao );
		propriedades.put("indicaVendorPromocao", indicaVendorPromocao );
		propriedades.put("periodicidade", periodicidade );
		propriedades.put("descricaoCondicao", descricaoCondicao );
		
		return propriedades;
	}
	
	public CondicaoPagamento toObject(Object object) {
		CondicaoPagamento condicaoPagamento = new CondicaoPagamento();
		JSONObject jsonObject = (JSONObject) object;
		
		try {
//			condicaoPagamento.codigoCondicaoPagamento = jsonObject.getInt("codigo"); 
//			condicaoPagamento.descricaoCondicao = jsonObject.getString("descricao");
			
			condicaoPagamento.codigoCondicaoPagamento = jsonObject.getInt("codigoCondicaoPagamento"); 
			condicaoPagamento.descricaoCondicao = jsonObject.getString("descricaoCondicao");
			condicaoPagamento.codigoFilialExpedicao = jsonObject.getInt("codigoFilialExpedicao");
			condicaoPagamento.codigoEstadoUniao = jsonObject.getString("codigoEstadoUniao");
			condicaoPagamento.codigoCanal = jsonObject.getString("codigoCanal");
			condicaoPagamento.codigoAtividade = jsonObject.getInt("codigoAtividade");
			condicaoPagamento.codigoGrupoCliente = jsonObject.getInt("codigoGrupoCliente");
			condicaoPagamento.codigoCondicaoPagamento = jsonObject.getInt("codigoCondicaoPagamento"); 
			condicaoPagamento.freteCondicaoPagamento = jsonObject.getString("freteCondicaoPagamento");
			condicaoPagamento.freteEncargoVendor = jsonObject.getString("freteEncargoVendor");
			condicaoPagamento.quantidadeDiaPrazo = jsonObject.getInt("quantidadeDiaPrazo");
			condicaoPagamento.numeroParcelasCondicao = jsonObject.getInt("numeroParcelasCondicao");
			condicaoPagamento.numeroPeriodoParcial = jsonObject.getInt("numeroPeriodoParcial");
			condicaoPagamento.valorLimiteMinimo = new BigDecimal(jsonObject.getString("valorLimiteMinimo")); //, StringUtil.formataMonetario(
			condicaoPagamento.tipoFinanciamento = jsonObject.getInt("tipoFinanciamento");
			condicaoPagamento.tipoCobrancaCondicao = jsonObject.getInt("tipoCobrancaCondicao");
			condicaoPagamento.tipoContratoCondicao = jsonObject.getInt("tipoContratoCondicao");
			condicaoPagamento.percentualPrimeiroParcial = new BigDecimal(jsonObject.getString("percentualPrimeiroParcial"));
	//		condicaoPagamento.dataInicioValida = (Date) propriedades.get("dataInicioValida");
	//		condicaoPagamento.dataFimValida = (Date) propriedades.get("dataFimValida");
			condicaoPagamento.flagCondicaoEspecial = jsonObject.getInt("flagCondicaoEspecial");
			condicaoPagamento.tipoPrazoCondicao = jsonObject.getInt("tipoPrazoCondicao");
			condicaoPagamento.indicaCondicaoTribanco = jsonObject.getInt("indicaCondicaoTribanco");
			condicaoPagamento.descricaoObservacaoCondicao = jsonObject.getString("descricaoObservacaoCondicao");
			condicaoPagamento.indicaVendorPromocao = jsonObject.getInt("indicaVendorPromocao");
			condicaoPagamento.periodicidade = jsonObject.getInt("periodicidade");
		
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
		}
		
		return condicaoPagamento;
	}
	
}
