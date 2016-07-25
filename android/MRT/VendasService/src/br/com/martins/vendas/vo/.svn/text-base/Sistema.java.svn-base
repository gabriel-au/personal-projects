package br.com.martins.vendas.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.StringUtil;

public class Sistema extends BaseVo {

	private static final long serialVersionUID = 3229924074178016041L;

	public Date data;

	public String versao;

	public Date ultimaSincronizacao;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("versao", versao);
		propriedades.put("data", StringUtil.formataData(data, DateUtil.DEFAULT_DATE_PATTERN));
		propriedades.put("hora", StringUtil.formataData(data, "HH:mm:ss"));
		propriedades.put("ultimaSincronizacao", StringUtil.formataData(ultimaSincronizacao, "dd/MM/yyyy HH:mm"));
		return propriedades;
	}
}
