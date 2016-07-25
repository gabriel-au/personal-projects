package br.com.martins.vendas.enums;

@Deprecated
public enum TipoMensagemErroPedido {

	ERRO_DESCONHECIDO(0, "Erro não identificado.", true), 
	ERRO_BONIFICACAO_INDEVIDA(1, "Bonificação (Poupe Certo) acima do máximo permitido.", true),
	ERRO_CP_COMPROR_CLI_SEM_CTT(2, "Condição(ões) de pagamento não permitida.", true), 
	ERRO_CP_VALORMINIMO(3, "Condição(ões) de pagamento com valor mínimo insuficiente.", true),
	ERRO_FILIAIS_PEDIDO(4, "Pedido com excesso de filiais de expedição.", true), 
	ERRO_FRETE_VALORMINIMO(5, "Modelo(s) de distribuição com valor mínimo do frete insuficiente.", true),
	ERRO_ITEM_DUPLICADO(6, "Mercadoria(s) duplicada(s).", true), 
	ERRO_ITEM_ESGOTADO(7, "Mercadoria(s) com estoque baixo.", false),
	ERRO_PARTICIPACAO_BENEFICIO(8, "Bonificação (Benefício) acima do máximo permitido.", true), 
	ERRO_PED_VALORMINIMO_BOLETO(9, "Boleto bancário com valor mínimo insuficiente.", true),
	ERRO_PED_VALORMINIMO_EPD(10, "Pedido da filial de expedição com valor mínimo insuficiente.", true), 
	ERRO_PORCENTAGEM_METALGRAMPO(11, "Mercadoria(s) com restrição sobre o total do pedido.", true),
	ERRO_PORCENTAGEM_SOJA(12, "Mercadoria(s) com restrição sobre o total do pedido.", true),
	ERRO_PROMOCAO_SEM_PMT(13, "Promoção(ões) sem parâmetros.", true),
	ERRO_QDE_ITEM_INVALIDA(14, "Mercadoria(s) com quantidade inválida.", true),
	ERRO_QDE_PROMOCAO(15, "Promoção(ões) com excesso de prêmios.", true),
	ERRO_QUOTA_EXCEDIDA(16, "Mercadoria(s) com quantidade superior ao máximo permitido.", true),
	ERRO_QUOTA_MP_DO_BEM(17, "Mercadoria(s) isenta(s) de PIS/COFINS com quantidade superior ao permitido.", true),
	ERRO_VENCIMENTO_CONDICAO(18, "Condição(ões) de pagamento com prazo não permitido.", true),
	ERRO_PONTOS_DETALHE_ITEM(19, "Pedido com valor mínimo de pontos insuficiente.", true),
	ERRO_MINIMO_PONTOS_PEDIDO(20, "Pedido com valor mínimo de pontos insuficiente.", true),
	ERRO_VALOR_MAXIMO_BRINDE_FLEX(21, "Valor do Brinde (Poupe Certo) maior que o máximo permitido.", true);

	public String mensagem;
	public int codigo;
	public boolean isCritica;

	private TipoMensagemErroPedido(int codigo, String mensagem, boolean isCritica) {
		this.codigo = codigo;
		this.mensagem = mensagem;
		this.isCritica = isCritica;
	}

}


