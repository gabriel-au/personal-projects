package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class Inicial extends BaseVo {

	private static final long serialVersionUID = -6018044923255809351L;

	public Sistema sistema;
	public DadosComerciaisRepresentante dadosComerciaisRepresentante;
	public Representante representante;
	public List<AvisosCliente> avisosCliente;
	public List<AvisosPedido> avisosPedido;

	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("sistema", sistema);
		propriedades.put("dadosComerciaisRepresentante", dadosComerciaisRepresentante);
		propriedades.put("representante", representante);
		propriedades.put("avisosCliente", avisosCliente);
		propriedades.put("avisosPedido", avisosPedido);
		return propriedades;
	}
}
