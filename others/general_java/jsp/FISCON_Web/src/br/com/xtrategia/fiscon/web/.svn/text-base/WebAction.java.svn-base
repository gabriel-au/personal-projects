package br.com.xtrategia.fiscon.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import br.com.xtrategia.fiscon.infra.NegocioFacade;
import br.com.xtrategia.fiscon.infra.NegocioFacadeImpl;
import br.com.xtrategia.fiscon.infra.TransferObject;
import br.com.xtrategia.fiscon.web.pojo.LogPojo;
import br.com.xtrategia.fiscon.web.pojo.MenuPojo;
import br.com.xtrategia.fiscon.web.pojo.PerfilPojo;
import br.com.xtrategia.fiscon.web.pojo.UsuarioPojo;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Superclasse para os <code>Action</code>
 * 
 * 
 */
public abstract class WebAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String mensagem;
	/**
	 * Nome ao qual são indexadas as mensagens passadas aos JSPs via request
	 */
	static public final String USUARIO_TRANSFER_OBJECT = "$usuario_transfer_object";
	static public final String LISTA_COMPRAS = "$lista_compras";

	private static final ThreadLocal<List<MenuPojo>> menuThreadLocal = new ThreadLocal<List<MenuPojo>>();

	/**
	 * ToDO colocar em JNDI caso haja a necessidade de distribuir a aplicação
	 */
	static private NegocioFacade fachada = new NegocioFacadeImpl();

	/**
	 * Utilitário de <i>logging </i>
	 */
	static private Log logging = LogFactory.getLog(WebAction.class);

	/**
	 * @return Instância da fachada
	 */
	static public NegocioFacade getFachada() {

		return WebAction.fachada;
	}

	/**
	 * Grava o log do acesso
	 */
	public void gravarLogAcesso(String acao) {
		HttpServletRequest request = ServletActionContext.getRequest();

		Logger.gravar(LogPojo.ACESSO_WEB, request.getRemoteHost(), getLogado()
				.getId(), acao);
	}

	/**
	 * Retorna o <code>UsuarioTo</code> com os dados do usuário que está logado
	 * em um dado momento
	 * 
	 * @param sessao
	 *            Sessao do usuário
	 * @return usuario
	 */
	public TransferObject getLogado() {
		HttpServletRequest request = ServletActionContext.getRequest();

		TransferObject retorno = null;

		if (request.getSession() != null) {

			retorno = (TransferObject) request.getSession().getAttribute(
					USUARIO_TRANSFER_OBJECT);
		}

		return retorno;
	}

	/**
	 * inserir o <code>UsuarioPojo</code> com os dados do usuário que está
	 * logado em um dado momento
	 * 
	 * @param pojo
	 *            Sessao do usuário
	 * @return usuario
	 */
	public void inserirSessao(TransferObject pojo) {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (request.getSession() != null) {
			request.getSession().setAttribute(USUARIO_TRANSFER_OBJECT, pojo);
			logging.info("usuário: " + pojo);
		}
	}

	/**
	 * inserir o <code>UsuarioPojo</code> com os dados do usuário que está
	 * logado em um dado momento
	 * 
	 * @param pojo
	 *            Sessao do usuário
	 * @return usuario
	 */
	public void limparSessao() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (request.getSession() != null) {
			request.getSession().invalidate();
			logging.info("Sessão limpa");
		}
	}

	/**
	 * Método que indica se o usuário deve estar logado para acessar a action
	 */
	public abstract boolean isLoggedInUserRequired();

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getUri() {
		return ServletActionContext.getRequest().getRequestURI();
	}

	public void setUri(String uri) {
	}

	public List<MenuPojo> getMenus() {
		if (menuThreadLocal != null && menuThreadLocal.get() != null) {
			System.out.println("PEGOU DO THREADLOCAL");
			return menuThreadLocal.get();
		} else {
			System.out.println("CRIOU");

			List<MenuPojo> listaMenu = new ArrayList<MenuPojo>();

			Iterator<PerfilPojo> pesfis = ((UsuarioPojo) getLogado())
					.getPerfilPojos().iterator();
			while (pesfis.hasNext()) {
				PerfilPojo perfilPojo = pesfis.next();

				Iterator<MenuPojo> menus = perfilPojo.getMenuPojos().iterator();
				while (menus.hasNext()) {
					MenuPojo menuPojo = menus.next();
					if (!listaMenu.contains(menuPojo)) {
						listaMenu.add(menuPojo);
					}
				}
			}
			menuThreadLocal.set(listaMenu);
			return listaMenu;
		}
	}

	public void setMenus(List<MenuPojo> menus) {
		menuThreadLocal.set(menus);
	}

}