/*m_errors[] =
{
	{TRUE, FALSE, 0, 0, TEXT("%ld - Erro não identificado.")},
	{TRUE, TRUE, 0, RN_ERRO_BONIFICACAO_INDEVIDA, TEXT("Bonificação (Poupe Certo) acima do máximo permitido.")},
	{TRUE, TRUE, 0, RN_ERRO_CP_COMPROR_CLI_SEM_CTT, TEXT("%ld - Condição(ões) de pagamento não permitida.")},
	{TRUE, TRUE, 0, RN_ERRO_CP_VALORMINIMO, TEXT("%ld - Condição(ões) de pagamento com valor mínimo insuficiente.")},
	{TRUE, TRUE, 0, RN_ERRO_FILIAIS_PEDIDO, TEXT("Pedido com excesso de filiais de expedição.")},
	{TRUE, TRUE, 0, RN_ERRO_FRETE_VALORMINIMO, TEXT("%ld - Modelo(s) de distribuição com valor mínimo do frete insuficiente.")},
	{TRUE, TRUE, 0, RN_ERRO_ITEM_DUPLICADO, TEXT("%ld - Mercadoria(s) duplicada(s).")},
	{FALSE,	TRUE, 0, RN_ERRO_ITEM_ESGOTADO, TEXT("%ld - Mercadoria(s) com estoque baixo.")},
	{TRUE, TRUE, 0, RN_ERRO_PARTICIPACAO_BENEFICIO, TEXT("Bonificação (Benefício) acima do máximo permitido.")},
	{TRUE, TRUE, 0, RN_ERRO_PED_VALORMINIMO_BOLETO, TEXT("%ld - Boleto bancário com valor mínimo insuficiente.")},
	{TRUE, TRUE, 0, RN_ERRO_PED_VALORMINIMO_EPD, TEXT("%ld - Pedido da filial de expedição com valor mínimo insuficiente.")},
	{TRUE, TRUE, 0, RN_ERRO_PORCENTAGEM_METALGRAMPO, TEXT("%ld - Mercadoria(s) com restrição sobre o total do pedido.")},
	{TRUE, TRUE, 0, RN_ERRO_PORCENTAGEM_SOJA, TEXT("%ld - Mercadoria(s) com restrição sobre o total do pedido.")},
	{TRUE, TRUE, 0, RN_ERRO_PROMOCAO_SEM_PMT, TEXT("%ld - Promoção(ões) sem parâmetros.")},
	{TRUE, TRUE, 0, RN_ERRO_QDE_ITEM_INVALIDA, TEXT("%ld - Mercadoria(s) com quantidade inválida.")},
	{TRUE, TRUE, 0, RN_ERRO_QDE_PROMOCAO, TEXT("%ld - Promoção(ões) com excesso de prêmios.")},
	{TRUE, TRUE, 0, RN_ERRO_QUOTA_EXCEDIDA, TEXT("%ld - Mercadoria(s) com quantidade superior ao máximo permitido.")},
	{TRUE, TRUE, 0, RN_ERRO_QUOTA_MP_DO_BEM, TEXT("%ld - Mercadoria(s) isenta(s) de PIS/COFINS com quantidade superior ao permitido.")},
	{TRUE, TRUE, 0, RN_ERRO_VENCIMENTO_CONDICAO, TEXT("%ld - Condição(ões) de pagamento com prazo não permitido.")},
	{TRUE, TRUE, 0, RN_ERRO_PONTOS_DETALHE_ITEM, TEXT("Pedido com valor mínimo de pontos insuficiente.")},
	{TRUE, TRUE, 0, RN_ERRO_MINIMO_PONTOS_PEDIDO, TEXT("Pedido com valor mínimo de pontos insuficiente.")},
	{TRUE, TRUE, 0, RN_ERRO_VALOR_MAXIMO_BRINDE_FLEX, TEXT("Valor do Brinde (Poupe Certo) maior que o máximo permitido.")}
};*/



//MessageBox(TEXT("Este pedido possui mensagens restritiva, por isso a confirmação não poderá prosseguir"), TEXT("Aviso"), MB_OK|MB_ICONEXCLAMATION);

