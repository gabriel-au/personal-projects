package br.com.martins.vendas.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;

public class DetalhePEE extends BaseVo {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= -1836071344168003982L;

	public Integer	codigoPrioridade;

	public String	identidadeInformacaoPEE;

	public String	descricaoInformacaoPEE;

	public String	descontoAcaoTatica;

	public Date	dataVigoracao;

	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codigoPrioridade", codigoPrioridade);
		map.put("identidadeInformacaoPEE", identidadeInformacaoPEE);
		map.put("descricaoInformacaoPEE", descricaoInformacaoPEE);
		map.put("descontoAcaoTatica", descontoAcaoTatica);
		map.put("dataVigoracao", DateUtil.formataData(dataVigoracao, DateUtil.DEFAULT_DATE_PATTERN));
		
		return map;
		
	}

}
