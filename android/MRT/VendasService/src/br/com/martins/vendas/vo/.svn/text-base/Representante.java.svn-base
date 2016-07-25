package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.StringUtil;

public class Representante extends BaseVo {

	private static final long serialVersionUID = -1851131905437547163L;

	/**
	 * CODREP
	 */
	public String codigoRepresentante;
	
	/**
	 * CODREP - GM
	 */
	public String codigoGerenteMercado;

	/**
	 * NOMREP
	 */
	public String nomeRepresentante;
	
	/**
	 * NOMREP - GM
	 */
	public String nomeGerenteMercado;

	/**
	 * CODGRPAFD
	 */
	public Integer	codigoAfinidade;
	
	/**
	 * TIPEDEVND
	 */
	public String tipoEntidade;

	/**
	 * FTPKMRG
	 */
	public BigDecimal fatorKMargem;

	/**
	 * VLRMAXDDED
	 */
	public BigDecimal valorMaxBrinde;
	
	/**
	 * INDCALCMS
	 */
	public String indicativoCalculoIcms;
	
	/**
	 * Tabela: PCAEQP - Representnte da Equipe de Gerente de Mercado
	 * INDCALCMS
	 */
	public String indicativoCalculoIcmsEquipe;
	
	public String tipoVenda;
	
	/**
	 * PERBNFPED
	 */
	public Double percentualBonificacaoPedido;
	
	/**
	 * PERVLRBNF
	 */
	public BigDecimal percentualValorBonificacao;
	

	public String naturezaRepresentante;

	/**
	 * EMLREP
	 */
	public String email;
	
	/**
	 * PERTXACDI
	 */
	public BigDecimal pecentualTaxaCDI;

	/**
	 * PERTXADPZ
	 */
	public BigDecimal pecentualTaxaDPZ;

	/**
	 * NROMAXNOT
	 */
	public Integer	numeroMaximoNotaFiscal;
	
	/**
	 *CODCEP 
	 */
	public String cep;
	
	/*
	 *DESTIPLGR 
	 */	
	public String tipoLogradouro;
	
	/*
	 * TAMCAM
	 */
	public String tamanhoCamiseta;
	
	/*
	 * DESLGR
	 */
	public String descLogradouro;
	
	/*
	 * NUMENDLGR
	 */
	public Integer numeroEndereco;
	
	/*
	 * DESCPLEND
	 */
	public String complemento;
	
	/*
	 * NOMBAIEND
	 */
	public String bairro;
	
	/*
	 * TLFRES
	 */
	public String telResidencial;
	
	/*
	 * TLFCEL1
	 */
	public String numCelular1;	

	/*
	 * TLFCEL2
	 */
	public String numCelular2;
	
	/*
	 * INDACTEML
	 */
	public Integer flagEmail;
	
	/*
	 * INDACTSMS
	 */
	public Integer flagSms;
	
	public String dominioEmail;

	public BigDecimal	previsaoSemanal;

	public BigDecimal	percentualPrevisto;
	
	public boolean isGerenteMercado;
	
	/**
	 * PERUTZFLX
	 */
	public BigDecimal percentualUtilizacaoFlex;
	
	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("codigoRepresentante", codigoRepresentante);
		propriedades.put("nomeRepresentante", nomeRepresentante);			
		propriedades.put("codigoAfinidade", codigoAfinidade);
		propriedades.put("tipoEntidade", tipoEntidade);
		propriedades.put("fatorKMargem", fatorKMargem);
		propriedades.put("valorMaxDeducao", valorMaxBrinde);
		propriedades.put("indicativoCalculoIcms", indicativoCalculoIcms);
		propriedades.put("indicativoCalculoIcmsEquipe", indicativoCalculoIcmsEquipe);
		propriedades.put("tipoVenda", tipoVenda);
		propriedades.put("pecentualTaxaCDI", pecentualTaxaCDI);
		propriedades.put("pecentualTaxaDPZ", pecentualTaxaDPZ);
		propriedades.put("email", email);			
		propriedades.put("naturezaRepresentante", naturezaRepresentante);			
		propriedades.put("numeroMaximoNotaFiscal", numeroMaximoNotaFiscal);
		propriedades.put("cep", StringUtil.formataCep(cep));			
		propriedades.put("tipoLogradouro", tipoLogradouro);
		propriedades.put("tamanhoCamiseta", tamanhoCamiseta);
		propriedades.put("descLogradouro", descLogradouro);
		propriedades.put("numeroEndereco", numeroEndereco);
		propriedades.put("complemento", complemento);
		propriedades.put("bairro", bairro);
		propriedades.put("telResidencial", StringUtil.formataTelefone(telResidencial));
		propriedades.put("numCelular1",StringUtil.formataTelefone(numCelular1));
		propriedades.put("numCelular2",StringUtil.formataTelefone(numCelular2));
		propriedades.put("flagEmail", flagEmail);
		propriedades.put("flagSms", flagSms);
		propriedades.put("dominioEmail", dominioEmail);
		propriedades.put("previsaoSemanal", previsaoSemanal);
		propriedades.put("percentualPrevisto", percentualPrevisto);

		propriedades.put("isGerenteMercado", isGerenteMercado);
		propriedades.put("codigoGerenteMercado", codigoGerenteMercado);
		propriedades.put("nomeGerenteMercado", nomeGerenteMercado);
		propriedades.put("percentualBonificacaoPedido", percentualBonificacaoPedido);
		
		return propriedades;
	}
}