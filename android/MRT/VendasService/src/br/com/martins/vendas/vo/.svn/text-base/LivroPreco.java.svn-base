/**
 * 
 */
package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

import com.brq.mobile.framework.core.BaseVo;

/**
 * 
 * TABLE: PCALVR
 * 
 * @author BRQ Mobile Team
 */
public class LivroPreco extends BaseVo {

	private static final long serialVersionUID = -7179953572078675640L;
	
	/**
	 * CODFILEPD
	 */
	public Integer		codFilialExpedicao;
	/**
	 * CODFILFAT
	 */
	public Integer		codFilialFaturamento;
	/**
	 * NUMRLCCID
	 */
	public Integer		numRelacaoCidade;
	/**
	 * CODMER
	 */
	public Integer		codMercadoria;
	/**
	 * TIPICTMER
	 */
	public String		tipoIncentivoMercadoria;
	/**
	 * CODSMBSIT
	 */
	public String		codSimboloSituacao;
	/**
	 * CODFLXPCO
	 */
	public String		codFlexivelPreco;
	/**
	 * FLGMEREXV
	 */
	public String		flgMercadoriaExclusivo;
	/**
	 * INDMERMTD
	 */
	public String		indMercadoriaMonitorada;
	/**
	 * CODTBTICM
	 */
	public Integer		codTributacaoICM;
	/**
	 * VLRUNTBRT
	 */
	public BigDecimal	valorUnitarioBruto;
	
	
	/**
	 * VLRBRTCXA
	 */
	public BigDecimal	valorBrutoCaixa;
	
	/**
	 * FLGPEE
	 */
	public String		flgPontoEncontroEletronico;
	/**
	 * TIPMCOREP
	 */
	public String		tipMarcacaoRepresentante;
	/**
	 * CSTCSTLGT
	 */
	public BigDecimal	cstCustoLogistica;
	/**
	 * PERTBTMER
	 */
	public BigDecimal	perTributacaoMercadoria;

	/**
	 * PERCMSMER
	 */
	public BigDecimal	perComissaoMercadoria;
	
	
	public boolean  flagUtilizaMinimoPrecoEspecial;
	public boolean flagUtilizaFlexivelPrecoEspecial;
	public boolean flagUtilizaFracaoPrecoEspecial;
	public boolean flagUtilizaBeneficioPrecoEspecial;

	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> properties = new TreeMap<String, Object>();

		properties.put("codFilialExpedicao", codFilialExpedicao);
		properties.put("codFilialFaturamento", codFilialFaturamento);
		properties.put("numRelacaoCidade", numRelacaoCidade);
		properties.put("codMercadoria", codMercadoria);
		properties.put("tipoIncentivoMercadoria", tipoIncentivoMercadoria);
		properties.put("codSimboloSituacao", codSimboloSituacao);
		properties.put("codFlexivelPreco", codFlexivelPreco);
		properties.put("flgMercadoriaExclusivo", flgMercadoriaExclusivo);
		properties.put("indMercadoriaMonitorada", indMercadoriaMonitorada);
		properties.put("codTributacaoICM", codTributacaoICM);
		properties.put("valorUnitarioBruto", valorUnitarioBruto);
		properties.put("flgPontoEncontroEletronico", flgPontoEncontroEletronico);
		properties.put("tipMarcacaoRepresentante", tipMarcacaoRepresentante);
		properties.put("cstCustoLogistica", cstCustoLogistica);
		properties.put("perTributacaoMercadoria", perTributacaoMercadoria);
		properties.put("perComissaoMercadoria", perComissaoMercadoria);

		return properties;
	}

}