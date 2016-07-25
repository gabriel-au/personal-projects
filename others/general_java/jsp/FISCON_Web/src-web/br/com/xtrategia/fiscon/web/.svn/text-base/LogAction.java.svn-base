package br.com.xtrategia.fiscon.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.xtrategia.fiscon.web.pojo.LogPojo;
import br.com.xtrategia.fiscon.web.pojo.UsuarioPojo;

public class LogAction extends CRUDAction<LogPojo> {

	private static final long serialVersionUID = 1L;

	private Date date;
	private List<UsuarioPojo> listaUsuario;
	private String dataEvento;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	protected LogPojo iniciarPojo() {
		return new LogPojo();
	}

	@Override
	public boolean isLoggedInUserRequired() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public String consultar() {
		date = new Date();
		iniciarPojo();
		pojo.setDataEvento(date);
		try {

			listaUsuario = (List<UsuarioPojo>) getFachada().execute(
					new UsuarioPojo(), "ListarCompleta").getConteudo();
		} catch (Exception e) {
			setMensagem("Erro na Consulta");
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String listar() {

		try {
			if (pagina == null || pagina.intValue()<1){
				pagina=new Long(1);
			}
			
			getPojo().setPagina(pagina);
			
			dataEvento = sdf.format(getPojo().getDataEvento());
			
			lista = (List<LogPojo>) getFachada()
					.execute(getPojo(), "ListarLog").getConteudo();

			paginaUltima = (Long) getFachada().execute(getPojo(),
			"QuantidadeItensLog").getConteudo();

			paginaUltima = (paginaUltima / 20) + 1;
			paginaAnterior = pagina - 1;
			if (paginaAnterior.intValue() < 0) {
				paginaAnterior = new Long(0);
			}
			paginaProxima = pagina + 1;

		} catch (Exception e) {
			setMensagem("Erro na Consulta");
		}

		return SUCCESS;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<UsuarioPojo> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<UsuarioPojo> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public String getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(String dataEvento) {
		this.dataEvento = dataEvento;
	}

}