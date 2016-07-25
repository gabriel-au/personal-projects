package br.com.martins.vendas.vendas.action;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;

import br.com.martins.vendas.services.RepresentanteService;
import br.com.martins.vendas.vo.Representante;

import com.brq.mobile.framework.core.ActionResult;
import com.brq.mobile.framework.core.ActionResult.Status;
import com.brq.mobile.framework.util.JsonUtil;

public class RepresentanteAction {
	
	public ActionResult execute(JSONArray array) throws JSONException, SQLException {
		Representante representante = new Representante();
		representante.cep =array.getString(0);
		representante.tipoLogradouro =array.getString(1);
		representante.tamanhoCamiseta =array.getString(2);
		representante.descLogradouro =array.getString(3);
		representante.complemento =array.getString(4);
		representante.numeroEndereco =array.getInt(5);
		representante.bairro =array.getString(6);
		representante.email =array.getString(7);
		representante.dominioEmail=array.getString(8);
		representante.telResidencial =array.getString(9);
		representante.numCelular1 =array.getString(10);
		representante.numCelular2 =array.getString(11);
		representante.flagEmail =array.getInt(12);
		representante.flagSms =array.getInt(13);
		representante.codigoRepresentante =array.getString(14);				
		
		if(!"".equals(representante.email)){
			representante.email=representante.email.concat("@").concat(representante.dominioEmail);	
		}			
		
		RepresentanteService.atualizaRepresentante(representante);
		Representante rep = RepresentanteService.findRepresentante(null);
		
		return new ActionResult(Status.OK, JsonUtil.toJson("representante", rep));
	}

}
