package br.com.xtrategia.fiscon.web;

import java.text.SimpleDateFormat;

import br.com.xtrategia.fiscon.web.pojo.CnhPojo;
import br.com.xtrategia.fiscon.web.servlet.ConsultarCNH;

public class CnhAction extends CRUDAction<CnhPojo> {

	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	protected CnhPojo iniciarPojo() {
		return new CnhPojo();
	}

	@Override
	public boolean isLoggedInUserRequired() {
		return true;
	}
	
	public String exibir(){
		try {
			ConsultarCNH consulta = new ConsultarCNH();
			
			String registro = pojo.getCnh();
			String data = sdf.format(pojo.getDataNascimento());
			
			if(registro==null || registro.equals("") || data==null){
				setMensagem("Infrome o Nº de Registro da CNH e Data de Nascimento");
				return ERROR;
			}
			//consulta.consultar("00079877665", "08/03/1974");
			consulta.consultar(registro, data);
			
			if(consulta.getPojo()==null || 
					consulta.getPojo().getCnh()==null || 
					consulta.getPojo().getCnh().trim().equals("")){
				setMensagem("CNH não encontrada");
				return ERROR;
			}
			
			setPojo(consulta.getPojo());
			
		} catch (Exception e) {
			setMensagem("Erro na Consulta");
			return ERROR;
		}

		return SUCCESS;
	}

	public String consultar(){
		return SUCCESS;
	}
}