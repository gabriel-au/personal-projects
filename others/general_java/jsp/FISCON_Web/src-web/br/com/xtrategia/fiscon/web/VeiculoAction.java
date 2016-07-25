package br.com.xtrategia.fiscon.web;

import java.util.List;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.web.pojo.CategoriaPojo;
import br.com.xtrategia.fiscon.web.pojo.CorPojo;
import br.com.xtrategia.fiscon.web.pojo.EspeciePojo;
import br.com.xtrategia.fiscon.web.pojo.MarcaModeloPojo;
import br.com.xtrategia.fiscon.web.pojo.MunicipioPojo;
import br.com.xtrategia.fiscon.web.pojo.TipoPojo;
import br.com.xtrategia.fiscon.web.pojo.VeiculoPojo;

public class VeiculoAction extends CRUDAction<VeiculoPojo> {

	private static final long serialVersionUID = 1L;
	
	private static List<CorPojo> listaCor;
	private static List<CategoriaPojo> listaCategoria;
	private static List<MarcaModeloPojo> listaMarcaModelo;
	private static List<TipoPojo> listaTipo;
	private static List<MunicipioPojo> listaMunicipio;
	private static List<EspeciePojo> listaEspecie;

	@Override
	protected VeiculoPojo iniciarPojo() {
		return new VeiculoPojo();
	}

	@Override
	public boolean isLoggedInUserRequired() {
		return true;
	}
	
	public String exibir() {
		try {
			setPojo((VeiculoPojo) getFachada().execute(getPojo(), "ConsultarVeiculo"));
			
			if(getPojo()==null){
				setMensagem("Veículo não encontrado");
				return ERROR;	
			}
			
		} catch (FISCONException e) {
			setMensagem("Erro na Consulta");
			return ERROR;
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String editar() {
		try{
			Long quantCor = (Long) getFachada().execute(new CorPojo(), "QuantidadeItens").getConteudo();
			Long quantCategoria = (Long) getFachada().execute(new CategoriaPojo(), "QuantidadeItens").getConteudo();
			Long quantMarcaModelo = (Long) getFachada().execute(new MarcaModeloPojo(), "QuantidadeItens").getConteudo();
			Long quantTipo = (Long) getFachada().execute(new TipoPojo(), "QuantidadeItens").getConteudo();
			Long quantMunicipio = (Long) getFachada().execute(new MunicipioPojo(), "QuantidadeItens").getConteudo();
			Long quantEspecie = (Long) getFachada().execute(new EspeciePojo(), "QuantidadeItens").getConteudo();
			
			if(listaCor==null || listaCor.size()!=quantCor.intValue()){
				listaCor = (List<CorPojo>) getFachada().execute(new CorPojo(), "ListarCompleta").getConteudo();
			}
			if(listaCategoria==null || listaCategoria.size()!=quantCategoria.intValue()){
				listaCategoria = (List<CategoriaPojo>) getFachada().execute(new CategoriaPojo(), "ListarCompleta").getConteudo();
			}
			if(listaMarcaModelo==null || listaMarcaModelo.size()!=quantMarcaModelo.intValue()){
				listaMarcaModelo = (List<MarcaModeloPojo>) getFachada().execute(new MarcaModeloPojo(), "ListarCompleta").getConteudo();
			}
			if(listaTipo==null || listaTipo.size()!=quantTipo.intValue()){
				listaTipo = (List<TipoPojo>) getFachada().execute(new TipoPojo(), "ListarCompleta").getConteudo();
			}
			if(listaMunicipio==null || listaMunicipio.size()!=quantMunicipio.intValue()){
				listaMunicipio=  (List<MunicipioPojo>) getFachada().execute(new MunicipioPojo(), "ListarCompleta").getConteudo();
			}
			if(listaEspecie==null || listaEspecie.size()!=quantEspecie.intValue()){
				listaEspecie = (List<EspeciePojo>) getFachada().execute(new EspeciePojo(), "ListarCompleta").getConteudo();
			}
		} catch (FISCONException e) {
			setMensagem("Erro na Consulta");
		}
		return super.editar();
	}
	
	public String consultar(){
		return SUCCESS;
	}
	
	public List<CorPojo> getListaCor() {
		return listaCor;
	}

	public void setListaCor(List<CorPojo> listaCor) {
		VeiculoAction.listaCor = listaCor;
	}

	public List<CategoriaPojo> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<CategoriaPojo> listaCategoria) {
		VeiculoAction.listaCategoria = listaCategoria;
	}

	public List<MarcaModeloPojo> getListaMarcaModelo() {
		return listaMarcaModelo;
	}

	public void setListaMarcaModelo(List<MarcaModeloPojo> listaMarcaModelo) {
		VeiculoAction.listaMarcaModelo = listaMarcaModelo;
	}

	public List<TipoPojo> getListaTipo() {
		return listaTipo;
	}

	public void setListaTipo(List<TipoPojo> listaTipo) {
		VeiculoAction.listaTipo = listaTipo;
	}

	public List<MunicipioPojo> getListaMunicipio() {
		return listaMunicipio;
	}

	public void setListaMunicipio(List<MunicipioPojo> listaMunicipio) {
		VeiculoAction.listaMunicipio = listaMunicipio;
	}

	public List<EspeciePojo> getListaEspecie() {
		return listaEspecie;
	}

	public void setListaEspecie(List<EspeciePojo> listaEspecie) {
		VeiculoAction.listaEspecie = listaEspecie;
	}

}