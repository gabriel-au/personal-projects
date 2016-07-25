package br.com.xtrategia.fiscon.web;

import java.util.List;

import br.com.xtrategia.fiscon.FISCONException;
import br.com.xtrategia.fiscon.infra.TransferObject;

public abstract class CRUDAction<T extends TransferObject> extends WebAction {

	private static final long serialVersionUID = 1L;

	public T pojo = iniciarPojo();
	public List<T> lista;
	protected Long pagina=new Long(0);
	protected Long paginaAnterior=new Long(0);
	protected Long paginaProxima=new Long(0);
	protected Long paginaUltima=new Long(0);

	protected abstract T iniciarPojo();
	
	protected String commandListar="Listar";
	protected String commandQuantidadeItens="QuantidadeItens";
	
	@SuppressWarnings("unchecked")
	public String listar() {
		try {
			if (pagina == null || pagina.intValue()<1){
				pagina=new Long(1);
			}
			
			getPojo().setPagina(pagina);
			
			lista = (List<T>) getFachada().execute(getPojo(), commandListar)
					.getConteudo();
			
			if(lista==null || lista.size()==0){
				setMensagem("Nenhum Registro encontrado!");
				paginaUltima=0L;
				return SUCCESS;
			}
			
			paginaUltima=(Long) getFachada().execute(getPojo(), commandQuantidadeItens).getConteudo();
			
			paginaUltima= (paginaUltima/20)+1;
			paginaAnterior=pagina-1;
			if(paginaAnterior.intValue()<0){
				paginaAnterior=new Long(0);
			}
			paginaProxima=pagina+1;
			/*if(paginaProxima.intValue()>paginaUltima.intValue()){
				paginaProxima=paginaUltima;
			}*/
			
		} catch (FISCONException e) {
			setMensagem("Erro na Consulta");
			return ERROR;
		}

		return SUCCESS;
	}
	
	public String listarPagina() {
		
		listar();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String editar() {
		try {
			if (getPojo() != null && getPojo().getId() != null
					&& getPojo().getId() > 0) {
				setPojo((T) getFachada().execute(getPojo(), "ConsultarById"));
			} else {
				setPojo(iniciarPojo());
			}
		} catch (FISCONException e) {
			setMensagem("Erro na Consulta");
		}

		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String salvar() {
		try {
			if (getPojo() != null) {
				if (getPojo().getId() != null && getPojo().getId() > 0) {
					setPojo((T) getFachada().execute(getPojo(), "Alterar"));
				} else {
					setPojo((T) getFachada().execute(getPojo(), "Incluir"));
				}
			}
		} catch (FISCONException e) {
			setMensagem("Erro no update");
		}
		return listar();
	}

	@SuppressWarnings("unchecked")
	public String excluir() {
		try {
			if (getPojo() != null && getPojo().getId() != null) {
				setPojo((T) getFachada().execute(getPojo(), "Excluir"));
			} else {
				setPojo(null);
			}
		} catch (FISCONException e) {
			setPojo(null);
			setMensagem("Erro na Consulta");
		}
		return listar();
	}

	public List<T> getLista() {
		return lista;
	}

	public void setLista(List<T> lista) {
		this.lista = lista;
	}

	public T getPojo() {
		return pojo;
	}

	public void setPojo(T pojo) {
		this.pojo = pojo;
	}

	public Long getPagina() {
		return pagina;
	}

	public void setPagina(Long pagina) {
		this.pagina = pagina;
	}

	public Long getPaginaAnterior() {
		return paginaAnterior;
	}

	public void setPaginaAnterior(Long paginaAnterior) {
		this.paginaAnterior = paginaAnterior;
	}

	public Long getPaginaProxima() {
		return paginaProxima;
	}

	public void setPaginaProxima(Long paginaProxima) {
		this.paginaProxima = paginaProxima;
	}

	public Long getPaginaUltima() {
		return paginaUltima;
	}

	public void setPaginaUltima(Long paginaUltima) {
		this.paginaUltima = paginaUltima;
	}

}
