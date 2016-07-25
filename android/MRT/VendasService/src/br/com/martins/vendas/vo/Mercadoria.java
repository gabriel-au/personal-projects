package br.com.martins.vendas.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brq.mobile.framework.core.BaseVo;

public class Mercadoria extends BaseVo {

	private static final long serialVersionUID = 8329879075582088088L;

	public Mercadoria() {
		percentualICMS = new PercentualICMS();
		percentualIPI = new PercentualIPI();
		
		codigosDeBloqueio = new ArrayList<Integer>(10);
	}

	public Integer grupoSTB;
	
	/**
	 * CODMER - tabela CADMER
	 */
	public Integer codigo;

	/**
	 * 
	 */
	public Integer digito;
	
	/**
	 * DESMER - tabela CADMER
	 */
	public String descricao;
	
	/**
	 * DESNGCMER
	 */
	public String descricaoNegocioMercadoria;

	/**
	 * TIPMER - tabela CADMER
	 */
	public String tipo;
	
	/**
	 * CODTERCHV1 = tabela CADMER
	 */
	public String codigoChaveCategoria;

	/**
	 * CodMerPcp
	 */
	public Integer codigoMercadoriaPrincipal;

	/**
	 * FLGPEE
	 */
	public String flagPee;

	public Integer codigoEspecialistaNegociacao;
	public Integer codigoClassificacaoNegociacao;
	public Integer codigoTributacaoMercadoria;
	public String codigoGrupoNCM;

	public Integer fatorConversaoUnitario;
	public Integer codigoGrupoFracionado;
	public Integer quantidadeMinimaVenda;
	public Integer quantidadeMinimaKit;
	public Integer flagUtilizaMinimoPrecoEspecial;
	
	public String flagMultiplicadorQuantidade;
	
	public BigDecimal valorPontos; // VLRPTOMER
	public BigDecimal valorPontosBonificacao;
	
	/**
	 * QDECXAFRN
	 */
	public Integer quantidadeCaixaFornecedor;
	
	/**
	 * QDECXAFRN
	 */
	public Integer quantidadeCaixaFracionada;
	

	/**
	 * QDEMERPED
	 */
	public Integer qtdMercadoriaPedida;

	/**
	 * QDEMERANT - Utilizado pela Gondola
	 */
	public Integer quantidadeMercadoriaAnterior;
	
	/**
	 * QDEMERATU - Quantidade Mercadoria Atual - Utilizado pela Gondola
	 */
	public Integer quantidadeMercadoriaAtual;
	
	public List<Integer> codigosDeBloqueio;
	public PercentualICMS percentualICMS;
	public PercentualIPI percentualIPI;
	public BigDecimal valorMinimoMercadoria;
	public BigDecimal valorMaximoMercadoria;
	public BigDecimal custoFracionado;
	public BigDecimal valorBeneficio;

	/**
	 * VLRFRTMER
	 */
	public BigDecimal valorFrete;

	/**
	 * Tabela: CADCPL DESCPLMER
	 */
	public String descricaoComplementarProduto;

	/**
	 * INDRTCBFV, 0 Não Aplica / 1 Aplica
	 */
	public int temRestricaoBrinde;

	/**
	 * INDRTCBFB, 0 Não Aplica / 1 Aplica
	 */
	public int temRestricaoBeneficioCustomizadoNoBrinde;

	/**
	 * INDRTCBNF, 0 Não Aplica / 1 Aplica
	 */
	public int temRestricaoBeneficioCustomizado;

	/**
	 * TIPMERESG
	 */
	public String tipoEsgotamento;

	public boolean temMercadoriaSimilar = false;

	/**
	 * INDMERKIT
	 */
	public Integer indicaMercadoriaKit;

	/**
	 * INDRSTKIT
	 */
	public Integer indicadorRestricaoKit;

	/**
	 * TIPPDCMER
	 */
	public Integer	tipoProducaoMercadoria;

	/**
	 * POSMER
	 */
	public Integer posicaoMercadoria;
	
		
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codigo", codigo);
		map.put("digito", obtemDigitoVerificador(codigo));
		map.put("descricao", descricao);
		
		
		map.put("codigoEspecialistaNegociacao", codigoEspecialistaNegociacao);
		map.put("quantidadeCaixaFornecedor", quantidadeCaixaFornecedor);

		map.put("tipoEsgotamento", tipoEsgotamento);
		map.put("qtdMercadoriaPedida", qtdMercadoriaPedida);
		map.put("quantidadeMercadoriaAnterior", quantidadeMercadoriaAnterior);
		map.put("quantidadeMercadoriaAtual", quantidadeMercadoriaAtual);
		map.put("quantidadeCaixaFracionada", quantidadeCaixaFracionada);
		map.put("descricaoNegocioMercadoria", descricaoNegocioMercadoria);
		map.put("valorFrete", valorFrete);
		map.put("temRestricaoBrinde", temRestricaoBrinde);
		map.put("temRestricaoBeneficioCustomizadoNoBrinde", temRestricaoBeneficioCustomizadoNoBrinde);
		map.put("temRestricaoBeneficioCustomizado", temRestricaoBeneficioCustomizado);
		map.put("percentualICMS", percentualICMS.toMap());
		map.put("flagPee", flagPee);
		map.put("fatorConversaoUnitario", fatorConversaoUnitario);
		map.put("temMercadoriaSimilar", temMercadoriaSimilar);
		map.put("indicaMercadoriaKit", indicaMercadoriaKit);
		map.put("indicadorRestricaoKit", indicadorRestricaoKit);
		map.put("valorBeneficio", valorBeneficio);
		
		map.put("tipoProducaoMercadoria", tipoProducaoMercadoria);
		
		map.put("posicaoMercadoria", posicaoMercadoria);
		
		
		return map;
	}
	
	/**
	 * Método responsável por calcular o dígito do código da mercadoria.
	 * 
	 * @param codigoMercadoria
	 *            objeto do tipo <code>java.lang.Integer</code>, correspondente
	 *            ao código da mercadoria.
	 * @return objeto do tipo <code>java.lang.Integer</code>, correspondente ao
	 *         dígito do código da mercadoria.
	 */
	public static Integer obtemDigitoVerificador(Integer codigoMercadoria) {
		
		Integer digito = 0;
		Integer resto = 0;
		Integer soma = 0;

		if (codigoMercadoria != null && 0 != codigoMercadoria) {

			String codigo = String.valueOf(codigoMercadoria);

			final int fullSize = codigo.length();		
			final char[] charArray = codigo.toCharArray();
			
			int pos	 = 0;
			int jump = 7 - fullSize;
			for (int j = 8; j > 1; j--) {
				if (--jump == 0) {
					continue;
				}
				
				digito = Integer.valueOf(String.valueOf(charArray[pos]));
				pos++;
				
				if ((digito < 0) || (digito > 9)) {
					digito = 0;
				}

				soma += digito * j;
			}

			// Faz o cálculo do dígito verificador através da somatória
			resto = soma % 11;
			resto = 11 - resto;

			if (resto < 10) {
				digito = resto;
			} else {
				digito = 0;
			}

			// CString strCodAux = strCodMer;
			// Faz a concatenação do código da mercadoria + "-" e
			// o dígito verificador
			// strCodMer.Format(TEXT("%s-%d"), strCodAux, iDig);

		}

		return digito;
	}
	
}