/*protected:
	enum
	{
		ERRO_DESCONHECIDO,
		ERRO_BONIFICACAO_INDEVIDA, #define RN_ERRO_BONIFICACAO_INDEVIDA (-20014)
		ERRO_CP_COMPROR_CLI_SEM_CTT, #define RN_ERRO_CP_COMPROR_CLI_SEM_CTT (-20017)
		ERRO_CP_VALORMINIMO, #define RN_ERRO_CP_VALORMINIMO (-20018)
		ERRO_FILIAIS_PEDIDO, #define RN_ERRO_FILIAIS_PEDIDO (-20019)
		ERRO_FRETE_VALORMINIMO, #define RN_ERRO_FRETE_VALORMINIMO (-20020)
		ERRO_ITEM_DUPLICADO, #define RN_ERRO_ITEM_DUPLICADO (-20024)
		ERRO_ITEM_ESGOTADO, #define RN_ERRO_ITEM_ESGOTADO (-20025)
		ERRO_PARTICIPACAO_BENEFICIO, #define RN_ERRO_PARTICIPACAO_BENEFICIO	(-20026)
		ERRO_PED_VALORMINIMO_BOLETO, #define RN_ERRO_PED_VALORMINIMO_BOLETO (-20027)
		ERRO_PED_VALORMINIMO_EPD, #define RN_ERRO_PED_VALORMINIMO_EPD (-20028)
		ERRO_PORCENTAGEM_METALGRAMPO, #define RN_ERRO_PORCENTAGEM_METALGRAMPO	(-20029)
		ERRO_PORCENTAGEM_SOJA, #define RN_ERRO_PORCENTAGEM_SOJA (-20030)
		ERRO_PROMOCAO_SEM_PMT, #define RN_ERRO_PROMOCAO_SEM_PMT (-20031)
		ERRO_QDE_ITEM_INVALIDA, #define RN_ERRO_QDE_ITEM_INVALIDA (-20032)
		ERRO_QDE_PROMOCAO, #define RN_ERRO_QDE_PROMOCAO (-20033)
		ERRO_QUOTA_EXCEDIDA, #define RN_ERRO_QUOTA_EXCEDIDA (-20034)
		ERRO_QUOTA_MP_DO_BEM, #define RN_ERRO_QUOTA_MP_DO_BEM (-20035)
		ERRO_VENCIMENTO_CONDICAO, #define RN_ERRO_VENCIMENTO_CONDICAO (-20037)
		ERRO_PONTOS_DETALHE_ITEM, #define RN_ERRO_PONTOS_DETALHE_ITEM (-20055)
		ERRO_MINIMO_PONTOS_PEDIDO,  #define RN_ERRO_MINIMO_PONTOS_PEDIDO (-20056)
		ERRO_VALOR_MAXIMO_BRINDE_FLEX #define RN_ERRO_VALOR_MAXIMO_BRINDE_FLEX (-20057)
	};*/

