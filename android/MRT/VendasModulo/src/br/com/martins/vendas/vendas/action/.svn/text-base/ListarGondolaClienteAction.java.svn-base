package br.com.martins.vendas.vendas.action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.martins.vendas.services.GondolaService;
import br.com.martins.vendas.vendas.dto.GondolaCliente;
import br.com.martins.vendas.vo.Gondola;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.log.Log;
import com.brq.mobile.framework.util.JsonUtil;

public class ListarGondolaClienteAction {

	private static final String TAG = ListarGondolaClienteAction.class.getName();

	public ActionResult execute(JSONArray args) {
		ActionResult result = new ActionResult(Status.ERROR);

		try {
			List<Gondola> listaGondulaCliente = GondolaService.listarItensTemporariosGondolaCliente(args.getInt(0));
			
			GondolaCliente gondolaCliente = new GondolaCliente();

			gondolaCliente.listaItensGondola.addAll(listaGondulaCliente);
			gondolaCliente.isVisualizaComissao = validaPermissaoVisualizarComissao();
			
			JSONObject json = JsonUtil.toJson("Gondola", gondolaCliente);
			result = new ActionResult(Status.OK, json);

		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			result = new ActionResult(Status.ERROR, e.getMessage());
		}

		return result;
	}
	
	/**
	 * Verifica permiss�o para visualizar a coluna de comiss�o
	 * @return boolean - true (possui permiss�o) ou false (n�o possui permiss�o)
	 * @throws Exception
	 */
	private boolean validaPermissaoVisualizarComissao() throws Exception {
		
		/*String indCalcCmsn = "";
		Representante representante = RepresentanteService.findRepresentante();

		if(representante.tipoEntidade.equals("6")){
			indCalcCmsn = representante.indicativoCalculoIcmsEquipe;
		}else{
			indCalcCmsn = representante.indicativoCalculoIcms;
		}
		
		if(indCalcCmsn.equals("1")){
			return true;
		}
		
		CString strTipEdeVnd, strCodRep, strIndCalCms;

		strTipEdeVnd = m_f4TipEdeVnd.str();
		
		if (TEXT(" 6") == strTipEdeVnd)
		{
			//variavel carregada quando busca nivel de RCA no PCAEQP
			strIndCalCms = m_strIndCalCmsEqp;
		}
		else
		{
			//O indicador do RCA esta no PCAREP
			strIndCalCms = (CString) m_f4IndCalCms.str();
		}
		return (TEXT("1") == strIndCalCms);	*/
		
		return false;
	}

}