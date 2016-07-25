package br.com.martins.vendas.vendas.action;

import java.util.List;

import br.com.martins.vendas.services.CidadesTerritoriosService;
import br.com.martins.vendas.vo.CidadesTerritorios;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.util.JsonUtil;

public class CidadesTerritoriosAction {

	public ActionResult execute() throws Exception {		
		List<CidadesTerritorios> cidades = CidadesTerritoriosService.obtemCidades();
		return new ActionResult(Status.OK, JsonUtil.toJson("cidades", cidades));
	}
	
}
