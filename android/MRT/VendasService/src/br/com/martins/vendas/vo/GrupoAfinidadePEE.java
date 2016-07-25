/**
 * 
 */
package br.com.martins.vendas.vo;

import java.util.Map;
import java.util.TreeMap;

import com.brq.mobile.framework.core.BaseVo;

/**
 * 
 * TABLE: PCALVR
 * 
 * @author BRQ Mobile Team
 */
public class GrupoAfinidadePEE extends BaseVo {

	private static final long serialVersionUID = 2938060521729390130L;

	/**
	 * CODMER
	 */
	public Integer		codMercadoria;
	
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
	 * CODGRPAFD
	 */
	public Integer		codGrupoAfinidade;
	
	/**
	 * FLGTRN
	 */
	public String		flgTransferencia;
	
	/**
	 * FLGATU
	 */
	
	public String		flgAtualizacao;
	
	/**
	 * DATPEE
	 */
	public String		datPontoEncontroEletronico;
	
	/**
	 * IDTINFPEE
	 * (Identidade da Informação do Ponto de Encontro Eletronico)
	 */
	public String		idtInformcaoPEE;
	
	/**
	 * DATVALAFD
	 */
	public String		datValidadeAfinidade;
	

	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> properties = new TreeMap<String, Object>();

		properties.put("codMercadoria", codMercadoria);
		properties.put("codFilialExpedicao", codFilialExpedicao);
		properties.put("codFilialFaturamento", codFilialFaturamento);
		properties.put("numRelacaoCidade", numRelacaoCidade);
		properties.put("codGrupoAfinidade", codGrupoAfinidade);
		properties.put("flgTransferencia", flgTransferencia);
		properties.put("flgAtualizacao", flgAtualizacao);
		properties.put("datPontoEncontroEletronico", datPontoEncontroEletronico);
		properties.put("idtInformcaoPEE", idtInformcaoPEE);
		properties.put("datValidadeAfinidade", datValidadeAfinidade);

		return properties;
	}
}