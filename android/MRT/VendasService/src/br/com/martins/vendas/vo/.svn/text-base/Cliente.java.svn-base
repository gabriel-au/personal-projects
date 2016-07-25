package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.StringUtil;

public class Cliente extends BaseVo {

	private static final long serialVersionUID = 8960336286130810103L;

	private static final String TAG = Cliente.class.getName();
	
	/**
	 * CODCLI
	 */
	public Integer codigoCliente;

	/**
	 * NOMCLI
	 */
	public String nomeCliente;

	/**
	 * NOMFNTCLI
	 */
	public String nomeFantasia;

	/**
	 * INDCLIFAV
	 */
	public String indicativoFavorito;

	/**
	 * INDCLITOP
	 */
	public String indicativoTop;

	/**
	 * MEDDIASATD
	 */
	public Integer mediaDiasSemAtendimento;

	/**
	 * MAXDIASATD
	 */
	public Integer maximoDiasSemAtendimento;

	/**
	 * EMLCLI
	 */
	public String email;

	/**
	 * DESTIPLGR
	 */
	public String tipoLogradouro;

	/**
	 * DESLGR
	 */
	public String descricaoLogradouro;

	/**
	 * NUMTLFCLI
	 */
	public String telefone;

	public Date positivar;

	/**
	 * TIPCOBCND
	 */
	public Integer tipoCobrancaCondicaoPagamento;

	/**
	 * CODBAI
	 */
	public String codigoBairro;

	/**
	 * Tabela PCABAI - NOMBAI
	 */
	public String nomeBairro;

	/**
	 * Tabela PCACID - NOMCID
	 */
	public String nomeCidade;

	/**
	 * Tabela PCACID - CODESTUNI
	 */
	public String uf;

	/**
	 * Tabela PCACLT - CODTETVND
	 */
	public Integer codigoTerritorio;

	/**
	 * CANAL DO CLIENTE [CODCNL]
	 */
	public Integer canal;

	/**
	 * Tabela PCASCL - DESSGMCLI
	 */
	public String descricaoSegmento;

	/**
	 * Tabela PCASCL - CODSGMCLI
	 */
	public String codigoSegmento;

	public String situacao;

	/**
	 * VLRLIMCRD
	 */
	public BigDecimal valorLimiteCredito;

	/**
	 * TIPLIMCRD
	 */
	public String tipoLimiteCredito;

	/**
	 * Tabela: PCATIT - VLRSLDABT
	 */
	public BigDecimal valorSaldoAberto;

	/**
	 * TipCttVdr
	 */
	public String tipoContratoVendor;

	/**
	 * TipSitJur
	 */
	public String tipoSituacaoJuridica;

	/**
	 * CodAti
	 */
	public Integer codigoAtividade;

	/**
	 * FlgCliEsp
	 */
	public String flagClienteEspecial;

	/**
	 * CodGrpCli
	 */
	public Integer codigoGrupoCliente;

	/**
	 * TipCobBco
	 */
	public Integer tipoCobrancaBanco;

	/**
	 * IndRtcCnd
	 */
	public String indicaRestricaoCondicao;

	/**
	 * FLGCLICSM
	 */
	public String flagClienteConsumidor;

	public CondicaoPagamento condicaoPagamento;

	public Date dataRevisaoCadastro;

	public Integer qtdMesRevisao;

	public String restricaoCondicaoPagamento;

	public String statusAtendimento;

	public Integer diasCorridos;

	public String descMotivoSituacao;

	public String mensagem;

	public Integer diferencaDias;

	public boolean bloqueado;

	public Date dataUltimoPedido;

	public String codigoClassificacao;

	public BigDecimal fatorAjusteMargem;

	public Integer codigoCidade;

	/**
	 * CODCIDPCO (Não é código da Cidade)
	 */
	public Integer codigoCidadePreco;

	/**
	 * NUMRLCCID, PCACPC ou PCAAGE
	 */
	public Integer numeroRelacionamentoCliente;

	/**
	 * CODSUP, PCAAGE
	 */
	public Integer codigoSupervisor;

	/**
	 * FLGAVRPSC
	 */
	public String flagAlvaraPsicotropico;

	/**
	 * FLGPCOESP
	 */
	public String flagPrecoEspecial;

	/**
	 * Tabela PCAMTV - TIPANLCRD
	 */
	public String tipoAnaliseCredito;

