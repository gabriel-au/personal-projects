package br.com.xtrategia.fiscon.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.web.pojo.AtualizacaoPojo;
import br.com.xtrategia.fiscon.web.pojo.PerfilPojo;
import br.com.xtrategia.fiscon.web.pojo.UsuarioPojo;

public class UsuarioAction extends CRUDAction<UsuarioPojo>{

	private static final long serialVersionUID = 1L;
	
	private List<AtualizacaoPojo> listaAtualizacao;
	private List<PerfilPojo> listaPerfil;
	private List<Integer> listaPerfilId;
	private String passwordWeb;
	private String passwordMob;

	@Override
	public boolean isLoggedInUserRequired() {
		return true;
	}

	@Override
	public String listar() {
		commandListar="ListarUsuario";
		commandQuantidadeItens="QuantidadeItensUsuario";
		return super.listar();
	}
	
	public String consultar(){
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String iniciar() {
		//pegar a lista de atualizações
		AtualizacaoPojo atualizacao = new AtualizacaoPojo();
		atualizacao.setQuantidadeRegistros(5);
		try {
			listaAtualizacao= (List<AtualizacaoPojo>) getFachada().execute(atualizacao, "AtualizacaoListar").getConteudo();
		} catch (FISCONException e) {
			setMensagem("Erro na Consulta");
		}
		
		pojo = (UsuarioPojo) getLogado();
		return (pojo!=null)?SUCCESS:ERROR;

	}
	
	public String logout(){
		limparSessao();
		return SUCCESS;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public String editar() {
		super.editar();
		try {
			listaPerfil= (List<PerfilPojo>) getFachada().execute(new PerfilPojo(), "Listar").getConteudo();
		} catch (FISCONException e) {
			setMensagem("Erro na Consulta");
		}
		
		listaPerfilId = new ArrayList<Integer>();
		
		if(pojo.getPerfilPojos()!=null){
			Iterator<PerfilPojo> inter = pojo.getPerfilPojos().iterator();
			while (inter.hasNext()) {
				PerfilPojo perfilPojo = inter.next();
				listaPerfilId.add(perfilPojo.getId());
			}
			
		}
		
		return SUCCESS;
	}
	
	@Override
	public String salvar() {
		boolean erro = false;
		if(!passwordMob.equals(pojo.getPasswordMob()) || passwordMob.equals("")){
			setMensagem("A Senha para uso em Celular é inválida");
			erro=true;
		}
		if(!passwordWeb.equals(pojo.getPasswordWeb())|| passwordWeb.equals("")){
			setMensagem("A Senha para uso em Celular é inválida");
			erro=true;
		}
		if(erro){
			editar();
			return ERROR;
		}
		if(listaPerfilId!=null && listaPerfilId.size()>0){
			pojo.setPerfilPojos(new HashSet<PerfilPojo>(0) );
			
			for(Integer i : listaPerfilId){
				pojo.getPerfilPojos().add(new PerfilPojo(i));
			}
		}
		
		return super.salvar();
	}

	@Override
	protected UsuarioPojo iniciarPojo() {
		return new UsuarioPojo();
	}


	public List<AtualizacaoPojo> getListaAtualizacao() {
		return listaAtualizacao;
	}


	public void setListaAtualizacao(List<AtualizacaoPojo> listaAtualizacao) {
		this.listaAtualizacao = listaAtualizacao;
	}


	public List<PerfilPojo> getListaPerfil() {
		return listaPerfil;
	}


	public void setListaPerfil(List<PerfilPojo> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}


	public String getPasswordWeb() {
		return passwordWeb;
	}


	public void setPasswordWeb(String passwordWeb) {
		this.passwordWeb = passwordWeb;
	}


	public String getPasswordMob() {
		return passwordMob;
	}


	public void setPasswordMob(String passwordMob) {
		this.passwordMob = passwordMob;
	}

	public List<Integer> getListaPerfilId() {
		return listaPerfilId;
	}

	public void setListaPerfilId(List<Integer> listaPerfilId) {
		this.listaPerfilId = listaPerfilId;
	}


}