/*//Erros tratados com mensagens definidas na estrutura m_static_struSystemErros

#define	RN_OK (1)
#define RN_CIDADE_INVALIDA (-10001)
#define RN_CLIENTE_NAO_ENCONTRADO (-10002)
#define RN_DATREFLIV_DO_PEDIDO_INVALIDA (-10003)
#define RN_ERRO_ABERTURA_ARQ_EXPORTACAO	(-10004)
#define RN_ERRO_CHAVE_INVALIDA (-10005)
#define RN_ERRO_ESCRITA_ARQ_EXPORTACAO (-10006)
#define	RN_ERRO_SEEK (-10007)
#define RN_ERRO_SEM_ITENS (-10008)
#define	RN_ERRO_TABELA (-10009)
#define	RN_NAO_ENCONTRADO (-10010)
#define RN_OPERACAO_INVALIDA (-10011)
#define RN_PEDIDO_EH_COTACAO (-10012)
#define RN_PEDIDO_JA_PREPARADO (-10013)
#define RN_PEDIDO_SO_LEITURA (-10014)
#define RN_RESTRINGE_PEDIDO (-10015)
#define RN_TOTAL_ZERO_QDE_ITENS (-10016)


//Erros que não possuem mensagens definidas na estrutura m_static_struSystemErros

#define RN_BLOQUEADA_REGIONALIZACAO (-20001)
#define RN_CALCULO_INCORRETO (-20002)
#define RN_CLIENTE_SEM_LIVRO (-20003)
#define RN_CLIENTE_TERRITORIO_DIFERENTE (-20004)
#define RN_CONCORRENTE_NAO_ENCONTRADO (-20005)
#define RN_CONDICAO_NAO_ENCONTRADA (-20006)
#define RN_DATREFLIV_ATUAL (-20007)
#define RN_DATREFLIV_NOVO_LIVRO (-20008)
#define RN_DATREFLIV_VENCIDA (-20009)
#define RN_DEPOIS (-20010)
#define RN_DESCONTO_INVALIDO (-20011)
#define RN_ERRO (-20012)
#define RN_ERRO_APAGA_REGISTRO (-20013)
#define RN_ERRO_BONIFICACAO_INDEVIDA (-20014)
#define RN_ERRO_CALCULO_PRECO (-20015)
#define RN_ERRO_COPIA_ARQUIVO (-20016)
#define RN_ERRO_CP_COMPROR_CLI_SEM_CTT (-20017)
#define RN_ERRO_CP_VALORMINIMO (-20018)
#define RN_ERRO_FILIAIS_PEDIDO (-20019)
#define RN_ERRO_FRETE_VALORMINIMO (-20020)
#define RN_ERRO_GRAVAR_CORPO_PEDIDO (-20021)
#define RN_ERRO_GRAVAR_ITENS (-20022)
#define RN_ERRO_INICIALIZA_TABELA (-20023)
#define RN_ERRO_ITEM_DUPLICADO (-20024)
#define RN_ERRO_ITEM_ESGOTADO (-20025)
#define RN_ERRO_PARTICIPACAO_BENEFICIO	(-20026)
#define RN_ERRO_PED_VALORMINIMO_BOLETO (-20027)
#define RN_ERRO_PED_VALORMINIMO_EPD (-20028)
#define RN_ERRO_PORCENTAGEM_METALGRAMPO	(-20029)
#define RN_ERRO_PORCENTAGEM_SOJA (-20030)
#define RN_ERRO_PROMOCAO_SEM_PMT (-20031)
#define RN_ERRO_QDE_ITEM_INVALIDA (-20032)
#define RN_ERRO_QDE_PROMOCAO (-20033)
#define RN_ERRO_QUOTA_EXCEDIDA (-20034)
#define RN_ERRO_QUOTA_MP_DO_BEM (-20035)
#define RN_ERRO_VALOR_MINIMO_ITEM (-20036)
#define RN_ERRO_VENCIMENTO_CONDICAO (-20037)
#define	RN_EXISTE_ITEM_GONDOLA (-20038)
#define RN_FALHA_INDICE (-20039)
#define RN_FIM_ARRAY_CODTERCHV (-20040)
#define	RN_FIM_PESQUISA (-20041)
#define RN_ITEM_INSERIDO_OK (-20042)
#define RN_ITEM_SEM_CONDICAO (-20043)
#define RN_ITEM_SEM_DESCONTO (-20044)
#define RN_MERCADORIA_INVALIDA (-20045)
#define RN_MERCADORIA_LIBERADA (-20046)
#define RN_MERCADORIA_SEM_PRECO (-20047)
#define RN_NAO_SE_APLICA_STB (-20048)
#define RN_NUMERO_NOTA_INVALIDO (-20049)
#define RN_PEDIDO_BLOQUEADO (-20050)
#define RN_PEDIDO_OK (-20051)
#define RN_QUANTIDADE_INVALIDA (-20052)
#define RN_SEM_FILIAL_FATURAMENTO (-20053)
#define RN_THREAD_FIM_THREAD (-20054)
#define RN_ERRO_PONTOS_DETALHE_ITEM (-20055)
#define RN_ERRO_MINIMO_PONTOS_PEDIDO (-20056)
#define RN_ERRO_VALOR_MAXIMO_BRINDE_FLEX (-20057)*/
