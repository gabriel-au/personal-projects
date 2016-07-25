package br.com.xtrategia.fiscon.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.web.pojo.AtualizacaoPojo;
import br.com.xtrategia.fiscon.web.pojo.MenuPojo;
import br.com.xtrategia.fiscon.web.pojo.PerfilPojo;
import br.com.xtrategia.fiscon.web.pojo.UsuarioPojo;

public class LoginAction extends CRUDAction<UsuarioPojo>{

	private static final long serialVersionUID = 1L;
	
	private List<AtualizacaoPojo> listaAtualizacao;
	private List<PerfilPojo> listaPerfil;
	private String passwordWeb;
	private String passwordMob;

	@Override
	public boolean isLoggedInUserRequired() {
		return false;
	}
	
	public String login(){
		setMenus(null);
		try {
			pojo = (UsuarioPojo) getFachada().execute(pojo, "LoginUsuario");
			if(pojo==null){
				setMensagem("Usuário ou Senha inválidos!");
				limparSessao();
				return ERROR;
			}else{
				inserirSessao(pojo);
				//montar o mene
				List<MenuPojo> listaMenu = new ArrayList<MenuPojo>();
				
				Iterator<PerfilPojo> pesfis= pojo.getPerfilPojos().iterator();
				System.out.println("CRIOU NO LOGIN");
				while (pesfis.hasNext()) {
					PerfilPojo perfilPojo = pesfis.next();
				
					Iterator<MenuPojo> menus = perfilPojo.getMenuPojos().iterator();
					while (menus.hasNext()) {
						MenuPojo menuPojo = menus.next();
						if(!listaMenu.contains(menuPojo)){
							listaMenu.add(menuPojo);
						}
					}
					
					
				}
				
				Collections.sort(listaMenu);
				setMenus(listaMenu);
			}
			return "success";
			
		} catch (FISCONException e) {
			limparSessao();
			return ERROR;
		}
		
	}
	
	public String logout(){
		limparSessao();
		return SUCCESS;
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


}
