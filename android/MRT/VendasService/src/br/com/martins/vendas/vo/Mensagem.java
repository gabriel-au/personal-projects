package br.com.martins.vendas.vo;

import java.util.HashMap;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

/**
 * Tabela: PCAFIL
 * 
 * @author BRQ Mobile Team
 */
public class Mensagem extends BaseVo {

	private static final long serialVersionUID = -4671821962448919828L;

	public Integer tipo;

	public String texto;
	
	public String valor;
	
	public String valorAbortado;
	

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("tipo",  Mensagem.TIPO.JS_ALERT.value.equals(tipo) || Mensagem.TIPO.JS_CONFIRM.value.equals(tipo) ? tipo : Mensagem.TIPO.JS_NONE.value);
		propriedades.put("texto", texto);
		propriedades.put("valor", valor);
		propriedades.put("valorAbortado", valorAbortado);
		return propriedades;
	}
	
	public enum TIPO {
		
		JS_NONE (0),
		
		JS_ALERT (1),
		
		JS_CONFIRM (2);
		
		public Integer value;
		
		private TIPO(Integer value) {
			this.value = value;
		}
    }
}
