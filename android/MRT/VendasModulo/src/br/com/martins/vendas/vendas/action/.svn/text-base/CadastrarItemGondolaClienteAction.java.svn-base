package br.com.martins.vendas.vendas.action;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;

import br.com.martins.vendas.services.GondolaService;
import br.com.martins.vendas.vo.Gondola;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.DateUtil;
import com.brq.mobile.framework.util.StringUtil;

public class CadastrarItemGondolaClienteAction {

	private static final String TAG = CadastrarItemGondolaClienteAction.class.getName();

	public ActionResult execute(JSONArray args) {
		ActionResult result = new ActionResult(Status.ERROR);

		try {
			Gondola gondola = preencherDadosGondola(args);
			boolean sucesso = GondolaService.cadastrarItemGondola(gondola); 
			result =  new ActionResult(sucesso ? Status.OK : Status.ERROR, "Erro ao adicionar item na gôndola.");
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
			result = new ActionResult(Status.ERROR, e.getMessage());
		}

		return result;
	}
	
	/**
	 * Método que preenche o objeto Gondola com informações recuperadas em tela
	 * @param args - JSONArray com informações de tela
	 * @return Gondola - objeto preenchido com informações da tela
	 * @throws JSONException
	 */
	private Gondola preencherDadosGondola(JSONArray args) throws JSONException {
		Date dataAtual = DateUtil.obterDataAtual();
		
		Gondola gondola = new Gondola();
		gondola.codigoMercadoria = args.getInt(0);
		gondola.codigoCliente = args.getInt(1);
		gondola.posicaoMercadoria = args.getInt(2);
		gondola.precoCliente = tratamentoPrecoCliente(args.getString(3));
		gondola.quantidadeMercadoriaAtual = Integer.valueOf(args.getString(4));
		gondola.codigoFilialExpedicao = args.getInt(5);
		gondola.codigoFilialFaturamento = args.getInt(6);
		gondola.dataAtual = dataAtual;
		gondola.dataUltimaVenda = DateUtil.formataData(dataAtual, DateUtil.DEFAULT_DATE_DATABASE);
		gondola.codigoChaveRegistro = obterChaveRegistro(gondola);
		gondola.quantidadeMercadoriaAnterior = 0;
        		
		return gondola;
		
	}

	/**
	 * Método que obtém o código da chave de registro
	 * @param gondola - informações de gôndola
	 * @return String - chave
	 */
	private String obterChaveRegistro(Gondola gondola){
		/*
		::GetSystemTime(&stAux);
		strCodChvRgt.Format(_T("%*d%s%04d%02d%02d%02d%02d%02d%04d"), TAMPOSMER, iPosMer, (LPCTSTR)strCodCli, stAux.wYear, stAux.wMonth, stAux.wDay, stAux.wHour, stAux.wMinute, stAux.wSecond, stAux.wMilliseconds);
		 */
		
		StringBuilder chave = new StringBuilder();
		Calendar dataHoje = Calendar.getInstance();
		chave.append(gondola.posicaoMercadoria);
		chave.append(gondola.codigoCliente);
		chave.append(StringUtil.preencheZerosEsquerda(String.valueOf(dataHoje.get(Calendar.YEAR)), 4));
		chave.append(StringUtil.preencheZerosEsquerda(String.valueOf(dataHoje.get(Calendar.MONTH)+1), 2));
		chave.append(StringUtil.preencheZerosEsquerda(String.valueOf(dataHoje.get(Calendar.DAY_OF_MONTH)), 2));
		chave.append(StringUtil.preencheZerosEsquerda(String.valueOf(dataHoje.get(Calendar.HOUR_OF_DAY)), 2));
		chave.append(StringUtil.preencheZerosEsquerda(String.valueOf(dataHoje.get(Calendar.MINUTE)), 2));
		chave.append(StringUtil.preencheZerosEsquerda(String.valueOf(dataHoje.get(Calendar.SECOND)), 2));
		chave.append(StringUtil.preencheZerosEsquerda(String.valueOf(dataHoje.get(Calendar.MILLISECOND)), 4));
		return chave.toString();
	}

	/**
	 * Método que trata o preenchimento do campo preço 
	 * @param precoCliente - informação de preço digitada na tela
	 * @return BigDecimal - retorna o valor do preço
	 */
	private BigDecimal tratamentoPrecoCliente(String precoCliente){
		BigDecimal preco = BigDecimal.ZERO;
		if(precoCliente != null && !"".equals(precoCliente)){
			preco = new BigDecimal(precoCliente);
		}
		return preco;
	}
	
}