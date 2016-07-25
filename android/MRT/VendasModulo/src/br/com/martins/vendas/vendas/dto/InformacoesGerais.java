package br.com.martins.vendas.vendas.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import br.com.martins.vendas.vo.FilialCliente;

import com.brq.mobile.framework.core.BaseVo;
import com.brq.mobile.framework.util.StringUtil;

public class InformacoesGerais extends BaseVo {

	private static final long serialVersionUID = 4977842138180011057L;

	public String nomeCliente;
	
	public FilialCliente filialCliente;

	public BigDecimal valorExpedicao;

	public BigDecimal valorBoletoBancario;

	public BigDecimal valorMinimoPontoPedido;

	public BigDecimal valorMaximoBrindePoupeCerto;
	
	public BigDecimal valorLimiteCreditoNomeado;
	
	public BigDecimal valorSaldoAbertoCliente;
	
	public String tipoVenda;
	
	public Integer totalTitulosAbertos;
	
	public Integer codigoModeloDistribuicao;
	
	public boolean isPedidoExiste;

	public Integer numeroPedido;
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> propriedades = new HashMap<String, Object>();
		propriedades.put("nomeCliente", nomeCliente);
		propriedades.put("filialCliente", filialCliente);
		propriedades.put("valorExpedicao", StringUtil.formataMonetario(valorExpedicao));
		propriedades.put("valorBoletoBancario", StringUtil.formataMonetario(valorBoletoBancario));
		propriedades.put("valorMinimoPontoPedido", StringUtil.formataMonetario(valorMinimoPontoPedido));
		propriedades.put("valorMaximoBrindePoupeCerto", StringUtil.formataMonetario(valorMaximoBrindePoupeCerto));
		propriedades.put("valorLimiteCreditoNomeado", StringUtil.formataMonetario(valorLimiteCreditoNomeado));
		propriedades.put("valorSaldoAbertoCliente", StringUtil.formataMonetario(valorSaldoAbertoCliente));
		propriedades.put("tipoVenda", tipoVenda);
		propriedades.put("totalTitulosAbertos", totalTitulosAbertos);
		propriedades.put("codigoModeloDistribuicao", codigoModeloDistribuicao);
		propriedades.put("isPedidoExiste", isPedidoExiste);
		propriedades.put("numeroPedido", numeroPedido);
		return propriedades;
	}
}