	/**
	 * CODESTDSN
	 */
	public String codigoEstadoDestino;

	public BigDecimal percentualDescontoFlexivel;

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codigoCliente", codigoCliente);
		map.put("nomeCliente", nomeCliente);
		map.put("nomeFantasia", nomeFantasia);
		map.put("indicativoFavorito", indicativoFavorito);
		map.put("indicativoTop", indicativoTop);
		map.put("mediaDiasSemAtendimento", mediaDiasSemAtendimento);
		map.put("maximoDiasSemAtendimento", maximoDiasSemAtendimento);
		map.put("email", email);
		map.put("tipoLogradouro", tipoLogradouro);
		map.put("descricaoLogradouro", descricaoLogradouro);
		map.put("telefone", telefone);
		map.put("positivar", DateUtil.formataData(positivar, DateUtil.DEFAULT_DATE_PATTERN));
		map.put("tipoCobrancaCondicaoPagamento", tipoCobrancaCondicaoPagamento);
		map.put("nomeBairro", nomeBairro);
		map.put("codigoCidade", codigoCidade);
		map.put("nomeCidade", nomeCidade);
		map.put("uf", uf);
		map.put("codigoTerritorio", codigoTerritorio);
		map.put("canal", canal);
		map.put("descricaoSegmento", descricaoSegmento);
		map.put("situacao", situacao);
		map.put("valorLimiteCredito", StringUtil.formataMonetario(valorLimiteCredito));
		map.put("tipoLimiteCredito", tipoLimiteCredito);
		map.put("valorSaldoAberto", StringUtil.formataMonetario(valorSaldoAberto));
		map.put("tipoContratoVendor", tipoContratoVendor);
		map.put("tipoSituacaoJuridica", tipoSituacaoJuridica);
		map.put("codigoAtividade", codigoAtividade);
		map.put("flagClienteEspecial", flagClienteEspecial);
		map.put("codigoGrupoCliente", codigoGrupoCliente);
		map.put("tipoCobrancaBanco", tipoCobrancaBanco);
		map.put("indicaRestricaoCondicao", indicaRestricaoCondicao);
		map.put("flagClienteConsumidor", flagClienteConsumidor);
		map.put("condicaoPagamento", condicaoPagamento);
		map.put("codigoClassificacao", codigoClassificacao);
		map.put("dataRevisaoCadastro", DateUtil.formataData(dataRevisaoCadastro, DateUtil.DEFAULT_DATE_PATTERN));
		map.put("qtdMesRevisao", qtdMesRevisao);
		map.put("diasCorridos", diasCorridos);
		map.put("descMotivoSituacao", descMotivoSituacao);
		map.put("mensagem", mensagem);
		map.put("bloqueado", bloqueado);
		map.put("diferencaDias", diferencaDias);
		map.put("codigoSegmento", codigoSegmento);
		map.put("restricaoCondicaoPagamento", restricaoCondicaoPagamento);
		map.put("dataUltimoPedido", DateUtil.formataData(dataUltimoPedido, DateUtil.DEFAULT_DATE_PATTERN));
		map.put("statusAtendimento", statusAtendimento);
		map.put("codigoCidadePreco", codigoCidadePreco);
		map.put("numeroRelacionamentoCliente", numeroRelacionamentoCliente);
		map.put("codigoSupervisor", codigoSupervisor);
		map.put("flagAlvaraPsicotropico", flagAlvaraPsicotropico);
		map.put("flagPrecoEspecial", flagPrecoEspecial);
		map.put("tipoAnaliseCredito", tipoAnaliseCredito);
		map.put("percentualDescontoFlexivel", percentualDescontoFlexivel);
		return map;
	}

	public Cliente toObject(Object object) {
		Cliente cliente = new Cliente();
		JSONObject jsonObject = (JSONObject) object;
		
		try {
			cliente.codigoCliente = cliente.codigoCliente = jsonObject.getInt("codigoCliente");
			cliente.nomeCliente = jsonObject.getString("nomeCliente");
			cliente.nomeFantasia = jsonObject.getString("nomeFantasia");
			cliente.indicativoFavorito = jsonObject.getString("indicativoFavorito");
			cliente.indicativoTop = jsonObject.getString("indicativoTop");
			cliente.mediaDiasSemAtendimento = jsonObject.getInt("mediaDiasSemAtendimento");
			cliente.maximoDiasSemAtendimento = jsonObject.getInt("maximoDiasSemAtendimento");
			cliente.email = jsonObject.getString("email");
			cliente.tipoLogradouro = jsonObject.getString("tipoLogradouro");
			cliente.descricaoLogradouro = jsonObject.getString("descricaoLogradouro");
			cliente.telefone = jsonObject.getString("telefone");
//			cliente.positivar = jsonObject.getString("positivar"); // DateUtil.formataData(positivar, DateUtil.DEFAULT_DATE_PATTERN));
			cliente.tipoCobrancaCondicaoPagamento = jsonObject.getInt("tipoCobrancaCondicaoPagamento");
			cliente.nomeBairro = jsonObject.getString("nomeBairro");
			cliente.codigoCidade = jsonObject.getInt("codigoCidade");
			cliente.nomeCidade = jsonObject.getString("nomeCidade");
			cliente.uf = jsonObject.getString("uf");
			cliente.codigoTerritorio = jsonObject.getInt("codigoTerritorio");
			cliente.canal = jsonObject.getInt("canal");
			cliente.descricaoSegmento = jsonObject.getString("descricaoSegmento");
			cliente.situacao = jsonObject.getString("situacao");
//			cliente.valorLimiteCredito = jsonObject.getString("valorLimiteCredito"); // StringUtil.formataMonetario(valorLimiteCredito));
			cliente.tipoLimiteCredito = jsonObject.getString("tipoLimiteCredito");
//			cliente.valorSaldoAberto = jsonObject.getString("valorSaldoAberto"); // StringUtil.formataMonetario(valorSaldoAberto));
			cliente.tipoContratoVendor = jsonObject.getString("tipoContratoVendor");
			cliente.tipoSituacaoJuridica = jsonObject.getString("tipoSituacaoJuridica");
			cliente.codigoAtividade = jsonObject.getInt("codigoAtividade");
			cliente.flagClienteEspecial = jsonObject.getString("flagClienteEspecial");
			cliente.codigoGrupoCliente = jsonObject.getInt("codigoGrupoCliente");
			cliente.tipoCobrancaBanco = jsonObject.getInt("tipoCobrancaBanco");
			cliente.indicaRestricaoCondicao = jsonObject.getString("indicaRestricaoCondicao");
			cliente.flagClienteConsumidor = jsonObject.getString("flagClienteConsumidor");
//			cliente.condicaoPagamento = (CondicaoPagamento) jsonObject.get("condicaoPagamento");
			cliente.codigoClassificacao = jsonObject.getString("codigoClassificacao");
//			cliente.dataRevisaoCadastro = jsonObject.getString("dataRevisaoCadastro"); // DateUtil.formataData(dataRevisaoCadastro, DateUtil.DEFAULT_DATE_PATTERN));
			cliente.qtdMesRevisao = jsonObject.getInt("qtdMesRevisao");
			cliente.diasCorridos = jsonObject.getInt("diasCorridos");
			cliente.descMotivoSituacao = jsonObject.getString("descMotivoSituacao");
			cliente.mensagem = jsonObject.getString("mensagem");
			cliente.bloqueado = jsonObject.getBoolean("bloqueado");
			cliente.diferencaDias = jsonObject.getInt("diferencaDias");
			cliente.codigoSegmento = jsonObject.getString("codigoSegmento");
			cliente.restricaoCondicaoPagamento = jsonObject.getString("restricaoCondicaoPagamento");
//			cliente.dataUltimoPedido = jsonObject.getString("dataUltimoPedido"); // DateUtil.formataData(dataUltimoPedido, DateUtil.DEFAULT_DATE_PATTERN));
			cliente.statusAtendimento = jsonObject.getString("statusAtendimento");
			cliente.codigoCidadePreco = jsonObject.getInt("codigoCidadePreco");
			cliente.numeroRelacionamentoCliente = jsonObject.getInt("numeroRelacionamentoCliente");
			cliente.codigoSupervisor = jsonObject.getInt("codigoSupervisor");
			cliente.flagAlvaraPsicotropico = jsonObject.getString("flagAlvaraPsicotropico");
			cliente.flagPrecoEspecial = jsonObject.getString("flagPrecoEspecial");
			cliente.tipoAnaliseCredito = jsonObject.getString("tipoAnaliseCredito");
//			cliente.percentualDescontoFlexivel = jsonObject.getString("percentualDescontoFlexivel");
		
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
		}
		
		return cliente;
	}
	
}